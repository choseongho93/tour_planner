<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="referrer" content="no-referrer" />
<!-- 세션 아이디 와 NickName 셋팅 -->
<c:set var="id" value="Test" scope="session"/>
<c:set var="nickname" value="Tnickname" scope="session"/> 
<!-- button 용 css/ bootstrap에서 가져옴 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/button.css">
<!-- table 용 css/ bootstrap에서 가져옴 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
<!-- navbar 용 css/ bootstrap에서 가져옴 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/navbar.css">
<style type="text/css">
html, body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
}
#map {
	position: relative;
	height: 100%;
	width: 100%;
}

#content_right {
	flex: 1;
	hegith: 91.7%;
	width: 40%;
}

#content_left {
	width: 60%;
	height: 100%;
}

#plan_content {
	background-color: white;
	flex: 1;
	width: 98.5%;
	height: 99.1%;
	color: black;
}

#floating-panel {
	position: absolute;
	top: 60px;
	z-index: 5;
	left: 35%;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
	font-family: 'Roboto', 'sans-serif';
	line-height: 30px;
	padding-left: 10px;
}

#drag-container {
	display: flex;
	height: 94%;
}

#dragbar {
	padding: 6px;
	cursor: col-resize;
	background-color: silver;
	height: 98.5%;
}

#infowindow-content {
	display: none;
}

#x_button {
	position: absolute;
	bottom: 30px;
	left: 20px;
	height: 80px;
	transform: rotate(45deg);
}

#marker_bar {
	display: none;
	position: absolute;
	bottom: 50px;
	left: 70px;
	background-color: white;
    word-spacing: 10px;
    padding: 5px;
}

#search_spot {
	display: none;
	position: absolute;
	bottom: 30px;
	left: 30px;
}

li {
	display: inline;
	border: 1px solid #bcbcbc;
	padding: 10px;
	top: 10px;
}

#menu {
	height: 7%;
	position: relative;
}

#direction_bar{
	position: absolute;
	background-color: white;
	display: none;
	top: 730px;
	left: 30px;	 
	width: 300px;
}
#direction_result{
	height: 470px;
	overflow: scroll;
}
#direction_table{
	position: relative;
	padding-top: 10px;
	left: 10px;	
}
#direction_start{
	margin-left: 7px;
	width: 200px;
}
#direction_end{
	margin-left: 7px;
	width: 200px;
}
#direction_mode{
    position: relative;
    letter-spacing: 30px;
    left: 60px;
    padding-top: 20px;
    padding-bottom: 0px;
    }
#direction_exit{
	float: right;
	padding-top: 5px;
	padding-right: 5px;
}


#menu li {
	display: inline;
	padding: 5px;
	top: 10px;
	position: relative;
	border: none;
}

#menu {
	background: #B2CCFF;
	height: 60px;
	line-height: 60px;
	font-size: 12pt;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	display: block;
}

#mainmenu, #search, #social {
	float: left;
}

ol, ul {
	list-style: none;
}

#mainmenu {
	display: inline-block;
	margin-left: 40px;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
}

#menuright {
	float: right;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	height: 60px;
}

#search, #soical {
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	padding-left: 12px;
	padding-right: 12px;
}

#soical {
	display: inline-block;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
}

#soical ul {
	margin: 0;
	padding: 0;
	border: 0;
	padding-left: 12px;
	padding-right: 12px;
}

#mainmenu button {
	background-color: inherit;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 10px 10px 10px 10px;
	transition: 0.3s;
	font-size: 17px;
	width: 40px;
	height: 60px;
}

#mainmenu button:hover {
	background-color: #343a40;
	color: white;
}

#plus{
	background-color: #138496;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 10px 10px 10px 10px;
	transition: 0.3s;
	font-size: 17px;
	width: 50px;
    height: 60px;
}
#minus{
	background-color: #dc3545;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 10px 10px 10px 10px;
	transition: 0.3s;
	font-size: 17px;
	width: 50px;
    height: 60px;
} 
</style>
<!-- Modal 용 -->
<style type="text/css">
#modal_naver{
	background-color: white;
	position: absolute;
	top: 120px;
	left: 100px;
	width: 700px;
	height: 73%;
	display: none;
}
.modal_naver_header{
	text-align: center;
}
#modal_naver_close{
	position: absolute;
	top: 10px;
	right: 10px;
}
#naver_search{
	background-color: white;
}
#modal_naver_search{
	text-align: center;
}
#modal_naver_list{
	overflow: auto;
	height: 620px;
	width: 100%;
}
#modal_naver_pages{
	text-align: center;
}

.thumb thumb-rollover a{
	float: left;
}
#modal_naver_list li{
	list-style-type: none;
}
#modal_naver_list .sp_thmb{
	float: left;
}
#modal_naver_list .dt{
    float: left;
    margin-left: 20px;
}
#modal_naver_list .sh_blog_passage{
    -webkit-margin-start: 60px;
    margin-left: 100px;
    }
#modal_naver_list .txt_block{
	text-align: right;
    }
#modal_naver_list dl{
    margin-top: 0px;
}
#modal_naver_list .sh_blog_title{
	float: left;
	margin-right: 15px;
	margin-left: 20px;
}
.sh_blog_top{
	border: none;
}
</style>
<!--Modal Blog -->
<style type="text/css">
#modal_blog{
	background-color: white;
	position: absolute;
	top: 120px;
	left: 100px;
	width: 700px;
	height: 73%;
	display: none;
}
.modal_blog_content{
	height: 100%;
}
#modal_blog_close{
	position: absolute;
	top: 10px;
	right: 10px;
}
#modal_blog_body{
	overflow: auto;
	height: 84%;
	width: 100%;
}
.se-image-resource{
	width: 300px;
	height: 200px;
}
.modal_blog_header{
	height: 12%;
	text-align: center;
	margin-top: 30px;
}
</style>
<!-- Modal Insta -->
<style type="text/css">
#modal_insta{
	background-color: white;
	position: absolute;
	top: 120px;
	left: 100px;
	width: 700px;
	height: 80%;
	display: none;
}
#modal_insta_header{
	text-align: center;
}
#modal_insta_close{
	position: absolute;
	top: 10px;
	right: 10px;
}
#modal_insta_search{
	text-align: center;
}
#insta_search{
	background-color: white;
}
#modal_insta_list{
	overflow: auto;
	margin-top: 20px;
	height: 626px;
	width: 100%;
	text-align: center;
}
</style>
<!-- Calender Div -->
<style type="text/css">
#div_cal{
	background-color: white;
	position: absolute;
	top: 120px;
	left: 25%;
	widht: 600px;
	height: 500px;
	display: none;
}
#div_cal_title h2 {
	text-align: center;
    margin-bottom: 0px;
}
#div_cal_button{
    text-align: center;
    word-spacing: 80px;
}
#cal {
	width: 500px;
	height: 400px;
	font-family: 'Nanum Gothic', serif;
	}
#cal #header {
	height: 50px;
	line-height: 50px;
	text-align: center;
	font-size: 18px;
	font-weight: bold;
	}
#cal .month_button{
	color: blue;
	text-decoration: none;
	padding: 10px;
}
#cal table {
	margin-top: 20px;
	width: 100%;
	height: 80%;
	}
#cal caption {
	display: none;
}

#cal tr{
	text-align: center;
	}
#cal td{
	cursor: pointer;
}
#cal .sun {
	color: deeppink;
	}
#cal .sat {
	color: deepskyblue;
	}
</style>

<!-- textarea & color변경-->
<style type="text/css">
#textColor_table {
	position: absolute;
	bottom: 2.8%;
	right: 14%;
	background-color: white;
	padding: 5px;
}

#textSave {
	position: absolute;
	height: 100px;
	width: 100px;
	right: 3%;
	bottom: 3%;
}

#set_color{
	margin-top: 7px;
}
#get_color{
	margin-top: 7px;
}
</style>

<!-- 상단 menu들 -->
<style type="text/css">
#menu li {
	display: inline;
	padding: 5px;
	top: 10px;
	position: relative;
}

#menu {
	background: #B2CCFF;
	height: 60px;
	line-height: 60px;
	font-size: 12pt;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	display: block;
}

#mainmenu, #search, #social {
	float: left;
}

#mainmenu ul {
	list-style: none;
}

#mainmenu {
	display: inline-block;
	margin-left: 40px;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
}

#menuright {
	float: right;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	height: 60px;
}

#search, #soical {
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	padding-left: 12px;
	padding-right: 12px;
}

#soical {
	display: inline-block;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
}

#soical ul {
	list-style: none;
	margin: 0;
	padding: 0;
	border: 0;
	padding-left: 12px;
	padding-right: 12px;
}
</style>

<!-- user클릭시 이벤트발생style -->
<style type="text/css">
#user{
		padding-top: 0px;
		padding-bottom: 0px;
		padding-left: 5px;
		padding-right: 5px;
		background-color: white;
	}
#userMenu {
	position: absolute;
	background-color: #f1f1f1;
	right: 0%;
	top: 6%;
	list-style-type: none;
	display: none;
	width: 80px;
	text-align: left;
	margin-left: auto;
	margin-right: auto;
	margin-top: 0px;
}

#userMenu a {
	color: black;
	text-decoration: none;
	display: block;
}
#userMenu li{
	border: none;
}
#mainmenu ul{
		display: table-row;
	}
#mainmenu li{
	display:table-cell;
	padding: 0px;
}
</style>

<!-- 경비계산 -->
<style type="text/css">
#expense {
	position: absolute;
	background-color: white;
	top: 25%;
	left: 25%;
	z-index: 5;
	width: 600px;
	height: 700px;
	display: none;
	margin-left: 30px;
	overflow: scroll;
	padding: 10px;
}

#expenseTable tr th {
	width: 172px;
}

#expenseTable tr td {
	width: 100px;
	text-align: center;
	padding: 0px;
	padding-top: 10px;
	padding-bottom: 10px;
}

#addBtn {
	top: 2%;
	right: 2%;
}

#expense_add{
	position: relative;
	left: 25%;
	padding-top: 5px;
	width: 50%;
}

#expense_save{
	position: relative;
	left: 40%;
	padding-top: 10px;
	word-spacing: 30px;
	width: 50%;
}
.expense_option{
	width: 53px;
}
</style>

</head>
<body>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script   src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAFwwRrTGwZCoP6_oDcdWi2t-Iwk6bi2dI&libraries=places&callback=initMap"
async defer></script>
	
	<nav id="menu">
		<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			<div class="btn-group mr-2" role="group" aria-label="First group" id="mainmenu">
				<!-- <button type="button" class="btn btn-outline-dark">1</button> -->
			</div>
			<div class="btn-group mr-2" role="group" aria-label="First group">
				<button id="plus" onclick="callAppendChild();" class="btn btn-info" style="background-color: #138496">+</button>
				<button id="minus" onclick="callRemoveChild();" class="btn btn-danger" style="background-color: #dc3545">-</button>
			</div>
		</div>

		<div id="menuright">
			<div id="search">
				<input type="text" id="modal_input">
			</div>
			<div id="soical">
				<ul>
					<li>
						<button type="button" id="naver_search">
							<img src="${pageContext.request.contextPath}/resources/image/menuIcon/search.png" height="30" width="30" />
						</button>
					</li>
					<li>
						<button type="button" id="insta_search">
							<img src="${pageContext.request.contextPath}/resources/image/menuIcon/instagram.png" height="30" width="30" />
						</button>
					</li>
					<li>
						<button type="button" id="user" class="btn-sm" data-toggle="dropdown" data-display="static" aria-haspopup="true" aria-expanded="false">
							<img src="${pageContext.request.contextPath}/resources/image/menuIcon/user.png" height="30" width="30" />
						</button>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<!-- drag-container -->
	<div id="drag-container">
		<div id="content_left">
			<div id="map"></div>
		</div>
		<div id="dragbar"></div>
		<div id="content_right">
			<textarea id="plan_content" name="content"></textarea>
			<table id="textColor_table">
				<tr>
					<td><label for="textcolor">글자색 변경 : </label></td>
					<td><input type="color" id="textcolor" /></td>
				</tr>
				<tr>
					<td><label for="bgcolor">배경색 변경 : </label></td>
					<td><input type="color" id="bgcolor" /></td>
				</tr>
				<tr>
					<td><button id='set_color' class="btn btn-sm btn-outline-dark">색상 저장</button></td>
					<td><button id='get_color' class="btn btn-sm btn-outline-dark">저장된 색상 가져오기</button></td>
				</tr>
			</table>
			<button id="textSave" class="btn btn-light">
				<img src="${pageContext.request.contextPath}/resources/image/save.png"/>
				<span style="font-size: 21px;">Save</span>
			</button>
		</div>
	</div>
	
	<div id="floating-panel">
		<input id="search_input" type="text"> <input
			id="search_submit" type="button" value="검색">
	</div>

	<div id="infowindow-content">
		<img id="place-icon" src="" height="16" width="16"> <span
			id="place-name" class="title"></span><br> Place ID <span
			id="place-id"></span><br> <span id="place-address"></span>
	</div>

	<img src="${pageContext.request.contextPath}/resources/image/x_button.png" id="x_button" />

	<div id="marker_bar">
		<button id="marker_add" class="btn btn-sm btn-outline-dark">마커 추가</button>
		<button id="maker_modify" class="btn btn-sm btn-outline-dark">순서 변경</button>
		<button id="marker_delete" class="btn btn-sm btn-outline-dark">마커 삭제</button>
		<button id="time_modify" class="btn btn-sm btn-outline-dark">시간 설정</button>
		<button id="add_my" class="btn btn-sm btn-outline-dark">담기</button>
	</div>
	
	<div id="direction_bar">
		<div id="direction_exit">
			<img src="${pageContext.request.contextPath}/resources/image/close.png">
		</div>
		<table id="direction_table">
			<tr>
				<td>시작</td>
				<td><input type="text" name="direction_start" id="direction_start"/></td>
			</tr>
			<tr>
				<td>도착</td>
				<td><input type="text" name="direction_end" id="direction_end"/></td>
			</tr>
		</table>
		<div id="direction_mode">
			<img src="${pageContext.request.contextPath}/resources/image/walking.png" id="walking"/>
			<img src="${pageContext.request.contextPath}/resources/image/driving.png" id="driving"/>
			<img src="${pageContext.request.contextPath}/resources/image/bus.png" id="biking"/>
		</div>
		<div id="direction_result">
			<p>Total Distance: <span id="total"></span></p>
		</div>
	</div>

 	<!-- Naver Search Modal div -->
	<div id="modal_naver">
    	<div class="modal_naver_content">
      		<div class="modal_naver_header">
        		<h2>네이버 검색  <img src="${pageContext.request.contextPath}/resources/image/close.png" id="modal_naver_close"/> </h2>
      		</div>
      		<div class="modal_naver_body">
      			<div id="modal_naver_search">
      				<input type="text" id="modal_naver_input"/>
      				<button id="modal_search_buton">검색</button>
      			</div>
      			<div id="modal_naver_list">
      			</div>
      			<div id="modal_naver_pages">
      			</div>
      		</div>
		</div>
	</div>
	
	<!-- Naver Blog Modal div -->
	<div id="modal_blog">
    	<div class="modal_blog_content">
      		<div class="modal_blog_header">
        		<h2 id="modal_blog_title"> </h2>
        		<img src="${pageContext.request.contextPath}/resources/image/close.png" id="modal_blog_close"/>
      		</div>
      		<div id="modal_blog_body">
      		</div>
		</div>
	</div>
	
	<!-- Instagram Modal div -->
	<div id="modal_insta">
    	<div id="modal_insta_content">
      		<div id="modal_insta_header">
        		<h2 id="modal_insta_title">인스타 검색  <img src="${pageContext.request.contextPath}/resources/image/close.png" id="modal_insta_close"/> </h2>
      		</div>
      		<div id="modal_insta_body">
      			<div id="modal_naver_search">
      				<input type="text" id="modal_insta_input"/>
      				<button id="modal_insta_buton">검색</button>
      			</div>
      			<div id="modal_insta_list">
      			</div>
      		</div>
		</div>
	</div>
	
	<!-- 달력 div -->
	<div id="div_cal" class="cal">
		<div id="div_cal_title" class="cal">
			<h2 class="cal">일정 선택</h2>
		</div>
		<div id="cal" class="cal"></div>
		<div id="div_cal_button" class="cal">
			<button type="button" id="div_cal_ok" class="cal btn btn-sm btn-outline-primary">확인</button>
			<button type="button" id="div_cal_reset" class="cal btn btn-sm btn-outline-secondary">초기화</button>
		</div>
	</div>
	<!-- 내정보 메뉴 bar -->
	<ul id="userMenu">
		<li><a href="${pageContext.request.contextPath}/member/userInfo">내정보</a></li>
		<li><a href="${pageContext.request.contextPath}/member/main">홈으로</a></li>
		<li><a href="#" id="calender">달력 편집</a></li>
		<li><a href="#" id="expenseCal">경비 계산</a></li>
	</ul>
	
	<!-- 경비계산 -->
	<div id="expense">
		<table id="expenseTable" class="table table-sm">
			<tr>
				<th>목록</th>
				<th>금액</th>
				<th>비고</th>
				<th style="width: 53px">옵션</th>
			</tr>
		</table>
		<div id="expense_add">
			<button id="addBtn" class="btn btn-sm btn-success">추가</button>
				합계 : <input type="text" id="totalCost" readonly />
		</div>
		<div id="expense_save">
			<button id="expenseSave" class="btn btn-sm btn-outline-primary">저장</button>
			<button id="expenseClose" class="btn btn-sm btn-outline-danger">닫기</button>
		</div>
	</div>
		
	<script>
		var map;
		var infowindow;
		var geocoder;
		var autocomplete;
		var marker;
		var marker_placeId = null;
		var clickHandler;
		
		var dto_marker = null;
		//x버튼이 눌렸는지 확인. 0이면 안눌림, 1이면 눌림.
		var isXButton =0;
		//장소를 저장 할 Object
		var routes = [];
		//장소 class 선언
		class route{
			/*
			this.marker;		//해당 장소의 마커
			this.day;		//해당 장소의 날짜
			this.num;		//해당 장소의 순서
			this.place;		//해당 장소의 이름
			*/
			constructor(marker, day, num, place){
				this.marker = marker;
				this.day = day;
				this.num = num;
				this.place = place;
			}
		}
		routes.push(new route(null, null, null, null));
		//경로 서비스 사용하기위한 객체 선언
		var directionsService;
        var directionsDisplay;
		//길찾기 경로의 선택된 모드. default는  걷기
		var selected_mode = 'WALKING';
			
		//dragBar 변수
		var left = document.getElementById('content_left');
		var right = document.getElementById('content_right');
		var bar = document.getElementById('dragbar');
		var floating = document.getElementById('floating-panel');
		
		//경로과 경로를 연결하기 위한 polyLine 객체 선언
		var poly;
		
		//시작 경로와 끝 경로 객체
		var place_start;
		var place_end;
		
		//체류 시간 infoWinodow 객체
		var duration_info;
		
		//drag함수 
		const drag = (e) => {
			  document.selection ? document.selection.empty() : window.getSelection().removeAllRanges();
			  left.style.width = (e.pageX - bar.offsetWidth / 2) + 'px';
			  floating.style.left =  (e.pageX - bar.offsetWidth / 2)-400 + 'px';
		};

		bar.addEventListener('mousedown', () => {
			  document.addEventListener('mousemove', drag);
		});

		bar.addEventListener('mouseup', () => {
			  document.removeEventListener('mousemove', drag);
		});
			
		//여행일정 변수 		
		var schedule=0;	//총일정 변수 선언
		var clickNum = 0;	//선택한 일정 변수
		var customer = new Map(); //key:n일차 / value:text값
		var selected_date ='1';
		
		function callAppendChild(data){
			console.log("여기2");
			if(schedule == 10){
				alert('10개 이상 생성할수 없습니다!');
				return;
			}
			schedule+=1;
			var mainmenu = document.getElementById('mainmenu');
			let button = '<button type="button" id="btn'+schedule+'" class="btn btn-outline-dark">'+schedule+'</button>'
			$('#mainmenu').append(button);
			if(data == null || data == undefined || data==''){
				addCalender();
				$('#btn'+schedule).click(function(){
					btnClickEvent(this);
				});
			}else{
				//addCalender();
				$('#btn'+schedule).click(function(){
					btnClickEvent(this,"out");
				});
			}
			//해당 날짜버튼 클릭시 실행이벤트
			
		};
		//일정 버튼 click Listener
		function btnClickEvent(data, out){
			//버튼 색 초기화 및 선택한 버튼 배경색 변화.
			duration_info.close();
			for(let i = 1;i<=schedule;i++){
				$('#btn'+i).css('color','#343a40');
				$('#btn'+i).css('background-color','inherit');	
			}
			console.log(data.id);
			$('#'+data.id).css('background-color','#343a40');
			$('#'+data.id).css('color','white');
			
			//선택된 날짜 저장.
			selected_date = data.id.substring(3)
			
			console.log(customer)
			var texts = customer.get(data.id.substring(3));
			$('#plan_content').val(texts);
			console.log("완료"+texts);
			//현재 선택된 날짜의 마커를 보여주는 함수
			
			if(out!="out"){
				console.log(poly);
				showMarker(data.id.substring(3), poly);
			}else{
				console.log(poly);
				showMarker(data.id.substring(3), poly);
			}
			
			//해당 날짜의 routes존재시 해당 위치로 맵 이동.
			for(let i=1;i<routes.length;i++){
				if(routes[i].day == selected_days[selected_date-1] && routes[i].num ==1){
					map.setCenter(routes[i].marker.getPosition());
					map.setZoom(18);
				}
			}
			//이전에 표시했던 경로 삭제
			directionsDisplay.setMap(null);
			place_start = '';
			place_end = '';
		}
		//여행 일정 삭제	버튼
		function callRemoveChild(){
			if(schedule==0){
				alert('일정이 존재하지 않습니다.');
				return;
			}
			schedule--;
		    var mainmenu = document.getElementById('mainmenu');
			mainmenu.removeChild(mainmenu.lastChild);
			delCalender();
		};
		//TextArea color색상변경
		//글자색 변경시 글상자의 글자색 적용
		$('#textcolor').change(function(){
			var color = $(this).val();
			$('#plan_content').css('color',color);
		});
		
		//배경색 변경시 글상자의 배경색에 적용
		$('#bgcolor').change(function(){
			var color = $(this).val();
			$('#plan_content').css('backgroundColor', color);
		});
		
		//글자색과 배경색을 로컬 스토리지에 저장
		$('#set_color').click(function(){
			var bgcolor = $('#bgcolor').val();
			var textcolor = $('textcolor').val();
			var obj = {
					bgcolor : bgcolor,
					textcolor : textcolor
			}
			//문자열 객체를 JSON형식으로 변환하여 보냄
			localStorage.setItem("color", JSON.stringify(obj));
		});
		
		//저장한 환경 설정을 읽어옴
		$('#get_color').click(function(){
			var obj = JSON.parse(localStorage.getItem("color")); 
			$('#plan_content').css({
				'backgroundColor': obj.bgcolor,
				'color': obj.textcolor
			});
		});
		
		//textarea 포커스 out이벤트시, 자동저장
	     $("#plan_content").focusout(function(){
	         if(schedule==0){
	         //alert('일정을 먼저 선택해주세요.');
	            }else{
	               if(selected_date==0){
	               var text = $('#plan_content').val();
	               selected_date=String(1);   //일정 클릭이없으면 default로 1저장
	               customer.set(selected_date,text);
	               console.log(selected_date + ": "+ customer.get(selected_date));
	            }else{
	               var text = $('#plan_content').val();
	               customer.set(selected_date,text);
	               console.log(selected_date + ": "+ customer.get(selected_date));
	            };
	         };
	      for (let element of customer) {
	        console.log(element);
	         };
	      });
	      
	      //TextArea값 글자제한 
	      $("#plan_content").keydown(function(e) {
	         var text = $('#plan_content').val();
	         if(text.length>1500){
	            alert("1500자 이상 저장할수없습니다.");
	            $("plan_content").attr("readonly",true);
	         }else{      
	             customer.set(selected_date,text);
	             console.log(selected_date + ": "+ customer.get(selected_date));
	         };
	   });
	
		function initMap() {
			console.log(window.innerWidth);
			// The location of Uluru
			var uluru = {lat: -25.344, lng: 131.036};
			// The map, centered at Uluru
			map = new google.maps.Map(
				document.getElementById('map'), {zoom: 4, center: uluru});
			// The marker, positioned at Uluru
			marker = new google.maps.Marker({position: uluru, map: map});
			
			clickHandler = new ClickEventHandler(map, location);
			
			//Map에 미리정의된 지역을 클릭시 출력되는 infoWindow
			var infowindow = new google.maps.InfoWindow();
			duration_info = new google.maps.InfoWindow();
			//위치 검색시 자동완성
			var search_input = document.getElementById('search_input');
			autocomplete = new google.maps.places.Autocomplete(search_input);
			//자동완성으로 입력 또는 엔터 입력시
			autocomplete.addListener('place_changed', function(){
				searchPlace();
			});
			//검색 버튼 눌렀을 경우
			geocoder = new google.maps.Geocoder();
			document.getElementById('search_submit').addEventListener('click', function() {
				geocodeAddress(geocoder, map);
			});
			
			//클릭한 곳에 마커 추가
			map.addListener('click', function(event) {
				//클릭 event가 일어난 곳의 위도와 경도를 가져온다.
				addMarker(event.latLng, map, infowindow);
			});
			
			//경로 검색 Service 추가
			directionsService = new google.maps.DirectionsService;
	        directionsDisplay = new google.maps.DirectionsRenderer({
	          map: map,
	          suppressMarkers: true,
	          panel: document.getElementById('direction_result')
	        });
	        
	        //경로를 이어주는 polyLine 생성 및 셋팅
	        poly = new google.maps.Polyline({
	            strokeColor: '#000000',
	            strokeOpacity: 0.6,
	            strokeWeight: 3
	          });
	        poly.setMap(map);
				console.log(poly);
	          //marker Server로 부터 가져온것... marker 함수가 iniMap안에서만 사용가능..
	          if(dto_marker != null || dto_marker != undefined){
	        	  let split_marker = dto_marker.split("/");
					for(let i=0; i<split_marker.length-1; i++){
						console.log(split_marker[i]);
						let temp_marker = split_marker[i].split("&");
						//latlng는 string이 아닌 Number값.
						let LatLng = (temp_marker[2].substring(1,temp_marker[2].length-1)).split(",");
						let new_marker = new google.maps.Marker({
							icon: '${pageContext.request.contextPath}/resources/image/number/'+temp_marker[1]+'.png',
							position: new google.maps.LatLng(LatLng[0], LatLng[1]),
							map: map,
						});
						let add_marker = new route(new_marker, temp_marker[0], Number(temp_marker[1]), temp_marker[3]);
						let service = new google.maps.places.PlacesService(map);
						service.getDetails({
								placeId: temp_marker[3]
							}, function(place, status) {
								if (status === google.maps.places.PlacesServiceStatus.OK) {
									add_marker.place = temp_marker[3];
								}else{
									add_marker.place = new_marker.getPosition();
								}
							});
						routes.push(add_marker);
						//이벤트 셋팅..
						console.log(routes);
						new_marker.addListener('click',function(){
							select_addedMarker(event, new_marker);
						});
					}				
					
					//1일자로 선택 및 for문 돌면서 Map에 셋팅.
					console.log(poly);
					showMarker(1,poly);
					selected_date = "1";
					
					//TextArea 셋팅
					$("#plan_content").val(customer.get("1"));
					//map 셋팅
					map.setCenter(routes[1].marker.getPosition());
					map.setZoom(18);
					
	          }
		}
		
		function searchPlace(){
			var place = autocomplete.getPlace();
			//검색 결과 해당 위치가 등록되어 있지 않는 경우
			if (!place.geometry) {
				//엔터로 입력시 정확한 값이 아니기때문에 autocomplete사용 할수 없다.
				geocodeAddress(geocoder, map);
				return;
			}
			// 검색한 장소에 대해 viewport가 있읏 경우 map에 셋팅
			// viewport 해당 장소에 대해 선호되는 setting 값.
			if (place.geometry.viewport) {
				map.fitBounds(place.geometry.viewport);
			} else {
				map.setCenter(place.geometry.location);
				map.setZoom(18);
			}
			
			//검색 장소에 마커 이동
			marker.setPosition(place.geometry.location);
			marker.setVisible(true);
			
		}
		
		function geocodeAddress(geocoder, resultsMap) {
			var address = document.getElementById('search_input').value;
			geocoder.geocode({'address': address}, function(results, status) {
				if (status === 'OK') {
					if (results[0].geometry.viewport) {
						resultsMap.fitBounds(results[0].geometry.viewport);
					} else {
						resultsMap.setCenter(results[0].geometry.location);
						resultsMap.setZoom(18);
					}
					
					//마커 이동
					marker.setPosition(results[0].geometry.location);
					marker.setVisible(true);
				} else {
					alert('Geocode was not successful for the following reason: ' + status);
				}
			});
		}
		
		//넘겨 받은 위/경도를 기반으로 마커를 추가한다.
		function addMarker(location, map, infowindow) {
			//마커가 없을 경우 마커를 생성하고 있을 경우엔 위치 변경만 하여 지도상 하나의 마커만 존재하도록 한다.
			
			if(marker == undefined || marker == null){
				marker = new google.maps.Marker({
					position: loaction,
					map: map,
					animation: google.maps.Animation.BOUNCE
				});
			}else{
				marker.setPosition(location);
				marker.setVisible(true);
				marker.setAnimation(google.maps.Animation.BOUNCE);
			}
			map.setCenter(location);
			
			//기존에 있던 경로 추가 이벤트 삭제
			$('#marker_add').off();
			//마커가 있는 상태에서 경로 추가 버튼 클릭시 발생 하는 이벤트
			$('#marker_add').click(function(event){
				if(routes.length>25){
					alert('더 이상 마커를 추가할수 없습니다!');
				}else{
					marker.setVisible(false);
					//현재 날짜의 가장 마지막 번호 구하기
					let num = getLastDayNum(selected_days[selected_date-1]);
					let selected_marker;
					if(marker){
							selected_marker = new google.maps.Marker({
							icon: '${pageContext.request.contextPath}/resources/image/number/'+(num+1)+'.png',
							position: location,
							map: map,
							animation: google.maps.Animation.BOUNCE
						});
						//selected_marker.setLabel(routes.length+'');
						for (var i = 1; i < routes.length; i++) {
								routes[i].marker.setAnimation(null);	
						}
						console.log(selected_marker);
						
						let add_marker = new route(selected_marker, selected_days[selected_date-1], num+1, marker_placeId);
						let service = new google.maps.places.PlacesService(map);
						service.getDetails({
								placeId: marker_placeId
							}, function(place, status) {
								if (status === google.maps.places.PlacesServiceStatus.OK) {
									add_marker.place = place.name;
								}else{
									add_marker.place = selected_marker.getPosition();
								}
							});

						routes.push(add_marker);
						console.log(routes);
						//해당 경로를 클릭한 상태에서의 엑션 활동
						//경로 추가시 이전 경로와의 연결
						console.log(poly);
						var path = poly.getPath();
						console.log(path);
						path.push(selected_marker.getPosition());
						//방금 추가한 마커에 대해 바로 마커액션을 할수 있도록 이벤트 추가.
						makerber_event(selected_marker);
						selected_marker.addListener('click',function(){
							select_addedMarker(event, selected_marker);
						});
						
						//use_tiem에 추가.
						let useTime = new use_time(selected_days[selected_date-1], num+1, " ", " ");
						use_times.push(useTime);
					}	
				}
			});
		}
		
		
		//POI 클릭시 
		var ClickEventHandler = function(map) {
	        this.map = map;
	        this.directionsService = new google.maps.DirectionsService;
	        this.directionsDisplay = new google.maps.DirectionsRenderer;
	        this.directionsDisplay.setMap(map);
	        this.placesService = new google.maps.places.PlacesService(map);

	        this.map.addListener('click', this.handleClick.bind(this));
		};
		
		ClickEventHandler.prototype.handleClick = function(event) {
	        console.log('You clicked on: ' + event.latLng);

	        if (event.placeId) {
				console.log('You clicked on place:' + event.placeId);
				var info = document.getElementsByClassName("gm-style-iw");
	        	if(info.length != 0){
	        		info[0].parentNode.style="display: ";
	        	}
	        	marker_placeId = event.placeId;
	        }else{
	        	marker_placeId = event.latLng;
	        	var info = document.getElementsByClassName("gm-style-iw");
	        	if(info.length != 0){
	        		console.log(info[0].parentNode);
	        		info[0].parentNode.style="display: none";
	        	}
	        }
		};
		
		//x_butotn을 클릭했을때 발생되는 이벤트 처리
		//bar의 에니메이션 처리 및 x_button의 회전	
		$(document).ready(function(){
			$("#x_button").click(function(){
				var rotation = null;
				var angle;
				var end_angle;
				if(isXButton == 0){
					angle=45;
					end_angle=0;
					isXButton=1;
					
					$('#marker_bar').attr('style','display:inline;');
					$('#marker_bar').animate({
						opacity: '1',
						left: '+=120px'
					},600);
					
					$('#direction_bar').attr('style','display:inline;');
					$('#direction_bar').animate({
						opacity: '1',
						top: '-=450px'
					},600);
					
				}else{
					angle=90;
					end_angle=45;
					isXButton=0;
					
					$('#marker_bar').animate({
						opacity: '0',
						left: '-=120px',
					},{
						duration: 600,
						complete: function(){
							$('#marker_bar').attr('style','display:none;');
						}
					});
					
					$('#direction_bar').animate({
						opacity: '0',
						top: '+=450px'
					},{
						duration: 600,
						complete: function(){
							$('#direction_bar').attr('style','display:none;');
						}
					});
				}

				var rotation = setInterval(function(){
					  angle-=1;
					  $("#x_button").animate({
						  left: '=+'+angle+'px',
						  left: '=-'+angle+'px',
					  },{
						step: function(){
							  $("#x_button").css('transform','rotate('+angle+'deg)')
							  if(angle==end_angle){
								  $("#x_button").stop();
								  clearInterval(rotation);
							  }
						  }
					  });
				},15);
			});
			
			//경로 모드 선택시 모드 변경.
			$('#walking').click(function(){
				selected_mode = 'WALKING';
				calculateAndDisplayRoute(directionsService, directionsDisplay);
				console.log(selected_mode);
			});
			$('#driving').click(function(){
				selected_mode = 'DRIVING';
				calculateAndDisplayRoute(directionsService, directionsDisplay);
				console.log(selected_mode);
			});
			$('#biking').click(function(){
				selected_mode = 'TRANSIT';
				calculateAndDisplayRoute(directionsService, directionsDisplay);
				console.log(selected_mode);
			});
			//경로찾기 Panel에서 x버튼을 누를경우 사라지기
			$('#direction_exit').click(function(){
				$('#direction_bar').animate({
					opacity: '0',
					top: '+=450px'
				},{
					duration: 600,
					complete: function(){
						$('#direction_bar').attr('style','display:none;');
					}
				});
			})
		});
		
		//경로 array에서 해당 Marker에 해당되는 index를 반환하는 함수
		function getRoutesIndex(marker){
			let i=1;
			for(;i<routes.length; i++){
				if(routes[i].marker == marker){
					break;
				}
			}
			return i;
		}

		//해당 날짜의 가장 마지막 index를 구하는 함수
		function getLastDayNum(data){
			let result=0;
			let i=1;
			for(;i<routes.length;i++){
				if(data == routes[i].day){
					result++;
				}
			}
			return result;
		}
		//경로 계산
		function calculateAndDisplayRoute(directionsService, directionsDisplay) {

			if(directionsDisplay.getMap() == null){
				directionsDisplay.setMap(map);
			}
	        directionsService.route({
	          origin: place_start,//$('#direction_start').val(),
	          destination: place_end,//$('#direction_end').val(),
	          travelMode: selected_mode
	        }, function(response, status) {
	          if (status == 'OK') {
	            directionsDisplay.setDirections(response);
	          //거리km 계산
				var total = 0;
		        var myroute = directionsDisplay.getDirections().routes[0];
		        for (var i = 0; i < myroute.legs.length; i++) {
		          total += myroute.legs[i].distance.value;
		        }
		        total = total / 1000;
		        document.getElementById('total').innerHTML = total + ' km';
	          } else {
	        	  $('#direction_result').html('경로가 존재하지 않습니다');
	          }
	        });
	      }
		function showMarker(data){
			//현재 날짜
			let nowDate = selected_days[data-1];
			//polyLine 초기화
			if(poly != null || poly != undefined){
				poly.setMap(null);
				console.log(poly);
			}
			poly = new google.maps.Polyline({
	            strokeColor: '#000000',
	            strokeOpacity: 0.6,
	            strokeWeight: 3
	          });
			
			console.log(poly);
			poly.setMap(map);
			let path = poly.getPath();
			for(let i=1; i<routes.length;i++){
				if(routes[i].day==nowDate){
					routes[i].marker.setMap(map);
					path.push(routes[i].marker.getPosition());
					console.log(routes[i].marker.getPosition())
				}else{
					routes[i].marker.setMap(null);
				}
			}			
		}
		
		function select_addedMarker(evet,selected_marker){
			//selected_marker.setAnimation(google.maps.Animation.BOUNCE);
			console.log(selected_marker);
			//다른 경로의 animation 삭제
			let ind = getRoutesIndex(selected_marker);
			
			for (let i = 1; i < routes.length; i++) {
				if(i != ind){
					routes[i].marker.setAnimation(null);	
				}
			}

			makerber_event(selected_marker);
			
			//클릭한 경로가 2번째 이상의 경로일 경우 전 경로 및 라인 연결함.
			//무조건 전 루트였던것을 num기준으로 바꿈.
			let index_temp = getRoutesIndex(selected_marker);
			console.log(routes[index_temp])
			if(routes[index_temp].num >1){
				for(let i=1;i<routes.length; i++){
					if(routes[i].day == routes[index_temp].day && routes[i].num == routes[index_temp].num-1){
						console.log(i);
						$('#direction_start').val(routes[i].place);
						$('#direction_end').val(routes[index_temp].place);
						place_start = routes[i].marker.getPosition();
						place_end = routes[index_temp].marker.getPosition();
						calculateAndDisplayRoute(directionsService, directionsDisplay);
						break;
					}
				}
			}else{
				directionsDisplay.setMap(null);
			}
			
			//클릭한곳에 windowInfo 나오게 만듬.
			duration_info.close();
			console.log(duration_info);
			let route =  routes[getRoutesIndex(selected_marker)];
			let useTime = use_times[getUseTime(route.day, route.num)];
			if(useTime.end==" "){
				duration_info.setContent(useTime.start);
				//duration_info.setPosition(selected_marker.getPosition());
				duration_info.open(map,selected_marker);
				if(useTime.start == " "){
					duration_info.close();
				}
			}else{
				duration_info.setContent(useTime.start+"~"+useTime.end);
				//duration_info.setPosition(selected_marker.getPosition());
				duration_info.open(map,selected_marker);
			}
			
			selected_marker.setAnimation(google.maps.Animation.BOUNCE);			
		}
		
		function makerber_event(selected_marker){
			selected_marker.setAnimation(google.maps.Animation.BOUNCE);
			$('#marker_delete').off();
			$('#maker_modify').off();
			$('#time_modify').off();
			//마커 삭제를 눌렀을 경우
			$('#marker_delete').click(function(){
				//체류 시간 삭제
				let useTime_index = getUseTime(routes[getRoutesIndex(selected_marker)].day, routes[getRoutesIndex(selected_marker)].num);
				let seleted_useTime = use_times[useTime_index]; 
				use_times.splice(useTime_index, 1);
				
				let selected_route = routes[getRoutesIndex(selected_marker)];
				let num = getRoutesIndex(selected_marker);		
				routes.splice(getRoutesIndex(selected_marker), 1);
				
				console.log(routes);
				console.log(num);
				
				for(let i=1; i<routes.length; i++){
					if(routes[i].day == selected_route.day && routes[i].num > selected_route.num){
						routes[i].marker.setIcon('${pageContext.request.contextPath}/resources/image/number/'+(routes[i].num-1)+'.png');
						routes[i].num = routes[i].num-1;	
					}
				}
				selected_marker.setMap(null);
				//경로 삭제시 polyLine 재설정
				poly.setMap(null);
				poly = new google.maps.Polyline({
		            strokeColor: '#000000',
		            strokeOpacity: 0.6,
		            strokeWeight: 3
				});
				poly.setMap(map);
				
				let path = poly.getPath();
				for(let i=1; i<routes.length; i++){
					if(routes[i].day == selected_route.day){
						path.push(routes[i].marker.getPosition());	
					}
				}
				
				//삭제시 use_time의 num도 같이 변경.
				for(let j=0; j<use_times.length; j++){
					if(use_times[j].day == seleted_useTime.day && use_times[j].num > seleted_useTime.num){
						use_times[j].num = use_times[j].num-1;
					}
				}
				
				//경로를 안보이게 처리
				directionsDisplay.setMap(null);
			});
			
			//경로 변경을 눌렀을 경우
			$('#maker_modify').click(function(){
				let index = routes[getRoutesIndex(selected_marker)].num;
				let input = prompt('변경할 경로 번호를 입력하세요!', index);
				
				let select_route = routes[getRoutesIndex(selected_marker)];
				let routes_temp = [];
				let use_time_temp = [];
				//선택된 날짜의 경로 뽑고 삭제.
				for(let i=1;i<routes.length;i++){
					if(routes[i].day == select_route.day){
						console.log(i);
						routes_temp.push(routes[i]);
						routes.splice(i, 1);
						i--;
					}
				}
				for(let i=0;i<use_times.length;i++){
					if(use_times[i].day == select_route.day){
						console.log(i);
						use_time_temp.push(use_times[i]);
						use_times.splice(i, 1);
						i--;
					}
				}
				let routes_temp_temp = [];
				let use_time_temp_temp = [];
				input--;
				index--;
				if(index > input){
					for(let i = 0; i<routes_temp.length; i++){
						if(i < input){
							routes_temp_temp[i] = routes_temp[i];
							use_time_temp_temp[i] = use_time_temp[i];
						}else if(i == input){
							routes_temp_temp[i] = routes_temp[index];
							routes_temp_temp[i].marker.setIcon('${pageContext.request.contextPath}/resources/image/number/'+(i+1)+'.png');
							routes_temp_temp[i].num = Number(i+1);
							use_time_temp_temp[i] = use_time_temp[index];
							use_time_temp_temp[i].num = Number(i+1);
						}else{
							if(i > index){
								break;
							}else{
								routes_temp_temp[i] = routes_temp[i-1];
								routes_temp_temp[i].marker.setIcon('${pageContext.request.contextPath}/resources/image/number/'+(i+1)+'.png');
								routes_temp_temp[i].num = i+1; 
								use_time_temp_temp[i] = use_time_temp[i-1];
								use_time_temp_temp[i].num = i+1;
							}
						}
					}	
				}else{
					for(let i=0; i<routes_temp.length; i++){
						if(i < index){
							routes_temp_temp[i] = routes_temp[i];
							use_time_temp_temp[i] = use_time_temp[i];
						}else if(i == index){
							routes_temp_temp[input] = routes_temp[i];
							routes_temp_temp[input].marker.setIcon('${pageContext.request.contextPath}/resources/image/number/'+(input+1)+'.png');
							routes_temp_temp[input].num = Number(input+1);
							use_time_temp_temp[input] = use_time_temp[i];
							use_time_temp_temp[input].num  = Number(input+1);
						}else if(i<= input){
							routes_temp_temp[i-1] = routes_temp[i];
							routes_temp_temp[i-1].marker.setIcon('${pageContext.request.contextPath}/resources/image/number/'+(i)+'.png');
							routes_temp_temp[i-1].num = i;
							use_time_temp_temp[i-1] = use_time_temp[i];
							use_time_temp_temp[i-1].num = i;
						}else if(i> input){
							routes_temp_temp[i] = routes_temp[i];
							use_time_temp_temp[i] = use_time_temp[i];
						}
					}
				}
				console.log(routes_temp_temp);
				routes_temp = routes_temp_temp;
				use_time_temp = use_time_temp_temp;
				for(let i=0;i<routes_temp.length; i++){
					routes.push(routes_temp[i]);
				}
				for(let i=0;i<use_time_temp.length;i++){
					use_times.push(use_time_temp[i]);
				}
				for(let i=0; i<routes.length; i++){
					if(routes[i] == undefined){
						routes.splice(i,1);
						i--;
					}
				}
				for(let i=0; i<use_times.length; i++){
					if(use_times[i] == undefined){
						use_times.splice(i,1);
						i--;
					}
				}
				
				console.log(routes);
				console.log(use_times);
				//경로 변경시 polyLine 재설정
				poly.setMap(null);
				poly = new google.maps.Polyline({
    				strokeColor: '#000000',
    				strokeOpacity: 0.6,
   					strokeWeight: 3
  				});
  				poly.setMap(map);
  				path = poly.getPath();
				for(let i=1; i<routes.length; i++){
					if(routes[i].day == select_route.day){
						path.push(routes[i].marker.getPosition());	
					}
				}
				//경로를 안보이게 처리
				directionsDisplay.setMap(null);
			});
			
			$('#time_modify').click(function(){
				let route =  routes[getRoutesIndex(selected_marker)];
				let useTime = use_times[getUseTime(route.day, route.num)];
				let start_temp = useTime.start;
				let end_temp = useTime.end;
				let input = prompt('시간을 입력해주세요!(ex. 1800~2000)', start_temp+"~"+end_temp);
				if(input){
					let time = input.split("~");
					if(time != null || time != undefined || time.length==2){
						console.log(useTime);
						useTime.start = time[0];
						useTime.end = time[1];
					}
				}
				console.log(use_times);
			});
		}
	</script>
	
	<!-- Modal 이벤트 처리 script -->
	<script>
	var now_url = '';
	
	//네이버 검색 버튼을 눌렀을 경우
	$('#naver_search').on('click', function () {
		let input = $('#modal_input').val();
		if(input == undefined || input == null || input == ''){
			alert('검색어를 입력 해주세요!');
		}else{
			$.ajax({
				url: '${pageContext.request.contextPath}/crolSearch?query='+input,
				success: function(data){
					$('#modal_naver_list').html(data.response);
					$("#modal_naver_pages").html(data.paging);
					$('#modal_naver').css('display','inline');
					now_url = data.url;
					event.preventDefault();
				}
			});
		}
	});
	//pages의 번호를 눌렀을 경우
	function modal_naver(data){
		//&과 = 이스케이프 처리
		var pars = "n="+encodeURIComponent(data);
		$.ajax({
			url: "${pageContext.request.contextPath}/crolSearch?"+pars,
			success: function(data){
				$('#modal_naver_list').html(data.response);
				//console.log(data);
				$("#modal_naver_pages").html(data.paging);
				now_url = data.url;
				return false;
			}
		});
	}
	//modal_naver내부안에서 검색어를 입력하고 검색을 했을경우
	$('#modal_search_buton').on('click', function(){
		let input = $('#modal_naver_input').val();
		if(input == undefined || input == null || input == ''){
			alert('검색어를 입력 해주세요!');
		}else{
			$.ajax({
				url: '${pageContext.request.contextPath}/crolSearch?query='+input,
				success: function(data){
					$('#modal_naver_list').html(data.response);
					$("#modal_naver_pages").html(data.paging);
					$('#modal_naver').css('display','inline');
					now_url = data.url;
					event.preventDefault();
				}
			});
		}
	});
	//네이버 검색 close 버튼
	$('#modal_naver_close').click(function(){
		$("#modal_naver").css('display','none');
	});
	
	//블로그로 들어갈 경우
	function modal_naver_blog(data){
		$('#modal_blog').css('display','inline');
		//&과 = 이스케이프 처리
		var pars = "n="+encodeURIComponent(data);
		$.ajax({
			url: "${pageContext.request.contextPath}/crolBlog?"+pars,
			success: function(data){
				$('#modal_blog_body').html(data.body);
				//console.log(data);
				$('#modal_blog_title').html(data.title);
				now_url = data.url;
				console.log("blog: "+now_url);
				return false;
			}
		});
	}
	//블로그 close 버튼
	$('#modal_blog_close').click(function(){
		$("#modal_blog").css('display','none');
	});
	
	//인스타 검색
	$('#insta_search').click(function(){
		let input = $('#modal_input').val();
		if(input == undefined || input == null || input == ''){
			alert('검색어를 입력 해주세요!');
		}else{
			$.ajax({
				url: '${pageContext.request.contextPath}/crolInsta?query='+input,
				success: function(data){
					let content = data.content;
					$('#modal_insta_list').html("<div id='modal_insta_img'>");
					for(let i=0;i<content.length; i++){
						$('#modal_insta_img').append($('<img/>', {
					        src: content[i],
					        style: "width: 100px; height: 100px"
					    }));	
					}
					$('#modal_insta').css('display','inline');
				}
			});
		}
	});
	//인스타 창에서 검색 누를경우
	$('#modal_insta_buton').click(function(){
		let input = $('#modal_insta_input').val();
		if(input == undefined || input == null || input == ''){
			alert('검색어를 입력 해주세요!');
		}else{
			$.ajax({
				url: '${pageContext.request.contextPath}/crolInsta?query='+input,
				success: function(data){
					let content = data.content;
					$('#modal_insta_list').html("<div id='modal_insta_img'>");
					for(let i=0;i<content.length; i++){
						$('#modal_insta_img').append($('<img/>', {
					        src: content[i],
					        style: "width: 100px; height: 100px"
					    }));	
					}
				}
			});
		}
	});
	//인스타 close 버튼
	$('#modal_insta_close').click(function(){
		$("#modal_insta").css('display','none');
	});
	//담기 버튼
	$('#add_my').click(function(){
		console.log("url: "+now_url);
		let string = $('#plan_content').val(); 
		$('#plan_content').val(string+now_url);
	})
	</script>
	
	<!-- 달력 이벤트 처리 script -->
	<script>
	var onCalDiv = 1;
	var start_date;
	var end_date;
	var selected_days = [];
	//주 영어로 setting
	var dateString = new Array('sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat');
	//각 달의 마지막 날 Array로 만듬.
	var lastDate = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	//윤년 계산
	//년도를 구함
	var date = new Date();
	var currentYear = date.getFullYear();
	if( (currentYear % 4 === 0 && currentYear % 100 !== 0) || currentYear % 400 === 0 )
		lastDate[1] = 29;
	
	//처음 들어왔을 경우  Calender 표시
	$('#div_cal').css('display','inline');
	setCal('cal');
		
	function setCal(id, date){
	var kCalendar = document.getElementById(id);
	
	if( typeof( date ) !== 'undefined' ) {
		date = date.split('_');
		date[1] = date[1] - 1;
		date = new Date(date[0], date[1], date[2]);
	} else {
		var date = new Date();
	}
	//년도를 구함
	currentYear = date.getFullYear();
	//연을 구함. 월은 0부터 시작하므로 +1, 12월은 11을 출력	
	var currentMonth = date.getMonth() + 1;
	//오늘 일자	
	var currentDate = date.getDate();
	
	//이번달 1일의 요일은 출력. 0은 일요일 6은 토요일
	date.setDate(1);
	var currentDay = date.getDay();
	
	//총 몇 주인지 구함.
	var currentLastDate = lastDate[currentMonth-1];
	var week = Math.ceil( ( currentDay + currentLastDate ) / 7 );
	
	//이전 과 다음 버튼에 들어갈 값  셋팅
	//만약 이번달이 1월이라면 1년 전 12월로 출력.
	if(currentMonth != 1)
		var prevDate = currentYear + '_' + ( currentMonth - 1 ) + '_' + currentDate;
	else
		var prevDate = ( currentYear - 1 ) + '_' + 12 + '_' + currentDate;
	//만약 이번달이 12월이라면 1년 후 1월로 출력.
	if(currentMonth != 12) 
		var nextDate = currentYear + '_' + ( currentMonth + 1 ) + '_' + currentDate;
	else
		var nextDate = ( currentYear + 1 ) + '_' + 1 + '_' + currentDate;
	
	//10월 이하라면 앞에 0을 붙여준다.
	if( currentMonth < 10 )
		var currentMonth = '0' + currentMonth;
	
	
	var calendar = '';
	
	calendar += '<div id="header" class="cal">';
	calendar += '<span class="cal"><a href="#" class="month_button left cal" onclick="setCal(\'' +  id + '\', \'' + prevDate + '\')"><</a></span>';
	calendar += '<span id="date" class="cal">' + currentYear + '년 ' + currentMonth + '월</span>';
	calendar += '<span class="cal"><a href="#" class="month_button right cal" onclick="setCal(\'' + id + '\', \'' + nextDate + '\')">></a></span>';
	calendar += '</div>';
	calendar += '<table border="0" cellspacing="0" cellpadding="0" class="cal">';
	calendar += '<caption class="cal">' + currentYear + '년 ' + currentMonth + '월 달력</caption>';
	calendar += '<thead>';
	calendar += '<tr class="cal">';
	calendar += '<th class="day sun cal" scope="row">일</th>';
	calendar += '<th class="day mon cal" scope="row">월</th>';
	calendar += '<th class="day tue cal" scope="row">화</th>';
	calendar += '<th class="day wed cal" scope="row">수</th>';
	calendar += '<th class="day thu cal" scope="row">목</th>';
	calendar += '<th class="day fri cal" scope="row">금</th>';
	calendar += '<th class="day sat cal" scope="row">토</th>';
	calendar += '</tr>';
	calendar += '</thead>';
	calendar += '<tbody>';
	
	//달의 시작 하는 요일을 넘기기위함.
	var dateNum = 1 - currentDay;
	
	for(var i = 0; i < week; i++) {
		calendar += '<tr class="cal">';
		for(var j = 0; j < 7; j++, dateNum++) {
			let nowDate = currentYear + '_' + currentMonth + '_' + dateNum;
			//1보다 작으면 빈칸으로 해야함. 또는 일자가 끝나고 남은 공간 처리
			if( dateNum < 1 || dateNum > currentLastDate ) {
				calendar += '<td class="' + dateString[j] + ' cal"> </td>';
				continue;
			}
			calendar += '<td onclick=addDateListener("'+nowDate+'") id="'+nowDate+'" class="' + dateString[j] + ' cal">' + dateNum + '</td>';
			//addDateListener(nowDate);
		}
		calendar += '</tr>';
	}
	
	calendar += '</tbody>';
	calendar += '</table>';
	
	kCalendar.innerHTML = calendar;
	cssSelectedDays();
	clickAlert();
	}
	//누른값 표시하는 함수
	function addDateListener(data){
		console.log(data);
		//시작이 비어있으면 첫번째 클릭
		//시작은 있고 끝이 없으면 두번쨰 클릭
		//끝이 있으면 시작과 끝을 초기화 후 시작에 넣기
		if(start_date == null || start_date== 'undefined' || start_date==''){
			start_date = data;
			$('#'+start_date).css('background-color','darkgray');
		}else if(end_date == null || end_date== 'undefined' || end_date==''){
			end_date = data;
			pushSeletedDays();
		}else{
			for(let i=0;i<selected_days.length;i++){
				$('#'+selected_days[i]).css('background-color','');
			}
			selected_days=[];
			start_date = data;
			end_date = '';
			$('#'+start_date).css('background-color','darkgray');
		}
	}
	//선택된 날짜를 넣는 함수
	function pushSeletedDays(){
		//년 월 일 분리
		let	now = start_date.split('_');
		let	end = end_date.split('_');
		console.log(now[2]+'');
		console.log('start '+start_date);
		console.log('end '+end_date);
		//end가 start보다 이른 날짜일 경우
		if(Number(now[2])>=Number(end[2]) || Number(now[1])>Number(end[1]) ){
			alert('도착 날짜는 출발 날짜보다 늦어야 합니다!');
			end_date='';
		}else{
			$('#'+end_date).css('background-color','darkgray');
			for(; ;now[2]=Number(now[2])+1 + ''){
				//마지막날이 지나면 월을 증가, 일을 초기화
				if(lastDate[Number(now[1])-1]<Number(now[2])){
					now[1] = Number(now[1])+1 +'';
					//앞에 0붙여줘야함.
					if(now[1]<10){
						now[1]='0'+now[1];
					}
					now[2]='0';
					continue;
				//끝 날자가 지나면 종료.
				}else if(end[1]==now[1] && Number(now[2])==Number(end[2])){
					console.log('now2 '+now[2]);
					selected_days.push(now[0]+'_'+now[1]+'_'+now[2]);
					break;
				}
				selected_days.push(now[0]+'_'+now[1]+'_'+now[2]);
			}
			//선택한 일정이 10일 이상이 되는 경우. 경고창 발생
			if(selected_days.length>10){
				alert('일정은 10일 아래로 선택해주세요!');
				selected_days = [];
				$('#'+end_date).css('background-color','');
				end_date = '';
			}else{
				cssSelectedDays();	
			}
				
		}
	}
	//선택된곳의 css를 변경하는 함수
	function cssSelectedDays(){
		$('#'+start_date).css('background-color','darkgray');
		$('#'+end_date).css('background-color','darkgray');
		for(let i=1;i<selected_days.length-1;i++){
			$('#'+selected_days[i]).css('background-color','gainsboro');
		}
	}
	
	$('#div_cal_ok').click(function(){
		//날짜 선택 안한 상태에서 확인 버튼 못누르게
		if(selected_days.length>0){
			$('#div_cal').css('display','none');
			//초기화
			schedule=0;
			$('#mainmenu').empty();
			//다시 셋팅
			for(let i=0;i<selected_days.length;i++){
				callAppendChild("일정 선택 완료");
			}
			//다른 곳 클릭 하면 경고창 뜨는 이벤트 삭제
			$('html').off('click');
			onCalDiv=0;
			$('#btn1').css('background-color','#343a40');
			$('#btn1').css('color','white');
		}else{
			alert('일정 날짜를 선택해주세요!');
		}
	});
	$('#div_cal_reset').click(function(){
		for(let i=selected_days.length-1;i>=0;i--){
			$('#'+selected_days[i]).css('background-color','');
		}
		selected_days=[];
		start_date='';
		end_date='';
	})
	$('#calender').click(function(){
		clickAlert();
		$('#div_cal').css('display','inline');
		onCalDiv=1;
		cssSelectedDays();
	})
	//일자 번호에서 + 버튼을 눌렀을 경우 일정 날짜의 맨뒤에 추가하는 함수.
	function addCalender(){
		let last_date_string = selected_days[selected_days.length-1];
		let last_date = last_date_string.split("_");
		let year=last_date[0];
		let month=last_date[1]; 
		let day=last_date[2];
		//list에 끝에 들어있는 날이 그 달의 마지막 날이면 
		if(lastDate[Number(last_date[1])-1]==Number(last_date[2])){
			//끝에 들어있는 달이 달의 마지막이면서 연의 마지막이면
			if(last_date[1]=="12"){
				year = Number(last_date[0])+1+'';
				month = "01";
				day = "1";
			}else{
				month = Number(last_date[1])+1+'';
				if(Number(month)<10){
					month='0'+month;
				}
				day = "1";
			}
		}else{
			day = Number(day)+1+'';
		}
		//마지막 날 변경
		end_date = year+'_'+month+'_'+day;
		//list에 추가
		selected_days.push(year+'_'+month+'_'+day);
	}
	//일자 번호에서 - 버튼을 눌렀을 경우 일정 날짜의 맨뒤의 일정을 삭제 하는 함수
	function delCalender(){
		$('#'+selected_days[selected_days.length-1]).css('background-color','');
		selected_days.pop();
		end_date = selected_days[selected_days.length-1];
		console.log(selected_days);
	}
	function clickAlert(){
		//일정 띄워져있을때 딴곳 누르면 경고창 띄우게
		$('html').click(function(e) {
			if(onCalDiv==1){
				if(!$(e.target).hasClass('cal') && $(e.target).attr('id')!='calender') {
					console.log(e.target);
					alert('일정을 먼저 선택해주세요!');
					e.stopPropagation();
					e.preventDefault();
				}	
			}else{
				
			}
		});
	}
	</script>
	
	<!-- 내정보버튼 클릭  -->
	<script>
	$(function(){
		$("#user").click(function(){
			console.log("user클릭");
			$("#userMenu").toggle("slow");
		})
	})
	</script>
	
	<!-- 저장 버튼 -->
	<script>
	//글저장
	//현재 Map의 제목
	var map_title='';
	//현재 Map의 글 번호
	var map_number='';
	$('#textSave').click(function(){
		while(true){
			let title = prompt('제목을 입력해주세요', map_title);
			console.log(title);
			if(title != null && title!=''){
				map_title = title;
				break;
			}
			alert('제목을 입력해주세요');
		}
		//마커 Parsing
		//routes
		//일자(_로 구문되어있음)&번호&위경도(.사용함...)&PlaceId/
		let markerParsing='';
		for(let i=1; i<routes.length; i++){
			markerParsing += routes[i].day+"&"+routes[i].num+"&"+routes[i].marker.getPosition()+"&"+routes[i].place+"/";
		}
		//날짜 Parsing
		//selected_days
		//날짜/
		let dayParsing='';
		for(let i=0; i<selected_days.length;i++){
			dayParsing += selected_days[i]+"/";
		}
		//TextArea Parsing
		//내용/
		let textAreaParsing='';
      	for (let [key,element] of customer) {
        	textAreaParsing += key+"."+element+"/";
		}
		//가계부 내용 Parsing
		//항복.돈/
      	let costParsing='';
        for(let i=0; i<costLists.length;i++){
           costParsing += costLists[i]+"."+costs[i]+"."+costNotes[i]+"/";
         }
        //use_time Parsing
        //날짜.번호.시작.끝/
        let useTimeParsing='';
        for(let i=0;i<use_times.length;i++){
        	useTimeParsing += use_times[i].day+"."+use_times[i].num+"."+use_times[i].start+"."+use_times[i].end+"/";
        }
        
		let submit = {'number': map_number, 'title': map_title, 'marker': markerParsing,
				'day': dayParsing, 'textArea': textAreaParsing, 'cost': costParsing, 'use_time' : useTimeParsing};
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    contentType: "application/json; charset=utf-8",
			url: '${pageContext.request.contextPath}/mapdataUpload',
			type: 'post',
			data: JSON.stringify(submit),
			dataType: 'json',
			success: function(){
				alert('저장이 완료 되었습니다!');
			},
			error: function(){
				alert('저장에 실패 하였습니다');	
			}
		})
		event.preventDefault();
		
	});
	</script>

	<!-- 경비계산  -->
   <script>
    var costLists = new Array(); //목록
    var costs = new Array(); //경비
    var costNotes = new Array(); //비고
    var totalCosts = $('#totalCost').val(); //합계 
   
   //내정보에서 경비계산클릭
   $(function(){
      $("#expenseCal").click(function(){
    	  if(onCalDiv ==0){
      		console.log("내정보에서 경비계산클릭");
  			$("#expense").toggle("slow");	  
      	  } 
      });
   });
   //행삭제
   function deleteLine(obj) {
       var tr = $(obj).parent().parent();
       tr.remove();
      var costs = document.getElementsByClassName('cost');
        autoCal(costs);
   }
   
   //행추가
   $(function(){
      $("#addBtn").click(function(){
         $('.cost').off('blur');
         console.log("추가버튼 클릭");   
         var row="<tr>";
         row+='<td><input type="text" class="costList"/></td>';
         row+='<td><input type="text" class="cost"/></td>';
         row+='<td><input type="text" class="costNote"/></td>';
         row+='<td class="expense_option"><button onclick="deleteLine(this);" class="btn btn-sm btn-danger">삭제</button></td>';
         row+="</tr>";
         $("#expenseTable").append(row);
         
         $('.cost').blur(function() {
            console.log("창뗴었음");
            var costs = document.getElementsByClassName('cost');
            console.log("갯수:"+costs.length);
			autoCal(costs);
         });
      });
   });
   
   //자동 계산
   function autoCal(costs){
      var sum = 0;
      for(let i=0;i<costs.length;i++){
             console.log(costs[i].value);  
             sum+=Number(costs[i].value);
      };
      $(function(){
         $('#totalCost').val(sum);
           console.log(sum);
      });
   };
   //경비계산 정보 저장
   $('#expenseSave').click(function(){
      if(confirm("정말 저장하시겠습니까?")==true){
         var listClass = document.getElementsByClassName('costList');
         var costClass = document.getElementsByClassName('cost');
         var noteClass = document.getElementsByClassName('costNote');
         console.log("listClass : "+listClass);
         console.log("costClass : "+costClass);
         console.log("noteClass : "+noteClass);
         //목록 array저장 및 출력
         for(let i=0;i<listClass.length;i++){
             costLists.push( listClass[i].value); //arraylist에 push해주기
             console.log("목록 array저장완료");
          };
         costLists.forEach(function (item, index, array) {
             console.log("목록 : "+"("+index+") : "+item);
         });
         //금액 array저장 및 출력
         for(let i=0;i<costClass.length;i++){
             costs.push( costClass[i].value); //arraylist에 push해주기
             console.log("금액 array저장완료");
          };
          costs.forEach(function (item, index, array) {
             console.log("금액 : "+"("+index+") : "+item);
         });
          //비고 array저장 및 출력
         for(let i=0;i<noteClass.length;i++){
             costNotes.push( noteClass[i].value); //arraylist에 push해주기
             console.log("비고 array저장완료");
          };
           costNotes.forEach(function (item, index, array) {
             console.log("비고 : "+"("+index+") : "+item);
         });
         console.log("--------------------------------------");
          $("#expense").css('display','none');
      }else{
         return;
      };
   });
   
   //경비 계산 닫기 
   $('#expenseClose').click(function(){
      if(confirm("먼저저장버튼을 클릭하셔야 목록이 저장됩니다.\n그래도 닫기하시겠습니까?")==true){
         console.log(costLists);
         if(costLists == '' || costs == '' || costNotes == ''){
            console.log("값없애기1");
               $('.costList').val('');
               $('.cost').val('');
               $('.costNote').val('');
               $("#expense").css('display','none');
            console.log("값없애기2");
         }else{
           costLists.forEach(function (item, index, array) {
             $('costLists').val(item);
             });
           costs.forEach(function (item, index, array) {
              $('cost').val(item);
             });
           costNotes.forEach(function (item, index, array) {
              $('costLists').val(item);
             });
              $("#expense").css('display','none');
          console.log("값넣기");
         }
      }else{
         return;
      };
	});
	</script>
   
	<!-- 체류 시간  -->
	<script>
	//체류 시간을 저장할 arrayList
	var use_times = [];
	//체류 시간 저장할
	class use_time{
		/*
		this.num;		//장소의 번호, 없으면 ""
		this.day;		//장소의 날짜, 없으면 ""
		this.start;		//장소의 시작 시간, 없으면 ""
		this.end;		//장소의 끝 시간, 없으면 ""
		*/
		constructor(day, num, start, end){
			this.day = day;
			this.num = num;
			this.start = start;
			this.end = end;
		}
	}
	
	//해당되는 use_time 가져오기
	function getUseTime(day, num){
		let i=0;
		for(; i<use_times.length; i++){
			if(use_times[i].day == day && use_times[i].num == num){
				break;
			}
		}
		return i;
	}
	
	</script>
<!-- 이미 있는 글을 읽어왔을 경우. -->
<c:choose>
	<c:when test="${ dto!=null }">
	<c:set var="marker" value="${dto.marker }"/>
		<script>
		
			//title
			map_title = "${dto.title}";
			
			//num
			map_number = "${dto.num}";
			
			//content 셋팅
			let temp_content = "${dto.content_1}";
			let temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_2}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_3}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_4}";	
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_5}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_6}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_7}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_8}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_9}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			temp_content = "${dto.content_10}";
			temp_content_split = temp_content.split(".");
			if(temp_content_split[0] != ""){
				customer.set(temp_content_split[0], temp_content_split[1]);
			}
			
			//Marker 셋팅
			var dto_marker = "${dto.marker}";
			
			//day 셋팅
			let day = "${dto.schedule}";
			let split_day = day.split("/");
			for(let i=0;i<split_day.length-1; i++){
				selected_days.push(split_day[i]);
			}
			start_date = selected_days[0];
			end_date = selected_days[selected_days.length-1];			
			
			//버튼 셋팅.
			$('#div_cal').css('display','none');
			//초기화
			schedule=0;
			$('#mainmenu').empty();
			//다시 셋팅
			for(let i=0;i<selected_days.length;i++){
				callAppendChild("일정 선택 완료");
			}
			//다른 곳 클릭 하면 경고창 뜨는 이벤트 삭제
			$('html').off('click');
			onCalDiv=0;
			$('#btn1').css('background-color','#343a40');
			$('#btn1').css('color','white');
			
			//cost 셋팅
    		//costLists[i]+"."+costs[i]+"."+costNotes[i]+
    		let dto_cost_list = "${dto.cost}"
    		let dto_cost = dto_cost_list.split("/");
    		for(let i=0;i<dto_cost.length-1;i++){
    			let dtoCost = dto_cost[i].split(".");
    			costLists.push(dtoCost[0]);
    			costs.push(dtoCost[1]);
    			costNotes.push(dtoCost[2]);
                var row="<tr>";
                row+='<td><input type="text" class="costList" value="'+dtoCost[0]+'"/></td>';
                row+='<td><input type="text" class="cost" value="'+Number(dtoCost[1])+'"/></td>';
                row+='<td><input type="text" class="costNote" value="'+dtoCost[2]+'"/></td>';
                row+='<td><button onclick="deleteLine(this);">삭제</button></td>';
                row+="</tr>";
                $("#expenseTable").append(row);
    		}
    		let sum=0;
    		console.log(costs);
			for(let i=0;i<costs.length;i++){
                 console.log(costs[i]);  
                 sum+=Number(costs[i]);
          	};
          	$('#totalCost').val(sum);

            $('.cost').blur(function() {
               console.log("창뗴었음");
               var costs = document.getElementsByClassName('cost');
               console.log("갯수:"+costs.length);
   				autoCal(costs);
            });
            
            //use_times 셋팅
            let dto_useTimes = "${dto.use_time}";
            let dto_useTimes_list = dto_useTimes.split("/");
            for(let i=0 ;i<dto_useTimes_list.length-1;i++){
            	let useTime_list = dto_useTimes_list[i].split(".");
            	let useTime = new use_time(useTime_list[0], useTime_list[1], useTime_list[2], useTime_list[3]);
            	use_times.push(useTime);
            }
		</script>
	</c:when>
</c:choose>
</body>
</html>
