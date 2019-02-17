<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8" />
	<c:choose>
		<c:when test="${loginId != null}">
			<script>
				alert('비밀번호를 다시 확인해주세요.');
				history.back();
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert('로그인 후 이용해주세요.');
				location.href='${pageContext.request.contextPath}/member/login';
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>