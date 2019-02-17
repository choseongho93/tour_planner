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
	var nicknameChk = 1;
	var saveNickname = $("#nickname").val();
	$("form").submit(function() {
		if(nicknameChk == 1) {
			true;
		}
		else {
			false;
		}
	});
	
	$("#userDelete").click(function () {
		var result = confirm("정말 계정을 삭제하시겠습니까?");
		if(result){
			location.href="userDelete";
		}else{
			return;
		};
	
	});
	
	
	$("#nickname").focusout(function() { //빈 칸 체크
		var nickname = $("#nickname").val();
		if(saveNickname == nickname) {
			nicknameChk = 1
			$("#lblNickname").html("");
		}
		else {
			if(nickname != "") {
				$.ajax({
					async : true,
					type : 'POST',
					data : nickname,
					url : "${pageContext.request.contextPath}/nicknameCheck",
					dataType : "json",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						if(data.cnt > 0) {
							nicknameChk = 0;
							$("#lblNickname").html("이미 존재하는 닉네임입니다.").css("color", "red");
						}
						else {
							nicknameChk = 1;
							$("#lblNickname").html("사용 가능한 닉네임입니다.").css("color", "green");
						}
					},
					error : function(error) {
						alert("전송 실패");
					}
				});
			}
			else {$("#lblNickname").html("닉네임을 입력해주세요.").css("color", "red");}
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
            <li><a href="boardCheck?boardNum=1">Tip</a></li>
            <li><a href="boardCheck?boardNum=2">건의</a></li>
            <li><a href="boardCheck?boardNum=3">Q&amp;A</a></li>
          </ul>
        </li>          
        <li ><a href="userInfo">내정보 변경<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-th-list"></span></a></li>        
        <li ><a href="userPwdChange">비밀번호 변경<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-tags"></span></a></li>
      </ul>
    </div>
  </div>
</nav>
	
	<div align="center" style="height:600px; ">
		<form action="userInfoChangeCheck" method="post">
			<table>
				<tr><td>아이디</td><td><label>${dto.id}</label></td></tr>
				<tr><td>이름</td><td><label>${dto.name}</label></td></tr>
			<c:choose>
				<c:when test="${dto.gender == 1}">
					<tr><td>성별</td><td><label>남자</label></td></tr>
				</c:when>
				<c:otherwise>
					<tr><td>성별</td><td><label>여자</label></td></tr>
				</c:otherwise>
			</c:choose>
				<tr><td>E-mail</td><td><input type="text" name="email" value="${dto.email}"></td></tr>
				<tr><td>생년월일</td><td><label>${dto.birth}</label></td></tr>
				<tr><td>나이</td><td><label>${dto.age}</label></td></tr>
				<tr><td>닉네임</td><td><input type="text" name="nickname" id="nickname" value="${dto.nickname}"></td></tr>
				<tr><td colspan="2"><label id="lblNickname"></label></td></tr>
				<tr><td colspan="2"><input type="submit" value="회원 수정" style="margin-right: 10px;"><input type="button" id="userDelete" value="회원 삭제" ></td></tr>
			</table>
		</form>
	</div>
	<c:import url="/default/mainBottom"/>
</body>
</html>