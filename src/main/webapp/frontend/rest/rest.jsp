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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css">
<style>
	.card-img-top {
		width: 450px;
		height: 250px;
		object-fit: cover; /* 保持圖片比例並填充圖片框 */
	}
	.collection-icon, .collection-icon -on {
		width: 30px;
		height: 30px;
	}

</style>

</head>
<body>
	<div class="container">
		<div class="row">

			<c:forEach var="rest" items="${restList}">
				<!-- col一行為12 4為每行3個 p-3元素的上、右、下、左四個方向都添加 1rem（約等於 16 像素）的內邊距 -->
				<div class="col-md-4 p-3">
					<div class="card shadow-lg">
						<a
							href="${pageContext.request.contextPath}/frontend/rest/demo.jsp">
							<img class="card-img-top img-thumbnail"
							src="${pageContext.request.contextPath}/static/images/rest/r_${rest.restId}.jpg"
							onerror="this.src='${pageContext.request.contextPath}/static/images/rest/404.jpg'">
						</a>
						<div class="card-body">
							<div class="restId" style="display: none;">${rest.restId}</div>
							<h4>${rest.restName}</h4>
							<h6>${rest.restType}</h6>
							<h6>${rest.restAddress}</h6>
							<div class="d-flex justify-content-between">
								<span class="d-inline">
									<img class="collection-icon" src="${pageContext.request.contextPath}/static/images/rest/heart.png">
								</span>
								<span class="d-inline">
									<a href="${pageContext.request.contextPath}/frontend/rest/demo.jsp">
										<button class="btn btn-primary">進入</button>
									</a>
								</span>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>



	<jsp:include page="../indexpage/footer.jsp" />
	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<%-- 		<script src="${pageContext.request.contextPath}/static/js/rest.js"></script> --%>
	<script>
		$(function() {
			$(".collection-icon").click(function() {
				let restId = $(this).closest("div.card-body").find(".restId").text();
				if ($(this).attr("class") === "collection-icon") {
					$(this).attr("src", "${pageContext.request.contextPath}/static/images/rest/fullheart.png");
					addCollection(restId);
				} else if ($(this).attr("class") === "collection-icon -on") {
					$(this).attr("src", "${pageContext.request.contextPath}/static/images/rest/heart.png");
					delCollection(restId);
				}
				$(this).toggleClass("-on");
			});
			
			function addCollection(restId) {
				console.log("add");
				let memId = 1;
				let url = "http://localhost:8080/DepthSpace/RestApi/doRestCollection?action=add&memId="+memId+"&restId="+restId;
				fetch(url, {
					method: "POST",
// 					headers: {"Content-Type": "application/json"},
// 					body: JSON.stringify(postData)
					})
					.then(response => {
			            if (!response.ok) {
			                throw new Error(`HTTP error! status: ${response.status}`);
			            }
			            return response.text();
					})
					.then(data => {
						alert(data);
					})
					.catch(error => {
						console.error(error);
					});
			}
			
			function delCollection(restId) {
				console.log("del");
				let memId = 1;
				let url = "http://localhost:8080/DepthSpace/RestApi/doRestCollection?action=delete&memId="+memId+"&restId="+restId;
				fetch(url, {
					method: "POST",
// 					headers: {"Content-Type": "application/json"},
// 					body: JSON.stringify(postData)
					})
					.then(response => {
			            if (!response.ok) {
			                throw new Error(`HTTP error! status: ${response.status}`);
			            }
						return response.text();
					})
					.then(data => {
						alert(data);
					})
					.catch(error => {
						console.error(error);
					});
			}
			
			
		})
	</script>
</body>
</html>
