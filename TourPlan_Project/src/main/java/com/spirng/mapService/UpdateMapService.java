package com.spirng.mapService;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.spirng.DAO.Map_DAO;
import com.spirng.DAO.Map_DTO;

public class UpdateMapService implements Map {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		java.util.Map<String, Object> map = model.asMap();
		java.util.Map<String, Object> data = (java.util.Map<String, Object>) map.get("map");
		
		Map_DTO dto = new Map_DTO();
		dto.setId((String)data.get("id"));
		dto.setNum((String)data.get("number"));
		System.out.println(dto.getNum());
		dto.setNickname((String)data.get("nickname"));
		dto.setSchedule((String)data.get("day"));
		
		System.out.println((String)data.get("textArea"));
		String[] contents = ((String)data.get("textArea")).split("/");
		ArrayList<String> contentArray = new ArrayList<>();
		for(int i=0; i<contents.length;i++) {
			System.out.println(contents[i]);
			contentArray.add(contents[i]);
		}
		if(contentArray.size()!=10) {
			for(int i = contents.length;i<=10;i++) {
				contentArray.add("");
			}
		}
		dto.setContent_1(contentArray.get(0));
		dto.setContent_2(contentArray.get(1));
		dto.setContent_3(contentArray.get(2));
		dto.setContent_4(contentArray.get(3));
		dto.setContent_5(contentArray.get(4));
		dto.setContent_6(contentArray.get(5));
		dto.setContent_7(contentArray.get(6));
		dto.setContent_8(contentArray.get(7));
		dto.setContent_9(contentArray.get(8));
		dto.setContent_10(contentArray.get(9));
		
		dto.setCost((String)data.get("cost"));
		dto.setTitle((String)data.get("title"));
		dto.setMarker((String)data.get("marker"));
		dto.setUse_time((String)data.get("use_time"));
		
		Map_DAO dao = new Map_DAO();
		dao.updateMap(dto);
	}

}
