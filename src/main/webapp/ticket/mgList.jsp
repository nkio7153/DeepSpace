<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  
    <title>票券列表</title>
  
 <style>
 
    /* 自定義樣式：縮小字體大小和行距 */
     body {
         font-size: 0.9rem; /* 根據需要調整 */
         line-height: 1.5; /* 根據需要調整 */
     }
     img {
         max-width: 100px;
         max-height: 100px;
         display: block;
         margin: 0 auto;
     }
/*      th, td { */
/*          vertical-align: middle; */
/*          text-align: center; */
/*          white-space: nowrap; /* 防止標題或內容換行 */ */
     }
     img {
         max-width: 100px;
         max-height: 100px; 
         display: block;
         margin: 0 auto;
    }
     th {
         white-space: nowrap; /* 表頭文字不換行 */
         vertical-align: middle;
         text-align: center;
     }

     td {
         /* 移除 td 的 white-space 屬性，允許內容自然斷行 */
         vertical-align: middle;
         text-align: center;
        }
         /* 避免橫向滾動設置表格最大寬度 */
     .table {
         width: 100%; /* 使表格寬度為100%，確保它在容器內自適應 */
         max-width: 100%; /* 這確保表格不會超出父容器 */
         overflow-x: auto; /* 若内容超出可滾動 */
      }
</style>
 
    
</head>
<body>
    <div class="container mt-5">
        <!-- 搜尋框 -->
		<div class="row mb-3">
		    <div class="col-md-6 col-sm-12 mb-2">
<!-- 		        <input type="text" class="form-control" placeholder="輸入查找的關鍵字"> -->
<%-- 		        	<form action="${pageContext.request.contextPath}/Ticket/mgsearch" method="post"> --%>
<!-- 						<label for="ticketId">選擇票券：</label> -->
<!-- 				    		<select id="ticketId" name="ticketId"> -->
<%-- 				        		<c:forEach var="ticket" items="${TicketList}" varStatus="status"> --%>
<%-- 				           			<option value="${ticketId}">${ticketId}</option> --%>
<%-- 				       			</c:forEach> --%>
<!-- 				    		</select> -->
<!-- 				    	<p><input type="submit" value="送出"></p> -->
<!-- 						<input type="hidden" name="action" value="doSearch"> -->
<!-- 					</form> -->
		<!-- onsubmit是使用ajaxSearch -->
		<form onsubmit="return ajaxSearch();">
		    <input type="text" id="searchField" name="ticketName" class="form-control" placeholder="票券名稱">
		    <input type="button" class="col-md-3 col-sm-6 mb-2" value="搜索" onclick="ajaxSearch();"> <!--觸發按鈕 -->
		</form>
		    </div>
<!-- 		    <div class="col-md-3 col-sm-6 mb-2"> -->
<%--     			<a href="${pageContext.request.contextPath}/backendticket/mgsearch" class="btn btn-primary btn-block">搜尋</a> --%>
<!-- 		    </div> -->
		    <div class="col-md-3 col-sm-6">
    			<a href="${pageContext.request.contextPath}/backendticket/mgadd" class="btn btn-success btn-block">新增</a>
    			
    		</div>
		</div>
  
<h1>票券列表</h1>

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
    <!-- 票券所有資料 -->
	    <c:forEach var="ticket" items="${ticketList}" varStatus="status">
	        <tr>
        <!-- 用當前頁數和每頁的數量計算項目序號 -->
       			<td>${itemsPerPage * (currentPage - 1) + status.index + 1}</td>
				<td>${ticket.ticketType.typeName}</td>
				<td>${ticket.ticketId}</td>
			    <td><img src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}" alt="Main Ticket Image"></td>
			    <td>${ticket.ticketName}</td>
			    <td>${ticket.price}</td>
			    <td>${ticket.stock}</td>
			    <td>${ticket.description}</td>    
			    <td>${ticket.publishedDate}</td> 
			    <td>${ticket.status}</td>      
			    <td>${ticket.city.cityName}</td>
			     <td>
                    <a href="${pageContext.request.contextPath}/backendticket/mgedit?ticketId=${ticket.ticketId}" class="btn btn-primary btn-sm">修改</a>
				    <a href="${pageContext.request.contextPath}/backendticket/mgdel?ticketId=${ticket.ticketId}" class="btn btn-danger btn-sm" onclick="return confirm('確定刪除？不要亂刪喔！');">删除</a>

                </td>  
	        </tr>
	    </c:forEach>
	    
	    
<!-- 分頁 -->
<div>
<nav>
    <ul class="pagination justify-content-center">
        <!-- "至第一頁" 只在非第一頁時顯示 -->
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/backendticket/mgList?page=1">首頁</a>
            </li>
        </c:if>
        
        <!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
        <c:if test="${currentPage - 1 != 0}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/backendticket/mglist?page=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <!-- 動態顯示頁碼，根據總頁數ticketPageQty生成 -->
        <c:forEach var="i" begin="1" end="${ticketPageQty}" step="1">
            <li class="page-item ${i == currentPage ? 'active' : ''}">
                <a class="page-link" href="${pageContext.request.contextPath}/backendticket/mglist?page=${i}">${i}</a>
            </li>
        </c:forEach>

        <!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
        <c:if test="${currentPage + 1 <= ticketPageQty}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/backendticket/mglist?page=${currentPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>

        <!-- "至最後一頁" 只在非最後一頁時顯示 -->
        <c:if test="${currentPage != ticketPageQty}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/backendticket/mglist?page=${ticketPageQty}">尾頁</a>
            </li>
        </c:if>
    </ul>
</nav>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function ajaxSearch() {
    var searchField = document.getElementById('searchField').value;

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/backendticket/mgsearch", // 搜尋網址
        data: { action: "doSearch", ticketName: searchField },
        success: function(data) {
            // 先清空目前的列表
            var tableBody = document.querySelector('table tbody');
            tableBody.innerHTML = "";

            // 將查詢到的資料填回列表
            data.forEach(function(ticket) {
                
                var row = `<tr>
                	<td>${itemsPerPage * (currentPage - 1) + status.index + 1}</td>
    				<td>${ticket.ticketType.typeName}</td>
    				<td>${ticket.ticketId}</td>
    			    <td><img src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}" alt="Main Ticket Image"></td>
    			    <td>${ticket.ticketName}</td>
    			    <td>${ticket.price}</td>
    			    <td>${ticket.stock}</td>
    			    <td>${ticket.description}</td>    
    			    <td>${ticket.publishedDate}</td> 
    			    <td>${ticket.status}</td>      
    			    <td>${ticket.city.cityName}</td>
    			    <td>
                        <a href="${pageContext.request.contextPath}/backendticket/mgedit?ticketId=${ticket.ticketId}" class="btn btn-primary btn-sm">修改</a>
    				    <a href="${pageContext.request.contextPath}/backendticket/mgdel?ticketId=${ticket.ticketId}" class="btn btn-danger btn-sm" onclick="return confirm('確定刪除？不要亂刪喔！');">删除</a>
                </tr>`;                

                tableBody.innerHTML += row;
            });
        },
        error: function(err) {
            console.log('Error:', err);
        }
    });
}
</script>



<!-- 回首頁 -->
<div class="page-item">
<a class="page-link" href="${pageContext.request.contextPath}/backendticket/">回首頁</a>
</div>

    <!-- 引入Bootstrap JS 和 Popper.js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
