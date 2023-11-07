<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>List Admins</title>
</head>
<body>
	<h1>管理員列表</h1>
	<c:if test="${adminPageQty > 0}">
  		<b><font color=red>第${currentPage}/${adminPageQty}頁</font></b>
	</c:if>
	<br>
	<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/cat.png">
	<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/cat.png">
	<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/cat.png">
	<table style="width:50%; text-align:center;">
		<tr>
			<th>管理員編號</th>
			<th>管理員姓名</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>帳號狀態</th>
		</tr>
		<c:forEach var="admin" items="${adminList}">
			<tr>
				<td>${admin.adminId}</td>
				<td>${admin.adminName}</td>
				<td>${admin.adminAcc}</td>
				<td>${admin.adminPwd}</td>
				<td>${admin.adminStatus}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/login?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/login?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= adminPageQty}">
		<a href="${pageContext.request.contextPath}/login?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != adminPageQty}">
		<a href="${pageContext.request.contextPath}/login?action=getAll&page=${adminPageQty}">至最後一頁</a>&nbsp;
	</c:if>
	<br>
	<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/inversecat.png">
	<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/inversecat.png">
	<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/img/inversecat.png">
	<br><br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>