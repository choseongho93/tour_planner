<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QA 게시판</title>
<style>
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
	    td {
		border-radius: 5px;
	}
	table {
		border-radius: 5px;
	}
</style>
</head>
<body>
<c:import url="/default/mainHeader" />
	<form action="Save_modify_QA_board" method="post" >
		<div align="center">
			<h1 style="text-align: center; color: #0064CD; font-family:verdana;">QA Board</h1>
			<table  style="width: 600px;">
				<tr>
					<td colspan="2" align="right"><input class="mainButton" type="button" value="목록으로" onclick="location.href='QA_board'">
						<input class="mainButton"  type="submit" value="수정">
						<input type="hidden" name="num" value="${lists.num}">
					</td>
				</tr>
				<tr style="text-align: center;">
					
					<td style="font-weight: bold; color: white; background: #0064CD; width:99%; height:15pt;">제    목</td>
					<td style="width:400px;"><input type="text" name="title" size="58" value="${lists.title}"></td>
				</tr>				
				<tr>
					<td colspan="4" align="right">
					<textarea rows="20" cols="70" name="content" wrap="hard" style="width:99%;">${lists.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						
					</td>
				</tr>
			</table>
		</div>	
	</form>
<br><br>
<c:import url="/default/mainBottom" />	
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>