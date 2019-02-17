package com.spring.plan_board;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Map_DAO;
import com.spirng.DAO.Map_DTO;


public class Search_plan_board implements PlanboardService {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		int term = 10; // �Խñ� 10���� ����� �����ֱ�
		int start = 0;
		int totalPage = 0;
		
		String subject = request.getParameter("indexOfSearchQABoard"); //�׸�
		String word = request.getParameter("searchOfQABoard"); //�ܾ�
		
		if(request.getParameter("indexOfSearchQABoard") == null || request.getParameter("searchOfQABoard") == null) {
			subject = request.getParameter("subject");
			word = request.getParameter("word");
		}
		
		System.out.println("SYSTEM : �˻� �з� �� �ܾ� (" + subject + "/" + word + ")");
		
		if(request.getParameter("start") == null)
			start = 1;
		else {
			start = Integer.parseInt(request.getParameter("start"));
		}
		
		int pageCheck = (start-1)*(term-1);
		int startPoint = start+pageCheck;
		int endPoint = startPoint+(term-1);
		
		Map_DAO dao = new Map_DAO();
		ArrayList<Map_DTO> lists =  dao.searchPlanBoard(subject, word, startPoint, endPoint);
		totalPage = dao.searchTotalPageOfPlanBoard(subject, word);
		
		model.addAttribute("lists", lists);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("term", term);
		model.addAttribute("start", start);
		model.addAttribute("subject", subject);
		model.addAttribute("word", word);
	}

}
