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
<p align="center">您已成功登入，歡迎回來，${authenticatedMem.memName}！</p>
<hr>

	<!-- 新增行程規劃查詢按鈕 -->
	<div style="display: flex; align-items: stretch;">
    <!-- 切30%的寬度給行稱規劃查詢(找所有自己的旅遊規劃) -->
    <div style="flex: 30%; margin-left: 10px;">
	     <form action="${pageContext.request.contextPath}/tr/tourList" method="post">
	     	<input type="hidden" name="memId" value="${authenticatedAdmin.adminId}" readonly">
            <input type="submit" value="我的行程查詢"  class="btn_style" >
        </form>
<%--         <form action="${pageContext.request.contextPath}/tour/newTour.jsp?memId=${authenticatedMem.memId}" method="post"> --%>
<%-- 	     	<input type="hidden" name="memId" value="${authenticatedMem.memId}" readonly"> --%>
<!--             <input type="submit" value="新增行程" style="height: 50px; width: 150px; font-size: 18px; color: #fff; background-color: #008CBA; border: none; padding: 10px; cursor: pointer; border-radius: 10px;"> -->
<!--         </form> -->
		<a href="${pageContext.request.contextPath}/tr/addTour?adminId=${authenticatedAdmin.adminId}" class="btn_style">新增行程</a>
	
	</div>
	
	<div style="flex: 70%;">
		
		
		<h1 align="center">管理員資訊</h1>
		<table border="1px" align="center" width="90%">
		
			<th style="display: none;">管理員編號</th><td  style="display: none;">${authenticatedAdmin.adminId} </td>
		
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
			<th>狀態</th><td>${adminStatus}</td>
		</tr>
	</table>
	<form align="center" action="${pageContext.request.contextPath}/admin/edit?adminId=${authenticatedAdmin.adminId}" method="post" >
		<input type="submit" value="修改管理員資料">
		<input type="hidden" name="action"	value="update">
	</form>
	
	<form align="center" action="${pageContext.request.contextPath}/admin/logout" method="post" >
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
