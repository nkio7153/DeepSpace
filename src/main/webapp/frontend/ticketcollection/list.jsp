<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>收藏票券列表</title>

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

			<div class="col-md-9">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h3 class="mb-0">共收藏 ${totalTickets} 項票券</h3>
					<form action="<%=request.getContextPath()%>/ticketcollection/list"
						method="get">
						<!-- 查詢票券區域  注意name是要查詢的值(servlet的查詢,areaId)-->
						<div class="col-md-9">
							<div
								class="d-flex justify-content-between align-items-center mb-3">
								<form method="get"
									action="<%=request.getContextPath()%>/ticketcollection/find">
									<div class="form-group mb-0">
										<label for="sortDropdown" class="mr-2">目的地搜尋</label> <select
											class="form-control d-inline-block" id="sortDropdown"
											name="sort" onchange="this.form.submit()">
											<option value="popularity">按熱門程度排序</option>
											<option value="ticketName">按票券名稱排序</option>
											<!-- 其他排序選項 -->
										</select>
									</div>
								</form>
							</div>
						</div>
					</form>

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
	</div>
	<!-- jQuery & Bootstrap JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>

	//右邊排序
            // 排序下拉選單的變化
            $('#sortDropdown').on('change', function() {
                var sortValue = $(this).val();
                var sortField = 'ticketName'; // 預設排序依據為票券名稱
                var sortOrder = 'asc'; // 預設排序方式為升序

                // 根據選項修改排序參數
                if (sortValue === 'popularity') {
                    sortField = 'sales';
                    sortOrder = 'desc'; // 熱門銷售降序
                } else if (sortValue === 'ticketName') {
                    sortField = 'ticketName';
                    sortOrder = 'asc';
                }

                // 更新隱藏輸入欄位的值
                $('input[name="sortField"]').val(sortField);
                $('input[name="sortOrder"]').val(sortOrder);

                // 提交表單
                $('#searchForm').submit();
            });
        });
    </script>
</body>
</html>