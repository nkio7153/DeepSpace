<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>

<html>
<head>
<meta charset="UTF-8">
<title>admin</title>

<script type="text/javascript">
//表單點擊找出對應的function
  function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/admin/adminUpdate.jsp?adminId=" + jsonData.adminId ;
  }
  function processDelete(jsonData){
 	 $.ajax({
     //指定http參數傳輸格式為POST
     type : "POST",
     //ajax請求配置
     data : jsonData,
     //請求目標的url
     url : "<%=request.getContextPath()%>/admin.do?action=del",
     //Ajax成功後執行的function，response為回傳的值
     success : function(data) {
    	 alert('刪除成功');
    	 window.location.reload();
     },
     //Ajax失敗後要執行的function，此例為印出錯誤訊息
     error : function(xhr, ajaxOptions, thrownError) {
      alert("哇 錯了");
     }
    });
  }
  //跳轉到add.jsp頁面
  function processAdd() {
	  window.location.href= "<%=request.getContextPath()%>/admin/adminAdd.jsp"
  }
</script>
</head>
<body>

<jsp:useBean id="adminService" scope="page" class="com.depthspace.admin.model.service.AdminServiceImpl"/>
<input type="button" class='button' id="btnAdd" value="新增" onclick="processAdd();" > 
	<c:forEach var="admin" items="${adminService.allAdmins}">
	<div class="mydiv">
		<h5>${admin.adminId}</h5>
		<h5>${admin.adminName}</h5>
		<h5>${admin.adminAcc}</h5>
		<h5>${admin.adminPwd}</h5>
		<h5>${admin.adminStatus}</h5>
	</div>
     		<td><input type="button" value="修改" onclick="processUpdate({adminId:'${admin.adminId}'});"></td>
     		<td><input type="button" value="刪除" onclick="processDelete({ADMIN_ID:'${admin.adminId}'});"></td>	
    </c:forEach>
</body>
</html>



