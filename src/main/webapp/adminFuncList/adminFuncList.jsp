<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>管理員功能列表</title>
</head>
<body>
    <h2>管理員功能管理</h2>
    <% 
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            out.println("<p>" + message + "</p>");
        }
    %>
    <form action="AdminFuncListServlet" method="post">
        <input type="hidden" name="action" value="add">
        功能 ID: <input type="text" name="funcId">
        <input type="submit" value="添加/刪除功能">
    </form>
</body>
</html>
