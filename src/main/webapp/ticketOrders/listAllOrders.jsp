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
    <jsp:include page="../indexpage/head.jsp" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<input type="button" value="返回" onclick="index()">
<h1 align="center">票券訂單列表</h1>
<hr>
<table border="1px" align="center" width="80%">
    <tr>
        <th class="text-center">序號</th>
        <th class="text-center">訂單編號</th>
<%--        <th>總品項</th>--%>
        <th class="text-center">會員編號</th>
        <th class="text-center">訂單日期</th>
        <th class="text-center">總金額</th>
        <th class="text-center">點數回饋</th>
        <th class="text-center">實付金額</th>
        <th class="text-center">支付方式</th>
        <th class="text-center">操作</th>
    </tr>


    <c:forEach items="${list}" var="order" varStatus="orderStatus">
        <tr>
            <td align="center">${orderStatus.count}</td>
            <td align="center">${order.orderId}</td>
<%--            <td align="center" class="orderItem"></td>--%>
            <td align="center">${order.memId}</td>
            <td align="center">${order.orderDate}</td>
            <td align="center">${order.totalAmount}</td>
            <td align="center">${order.pointsFeedback}</td>
            <td align="center">${order.amountPaid}</td>
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

    function orderList(){
        document.location.href=
            "${pageContext.request.contextPath}/tod/list?orderId=${order.orderId}"
    }
    function index(){
        document.location.href=
            "${pageContext.request.contextPath}/to/index"
    }

    <%--$(document).ready(function(){--%>
    <%--    var items = '${items}';--%>
    <%--    var orderItems = JSON.parse(items);--%>
    <%--    $(".orderItem").each(function(index){--%>
    <%--        $(this).text(orderItems[index]);--%>
    <%--    });--%>
    <%--});--%>
    </script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
