package com.spring.QABoardService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.QA_board_DAO;
import com.spirng.DAO.QA_board_DTO;
import com.spirng.DAO.Reply_board_DAO;
import com.spirng.DAO.Reply_board_DTO;


public class Content_QA_board implements QA_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		String num = request.getParameter("num");
		System.out.println("게시글 번호 : "+num); //게시글 번호
		
		QA_board_DAO dao = new QA_board_DAO();
		QA_board_DTO lists = dao.ViewContent_QA_board(num);
		
		model.addAttribute("lists",lists);
		
		String board = "QA_board"; //게시판 종류
		
		Reply_board_DAO dao01 = new Reply_board_DAO();
		ArrayList<Reply_board_DTO> abc = dao01.ViewReply_board(board, num);
		
		//댓글
		model.addAttribute("abc",abc);
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
