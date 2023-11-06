<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>會員擁有票券查詢</title>
  <jsp:include page="/indexpage/head.jsp" />
</head>
<body>
<jsp:include page="/indexpage/header.jsp" />
<jsp:include page="/indexpage/headpic.jsp"/>

<!-- 主標題 -->
<h1>會員擁有票券查詢</h1>

<!-- 查詢表單 -->
<form method="post" action="<%=request.getContextPath()%>/mto/memList">
  <label for="memId">選擇會員編號：</label>
  <select id="memId" name="memId">
    <c:forEach var="memId" items="${uniqueMemIds}">
      <option value="${memId}">${memId}</option>
    </c:forEach>
  </select>
  <input type="submit" value="查詢">
</form>

<!-- 其他操作 -->
<ul>
  <li><a href="<%=request.getContextPath()%>/to/index?memId=${memId}">查看票券訂單索引頁面</a></li>
  <br>
  <br>
  <br>
  <li><a href="<%=request.getContextPath()%>/tsc/index?memId=${memId}">票券購物車索引頁面</a></li>
</ul>
<jsp:include page="/indexpage/footer.jsp" />
</body>
</html>
