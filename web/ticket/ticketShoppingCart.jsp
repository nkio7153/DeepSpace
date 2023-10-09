<%@ page import="com.depthspace.ticketshoppingcart.service.TicketShoppingCartService" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    TicketShoppingCartService tscSv = new TicketShoppingCartService();
    List<TicketShoppingCartVO> list = tscSv.getAll();
    HashSet<Integer> uniqueMemIds = new HashSet<>();
    for (TicketShoppingCartVO vo: list){
        uniqueMemIds.add(vo.getMemId());
    }
    request.setAttribute("uniqueMemIds",uniqueMemIds);
%>
<html>
<head>
    <title>歡迎使用購物車資料查詢</title>
    <style>
        /* 這裡是 CSS 樣式 */
        /* ... */
    </style>
</head>
<body>

<!-- 主標題 -->
<h1>購物車資訊查詢</h1>

<!-- 錯誤信息顯示區 -->
<c:if test="${not empty errorMsgs}">
    <div style="color:red;">
        <strong>請修正以下錯誤：</strong>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li>${message}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<!-- 查詢表單 -->
<form method="post" action="<%=request.getContextPath()%>/tsc/memCartList">
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
    <li><a href="<%=request.getContextPath()%>/tsc/listAll">查看所有購物車列表</a></li>
</ul>
</body>
</html>
