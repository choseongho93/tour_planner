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
		<c:when test="${val == 1}">
			<script type="text/javascript">
				alert("비밀번호 변경 완료되었습니다.")
				location.href="${pageContext.request.contextPath}/member/main";
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("비밀번호 변경 실패하였습니다.")
				history.back();
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>