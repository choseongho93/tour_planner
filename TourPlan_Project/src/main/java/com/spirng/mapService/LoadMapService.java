package com.spirng.mapService;

import org.springframework.ui.Model;

import com.spirng.DAO.Map_DAO;
import com.spirng.DAO.Map_DTO;

public class LoadMapService implements Map {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map_DTO dto = new Map_DTO();
		Map_DAO dao = new Map_DAO();
		java.util.Map<String, Object> map = model.asMap();
		String num = (String)map.get("num");
		dto = dao.selectDTO(num);
		model.addAttribute("dto", dto);
	}

}
