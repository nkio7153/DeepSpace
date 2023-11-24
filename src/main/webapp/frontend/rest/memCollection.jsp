<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>收藏餐廳列表</title>

<jsp:include page="/indexpage/head.jsp" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet"
	href="<c:url value='/static/css/frontendlist.css'/>">
<style>
	.myfa {
	    margin: auto;
	}
	.collection-icon, .collection-icon -on {
		width: 30px;
		height: 30px;
	}
	.blank-line {
     	height: 500px;
    }
</style>
</head>
<body>


	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />
	
	
	<div class="container mt-5">

		<div class="row">

			<div class="col-md-9 myfa">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<c:choose>
			            <c:when test="${not empty rcList }">
			                <h3 class="mb-0">您的餐廳收藏</h3>
			            </c:when>
			            <c:otherwise>
			                <h3 class="mb-0 blank-line">您目前無任何餐廳收藏</h3>
			            </c:otherwise>
			        </c:choose>
						<div class="col-md-16">
							<div
								class="d-flex justify-content-between align-items-center mb-3">
							</div>
						</div>
				</div>
				<div class="row">

			<c:forEach var="rc" items="${rcList}">
				<div class="col-md-4 p-3">
					<div class="card shadow-lg">
						<a href="${pageContext.request.contextPath}/Rest/Restinfo?restId=${rc.restId}">
							<img class="card-img-top img-thumbnail"	src="${pageContext.request.contextPath}/static/images/rest/r_${rc.restId}.jpg"
							     onerror="this.src='${pageContext.request.contextPath}/static/images/rest/404.jpg'">
						</a>
						<div class="card-body">
							<div class="restId" style="display: none;">${rc.restId}</div>
								<h4>${rc.restVO.restName}</h4>
								<h6>${rc.restVO.restType}</h6>
								<h6>${rc.restVO.restAddress}</h6>
								<div class="d-flex justify-content-between">
									<span class="d-inline">
												<img class="collection-icon -on fullheart" style="cursor: pointer;"
													src="${pageContext.request.contextPath}/static/images/rest/fullheart.png">
									</span> 
									<span class="d-inline">
										<a	href="${pageContext.request.contextPath}/Rest/Restinfo?restId=${rc.restId}">
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
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		
	<script>

	$(document).ready(function() {
		
		$(".collection-icon").click(function() {
			let restId = $(this).closest("div.card-body").find(".restId").text();
			let memId = "${memId}";
			doCollection("delete", memId, restId);
		});
		
		function doCollection(action, memId, restId) {
			let url = "/DepthSpace/RestApi/doRestCollection?action="+action+"&memId="+memId+"&restId="+restId;
			fetch(url, {
				method: "POST",
				})
				.then(response => {
		            if (!response.ok) {
		                throw new Error(`HTTP error! status: ${response.status}`);
		            }
		            return response.text();
				})
				.then(data => {
					Swal.fire("已取消收藏");
					location.reload(true);
				})
				.catch(error => {
					console.error(error);
				});
		}
		
	});


</script>

	<jsp:include page="/indexpage/footer.jsp" />
</body>

</html>