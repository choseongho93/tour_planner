package com.spring.memeber_init_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;
import com.spirng.DAO.MemberDTO;


public class RegistrationMemberService implements MemberIntService{
	@Override
	public int execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String birth, year, month, day;
		year = request.getParameter("birthYear");
		month = request.getParameter("birthMonth");
		day = request.getParameter("birthDay");
		if(day.length() == 1) {
			day = 0 + day;
		}
		birth = year + month + day;
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setGender(Integer.parseInt(request.getParameter("gender")));
		dto.setBirth(birth);
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setEmail(request.getParameter("email"));
		dto.setNickname(request.getParameter("id"));
		dto.setQuestion(Integer.parseInt(request.getParameter("question")));
		dto.setAnswer(request.getParameter("answer"));
		int val = dao.registrationMember(dto);
		return val;
	}
}
