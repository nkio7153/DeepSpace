<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.admin.service.AdminService"%>

<html>
<head>
<%--  include head.jsp--%>
<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<title>修改密碼</title>
<style>
body {
	background-color: #f7f7f7;
	font-family: Arial, sans-serif;
}

.center-form {
	display: flex;
	flex-direction: column;
	align-items: center;
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

form {
	width: 100%;
	text-align: center;
}

.mb-3 {
	margin-bottom: 15px;
}

input {
	width: 100%;
	padding: 8px;
	border: 1px solid #cccccc;
	border-radius: 4px;
}

#input, #verifyBtn {
	width: 100%;
	padding: 10px;
	border: none;
	background-color: #267af7ba;
	color: white;
	border-radius: 4px;
	cursor: pointer;
}

#input:hover, #verifyBtn:hover {
	background-color: #5998f6ba;
}

#password, #verify {
	display: none;
}
</style>
</head>
<body>
	<%--include header.jsp--%>
	<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
	<div class="container-fluid my-0">
		<div class="row">
			<%--    側邊欄--%>
			<div class="col-lg-2 g-3 mt-1">
				<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
			</div>

			<div class="col-lg-10 g-2 transparent rounded mt-1">
				<%--      放入自己body裡的代碼--%>
				<div class="center-form">
					<h2>變更密碼</h2>
					<!--         <h6>更新密碼</h6>             -->
					<div id="">
						<div class="mb-3 mt-3">
							<input type="password" name="changePassword"
								class="changePassword" id="changePassword" required
								placeholder="請輸入新密碼">
						</div>

						<div class="mb-3">
							<input type="password" name="changePassword"
								class="changePassword" id="checkPassword" required
								placeholder="確認新密碼">
						</div>

						<div class="center-button">
							<input type="submit" id="input" value="提交" onclick="submitForm()">
						</div>
					</div>
				</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
				<script type="text/javascript">
					let error = '${param.error}';

					if (error == 'true') {
						// 	 console.log("error="+error);
						alert("帳號錯誤或電子郵件輸入錯誤");
					}

					function isValidPassword(password, confirmPassword) {
						return password === confirmPassword;
					}

					$("#checkPassword").on(
							'blur',
							function() {
								var passwordValue = $("#changePassword").val()
										.trim();
								var confirmPasswordValue = $("#checkPassword")
										.val().trim();
								console.log(passwordValue)
								console.log(confirmPasswordValue)

								if (confirmPasswordValue === ""
										|| !isValidPassword(passwordValue,
												confirmPasswordValue)) {
									alert("請輸入相同且有效的密碼。");
									return;
								}
							});

					function submitForm() {
						let changePassword = $("#changePassword").val();
						//     console.log(memAcc + "," + memEmail)
						// 使用 jQuery 的 AJAX 函數
						$
								.ajax({
									type : "POST",
									url : "${pageContext.request.contextPath}/ad/changePassword",
									data : {
										changePassword : changePassword
									},

									success : function(response) {
										//         	console.log(response)
										if (response === 'success') {
											alert("密碼更新成功");

											window.location.href = "${pageContext.request.contextPath}/admin/login.jsp";

										} else {
											alert("帳號錯誤或電子郵件輸入錯誤");
										}
									}
								});
					}
				</script>

			</div>
		</div>
	</div>

</body>
</html>
