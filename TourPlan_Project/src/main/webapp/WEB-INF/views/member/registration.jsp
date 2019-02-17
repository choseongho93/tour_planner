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
<style type="text/css">
@import
	url(http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700);

@import
	url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700);

h1, h2, h3, h4, h5, h6 {
	font-family: 'Roboto Condensed', sans-serif;
	font-weight: 400;
	color: #111;
	margin-top: 5px;
	margin-bottom: 5px;
}

h1, h2, h3 {
	text-transform: uppercase;
}

input.upload {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 12px;
	cursor: pointer;
	opacity: 1;
	filter: alpha(opacity = 1);
}

.form-inline .form-group {
	margin-left: 0;
	margin-right: 0;
}

.control-label {
	color: #333333;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var idChk = 0;
		var nicknameChk = 0;
		
		$("form").submit(function() {//데이터 전송시 빈 칸 체크
			var id = $("#id").val();
			var pwd = $("#pwd").val();
			var pwdChk = $("#pwdChk").val();
			var name = $("#name").val();
			var gender = $("#gender").val();
			var birthYear = $("#birthYear").val();
			var birthMonth = $("#birthMonth").val();
			var birthDay = $("#birthDay").val();
			var age = $("#age").val();
			var email = $("#email").val();
			var nickname = $("#nickname").val();
			var question = $("#question").val();
			var answer = $("#answer").val();
			if(id != "" && pwd != "" && pwdChk != "" && name != "" && gender != 0 && birthYear != "" && birthMonth != 0 && birthDay != "" && age != "" && email != "" && nickname != "" && question != 0 && answer != "") {
				if(pwd == pwdChk && idChk == 1 && nicknameChk == 1){
					return true;
				}
				else if(idChk == 0) {
					$("#lblId").html("아이디를 다시 확인해주세요.").css("color", "red");
					return false;
				}
				else if(nicknameChk == 0) {
					$("#lblNickname").html("닉네임을 다시 확인해주세요.").css("color", "red");
					return false;
				}
				else {
					$("#lblPwdChk").html("비밀번호를 다시 확인해주세요.").css("color", "red");
					return false;
				}
			}
			else {
				if(id == "") {$("#lblId").html("아이디를 입력해주세요.").css("color", "red");}
				if(pwd == "") {$("#lblPwd").html("비밀번호를 입력해주세요.").css("color", "red");}
				if(pwdChk == "") {$("#lblPwdChk").html("비밀번호 확인을 입력해주세요.").css("color", "red");}
				else if(pwd != pwdChk) {$("#lblPwdChk").html("비밀번호를 다시 확인해주세요.").css("color", "red");}
				if(name == "") {$("#lblName").html("아름을 입력해주세요.").css("color", "red");}
				if(gender == 0) {$("#lblGender").html("성별을 선택해주세요").css("color", "red");}
				if(birthYear == "") {$("#lblBirth").html("년 4자리를 입력해주세요.").css("color", "red");}
				else if(birthMonth == 0) {$("#lblBirth").html("월을 선택해주세요.").css("color", "red");}
				else if(birthDay == "") {$("#lblBirth").html("일을 입력해주세요.").css("color", "red");}
				if(age == "") {$("#lblAge").html("나이를 입력해주세요.").css("color", "red");}
				if(email == "") {$("#lblEmail").html("E-mail을 입력해주세요.").css("color", "red");}
				if(nickname == "") {$("#lblNickname").html("닉네임을 입력해주세요.").css("color", "red");}
				if(question == 0) {$("#lblQuestion").html("질문을 선택해주세요.").css("color", "red");}
				if(answer == "") {$("#lblAnswer").html("답변을 입력해주세요.").css("color", "red");}
				return false;
			}
		});
		
		$("#id").focusout(function() {//빈 칸 체크
			var id = $("#id").val();
			if(id != "") {
				$.ajax({
					async : true,
					type : 'POST',
					data : id,
					url : "${pageContext.request.contextPath}/idCheck",
					dataType : "json",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						if(data.cnt > 0) {
							$("#lblId").html("이미 존재하는 아이디입니다.").css("color", "red");
						}
						else {
							idChk = 1;
							$("#lblId").html("사용 가능한 아이디입니다.").css("color", "green");
						}
					},
					error : function(error) {
						alert("전송 실패");
					}
				});
			}
			else {
				$("#lblId").html("아이디를 입력해주세요.").css("color", "red");
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
		$("#name").focusout(function() {
			var name = $("#name").val();
			if(name != "") {$("#lblName").html("");}
			else {$("#lblName").html("이름을 입력해주세요.").css("color", "red");}
		});
		$("#gender").change(function() {
			var gender = $("#gender").val();
			if(gender != 0) {$("#lblGender").html("");}
			else {$("#lblGender").html("성별을 선택해주세요.").css("color", "red");}
		});
		$("#birthYear").change(function() {
			var birthYear = $("#birthYear").val();
			if(birthYear != 0) {$("#lblBirth").html("");}
			else {$("#lblBirth").html("년도를 선택해주세요.").css("color", "red");}
		});
		$("#birthMonth").change(function() {
			var birthMonth = $("#birthMonth").val();
			if(birthMonth != 0) {$("#lblBirth").html("");}
			else {$("#lblBirth").html("월을 선택해주세요.").css("color", "red");}
		});
		$("#birthDay").change(function() {
			var birthDay = $("#birthDay").val();
			if(birthDay != 0) {$("#lblBirth").html("");}
			else {$("#lblBirth").html("일을 선택해주세요.").css("color", "red");}
		});
		$("#age").focusout(function() {
			var age = $("#age").val();
			if(age != "") {$("#lblAge").html("");}
			else {$("#lblAge").html("나이를 입력해주세요.").css("color", "red");}
		});
		$("#email").focusout(function() {
			var email = $("#email").val();
			if(email != "") {$("#lblEmail").html("");}
			else {$("#lblEmail").html("E-mail을 입력해주세요.").css("color", "red");}
		});
		$("#nickname").focusout(function() { //빈 칸 체크
			var nickname = $("#nickname").val();
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
		});
		$("#question").change(function() {
			var question = $("#question").val();
			if(question != 0) {$("#lblQuestion").html("");}
			else {$("#lblQuestion").html("질문을 선택해주세요.").css("color", "red");}
		});
		$("#answer").focusout(function() {
			var answer = $("#answer").val();
			if(answer != "") {$("#lblAnswer").html("");}
			else {$("#lblAnswer").html("답변을 입력해주세요.").css("color", "red");}
		});
	});
</script>
<style type="text/css">
	.align tr td {
		text-align: center;
		color: red;
	}
</style>
</head>
<body>
	<fmt:requestEncoding value="utf-8" />
	<c:import url="/default/mainHeader"/>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="entry-title">
					<span>회원가입</span>
				</h1>
				<hr>
				<form class="form-horizontal" action="registrationCheck"
					method="POST">

					<div class="form-group">
						<label class="control-label col-sm-3">아이디 <span
							class="text-danger">*</span></label>
						<div class="col-md-5 col-sm-8">
							<input type="text" class="form-control" name="id" id="id"
								maxlength="10" placeholder="아이디 입력">
						</div>
						<label class="control-label col-sm-8" id="lblId"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">비밀번호 <span
							class="text-danger">*</span></label>
						<div class="col-md-5 col-sm-8">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input type="password"
									class="form-control" name="pwd" id="pwd" maxlength="10"
									placeholder="비밀번호 입력">
							</div>
						</div>
						<label class="control-label col-sm-8" id="lblPwd"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">비밀번호 재확인 <span
							class="text-danger">*</span></label>
						<div class="col-md-5 col-sm-8">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input type="password"
									class="form-control" name="pwdChk" id="pwdChk" maxlength="10"
									placeholder="비밀번호 재확인 입력">
							</div>
						</div>
						<label class="control-label col-sm-8" id="lblPwdChk"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">이름 : <span
							class="text-danger">*</span></label>
						<div class="col-md-5 col-sm-8">
							<input type="text" class="form-control" name="name" id="name"
								maxlength="10" placeholder="이름 입력">
						</div>
						<label class="control-label col-sm-8" id="lblName"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">생년월일 <span
							class="text-danger">*</span></label>
						<div class="col-xs-8">
							<div class="form-inline">
								<div class="form-group">
									<div class="col-md-8 col-sm-10">
										<select name="birthDay" id="birthDay">
											<option value="0">일</option>
											<option value="1">1일</option>
											<option value="2">2일</option>
											<option value="3">3일</option>
											<option value="4">4일</option>
											<option value="5">5일</option>
											<option value="6">6일</option>
											<option value="7">7일</option>
											<option value="8">8일</option>
											<option value="9">9일</option>
											<option value="10">10일</option>
											<option value="11">11일</option>
											<option value="12">12일</option>
											<option value="13">13일</option>
											<option value="14">14일</option>
											<option value="15">15일</option>
											<option value="16">16일</option>
											<option value="17">17일</option>
											<option value="18">18일</option>
											<option value="19">19일</option>
											<option value="20">20일</option>
											<option value="21">21일</option>
											<option value="22">22일</option>
											<option value="23">23일</option>
											<option value="24">24일</option>
											<option value="25">25일</option>
											<option value="26">26일</option>
											<option value="27">27일</option>
											<option value="28">28일</option>
											<option value="29">29일</option>
											<option value="30">30일</option>
											<option value="31">31일</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-8 col-sm-10">
										<select name="birthMonth" id="birthMonth">
											<option value="0">월</option>
											<option value="1">1월</option>
											<option value="2">2월</option>
											<option value="3">3월</option>
											<option value="4">4월</option>
											<option value="5">5월</option>
											<option value="6">6월</option>
											<option value="7">7월</option>
											<option value="8">8월</option>
											<option value="9">9월</option>
											<option value="10">10월</option>
											<option value="11">11월</option>
											<option value="12">12월</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-8 col-sm-10">
										<select name="birthYear" id="birthYear" style="width: 100px;">
											<option value="0">년도</option>
											<option value="1955">1955년</option>
											<option value="1956">1956년</option>
											<option value="1957">1957년</option>
											<option value="1958">1958년</option>
											<option value="1959">1959년</option>
											<option value="1960">1960년</option>
											<option value="1961">1961년</option>
											<option value="1962">1962년</option>
											<option value="1963">1963년</option>
											<option value="1964">1964년</option>
											<option value="1965">1965년</option>
											<option value="1966">1966년</option>
											<option value="1967">1967년</option>
											<option value="1968">1968년</option>
											<option value="1969">1969년</option>
											<option value="1970">1970년</option>
											<option value="1971">1971년</option>
											<option value="1972">1972년</option>
											<option value="1973">1973년</option>
											<option value="1974">1974년</option>
											<option value="1975">1975년</option>
											<option value="1976">1976년</option>
											<option value="1977">1977년</option>
											<option value="1978">1978년</option>
											<option value="1979">1979년</option>
											<option value="1980">1980년</option>
											<option value="1981">1981년</option>
											<option value="1982">1982년</option>
											<option value="1983">1983년</option>
											<option value="1984">1984년</option>
											<option value="1985">1985년</option>
											<option value="1986">1986년</option>
											<option value="1987">1987년</option>
											<option value="1988">1988년</option>
											<option value="1989">1989년</option>
											<option value="1990">1990년</option>
											<option value="1991">1991년</option>
											<option value="1992">1992년</option>
											<option value="1993">1993년</option>
											<option value="1994">1994년</option>
											<option value="1995">1995년</option>
											<option value="1996">1996년</option>
											<option value="1997">1997년</option>
											<option value="1998">1998년</option>
											<option value="1999">1999년</option>
											<option value="2000">2000년</option>
											<option value="2001">2001년</option>
											<option value="2002">2002년</option>
											<option value="2003">2003년</option>
											<option value="2004">2004년</option>
											<option value="2005">2005년</option>
											<option value="2006">2006년</option>
											<option value="2007">2007년</option>
											<option value="2008">2008년</option>
											<option value="2009">2009년</option>
											<option value="2010">2010년</option>
											<option value="2011">2011년</option>
											<option value="2012">2012년</option>
											<option value="2013">2013년</option>
											<option value="2014">2014년</option>
											<option value="2015">2015년</option>
											<option value="2016">2016년</option>
											<option value="2017">2017년</option>
											<option value="2018">2018년</option>
											<option value="2019">2019년</option>
											<option value="2020">2020년</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-sm-5" id="lblBirth"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">성별 <span
							class="text-danger">*</span></label>
						<div class="col-md-8 col-sm-10">
							<select class="custom-select mr-sm-2" name="gender" id="gender">
								<option value="0" selected>선택</option>
								<option value="1">남자</option>
								<option value="2">여자</option>
							</select>
						</div>
						<label class="control-label col-sm-5" id="lblGender"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">나이 <span
							class="text-danger">*</span></label>
						<div class="col-md-5 col-sm-8">
							<div class="input-group">
								<input type="number" name="age" id="age" min="1" max="100"
									maxlength="4" class="form-control" placeholder="나이 입력">
							</div>
						</div>
						<label class="control-label col-sm-8" id="lblAge"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">E-Mail </label>
						<div class="col-md-5 col-sm-8">
							<input type="text" class="form-control" name="email" id="email"
								maxlength="30" placeholder="E-Mail 입력">
						</div>
						<label class="control-label col-sm-8" id="lblEmail"></label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3">닉네임 </label>
						<div class="col-md-5 col-sm-8">
							<input type="text" class="form-control" name="nickname"
								id="nickname" maxlength="10"
								placeholder="(영문자:최대 10글자 /한글:최대 5글자) ">
						</div>
						<label class="control-label col-sm-8" id="lblNickname"></label>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-3">질문 <span
							class="text-danger">*</span></label>
						<div class="col-md-8 col-sm-10">
							<select name="question" id="question" style="width: 153px;">
							<option value="0">질문 선택</option>
							<option value="1">거주하는 지역</option>
							<option value="2">어머니의 성함</option>
							<option value="3">아버지의 성함</option>
							<option value="4">태어난 고향</option>
							<option value="5">당신이 좋아하는 책</option>
							<option value="6">당신의 좌우명</option>
							<option value="7">가장 좋아하는 과일</option>
						</select>
						</div>
						<label class="control-label col-sm-5" id="lblQuestion"></label>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-3">답변 <span
							class="text-danger">*</span></label>
						<div class="col-md-5 col-sm-8">
								<input type="text" name="answer" id="answer" maxlength="20"
								class="form-control" placeholder="(영문자:최대 20글자 /한글:최대 10글자)">
						</div>
						<label class="control-label col-sm-8" id="lblAnswer"></label>
					</div>

					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-10">
							<input name="Submit" type="submit" value="가입"
								class="btn btn-primary"> <input type="button"
								value="가입 취소" onclick="location.href='${pageContext.request.contextPath}/member/main';"
								class="btn btn-primary">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<c:import url="/default/mainBottom" />
</body>
</html>