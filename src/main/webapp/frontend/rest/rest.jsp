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
			let memId = "${memId}";
			let morningNum;
        	let noonNum;
        	let eveningNum;
			
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
			
			// 選擇日期添加可預約時段
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
			
			// 確認訂位時段是否還有人數可以訂位 若可以則返回剩餘訂位人數
			function bookingTimeNumcheck(bookingTime, morningNum, noonNum, eveningNum, bookingNumber) {
			    let status = true;
			    let Num = 0;
			
			    switch (parseInt(bookingTime)) {
			        case 0:
			            if (typeof morningNum === "undefined" || morningNum < bookingNumber) {
			                status = false;
			            } else {
			                Num = morningNum - bookingNumber;
			            }
			            break;
			        case 1:
			            if (typeof noonNum === "undefined" || noonNum < bookingNumber) {
			                status = false;
			            } else {
			                Num = noonNum - bookingNumber;
			            }
			            break;
			        case 2:
			            if (typeof eveningNum === "undefined" || eveningNum < bookingNumber) {
			                status = false;
			            } else {
			                Num = eveningNum - bookingNumber;
			            }
			            break;
			    }
			    return { status: status, Num: Num };
			}

			
			// 訂位執行
			function bookingStart(Num) {
			    // 判斷是否已會員登入
			    if (typeof parseInt(memId, 10) === 'number' && memId.length !== 0){
			    	
		            // 扣除訂位時段的人數
					$.ajax({
						type: 'post',
						data: $('#bookingform').serialize(),
						url: '/DepthSpace/RestApi/doRestBookingDate?action=minus&Num='+Num,
						success: function(data){
							console.log(data);
						},
						error: function(data){
							console.log(data);
							return false;
						}
					})				              
					
			    	
			    	
			    	// 新增訂位資訊
			    	$.ajax({
				          type: 'post',
				          data: $('#bookingform').serialize(),
				          url: $(this).attr('action'),
				          success: function(data) {
				              alert('訂位成功');
				              
				              
							  // 訂位成功發送郵件通知
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
			    	// 未登入，提示請先登入並跳轉至登入頁面
					alert("請先登入");
					window.location.href = "/DepthSpace/member/login.jsp";
			    }
			}
			
			
			
			
			// 訂位確認執行
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
				let bookingNumber = $('#bookingform input[name="bookingNumber"]').val();
				let params = "restId=" + restId + "&bookingDate=" + bookingDate;
				$.ajax({
				    type: 'get',
				    url: "/DepthSpace/RestApi/getRestBookingDate?" + params,
				    success: function (data) {
// 				    	console.log(data);
				    	// 取各時段可預約人數
				        $.each(data, function (index, entry) {
				        	if (index === "morningNum" && entry){
				        		morningNum = entry;
			                }
			                if (index === "noonNum" && entry){
			                	noonNum = entry;
			                }
			                if (index === "eveningNum" && entry){
			                	eveningNum = entry;
			                }
				        });
// 				    	console.log("morningNum "+morningNum);
// 				    	console.log("noonNum "+noonNum);
// 				    	console.log("eveningNum "+eveningNum);
// 				    	console.log("bookingNumber "+bookingNumber);
				    	
				        // 訂位時段 對比同時段訂位數已為0則提示無法訂位
				        let booking  = bookingTimeNumcheck(bookingTime, morningNum, noonNum, eveningNum, bookingNumber);
// 				        console.log(booking.status);
// 				        console.log(booking.Num);
				        if (booking.status) {
				        	// 執行訂位
				        	bookingStart(booking.Num);
				        } else {
				        	alert("此時段已無法訂位");
				        }
				   	},
				    error: function (xhr, status, error) {
				        console.log('查詢失敗');
				    }
				});
			});
			    
			
			
			
			
		})
	</script>
</body>
</html>
