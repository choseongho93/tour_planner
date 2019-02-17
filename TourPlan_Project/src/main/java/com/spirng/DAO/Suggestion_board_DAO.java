package com.spirng.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Suggestion_board_DAO {
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user="jsp";
	private String pwd="1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Suggestion_board_DAO() {
		try {
			Class.forName(driver);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//1. suggestion 寃뚯떆湲� 湲� 媛��졇���꽌 蹂댁뿬二쇨린
	public ArrayList<Suggestion_board_DTO> ViewSuggestion_board(int Start, int end){
		//String sql = "select A.* from(select * from Suggestion_board)A order by num asc";
		String sql = "select B.* from(Select rownum rn, A.* from(select * from Suggestion_board order by num desc)A)B where rn between "+Start +" and " + end;
		ArrayList<Suggestion_board_DTO> Suggestion_boardView = new ArrayList<Suggestion_board_DTO>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Suggestion_board_DTO dto = new Suggestion_board_DTO();
				
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setNickname(rs.getString("nickname"));
				dto.setId(rs.getString("id"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				//�씠誘몄�
				//dto.setImgfile1(rs.getBlob("imgfile1"));
				//dto.setImagename1(rs.getString("imagename1"));
				//dto.setImg_path1(rs.getString("img_path1"));
				Suggestion_boardView.add(dto);
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
		}return Suggestion_boardView;
	}
	
	public void insertTipBoard(Suggestion_board_DTO dto, int fileNum) throws FileNotFoundException {
		ArrayList<ImageFile_DTO> imgInfo = new ArrayList<ImageFile_DTO>();

		if(fileNum > 0) {
			ImageFile_DTO img1 = new ImageFile_DTO();
			img1.setImgName(dto.getImagename1());
			img1.setFilePath(dto.getImg_path1());
			img1.setBasicPath(new File(dto.getImg_path1()));
			img1.setFis(new FileInputStream(new File(img1.getBasicPath(),img1.getImgName())));
			imgInfo.add(img1);
			
			if(fileNum == 2) {
				ImageFile_DTO img2 = new ImageFile_DTO();
				img2.setImgName(dto.getImagename2());
				img2.setFilePath(dto.getImg_path2());
				
				System.out.println("�씠誘몄� 寃쎈줈2 : " + dto.getImg_path2());
				
				img2.setBasicPath(new File(dto.getImg_path2()));
				img2.setFis(new FileInputStream(new File(img2.getBasicPath(),img2.getImgName())));
				imgInfo.add(img2);
			}
		}else {
			String sql = "insert into suggestion_board(subject,title,content,nickname,id,hit,num) values(?,?,?,?,?,0,suggestion_board_seq.nextval)";
			
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				ps.setString(4, dto.getNickname());
				ps.setString(5, dto.getId());
			
				ps.executeUpdate();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(ps!= null) ps.close();
						if(con != null) con.close();
					}catch(Exception e) {
						e.printStackTrace();
				}
			}
		}
			System.out.println("�씠誘몄� �궗�씠利� : " + imgInfo.size());
			if(imgInfo.size() == 1) {
				String sql = "insert into suggestion_board(subject,title,content,nickname,id,imagename1,imgfile1,img_path1,hit,num) values(?,?,?,?,?,?,?,?,0,suggestion_board_seq.nextval)";

				try {
					con = DriverManager.getConnection(url, user, pwd);
					ps = con.prepareStatement(sql);
					ps.setString(1, dto.getSubject());
					ps.setString(2, dto.getTitle());
					ps.setString(3, dto.getContent());
					ps.setString(4, dto.getNickname());
					ps.setString(5, dto.getId());
					
					ps.setString(6, imgInfo.get(0).getImgName());
					File f = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
					ps.setBinaryStream(7, (imgInfo.get(0)).getFis(), (int)f.length());
					
					ps.setString(8, dto.getImg_path1());
					
					ps.executeUpdate();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(ps!= null) ps.close();
						if(con != null) con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			else if(imgInfo.size() == 2) {
				String sql = "insert into suggestion_board(subject,title,content,nickname,id,imagename1,imgfile1,img_path1,imagename2,imgfile2,img_path2,hit,num) values(?,?,?,?,?,?,?,?,?,?,?,0,suggestion_board_seq.nextval)";
				
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				ps.setString(4, dto.getNickname());
				ps.setString(5, dto.getId());
				
				ps.setString(6, imgInfo.get(0).getImgName());
				File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(7, (imgInfo.get(0)).getFis(), (int)f1.length());
				
				ps.setString(8, dto.getImg_path1());
				
				ps.setString(9, imgInfo.get(1).getImgName());
				File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(10, (imgInfo.get(1)).getFis(), (int)f2.length());
				
				ps.setString(11, dto.getImg_path2());
				
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();		
			}finally {
				try {
					if(ps != null) ps.close();
					if(con != null) con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	//3. 踰덊샇(num)�븯�굹濡� 寃뚯떆湲� �궡�슜 蹂댁뿬二쇨린
	public Suggestion_board_DTO ViewContent_Suggestion_board(String num) {
		upHit(num); //議고쉶�닔
		String sql = "select * from Suggestion_board where num=?";
		
		Suggestion_board_DTO dto = new Suggestion_board_DTO();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setNum(rs.getInt("num"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setSubject(rs.getString("subject"));
				dto.setTitle(rs.getString("title"));
				dto.setImagename1(rs.getString("imagename1"));
				dto.setImg_path1(rs.getString("img_path1"));
				dto.setImage1(rs.getBlob("imgfile1"));
				dto.setImagename2(rs.getString("imagename2"));
				dto.setImg_path2(rs.getString("img_path2"));
				dto.setImage2(rs.getBlob("imgfile2"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				//if(rs!=null) rs.close();
				//if(ps !=null) ps.close();
				//if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}	
		}return dto;
	}
	//4. suggestion 寃뚯떆湲� �궘�젣
	public void DeleteContent_Suggestion_board(String num) {
		String sql = "delete from Suggestion_board where num=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			ps.executeQuery();
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
		String sql = "update Suggestion_board set hit=hit+1 where num=?";
		
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
	
	//7. page - Suggestion_board�뿉 �엳�뒗 �쟾泥� 湲�(�뻾�쓽 媛쒖닔)
	public int getTotalPageOfSuggestion() {
		String sql = "select count(*) from Suggestion_board"; // Suggestion_board�뿉 �엳�뒗 湲� 移댁슫�듃
		int totalPage = 0;
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalPage = rs.getInt(1);//泥ル쾲吏� 移쇰읆(媛��졇�삩 寃곌낵臾�)�뿉�꽌 �뜲�씠�꽣 �뼸�뼱�삤湲�
				System.out.println("SYSTEM : Suggestion totalPage ("  +totalPage +")");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return totalPage;
	}

	//8. 寃��깋 媛� list�뿉 �꽔�뼱 蹂댁뿬二쇨린
	public ArrayList<Suggestion_board_DTO> searchSuggestionBoard(String subject, String word, int start, int end){
		//A : SB �빆紐⑹뿉�꽌 word媛� �뱾�뼱媛� �떒�뼱瑜� num�쑝濡� �궡由쇱감�닚 / start, end �궗�씠濡� select
		String sql = "select B.* from(Select rownum rn, A.* from(select * from Suggestion_board where "+ subject + " like '%"+ word +"%' order by num desc)A)B where rn between " +start +" and "+end;
		
		ArrayList<Suggestion_board_DTO> list = new ArrayList<Suggestion_board_DTO>();
		try{
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Suggestion_board_DTO dto = new Suggestion_board_DTO();
				
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				
				list.add(dto);
 			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	//9. 寃��깋 �쟾泥� 寃뚯떆湲� �닔
	public int searchTotalPageOfSuggestionBoard(String subject, String searchWord) {
		String sql = "select count(*) from Suggestion_board where "+ subject +" like '%"+searchWord+"%'";
		int totalPage = 0;
		
		try
		{
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				totalPage = rs.getInt(1);
				System.out.println("SYSTEM : Suggestion_Board 寃��깋 珥� �닔 = " + totalPage);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try{
				if(rs != null)	rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}return totalPage;
	}
	//5.suggestion 寃뚯떆湲� �닔�젙
	public void ModifyContent_Suggestion_board(String title, String content, int num) {
		String sql = "update Suggestion_board set title=?,content=? where num=?";
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
	//10. �씠誘몄� �닔�젙
	public void modifyTipBoard(Suggestion_board_DTO dto, String num, int lastImgNum) {

		ArrayList<ImageFile_DTO> imgInfo = new ArrayList<ImageFile_DTO>();
		String sql = "update Suggestion_board set subject=?, title=?, content=?, imagename1=?, imgfile1=?, img_path1=?, imagename2=?, imgfile2=?, img_path2=? where num=?";
		
		if(lastImgNum > 0){
			ImageFile_DTO img1 = new ImageFile_DTO();
			img1.setImgName(dto.getImagename1());
			img1.setFilePath(dto.getImg_path1());
			img1.setBasicPath(new File(dto.getImg_path1()));
			
			try {
				img1.setFis(new FileInputStream(new File(img1.getBasicPath(),img1.getImgName())));
			} catch (FileNotFoundException e) {e.printStackTrace();}
			
			imgInfo.add(img1);
			
			if(lastImgNum == 2) {
				ImageFile_DTO img2 = new ImageFile_DTO();
				img2.setImgName(dto.getImagename2());
				img2.setFilePath(dto.getImg_path2());
				
				System.out.println("img_寃쎈줈2 : " + dto.getImg_path2());
				
				img2.setBasicPath(new File(dto.getImg_path2()));
				try {
					img2.setFis(new FileInputStream(new File(img2.getBasicPath(),img2.getImgName())));
				} catch (FileNotFoundException e) {e.printStackTrace();}
				
				imgInfo.add(img2);
			}
		}
		
		if(lastImgNum == 0) {
			System.out.println("�씠誘몄� �뾾�쓣 寃쎌슦");
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				
				ps.setString(4, dto.getImagename1());
				//File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(5, null);
				ps.setString(6, dto.getImg_path1());
				
				ps.setString(7, dto.getImagename2());
			//	File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(8, null);
				ps.setString(9, dto.getImg_path2());

				ps.setString(10, num);
				ps.executeUpdate();

				//ps.setString(4, boardNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(ps != null) ps.close();
					if(con != null) con.close();
				}catch (Exception e) {}
			}
		}
		else if(lastImgNum == 1) {
			System.out.println("�씠誘몄� 1�씪 寃쎌슦");
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				
				ps.setString(4, imgInfo.get(0).getImgName());
				File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(5, (imgInfo.get(0)).getFis(), (int)f1.length());
				ps.setString(6, dto.getImg_path1());
				
				ps.setString(7, null);
			//	File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(8, null);
				ps.setString(9, null);

				ps.setString(10, num);
				ps.executeUpdate();
				
			//	ps.setString(7, boardNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(ps != null) ps.close();
					if(con != null) con.close();
				}catch (Exception e) {}
			}			
		}
		else if(lastImgNum == 2) {
			System.out.println("�씠誘몄� 2媛쒖씪 寃�");
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				
				ps.setString(4, imgInfo.get(0).getImgName());
				File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(5, (imgInfo.get(0)).getFis(), (int)f1.length());
				ps.setString(6, dto.getImg_path1());
				
				ps.setString(7, imgInfo.get(1).getImgName());
				File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(8, (imgInfo.get(1)).getFis(), (int)f2.length());
				ps.setString(9, dto.getImg_path2());

				ps.setString(10, num);
				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(ps != null) ps.close();
					if(con != null) con.close();
				}catch (Exception e) {}
			}			
		}
	}
}
