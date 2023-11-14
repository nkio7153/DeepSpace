<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<title>選擇票券體驗</title>
<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet"
	href="<c:url value='/static/css/frontendlist.css'/>">


</head>
<body>
	<div class="container mt-5">

		<div class="row">

			<!-- 右側內容 -->
				<div class="d-flex justify-content-between align-items-center mb-3">

					<c:choose>
						<c:when test="${not empty searchCount}">
							<h3 class="mb-0">搜尋結果 ${searchCount} 項票券</h3>
						</c:when>
						<c:otherwise>
							<h3 class="mb-0">共有 ${totalTickets} 項票券體驗</h3>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- 票券列表 -->
				<div class="ticket-list">
					<c:forEach items="${resultSet}" var="ticket">
						<a
							href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=${ticket.ticketId}"
							class="no-underline"> <!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="<%=request.getContextPath()%>/ticketmainimage?ticketId=${ticket.ticketId}"
											alt="Main Ticket Image" class="ticket-img">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${ticket.ticketName}</h5>
											<p class="card-title">${ticket.ticketType.typeName}|
												${ticket.city.cityName}</p>
											<p class="card-title">
												<c:choose>
													<c:when test="${fn:length(ticket.description) > 30}">
								${fn:substring(ticket.description,0,30)}...
								</c:when>
													<c:otherwise>
								${ticket.description}
								</c:otherwise>
												</c:choose>
											</p>
											<p class="card-text">
												<small class="text-muted">
													${averageStarsMap[ticket.ticketId]} <!-- 實星 --> <c:forEach
														begin="1" end="${averageStarsMap[ticket.ticketId]}"
														var="i">
														<i class="fas fa-star gold-star"></i>
													</c:forEach> <!-- 半星 --> <c:if
														test="${averageStarsMap[ticket.ticketId] % 1 != 0}">
														<i class="fas fa-star-half-alt gold-star"></i>
														<!-- 有半星就+ -->
														<c:set var="emptyStarsStart"
															value="${Math.floor(averageStarsMap[ticket.ticketId]) + 2}" />
													</c:if> <!-- 沒有半星就往下一個數 --> <c:if
														test="${averageStarsMap[ticket.ticketId] % 1 == 0}">
														<c:set var="emptyStarsStart"
															value="${averageStarsMap[ticket.ticketId] + 1}" />
													</c:if> <!-- 空星 --> <c:forEach begin="${emptyStarsStart}" end="5"
														var="j">
														<i class="far fa-star gold-star"></i>
													</c:forEach> (${totalRatingCountMap[ticket.ticketId]})
													銷售量${ticketOrderCountMap[ticket.ticketId]}
												</small>
											</p>
											<p class="card-text">NT$ ${ticket.price}</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	<%-- 分頁 若是全列表則執行以下分頁--%>
	<c:if test="${empty searchCount}">
		<div>
			<nav>

				<ul class="pagination justify-content-center">
					<!-- "至第一頁" 只在非第一頁時顯示 -->
					<c:if test="${currentPage > 1}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketproduct/list?page=1">第一頁</a>
						</li>
					</c:if>

					<!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
					<c:if test="${currentPage - 1 != 0}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketproduct/list?page=${currentPage - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
					<c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link"
							href="${pageContext.request.contextPath}/ticketproduct/list?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
					<c:if test="${currentPage + 1 <= ticketPageQty}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketproduct/list?page=${currentPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>

					<!-- "至最後一頁" 只在非最後一頁時顯示 -->
					<c:if test="${currentPage != ticketPageQty}">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/ticketproduct/list?page=${ticketPageQty}">尾頁</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</c:if>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>