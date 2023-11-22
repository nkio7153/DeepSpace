<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
    .table-list .viewimg{
    max-width: 30%;
    max-height: none;
    display: block;
    margin: inherit;
    padding-bottom: 20px;
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
<title>專欄列表</title>
<%--  include --%>
	<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    
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
		<h5>專欄列表</h5>
		<div class="rowrow"><div class="row">
         <div class="col-md-4">
            <form id="filterForm" method="get" action="<%=request.getContextPath()%>/columnmg/find">
                <div class="custom-select-wrapper">
    			<select id="colTypeId" name="colTypeId" class="form-control">
                        <option value="">專欄類型選單</option>
                        <c:forEach var="typeItem" items="${uniqueTypes}">
                            <option value="${typeItem.colTypeId}">${typeItem.colTypeName}</option>
                        </c:forEach>
                    </select>
                    </div>
            </form>
          </div>
          <div class="col-md-4">           
	        <form id="filterForm2" method="get" action="<%=request.getContextPath()%>/columnmg/find">
	            <div class="input-group">
	                <input type="text" id="artiTitle" name="artiTitle" class="form-control" placeholder="標題關鍵字" value="${column.artiTitle}">
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
       </div></div>

		<!-- 表格 -->
		<div id="right">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>編號</th>
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
						<td style="white-space:nowrap;">${column.colType.colTypeName}</td>
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
						<td style="white-space:nowrap;"><fmt:formatDate value="${column.articleDate}" pattern="yyyy-MM-dd" /></td>
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

					<!-- 動態顯示頁碼，根據總頁數pageQty生成 -->
					<c:forEach var="i" begin="1" end="${pageQty}" step="1">
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
			</div>
		 </div>
	 </div>
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
		                <div>
		                    <div class="carousel-inner" id="images"></div>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">專欄類型</strong>
		                        <p id="colType"></p>
		                    </div>
		                    <div class="col-md-6 mb-3"><strong class="d-block mb-2">文章標題</strong>
		                        <p id="title"></p>
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
		</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>			
<script>
$(document).ready(function() {
    $('#filterForm, #filterForm2').on('change', 'select', function() {
        var formId = $(this).closest('form').attr('id');
    	Swal.fire({
            title: '正在處理',
            html: '請稍等，正在更新資料。',
            timer: 500,
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

$(document).ready(function() {
    $('#filterForm, #filterForm2').on('submit', function(e) {
        e.preventDefault(); 
        $('#loadingSpinner').show(); // 載入動畫
        var formData = $(this).serialize();
        $.ajax({
            url: "<%=request.getContextPath()%>/columnmg/find", 
            data : formData, 
            success : function(result) {
                $('#right').html(result);
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

    $('.view').on('click', function() {
        var artiId = $(this).data('arti-id');
        $.ajax({
            url: "<%=request.getContextPath()%>/columnmg/view",
            type: "GET",
            data: {artiId: artiId},           
            success: function(column) {
            	 var date = new Date(column.articleDate);
                 var formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');
            	$('#artiId').text(column.artiId);
                $('#title').text(column.artiTitle);
                $('#artiContent').html(column.artiContent);
                $('#articleDate').text(formattedDate);
                $('#artiStatus').text(column.artiStatus == 1 ? '此文章上架中' : '此文章未上架');
                $('#colType').text(column.colType);
                $('#admin').text(column.admin);
                $('#editButton').attr('href', '${pageContext.request.contextPath}/columnmg/edit?artiId=' + artiId);
                var carouselInner = $('#images').empty();
                var carouselItem = $('<div>').addClass('carousel-item active');
                var img = $('<img>')
                    .attr("src", "<%=request.getContextPath()%>/columnmainimage?artiId=" + artiId)
                    .addClass("viewimg");
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
