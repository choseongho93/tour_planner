package com.spirng.DAO;

import java.sql.Blob;
import java.sql.Timestamp;

public class Suggestion_board_DTO {
	
	private String subject; // 항목
	private String title; //제목
	private String content; //내용
	private String nickname; //닉네임
	private String id; //아이디
	private Timestamp savedate; //작성일자
	private int hit; //조회수
	private int num; // 게시글 번호
	private String imagename1, img_path1, imagename2, img_path2;//이미지 이름,이미지 경로
	public String getImagename1() {
		return imagename1;
	}
	public void setImagename1(String imagename1) {
		this.imagename1 = imagename1;
	}
	public String getImg_path1() {
		return img_path1;
	}
	public void setImg_path1(String img_path1) {
		this.img_path1 = img_path1;
	}
	public String getImagename2() {
		return imagename2;
	}
	public void setImagename2(String imagename2) {
		this.imagename2 = imagename2;
	}
	public String getImg_path2() {
		return img_path2;
	}
	public void setImg_path2(String img_path2) {
		this.img_path2 = img_path2;
	}
	public Blob getImage1() {
		return image1;
	}
	public void setImage1(Blob image1) {
		this.image1 = image1;
	}
	public Blob getImage2() {
		return image2;
	}
	public void setImage2(Blob image2) {
		this.image2 = image2;
	}
	private Blob image1, image2;
	

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getSavedate() {
		return savedate;
	}
	public void setSavedate(Timestamp savedate) {
		this.savedate = savedate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}



	

}
