package com.spring.TipBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Reply_board_DTO;
import com.spirng.DAO.Tip_board_DAO;


public class modifyReplyViewService implements TipBoardService{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String num = request.getParameter("num");//°ª °¡Á®¿È
		String rpnum = request.getParameter("rpnum");
		
		Reply_board_DTO dto = new Reply_board_DTO();
		Tip_board_DAO dao = new Tip_board_DAO();
		dto = dao.modifiyReplyView(rpnum);
		
		model.addAttribute("modify_Reply", dto);
		model.addAttribute("boardNumber", num);
	}
}
