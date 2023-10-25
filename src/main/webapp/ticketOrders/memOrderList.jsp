<%@ page import="com.depthspace.ticketshoppingcart.service.TicketShoppingCartService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>票券訂單資料</title>
  <jsp:include page="../indexpage/head.jsp" />
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />

<input type="button" value="返回" onclick="index()">
<h3>歡迎會員${memId}</h3>
<script type="text/javascript">

  function index(){
    document.location.href=
            "${pageContext.request.contextPath}/to/index"
  }
  function del1(memId, ticketId){
    console.log("memId:", memId, "ticketId:", ticketId);
    var ok=window.confirm("確定要刪除嗎");
    if(ok){
      document.location.href="${pageContext.request.contextPath}/to/delete1?memId="+memId+"&ticketId="+ticketId
    }
  }

</script>

<h1 align="center">會員訂單列表</h1>
<hr>
<table border="1px" align="center" width="80%">
  <tr>
    <th class="text-center">序號</th>
    <th class="text-center">訂單編號</th>
    <th class="text-center">訂單日期</th>
    <th class="text-center">總金額</th>
    <th class="text-center">使用點數</th>
    <th class="text-center">實付金額</th>
    <th class="text-center">點數回饋</th>
    <th class="text-center">支付方式</th>
    <th class="text-center">操作</th>
  </tr>

  <c:forEach items="${list}" var="order" varStatus="orderStatus">
    <tr>
      <td align="center">${orderStatus.count}</td>
      <td align="center">${order.orderId}</td>
      <td align="center">${order.orderDate}</td>
      <td align="center">${order.totalAmount}</td>
      <td align="center">${order.amountPaid - order.totalAmount}</td>
      <td align="center">${order.amountPaid}</td>
      <td align="center">${order.pointsFeedback}</td>
      <td align="center">${order.paymentMethod}</td>
      <td align="center" class="no-wrap w150">
        <a href="${pageContext.request.contextPath}/tod/list?orderId=${order.orderId}" >訂單明細</a>
        <a href="">取消訂單</a>
      </td>
    </tr>
  </c:forEach>
</table>
<hr>
<a href="${pageContext.request.contextPath}/ticketOrders/addOrder.jsp?memId=${memId}">添加訂單</a>
<hr>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
