package com.spring.memeber_init_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;


public class UserDeleteMemberService implements MemberIntService{
	@Override
	public int execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginId");
		MemberDAO dao = new MemberDAO();
		int val = dao.userDeleteMember(id);
		return val;
	}
}
