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
			var name=$("#name").val();
			var birth=$("#birth").val();
			if(name != "" && birth != "") {return true;}
			else {
				if(name == "") {$("#lblName").html("이름을 입력해주세요.").css("color", "red");}
				if(birth == "") {$("#lblBirth").html("생년월일을 입력해주세요.").css("color", "red");}
				return false;
			}
		});
		$("#name").focusout(function() {
			var name = $("#name").val();
			if(name == "") {$("#lblName").html("이름을 입력해주세요.").css("color", "red");}
			else {$("#lblName").html("");}
		});
		$("#birth").focusout(function() {
			var birth = $("#birth").val();
			if(birth == "") {$("#lblBirth").html("생년월일을 입력해주세요.").css("color", "red");}
			else {$("#lblBirth").html("");}
		});
	});
</script>
</head>
<body>
	<fmt:requestEncoding value="utf-8" />
	<div align="center">
		<h4>아이디 찾기</h4>
		<form action="idInquirySuccess" method="post">
			<table>
				<tr>
					<td>이름 입력</td>
					<td><input type="text" name="name" id="name" maxlength="5" placeholder="ex)홍길동"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><label id="lblName"></label></td>
				</tr>
				<tr>
					<td>생년월일 입력</td>
					<td><input type="text" name="birth" id="birth" maxlength="8" placeholder="ex) yyyymmdd"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><label id="lblBirth"></label></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="확인"></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>