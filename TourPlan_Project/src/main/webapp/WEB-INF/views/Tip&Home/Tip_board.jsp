<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팁 게시판</title>

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
		text-align: center;
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
	
    </style>

</head>
<body>
<header>
	<c:import url="/default/mainHeader"/>
</header>
<br><br>

<form action="search_Tip_board" method="post">
	<div align="center">
	<h1 class="text-stroke" style="text-align: center; color: white; font-family:Broadway;"> - TIP Board - </h1>
	<div style="width:650px;" align="right">
		<input class="mainButton" type="button" value="목록으로" style="width: 75px; height: 20px;" onclick="location.href='tip'">
		<input class="mainButton" type="button" value="글작성" style="width: 45px; height: 20px;" onclick="location.href='write_Tip_board'">
	</div>
	
	<table style="width: 650px; border-radius: 5px; font-size: 9pt; font-family:돋움; text-align: center;">
		<tr style="background-color: #0064CD; color: white; border-radius: 5px; font-size:10pt;">
			<th style="width:50px; height: 20px;">글 번호</th> <th style="width:70px;">목    차</th> 
			<th style="width:300px;">제    목</th> <th style="width:90px;">닉 네 임</th> 
			<th style="width:80px;">작 성 일</th> <th style="width:60px;">조 회 수</th>
		</tr>
		<c:choose>
			<c:when test="${list.size() == 0}">
			<tr style="background-color: white; border-radius: 5px;">
				<td colspan="6" style="text-align: center; font-weight: bold;">현재 게시글이 존재하지 않습니다.</td>
			</tr>
			</c:when>
			<c:otherwise>
				<c:set var="cnt" value="1"/>
				<c:forEach items="${list}" var="dto">
					<c:set var="cnt" value="${cnt+1}"/>
					<c:choose>
						<c:when test="${cnt%2 eq 0}">
							<tr style="background-color: white; border-radius: 5px;">
								<th>${dto.num}</th> <th>${dto.subject}</th> 
								<th><a href="select_Tip_board?num=${dto.num}" style="color: black; text-decoration: none;">${dto.title}</a></th> <th>${dto.nickname}</th> 
								<th>${dto.savedate}</th> <th>${dto.hit}</th> 
							</tr>
						</c:when>
						<c:otherwise>
							<tr style="background-color: #ECEBFF; border-radius: 5px;">
								<th>${dto.num}</th> <th>${dto.subject}</th> 
								<th><a href="select_Tip_board?num=${dto.num}" style="color: black; text-decoration: none;">${dto.title}</a></th> <th>${dto.nickname}</th> 
								<th>${dto.savedate}</th> <th>${dto.hit}</th> 
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr align="center" style="background-color: #0064CD; border-radius: 5px;">
			<td>  
				<select id="indexOfSearchTipBoard" name="indexOfSearchTipBoard" style="color: white;">
						<option value="num">글 번호
						<option value="subject">목차
						<option value="title">제목
						<option value="nickname">닉네임
						<option value="savedate">등록날짜
				</select>
			</td>
			<td colspan="4"><input class="textbox" type="text" name="searchOfTipBoard" size="70"> </td>
			<td><input class="mainButton" type="submit" value="검색"></td>	
		</tr>
		<tr style="background-color: white; border-radius: 5px;">
			<td colspan="6" align="center">
				<c:choose>
					<c:when test="${start > 1 }">
						<button class="mainButton" type="button" onclick="location.href='tip?start=${start-1}'"style="color: black; text-decoration: none;">이전</button>
					</c:when>
					<c:otherwise>
						<button class="mainButton" type="button" disabled>이전</button>
					</c:otherwise>
				</c:choose>
				
				<!-- 페이지 번호 나타나는 것 -->
				<c:choose>
					<c:when test="${totalPage <= term}">
						<c:set var="totalPage" value="1"/>
					</c:when>
					<c:when test="${totalPage%(term) eq 0}">  
						<c:set var="totalPage" value="${totalPage/term}"/>
					</c:when>
					<c:otherwise>
						<c:set var="totalPage"  value="${(totalPage/term)+1}"/>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="1" end="${totalPage}" step="1" var="cnt">
					<c:choose>
						<c:when test="${cnt eq start}">
							<a href="tip?start=${cnt}"style="color: black; font-size:10pt;">[${cnt}]</a>
						</c:when>
						<c:otherwise>
							<a href="tip?start=${cnt}"style="color: black; text-decoration: none;">[${cnt}]</a>
						</c:otherwise>
					</c:choose>
					<c:if test="${cnt < totalPage and (cnt+1) > totalPage }">
						<c:set var="totalPage" value="${cnt}"/>
					</c:if>
				</c:forEach>
				
				<!-- 다음버튼 나오는거 -->
				<c:choose>
					<c:when test="${start < totalPage}">
						<button class="mainButton" type="button" onclick="location.href='tip?start=${start+1}'"style="color: black; text-decoration: none;">다음</button>	
					</c:when>
					<c:otherwise>
						<button class="mainButton" type="button" disabled>다음</button>
					</c:otherwise>
				</c:choose>	
				<!-- ${start} / ${totalPage}-->
			</td>
		</tr>
 	</table>
 	</div>
</form>
<br><br>
<footer>
	<c:import url="/default/mainBottom"/>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</footer>
</body>
</html>