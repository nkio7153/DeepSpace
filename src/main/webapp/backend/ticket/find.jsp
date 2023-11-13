<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
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

	<h1>票券搜尋結果</h1>
	<div class="container mt-5">
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
				<!-- 符合搜尋結果的票券資料 var要填-->
				<c:forEach items="${list}" var="ticket" varStatus="ticketStatus">
					<tr>
						<td>${ticketStatus.count}</td>
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
						<td>${ticket.city.cityName}</td>
						<td><a
							href="${pageContext.request.contextPath}/ticketmg/edit?ticketId=${ticket.ticketId}"
							class="btn btn-primary btn-sm">修改</a> <a
							href="${pageContext.request.contextPath}/ticketmg/del?ticketId=${ticket.ticketId}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('確定刪除？不要亂刪喔！');">删除</a></td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<!-- 回首頁 -->
	<div class="page-item">
		<a class="page-link"
			href="${pageContext.request.contextPath}/ticketmg/list">回票券總列表</a>
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