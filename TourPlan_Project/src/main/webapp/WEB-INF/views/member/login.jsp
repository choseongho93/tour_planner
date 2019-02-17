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
<script src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script> 
<style type="text/css">
@media ( min-width : 768px) {
	.omb_row-sm-offset-3 div:first-child[class*="col-"] {
		margin-left: 25%;
	}
}

.omb_login .omb_authTitle {
	text-align: center;
	line-height: 300%;
}

.omb_login .omb_socialButtons a {
	color: white;
	//
	In
	yourUse
	@body-bg
	opacity
	:
	0.9;
}

.omb_login .omb_socialButtons a:hover {
	color: white;
	opacity: 1;
}

.omb_login .omb_socialButtons .omb_btn-facebook {
	background: #3b5998;
}

.omb_login .omb_socialButtons .omb_btn-twitter {
	background: #00aced;
}

.omb_login .omb_socialButtons .omb_btn-google {
	background: #c32f10;
}

.omb_login .omb_loginOr {
	position: relative;
	font-size: 1.5em;
	color: #aaa;
	margin-top: 1em;
	margin-bottom: 1em;
	padding-top: 0.5em;
	padding-bottom: 0.5em;
}

.omb_login .omb_loginOr .omb_hrOr {
	background-color: #cdcdcd;
	height: 1px;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
}

.omb_login .omb_loginOr .omb_spanOr {
	display: block;
	position: absolute;
	left: 50%;
	top: -0.6em;
	margin-left: -1.5em;
	background-color: white;
	width: 3em;
	text-align: center;
}

.omb_login .omb_loginForm .input-group.i {
	width: 2em;
}

.omb_login .omb_loginForm  .help-block {
	color: red;
}

@media ( min-width : 768px) {
	.omb_login .omb_forgotPwd {
		text-align: right;
		margin-top: 10px;
	}
}
</style>
<script type="text/javascript">
	$(function() {//ID저장 기능
		var userInputId = getCookie("userInputId");//저장된 쿠키값 가져오기
		$("input[name='id']").val(userInputId);
		if ($("input[name='id']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 아이디 저장하기 체크되어있을 시,
			$("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기
		}
		$("form").submit(function() { // 로그인을 눌렀을 때
			if ($("#idSaveCheck").is(":checked")) { // ID 저장하기가 체크 상태면,
				var userInputId = $("input[name='id']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			} else { // ID 저장하기가 해제 상태면 삭제,
				deleteCookie("userInputId");
			}
		});
	});
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}
	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}
	function getCookie(cookieName) {
		cookieName = cookieName + "="; 
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = "";
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(";", start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>

</head>
<body>
	<c:import url="/default/mainHeader" />


	<div class="container">


		<div class="omb_login">
			<h3 class="omb_authTitle">
				로그인 or<a href="registration"> 회원가입</a>
			</h3>
			<div class="row omb_row-sm-offset-3 omb_socialButtons">
				<div class="col-xs-4 col-sm-2">
					<a href="#" class="btn btn-lg btn-block omb_btn-facebook"> <i
						class="fa fa-facebook visible-xs"></i> <span class="hidden-xs">Facebook</span>
					</a>
				</div>
				<div class="col-xs-4 col-sm-2">
					<a href="#" class="btn btn-lg btn-block omb_btn-twitter"> <i
						class="fa fa-twitter visible-xs"></i> <span class="hidden-xs">Twitter</span>
					</a>
				</div>
				<div class="col-xs-4 col-sm-2">
					<a href="#" class="btn btn-lg btn-block omb_btn-google"> <i
						class="fa fa-google-plus visible-xs"></i> <span class="hidden-xs">Google+</span>
					</a>
				</div>
			</div>

			<div class="row omb_row-sm-offset-3 omb_loginOr">
				<div class="col-xs-12 col-sm-6">
					<hr class="omb_hrOr">
					<span class="omb_spanOr">or</span>
				</div>
			</div>
			<div class="row omb_row-sm-offset-3">
				<div class="col-xs-12 col-sm-6">
					<form class="omb_loginForm" action="loginCheck" autocomplete="off"
						method="POST">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input type="text" class="form-control" name="id" maxlength="10"
								placeholder="아이디">
						</div>
						<span class="help-block"></span>

						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" class="form-control" name="pwd"
								maxlength="10" placeholder="비밀번호">
						</div>

						<div class="input-group">
							<label class="checkbox"> <input type="checkbox"
								value="remember-me" id="idSaveCheck">아이디 저장하기
							</label>
						</div>
						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
					</form>
				</div>
			</div>

			<div class="col-xs-12 col-sm-9">
				<p class="omb_forgotPwd">
					<a href="idInquiry"
						onclick="window.open(this.href,'_blank','width=320,height=250, scrollbars=no');return false;">아이디를
						잊어버리셨나요?</a>
				</p>
				<p class="omb_forgotPwd">
					<a href="pwdInquiry"
						onclick="window.open(this.href,'_blank','width=320,height=270, scrollbars=no');return false;">비밀번호를
						잊어버리셨나요?</a>
				</p>
			</div>
		</div>
	</div>





	<c:import url="/default/mainBottom" />
	
</body>
</html>