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
<title>分帳表清單</title>
	<style>
		.btnContent {
		    text-align: left; /* 居中对齐按钮 */
		}
		
		.btnContent input[type="button"] {
		    margin: 0 10px; /* 为按钮之间添加一些间距，可以根据需要调整 */
    		width: 100px; /* 设置按钮宽度为100像素，可以根据需要调整 */
		    
		}
	</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
    $(document).ready(function() {
        $.ajax({
            type: 'post',
            url:  '<%=request.getContextPath()%>/account/account.do?action=list',
            contentType: "application/x-www-form-urlencoded",
            cache: false,
            dataType: 'json',
            async: false,
            success: function(data) {
                data.forEach(function(item) {
                    var row = '<tr>' +
                              '<td>' + item.accountId + '</td>' +
                              '<td>' + item.accountName + '</td>' +
                              '<td>' + item.memId + '</td>' +
                              '<td><input type="button" value="修改" onclick="processUpDate($(this));"></td>' +
                              '<td><input type="button" value="刪除" onclick="processDelete(' + item.accountId + ');"></td>' +
                              '</tr>';
                    $('#dataTable tr:last').after(row);
                });
            }
        })  
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
                // 清除list
                $('#dataTable tr').each(function() {
                    if ($(this).find('td:first').text() == accountId) {
                        $(this).remove();
                    }
                });
            }
        });
    }
    
    function processUpDate(elem) {
    	var $tr = $(elem).parent().parent();
    	 var accountId = $tr.find('td').eq(0).text();
    	 var accountName = $tr.find('td').eq(1).text();
    	 var memId = $tr.find('td').eq(2).text();
//     	 // 使用URL參數將值傳送到update.jsp
    	 window.location.href = '<%=request.getContextPath()%>/account/update.jsp?accountId=' + accountId + '&accountName=' + accountName + '&memId=' + memId;
	}
</script>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
	<div id="list">
		<h1>分帳表清單</h1>
		<table id='dataTable'>
			<tr>
				<th>分帳表編號</th>
				<th>分帳表名稱</th>
				<th>會員ID</th>
				<th>功能</th>
				<th>功能</th>
			</tr>
		</table>
	</div>
	<div class="btnContent">
    <input type="button" id="btnAdd" value="新增" onclick="window.location.href='<%=request.getContextPath()%>/account/add.jsp'">
    <input type="button" id="btnReturn" value="返回" onclick="window.location.href='<%=request.getContextPath()%>/account/index.jsp'">
	</div>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
