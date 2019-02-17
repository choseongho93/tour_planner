package com.spirng.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class MemberDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user = "jsp";
	private String password = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MemberDAO() {
		try {
			Class.forName(driver);
		}
		catch (Exception e) {}
	}
	
	public int idCheck(String id) {
		int val = 0; //?占쏙옙?占쏙옙 占�??占쏙옙
		String sql = "select * from member where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			val = ps.executeUpdate(); //以�蹂�
		}
		catch (Exception e) {}
		finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return val;
	}
	
	public int nicknameCheck(String nickname) {
		int val = 0; //?占쏙옙?占쏙옙 占�??占쏙옙
		String sql = "select * from member where nickname=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			val = ps.executeUpdate(); //以�蹂�
		}
		catch (Exception e) {}
		finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return val;
	}

	public MemberDTO loginMember(String id, String pwd) {
		MemberDTO dto = new MemberDTO(); //濡�洹�?占쏙옙 ?占쏙옙?占쏙옙
		String sql = "select * from member where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) { //濡�洹�?占쏙옙 ?占쏙옙占�?
					dto.setId(rs.getString("id"));
					dto.setNickname(rs.getString("nickname"));
				}
			}
		}
		catch (Exception e) {}
		finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			}
			catch (Exception e) {}
		}
		return dto;
	}

	public int registrationMember(MemberDTO dto) {
		System.out.println("?占쏙옙占�?");
		int val = 0; //?占쏙옙?占쏙옙占�??占쏙옙 ?占쏙옙?占쏙옙
		String sql = "insert into member(id, pwd, name, gender, birth, age, email, nickname, question, answer) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			ps.setInt(4, dto.getGender());
			ps.setString(5, dto.getBirth());
			ps.setInt(6, dto.getAge());
			ps.setString(7, dto.getEmail());
			ps.setString(8, dto.getNickname());
			ps.setInt(9, dto.getQuestion());
			ps.setString(10, dto.getAnswer());
			val = ps.executeUpdate(); //?占쏙옙?占쏙옙占�??占쏙옙 ?占쏙옙占�?
		}
		catch (Exception e) {}
		finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}

		return val;
	}
	
	public MemberDTO idInquiryMember(String name, String birth) {
		MemberDTO dto = new MemberDTO();
		String sql = "select * from member where name=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("birth").equals(birth)) {
					dto.setId(rs.getString("id"));
				}
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
		return dto;
	}
	
	public MemberDTO pwdInquiryMember(String id, String question, String answer) {
		MemberDTO dto = new MemberDTO();
		String sql = "select * from member where id = ?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("question").equals(question)) {
					if(rs.getString("answer").equals(answer)) {
						dto.setId(rs.getString("id"));
					}
				}
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
		return dto;
	}
	
	public int pwdUpdateMember(String id, String pwd) {
		int val = 0;
		String sql = "update member set pwd=? where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setString(2, id);
			val = ps.executeUpdate();
		}
		catch (Exception e) {}
		finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {}
		}
		return val;
	}
	
	public MemberDTO userCheck(MemberDTO inputDTO) {
		MemberDTO dto = new MemberDTO();
		String sql = "select * from member where id = ?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, inputDTO.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("pwd").equals(inputDTO.getPwd())) {
					dto.setId(rs.getString("id"));
					dto.setPwd(rs.getString("pwd"));
					dto.setName(rs.getString("name"));
					dto.setGender(rs.getInt("gender"));
					dto.setEmail(rs.getString("email"));
					dto.setBirth(rs.getString("birth"));
					dto.setAge(rs.getInt("age"));
					dto.setNickname(rs.getString("nickname"));
				}
			}
		}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {e.printStackTrace();}
		}
		return dto;
	}
	
	public int userInfoChangeMember(String id, String email, String nickname) {
		int val = 0;
		String sql = "update member set email=?, nickname=? where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, nickname);
			ps.setString(3, id);
			val = ps.executeUpdate();
		}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {e.printStackTrace();}
		}
		return val;
	}
	
	public int userPwdChangeMember(MemberDTO inputDTO, String pwdChange) {
		MemberDTO dto = new MemberDTO();
		dto = userCheck(inputDTO);
		int val = 0;
		String sql = "update member set pwd=? where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, pwdChange);
			ps.setString(2, dto.getId());
			val = ps.executeUpdate();
		}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				
			}
			catch (Exception e) {e.printStackTrace();}
		}
		return val;
	}
	
	public int userDeleteMember(String id) {
		int val = 0;
		String sql = "delete from member where id=?";
		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			val = ps.executeUpdate();
		}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch (Exception e) {e.printStackTrace();}
		}
		return val;
	}
}
