package com.spring.TipBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DTO;
import com.spirng.DAO.Tip_board_DAO;


public class saveReplyOfTipBoardService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession(true);
		
		Reply_board_DTO dto = new Reply_board_DTO();
		
		dto.setBoard("tip_board");
		
		String content = request.getParameter("reply_board");
		content = content.replace("\r\n","<br>");
		
		dto.setContent(content);
		dto.setId((String)session.getAttribute("userId"));
		dto.setNickname((String)session.getAttribute("userNickName"));
		
		System.out.println("���� ���� : " + content);
		System.out.println("�Խñ� ��ȣ : " + request.getParameter("num"));
		System.out.println("����� ���̵� : " + (String)session.getAttribute("userId"));
		System.out.println("����� �г��� : " + (String)session.getAttribute("userNickName"));
		
		String num = request.getParameter("num");
		int parseNum = Integer.parseInt(num);
		
		dto.setNum(parseNum);		
		
		Tip_board_DAO dao = new Tip_board_DAO();
		dao.saveReplyIntoTipBoard(dto);
		
		//------------------------------------------------------
		
		session.setAttribute("currentNum", parseNum);
	}
}
