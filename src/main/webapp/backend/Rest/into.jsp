<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../static/css/main.css">
<title>餐廳 Demo</title>
</head>
<body>
	<h1>餐廳</h1>
	<a href="${pageContext.request.contextPath}/Rest/Rest.do?action=getAll">查詢所有餐廳</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/backend/Rest/listCompositeQuery.jsp">查詢餐廳</a>

</body>
</html>