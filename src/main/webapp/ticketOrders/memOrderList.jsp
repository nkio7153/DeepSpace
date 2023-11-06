<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>票券訂單資料</title>
  <jsp:include page="../indexpage/head.jsp" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>

<div class="container mt-4">
  <button type="button" class="btn btn-secondary mb-3" onclick="index()">返回</button>
  <h3>歡迎會員${memId}</h3>
  <h1 class="text-center my-4">會員訂單列表</h1>

  <c:if test="${toMemPageQty > 0}">
    <b><font color=red>第${currentPage}/${toMemPageQty}頁</font></b>
  </c:if>

  <table class="table table-bordered">
    <thead>
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
    </thead>
    <tbody>
    <c:forEach items="${list}" var="order" varStatus="orderStatus">
      <tr>
        <td class="text-center">${orderStatus.count}</td>
        <td class="text-center">${order.orderId}</td>
        <td class="text-center">${order.orderDate}</td>
        <td class="text-center">${order.totalAmount}</td>
        <td class="text-center">${order.amountPaid - order.totalAmount}</td>
        <td class="text-center">${order.amountPaid}</td>
        <td class="text-center">${order.pointsFeedback}</td>
        <td class="text-center" name="paymentMethod">${order.paymentMethod}</td>
        <td class="text-center">
          <a href="${pageContext.request.contextPath}/tod/frontList?orderId=${order.orderId}&totalAmount=${order.totalAmount}&amountPaid=${order.amountPaid}" class="btn btn-primary">訂單明細</a>
          <button class="btn btn-danger">取消訂單</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <c:if test="${currentPage > 1}">
    <a href="${pageContext.request.contextPath}/to/memOrderList?page=1&memId=${memId}">至第一頁</a>&nbsp;
  </c:if>
  <c:if test="${currentPage - 1 != 0}">
    <a href="${pageContext.request.contextPath}/to/memOrderList?page=${currentPage - 1}&memId=${memId}">上一頁</a>&nbsp;
  </c:if>
  <c:if test="${currentPage + 1 <= toMemPageQty}">
    <a href="${pageContext.request.contextPath}/to/memOrderList?page=${currentPage + 1}&memId=${memId}">下一頁</a>&nbsp;
  </c:if>
  <c:if test="${currentPage != toMemPageQty}">
    <a href="${pageContext.request.contextPath}/to/memOrderList?page=${toMemPageQty}&memId=${memId}">至最後一頁</a>&nbsp;
  </c:if>

</div>

<script type="text/javascript">
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
