<%@ page import="com.depthspace.account.model.account.service.AccountServiceImpl" %>
<%@ page import="com.depthspace.account.model.account.AccountVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
 <jsp:include page="../indexpage/head.jsp" />
<meta charset="UTF-8">
<title>新增分帳表</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
    <form method="Post" action="${pageContext.request.contextPath}/account/account.do?action=addMemList">
        <table>
            <tr>
                <th>分帳表名稱</th>                
                <td><input type="text" name="accountName" id="accountName">
                    <span id="accountNameError" style="color: red;"></span>
                </td>
            </tr>
            <tr style="display: none;">
                <th>會員ID</th>                
                <td><input type="text" name="memId" value="${param.memId}">
                </td>
            </tr>
        </table>
        <input type="submit" value="儲存">
        <input type="button" id="btnCancel" value="取消" onclick="window.location.href='<%=request.getContextPath()%>/account/index.jsp'">
    </form>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
