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
</head>
<body>
	<div class="container mt-5">
		<div class="container mt-5">
			<div class="container mt-5">
				<h1>修改票券</h1>
				<form action="<%=request.getContextPath()%>/backendticket/mgedit"
					method="post" enctype="multipart/form-data">
					<div class="row">
						<!-- ID -->
						<input type="hidden" name="ticketId" value=${ticket.ticketId}>
						<!-- 類型 -->
						<div class="form-group col-md-6">
							<label for="ticketTypeId">票券類型</label> <select name=ticketTypeId
								id="ticketTypeId" class="form-control">
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
								class="form-control" id="ticketName" name="ticketName"
								value="${ticket.ticketName}">
						</div>

						<!-- 價格 -->
						<div class="form-group col-md-6">
							<label for="price">價格</label> <input type="number"
								class="form-control" id="price" name="price"
								value="${ticket.price}">
						</div>

						<!-- 數量 -->
						<div class="form-group col-md-6">
							<label for="stock">數量</label> <input type="number"
								class="form-control" id="stock" name="stock"
								value="${ticket.stock}">
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
								<label>圖片預覽</label> <img id="imagePreview"
									src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}"
									alt="圖片預覽" style="max-width: 100%; max-height: 300px;" />
							</div>
						</div>

						<!-- 是否為主圖 -->
						<div class="form-group col-md-6">
							<label for="isMainImage">是否為主圖</label> <input type="checkbox"
								id="isMainImage" name="isMainImage" value="1"
								${ticketImages.isMainImage == 1 ? 'checked' : ''}>
						</div>

						<!-- 使用天數 -->
						<div class="form-group col-md-12">
							<label for="validDays">使用天數</label> <input type="text"
								title="請輸入數字，例如: 365" class="form-control" id="validDays"
								name="validDays" value="${ticket.validDays}">
						</div>

						<!-- 描述 -->
						<div class="form-group col-md-12">
							<label for="description">描述</label>
							<textarea class="form-control" id="description"
								name="description" rows="4">${ticket.description}</textarea>
						</div>

						<!-- 區域 -->
						<div class="form-group col-md-6">
							<label for="cityId">區域</label> <select name="cityId" id="cityId"
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
							<label for="address">地址</label> <input type="text"
								class="form-control" id="address" name="address"
								value="${ticket.address}">
						</div>

						<!-- 經度 -->
						<div class="form-group col-md-6">
							<label for="longitude">經度</label> <input type="text"
								class="form-control" id="longitude" name="longitude"
								value="${ticket.longitude}">
						</div>

						<!-- 緯度 -->
						<div class="form-group col-md-6">
							<label for="latitude">緯度</label> <input type="text"
								class="form-control" id="latitude" name="latitude"
								value="${ticket.latitude}">
						</div>

						<!-- 上下架狀況 -->
						<div class="form-group col-md-6">
							<label>上下架</label><br> <input type="radio" id="on"
								name="status" value="1" ${ticket.status == 1 ? 'checked' : ''}>
							<label for="on"">上架</label> <input type="radio" id="off"
								name="status" value="0" ${ticket.status == 0 ? 'checked' : ''}>
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