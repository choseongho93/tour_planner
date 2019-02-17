package com.spring.replyBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DAO;


public class Write_Reply_board implements Reply_boardService{

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
		
		String id = (String)session.getAttribute("UserId"); //아이디
		String nickName = (String)session.getAttribute("userNickName"); //닉네임
		String content = request.getParameter("content"); //내용
		content = content.replace("\r\n","<br>");
		//줄띄어쓰기 그대로 붙여 넣어짐
		String board = request.getParameter("board"); //어느 게시판
		int num = Integer.parseInt(request.getParameter("num")); //게시글 번호

		Reply_board_DAO dao = new Reply_board_DAO();
		int result = dao.RegisterReply_board(nickName, content, board, id, num);
		
		return result;
	}

}
