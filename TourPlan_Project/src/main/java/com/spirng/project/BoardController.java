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
	//1.�α���  ȭ�� �����ֱ�
	@RequestMapping(value="/login")
	public String login() {
		System.out.println("�α��� ȭ�� �����ֱ�");
		return "/board/login";
	}
	//2.�α��� ���̵� ��й�ȣ üũ
	@RequestMapping(value="/CheckLogin",method=RequestMethod.POST)
	public String ChechkLogin(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		login = new CheckLogin();
		int result = login.execute(model);
		
		System.out.println("�α��� üũ");
		
		if(result == 0) {
			HttpSession session = request.getSession();
			session.setAttribute("UserID", request.getParameter("id"));
			System.out.println("session ����");
			return "/board//login";
		}else 
			return "redirect:login";
	}
	//4.�α׾ƿ�
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		login = new Logout();
		login.execute01(model);
		System.out.println("logout����&session ����");
		
		return "/board/login";
	}*/
	//5.QA�Խ��� �����ֱ�
	@RequestMapping(value="/QA_board")
	public String QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		qa = new View_QA_board();
		qa.execute(model);
		
		System.out.println("QA_board ����");
		
		return "/board/QA_board";
	}
	//6.QA�Խ��� �Ⱥ����ֱ�
	@RequestMapping(value="NO_QA_board")
	public String No_QA_board() {
		System.out.println("�α��� �ϼ���");
		
		return "redirect:login";
	}
	//7.QA�Խ��� �� ����ϱ�
	@RequestMapping(value="Chk_Write_QA_board",method=RequestMethod.POST)
	public String Chk_Write_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		qa = new com.spring.QABoardService.Write_QA_board();
		int result = qa.execute01(model);
		
		if(result==1) {
			System.out.println("QA�Խ��� �� ��� ����");
			return "redirect:QA_board";
		}else {
			System.out.println("QA�Խ��� �� ��� ����");
			return "redirect:QA_board";
		}
	}
	//8.QA�Խ��� �۾��� �����ֱ�
	@RequestMapping(value="Write_QA_board")
	public String Write_QA_board() {
		System.out.println("QA�Խ��� �۾��� ����");
		
		return "/board/Write_QA_board";
	}
	//9.QA�Խ��� ��->���� �����ֱ�&��� �����ֱ�
	@RequestMapping(value="Content_QA_board", method=RequestMethod.GET)
	public String Content_QA_board(Model model, HttpServletRequest request) {
		System.out.println("QA�Խ��� ���� ����");
		model.addAttribute("request",request);
		
		qa = new com.spring.QABoardService.Content_QA_board();
		qa.execute(model);
		
		return "/board/Content_QA_board";
	}
	
	//6.QA�Խ��� ���������� �����ֱ�
	@RequestMapping(value="modify_QA_board", method= RequestMethod.GET)
	public String ModifyContent_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
				
		qa = new com.spring.QABoardService.Content_QA_board();
		qa.execute(model);
				
		return "/board/modify_QA_board";
			}
	
	//10.QA�Խñ� ����
	@RequestMapping(value="Save_modify_QA_board", method=RequestMethod.POST)
	public String Modify_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		qa = new com.spring.QABoardService.ModifyContent_QA_board();
		qa.execute(model);
		String num = request.getParameter("num");
		
		return "redirect:Content_QA_board?num="+num;
	}
	//11.QA�Խñ� ����
	@RequestMapping(value="DeleteContent_QA_board",method=RequestMethod.GET)
	public String DeleteContent_QA_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		qa = new com.spring.QABoardService.DeleteContent_QA_board();
		qa.execute(model);
		
		return "redirect:QA_board";
	}
	//12. QA�Խñ� �˻�
	//�˻���� ������
		@RequestMapping(value="/search_QA_board", method=RequestMethod.POST)//�˻�
		public String Post_Search_QA_Board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			qa = new Search_QA_board();
			qa.execute(model);
			
			return "/board/Search_QA_board";
		}
		
	//13. �˻�������� ������ �̵��� �������� ���� ������ ����Ʈ ������
		@RequestMapping(value="/search_QA_board", method=RequestMethod.GET)//�˻�
		public String Get_Search_QA_Board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			qa = new Search_QA_board();
			qa.execute(model);
			
			return "/board/Search_QA_board";
		}	
	//------------------------------------------------------------------------------------------------------
	
	//1.QA �Խ��� ��� ���
	@RequestMapping(value="Write_Reply_board",method=RequestMethod.POST)
	public String Chk_Write_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		re = new Write_Reply_board();
		//��� ����ϰ� �ٷ� num�Խ������� �����ֱ�
		int result = re.execute01(model);
		String num = request.getParameter("num");
		
		if(result==1) {
			System.out.println("QA�Խ��� ��� ��� ����");
			return "redirect:Content_QA_board?num="+num;
		}else {
			System.out.println("QA�Խ��� ��� ��� ����");
			return "redirect:QA_board";
		}
	}
	//2.QA �Խ��� ��� ����
	@RequestMapping(value="Delete_Reply_board",method=RequestMethod.GET)
	public String Delete_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		re = new Delte_Reply_board();
		re.execute(model);
		
		String num = request.getParameter("num");
		System.out.println(num);
		
		return "redirect:Content_QA_board?num="+num;
	}
	//3.QA�Խ��� ��� ����
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
		System.out.println("�Խ��� : "+board);
		System.out.println("�Խñ� ��ȣ : "+num);
		
		try {
			out = response.getWriter();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.println("<script>alert('���������� �����Ǿ����ϴ�.'); opener.document.location.reload(); self.close();</script>");
		out.flush();
		/*
		if(board.equals("Suggestion_board")) {
			System.out.println("Suggestion �Խ��� ��� ����");
			return "redirect:Content_Suggestion_board?num="+num;
		}else {
			System.out.println("QA�Խ��� ��� ����");
			return "redirect:Content_QA_board?num="+num;
		}
		*/
	}
	
	//4.Reply �Խ��� �����ֱ� & ��� ���� �����ֱ� (����)
	@RequestMapping(value="View_Modify_Reply_board",method=RequestMethod.GET)
	public String Reply_board(Model model,HttpServletRequest request) {
		System.out.println("��� ���� �Խ��� ����");
		model.addAttribute("request", request);
		
		re = new View_Modify_Reply_board();
		re.execute(model);
		
		return "/board/Modify_Reply_board";
	}
	//-------------------------------------------------------------------------------------------
	
	//1.���ǰԽ��� �Ⱥ����ֱ�
	@RequestMapping(value="No_Suggestion_board")
	public String No_Suggestion_board() {
		System.out.println("�α��� �ϼ���");
		
		return "redirect:login";
	}
	
	//2.���� �Խ��� �� �����ֱ�
	@RequestMapping(value="/Suggestion_board")
	public String Suggestion_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		su = new View_Suggestion_board();
		su.execute(model);
		
		System.out.println("Suggestion_board");
		
		return "/board/Suggestion_board";
	}
	
	//3.���ǰԽ��� �۾��� �����ֱ�
		@RequestMapping(value="Write_Suggestion_board")
		public String Write_Suggestion_board() {
			System.out.println("���ǰԽ��� �۾��� ����");
			
			return "/board/Write_Suggestion_board";
		}
	
	//4.Suggestion �Խ��� �� ����ϱ�
	@RequestMapping(value="ChkWrite_Suggestion_board",method=RequestMethod.POST)
	public String ChkWrite_Suggestion_board(MultipartHttpServletRequest request, Model model){
				
		model.addAttribute("request", request);
		su = new Write_Suggestion_board();
		su.execute(model);

		return "redirect:Suggestion_board";
	}

	//5.Suggestion �Խ��� ��->���� �����ֱ�&��� �����ֱ�
		@RequestMapping(value="Content_Suggestion_board", method=RequestMethod.GET)
		public String Content_Suggestion_board(Model model, HttpServletRequest request) {
			System.out.println("Suggestion �Խ��� ���� ����");
			model.addAttribute("request",request);
			
			su = new com.spring.suggestionBoard.Content_Suggestion_board();
			su.execute(model);
			
 			return "/board/Content_Suggestion_board";
		}
	//6.suggestion �Խñ� ����
		@RequestMapping(value="DeleteContent_Suggestion_board",method=RequestMethod.GET)
		public String DeleteContent_Suggestion_board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			
			su = new com.spring.suggestionBoard.DeleteContent_Suggestion_board();
			su.execute(model);
			
			return "redirect:Suggestion_board";
		}
	//7.suggestion �Խñ� ���� ������ �����ֱ�
		@RequestMapping(value="Modify_Suggestion_board", method= RequestMethod.GET)
		public String ModifyContent_Suggestion_board(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			
			su = new com.spring.suggestionBoard.Content_Suggestion_board();
			su.execute(model);
			
			return "/board/Modify_SB";
		}
		
		//�� ������ ������ �����ϴ� ���
		@RequestMapping(value="/save_modi_Suggestion_board", method=RequestMethod.POST)
		public String modify_Suggestion_board(MultipartHttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			su = new com.spring.suggestionBoard.ModifyContent_Suggestion_board();
			su.execute(model);
			
			String num = request.getParameter("num");
			return "redirect:Suggestion_board?num="+num;
		}
		
		//8. Suggestion�Խñ� �˻�
		//�˻���� ������
			@RequestMapping(value="/search_Suggestion_board", method=RequestMethod.POST)//�˻�
			public String Post_Search_SuGGESTION_Board(HttpServletRequest request, Model model) {
				model.addAttribute("request", request);
				su = new Search_Suggestion_board();
				su.execute(model);
				
				return "/board/Search_Suggestion_board";
			}
			
		//9. �˻�������� ������ �̵��� �������� ���� ������ ����Ʈ ������
			@RequestMapping(value="/search_Suggestion_board", method=RequestMethod.GET)//�˻�
			public String Get_Search_Suggestion_Board(HttpServletRequest request, Model model) {
				model.addAttribute("request", request);
				su = new Search_Suggestion_board();
				su.execute(model);
				
				return "/board/Search_Suggestion_board";
			}
	//-------------------------------------------------------------------------------------------------
	
	//1.Suggestion �Խ��� ��� ���
	@RequestMapping(value="SU_Write_Reply_board",method={RequestMethod.GET, RequestMethod.POST})
	
	public String Su_Write_Reply_board(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		re = new Write_Reply_board();
		//��� ����ϰ� �ٷ� num�Խ������� �����ֱ�
		int result = re.execute01(model);
		String num = request.getParameter("num");
			
		if(result==1) {
			System.out.println("Suggestion �Խ��� ��� ��� ����");
			return "redirect:Content_Suggestion_board?num="+num;
		}else {
			System.out.println("Suggestion �Խ��� ��� ��� ����");
			return "redirect:Suggestion_board";
		}
	}	
	
	//2.Suggestion �Խ��� ��� ����
	@RequestMapping(value="Su_Modify_Reply_board", method= {RequestMethod.GET, RequestMethod.POST})
	public String su_Modify_Reply_board(HttpServletRequest request , Model model) {
		model.addAttribute("request", request);
	//	String num = request.getParameter("num");
		
		re = new com.spring.replyBoardService.Modify_Reply_board();
		re.execute(model);
		
		return "redirect:Suggestion_board";
	}
	
	//3.Suggestion �Խ��� ��� ����
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
	         //Header�� ���� ���� ����.
	         response.setHeader("Content-Disposition", "inline;filename=\"" + dto.getImagename1() + "\"");
	         //response�� OutputStream ����.
	         OutputStream outputStream = response.getOutputStream();
	         //contentType ����
	         response.setContentType("image/jpeg");
	         
	         //Blob�κ��� �̹����� �����ͼ� SerialBlob�� ����.
	         Blob image_vis = dto.getImage1();
	          byte[] bytes = image_vis.getBytes(1, (int)image_vis.length());
	         SerialBlob blob = new SerialBlob(bytes);
	         //Byte Stream�� outputStream ���� ����.
	         //IOUtils�� io�� �̵� ���� �� �ϴ� class
	         IOUtils.copy(blob.getBinaryStream(), outputStream);
	         
	         //�� ���������� DAO���� close�ϸ� �ȵ�.
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
	         //Header�� ���� ���� ����.
	         response.setHeader("Content-Disposition", "inline;filename=\"" + dto.getImagename1() + "\"");
	         //response�� OutputStream ����.
	         OutputStream outputStream = response.getOutputStream();
	         //contentType ����
	         response.setContentType("image/jpeg");
	         
	         //Blob�κ��� �̹����� �����ͼ� SerialBlob�� ����.
	         Blob image_vis = dto.getImage2();
	          byte[] bytes = image_vis.getBytes(1, (int)image_vis.length());
	         SerialBlob blob = new SerialBlob(bytes);
	         //Byte Stream�� outputStream ���� ����.
	         //IOUtils�� io�� �̵� ���� �� �ϴ� class
	         IOUtils.copy(blob.getBinaryStream(), outputStream);
	         
	         //�� ���������� DAO���� close�ϸ� �ȵ�.
	         outputStream.flush();
	         outputStream.close();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }

	   }
}
