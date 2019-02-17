<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>팁 게시판</title>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/codingBooster.css" rel="stylesheet">
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

	<style>      
     .text-stroke {
   		 text-shadow:
    	-1px -1px 0 #000,
    	1px -1px 0 #000,
   	 	-1px 1px 0 #000,
   		1px 1px 0 #000;  
	}

	td {
		border-radius: 5px;
	}
	table {
		border-radius: 5px;
	}
	
	.mainButton{
		background-color: #0064CD;
		color: white;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.whitezone{
		background-color: white;
		color: black;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.bluezone{
		background-color: #ECEBFF;
		color: black;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.subButton{
		background-color: white;
		color: black;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	select {
		background-color: #0064CD;
		color: white;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.textbox {
		background-color: #ECEBFF;
		color: black;
		border: none;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
    </style>

</head>
<body>
<header>
	<c:import url="/default/mainHeader"/>
</header>

<form action="save_reply_board", method="post">
		<div align="center">
			<h1 class="text-stroke" style="text-align: center; color: white; font-family:Broadway;">Reference other's Tip!</h1>
			
			<table border="1"  style="width: 600px; font-family:돋움;">
				<tr align="right">
					<td colspan="8" style="height: 20px;">
						<input class="mainButton" type="button" value="목록으로" onclick="location.href='tip'">
						
						<c:if test="${sessionScope.userId eq select_Tip_board.id or sessionScope.userId eq 'Admin'}">
							<input class="mainButton" type="button" value="글 수정" onclick="location.href='modify_tip_board?num=${select_Tip_board.num}'">
							<input class="mainButton" type="button" value="글 삭제" onclick="location.href='delete_tip_board?num=${select_Tip_board.num}'">
						</c:if>
					</td>
				</tr>
				<tr style="text-align: center; background-color:#0064CD; font-family:돋움;">
					<td style="background: #0064CD; width: 50px; font-size: 9pt;font-weight: bold; color: white;">No.</td> 
					<td style="width: 60px; font-size: 9pt; background-color: white; font-weight: bold;">${select_Tip_board.num}</td>
					<td colspan="6" style="color: white; font-weight: bold;">${select_Tip_board.title}</td>
				</tr>
				<tr style="text-align: center; font-size: 9pt; font-weight: bold;">
					<td style="background: #0064CD; width: 50px; color: white;">분    류</td> <td style="width:80px; background-color: white;">${select_Tip_board.subject}</td>
					<td style="background: #0064CD; width: 50px; color: white;">작성자</td><td style="width:80px; background-color: white;">${select_Tip_board.nickname}</td>
					<td style="background: #0064CD; width: 60px; color: white;">등록시간</td><td style="width:140px; background-color: white;">${select_Tip_board.savedate}</td>
					<td style="background: #0064CD; width: 50px; color: white;">조회수</td><td style="width:50px; background-color: white;">${select_Tip_board.hit}</td>
				</tr>
				<tr style="text-align: center; font-size: 9pt; font-weight: bold;">
				</tr>	
				<tr style="width: 600px; height: 300px;">
					<td colspan="8" style="background-color: white; text-align:center;">
						<br>
						${select_Tip_board.content}
						<br>	
						<br>
						<!-- 이미지 갯수에 맞춰서 이미지 출력하는 곳 -->
						<c:choose>
							<c:when test="${imgNum == 1}">
							<div align="center"><img src="tip_image?num=${select_Tip_board.num}" alt="이미지 로드중 문제가 발생했습니다."></div><br>
							</c:when>
							<c:when test="${imgNum == 2}">
							<div align="center"><img src="tip_image?num=${select_Tip_board.num}" alt="이미지 로드중 문제가 발생했습니다."></div><br>
							<div align="center"><img src="tip_image1?num=${select_Tip_board.num}" alt="이미지 로드중 문제가 발생했습니다."></div><br>
							</c:when>
						</c:choose>
						<br>
					</td>
				</tr>	
				<!-- 아래부터 댓글 확인 공간 -->	
				<c:choose>
					<c:when test="${reply_list.size() == 0}">
						<tr style="text-align: center;">
							<td colspan="8" style="text-align: center; font-weight: bold; background-color: #ECEBFF;">첫 댓글을 달아주세요~</td>
						</tr>
						
					<!-- 댓글 색깔 구분해서 표시하는 곳 -->
					</c:when>
					<c:otherwise>						
						<c:set var="cnt" value="1"/>
						<c:forEach items="${reply_list}" var="reply">
							<c:set var="cnt" value="${cnt+1}"/>
							<c:choose>
								<c:when test="${cnt%2 eq 0}"><!-- 짝수번째 줄이면 하늘색, 아니면 흰색 -->
									<tr style="font-size: 9pt; background-color: #ECEBFF;">
										<td style="font-weight: bold; font-size: 8pt; text-align: center;">${reply.nickname}</td>
										<c:choose>
											<c:when test="${sessionScope.userId eq reply.id or sessionScope.userId eq 'Admin'}">
												<td colspan="5">${ reply.content}</td> 
												<td align="center">	
													<input type="hidden" value="${reply.rpnum}" name="rpnum">
													<input class="bluezone" type="button" value="삭제" id="deleteReply" onclick="location.href='delete_reply_board?rpnum=${reply.rpnum}&num=${select_Tip_board.num}'" >
													<input class="bluezone" type="button" value="수정" onclick="window.open('modify_reply_view?&num=${select_Tip_board.num}&rpnum=${reply.rpnum}', '댓글 수정', 'width=470px, height=250px')">
												</td>
											</c:when>
											<c:otherwise>
												<td colspan="6">${ reply.content}</td>
											</c:otherwise>
										</c:choose>
										<td style="text-align: center;">${reply.savedate}</td>
									</tr>
								</c:when>
								<c:otherwise><!-- 짝수번째 줄이면 하늘색, 아니면 흰색 -->
									<tr style="font-size: 9pt; background-color: white;">
										<td style="font-weight: bold; font-size: 8pt; text-align: center;">${reply.nickname}</td>
										<c:choose>
											<c:when test="${sessionScope.userId eq reply.id or sessionScope.userId eq 'Admin'}">
												<td colspan="5"> ${reply.content}</td> 
												<td align="center">
													<input type="hidden" value="${reply.rpnum}" name="rpnum">
													<input class="whitezone" type="button" value="삭제" id="deleteReply" onclick="location.href='delete_reply_board?rpnum=${reply.rpnum}&num=${select_Tip_board.num}'" >
													<input class="whitezone" type="button" value="수정" onclick="window.open('modify_reply_view?&num=${select_Tip_board.num}&rpnum=${reply.rpnum}', '댓글 수정', 'width=470px, height=250px')">
												
												</td>
											</c:when>
											<c:otherwise>
												<td colspan="6"> ${reply.content}</td>
											</c:otherwise>
										</c:choose>
										<td style="text-align: center;">${reply.savedate}</td>
									</tr>
								</c:otherwise>
						</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
					<!-- 여기까지가 등록된 댓글 표시하는 곳 -->
					
				<!-- 댓글 입력 공간 -->	
					<tr style="height: 40px;">
					<td style="font-size: 9pt; font-weight:bold; text-align: center; background-color: #0064CD; color: white;">
					${sessionScope.userNickName} 
					<input type="hidden" name="num" value="${select_Tip_board.num}">
					</td>
					
					<td colspan="6" style="align-content: center; padding: 5px; background-color: #0064CD;">
					<textarea class="textbox" rows="3" cols="70" name="reply_board"></textarea>
					</td>
					<td style="background-color: #0064CD;"><input class="mainButton" type="submit" value="등  록" style="width: 55px; height: 50px;'"></td>
				<!-- 아래부터 댓글 입력 공간 -->
				</tr>
			</table>
		</div>	
	</form>
<br><br>
<footer>
	<c:import url="/default/mainBottom"/>
</footer>
</body>
</html>