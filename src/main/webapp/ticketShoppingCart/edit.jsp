<%@page contentType="text/html; charset=UTF-8"%>
<%--<%--%>
<%--	Dept dept=(Dept)request.getAttribute("dept");--%>
<%--%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
<h3>歡迎oscar</h3>
<h1>票券修改</h1>
<hr>
<form action="${pageContext.request.contextPath}/tsc/modify" method="post">
    會員編號<input type="text" name="memId" value="${param.memId}" readonly><br>
    票券編號<input type="text" name="ticketId" value="${param.ticketId}"><br>
    票券數量<input type="text" name="quantity" value="${param.quantity}"><br>
    <input type="submit" value="修改"><br>


</form>
</body>
</html>