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

<title>修改專欄文章</title>

<script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>




</head>

<body>
	<div class="container mt-5">
		<div class="container mt-5">
			<div class="container mt-5">
				<h1>修改專欄</h1>
				<form action="<%=request.getContextPath()%>/columnmg/edit"
					method="post" enctype="multipart/form-data">
					<div class="row">
						<!-- ID -->
						<input type="hidden" name="artiId" value=${columnArticles.artiId}></input>
						<!-- 類型 -->
						<div class="form-group col-md-6">
							<label for="colTypeId">專欄類型</label> <select name=colTypeId
								id="colTypeId" class="form-control">
								<option value="">請選擇專欄類型</option>
								<c:forEach var="typeItem" items="${columnTypes}">
									<!-- 選單原先值 -->
									<option value="${typeItem.colTypeId}"
										${typeItem.colTypeId == columnArticles.colType.colTypeId ? 'selected' : ''}>
										${typeItem.colTypeName}</option>
								</c:forEach>
							</select>
						</div>
						<!-- 文章標題 -->
						<div class="form-group col-md-6">
							<label for="artiTitle">文章標題</label> <input type="text"
								class="form-control" id="artiTitle" name="artiTitle"
								value="${columnArticles.artiTitle}">
						</div>
						<!-- 圖片上傳 -->
						<div class="row">
							<div class="form-group col-md-6">
								<label for="colImg">圖片</label> <input type="file"
									class="form-control-file" id="colImg" name="colImg"
									onchange="previewImage(event)">
							</div>
							<!-- 圖片預覽 -->
							<div class="form-group col-md-6">
								<label>圖片預覽</label> <img id="imagePreview"
									src="<%=request.getContextPath()%>/columnmainimage?artiId=${columnArticles.artiId}"
									alt="圖片預覽" style="max-width: 100%; max-height: 300px;" />
							</div>
						</div>

						<!-- 文章內容 獨立一行-->
						<div class="form-group col-md-12">
							<label for="description">描述</label>
							<textarea class="form-control" id="description"
								name="artiContent" rows="4">${columnArticles.artiContent}</textarea>
							<script>
								CKEDITOR.replace('description');
							</script>
						</div>
						<!-- 上下架狀況 -->
						<div class="form-group col-md-6">
							<label>上下架</label><br> <input type="radio" id="on"
								name="artiStatus" value="1"
								${columnArticles.artiStatus == 1 ? 'checked' : ''}> <label
								for="on"">上架</label> <input type="radio" id="off"
								name="artiStatus" value="0"
								${columnArticles.artiStatus == 0 ? 'checked' : ''}> <label
								for="off">未上架</label>
						</div>
					</div>

					<!-- 發文管理者 -->
					<div class="form-group col-md-6">
						<label for="adminId">發文者</label> <input type="text"
							class="form-control" id="adminId" name="adminId"
							value="${columnArticles.admin}">
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
