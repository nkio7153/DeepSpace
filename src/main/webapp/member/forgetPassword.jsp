<%@ page import="com.depthspace.member.service.MemberService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <title>忘記密碼</title>
     <style>
        .center-form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        form {
            width: 30%; /* 設置表單寬度，可以根據需要進行調整 */
        }
/*         .center-button { */
/*             text-align: center; */
            
/*         } */
         .input {
         	text-align: center;
            margin-bottom: 20px; /* 新增的樣式，設定底部間距 */
        }
    </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
    <div class="center-form"> <!-- 新增的div -->
        <h2>忘記密碼</h2>
        <form action="ForgetPasswordServlet" method="post">
            請輸入您的電子郵件地址：
            <input type="text" name="email" required>
           <div class="center-button">
                <input type="submit" class="input" value="提交">
            </div>	
        </form>
    </div>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
