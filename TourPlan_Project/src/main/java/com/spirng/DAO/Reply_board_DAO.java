package com.spirng.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Reply_board_DAO {
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user="jsp";
	private String pwd="1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Reply_board_DAO() {
		try {
			Class.forName(driver);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//1.reply占쏙옙占� 占쏙옙 占쏙옙占쏙옙占싶쇽옙 占쏙옙占쏙옙占쌍깍옙 ->占쌉쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌉시깍옙 占쏙옙호(占싸몌옙占�)占쏙옙 占쏙옙占쏙옙 占쏙옙肪占� 占쏙옙占쏙옙占쏙옙占쏙옙
	public ArrayList<Reply_board_DTO> ViewReply_board(String board, String num){
		String sql = "select A.* from(select * from Reply_board where board=? and num=?)A order by rpnum asc";
		ArrayList<Reply_board_DTO> Reply_boardView = new ArrayList<Reply_board_DTO>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, board);
			ps.setString(2, num);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reply_board_DTO dto = new Reply_board_DTO();
				
				dto.setId(rs.getString("id"));
				dto.setBoard(rs.getString("board"));
				dto.setNickname(rs.getString("nickname"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setNum(rs.getInt("num"));
				dto.setRpnum(rs.getInt("rpnum"));
				Reply_boardView.add(dto);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps !=null) ps.close();
				if(con!=null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}return Reply_boardView;
	}
	
	//2.reply占쏙옙占� 占쏙옙占�"
	public int RegisterReply_board(String nickname, String content, String board, String id, int num) {
		String sql = "insert into reply_board(nickname,content,board,id,num,savedate,rpnum)"
				+"values(?,?,?,?,?,sysdate,reply_board_seq.nextval)";
		int result = 0;
	
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setString(2, content);
			ps.setString(3, board);
			ps.setString(4, id);
			ps.setInt(5, num);
			
			result = ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.cancel();
				if(con!=null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}return result;
	}
	
	//3.reply 占쏙옙占� 占쏙옙占쏙옙
	public void Delte_Reply_board(String rpnum) {
		String sql = "delete from reply_board where rpnum=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, rpnum);
			ps.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	//4.reply 占쏙옙占� 占쏙옙占쏙옙
	public void Modify_Reply_board(int rpnum, String content) {
		String sql = "update Reply_board set content=? where rpnum=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, rpnum);
			ps.executeUpdate();
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();	if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	//5.rpnum占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙
	public Reply_board_DTO ViewReply_board(int rpnum) {
		String sql = "select * from reply_board where rpnum=?";
		
		Reply_board_DTO dto = new Reply_board_DTO();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setInt(1, rpnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto.setRpnum(rs.getInt("rpnum"));
				dto.setContent(rs.getString("content"));
				dto.setNickname(rs.getString("nickname"));
				dto.setId(rs.getString("id"));
				dto.setBoard(rs.getString("board"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setNum(rs.getInt("num"));
			}
			
			int rpnum01 = dto.getRpnum();
			System.out.println("db 占쏙옙占쏙옙 占쏙옙 : "+rpnum01);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps !=null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}return dto;
	}
	
	
	
}
