package com.spring.suggestionBoard;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Suggestion_board_DAO;
import com.spirng.DAO.Suggestion_board_DTO;

public class View_Suggestion_board implements Suggestion_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest) map.get("request");
		
		int term = 10; // 보여지고 싶은 게시글 수
		int start = 0; // 현재있는 페이지(쪽)
		int totalPage = 0; // 총 페이지 수
		
		if(request.getParameter("start") == null) 
			start = 1; //start 받아온게 없으면 start 1로 해주기
		else { 
			start = Integer.parseInt(request.getParameter("start")); //형변환+start받기
		}
		
		int pageCheck = (start-1)*(term-1); //start,endpoint 계산해주는 것
		int startPoint = start+pageCheck; //페이지에서 시작하는 게시글
		int endPoint = startPoint+(term-1); //페이지에서 마지막 게시글

		Suggestion_board_DAO dao = new Suggestion_board_DAO();
		ArrayList<Suggestion_board_DTO> lists = dao.ViewSuggestion_board(startPoint, endPoint);
		totalPage = dao.getTotalPageOfSuggestion();
				
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

	@Override
	public int register(MultipartHttpServletRequest rquest) {
		// TODO Auto-generated method stub
		return 0;
	}



}
