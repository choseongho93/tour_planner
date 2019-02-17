package com.spring.TipBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.ui.Model;

import com.spirng.DAO.Tip_board_DAO;


public class modifyReplyBoardService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		Tip_board_DAO dao = new Tip_board_DAO();

	//	String content = request.getParameter("modifyReply");	
	//	System.out.println("�Է��� ���(content) : " + content);
	//	String rpnum = request.getParameter("rpnum");
	//	System.out.println("�Է��� ��� ��ȣ : " + rpnum);
	//	String num = request.getParameter("num");
	//	System.out.println("����� �ִ� �� ��ȣ : " + num);
	//	content = content.replace("\r\n","<br>");
		
		String content = request.getParameter("modifyTextReply");		
		String rpnum = request.getParameter("replynumber");
		String boardnumber = request.getParameter("boardnumber");
		
		System.out.println(rpnum + " : " + content);
		
		dao.modifyReplyBoard(rpnum, content);
		
		//model.addAttribute(attributeValue)
	}
}
