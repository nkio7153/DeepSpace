<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  <style>
  	.mc-5{
  		margin-top:-65px;
  	}

.input-box {
  border-radius: 30px; /* 設定輸入框的圓角 */
}

body, html {
  margin: 0;
  padding: 0;
  overflow-y: hidden; /* 阻止垂直滾動 */
}

.container-fluid {
  max-height: 100vh; /* 設定最大高度為視窗的高度 */
  overflow-y: hidden; /* 阻止垂直滾動 */
}


  </style>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid h-100">
  <div class="row justify-content-center align-items-center h-100">
<%--    側邊欄--%>

		<div class="sidebar-sticky">
			<div class="mt-2">
				<a class="navbar-brand fs-2 mx-2 my-0 fw-bold p-0" href="${pageContext.request.contextPath}/indexpage/index.jsp">
					<img class="mh my-0" src="${pageContext.request.contextPath}/backend/backIndex/image/logo.jpg">
				</a>
			</div>
		</div>

    <div class="col-lg-4 transparent mb-5 mc-5 input-box">
<%--      放入自己body裡的代碼--%>
      <form align="center" action="${pageContext.request.contextPath}/ad/login" method="post">
		<div class="main-box login">
			<h3>登入</h3>
			<label for="adminAcc">帳號</label>
			<input type="adminAcc" name="adminAcc" id="adminAcc" value="${adminAcc}" required>
		</div>

		<div class="input-box">
			<label for="password">密碼</label>
			<input type="password" name="password" id="password" value="${password}" required>
		</div>

		<div class="check">
			<label><input type="checkbox">記住我</label> <a href="${pageContext.request.contextPath}/admin/forgetPassword.jsp"	id="forgetPwd">忘記密碼</a>
		</div>
	<input type="hidden" name="loginLocation" value="${param.requestURI}">
		<input type="submit" value="登入">
	</form>



    </div>
<!--     <div class="col-lg-4 g-3 my-0"> -->

<!--     </div> -->
  </div>
</div>
<script>
	var error='${param.error}';

	 if(error=='false'){
		 console.log("error="+error);
		 alert("帳號錯誤或無此帳號");
	 } else if (error=='true') {
		 alert("密碼錯誤，請從新輸入")
	 } else if(error=='nostatus'){
		 alert("此帳戶已停權，請聯繫客服")
	 }
	 
	 
		 </script>
</body>
</html>
