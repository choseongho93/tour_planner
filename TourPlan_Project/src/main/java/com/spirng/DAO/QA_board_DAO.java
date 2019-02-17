package com.spirng.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QA_board_DAO{
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user="jsp";
	private String pwd="1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public QA_board_DAO() {
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//1. QA寃뚯떆湲� 湲� 媛��졇���꽌 蹂댁뿬二쇨린
	public ArrayList<QA_board_DTO> ViewQA_board(int Start,int end){
		//String sql = "select A.* from(select * from QA_board)A order by num asc";
		String sql = "select B.* from(Select rownum rn, A.* from(select * from QA_board order by num desc)A)B where rn between " + Start + " and " + end;
		
		ArrayList<QA_board_DTO> QA_boardView = new ArrayList<QA_board_DTO>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps= con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				QA_board_DTO dto = new QA_board_DTO();
				
				dto.setId(rs.getString("id"));
				dto.setnickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setNum(rs.getInt("num"));
				QA_boardView.add(dto);
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
			
		}return QA_boardView;
	}
	
	//2. QA寃뚯떆湲� 湲� �벑濡앺븯湲� (0�씠硫� �벑濡� �떎�뙣, 1�씠硫� �벑濡� �꽦怨�)
	public int RegisterQA_board(String id, String nickname, String title, String content) {
		String sql = "insert into QA_board(id,nickname,title,content,hit,savedate,num)"
				+"values(?,?,?,?,0,sysdate,QA_board_seq.nextval)";
		int result=0;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, nickname);
			ps.setString(3, title);
			ps.setString(4, content);
	
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
		}
		return result;
	}
	
	//3.踰덊샇濡� �븯�굹�쓽 寃뚯떆湲� �궡�슜 蹂댁뿬二쇨린
	public QA_board_DTO ViewContent_QA_board(String num) {
		upHit(num); //議고쉶�닔
		String sql = "select * from QA_board where num=?";
		
		QA_board_DTO dto = new QA_board_DTO();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setnickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setHit(rs.getInt("hit"));
				dto.setSavedate(rs.getTimestamp("savedate"));
			}
		}catch (Exception e) {
			// TODO: handle exception
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
	//4. QA寃뚯떆�뙋 寃뚯떆湲� �닔�젙
	public void ModifyContent_QA_board(String title, String content, int num) {
		String sql = "update QA_board set title=?,content=? where num=?";
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, num);
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
	//5. QA寃뚯떆�뙋 寃뚯떆湲� �궘�젣
	public void DeleteContent_QA_board(String num) {
		String sql = "delete from QA_board where num=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
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
	//6. 議고쉶�닔
	public void upHit(String id) {
		String sql = "update QA_board set hit=hit+1 where num=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps !=null) ps.close();
				if(con != null) con.close();
			}catch(Exception e) {}
		}
	}
	
	//7.total page
	public int getTotalPageOfTipBoard() {
		String sql = "select count(*) from QA_board";
		int totalPage = 0;
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalPage = rs.getInt(1);
				System.out.println("SYSTEM : QA_board totalpage (" + totalPage + ")");
			}
		}catch (Exception e) {
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
			}
		}
		
		return totalPage;
	}
	//8. 寃��깋
	public ArrayList<QA_board_DTO> searchQABoard(String subject, String word, int start, int end){
		String sql = "select B.* from(Select rownum rn, A.* from(select * from QA_board where "+ subject +" like '%"+word+"%' order by num desc)A)B where rn between " + start + " and " + end;
		
		ArrayList<QA_board_DTO> list = new ArrayList<QA_board_DTO>();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QA_board_DTO dto = new QA_board_DTO();
				
				dto.setId(rs.getString("id"));
				dto.setnickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setNum(rs.getInt("num"));
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
	
	//9. 寃��깋�쟾泥� �럹�씠吏� �닔
	public int searchTotalPageOfQABoard(String subject, String searchWord) {
		String sql = "select count(*) from QA_board where "+ subject +" like '%"+searchWord+"%'";
		int totalPage=0;
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalPage = rs.getInt(1);
				System.out.println("SYSTEM : QA_Board 寃��깋 珥� �닔 = " + totalPage);
			}
		}catch (Exception e) {
		}finally {
			try {
				if(rs != null)	rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
			}
		}
		return totalPage;
	}
	
	
}



