package com.spring.check_service;

import com.spirng.DAO.MemberDAO;

public class IdCheckService implements CheckService {
	@Override
	public int execute(String data) {
		MemberDAO dao = new MemberDAO();
		int val = dao.idCheck(data);
		return val;
	}
}
