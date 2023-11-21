<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
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
		<table class="table table-bordered">
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
					<c:forEach var="column" items="${list}" varStatus="status">
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
					href="${pageContext.request.contextPath}/columnmg/list">回總列表</a>
			</div>
 	 <!-- 查看		 -->
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
		                    <div id="carouselExampleIndicators" class="carousel slide">
		                        <div class="carousel-inner" id="images"></div>
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
		


<script>
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
</body>
</html>