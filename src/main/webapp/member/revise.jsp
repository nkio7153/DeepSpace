<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>修改會員資料 revise.jsp</title>
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
	<h1 align="center">修改會員資料</h1>
	<form align="center" action="${pageContext.request.contextPath}/mem/modify" method="post" enctype="multipart/form-data">
		<table border="1" align="center" width="50%">
			
				<th style="display: none;">會員編號</th>
				<input type="hidden" name="memId" value="${memId}" readonly">
			
			<tr>
				<th>會員大頭貼</th>
					<td>
	                	<img src="data:image/jpeg;base64,${base64Image}" alt="ProfileImage" />
	                	<label>更新大頭貼</label>
	                    <input type="file" id="memImage" name="memImage" onchange="loadFile(event)" />
	                    <!-- 將 base64Image 存儲在一個隱藏的表單字段中 -->
	                    <input type="hidden" name="memImage" value="${base64Image}" />
					</td>
			</tr>
			<tr>
	            <th>帳號</th>
	            <td><input type="text" name="memAcc" value="${mem.memAcc}" required readonly></td>
	        </tr>
	        <tr>
	            <th>密碼</th>
	            <td><input type="text" name="memPwd"
					value="${mem.memPwd}" required></td>
	        </tr>
	        <tr>
	            <th>會員姓名</th>
	            <td><input type="text" name="memName"
					value="${mem.memName}" required></td>
	        </tr>
	        <tr>
	            <th>身分證字號</th>
	            <td><br>
	            <input type="text" name="memIdentity"
					value="${mem.memIdentity}" required></td>
	        </tr>
	        <tr>
	            <th>生日</th>
	            <td><input type="date" name="memBth"
					value="${mem.memBth}" required></td>
	        </tr>
	        <tr>
	            <th>性別</th>
	            <td><input type="text" name="memSex"
					value="${sex}" required></td>
	        </tr>
	        <tr>
	            <th>電子郵件</th>
	            <td><input type="text" name="memEmail"
					value="${mem.memEmail}" required></td>
	        </tr>
	        <tr>
	            <th>手機電話</th>
	            <td><input type="text" name="memTel"
					value="${mem.memTel}" required></td>
	        </tr>
	        <tr>
	            <th>地址</th>
	            <td><input type="text" name="memAdd"
					value="${mem.memAdd}" required></td>
	        </tr>
	        <tr>
	            <th>狀態</th>
	            <td><input type="text" name="accStatus"
					value="${status}" readonly></td>
	        </tr>
	        <tr>
	            <th>點數</th>
	            <td>${mem.memPoint}</td>
<!-- 	            <td><input type="text" name="memPoint" -->
<%-- 					value="${mem.memPoint}" disabled></td> --%>
	        </tr>
	        
	    </table>
    <p align="center">
		<input type="submit" value="儲存會員資料">
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
					memId : document.getElementById("memId").value,
					base64Image : document
							.getElementById("base64Image").value,
					memAcc : document.getElementById("memAcc").value,
					memPwd : document.getElementById("memPwd").value,
					memName : document.getElementById("memName").value,
					memIdentity : document
					.getElementById("memIdentity").value,
					memBth : document.getElementById("memBth").value,
					memSex : document.getElementById("memSex").value,
					memEmail : document.getElementById("memEmail").value,
					memTel : document.getElementById("memTel").value,
					memAdd : document.getElementById("memAdd").value
				//                 memName: document.getElementById("memId").value;
				//                 memName: document.getElementById("memId").value;

				};

				// 存儲修改後的資料到 Local Storage
				localStorage.setItem('modifiedData', JSON
						.stringify(modifiedData));

				// 重導向到 success.jsp
				document.location.href = "${pageContext.request.contextPath}/mem/success";
			}
	</script>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
