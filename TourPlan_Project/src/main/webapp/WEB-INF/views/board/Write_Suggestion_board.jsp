<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    	font-size: 10pt;
		
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
    	height:20pt;
	}
</style>
</head>
<body>
<c:import url="/default/mainHeader" />
	<div align="center">
			<h1  style="text-align: center; color: #0064CD; font-family:verdana; font-size:30px;" >Suggestion </h1>
		<form action="ChkWrite_Suggestion_board" method="post" enctype="multipart/form-data" >
			<table style="width:600px; border-radius:5px;">
			<tr>
				<td colspan="4" align="right">
					<input  class="mainButton" type="submit" value="저장"> &nbsp;
					<input  class="mainButton" type="button" value="목록으로" onclick="location.href='Suggestion_board'">
				</td>
			</tr>
			<tr>
				<td class="font">항목</td>
				<td>
					<select name="subject">
			             <option value="신고">신고</option>
			             <option value="건의">건의</option>
				 	</select>
				</td>
			
				<td class="font"  width="100px;" >제목</td>
				<td>
					<input type="text" name="title" size="60">
				</td>
		
			<tr>
				<td class="font"  width="70px;" >첨부파일</td>
				<td colspan="3"><input multiple="multiple" type="file" name="image"></td>
			</tr>
			<tr>
				<td class="font">내용</td>
				<td colspan="3">
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