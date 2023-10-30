<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<title>票券列表</title>

<style>

/* 自定義樣式：縮小字體大小和行距 */
body {
	font-size: 0.9rem;
	line-height: 1.5;
}

img {
	max-width: 100px;
	max-height: 100px;
	display: block;
	margin: 0 auto;
}

th {
	white-space: nowrap; /* 表頭文字不換行 */
	vertical-align: middle;
	text-align: center;
}

td {
	/* 移除 td 的 white-space 屬性，允許內容自然斷行 */
	vertical-align: middle;
	text-align: center;
}
/* 避免橫向滾動設置表格最大寬度 */
.table {
	width: 100%; /* 使表格寬度為100%，確保它在容器內自適應 */
	max-width: 100%; /* 這確保表格不會超出父容器 */
	overflow-x: auto; /* 若内容超出可滾動 */
}
</style>

<!-- 引入Bootstrap JS 和 Popper.js -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</head>
<body>

	<!-- 查詢票券選單 
	注意：forEach的是未分頁的全部資料items=ticketListAll
	option的value是實際傳遞值、第二的表單顯示的資料-->
	<form method="get"
		action="<%=request.getContextPath()%>/backendticket/mgfind">
		<label for="ticketId">票券名稱</label> <select id="ticketId"
			name="ticketId">
			<c:forEach var="ticket" items="${ticketListAll}">
				<option value="${ticket.ticketId}">${ticket.ticketName}</option>
			</c:forEach>
		</select> <input type="submit" value="查詢">
	</form>

	<!-- 查詢票券類型選單  此處使用的是ticketTypeVO -->
	<form method="get"
		action="<%=request.getContextPath()%>/backendticket/mgfind">
				<label for="ticketTypeId">票券類型</label> <select id="ticketTypeId"
			name="ticketTypeId">
			<c:forEach var="typeItem" items="${uniqueTicketTypes}">
				<option value="${typeItem.ticketTypeId}">${typeItem.typeName}</option>
			</c:forEach>
		</select> <input type="submit" value="查詢">
	</form>
	
		<!-- 查詢票券區域選單 -->
	<form method="get"
		action="<%=request.getContextPath()%>/backendticket/mgfind">
		<label for="ticketCityName">票券區域</label> <select id="ticketCityName"
			name="ticketCityName">
			<c:forEach var="typeItem" items="${uniqueTicketArea}">
				<option value="${typeItem.cityId}">${typeItem.cityName}</option>
			</c:forEach>
		</select> <input type="submit" value="查詢">
	</form>

	<div class="container mt-5">
		<!-- 票券名稱模糊查詢 -->
		<div class="row mb-3">
			<div class="col-md-12 col-sm-12 mb-2">
				<form method="get"
					action="<%=request.getContextPath()%>/backendticket/mgfind">
					<div class="input-group">
						<input type="text" id="ticketName" name="ticketName"
							class="form-control" placeholder="票券名稱"
							value="${ticket.ticketName}"> <span
							class="input-group-btn">
							<button class="btn btn-primary" type="submit">搜索</button>
						</span>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

					</div>
				</form>
			</div>
		</div>

		<h1>票券列表</h1>

		<!-- 表格 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>項次</th>
					<th>類型</th>
					<th>編號</th>
					<th>圖片</th>
					<th>名稱</th>
					<th>價格</th>
					<th>數量</th>
					<th>描述</th>
					<th>發布日</th>
					<th>狀況</th>
					<th>區域</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 票券所有資料 -->
				<c:forEach var="ticket" items="${ticketList}" varStatus="status">
					<tr>
						<!-- 用當前頁數和每頁的數量計算項目序號 -->
						<td>${itemsPerPage * (currentPage - 1) + status.index + 1}</td>
						<td>${ticket.ticketType.typeName}</td>
						<td>${ticket.ticketId}</td>
						<td><img
							src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}&isMainImage=1"
							alt="Main Ticket Image"></td>
						<td>${ticket.ticketName}</td>
						<td>${ticket.price}</td>
						<td>${ticket.stock}</td>
						<td>${ticket.description}</td>
						<td>${ticket.publishedDate}</td>
						<td>${ticket.status}</td>
						<td>${ticket.city.cityName}</td>
						<td><a
							href="${pageContext.request.contextPath}/backendticket/mgedit?ticketId=${ticket.ticketId}"
							class="btn btn-primary btn-sm">修改</a> <a
							href="${pageContext.request.contextPath}/backendticket/mgdel?ticketId=${ticket.ticketId}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('確定刪除？不要亂刪喔！');">删除</a></td>
					</tr>
				</c:forEach>


				<!-- 分頁 -->
				<div>
					<nav>
						<ul class="pagination justify-content-center">
							<!-- "至第一頁" 只在非第一頁時顯示 -->
							<c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/backendticket/mgList?page=1">首頁</a>
								</li>
							</c:if>

							<!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
							<c:if test="${currentPage - 1 != 0}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/backendticket/mglist?page=${currentPage - 1}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:if>

							<!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
							<c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
								<li class="page-item ${i == currentPage ? 'active' : ''}">
									<a class="page-link"
									href="${pageContext.request.contextPath}/backendticket/mglist?page=${i}">${i}</a>
								</li>
							</c:forEach>

							<!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
							<c:if test="${currentPage + 1 <= ticketPageQty}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/backendticket/mglist?page=${currentPage + 1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:if>

							<!-- "至最後一頁" 只在非最後一頁時顯示 -->
							<c:if test="${currentPage != ticketPageQty}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/backendticket/mglist?page=${ticketPageQty}">尾頁</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>

				<!-- 回首頁 -->
				<div class="page-item">
					<a class="page-link"
						href="${pageContext.request.contextPath}/backendticket/">回首頁</a>
				</div>
</body>
</html>
