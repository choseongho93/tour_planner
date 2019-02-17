package com.spirng.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user = "jsp";
	private String password = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public BoardDAO() {
		try {
			Class.forName(driver);
		}
		catch (Exception e) {}
	}
	
	public ArrayList<BoardDTO> qaBoard(String id) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		String sql = "select * from qa_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setDay(rs.getString("day"));
				boardList.add(dto);
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return boardList;
	}
	
	public ArrayList<BoardDTO> suqqestionBoard(String id) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		String sql = "select * from suqqestion_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setDay(rs.getString("day"));
				boardList.add(dto);
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return boardList;
	}
	
	public ArrayList<BoardDTO> tipBoard(String id) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		String sql = "select * from tip_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setDay(rs.getString("day"));
				boardList.add(dto);
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return boardList;
	}
	
	public ArrayList<BoardDTO> planBoard(String id) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		String sql = "select * from plan_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setDay(rs.getString("day"));
				boardList.add(dto);
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return boardList;
	}
	
	public ArrayList<BoardDTO> basketBoard(String id) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		String sql = "select * from basket_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setDay(rs.getString("day"));
				boardList.add(dto);
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return boardList;
	}
	
	public ArrayList<BoardDTO> replyBoard(String id) {
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		String sql = "select * from reply_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setDay(rs.getString("day"));
				boardList.add(dto);
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return boardList;
	}
}
