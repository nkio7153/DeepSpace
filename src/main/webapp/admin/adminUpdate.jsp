<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<!-- 日期的套版 -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta charset="UTF-8">
<title>admin</title>
<script>
	$(document).ready(function(){
	 	 $.ajax({
	 	     //指定http參數傳輸格式為POST
	 	     type : "POST",
	 	     data : {ADMIN_Id : '<%= request.getParameter("adminId")%>'},
	 	     //請求目標的url
	 	     url : "<%=request.getContextPath()%>/admin.do?action=query",
	 	     //Ajax成功後執行的function，response為回傳的值
	 	     success : function(data) {
	 	    	 var jsonObj = JSON.parse(data);
	 	    	 $('#ADMIN_ID').val(jsonObj.adminId);
	 	    	 $('#ADMIN_NAME').val(jsonObj.adminName);
	 	    	 $('#ADMIN_ACC').val(jsonObj.adminAcc);
	 	    	 $('#ADMIN_PWD').val(jsonObj.adminPwd);
	 	    	 $('#ADMIN_STATUS').val(jsonObj.adminStatus);
	 	     },
	 	     //Ajax失敗後要執行的function，此例為印出錯誤訊息
	 	     error : function(xhr, ajaxOptions, thrownError) {
	 	      alert("哇 錯了");
	 	     }
	 	});	
	})
	
</script>
</head>
<body>
<!--                               request.getContextPath()動態根路徑，action=update找到後端switch(action)的update-->
	<form method="post" action="<%=request.getContextPath()%>/admin.do?action=update">
		<table>
		
		<tr>
			<th>管理員編號</th>
			<td>
				<input type="hidden" name="ADMIN_ID" id="ADMIN_ID" value="" >

			</td>
		</tr>
		<tr>
			<th>管理員名字</th>
			<td>
				<input type="text" name="ADMIN_NAME" id="ADMIN_NAME" required >
			</td>
		</tr>
		<tr>
			<th>管理員帳號</th>
			<td>
				<input type="text" name="ADMIN_ACC" id="ADMIN_ACC" required >
			</td>
		</tr>
		<tr>
			<th>管理員密碼</th>
			<td>
				<input type="password" name="ADMIN_PWD" id="ADMIN_PWD" required >
			</td>
		</tr>
		<tr>
			<th>管理員帳號狀態</th>
			<td>
				<input type="radio" name="ADMIN_STATUS" value="0">離職
				<input type="radio" name="ADMIN_STATUS" value="1" checked>在職
				<input type="radio" name="ADMIN_STATUS" value="2">停職
			</td>
		</tr>
		</table>

		
		<button type="submit">送出</button>
                    <!-- 		取消後跳轉回去首頁 -->
		<input  type="button" onclick="window.location.href='<%=request.getContextPath()%>admin/admin.jsp'" value="取消">
	</form>
</body>
</html>