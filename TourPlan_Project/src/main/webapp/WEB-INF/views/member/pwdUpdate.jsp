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
	$(document).ready(function() {
		$("form").submit(function() {
			if(pwd != "" && pwdChk != "") {
				if(pwd == pwdChk){
					return true;
				}
				else {
					$("#lblPwdChk").html("비밀번호를 다시 확인해주세요.").css("color", "red");
					return false;
				}
			}
			else {
				if(pwd == "") {$("#lblPwd").html("비밀번호를 입력해주세요.").css("color", "red");}
				if(pwdChk == "") {$("#lblPwdChk").html("비밀번호 확인을 입력해주세요.").css("color", "red");}
			}
		});
		
		$("#pwd").focusout(function() {
			var pwd = $("#pwd").val();
			if(pwd != "") {$("#lblPwd").html("");}
			else {$("#lblPwd").html("비밀번호를 입력해주세요.").css("color", "red");}
		});
		$("#pwdChk").focusout(function() {
			var pwd = $("#pwd").val();
			var pwdChk = $("#pwdChk").val();
			if(pwdChk != "") {$("#lblPwdChk").html("");}
			else {$("#lblPwdChk").html("비밀번호 확인을 입력해주세요.").css("color", "red");}
			if(pwd != "" && pwdChk != "") {
				if(pwd == pwdChk) {
					$("#lblPwdChk").html("비밀번호가 일치합니다.").css("color", "green");
				}
				else {
					$("#lblPwdChk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
					$("#pwdChk").val("");
				}
			}
		});
	});
</script>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<div align="center">
		<h4>비밀번호 변경</h4>
		<form action="pwdUpdateSuccess" method="post">
			<input type="hidden" name="id" value="${dto_id}">
			<table class="align">
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pwd" id="pwd" maxlength="10"></td>
				</tr>
				<tr>
					<td colspan="2"><label id="lblPwd"></label></td>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="pwdChk" id="pwdChk" maxlength="10"></td>
				</tr>
				<tr>
					<td colspan="2"><label id="lblPwdChk"></label></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="변경"></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>