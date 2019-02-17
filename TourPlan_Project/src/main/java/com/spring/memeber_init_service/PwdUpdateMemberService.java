package com.spring.memeber_init_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.MemberDAO;


public class PwdUpdateMemberService implements MemberIntService {
	@Override
	public int execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDAO dao = new MemberDAO();
		int val = dao.pwdUpdateMember(id, pwd);
		return val;
	}

}
