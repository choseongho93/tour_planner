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

<!-- 게시글 검색으로 보내기 -->
<form action="${pageContext.request.contextPath}/search_plan_board" method="post">
	<div align="center">
		<h1  class="text-stroke" style="text-align: center; color: white; font-family:Broadway;" >여행 계획</h1>
		<div style="width:700px;" align="right">
			<input class="mainButton" type="button" value="목록" style="width: 45px;" onclick="location.href='${pageContext.request.contextPath}/plan/plan_board'">
			<input class="mainButton" type="button" value="웹" style="width: 45px;" onclick="webSwitch(this)">
		</div>
			<table style="width: 700px; border-radius: 5px; font-size: 9pt; font-family:돋움;">
				<tr style="background-color: #0064CD; color: white; border-radius: 5px; font-size:10pt;">
					<th style="width:50px; height: 20px;">글 번호</th>
					<th  style="width:450px;">제목</th>
					<th style="width:90px;">닉네임</th>
					<th style="width:80px;">작성일자</th>
					<th style="width:60px;">조회수</th>
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
				<c:forEach items="${lists }" var="dto">
					<c:set var="cnt" value="${cnt+1 }" /> 
					<c:choose>
						<c:when test="${ cnt%2 eq 0 }" >
							<Tr style="background-color:white; border-radius:5px;">
								<td>${dto.num}</td>
								<!-- 제목을 누르면 내용보여주기-num으로 구분 -->
								<td><a href="${pageContext.request.contextPath}/plan/planView?num=${dto.num }">${dto.title}</a></td>
								<Td>${dto.nickname}</Td>
								<td>${dto.savedate}</td>
								<td>${dto.hit }</td>
							</Tr>
						</c:when>
						<c:otherwise>
							<tr style="background-color:#ECEBFF; bofder-radius:5px;">
								<td>${dto.num}</td>
								<!-- 제목을 누르면 내용보여주기-num으로 구분 -->
								<td><a href="${pageContext.request.contextPath}/plan/planView?num=${dto.num }">${dto.title}</a></td>
								<Td>${dto.nickname}</Td>
								<td>${dto.savedate}</td>
								<td>${dto.hit }</td>
							</tr>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	
	<!-- 검색항목 -->
		<tr align="center" style="background-color: #0064CD; border-radius: 5px;">
			<td>  
				<select id="indexOfSearchQABoard" name="subject"> 
						<option value="num">글 번호
						<option value="title">제목
						<option value="nickname">닉네임
						<option value="savedate">등록날짜
				</select>
			</td>
	<!-- 검색 -->
			<td colspan="3"><input class="textbox" type="text" name="word" size="70"> </td>
			<td><input class="mainButton" type="submit" value="검색"></td>	
		</tr>
		
	<!-- 이전 버튼 -->
			<tr style="background-color: white; border-radius: 5px;">
			<td colspan="6" align="center">
				<c:choose>
					<c:when test="${start > 1 }">
						<c:choose>
							<c:when test="${word !=null || word != '' }">
								<button class="mainButton" type="button" onclick="location.href='${pageContext.request.contextPath}/search_plan_board?start=${start-1}word=${word}&subject=${subject}'
								"style="color: black; text-decoration: none;">이전</button>
							</c:when>
							<c:otherwise>
								<button class="mainButton" type="button" onclick="location.href='#?start=${start-1}'
								"style="color: black; text-decoration: none;">이전</button>	
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<button class="mainButton" type="button" disabled>이전</button>
					</c:otherwise>
				</c:choose>
		
		<!-- 페이지 번호-->
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
							<c:choose>
								<c:when test="${word !=null || word != '' }">
									<a href="${pageContext.request.contextPath}/search_plan_board?start=${cnt}&word=${word}&subject=${subject}"style="color: black; font-size:10pt;">[${cnt}]</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/plan/plan_board?start=${cnt}"style="color: black; font-size:10pt;">[${cnt}]</a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${word !=null || word != '' }">
									<a href="${pageContext.request.contextPath}/search_plan_board?start=${cnt}&word=${word}&subject=${subject}"style="color: black; text-decoration: none;">[${cnt}]</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/plan/plan_board?start=${cnt}"style="color: black; text-decoration: none;">[${cnt}]</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<c:if test="${cnt < totalPage and (cnt+1) > totalPage }">
						<c:set var="totalPage" value="${cnt}"/>
					</c:if>
				</c:forEach>	

				
		<!-- 다음버튼-->
				<c:choose>
					<c:when test="${start < totalPage}">
						<c:choose>
							<c:when test="${word !=null || word != '' }">
								<button class="mainButton" type="button" onclick="location.href='${pageContext.request.contextPath}/search_plan_board?start=${start+1}word=${word}&subject=${subject}'
								"style="color: black; text-decoration: none;">다음</button>
							</c:when>
							<c:otherwise>
								<button class="mainButton" type="button" onclick="location.href='#?start=${start+1}'
								"style="color: black; text-decoration: none;">다음</button>	
							</c:otherwise>
						</c:choose>
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
	<script>
//web이면 0, Mobile 이면 1
var webMob = 0;
function webSwitch(data){
	if(webMob == 0){
		let list = $('td > a')
		console.log(list);
		for(let i=0; i<list.length; i++){
			console.log(list[i].href);
			let st = list[i].href;
			st = st.replace('planView?', 'mPlanView?');
			list[i].href = st;
		}
		data.value = "모바일";
		webMob = 1;
	}else{
		let list = $('td > a');
		for(let i=0; i<list.length; i++){
			console.log(list[i].href);
			let st = list[i].href;
			st = st.replace('mPlanView?', 'planView?');
			list[i].href = st;
		}
		data.value = "웹";
		webMob = 0;
	}
}
</script>


<c:import url="../default/mainBottom.jsp" />
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>