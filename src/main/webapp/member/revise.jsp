<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   MemVO memvo = (MemVO) request.getAttribute("memId");
%>

<html>
<head>
    <title>修改會員資料 revise.jsp</title>
</head>
<body>
    <h1 align="center">修改會員資料</h1>
    <table border="1" align="center" width="50%">
        <tr>
            <th>會員編號</th>
            <td>${mem.memId}</td>
        </tr>
        <tr>
            <th>會員大頭貼</th>
            <td><img src="data:image/jpeg;base64, ${base64Image}" ></td>
        </tr>
        <tr>
            <th>帳號</th>
            <td><input type="text" name="memId" value="${mem.memAcc}"></td>
        </tr>
        <tr>
            <th>密碼</th>
            <td><input type="text" name="memId" value="${mem.memPwd}"></td>
        </tr>
        <tr>
            <th>會員姓名</th>
            <td><input type="text" name="memId" value="${mem.memName}"></td>
        </tr>
        <tr>
            <th>身分證字號</th>
            <td><input type="text" name="memId" value="${mem.memIdentity}"></td>
        </tr>
        <tr>
            <th>生日</th>
            <td><input type="text" name="memId" value="${mem.memBth}"></td>
        </tr>
        <tr>
            <th>性別</th>
            <td>${mem.memSex}</td>
        </tr>
        <tr>
            <th>電子郵件</th>
            <td><input type="text" name="memId" value="${mem.memEmail}"></td>
        </tr>
        <tr>
            <th>手機電話</th>
            <td><input type="text" name="memId" value="${mem.memTel}"></td>
        </tr>
        <tr>
            <th>地址</th>
            <td><input type="text" name="memId" value="${mem.memAdd}"></td>
        </tr>
    </table>
    <p align="center">
        <input type="button" value="儲存會員資料" onclick="save()" />
        <input type="button" value="取消" onclick="history.back()">
    </p>
    <script type="text/javascript">
        function save() {
            // 導向到修改頁面
            document.location.href = "${pageContext.request.contextPath}/member/revise.jsp";
        }
        
//         function cancel() {
//             // 導向回success頁面
//             document.location.href = "${pageContext.request.contextPath}/member/success.jsp";
//         }
    </script>
</body>
</html>
