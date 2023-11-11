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
	
	<%
		Integer memId = 1;
		String restId = request.getParameter("restId");
		RestService restService = new RestServiecImpl();
		RestVO restList = restService.getRestByRestId(Integer.valueOf(restId));
		request.setAttribute("rest", restList);
	%>
	
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
			
			
			<form id="bookingform" action="/DepthSpace/RestApi/doMemBooking?action=add" method="post" class="p-5">
				  <input type="hidden" name="restId" value="${rest.restId}">
				  <input type="hidden" name="memId" value="<%= memId %>">
				  <input type="hidden" name="restName" value="${rest.restName}">
				  <div class="row mb-3">
				    <label for="bookingDate" class="col-sm-2 col-form-label">選擇日期：</label>
				    <div class="col">
				      <input type="date" id="date" name="bookingDate" required>
				    </div>
				  </div>
				  
				  <fieldset class="row mb-3">
				    <legend class="col-form-label col-sm-2 mt-2">時段</legend>
				    <div class="col">
						<div class="form-check form-check-inline p-3">
						  <input class="form-check-input" type="radio" name="bookingTime" id="inlineRadio1" value="0" checked>
						  <label class="form-check-label" for="bookingTime">早</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bookingTime" id="inlineRadio2" value="1">
						  <label class="form-check-label" for="bookingTime">中</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bookingTime" id="inlineRadio3" value="2" disabled>
						  <label class="form-check-label" for="bookingTime">晚</label>
						</div>
				    </div>
				  </fieldset>
				   
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
			})
			$("#btn_book").click(function(){
				if ($(this).hasClass("btn-light")){
					$(this).toggleClass("btn-light btn-dark");
					$("#btn_info").toggleClass("btn-dark btn-light");
					$("#col_book").toggleClass("d-none d-block");
					$("#col_info").toggleClass("d-block d-none");
				}
			})
	
			$('#bookingform').submit(function(event) {
			    event.preventDefault();

			    $.ajax({
			          type: 'post',
			          data: $('#bookingform').serialize(),
			          url: $(this).attr('action'),
			          success: function(data) {
			              alert('新增成功');
			          },
			          error: function(xhr, status, error) {
			              alert('系統異常');
			          }
				});
			    
			    $.ajax({
			          type: 'post',
			          data: $('#bookingform').serialize(),
			          url: "/DepthSpace/RestApi/toMail",
			          success: function(data) {
			              console.log('新增成功');
			          },
			          error: function(xhr, status, error) {
			        	  console.log('系統異常');
			          }
				});
			    
			});
			
			
			
			
		})
	</script>
</body>
</html>
