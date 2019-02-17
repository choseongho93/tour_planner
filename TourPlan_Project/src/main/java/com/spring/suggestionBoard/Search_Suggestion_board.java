package com.spring.suggestionBoard;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Suggestion_board_DAO;
import com.spirng.DAO.Suggestion_board_DTO;


public class Search_Suggestion_board implements Suggestion_boardService{

	//검색
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		int term = 10;
		int start = 0;
		int totalPage = 0;
		
		//항목 , 단어 받기
		String subject = request.getParameter("indexOfSearchSuggestionBoard");
		String word = request.getParameter("searchOfSuggestionBoard");
		
		if(request.getParameter("indexOfSearchSuggestionBoard") == null || request.getParameter("searchOfSuggestionBoard")==null) {
			subject = request.getParameter("subject");
			word = request.getParameter("word");
		}
		System.out.println("SYSTEM : 검색 분류 및 단어 (" + subject + "/" + word + ")");
		
		//첫페이지->1
		if(request.getParameter("start")== null) 
			start = 1;
		else  {
			start = Integer.parseInt(request.getParameter("start"));
		}
		
		int pageCheck = (start-1)*(term-1);
		int startPoint = start+pageCheck; //페이지 시작하는 게시글
		int endPoint = startPoint+(term-1); //페이지 끝나는 게시글
		
		Suggestion_board_DAO dao = new Suggestion_board_DAO();
		ArrayList<Suggestion_board_DTO> lists = dao.searchSuggestionBoard(subject, word, startPoint, endPoint);
		totalPage = dao.searchTotalPageOfSuggestionBoard(subject, word);
		
		model.addAttribute("lists",lists);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("term",term);
		model.addAttribute("start",start);
		model.addAttribute("subject",subject);
		model.addAttribute("word",word);
	
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int register(MultipartHttpServletRequest rquest) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

}
