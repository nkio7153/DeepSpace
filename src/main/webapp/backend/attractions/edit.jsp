<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.depthspace.attractions.service.AttractionsTypeService"%>
<%@ page import="com.depthspace.attractions.model.AttractionsTypeVO"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>
<title>修改景點內容</title>

<%--  include --%>
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
		<h5>修改景點內容</h5>
		<form action="<%=request.getContextPath()%>/attractionsEnd/add2" method="post" enctype="multipart/form-data">
			<div class="row">
				
				<div class="form-group col-md-6">
				    <label for="attractionsTypesId">景點類型</label>
				    <select name="attractionsTypesId" id="attractionsTypesId" class="form-control" required>
				        <option value="">請選擇景點類型</option>
				        <c:forEach var="typeItem" items="${attractionsTypes}">
				            <option value="${typeItem.attractionsTypeId}"
				            	${typeItem.attractionsTypeId == attrvo.attractionsTypeId.attractionsTypeId ? 'selected' : ''}>
				            ${typeItem.typeName}</option>
				        </c:forEach>
				    </select>
				</div>

				<!-- 景點名稱 -->
				<div class="form-group col-md-6">
					<label for="attractionsName">景點名稱</label> 
<!-- 					<input type="text" id="memAcc" name="memAcc" value="" required> -->
					<input type="text" class="form-control" id="attractionsName" name="attractionsName" value="${attrvo.attractionsName}" required>
				</div>
				<!-- 地址 -->
				<div class="form-group col-md-6" style="display: flex;">
				    <!-- 縣市 -->
				    <select name="city" id="city" class="form-control" required>
				        <option value="">請選縣市</option>
				        <c:forEach var="typeItem" items="${city}">
				            <option value="${typeItem.cityId}"
				            	
				            >${typeItem.cityName}</option>
				        </c:forEach>
				    </select>
				    
				    <!-- 區域 -->
				    <select name="area" id="area" class="form-control" required>
				       
				        <c:forEach var="typeItem" items="${area}">
				            <option value="${typeItem.areaId}" 
				            	${typeItem.areaId == attrvo.areaId ? 'selected' : ''}>
				            ${typeItem.areaName}</option>
				        </c:forEach>

				    </select>
				    
				    <label for="address">地址</label>
				    <input type="text" class="form-control" id="address" name="address" value="${newAddress}" required>
				</div>
				<!-- 圖片 -->
				<div class="form-group col-md-6">
					<label for="attractionsImg">圖片</label> <input type="file"
						class="form-control-file" id="attractionsImg" name="attractionsImg" onchange="previewImage(event)">
				</div>

<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label>圖片預覽</label> -->
<!-- 					<div id="imagePreview"></div> -->
<!-- 				</div> -->
				<!-- 介紹 -->
				<div class="form-group col-md-12">
					<label for="description">描述</label>
					<textarea class="form-control" id="description" name="description"
						rows="4" required></textarea>
					<script>
						CKEDITOR.replace('description');
					</script>
				</div>
				
			</div>

			<input type="submit" class="btn btn-primary" name="action" value="送出">
		</form>
	</div>

			</div>
<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->

<script src="${pageContext.request.contextPath}/backend/attractions/js/add.js"></script>
</body>
</html>
