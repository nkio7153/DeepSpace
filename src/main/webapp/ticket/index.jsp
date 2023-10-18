<%@ page import="com.depthspace.ticket.service.TicketService" %>
<%@ page import="com.depthspace.ticket.model.TicketVO" %>
<%@ page import="com.depthspace.ticket.model.TicketInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>票券管理系統</title>
</head>
<body>
	<h1>票券管理系統</h1>
	<h2>票券查詢</h2>
		<ul>
		<li><a href="<%=request.getContextPath()%>/ticketproduct/listAll">查看所有票券列表</a></li>
		</ul>

</body>
</html>