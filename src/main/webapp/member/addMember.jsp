<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faq.model.service.*"%>
<%@ page import="com.depthspace.faq.model.controller.*"%>
<%@ page import="com.depthspace.faq.model.model.*"%>

<html>
<head>
    <title>註冊會員</title>
</head>
<body>
    <h1>註冊會員</h1>
    <form action="新增會員的後端處理URL" method="POST">
        <label for="memAcc">帳號:</label>
        <input type="text" id="memAcc" name="memAcc" required><br><br>
        
        <label for="memPwd">密碼:</label>
        <input type="password" id="memPwd" name="memPwd" required><br><br>
        
        <label for="memName">會員姓名:</label>
        <input type="text" id="memName" name="memName" required><br><br>
        
        <label for="memIdentity">身分證字號:</label>
        <input type="text" id="memIdentity" name="memIdentity" required><br><br>
        
        <label for="memBth">生日:</label>
        <input type="date" id="memBth" name="memBth" required><br><br>
        
        <label for="memSex">性別:</label>
        <input type="radio" id="male" name="memSex" value="1"> 男
        <input type="radio" id="female" name="memSex" value="0"> 女<br><br>
        
        <label for="memEmail">電子郵件:</label>
        <input type="email" id="memEmail" name="memEmail" required><br><br>
        
        <label for="memTel">手機電話:</label>
        <input type="tel" id="memTel" name="memTel" required><br><br>
        
        <label for="memAdd">地址:</label>
        <input type="text" id="memAdd" name="memAdd" required><br><br>
        
        <label for="accStatus">狀態:</label>
        <select id="accStatus" name="accStatus">
            <option value="1">正常</option>
            <option value="0">停用</option>
        </select><br><br>
        
        <label for="memPoint">會員點數:</label>
        <input type="number" id="memPoint" name="memPoint" required><br><br>
        
        <label for="memImage">會員圖片:</label>
        <input type="file" id="memImage" name="memImage"><br><br>
        
        <input type="submit" value="新增會員">
    </form>
</body>
</html>
