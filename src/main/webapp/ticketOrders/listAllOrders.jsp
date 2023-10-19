<%@ page import="com.depthspace.ticketshoppingcart.service.TicketShoppingCartService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %><%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/10/9
  Time: 上午 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>票券訂單</title>
</head>
<body>
<input type="button" value="返回" onclick="index()">
<h1 align="center">票券訂單列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序號</th>
        <th>訂單編號</th>
        <th>會員編號</th>
        <th>訂單日期</th>
        <th>總金額</th>
        <th>點數回饋</th>
        <th>實付金額</th>
        <th>狀態</th>
        <th>支付方式</th>
        <th>操作</th>
    </tr>


    <c:forEach items="${list}" var="order" varStatus="orderStatus">
        <tr>
            <td align="center">${orderStatus.count}</td>
            <td align="center">${order.orderId}</td>
            <td align="center">${order.memId}</td>
            <td align="center">${order.orderDate}</td>
            <td align="center">${order.totalAmount}</td>
            <td align="center">${order.pointsFeedback}</td>
            <td align="center">${order.amountPaid}</td>
            <td align="center">${order.status}</td>
            <td align="center">${order.paymentMethod}</td>
            <td align="center" width="100px">
<%--                <a href="">刪除</a>--%>
                <a href="">訂單明細</a>
            </td>
        </tr>

    </c:forEach>
</table>
<hr>
    <script type="text/javascript">

    function index(){
        document.location.href=
            "${pageContext.request.contextPath}/to/index"
    }
    </script>
</body>
</html>
