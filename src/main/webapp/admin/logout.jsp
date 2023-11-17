<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
     <title>登出</title>
    <script>
        // 5秒後自動重定向到登入頁面
        setTimeout(function() {
            window.location.href = "<%=request.getContextPath()%>/admin/login.jsp";
        }, 3000);
    </script>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
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

        // 顯示登出訊息
        out.println("<h2>您已成功登出。3秒後將返回登入頁面。</h2>");
    %>

    </div>
  </div>
</div>

</body>
</html>
