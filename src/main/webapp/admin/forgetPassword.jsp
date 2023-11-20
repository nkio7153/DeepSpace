<%@ page import="com.depthspace.admin.service.AdminService" %>
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
             width: 50%; /* 設置表單寬度，可以根據需要進行調整 */
             text-align: center;
         } 
/*         .center-button { */
/*             text-align: center; */
            
/*         } */	
         #input, #verifyBtn {
		    width: 65px; /* 設置按鈕的寬度，可以根據需要進行調整 */
		    font-size: 20px;
		    color: #fff;
		    background-color: #008CBA;
		    border: none;
		    padding: 7px; /* 設置按鈕的內邊距，可以根據需要進行調整 */
		    cursor: pointer;
		    border-radius: 6px;
		    margin: 8px; /* 設置按鈕的外邊距，可以根據需要進行調整 */
		}
    </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
    <div class="center-form"> 
        <h2>FORGET PASSWORD 忘記密碼</h2>
            <h6>系統會自動將密碼寄到您的信箱中，確認送出後再請留意收取唷!</h6>
            
            <div id="forgetPassword">
	            <div class="mb-3 mt-3">
	            	<input type="text" name="adminAcc" class="adminAcc" id="adminAcc" required placeholder="輸入您的帳號">
	            </div>
	    
	            </div>
	           <div class="center-button">
	                <input type="submit" id="input" value="提交" onclick="submitForm()">
	            </div>
            </div>
    </div>
    
    	<!-- 新的輸入框和按鈕 -->
<%-- 	    <form id="password" action="${pageContext.request.contextPath}/member/login.jsp" method="post"> --%>
	        <div id="verify" style="display:none;"  class="center-form">
	            <div class="mb-3 mt-3">
	                <input type="text" name="password" id="password" placeholder="輸入驗證碼">
	            </div>
	
	            <div class="center-button">
	                <input type="button" id="verifyBtn" value="驗證" onclick="verifyCode()">
	            </div>
	        </div>
<!--         </form> -->
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
