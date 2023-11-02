<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>票券訂單明細</title>
    <jsp:include page="../indexpage/head.jsp" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<div class="container mt-4">
    <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">返回</button>
    <h1 class="text-center my-4">訂單明細列表</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th class="text-center">序號</th>
            <th class="text-center">訂單編號</th>
            <th class="text-center">票券編號</th>
            <th class="text-center">單價</th>
            <th class="text-center">商品折扣價</th>
            <th class="text-center">數量</th>
            <th class="text-center">商品小計</th>
            <th class="text-center">商品評價</th>
            <th class="text-center">星星數</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="od" varStatus="odst">
            <tr>
                <td class="text-center">${odst.count}</td>
                <td class="text-center">${od.orderId}</td>
                <td class="text-center">${od.ticketId}</td>
                <td class="text-center">${od.unitPrice}</td>
                <td class="text-center">${od.discountPrice}</td>
                <td class="text-center">${od.quantity}</td>
                <td class="text-center">${od.subtotal}</td>
                <td class="text-center">${od.ticketReviews}</td>
                <td class="text-center">${od.stars}</td>
                <td class="text-center">
                    <a href="#" class="btn btn-primary">商品連結</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-end">
        <div>
            <p><strong>小計總額:</strong> $<span name="totalAmount">${totalAmount}</span></p>
            <p><strong>使用點數:</strong> $<span name="coupen">${totalAmount-amountPaid}</span></p>
            <p><strong>實付金額:</strong> $<span name="amountPaid">${amountPaid}</span></p>
        </div>
    </div>
</div>

<script type="text/javascript">
    function index() {
        document.location.href = "${pageContext.request.contextPath}/to/index";
    }
</script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
