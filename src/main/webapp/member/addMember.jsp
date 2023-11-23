<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>


<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <title>註冊會員</title>
    
    <style>
/*     	label.hidden { */
/*         	display: none; /* 或者使用 visibility: hidden; */ */
/*         } */
		/* 調整輸入框寬度 */
        input.form-control {
            max-width: 300px;
        }
        /* 調整上下間距 */
        .form-group {
            margin-bottom: 10px;
        }
        /* 調整加入會員按鈕位置 */
        .btn-primary {
            margin: 0 auto;
            display: block;
        }
        /* 閉眼圖 */
	    #box {
/* 		    width: 400px; */
/* 		    border: 1px solid #ccc; */
/* 		    margin: 100px auto; */
/* 		    padding: 1px; */
		    position: relative;
		}
		#eye {
		    position: absolute;
		    top: 50%;
		    right: 10px; /* 調整右邊距 */
		    transform: translateY(-50%);
		    cursor: pointer;
		    z-index: 1; /* 讓圖片疊在密碼輸入框上方 */
		}

	    
	        /* 为img设置样式 */
	    #box img {
	        position: absolute;
/* 	        top: 4px; */
	        right: 6px;
	        width: 24px;
	        /* 把鼠标的样式换成小手 */
	        cursor: pointer;
	    }
	    
	    /* 調整輸入框寬度 */
 		input.form-control {
/*  		    max-width: 300px; */
/*  		    padding-right: 30px; */
 		}
	    #preview_img {
			 border-radius: 60px;
			 width: 120px;
			 height: 120px;
			 object-fit: cover;
			 outline: none;
		 }
        

    </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<!--     <h1>註冊會員</h1> -->
<%--     <form action="${pageContext.request.contextPath}/mem/save" method="post" enctype="multipart/form-data"> --%>
<!--         <label for="memAcc">帳號:</label> -->
<!--         <input type="text" id="memAcc" name="memAcc" value="" required><br><br> -->
        
<!--         <label for="memPwd">密碼:</label> -->
<!--         <input type="password" id="memPwd" name="memPwd" value="" required><br><br> -->
        
<!--         <label for="memName">會員姓名:</label> -->
<%--         <input type="text" id="memName" name="memName" value="${mem.memName}" required><br><br> --%>
        
<!--         <label for="memIdentity">身分證字號:</label> -->
<%--         <input type="text" id="memIdentity" name="memIdentity" value="${mem.memIdentity}" required><br><br> --%>
        
<!--         <label for="memBth">生日:</label> -->
<%--         <input type="date" id="memBth" name="memBth" value="${mem.memBth}" required><br><br> --%>
        
<!--         <label for="memSex">性別:</label> -->
<!--         <input type="radio" id="memSex" name="memSex" value="1"> 男 -->
<!--         <input type="radio" id="memSex" name="memSex" value="2"> 女<br><br> -->
        
<!--         <label for="memEmail">電子郵件:</label> -->
<%--         <input type="email" id="memEmail" name="memEmail" value="${mem.memEmail}" required><br><br> --%>
        
<!--         <label for="memTel">手機電話:</label> -->
<%--         <input type="tel" id="memTel" name="memTel" value="${mem.memTel}" required><br><br> --%>
        
<!--         <label for="memAdd">地址:</label> -->
<%--         <input type="text" id="memAdd" name="memAdd" value="${mem.memAdd}" required><br><br> --%>
        
<!--         <label for="accStatus" >狀態:</label> -->
<!--         <select id="accStatus" name="accStatus"  > -->
<!--             <option value="1" >正常使用中</option> -->
<!-- <!--             <option value="0"  style="display: none;">停用</option> --> 
<!--         </select><br><br> -->
        
<!-- <!--         <label for="memPoint">會員點數:</label> -->
<%-- <%--         <input type="number" id="memPoint" name="memPoint" value="${mem.memPoint}" ><br><br> --%>
        
<!--         <label for="memImage">會員圖片:</label> -->
<!--         <input type="file" id="picture" name="memImage"> -->
        
<!--         <div class="preview" style="margin-right: 10px;"> -->
<!-- 			<img id="preview_img" class="preview_jpg" > -->
<!-- 		</div> -->
<!--         <br><br> -->
<%-- <%--         <input type="hidden" name="base64Image" value="${base64Image}"> --%>
        
<!--         <input type="submit" value="加入會員"> -->
<!--         <input type="hidden" name="action" value="save"> -->
<!--     </form> -->
		<div class="container mt-5">
        <h1 class="text-center">註冊會員</h1>
        <form action="${pageContext.request.contextPath}/mem/save" method="post" enctype="multipart/form-data">
            
            <div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memAcc" class="col-sm-2 col-form-label text-right">帳號:</label>
	                <div class="col-sm-8">
	                    <input type="text" class="form-control" id="memAcc" name="memAcc" value="" required>
	                	<br>
	                	<div id="accountError" style="color: red;"></div>
	                </div>
	            </div>
	        </div>

			<div class="row">
	            <div class="form-group row justify-content-center" >
	                <label for="memPwd" class="col-sm-2 col-form-label text-right">密碼:</label>
	                <div class="col-sm-8">
	                	<div id="box">
	                    <input type="password" class="form-control" id="memPwd" name="memPwd" value="" required>
	                    <!--眼睛图片 -->
	        			<img src="${pageContext.request.contextPath}/member/images/close.png" id="eye">
	                	</div>
	                </div>
	            </div>
	        </div>
            
            <div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memName" class="col-sm-2 col-form-label text-right">會員姓名:</label>
	                <div class="col-sm-8">
	                    <input type="text" class="form-control" id="memName" name="memName" value="${mem.memName}" required>
	                </div>
	            </div>
	        </div>
	
			<div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memIdentity" class="col-sm-2 col-form-label text-right">身分證字號:</label>
	                <div class="col-sm-8">
	                    <input type="text" class="form-control" id="memIdentity" name="memIdentity" value="${mem.memIdentity}" required>
	                	<br>
	                	<div id="result_Id" style="color: red;"></div>
	                </div>
	            </div>
            </div>

			<div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memBth" class="col-sm-2 col-form-label text-right">生日:</label>
	                <div class="col-sm-8">
	                    <input type="date" class="form-control" id="memBth" name="memBth" value="${mem.memBth}" required
	                    max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
	                </div>
	            </div>
            </div>

			<div class="row">
	             <div class="form-group row justify-content-center">
	                <label for="memSex" class="col-sm-2 col-form-label text-right">性別:</label>
	                <div class="col-sm-8">
	                    <div class="form-check form-check-inline">
	                        <input type="radio" class="form-check-input" id="memSex1" name="memSex" value="1">
	                        <label class="form-check-label" for="memSex1">男</label>
	                    </div>
	                    <div class="form-check form-check-inline">
	                        <input type="radio" class="form-check-input" id="memSex2" name="memSex" value="2">
	                        <label class="form-check-label" for="memSex2">女</label>
	                    </div>
	                </div>
	            </div>
	        </div>

			<div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memEmail" class="col-sm-2 col-form-label text-right">電子郵件:</label>
	                <div class="col-sm-8">
	                    <input type="email" class="form-control" id="memEmail" name="memEmail" value="${mem.memEmail}" required>
	                </div>
	            </div>
	        </div>
	        
			<div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memTel" class="col-sm-2 col-form-label text-right">手機電話:</label>
	                <div class="col-sm-8">
	                    <input type="tel" class="form-control" id="memTel" name="memTel" value="${mem.memTel}" required>
	                </div>
	            </div>
	        </div>

			<div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memAdd" class="col-sm-2 col-form-label text-right">地址:</label>
	                <div class="col-sm-8">
	                	<!-- 縣市 -->
					    <select name="city" id="city" class="form-control" required>
					        <option value="">請選縣市</option>
					        <c:forEach var="typeItem" items="${city}">
					            <option value="${typeItem.cityId}">${typeItem.cityName}</option>
					        </c:forEach>
					    </select>
					    
					    <!-- 區域 -->
					    <select name="area" id="area" class="form-control" required>
					    <!-- 放區域的選單 -->
					    </select>
	                    <input type="text" class="form-control" id="memAdd" name="memAdd" value="" placeholder="請輸入地址" required>
	                </div>
	            </div>
            </div>

<!-- 			<div class="row"> -->
<!-- 	            <div class="form-group row justify-content-center"> -->
<!-- 	                <label for="accStatus" class="col-sm-2 col-form-label text-right">狀態:</label> -->
<!-- 	                <div class="col-sm-8"> -->
<!-- 	                    <select class="form-control" id="accStatus" name="accStatus"> -->
<!-- 	                        <option value="1">正常使用中</option> -->
<!-- 	                        <option value="0" style="display: none;">停用</option> -->
<!-- 	                    </select> -->
<!-- 	                </div> -->
<!-- 	            </div> -->
<!-- 	        </div> -->

			<input type="hidden" id="accStatus" name="accStatus" value="1">

			<div class="row">
	            <div class="form-group row justify-content-center">
	                <label for="memImage" class="col-sm-2 col-form-label text-right">會員圖片:</label>
	                <div class="col-sm-8">
<!-- 	                預覽圖 -->
	                    <input type="file" class="form-control-file" id="picture" name="memImage">
	                    <div class="preview mt-2">
	                        <img id="preview_img" class="preview_jpg img-fluid">
	                    </div>
	                </div>
	            </div>
	        </div>

            <input type="submit" class="btn btn-primary" value="加入會員">
            <input type="hidden" name="action" value="save">
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    
    
    <script>
    var file = $("#picture"); // 獲取input file元素
    var preview_el = $("#preview_img"); // 獲取預覽圖片元素
    //上傳檔案觸發change事件時，更換預覽圖
    file.on("change", function() {
        if (this.files && this.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                preview_el.attr("src", e.target.result);
            }

            reader.readAsDataURL(this.files[0]);
        }
    });
    //驗證帳號
    $("#memAcc").on('blur', function() {
    	var memAcc = $("#memAcc").val().trim(); 
//     	console.log(memAcc)
        if (memAcc === "") {
            return;
        }
        let url = "${pageContext.request.contextPath}/mem/checkAccount?memAcc="+memAcc;
        fetch(url)
        	.then(function(response){
		    	return response.json();
		    })
		    .then(function(data){
            	console.log(data)
		    	if (data  == 'true') {
                    $('#accountError').text("可用帳號");
                    isMemaccountValid=true;
                    return;
                } else {
                    $('#accountError').text("帳號已存在，請重新輸入");
                    return;
                }
		    	
		    })
		    .catch(function(error){
		    	console.log(error);
		    })
   		 });
    
    //驗證email
     function isValidEmail(email) {
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }
    
    $("#memEmail").on('blur', async function() {
        var emailValue = $("#memEmail").val().trim();
        if (emailValue === "" || !isValidEmail(emailValue)) {
            alert("請輸入有效的Email地址。");
            return;
        }
    });
    
    //1、获取元素
    var pwd = document.getElementById("memPwd");
    var eye = document.getElementById("eye");
    var flag = 0;//默认眼睛是关上的
    //2、注册事件，定义事件处理程序
    eye.onclick = function () {
        if (flag == 0) {//当眼睛是闭上的时候，点击后：
            pwd.type = "text";//密码框样式换成文本，即可显示密码
            eye.src = "${pageContext.request.contextPath}/member/images/open.png";//图片地址修改为眼睛打开的图片
            flag = 1;
        } else {
            pwd.type = "password";//文本框换成密码框
            eye.src = "${pageContext.request.contextPath}/member/images/close.png";//图片换成闭眼的
            flag = 0;
        }
    }
    //驗證身分證
    $("#memIdentity").on('blur', function() {
	    var id = $("#memIdentity").val().trim();
	    console.log(id); // 注意這裡應該是 id 而不是 memIdentity
	
	    var memIdCheck = /^[A-Z][12]\d{8}$/;
	    if (memIdCheck.test(id)) {
	        $("#result_Id").text(""); // 使用 jQuery 簡化修改文字內容的操作
	        isMemIdValid = true;
	    } else {
	        $("#result_Id").text("身分字號不符合規格，首字需大寫");
	        isMemIdValid = false;
	    }
	});

    
    
    </script>
    <script src="${pageContext.request.contextPath}/member/js/add.js"></script>
    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
