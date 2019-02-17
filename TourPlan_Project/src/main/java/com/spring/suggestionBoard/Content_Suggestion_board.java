package com.spring.suggestionBoard;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Reply_board_DAO;
import com.spirng.DAO.Reply_board_DTO;
import com.spirng.DAO.Suggestion_board_DAO;
import com.spirng.DAO.Suggestion_board_DTO;


public class Content_Suggestion_board implements Suggestion_boardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		String num = request.getParameter("num");
		System.out.println("게시글 번호 : "+num); //게시글 번호
		
		int imgNum = 0;
		
		Suggestion_board_DAO dao = new Suggestion_board_DAO();
		Suggestion_board_DTO lists = dao.ViewContent_Suggestion_board(num);
		
		model.addAttribute("lists",lists);
		
		if(lists.getImagename1() != null)
			imgNum = 1;
		if(lists.getImagename2() != null)
			imgNum = 2;
		
		
		String board = "Suggestion_board"; //게시판 종류
		
		Reply_board_DAO dao02 = new Reply_board_DAO();
		ArrayList<Reply_board_DTO> abc = dao02.ViewReply_board(board, num);
		
		//댓글
		model.addAttribute("abc",abc);
		
		model.addAttribute("imgNum", imgNum);
		
		System.out.println("이미지 갯수 in sevice 파일 : " + imgNum);
		
		System.out.println("이미지 정보 1 : " + lists.getImagename1());
		System.out.println("이미지 정보 2 : " + lists.getImagename2());
		
		
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
