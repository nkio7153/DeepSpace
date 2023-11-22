<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
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
		 object-fit: cover;
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
	.update {
	    width: 150px;
	    font-size: 20px;
	    color: #fff;
	    background-color: #008CBA;
	    border: none;
	    padding: 10px;
	    cursor: pointer;
	    border-radius: 10px;
	    margin: 10px;
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
	     	<input type="hidden" name="memId" value="${authenticatedMem.memId}" readonly">
            <input type="submit" value="我的行程查詢"  class="btn_style" >
        </form>

		<form action="${pageContext.request.contextPath}/tr/addTour?memId=${authenticatedMem.memId}" method="post">
		    <input type="submit" value="新增行程"  class="btn_style" >
        </form>
	
	</div>
	
	<div style="flex: 80%;">
		
		
		<h1 align="center">我的會員資訊</h1>
		<table border="1px" align="center" width="90%">
		
			<th style="display: none;">會員編號</th><td  style="display: none;">${authenticatedMem.memId} </td>
		
		<tr>
			<th>會員大頭貼</th>

			<td>
				<img  id="myImage" src="data:image/jpeg;base64,${base64Image}" />
				<input type="hidden" name="myImage" value="${base64Image}" />
			</td>
			
		</tr>
		
		<tr>
			<th>帳號</th><td>${authenticatedMem.memAcc}</td>
		</tr>
<!-- 		<tr> -->
<%-- 			<th>密碼</th><td>${authenticatedMem.memPwd}</td> --%>
<!-- 		</tr> -->
		<tr>
			<th>會員姓名</th><td>${authenticatedMem.memName}</td>
		</tr>
		<tr>
			<th>身分證字號</th><td>${authenticatedMem.memIdentity}</td>
		</tr>
		<tr>
			<th>生日</th><td>${authenticatedMem.memBth}</td>
		</tr>
		<tr>
			<th>性別</th><td>${sex}</td>
		</tr>
		<tr>
			<th>電子郵件</th><td>${authenticatedMem.memEmail}</td>
		</tr>
		<tr>
			<th>手機電話</th><td>${authenticatedMem.memTel}</td>
		</tr>
		<tr>
			<th>地址</th><td>${authenticatedMem.memAdd}</td>
		</tr>
		<tr>
			<th>狀態</th><td>${status}</td>
		</tr>
		<tr>
			<th>會員點數</th><td>${authenticatedMem.memPoint}</td>
		</tr>
	</table>
	<form align="center" action="${pageContext.request.contextPath}/mem/edit?memId=${authenticatedMem.memId}" method="post" >
		<input type="submit" class="update" value="修改會員資料">
<!-- 		<input type="hidden" name="action"	value="update"> -->
	</form>


	</div>
    </div>
	
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
