<%@ page import="java.util.ArrayList"%>
<%@ page
	import="com.depthspace.restaurant.service.RestcollectionServiceImpl"%>
<%@ page
	import="com.depthspace.restaurant.service.RestcollectionService"%>
<%@ page
	import="com.depthspace.restaurant.model.restcollection.RestCollectionVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<%
	Integer memId = 1;
	if (memId != null) {
		RestcollectionService rcs = new RestcollectionServiceImpl();
		List<RestCollectionVO> rcList = rcs.findByMemId(memId);
		request.setAttribute("rcList", rcList);
	}
%>


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
							href="${pageContext.request.contextPath}/Rest/Restinfo?restId=${rest.restId}">
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
								<span class="d-inline"> <!-- 判斷會員已收藏顯示愛心 --> <c:set
										var="status" value="0" scope="request" /> <c:forEach var="rc"
										items="${rcList}">
										<c:if test="${rc.restId eq rest.restId}">
											<c:set var="status" value="1" scope="request" />
										</c:if>
									</c:forEach> <c:choose>
										<c:when test="${status eq 1}">
											<img class="collection-icon -on" style="cursor: pointer;"
												src="${pageContext.request.contextPath}/static/images/rest/fullheart.png">
										</c:when>
										<c:otherwise>
											<img class="collection-icon" style="cursor: pointer;"
												src="${pageContext.request.contextPath}/static/images/rest/heart.png">
										</c:otherwise>
									</c:choose>

								</span> <span class="d-inline"> <a
									href="${pageContext.request.contextPath}/Rest/Restinfo?restId=${rest.restId}">
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
	<%-- 		<script src="${pageContext.request.contextPath}/static/js/rest.js"></script> --%>
	<script>
		$(function() {
			
			
			$(".collection-icon").click(function() {
				let restId = $(this).closest("div.card-body").find(".restId").text();
				if ($(this).attr("class") === "collection-icon") {
					$(this).attr("src", "${pageContext.request.contextPath}/static/images/rest/fullheart.png");
					doCollection("add", restId);
				} else if ($(this).attr("class") === "collection-icon -on") {
					$(this).attr("src", "${pageContext.request.contextPath}/static/images/rest/heart.png");
					doCollection("delete", restId);
				}
				$(this).toggleClass("-on");
			});
			
			function doCollection(action, restId) {
				let memId = <%= memId %>;
				let origin = window.location.origin; // http://hostname:port
				let url = origin+"/DepthSpace/RestApi/doRestCollection?action="+action+"&memId="+memId+"&restId="+restId;
				console.log(url);
				fetch(url, {
					method: "POST",
// 					headers: {
// 			        	'Accept': 'application/json',
// 			            'Content-Type': 'application/json'
// 			        },
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
