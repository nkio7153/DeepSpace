<%@page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@page import="com.depthspace.restaurant.service.RestService"%>
<%@page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>餐廳查詢</title>
</head>
<body>
	<h3><b>複合查詢 (使用 Criteria Query)：</b></h3>
	<form action="${pageContext.request.contextPath}/Rest/Rest.do" method="post">
		<label for="restId">選擇餐廳編號：</label>
    		<select id="restId" name="restId">
        		<c:forEach var="rest" items="${RestList}">
           			<option value="${rest.restId}">${rest.restId}</option>
       			</c:forEach>
    		</select>
		<p><label>餐廳查詢：</label></p>
		<input type="text" name="restName"><br>
		<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
	<table style="width:50%; text-align:center;">
		<tr>
			<th>餐廳編號</th>
			<th>餐廳名稱</th>
			<th>餐廳電話</th>
			<th>餐廳地址</th>
			<th>營業時間</th>
		</tr>
		<tr>
			<td>${rest.restId}</td>
			<td>${rest.restName}</td>
			<td>${rest.restTel}</td>
			<td>${rest.restAddress}</td>
			<td>${rest.restOpen}</td>
		</tr>
	</table>
	<br>
	<a href="${pageContext.request.contextPath}/Rest/index.jsp">回首頁</a>	
</body>
</html>