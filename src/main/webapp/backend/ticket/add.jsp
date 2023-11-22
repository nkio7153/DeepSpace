<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<title>新增票券</title>
<style>
body, label {
	font-size: 0.875rem;
	line-height: 1.5;
}

h1 {
	white-space: nowrap;
	font-size: 1.5rem;
	overflow: hidden;
	text-overflow: ellipsis; 
}

.form-control, .btn { 
	font-size: 0.875rem;
}

.imageContainer { /*上傳圖片的預覽區域'*/
	display: inline-block;
	position: relative;
	margin: 10px;
	width: 100px;
	height: 100px;
	overflow: hidden;
}

.previewImg {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.deleteIcon {
	position: absolute;
	top: 0;
	right: 0;
	background-color: rgba(255, 255, 255, 0.7);
	padding: 2px 5px;
	cursor: pointer;
	font-weight: bold;
	font-size: 14px;
}
</style>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAxM8Qw1Zvra1gPDfG5mwO-7FlPtXUoFns&libraries=places&callback=initAutocomplete" async defer></script>
    <script>
        function initAutocomplete() {
            var autocomplete = new google.maps.places.Autocomplete(
                document.getElementById('address'),
                {types: ['geocode']}
            );

            autocomplete.addListener('place_changed', fillInAddress);
        }

        function fillInAddress() {
            var place = this.getPlace();
            document.getElementById('latitude').value = place.geometry.location.lat();
            document.getElementById('longitude').value = place.geometry.location.lng();
        }
    </script>
	
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
		<h5>新增票券</h5>
		<form action="<%=request.getContextPath()%>/ticketmg/add"
      	method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
			<div class="row">

				<!-- 類型 -->
				<!-- 關聯表格說明，三個變數，itmes是servlet傳來的list、option value為其元素值、第三個為出現在選單的文字-->
				<!-- forEach的var跟option的是有關連的，取自於其forEach遍歷的資料 -->
				<div class="form-group col-md-6">
					<label for="ticketTypeId">票券類型<span style="color: #f27474;">*</span></label> <select name=ticketTypeId
						id="ticketTypeId" class="form-control" required>
						<option value="">請選擇票券類型</option>
						<c:forEach var="typeItem" items="${ticketTypes}">
							<option value="${typeItem.ticketTypeId}">${typeItem.typeName}</option>
						</c:forEach>
					</select>
				</div>

				<!-- 票券名稱 -->
				<div class="form-group col-md-6">
				    <label for="ticketName">票券名稱 <span style="color: #f27474;">*</span></label>
				    <input type="text" class="form-control" id="ticketName" name="ticketName" required>
				</div>

				<!-- 價格 -->
				<div class="form-group col-md-6">
					<label for="price">價格<span style="color: #f27474;">*</span></label>
					 <input type="number" class="form-control" id="price" name="price" required min="0">
				</div>

				<!-- 數量 -->
				<div class="form-group col-md-6">
					<label for="stock">數量<span style="color: #f27474;">*</span></label> <input type="number"
						class="form-control" id="stock" name="stock" required min="0">
				</div>

				<div class="row">
					<!-- 票券圖片 -->
					<div class="form-group col-md-6">
						<input type="file" class="form-control-file" id="ticketImages" name="ticketImages[]" required multiple onchange="previewImages(event)">
					</div>

					<div class="form-group col-md-6">
						<label>圖片預覽</label>
						<div id="imagesPreview"></div>
					</div>

					<!-- 使用天數 -->
					<div class="form-group col-md-12">
						<label for="validDays">使用天數<span style="color: #f27474;">*</span></label> <input type="text"
							title="請輸入數字，例如: 365" class="form-control" id="validDays"
							name="validDays"  required min="0">
					</div>

					<!-- 描述 -->
					<div class="form-group col-md-12">
						<label for="description">描述</label>
						<textarea class="form-control" id="description" name="description" rows="4"></textarea>
						<script>
						    CKEDITOR.replace('description');
						</script>
					</div>

					<!-- 區域 -->
					<div class="form-group col-md-6">
						<label for="cityId">區域<span style="color: #f27474;">*</span></label> <select name="cityId" id="cityId"
							class="form-control" required>
							<option value="">請選擇縣市</option>
							<c:forEach var="cityItem" items="${cities}">
								<option value="${cityItem.cityId}">${cityItem.cityName}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 地址 -->
				    <div class="form-group col-md-6">
				        <label for="address">地址<span style="color: #f27474;">*</span></label>
				        <input type="text" class="form-control" id="address" name="address" required>
				    </div>
				
				    <div class="form-group col-md-6">
				        <label for="longitude">經度<span style="color: #f27474;">*</span></label>
				        <input type="text" class="form-control" id="longitude" name="longitude" required readonly>
				    </div>
				
				    <div class="form-group col-md-6">
				        <label for="latitude">緯度<span style="color: #f27474;">*</span></label>
				        <input type="text" class="form-control" id="latitude" name="latitude" required readonly>
				    </div>

					<!-- 上下架狀況 -->
					<div class="form-group col-md-6">
						<label>上下架</label><br> <input type="radio" id="on"
							checked="true" name="status" value="1"> <label for="on">上架</label>
						<input type="radio" id="off" name="status" value="0"> <label
							for="off">未上架</label>
					</div>
				<div><button type="submit" class="btn btn-primary" name="action">送出</button></div>
			</div>
		  </div>
		</form>
	</div>
<br>

<script>
$(document).ready(function() {
    $('form').on('submit', function(e) {
        var isFormValid = true;
        $(this).find(':input[required]').each(function() {
            if (!this.value.trim()) {
                isFormValid = false;
            }
        });

        if (!isFormValid) {
            e.preventDefault();
            Swal.fire({
                icon: 'error',
                title: '錯誤',
                text: '請填寫所有必填欄位',
            });
        } else {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '已送出',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
});
    function previewImages(event) {
        var files = event.target.files;
        var imagesPreview = document.getElementById('imagesPreview');

        // 清空目前的預覽圖片
        imagesPreview.innerHTML = '';

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            if (file) {
                var reader = new FileReader();
                reader.onload = function(e) {
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
    }
    
    // 送出的提示動畫
    function showSwal() {
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: '已送出',
            showConfirmButton: false,
            timer: 1500
        });
    }
    
    
    function geocode() {
        var address = document.getElementById('address').value;
        var geocoder = new google.maps.Geocoder();

        geocoder.geocode({'address': address}, function(results, status) {
            if (status === 'OK') {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
                document.getElementById('latitude').innerHTML = lat;
                document.getElementById('longitude').innerHTML = lng;
            } else {
                alert('Geocode 不成功，原因: ' + status);
            }
        });
    }
</script>
<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>
</body>
</html>
