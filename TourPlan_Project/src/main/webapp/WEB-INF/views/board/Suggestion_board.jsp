<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<form action="search_Suggestion_board" method="post">
	<div align="center">
		<h1 class="text-stroke" style="text-align: center; color: white; font-family:verdana; font-size:50pt;" > Suggestion </h1>
		<div style="width:700px; padding-bottom:5px; padding-top:50px;" align="right">
			<input class = "mainButton" type="button" value="목록으로" style="width:75px;" onclick="location.href='Suggestion_board'">
			<input class ="mainButton" type="button" value="글작성" style="width:55px;" onclick="location.href='Write_Suggestion_board'">
		</div>
		<table style="width: 700px; border-radius: 5px; font-size: 9pt; font-family:돋움;">
			<tr style="background-color: #0064CD; color: white; border-radius: 5px; font-size:10pt;">
				<th style="width:50px; height:20px;">글 번호</th>
				<th style="width:90px;">항목</th>
				<th style="width:450px">제목</th>
				<th style="width:80px;">작성일자</th>
				<th style="width:60px">조회수</th>
			</tr>
				<c:choose>
				<c:when test="${lists.size() == 0}">
					<tr style="background-color: white; border-radius: 5px;">
					<td colspan="5" style="text-align: center; font-weight: bold;">현재 게시글이 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
			<!-- 게시글 색깔 구분 -->
			<c:set var="cnt" value="1"/>
			<c:forEach items="${lists}" var="dto">
			<c:set var="cnt" value="${cnt+1 }" />
				<c:choose>
					<c:when test="${cnt % 2 eq 0 }" >
						<tr style="background-color:white; border-radius:5px;">
							<td>${dto.num }</td>
							<td>${dto.subject }</td>
							
							<!-- admin이거나 게시글 쓴 사람만 글 볼 수 있음 -->
							<td>
								<c:choose>
									<c:when test="${(userId eq dto.id) || (userId eq 'admin') }">
										<a href="Content_Suggestion_board?num=${dto.num }">
											${dto.title }
										</a>	
								</c:when>
								<c:otherwise>
									${dto.title }
								</c:otherwise>
								</c:choose>
							</td>
							<td>${dto.savedate }</td>
							<td>${dto.hit }</td>
						</tr>
					</c:when>
					<c:otherwise>
					<tr style="background-color:#ECEBFF; border-radius:5px;">
						<td>${dto.num }</td>
						<td>${dto.subject }</td>
						<td>
							<c:choose>
							<c:when test="${(userId eq dto.id) || (userId eq 'admin') }">
								<a href="Content_Suggestion_board?num=${dto.num }">
								${dto.title }
								</a>	
							</c:when>
							<c:otherwise>
								${dto.title }
							</c:otherwise>
							</c:choose>
						</td>
						<td>${dto.savedate }</td>
						<td>${dto.hit }</td>
					</tr>
				</c:otherwise>
				</c:choose>
				</c:forEach>
			</c:otherwise>
			</c:choose>
			
			
			<!-- 검색(항목) -->
			<tr align="center" style="background-color : #0064CD; border-radius : 5px ">
				<td>
					<select id = "indexOfSearchSuggestionBoard" name="indexOfSearchSuggestionBoard">
						<option value="num">글 번호
						<option value="subject">항목
						<option value="title">제목
						<option value="savedate">등록날짜
					</select>
				</td>
				<!-- 검색 -->
				<td colspan="3"><input class="textbox" type="text" name="searchOfSuggestionBoard" size="70"></td>
				<Td><input class="mainButton" type="submit" value="검색"></Td>
			</tr>
		
			<!-- 이전 버튼 -->
			<tr style="background-color: white; border-radius: 5px;">
			<td colspan="6" align="center">
				<c:choose>
					<c:when test="${start > 1 }">
					<!-- 페이지가 1보다 크면 이전 버튼 클릭 시 start-1로 start 보냄 /  -->
						<button class="mainButton" type="button" onclick="location.href='Suggestion_board?start=${start-1}'
						"style="color: black; text-decoration: none;">이전</button>
					</c:when>
					<c:otherwise>
					<!-- start가 1보다 작으면 이전버튼 사용 할 수 없음 -->
						<button class="mainButton" type="button" disabled>이전</button>
					</c:otherwise>
				</c:choose>
			
			<!-- 페이지 번호 & totalPage -->
			<!-- totalPage(총 게시글 수)가 보여지고 싶은 게시글 수(term)보다 작으면 totalPage(총 페이지 수)는 1로 지정-->
			<!-- totalPage(총 게시글 수)가 보여지고 싶은 게시글 수(term)보다 같으면 totalPage(총 페이지 수)는 나눈 몫으로 지정-->
			<!-- totalPage(총 게시글 수)가 보여지고 싶은 게시글 수(term)보다 크면 totalPage(총 페이지 수)는 나눈 몫+1로 지정-->
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
				
				<!-- 1 ~ totalPage(총 페이지 수) -->
				<!-- 현재 페이지랑 start가 같으면 글쓰크기 크게 -->
				<!-- 그렇지 않은 페이지들은  효과 없음 -->
				<!-- 다음 버튼 disabled ??? -->
				<c:forEach begin="1" end="${totalPage}" step="1" var="cnt">
					<c:choose>
						<c:when test="${cnt eq start}">
							<a href="Suggeston_board?start=${cnt}"style="color: black; font-size:10pt;">[${cnt}]</a>
						</c:when>
						<c:otherwise>
							<a href="Suggestion_board?start=${cnt}"style="color: black; text-decoration: none;">[${cnt}]</a>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${cnt < totalPage and (cnt+1) > totalPage }">
						<c:set var="totalPage" value="${cnt}"/>
					</c:if>
				</c:forEach>
				
				<!-- 다음버튼 나오는거 -->
				<c:choose>
					<c:when test="${start < totalPage}">
					<!-- 위에 지정한 totalpage(총 페이지 수)보다 start가 작으면 start+1로 start보냄 -->
						<button class="mainButton" type="button" onclick="location.href='Suggestion_board?start=${start+1}'
						"style="color: black; text-decoration: none;">다음</button>	
					</c:when>
					<c:otherwise>
					<!-- 작지 않으면 다음 버튼사용 할 수 없음 -->
						<button class="mainButton" type="button" disabled>다음</button>
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