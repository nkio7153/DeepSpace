<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>修改管理員資料 revise.jsp</title>
<style>
  img {
      border-radius: 60px;
	  width: 120px;
	  height: 120px;
  }
  #preview span.text{
        position: absolute;
        display: inline-block;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        color: lightgray;
      }
</style>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
	<h1 align="center">修改管理員資料</h1>
	<form align="center" action="${pageContext.request.contextPath}/ad/modify" method="post" enctype="multipart/form-data">
		<table border="1" align="center" width="50%">
			
				<th style="display: none;">管理員編號</th>
				<input type="hidden" name="adminId" value="${adminId}" readonly">
		
			<tr>
	            <th>帳號</th>
	            <td><input type="text" name="adminAcc" value="${admin.adminAcc}" required readonly></td>
	        </tr>
	        <tr>
	            <th>密碼</th>
	            <td><input type="text" name="adminPwd"
					value="${admin.adminPwd}" required></td>
	        </tr>
	        <tr>
	            <th>管理員姓名</th>
	            <td><input type="text" name="adminName"
					value="${admin.adminName}" required></td>
	        </tr>
	        <tr>
	            <th>狀態</th>
	            <td><input type="text" name="adminStatus"
					value="${adminstatus}" readonly></td>
	        </tr>
	        
	    </table>
    <p align="center">
		<input type="submit" value="儲存管理員資料">
		<input type="hidden" name="action" value="modify">
	
	
<!--         <input type="button" value="儲存會員資料" onclick="save()" /> -->
        <input type="button" value="取消" onclick="history.back()">
       
    </p>
     </form>
    <script type="text/javascript">
	    var loadFile = function(event){
	    	var reader = new FileReader();
	    	reader.onload = function(){
	    	}
	    }
			function change() {
			// 獲取修改後的資料
				var modifiedData = {
				// 這裡放入你的修改後的資料
					adminId : document.getElementById("adminId").value,
					adminAcc : document.getElementById("adminAcc").value,
					adminPwd : document.getElementById("adminPwd").value,
					adminName : document.getElementById("adminName").value,

				};

				// 存儲修改後的資料到 Local Storage
				localStorage.setItem('modifiedData', JSON
						.stringify(modifiedData));

				// 重導向到 success.jsp
				document.location.href = "${pageContext.request.contextPath}/ad/success";
			}
	</script>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
