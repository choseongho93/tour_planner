package com.spirng.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Home_DAO {
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user = "jsp";
	private String pwd = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Home_DAO() {
		try {
			Class.forName(driver);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//占쏙옙회占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
	public ArrayList<Tip_board_DTO> topTipBoard() {
		String sql = "select B.* from(Select rownum rn, A.* from(select * from tip_board order by hit desc)A)B where rn between 1 and 7";
		ArrayList<Tip_board_DTO> list = new ArrayList<Tip_board_DTO>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Tip_board_DTO dto = new Tip_board_DTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setTitle(rs.getString("title"));
				dto.setNickname(rs.getString("nickname"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
	
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {}
		}
		
		return list;
	}
	
	//채占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙(占쏙옙 占쏙옙 占쏙옙占� 占쏙옙占싱듸옙, 占싻놂옙占쏙옙, 占쏙옙 占쏙옙占쏙옙, 占시곤옙(long)占쏙옙占승뤄옙 占쏙옙占쏙옙. 占쏙옙 占쏙옙占싹뤄옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙.)
	public void insertChatData(chat_DTO dto) {
		String sql = "insert into chat_board(userid,usernickname,content,chattime) values(?,?,?,?)";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getUserNickname());
			ps.setString(3, dto.getContent());
			ps.setLong(4, dto.getChattime());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
				System.out.println("채占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙퓸占쏙옙占쏙옙求占�!!");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�.(처占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙)
	public ArrayList<chat_DTO> getChatHistory(){
		String sql = "select * from chat_board";
		ArrayList<chat_DTO> list = new ArrayList<chat_DTO>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				chat_DTO dto = new chat_DTO();
				dto.setUserId(rs.getString("userid"));
				dto.setUserNickname(rs.getString("usernickname"));
				dto.setContent(rs.getString("content"));
				dto.setChattime(rs.getLong("chattime"));
				dto.setStr_time();
				
			//	System.out.println("占쏙옙占쏙옙占� 占시곤옙 : " + dto.getStr_time());
	
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {}
		}
		return list;	
	}
	
	//占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占시곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙챨占� 占쏙옙占쏙옙占쏙옙 채占시놂옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�
	public ArrayList<chat_DTO> getChatBeforeNow(long lastRefreshTime){
		String sql = "select * from chat_board where ?<chattime and chattime<?";
		
		ArrayList<chat_DTO> list = new ArrayList<chat_DTO>();
		
		long currenttime = System.currentTimeMillis();//占쏙옙占쏙옙챨占�
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setLong(1, lastRefreshTime);//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占시곤옙占쏙옙占쏙옙占쏙옙占쏙옙
			ps.setLong(2, currenttime);//占쏙옙占쌥깍옙占쏙옙占쏙옙 占쏙옙占�
			rs = ps.executeQuery();
			
			while(rs.next()) {
				chat_DTO dto = new chat_DTO();
				dto.setUserId(rs.getString("userid"));
				dto.setUserNickname(rs.getString("usernickname"));
				dto.setContent(rs.getString("content"));
				dto.setChattime(rs.getLong("chattime"));
				dto.setStr_time();//占쏙옙占쏙옙 占시곤옙占쏙옙占쏙옙 String 占쏙옙占승뤄옙 占쏙옙占쏙옙 占쌕뀐옙占쏙옙.
				
				//System.out.println("占쏙옙占쏙옙占� 占시곤옙 : " + dto.getStr_time());//占쏙옙트占쏙옙占쏙옙占승뤄옙 占쏙옙환占쌔쇽옙 占쏙옙占쏙옙占�, 占쏙옙占쏙옙占시띰옙 占쏙옙占쌕억옙占쏙옙 占쏙옙.
	
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {}
		}
		return list;	
	}
	
}
