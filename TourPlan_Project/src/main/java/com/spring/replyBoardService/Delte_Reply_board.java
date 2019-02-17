package com.spring.replyBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DAO;

public class Delte_Reply_board implements Reply_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest) map.get("request");
		String rpnum = request.getParameter("rpnum");
		System.out.println("´ñ±Û ¹øÈ£ : "+rpnum);
		Reply_board_DAO dao = new Reply_board_DAO();
		dao.Delte_Reply_board(rpnum);
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
