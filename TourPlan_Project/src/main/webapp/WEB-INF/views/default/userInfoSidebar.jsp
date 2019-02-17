<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<style>
.sidenav {
  width: 130px;
  position: fixed;
  z-index: 1;
  top: 20px;
  left: 10px;
  background: #eee;
  overflow-x: hidden;
  padding: 8px 0;
  top: 65px;
}

.sidenav a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 14px;
  color: #2196F3;
  display: block;
}

.sidenav a:hover {
  color: #064579;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>
</head>
<body>
	<div class="sidenav">
		<a href="${pageContext.request.contextPath}/member/userInfo">내정보 변경</a>
		<a href="${pageContext.request.contextPath}/member/userPwdChange">비밀번호 변경</a>
		<hr>
		<a href="${pageContext.request.contextPath}/member/boardCheck?boardNum=1">Q&amp;A 게시판</a>
		<a href="${pageContext.request.contextPath}/member/boardCheck?boardNum=2">건의 게시판</a>
		<a href="${pageContext.request.contextPath}/member/boardCheck?boardNum=3">팁 게시판</a>
		<a href="${pageContext.request.contextPath}/member/boardCheck?boardNum=4">? 게시판</a>
		<a href="${pageContext.request.contextPath}/member/boardCheck?boardNum=5">? 게시판</a>
		<a href="${pageContext.request.contextPath}/member/boardCheck?boardNum=6">? 게시판</a>
	</div>
</body>
</html>
