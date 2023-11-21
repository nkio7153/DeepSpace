<%@ page import="com.depthspace.forum.model.forumarticles.service.ForumArticlesServiceImpl" %>
<%@ page import="com.depthspace.forum.model.forumarticles.ForumArticlesVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>我的文章</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {	
	
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
	
	
	//刪除文章收藏
	 $('.collect-button').click(function() {
	        var articleId = $(this).data('article-id');
	        $.ajax({
	            type: 'post',
	            url: '<%=request.getContextPath()%>/collect?action=add',
	            data: { articleId: articleId },
	            dataType: 'json',
	            success: function(response) {
	            	window.location.href='<%=request.getContextPath()%>/forumArticles.do?action=doArtiCollectList';
	            	alert("成功刪除");            			
	            },
	        });
	    });
	
    $('#articlesRow').on('click', '.cards', function() {
    	
    	// 檢查點擊的元素是否為按鈕，如果是，則不進行後續操作
        if ($(event.target).hasClass('btn')) {
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
});
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
	
	div.modal-body {
    padding: 2.5rem !important;;
	}
	
	#articlesRow {
  		display: flex;
  		flex-wrap: wrap;
	}

	.cards {
	  background-color: #fff; /* 设置卡片的背景颜色 */
	  border: 1px solid #ccc; /* 设置边框 */
	  border-radius: 5px; /* 圆角边框 */
	  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影效果 */
	  transition: transform 0.2s ease-in-out; /* 添加hover时的平移效果 */
	}

	.cards:hover {
	  transform: scale(1.05); /* 鼠标悬停时稍微放大卡片 */
	  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 提高阴影深度 */
	}
	
	div.modal-body {
    padding: 2.5rem !important;;
	}
</style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
 	<div id="list" class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
		    <form method="post" action="<%=request.getContextPath()%>/forumArticles.do?action=doArtiTypeList">
		        <label for="artiTypeId">選擇文章類型：</label>
		        <select id="artiTypeId" name="artiTypeId">
		        </select>
		        <input type="submit" value="查詢">
		    </form>
<%-- 				<a type="button" class="btn btn-primary" onclick="checkSession(event)" href="${pageContext.request.contextPath}/forumArticles/list.jsp">所有文章</a> --%>
<%-- 				<a type="button" class="btn btn-primary" onclick="checkSession(event)" href="${pageContext.request.contextPath}/forumArticles.do?action=getmemlist">我的文章</a> --%>
<%-- 				<a type="button" class="btn btn-primary" onclick="checkSession(event)" href="${pageContext.request.contextPath}/forumArticles.do?action=doArtiCollectList">我的收藏</a> --%>
<%-- 		        <a type="button" class="btn btn-primary" onclick="checkSession(event)" href="${pageContext.request.contextPath}/forumArticles.do?action=addArticle">新增文章</a> --%>
	    </div>
        <div id="articlesRow" class="row">
            <c:forEach var="article" items="${list}">
                <div class="col-md-4 mb-3">
                    <div class="cards h-100">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            會員ID: ${article.memId} 
                        </li>
                        <img src="data:image/jpeg;base64,${article.base64Str}" class="card-img-top fixed-height-img">
                        <div class="card-body card-content">
                            <h5 class="card-title">${article.artiTitle}</h5>
                            <div class="card-text hidden">${article.artiText}</div>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-none">訊息ID: ${article.msgId}</li>
                            <li class="list-group-item d-none">文章ID: ${article.articleId}</li>
                            <li class="list-group-item d-none">文章類型: ${article.artiTypeId}</li>
                            <li class="list-group-item d-none likeicon">${article.artiLk}</li>
                        </ul>
                        	<div class="card-footer d-flex justify-content-between align-items-center">
                        	<fmt:formatDate value="${article.artiTime}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
                            <small class="text-muted">發布時間:${formattedDate}</small>
                            <button class="btn btn-primary collect-button float-end" data-article-id="${article.articleId}">取消收藏</button>
                       		</div>
                    	</div>                   
                </div>
            </c:forEach>
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
            <h5 class="card-text float-end" id="artiLk">總讚數:</h5>
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