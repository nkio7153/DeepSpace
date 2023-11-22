<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>


<html>
<head>
    <title>註冊管理員</title>
      <style>
        label.hidden {
            display: none; /* 或者使用 visibility: hidden; */
        } .error {
            color: red;
        }
    </style>
    <script>
    function validateEmail() {
        var email = document.getElementById('adminAcc').value;
        var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailRegex.test(email)) {
            document.getElementById('emailError').style.display = 'block';
            return false;
        }
        document.getElementById('emailError').style.display = 'none';
        return true;
    }
    
    function checkAccount() {
        var adminAcc = document.getElementById('adminAcc').value;
        if (adminAcc.length < 16 || adminAcc.length > 40) {
            document.getElementById('accountError').innerText = '帳號長度必須在 6 到 30 個字元之間';
            document.getElementById('accountError').style.display = 'block';
            return;
        }
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '${pageContext.request.contextPath}/ad/checkAccount', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (xhr.responseText === 'false') {
                    document.getElementById('accountError').innerText = '此帳號已存在';
                    document.getElementById('accountError').style.display = 'block';
                } else {
                    document.getElementById('accountError').style.display = 'none';
                }
            }
        };
        xhr.send('adminAcc=' + encodeURIComponent(adminAcc));
    }
    </script>
</head>
<body>
    <h1>註冊會員</h1>
    <form action="${pageContext.request.contextPath}/ad/save" method="post" enctype="multipart/form-data">
     
      <!-- 在這裡檢查並顯示錯誤訊息 -->
        <c:if test="${not empty errorMsgs}">
            <div style="color: red;">
                <c:forEach var="error" items="${errorMsgs}">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </c:if>
     
         <label for="adminAcc">帳號:</label>
        <input type="text" id="adminAcc" name="adminAcc" value="" required onblur="checkAccount()"><br>
        <span id="accountError" class="error" style="display:none;"></span><br>

        <label for="adminPwd">密碼:</label>
        <input type="password" id="adminPwd" name="adminPwd" value="" required><br><br>
        
        <label for="adminName">管理員姓名:</label>
        <input type="text" id="adminName" name="adminName" value="" required><br><br>
        
        <label for="adminStatus" >管理員帳號狀態:</label>
        <select id="adminStatus" name="adminStatus"  >
            <option value="1" >正常使用中</option>
<!--             <option value="2"  style="display: none;">停用</option> -->
        </select>
         <br>
         <label for="adminVerifyStatus" >驗證碼狀態:</label>
        <select id="adminVerifyStatus" name="adminVerifyStatus"  >
            <option value="1" >已驗證</option>
<!--             <option value="2"  style="display: none;">未驗證</option> -->
        </select>
        <br>
        <tr>
			<th>管理員帳號權限</th>
			<td>
				<input type="radio" name="adminFuncName" value="0">無功能
				<input type="radio" name="adminFuncName" value="1" checked>餐廳管理員
				<input type="radio" name="adminFuncName" value="2">總管理員
			</td>
		</tr>
        <br>
        <input type="submit" value="加入管理員">
        <input type="hidden" name="action" value="save">
    </form>
</body>
</html>
