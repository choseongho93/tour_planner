package com.spring.TipBoardService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Tip_board_DAO;
import com.spirng.DAO.Tip_board_DTO;


public class SearchTipBoardService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
	// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		int term = 10;
		int start = 0;
		int totalPage = 0;
		
		String subject = request.getParameter("indexOfSearchTipBoard");
		String word = request.getParameter("searchOfTipBoard");
		
		if(request.getParameter("indexOfSearchTipBoard") == null || request.getParameter("searchOfTipBoard") == null) {
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
		
		Tip_board_DAO dao = new Tip_board_DAO();
		ArrayList<Tip_board_DTO> lists =  dao.searchTipBoard(subject, word, startPoint, endPoint);
		totalPage = dao.searchTotalPageOfTipBoard(subject, word);
		
		model.addAttribute("list", lists);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("term", term);
		model.addAttribute("start", start);
		model.addAttribute("subject", subject);
		model.addAttribute("word", word);
	}
}
