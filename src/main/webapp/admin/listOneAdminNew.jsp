<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.depthspace.admin.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<title>員工資料</title>
<style>
body {
	font-family: 'Poppins', sans-serif;
	background: linear-gradient(45deg, #c5deea 0%, #8abbd7 31%, #066dab 100%);
}

.box-area {
	width: 1230px;
}

th {
    text-align: center;
}

img { 
     max-width: 100px;
     height: 50px; 
 } 
</style>
</head>
<body>
	<div
		class="container d-flex justify-content-center align-items-center min-vh-100">
		<div class="row border rounded-5 p-3 bg-white shadow box-area">
			<div class="header-text mb-1 g-2 text-center">
				<div class="d-flex align-items-center justify-content-between">
					<h3 style="margin-top: 0; margin: auto;">員工資料</h3>
					<button class="btn btn-primary" type="button" id="mainPage">首頁</button>
				</div>
			</div>
			<div class="card">
				<div class="col-lg-12 g-2">
					<div class="card-header">
						員工列表
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-box-fill" viewBox="0 0 16 16"
							style="float: right;">
                            <path fill-rule="evenodd"
								d="M15.528 2.973a.75.75 0 0 1 .472.696v8.662a.75.75 0 0 1-.472.696l-7.25 2.9a.75.75 0 0 1-.557 0l-7.25-2.9A.75.75 0 0 1 0 12.331V3.669a.75.75 0 0 1 .471-.696L7.443.184l.004-.001.274-.110a.75.75 0 0 1 .558 0l.274.110.004.001 6.971 2.789Zm-1.374.527L8 5.962 1.846 3.5 1 3.839v.4l6.5 2.6v7.922l.5.2.5-.2V6.84l6.5-2.6v-.4l-.846-.339Z" />
                        </svg>
					</div>
				</div>
				<div style="margin-top: 10px;">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>員工編號</th>
								<th>員工帳號</th>
								<th>員工密碼</th>
								<th>員工姓名</th>
								<th>員工信箱</th>
								<th>員工電話</th>
								<th>到職日</th>
								<th>員工權限</th>
								<th>照片</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>${AdminVO.adminNo}</th>
								<th>${AdminVO.adminAccount}</th>
								<th>${AdminVO.adminPassword}</th>
								<th>${AdminVO.adminName}</th>
								<th>${AdminVO.adminEmail}</th>
								<th>${AdminVO.adminPhone}</th>
								<th>${AdminVO.createDate}</th>
								<th>
									<c:choose>
										<c:when test="${AdminVO.adminStat == 1}">一般</c:when>
										<c:when test="${AdminVO.adminStat == 2}">管理員</c:when>
										<c:when test="${AdminVO.adminStat == 3}">停權</c:when>
										<%--<c:otherwise>離職</c:otherwise>--%>
									</c:choose>
						        </th>
								<th>
									<img src="${pageContext.request.contextPath}/ReadIMG?id=${AdminVO.adminNo}">
								</th>
								<th class="text-center">
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/admin/admin.do">
										<button class="btn btn-success" type="submit">修改</button>
										<input type="hidden" name="adminNo" value="${AdminVO.adminNo}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/admin/admin.do">
										<button class="btn btn-danger" type="submit">刪除</button>
										<input type="hidden" name="adminNo" value="${AdminVO.adminNo}">
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
								</th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
		let mainPage = document.getElementById('mainPage');
		// 添加典籍事件處理程序
		mainPage.addEventListener('click', function() {
			// 執行頁面導航，跳轉到 addAdmin.jsp
			window.location.href = 'adminSystem.jsp';
		});
	</script>
	<script src="../js/popper.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>
