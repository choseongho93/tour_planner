package com.spirng.DAO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class chat_DTO {//나머진 생성자..
	
	private String userId, userNickname, content;
	long chattime;
	String str_time;
	
	public long getChattime() {	return chattime;}
	public void setChattime(long chattime) {this.chattime = chattime;}
	
	public String getStr_time() {return str_time;}
	public void setStr_time() {this.str_time = parseToStringTime(getChattime());}
	
	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	
	public String getUserNickname() {return userNickname;}
	public void setUserNickname(String userNickname) {this.userNickname = userNickname;}
	
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	
	//시간을 스트링 형태로 변경시켜주는 함수
	public String parseToStringTime(long timeInMills) {
		SimpleDateFormat getTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = getTime.format(new Date(timeInMills));
		
		return time;
	}
}
