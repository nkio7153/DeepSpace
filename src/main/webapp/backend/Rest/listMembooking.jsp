<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
  	<title>訂位列表</title>
    <%-- include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  </head>
	
	<style>
		img {
		    width: 100px;
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
<!--         	<div class="container pt-3"> -->
	          <%-- 放入自己body裡的代碼--%>
	          <table id="myTable" class="hover">
	            <thead>
	              <tr>
					<th>訂位編號</th>
	                <th>餐廳編號</th>
					<th>訂位日期</th>
					<th>會員編號</th>
					<th>訂位時段</th>
					<th>訂位組數</th>
					<th>報到狀態</th>
					<th>報到</th>
	              </tr>
	            </thead>
	            <tbody>
	              <c:forEach var="mb" items="${mbList}">
					<tr>
						<td>${mb.bookingId}</td>
						<td>${mb.restId}</td>
						<td>${mb.bookingDate}</td>
						<td>${mb.memId}</td>
						<c:choose>
						    <c:when test="${mb.bookingTime == 1}">
						        <td>早</td>
						    </c:when>
						    <c:when test="${mb.bookingTime == 2}">
						        <td>中</td>
						    </c:when>
						    <c:when test="${mb.bookingTime == 3}">
						        <td>晚</td>
						    </c:when>
						</c:choose>
						<td>${mb.bookingNumber}</td>
						<c:choose>
						    <c:when test="${mb.checkStatus == 0}">
						        <td class="text-warning">未報到</td>
						    </c:when>
						    <c:when test="${mb.checkStatus == 1}">
						        <td>已報到</td>
						    </c:when>
						    <c:when test="${mb.checkStatus == 2}">
						        <td class="text-danger">已取消</td>
						    </c:when>
						</c:choose>
						<td>
			                <form method="post" action="${pageContext.request.contextPath}/backend/Rest.do">
							<input type="submit" class="btn btn-secondary" value="報到確認">
							<input type="hidden" name="bookingId" value="${mb.bookingId}">
							<input type="hidden" name="action" value="checkBooking">
						</form>
			            </td>
					</tr>
				</c:forEach>
	            </tbody>
	          </table>
<!--         	</div> -->
        </div>
      </div>
    </div>
    
    
    
    
    
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
    <script>
	    $(function() {
	        $('#myTable').DataTable({
	            // 中文化
	            language: {
	                url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/zh-HANT.json',
	            },
	            "pageLength": 25,
	        });
	    });
    </script>
  </body>
</html>
