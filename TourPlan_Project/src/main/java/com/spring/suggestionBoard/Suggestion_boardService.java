package com.spring.suggestionBoard;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Suggestion_boardService {

	public void execute(Model model);
	
	public int execute01(Model model);
	
	public int register(MultipartHttpServletRequest rquest) throws FileNotFoundException;


}
