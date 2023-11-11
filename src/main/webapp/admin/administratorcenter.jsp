<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理員中心</title>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = "<%=request.getContextPath()%>/admin/adminUpdate.jsp?adminId=" + jsonData.adminId ;
  }
</script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.4.1/font/bootstrap-icons.min.css">
<style>
body {
	background-color: #FFF3E0;
}

.card, .navbar, .navbar-collapse {
	background-color: sandybrown;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand" href="#">管理員中心</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="#">首頁
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">訓練師個人資料管理</a></li>
				<li class="nav-item"><a class="nav-link" href="#">管理員個人資料管理</a></li>
				<li class="nav-item">
					<form method="POST"
						action="${pageContext.request.contextPath}/logout1"
						style="all: unset;">
						<input type="hidden" name="action" value="adminlogout">
						<button class="nav-link btn btn-link"
							style="color: inherit; text-decoration: none;" id="logoutButton"
							type="submit">登出</button>
					</form>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">
		<div class="row">
			<div class="col-12 col-md-4">
				<div class="card">
					<div class="card-header">
						<!-- 再次使用 sessionScope 來獲取用戶名 -->
						您好，
						<c:out value="${admin.adminName}！" />
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<form method="post"
								action="${pageContext.request.contextPath}/admin.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="action1" value="update"> <input
									type="hidden" name="adminId" value="${admin.adminId}"> <input
									type="button" class="update-btn" value="修改"
									onclick="processUpdate({adminId:'${admin.adminId}'});">
							</form>
						</li>
						<li class="list-group-item">變更密碼</li>
					</ul>
				</div>
			</div>
			<div class="col-12 col-md-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">個人資料</h5>
						<table class="table table-bordered table-hover">
							<tbody>
								<tr>
									<th scope="row">管理員編號</th>
									<td>${admin.adminId}</td>
								</tr>
								<tr>
									<th scope="row">姓名</th>
									<td>${admin.adminName}</td>
								</tr>
								<tr>
									<th scope="row">密碼</th>
									<td>${admin.adminPwd}</td>
								</tr>
								<tr>
									<th scope="row">狀態</th>
									<td>${admin.adminStatus}</td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
