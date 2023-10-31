<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<jsp:include page="./indexpage/head.jsp" />
	<jsp:include page="./indexpage/header.jsp" />
	<jsp:include page="./indexpage/headpic.jsp" />
</head>
<body>

	<table style="width:100%; text-align:center;">
		<tr>
			<th>會員編號</th>
			<th>餐廳編號</th>
			
		</tr>
		<c:forEach var="rc" items="${rcList}">
			<tr>
				<td>${rc.memId}</td>
				<td>${rc.restId}</td>
		</c:forEach>
	</table>

	<jsp:include page="./indexpage/footer.jsp" />
</body>
</html>