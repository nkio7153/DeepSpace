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
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$(document).ready(function() {
    $.ajax({
        type: 'post',
        url:  '<%=request.getContextPath()%>/forumArticles.do?action=list',
        contentType: "application/x-www-form-urlencoded",
        cache: false,
        dataType: 'json',
        async: false,
        success: function(data) {
            data.forEach(function(item) {
                var binaryImageData = item.artiImg;
                var base64ImageData = btoa(String.fromCharCode.apply(null, new Uint8Array(binaryImageData)));
                
                var date = new Date(item.artiTime);
                var formattedDate = formatDate(date);
                var row = '<tr>' +
                          '<td>' + item.articleId+ '</td>' +
                          '<td>' + item.memId + '</td>' +
                          '<td>' + item.msgId + '</td>' +
                          '<td>' + item.artiTypeId + '</td>' +
                          '<td>' + item.artiTitle + '</td>' +
                          '<td>' + formattedDate + '</td>' +
                          '<td>' + item.artiText + '</td>' +
                          '<td>' + item.artiLk + '</td>' +
                          '<td>' + item.artiStatus + '</td>' +
                          '<td><img src="data:image/jpeg;base64,' + base64ImageData + '"></td>' +
                          '<td><input type="button" value="修改" onclick="processUpDate();"></td>' +
                          '<td><input type="button" value="刪除" onclick="processDelete();"></td>' +
                          '</tr>';
                $('#dataTable tr:last').after(row);
            });
        }
    })  
});

function formatDate(date) {
    var year = date.getFullYear();
    var month = padZero(date.getMonth() + 1); // 月份从0开始
    var day = padZero(date.getDate());
    var hours = padZero(date.getHours());
    var minutes = padZero(date.getMinutes());
    var seconds = padZero(date.getSeconds());
    return year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}

// 函數用於補零
function padZero(value) {
    return value < 10 ? '0' + value : value;
}
</script>
</head>
<body>
<div id="list">
    <h1>分帳表清單</h1>
    <table id='dataTable'>
        <tr>
            <th>文章編號</th>
            <th>會員編號 </th>
            <th>上層留言編號</th>
            <th>文章類型編號</th>
            <th>文章標題</th>
            <th>文章發佈時間</th>
            <th>文章內容</th>
            <th>文章點讚數</th>
            <th>文章狀態</th>
            <th>圖片訊息</th>
            <th>功能</th>
            <th>功能</th>
        </tr>
    </table>
</div>
</body>
</html>
