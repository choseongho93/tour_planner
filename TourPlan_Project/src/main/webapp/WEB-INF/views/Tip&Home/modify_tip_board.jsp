<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팁 게시판</title>

<style type="text/css">
	body{
 		font-family: 휴먼모음T;
	}

	td{
		border-radius: 5px;
		font-family: 휴먼모음T;
	}
	
	button{
		background-color: #0064CD;
		border-radius: 5px;
		color: white;
	}
     
     .text-stroke {
   		 text-shadow:
    	-1px -1px 0 #000,
    	1px -1px 0 #000,
   	 	-1px 1px 0 #000,
   		1px 1px 0 #000;  
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
		background-color: #ECEBFF;
		color: #0064CD;
		border: none;
		text-align: center;
    	text-decoration: none;
   		display: inline-block;
   		border-radius: 5px;
    	font-family: 휴먼모음T;
    	cursor: pointer;
	}
	
	.textbox {
 		background-color: white;
		color: black;
		border: none;
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
	<form action="save_modi_tip_board" method="post" enctype="multipart/form-data">
		<div align="center">
			<h1 class="text-stroke" style="text-align: center; color: white; font-family:Broadway;"> -Modify - </h1>
			<table border="1" style="width: 600px;">
				<tr>
					<td colspan="4" align="right"><input class="mainButton" type="button" value="목록으로" onclick="location.href='tip'"></td>
				</tr>
				<tr style="text-align: center;">
					<td style="font-weight: bold; background: #0064CD; width: 70px; color: white;">분    류</td> 
					<td style="width: 70px;">
						<select id="indexOfModiTipBoard" name="indexOfModiTipBoard" style="text-align: center;">
							<option value="맛집 정보">맛집 정보
							<option value="선물 정보">선물 정보
							<option value="장소 정보">장소 정보
							<option value="날씨 정보">날씨 정보
							<option value="기   타">기   타
						</select>
					</td>
					<td style="font-weight: bold;  background: #0064CD; width: 70px; color: white;">제    목</td>
					<td style="width: 350px;"><input class="textbox" type="text" name="ModiTipBoardTitle" size="50" value="${select_Tip_board.title}"></td>
				</tr>				
				<tr>
					<td colspan="4" align="right">
					<textarea class="textbox" rows="20" cols="82" name="modiBoardContent" wrap="hard">${select_Tip_board.content}</textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="4">
						<c:choose>
						<c:when test="${imgNum == 1}">
							<input type="hidden" name="hiddenFileName1" value="${select_Tip_board.imagename1}">
							<input type="text" style="width: 600px;" name="attatchment1" value="${select_Tip_board.imagename1}">
						</c:when>
						<c:when test="${imgNum == 2}">
							<input type="hidden" name="hiddenFileName1" value="${select_Tip_board.imagename1}">
							<input type="hidden" name="hiddenFileName2" value="${select_Tip_board.imagename2}">
							<input type="text" style="width: 600px;" name="attatchment1" value="${select_Tip_board.imagename1}">
							<input type="text" style="width: 600px;" name="attatchment2" value="${select_Tip_board.imagename2}">
						</c:when>
					</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right"><input multiple="multiple" type="file" name="file" placeholder="이미지 추가" style="background-color: white; width: 600px;"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input class="mainButton" type="submit" value="수정완료">
						<input type="hidden" name="boardnumber" value="${select_Tip_board.num}">
					</td>
				</tr>
			</table>
		</div>	
	</form>
<br><br>
<footer>
	<c:import url="/default/mainBottom"/>
</footer>
</body>
</html>