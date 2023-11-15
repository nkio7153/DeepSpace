<%@ page import="com.depthspace.forum.model.forumarticles.service.ForumArticlesServiceImpl" %>
<%@ page import="com.depthspace.forum.model.forumarticles.ForumArticlesVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>論壇文章清單</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>

$(document).ready(function() {
    let check = $("[name = 'check']").text();
    if (check=="登入/註冊"){
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
                    var cleanArtiText = item.artiText
	                .replace(/<span[^>]*>/g, '')
	                .replace(/<\/span>/g, '');
                    var likesSpan = '<span class="likes float-right"><i class="fas fa-thumbs-up"></i> ' + item.artiLk + '</span>';
                    var card = $('<div class="col-md-4 mb-3">')
                    .append('<div class="card h-100">' +
                    		'<li class="list-group-item d-flex justify-content-between align-items-center">會員ID: ' + item.memId + likesSpan + '</li>' +
                        '<img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img">' +
                        '<div class="card-body card-content">' +
                            '<h5 class="card-title">' + item.artiTitle + '</h5>' +
                            '<div class="card-text hidden">'  + cleanArtiText + '</div>' +
                        '</div>' +
                        '<ul class="list-group list-group-flush">' +                       
                            '<li class="list-group-item hidden-status">訊息ID: ' + item.msgId + '</li>' +
                            '<li class="list-group-item hidden-status">文章類型: ' + item.artiTypeId + '</li>' +
                            '<li class="list-group-item hidden-status">文章編號: ' + item.articleId + '</li>' +
                        '</ul>' +
                        '<div class="card-footer">' +
                            '<small class="text-muted">發布時間: ' + formattedDate + '</small>' +
                            '<button class="btn btn-primary collect-button float-end like-button" data-articleid="' + item.articleId + '"><i class="far fa-heart"></i></button>'+                        
                            '</div>' +
                    '</div>');
                    $('#articlesRow').append(card);
                });
            }
        })
    }else{
    	  $.ajax({
    	        type: 'get',
    	        url: '<%=request.getContextPath()%>/forumArticles.do?action=list', // 替換為實際的 URL
    	        cache: false,
    	        dataType: 'json',
    	        async: false,
    	        success: function(response) {
    	            let articlesCollectList = response.articlesCollectList;
    	            let forumArticlesList = response.forumArticlesList;
    	            forumArticlesList.forEach(function(item) {
    	                var base64ImageData = item.base64Str;
    	                var date = new Date(item.artiTime);
    	                var formattedDate = formatDate(date);
    	                var cleanArtiText = item.artiText
    	                .replace(/<span[^>]*>/g, '')
    	                .replace(/<\/span>/g, '');
    	                var likesSpan = '<span class="likes float-right"><i class="fas fa-thumbs-up"></i> ' + item.artiLk + '</span>';
    	                var isCollected = articlesCollectList.some(collectArticle => collectArticle.articleId === item.articleId);
    	                var buttonClass = isCollected ? 'btn btn-primary collect-button float-end collected' : 'btn btn-primary collect-button float-end';
    	                var heartIcon = isCollected ? '<i class="fas fa-heart"></i>' : '<i class="far fa-heart"></i>';    	               
    	                var card = $('<div class="col-md-4 mb-3">')
    	                .append('<div class="card h-100">' +
    	                        '<li class="list-group-item d-flex justify-content-between align-items-center">會員ID: ' + item.memId + likesSpan + '</li>' +
    	                        '<img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img">' +
    	                        '<div class="card-body card-content">' +
    	                            '<h5 class="card-title">' + item.artiTitle + '</h5>' +
    	                            '<div class="card-text hidden">'  + cleanArtiText + '</div>' +
    	                        '</div>' +
    	                        '<ul class="list-group list-group-flush">' +                       
    	                            '<li class="list-group-item hidden-status">訊息ID: ' + item.msgId + '</li>' +
    	                            '<li class="list-group-item hidden-status">文章類型: ' + item.artiTypeId + '</li>' +
    	                            '<li class="list-group-item hidden-status">文章編號: ' + item.articleId + '</li>' +
    	                        '</ul>' +
    	                        '<div class="card-footer">' +
    	                            '<small class="text-muted">發布時間: ' + formattedDate + '</small>' +    	                        
    	                            '<button class="' + buttonClass + ' like-button" data-articleid="' + item.articleId + '">' + heartIcon + '</button>'+                        
    	                        '</div>' +
    	                '</div>');
    	                $('#articlesRow').append(card);
    	            });
    	        }
    	    });
    }
    
    $('#articlesRow').on('click', '.collect-button', function(event) {
        var button = $(this); // 獲取當前按鈕的 jQuery 對象
        var articleId = button.data('articleid');

        let check = $("[name='check']").text();
        if(check == "登入/註冊") {
            event.preventDefault();
            window.alert("請先登入");
            return; // 阻止後續代碼執行
        }

        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/collect?action=add',
            data: { articleId: articleId },
            dataType: 'json',
            success: function(response) {
            	if(response.message === '添加收藏') {
            	    button.html('<i class="fas fa-heart"></i>'); // 使用實心愛心圖標
            	    button.addClass('collected');
            	} else if(response.message === '取消收藏') {
            	    button.html('<i class="far fa-heart"></i>'); // 使用空心愛心圖標
            	    button.removeClass('collected');
            	}
            }
        });
    });
    
//     $.ajax({
//         type: "post",
<%--         url: '<%=request.getContextPath()%>/forumArticles.do?action=getmemlist', --%>
//         dataType: "json",
//         success: function(data) {
//             var selectBox = $("#memId");
//             selectBox.empty(); 
//             $.each(data, function(index, value) {
//                 selectBox.append($("<option></option>").attr("value", value).text(value));
//             });
//         }
//     });
    
	    $.ajax({
	        type: "post",
	        url: '<%=request.getContextPath()%>/forumArticles.do?action=getArtiTypeList',
	        dataType: "json",
	        success: function(data) {
	            var selectBox = $("#artiTypeId");
	            selectBox.empty(); 
	            $.each(data, function(index, atvo) {
	                selectBox.append($("<option></option>").attr("value", atvo.artiTypeId).text(atvo.artiTypeText));
	            });
	        }
	    });
	    
	    $('#articlesRow').on('click', '.card', function() {
	    	
	    	 // 檢查點擊的元素是否為按鈕，如果是，則不進行後續操作
	        if ($(event.target).hasClass('btn') || $(event.target).hasClass('fa-heart')){
	            return;
	        }
	    	 
	        var articleImage = $(this).find('img.card-img-top').attr('src');
	        var articleTitle = $(this).find('h5.card-title').text();
	        var articleText = $(this).find('div.card-text').html();
	        var articleDate = $(this).find('.text-muted').text().replace('發布時間: ', '');

	        // 填充模態對話框中的內容
	        $('#articleImage').attr('src', articleImage);
	        $('#articleTitle').text(articleTitle);
	        $('#articleText').html(articleText);
	        $('#articleDate').text('發布時間: ' + articleDate);

	        // 顯示模態對話框
	        $('#articleDetailsModal').modal('show');
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
    
    function checkSession(e){
        let check = $("[name='check']").text();
        if (check == "登入/註冊"){
            e.preventDefault(); // 阻止點擊事件的預設行為，即防止按鈕的超連結跳轉
            window.alert("請先登入"); // 顯示警告訊息，提醒使用者登入
        }
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
    }

    .likes {
        display: flex;
        align-items: center;
        justify-content: flex-end; /* 將點讚數靠右顯示 */
    }
    
    .list-group-item {
        display: flex;
        justify-content: space-between; /* 新增：使內容分佈左右兩端 */
        align-items: center; /* 新增：垂直居中對齊 */
    }

    .hidden-status {
    	display: none;
	}
	
		
	.collected i.fas.fa-heart {
	font-size:24px;
    color: red; /* 已收藏的愛心顏色 */
	}

	i.far.fa-heart {
	font-size:24px;
    color: black; /* 未收藏的愛心顏色 */
	}
	
	.like-button{
       color: transparent;
       background-color: transparent;
       border-color: transparent;
     }

	.like-button:hover, .like-button:focus, .like-button:active, .like-button:visited {
       outline: none;
       box-shadow: none !important;
       color: transparent;
       background-color: transparent;
       border-color: transparent;
     }
	
 	.btn:hover, .btn:focus, .btn:active, .btn:visited {
       outline: none;
       box-shadow: none !important;
     }
     
    div.hidden {
     display:none;
     }
     
    div.font-sizee p {
    font-size: 20px;
	}
	
	div.font-sizee {
    font-size: 20px;
	}
</style>

</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<div id="list" class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>文章清單</h1>
            <!-- 查詢表單 -->
<%--     <form method="post" action="<%=request.getContextPath()%>/forumArticles.do?action=domemlist"> --%>
<!--         <label for="memId">選擇會員編號：</label> -->
<!--         <select id="memId" name="memId"> -->
<!--         </select> -->
<!--         <input type="submit" value="查詢"> -->
<!--     </form> -->
    <form method="post" action="<%=request.getContextPath()%>/forumArticles.do?action=doArtiTypeList">
        <label for="artiTypeId">選擇文章類型：</label>
        <select id="artiTypeId" name="artiTypeId">
        </select>
        <input type="submit" value="查詢">
    </form>
		<button type="button" class="btn btn-primary" onclick="checkSession(event);window.location.href='<%=request.getContextPath()%>/forumArticles.do?action=getmemlist'">我的文章</button>
		<button type="button" class="btn btn-primary" onclick="checkSession(event);window.location.href='<%=request.getContextPath()%>/forumArticles.do?action=doArtiCollectList'">我的收藏</button>
        <button type="button" class="btn btn-primary" onclick="checkSession(event);window.location.href='<%=request.getContextPath()%>/forumArticles.do?action=addArticle'">新增文章</button>
    </div>
    <div id="articlesRow" class="row">
        <!-- 卡片內容將會通過 jQuery 動態加載到這裡 -->
    </div>
</div>

<!-- 模態對話框 -->
<div class="modal fade" id="articleDetailsModal" tabindex="-1" aria-labelledby="articleDetailsModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="articleDetailsModalLabel">文章詳細信息</h5>
        <button type="button" class="btn btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="card" style="width: 45rem;">
          <div id="pic">
            <img src="" class="card-img-top" alt="文章圖片" id="articleImage">
          </div>
          <div class="card-body">
            <h3 class="card-title" id="articleTitle">文章標題:</h3>
            <div class="card-text font-sizee" id="articleText">文章內容:</div>
            <br>
            <div class="d-flex justify-content-end">
              <h6 class="card-text" id="articleDate">發布時間:</h6>
            </div>
          </div>
          <div class="card-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
