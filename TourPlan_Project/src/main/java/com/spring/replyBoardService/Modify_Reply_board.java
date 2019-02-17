package com.spring.replyBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DAO;


public class Modify_Reply_board implements Reply_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String board = request.getParameter("board");
		
		int rpnum = Integer.parseInt(request.getParameter("rpnum"));
		System.out.println("댓글번호 rpnum : "+rpnum);
		String content = request.getParameter("content");
		System.out.println("내용 content : "+ content);
		
		Reply_board_DAO dao = new Reply_board_DAO();
		dao.Modify_Reply_board(rpnum, content);
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

}
