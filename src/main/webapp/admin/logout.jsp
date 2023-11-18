<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
      <title>登出</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<style>
.transparent{
background-color: transparent
}
</style>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
  <div class="row">
<%--    側邊欄--%>
    
    </div>

    <div class="col-lg-10 g-2 transparent rounded mt-1">
<%--      放入自己body裡的代碼--%>
     <%
        // 清除session
        session.invalidate();
    %>
    <h2>您已成功登出。</h2>
    <a href="<%=request.getContextPath()%>/backend/backIndex/index.jsp">點擊這裡返回後台登入畫面</a>
   

    </div>
  </div>
</div>

</body>
</html>
