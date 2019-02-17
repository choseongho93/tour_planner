<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
	
	<style>
		
	.text-stroke {
   		 text-shadow:
    	-1px -1px 0 #000,
    	1px -1px 0 #000,
   	 	-1px 1px 0 #000,
   		1px 1px 0 #000;  
	}
	
    td {
		border-radius: 5px;
	}
	table {
		border-radius: 5px;
		text-align: center;
	}
	
	.mainButton{
		background-color: #0064CD;
		color: white;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.subButton{
		background-color: white;
		color: black;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	select {
		background-color: #0064CD;
		color: white;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.textbox {
		background-color: white;
		color: #0064CD;
		border: none;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
    </style>

</head>
<body style="font-family: 돋움; background-color: #ECEBFF; font-size: 10pt;">
	<form action="modify_reply_board" method="post">
		<div align="center" style="width: 450px;">
			<table style="border-radius: 5px; width: 450px;">
				<tr height="30px;">
					<td colspan="4" style="text-align: center;"><font class="text-stroke" style="color: white; font-family:Broadway; font-size:18pt;">Modify</font></td>
				</tr>
				<tr style="height: 20px;">
					<td style="background-color:#0064CD; color: white; font-weight: bold;">작성자</td>
					<td style="color: #0064CD; background-color: white;">${modify_Reply.nickname}</td>
					
					<td style="background-color:#0064CD; color: white; font-weight: bold;">작성일시</td>
					<td style="color: #0064CD; background-color: white;">${modify_Reply.savedate}</td>
				</tr>
				<tr style="height: 20px;">
					<td colspan="4" style="background-color:#0064CD; color: white; font-weight: bold;">내용</td>
				</tr>
				<tr style="height: 30px;">
					<td colspan="4">
						<textarea class="textbox" rows="4" cols="65" name="modifyTextReply">${modify_Reply.content}
						</textarea>
					</td>
				</tr>
				<tr align="right" >
					<td colspan="4" align="center" style="height: 20px;">
						<button class="mainButton" style="height: 25px; width: 40px;" type="button" onclick="cancle()">취소</button>
						<input class="mainButton"  style="height: 25px; width: 40px;" type="submit" value="수정">
						
						<input type="hidden" name="boardnumber" value="${boardNumber}">
						<input type="hidden" name="replynumber" value="${modify_Reply.rpnum}">
					</td>
				</tr>
			</table>
		</div>
	</form>
	
	<script>//취소 버튼 눌렀을 때 화면 닫는 기능.
		function cancle(){
			self.close();
		}
	</script>
</body>
</html>