<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>

<html>
<head>
    <%-- include head.jsp --%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <title>註冊管理員</title>
       <style>
        label.hidden {
            display: none; /* 或者使用 visibility: hidden; */
        } 
        .error {
            color: red;
        }
        .centered-form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .centered-form > * {
            width: 50%;
            margin-bottom: 15px;
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
    <%-- include header.jsp --%>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid my-0">
        <div class="row">
            <%-- 側邊欄 --%>
            <div class="col-lg-2 g-3 mt-1">
                <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
            </div>

            <div class="col-lg-10 g-2 transparent rounded mt-1">
                <h1 class="mb-4 text-center">註冊管理員</h1>
                <form action="${pageContext.request.contextPath}/ad/save" method="post" enctype="multipart/form-data" class="centered-form">
                    <c:if test="${not empty errorMsgs}">
                        <div class="alert alert-danger" style="width: 50%;">
                            <c:forEach var="error" items="${errorMsgs}">
                                <p>${error}</p>
                            </c:forEach>
                        </div>
                    </c:if>
                    
                    <input type="text" class="form-control" id="adminAcc" name="adminAcc" placeholder="帳號" required onblur="checkAccount()">
                    <span id="accountError" class="error"></span>

                    <input type="password" class="form-control" id="adminPwd" name="adminPwd" placeholder="密碼" required>

                    <input type="text" class="form-control" id="adminName" name="adminName" placeholder="管理員姓名" required>

                    <select class="form-select" id="adminStatus" name="adminStatus">
                        <option value="1">正常使用中</option>
                        <!-- <option value="2" style="display: none;">停用</option> -->
                    </select>

                    <select class="form-select" id="adminVerifyStatus" name="adminVerifyStatus">
                        <option value="1">已驗證</option>
                        <!-- <option value="2" style="display: none;">未驗證</option> -->
                    </select>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="adminFuncName" value="0" id="noFunction">
                        <label class="form-check-label" for="noFunction">無功能</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="adminFuncName" value="1" id="restaurantAdmin" checked>
                        <label class="form-check-label" for="restaurantAdmin">餐廳管理員</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="adminFuncName" value="2" id="superAdmin">
                        <label class="form-check-label" for="superAdmin">總管理員</label>
                    </div>

                    <input type="submit" class="btn btn-primary" value="加入管理員">
                    <input type="hidden" name="action" value="save">
                </form>
            </div>
        </div>
    </div>
   
</body>
</html>
