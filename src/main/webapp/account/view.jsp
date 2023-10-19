<%@ page import="com.depthspace.account.model.account.service.AccountServiceImpl" %>
<%@ page import="com.depthspace.account.model.account.AccountVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>帳戶詳細資料</title>
</head>
<body>
    <h1>帳戶詳細資料</h1>
    <p>分帳表ID: ${account.accountId}</p>
    <p>分帳表名稱: ${account.accountName}</p>
    <p>會員編號: ${account.memId}</p>
    <a href="${pageContext.request.contextPath}/account/list.jsp">回目錄</a>
</body>
</html>
