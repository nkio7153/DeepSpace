<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>功能列表</title>
</head>
<body>
    <h2>功能列表</h2>
    <table border="1">
        <tr>
            <th>功能ID</th>
            <th>功能名稱</th>
            <!-- 其他列 -->
        </tr>
        <c:forEach var="function" items="${functionList}">
            <tr>
                <td>${function.funcId}</td>
                <td>${function.funcName}</td>
                <!-- 其他數據 -->
            </tr>
        </c:forEach>
    </table>
</body>
</html>
