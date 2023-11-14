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
<style>
 .table-hover tbody tr:hover {
     background-color: #f5f5f5;
 }
</style>
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
 <div class="col-lg-10 g-2 transparent rounded my-0">
   <div class="table-list">
	<h5>票券搜尋結果</h5>
     <div class="container mt-5">
         <!-- 判斷 list 是否為空 -->
         <c:choose>
             <c:when test="${not empty list}">
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
					<td>
				    <a href="${pageContext.request.contextPath}/ticketmg/edit?ticketId=${ticket.ticketId}" class="btn btn-primary btn-sm" style="background-color: #63bfc9; border-color: #63bfc9;">
				        <i class="fas fa-edit"></i>
				    </a><p></p>
				    <button type="button" class="btn btn-primary btn-sm view-ticket" data-toggle="modal" data-target="#viewTicketModal" data-ticket-id="${ticket.ticketId}" style="background-color: #63bfc9; border-color: #63bfc9;">
				        <i class="fas fa-eye"></i>
				    </button>
					</td>
					</tr>
				</c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <!-- 查詢結果為空 -->
                    <div class="text-center">
<!--                         <img src="path_to_cute_image.jpg" alt="No Results Found" /> -->
                        <p>沒有找到任何結果</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    	<!-- 查看票券 -->		
		<div class="modal fade" id="viewTicketModal" tabindex="-1" role="dialog" aria-labelledby="viewTicketModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-lg" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="viewTicketModalLabel">票券詳情</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <div class="modal-body">
		                <div class="row">
                          <!-- 輪播圖 -->
		                    <div id="carouselExampleIndicators" class="carousel slide">
		                        <div class="carousel-inner" id="ticketImagesCarousel"></div>
		                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		                            <span class="sr-only">Previous</span>
		                        </a>
		                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
		                            <span class="sr-only">Next</span>
		                        </a>
							</div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">票券類型</strong>
		                        <p id="modalTicketType"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">票券名稱</strong>
		                        <p id="modalTicketName"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">價格</strong>
		                        <p id="modalPrice"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">數量</strong>
		                        <p id="modalStock"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">使用天數</strong>
		                        <p id="modalValidDays"></p>
		                    </div>
		                    <div class="col-md-12 mb-3"><strong class="d-block mb-2">描述</strong>
		                        <div id="modalDescription"></div>
		                    </div>		                    
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">區域</strong>
		                        <p id="modalCity"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">地址</strong>
		                        <p id="modalAddress"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">經度</strong>
		                        <p id="modalLongitude"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">緯度</strong>
		                        <p id="modalLatitude"></p>
		                    </div>
		                    <div class="col-md-6 mb-3" id="modalStatus"><strong class="d-block mb-2"></strong>
		                    </div>
		                </div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
		                <a href="${pageContext.request.contextPath}/ticketmg/edit?ticketId=${ticket.ticketId}" class="btn btn-primary" id="editTicketButton">修改</a>
		            </div>
		        </div>
		    </div>
		</div>
		
	<!-- 回首頁 -->
	<div class="page-item">
		<a class="page-link"
			href="${pageContext.request.contextPath}/ticketmg/list">回票券總列表</a>
	</div>
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
<script>
$('.view-ticket').on('click', function() {
    var ticketId = $(this).data('ticket-id');
    $.ajax({
        url: "<%=request.getContextPath()%>/ticketmg/view",
        type: "GET",
        data: {ticketId: ticketId},           
        success: function(ticket) {
            $('#modalTicketType').text(ticket.ticketType);
            $('#modalTicketName').text(ticket.ticketName);
            $('#modalPrice').text(ticket.price);
            $('#modalValidDays').text(ticket.validDays);
            $('#modalStock').text(ticket.stock);
            $('#modalDescription').text(ticket.description);
            $('#modalCity').text(ticket.cityName);
            $('#modalAddress').text(ticket.address);
            $('#modalLongitude').text(ticket.longitude);
            $('#modalLatitude').text(ticket.latitude);
            $('#modalStatus').text(ticket.status == 1 ? '此商品上架中' : '此商品未上架'); 
            $('#modalDescription').html(ticket.description);
            
            var carouselInner = $('#ticketImagesCarousel').empty();
            $.getJSON("<%=request.getContextPath()%>/ticketallimage?action=getIds&ticketId=" + ticketId, function(imageIds) {
                imageIds.forEach(function(imageId, index) {
                    var carouselItem = $('<div>').addClass('carousel-item' + (index === 0 ? ' active' : ''));
                    var img = $('<img>')
                        .attr("src", "<%=request.getContextPath()%>/ticketallimage?action=getImage&imageId="  + imageId + "&ticketId=" + ticketId)
                        .addClass("d-block w-100");
                    carouselItem.append(img);
                    carouselInner.append(carouselItem);
                });
            });
            $('#editTicketButton').attr('href', '<%=request.getContextPath()%>/ticketmg/edit?ticketId=' + ticketId); // 設置編輯按鈕的鏈接
        },
        
        error: function(error) {
            console.log("Error: ", error);
        }
    });
});

</script>
</body>
</html>