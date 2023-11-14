<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.JSONObject" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>成功登入 success.jsp</title>
<style>
	 #myImage {
		 border-radius: 60px;
		 width: 120px;
		 height: 120px;
		 z-index: 1;
	 }
	 #image-container {
	     text-align: center; /* 圖片置中 */
	 }
	.btn_style {
		height: 50px;
	    width: 150px;
	    font-size: 20px;
	    color: #fff;
	    background-color: #008CBA;
	    border: none;
	    padding: 10px;
	    cursor: pointer;
	    border-radius: 10px;
	}
</style>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
<%-- 	<form action="${pageContext.request.contextPath}/mem/success" method="post"> --%>
	<h1 align="center">歡迎登入成功！</h1>
<p align="center">您已成功登入，歡迎回來，${admin.adminName}！</p>
<hr>

	
	</div>
	
	<div style="flex: 70%;">
		
		
		<h1 align="center">管理員資訊</h1>
		<table border="1px" align="center" width="90%">
		
			<th style="display: none;">管理員編號</th><td  style="display: none;">${admin.adminId} </td>
		
		<tr>
			<th>帳號</th><td>${admin.adminAcc}</td>
		</tr>
		<tr>
			<th>密碼</th><td>${admin.adminPwd}</td>
		</tr>
		<tr>
			<th>管理員姓名</th><td>${admin.adminName}</td>
		</tr>
		<tr>
			<th>狀態</th><td>${admin.adminStatus}</td>
		</tr>
	</table>
	<form align="center" action="${pageContext.request.contextPath}/ad/edit?adminId=${admin.adminId}" method="post" >
		<input type="submit" value="修改管理員資料">
		<input type="hidden" name="action"	value="update">
	</form>
	
	<form align="center" action="${pageContext.request.contextPath}/ad/logout" method="post" >
		<input type="submit" value="登出">
	</form>

	</div>
    </div>
	<script type="text/javascript">
	    function revise() {
	        document.location.href = "${pageContext.request.contextPath}/admin/revise.jsp";
	    }
	</script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
