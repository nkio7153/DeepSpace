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
<input type="button" value="返回" onclick="history.back()">
<h3>歡迎${param.memId}</h3>
<h1>票券修改</h1>
<hr>
<form action="${pageContext.request.contextPath}/tsc/modify" method="post">
    會員編號<input type="text" name="memId" value="${param.memId}" readonly><br>
    票券編號<input type="text" name="ticketId" value="${param.ticketId}"><br>
    票券數量<input type="text" name="quantity" value="${param.quantity}"><br>
    <th>訂單編號</th>
    <th>訂單日期</th>
    <th>總金額</th>
    <th>點數回饋</th>
    <th>實付金額</th>
    <th>狀態</th>
    <th>支付方式</th>
    <th>操作</th>
    <input type="submit" value="修改"><br>


</form>
</body>
</html>