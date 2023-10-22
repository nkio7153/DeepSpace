<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import=" com.depthspace.member.model.MemVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>登入</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/mem/success" method="post">
		<div class="main-box login">
			<h3>登入</h3>
			<label for="email">信箱</label>
			<input type="email" name="email" id="email" value="${param.email}" required>
		</div>

		<div class="input-box">
			<label for="password">密碼</label>
			<input type="password" name="password" id="password" value="${param.password}" required>
		</div>

		<div class="check">
			<label><input type="checkbox">記住我</label> <a href="#"	id="forgetPwd">忘記密碼</a>
		</div>

		<input type="submit" value="登入">
	</form>
	<div class="register">
		<p>
			如果沒有帳號?
			<a href="${pageContext.request.contextPath}/mem/addMember" class="register-link" value="update" method="post" >點擊註冊</a>
		</p>
	</div>
</body>
</html>
