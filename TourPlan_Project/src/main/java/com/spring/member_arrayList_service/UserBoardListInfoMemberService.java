package com.spring.member_arrayList_service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.BoardDAO;
import com.spirng.DAO.BoardDTO;

public class UserBoardListInfoMemberService implements MemberArrayListService {
	@Override
	public ArrayList<BoardDTO> execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		String boardNum = request.getParameter("boardNum");
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		if (boardNum.equals("1")) boardList = dao.qaBoard(id);
		else if (boardNum.equals("2")) boardList = dao.suqqestionBoard(id);
		else if (boardNum.equals("3")) boardList = dao.tipBoard(id);
		else if (boardNum.equals("4")) boardList = dao.planBoard(id);
		else if (boardNum.equals("5")) boardList = dao.basketBoard(id);
		else boardList = dao.replyBoard(id);
		return boardList;
	}
}
