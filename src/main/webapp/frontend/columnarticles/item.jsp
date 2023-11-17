<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>專欄文章詳情</title>
<jsp:include page="/indexpage/head.jsp" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">


</head>

<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div class="container mt-5">

		<!-- 大圖 -->
		<div class="row mb-4">
			<div class="col-12">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
					<img src="<%=request.getContextPath()%>/columnmainimage?artiId=${columnArticles.artiId}"
					alt="Main Image"></div>
				</div>
			</div>
		</div>
		<!-- 標題及內文 -->
		<div class="row mb-4">
			<div class="col-10">
				<h3>${columnArticles.artiTitle}</h3>
			</div>
			<div class="col-12 mt-3">
				<div class="d-flex justify-content-between align-items-center">
					<div class="rating">
						<p class="mb-0">${columnArticles.colType.colTypeName}</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col-12">
				<p>${columnArticles.artiContent}</p>
			</div>
		</div>
		<div>
		</div>
		 <c:choose>
         <c:when test="${recommendedArticles == null}"><p></p>
         </c:when>
         <c:otherwise>
		<h5>你可能還喜歡</h5>
        <div class="row">
            <c:forEach  items="${recommendedArticles}" var="article" begin="0" end="4">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="<%=request.getContextPath()%>/columnmainimage?artiId=${article.artiId}" class="card-img-top" alt="Article Image">
                        <div class="article">
                            <h6 class="card-title"><c:choose>
								<c:when test="${fn:length(article.artiTitle) > 10}">
								${fn:substring(article.artiTitle,0,10)}...
								</c:when>
								<c:otherwise>
								${article.artiTitle}
								</c:otherwise>
							 </c:choose></h5>
                            <p class="card-text"><c:choose>
								<c:when test="${fn:length(article.artiContent) > 30}">
								${fn:substring(article.artiContent,0,30)}...
								</c:when>
								<c:otherwise>
								${article.artiContent}
								</c:otherwise>
							 </c:choose></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        				 </c:otherwise>
                </c:choose>
    	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>

	<jsp:include page="/indexpage/footer.jsp" />

</body>

</html>
