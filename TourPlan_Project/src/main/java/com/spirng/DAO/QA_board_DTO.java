package com.spirng.DAO;

import java.sql.Timestamp;

public class QA_board_DTO {

		private String id; //아이디
		private String nickname; //닉네임
		private String title; //제목
		private String content; //내용
		private int hit; //조회수
		private Timestamp savedate; //작성일자
		private int num; //게시글 번호
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getnickname() {
			return nickname;
		}
		public void setnickname(String nickname) {
			this.nickname = nickname;
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
		public Timestamp getSavedate() {
			return savedate;
		}
		public void setSavedate(Timestamp string) {
			this.savedate = string;
		}
}
