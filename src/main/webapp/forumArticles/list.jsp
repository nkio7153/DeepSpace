<%@ page import="com.depthspace.forum.model.forumarticles.service.ForumArticlesServiceImpl" %>
<%@ page import="com.depthspace.forum.model.forumarticles.ForumArticlesVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>論壇文章清單</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>

$(document).ready(function() {
    $.ajax({
        type: 'post',
        url: '<%=request.getContextPath()%>/forumArticles.do?action=list',
        cache: false,
        dataType: 'json',
        async: false,
        success: function(data) {
            data.forEach(function(item) {
                var base64ImageData = item.base64Str;
                var date = new Date(item.artiTime);
                var formattedDate = formatDate(date);
                var card = $('<div class="col-md-4 mb-3">')
                .append('<div class="card h-100">' +
                    '<img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img">' +
                    '<div class="card-body card-content">' +
                        '<h5 class="card-title">' + item.artiTitle + '</h5>' +
                        '<p class="card-text">' + item.artiText + '</p>' +
                    '</div>' +
                    '<ul class="list-group list-group-flush">' +
                        '<li class="list-group-item">會員ID: ' + item.memId + '</li>' +
                        '<li class="list-group-item">訊息ID: ' + item.msgId + '</li>' +
                        '<li class="list-group-item hidden-status">文章狀態: ' + (item.artiStatus === 1 ? '啟用' : '禁用') + '</li>' +
                    '</ul>' +
                    '<div class="card-footer d-flex justify-content-between align-items-center">' +
                        '<small class="text-muted">發布時間: ' + formattedDate + '</small>' +
                        '<div>' +
                            '<button class="btn btn-primary btn-sm">修改</button>' +
                            '<button class="btn btn-danger btn-sm ml-2">刪除</button>' +
                            '<span class="likes"><i class="fas fa-thumbs-up"></i> ' + item.artiLk + '</span>' +
                        '</div>' +
                    '</div>' +
                '</div>');
                $('#articlesRow').append(card);
            });
        }
    })  
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
    .fixed-height-img {
        height: 200px;
        object-fit: cover;
        width: 100%;
    }

    .card-footer {
        padding: 0.5rem 1rem; /* 保留原有的 padding */
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .card-footer .btn {
        margin-right: 10px; /* 為按鈕增加右邊距 */
    }

    .card-footer .btn:last-child {
        margin-right: 0; /* 最後一個按鈕不加右邊距 */
    }

    .likes {
        display: flex;
        align-items: center;
    }

    .hidden-status {
    	display: none;
	}
</style>

</head>
<body>
<div id="list" class="container mt-5">
    <h1 class="mb-4">文章清單</h1>
    <div id="articlesRow" class="row">
        <!-- 卡片內容將會通過 jQuery 動態加載到這裡 -->
    </div>
</div>
<!-- 引入 Bootstrap JS 和 Popper.js 依賴 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
