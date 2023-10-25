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
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>
<input type="button" value="返回" onclick="history.back()">
<h1 align="center">訂單明細列表</h1>
<hr>
<table border="1px" align="center" width="80%">
    <tr>
        <th>序號</th>
        <th>訂單編號</th>
        <th>票券編號</th>
        <th>單價</th>
        <th>商品折扣價</th>
        <th>數量</th>
        <th>商品小計</th>
        <th>商品評價</th>
        <th>星星數</th>
        <th>操作</th>
    </tr>


    <c:forEach items="${list}" var="od" varStatus="odst">
        <tr>
            <td align="center">${odst.count}</td>
            <td align="center">${od.orderId}</td>
            <td align="center">${od.ticketId}</td>
            <td align="center">${od.unitPrice}</td>
            <td align="center">${od.discountPrice}</td>
            <td align="center">${od.quantity}</td>
            <td align="center">${od.subtotal}</td>
            <td align="center">${od.ticketReviews}</td>
            <td align="center">${od.stars}</td>
            <td align="center" width="100px">
<%--                <a href="">刪除</a>--%>
                <a href="">商品連結</a>
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
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
