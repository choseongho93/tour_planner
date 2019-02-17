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
		System.out.println("Map Controller ������ ����");
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
			System.out.println("���� �������!");
		}else {
			//������ ��������
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
	
	//���̹� �˻� ó��
	@RequestMapping("/crolSearch")
	public @ResponseBody Map<String, Object> crolSearch(HttpServletRequest request){
		System.out.println("[SYSTEM] Controller�� crolSearch");
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
			
	        //Jsoup.connect�� URL�� ��� �д´�
	         //.get(); �޼ҵ带 ���ؼ� Document Ÿ���� ���� doc�� �����Ѵ�
			//System.out.println(doc);//doc �� ��� ������ ����Ѵ�
			Elements paging = doc.select("div.paging");
			Elements elem = doc.select("ul#elThumbnailResultArea");
			//�ʿ���� a�±� ����
			Elements delete = elem.select("a.thumb_num");
			delete.remove();
			Elements mapEle = elem.select("a.map_opn");
			mapEle.remove();
			Elements url_a = elem.select("a.url");
			url_a.remove();
			//��α� �� �˻� �׸� ����
			Elements url_blog = elem.select("a.txt84");
			for(int i=1; i<url_blog.size();i+=2) {
				Element temp = url_blog.get(i);
				temp.remove();
			}	
			//����¡�� onclick �̹�Ʈ ����
			Elements child = paging.select("a");
			for(int i=0; i<child.size(); i++) {
				Element temp = child.get(i);
				String href = temp.attr("href");
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver('"+href+"'); event.preventDefault();");
			}
			
			//��α� �̵� �ּ� ó��
			// ����, ����, ��α� �̸� 3�� ��  href�� onclick�� �����ؾ���.
			//���� a�±��� href ����
			Elements thumb_rollover = elem.select("a.sp_thmb");
			//Elements pic_a = thumb_rollover.select("a");
			for(int i=0; i<thumb_rollover.size(); i++) {
				Element temp = thumb_rollover.get(i);
				String href = temp.attr("href");
				System.out.println(href);
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver_blog('"+href+"'); event.preventDefault();");
			}
			//������ a�±� href ����
			Elements sh_blog_title = elem.select("a.sh_blog_title");
			for(int i=0; i<sh_blog_title.size(); i++) {
				Element temp = sh_blog_title.get(i);
				String href = temp.attr("href");
				temp.attr("href","#");
				temp.attr("onclick","javascript: modal_naver_blog('"+href+"'); event.preventDefault();");
			}
			//��α��̸��� a�±� href ����
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
	
	//���̹� ��α� �̵� ó��
	@RequestMapping("/crolBlog")
	public @ResponseBody Map<String, Object> crolBlog(HttpServletRequest request){
		System.out.println("[SYSTEM] Controller�� crolBlog");
		Map<String, Object> map = new HashMap<String, Object>();
		//URL�� ��α��� main ifram �� src�� �ְ�, �� src�� �ǵڿ� false �Ӽ��� ������Ѵ�
		Document doc;
		String url = request.getParameter("n");
		try {
			doc = Jsoup.connect(url).get();
			Elements iframe = doc.select("iframe");
			String real_url ="";
			//��α״� iframe �� frame 2���� ������ �׿�����.
			//frame�� ��� src�� iframe�� �ִ� ������ redirect�ȴ�.
			if(iframe.size()==0) {
				Elements frame = doc.select("frame");
				real_url = frame.get(0).attr("src");
				doc = Jsoup.connect(real_url).get();
				iframe = doc.select("iframe");
				real_url = iframe.get(0).attr("src");
			}else {
				real_url = iframe.get(0).attr("src");
			}
			
			//iframe ������ html ������.
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
			System.out.println("Ÿ��Ʋ!"+title);
			
			map.put("url", url);
			map.put("title", title);
			map.put("body", content.html());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	//plan ������ ���� Ŭ���� DB�� �ش� �����͸� ����
	@RequestMapping("/mapdataUpload")
	public @ResponseBody Map<String, Object> mapdataUpload(@RequestBody String json, HttpSession session, Model model) {
		System.out.println("[SYSTEM] Controller�� crolBlog");
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�⺻ ����. ���߿� �����
		map.put("id", session.getAttribute("id"));
		map.put("nickname", session.getAttribute("nickname"));
		
		//ù���� ���� �̹� �ִ����� ������Ʈ �ϴ� ���
		if(map.get("number").equals("")) {
			System.out.println("ù �� ��");
			model.addAttribute("map", map);
			service = new SaveMapService();
			service.execute(model);
		}else {
			System.out.println("�ִ� ����");
			model.addAttribute("map", map);
			service = new UpdateMapService();
			service.execute(model);
		}
		
		//ajax���� �����ִ� ���� ������ �� �޾Ҿ 404 error �߻�
		Map<String, Object> resultMap = new HashMap<String, Object>();
		return resultMap;
	}
	
	@RequestMapping("/plan/planView")
	public String planView(HttpServletRequest request, HttpSession session, Model model) {
		//������ ��������
		model.addAttribute("num", request.getParameter("num"));
		service = new LoadMapService();
		service.execute(model);

		return "plan/planView";
	}
	
	@RequestMapping("/plan/mPlan")
	public String mPlan(HttpServletRequest request, Model model) {
		if(request.getParameter("num") == null || request.getParameter("num").equals("")) {
			System.out.println("���� �������!");
		}else {
			//������ ��������
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
		//������ ��������
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
