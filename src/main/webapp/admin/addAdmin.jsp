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
        }
    </style>
</head>
<body>
    <h1>註冊會員</h1>
    <form action="${pageContext.request.contextPath}/ad/save" method="post" enctype="multipart/form-data">
        <label for="adminAcc">帳號:</label>
        <input type="text" id="adminAcc" name="adminAcc" value="" required><br><br>
        
        <label for="adminPwd">密碼:</label>
        <input type="password" id="adminPwd" name="adminPwd" value="" required><br><br>
        
        <label for="adminName">管理員姓名:</label>
        <input type="text" id="adminName" name="adminName" value="" required><br><br>
        
        <label for="adminStatus" >狀態:</label>
        <select id="adminStatus" name="adminStatus"  >
            <option value="1" >正常使用中</option>
<!--             <option value="0"  style="display: none;">停用</option> -->
        </select><br><br>
        
        <input type="submit" value="加入管理員">
        <input type="hidden" name="action" value="save">
    </form>
</body>
</html>
