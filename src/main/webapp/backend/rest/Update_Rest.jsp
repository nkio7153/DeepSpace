<%@ page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@ page import="com.depthspace.restaurant.service.RestService"%>
<%@ page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
<title>餐廳修改</title>
</head>
<body>
	<h3><b>餐廳資料修改</b></h3>
	<br>
	<form action="${pageContext.request.contextPath}/Rest/Rest.do" method="post">
		<p><label>餐廳編號：</label>
		<input type="text" name="restId" value=${rest.restId} readonly><br>
		<p><label>餐廳名稱：</label>
		<input type="text" name="restName" value=${rest.restName}><br>
		<p><label>餐廳電話：</label>
		<input type="text" name="restTel" value=${rest.restTel}><br>
		<p><label>餐廳地址：</label>
		<input type="text" name="restAddress" value=${rest.restAddress}><br>
		<p><label>餐廳類型：</label>
		<input type="text" name="restType" value=${rest.restType}><br>
		<p><label>營業時間：</label>
		<input type="text" name="restOpen" value=${rest.restOpen}><br>
		<p><label>餐廳上下架 (0下架/1上架)：</label>
		<select id="restStatus" name="restStatus">
	        <option value=0>0</option>
	        <option value=1>1</option>
	   	</select>
	   	<p><label>可預約組數：</label>
		<input type="number" name="bookingLimit" value=${rest.bookingLimit}><br>
		<p><label>管理員ID：</label>
		<input type="number" name="adminId" pattern="[1-5]" value=${rest.adminId}><br>
		<p><input type="submit" value="修改"></p>
		<input type="hidden" name="action" value="update">
   	</form>
		
		
	<br>
	<a href="${pageContext.request.contextPath}/backend/Rest/into.jsp">回首頁</a>
	
	<script>
    	// 將下拉框的預設選項設定為第二個選項（索引為1）
    	document.getElementById("restStatus").selectedIndex = ${rest.restStatus};
	</script>	
</body>
</html>