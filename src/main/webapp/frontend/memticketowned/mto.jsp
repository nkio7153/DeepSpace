<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/11/2
  Time: 上午 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>我的票券</title>
    <jsp:include page="/indexpage/head.jsp" />

    <!-- 引入Bootstrap CSS樣式表 -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/indexpage/header.jsp" />
<jsp:include page="/indexpage/headpic.jsp"/>
<div class="container mt-4">
    <h1 class="text-center my-4">我的票券</h1>
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>序號</th>
            <th>擁有票券編號</th>
            <th>會員編號</th>
            <th>票券編號</th>
            <th>發放日期</th>
            <th>過期日期</th>
            <th>使用狀態</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="mto" varStatus="mtoStatus">
            <tr>
                <td>${mtoStatus.count}</td>
                <td>${mto.ticketOwnedId}</td>
                <td>${mto.memId}</td>
                <td>${mto.ticketId}</td>
                <td>${mto.releaseDate}</td>
                <td>${mto.expiryDate}</td>
                <td>${mto.statusOfUse}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- 引入Bootstrap JavaScript文件（必要時） -->
<jsp:include page="/indexpage/footer.jsp" />
</body>
</html>
