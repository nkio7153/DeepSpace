<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.attractions.service.AttractionsService"%>
<%@ page import="com.depthspace.attractions.model.AttractionsVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<%
// AttractionsService attrs = new AttractionsService();
// List<AttractionsVO> list = attrs.getAll();
// pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>景點列表</title>

<jsp:include page="/indexpage/head.jsp" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet"
	href="<c:url value='/static/css/frontendlist.css'/>">

</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div class="container mt-5">
		<div class="row">

			<!-- 左側篩選條件 -->

			<div class="col-md-3">
				<form method="post" id="searchForm"
					action="${pageContext.request.contextPath}/attr/search">
					<!-- 					搜尋框 -->
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="景點名稱"
							id="attractionName" name="attractionsName"
							value="${attractions.attractionsName}" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-primary" type="submit">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>

					<h4>選擇你想去的縣市吧!</h4>
					<div class="form-group">
						<c:forEach var="areaItem" items="${city}" varStatus="status">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="cityId_${status.index}" name="cityId"
									value="${areaItem.cityId}"> <label
									class="custom-control-label" for="cityId_${status.index}">
									${areaItem.cityName} </label>
							</div>
						</c:forEach>
					</div>
				</form>
			</div>

			<!-- 右側景點列表 -->
			<div class="col-md-9" id="attractionsRight">
				<div style="text-align: center; margin-bottom: 10px;">
					<b style="font-size: 22px;"> 共有 <font color=#344648>
							${fn:length(list)} </font> 筆
					</b>
				</div>

				<%-- 				<%@ include file="page1.file"%> --%>
				<div class="form-group">

					<c:forEach var="listItem" items="${list}" varStatus="status">
						<a
							href="${pageContext.request.contextPath}/attr/oneList?attractionsId=${listItem.attractionsId}"
							class="no-underline"> <!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/attractionsImage?attractionsId=${listItem.attractionsId}"
											alt="" class="attractions-img h-100">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${listItem.attractionsName}</h5>
											<p class="card-text">${listItem.address}&ensp;|&ensp;</p>
											<p class="card-text">
												
												<c:choose>
													<c:when test="${fn:length(listItem.description) > 60}">
													${fn:substring(listItem.description,0,60)}...
													</c:when>
													<c:otherwise>
													${listItem.description}
													</c:otherwise>
												</c:choose>
												<%-- <label for="attractionsId_${status.index}"> --%>
												<%-- ${listItem.description} --%>
												<!-- </label> -->
											</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>

				</div>
				<%-- 			    <%@ include file="page2.file"%> --%>

			</div>
		</div>
	</div>

	<!-- jQuery & Bootstrap JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 	<script -->
<!-- 		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->

<div style="width: 100%; max-width: 1400px;">
    <!-- 頁腳的內容放在這裡 -->
    <jsp:include page="/indexpage/footer.jsp" />
</div>


</body>
</html>

