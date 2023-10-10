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
    <title>所有購物車資料 listAllCart.jsp</title>
</head>
<body>
<h1 align="center">購物車全部列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序號</th>
        <th>會員編號</th>
        <th>票券編號</th>
        <th>票券名稱</th>
        <th>票券數量</th>
        <th>加入日期</th>
        <th>操作</th>
    </tr>


    <c:forEach items="${list}" var="cart" varStatus="cartStatus">
        <tr>
            <td align="center">${cartStatus.count}</td>
            <td align="center">${cart.memId}</td>
            <td align="center">${cart.ticketId}</td>
            <td align="center">${cart.ticketName}</td>
            <td align="center">${cart.quantity}</td>
            <td align="center">${cart.addedDate}</td>
            <td align="center" width="100px">
                <a href="">刪除</a>
                <a href="">修改</a>
            </td>
        </tr>

    </c:forEach>
</table>
<hr>
    <a href="add.jsp">購物車票券添加</a>
</body>
</html>
