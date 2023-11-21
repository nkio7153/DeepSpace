<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">	
<link rel="stylesheet"	href="<c:url value='/static/css/backendlist.css'/>">
<style>
    .add-button {
        background-color: #63bfc9; 
        color: white; 
        padding: 8px 40px; 
        border: none; 
        border-radius: 5px;
        cursor: pointer; 
    }
    .add-button:hover {
        background-color: #5b969c; 
    }
    .table-hover tbody tr:hover {
        background-color: #f5f5f5;
    }
     .h5, h5 {
    font-size: 1.2rem;
    padding: 0px 0px 10px 0px;
	}
	.rowrow{
    padding: 0px 0px 20px 0px;
	}
	.custom-select-wrapper {
	    position: relative;
	}
	
	.custom-select-wrapper select {
	    appearance: none; 
	    -webkit-appearance: none;
	    -moz-appearance: none;
	    padding-right: 30px; 
	}
	
	.custom-select-wrapper::after {
	    content: "\f078"; 
	    font-family: "FontAwesome";
	    position: absolute;
	    top: 0;
	    right: 10px;
	    pointer-events: none; 
	    color: #555;
	    font-size: 18px;
	    height: 100%;
	    display: flex;
	    align-items: center;
	}
</style>

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
            
            <div class="col-lg-10 g-2 transparent rounded my-0  scrollable-table">
                <div class="table-list">
                    <div class="container mt-5">
                    <h5>票券列表</h5>
                        <!-- 三個查詢表單並排顯示 -->
                        <div class="row">
                            <div class="col-md-4">
                                <form id="filterForm" method="get" action="<%=request.getContextPath()%>/ticketmg/find">
                                    <div class="custom-select-wrapper">
                                    <select id="ticketId" name="ticketId" class="form-control mb-2">
                                        <option value="">票券名稱</option>
                                        <c:forEach var="ticket" items="${ticketListAll}">
                                            <option value="${ticket.ticketId}">${ticket.ticketName}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </form>
                            </div>

                            <div class="col-md-4">
                                <form id="filterForm2" method="get" action="<%=request.getContextPath()%>/ticketmg/find">
                                    <div class="custom-select-wrapper">
                                    <select id="ticketTypeId" name="ticketTypeId" class="form-control mb-2">
                                        <option value="">票券類型</option>
                                        <c:forEach var="typeItem" items="${uniqueTicketTypes}">
                                            <option value="${typeItem.ticketTypeId}">${typeItem.typeName}</option>
                                        </c:forEach>
                                    </select>
                                     </div>
                                </form>
                            </div>

                            <div class="col-md-4">
                                <form id="filterForm3" method="get" action="<%=request.getContextPath()%>/ticketmg/find">
 									<div class="custom-select-wrapper">
                                    <select id="cityId" name="areaId" class="form-control mb-2">
                                        <option value="">票券區域</option>
                                        <c:forEach var="areaItem" items="${uniqueTicketArea}">
                                            <option value="${areaItem.cityId}">${areaItem.cityName}</option>
                                        </c:forEach>
                                    </select>
                                   </div>
                                </form>
                            </div>
                        </div>

                        <div class="row mt-3">
					    <div class="col-md-8">
					        <form id="filterForm5" method="get" action="<%=request.getContextPath()%>/ticketmg/find">
					            <div class="input-group">
					                <input type="text" id="ticketName" name="ticketName" class="form-control" placeholder="票券名稱關鍵字搜尋" value="${ticket.ticketName}">
					                <div class="input-group-append">
					                    <button class="btn btn-outline-secondary" type="submit">
					                        <i class="fas fa-search"></i>
					                    </button>
					                </div>
					            </div>
					        </form>
					    </div>
					    <div class="col-md-4 d-flex justify-content-end">
					        <form method="get" action="<%=request.getContextPath()%>/ticketmg/add">
					            <button class="add-button" type="submit">新增</button>
					        </form>
					    </div>
					</div>

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
				<c:forEach var="ticket" items="${ticketList}" varStatus="status">
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
	 </div>
	</div>
  </div>
 </div>
</div>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    $(document).ready(function() {
        $('#filterForm, #filterForm2, #filterForm3, #filterForm4, #filterForm5').on('change', 'select', function() {
            var formId = $(this).closest('form').attr('id');
        	Swal.fire({
                title: '正在處理',
                html: '請稍等，正在更新資料。',
                timer: 1500,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading();
                },
                willClose: () => {
                    $('#' + formId).submit();
                }
            });
        });
    });
    
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
                $('#editTicketButton').attr('href', '<%=request.getContextPath()%>/ticketmg/edit?ticketId=' + ticketId); 
            },
            
            error: function(error) {
                console.log("Error: ", error);
            }
        });
    });

</script>
		               
<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>
</body>
</html>
