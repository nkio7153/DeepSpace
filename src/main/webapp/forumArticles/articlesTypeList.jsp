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
</style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
 	<div id="list" class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
	        <div class="btn-group" role="group"> <!-- 添加這個 div 來包裹按鈕 -->
	            <button type="button" class="btn btn-primary me-2" onclick="window.location.href='<%=request.getContextPath()%>/forumArticles/list.jsp'">瀏覽所有文章</button>
	            <button type="button" class="btn btn-primary" onclick="window.location.href='<%=request.getContextPath()%>/forumArticles/add.jsp'">新增文章</button>
	        </div>
    	</div>
        <div id="articlesRow" class="row">
            <c:forEach var="article" items="${list}">
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            會員ID: ${article.memId} 
                            <span class="likes float-right">
                                <i class="fas fa-thumbs-up"></i> ${article.artiLk}
                            </span>
                        </li>
                        <img src="data:image/jpeg;base64,${article.base64Str}" class="card-img-top fixed-height-img">
                        <div class="card-body card-content">
                            <h5 class="card-title">${article.artiTitle}</h5>
                            <p class="card-text">${article.artiText}</p>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-none">訊息ID: ${article.msgId}</li>
                            <li class="list-group-item d-none">文章ID: ${article.articleId}</li>
                            <li class="list-group-item d-none">文章類型: ${article.artiTypeId}</li>
                        </ul>
                        	<div class="card-footer">
                        	<fmt:formatDate value="${article.artiTime}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
                            <small class="text-muted">發布時間:${formattedDate}</small>
                       		</div>
                    	</div>                   
                </div>
            </c:forEach>
        </div>
    </div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>