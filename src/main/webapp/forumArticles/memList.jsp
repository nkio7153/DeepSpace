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
	
	@media (max-width: 991px) {
		.caca {
		  width: 100%;
		}
    }
		
    @media (min-width: 992px) {
		 .caca {
		  width: 45rem;
		 }
	}
	
	.notthing {
    	margin-bottom: 100px;
    	margin-top: 50px;
	}
</style>
</head>
<body>
<script>
$(document).ready(function() {
	
    $('#articlesRow').on('click', '.card', function() {
    	
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
		function processDelete(articleId) {
		    var jsonObj = { articleId: articleId };
		    $.ajax({
		        type: 'post',
		        data: jsonObj,
		        url: '<%=request.getContextPath()%>/forumArticles.do?action=del',
		        async: false,
		        success: function(data) {
		        	Swal.fire({
		        		  title: "您確定嗎？",
		        		  text: "刪除後將無法恢復！",
		        		  icon: "warning",
		        		  showCancelButton: true,
		        		  confirmButtonColor: "#3085d6",
		        		  cancelButtonColor: "#d33",
		        		  confirmButtonText: "是的"
		        		}).then((result) => {
		        		  if (result.isConfirmed) {
		        		    // 遍歷表格的每一行，尋找與 articleId 匹配的行並刪除
		        		    $('#dataTable tr').each(function() {
		        		      if ($(this).find('td:first').text() == articleId) {
		        		        $(this).remove();
		        		      }
		        		    });

		        		    // 顯示刪除成功的消息
		        		    Swal.fire({
		        		      title: "已刪除！",
		        		      text: "您的檔案已被刪除。",
		        		      icon: "success"
		        		    }).then(() => {
		        		      // 刪除成功的訊息確認後，進行頁面跳轉
		        		      window.location.href = '<%=request.getContextPath()%>/forumArticles.do?action=getmemlist';
		        		    });
		        		  }
		        		});

		        }
		    });
		}
</script>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<h3 class="text-primary bg-light p-3 border border-primary text-center shadow">我的文章列表</h3>
 	<div id="list" class="container mt-5">
        <div id="articlesRow" class="row">
            <c:forEach var="article" items="${list}">
                <div class="col-md-4 mb-3">
                 <form action="<%=request.getContextPath()%>/forumArticles/edit.jsp" method="post">
                    <div class="card h-100">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            會員ID: ${article.memId} 
                        </li>
                        <img src="data:image/jpeg;base64,${article.base64Str}" class="card-img-top fixed-height-img">
                        <div class="card-body card-content">
                            <h5 class="card-title">${article.artiTitle}</h5>
                            <div class="card-text hidden">${article.artiText}</div>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-none">文章ID: ${article.articleId}</li>
                            <li class="list-group-item d-none">文章類型: ${article.artiTypeId}</li>
                            <li class="list-group-item d-none likeicon">${article.artiLk}</li>
                        </ul>
                        	<div class="card-footer">
                        	<fmt:formatDate value="${article.artiTime}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
                            <small class="text-muted">發布時間:${formattedDate}</small>
                            	<!-- 隱藏輸入欄位 -->
			                    <input type="hidden" name="articleId" value="${article.articleId}">
			                    <input type="hidden" name="memId" value="${article.memId}">
			                    <input type="hidden" name="artiTitle" value="${article.artiTitle}">
			                    <input type="hidden" name="artiText" value="${article.artiText}">
			                    <input type="hidden" name="artiLk" value="${article.artiLk}">
			                    <input type="hidden" name="artiTypeId" value="${article.artiTypeId}">
			                    <input type="hidden" name="base64Str" value="${article.base64Str}">
			                    <input type="hidden" name="formattedDate" value="${formattedDate}">
                            <button type="button" class="btn btn-danger float-end" onclick="processDelete(${article.articleId})">刪除</button>
                            <button type="submit" class="btn btn-danger float-end me-2">修改</button>
                       		</div>
                    	</div>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
  <c:if test="${list.isEmpty()}">
    <div align="center" class="notthing">
      <h1>暫無文章</h1>
      <h3>趕快來新增你的第一篇文章吧!</h3>
    </div>
  </c:if> 
    <!-- 模態對話框 -->
<div class="modal fade" id="articleDetailsModal" tabindex="-1" aria-labelledby="articleDetailsModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="articleDetailsModalLabel">文章詳細信息</h5>
        <button type="button" class="btn btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="card caca">
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