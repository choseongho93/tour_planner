package com.spirng.DAO;

import java.sql.Timestamp;

public class QA_board_DTO {

		private String id; //���̵�
		private String nickname; //�г���
		private String title; //����
		private String content; //����
		private int hit; //��ȸ��
		private Timestamp savedate; //�ۼ�����
		private int num; //�Խñ� ��ȣ
		
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
