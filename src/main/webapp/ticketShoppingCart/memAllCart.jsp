<%@ page import="com.depthspace.ticketshoppingcart.service.TicketShoppingCartService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>所有購物車資料 memAllCart.jsp</title>
  <jsp:include page="../indexpage/head.jsp" />
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />

<h3>歡迎會員${memId}</h3>

<input type="button" value="返回" onclick="index()">
<script type="text/javascript">

  function index(){
    document.location.href=
            "${pageContext.request.contextPath}/tsc/index"
  }
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
<style>
  img.jpg {
    width:150px;
    height: 100px;
  }
  .hidden {
    display: none;
  }
  .no-wrap {
    white-space: nowrap;
  }
  .w150{
    width:150px;
  }
  .w200{
    width:200px;
  }


</style>
<h1 align="center">會員購物車列表</h1>
<hr>
<table border="1px" align="center" width="80%">
<%--  //票券圖片、票券名稱、票券介紹、價格、數量、小計--%>
    <tr>
      <th class="no-wrap" align="center">序號</th>
      <th class="no-wrap" align="center">票券圖片</th>
      <th class="hidden" align="center">票券編號</th>
      <th class="no-wrap w150" align="center" >票券名稱</th>
      <th class="no-wrap w200" align="center" >票券介紹</th>
      <th class="no-wrap" align="center">價格</th>
      <th class="no-wrap" align="center">票券數量</th>
      <th class="no-wrap" align="center">小計</th>
      <th class="no-wrap w150" align="center">操作</th>
    </tr>


  <c:forEach items="${list}" var="cart" varStatus="cartStatus">
    <tr>
      <td align="center">${cartStatus.count}</td>
      <td>
      <img src="data:image/jpeg;base64,${cart.base64Image}" class="jpg"/>
      </td>
      <td align="center" class="hidden">${cart.ticketId}</td>
      <td align="center">${cart.ticketName}</td>
      <td align="center">${cart.description}</td>
      <td align="center">${cart.price}</td>
      <td align="center">${cart.quantity}</td>
      <td align="center">${cart.subtotal}</td>
      <td align="center" class="no-wrap w150">
        <!-- 這裡添加你的後端刪除和修改的路徑 -->
        <a href="javascript:void(0)" onclick="del1(${cart.memId},${cart.ticketId})">刪除</a>
        <a href="${pageContext.request.contextPath}/ticketShoppingCart/edit.jsp?memId=${cart.memId}
        &ticketId=${cart.ticketId}&quantity=${cart.quantity}" >修改</a>
      </td>
    </tr>
  </c:forEach>
  <hr>
</table>
<a href="${pageContext.request.contextPath}/ticketShoppingCart/addCart.jsp?memId=${memId}">購物車票券添加</a>
<hr>
<a href="javascript:void(0)" onclick="delAll(${memId})"> 購物車票清空</a>

<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
