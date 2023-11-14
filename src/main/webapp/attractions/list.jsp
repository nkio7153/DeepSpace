<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
				<form id="searchForm">
					<!-- 搜尋框 -->
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
									id="cityId_${status.index}" name="areaId"
									value="${areaItem.cityId}">
									<label class="custom-control-label" for="cityId_${status.index}">
										${areaItem.cityName} 
									</label>
							</div>
						</c:forEach>
					</div>
				</form>
			</div>

			<!-- 右側景點列表 -->
			<div class="col-md-9" id="attricationsRight">
				<div class="form-group">
					<c:forEach var="listItem" items="${list}" varStatus="status">
						<a
							href="${pageContext.request.contextPath}/attr/oneList?attractionsId=${listItem.attractionsId}"
							class="no-underline">
							<!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="<%=request.getContextPath()%>/attractionsImage?attractionsId=${listItem.attractionsId}"
											alt="" class="attractions-img">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${listItem.attractionsName}</h5>
											<p class="card-title">${listItem.address}&ensp;|&ensp;</p>
											<p class="card-title">
												<label for="attractionsId_${status.index}">
													${listItem.description} </label>
											</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>

	<script>
 
      //左邊搜尋條件
        $(document).ready(function() {
        	// 處理表單提交事件
            $('#searchForm').on('submit', function(e) {
                e.preventDefault(); // 防止表單的默認提交行為
                // 從表單收集數據，使用 serialize() 方法將表單數據序列化為字串
                let formData = $(this).serialize();
//                 console.log(formData)
                
                let areaId = $('input[name="areaId"]:checked').val();
                let attractionsName = $('#attractionName').val();
                
                console.log(areaId + attractionsName)
             	// 發送 fetch 請求
             	let url = "${pageContext.request.contextPath}/attr/search?areaId=" + areaId + "&attractionsName=" + attractionsName;
             	fetch(url)
		            .then(function(response){
		                return response.text();
		            })
		            .then(function(data){
// 		                console.log(data);
		            })
		            .catch(function(error){
		                console.log(error);
		            })
		        })
                
            });
    </script>
	<!-- 景點列表 -->
	<!--                 <div class="attraction-lists"> -->
	<%--                     <c:forEach items="${list}" var="attractions"> --%>
	<%--                         <a href="${pageContext.request.contextPath}/attractions/details?attractionsId=${attractions.attractionsId}" class="no-underline"> --%>
	<!--                             <div class="card mb-3 clickable-card"> -->
	<!-- 								<div class="row no-gutters"> -->
	<!-- 									<div class="col-md-4"> -->
	<!-- 										可以加上景點的圖片 -->
	<!-- 										<img -->
	<%-- 											src="<%=request.getContextPath()%>/attractionimage?attractionsId=${attractions.attractionId}" --%>
	<!-- 											alt="Attraction Image" class="attraction-img"> -->
	<!-- 									</div> -->
	<!-- 									<div class="col-md-8"> -->
	<!-- 										<div class="card-body"> -->
	<%-- 											<h5 class="card-title">${attractions.attractionName}</h5> --%>
	<%-- 											<p class="card-title">${attractions.city.cityName}</p> --%>
	<!-- 											<p class="card-text"> -->
	<!-- 												這裡可以加上景點的描述 -->
	<%-- 												${attractions.description} --%>
	<!-- 											</p> -->
	<!-- 											其他景點相關的資訊 -->
	<!-- 										</div> -->
	<!-- 									</div> -->
	<!-- 								</div> -->
	<!-- 							</div> -->
	<!-- 						</a> -->
	<%--                     </c:forEach> --%>
	<!--                 </div> -->

	

	

	<!-- 這裡可以保留或修改分頁部分，視情況而定 -->




	<!-- 				景點列表  -->
	<!-- 				<div class="attraction-lists" id="attractionright"> -->
	<%-- 					<c:forEach items="${list}" var="attractions"> --%>
	<%-- 					    <a href="${pageContext.request.contextPath}/attractions/details?attractionId=${attractions.attractionsId}" class="no-underline"> --%>
	<!-- 					        <div class="card mb-3 clickable-card"> -->
	<!-- 					            <div class="row no-gutters"> -->
	<!-- 					                <div class="col-md-4"> -->
	<!-- 					                    可以加上景點的圖片 -->
	<%-- 					                    <img src="<%=request.getContextPath()%>/attractionimage?attractionId=${attractions.attractionId}" alt="Attraction Image" class="attraction-img"> --%>
	<!-- 					                </div> -->
	<!-- 					                <div class="col-md-8"> -->
	<!-- 					                    <div class="card-body"> -->
	<%-- 					                        <h5 class="card-title">${attractions.attractionName}</h5> --%>
	<%-- 					                        <p class="card-title">${attractions.city.cityName}</p> --%>
	<!-- 					                        <p class="card-text"> -->
	<!-- 					                            這裡可以加上景點的描述 -->
	<%-- 					                            ${attractions.description} --%>
	<!-- 					                        </p> -->
	<!-- 					                        其他景點相關的資訊 -->
	<!-- 					                    </div> -->
	<!-- 					                </div> -->
	<!-- 					            </div> -->
	<!-- 					        </div> -->
	<!-- 					    </a> -->
	<%-- 					</c:forEach> --%>

	<!-- 				</div> -->
	<!-- jQuery & Bootstrap JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>

