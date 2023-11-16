<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>新增功能</title>
</head>
<body>
<h2>新增功能</h2>
<form action="function/add" method="post">
    <label for="funcName">功能名稱:</label>
    <input type="text" id="funcName" name="funcName"><br>
    <!-- 其他輸入欄位 -->
    <input type="submit" value="提交">
</form>
</body>
</html>
