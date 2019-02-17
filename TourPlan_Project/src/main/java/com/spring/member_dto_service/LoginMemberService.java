package com.spring.member_dto_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;
import com.spirng.DAO.MemberDTO;


public class LoginMemberService implements MemberDTOService {
	@Override
	public MemberDTO execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.loginMember(id, pwd);
		return dto;
	}
}
