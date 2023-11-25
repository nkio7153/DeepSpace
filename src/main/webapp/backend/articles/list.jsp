<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    $.ajax({
        url: '<%=request.getContextPath()%>/forumArticles.do?action=backlist',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            var rows = '';
            $.each(data, function(key, value){
            	var base64ImageData = value.base64Str;
            	var date = new Date(value.artiTime);
                var formattedDate = formatDate(date);
                var cleanArtiText = value.artiText
                .replace(/<span[^>]*>/g, '')
                .replace(/<\/span>/g, '')
                .replace(/<p[^>]*>/g, '')
                .replace(/<\/p>/g, '');
                rows += '<tr>';
                rows += '<td class="text-center">' + value.articleId + '</td>';
                rows += '<td class="text-center">' + value.memId + '</td>';
                rows += '<td class="text-center">' + value.artiTypeId + '</td>';
                rows += '<td class="text-center">' + value.artiTitle + '</td>';
                rows += '<td class="text-center">' + formattedDate + '</td>';
                rows += '<td class="text-center arti-content">' + cleanArtiText + '</td>';
                rows += '<td class="text-center">' + value.artiLk + '</td>';
                rows += '<td class="text-center pic"><img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img"></td>';
                rows += '<td class="text-center">' +
                '<div class="d-flex flex-column align-items-center">' +
                '<button onclick="showModal(\'' + value.articleId + '\', \'' + value.artiTitle + '\', \'' + formattedDate + '\', \'' + cleanArtiText + '\', \'' + base64ImageData + '\')" class="btn btn-info btn-sm my-1 btn-fixed-width"><i class="fas fa-eye"></i></button>' +
                '<button class="delete-btn btn btn-danger btn-sm my-1 btn-fixed-width" data-article-id="' + value.articleId + '"><i class="fas fa-trash"></i></button>' +
                '</div>' +
                '</td>';
                rows += '</tr>';
            });
            $("#listTable tbody").html(rows);
        }
    });
    
    //刪除該篇文章
    $(document).on("click", ".delete-btn", function() {
        var articleIdToDelete = $(this).data("article-id");
        // 首先顯示確認彈窗
        Swal.fire({
            title: "您確定嗎?",
            text: "您將無法恢復此操作！",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "是的",
            cancelButtonText: "取消"
        }).then((result) => {
            // 如果用戶確認了刪除操作
            if (result.isConfirmed) {
                // 執行 AJAX 請求來刪除文章
                $.ajax({
                    url: '<%=request.getContextPath()%>/forumArticles.do?action=backdel&articleId=' + articleIdToDelete,
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {
                        // 刪除成功，顯示提示彈窗
                        Swal.fire({
                            title: "已刪除！",
                            text: "文章已被刪除。",
                            icon: "success"
                        }).then(() => {
                            // 這裡可以替代 location.reload(); 以非重載的方式從頁面中移除該文章的元素
                            // 例如使用 jQuery 移除該行
                            $('button[data-article-id="' + articleIdToDelete + '"]').closest('tr').remove();
                        });
                    }
                });
            }
        });
    });
});

function formatDate(date) {
    var year = date.getFullYear();
    var month = padZero(date.getMonth() + 1); // 月份從0開始
    var day = padZero(date.getDate());
    var hours = padZero(date.getHours());
    var minutes = padZero(date.getMinutes());
    var seconds = padZero(date.getSeconds());
    return year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}

function padZero(value) {
    return value < 10 ? '0' + value : value;
}

//搜尋單篇文章
function showArticle() {
    var articleId = $('#articleIdInput').val();
    if (articleId) {
        $.ajax({
            url: '<%=request.getContextPath()%>/forumArticles.do?action=backlist',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                var selectedArticle = data.find(article => article.articleId == articleId);
                if (selectedArticle) {
                    var row = '';
                    var base64ImageData = selectedArticle.base64Str;
                    var date = new Date(selectedArticle.artiTime);
                    var formattedDate = formatDate(date);
                    var cleanArtiText = selectedArticle.artiText
                        .replace(/<span[^>]*>/g, '')
                        .replace(/<\/span>/g, '')
                        .replace(/<p[^>]*>/g, '')
                        .replace(/<\/p>/g, '');
                    row += '<tr>';
                    row += '<td class="text-center">' + selectedArticle.articleId + '</td>';
                    row += '<td class="text-center">' + selectedArticle.memId + '</td>';
                    row += '<td class="text-center">' + selectedArticle.artiTypeId + '</td>';
                    row += '<td class="text-center">' + selectedArticle.artiTitle + '</td>';
                    row += '<td class="text-center">' + formattedDate + '</td>';
                    row += '<td class="text-center arti-content">' + cleanArtiText + '</td>';
                    row += '<td class="text-center">' + selectedArticle.artiLk + '</td>';
                    row += '<td class="text-center pic"><img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img"></td>';
                    row += '<td class="text-center">' +
                    '<div class="d-flex flex-column align-items-center">' +
                    '<button onclick="showModal(\'' + selectedArticle.articleId + '\', \'' + selectedArticle.artiTitle + '\', \'' + formattedDate + '\', \'' + cleanArtiText + '\', \'' + base64ImageData + '\')" class="btn btn-info btn-sm my-1 btn-fixed-width"><i class="fas fa-eye"></i></button>' +
                    '<button class="delete-btn btn btn-danger btn-sm my-1 btn-fixed-width" data-article-id="' + selectedArticle.articleId + '"><i class="fas fa-trash"></i></button>' +
                    '</div>' +
                    '</td>';
                    row += '</tr>';
                    $("#listTable tbody").html(row);
                } else {
                    alert('沒有找到匹配的文章');
                }
            },
            complete: function() {
                $('#articleIdInput').val('');
            }
        });
    }
}

//查詢全部文章
function performNewAction() {
    $.ajax({
        url: '<%=request.getContextPath()%>/forumArticles.do?action=backlist',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            var rows = '';
            $.each(data, function(key, value){
            	var base64ImageData = value.base64Str;
            	var date = new Date(value.artiTime);
                var formattedDate = formatDate(date);
                var cleanArtiText = value.artiText
                .replace(/<span[^>]*>/g, '')
                .replace(/<\/span>/g, '')
                .replace(/<p[^>]*>/g, '')
                .replace(/<\/p>/g, '');
                rows += '<tr>';
                rows += '<td class="text-center">' + value.articleId + '</td>';
                rows += '<td class="text-center">' + value.memId + '</td>';
                rows += '<td class="text-center">' + value.artiTypeId + '</td>';
                rows += '<td class="text-center">' + value.artiTitle + '</td>';
                rows += '<td class="text-center">' + formattedDate + '</td>';
                rows += '<td class="text-center arti-content">' + cleanArtiText + '</td>';
                rows += '<td class="text-center">' + value.artiLk + '</td>';
                rows += '<td class="text-center pic"><img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img"></td>';
                rows += '<td class="text-center">' +
                '<div class="d-flex flex-column align-items-center">' +
                '<button onclick="showModal(\'' + value.articleId + '\', \'' + value.artiTitle + '\', \'' + formattedDate + '\', \'' + cleanArtiText + '\', \'' + base64ImageData + '\')" class="btn btn-info btn-sm my-1 btn-fixed-width"><i class="fas fa-eye"></i></button>' +
                '<button class="delete-btn btn btn-danger btn-sm my-1 btn-fixed-width" data-article-id="' + value.articleId + '"><i class="fas fa-trash"></i></button>' +
                '</div>' +
                '</td>';
                rows += '</tr>';
            });
            $("#listTable tbody").html(rows);
        }
    });
}

function showModal(articleId, title, date, content, imageData) {
    $('#modalArticleTitle').text(title);
    $('#modalArticleContent').text(content);
    $('#modalArticleDate').text(date);
    $('#modalArticleImage').attr('src', 'data:image/jpeg;base64,' + imageData);

    // 使用 Bootstrap 方法顯示模態框
    $('#articleModal').modal('show');
}

function closeModal() {
    // 使用 Bootstrap 方法隱藏模態框
    $('#articleModal').modal('hide');
}

</script>
<style>
    table {
        width: 95%;
        border-collapse: collapse;
        background: #fff;
        background: #fff;
    	margin-left: 35px;
    	margin-top: 10px;
    	margin-bottom: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    td {
    height: 120px; /* 設定所有單元格的高度 */
    vertical-align: middle; /* 垂直居中內容 */
	}
    th {
        background-color: #f2f2f2;
        text-align: center; /* 加入這行代碼來置中表頭 */
    }
    tr:hover {
        background-color: #ddd;
    }
    .text-center {
        text-align: center;
    }
     .arti-content {
    	max-width: 150px; /* 設置最大寬度 */
    	max-height: 100px; /* 設置最大寬度 */
    	text-overflow: ellipsis; /* 文本超出時顯示省略號 */
    	overflow: hidden; /* 隱藏超出部分的文本 */
    	white-space: nowrap; /* 保持文本在一行內 */
	}
	.pic img {
	    width: 150px; /* 設定圖片固定寬度 */
	    height: 100px; /* 設定圖片固定高度 */
	    object-fit: fill; /* 會改變圖片的寬高比以填滿容器 */
	}
	.marg{
		margin-top: 10px;
	}
	.select {
	    background-color: #63bfc9;
	    color: white;
	    padding: 8px 40px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	}
	.selects {
	    background-color: #63bfc9;
	    color: white;
	    padding: 8px 40px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	}
	.write{
		height: 40px;
    	width: 270px;
	}
	.container {
	    margin: 10px; /* 設定外部容器的邊距 */
	}

	.write{
	    margin-left: 15px;
	}
	.select{
	    margin-left: 15px;
	}
	.selects{
	    margin-right: 30px;
	}
	.btn-fixed-width {
    width: 35px; /* 或者任何您需要的寬度 */
	}
</style>
</head>
<body>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
	<div class="row">
	  
	<div class="col-lg-2 g-3 mt-1">
	<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
	</div>
	
	<div class="col-lg-10 g-2 transparent rounded mt-1">
	 <h3 class="text-center mt-2">文章列表</h3>
	        <hr class="my-0">
			<div class="container" style="display: flex; justify-content: space-between;">
			    <div>
			        <input type="text" id="articleIdInput" placeholder="輸入文章編號" class="write">
			        <button onclick="showArticle()" class="select">搜尋文章</button>
			    </div>
			    <button onclick="performNewAction()" class="selects">顯示所有文章</button>
			</div>
<table id="listTable" class="listTable">
    <thead>
        <tr>
            <th>文章編號</th>
            <th>會員編號</th>
            <th>文章類型編號</th>
            <th>文章標題</th>
            <th>發布日期</th>
            <th>文章內容</th>
            <th>按讚數</th>
            <th>圖片</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <!-- AJAX 動態數據將被填充在這裡 -->
    </tbody>
</table>

<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>

<!-- 模態框 -->
<div id="articleModal" class="modal fade" tabindex="-1" aria-labelledby="articleDetailsModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="articleDetailsModalLabel">文章詳細信息</h5>
      </div>
      <div class="modal-body">
        <!-- Bootstrap 卡片結構 -->
        <div class="card">
          <!-- 照片放在最上方 -->
          <img src="" class="card-img-top" alt="文章圖片" id="modalArticleImage">
          <div class="card-body">
            <!-- 文章標題 -->
            <h3 class="card-title" id="modalArticleTitle">文章標題</h3>
            <!-- 文章內容 -->
            <p class="card-text" id="modalArticleContent">文章內容</p>
            <!-- 發布日期 -->
            <p class="card-text"><small class="text-muted" id="modalArticleDate">發布日期</small></p>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
      </div>
    </div>
  </div>
</div>



</body>
</html>
