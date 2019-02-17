package com.spring.HomeService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.Home_DAO;
import com.spirng.DAO.chat_DTO;


public class insertChatDBService implements HomeService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		HttpSession session = request.getSession(true);
		
		String nickname = (String)session.getAttribute("userNickName");
		String id = (String)session.getAttribute("userId");
		String content = request.getParameter("input_chat_box");
		
		chat_DTO dto = new chat_DTO();
		
		dto.setUserId(id);
		dto.setUserNickname(nickname);
		dto.setContent(content);
		dto.setChattime(System.currentTimeMillis());//현재 시간을 long형태로 저장
		
		Home_DAO dao = new Home_DAO();
		
		dao.insertChatData(dto);
	}
}
