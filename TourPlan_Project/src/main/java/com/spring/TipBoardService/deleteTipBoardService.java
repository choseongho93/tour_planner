package com.spring.TipBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Tip_board_DAO;


public class deleteTipBoardService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String num = request.getParameter("num");
		
		System.out.println(num);
		
		Tip_board_DAO dao = new Tip_board_DAO();
		
		dao.deleteTipBoard(num);
	}
}
