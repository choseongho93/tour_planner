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
</head>
<body>
<script type="text/javascript">
$(function(){
    $(".dropdown").hover(            
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            },
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            });
    });
    
</script>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/member/main">JJa Ja</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/member/main">소개<span
							class="sr-only"></span></a></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">게시판<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/home">Main 게시판</a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/tip">Tip</a></li>
							<li><a href="${pageContext.request.contextPath}/QA_board">Q&amp;A</a></li>
							<li><a href="${pageContext.request.contextPath}/Suggestion_board">건의</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">여행계획<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/plan/plan_board">계획 보기</a></li>
							<li><a href="${pageContext.request.contextPath}/plan/plan">계획 짜기</a></li>
							<li><a href="${pageContext.request.contextPath}/plan/mPlan">모바일로 짜기</a></li>
						</ul></li>
					<li><a href="${pageContext.request.contextPath}/member/tripPicture">여행준비전영상</a></li>
					<li><a href="${pageContext.request.contextPath}/member/contact">contact</a></li>
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
							<li><c:choose>
									<c:when test="${loginId == null}">
										<a href="${pageContext.request.contextPath}/member/login">로그인</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
									</c:otherwise>
								</c:choose></li>
							<li><c:choose>
									<c:when test="${loginId == null}">
										<a href="${pageContext.request.contextPath}/member/registration">회원가입</a>
									</c:when>
								</c:choose></li>
							<li><c:choose>
									<c:when test="${loginId != null}">
										<a href="${pageContext.request.contextPath}/member/userInfo">내정보</a>
									</c:when>
								</c:choose></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<script
		src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>