<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>


<html>
<head>
    <title>註冊會員</title>
      <style>
        label.hidden {
            display: none; /* 或者使用 visibility: hidden; */
        }
    </style>
</head>
<body>
    <h1>註冊會員</h1>
    <form action="${pageContext.request.contextPath}/mem/save" method="post" enctype="multipart/form-data">
        <label for="memAcc">帳號:</label>
        <input type="text" id="memAcc" name="memAcc" value="" required><br><br>
        
        <label for="memPwd">密碼:</label>
        <input type="password" id="memPwd" name="memPwd" value="" required><br><br>
        
        <label for="memName">會員姓名:</label>
        <input type="text" id="memName" name="memName" value="${mem.memName}" required><br><br>
        
        <label for="memIdentity">身分證字號:</label>
        <input type="text" id="memIdentity" name="memIdentity" value="${mem.memIdentity}" required><br><br>
        
        <label for="memBth">生日:</label>
        <input type="date" id="memBth" name="memBth" value="${mem.memBth}" required><br><br>
        
        <label for="memSex">性別:</label>
        <input type="radio" id="memSex" name="memSex" value="1"> 男
        <input type="radio" id="memSex" name="memSex" value="2"> 女<br><br>
        
        <label for="memEmail">電子郵件:</label>
        <input type="email" id="memEmail" name="memEmail" value="${mem.memEmail}" required><br><br>
        
        <label for="memTel">手機電話:</label>
        <input type="tel" id="memTel" name="memTel" value="${mem.memTel}" required><br><br>
        
        <label for="memAdd">地址:</label>
        <input type="text" id="memAdd" name="memAdd" value="${mem.memAdd}" required><br><br>
        
        <label for="accStatus" >狀態:</label>
        <select id="accStatus" name="accStatus"  >
            <option value="1" >正常使用中</option>
<!--             <option value="0"  style="display: none;">停用</option> -->
        </select><br><br>
        
<!--         <label for="memPoint">會員點數:</label> -->
<%--         <input type="number" id="memPoint" name="memPoint" value="${mem.memPoint}" ><br><br> --%>
        
        <label for="memImage">會員圖片:</label>
        <input type="file" id="memImage" name="memImage"><br><br>
        <input type="hidden" name="base64Image" value="${base64Image}">
        
        <input type="submit" value="加入會員">
        <input type="hidden" name="action" value="save">
    </form>
</body>
</html>
