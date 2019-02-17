package com.spring.replyBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DAO;
import com.spirng.DAO.Reply_board_DTO;


public class View_Modify_Reply_board implements Reply_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest) map.get("request");
		int rpnum = Integer.parseInt(request.getParameter("rpnum"));
		System.out.println("´ñ±Û ¹øÈ£ : "+rpnum);
		
		Reply_board_DAO dao = new Reply_board_DAO();
		Reply_board_DTO lists = dao.ViewReply_board(rpnum);
		
		model.addAttribute("lists",lists);
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
