<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
   .table-hover tbody tr:hover {
    background-color: #f5f5f5;
    }
    .table-list .viewimg{
    max-width: 30%;
    max-height: none;
    display: block;
    margin: inherit;
    padding-bottom: 20px;
    }
    .empty{
    margin-top: 0;
    margin-bottom: 1rem;
    margin-bottom: 5rem;
    margin-top: 2rem;
    font-size: 1.5rem;
    text-align: center;
    }
</style>
         <!-- 判斷 list 是否為空 -->
         <c:choose>
             <c:when test="${not empty list}">
		<!-- 表格 -->
		<div id="right">
		<table class="table table-bordered table-hover" id="table-hover">
			<thead>
				<tr>
					<th>編號</th>
					<th>區域</th>
					<th>類型</th>
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
				<c:forEach var="ticket" items="${list}" varStatus="status">
					<tr >
						<td>${ticket.ticketId}</td>
						<td style="white-space:nowrap;">${ticket.city.cityName}</td>
						<td style="white-space:nowrap;">${ticket.ticketType.typeName}</td>
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
						<td style="white-space:nowrap;"><fmt:formatDate value="${ticket.publishedDate}" pattern="yyyy-MM-dd" /></td>
						<td><c:choose>
								<c:when test="${ticket.status ==1}">上架</c:when>
								<c:otherwise>未上架</c:otherwise>
							</c:choose></td>
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
                    <div class="empty">
                        <p>沒有找到任何結果</p>
                    </div>
                </c:otherwise>
            </c:choose>
    		<!-- 回列表 -->
			<div class="page-item" style="padding-bottom:50px;text-align:center">
				<a class="page-link"
					href="${pageContext.request.contextPath}/ticketmg/list">回總列表</a>
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