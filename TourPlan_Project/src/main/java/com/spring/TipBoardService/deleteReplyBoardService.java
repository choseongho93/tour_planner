package com.spring.TipBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Tip_board_DAO;

public class deleteReplyBoardService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String rpnum = request.getParameter("rpnum");
		
		System.out.println("삭제할 댓글 번호 : " + rpnum);
		
		Tip_board_DAO dao = new Tip_board_DAO();
		
		dao.deleteReplyBoard(rpnum);
	}
}
