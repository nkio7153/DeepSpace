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
	
	.hidden{
		display:none;
	}
</style>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
  <div class="row">
<%--    側邊欄--%>
    <div class="col-lg-2 g-3 mt-1">
    <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
    </div>

    <div class="col-lg-10 g-2 transparent rounded mt-1">
<%--      放入自己body裡的代碼--%>
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
<!-- 		<tr> -->
<%-- 			<th>密碼</th><td>${admin.adminPwd}</td> --%>
<!-- 		</tr> -->
		<tr>
			<th>管理員姓名</th><td>${admin.adminName}</td>
		</tr>
		<tr class="hidden">
			<th>狀態</th><td>${adminStatus}</td>
		</tr>
	</table>
	<form align="center" action="${pageContext.request.contextPath}/ad/edit?adminId=${admin.adminId}" method="post" >
		<input type="submit" class="update" value="修改管理員資料">
		<input type="hidden" name="action"	value="update">
	</form>

	</div>
    </div>
	<script type="text/javascript">
	    function revise() {
	        document.location.href = "${pageContext.request.contextPath}/admin/revise.jsp";
	    }
	</script>

    </div>
  </div>
</div>

</body>
</html>
