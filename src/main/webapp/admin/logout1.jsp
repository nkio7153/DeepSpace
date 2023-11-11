<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #fff4e5; /* 淺橘色背景 */
}

.login-container {
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
	border-radius: 8px;
}

.login-header {
	color: #ffa500; /* 橘色文字 */
	margin-bottom: 20px;
}

.btn-access {
	background-color: #ffa500; /* 橘色按鈕 */
	color: white;
	border: none;
}

.btn-access:hover {
	background-color: #ff8c00; /* 深橘色按鈕 */
}

.error-msg {
	color: red;
	margin-bottom: 10px;
}
</style>
<title>login</title>
</head>
<body>
	<div class="login-container">
		<h3 class="login-header text-center">
			<b>會員登入</b>
		</h3>
		<form method="POST" action="${pageContext.request.contextPath}/logout1" onsubmit="return validateForm()">
			<div class="error-msg">${errorMsgs.adminlogin}</div>
			<div class="form-group">
				<label for="username"><b>帳號</b></label> <input type="text"
					id="username" class="form-control" name="adminAcc">
			</div>
			<div class="error-msg">${errorMsgs.adminacc}</div>
			<div class="form-group">
				<label for="password"><b>密碼</b></label> <input type="password"
					id="password" class="form-control" name="adminPwd"> 
			</div>
			<div class="error-msg">${errorMsgs.adminpwd}</div>
			<div class="form-group">
				<input type="hidden" name="action1" value="adminlogout1">
				<button class="btn btn-access" id="loginButton" type="submit">
					<b>登入</b>
				</button>
			</div>
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" name="remember"
					id="remember"> <label class="form-check-label"
					for="remember"><b>記住帳號</b></label>
			</div>
		</form>
		<a href='/admin/adminAdd.jsp'
			class="text-decoration-none"><b>註冊會員</b></a> <a href="#"
			class="text-decoration-none"> <b>忘記密碼</b></a>
	</div>

	<script>
        let username = document.getElementById("username");
        let remember = document.getElementById("remember");
        let account1 = JSON.parse(localStorage.getItem("account1"));

        if(account1){
            username.value = account1.username;
            remember.checked = true;
        }

        remember.onchange = function(){
            if(this.checked){
                var data = {username: username.value};
                localStorage.setItem("account1", JSON.stringify(data));
            } else {
                localStorage.removeItem("account1");
            }
        }
    </script>
    <script>
        function validateForm() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;

            if(username === "") {
                alert("帳號不能為空");
                return false;
            }
            if(password === "") {
                alert("密碼不能為空");
                return false;
            }
            return true;
        }
        // 省略其他腳本
    </script>
    

	<!-- 引入 Bootstrap JavaScript (依賴 jQuery 和 Popper.js) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.9.9/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>