<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
  	<title>訂位確認</title>
    <%-- include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  </head>
	
	<style>
	    img {
		    width: 300px;
            height: 150px;
		}
	</style>
  <body>
    <%--include header.jsp--%>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid my-0">
      <div class="row">
        <%-- 側邊欄--%>
        <div class="col-lg-2 g-3 mt-1">
          <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>

        <div class="col-lg-10 g-2 transparent rounded mt-1">
	          <%-- 放入自己body裡的代碼--%>
	          <form id="toUpdate" class="row g-2 p-3" action="${pageContext.request.contextPath}/backend/Rest.do?action=checkBookingUpdate" method="post">
	          	  <input type="hidden" name="restId" value="${mb.restId}">
	          	  <div class="col-12">
				    <label for="bookingId" class="form-label">訂位編號</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="bookingId" name="bookingId" value=${mb.bookingId} readonly>
				  </div>
	          	  <div class="col-12">
				    <label for="memId" class="form-label">會員</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="memId" name="memId" value=${mb.memId} readonly>
				  </div>
				  
				  <div class="col-12">
				    <label for="bookingDate" class="form-label">訂位日期</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="bookingDate" name="bookingDate" value=${mb.bookingDate} readonly>
				  </div>
				  
				  <div class="col-12">
				    <label for="bookingTime" class="form-label">訂位時段</label>
				  </div>
				  <div class="col-6 mb-2">
						<c:choose>
						    <c:when test="${mb.bookingTime == 1}">
						        <input type="text" class="form-control" value="早" readonly>
						        <input type="hidden" name="bookingTime" value="${mb.bookingTime}">
						    </c:when>
						    <c:when test="${mb.bookingTime == 2}">
						        <input type="text" class="form-control" value="中" readonly>
						        <input type="hidden" name="bookingTime" value="${mb.bookingTime}">
						    </c:when>
						    <c:when test="${mb.bookingTime == 3}">
						        <input type="text" class="form-control"value="晚" readonly>
						        <input type="hidden" name="bookingTime" value="${mb.bookingTime}">
						    </c:when>
						</c:choose>
				  </div>
				  
				  <div class="col-12">
				    <label for="bookingNumber" class="form-label">訂位人數</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="bookingNumber" name="bookingNumber" value=${mb.bookingNumber} readonly>
				  </div>
				  
				  
				  <div class="col-12">
				    <label for="restStatus" class="form-label">訂位狀態</label>
				  </div>
				  <div class="col-auto mb-2">
				    <select id="restStatus" class="form-select" name="checkStatus">
						<c:choose>
						    <c:when test="${mb.checkStatus == 0}">
						        <option value="0" selected>未報到</option>
						        <option value="1">已報到</option>
						        <option value="2">已取消</option>
						    </c:when>
						    <c:when test="${mb.checkStatus == 1}">
						        <option value="0">未報到</option>
						        <option value="1" selected>已報到</option>
						        <option value="2">已取消</option>
						    </c:when>
						    <c:when test="${mb.checkStatus == 2}">
						        <option value="0">未報到</option>
						        <option value="1">已報到</option>
						        <option value="2" selected>已取消</option>
						    </c:when>
						</c:choose>
				    </select>
				  </div>
				  
					
				  <div class="col-12">
				    <button type="submit" class="btn btn-secondary">確認</button>
				  </div>
				  
			</form>
        </div>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
    <script>
    	$(function(){
    		
    	})
    </script>
  </body>
</html>
