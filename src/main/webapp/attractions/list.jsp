<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.attractions.service.AttractionsService"%>
<%@ page import="com.depthspace.attractions.model.AttractionsVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%
AttractionsService attrs = new AttractionsService();
List<AttractionsVO> list = attrs.getAll();
pageContext.setAttribute("list", list);
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

<style>
	#form-right{
		margin-top: 10px;
	}
</style>
</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div class="container mt-5">
		<div class="row">

			<!-- 左側篩選條件 -->

			<div class="col-md-3 mt-5">
				<form method="post" id="searchForm"
					action="${pageContext.request.contextPath}/attr/search">
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
				</form>

					<div class="form-group">
					    <label for="citySelect">選擇你想去的縣市吧!</label>
					    <select class="form-control" id="citySelect" name="cityId">
					        <c:forEach var="areaItem" items="${city}">
					            <option value="${areaItem.cityId}">${areaItem.cityName}</option>
					        </c:forEach>
					    </select>
					</div>
<!-- 					<h4>選擇你想去的縣市吧!</h4> -->
<!-- 					<div class="form-group"> -->
<%-- 						<c:forEach var="areaItem" items="${city}" varStatus="status"> --%>
<!-- 							<div class="custom-control custom-checkbox"> -->
<!-- 								<input type="checkbox" class="custom-control-input" -->
<%-- 									id="cityId_${status.index}" name="cityId" --%>
<%-- 									value="${areaItem.cityId}"> <label --%>
<%-- 									class="custom-control-label" for="cityId_${status.index}"> --%>
<%-- 									${areaItem.cityName} </label> --%>
<!-- 							</div> -->
<%-- 						</c:forEach> --%>
<!-- 					</div> -->
				
			</div>

			<!-- 右側景點列表 -->
			<div class="col-md-9" id="attractionsRight">
				<%@ include file="page1.file"%>
				<div class="form-group" id="form-right">

					<c:forEach var="listItem" items="${list}" varStatus="status"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<a
							href="${pageContext.request.contextPath}/attr/oneList?attractionsId=${listItem.attractionsId}"
							class="no-underline"> <!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="<%=request.getContextPath()%>/attractionsImage?attractionsId=${listItem.attractionsId}"
											alt="" class="attractions-img h-100">
									</div>
									<div class="col-md-8">
										<div class="card-body" >
											<h5 class="card-title">${listItem.attractionsName}</h5>
											<p class="card-text">${listItem.address}&ensp;|&ensp;</p>
											<p class="card-text" >
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

				<div style="text-align: center;" class="page">
					<%@ include file="../tour/page2.file"%>
				</div>

			</div>


		</div>
	</div>

	<script>
		
	$(document).ready(function() {
        // 監聽下拉式選單值改變事件
        $("#citySelect").change(function() {
            // 取得選擇的縣市值
            var cityId = $(this).val();
            console.log(cityId)

            // 使用 AJAX 將值傳送到後端
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/attr/search",
                data: { cityId: cityId },
                success: function(response) {
                    var html = "";
                    for (var i = 0; i < response.length; i++) {
                        var listItem = response[i];
                        var attractionsId = listItem.attractionsId;
                        var attractionsName = listItem.attractionsName;
                        var description = listItem.description;
                        var address = listItem.address;
                        var contextPath = "${pageContext.request.contextPath}";

                        html += "<a href='" + window.location.pathname.replace("/list", "") + "/oneList?attractionsId=" + attractionsId + "' class='no-underline'>";
                        html += "<div class='card mb-3 clickable-card'>";
                        html += "<div class='row no-gutters'>";
                        html += "<div class='col-md-4'>";
                        html += "<img src='" + contextPath + "/attractionsImage?attractionsId=" + attractionsId + "' alt='' class='attractions-img h-100'>";
                        html += "</div>";
                        html += "<div class='col-md-8'>";
                        html += "<div class='card-body'>";
                        html += "<h5 class='card-title'>" + attractionsName + "</h5>";
                        html += "<p class='card-text'>" + address + "&ensp;|&ensp;</p>";
                        html += "<p class='card-text'>";

                        if (description.length > 60) {
                            html += description.substring(0, 60) + "...";
                        } else {
                            html += description;
                        }

                        html += "</p>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                        html += "</a>";
                    }

                 	// 插入總筆數的 div 元素
                    var totalRecordsDiv = "<div style='text-align: center; margin-bottom: 13px;'><b style='font-size: 22px;'> 共有 <font color=#344648>" + response.length + "</font> 筆</b></div>";
                    
                    // 將生成的 HTML 和總筆數的 div 插入到 #attractionsRight 中
                    $('#attractionsRight .form-group').empty().append(totalRecordsDiv + html);
                    $('.page').hide();
                 	// 設置 #form-right 的 margin-top 為 46px
                    $('#form-right').css('margin-top', '4px');
                },
                error: function(error) {
                    console.error("傳送到後端時發生錯誤");
                    console.error(error);
                }
            });
        });
    });
</script>

	<!-- jQuery & Bootstrap JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- 	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->

	<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>

