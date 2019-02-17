package com.spring.member_dto_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;
import com.spirng.DAO.MemberDTO;


public class IdInquiryMemberService implements MemberDTOService{
	@Override
	public MemberDTO execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.idInquiryMember(name, birth);
		return dto;
	}
}
