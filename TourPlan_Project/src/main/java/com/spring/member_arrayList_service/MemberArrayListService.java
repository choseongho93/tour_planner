package com.spring.member_arrayList_service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.spirng.DAO.BoardDTO;


public interface MemberArrayListService {
	public ArrayList<BoardDTO> execute(Model model);
}
