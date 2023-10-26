<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/10/24
  Time: 下午 06:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>促銷管理</title>
  <jsp:include page="../indexpage/head.jsp" />
  <!-- 添加 Bootstrap CSS 链接 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<div class="container mt-5">
  <h1 class="text-center">促銷管理</h1>
  <hr>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th scope="col">序號</th>
      <th scope="col">促銷編號</th>
      <th scope="col">促銷名稱</th>
      <th scope="col">開始日期</th>
      <th scope="col">結束日期</th>
      <th scope="col">描述</th>
      <th scope="col">圖片</th>
      <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="pro" varStatus="proStatus">
      <tr>
        <td>${proStatus.count}</td>
        <td>${pro.promotionId}</td>
        <td>${pro.promoName}</td>
        <td>${pro.startDate}</td>
        <td>${pro.endDate}</td>
        <td>${pro.description}</td>
        <td>${pro.picture}</td>
        <td>
          <a href="#" class="badge btn-primary rounded fs-5" onclick="edit-rows">修改</a>
          <a href="#" class="badge btn-warning rounded fs-5">下架</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <hr>
  <div class="text-end">
  <a class="btn btn-primary badge badge-info rounded" href="${pageContext.request.contextPath}/">新增促銷活動</a>
  </div>
</div>
<!-- 添加 Bootstrap JavaScript 链接（必须在body结束前） -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.min.js"></script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
