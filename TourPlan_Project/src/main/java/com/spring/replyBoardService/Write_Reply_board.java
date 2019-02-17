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
		
		String id = (String)session.getAttribute("UserId"); //���̵�
		String nickName = (String)session.getAttribute("userNickName"); //�г���
		String content = request.getParameter("content"); //����
		content = content.replace("\r\n","<br>");
		//�ٶ��� �״�� �ٿ� �־���
		String board = request.getParameter("board"); //��� �Խ���
		int num = Integer.parseInt(request.getParameter("num")); //�Խñ� ��ȣ

		Reply_board_DAO dao = new Reply_board_DAO();
		int result = dao.RegisterReply_board(nickName, content, board, id, num);
		
		return result;
	}

}
