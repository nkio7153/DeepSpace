<%@ page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@ page import="com.depthspace.restaurant.service.RestService"%>
<%@ page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<% 
	RestService restService = new RestServiecImpl();
	List<RestVO> restList = restService.getAllRest();
	request.setAttribute("RestList", restList);
%>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Rest/css/main.css">
<title>餐廳查詢</title>
	<style>
		img {
		    width: 100%;
		    height: 100%;
		}
	</style>
</head>
<body>
	<h3><b>PK查詢：</b></h3>
	<form action="${pageContext.request.contextPath}/Rest/Rest.do" method="post">
		<label for="restId">選擇餐廳編號：</label>
    		<select id="restId" name="restId">
        		<c:forEach var="rest" items="${RestList}">
           			<option value="${rest.restId}">${rest.restId}</option>
       			</c:forEach>
    		</select>
    	<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
	<table style="width:50%; text-align:center;">
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
		</tr>
	</table>
	<br>
	<form action="${pageContext.request.contextPath}/Rest/Rest.do" method="post">
		<p><label>餐廳名稱：</label>
		<input type="text" name="restName"><br>
		<p><label>餐廳電話：</label>
		<input type="text" name="restTel"><br>
		<p><label>餐廳地址：</label>
		<input type="text" name="restAddress"><br>
		<p><label>餐廳類型：</label>
		<input type="text" name="restType"><br>
		<p><label>營業時間：</label>
		<input type="text" name="restOpen"><br>
		<p><label>餐廳上下架 (0下架/1上架)：</label>
		<select id="restStatus" name="restStatus">
	        <option value=0>0</option>
	        <option value=1>1</option>
	   	</select>
	   	<p><label>可預約組數：</label>
		<input type="number" name="bookingLimit"><br>
		<p><label>管理員ID：</label>
		<input type="number" name="adminId" pattern="[1-5]"><br>
		<p><input type="submit" value="新增"></p>
		<input type="hidden" name="action" value="add">
   	</form>
		
		
	<br>
	<a href="${pageContext.request.contextPath}/Rest/into.jsp">回首頁</a>	
	<script src="${pageContext.request.contextPath}/Rest/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/Rest/js/rest.js"></script>
	
</body>
</html>