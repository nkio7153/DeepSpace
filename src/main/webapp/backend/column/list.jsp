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
</style>
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
   <div class="table-list">
       <div class="container mt-5">
		<div class="row">
         <div class="col-md-4">
            <form id="filterForm2" method="get" action="<%=request.getContextPath()%>/ticketmg/find">
                <label for="ticketTypeId" style="display: none";></label><select id="ticketTypeId" name="ticketTypeId" class="form-control mb-2">
                    <option value="">專欄類型選單</option>
                    <c:forEach var="typeItem" items="${uniqueTicketTypes}">
                        <option value="${typeItem.ticketTypeId}">${typeItem.typeName}</option>
                    </c:forEach>
                </select>
            </form>
          </div>
          <div class="col-md-4">           
	        <form id="filterForm5" method="get" action="<%=request.getContextPath()%>/ticketmg/find">
	            <div class="input-group">
	                <input type="text" id="ticketName" name="ticketName" class="form-control" placeholder="文章標題關鍵字搜尋" value="${ticket.ticketName}">
	                <div class="input-group-append">
	                    <button class="btn btn-outline-secondary" type="submit">
	                        <i class="fas fa-search"></i>
	                    </button>
	                </div>
	            </div>
	        </form>
	       </div> 
               <div class="col-md-4 d-flex justify-content-end"> 
	      		<form method="get" action="<%=request.getContextPath()%>/columnmg/add">
				<button class="add-button" type="submit">新增</button>
				</form>
		  </div>
       </div>
		<h5>專欄列表</h5>

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
						<td>
						    <a href="${pageContext.request.contextPath}/columnmg/edit?artiId=${column.artiId}" class="btn btn-primary btn-sm" style="background-color: #63bfc9; border-color: #63bfc9;">
						        <i class="fas fa-edit"></i>
						    </a><p></p>
						    <button type="button" class="btn btn-primary btn-sm view" data-toggle="modal" data-target="#viewModal" data-arti-id="${column.artiId}" style="background-color: #63bfc9; border-color: #63bfc9;">
						        <i class="fas fa-eye"></i>
						    </button>
						</td>				
				</tr>
				</c:forEach>
		</table>
	 </div>
	 
	 <!-- 查看 -->		
		<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-lg" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="viewModalLabel">專欄文章詳情</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <div class="modal-body">
		                <div class="row">
                          <!-- 輪播圖 -->
		                    <div id="carouselExampleIndicators" class="carousel slide">
		                        <div class="carousel-inner" id="images"></div>
		                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		                            <span class="sr-only">Previous</span>
		                        </a>
		                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
		                            <span class="sr-only">Next</span>
		                        </a>
							</div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">專欄類型</strong>
		                        <p id="colType"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">文章標題</strong>
		                        <p id="artiTitle"></p>
		                    </div>
		                    <div class="col-md-12 mb-3"><strong class="d-block mb-2">文章內文</strong>
		                        <div id="artiContent"></div>
		                    </div>		                    
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">發文日期</strong>
		                        <p id="articleDate"></p>
		                    </div>
		                     <div class="col-md-6 mb-3"><strong class="d-block mb-2">管理者</strong>
		                        <p id="admin"></p>
		                    </div>
		                    <div class="col-md-6 mb-3" id="artiStatus"><strong class="d-block mb-2"></strong>
		                    </div>
		                </div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
		                <a href="${pageContext.request.contextPath}/columnmg/edit?artiId=${column.artiId}" class="btn btn-primary" id="editButton">修改</a>
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
			<div class="page-item">
				<a class="page-link"
					href="${pageContext.request.contextPath}/columnmg/">回首頁</a>
				</div>
			</div>
		</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
			
<script>
    $('.view').on('click', function() {
        var artiId = $(this).data('arti-id');
        $.ajax({
            url: "<%=request.getContextPath()%>/columnmg/view",
            type: "GET",
            data: {artiId: artiId},           
            success: function(column) {
            	console.log(column);
            	$('#artiId').text(column.artiId);
                $('#artiTitle').text(column.artiTitle);
                $('#artiContent').html(column.artiContent);
                $('#articleDate').text(column.articleDate);
                $('#artiStatus').text(column.artiStatus == 1 ? '此文章上架中' : '此文章未上架');
                $('#colType').text(column.colType);
                $('#admin').text(column.admin);
                $('#editButton').attr('href', '${pageContext.request.contextPath}/columnmg/edit?artiId=' + artiId);
                var carouselInner = $('#images').empty();
                var carouselItem = $('<div>').addClass('carousel-item active');
                var img = $('<img>')
                    .attr("src", "<%=request.getContextPath()%>/columnmainimage?artiId=" + artiId)
                    .addClass("d-block w-100");
                carouselItem.append(img);
                carouselInner.append(carouselItem);
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
