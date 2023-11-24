<%@ page import="com.depthspace.admin.service.AdminService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../indexpage/head.jsp" />
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>忘記密碼</title>
</head>
<body>
    <jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />
    
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
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
                alert("驗證成功，導回登入");
                window.location.href = "${pageContext.request.contextPath}/admin/success.jsp";
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
<jsp:include page="../indexpage/footer.jsp" />
    <!-- 引入 Bootstrap Bundle (包含 Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
