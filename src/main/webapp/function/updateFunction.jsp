<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>更新功能</title>
</head>
<body>
<h2>更新功能</h2>
<form action="function/update" method="post">
    <input type="hidden" name="funcId" value="${function.funcId}">
    <label for="funcName">功能名稱:</label>
    <input type="text" id="funcName" name="funcName" value="${function.funcName}"><br>
    <!-- 其他輸入欄位 -->
    <input type="submit" value="更新">
</form>
</body>
</html>
