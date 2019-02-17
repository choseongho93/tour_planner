package com.spring.QABoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.QA_board_DAO;


public class ModifyContent_QA_board implements QA_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub\
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		QA_board_DAO dao = new QA_board_DAO();
		dao.ModifyContent_QA_board(title, content, num);
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
