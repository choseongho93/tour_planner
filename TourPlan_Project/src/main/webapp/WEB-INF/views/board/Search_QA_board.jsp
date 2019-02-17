<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
      body {
   		-webkit-background-size: cover;
    	-moz-background-size: cover;
    	-o-background-size: cover;
    	background-size: cover;
      }
      
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
<c:import url="/default/mainHeader" />
<form action="search_QA_board" method="post">
	<div align="center" >
	<h1 class="text-stroke" style="text-align: center; color: white; font-family:verdana;">${subject} : ${word} Search Result</h1>
	<div style="width:700px;" align="right">
		<input class="mainButton" type="button" value="목록으로" onclick="location.href='QA_board'">
		<input class="mainButton" type="button" value="글작성" onclick="location.href='Write_QA_board'">
	</div>
	
	<table style="width: 700px; border-radius: 5px; font-size: 9pt; font-family:돋움;">
				<tr style="background-color: #0064CD; color: white; border-radius: 5px; font-size:10pt;">
					<th style="width:50px; height: 20px;">글 번호</th>
					<th  style="width:450px;">제목</th>
					<th style="width:90px;">닉네임</th>
					<th style="width:80px;">작성일자</th>
					<th style="width:60px;">조회수</th>
				</tr>
				<!-- 게시글 색깔 구분 -->
				<c:set var="cnt" value="1"/>
				<c:forEach items="${lists }" var="dto">
					<c:set var="cnt" value="${cnt+1 }" /> 
					<c:choose>
						<c:when test="${ cnt%2 eq 0 }" >
							<Tr style="background-color:white; border-radius:5px;">
								<td>${dto.num}</td>
								<!-- 제목을 누르면 내용보여주기-num으로 구분 -->
								<td><a href="Content_QA_board?num=${dto.num }">${dto.title}</a></td>
								<Td>${dto.nickName}</Td>
								<td>${dto.savedate}</td>
								<td>${dto.hit }</td>
							</Tr>
						</c:when>
						<c:otherwise>
							<tr style="background-color:#ECEBFF; bofder-radius:5px;">
								<td>${dto.num}</td>
								<!-- 제목을 누르면 내용보여주기-num으로 구분 -->
								<td><a href="Content_QA_board?num=${dto.num }">${dto.title}</a></td>
								<Td>${dto.nickName}</Td>
								<td>${dto.savedate}</td>
								<td>${dto.hit }</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		<tr align="center" style="background-color: #0064CD; border-radius: 5px;">
			<td>  
				<!-- 검색항목 -->
				<select id="indexOfSearchQABoard" name="indexOfSearchQABoard"> 
						<option value="num">글 번호
						<option value="title">제목
						<option value="nickName">닉네임
						<option value="savedate">등록날짜
				</select>
			</td>
			<!-- 검색 -->
			<td colspan="3"><input class="textbox" type="text" name="searchOfQABoard" size="70"> </td>
			<td><input class="mainButton" type="submit" value="검색"></td>	
		</tr>
		
		<tr style="background-color: white; border-radius: 5px;">
			<td colspan="6" align="center">
				<c:choose>
					<c:when test="${start > 1 }">
						<button type="button" onclick="location.href='search_QA_board?start=${start-1}&subject=${subject}&word=${word}'">이전</button>
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
							<a href="search_QA_board?start=${cnt}&subject=${subject}&word=${word}"style="color: black; font-weight:bold; font-size:10pt;">[${cnt}]</a>
						</c:when>
						<c:otherwise>
							<a href="search_QA_board?start=${cnt}&subject=${subject}&word=${word}"style="color: black; text-decoration: none;">[${cnt}]</a>
						</c:otherwise>
					</c:choose>
					<c:if test="${cnt < totalPage and (cnt+1) > totalPage }">
						<c:set var="totalPage" value="${cnt}"/>
					</c:if>
				</c:forEach>
				
				<!-- 다음버튼 나오는거 -->
				<c:choose>
					<c:when test="${start < totalPage}">
						<button class="mainButton" type="button" onclick="location.href='search_QA_board?start=${start+1}&subject=${subject}&word=${word}'">다음</button>	
					</c:when>
					<c:otherwise>
						<button type="button" disabled>다음</button>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
 	</table>
 	</div>
</form>
<c:import url="/default/mainBottom" />
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>	
</body>
</html>