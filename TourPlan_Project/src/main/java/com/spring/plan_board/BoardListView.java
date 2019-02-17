package com.spring.plan_board;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Map_DAO;
import com.spirng.DAO.Map_DTO;

public class BoardListView implements PlanboardService {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int term = 10;
		int start = 0;
		int totalPage = 0;
		
		if(request.getParameter("start") == null)
			start = 1;
		else {
			start = Integer.parseInt(request.getParameter("start"));
			System.out.println("system : plan Board List start (" + start + ")");
		}
		int pageCheck = (start-1)*(term-1);
		int startPoint = start+pageCheck;
		int endPoint = startPoint+(term-1);
		
		Map_DAO dao = new Map_DAO();
		ArrayList<Map_DTO> dtos = dao.getList(startPoint, endPoint);
		
		totalPage = dao.getListTotal();
		
		model.addAttribute("lists",dtos);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("term",term);
		model.addAttribute("start",start);
	}

}
