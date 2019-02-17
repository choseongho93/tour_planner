package com.spring.QABoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.QA_board_DAO;


public class Write_QA_board implements QA_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		HttpSession session = request.getSession(); 
		
		String id = (String) session.getAttribute("userId");
		System.out.println("id : "+id);
		String nickName = (String) session.getAttribute("userNickName");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		content = content.replace("\r\n","<br>");
		//줄띄어쓰기 안하면 그대로 붙여 넣어짐
		
		QA_board_DAO dao = new QA_board_DAO();
		int result = dao.RegisterQA_board(id, nickName, title, content);
		
		return result;
	}

}
