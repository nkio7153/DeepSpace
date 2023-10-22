<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticket.model.TicketVO" %>
<%@ page import="com.depthspace.ticket.model.TicketInfo" %>
<%@ page import="com.depthspace.attractions.model.CityVO" %>
<%@ page import="com.depthspace.ticket.service.TicketService" %>
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
    img {
        max-width: 100px;
        max-height: 100px; 
        display: block;
        margin: 0 auto;
    }
    th {
        vertical-align: middle;
        text-align: center;
    }
</style>
 
    
</head>
<body>
    <div class="container mt-5">
        <!-- 搜尋框 -->
			<div class="row mb-3">
			    <div class="col-md-8 col-sm-12 mb-2">
			        <input type="text" class="form-control" placeholder="輸入查找的關鍵字">
			    </div>
			    <div class="col-md-4 col-sm-12">
			        <button class="btn btn-primary btn-block">搜尋關鍵字</button>
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
		    </tr>
             </thead>
            <tbody>   
    <!-- 票券所有資料 -->
	    <c:forEach var="ticket" items="${list}" varStatus="status">
	        <tr>
				<td>${status.index + 1}</td>
				<td>${ticket.typeName}</td>
				<td>${ticket.ticketId}</td>
			    <td>${ticket.image}</td>
			    <td>${ticket.ticketName}</td>
			    <td>${ticket.price}</td>
			    <td>${ticket.stock}</td>
			    <td>${ticket.description}</td>    
			    <td>${ticket.publishedDate}</td> 
			    <td>${ticket.status}</td>      
			     <td>${ticket.cityName}</td>     
	        </tr>
	    </c:forEach>
        <!-- 分頁 -->
        <nav>
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
    </div>

    <!-- 引入Bootstrap JS 和 Popper.js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
