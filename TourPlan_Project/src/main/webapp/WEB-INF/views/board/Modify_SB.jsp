<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>건의 게시판</title>
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
	<form action="save_modi_Suggestion_board" method="post" enctype="multipart/form-data">
		<div align="center">
			<h1 style="text-align: center; color: #0064CD; font-family:verdana;">Suggestion Board</h1>
			<table style="width: 600px;">
				<tr>
					<td colspan="4" align="right"><input class="mainButton" type="button" value="목록으로" onclick="location.href='Suggestion_board'">
					<input class="mainButton" type="submit" value="수정">
					<input type="hidden" name="num" value="${lists.num}">
					</td>
				</tr>
				<tr style="text-align: center;">
					<td style=" color:white; font-weight: bold; background: #0064CD; width: 100%;">분    류</td> 
					<td>
						<select id="indexOfModiSuggestionBoard" name="indexOfModiSuggestionBoard" style="text-align: center; width: 100%;">
							<option value="신고">신고
							<option value="건의">건의
						</select>
					</td>
					<td style=" color:white;  font-weight: bold;  background: #0064CD; width: 100%; ">제    목</td>
					<td style="width:400px;"><input type="text" name="ModiSuggestionBoardTitle" size="58" value="${lists.title}"></td>
				</tr>				
				<tr>
					<td colspan="4" align="right">
					<textarea rows="30" cols="30" name="modiSuggestionBoardContent" wrap="hard" style="width:99%; ">${lists.content}</textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="4">
						<c:choose>
						<c:when test="${imgNum == 1}">
							<input type="hidden" name="hiddenFileName1" value="${lists.imagename1}">
							<input type="text" style="width: 600px;" name="attatchment1" value="${lists.imagename1}">
						</c:when>
						<c:when test="${imgNum == 2}">
							<input type="hidden" name="hiddenFileName1" value="${lists.imagename1}">
							<input type="hidden" name="hiddenFileName2" value="${lists.imagename2}">
							<input type="text" style="width: 600px;" name="attatchment1" value="${lists.imagename1}">
							<input type="text" style="width: 600px;" name="attatchment2" value="${lists.imagename2}">
						</c:when>
					</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right"><input multiple="multiple" type="file" name="file" placeholder="이미지 추가" style="background-color: white; width: 600px;"></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
					
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