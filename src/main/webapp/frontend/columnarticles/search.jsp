<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <title>選擇專欄文章</title> -->
<!-- <!-- CSS --> 
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"> -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<c:url value='/static/css/frontendlist.css'/>"> --%>


<!-- </head> -->
<!-- <body> -->

			<!-- 右側內容 "-->
			<div class="col-md-9" >
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h2 class="mb-0">共有 ${searchCount} 篇文章</h2>
					<div class="form-group mb-0">
						<label for="sortSet" class="mr-2"></label> <select
							class="form-control d-inline-block" id="sortSet" style="width: auto;" onchange="sortArticles()">
							<option value="asc">預設</option>
							<option value="desc">按發布日排序(新→舊)</option>
							<option value="asc">按發布日排序(舊→新)</option>
						</select>
					</div>
				</div>
				<!-- 專欄文章列表 -->
				<div class="colum" id="right">
					<c:forEach items="${resultSet}" var="column">
						<a
							href="${pageContext.request.contextPath}/columnarticles/item?artiId=${column.artiId}"
							class="no-underline"> <!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="<%=request.getContextPath()%>/columnmainimage?artiId=${column.artiId}"
											alt="Main Image" class="img">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${column.artiTitle}</h5>
											<p class="card-title">${column.colType.colTypeName}</p>
											<p class="card-title">
												<c:choose>
													<c:when test="${fn:length(column.artiContent) > 30}">
								${fn:substring(column.artiContent,0,30)}...
								</c:when>
													<c:otherwise>
								${column.artiContent}
								</c:otherwise>
												</c:choose>
											</p>
											<p class="card-text">
												<small class="text-muted">${column.articleDate}</small>
											</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
				
	<div>
	<c:forEach begin="1" end="${pageQtyA}" var="i">
    <li class="page-item ${i == currentPage ? 'active' : ''}">
        <a class="page-link" href="${pageContext.request.contextPath}/columnarticles/list?page=${i}">${i}</a>
    </li>
</c:forEach>
    </div>					
				
			</div>
<!-- 	<script -->
<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- 	<script -->
<!-- 		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
<!--     <script> -->
 <script>
 //排序
 function sortArticles() {
    var sortValue = document.getElementById("sortSet").value;
    var formData = $('#searchForm').serialize(); //把搜尋結果放進來
    formData += '&sort=' + sortValue;

    $.ajax({
        url: "<%=request.getContextPath()%>/columnarticles/search", 
        type: 'GET',
        data: formData,
        success: function(response) {
            $('#right').html(response);
            document.getElementById("sortSet").value = sortValue;
        },
        error: function(error) {
            console.error('排序失敗', error);
        }
        
    });
}
 </script>   


  
<!-- </script> -->
			
<!-- </body> -->
<!-- </html> -->