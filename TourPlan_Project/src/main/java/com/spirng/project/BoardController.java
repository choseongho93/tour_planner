package com.spirng.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spirng.DAO.Suggestion_board_DAO;
import com.spirng.DAO.Suggestion_board_DTO;
import com.spring.QABoardService.QA_boardService;
import com.spring.QABoardService.Search_QA_board;
import com.spring.QABoardService.View_QA_board;
import com.spring.replyBoardService.Delte_Reply_board;
import com.spring.replyBoardService.Reply_boardService;
import com.spring.replyBoardService.View_Modify_Reply_board;
import com.spring.replyBoardService.Write_Reply_board;
import com.spring.suggestionBoard.Search_Suggestion_board;
import com.spring.suggestionBoard.Suggestion_boardService;
import com.spring.suggestionBoard.View_Suggestion_board;
import com.spring.suggestionBoard.Write_Suggestion_board;

@Controller
public class BoardController {
	
	private QA_boardService qa;
	//private LoginService login;
	private Reply_boardService re;
	private Suggestion_boardService su;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	/*
	//1.로그인  화면 보여주기
	@RequestMapping(value="/login")
	public String login() {
		System.out.println("로그인 화면 보여주기");
		return "/board/login";
	}
	//2.로그인 아이디 비밀번호 체크
	@RequestMapping(value="/CheckLogin",method=RequestMethod.POST)
	public String ChechkLogin(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		login = new CheckLogin();
		int result = login.execute(model);
		
		System.out.println("로그인 체크");
		
		if(result == 0) {
			HttpSession session = request.getSession();
			session.setAttribute("UserID", request.getParameter("id"));
			System.out.println("session 성공");
			return "/board//login";
		}else 
			return "redirect:login";
	}
	//4.로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		login = new Logout();
		login.execute01(model);
		System.out.println("logout실행&session 삭제");
		
		return "/board/login";
	}*/
	//5.QA게시판 보여주기
	@RequestMapping(value="/QA_board")
	public String QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		qa = new View_QA_board();
		qa.execute(model);
		
		System.out.println("QA_board 실행");
		
		return "/board/QA_board";
	}
	//6.QA게시판 안보여주기
	@RequestMapping(value="NO_QA_board")
	public String No_QA_board() {
		System.out.println("로그인 하세요");
		
		return "redirect:login";
	}
	//7.QA게시판 글 등록하기
	@RequestMapping(value="Chk_Write_QA_board",method=RequestMethod.POST)
	public String Chk_Write_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		qa = new com.spring.QABoardService.Write_QA_board();
		int result = qa.execute01(model);
		
		if(result==1) {
			System.out.println("QA게시판 글 등록 성공");
			return "redirect:QA_board";
		}else {
			System.out.println("QA게시판 글 등록 실패");
			return "redirect:QA_board";
		}
	}
	//8.QA게시판 글쓰기 보여주기
	@RequestMapping(value="Write_QA_board")
	public String Write_QA_board() {
		System.out.println("QA게시판 글쓰기 실행");
		
		return "/board/Write_QA_board";
	}
	//9.QA게시판 글->내용 보여주기&댓글 보여주기
	@RequestMapping(value="Content_QA_board", method=RequestMethod.GET)
	public String Content_QA_board(Model model, HttpServletRequest request) {
		System.out.println("QA게시판 내용 실행");
		model.addAttribute("request",request);
		
		qa = new com.spring.QABoardService.Content_QA_board();
		qa.execute(model);
		
		return "/board/Content_QA_board";
	}
	
	//6.QA게시판 수정페이지 보여주기
	@RequestMapping(value="modify_QA_board", method= RequestMethod.GET)
	public String ModifyContent_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
				
		qa = new com.spring.QABoardService.Content_QA_board();
		qa.execute(model);
				
		return "/board/modify_QA_board";
			}
	
	//10.QA게시글 수정
	@RequestMapping(value="Save_modify_QA_board", method=RequestMethod.POST)
	public String Modify_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		qa = new com.spring.QABoardService.ModifyContent_QA_board();
		qa.execute(model);
		String num = request.getParameter("num");
		
		return "redirect:Content_QA_board?num="+num;
	}
	//11.QA게시글 삭제
	@RequestMapping(value="DeleteContent_QA_board",method=RequestMethod.GET)
	public String DeleteContent_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		qa = new com.spring.QABoardService.DeleteContent_QA_board();
		qa.execute(model);
		
		return "redirect:QA_board";
	}
	//12. QA게시글 검색
	//검색결과 보여줌
		@RequestMapping(value="/search_QA_board", method=RequestMethod.POST)//검색
		public String Post_Search_QA_Board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			qa = new Search_QA_board();
			qa.execute(model);
			
			return "/board/Search_QA_board";
		}
		
	//13. 검색결과에서 페이지 이동시 페이지에 따라 나오는 리스트 보여줌
		@RequestMapping(value="/search_QA_board", method=RequestMethod.GET)//검색
		public String Get_Search_QA_Board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			qa = new Search_QA_board();
			qa.execute(model);
			
			return "/board/Search_QA_board";
		}	
	//------------------------------------------------------------------------------------------------------
	
	//1.QA 게시판 댓글 등록
	@RequestMapping(value="Write_Reply_board",method=RequestMethod.POST)
	public String Chk_Write_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		re = new Write_Reply_board();
		//댓글 등록하고 바로 num게시판으로 보여주기
		int result = re.execute01(model);
		String num = request.getParameter("num");
		
		if(result==1) {
			System.out.println("QA게시판 댓글 등록 성공");
			return "redirect:Content_QA_board?num="+num;
		}else {
			System.out.println("QA게시판 댓글 등록 실패");
			return "redirect:QA_board";
		}
	}
	//2.QA 게시판 댓글 삭제
	@RequestMapping(value="Delete_Reply_board",method=RequestMethod.GET)
	public String Delete_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		re = new Delte_Reply_board();
		re.execute(model);
		
		String num = request.getParameter("num");
		System.out.println(num);
		
		return "redirect:Content_QA_board?num="+num;
	}
	//3.QA게시판 댓글 수정
	@RequestMapping(value="Modify_Reply_board", method= {RequestMethod.GET, RequestMethod.POST})
	public void Modify_Reply_board(HttpServletRequest request , Model model, HttpServletResponse response) {
		model.addAttribute("request", request);
	//	String num = request.getParameter("num");
		
		re = new com.spring.replyBoardService.Modify_Reply_board();
		re.execute(model);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		String num = request.getParameter("num");
		String board = request.getParameter("board");
		System.out.println("게시판 : "+board);
		System.out.println("게시글 번호 : "+num);
		
		try {
			out = response.getWriter();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.println("<script>alert('정상적으로 수정되었습니다.'); opener.document.location.reload(); self.close();</script>");
		out.flush();
		/*
		if(board.equals("Suggestion_board")) {
			System.out.println("Suggestion 게시판 댓글 수정");
			return "redirect:Content_Suggestion_board?num="+num;
		}else {
			System.out.println("QA게시판 댓글 수정");
			return "redirect:Content_QA_board?num="+num;
		}
		*/
	}
	
	//4.Reply 게시판 보여주기 & 댓글 내용 보여주기 (수정)
	@RequestMapping(value="View_Modify_Reply_board",method=RequestMethod.GET)
	public String Reply_board(Model model,HttpServletRequest request) {
		System.out.println("댓글 내용 게시판 실행");
		model.addAttribute("request", request);
		
		re = new View_Modify_Reply_board();
		re.execute(model);
		
		return "/board/Modify_Reply_board";
	}
	//-------------------------------------------------------------------------------------------
	
	//1.건의게시판 안보여주기
	@RequestMapping(value="No_Suggestion_board")
	public String No_Suggestion_board() {
		System.out.println("로그인 하세요");
		
		return "redirect:login";
	}
	
	//2.건의 게시판 글 보여주기
	@RequestMapping(value="/Suggestion_board")
	public String Suggestion_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		su = new View_Suggestion_board();
		su.execute(model);
		
		System.out.println("Suggestion_board");
		
		return "/board/Suggestion_board";
	}
	
	//3.건의게시판 글쓰기 보여주기
		@RequestMapping(value="Write_Suggestion_board")
		public String Write_Suggestion_board() {
			System.out.println("건의게시판 글쓰기 실행");
			
			return "/board/Write_Suggestion_board";
		}
	
	//4.Suggestion 게시판 글 등록하기
	@RequestMapping(value="ChkWrite_Suggestion_board",method=RequestMethod.POST)
	public String ChkWrite_Suggestion_board(MultipartHttpServletRequest request, Model model){
				
		model.addAttribute("request", request);
		su = new Write_Suggestion_board();
		su.execute(model);

		return "redirect:Suggestion_board";
	}

	//5.Suggestion 게시판 글->내용 보여주기&댓글 보여주기
		@RequestMapping(value="Content_Suggestion_board", method=RequestMethod.GET)
		public String Content_Suggestion_board(Model model, HttpServletRequest request) {
			System.out.println("Suggestion 게시판 내용 실행");
			model.addAttribute("request",request);
			
			su = new com.spring.suggestionBoard.Content_Suggestion_board();
			su.execute(model);
			
 			return "/board/Content_Suggestion_board";
		}
	//6.suggestion 게시글 삭제
		@RequestMapping(value="DeleteContent_Suggestion_board",method=RequestMethod.GET)
		public String DeleteContent_Suggestion_board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			
			su = new com.spring.suggestionBoard.DeleteContent_Suggestion_board();
			su.execute(model);
			
			return "redirect:Suggestion_board";
		}
	//7.suggestion 게시글 수정 페이지 보여주기
		@RequestMapping(value="Modify_Suggestion_board", method= RequestMethod.GET)
		public String ModifyContent_Suggestion_board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			
			su = new com.spring.suggestionBoard.Content_Suggestion_board();
			su.execute(model);
			
			return "/board/Modify_SB";
		}
		
		//글 수정된 내용을 저장하는 기능
		@RequestMapping(value="/save_modi_Suggestion_board", method=RequestMethod.POST)
		public String modify_Suggestion_board(MultipartHttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			su = new com.spring.suggestionBoard.ModifyContent_Suggestion_board();
			su.execute(model);
			
			String num = request.getParameter("num");
			return "redirect:Suggestion_board?num="+num;
		}
		
		//8. Suggestion게시글 검색
		//검색결과 보여줌
			@RequestMapping(value="/search_Suggestion_board", method=RequestMethod.POST)//검색
			public String Post_Search_SuGGESTION_Board(HttpServletRequest request, Model model) {
				model.addAttribute("request", request);
				su = new Search_Suggestion_board();
				su.execute(model);
				
				return "/board/Search_Suggestion_board";
			}
			
		//9. 검색결과에서 페이지 이동시 페이지에 따라 나오는 리스트 보여줌
			@RequestMapping(value="/search_Suggestion_board", method=RequestMethod.GET)//검색
			public String Get_Search_Suggestion_Board(HttpServletRequest request, Model model) {
				model.addAttribute("request", request);
				su = new Search_Suggestion_board();
				su.execute(model);
				
				return "/board/Search_Suggestion_board";
			}
	//-------------------------------------------------------------------------------------------------
	
	//1.Suggestion 게시판 댓글 등록
	@RequestMapping(value="SU_Write_Reply_board",method={RequestMethod.GET, RequestMethod.POST})
	
	public String Su_Write_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		re = new Write_Reply_board();
		//댓글 등록하고 바로 num게시판으로 보여주기
		int result = re.execute01(model);
		String num = request.getParameter("num");
			
		if(result==1) {
			System.out.println("Suggestion 게시판 댓글 등록 성공");
			return "redirect:Content_Suggestion_board?num="+num;
		}else {
			System.out.println("Suggestion 게시판 댓글 등록 실패");
			return "redirect:Suggestion_board";
		}
	}	
	
	//2.Suggestion 게시판 댓글 수정
	@RequestMapping(value="Su_Modify_Reply_board", method= {RequestMethod.GET, RequestMethod.POST})
	public String su_Modify_Reply_board(HttpServletRequest request , Model model) {
		model.addAttribute("request", request);
	//	String num = request.getParameter("num");
		
		re = new com.spring.replyBoardService.Modify_Reply_board();
		re.execute(model);
		
		return "redirect:Suggestion_board";
	}
	
	//3.Suggestion 게시판 댓글 삭제
	@RequestMapping(value="SU_Delte_Reply_board",method=RequestMethod.GET)
	public String Su_Delete_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		re = new Delte_Reply_board();
		re.execute(model);
		
		String num = request.getParameter("num");
		System.out.println(num);
		
		return "redirect:Content_Suggestion_board?num="+num;
	}	
		
	//-------------------------------------------------------------
	
	@RequestMapping("/suggestion_image")
	   public void tip_image(HttpServletRequest request, HttpServletResponse response) {
	      Suggestion_board_DAO dao = new Suggestion_board_DAO();
	      Suggestion_board_DTO dto = new Suggestion_board_DTO();
	      dto = dao.ViewContent_Suggestion_board((String)request.getParameter("num"));
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
	
	@RequestMapping("/suggestion_image1")
	   public void tip_image1(HttpServletRequest request, HttpServletResponse response) {
		Suggestion_board_DAO dao = new Suggestion_board_DAO();
	    Suggestion_board_DTO dto = new Suggestion_board_DTO();
	      dto = dao.ViewContent_Suggestion_board((String)request.getParameter("num"));
	      try {
	         //Header에 파일 형식 셋팅.
	         response.setHeader("Content-Disposition", "inline;filename=\"" + dto.getImagename1() + "\"");
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
