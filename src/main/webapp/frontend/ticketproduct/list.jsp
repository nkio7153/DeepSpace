<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<title>選擇票券體驗</title>

<jsp:include page="/indexpage/head.jsp" />

<!-- CSS -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet"
	href="<c:url value='/static/css/frontendlist.css'/>">
<style>
.loading-spinner {
	border: 5px solid #f3f3f3;
	border-top: 5px solid #3498db;
	border-radius: 50%;
	width: 50px;
	height: 50px;
	animation: spin 2s linear infinite;
	position: fixed;
	left: 50%;
	top: 50%;
	margin-left: -25px;
	margin-top: -25px;
	z-index: 1000;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
 }   
.view {
	padding-top: 30px;
    padding-bottom: 30px;
    text-align: center;
}

.view .page-item {
	display: inline-block;
	margin-right: 5px;
}

.view .page-link {
	padding: 5px 10px;
}
h6 {
    padding-bottom: 5px;
    padding-top: 8px;
    color: #0d6efdb5;
}

</style>

</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div id="loadingSpinner" class="loading-spinner" style="display: none;"></div>
	<div class="container mt-5">
		<div class="row">
			<!-- 左側篩選 -->
			<div class="col-md-3">
				<form id="searchForm"">
					<!-- 搜尋框 -->
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="票券名稱"
							id="ticketName" name="ticketName" value="${ticket.ticketName}"
							aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-primary" type="submit">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<!-- 目的地 -->
					<h6>目的地</h6>
	        		<div class="form-group">
		            <c:forEach var="areaItem" items="${uniqueTicketArea}" varStatus="status">
		                <div class="custom-control custom-checkbox">
		                    <input type="checkbox" class="custom-control-input" id="cityId${status.index}" 
		                           name="areaId" value="${areaItem.cityId}">
		                    <label class="custom-control-label" for="cityId${status.index}">${areaItem.cityName}</label>
		                </div>
		            </c:forEach>
	     		   </div>
					<!-- 票券類型 -->
					<h6>票券類型</h6>
					<div class="form-group">
					    <c:forEach var="typeItem" items="${uniqueTicketTypes}" varStatus="status">
					        <div class="custom-control custom-checkbox">
					            <input type="checkbox" class="custom-control-input" id="ticketTypeId${status.index}" 
					                   name="ticketTypeId" value="${typeItem.ticketTypeId}">
					            <label class="custom-control-label" for="ticketTypeId${status.index}">${typeItem.typeName}</label>
					        </div>
					    </c:forEach>
					</div>
				</form>
			</div>
			<!-- 右側內容 -->
			<div class="col-md-9" id="ticketright">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h3 class="mb-0">共有 ${total} 項票券體驗</h3>
					<!-- 排序 -->
					<label for="sortet"></label>
					<div class="form-group">
						<select class="form-control" id="sortSet" style="width: auto;"
							onchange="sortArticles()">
							<option>預設</option>
							<option value="desc">按發布日排序(新→舊)</option>
							<option value="asc">按發布日排序(舊→新)</option>
						</select>
					</div>
				</div>
				<!-- 票券列表 -->
				<div class="ticket-lists" id="ticketright">
					<c:forEach items="${resultSet}" var="ticket">
						<a
							href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=${ticket.ticketId}"
							class="no-underline"> <!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="<%=request.getContextPath()%>/ticketmainimage?ticketId=${ticket.ticketId}"
											alt="Main Ticket Image" class="h-100 ticket-img">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${ticket.ticketName}</h5>
											<p class="card-title">
												<i class="fa-solid fa-tags" style="color: #4e939a;"></i>&ensp;&ensp;${ticket.ticketType.typeName}&ensp;|&ensp;
												${ticket.city.cityName}
											</p>
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
												<c:choose>
													<c:when
														test="${totalRatingCountMap[ticket.ticketId] == 0 || totalRatingCountMap[ticket.ticketId] == null}">
														<p>暫無評價</p>
													</c:when>
													<c:otherwise>
														<p class="card-text">
															<small class="text-muted">
																${averageStarsMap[ticket.ticketId]} <c:forEach begin="1"
																	end="${averageStarsMap[ticket.ticketId]}" var="i">
																	<i class="fas fa-star gold-star"></i>
																</c:forEach> <c:if
																	test="${averageStarsMap[ticket.ticketId] % 1 != 0}">
																	<i class="fas fa-star-half-alt gold-star"></i>
																	<c:set var="emptyStarsStart"
																		value="${Math.floor(averageStarsMap[ticket.ticketId]) + 2}" />
																</c:if> <c:if
																	test="${averageStarsMap[ticket.ticketId] % 1 == 0}">
																	<c:set var="emptyStarsStart"
																		value="${averageStarsMap[ticket.ticketId] + 1}" />
																</c:if> <c:forEach begin="${emptyStarsStart}" end="5" var="j">
																	<i class="far fa-star gold-star"></i>
																</c:forEach> (${totalRatingCountMap[ticket.ticketId]})
															</small>
														</p>
													</c:otherwise>
												</c:choose>
											<p class="card-text">NT$ ${ticket.price}</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
					<!-- 分頁部分 -->
					<div class="view">
						<div class="pagination-container">
							<c:forEach begin="1" end="${pageQty}" var="i">
								<li class="page-item ${i == currentPage ? 'active' : ''}">
									<a class="page-link"
									href="${pageContext.request.contextPath}/ticketproduct/list?page=${i}">${i}</a>
								</li>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/17a3eedde4.js"
		crossorigin="anonymous"></script>
<script>
//左邊搜尋條件
$(document).ready(function() {
    $('#searchForm').on('submit', function(e) {
        e.preventDefault(); 
        $('#loadingSpinner').show(); // 載入動畫
        var formData = $(this).serialize();
        $.ajax({
            url: "<%=request.getContextPath()%>/ticketproduct/search", 
            data : formData, 
            success : function(result) {
                $('#ticketright').html(result);
                updatePagination(result.pageQtyA, result.currentPage);
            },
            complete: function() {
                $('#loadingSpinner').hide(); // 隱藏載入動畫
            }
        });
    });
    
	// 篩選變更也觸發
	$('input[type=checkbox]').change(function() {
		$('#searchForm').submit();
	});
});
//頁數變動
function updatePagination(pageQtyA, currentPage) {
    var paginationHtml = '';
    for (var i = 1; i <= pageQtyA; i++) {
        paginationHtml += '<li class="page-item ' + (i === currentPage ? 'active' : '') + '">';
        paginationHtml += '<a class="page-link" href="#" onclick="loadPage(' + i + '); return false;">' + i + '</a></li>';
    }
    $('.pagination').html(paginationHtml);
}
function loadPage(pageNumber) {
    $.ajax({
        url: '<%=request.getContextPath()%>/ticketproduct/search', 
        data: { page: pageNumber },
        success: function(response) {
            $('#ticketright').html(response);
        }
    });
}
function sortArticles() {
    var sortValue = document.getElementById("sortSet").value;
    $.ajax({
        url: "<%=request.getContextPath()%>/ticketproduct/search", 
        type: 'GET',
        data: { sort: sortValue },
        success: function(response) {
            $('#ticketright').html(response);
            document.getElementById("sortSet").value = sortValue;
        },
        error: function(error) {
            console.error('排序失敗', error);
        }
    });
}

      
</script>

	<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>