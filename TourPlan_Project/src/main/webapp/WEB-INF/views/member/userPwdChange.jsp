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
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function() {
			var pwd = $("#pwd").val();
			var pwdChange = $("#pwdChange").val();
			var pwdChangeChk = $("#pwdChangeChk").val();
			if (pwd != "" && pwdChange != "" && pwdChangeChk != "") {
				if (pwdChange == pwdChangeChk) {return true;}
				else {
					$("#lblPwdChangeChk").html("변경할 비밀번호를 다시 확인해주세요.").css("color", "red");
					return false;
				}
			}
			else {
				if (pwd == "") {$("#lblPwd").html("비밀번호를 입력해주세요.").css("color", "red");}
				if (pwdChange == "") {$("#lblPwdChange").html("변경할 비밀번호를 입력해주세요.").css("color", "red");}
				else if (pwdChangeChk == "") {$("#lblPwdChangeChk").html("변경할 비밀번호 확인을 입력해주세요.").css("color", "red");}
				return false;
			}
		});

		$("#pwd").focusout(function() {
			var pwd = $("#pwd").val();
			if (pwd == "") {$("#lblPwd").html("비밀번호를 입력해주세요.").css("color", "red");}
			else {$("#lblPwd").html("");}
		});
		$("#pwdChange").focusout(function() {
			var pwdChange = $("#pwdChange").val();
			if (pwdChange == "") {$("#lblPwdChangeChk").html("변경할 비밀번호를 입력해주세요.").css("color", "red");}
			else {$("#lblPwdChangeChk").html("");}
		});

		$("#pwdChangeChk").focusout(function() {//비밀번호 확인 체크
			var pwdChange = $("#pwdChange").val();
			var pwdChangeChk = $("#pwdChangeChk").val();
			if (pwdChange != "" || pwdChangeChk != "") {
				if (pwdChange == pwdChangeChk) {$("#lblPwdChangeChk").html("비밀번호가 일치합니다.").css("color", "green");}
				else {
					$("#lblPwdChangeChk").html("비밀번호가 일치하지 않습니다.").css("color", "red");
					$("#pwdChangeChk").val("");
				}
			}
		});
	});
</script>
<style type="text/css">

nav.sidebar, .main {
	-webkit-transition: margin 200ms ease-out;
	-moz-transition: margin 200ms ease-out;
	-o-transition: margin 200ms ease-out;
	transition: margin 200ms ease-out;
}

.main {
	padding: 10px 10px 0 10px;
}

@media ( min-width : 765px) {
	.main {
		position: absolute;
		width: calc(100% - 40px);
		margin-left: 40px;
		float: right;
	}
	nav.sidebar:hover+.main {
		margin-left: 200px;
	}
	nav.sidebar.navbar.sidebar>.container .navbar-brand, .navbar>.container-fluid .navbar-brand
		{
		margin-left: 0px;
	}
	nav.sidebar .navbar-brand, nav.sidebar .navbar-header {
		text-align: center;
		width: 100%;
		margin-left: 0px;
	}
	nav.sidebar a {
		padding-right: 13px;
	}
	nav.sidebar .navbar-nav>li:first-child {
		border-top: 1px #e5e5e5 solid;
	}
	nav.sidebar .navbar-nav>li {
		border-bottom: 1px #e5e5e5 solid;
	}
	nav.sidebar .navbar-nav .open .dropdown-menu {
		position: static;
		float: none;
		width: auto;
		margin-top: 0;
		background-color: transparent;
		border: 0;
		-webkit-box-shadow: none;
		box-shadow: none;
	}
	nav.sidebar .navbar-collapse, nav.sidebar .container-fluid {
		padding: 0 0px 0 0px;
	}
	.navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
		color: #777;
	}
	nav.sidebar {
		width: 200px;
		height: 100%;
		margin-left: -160px;
		float: left;
		margin-bottom: 0px;
	}
	nav.sidebar li {
		width: 100%;
	}
	nav.sidebar:hover {
		margin-left: 0px;
	}
	.forAnimate {
		opacity: 0;
	}
}

@media ( min-width : 1330px) {
	.main {
		width: calc(100% - 200px);
		margin-left: 200px;
	}
	nav.sidebar {
		margin-left: 0px;
		float: left;
	}
	nav.sidebar .forAnimate {
		opacity: 1;
	}
}

nav.sidebar .navbar-nav .open .dropdown-menu>li>a:hover, nav.sidebar .navbar-nav .open .dropdown-menu>li>a:focus
	{
	color: #CCC;
	background-color: transparent;
}

nav:hover .forAnimate {
	opacity: 1;
}

section {
	padding-left: 15px;
}
</style>
</head>
<body>
	<fmt:requestEncoding value="utf-8" />
	<c:choose>
		<c:when test="${loginId == null}">
			<script type="text/javascript">
				alert("로그인 후 이용해주세요.");
				location.href='${pageContext.request.contextPath}/member/login';
			</script>
		</c:when>
		<c:otherwise>
			<c:import url="/default/mainHeader"/>
			
				<nav class="navbar navbar-default sidebar" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>      
    </div>
    <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="main">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">게시판<span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a>
          <ul class="dropdown-menu forAnimate" role="menu">
            <li><a href="boardCheck?boardNum=1">1</a></li>
            <li><a href="boardCheck?boardNum=2">2</a></li>
            <li><a href="boardCheck?boardNum=3">3</a></li>
            <li><a href="boardCheck?boardNum=4">4</a></li>
            <li><a href="boardCheck?boardNum=5">5</a></li>
          </ul>
        </li>          
        <li ><a href="userInfo">내정보 변경<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-th-list"></span></a></li>        
        <li ><a href="userPwdChange">비밀번호 변경<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-tags"></span></a></li>
      </ul>
    </div>
  </div>
</nav>
			
			<div align="center" style="height: 600px;">
				<h2>비밀번호 변경</h2>
				<form action="userPwdChangeCheck" method="post">
					<table>
						<tr>
							<th>현재 비밀번호</th>
							<td><input type="password" name="pwd" id="pwd"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><label id="lblPwd"></label></td>
						</tr>
						<tr>
							<th>비밀번호 변경</th>
							<td><input type="password" name="pwdChange" id="pwdChange"></td>
						</tr>
						<tr>
							<th>비밀번호 변경 확인</th>
							<td><input type="password" name="pwdChangeChk" id="pwdChangeChk"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><label id="lblPwdChangeChk"></label></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="비밀번호 변경"></td>
						</tr>
					</table>
				</form>
			</div>
			<c:import url="/default/mainBottom"/>
		</c:otherwise>
	</c:choose>
</body>
</html>