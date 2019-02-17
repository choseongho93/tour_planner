<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    	height:15pt;
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
		.whitezone01{
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
	.category{
		background: #0064CD; 
		width: 50px; 
		color: white;	
		text-align:center;
	}
	
	.categoryTd{
		width:80px; 
		background-color: white;
		text-align:center;
	}
    </style>
</head>
<body>
<c:import url="/default/mainHeader" />
	<div align="center">
		<form action="SU_Write_Reply_board" method="post">
			<input type="hidden" name="num" value="${lists.num }">
			<table border="1" style="width:600px; font-family:돋움;">
			<tr>
				<td colspan="6" align="right">
					<input class="mainButton" type="button" value="목록으로" onclick="location.href='Suggestion_board'">
					<input class="mainButton" type="button" value="수정" onclick="location.href='Modify_Suggestion_board?num=${lists.num }'">
					<input class="mainButton" type="button" value="삭제" onclick="location.href='DeleteContent_Suggestion_board?num=${lists.num }'">
				</td>
			</tr>
			<tr style="text-align:center; background-color:#0064CD; font-family:돋움;">
					<td style="background: #0064CD; width:50px; font-size:9pt; font-weight:bold; color:white;">NO.</td>
					<td style="width:60px; font-size:9pt; background-color:white;font-weight:bold;">${lists.num }</td>
					<td colspan="5" style="color:white;">${lists.title }</td>
				</tr>
				<tr style="text-aglin:center; font-size:9pt; font-weight:bold;">
					<td class="category">항목</td><td class="categoryTd" >${lists.subject }</td>
					<td class="category">작성일자</td><td class="categoryId" >${lists.savedate }</td>
					<td class="category">조회수 </td><td class="categoryId" >${lists.hit }</td>
				</tr>
				<tr style="width:600px; height:300px;">
					<td colspan="6" style="background-color:white; text-align:center;">
						${lists.content }
					
						<div>
	                    	<!-- 이미지 수에 맞춰서 이미지 출력하는 곳 -->
						<c:choose>
							<c:when test="${imgNum == 1}">
							<div align="center"><img src="suggestion_image?num=${lists.num }" alt="이미지 로드중 문제가 발생했습니다."></div>
							</c:when>
							<c:when test="${imgNum == 2}">
							<div align="center"><img src="suggestion_image?num=${lists.num }" alt="이미지 로드중 문제가 발생했습니다."></div><br>
							<div align="center"><img src="suggestion_image1?num=${lists.num }" alt="이미지 로드중 문제가 발생했습니다."></div>
							</c:when>
						</c:choose>
	               	 	</div>
					</td>
				</tr>
			
			<!-- 댓글 보여주기 & 색 구분 -->
			<c:set var="cnt" value="1"/>
			<c:forEach items="${abc }" var="dto">
			<c:set var="cnt" value="${cnt+1 }" />
			<c:choose>
				<c:when test="${cnt % 2 eq 0 }">
					<tr style="font-size:9pt; background-color:#ECEBFF;">
						<td style="font-weight:bold; font-size:8pt; text-align:center;  height:40px;" width="100" >${dto.id }</td>
						<td style="font-weight:bold; font-size:9pt; text-align:center;" colspan="3">${dto.content }</td>
						
						<c:choose>
						<c:when test="${userId eq 'admin' }">
							<td width="100" align="center">
								<div style="text-align:center;">
									<input class="whitezone01" type="button" value="수정" onclick="window.open('View_Modify_Reply_board?rpnum=${dto.rpnum }', '댓글 수정', 'width=470px, height=250px')">
	                        		<input class="whitezone01" type="button" value="삭제" onclick="location.href='SU_Delte_Reply_board?rpnum=${dto.rpnum }&num=${dto.num}'">
								</div>
							</td>
							<td style="font-size:8pt;">${dto.savedate }</td>	
							</c:when>
							<c:otherwise>
							<td style="font-size:8pt;" colspan="2">${dto.savedate }</td>	
							</c:otherwise>
							</c:choose>
					</tr>
				</c:when>
				<c:otherwise>
					<tr style="font-size:9pt; backgound-color:white; ">
						<td style="font-weight:bold; font-size:8pt; text-align:center; height:40px;" width="100">${dto.id }</td>
						<td style="font-weight:bold; font-size:9pt; text-align:center;" colspan="3">${dto.content }</td>
							
							<c:choose>
							<c:when test="${userId eq 'admin' }">
								<td width="100" align="center">
									<div style="text-align:center;">
										<input class="whitezone" type="button" value="수정" onclick="window.open('View_Modify_Reply_board?rpnum=${dto.rpnum }', '댓글 수정', 'width=470px, height=250px')">
	                        			<input class="whitezone" type="button" value="삭제" onclick="location.href='SU_Delte_Reply_board?rpnum=${dto.rpnum }&num=${dto.num}'">
									</div>
								</td>
								<td style="font-size:8pt;">${dto.savedate }</td>	
							</c:when>
							<c:otherwise>
								<td style="font-size:8pt;" colspan="2">${dto.savedate }</td>	
								</c:otherwise>
							</c:choose>
					</tr>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			
			<!-- 댓글 작성 -->
			<c:choose>
				<c:when test="${userId eq 'admin' }">
					<input type="hidden" name="num"  value="${lists.num }">
					<input type="hidden" name="board"  value="Suggestion_board">
						<Tr style="height:15px;">
							<td width="100" style="font-size:9pt; font-weight:bold; text-align:center; background-color:#0064CD; color:white;">${userId }</td>
							<td colspan="4" style="align-content:center; padding:5px; backgoround-color:#0064CD;">
								<textarea name="content" rows="3" style="width:99%;"></textarea>
							</td>
							<td  style = "background-color:#0064CD; "width="130">
								<input class="mainButton" style="width:55px; height:15px; text-align:center;" type="submit" value="등록">
							</td>
						</Tr>
				</c:when>
		</c:choose>
	</table>
</form>	
</div>
<c:import url="/default/mainBottom" />	
</body>
</html>