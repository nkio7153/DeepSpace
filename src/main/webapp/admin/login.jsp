<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>登入</title>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />

	<form align="center" action="${pageContext.request.contextPath}/admin/login" method="post">
		<div class="main-box login">
			<h3>登入</h3>
			<label for="adminAcc">帳號</label>
			<input type="adminAcc" name="adminAcc" id="adminAcc" value="${req.adminAcc}" required>
		</div>

		<div class="input-box">
			<label for="password">密碼</label>
			<input type="password" name="password" id="password" value="${req.password}" required>
		</div>

		<div class="check">
			<label><input type="checkbox">記住我</label> <a href="#"	id="forgetPwd">忘記密碼</a>
		</div>
	<input type="hidden" name="loginLocation" value="${param.requestURI}">
		<input type="submit" value="登入">
	
	<div class="register">
		<p>
			如果沒有帳號?
			<a href="${pageContext.request.contextPath}/admin/addAdmin.jsp" class="register-link" value="update" method="post" >點擊註冊</a>
		</p>
	</div>
	</form>
	
	<script>
	var error='${param.error}';
	 
	 if(error=='false'){
		 console.log("error="+error);
		 alert("帳號錯誤或無此帳號");
	 } else if (error=='true') {
		 alert("密碼錯誤，請從新輸入")
	 }
		 </script>
		 
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
