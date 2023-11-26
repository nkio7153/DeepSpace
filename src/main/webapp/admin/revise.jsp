<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<%--  include head.jsp--%>
<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<title>修改管理員資料 revise.jsp</title>
<style>

/*   #preview span.text{ */
/*         position: absolute; */
/*         display: inline-block; */
/*         left: 50%; */
/*         top: 50%; */
/*         transform: translate(-50%, -50%); */
/*         color: lightgray; */
/*       } */
</style>
</head>
<body>
	<%--include header.jsp--%>
	<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
	<div class="container-fluid my-0">
		<div class="row">
			<%--    側邊欄--%>
			<div class="col-lg-2 g-3 mt-1">
				<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
			</div>

			<div class="col-lg-10 g-2 transparent rounded mt-1">
				<%--      放入自己body裡的代碼--%>
				<h1 align="center">修改管理員資料</h1>
				<form align="center"
					action="${pageContext.request.contextPath}/ad/modify" method="post"
					enctype="multipart/form-data">
					<table border="1" align="center" width="50%">

						<th style="display: none;">管理員編號</th>
						<input type="hidden" name="adminId" value="${param.adminId}"readonly">

						<tr>
							<th>帳號</th>
							<td><input type="text" name="adminAcc"
								value="${param.adminAcc}" readonly></td>
						</tr>
					
							
							<input type="hidden" name="adminPwd" value="${param.adminPwd}" required>
						
						<tr>
							<th>管理員姓名</th>
							<td><input type="text" name="adminName"
								value="${param.adminName}" required></td>
						</tr>
						
						<tr>
        <th>權限</th>
        <td>
            <select name="adminFuncName">
                <option value="1" ${adminFuncName == 1 ? 'selected' : ''}>餐廳管理員</option>
                <option value="2" ${adminFuncName == 2 ? 'selected' : ''}>總管理員</option>
            </select>
        </td>
    </tr>
						
					</table>
					<p align="center">
						<input type="submit" value="儲存管理員資料"> <input type="hidden"
							name="action" value="modify"> <input type="button"
							value="取消" onclick="history.back()">

					</p>
				</form>
				<script type="text/javascript">
					
				</script>

			</div>
		</div>
	</div>

</body>
</html>
