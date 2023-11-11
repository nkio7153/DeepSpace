<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.depthspace.admin.model.model.*" %>
<%
	AdminVO admin = (AdminVO) request.getAttribute("admin");
%>
<html>
<head>
<!-- 日期的套版 -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
<meta charset="UTF-8">
<title>admin</title>

<style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      margin: 0;
      padding: 0;
    }

    form {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th, td {
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    th {
      text-align: right;
      font-weight: bold;
    }

    td {
      text-align: left;
    }
   

    input[type="text"],
    input[type="password"],
    input[type="file"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    input[type="radio"] {
      margin-right: 10px;
    }

    button[type="submit"],
    input[type="button"] {
      display: inline-block;
      padding: 10px 20px;
      background-color: #3498db;
      color: #fff;
      border: none;
      border-radius: 5px;
      text-align: center;
      text-decoration: none;
      font-size: 16px;
      cursor: pointer;
      margin-top: 10px;
    }

    button[type="submit"]:hover,
    input[type="button"]:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>
<!-- request.getContextPath()動態根路徑，action=add找到後端switch(action)的add-->
	<form method="post" action="<%=request.getContextPath()%>/admin.do?action=add" accept-charset="UTF-8" enctype="multipart/form-data">
		<table>
		<tr>
			<th>管理員名字</th>
			<td>
				<input type="text" name="ADMIN_NAME" id="ADMIN_NAME" required >
			</td>
		</tr>
		<tr>
			<th>管理員帳號</th>
			<td>
				<input type="text" name="ADMIN_ACC" id="ADMIN_ACC" required>
			</td>
		</tr>
		<tr>
			<th>管理員密碼</th>
			<td>
				<input type="password" name="ADMIN_PWD" id="ADMIN_PWD" required>
			</td>
		</tr>
		<tr>
			<th>管理員帳號狀態</th>
			<td>
				<input type="radio" name="ADMIN_STATUS" value="0">離職
				<input type="radio" name="ADMIN_STATUS" value="1" checked>在職
				<input type="radio" name="ADMIN_STATUS" value="2">停職
			</td>
		</tr>
		</table>

		
		<button type="submit">送出</button>
		<!-- 		點擊取消後跳轉回去首頁 -->
		<input  type="button" onclick="window.location.href='<%=request.getContextPath()%>/admin/admin1.jsp'" value="取消">
	</form>
</body>
</html>