
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增分帳表</title>
</head>
<body>
    <h1>新增分帳表</h1>
    <form action="${pageContext.request.contextPath}/account/add.jsp" method="post">
        <label for="accountName">分帳表名稱:</label>
        <input type="text" id="accountName" name="accountName" required><br>
        <label for="memId">會員ID:</label>
        <input type="number" id="memId" name="memId" required><br>
        <input type="submit" value="新增分帳表">
    </form>
    <a href="${pageContext.request.contextPath}/account/list.jsp">回到目錄</a>
</body>
</html>
