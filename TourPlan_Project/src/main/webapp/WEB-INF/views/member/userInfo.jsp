<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/codingBooster.css" rel="stylesheet">
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</head>
<body>
	<fmt:requestEncoding value="utf-8" />
	<c:import url="/default/mainHeader"></c:import>
	<div align="center" style="height: 600px;">
		<h1>회원 정보 페이지</h1>
		<form action="userInfoChange" method="post">
			비밀번호 <input type="password" name="pwd" maxlength="10">
			<input type="submit" value="확인" style="width: 120; height: 40">
		</form>
	</div>
	<c:import url="/default/mainBottom"/>
</body>
</html>