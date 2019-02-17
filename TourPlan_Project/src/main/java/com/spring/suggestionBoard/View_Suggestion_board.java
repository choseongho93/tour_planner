package com.spring.suggestionBoard;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Suggestion_board_DAO;
import com.spirng.DAO.Suggestion_board_DTO;

public class View_Suggestion_board implements Suggestion_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest) map.get("request");
		
		int term = 10; // �������� ���� �Խñ� ��
		int start = 0; // �����ִ� ������(��)
		int totalPage = 0; // �� ������ ��
		
		if(request.getParameter("start") == null) 
			start = 1; //start �޾ƿ°� ������ start 1�� ���ֱ�
		else { 
			start = Integer.parseInt(request.getParameter("start")); //����ȯ+start�ޱ�
		}
		
		int pageCheck = (start-1)*(term-1); //start,endpoint ������ִ� ��
		int startPoint = start+pageCheck; //���������� �����ϴ� �Խñ�
		int endPoint = startPoint+(term-1); //���������� ������ �Խñ�

		Suggestion_board_DAO dao = new Suggestion_board_DAO();
		ArrayList<Suggestion_board_DTO> lists = dao.ViewSuggestion_board(startPoint, endPoint);
		totalPage = dao.getTotalPageOfSuggestion();
				
		model.addAttribute("lists",lists);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("term",term);
		model.addAttribute("start",start);
		
	}

	@Override
	public int execute01(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int register(MultipartHttpServletRequest rquest) {
		// TODO Auto-generated method stub
		return 0;
	}



}
