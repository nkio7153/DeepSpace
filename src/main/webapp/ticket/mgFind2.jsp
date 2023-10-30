<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
 <style>
 
    /* 自定義樣式：縮小字體大小和行距 */
     body {
         font-size: 0.9rem; 
         line-height: 1.5; 
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
 
    <!-- 引入Bootstrap JS 和 Popper.js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
  
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
    <!-- 符合搜尋結果的票券資料 var要填-->
	    <c:forEach items="${list}" var="ticket" varStatus="ticketStatus">
	        <tr>
	        	<td>${ticketStatus.count}</td>
				<td>${ticketType.typeName}</td>
				<td>${ticket.ticketId}</td>
			    <td><img src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}&isMainImage=1" alt="Main Ticket Image"></td>
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
	    

	<!-- 回首頁 -->
	<div class="page-item">
	<a class="page-link" href="${pageContext.request.contextPath}/backendticket/">回首頁</a>
	</div>



</body>
</html>