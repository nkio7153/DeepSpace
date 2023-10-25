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
<title>您好，您已加入會員 newMember.jsp</title>
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
	<h1 align="center">歡迎登入成功！</h1>
	<p align="center">您已成功登入，歡迎回來，${authenticatedMem.memName}！</p>
	<hr>
	<h1 align="center">會員資訊</h1>
	<form align="center" action="${pageContext.request.contextPath}/mem/edit?memId=${authenticatedMem.memId}" method="post" >
	<table border="1px" align="center" width="50%">
		<tr>
		<th style="display: none;">會員編號</th>
				<input type="hidden" id="memId" name="memId" value="${memId}" "readonly">
		</tr>
		<tr>
			<th>會員大頭貼</th><td><img src="data:image/jpeg;base64, ${base64Image}" ></td>
		</tr>
		<tr>
			<th>帳號</th><td>${authenticatedMem.memAcc}</td>
		</tr>
		<tr>
			<th>密碼</th><td>${authenticatedMem.memPwd}</td>
		</tr>
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
	
		<input type="submit" value="修改會員資料">
		<input type="hidden" name="action"	value="edit">
	</form>
	<input type="button" value="登出" align="center" onclick="index()">
	<script type="text/javascript">
	    function index() {
	        document.location.href = "${pageContext.request.contextPath}/member/member.jsp";
	    }
	    function revise() {
	        document.location.href = "${pageContext.request.contextPath}/member/revise.jsp";
	    }
	</script>

</body>
</html>
