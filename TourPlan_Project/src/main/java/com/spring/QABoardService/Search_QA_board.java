package com.spring.QABoardService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.QA_board_DAO;
import com.spirng.DAO.QA_board_DTO;


public class Search_QA_board implements QA_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		int term = 10; // 게시글 10개로 나누어서 보여주기
		int start = 0;
		int totalPage = 0;
		
		String subject = request.getParameter("indexOfSearchQABoard"); //항목
		String word = request.getParameter("searchOfQABoard"); //단어
		
		if(request.getParameter("indexOfSearchQABoard") == null || request.getParameter("searchOfQABoard") == null) {
			subject = request.getParameter("subject");
			word = request.getParameter("word");
		}
		
		System.out.println("SYSTEM : 검색 분류 및 단어 (" + subject + "/" + word + ")");
		
		if(request.getParameter("start") == null)
			start = 1;
		else {
			start = Integer.parseInt(request.getParameter("start"));
		}
		
		int pageCheck = (start-1)*(term-1);
		int startPoint = start+pageCheck;
		int endPoint = startPoint+(term-1);
		
		QA_board_DAO dao = new QA_board_DAO();
		ArrayList<QA_board_DTO> lists =  dao.searchQABoard(subject, word, startPoint, endPoint);
		totalPage = dao.searchTotalPageOfQABoard(subject, word);
		
		model.addAttribute("lists", lists);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("term", term);
		model.addAttribute("start", start);
		model.addAttribute("subject", subject);
		model.addAttribute("word", word);
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
