<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<title>修改票券</title>
</head>
<body>
	<div class="container mt-5">
		<div class="container mt-5">
			<div class="container mt-5">
				<h1>修改票券</h1>
				<form action="<%= request.getContextPath()%>/backendticket/mgeditsuccess" method="post">
					<div class="row">
						<!-- 類型 -->
						<div class="form-group col-md-6">
							<label for="type">類型</label> <select name="type" id="type"
								class="form-control">
								<c:forEach var="typeItem" items="${ticket.ticketType.typeName}">
									<option value="${typeItem}">${typeItem}</option>
								</c:forEach>
							</select>
						</div>

						<!-- 票券名稱 -->
						<div class="form-group col-md-6">
							<label for="ticketName">票券名稱</label> <input type="text"
								class="form-control" id="ticketName" name="ticketName" value="${ticket.ticketName}" >
						</div>

						<!-- 價格 -->
						<div class="form-group col-md-6">
							<label for="price">價格</label> <input type="number"
								class="form-control" id="price" name="price" value="${ticket.price}">
						</div>

						<!-- 數量 -->
						<div class="form-group col-md-6">
							<label for="stock">數量</label> <input type="number"
								class="form-control" id="stock" name="stock" value="${ticket.stock}">
						</div>


						<div class="row">
						<!-- 圖片上傳 -->
							<div class="form-group col-md-6">
								<label for="ticketImage">圖片</label> <input type="file"
									class="form-control-file" id="ticketImage" name="ticketImage"
									onchange="previewImage(event)">
							</div>

						<!-- 圖片預覽 -->
							<div class="form-group col-md-6">
								<label>圖片預覽</label> <img id="imagePreview" src="#" alt="圖片預覽"
									style="max-width: 100%; max-height: 300px; display: none;" />
							</div>
						</div>


						<!-- 使用天數 -->
						<div class="form-group col-md-12">
							<label for="validDays">使用天數</label> <input type="text"
								title="請輸入數字，例如: 365"
								class="form-control" id="validDays" name="validDays" value="${ticket.validDays}">
						</div>

						<!-- 描述 -->
						<div class="form-group col-md-12">
							<label for="description">描述</label>
							<textarea class="form-control" id="description"
								name="description" rows="4"  value="${ticket.description}"></textarea>
						</div>

						<!-- 區域 -->
						<div class="form-group col-md-6">
							<label for="cityId">區域</label> <select name="cityId"
								id="cityId" class="form-control">
								<c:forEach var="cityItem" items="${ticket.city.cityName}">
									<option value="${ticket.city.cityName}">${ticket.city.cityName}</option>
								</c:forEach>
							</select>
						</div>

						<!-- 地址 -->
						<div class="form-group col-md-6">
							<label for="address">地址</label> <input type="text"
								class="form-control" id="address" name="address" value="${ticket.address}">
						</div>

						<!-- 經度 -->
						<div class="form-group col-md-6">
							<label for="longitude">經度</label> <input type="text"
								class="form-control" id="longitude" name="longitude" value="${ticket.longitude}">
						</div>

						<!-- 緯度 -->
						<div class="form-group col-md-6">
							<label for="latitude">緯度</label> <input type="text"
								class="form-control" id="latitude" name="latitude" value="${ticket.latitude}">
						</div>

						<!-- 上下架狀況 -->
						<div class="form-group col-md-6">
							<label>上下架</label><br> <input type="radio" id="on"
								name="status" value="1"> <label for="on" ">上架</label>

							<input type="radio" id="off" name="status" value="0">
							<label for="off">未上架</label>
						</div>
					</div>

					<button type="submit" class="btn btn-primary" name="action">送出</button>
				</form>
			</div>
  		</div> 
  	</div> 			
    			
			<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
			<script
				src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
			<script
				src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>