<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:choose>
			<c:when test="${UserID == null }">
				<div align="left" style="padding-left:30px">
					<h1>로그인 페이지 입니다.</h1>
				</div>
				<div align="right" style="padding-right:10px">
					<form action="CheckLogin" method="post">
						<table>
							<tr>
								<td>
									<input type="text" name="id" id="id" placeholder="아이디">
								</td>
								<td rowspan="2" >
									<input type="submit" value="로그인" style="width:60px; height:50px;">
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="pwd" id="pwd" placeholder="비밀번호">
								</td>
							</tr>
							<tr>
								<td><a href="NO_QA_board">Q&A 게시판</a> </td>
								<td><a href="No_Suggestion_board">건의 게시판</a></td>
							</tr>
						</table>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<a href="QA_board">Q&A 게시판</a> &ensp;
				<a href="Suggestion_board">건의 게시판</a>&ensp;
				<a href="logout">로그 아웃</a> &ensp;
		</c:otherwise>
		</c:choose>
</body>
</html>