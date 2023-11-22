<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<style>
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

.type-label {
    display: inline-block;
    background-color: #0d71fd59;
    color: white;
    padding: 2px 8px;
    font-size: 12px;
    border-radius: 5px;
    margin-left: 5px;
}

.text-right {
    text-align: right;
}
</style>
			<!-- 右側內容 "-->
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h2 class="mb-0">共有 ${searchCount} 篇文章</h2>
					<div class="form-group mb-0">
						<label for="sortSet" class="mr-2"></label> <select
							class="form-control d-inline-block" id="sortSet" style="width: auto;" onchange="sortArticles()">
							<option>預設</option>
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
											alt="Main Image" class="img h-100">
									</div>
								<div class="col-md-8">
								    <div class="card-body">
								        <h5 class="card-title">${column.artiTitle}</h5>
								            <span class="type-label">${column.colType.colTypeName}</span>
											<p class="card-title">
											<c:choose>
											<c:when test="${fn:length(column.artiContent) > 60}">
											${fn:substring(column.artiContent,0,60)}...
											</c:when>
											<c:otherwise>
											${column.artiContent}
											</c:otherwise>
											</c:choose>
											</p>
											 <p class="card-text text-right"> 
									           <small class="text-muted">
									               <fmt:formatDate value="${column.articleDate}" pattern="yyyy-MM-dd" />
									           </small>
									       </p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
				
	
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