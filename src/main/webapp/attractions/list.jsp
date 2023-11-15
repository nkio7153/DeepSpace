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
				 <form method="post" id="searchForm" action="<%=request.getContextPath()%>/attr/search">
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
									id="cityId_${status.index}" name="cityId"
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
			<div class="col-md-9" id="attractionsRight">
			    <div class="form-group">
			        <c:forEach var="listItem" items="${list}" varStatus="status">
			            <a href="${pageContext.request.contextPath}/attr/oneList?attractionsId=${listItem.attractionsId}" class="no-underline">
			                <!-- 整張卡片點擊 -->
			                <div class="card mb-3 clickable-card">
			                    <div class="row no-gutters">
			                        <div class="col-md-4">
			                            <img src="<%=request.getContextPath()%>/attractionsImage?attractionsId=${listItem.attractionsId}" alt="" class="attractions-img">
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                                <h5 class="card-title">${listItem.attractionsName}</h5>
			                                <p class="card-text">${listItem.address}&ensp;|&ensp;</p>
			                                <p class="card-text">
			                                    <label for="attractionsId_${status.index}">
			                                        ${listItem.description}
			                                    </label>
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
//         $(document).ready(function() {
//         	// 處理表單提交事件
//             $('#searchForm').on('submit', function(e) {
//                 e.preventDefault(); // 防止表單的默認提交行為
//                 // 從表單收集數據，使用 serialize() 方法將表單數據序列化為字串
//                 let formData = $(this).serialize();
// //                 console.log(formData)
                
//                 let cityId = $('input[name="cityId"]:checked').val();
//                 if(cityId === undefined){
//                 	cityId = 0;
//                 }
//                 let attractionsName = $('#attractionName').val();
                
// //                 console.log(cityId + attractionsName)
//              	// 發送 fetch 請求
//              	let url = "${pageContext.request.contextPath}/attr/search?cityId=" + cityId + "&attractionsName=" + attractionsName;
//              	fetch(url)
// 		            .then(function(response){
// 		                return response.text();
// 		            })
// 		            .then(function(data){
// 		                console.log(this);
// 		            	$('#attricationsRight').empty();
// 		            	// 重新加入元素
// 		            	 let jsonData = JSON.parse(data);
// 		            	// 重新加入元素
// 		            	    for (let i = 0; i < jsonData.length; i++) {
// 		            	        let newItem = jsonData[i];
// 		            	        // 重新加入元素
// 		            	        let newElement = `
// 		            	            <a href="${pageContext.request.contextPath}/attr/oneList?attractionsId=${newItem.attractionsId}" class="no-underline">
// 		            	                <div class="card mb-3 clickable-card">
// 		            	                    <div class="row no-gutters">
// 		            	                        <div class="col-md-4">
// 		            	                            <img src="${request.getContextPath()}/attractionsImage?attractionsId=${newItem.attractionsId}" alt="" class="attractions-img">
// 		            	                        </div>
// 		            	                        <div class="col-md-8">
// 		            	                            <div class="card-body">
// 		            	                                <h5 class="card-title">${newItem.attractionsName}</h5>
// 		            	                                <p class="card-title">${newItem.address}&ensp;|&ensp;</p>
// 		            	                                <p class="card-title">
// 		            	                                    <label for="attractionsId_${i}">
// 		            	                                        ${newItem.description}
// 		            	                                    </label>
// 		            	                                </p>
// 		            	                            </div>
// 		            	                        </div>
// 		            	                    </div>
// 		            	                </div>
// 		            	            </a>
// 		            	        `;
	
// 			            	// 將新元素加入到容器中
// 			            	$('#attricationsRight .form-group').append(newElement);
//                         }
// 		            })
// 		            .catch(function(error){
// 		                console.log(error);
// 		            })
// 		        })
                
//             });
      

    </script>

	<!-- jQuery & Bootstrap JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>

