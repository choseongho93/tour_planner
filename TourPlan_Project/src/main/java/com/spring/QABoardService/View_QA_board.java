package com.spring.QABoardService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.QA_board_DAO;
import com.spirng.DAO.QA_board_DTO;


public class View_QA_board implements QA_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest) map.get("request");
		
		int term = 10;
		int start = 0;
		int totalPage = 0;
		
		if(request.getParameter("start") == null)
			start = 1;
		else {
			start = Integer.parseInt(request.getParameter("start"));
			System.out.println("system : start (" + start + ")");
		}
		int pageCheck = (start-1)*(term-1);
		int startPoint = start+pageCheck;
		int endPoint = startPoint+(term-1);
		
		QA_board_DAO dao = new QA_board_DAO(); 
		ArrayList<QA_board_DTO> lists = dao.ViewQA_board(startPoint,endPoint);
		totalPage =dao.getTotalPageOfTipBoard();
		
		model.addAttribute("lists",lists);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("term",term);
		model.addAttribute("start",start);
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
