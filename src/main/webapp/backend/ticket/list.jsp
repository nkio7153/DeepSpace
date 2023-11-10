<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">	
<link rel="stylesheet"	href="<c:url value='/static/css/backendlist.css'/>">

<title>票券列表</title>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
//滑鼠滑過行
$(document).ready(function(){
   $('table tbody tr').hover(function(){
       $(this).addClass('hover-highlight');
   }, function(){
       $(this).removeClass('hover-highlight'); 
   });
});

</script>

</head>
<body>

	<div class="container mt-5">

		<!-- 三個查詢表單並排顯示 -->
		<div class="row">

			<!-- 查詢票券名稱 注意：forEach的是未分頁的全部資料items=ticketListAll、option的value是實際傳遞值、第二的表單顯示的資料 -->
			<div class="col-md-4">
				<form method="get"
					action="<%=request.getContextPath()%>/ticketmg/find">
					<label for="ticketId">票券名稱</label> <select id="ticketId"
						name="ticketId" class="form-control mb-2">
						<c:forEach var="ticket" items="${ticketListAll}">
							<option value="${ticket.ticketId}">${ticket.ticketName}</option>
						</c:forEach>
					</select> <input type="submit" value="查詢" class="btn btn-primary">
				</form>
			</div>

			<!-- 查詢票券類型 -->
			<div class="col-md-4">
				<form method="get"
					action="<%=request.getContextPath()%>/ticketmg/find">
					<label for="ticketTypeId">票券類型</label> <select id="ticketTypeId"
						name="ticketTypeId" class="form-control mb-2">
						<c:forEach var="typeItem" items="${uniqueTicketTypes}">
							<option value="${typeItem.ticketTypeId}">${typeItem.typeName}</option>
						</c:forEach>
					</select> <input type="submit" value="查詢" class="btn btn-primary">
				</form>
			</div>

			<!-- 查詢票券區域  注意name是要查詢的值(servlet的查詢,areaId)-->
			<div class="col-md-4">
				<form method="get"
					action="<%=request.getContextPath()%>/ticketmg/find">
					<label for="cityId">票券區域</label> <select id="cityId" name="areaId"
						class="form-control mb-2">
						<c:forEach var="areaItem" items="${uniqueTicketArea}">
							<option value="${areaItem.cityId}">${areaItem.cityName}</option>
						</c:forEach>
					</select> <input type="submit" value="查詢" class="btn btn-primary">
				</form>
			</div>

		</div>
		<!-- end of row -->


		<div class="container mt-5">
			<!-- 票券名稱模糊查詢 -->
			<div class="row mb-3">
				<div class="col-md-12 col-sm-12 mb-2">
					<form method="get"
						action="<%=request.getContextPath()%>/ticketmg/find">
						<div class="input-group">
							<input type="text" id="ticketName" name="ticketName"
								class="form-control" placeholder="票券名稱"
								value="${ticket.ticketName}"> <span
								class="input-group-btn">
								<button class="btn btn-primary" type="submit">查詢</button>
							</span>
						</div>
					</form>
					<!-- 新增票券 -->
					<form method="get"
						action="<%=request.getContextPath()%>/ticketmg/add">
						<button class="btn btn-primary" type="submit">新增</button>
					</form>
				</div>

			</div>
		</div>

		<h1>票券列表</h1>

		<!-- 表格 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>項次</th>
					<th>區域</th>
					<th>類型</th>
					<th>編號</th>
					<th>圖片</th>
					<th>名稱</th>
					<th>價格</th>
					<th>數量</th>
					<th>描述</th>
					<th>發布日</th>
					<th>狀況</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 票券所有資料 -->
				<c:forEach var="ticket" items="${ticketList}" varStatus="status">
					<tr>
						<!-- 用當前頁數和每頁的數量計算項目序號 -->
						<td>${itemsPerPage * (currentPage - 1) + status.index + 1}</td>
						<td>${ticket.city.cityName}</td>
						<td>${ticket.ticketType.typeName}</td>
						<td>${ticket.ticketId}</td>
						<td><img
							src="<%=request.getContextPath()%>/ticketmainimage?ticketId=${ticket.ticketId}"
							alt="Main Ticket Image"></td>
						<td>${ticket.ticketName}</td>
						<td>${ticket.price}</td>
						<td>${ticket.stock}</td>
						<td><c:choose>
								<c:when test="${fn:length(ticket.description) > 30}">
								${fn:substring(ticket.description,0,30)}...
								</c:when>
								<c:otherwise>
								${ticket.description}
								</c:otherwise>
							</c:choose></td>
						<td>${ticket.publishedDate}</td>
						<td><c:choose>
								<c:when test="${ticket.status ==1}">上架</c:when>
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
							href="${pageContext.request.contextPath}/ticketmg/list?page=1">第一頁</a>
						</li>
					</c:if>

					<!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
					<c:if test="${currentPage - 1 != 0}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketmg/list?page=${currentPage - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
					<c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link"
							href="${pageContext.request.contextPath}/ticketmg/list?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
					<c:if test="${currentPage + 1 <= ticketPageQty}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketmg/list?page=${currentPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>

					<!-- "至最後一頁" 只在非最後一頁時顯示 -->
					<c:if test="${currentPage != ticketPageQty}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketmg/list?page=${ticketPageQty}">尾頁</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>

		<!-- 回首頁 -->
		<div class="page-item">
			<a class="page-link"
				href="${pageContext.request.contextPath}/ticketmg/list">回首頁</a>
		</div>
</body>
</html>
