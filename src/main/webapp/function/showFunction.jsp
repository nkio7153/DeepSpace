<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>功能詳情</title>
</head>
<body>
<h2>功能詳情</h2>
<p>功能ID: ${function.funcId}</p>
<p>功能名稱: ${function.funcName}</p>
<!-- 其他功能屬性 -->
<a href="listFunctions">返回列表</a>
</body>
</html>
