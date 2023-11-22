<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		rel="stylesheet">
	<title>修改票券</title>
	<script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>
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
	<div class="container mt-5">
		<div class="container mt-5">
			<div class="container mt-5">
				<h5>修改票券</h5>
				<form action="<%=request.getContextPath()%>/ticketmg/edit" method="post" 
				enctype="multipart/form-data" onsubmit="return showSwal();">
					<div class="row">
						<!-- ID -->
						<input type="hidden" name="ticketId" value="${ticket.ticketId}">
						<!-- 類型 -->
						<div class="form-group col-md-6">
							<label for="ticketTypeId">票券類型</label> <select name=ticketTypeId
								id="ticketTypeId" class="form-control" required>
								<option value="">請選擇票券類型</option>
								<c:forEach var="typeItem" items="${ticketTypes}">
									<!-- 選單原先值 -->
									<option value="${typeItem.ticketTypeId}"
										${typeItem.ticketTypeId == ticket.ticketType.ticketTypeId ? 'selected' : ''}>
										${typeItem.typeName}</option>
								</c:forEach>
							</select>
						</div>
						<!-- 票券名稱 -->
						<div class="form-group col-md-6">
							<label for="ticketName">票券名稱</label> <input type="text"
								class="form-control" id="ticketName" name="ticketName" required
								value="${ticket.ticketName}">
						</div>
						<!-- 價格 -->
						<div class="form-group col-md-6">
							<label for="price">價格</label> <input type="number"
								class="form-control" id="price" name="price" required min="0"
								value="${ticket.price}">
						</div>
						<!-- 數量 -->
						<div class="form-group col-md-6">
							<label for="stock">數量</label> <input type="number"
								class="form-control" id="stock" name="stock" required min="0"
								value="${ticket.stock}">
						</div>
						<div class="row">
						<!-- 圖片上傳 -->
						<div class="form-group col-md-12">
						    <label>圖片列表</label>
						    <div id="image-list" class="d-flex flex-wrap">
						    </div>
						    <button type="button" id="addImage" class="btn btn-primary mt-2" name="ticketImages[]" >新增圖片</button>
						</div>            
						<!-- 使用天數 -->
						<div class="form-group col-md-12">
							<label for="validDays">使用天數</label> <input type="text" required min="0"
								title="請輸入數字，例如: 365" class="form-control" id="validDays"
								name="validDays" value="${ticket.validDays}">
						</div>

						<!-- 描述 -->
						<div class="form-group col-md-12">
							<label for="description">描述</label>
							<textarea class="form-control" id="description"
								name="description" rows="4">${ticket.description}</textarea>
						<script>
									CKEDITOR.replace('description');
								</script>
						</div>
						
						

						<!-- 區域 -->
						<div class="form-group col-md-6">
							<label for="cityId">區域</label> <select name="cityId" id="cityId" required
								class="form-control">
								<option value="">請選擇縣市</option>
								<c:forEach var="cityItem" items="${cities}">
									<!-- 選單原先值 從選項中比對存入的資料值-->
									<option value="${cityItem.cityId}"
										${cityItem.cityName == ticket.city.cityName ? 'selected' : ''}>
										${cityItem.cityName}</option>
								</c:forEach>
							</select>
						</div>
						<!-- 地址 -->
						<div class="form-group col-md-6">
						    <label for="address">地址</label>
						    <input type="text" class="form-control" id="address" name="address" required value="${ticket.address}" onchange="geocodeAddress()">
						</div>

						<!-- 經度 -->
						<div class="form-group col-md-6">
						    <label for="longitude">經度</label>
						    <input type="text" class="form-control" id="longitude" name="longitude" required value="${ticket.longitude}">
						</div>

						<!-- 緯度 -->
						<div class="form-group col-md-6">
							<label for="latitude">緯度</label> <input type="text"
								class="form-control" id="latitude" name="latitude" required min="0"
								value="${ticket.latitude}">
						</div>

						<!-- 上下架狀況 -->
						<div class="form-group col-md-6">
							<label>上下架</label><br> <input type="radio" id="on"
								name="status" value="1" ${ticket.status == 1 ? 'checked' : ''}>
							<label for="on">上架</label> <input type="radio" id="off"
								name="status" value="0" ${ticket.status == 0 ? 'checked' : ''}>
							<label for="off">未上架</label>
						</div>
					</div>

					<div><button type="submit" class="btn btn-primary" name="action">送出</button></div>
				</form>
			</div>
		</div>
	</div>
<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>
	<script	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAxM8Qw1Zvra1gPDfG5mwO-7FlPtXUoFns&libraries=places&callback=initAutocomplete" async defer></script>
    <script>
    function geocodeAddress() {
        var address = document.getElementById('address').value;
        var geocoder = new google.maps.Geocoder();

        geocoder.geocode({'address': address}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var latitude = results[0].geometry.location.lat();
                var longitude = results[0].geometry.location.lng();

                document.getElementById('latitude').value = latitude;
                document.getElementById('longitude').value = longitude;
            } else {
                alert('Geocode 不成功，原因: ' + status);
            }
        });
    }
        
    $(document).ready(function() {
        const ticketId = ${ticket.ticketId};
        var newImageCount = 0; // 初始化新圖片計數器

        $.getJSON("<%=request.getContextPath()%>/ticketallimage?action=getIds&ticketId=" + ticketId, function(serialIds) {
            var imageListDiv = $("#image-list").empty(); // 清空現有的圖片列表
            serialIds.forEach(function(id) {
                var imageCard = $('<div class="card m-2" style="width: 18rem;"></div>');
                var image = $('<img class="card-img-top" style="max-height: 200px; object-fit: cover;">')
                    .attr("src", "<%=request.getContextPath()%>/ticketallimage?action=getImage&imageId="  + id + "&ticketId=" + ticketId);
                var cardBody = $('<div class="card-body"></div>');
                var fileInput = $('<input type="file" name="image_' + id + '">').change(function(event){
                    createImagePreview(event, image);
                });
                var hiddenInput = $('<input type="hidden" name="imageIds" value="' + id + '">');

                cardBody.append(fileInput).append(hiddenInput);
                imageCard.append(image).append(cardBody);
                imageListDiv.append(imageCard); 
            });
        });

        // 新增圖片按鈕的功能
        $("#addImage").click(function() {
            newImageCount++;
            var fileInputName = 'ticketImage_new_' + newImageCount; 
            var newImageCard = $('<div class="card m-2" style="width: 18rem;"></div>');
            var image = $('<img class="card-img-top" style="display:none; max-height: 200px; object-fit: cover;">');
            var cardBody = $('<div class="card-body"></div>');
            var fileInput = $('<input type="file" name="' + fileInputName + '">').change(function(event){
                createImagePreview(event, image);
            });

            cardBody.append(fileInput);
            newImageCard.append(image).append(cardBody);
            $("#image-list").append(newImageCard); 
        });
    });

    // 圖片上傳預覽函數
    function createImagePreview(event, imageElement) {
        var reader = new FileReader();
        reader.onload = function(e) {
            imageElement.attr('src', e.target.result).show();
        };
        reader.readAsDataURL(event.target.files[0]);
    }

    // 送出提示動畫
    function showSwal() {
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: '修改成功',
            showConfirmButton: false,
            timer: 1500
        });
    }
</script>
</body>
</html>
