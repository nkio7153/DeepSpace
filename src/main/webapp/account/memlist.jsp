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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(document).ready(function() {
    $(".edit-button").click(function() {
        var row = $(this).closest("tr");
        row.find(".display-name").hide();
        row.find(".edit-name").show();
        row.find(".edit-button").hide();
        row.find(".cancel-button").show();
        row.find(".save-button").show();
    });

    $(".cancel-button").click(function() {
        var row = $(this).closest("tr");
        row.find(".display-name").show();
        row.find(".edit-name").hide();
        row.find(".edit-button").show();
        row.find(".cancel-button").hide();
        row.find(".save-button").hide();
    });

    $(".save-button").click(function() {
        var row = $(this).closest("tr");
        var newName = row.find(".edit-name").val();
        var accountId = row.find('input[name="accountId"]').val();
        var memId = row.find('input[name="memId"]').val();
        var jsonObj = {
            accountId: accountId,
            accountName: newName,
            memId: memId
        };
        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/account/account.do?action=updateMemList',
            data: jsonObj,
            success: function(response) {
            	alert('更新成功'); 
                row.find(".display-name").text(newName);
                row.find(".display-name").show();
                row.find(".edit-name").hide();
                row.find(".edit-button").show();
                row.find(".cancel-button").hide();
                row.find(".save-button").hide();
                window.location.href='<%=request.getContextPath()%>/account/index.jsp';
            },
        });
    });
});


function processDelete(accountId) {
    var jsonObj = { accountId: accountId };
    $.ajax({
        type: 'post',
        data: jsonObj,
        url: '<%=request.getContextPath()%>/account/account.do?action=del',
        async: false,
        success: function(data) {
            alert('刪除成功');    
            $('#dataTable tr').each(function() {
                if ($(this).find('td:first').text() == accountId) {
                    $(this).remove();
                }
            });
            window.location.href='<%=request.getContextPath()%>/account/index.jsp';
        }
    });
}
</script>
<title>查詢會員分帳表</title>
<style>
table {
	border-collapse: collapse;
	width: 80%;
	margin: 0 auto;
	border-radius: 10px;
	border: 1px solid #ccc;
}

th, td {
	text-align: center;
	padding: 5px;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

tr:nth-child(odd) {
	background-color: #f9f9f9;
}

input[type="button"] {
	background-color: #007BFF;
	color: #fff;
	border: none;
	padding: 5px 20px;
	cursor: pointer;
	border-radius: 5px;
}

.button-container {
	text-align: right;
}
</style>
<script>
</script>
</head>
<body>
    <jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />
<h1 align="center">會員分帳表列表</h1>
<hr>
<table border="1px" align="center" width="80%">
    <tr>
        <th class="no-wrap" align="center">分帳表名稱</th>
        <th class="no-wrap" align="center">功能</th>
        <th class="no-wrap" align="center">功能</th>
    </tr>
 <c:forEach items="${list}" var="account" varStatus="cartStatus">
    <tr>
        <td align="center">
            <span class="display-name">${account.accountName}</span>
            <input type="text" class="edit-name" value="${account.accountName}" style="display: none;">
			<input type="hidden" name="accountId" value="${account.accountId}">
			<input type="hidden" name="memId" value="${account.memId}">
        </td>
        <td>
            <button class="edit-button">編輯</button>
            <button class="cancel-button" style="display: none;">取消</button>
            <button class="save-button" style="display: none;">確認</button>
        </td>
        <td>
            <input type="button" value="刪除" onclick="processDelete(${account.accountId});">
        </td>
    </tr>
</c:forEach>

</table>
	<div class="button-container" align="center">
	  <input type="button" value="新增" onclick="window.location.href='<%=request.getContextPath()%>/account/addmem.jsp?memId=${memId} '">
	  <input type="button" value="返回" onclick="window.location.href='<%=request.getContextPath()%>/account/index.jsp'">
	</div>
 <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>;