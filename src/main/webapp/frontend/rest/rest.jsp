<%@ page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
<%@ page import="com.depthspace.restaurant.service.RestService"%>
<%@ page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<jsp:include page="/indexpage/head.jsp" />
	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />
	<!-- bootstrapCDN -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

	<style>
	    body {
	        font-size: 25px;
	    }
	</style>
</head>
<body>
	
	<div class="container">
		<div class="row p-3">
			<div class="col-6 text-center d-grid gap-2">
				<h1 id="btn_info" class="btn btn-dark rounded-3 border border-secondary" style="font-size: 35px;">餐廳資訊</h1>
			</div>
			<div class="col-6 text-center d-grid gap-2">
				<h1 id="btn_book" class="btn btn-light rounded-3 border border-secondary" style="font-size: 35px;">餐廳訂位</h1>
			</div>
		</div>
		<div id="col_info" class="row p-1 border border-3 border-danger mb-3 rounded-3">
<%-- 			<jsp:include page="info/r${restId}.jsp" /> --%>
			<jsp:include page="info/info.jsp" />
		</div>
		<div id="col_book" class="row p-1 d-none border border-3 border-danger mb-3 rounded-3">
			
			<h1>${rest.restName}</h1>
			<form id="bookingform" action="/DepthSpace/RestApi/doMemBooking?action=add" method="post" class="p-5">
				  <input type="hidden" name="restId" value="${rest.restId}">
				  <input type="hidden" name="memId" value="${memId}">
				  <input type="hidden" name="restName" value="${rest.restName}">
				  <input type="hidden" name="restAddress" value="${rest.restAddress}">
				  <div class="row mb-3">
				    <label for="bookingDate" class="col-sm-2 col-form-label">選擇日期：</label>
				    <div class="col">
				      <input type="date" id="bookingDate" name="bookingDate" required>
				    </div>
				  </div>

				  <div class="row mb-3">
				    <label for="bookingTime" class="col-sm-2 col-form-label">時段</label>
				    <div class="col-sm-3">
				      <select id="bookingTime" name="bookingTime">
						<option>請選擇</option>
					</select>
				    </div>
				  </div>
				  
				   <div class="row mb-3">
				    <label for="bookingNumber" class="col-sm-2 col-form-label">人數</label>
				    <div class="col-sm-3">
				      <input type="number" class="form-control" name="bookingNumber" required>
				    </div>
				  </div>
				   
				  <button type="submit" class="btn btn-primary">確認</button>
		</form>
			
			
		</div>
		
	</div>


	


	<jsp:include page="/indexpage/footer.jsp" />
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<script>
		$(function() {
			$("#btn_info").click(function(){
				if ($(this).hasClass("btn-light")){
					$(this).toggleClass("btn-light btn-dark");
					$("#btn_book").toggleClass("btn-dark btn-light");
					$("#col_info").toggleClass("d-none d-block");
					$("#col_book").toggleClass("d-block d-none");
				}
			});
			$("#btn_book").click(function(){
				if ($(this).hasClass("btn-light")){
					$(this).toggleClass("btn-light btn-dark");
					$("#btn_info").toggleClass("btn-dark btn-light");
					$("#col_book").toggleClass("d-none d-block");
					$("#col_info").toggleClass("d-block d-none");
				}
			});
			// 選擇日期
			$("#bookingDate").change(function(){
				let params = "restId="+${rest.restId}+"&bookingDate="+$(this).val();
				// 清空時段
				$("#bookingTime").empty().append("<option>請選擇</option>");
				$.ajax({
			          type: 'get',
			          url: "/DepthSpace/RestApi/getRestBookingDate?"+params,
			          success: function(data) {
			        	  $.each(data, function(index, entry) {
			        		  if (index === "restOpen" && !entry){
			        			  alert("本日公休，請重新選擇日期");
			        			  $("#bookingDate").val("");
			        		  }
			                  if (index === "morningNum" && entry){
			                	  $("#bookingTime").append("<option value='0'>早上</option>");
			                  }
			                  if (index === "noonNum" && entry){
			                	  $("#bookingTime").append("<option value='1'>中午</option>");
			                  }
			                  if (index === "eveningNum" && entry){
			                	  $("#bookingTime").append("<option value='2'>晚上</option>");
			                  }
			              });
			         }
				});
				    
			});
			
			let memId = "${memId}";
			$('#bookingform').submit(function(event) {
			    event.preventDefault();
			    // 若沒選擇時段則停止表單發送
			    if ($("#bookingTime").val() === "請選擇"){
			    	alert("請選擇時段");
			    	return false;			    	
			    }
			    // 判斷訂位當下是否有剩餘可以訂位 若無則跳出提示並中斷
			    let restId = $('#bookingform input[name="restId"]').val();
				let bookingDate = $('#bookingform input[name="bookingDate"]').val();
				let bookingTime = $('#bookingTime').val();
				let params = "restId=" + restId + "&bookingDate=" + bookingDate;
				$.ajax({
				    type: 'get',
				    url: "/DepthSpace/RestApi/getRestBookingDate?" + params,
				    success: function (data) {
				        $.each(data, function (index, entry) {
				        	// 取訂位時段 對比同時段訂位數已為0則提示無法訂位
				        	let morningNum = index===morningNum?
				            switch (parseInt(bookingTime)) {
				                case 0:
				                    if (index === "morningNum" && !entry) {
				                        alert("此時段已無法訂位");
				                        return false;
				                    }
				                    break;
				                case 1:
				                    if (index === "noonNum" && !entry) {
				                        alert("此時段已無法訂位");
				                        return false;
				                    }
				                    break;
				                case 2:
				                    if (index === "eveningNum" && !entry) {
				                        alert("此時段已無法訂位");
				                        return false;
				                    }
				                    break;
				            }
				        });
				    },
				    error: function (xhr, status, error) {
				        console.log('查詢失敗');
				    }
				});

			    // 新增訂位
			    // 判斷是否已會員登入
			    if (typeof parseInt(memId, 10) === 'number' && memId.length !== 0){
			    	$.ajax({
				          type: 'post',
				          data: $('#bookingform').serialize(),
				          url: $(this).attr('action'),
				          success: function(data) {
				              alert('訂位成功');
				              // call 扣掉訂位時段的人數
							  $.ajax({
								  type: 'post',
								  data: $('#bookingform').serialize(),
								  url: '/DepthSpace/RestApi/doRestBookingDate?action=minus',
								  success: function(data){
									  console.log(data);
								  },
								  error: function(data){
									  console.log(data);
								  }
							  })				              
							  return false;
				              
				              
				              // Mail發送
				              $.ajax({
						          type: 'post',
						          data: $('#bookingform').serialize(),
						          url: "/DepthSpace/RestApi/toMail",
						          success: function(data) {
						              console.log(data);
						          },
						          error: function(xhr, status, error) {
						        	  console.log(data);
						          }
							});
				          },
				          error: function(xhr, status, error) {
				              alert('訂位失敗');
				          }
					});
			    } else {
			    	console.log(memId);
					alert("請先登入");
					// 跳轉至登入頁面
					window.location.href = "/DepthSpace/member/login.jsp";
			    }
			});
			
			
			
			
		})
	</script>
</body>
</html>
