<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html>
<html>
<head>
    <title>選擇票券體驗</title>
    <jsp:include page="../indexpage/head.jsp" /> 
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="path_to_your_css.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    
	<style>

		.ticket-img {
		    width: 100%;
		    height: 200px;
		    object-fit: cover;  /* 使圖片覆蓋整個容器 */
		    object-position: center;  /* 確保圖片在容器中央 */
		}
		.card-text {
		    display: -webkit-box;
		    -webkit-line-clamp: 2; /* 限制行數 */
		    -webkit-box-orient: vertical;
		    overflow: hidden;
		    text-overflow: ellipsis;
		}
		.card {
		    border: none;
		}
		.clickable-card {
		    cursor: pointer;  /* 讓卡片呈現手指樣式 */
		    border: 1px solid #ccc;  /* 加上淺灰色框線 */
		    border-radius: 5px;  /* 加上圓角 */
		}
		    /* 刪除連結的底線 */
	    a {
	        text-decoration: none;
	    }
	        /* 增加hover效果 */
	    .clickable-card:hover {
	        opacity: 0.8;  /* 這會使整個卡片變為淺色 */
	    }
    
	</style>
    
    
</head>
<body>

<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<div class="container mt-5">

    <div class="row">

        <!-- 左側篩選條件 -->
        <div class="col-md-3">
            <!-- 搜尋框與放大鏡按鈕 -->
		    <div class="input-group mb-3">
		        <input type="text" class="form-control" placeholder="搜尋..." aria-label="Search">
		        <div class="input-group-append">
		            <button class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
		        </div>
		    </div>
		
		    <h4>目的地</h4>
            <div class="form-group">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="location1">
                    <label class="custom-control-label" for="location1">北部</label>
                </div>
                <!-- 其他目的 -->
            </div>
            
            <h4>票券類型</h4>
            <div class="form-group">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="type1">
                    <label class="custom-control-label" for="type1">XXX票</label>
                </div>
                <!-- 其他票 -->
            </div>
        </div>

        <!-- 右側內容 -->
			<div class="col-md-9">
			    <div class="d-flex justify-content-between align-items-center mb-3">
			        <h2 class="mb-0">共有 ${totalTickets} 項票券體驗</h2>
			        <div class="form-group mb-0">
			            <label for="sortDropdown" class="mr-2">排序方式：</label>
			            <select class="form-control d-inline-block" id="sortDropdown" style="width:auto;">
	                <option>按熱門程度排序</option>
                <!-- 其他排序 -->
                </select>
            </div>
            </div>
            <!-- 票券列表 -->
            <div class="ticket-list">
              <c:forEach items="${ticketList}" var="ticket">
               <a href="${pageContext.request.contextPath}/ticketproduct/ticket?ticketId=${ticket.ticketId}">  <!-- 整張卡片點擊 -->
                <div class="card mb-3 clickable-card">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                            <img src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}" alt="Main Ticket Image" class="ticket-img">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${ticket.ticketName}</h5>
                                <p class="card-title">${ticket.ticketType.typeName} | ${ticket.city.cityName}</p>
                                <p class="card-text">${ticket.description}</p>
                                <p class="card-text"><small class="text-muted">4.4 ★★★★★ (520)</small></p>
                                <p class="card-text">NT$ ${ticket.price}</p>
                            </div>
                        </div>
                    </div>
                </div>
               </a> 
              </c:forEach>
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
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/ticketproduct/list?page=1">首頁</a>
            </li>
        </c:if>
        
        <!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
        <c:if test="${currentPage - 1 != 0}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/ticketproduct/list?page=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
        <c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
            <li class="page-item ${i == currentPage ? 'active' : ''}">
                <a class="page-link" href="${pageContext.request.contextPath}/ticketproduct/list?page=${i}">${i}</a>
            </li>
        </c:forEach>

        <!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
        <c:if test="${currentPage + 1 <= ticketPageQty}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/ticketproduct/list?page=${currentPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>

        <!-- "至最後一頁" 只在非最後一頁時顯示 -->
        <c:if test="${currentPage != ticketPageQty}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/ticketproduct/list?page=${ticketPageQty}">尾頁</a>
            </li>
        </c:if>
    </ul>
</nav>
</div>

<!-- jQuery & Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<jsp:include page="../indexpage/footer.jsp" /> 

</body>
</html>