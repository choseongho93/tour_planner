<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
		td{
		border-radius: 5px;
	}
	
	button{
		background-color: #0064CD;
		border-radius: 5px;
		color: white;
	}
	.font {
		font-weight: bold; 
		background: #0064CD; 
		color:white;
		font-family: 휴먼모음T;
		text-align: center;
    	text-decoration: none;
    	font-size:11pt;
		
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
</style>
</head>
<body>
<c:import url="/default/mainHeader" />
	<div align = "center">
		<h1  style="text-align: center; color: #0064CD; font-family:verdana; font-size:30px;" >Q & A </h1>
		<form action="Chk_Write_QA_board" method="post">
			<table  style="width:600px; border-radius:5px;">
				<Tr >
					<td colspan="2" align="right">
						<input class="mainButton" type="submit" value="저장">&nbsp;&nbsp;
						<input class="mainButton" type="button" value="목록으로" onclick="location.href='QA_board'">
						</Td>
				</Tr>
				<tr>
					 <td class="font" width="100px;">제목</td>
					 <td ><input type="text" name="title" size="80"></td>
				</tr>
				<tr>
					<td class="font">내용</td>
					<td>
						<textarea name="content" rows="10" style="width:99%;"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<c:import url="/default/mainBottom" />	
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>