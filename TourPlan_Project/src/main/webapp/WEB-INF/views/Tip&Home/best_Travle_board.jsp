<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	.line{
		border-bottom:1px solid #FAED7D;	
	}
	td{
		text-align: center;
	}
</style>
</head>
<body style="text-align: center;">
<div align="center">
	<table style="width: 500px; height: 300px; font-size: 8pt; text-align: cente;" border="0">		
		<!-- 목차 -->
		<tr style="background-color:#FAED7D; text-align:center; height: 50px;" >
			<th style="width:25px;">순    위</th>	<th style="width:190px;">제    목</th> 
			<th style="width:80px;">닉 네 임</th> 	<th style="width:50px;">조 회 수</th>
		</tr>
		
		<!-- 순위별 게시글 -->
		<c:set var="rank" value="0"/><!-- 랭크 계산 -->
			<c:forEach items="${Top_Travle_list}" var="dto"><!-- 리턴되는 값만 넣어주세요. -->
				<c:set var="rank" value="${rank+1}"/>
				<tr style="background-color: white; text-align: center;">
				<!-- 링크 연결되는 곳에 주소 변경해주세요. -->
					<th class="line">${rank}</th> <th class="line"><a href="#?num=${dto.num}" style="text-decoration: none; color: black;">${dto.title}</a></th> 
					<th class="line">${dto.nickname}</th> <th class="line">${dto.hit}</th> 
				</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>