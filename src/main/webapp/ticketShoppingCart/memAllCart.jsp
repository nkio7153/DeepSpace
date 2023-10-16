<%@ page import="com.depthspace.ticketshoppingcart.service.TicketShoppingCartService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>所有購物車資料 memAllCart.jsp</title>
</head>
<body>

<input type="button" value="返回" onclick="window.history.back()">
<script type="text/javascript">
  function del1(memId, ticketId){
    console.log("memId:", memId, "ticketId:", ticketId);
    var ok=window.confirm("確定要刪除嗎");
    if(ok){
      document.location.href="${pageContext.request.contextPath}/tsc/delete1?memId="+memId+"&ticketId="+ticketId
    }
  }
  function delAll(memId){
    var ok=window.confirm("確定要刪除嗎");
    if(ok){
      document.location.href="${pageContext.request.contextPath}/tsc/deleteAll?memId="+memId
    }
  }

</script>
<h1 align="center">會員購物車列表</h1>
<hr>
<table border="1px" align="center" width="60%">
  <tr>
    <th>序號</th>
    <th>會員編號</th>
    <th>票券編號</th>
    <th>票券數量</th>
    <th>加入日期</th>
    <th>操作</th>
  </tr>

  <c:forEach items="${list}" var="cart" varStatus="cartStatus">
    <tr>
      <td align="center">${cartStatus.count}</td>
      <td align="center">${cart.memId}</td>
      <td align="center">${cart.ticketId}</td>
      <td align="center">${cart.quantity}</td>
      <td align="center">${cart.addedDate}</td>
      <td align="center" width="100px">
        <!-- 這裡添加你的後端刪除和修改的路徑 -->
        <a href="javascript:void(0)" onclick="del1(${cart.memId},${cart.ticketId})">刪除</a>
        <a href="ticketShoppingCart/edit.jsp?cart=${cart}">修改</a>
      </td>
    </tr>
  </c:forEach>
</table>
<hr>
<a href="${pageContext.request.contextPath}/ticketShoppingCart/addCart.jsp?memId=${memId}">購物車票券添加</a>
<hr>
<a href="javascript:void(0)" onclick="delAll(${memId})"> 購物車票清空</a>
</body>
</html>
