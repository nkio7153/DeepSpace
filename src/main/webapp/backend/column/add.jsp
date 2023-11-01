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

<title>新增專欄文章</title>

<script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>



<style>
body, label {
	font-size: 0.875rem;
	line-height: 1.5;
}

h1 {
	white-space: nowrap; /* 防止標題斷行 */
	font-size: 1.5rem;
	overflow: hidden;
	text-overflow: ellipsis; /* 標題過長省略號表示 */
}

.form-control, .btn { /* 同時縮小表單控件和按鈕的大小 */
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
</head>

<body>
	<div class="container mt-5">
		<h1>新增專欄文章</h1>
		<form action="<%=request.getContextPath()%>/columnmg/add"
			method="post" enctype="multipart/form-data">
			<div class="row">
				<!-- 類型 -->
				<!-- 關聯表格說明，三個變數，itmes是servlet傳來的list、option value為其元素值、第三個為出現在選單的文字-->
				<!-- forEach的var跟option的是有關連的，取自於其forEach遍歷的資料 -->
				<div class="form-group col-md-6">
					<label for="colTypeId">專欄類型</label> <select name="colTypeId"
						id="colTypeId" class="form-control">
						<option value="">請選擇專欄類型</option>
						<c:forEach var="typeItem" items="${columnTypes}">
							<option value="${typeItem.colTypeId}">${typeItem.colTypeName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- 文章標題 -->
				<div class="form-group col-md-6">
					<label for="artiTitle">文章標題</label> <input type="text"
						class="form-control" id="artiTitle" name="artiTitle">
				</div>
				<!-- 專欄圖片 獨立一行-->
				<div class="form-group col-md-6">
					<label for="columnImage">文章主圖</label> <input type="file"
						class="form-control-file" id="colImg" name="colImg"
						onchange="previewImage(event)">
				</div>

				<div class="form-group col-md-6">
					<label>圖片預覽</label>
					<div id="imagePreview"></div>
				</div>
				<!-- 文章內容 獨立一行-->
				<div class="form-group col-md-12">
					<label for="artiContent">描述</label>
					<textarea class="form-control" id="artiContent" name="artiContent"
						rows="4"></textarea>
					<script>
						CKEDITOR.replace('artiContent');
					</script>
				</div>
				<!-- 上下架狀況 -->
				<div class="form-group col-md-6">
					<label>上下架</label><br> <input type="radio" id="on"
						checked="true" name="artiStatus" value="1"> <label
						for="on">上架</label> <input type="radio" id="off" name="artiStatus"
						value="0"> <label for="off">未上架</label>
				</div>
				<!-- 發文管理者 -->
				<div class="form-group col-md-6">
					<label for="adminId">發文者</label> <select name="adminId"
						id="adminId" class="form-control">
						<option value="adminId">發文管理者</option>
						<c:forEach var="adminItem" items="${admins}">
							<option value="${adminItem.adminId}">${adminItem.adminName}</option>
						</c:forEach>
						</select>
				</div>
			</div>

			<button type="submit" class="btn btn-primary" name="action">送出</button>
		</form>
	</div>



	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
