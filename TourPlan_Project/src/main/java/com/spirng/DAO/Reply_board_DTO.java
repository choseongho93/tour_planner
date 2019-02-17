package com.spirng.DAO;

import java.sql.Timestamp;

public class Reply_board_DTO {
	private String board, id, nickname, content;
	private Timestamp savedate;
	int num, rpnum;
	
	public int getRpnum() {return rpnum;}
	public void setRpnum(int rpnum) {this.rpnum = rpnum;}
	
	public String getBoard() {	return board;}
	public void setBoard(String board) {this.board = board;}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String getNickname() {return nickname;}
	public void setNickname(String nickname) {this.nickname = nickname;}
	
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	
	public Timestamp getSavedate() {return savedate;}
	public void setSavedate(Timestamp savedate) {this.savedate = savedate;}
	
	public int getNum() {return num;}
	public void setNum(int num) {this.num = num;}
		
}
