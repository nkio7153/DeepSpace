<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>票券訂單</title>
    <jsp:include page="../indexpage/head.jsp" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>
<div class="container mt-4">
    <button type="button" class="btn btn-secondary" onclick="history.back()">返回</button>
    <h1 class="text-center my-4">訂單列表</h1>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="text-center">序號</th>
            <th class="text-center">訂單編號</th>
            <th class="text-center">會員編號</th>
            <th class="text-center">訂單日期</th>
            <th class="text-center">總金額</th>
            <th class="text-center">點數回饋</th>
            <th class="text-center">實付金額</th>
            <th class="text-center">支付方式</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="order" varStatus="orderStatus">
            <tr>
                <td align="center">${orderStatus.count}</td>
                <td align="center">${order.orderId}</td>
                <td align="center">${order.memId}</td>
                <td align="center">${order.orderDate}</td>
                <td align="center">${order.totalAmount}</td>
                <td align="center">${order.pointsFeedback}</td>
                <td align="center">${order.amountPaid}</td>
                <td align="center" name="paymentMethod">${order.paymentMethod}</td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/tod/backList?orderId=${order.orderId}&totalAmount=${order.totalAmount}&amountPaid=${order.amountPaid}" class="btn btn-secondary">訂單明細</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    function orderList(){
        document.location.href= "${pageContext.request.contextPath}/tod/list?orderId=${order.orderId}";
    }
    function index(){
        document.location.href= "${pageContext.request.contextPath}/to/index";
    }
    $(document).ready(function(){
        $('[name="paymentMethod"]').each(function(){
            switch ($(this).text()) {
                case '1':
                    $(this).text("信用卡");
                    break;
                case '2':
                    $(this).text("轉帳");
                    break;
                case '3':
                    $(this).text("街口支付");
                    break;
            }
        })
    })
</script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
