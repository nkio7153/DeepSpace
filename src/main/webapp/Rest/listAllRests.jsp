<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Rest/css/main.css">
<title>餐廳列表</title>
	<style>
	img {
	    width: auto;
	    height: auto;
	}
	</style>
</head>
<body>
<!-- 	<h1>餐廳列表</h1> -->
	<table style="width:100%; text-align:center;">
		<tr>
			<th>餐廳圖片</th>
			<th>餐廳編號</th>
			<th>餐廳名稱</th>
			<th>餐廳電話</th>
			<th>餐廳地址</th>
			<th>餐廳類型</th>
			<th>營業時間</th>
			<th>上/下架</th>
			<th>預設可訂位組數</th>
			<th>管理員ID</th>
			
		</tr>
		<c:forEach var="rest" items="${restList}">
			<tr>
				<td><img src="images/r_${rest.restId}.jpg" onerror="this.src='images/404.jpg'"></td>
				<td>${rest.restId}</td>
				<td>${rest.restName}</td>
				<td>${rest.restTel}</td>
				<td>${rest.restAddress}</td>
				<td>${rest.restType}</td>
				<td>${rest.restOpen}</td>
				<c:choose>
				    <c:when test="${rest.restStatus == 0}">
				        <td>下架</td>
				    </c:when>
				    <c:when test="${rest.restStatus == 1}">
				        <td>上架</td>
				    </c:when>
				</c:choose>
				<td>${rest.bookingLimit}組</td>
				<td>${rest.adminId}</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/Rest/Rest.do">
						<input type="submit" value="修改">
						<input type="hidden" name="restId" value="${rest.restId}">
						<input type="hidden" name="action" value="getId_for_update">
					</form>
				</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/Rest/Rest.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="restId" value="${rest.restId}">
						<input type="hidden" name="action" value="delete">
					</form>
				</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/Rest/Rest.do">
						<input type="submit" value="顯示會員訂位">
						<input type="hidden" name="restId" value="${rest.restId}">
						<input type="hidden" name="action" value="getMembooking">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<a href="${pageContext.request.contextPath}/Rest/into.jsp">回首頁</a>
	
	<script src="${pageContext.request.contextPath}/Rest/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/Rest/js/rest.js"></script>
	
</body>
</html>