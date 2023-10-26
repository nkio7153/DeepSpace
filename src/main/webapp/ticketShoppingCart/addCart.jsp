<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>購物車票券添加(addCart.jsp)</title>
</head>
<body>
<h1 align="center">購物車票券添加</h1>
<hr>
<form action="${pageContext.request.contextPath}/tsc/save" method="post">
    會員編號:<input type="text" name="memId" value="${param.memId}" readonly><br>
    票券編號<input type="text" name="ticketId"><br>
    票券數量<input type="text" name="quantity"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>