package com.spirng.project;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.spirng.DAO.Tip_board_DAO;
import com.spirng.DAO.Tip_board_DTO;
import com.spring.TipBoardService.SaveTipBoardService;
import com.spring.TipBoardService.SearchTipBoardService;
import com.spring.TipBoardService.TipBoardService;
import com.spring.TipBoardService.ViewTipBoardService;
import com.spring.TipBoardService.deleteReplyBoardService;
import com.spring.TipBoardService.deleteTipBoardService;
import com.spring.TipBoardService.modifyReplyBoardService;
import com.spring.TipBoardService.modifyReplyViewService;
import com.spring.TipBoardService.modifyTipBoardService;
import com.spring.TipBoardService.saveReplyOfTipBoardService;
import com.spring.TipBoardService.seletTipBoardService;


@Controller
public class Tip_BoardController {

	private TipBoardService tip_board;
	
	//팁 게시판 리스트 보여줌
	@RequestMapping(value="/tip")//리스트 보기
	public String view_Tip_board(Model model, HttpServletRequest request) {	
		HttpSession session = request.getSession(true);	
		System.out.println("SYSTEM : View_Tip_board 실행");
		model.addAttribute("request", request);
		tip_board = new ViewTipBoardService();//객체 생성
		tip_board.execute(model);
		return "/Tip&Home/Tip_board";	
	}
	
	//검색결과 보여줌
	@RequestMapping(value="/search_Tip_board", method=RequestMethod.POST)//검색
	public String Post_Search_Tip_Board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new SearchTipBoardService();
		tip_board.execute(model);
		return "/Tip&Home/search_Tip_board";
	}
	
	//검색결과에서 페이지 이동시 페이지에 따라 나오는 리스트 보여줌
	@RequestMapping(value="/search_Tip_board", method=RequestMethod.GET)//검색
	public String Get_Search_Tip_Board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new SearchTipBoardService();
		tip_board.execute(model);
		return "/Tip&Home/search_Tip_board";
	}
	
	//글 쓰는 화면
	@RequestMapping("/write_Tip_board")
	public String write_view() {
		System.out.println("SYSTEM : Write_Tip_board 실행");
		return "/Tip&Home/Write_Tip_board";
	}
	
	//쓴 글 저장하는 기능
	@RequestMapping(value="/save_Tip_board", method=RequestMethod.POST)
	public String save_Tip_Board(MultipartHttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new SaveTipBoardService();
		tip_board.execute(model);		
		return "redirect:tip";
	}
	
	//글 중 하나 선택해서 상세 페이지로 가는 기능
	@RequestMapping(value="/select_Tip_board", method=RequestMethod.GET)
	public String select_Tip_Board(HttpServletRequest request, Model model) {
		request.removeAttribute("currentNum");
		model.addAttribute("request", request);
		tip_board = new seletTipBoardService();
		tip_board.execute(model);
		System.out.println("들어온 num:"+request.getParameter("num"));
		return "/Tip&Home/select_Tip_board";
	}
	
	//댓글 저장함
	@RequestMapping(value="/save_reply_board", method=RequestMethod.POST)
	public String save_reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new saveReplyOfTipBoardService();
		String num = request.getParameter("num");
		System.out.println("parameter : " + request.getParameter("num"));
		tip_board.execute(model);
		
		return "redirect:select_Tip_board?num="+num;
	}

	//글 수정 페이지 보여줌
	@RequestMapping(value="/modify_tip_board", method=RequestMethod.GET)
	public String viewModifyPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new seletTipBoardService();
		tip_board.execute(model);
		return "/Tip&Home/modify_tip_board";
	}
	
	//글 수정된 내용을 저장하는 기능
	@RequestMapping(value="/save_modi_tip_board", method=RequestMethod.POST)
	public String modify_tip_board(MultipartHttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new modifyTipBoardService();
		tip_board.execute(model);
		String num = request.getParameter("boardnumber");
		return "redirect:select_Tip_board?num="+num;
	}
	
	//글 삭제
	@RequestMapping(value="/delete_tip_board", method=RequestMethod.GET)
	public String delete_tip_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new deleteTipBoardService();
		tip_board.execute(model);
		return "redirect:tip";
	}
	
	//댓글 삭제
	@RequestMapping(value="/delete_reply_board")
	public String delete_reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		String num = request.getParameter("num");
		tip_board = new deleteReplyBoardService();
		tip_board.execute(model);
		return "redirect:select_Tip_board?num="+num;
	}
	
	//댓글 수정하는 화면을 띄워줌(새창에서 열림)//
	@RequestMapping(value="/modify_reply_view")
	public String modify_reply_View(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		tip_board = new modifyReplyViewService();
		tip_board.execute(model);
		
		return "/Tip&Home/modify_reply";
	}
	
	//댓글 수정후 저장하는 기능(창 종료 + 글 refresh)
	@RequestMapping(value="/modify_reply_board", method=RequestMethod.POST)
	public void modify_reply_board(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		
		tip_board = new modifyReplyBoardService();
		tip_board.execute(model);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<script>alert('정상적으로 수정되었습니다.'); opener.document.location.reload(); self.close();</script>"); 
		out.flush();
	}
	
	
	@RequestMapping("/tip_image")
	   public void tip_image(HttpServletRequest request, HttpServletResponse response) {
	      Tip_board_DAO dao = new Tip_board_DAO();
	      Tip_board_DTO dto = new Tip_board_DTO();
	      dto = dao.selectTipView((String)request.getParameter("num"));
	      try {
	         //Header에 파일 형식 셋팅.
	         response.setHeader("Content-Disposition", "inline;filename=\"" + dto.getImagename1() + "\"");
	         //response에 OutputStream 생성.
	         OutputStream outputStream = response.getOutputStream();
	         //contentType 셋팅
	         response.setContentType("image/jpeg");
	         
	         //Blob로부터 이미지를 가져와서 SerialBlob로 생성.
	         Blob image_vis = dto.getImage1();
	          byte[] bytes = image_vis.getBytes(1, (int)image_vis.length());
	         SerialBlob blob = new SerialBlob(bytes);
	         //Byte Stream을 outputStream 으로 복사.
	         //IOUtils는 io을 이동 복사 비교 하는 class
	         IOUtils.copy(blob.getBinaryStream(), outputStream);
	         
	         //다 읽을때까지 DAO에서 close하면 안됨.
	         outputStream.flush();
	         outputStream.close();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	@RequestMapping("/tip_image1")
	   public void tip_image1(HttpServletRequest request, HttpServletResponse response) {
	      Tip_board_DAO dao = new Tip_board_DAO();
	      Tip_board_DTO dto = new Tip_board_DTO();
	      dto = dao.selectTipView((String)request.getParameter("num"));
	      try {
	         //Header에 파일 형식 셋팅.
	         response.setHeader("Content-Disposition", "inline;filename=\"" + dto.getImagename2() + "\"");
	         //response에 OutputStream 생성.
	         OutputStream outputStream = response.getOutputStream();
	         //contentType 셋팅
	         response.setContentType("image/jpeg");
	         
	         //Blob로부터 이미지를 가져와서 SerialBlob로 생성.
	         Blob image_vis = dto.getImage2();
	          byte[] bytes = image_vis.getBytes(1, (int)image_vis.length());
	         SerialBlob blob = new SerialBlob(bytes);
	         //Byte Stream을 outputStream 으로 복사.
	         //IOUtils는 io을 이동 복사 비교 하는 class
	         IOUtils.copy(blob.getBinaryStream(), outputStream);
	         
	         //다 읽을때까지 DAO에서 close하면 안됨.
	         outputStream.flush();
	         outputStream.close();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	   }	
}
