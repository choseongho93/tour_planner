<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/codingBooster.css" rel="stylesheet">
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<footer style="background-color: #0064CD; color: #FFFFFF">
		<div class="container">
			<br>
			<div class="row">
				<div class="col-sm-2" style="text-align: center;">
					<h5>Copyright &copy; 2019</h5>
					<h5>JJa Ja(짜자)	 Developer Team</h5>
				</div>
				<div class="col-sm-4">
					<h4>대표자 소개</h4>
					<p>"짜자"의 대표 OOO입니다.</p>
				</div>
				<div class="col-sm-2">
					<h4 style="text-align: center;">메뉴</h4>
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/plan/plan" class="list-group-item">여행일정</a>
						<a href="${pageContext.request.contextPath}/home"	class="list-group-item">게시판</a>
						<a href="${pageContext.request.contextPath}/member/userInfo" class="list-group-item">내정보</a>
					</div>
				</div>
				<div class="col-sm-2">
					<h4 style="text-align: center;">SNS</h4>
					<div class="list-group">
						<a href="https://www.facebook.com/" class="list-group-item">페이스북</a>
						<a href="https://www.youtube.com/" class="list-group-item">유튜브</a>
						<a href="https://www.naver.com/" class="list-group-item">네이버</a>
					</div>
				</div>
				<div class="col-sm-2">
					<h4 style="text-align: center;">
						<span class="glyphicon glyphicon-ok"></span>&nbsp;by JJa Ja 개발팀
					</h4>
				</div>
			</div>
		</div>
	</footer>
	<script
		src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>