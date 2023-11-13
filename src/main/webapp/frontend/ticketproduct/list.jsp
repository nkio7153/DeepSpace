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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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


</style>

</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div id="loadingSpinner" class="loading-spinner" style="display: none;"></div>
	<div class="container mt-5">

		<div class="row">

			<!-- 左側篩選條件 -->
			<div class="col-md-3">
				<form id="searchForm" onsubmit="event.preventDefault(); updateTicketList(1);">
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
        <div class="form-group">
            <c:forEach var="areaItem" items="${uniqueTicketArea}" varStatus="status">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cityId${status.index}" 
                           name="areaId" value="${areaItem.cityId}" onchange="updateTicketList(1)">
                    <label class="custom-control-label" for="cityId${status.index}">${areaItem.cityName}</label>
                </div>
            </c:forEach>
        </div>

				<!-- 票券類型 -->
				<h4>票券類型</h4>
				<div class="form-group">
				    <c:forEach var="typeItem" items="${uniqueTicketTypes}" varStatus="status">
				        <div class="custom-control custom-checkbox">
				            <input type="checkbox" class="custom-control-input" id="ticketTypeId${status.index}" 
				                   name="ticketTypeId" value="${typeItem.ticketTypeId}" onchange="updateTicketList(1)">
				            <label class="custom-control-label" for="ticketTypeId${status.index}">${typeItem.typeName}</label>
				        </div>
				    </c:forEach>
				</div>
				</form>
			</div>
			<!-- 右側內容 -->
			<div class="col-md-9" id="ticketright">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<div >
					<c:choose>
						<c:when test="${not empty searchCount}">
							<h3 class="mb-0">搜尋結果 ${searchCount} 項票券</h3>
						</c:when>
						<c:otherwise>
							<h3 class="mb-0">共有 ${totalTickets} 項票券體驗</h3>
						</c:otherwise>
					</c:choose>
					</div>
				<!-- 排序 -->				    
				<label for="sortOption">排序方式：</label>
				<div class="form-group">
				    <select class="form-control" id="sortOption" onchange="updateTicketList(1)">
				 <option value="ticketId_asc" ${param.sortField == 'ticketId' && param.sortOrder == 'asc' ? 'selected' : ''}>依上市日(新→舊)</option>
				<option value="ticketId_desc" ${param.sortField == 'ticketId' && param.sortOrder == 'desc' ? 'selected' : ''}>依上市日(舊→新)</option>
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
											alt="Main Ticket Image" class="ticket-img">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${ticket.ticketName}</h5>
											<p class="card-title">${ticket.ticketType.typeName}&ensp;|&ensp;
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
	<%-- 分頁--%>
	
	<ul class="pagination justify-content-center">
    <c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
        <li class="page-item ${i == currentPage ? 'active' : ''}">
            <a class="page-link" href="#" onclick="updateTicketList(${i});">${i}</a>
        </li>
    </c:forEach>
</ul>

	<!-- jQuery & Bootstrap JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
 

// 	function updateTicketList(currentPage) {
// 	    $('#loadingSpinner').show();
// 	    var searchQuery = $('#ticketName').val(); // 關鍵字查詢
// 	    var formData = $('#searchForm').serialize();
// 	    var sortOption = $('#sortOption').val().split('_');
// 	    var sortField = sortOption[0];
// 	    var sortOrder = sortOption[1];
// 	    formData += '&page=' + currentPage + '&ajax=true';
	    
// 	    $.ajax({
// 	        url: '<c:url value="/ticketproduct/list"/>', 
// 	        type: 'GET',
// 	        data: {
// 	            page: currentPage,
// 	            sortField: sortField,
// 	            sortOrder: sortOrder,
// 	            searchQuery: searchQuery,
// 	            formData,
// 	            ajax: 'true'
// 	        },
// 	        success: function(response) {
// 	            $('#ticketright').html(response); 
// 	            $('#loadingSpinner').hide();
// 	        }, 
// 	        error: function(){
// 	            $('#loadingSpinner').hide();
// 	            alert("發生錯誤，請重試！")
// 	        }
// 	    });
// 	}

function updateTicketList(currentPage) {
    $('#loadingSpinner').show();
    
    // 收集搜索和篩選條件
    var filterConditions = {};
    filterConditions['page'] = currentPage;
    filterConditions['ajax'] = 'true';

    // 搜尋關鍵字
    var searchQuery = $('#ticketName').val(); 
    if (searchQuery) {
        filterConditions['ticketName'] = [searchQuery];
    }

    // 篩選條件
    $('input:checkbox[name="areaId"]:checked').each(function() {
        if (!filterConditions['areaId']) {
            filterConditions['areaId'] = [];
        }
        filterConditions['areaId'].push($(this).val());
    });
    $('input:checkbox[name="ticketTypeId"]:checked').each(function() {
        if (!filterConditions['ticketTypeId']) {
            filterConditions['ticketTypeId'] = [];
        }
        filterConditions['ticketTypeId'].push($(this).val());
    });

    // 排序選項
    var sortOption = $('#sortOption').val().split('_');
    filterConditions['sortField'] = sortOption[0];
    filterConditions['sortOrder'] = sortOption[1];

    $.ajax({
        url: '<c:url value="/ticketproduct/list"/>', 
        type: 'GET',
        data: filterConditions,
        success: function(response) {
            $('#ticketright').html(response); 
            $('#loadingSpinner').hide();
        }, 
        error: function(){
            $('#loadingSpinner').hide();
            alert("發生錯誤，請重試！")
        }
    });
}

      
	</script>

	<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>