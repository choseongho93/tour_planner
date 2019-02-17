package com.spring.memeber_init_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;


public class UserInfoChangeMemberService implements MemberIntService {
	
	@Override
	public int execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		MemberDAO dao = new MemberDAO();
		String id = (String)session.getAttribute("loginId");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		int val = dao.userInfoChangeMember(id, email, nickname);
		return val;
	}
}
