<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
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
			<div>訂位</div>
		</div>
		
	</div>


	


	<jsp:include page="../indexpage/footer.jsp" />
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
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
		})
	</script>
</body>
</html>
