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

<title>專欄列表</title>

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

<!-- 	<div class="container mt-5"> -->

<!-- 		<!-- 三個查詢表單並排顯示 --> 
<!-- 		<div class="row"> -->

<!-- 			<!-- 查詢票券名稱 注意：forEach的是未分頁的全部資料items=ticketListAll、option的value是實際傳遞值、第二的表單顯示的資料 --> 
<!-- 			<div class="col-md-4"> -->
<!-- 				<form method="get" -->
<%-- 					action="<%=request.getContextPath()%>/ticketmg/find"> --%>
<!-- 					<label for="ticketId">票券名稱</label> <select id="ticketId" -->
<!-- 						name="ticketId" class="form-control mb-2"> -->
<%-- 						<c:forEach var="ticket" items="${ticketListAll}"> --%>
<%-- 							<option value="${ticket.ticketId}">${ticket.ticketName}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> <input type="submit" value="查詢" class="btn btn-primary"> -->
<!-- 				</form> -->
<!-- 			</div> -->

<!-- 			<!-- 查詢票券類型 --> 
<!-- 			<div class="col-md-4"> -->
<!-- 				<form method="get" -->
<%-- 					action="<%=request.getContextPath()%>/ticketmg/find"> --%>
<!-- 					<label for="ticketTypeId">票券類型</label> <select id="ticketTypeId" -->
<!-- 						name="ticketTypeId" class="form-control mb-2"> -->
<%-- 						<c:forEach var="typeItem" items="${uniqueTicketTypes}"> --%>
<%-- 							<option value="${typeItem.ticketTypeId}">${typeItem.typeName}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> <input type="submit" value="查詢" class="btn btn-primary"> -->
<!-- 				</form> -->
<!-- 			</div> -->

<!-- 			<!-- 查詢票券區域  注意name是要查詢的值(servlet的查詢,areaId)--> 
<!-- 			<div class="col-md-4"> -->
<!-- 				<form method="get" -->
<%-- 					action="<%=request.getContextPath()%>/ticketmg/find"> --%>
<!-- 					<label for="cityId">票券區域</label> <select id="cityId" name="areaId" -->
<!-- 						class="form-control mb-2"> -->
<%-- 						<c:forEach var="typeItem" items="${uniqueTicketArea}"> --%>
<%-- 							<option value="${typeItem.cityId}">${typeItem.cityName}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> <input type="submit" value="查詢" class="btn btn-primary"> -->
<!-- 				</form> -->
<!-- 			</div> -->

<!-- 		</div> -->
<!-- 		<!-- end of row --> 


<!-- 		<div class="container mt-5"> -->
<!-- 			<!-- 票券名稱模糊查詢 --> 
<!-- 			<div class="row mb-3"> -->
<!-- 				<div class="col-md-12 col-sm-12 mb-2"> -->
<!-- 					<form method="get" -->
<%-- 						action="<%=request.getContextPath()%>/ticketmg/find"> --%>
<!-- 						<div class="input-group"> -->
<!-- 							<input type="text" id="ticketName" name="ticketName" -->
<!-- 								class="form-control" placeholder="票券名稱" -->
<%-- 								value="${ticket.ticketName}"> <span --%>
<!-- 								class="input-group-btn"> -->
<!-- 								<button class="btn btn-primary" type="submit">查詢</button> -->
<!-- 							</span> -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 					<form method="get" -->
<%-- 						action="<%=request.getContextPath()%>/ticketmg/add"> --%>
<!-- 						<button class="btn btn-primary" type="submit">新增</button> -->
<!-- 					</form> -->
<!-- 				</div> -->

<!-- 			</div> -->
<!-- 		</div> -->

		<h1>專欄列表</h1>

		<!-- 表格 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>文章編號</th>
					<th>專欄類型</th>
					<th>圖片</th>
					<th>專欄標題</th>
					<th>專欄文章</th>
					<th>發布日</th>
					<th>管理者</th>
					<th>狀況</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 所有資料 -->
				<c:forEach var="column" items="${columnList}" varStatus="status">					
						<!-- 用當前頁數和每頁的數量計算項目序號 -->
						<td>${columnarticles.artiId}</td>
						<td>${columnarticles.artiTypeId}</td>
						<td><img
							src="<%=request.getContextPath()%>"
							alt="Main Image"></td>
						<td>${columnarticles.artiTitle}</td>
						<td><c:choose>
								<c:when test="${fn:length(columnarticles.articontent) > 30}">
								${fn:substring(columnarticles.articontent,0,30)}...
								</c:when>
								<c:otherwise>
								${columnarticles.articontent}
								</c:otherwise>
							</c:choose></td>
						<td>${columnarticles.articleDate}</td>
						<td>${columnarticles.adminId}</td>
						<td><c:choose>
								<c:when test="${columnarticles.artiStatus ==1}">上架</c:when>
								<c:otherwise>未上架</c:otherwise>
							</c:choose></td>
						<td><a
							href="${pageContext.request.contextPath}/ticketmg/edit?ticketId=${ticket.ticketId}"
							class="btn btn-primary btn-sm">修改</a> <a
							href="${pageContext.request.contextPath}/ticketmg/del?ticketId=${ticket.ticketId}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('確定刪除？不要亂刪喔！');">删除</a></td>
					</tr>
				</c:forEach>
		</table>

		<!-- 分頁 -->
		<div>
			<nav>
				<ul class="pagination justify-content-center">
					<!-- "至第一頁" 只在非第一頁時顯示 -->
					<c:if test="${currentPage > 1}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/columnmg/list?page=1">第一頁</a>
						</li>
					</c:if>

					<!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
					<c:if test="${currentPage - 1 != 0}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/columnmg/list?page=${currentPage - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
					<c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link"
							href="${pageContext.request.contextPath}/columnmg/list?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
					<c:if test="${currentPage + 1 <= pageQty}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/columnmg/list?page=${currentPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>

					<!-- "至最後一頁" 只在非最後一頁時顯示 -->
					<c:if test="${currentPage != pageQty}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/columnmg/list?page=${pageQty}">尾頁</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>

		<!-- 回首頁 -->
		<div class="page-item">
			<a class="page-link"
				href="${pageContext.request.contextPath}/columnmg/">回首頁</a>
		</div>
</body>
</html>
