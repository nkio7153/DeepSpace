<%@ page import="com.depthspace.account.model.account.service.AccountServiceImpl" %>
<%@ page import="com.depthspace.account.model.account.AccountVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增分帳表</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4" crossorigin="anonymous"></script>
<script>
    function processAddSave() {
        var accountName = document.getElementById("accountName").value;
        var memId = document.getElementById("memId").value;
        var accountNameError = document.getElementById("accountNameError");
        var memIdError = document.getElementById("memIdError");
        // 清空舊的錯誤訊息
        accountNameError.textContent = "";
        memIdError.textContent = "";

        if (accountName.trim() === "") {
            accountNameError.textContent = '分帳表名稱不能為空';
            return;
        }

        if (isNaN(memId) || memId.trim() === "") {
            memIdError.textContent = '會員ID必須是一個有效的數字';
            return;
        }

        $.ajax({
            type: 'post',
            data: $('#addForm').serialize(),
            url: '<%=request.getContextPath()%>/account/account.do?action=add',
            success: function(data) {
                alert('新增成功');
                window.location.href = '<%=request.getContextPath()%>/account/list.jsp';
            }
        });
    }
</script>
</head>
<body>
<div id="Add">
    <form id="addForm" method="Post">
        <table>
            <tr>
                <th>分帳表名稱</th>
                <td><input type="text" name="accountName" id="accountName">
                    <span id="accountNameError" style="color: red;"></span>
                </td>
            </tr>
            <tr>
                <th>會員ID</th>
                <td><input type="text" name="memId" id="memId">
                    <span id="memIdError" style="color: red;"></span>
                </td>
            </tr>
        </table>
    </form>
</div>
<input type="button" id="btnUpDateSave" value="儲存" onclick="processAddSave();">
<input type="button" id="btnCancel" value="取消" onclick="window.location.href='<%=request.getContextPath()%>/account/list.jsp'">
</body>
</html>
