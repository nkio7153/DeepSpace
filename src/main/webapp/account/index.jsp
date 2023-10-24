<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../indexpage/head.jsp" />
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
<!-- 主標題 -->
<h1>分帳表查詢</h1>

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
    <li><a href="<%=request.getContextPath()%>/account/list.jsp">查看所有分帳表列表</a></li>
</ul>

<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
