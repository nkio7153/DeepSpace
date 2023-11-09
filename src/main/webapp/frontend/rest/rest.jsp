<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<jsp:include page="../indexpage/head.jsp" />
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />
	<!-- bootstrapCDN -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	<meta charset='utf-8' />
	<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/core@6.1.9/index.global.min.js'></script>
	<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/web-component@6.1.9/index.global.min.js'></script>
	<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@6.1.9/index.global.min.js'></script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
	
	
	<style>
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
			<label for="date">選擇日期：<input type="date" id="date" name="date"></label>
			
			<input type="text" id="datepicker">
			
			<full-calendar shadow options='{
			    "headerToolbar": {
			      "left": "prev,next today",
			      "center": "title",
			      "right": ""
			    }
			  }' />
			
		</div>
		
	</div>


	


	<jsp:include page="../indexpage/footer.jsp" />
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	
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
			
			const fullCalendarElement = document.querySelector('full-calendar')

			fullCalendarElement.options = {
			  headerToolbar: {
			    left: 'prev today',
			    center: 'title',
			    right: 'next'
			  }
			}
			

			// 使用 Bootstrap Datepicker 插件初始化日期選擇器
		    $('#datepicker').datepicker({
		    	language: 'zh-TW',
		        format: 'yyyy-mm-dd', // 設定日期格式
		        autoclose: true // 選擇日期後自動關閉日期選擇器
		    });
	
			$("#date").on('input', function(){
				console.log($(this).val());
			});
	
			
		})
	</script>
</body>
</html>
