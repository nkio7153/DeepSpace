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
<!-- <script src="https://code.jquery.com/jquery-3.7.1.js"></script> -->
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
                    var collectbutton = '<button class="btn btn-primary collect-button float-end like-button" data-articleid="' + item.articleId + '"><i class="far fa-heart"></i></button>';
                    var likeButton = '<button class="btn btn-primary like-buttons likes-button" data-articleid="' + item.articleId + '"><i class="far fa-thumbs-up"></i></button>';
                    var reportButton = '<button class="btn btn-warning report-button"><i class="fas fa-exclamation reporticon"></i></button>';
                    var card = $('<div class="col-lg-4 col-md-6 mb-3">')
                    .append('<div class="cards">' +
                    		'<li class="list-group-item d-flex justify-content-between align-items-center">會員ID: ' + item.memId + collectbutton + '</li>' +
                        '<img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img">' +
                        '<div class="card-body card-content">' +
                            '<h5 class="card-title">' + item.artiTitle + '</h5>' +
                            '<div class="card-text hidden">'  + cleanArtiText + '</div>' +
                        '</div>' +
                        '<ul class="list-group list-group-flush">' +                       
                            '<li class="list-group-item hidden-status">文章類型: ' + item.artiTypeId + '</li>' +
                            '<li class="list-group-item hidden-status">文章編號: ' + item.articleId + '</li>' +
                            '<li class="list-group-item hidden-status likeicon">'  + item.artiLk +  '</li>' +
                        '</ul>' +
                        '<div class="card-footer d-flex justify-content-between align-items-center">' +
                            '<small class="text-muted">發布時間: ' + formattedDate + '</small>' +    
                            '<div>' + // 新增一個容器包住兩個按鈕
                            reportButton +   // 檢舉按鈕
                            likeButton +     // 按讚按鈕
                        	'</div>' +
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
    	            let articlesLikeList = response.articlesLikeList;
    	            let forumArticlesList = response.forumArticlesList;
    	            forumArticlesList.forEach(function(item) {
    	                var base64ImageData = item.base64Str;
    	                var date = new Date(item.artiTime);
    	                var formattedDate = formatDate(date);
    	                var cleanArtiText = item.artiText
    	                .replace(/<span[^>]*>/g, '')
    	                .replace(/<\/span>/g, '');
    	                
    	                var isCollected = articlesCollectList.some(collectArticle => collectArticle.articleId === item.articleId);
    	                var buttonClass = isCollected ? 'btn btn-primary collect-button float-end collected' : 'btn btn-primary collect-button float-end';
    	                var heartIcon = isCollected ? '<i class="fas fa-heart"></i>' : '<i class="far fa-heart"></i>';
    	                var collectButton = '<button class="' + buttonClass + ' like-button" data-articleid="' + item.articleId + '">' + heartIcon + '</button>';
    	                
    	                var islike = articlesLikeList.some(likeArticle => likeArticle.articleId === item.articleId);
    	                var thumbIcon = islike ? '<i class="fas fa-thumbs-up"></i>' : '<i class="far fa-thumbs-up"></i>';
    	                var likeButtonClass = islike ? 'btn btn-primary like-buttons liked' : 'btn btn-primary like-buttons float-end';
    	                var likeButton = '<button class="' + likeButtonClass + ' likes-button" data-articleid="' + item.articleId + '">' + thumbIcon + '</button>';
    	               
    	                var reportButton = '<button class="btn btn-warning report-button" data-articleid="' + item.articleId + '"><i class="fas fa-exclamation reporticon"></i></button>';
    	                var card = $('<div class="col-lg-4 col-md-6 mb-3">')
    	                .append('<div class="cards">' +
    	                        '<li class="list-group-item d-flex justify-content-between align-items-center">會員ID: ' + item.memId + collectButton + '</li>' +
    	                        '<img src="data:image/jpeg;base64,' + base64ImageData + '" class="card-img-top fixed-height-img">' +
    	                        '<div class="card-body card-content">' +
    	                            '<h5 class="card-title">' + item.artiTitle + '</h5>' +
    	                            '<div class="card-text hidden">'  + cleanArtiText + '</div>' +
    	                        '</div>' +
    	                        '<ul class="list-group list-group-flush">' +                           	                
    	                            '<li class="list-group-item hidden-status">文章類型: ' + item.artiTypeId + '</li>' +
    	                            '<li class="list-group-item hidden-status">文章編號: ' + item.articleId + '</li>' +
    	                            '<li class="list-group-item hidden-status likeicon">'  + item.artiLk +  '</li>' +
    	                        '</ul>' +
    	                        '<div class="card-footer d-flex justify-content-between align-items-center">' +
    	                        '<small class="text-muted">發布時間: ' + formattedDate + '</small>' +    	                        
    	                        '<div>' + // 新增一個容器包住兩個按鈕
    	                        reportButton +   // 檢舉按鈕
    	                        likeButton +     // 按讚按鈕
    	                        '</div>' +                        
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
            event.preventDefault(); // 防止預設事件，例如表單提交或連結跳轉

            // 使用 SweetAlert2 顯示彈窗
            Swal.fire({
                icon: "error", // 設置彈窗圖示為錯誤
                text: "尚未登入！", // 設置顯示的文字
                footer: '<a href="${pageContext.request.contextPath}/member/login.jsp">點此登入</a>' // 設置彈窗底部，包含一個登入連結
            });
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
    
    
    $('#articlesRow').on('click', '.like-buttons', function(event) {
        var button = $(this); // 獲取當前按鈕的 jQuery 對象
        var articleId = button.data('articleid');

        let check = $("[name='check']").text();
        if(check == "登入/註冊") {
            event.preventDefault(); // 防止預設事件，例如表單提交或連結跳轉

            // 使用 SweetAlert2 顯示彈窗
            Swal.fire({
                icon: "error", // 設置彈窗圖示為錯誤
                text: "尚未登入！", // 設置顯示的文字
                footer: '<a href="${pageContext.request.contextPath}/member/login.jsp">點此登入</a>' // 設置彈窗底部，包含一個登入連結
            });
            return; // 阻止後續代碼執行
        }

        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/like?action=add',
            data: { articleId: articleId },
            dataType: 'json',
            success: function(response) {
            	if(response.message === '添加按讚') {
            	    button.html('<i class="fas fa-thumbs-up"></i>'); // 使用實心讚
            	    button.addClass('collected');
            	} else if(response.message === '取消按讚') {
            	    button.html('<i class="far fa-thumbs-up"></i>'); // 使用空心讚
            	    button.removeClass('collected');
            	}
            }
        });
    });

    
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
	    
	    $('#articlesRow').on('click', '.cards', function() {
	    	
	    	 // 檢查點擊的元素是否為按鈕，如果是，則不進行後續操作
	        if ($(event.target).hasClass('btn') || $(event.target).hasClass('fa-heart')|| $(event.target).hasClass('fa-exclamation')||$(event.target).hasClass('fa-thumbs-up')){
	            return;
	        }
	    	 
	        var articleImage = $(this).find('img.card-img-top').attr('src');
	        var articleTitle = $(this).find('h5.card-title').text();
	        var articleText = $(this).find('div.card-text').html();
	        var articleDate = $(this).find('.text-muted').text().replace('發布時間: ', '');
	        var artiLk = $(this).find('.likeicon').text().replace('總讚數: ', '');

	        // 填充模態對話框中的內容
	        $('#articleImage').attr('src', articleImage);
	        $('#articleTitle').text(articleTitle);
	        $('#articleText').html(articleText);
	        $('#articleDate').text('發布時間: ' + articleDate);
	        $('#artiLk').text(artiLk + '人按讚');

	        // 顯示模態對話框
	        $('#articleDetailsModal').modal('show');
	    });
	    
	    
	    $('#articlesRow').on('click', '.report-button', function(event) {
	        let check = $("[name='check']").text();
	        if(check == "登入/註冊") {
	            event.preventDefault(); // 防止預設事件，例如表單提交或連結跳轉

	            // 使用 SweetAlert2 顯示彈窗
	            Swal.fire({
	                icon: "error", // 設置彈窗圖示為錯誤
	                text: "尚未登入！", // 設置顯示的文字
	                footer: '<a href="${pageContext.request.contextPath}/member/login.jsp">點此登入</a>' // 設置彈窗底部，包含一個登入連結
	            });
	            return; // 阻止後續代碼執行
	        }

	        var articleId = $(this).data('articleid');
	        $('#articleId').val(articleId);
	        var now = new Date();
	        var formattedTime = formatDate(now);
	        $('#reportTime').val(formattedTime);
	        $('#reportModal').modal('show');
	    });

	    $('#reportForm').submit(function(event) {
	        event.preventDefault(); // 阻止表單的默認提交行為
	        
	        // 獲取檢舉原因的輸入值
	        var reportContent = $('#reportReason').val().trim();

	        // 檢查是否輸入了檢舉原因
	        if(reportContent === '') {
	            // 如果沒有輸入，顯示錯誤提示
	            Swal.fire({
	                icon: 'error',
	                title: '錯誤',
	                text: '請輸入檢舉原因！'
	            });
	        } else {
	            // 如果輸入了檢舉原因，則序列化表單數據並發送 AJAX 請求
	            var formData = $(this).serialize();

	            $.ajax({
	                type: 'post',
	                url: '<%=request.getContextPath()%>/report?action=add',
	                data: formData,
	                dataType: 'json',
	                success: function(response) {
	                    // 如果提交成功，顯示成功提示並關閉模態框
	                    Swal.fire({
	                        position: "top-center",
	                        icon: "success",
	                        title: "檢舉成功",
	                        showConfirmButton: false,
	                        timer: 1000
	                    });
	                    $('#reportModal').modal('hide');
	                 	// 清空表單
	                    $('#reportForm')[0].reset();
	                }
	            });
	        }
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
       padding-right: 0px;
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
	
	#articlesRow {
  		display: flex;
  		flex-wrap: wrap;
	}

	.cards {
	  background-color: #fff;
	  border: 1px solid #ccc;
	  border-radius: 5px;
	  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	  transition: transform 0.2s ease-in-out;
	}

	.cards:hover {
	  transform: scale(1.05);
	  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	}
	
	div.modal-body {
    padding: 2.5rem !important;
	}
	
	.likes-button{
       background-color: #A7BEC6;
       border-color: transparent;
	}
	
	.likes-button:hover {
       outline: none;
       box-shadow: none !important;
       background-color: #5B7493;
       border-color: transparent;
     }
     
     .likes-button:focus, .likes-button:active{
       outline: none;
       box-shadow: none !important;
       background-color: #A7BEC6;
       border-color: transparent;
     }
     
		@media (max-width: 991px) {
		  .card {
		    width: 100%;
		  }
		}
		
		@media (min-width: 992px) {
		  .card {
		    width: 45rem;
		  }
		}
     
     .report-button{
     	margin-right: 12px;
     	background-color: #FF0000 !important;
    	border-color: #FF0000 !important;
    	width: 40px;
     }
      .reportborder:hover, .reportborder:focus, .reportborder:active, .reportborder:visited {
       outline: none;
       box-shadow: none !important;
     }
     
     .reporticon{
     color:	#FFFFFF;
     font-size: 20px;
     }
	
		     .select {
	    background-color: #63bfc9;
	    color: white;
	    padding: 8px 40px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
		}
		
		.select:hover {
       outline: none;
       box-shadow: none !important;
       background-color: #63bfc9;
       border-color: transparent;
     }
     
     .select:focus, .select:active{
       outline: none;
       box-shadow: none !important;
       background-color: #63bfc9;
       border-color: transparent;
     }
</style>

</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<h3 class="text-primary bg-light p-3 border border-primary text-center shadow">文章列表</h3>
<div id="list" class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
    <form method="post" action="<%=request.getContextPath()%>/forumArticles.do?action=doArtiTypeList">
        <label for="artiTypeId">選擇文章類型：</label>
        <select id="artiTypeId" name="artiTypeId">
        </select>
        <input type="submit" value="查詢">
    </form>
    <a type="button" class="btn btn-primary select" href="${pageContext.request.contextPath}/forumArticles/list.jsp">所有文章</a>
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
        <div class="card">
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
          <div class="card-footer d-flex justify-content-between align-items-center">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
             <h5 class="card-text float-end" id="artiLk">總讚數:</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reportModalLabel">檢舉文章</h5>
                <button type="button" class="btn-close reportborder" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="reportForm">
                    <div class="mb-3">
                        <label for="reportReason" class="form-label">檢舉原因</label>
                        <textarea class="form-control reportborder" id="reportReason" name="reportContent" rows="3"></textarea>
                    </div>
                    <input type="hidden" id="articleId" name="articleId">
                    <input type="hidden" id="reportTime" name="reportTime">
    				<input type="hidden" id="reportStatus" name="reportStatus" value="0">
                    <button type="submit" class="btn btn-danger">提交檢舉</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script> -->
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
