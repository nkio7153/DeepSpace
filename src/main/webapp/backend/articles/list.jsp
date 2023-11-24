<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
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
                rows += '<td class="text-center"><button class="delete-btn" data-article-id="' + value.articleId + '"><i class="fas fa-trash"></i></button></td>';
                rows += '</tr>';
            });
            $("#listTable tbody").html(rows);
        }
    });
    
    //刪除該篇文章
    $(document).on("click", ".delete-btn", function() {
        var articleIdToDelete = $(this).data("article-id");

        // 發送Ajax請求刪除文章
        $.ajax({
            url: '<%=request.getContextPath()%>/forumArticles.do?action=backdel&articleId=' + articleIdToDelete,
            type: 'POST',
            dataType: 'json',
            success: function(data) {
                location.reload();
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

</script>
<style>
    table {
        width: 95%;
        border-collapse: collapse;
        background: #fff;
        background: #fff;
    	margin-left: 35px;
    	margin-top: 10px;
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

</body>
</html>
