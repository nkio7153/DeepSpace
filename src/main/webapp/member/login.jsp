<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>登入</title>
<style>
    body {
        background-color: #f7f7f7; /* 設置背景顏色 */
        font-family: Arial, sans-serif; /* 設置字體 */
    }

    .login-container {
        max-width: 400px; /* 最大寬度 */
        margin: 50px auto; /* 上下邊距和自動水平居中 */
        padding: 20px;
        background-color: #ffffff; /* 設置背景顏色 */
        border-radius: 8px; /* 圓角邊框 */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 邊框陰影 */
    }

    .login-box h3 {
        color: #333333; /* 標題顏色 */
        margin-bottom: 20px; /* 標題下邊距 */
        text-align: center;
    }

    .input-box {
        margin-bottom: 15px; /* 輸入框下邊距 */
    }

    .input-box input {
        width: 100%; /* 輸入框寬度 */
        padding: 8px; /* 輸入框內邊距 */
        border: 1px solid #cccccc; /* 邊框顏色 */
        border-radius: 4px; /* 圓角邊框 */
    }

    .check {
        display: flex; /* 彈性布局 */
        justify-content: space-between; /* 兩端對齊 */
        align-items: center; /* 垂直居中 */
        margin-bottom: 20px; /* 下邊距 */
    }

    .check label {
        cursor: pointer; /* 鼠標樣式 */
    }

    .check a {
        color: #0066cc; /* 連結顏色 */
        text-decoration: none; /* 去除下劃線 */
    }

    .register {
        text-align: center; /* 居中對齊 */
    }

    .register a {
        color: #0066cc; /* 連結顏色 */
        text-decoration: none; /* 去除下劃線 */
    }

    .submit-btn {
        width: 100%; /* 寬度 */
        padding: 10px; /* 內邊距 */
        border: none; /* 去除邊框 */
        background-color: #267af7ba; /* 背景顏色 */
        color: white; /* 文字顏色 */
        border-radius: 4px; /* 圓角邊框 */
        cursor: pointer; /* 鼠標樣式 */
    }

    .submit-btn:hover {
        background-color: #5998f6ba; /* 滑鼠懸停時背景顏色變化 */
    }
</style>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />

    <div class="login-container">
        <!-- 登入表單 -->
        <form action="${pageContext.request.contextPath}/mem/login" method="post">
            <div class="login-box">
                <h3>登入</h3>
                <div class="input-box">
                    <label for="memAcc">帳號</label>
                    <input type="text" name="memAcc" id="memAcc" value="${req.memAcc}" required>
                </div>

                <div class="input-box">
                    <label for="password">密碼</label>
                    <input type="password" name="password" id="password" value="${req.password}" required>
                </div>

                <div class="check">
                    <label><input type="checkbox">記住我</label>
                    <a href="${pageContext.request.contextPath}/member/forgetPassword.jsp" id="forgetPwd">忘記密碼</a>
                </div>
                <input type="hidden" name="loginLocation" value="${param.requestURI}">
                <input type="submit" class="submit-btn" value="登入">
            </div>
            
            <div class="register">
                <p>如果沒有帳號? <a href="${pageContext.request.contextPath}/mem/signIn">點擊註冊</a></p>
            </div>
        </form>
    </div>

    <script>
        var error = '${param.error}';
        if (error == 'false') {
            alert("帳號錯誤或無此帳號");
        } else if (error == 'true') {
            alert("密碼錯誤，請重新輸入");
        } else if (error == 'nostatus') {
            alert("此帳戶已停權，請聯繫客服");
        }
    </script>

 <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
