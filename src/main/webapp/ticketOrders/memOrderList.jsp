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
  <style>
    .button2 {
      padding: 6px 12px;
      margin: 5px;
      cursor: pointer;
      color: #fff;
      border: none;
      border-radius: 4px;
      transition: background-color 0.3s ease;
    }
    .button2:hover{
      color:black;
    }
    .bg1{
      background-color: #007bff;
    }
    .bg2{
      background-color: #ff6b6b;
    }
  </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>
<h3 class="text-primary bg-light p-3 border border-primary text-center shadow">會員訂單列表</h3>
<div class="container mt-4">
<%--  <button type="button" class="btn btn-secondary mb-3" onclick="index()">返回</button>--%>
<%--  <h3>歡迎會員${memId}</h3>--%>
<%--  <h3 class="text-primary bg-light p-3 border border-primary text-center shadow">會員訂單列表</h3>--%>



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
        <td class="text-center" name="orderDate">${order.orderDate}</td>
        <td class="text-center">${order.totalAmount}</td>
        <td class="text-center">${order.amountPaid - order.totalAmount}</td>
        <td class="text-center">${order.amountPaid}</td>
        <td class="text-center">${order.pointsFeedback}</td>
        <td class="text-center" name="paymentMethod">${order.paymentMethod}</td>
        <td class="text-center">
          <a href="${pageContext.request.contextPath}/tod/frontList?orderId=${order.orderId}" class="bg1 button2">訂單明細</a>
          <a href="#" class="button2 bg2" >取消訂單</a>
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
  $(".bg2").on("click",function(){

    let orderDateStr=$(this).closest("tr").find("[name='orderDate']").text();//2023-10-24 16:23:26.0
    let orderDate=new Date(orderDateStr.replace(' ','T'));
    console.log("orderDateStr"+orderDateStr);
    console.log(orderDate);
    let expiryDate=new Date(orderDate);
   expiryDate.setDate(expiryDate.getDate() + 7);
    console.log("expiryDate="+expiryDate);
    let currentDate = new Date();//當前時間
    console.log("currentDate="+currentDate);
    if(currentDate > expiryDate){
      window.alert("退款期限已超過7天");
    }else {
      $(this).text("審核中");
      $(this).off("click");
    }
  })
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
