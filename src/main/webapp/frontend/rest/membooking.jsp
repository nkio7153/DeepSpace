<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>訂位列表</title>
<jsp:include page="/indexpage/head.jsp" />
<jsp:include page="/indexpage/header.jsp" />
<jsp:include page="/indexpage/headpic.jsp" />
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
<style>
	.blank-line {
     	height: 150px;
    }
</style>

</head>
<body>
	<p class="fs-1 text-center">訂位列表</p>
	<div class="row justify-content-center mt-3">
	<div class="col-8 transparent rounded">
		<table id="myTable" class="hover">
	            <thead>
	              <tr>
					<th>訂位編號</th>
	                <th>餐廳名稱</th>
					<th>訂位日期</th>
					<th>訂位時段</th>
					<th>訂位人數</th>
					<th>狀態</th>
					<th></th>
	              </tr>
	            </thead>
	            <tbody>
	              <c:forEach var="mb" items="${mbList}">
					<tr>
						<td>${mb.bookingId}</td>
						<td>${mb.restVO.restName}</td>
						<td>${mb.bookingDate}</td>
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
							<c:choose>
						    <c:when test="${mb.checkStatus == 0}">
						        <input type="submit" class="btn btn-danger cancel-btn" data-booking-id="${mb.bookingId}" value="取消訂位">
						    </c:when>
						    <c:when test="${mb.checkStatus == 1}">
						        <input disabled type="submit" class="btn btn-danger cancel-btn" data-booking-id="${mb.bookingId}" value="取消訂位">
						    </c:when>
						    <c:when test="${mb.checkStatus == 2}">
						        <input disabled type="submit" class="btn btn-danger cancel-btn" data-booking-id="${mb.bookingId}" value="取消訂位">
						    </c:when>
						</c:choose>
							
			            </td>
					</tr>
				</c:forEach>
	            </tbody>
	          </table>
	         </div>
	         </div>
	         <h3 class="mb-0 blank-line"></h3>


	<jsp:include page="/indexpage/footer.jsp" />
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		$(function() {
		    $('#myTable').DataTable({
		        language: {
		            url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/zh-HANT.json',
		        },
		        searching: false,
		        lengthChange: false,
		        info: false,
		    });
		    
		    $(".cancel-btn").click(function(e){
		    	e.preventDefault();
		    	var bookingId = $(this).data('booking-id');
		    	
	            Swal.fire({
		            title: "確定取消嗎",
		            icon: "warning",
		            showCancelButton: true,
		            confirmButtonColor: "#3085d6",
		            cancelButtonColor: "#d33",
		            confirmButtonText: "YES"
		        }).then((result) => {
		        	if (result.isConfirmed) {
			        	fetch("/DepthSpace/RestApi/doMemBooking?action=cancel&bookingId="+bookingId, {
							method: "POST",
							})
							.then(response => {
					            if (!response.ok) {
					                throw new Error(`HTTP error! status: ${response.status}`);
					            }
					            return response.text();
							})
							.then(data => {
								if (data == 1) {
									Swal.fire("已取消訂位");
									location.reload(true);
								} else if (data == -1) {
									Swal.fire("系統異常");
								}
							})
							.catch(error => {
								console.error(error);
							});
		        	}
			      });
		        
		    });
		    
		    
		    
		});

	</script>
</body>
</html>
