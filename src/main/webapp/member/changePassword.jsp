<%@ page import="com.depthspace.member.service.MemberService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <title>修改密碼</title>
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
            <h6>更新密碼</h6>            
            <div id="">
	            <div class="mb-3 mt-3">
	            	<input type="text" name="changePassword" class="changePassword" id="changePassword" required placeholder="請輸入新密碼">
	            </div>
	    
	    		<div class="mb-3">
	            	<input type="text" name="changePassword" class="changePassword" id="checkPassword" required placeholder="確認新密碼">
	            </div>
	           <div class="center-button">
	                <input type="submit" id="input" value="提交" onclick="submitForm()">
	            </div>
            </div>
    </div>
    

<script type="text/javascript">
let error='${param.error}';

if(error=='true'){
// 	 console.log("error="+error);
	 alert("帳號錯誤或電子郵件輸入錯誤");
}



function isValidPassword(password, confirmPassword) {
    return password === confirmPassword;
}

$("#checkPassword").on('blur',function() {
    var passwordValue = $("#changePassword").val().trim();
    var confirmPasswordValue = $("#checkPassword").val().trim();
    console.log(passwordValue)
    console.log(confirmPasswordValue)

    if (confirmPasswordValue === "" || !isValidPassword(passwordValue, confirmPasswordValue)) {
        alert("請輸入相同且有效的密碼。");
        return;
    }
});

function submitForm() {
	let changePassword = $("#changePassword").val();
//     console.log(memAcc + "," + memEmail)
    // 使用 jQuery 的 AJAX 函數
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/mem/checkPassword",
        data: { memAcc: memAcc, changePassword: changePassword },

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