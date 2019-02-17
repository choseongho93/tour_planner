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

	<c:import url="/default/mainHeader"/>

	<div class="container">
		<div class="row">
			<ul class="list-group">
				<a href="#"	class="list-group-item active">1. 미국여행준비전 영상</a>
				<a href="#"	class="list-group-item">2. 캐나다여행준비전 영상</a>
				<a href="#"	class="list-group-item">3. 유럽여행준비전 영상</a>
				<a href="#"	class="list-group-item">4. 브라질여행준비전 영상</a>
				<a href="#"	class="list-group-item">5. 일본여행준비전 영상</a>
			</ul>
		</div>
		<div class="row">
			<div class="embed-responsive embed-responsive-16by9">
				<iframe class="embed-responsive-item" src="https://www.youtube.com/embed/GhV97SUs44c"></iframe>
			</div> 
		</div>
	</div>
	<div class="container">
		<hr>
			<form class="form-horizontal">
				<div class="form-group">
					<label>댓글: </label>
					<textarea class="form-control" rows="5" id="commentContent" name="commentContext"></textarea>
					<br>
					<button type="submit" class="btn pull-right">등록</button>
				</div>
			</form>
		<hr>
	</div>

	<c:import url="/default/mainBottom"/>


	<script
		src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>