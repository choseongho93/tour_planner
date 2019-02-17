package com.spirng.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spirng.DAO.Home_DAO;
import com.spirng.DAO.chat_DTO;
import com.spirng.mapService.GetBestPlanService;
import com.spring.HomeService.HomeService;
import com.spring.HomeService.getBestTipBoardService;

@Controller
public class HomeController {
	private HomeService home_board;
	private static String url = "jdbc:oracle:thin:@192.168.0.60:1521:xe";
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//일반 홈 화면
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("request", request);
		home_board = new getBestTipBoardService();//객체 생성
		home_board.execute(model);
		com.spirng.mapService.Map map_service = new GetBestPlanService();
		map_service.execute(model);
		
		return "/Tip&Home/home";
	}

	//채팅 전송 버튼을 눌렀을때.
	@ResponseBody//데이터를 가져오는거
	@RequestMapping(value="/sendTheChat", method = RequestMethod.GET)
	public void SubmitChat(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception{
		Home_DAO dao = new Home_DAO();
		HttpSession session = request.getSession(true);
	
		String userId = (String)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		System.out.println(request.getParameter("input_chat_box"));//채팅창에 입력한 값
		System.out.println(userId + "/" + userNickName);//사용자 정보(아이디/닉네임)
		
		chat_DTO dto = new chat_DTO();
		
		dto.setUserId(userId);
		dto.setUserNickname(userNickName);
		dto.setContent(request.getParameter("input_chat_box"));
		dto.setChattime(System.currentTimeMillis());
		
		dao.insertChatData(dto);//채팅 내용을 DB에 저장
		//====================================================
		Thread.sleep(100);
		//추가하지 않을 경우 현재 글이 누락되거나 중복으로 표시되는 경우가 발생해 시간차를 두어 입력한 글이 함께 나오도록 함.
		
		ArrayList<chat_DTO> chatList = new ArrayList<chat_DTO>();
		
		for(int i = 0 ; i < chatList.size() ; i++) {
			map.put(String.valueOf(i), chatList.get(i));
		}

		long currentTime = (Long)session.getAttribute("lastRefreshTime");//접속했거나 새로 고침 했을 때의 refresh시간(long)
		
		chatList = dao.getChatBeforeNow(currentTime);//채팅 내용 가져옴(내용은 위의 마지막 리프레시 시간에서 최근시간까지로 잡음)
		//애당초 새로고침 하면 home화면이 refresh되면서 기존의 기록이 모두 가져와질 것이므로 신경쓰지 않아도 됨.
		
		map.put("chat", chatList);//채팅 리스트를 chat이란 key로 매핑해둠
		map.put("userId", (String)session.getAttribute("userId"));//내가 쓴 글인지 확인하기 위해 닉네임이 아닌 id로 확인
		
		System.out.println("유저 세션값 : " + session.getAttribute("userId"));
	
		String callback = request.getParameter("callback");
  		ObjectMapper om = new ObjectMapper();
  		String json = om.writeValueAsString(map);
  		Writer out = response.getWriter();
  		out.append(callback).append("(").append(json).append(")");
	}
	
	//채팅의 전체 내역을 가져오는 기능(페이지가 로드될때, 주기적으로 refresh될때 발생한다)
	@ResponseBody
	@RequestMapping(value="/getHistoryFirst", method = RequestMethod.GET)//데이터를 가져오는거
	public void getHistory(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {	
		ArrayList<chat_DTO> chatList = new ArrayList<chat_DTO>();
		Home_DAO dao = new Home_DAO();
		
		for(int i = 0 ; i < chatList.size() ; i++) {
			map.put(String.valueOf(i), chatList.get(i));
		}
		
		chatList = dao.getChatHistory();
		map.put("chat", chatList);
		
		HttpSession session = request.getSession(true);	
		map.put("userId", (String)session.getAttribute("userId"));
		System.out.println("유저 세션값 : " + session.getAttribute("userId"));
	
		String callback = request.getParameter("callback");
  		ObjectMapper om = new ObjectMapper();
  		String json = om.writeValueAsString(map);
  		Writer out = response.getWriter();
  		out.append(callback).append("(").append(json).append(")");
	}
}
