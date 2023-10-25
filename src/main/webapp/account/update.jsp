<%@ page import="com.depthspace.account.model.account.service.AccountServiceImpl" %>
<%@ page import="com.depthspace.account.model.account.AccountVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<meta charset="UTF-8">
<title>更新分帳表</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4" crossorigin="anonymous"></script>
<script>
function processUpdateSave() {
    var accountId = '${param.accountId}'; 
    var accountName = document.getElementById("accountName").value;
    var memId = document.getElementById("memId").value;
    
    if (accountName.trim() === "") {
        document.getElementById("accountNameError").innerText = "分帳表名稱不能為空";
        return;
    } else {
        document.getElementById("accountNameError").innerText = "";
    }
    
    if (isNaN(memId) || memId.trim() === "") {
        document.getElementById("memIdError").innerText = "會員ID必須是一個有效的數字";
        return;
    } else {
        document.getElementById("memIdError").innerText = "";
    }
    
    var jsonObj = {
        accountId: accountId,
        accountName: accountName,
        memId: memId
    };
    $.ajax({
        type: 'post',
        data: jsonObj,
        url: '<%=request.getContextPath()%>/account/account.do?action=update',
        async: false,
        success: function(data) {
            alert('更新成功');
            window.location.href = '<%=request.getContextPath()%>/account/list.jsp';
        }
    });
}
</script>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
    <div id="Add">
        <form id="updateForm" method="Post" accept-charset="UTF-8">
            <table>
                <tr>
                    <th>分帳表名稱</th>
                    <td><input type="text" name="accountName" id="accountName" value="${param.accountName}">
                        <span id="accountNameError" style="color: red;"></span>
                    </td>
                </tr>
                <tr>
                    <th>會員ID</th>
                    <td><input type="text" name="memId" id="memId" value="${param.memId}">
                        <span id="memIdError" style="color: red;"></span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <input type="button" id="btnUpDateSave" value="儲存" onclick="processUpdateSave();">
    <input type="button" id="btnCancel" value="取消" onclick="window.location.href='<%=request.getContextPath()%>/account/list.jsp'">
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
