<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/10/9
  Time: 下午 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>訂單添加</title>
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
    <body>
    <input type="button" value="返回" onclick="history.back()">
    <h1 align="center">訂單添加</h1>
    <hr>
        <form action="${pageContext.request.contextPath}/tsc/save" method="post">
            會員編號:<input type="text" name="memId" value="${param.memId}" readonly><br>
            訂單編號<input type="text" name="ticketId"><br>
            訂單日期<input type="text" name="orderDate"><br>
            總金額<input type="text" name="totalAmount"><br>
            點數回饋<input type="text" name="pointsFeedback" hidden><br>
            實付金額<input type="text" name="amountPaid"><br>
            支付方式<input type="text" name="paymentMethod"><br>
            <input type="submit" value="保存">
        </form>

    </body>
</html>
