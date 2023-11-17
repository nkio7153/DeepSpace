<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登出</title>
    <script>
        // 5秒後自動重定向到登入頁面
        setTimeout(function() {
            window.location.href = "<%=request.getContextPath()%>/admin/login.jsp";
        }, 3000);
    </script>
</head>
<body>
    <%
        // 清除session
        session.invalidate();

        // 顯示登出訊息
        out.println("<h2>您已成功登出。3秒後將返回登入頁面。</h2>");
    %>
</body>
</html>
