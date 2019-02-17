package com.spirng.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spirng.DAO.BoardDTO;
import com.spirng.DAO.MemberDTO;
import com.spring.check_service.CheckService;
import com.spring.check_service.IdCheckService;
import com.spring.check_service.NicknameCheckService;
import com.spring.member_arrayList_service.MemberArrayListService;
import com.spring.member_arrayList_service.UserBoardListInfoMemberService;
import com.spring.member_dto_service.IdInquiryMemberService;
import com.spring.member_dto_service.LoginMemberService;
import com.spring.member_dto_service.MemberDTOService;
import com.spring.member_dto_service.PwdInquiryMemberService;
import com.spring.member_dto_service.UserInfoMemberService;
import com.spring.memeber_init_service.MemberIntService;
import com.spring.memeber_init_service.PwdUpdateMemberService;
import com.spring.memeber_init_service.RegistrationMemberService;
import com.spring.memeber_init_service.UserDeleteMemberService;
import com.spring.memeber_init_service.UserInfoChangeMemberService;
import com.spring.memeber_init_service.UserPwdChangeMemberService;


@Controller
public class MemberController {
	public MemberIntService mis;
	public MemberDTOService mds;
	public MemberArrayListService mas;
	public CheckService cs;

	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> idCheck(@RequestBody String id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		cs = new IdCheckService();
		int count = cs.execute(id);
		map.put("cnt", count);
		return map;
	}

	@RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> nicknameCheck(@RequestBody String nickname) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		cs = new NicknameCheckService();
		int count = cs.execute(nickname);
		map.put("cnt", count);
		return map;
	}

	@RequestMapping(value = "/default/mainBottom")
	public String mainBottom() {
		return "default/mainBottom";
	}

	@RequestMapping(value = "/default/mainHeader")
	public String mainHeader() {
		return "default/mainHeader";
	}

	@RequestMapping(value = "/default/userInfoSidebar")
	public String userInfoSidebar() {
		return "default/userInfoSidebar";
	}

	@RequestMapping(value = "/member/main")
	public String main() {
		return "member/main";
	}

	@RequestMapping(value = "/member/login")
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "/member/frame")
	public String frame() {
		return "member/frame";
	}

	@RequestMapping(value = "/member/contact")
	public String contact() {
		return "member/contact";
	}

	@RequestMapping(value = "/member/tripPicture")
	public String tripPicture() {
		return "member/tripPicture";
	}

	@RequestMapping(value = "/member/loginCheck", method = RequestMethod.POST)
	public String loginCheck(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mds = new LoginMemberService();
		MemberDTO dto = mds.execute(model);
		String url = "";
		if (dto.getId() == null || dto.getNickname() == null) {
			url = "redirect:login";
		} else {
			url = "redirect:loginSuccess?id=" + dto.getId() + "&nickname=" + dto.getNickname();
		}
		return url;
	}

	@RequestMapping(value = "/member/loginSuccess")
	public String loginSuccess(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("loginId", request.getParameter("id"));
		session.setAttribute("loginNickname", request.getParameter("nickname"));
		return "redirect:main";
	}

	@RequestMapping(value = "/member/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:main";
	}

	@RequestMapping(value = "/member/registration")
	public String registration() {
		return "member/registration";
	}

	@RequestMapping(value = "/member/registrationCheck", method = RequestMethod.POST)
	public String registrationCheck(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mis = new RegistrationMemberService();
		int val = mis.execute(model);
		String url = "";
		if (val == 0) {
			url = "member/registrationFail";
		} else {
			url = "redirect:main";
		}
		return url;
	}

	@RequestMapping(value = "/member/idInquiry")
	public String idInquiry() {
		return "member/idInquiry";
	}

	@RequestMapping(value = "/member/idInquirySuccess", method = RequestMethod.POST)
	public String idInquiryCheck(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mds = new IdInquiryMemberService();
		MemberDTO dto = new MemberDTO();
		dto = mds.execute(model);
		String url = "";
		if (dto.getId() == null) {
			url = "member/idInquiryFail";
		} else {
			model.addAttribute("dto_id", dto.getId());
			url = "member/idInquirySuccess";
		}
		return url;
	}

	@RequestMapping(value = "/member/pwdInquiry")
	public String pwdInquiry() {
		return "member/pwdInquiry";
	}

	@RequestMapping(value = "/member/pwdInquiryFail")
	public String pwdInquiryFail() {
		return "member/pwdInquiryFail";
	}

	@RequestMapping(value = "/member/pwdUpdate", method = RequestMethod.POST)
	public String pwdInquiryCheck(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mds = new PwdInquiryMemberService();
		MemberDTO dto = new MemberDTO();
		dto = mds.execute(model);
		String url = "";
		if (dto.getId() == null) {
			url = "member/pwdInquiryFail";
		} else {
			model.addAttribute("dto_id", dto.getId());
			url = "member/pwdUpdate";
		}
		return url;
	}

	@RequestMapping(value = "/member/pwdUpdateSuccess", method = RequestMethod.POST)
	public String pwdUpdateSuccess(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mis = new PwdUpdateMemberService();
		int val = mis.execute(model);
		String url = "";
		if (val == 1) {
			url = "member/pwdUpdateSuccess";
		} else {
			model.addAttribute("val", val);
			url = "member/pwdInquiryFail";
		}
		return url;
	}

	@RequestMapping(value = "/member/userInfo")
	public String userInfo(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		String url = "";
		if (id == null) {
			url = "member/userInfoFail";
		} else {
			url = "member/userInfo";
		}
		return url;
	}

	@RequestMapping(value = "/member/userInfoChange", method = RequestMethod.POST)
	public String userInfoChange(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mds = new UserInfoMemberService();
		MemberDTO dto = new MemberDTO();
		dto = mds.execute(model);
		String url = "";
		if (dto.getPwd() == null) {
			url = "member/userInfoFail";
		} else {
			model.addAttribute("dto", dto);
			url = "member/userInfoChange";
		}
		return url;
	}

	@RequestMapping(value = "/member/userInfoChangeCheck")
	public String userInfoChangeCheck(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mis = new UserInfoChangeMemberService();
		int val = mis.execute(model);
		HttpSession session = request.getSession();
		if (val == 1) {
			session.invalidate();
			model.addAttribute("val", val);
		}
		return "member/userInfoChangeCheck";
	}

	@RequestMapping(value = "/member/userPwdChange")
	public String userPwdChange() {
		return "member/userPwdChange";
	}

	@RequestMapping(value = "/member/userPwdChangeCheck")
	public String userPwdChangeCheck(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		mis = new UserPwdChangeMemberService();
		int val = mis.execute(model);
		HttpSession session = request.getSession();
		if (val == 1) {
			session.invalidate();
			model.addAttribute("val", val);
		}
		return "member/userPwdChangeCheck";
	}

	@RequestMapping(value = "/member/userDelete")
	public String userDelete(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("request", request);
		mis = new UserDeleteMemberService();
		int val = mis.execute(model);
		HttpSession session = request.getSession();
		if (val == 1) {
			session.invalidate();
		}
		return "redirect:main";
	}

	@RequestMapping(value = "/member/boardCheck")
	public String boardCheck(Model model, HttpServletRequest request) {
		String boardNum = request.getParameter("boardNum");
		return "redirect:userBoardListInfo?boardNum=" + boardNum;
	}

	@RequestMapping(value = "/member/userBoardListInfo")
	public String userBoardListInfo(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		mas = new UserBoardListInfoMemberService();
		boardList = mas.execute(model);
		model.addAttribute("board", boardList);
		return "member/userBoardListInfo";
	}
}
