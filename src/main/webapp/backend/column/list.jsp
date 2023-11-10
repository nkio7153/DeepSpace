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

<title>專欄列表</title>
<%--  include --%>
	<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  
</head>

<body>

	<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
	<div class="container-fluid my-0">
	<div class="row">
	  
	<div class="col-lg-2 g-3 my-0">
	<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
	</div>
	
	<div class="col-lg-10 g-2 transparent rounded my-0">
	
<%-- include end--%>

<div class="table-list">

	<div class="container mt-5">
		<!-- 新增 -->
		<form method="get" action="<%=request.getContextPath()%>/columnmg/add">
			<button class="btn btn-primary" type="submit">新增</button>
		</form>

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
					<th>發布者</th>
					<th>狀況</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 所有資料 -->
				<tr>
					<c:forEach var="column" items="${columnList}" varStatus="status">
						<!-- 用當前頁數和每頁的數量計算項目序號 var.關聯變數.對方要呈現的變數-->
						<td>${column.artiId}</td>
						<td>${column.colType.colTypeName}</td>
						<td><img
							src="<%=request.getContextPath()%>/columnmainimage?artiId=${column.artiId}"
							alt="Main Image"></td>
						<td>${column.artiTitle}</td>
						<td><c:choose>
								<c:when test="${fn:length(column.artiContent) > 30}">
								${fn:substring(column.artiContent,0,30)}...
								</c:when>
								<c:otherwise>
								${column.artiContent}
								</c:otherwise>
							</c:choose></td>
						<td>${column.articleDate}</td>
						<td>${column.admin.adminName}</td>
						<td><c:choose>
								<c:when test="${column.artiStatus ==1}">上架</c:when>
								<c:otherwise>未上架</c:otherwise>
							</c:choose></td>
						<td><a
							href="${pageContext.request.contextPath}/columnmg/edit?artiId=${column.artiId}"
							class="btn btn-primary btn-sm">修改</a> </td>
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
		</div>
<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
</body>
</html>
