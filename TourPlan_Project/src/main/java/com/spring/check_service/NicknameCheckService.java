package com.spring.check_service;

import com.spirng.DAO.MemberDAO;

public class NicknameCheckService implements CheckService {
	@Override
	public int execute(String data) {
		MemberDAO dao = new MemberDAO();
		int val = dao.nicknameCheck(data);
		return val;
	}
}
