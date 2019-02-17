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

import org.aspectj.weaver.NewFieldTypeMunger;

public class Tip_board_DAO {
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@210.92.47.25:1521:xe";
	private String user = "jsp";
	private String pwd = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Tip_board_DAO() {
		try {
			Class.forName(driver);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙
	public ArrayList<Tip_board_DTO> viewTipBoard(int Start, int end) {
		String sql = "select B.* from(Select rownum rn, A.* from(select * from tip_board order by num desc)A)B where rn between " + Start + " and " + end;
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
	
	//占쏙옙체 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�
	public int getTotalPageOfTipBoard() {
		String sql = "select count(*) from tip_board";
		int totalPage = 0;
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalPage = rs.getInt(1);
				System.out.println("SYSTEM : Tip_Board 占쏙옙체 占쏙옙占쏙옙占쏙옙占쏙옙 (" + totalPage + ")");
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
	
	//占싯삼옙 占쏙옙占� 占쏙옙占�
	public ArrayList<Tip_board_DTO> searchTipBoard(String subject, String word, int start, int end){
		String sql = "select B.* from(Select rownum rn, A.* from(select * from tip_board where "+ subject +" like '%"+word+"%' order by num desc)A)B where rn between " + start + " and " + end;
		
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
	
	//占싯삼옙 占쏙옙占쏙옙占� 占쏙옙체 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�
	public int searchTotalPageOfTipBoard(String subject, String searchWord) {
		String sql = "select count(*) from tip_board where "+ subject +" like '%"+searchWord+"%'";
		int totalPage=0;
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalPage = rs.getInt(1);
				System.out.println("SYSTEM : Tip_Board 占싯삼옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙 = " + totalPage);
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
	
	//占쏙옙 占쏙옙占쏙옙
	public void insertTipBoard(Tip_board_DTO dto, int fileNum) throws FileNotFoundException {
		ArrayList<ImageFile_DTO> imgInfo = new ArrayList<ImageFile_DTO>();

		if(fileNum > 0) {//占싱뱄옙占쏙옙占쏙옙 占싹놂옙占쏙옙 占쏙옙占�
			ImageFile_DTO img1 = new ImageFile_DTO();
			img1.setImgName(dto.getImagename1());
			img1.setFilePath(dto.getImg_path1());
			img1.setBasicPath(new File(dto.getImg_path1()));
			img1.setFis(new FileInputStream(new File(img1.getBasicPath(),img1.getImgName())));
			imgInfo.add(img1);//占싱뱄옙占쏙옙占쏙옙 占싼곤옙占쏙옙 占쏙옙占� 占쏙옙占썩서 占쏙옙.
			
			if(fileNum == 2) {//占싱뱄옙占쏙옙占쏙옙 占싸곤옙占쏙옙 占쏙옙占�
				ImageFile_DTO img2 = new ImageFile_DTO();
				img2.setImgName(dto.getImagename2());
				img2.setFilePath(dto.getImg_path2());
				
				System.out.println("占싱뱄옙占쏙옙 占쏙옙占� : " + dto.getImg_path2());
				
				img2.setBasicPath(new File(dto.getImg_path2()));
				img2.setFis(new FileInputStream(new File(img2.getBasicPath(),img2.getImgName())));
				imgInfo.add(img2);//占싱뱄옙占쏙옙占쏙옙 占싸곤옙占쏙옙 占쏙옙占� 占쏙옙占썩서 占쏙옙.
			}
		}else {//占싱뱄옙占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쌕뤄옙 占쌉뤄옙占싹곤옙 占쏙옙占쏙옙
			String sql = "insert into tip_board(subject,title,content,nickname,id,hit,num) values(?,?,?,?,?,0,tip_board_seq.nextval)";
			
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
		}//占싱뱄옙占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙 占쌉뤄옙占싹곤옙 占싼어감
			System.out.println("占싱뱄옙占쏙옙 占쏙옙占쏙옙 占썼열 占쏙옙占쏙옙 : " + imgInfo.size());
			if(imgInfo.size() == 1) {//占싱뱄옙占쏙옙 占싹놂옙占싹띰옙
				String sql = "insert into tip_board(subject,title,content,nickname,id,imagename1,imgfile1,img_path1,hit,num) values(?,?,?,?,?,?,?,?,0,tip_board_seq.nextval)";

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
			else if(imgInfo.size() == 2) {//占싱뱄옙占쏙옙 占싸곤옙占싹띰옙.
				String sql = "insert into tip_board(subject,title,content,nickname,id,imagename1,imgfile1,img_path1,imagename2,imgfile2,img_path2,hit,num) values(?,?,?,?,?,?,?,?,?,?,?,0,tip_board_seq.nextval)";
				
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				ps.setString(4, dto.getNickname());
				ps.setString(5, dto.getId());
				
				ps.setString(6, imgInfo.get(0).getImgName());//占싱뱄옙占쏙옙 占싱몌옙
				File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(7, (imgInfo.get(0)).getFis(), (int)f1.length());//占쏙옙占쏙옙트 占쏙옙占쏙옙
				
				ps.setString(8, dto.getImg_path1());//占쏙옙占�
				
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
	
	//占쏙옙 占쏙옙占쏙옙
	public Tip_board_DTO selectTipView(String num) {
		
		String sql = "select * from tip_board where num=?";
		upTheHit(num);
		
		Tip_board_DTO dto = new Tip_board_DTO();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setTitle(rs.getString("title"));
				dto.setNickname(rs.getString("nickname"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setImagename1(rs.getString("imagename1"));
				dto.setImg_path1(rs.getString("img_path1"));
				dto.setImage1(rs.getBlob("imgfile1"));
				dto.setImagename2(rs.getString("imagename2"));
				dto.setImg_path2(rs.getString("img_path2"));
				dto.setImage2(rs.getBlob("imgfile2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//if(rs != null) rs.close();
				//if(ps != null) ps.close();
				//if(con != null) con.close();
			}catch (Exception e) {}
		}	
		return dto;
	}
	
	//占쏙옙占� 占쏙옙占� 호占쏙옙
	public ArrayList<Reply_board_DTO> getReplyOfTip(String num){
		String sql = "select * from reply_board where board='tip_board' and num=? order by savedate asc";

		ArrayList<Reply_board_DTO> rp_list = new ArrayList<Reply_board_DTO>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reply_board_DTO dto = new Reply_board_DTO();
				dto.setNum(rs.getInt("num"));
				dto.setBoard(rs.getString("board"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setRpnum(rs.getInt("rpnum"));
				
				rp_list.add(dto);
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
		return rp_list;
	}
	
	//占쏙옙占� 占쌨깍옙
	public void saveReplyIntoTipBoard(Reply_board_DTO dto) {
		String sql = "insert into reply_board(board,nickname,id,content,num,rpnum) values(?,?,?,?,?,reply_board_seq.nextval)";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBoard());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getId());
			ps.setString(4, dto.getContent());
			ps.setInt(5, dto.getNum());
			
			 int result = ps.executeUpdate();
			 System.out.println("result : " + result);			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {
			}
		}
	}
	
	//占쏙옙회占쏙옙 占시몌옙
	public void upTheHit(String num) {
		String sql = "update tip_board set hit=hit+1 where num=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
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
	
	//占쏙옙 占쏙옙占쏙옙
	public void modifyTipBoard(Tip_board_DTO dto, String boardNumber, int lastImgNum) {
		//占싱뱄옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙占� 占쌍겠쏙옙占싹댐옙..
		ArrayList<ImageFile_DTO> imgInfo = new ArrayList<ImageFile_DTO>();
		String sql = "update tip_board set subject=?, title=?, content=?, imagename1=?, imgfile1=?, img_path1=?, imagename2=?, imgfile2=?, img_path2=? where num=?";
		
		if(lastImgNum > 0){//占싱뱄옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹몌옙.
			ImageFile_DTO img1 = new ImageFile_DTO();
			img1.setImgName(dto.getImagename1());
			img1.setFilePath(dto.getImg_path1());
			img1.setBasicPath(new File(dto.getImg_path1()));
			
			try {
				img1.setFis(new FileInputStream(new File(img1.getBasicPath(),img1.getImgName())));
			} catch (FileNotFoundException e) {e.printStackTrace();}
			
			imgInfo.add(img1);//占싱뱄옙占쏙옙占쏙옙 占싼곤옙占쏙옙 占쏙옙占� 占쏙옙占썩서 占쏙옙.
			
			if(lastImgNum == 2) {//占싱뱄옙占쏙옙占쏙옙 占싸곤옙占쏙옙 占쏙옙占�
				ImageFile_DTO img2 = new ImageFile_DTO();
				img2.setImgName(dto.getImagename2());
				img2.setFilePath(dto.getImg_path2());
				
				System.out.println("占싱뱄옙占쏙옙 占쏙옙占� : " + dto.getImg_path2());
				
				img2.setBasicPath(new File(dto.getImg_path2()));
				try {
					img2.setFis(new FileInputStream(new File(img2.getBasicPath(),img2.getImgName())));
				} catch (FileNotFoundException e) {e.printStackTrace();}
				
				imgInfo.add(img2);//占싱뱄옙占쏙옙占쏙옙 占싸곤옙占쏙옙 占쏙옙占� 占쏙옙占썩서 占쏙옙.
			}
		}
		
		if(lastImgNum == 0) {
			System.out.println("占싱뱄옙占쏙옙 0占쏙옙");
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());//占쏙옙占쏙옙占� 占싻뤄옙
				ps.setString(2, dto.getTitle());//占쏙옙占쏙옙占� 占쏙옙占쏙옙
				ps.setString(3, dto.getContent());//占쏙옙占쏙옙占� 占쏙옙占쏙옙
				
				ps.setString(4, dto.getImagename1());//占싱뱄옙占쏙옙 占싱몌옙
				//File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(5, null);//占쏙옙占쏙옙트 占쏙옙占쏙옙	
				ps.setString(6, dto.getImg_path1());//占쏙옙占�
				
				ps.setString(7, dto.getImagename2());
			//	File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(8, null);
				ps.setString(9, dto.getImg_path2());

				ps.setString(10, boardNumber);//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占싼뱄옙
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
			System.out.println("占싱뱄옙占쏙옙 1占쏙옙");
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());//占쏙옙占쏙옙占� 占싻뤄옙
				ps.setString(2, dto.getTitle());//占쏙옙占쏙옙占� 占쏙옙占쏙옙
				ps.setString(3, dto.getContent());//占쏙옙占쏙옙占� 占쏙옙占쏙옙
				
				ps.setString(4, imgInfo.get(0).getImgName());//占싱뱄옙占쏙옙 占싱몌옙
				File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(5, (imgInfo.get(0)).getFis(), (int)f1.length());//占쏙옙占쏙옙트 占쏙옙占쏙옙	
				ps.setString(6, dto.getImg_path1());//占쏙옙占�
				
				ps.setString(7, null);
			//	File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(8, null);
				ps.setString(9, null);

				ps.setString(10, boardNumber);//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占싼뱄옙
				ps.executeUpdate();
				
			//	ps.setString(7, boardNumber);//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占싼뱄옙
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
			System.out.println("占싱뱄옙占쏙옙 2占쏙옙");
			try {
				con = DriverManager.getConnection(url, user, pwd);
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getSubject());//占쏙옙占쏙옙占� 占싻뤄옙
				ps.setString(2, dto.getTitle());//占쏙옙占쏙옙占� 占쏙옙占쏙옙
				ps.setString(3, dto.getContent());//占쏙옙占쏙옙占� 占쏙옙占쏙옙
				
				ps.setString(4, imgInfo.get(0).getImgName());//占싱뱄옙占쏙옙 占싱몌옙
				File f1 = new File(imgInfo.get(0).getBasicPath(),imgInfo.get(0).getImgName());
				ps.setBinaryStream(5, (imgInfo.get(0)).getFis(), (int)f1.length());//占쏙옙占쏙옙트 占쏙옙占쏙옙	
				ps.setString(6, dto.getImg_path1());//占쏙옙占�
				
				ps.setString(7, imgInfo.get(1).getImgName());
				File f2 = new File(imgInfo.get(1).getBasicPath(),imgInfo.get(1).getImgName());
				ps.setBinaryStream(8, (imgInfo.get(1)).getFis(), (int)f2.length());
				ps.setString(9, dto.getImg_path2());

				ps.setString(10, boardNumber);//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占싼뱄옙
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
	
	//占쏙옙 占쏙옙占쏙옙
	public void deleteTipBoard(String num) {
		String sql = "delete from tip_board where num=?";
		
		deleteReplyOfTipBoard(num);
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
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
	
	//占쏙옙 占쏙옙占쏙옙占쏙옙 占쌔댐옙 占쌜울옙 占쌨뤄옙占쌍댐옙 占쏙옙滂俑� 占싹곤옙 占쏙옙占쏙옙占실댐옙 占쏙옙占�
	public void deleteReplyOfTipBoard(String num) {
		String sql = "delete from reply_board where board='tip_board' and num=?";

		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, num);
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
	
	//占쏙옙占� 占쏙옙占쏙옙
	public void deleteReplyBoard(String rpnum) {
		String sql = "delete from reply_board where board='tip_board' and rpnum=?";

		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, rpnum);
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
	
	//占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占�
	public void modifyReplyBoard(String rpnum, String content) {
		String sql = "update reply_board set content=? where rpnum=?";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setString(2, rpnum);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch (Exception e) {}
		}			
	}
	
	//占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌔댐옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�.
	public Reply_board_DTO modifiyReplyView(String rpnum) {
		String sql = "select * from reply_board where rpnum=?";
		
		Reply_board_DTO dto = new Reply_board_DTO();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, rpnum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setBoard(rs.getString("board"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setRpnum(rs.getInt("rpnum"));
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
		
		return dto;
	}
	
	
	
	
	
	
	
}
