<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.depthspace.attractions.service.AttractionsTypeService"%>
<%@ page import="com.depthspace.attractions.model.AttractionsTypeVO"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>
    <title>新增景點</title>
    <style>
    	.viewalert {
		    --bs-alert-bg: #0000 !important; 
		    --bs-alert-border-color: #0000 !important; 
			color: #ec8383 !important;
		    padding-left: 0px !important;
		    font-size: small !important;;
		}
    </style>

    <%-- include --%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>

<body>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid my-0">
        <div class="row">
            <div class="col-lg-2 g-3 my-0">
                <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
            </div>

            <div class="col-lg-10 g-2 transparent rounded my-0">
                <%-- include end--%>
                <div class="table-list">
                    <div class="container mt-5">
                        <h5 class="mb-4">新增景點</h5>
                        <form action="<%=request.getContextPath()%>/attractionsEnd/add2" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="attractionsTypesId">景點類型<span style="color: #f27474;">* </span><span class="viewalert"><%= request.getAttribute("errorMessageAttractionsTypesId") != null ? request.getAttribute("errorMessageAttractionsTypesId"): "" %></span>
                                    </label>
                                    <select name="attractionsTypesId" id="attractionsTypesId" class="form-control" >
                                        <option value="">請選擇景點類型</option>
	                                        <c:forEach var="typeItem" items="${attractionsTypes}">
		                                        <c:choose>
						                            <c:when test="${typeItem.attractionsTypeId == checkAttractionsTypesId}">
										                <option value="${typeItem.attractionsTypeId}" selected>${typeItem.typeName}</option>
										            </c:when>
										            <c:otherwise>
										                <option value="${typeItem.attractionsTypeId}">${typeItem.typeName}</option>
										            </c:otherwise>
									        	</c:choose>
								        	</c:forEach>
<%--                                         <c:forEach var="typeItem" items="${attractionsTypes}"> --%>
<%--                                             <option value="${typeItem.attractionsTypeId}">${typeItem.typeName}</option> --%>
<%--                                         </c:forEach> --%>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="attractionsName">景點名稱<span style="color: #f27474;">* </span><span class="viewalert"><%= request.getAttribute("errorMessageAattractionsName") != null ? request.getAttribute("errorMessageAattractionsName"): "" %></span>
                                    </label>
                                    <input type="text" class="form-control" id="attractionsName" name="attractionsName" value="<%= request.getAttribute("attractionsName") != null ? request.getAttribute("attractionsName") : "" %>" >
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="city">縣市<span style="color: #f27474;">* </span><span class="viewalert"><%= request.getAttribute("errorMessageCityId") != null ? request.getAttribute("errorMessageCityId"): "" %></span></label>
                                    <select name="city" id="city" class="form-control" >
                                        <option value="">請選擇縣市</option>
                                        	<c:forEach var="typeItem" items="${city}">
		                                        <c:choose>
						                            <c:when test="${typeItem.cityId == checkCityId}">
										                <option value="${typeItem.cityId}" selected>${typeItem.cityName}</option>
										            </c:when>
										            <c:otherwise>
										                <option value="${typeItem.cityId}">${typeItem.cityName}</option>
										            </c:otherwise>
									        	</c:choose>
								        	</c:forEach>
<%--                                         <c:forEach var="typeItem" items="${city}"> --%>
<%--                                             <option value="${typeItem.cityId}">${typeItem.cityName}</option> --%>
<%--                                         </c:forEach> --%>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="area">區域<span style="color: #f27474;">* </span><span class="viewalert"><%= request.getAttribute("errorMessageAreaId") != null ? request.getAttribute("errorMessageAreaId"): "" %></span></label>
                                    <select name="area" id="area" class="form-control" >
                                    	<c:forEach var="typeItem" items="${area}">
		                                        <c:choose>
						                            <c:when test="${typeItem.areaId == checkAreaId}">
										                <option value="${typeItem.areaId}" selected>${typeItem.areaName}</option>
										            </c:when>
										            <c:otherwise>
										                
										            </c:otherwise>
									        	</c:choose>
								        	</c:forEach>
                                    
                                        <!-- <c:forEach var="typeItem" items="${area}">
                                            <option value="${typeItem.areaId}">${typeItem.areaName}</option>
                                        </c:forEach> -->
                                    </select>
                                </div>

                                <div class="col-12 mb-3">
                                    <label for="address">地址<span style="color: #f27474;">* </span><span class="viewalert"><%= request.getAttribute("errorMessageAddress") != null ? request.getAttribute("errorMessageAddress"): "" %></span></label>
                                    <input type="text" class="form-control" id="address" name="address" value="<%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %>" >
                                </div>

                                <div class="col-12 mb-3">
                                    <label for="attractionsImg">圖片</label>
                                    <input type="file" class="form-control-file" id="attractionsImg" name="attractionsImg" onchange="previewImage(event)" >
                                </div>

                                <div class="col-12 mb-3">
                                    <label>圖片預覽</label>
                                    <div id="imagePreview" style="width: 300px" class="text-center"></div>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label for="description">描述<span style="color: #f27474;">* </span></label>
                                    <textarea class="form-control" id="description" name="description" rows="4" ></textarea>
                                    <script>
                                        CKEDITOR.replace('description');
                                    </script>
                                </div>
                            </div>

                            <input type="submit" class="btn btn-primary" name="action" value="送出">
                        </form>
                    </div>
                </div>
                <%-- include --%>
            </div>
        </div>
    </div>

    <%-- include end --%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/backend/attractions/js/add.js"></script>
</body>
</html>
