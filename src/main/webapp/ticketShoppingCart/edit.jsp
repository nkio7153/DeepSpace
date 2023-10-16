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
<h3>歡迎${username}</h3>
<h1>修改部門</h1>
<hr>
<form action="${pageContext.request.contextPath}/tsc/modify" method="post">
    會員編號<input type="text" name="dname" value="${cart.memId}"><br>
    票券編號<input type="text" name="loc" value="${cart.ticketId}"><br>
    票券數量<input type="text" name="loc" value="${cart.quantity}"><br>
    加入日期<input type="text" name="loc" value="${cart.addedDate}"><br>
    <input type="submit" value="修改"><br>


</form>
</body>
</html>