<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {//데이터 전송시 빈 칸 체크
		$("form").submit(function() {
			var id=$("#id").val();
			var question=$("#question").val();
			var answer=$("#answer").val();
			if(id != "" && question != 0 && answer != "") {return true;}
			else {
				if(id == "") {$("#lblId").html("아이디를 입력해주세요.").css("color", "red");}
				if(question == 0) {$("#lblQuestion").html("질문을 선택해주세요.").css("color", "red");}
				if(answer == "") {$("#lblAnswer").html("답변을 입력해주세요.").css("color", "red");}
				return false;
			}
		});
		$("#id").focusout(function() {
			var id = $("#id").val();
			if(id == "") {$("#lblId").html("아이디를 입력해주세요.").css("color", "red");}
			else {$("#lblId").html("");}
		});
		$("#question").change(function() {
			var question = $("#question").val();
			if(question == 0) {$("#lblQuestion").html("질문을 선택해주세요.").css("color", "red");}
			else {$("#lblQuestion").html("");}
		});
		$("#answer").focusout(function() {
			var answer = $("#answer").val();
			if(answer == "") {$("#lblAnswer").html("답변을 입력해주세요.").css("color", "red");}
			else {$("#lblAnswer").html("");}
		});
	});
	
</script>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
<div align="center">
<h4>비밀번호 찾기</h4>
	<form action="pwdUpdate" method="post">
		<table>
			<tr>
				<td>아이디 입력</td>
				<td><input type="text" name="id" id="id"></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><label id="lblId"></label></td>
			</tr>
			<tr>
				<td>질문 선택</td>
				<td>
					<select name="question" id="question" style="width: 153px;">
						<option value="0">질문 선택</option>
						<option value="1">거주하는 지역</option>
						<option value="2">어머니의 성함</option>
						<option value="3">아버지의 성함</option>
						<option value="4">태어난 고향</option>
						<option value="5">당신이 좋아하는 책</option>
						<option value="6">당신의 좌우명</option>
						<option value="7">가장 좋아하는 과일</option>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><label id="lblQuestion"></label></td>
			</tr>
			<tr>
				<td>답변 입력</td>
				<td><input type="text" name="answer" id="answer"></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><label id="lblAnswer"></label></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="확인"></th>
			</tr>
		</table>
	</form>
</div>
</body>
</html>