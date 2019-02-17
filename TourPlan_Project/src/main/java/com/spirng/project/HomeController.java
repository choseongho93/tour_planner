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

	//�Ϲ� Ȩ ȭ��
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("request", request);
		home_board = new getBestTipBoardService();//��ü ����
		home_board.execute(model);
		com.spirng.mapService.Map map_service = new GetBestPlanService();
		map_service.execute(model);
		
		return "/Tip&Home/home";
	}

	//ä�� ���� ��ư�� ��������.
	@ResponseBody//�����͸� �������°�
	@RequestMapping(value="/sendTheChat", method = RequestMethod.GET)
	public void SubmitChat(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception{
		Home_DAO dao = new Home_DAO();
		HttpSession session = request.getSession(true);
	
		String userId = (String)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		System.out.println(request.getParameter("input_chat_box"));//ä��â�� �Է��� ��
		System.out.println(userId + "/" + userNickName);//����� ����(���̵�/�г���)
		
		chat_DTO dto = new chat_DTO();
		
		dto.setUserId(userId);
		dto.setUserNickname(userNickName);
		dto.setContent(request.getParameter("input_chat_box"));
		dto.setChattime(System.currentTimeMillis());
		
		dao.insertChatData(dto);//ä�� ������ DB�� ����
		//====================================================
		Thread.sleep(100);
		//�߰����� ���� ��� ���� ���� �����ǰų� �ߺ����� ǥ�õǴ� ��찡 �߻��� �ð����� �ξ� �Է��� ���� �Բ� �������� ��.
		
		ArrayList<chat_DTO> chatList = new ArrayList<chat_DTO>();
		
		for(int i = 0 ; i < chatList.size() ; i++) {
			map.put(String.valueOf(i), chatList.get(i));
		}

		long currentTime = (Long)session.getAttribute("lastRefreshTime");//�����߰ų� ���� ��ħ ���� ���� refresh�ð�(long)
		
		chatList = dao.getChatBeforeNow(currentTime);//ä�� ���� ������(������ ���� ������ �������� �ð����� �ֱٽð������� ����)
		//�ִ��� ���ΰ�ħ �ϸ� homeȭ���� refresh�Ǹ鼭 ������ ����� ��� �������� ���̹Ƿ� �Ű澲�� �ʾƵ� ��.
		
		map.put("chat", chatList);//ä�� ����Ʈ�� chat�̶� key�� �����ص�
		map.put("userId", (String)session.getAttribute("userId"));//���� �� ������ Ȯ���ϱ� ���� �г����� �ƴ� id�� Ȯ��
		
		System.out.println("���� ���ǰ� : " + session.getAttribute("userId"));
	
		String callback = request.getParameter("callback");
  		ObjectMapper om = new ObjectMapper();
  		String json = om.writeValueAsString(map);
  		Writer out = response.getWriter();
  		out.append(callback).append("(").append(json).append(")");
	}
	
	//ä���� ��ü ������ �������� ���(�������� �ε�ɶ�, �ֱ������� refresh�ɶ� �߻��Ѵ�)
	@ResponseBody
	@RequestMapping(value="/getHistoryFirst", method = RequestMethod.GET)//�����͸� �������°�
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
		System.out.println("���� ���ǰ� : " + session.getAttribute("userId"));
	
		String callback = request.getParameter("callback");
  		ObjectMapper om = new ObjectMapper();
  		String json = om.writeValueAsString(map);
  		Writer out = response.getWriter();
  		out.append(callback).append("(").append(json).append(")");
	}
}
