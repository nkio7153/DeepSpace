<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>成功登入 listOneAdmin.jsp</title>
<style>
  img {
      border-radius: 60px;
	  width: 120px;
	  height: 120px;
  }
</style>
</head>
<body>
<%-- 	<form action="${pageContext.request.contextPath}/mem/success" method="post"> --%>
	<h1 align="center">已修改成功！</h1>
	<p align="center">您已成功登入，歡迎回來，${authenticatedAdmin.adminName}！</p>
	<hr>
	<h1 align="center">管理員資訊</h1>
	<table border="1px" align="center" width="50%">
		<tr>
			<th>管理員編號</th><td>${authenticatedAdmin.adminId} </td>
		</tr>
		<tr>
			<th>帳號</th><td>${authenticatedAdmin.adminAcc}</td>
		</tr>
		<tr>
			<th>密碼</th><td>${authenticatedAdmin.adminPwd}</td>
		</tr>
		<tr>
			<th>管理員姓名</th><td>${authenticatedAdmin.adminName}</td>
		</tr>
		<tr>
			<th>狀態</th><td>${authenticatedAdmin.adminStatus}</td>
		</tr>
	</table>
	<form align="center" action="${pageContext.request.contextPath}/ad/edit?adminId=${authenticatedAdmin.adminId}" method="post" >
		<input type="submit" value="修改管理員資料">
		<input type="hidden" name="action"	value="update">
	</form>
	<input type="button" value="登出" align="center" onclick="index()">
	<script type="text/javascript">
	    function index() {
	        document.location.href = "${pageContext.request.contextPath}/ad/login.jsp";
	    }
	    function revise() {
	        document.location.href = "${pageContext.request.contextPath}/ad/revise.jsp";
	    }
	</script>

</body>
</html>
