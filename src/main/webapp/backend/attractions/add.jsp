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
<title>新增景點</title>

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
		<h5>新增景點</h5>
		<form action="<%=request.getContextPath()%>/attractionsEnd/add2" method="post" enctype="multipart/form-data">
			<div class="row">
				<!-- 類型 -->
				<!-- 關聯表格說明，三個變數，itmes是servlet傳來的list、option value為其元素值、第三個為出現在選單的文字-->
				<!-- forEach的var跟option的是有關連的，取自於其forEach遍歷的資料 -->
				<div class="form-group col-md-6">
				    <label for="attractionsTypesId">景點類型</label>
				    <select name="attractionsTypesId" id="attractionsTypesId" class="form-control" required>
				        <option value="">請選擇景點類型</option>
				        <c:forEach var="typeItem" items="${attractionsTypes}">
				            <option value="${typeItem.attractionsTypeId}">${typeItem.typeName}</option>
				        </c:forEach>
				    </select>
				</div>

				
				<!-- 景點名稱 -->
				<div class="form-group col-md-6">
					<label for="attractionsName">景點名稱</label> <input type="text"
						class="form-control" id="attractionsName" name="attractionsName" required>
				</div>
				<!-- 地址 -->
				<div class="form-group col-md-6" style="display: flex;">
				    <!-- 縣市 -->
				    <select name="city" id="city" class="form-control" required>
				        <option value="">請選區域</option>
				        <c:forEach var="typeItem" items="${city}">
				            <option value="${typeItem.cityId}">${typeItem.cityName}</option>
				        </c:forEach>
				    </select>
				    
				    <!-- 地址 -->
				    <select name="area" id="area" class="form-control" required>
				        <option value="">請選縣市</option>
				        <c:forEach var="typeItem" items="${area}">
				            <option value="${typeItem.areaId}">${typeItem.areaName}</option>
				        </c:forEach>
				    </select>
				    
				    <label for="address">地址</label>
				    <input type="text" class="form-control" id="address" name="address" required>
				</div>
				<!-- 圖片 -->
				<div class="form-group col-md-6">
					<label for="attractionsImg">圖片</label> <input type="file"
						class="form-control-file" id="attractionsImg" name="attractionsImg" required
						onchange="previewImage(event)">
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

			<button type="submit" class="btn btn-primary" name="action">送出</button>
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
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
	function previewImage(event) {
		var file = event.target.files[0];
		if (file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var imagesPreview = document
						.getElementById('imagePreview');

				// 創建一個div容器，包含圖片和刪除按鈕
				var container = document.createElement('div');
				container.className = 'imageContainer';

				var img = document.createElement('img');
				img.src = e.target.result;
				img.className = 'previewImg';
				container.appendChild(img);

				var deleteButton = document.createElement('div');
				deleteButton.innerText = 'x';
				deleteButton.className = 'deleteIcon';
				deleteButton.onclick = function() {
					imagesPreview.removeChild(container);
				};
				container.appendChild(deleteButton);

				imagesPreview.appendChild(container);
			}
			reader.readAsDataURL(file);
		}
	}
</script>
</body>
</html>
