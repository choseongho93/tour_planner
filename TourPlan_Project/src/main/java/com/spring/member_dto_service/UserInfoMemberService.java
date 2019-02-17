package com.spring.member_dto_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;
import com.spirng.DAO.MemberDTO;

public class UserInfoMemberService implements MemberDTOService {
	@Override
	public MemberDTO execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		String id = (String)session.getAttribute("loginId");
		String pwd = request.getParameter("pwd");
		dto.setId(id);
		dto.setPwd(pwd);
		return dao.userCheck(dto);
	}
}
