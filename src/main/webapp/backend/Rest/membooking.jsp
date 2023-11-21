<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
<title>餐廳列表</title>
	<style>
	</style>
</head>
<body>
	<h1>餐廳訂位列表</h1>
	<table style="width:100%; text-align:center;">
		<tr>
			<th>訂位編號</th>
			<th>餐廳編號</th>
			<th>會員編號</th>
			<th>報到狀態</th>
			<th>訂位時段</th>
			<th>訂位組數</th>
			<th>訂位日期</th>
		</tr>
		<c:forEach var="mb" items="${mbList}">
			<tr>
				<td>${mb.bookingId}</td>
				<td>${mb.restId}</td>
				<td>${mb.memId}</td>
				<c:choose>
				    <c:when test="${mb.checkStatus == 0}">
				        <td>未報到</td>
				    </c:when>
				    <c:when test="${mb.checkStatus == 1}">
				        <td>已報到</td>
				    </c:when>
				    <c:when test="${mb.checkStatus == 2}">
				        <td>已取消</td>
				    </c:when>
				</c:choose>
				<c:choose>
				    <c:when test="${mb.bookingTime == 0}">
				        <td>早</td>
				    </c:when>
				    <c:when test="${mb.bookingTime == 1}">
				        <td>中</td>
				    </c:when>
				    <c:when test="${mb.bookingTime == 2}">
				        <td>晚</td>
				    </c:when>
				</c:choose>
				<td>${mb.bookingNumber}</td>
				<td>${mb.bookingDate}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<a href="${pageContext.request.contextPath}/backend/Rest/into.jsp">回首頁</a>
	
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/rest.js"></script>
	
</body>
</html>