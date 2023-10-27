<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
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
<%-- 錯誤表列 --%>
<div style="text-align: center;">
<c:if test="${not empty errorMsgs}">
	<font  style="color:red" align="center">查無資料：帳號或密碼錯誤</font>
	<ul style="list-style: none;">
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>


	<form align="center" action="${pageContext.request.contextPath}/mem/success" method="post">
		<div class="main-box login">
			<h3>登入</h3>
			<label for="memAcc">帳號</label>
			<input type="memAcc" name="memAcc" id="memAcc" value="${req.memAcc}" required>
		</div>

		<div class="input-box">
			<label for="password">密碼</label>
			<input type="password" name="password" id="password" value="${req.password}" required>
		</div>

		<div class="check">
			<label><input type="checkbox">記住我</label> <a href="#"	id="forgetPwd">忘記密碼</a>
		</div>

		<input type="submit" value="登入">
	
	<div class="register">
		<p>
			如果沒有帳號?
			<a href="${pageContext.request.contextPath}/member/addMember.jsp" class="register-link" value="update" method="post" >點擊註冊</a>
		</p>
	</div>
	</form>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
