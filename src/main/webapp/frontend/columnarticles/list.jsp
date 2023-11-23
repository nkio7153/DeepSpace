<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>選擇專欄文章</title>
<jsp:include page="/indexpage/head.jsp" />

<!-- CSS -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet"
	href="<c:url value='/static/css/frontendlist.css'/>">

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
.viewdiv {
	margin-bottom: 50px;
}
.viewbtn {
    background-color: #9bbdd7;
    border-color: #9bbdd7;
}
.text-right {
    text-align: right;
}
h6 {
    padding-bottom: 5px;
    padding-top: 8px;
    color: #6d9de3b5;
    font-weight: bold;
}
select {
    -webkit-appearance: menulist !important; 
    -moz-appearance: menulist !important;
    appearance: menulist !important;
}
</style>

</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div class="container mt-5">

		<div class="row viewdiv">

			<!-- 左側篩選條件 -->
			<div class="col-md-3">
				<form id="searchForm">
					<!-- 搜尋框 -->
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="名稱"
							id="artiTitle" name="artiTitle" value="${column.artiTitle}"
							aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-primary viewbtn" type="submit">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<h6>專欄類型</h6>
					<div class="form-group">
						<c:forEach var="typeItem" items="${uniqueColTypes}"
							varStatus="status">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="colTypeId${status.index}" name="colTypeId"
									value="${typeItem.colTypeId}"> <label
									class="custom-control-label" for="colTypeId${status.index}">${typeItem.colTypeName}</label>
							</div>
						</c:forEach>
					</div>
				</form>
			</div>
			<!-- 右側內容 "-->
			<div class="col-md-9" id="right">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h2 class="mb-0">共有 ${total} 篇文章</h2>
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
				<div class="column" >
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
				
		<div >
		<div class="view">
		<div class="pagination-container">
		<c:forEach begin="1" end="${pageQty}" var="i">
	    <li class="page-item ${i == currentPage ? 'active' : ''}">
	        <a class="page-link" href="${pageContext.request.contextPath}/columnarticles/list?page=${i}">${i}</a>
	    </li>
		</c:forEach>
	    </div>					
		</div>
		</div>		
		</div>
		</div>

	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    
    //左邊搜尋條件
    $(document).ready(function() {
        $('#searchForm').on('submit', function(e) {
            e.preventDefault(); 
            $('#loadingAnimation').show();
            var formData = $(this).serialize();
            $.ajax({
                url: "<%=request.getContextPath()%>/columnarticles/search", 
				data : formData, 
				success : function(result) {
					console.log(result);
					$('#right').html(result);
					updatePagination(result.pageQtyA, result.currentPage);
				},
	            complete: function() {
	                $('#loadingSpinner').hide();
	            }
			});
		});

		// 篩選變更也觸發
		$('input[type=checkbox]').change(function() {
			$('#searchForm').submit();
		});
	});
	//頁數變動
    function updatePagination(pageQtyA, currentPage) {
        var paginationHtml = '';
        for (var i = 1; i <= pageQtyA; i++) {
            paginationHtml += '<li class="page-item ' + (i === currentPage ? 'active' : '') + '">';
            paginationHtml += '<a class="page-link" href="#" onclick="loadPage(' + i + '); return false;">' + i + '</a></li>';
        }
        $('.pagination').html(paginationHtml);
    }
    function loadPage(pageNumber) {
        $.ajax({
            url: '<%=request.getContextPath()%>/columnarticles/search', 
            data: { page: pageNumber },
            success: function(response) {
                $('#right').html(response);
            }
        });
    }
    function sortArticles() {
        var sortValue = document.getElementById("sortSet").value;
        $.ajax({
            url: "<%=request.getContextPath()%>/columnarticles/search", 
            type: 'GET',
            data: { sort: sortValue },
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

	<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>