package com.spring.HomeService;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.Home_DAO;
import com.spirng.DAO.Tip_board_DTO;

public class getBestTipBoardService implements HomeService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String subject = request.getParameter("subject");
				
		Home_DAO dao = new Home_DAO();
		ArrayList<Tip_board_DTO> list =  dao.topTipBoard();
		
		System.out.println("√— ±Ê¿Ã : " + list.size());
		
		model.addAttribute("Top_Tip_list", list);
		//model.addAttribute("subject",subject);
		//¿œ¥‹¿∫ ∆¡ ¡§∫∏∏∏..

	}
}
