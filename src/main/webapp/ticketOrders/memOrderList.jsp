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



  <table class="table table-bordered table-hover table-striped">
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

  <c:if test="${list.isEmpty()}">
    <div align="center">
      <h1>暫無訂單</h1>
    </div>
  </c:if>
  <!-- 分頁 -->
  <div>
    <nav>
      <ul class="pagination justify-content-center">
        <!-- "至第一頁" 只在非第一頁時顯示 -->
        <c:if test="${currentPage > 1}">
          <li class="page-item"><a class="page-link"
                                   href="${pageContext.request.contextPath}/to/memOrderList?page=1">第一頁</a>
          </li>
        </c:if>

        <!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
        <c:if test="${currentPage - 1 != 0}">
          <li class="page-item"><a class="page-link"
                                   href="${pageContext.request.contextPath}/to/memOrderList?page=${currentPage - 1}"
                                   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
          </a></li>
        </c:if>

        <!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
        <c:forEach var="i" begin="1" end="${toMemPageQty}" step="1">
          <li class="page-item ${i == currentPage ? 'active' : ''}"><a
                  class="page-link"
                  href="${pageContext.request.contextPath}/to/memOrderList?page=${i}">${i}</a>
          </li>
        </c:forEach>

        <!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
        <c:if test="${currentPage + 1 <= toMemPageQty}">
          <li class="page-item"><a class="page-link"
                                   href="${pageContext.request.contextPath}/to/memOrderList?page=${currentPage + 1}"
                                   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
          </a></li>
        </c:if>

        <!-- "至最後一頁" 只在非最後一頁時顯示 -->
        <c:if test="${currentPage != toMemPageQty}">
          <li class="page-item"><a class="page-link"
                                   href="${pageContext.request.contextPath}/to/memOrderList?page=${toMemPageQty}">尾頁</a>
          </li>
        </c:if>
      </ul>
    </nav>
  </div>


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
