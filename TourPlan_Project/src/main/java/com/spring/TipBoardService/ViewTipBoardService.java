package com.spring.TipBoardService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.Tip_board_DAO;
import com.spirng.DAO.Tip_board_DTO;


public class ViewTipBoardService implements TipBoardService{
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		int term = 10;
		int start = 0;
		int totalPage = 0;

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		if(request.getParameter("start") == null)
			start = 1;
		else {
			start = Integer.parseInt(request.getParameter("start"));
		}
		
		int pageCheck = (start-1)*(term-1);
		int startPoint = start+pageCheck;
		int endPoint = startPoint+(term-1);
				
		Tip_board_DAO dao = new Tip_board_DAO();
		ArrayList<Tip_board_DTO> list =  dao.viewTipBoard(startPoint,endPoint);
		totalPage = dao.getTotalPageOfTipBoard();
		
		model.addAttribute("list", list);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("term", term);
		model.addAttribute("start", start);
		
		//===========================================
		HttpSession session = request.getSession(true);
		
		session.setAttribute("userId", "HeyJoon");
		session.setAttribute("userNickName", "Zoonie");
	}
}
