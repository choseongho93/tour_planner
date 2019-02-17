package com.spring.TipBoardService;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DTO;
import com.spirng.DAO.Tip_board_DAO;
import com.spirng.DAO.Tip_board_DTO;

public class seletTipBoardService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String num = request.getParameter("num");//값 가져옴
		
		int imgNum = 0;
		
		Tip_board_DAO dao = new Tip_board_DAO();
		Tip_board_DTO dto = dao.selectTipView(num);
		ArrayList<Reply_board_DTO> rp_list = dao.getReplyOfTip(num);
		
		if(dto.getImagename1() != null)
			imgNum = 1;
		if(dto.getImagename2() != null)
			imgNum = 2;
		
		System.out.println("rp_list 길이 : " + rp_list.size());
		System.out.println("이미지 갯수 in sevice 파일 : " + imgNum);
		
		System.out.println("이미지 정보 1 : " + dto.getImagename1());
		System.out.println("이미지 정보 2 : " + dto.getImagename2());
		
		//=========================================================blob를 바이트스트림으로 변환.
	/*	Blob blob1 = dto.getImage1();
		Blob blob2 = dto.getImage2();

		int blobLength1, blobLength2;
		try {
			blobLength1 = (int) blob1.length();
			blobLength2 = (int) blob2.length();
			byte[] blobAsBytes1 = blob1.getBytes(1, blobLength1);
			byte[] blobAsBytes2 = blob2.getBytes(1, blobLength2);
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			try {
				blob1.free();
				blob2.free();
			}catch(Exception e) {e.printStackTrace();}
		}*/
		//=========================================================
		
		
		
		model.addAttribute("select_Tip_board",dto);
		model.addAttribute("reply_list",rp_list);
		model.addAttribute("imgNum", imgNum);
	}
}
