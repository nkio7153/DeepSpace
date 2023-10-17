<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>餐廳列表</title>
</head>
<body>
	<h1>餐廳列表</h1>
	<table style="width:50%; text-align:center;">
		<tr>
			<th>餐廳編號</th>
			<th>餐廳名稱</th>
			<th>餐廳電話</th>
			<th>餐廳地址</th>
			<th>營業時間</th>
		</tr>
		<c:forEach var="rest" items="${restList}">
			<tr>
				<td>${rest.restId}</td>
				<td>${rest.restName}</td>
				<td>${rest.restTel}</td>
				<td>${rest.restAddress}</td>
				<td>${rest.restOpen}</td>
				<td>
					<div>
						<input type="submit" value="修改">
						<input type="submit" value="刪除">
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<a href="${pageContext.request.contextPath}/Rest/index.jsp">回首頁</a>	
</body>
</html>