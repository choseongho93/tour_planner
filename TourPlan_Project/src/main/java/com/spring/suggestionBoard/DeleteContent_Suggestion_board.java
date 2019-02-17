package com.spring.suggestionBoard;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Suggestion_board_DAO;


public class DeleteContent_Suggestion_board implements Suggestion_boardService {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest) map.get("request");
		String num = request.getParameter("num");
		
		Suggestion_board_DAO dao = new Suggestion_board_DAO();
		dao.DeleteContent_Suggestion_board(num);
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int register(MultipartHttpServletRequest rquest) {
		return 0;
		// TODO Auto-generated method stub
		
	}



}
