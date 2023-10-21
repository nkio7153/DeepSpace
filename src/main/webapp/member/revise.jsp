<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <tr>
            <th>狀態</th>
            <td>${mem.accStatus}</td>
        </tr>
        <tr>
            <th>點數</th>
            <td>${mem.memPoint}</td>
        </tr>
        
    </table>
    <p align="center">
<%--     <form align="center" action="${pageContext.request.contextPath}/mem/success.jsp" method="post" > --%>
<!-- 		<input type="submit" value="儲存會員資料"> -->
<!-- 		<input type="hidden" name="action"	value="modify"> -->
	</form>
        <input type="button" value="儲存會員資料" onclick="save()" />
        <input type="button" value="取消" onclick="history.back()">
    </p>
    <script type="text/javascript">
        function save() {
        	 // 獲取修改後的資料
            var modifiedData = {
                // 這裡放入你的修改後的資料
                memId: document.getElementById("memId").value;
            	base64Image: document.getElementById("base64Image").value;
	            memAcc: document.getElementById("memAcc").value;
	            memPwd: document.getElementById("memPwd").value;
                memName: document.getElementById("memName").value;
	            memIdentity: document.getElementById("memIdentity").value;
	            memBth: document.getElementById("memBth").value;
	            memSex: document.getElementById("memSex").value;
	            memEmail: document.getElementById("memEmail").value;
	            memTel: document.getElementById("memTel").value;
	            memAdd: document.getElementById("memAdd").value;
//                 memName: document.getElementById("memId").value;
//                 memName: document.getElementById("memId").value;
                
            };

            // 存儲修改後的資料到 Local Storage
            localStorage.setItem('modifiedData', JSON.stringify(modifiedData));

            // 重導向到 success.jsp
            document.location.href = "${pageContext.request.contextPath}/mem/success";
        }
        
//         function cancel() {
//             // 導向回success頁面
//             document.location.href = "${pageContext.request.contextPath}/member/success.jsp";
//         }
    </script>
</body>
</html>
