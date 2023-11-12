<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>收藏票券列表</title>

<jsp:include page="/indexpage/head.jsp" />

<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet"
	href="<c:url value='/static/css/frontendlist.css'/>">

</head>
<body>


	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />
	
	
	<div class="container mt-5">

		<div class="row">

			<div class="col-md-9">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<%-- 					<h3 class="mb-0">共收藏 ${totalTickets} 項票券</h3> --%>
					<h3 class="mb-0">您的票券收藏</h3>
					<form action="<%=request.getContextPath()%>/ticketcollection/list"
						method="get">
						<div class="col-md-16">
							<div
								class="d-flex justify-content-between align-items-center mb-3">

							</div>
						</div>
					</form>
				</div>
				<!-- 票券列表 -->
				<div class="ticket-list">
					<c:forEach items="${resultSet}" var="ticketCollection">
						<div class="card mb-3 clickable-card">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img
										src="<%=request.getContextPath()%>/ticketmainimage?ticketId=${ticketCollection.ticketVO.ticketId}"
										alt="Main Ticket Image" class="ticket-img">
								</div>
								<div class="col-md-8">
									<a
										href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=${ticketCollection.ticketVO.ticketId}"
										class="no-underline"> <!-- 整張卡片點擊 -->

										<div class="card-body">
											<h5 class="card-title">${ticketCollection.ticketVO.ticketName}</h5>
											<p class="card-title">${ticketCollection.ticketVO.ticketType.typeName}&ensp;|&ensp;${ticketCollection.ticketVO.city.cityName}</p>
											<p class="card-title">
												<c:choose>
													<c:when
														test="${fn:length(ticketCollection.ticketVO.description) > 30}">
								${fn:substring(ticketCollection.ticketVO.description,0,30)}...
								</c:when>
													<c:otherwise>
								${ticketCollection.ticketVO.description}
								</c:otherwise>
												</c:choose>
											</p>
											<p class="card-text">
												<small class="text-muted">
													${averageStarsMap[ticketCollection.ticketVO.ticketId]} <!-- 實星 -->
													<c:forEach begin="1"
														end="${averageStarsMap[ticketCollection.ticketVO.ticketId]}"
														var="i">
														<i class="fas fa-star gold-star"></i>
													</c:forEach> <!-- 半星 --> <c:if
														test="${averageStarsMap[ticketCollection.ticketVO.ticketId] % 1 != 0}">
														<i class="fas fa-star-half-alt gold-star"></i>
														<!-- 有半星就+ -->
														<c:set var="emptyStarsStart"
															value="${Math.floor(averageStarsMap[ticketCollection.ticketVO.ticketId]) + 2}" />
													</c:if> <!-- 沒有半星就往下一個數 --> <c:if
														test="${averageStarsMap[ticketCollection.ticketVO.ticketId] % 1 == 0}">
														<c:set var="emptyStarsStart"
															value="${averageStarsMap[ticketCollection.ticketVO.ticketId] + 1}" />
													</c:if> <!-- 空星 --> <c:forEach begin="${emptyStarsStart}" end="5"
														var="j">
														<i class="far fa-star gold-star"></i>
													</c:forEach>
													(${totalRatingCountMap[ticketCollection.ticketVO.ticketId]})
													銷售量${ticketOrderCountMap[ticketCollection.ticketVO.ticketId]}
												</small>
											</p>
											<p class="card-text">NT$
												${ticketCollection.ticketVO.price}</p>
										</div>
									</a>
								</div>
							</div>
							<!-- 愛心 -->
							<div class="favorite-icon-container">
								<i class="fas fa-heart favorite-icon"
									data-ticketId="${ticketCollection.ticketVO.ticketId}"></i>
							</div>
						</div>

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

// 	//右邊排序
//     // 排序下拉選單的變化
//     $('#sortDropdown').on('change', function() {
//         var sortValue = $(this).val();
//         var sortField = 'ticketName'; // 預設排序依據為票券名稱
//         var sortOrder = 'asc'; // 預設排序方式為升序

//         // 根據選項修改排序參數
//         if (sortValue === 'popularity') {
//             sortField = 'sales';
//             sortOrder = 'desc'; // 熱門銷售降序
//         } else if (sortValue === 'ticketName') {
//             sortField = 'ticketName';
//             sortOrder = 'asc';
//         }

//         // 更新隱藏輸入欄位的值
//         $('input[name="sortField"]').val(sortField);
//         $('input[name="sortOrder"]').val(sortOrder);

//         // 提交表單
//         $('#searchForm').submit();
//     });

//愛心取消收藏
$(document).ready(function() {
    $('.favorite-icon').click(function(e) {
        e.preventDefault();
        var iconElement = $(this); 
        var ticketId = iconElement.data('ticketid'); 

        iconElement.addClass('break-heart-animation');
        iconElement.on('animationend', function() {

            $.ajax({
                url: '<%=request.getContextPath()%>/ticketcollection/del',
                type: 'GET',
                data: { ticketId: ticketId },
                dataType: 'json', 
                success: function(response) {
                     if (response.success) {
                        console.log('取消收藏，票券移除成功!');
                    } else {
                        console.log('取消收藏失敗: ', response.message);
                    }
                },
                error: function(xhr, status, error) {
                    // 處理錯誤情況
                    console.log('Ajax請求錯誤: ' + error.message);
                },
                complete: function() {
                    // 動畫結束後移除卡片
                    iconElement.closest('.card').remove();
                }
            });
            // 移除動畫
            iconElement.removeClass('break-heart-animation');
        });
    });
});

</script>

	<jsp:include page="/indexpage/footer.jsp" />
</body>

</html>