package com.spirng.project;


import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spirng.DAO.Map_DAO;
import com.spirng.DAO.Map_DTO;
import com.spirng.mapService.LoadMapService;
import com.spirng.mapService.MapDBTemplate;
import com.spirng.mapService.SaveMapService;
import com.spirng.mapService.UpdateMapService;
import com.spring.plan_board.BoardListView;
import com.spring.plan_board.PlanboardService;
import com.spring.plan_board.Search_plan_board;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PlanController {
	com.spirng.mapService.Map service;
	PlanboardService boardService;
	
	public PlanController() {
		// TODO Auto-generated constructor stub
		System.out.println("Map Controller 생성자 실행");
		String configLocation = "classpath:applicationDBC.xml";
		GenericXmlApplicationContext context = new GenericXmlApplicationContext(configLocation);
		JdbcTemplate template = context.getBean("template", JdbcTemplate.class);
		MapDBTemplate.template = template;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping("/plan/plan")
	public String getPlan(HttpServletRequest request, HttpSession session, Model model) {
		if(request.getParameter("num") == null || request.getParameter("num").equals("")) {
			System.out.println("새로 만든거임!");
		}else {
			//데이터 가져오기
			model.addAttribute("num", request.getParameter("num"));
			service = new LoadMapService();
			service.execute(model);
			/*
			 Map<String, Object> map = model.asMap();
			Map_DTO dto = map.get("dto");
			 if(!session.getAttribute("id").equal(dto.getId())){
			 return "redirect:planView?num="+request.getParameter("num");
			 }
			*/
		}
		return "plan/plan";
	}
	
	//네이버 검색 처리
	@RequestMapping("/crolSearch")
	public @ResponseBody Map<String, Object> crolSearch(HttpServletRequest request){
		System.out.println("[SYSTEM] Controller의 crolSearch");
		Map<String, Object> map = new HashMap<String, Object>();
		String url = "";
		try {
			Document doc;
			if(request.getParameter("n") == null || request.getParameter("n").equals("undifined") || request.getParameter("n").equals(""))
			{
				doc = Jsoup.connect("https://search.naver.com/search.naver?where=post&sm=tab_jum&query="+request.getParameter("query")).get();
				url = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query="+request.getParameter("query");
			}else {
				//System.out.println(request.getParameter("n"));			
				doc = Jsoup.connect("https://search.naver.com/search.naver"+request.getParameter("n")).get();
				url = "https://search.naver.com/search.naver"+request.getParameter("n");
			}
			
	        //Jsoup.connect에 URL을 적어서 읽는다
	         //.get(); 메소드를 통해서 Document 타입의 변수 doc에 대입한다
			//System.out.println(doc);//doc 에 담긴 내용을 출력한다
			Elements paging = doc.select("div.paging");
			Elements elem = doc.select("ul#elThumbnailResultArea");
			//필요없는 a태그 제거
			Elements delete = elem.select("a.thumb_num");
			delete.remove();
			Elements mapEle = elem.select("a.map_opn");
			mapEle.remove();
			Elements url_a = elem.select("a.url");
			url_a.remove();
			//블로그 내 검색 항목 제거
			Elements url_blog = elem.select("a.txt84");
			for(int i=1; i<url_blog.size();i+=2) {
				Element temp = url_blog.get(i);
				temp.remove();
			}	
			//페이징의 onclick 이밴트 변경
			Elements child = paging.select("a");
			for(int i=0; i<child.size(); i++) {
				Element temp = child.get(i);
				String href = temp.attr("href");
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver('"+href+"'); event.preventDefault();");
			}
			
			//블로그 이동 주소 처리
			// 사진, 제목, 블로그 이름 3개 의  href과 onclick을 수정해야함.
			//사진 a태그의 href 변경
			Elements thumb_rollover = elem.select("a.sp_thmb");
			//Elements pic_a = thumb_rollover.select("a");
			for(int i=0; i<thumb_rollover.size(); i++) {
				Element temp = thumb_rollover.get(i);
				String href = temp.attr("href");
				System.out.println(href);
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver_blog('"+href+"'); event.preventDefault();");
			}
			//제목의 a태그 href 변경
			Elements sh_blog_title = elem.select("a.sh_blog_title");
			for(int i=0; i<sh_blog_title.size(); i++) {
				Element temp = sh_blog_title.get(i);
				String href = temp.attr("href");
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver_blog('"+href+"'); event.preventDefault();");
			}
			//블로그이름의 a태그 href 변경
			Elements inline = elem.select("span.inline");
			Elements blog_name_a = inline.select("a");
			for(int i=0; i<blog_name_a.size(); i++) {
				Element temp = blog_name_a.get(i);
				String href = temp.attr("href");
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver_blog('"+href+"'); event.preventDefault();");
			}
			map.put("url", url);
			map.put("response", elem.html());
			map.put("paging", paging.html());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	//네이버 블로그 이동 처리
	@RequestMapping("/crolBlog")
	public @ResponseBody Map<String, Object> crolBlog(HttpServletRequest request){
		System.out.println("[SYSTEM] Controller의 crolBlog");
		Map<String, Object> map = new HashMap<String, Object>();
		//URL은 블로그이 main ifram 의 src에 있고, 그 src의 맨뒤에 false 속성을 빼줘야한다
		Document doc;
		String url = request.getParameter("n");
		try {
			doc = Jsoup.connect(url).get();
			Elements iframe = doc.select("iframe");
			String real_url ="";
			//블로그는 iframe 과 frame 2개의 종류로 쌓여있음.
			//frame일 경우 src는 iframe이 있는 곳으로 redirect된다.
			if(iframe.size()==0) {
				Elements frame = doc.select("frame");
				real_url = frame.get(0).attr("src");
				doc = Jsoup.connect(real_url).get();
				iframe = doc.select("iframe");
				real_url = iframe.get(0).attr("src");
			}else {
				real_url = iframe.get(0).attr("src");
			}
			
			//iframe 내부의 html 가져옴.
			real_url = real_url.substring(0,real_url.lastIndexOf("&"));
			System.out.println(real_url);
			
			doc = Jsoup.connect("http://blog.naver.com"+real_url).get();
			Elements content = doc.select("div.se-main-container");

			Elements link = doc.select("div.se-oglink");
			link.remove();
			Elements oembed = doc.select("div.se-oembed");
			oembed.remove();
			Elements se_map = doc.select("div.se-placesMap");
			se_map.remove();
			
			Elements title_pparent = doc.select("div.se-title-text");
			Elements title_parent = title_pparent.select("p");
			System.out.println("p:" + title_parent);
			Elements title_tag = title_parent.select("span");
			String title = title_tag.html();
			System.out.println("t:" + title);
			if(!title_tag.is("span")) {
				title = title.substring(title.indexOf(">")+1);
				title = title.substring(title.indexOf(">")+1);
				title = title.substring(title.indexOf(">")+1);
				System.out.println("i>:" + title);
			}else {
				System.out.println("e>:" + title);
			}
			System.out.println("타이틀!"+title);
			
			map.put("url", url);
			map.put("title", title);
			map.put("body", content.html());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	//plan 페이지 저장 클릭시 DB에 해당 데이터를 저장
	@RequestMapping("/mapdataUpload")
	public @ResponseBody Map<String, Object> mapdataUpload(@RequestBody String json, HttpSession session, Model model) {
		System.out.println("[SYSTEM] Controller의 crolBlog");
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//기본 셋팅. 나중에 지울것
		map.put("id", session.getAttribute("id"));
		map.put("nickname", session.getAttribute("nickname"));
		
		//첫글인 경우와 이미 있던글을 업데이트 하는 경우
		if(map.get("number").equals("")) {
			System.out.println("첫 글 임");
			model.addAttribute("map", map);
			service = new SaveMapService();
			service.execute(model);
		}else {
			System.out.println("있던 글임");
			model.addAttribute("map", map);
			service = new UpdateMapService();
			service.execute(model);
		}
		
		//ajax한테 돌려주는 값이 없으면 잘 받았어도 404 error 발생
		Map<String, Object> resultMap = new HashMap<String, Object>();
		return resultMap;
	}
	
	@RequestMapping("/plan/planView")
	public String planView(HttpServletRequest request, HttpSession session, Model model) {
		//데이터 가져오기
		model.addAttribute("num", request.getParameter("num"));
		service = new LoadMapService();
		service.execute(model);

		return "plan/planView";
	}
	
	@RequestMapping("/plan/mPlan")
	public String mPlan(HttpServletRequest request, Model model) {
		if(request.getParameter("num") == null || request.getParameter("num").equals("")) {
			System.out.println("새로 만든거임!");
		}else {
			//데이터 가져오기
			model.addAttribute("num", request.getParameter("num"));
			service = new LoadMapService();
			service.execute(model);
			/*
			 Map<String, Object> map = model.asMap();
			Map_DTO dto = map.get("dto");
			 if(!session.getAttribute("id").equal(dto.getId())){
			 return "redirect:planView?num="+request.getParameter("num");
			 }
			*/
		}
		return "plan/mPlan";
	}
	
	@RequestMapping("/plan/mPlanView")
	public String mPlanView(HttpServletRequest request, Model model) {
		//데이터 가져오기
		model.addAttribute("num", request.getParameter("num"));
		service = new LoadMapService();
		service.execute(model);

		return "plan/mPlanView";
	}
	
	@RequestMapping("/plan/plan_board")
	public String plan_board(HttpServletRequest request, Model model) {
		System.out.println("[SYSTEM] plan_board planList View");
		model.addAttribute("request", request);
		
		boardService = new BoardListView();
		boardService.execute(model);
		
		return "plan/plan_board";
	}
	
	@RequestMapping("/search_plan_board")
	public String search_plan_board(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		boardService = new Search_plan_board();
		boardService.execute(model);
		
		return "plan/plan_board";
	}
	
	
}
