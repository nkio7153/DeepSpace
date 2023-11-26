<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.depthspace.admin.service.AdminService" %>

<html>
<head>
    <title>忘記密碼</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
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
       <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 border border-3 p-4 shadow-sm" style="height:330px;">
                <div class="text-center mb-4">
                    <h2>FORGET PASSWORD 忘記密碼</h2>
                    <p class="lead">系統會自動將密碼寄到您的信箱中，確認送出後再請留意收取唷!</p>
                </div>
                <div id="forgetPassword" class="card p-4 shadow-sm">
                    <div class="mb-3">
                        <input type="text" name="adminAcc" class="form-control" id="adminAcc" required placeholder="輸入您的帳號">
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary" onclick="submitForm()">提交</button>
                    </div>
                </div>
                <div id="verify" class="card p-4 shadow-sm mt-3" style="display:none;">
                    <div class="mb-3">
                        <input type="text" name="password" class="form-control" id="password" placeholder="輸入驗證碼">
                    </div>
                    <div class="d-grid">
                        <button type="button" class="btn btn-primary" onclick="verifyCode()">驗證</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    </div>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
let error='${param.error}';

if(error=='true'){
// 	 console.log("error="+error);
	 alert("帳號輸入錯誤");
}



function verifyCode() {
	
	let adminAcc = $("#adminAcc").val();
    let password = $("#password").val();
//     console.log(memEmail + "," + password)
    
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/ad/checkVerify",
        data: { adminAcc: adminAcc, password: password},

        success: function(response) {
        	console.log(response)
            if (response === 'success') {
                alert("驗證成功");
                window.location.href = "${pageContext.request.contextPath}/admin/changePassword.jsp";
            } else {
                alert("驗證碼輸入錯誤，請從新輸入");
            }
        }
    });
}



function submitForm() {
	let adminAcc = $("#adminAcc").val();
//     console.log(memAcc + "," + memEmail)
    // 使用 jQuery 的 AJAX 函數
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/ad/forgetPassword",
        data: { adminAcc: adminAcc},

        success: function(response) {
//         	console.log(response)
            if (response === 'success') {
                alert("提交成功，密碼將寄送到您的信箱中！");
                
                //驗證畫面打開，輸入畫面藏起
                $("#verify").show();
                $("#forgetPassword").hide();
                $("#input").hide();
            } else {
                alert("帳號錯誤或電子郵件輸入錯誤");
            }
        }
    });
}
</script>
</body>
</html>
