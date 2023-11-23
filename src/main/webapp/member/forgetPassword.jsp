<%@ page import="com.depthspace.member.service.MemberService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <title>忘記密碼</title>
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
	    h6 {
	    	font-size: 0.8rem;
	    	margin-top: 10px;
	    	color: #7D8E95;
	    }
	</style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
    <div class="center-form"> 
        <h2>忘記密碼</h2>
           
            
            <div id="forgetPassword">
	            <div class="mb-3 mt-3">
	            	<input type="text" name="memAcc" class="memAcc" id="memAcc" required placeholder="輸入您的帳號">
	            </div>
	    
	    		<div class="mb-3">
	            	<input type="text" name="memEmail" class="memEmail" id="memEmail" required placeholder="請輸入您的電子郵件">
	            </div>
	           <div class="center-button">
	                <input type="submit" id="input" value="提交" onclick="submitForm()">
	            </div>
	             <h6>密碼將寄到您的信箱，送出後請收信!</h6>
            </div>
            <!-- 新的輸入框和按鈕 -->
<%-- 	    <form id="password" action="${pageContext.request.contextPath}/member/login.jsp" method="post"> --%>
	        <div id="verify" style="display:none;"  class="m-0 center-form">
	            <div class="mb-3 mt-3">
	                <input type="text" name="password" id="password" placeholder="輸入驗證碼">
	            </div>
	
	            <div class="center-button">
	                <input type="button" id="verifyBtn" value="驗證" onclick="verifyCode()">
	            </div>
	        </div>
<!--         </form> -->
    </div>
    
    	
<script type="text/javascript">
let error='${param.error}';

if(error=='true'){
// 	 console.log("error="+error);
	 alert("帳號錯誤或電子郵件輸入錯誤");
}



function verifyCode() {
	
	let memAcc = $("#memAcc").val();
    let memEmail = $("#memEmail").val();
    let password = $("#password").val();
//     console.log(memEmail + "," + password)
    
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/mem/checkVerify",
        data: { memAcc: memAcc, memEmail: memEmail ,password: password},

        success: function(response) {
        	console.log(response)
            if (response === 'success') {
                alert("驗證成功，請先更換密碼");
                
                window.location.href = "${pageContext.request.contextPath}/member/changePassword.jsp";
            } else {
                alert("驗證碼輸入錯誤，請從新輸入");
            }
        }
    });
}



function submitForm() {
	let memAcc = $("#memAcc").val();
	let memEmail = $("#memEmail").val();
//     console.log(memAcc + "," + memEmail)
    // 使用 jQuery 的 AJAX 函數
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/mem/forgetPassword",
        data: { memAcc: memAcc, memEmail: memEmail },

        success: function(response) {
//         	console.log(response)
            if (response === 'success') {
                alert("提交成功，密碼將寄送到您的信箱中！");
                
                //驗證畫面打開，輸入畫面藏起
                $("#password").show();
                $("#verify").show();
                $("#forgetPassword").hide();
            } else {
                alert("帳號錯誤或電子郵件輸入錯誤");
            }
        }
    });
}
</script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
