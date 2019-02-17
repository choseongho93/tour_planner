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
<title>JJa Ja</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/codingBooster.css" rel="stylesheet">
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JJa Ja</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<li><a href="main">소개<span class="sr-only"></span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="ture"
						aria-expanded="false">게시판<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">tip</a></li>
							<li><a href="#">자유</a></li>
							<li><a href="#">몰라</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">여행계획<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">계획 보기</a></li>
							<li><a href="#">계획 짜기</a></li>
						</ul></li>
					<li><a href="#">여행준비전영상</a></li>
					<li><a href="#">contact</a></li>
				</ul>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="내용을 입력하세요.">
					</div>
					<button type="submit" class="btn btn-default">검색</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="ture"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="login">로그인</a></li>
							<li><a href="registration">회원가입</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>




	<footer style="background-color: #0064CD; color: #FFFFFF">
		<div class="container">
			<br>
			<div class="row">
				<div class="col-sm-2" style="text-align: center;">
					<h5>Copyright &copy; 2019</h5>
					<h5>조성호(Developer)</h5>
				</div>
				<div class="col-sm-4">
					<h4>대표자 소개</h4>
					<p>저는 "짜자"의 대표 조성호입니다.</p>
				</div>
				<div class="col-sm-2">
					<h4 style="text-align: center;">메뉴</h4>
					<div class="list-group">
						<a href="#" class="list-group-item">여행일정</a> <a href="#"
							class="list-group-item">게시판</a> <a href="#"
							class="list-group-item">내정보</a>
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
						<span class="glyphicon glyphicon-ok"></span>&nbsp;by 조성호
					</h4>
				</div>
			</div>
		</div>
	</footer>


	<script
		src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>