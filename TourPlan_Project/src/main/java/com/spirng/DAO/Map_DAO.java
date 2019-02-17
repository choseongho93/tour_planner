package com.spirng.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spirng.mapService.Map;
import com.spirng.mapService.MapDBTemplate;

public class Map_DAO {
	private JdbcTemplate template;
	private PreparedStatement pre;
	private Connection con;
	private ResultSet rs;
	
	public Map_DAO() {
		// TODO Auto-generated constructor stub
		try {
			this.template = MapDBTemplate.template;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertNewMap(Map_DTO map) {
		String sql = "insert into plan_board(id, nickname, schedule, cost, savedate, num, marker, title, hit, use_time"
				+ ", content_1, content_2, content_3, content_4, content_5, content_6, content_7, content_8"
				+ ", content_9, content_10)"
				+ "values(?,?,?, ?,sysdate,plan_board_seq.nextval,?,?,0,?, ?,?,?,?,?,?,?,?,?,?)";
		template.update(sql, ps->{
			ps.setString(1, map.getId());
			ps.setString(2, map.getNickname());
			ps.setString(3, map.getSchedule());
			//ps.setString(4, map.getContent());
			ps.setString(4, map.getCost());
			
			ps.setString(5, map.getMarker());
			ps.setString(6, map.getTitle());
			
			ps.setString(7, map.getUse_time());
			
			ps.setString(8, map.getContent_1());
			ps.setString(9, map.getContent_2());
			ps.setString(10, map.getContent_3());
			ps.setString(11, map.getContent_4());
			ps.setString(12, map.getContent_5());
			ps.setString(13, map.getContent_6());
			ps.setString(14, map.getContent_7());
			ps.setString(15, map.getContent_8());
			ps.setString(16, map.getContent_9());
			ps.setString(17, map.getContent_10());
			
		});
	}
	
	public Map_DTO selectDTO(String num) {
		Map_DTO dto = new Map_DTO();
		String sql = "select * from plan_board";
		if(num != null) {
			sql += " where num="+num;
			updateHit(num);
		}try {
			dto = template.queryForObject(sql, new BeanPropertyRowMapper<Map_DTO>(Map_DTO.class));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("plan_board select Error");
		}
	
		return dto;
	}
	
	public void updateMap(Map_DTO map) {
		String sql = "update plan_board set schedule=?, cost=?, marker=?, title=?, use_time=?,"
				+ "content_1=?,content_2=?,content_3=?,content_4=?,content_5=?,content_6=?,"
				+ "content_7=?,content_8=?,content_9=?,content_10=? where num=?";
		try {
			template.update(sql, ps->{
				ps.setString(1, map.getSchedule());
				ps.setString(2, map.getCost());
				
				ps.setString(3, map.getMarker());
				ps.setString(4, map.getTitle());
				
				ps.setString(5, map.getUse_time());
				
				ps.setString(6, map.getContent_1());
				ps.setString(7, map.getContent_2());
				ps.setString(8, map.getContent_3());
				ps.setString(9, map.getContent_4());
				ps.setString(10, map.getContent_5());
				ps.setString(11, map.getContent_6());
				ps.setString(12, map.getContent_7());
				ps.setString(13, map.getContent_8());
				ps.setString(14, map.getContent_9());
				ps.setString(15, map.getContent_10());
				
				ps.setString(16, map.getNum());
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Map_DTO> getList(int start, int end){
		ArrayList<Map_DTO> dtos = new ArrayList<>();
		String sql = "select B.* from(Select rownum rn, A.* from(select * from plan_board order by num desc)A)B where rn between " + start + " and " + end;
		try {
			dtos = (ArrayList<Map_DTO>) template.query(sql, new BeanPropertyRowMapper<Map_DTO>(Map_DTO.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtos;
	}
	
	public int getListTotal() {
		int result = 0;
		String sql = "select count(*) from plan_board";
		try {
			result = template.queryForObject(sql, Integer.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Map_DTO> searchPlanBoard(String subject, String word, int start, int end) {
		ArrayList<Map_DTO> dtos = new ArrayList<>();
		String sql = "select B.* from(Select rownum rn, A.* from(select * from plan_board where "+ subject +" like '%"+word+"%' order by num desc)A)B where rn between " + start + " and " + end;
		try {
			dtos = (ArrayList<Map_DTO>) template.query(sql, new BeanPropertyRowMapper<Map_DTO>(Map_DTO.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtos;
	}
	public int searchTotalPageOfPlanBoard(String subject, String word ) {
		int result =0;
		String sql = "select count(*) from plan_board where "+subject+" like '%"+word+"%'";
		try{
			result = template.queryForObject(sql, Integer.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public void updateHit(String num) {
		String sql = "update plan_board set hit= hit+1 where num=?";
		try {
			template.update(sql, ps->{
				ps.setString(1, num);
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Map_DTO> topTipBoard(){
		ArrayList<Map_DTO> dtos = new ArrayList<>();
		String sql = "select B.* from(Select rownum rn, A.* from(select * from plan_board order by hit desc)A)B where rn between 1 and 7";
		try{
			dtos = (ArrayList<Map_DTO>) template.query(sql, new BeanPropertyRowMapper<Map_DTO>(Map_DTO.class));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dtos;
	}
}
