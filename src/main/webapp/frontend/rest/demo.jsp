<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<jsp:include page="../indexpage/head.jsp" />
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
	
	<table id="Restdata" style="width:100%; text-align:center;">
		<tr>
			<th>餐廳編號</th>
			<th>餐廳名稱</th>
			<th>餐廳電話</th>
			<th>餐廳地址</th>
			<th>餐廳類型</th>
			<th>營業時間</th>
			<th>上/下架</th>
			<th>預設可訂位組數</th>
			<th>管理員ID</th>
			<th>餐廳圖片</th>
		</tr>
		
	</table>




	<jsp:include page="../indexpage/footer.jsp" />
	
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/restdemo.js"></script>
</body>
</html>
