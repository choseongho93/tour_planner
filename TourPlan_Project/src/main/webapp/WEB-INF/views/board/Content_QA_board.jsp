<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		background-color:#0064CD;
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
	}
	
	.categoryTd{
		width:80px; 
		background-color: white;
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
    </style>
</head>
<body>
<c:import url="/default/mainHeader" />
	<div align="center">
	<form action="Write_Reply_board" method="post">
			<input type="hidden" name="num"  value="${lists.num }">
			<input type="hidden" name="board"  value="QA_board">
			<table border="1" style="width:600px; font-family:돋움;">
				<c:choose>
					<c:when test="${(userId eq lists.id) || (userId eq 'admin') }">
						<tr align="right">
							<td colspan="6">
								<input class="mainButton" type="button" value="목록으로" onclick="location.href='QA_board'">
								<input class="mainButton" type="button" value="수정" onclick="location.href='modify_QA_board?num=${lists.num }'">
								<input class="mainButton" type="button" value="삭제" onclick="location.href='DeleteContent_QA_board?num=${lists.num }'">
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" align="right">
								<input class="mainButton" type="button" value="목록으로" onclick="location.href='QA_board'">
							</td>
						</tr>
					</c:otherwise>
				</c:choose>	
				<tr style="text-align:center; background-color:#0064CD; font-family:돋움;">
					<td style="background: #0064CD; width:50px; font-size:9pt; font-weight:bold; color:white;">NO.</td>
					<td style="width:60px; font-size:9pt; background-color:white;font-weight:bold;">${lists.num }</td>
					<td colspan="5" style="color:white;">${lists.title }</td>
				</tr>
				<tr style="text-align:center; font-size:9pt; font-weight:bold;">
					<td class="category">닉네임</td><td class="categoryTd">${lists.nickname }</td>
					<Td class="category">작성일자</Td><td class="categoryTd">${lists.savedate }</td>
					<td class="category">조회수 </td><td class="categoryTd" colspan="2">${lists.hit }</td>
				</tr>
				<tr style="text-align:center; font-size:9pt; font-weight:bold;"> <!-- 간격 -->
				</tr>
				<tr style="width:600px; height:300px;">
					<td colspan="7" style="background-color:white; text-align:center;">
						${lists.content }
					</td>
				</tr>
	
			<!-- 댓글 보여주기 & 색 구분 -->
			<c:set var="cnt" value="1" />
			<c:forEach items="${abc }" var="dto">
			<c:set var="cnt" value="${cnt+1 }" />
			<c:choose>
				<c:when test="${cnt % 2 eq 0 }">
					<tr style="font-size:9pt; background-color:#ECEBFF; height:20px; ">
						<td style="font-weight:bold; font-size:8pt; text-align:center;" width="100"> ${dto.nickname }</td>
						<Td style="font-weight:bold; font-size:9pt; text-align:center;" colspan="3">${dto.content}</Td>
							<c:choose>
                    		<c:when test="${userId eq dto.id || userId eq 'admin' }">
                    		<td width="100" align="center">
	                    		<div style="text-align:center;">
	               					<input class="whitezone01" type="button" value="수정" onclick="window.open('View_Modify_Reply_board?rpnum=${dto.rpnum }', '댓글 수정', 'width=470px, height=250px')">
	                      			<input class="whitezone01" type="button" value="삭제" onclick="location.href='Delete_Reply_board?rpnum=${dto.rpnum }&num=${dto.num}'">
	                    		</div>
	                    	</td>
	                    	<td style="font-size:8pt; text-align:center;">${dto.savedate }</td>
                		</c:when> 
                		<c:otherwise>
                		 	<td style="font-size:8pt; text-align:center;" colspan="2" >${dto.savedate }</td>
                		</c:otherwise>
						</c:choose>
					</tr>
				</c:when>
				<c:otherwise>
					<tr style="font-size:9pt; backgounr-color:white;  height:20px; ">
						<td style="font-weight:bold; font-size:8pt; text-align:center;" width="100"> ${dto.nickname }</td>
						<Td style="font-weight:bold; font-size:9pt; text-align:center; "colspan="3" >${dto.content}</Td>
							<c:choose>
                    		<c:when test="${userId eq dto.id || userId eq 'admin' }">
                    			<td width="100" align="center">
	                    		<div style="text-align:center;">
	               					<input class="whitezone" type="button" value="수정" onclick="window.open('View_Modify_Reply_board?rpnum=${dto.rpnum }', '댓글 수정', 'width=470px, height=250px')">
	                      			<input class="whitezone" type="button" value="삭제" onclick="location.href='Delete_Reply_board?rpnum=${dto.rpnum }&num=${dto.num}'">
	                    		</div>
	                    		</td>
	                    		<td style="font-size:8pt; text-align:center;">${dto.savedate }</td>
                		</c:when> 
                		<c:otherwise>
                			<td style="font-size:8pt; text-align:center;" colspan="2">${dto.savedate }</td>
                		</c:otherwise>
						</c:choose>	
					</tr>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			
			<!-- 댓글 작성 -->
				<Tr style="height:20px;">
					<td style="font-size:9pt; font-weight:bold; text-align:center; background-color:#0064CD; color:white;"width="100">${userNickName }</td>
					<td style="align-content:center; padding:5px; backgound-color:#0064CD;" colspan="4" width="400">
						<textarea name="content" rows="3" style="width:99%;"></textarea>
					</td>
					<td style="background-color:#0064CD;" colspan="1" width="100">
						<input class="mainButton" style="width:55px; height:20px; text-align:right;"  type="submit" value="등록">
					</td>
				</Tr>
			
			</table>
		</form>
	</div>
	<c:import url="/default/mainBottom" />	
</body>
</html>