package com.spirng.mapService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.spirng.DAO.Map_DAO;
import com.spirng.DAO.Map_DTO;

public class GetBestPlanService implements Map {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		java.util.Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		Map_DAO dao = new Map_DAO();
		ArrayList<Map_DTO> dtos = new ArrayList<>();
		dtos = dao.topTipBoard();
		
		model.addAttribute("Top_Travle_list", dtos);
	}

}
