<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.depthspace.admin.model.*"%>
<%@ page import="com.depthspace.admin.model.service.*"%>

<% Object account = session.getAttribute("adminAccount");
		if (account == null) {
			System.out.println("確認清除");
		}
%>

<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/backendlogin.css">
    <title>員工登入</title>
    <style>
    
    div.right-box .error-message {
    color: red;
    margin-top: 0px; /* 調整上邊距的數值 */
    margin-left: 12px;
	}
	
    </style>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounded-5 p-3 bg-white shadow box-area">
            <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box"
                style="background: #ffffff;">
                <div class="featured-image mb-3">
                    <img src="<%=request.getContextPath()%>/img/backend.png" class="img-fluid" style="width: 250px;">
                </div>
            </div>
            <div class="col-md-6 right-box">
                <div class="row align-items-center">
                    <div class="header-text mb-2">
                        <p class="text-black fs-4"
                            style="font-family: 'Courier New', Courier, monospace; font-weight: 550;">後台管理系統</p>
                    </div>
                    <form method="post" action="adminLoginHandler">
                        <div class="input-group mb-2">
                            <input type="text" class="form-control form-control-lg bg-light fs-6" placeholder="請輸入帳號" name="adminAccount" value="${param.adminAccount}">
                        </div>
                            <div class="error-message">${requestScope.errorMsgs.adminAccount}</div>
                        <div class="input-group mb-2">
                            <input type="password" class="form-control form-control-lg bg-light fs-6" placeholder="請輸入密碼" name="adminPassword" value="${param.adminPassword}">
                        </div>
                            <div class="error-message">${requestScope.errorMsgs.adminPassword}</div>
                        <br>
                        <div class="input-group mb-3">
                            <button class="btn btn-lg btn-primary w-100 fs-6" type="submit">登入</button>
                        </div>
                        <div class="error-message">${requestScope.errorMsgs.adminStat}</div>
                    </form>
                </div>
            </div>
        </div>
    </div>



    <script src="<%=request.getContextPath()%>/js/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>
