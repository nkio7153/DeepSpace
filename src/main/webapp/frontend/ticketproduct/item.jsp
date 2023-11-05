<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>票券詳情</title>
<jsp:include page="/indexpage/head.jsp" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

<style>
  /* 評價的星星樣式(實) */
  .gold-star {
    color: gold;
  }
  /* 評價的星星樣式(虛) */
  .fa-star, .fa-star-half-alt {
    border: none;
  }
</style>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<script>

$(document).ready(function() {
	//JQuery請求圖片ID列表
	const ticketId = ${ticket.ticketId}; //取得票券ID
    $.getJSON("<%=request.getContextPath()%>/ticketallimage?action=getIds&ticketId=" + ticketId, function(serialIds) {
        var carouselInner = $("#carouselExampleIndicators .carousel-inner").empty(); // 清空輪播內容

        serialIds.forEach(function(id, index) {
            // 抓取票券ID每個圖
            var carouselItem = $("<div>").addClass("carousel-item").addClass(index === 0 ? "active" : "");
            var img = $("<img>")
                .attr("src", "<%=request.getContextPath()%>/ticketallimage?action=getImage&imageId="  + id + "&ticketId=" + ticketId)
                .addClass("d-block w-100 rounded")
                .css("height", "350px")
                .css("object-fit", "cover");

            carouselItem.append(img);
            carouselInner.append(carouselItem);
        });
    });

	
	//收藏動態
    $(document).ready(function() {
        $("#favoriteIcon").click(function() {
            if ($(this).hasClass("far")) {
                $(this).removeClass("far").addClass("fas");
            } else {
                $(this).removeClass("fas").addClass("far");
            }
        });
    });
    
   
 	//購物車加入
  $(".btn").on("click",function(){
	let url = "${pageContext.request.contextPath}/tsc/save?ticketId=" + ${ticket.ticketId} + "&memId=1";
    fetch(url)
            .then(function(response){
              return response.text();
            })
            .then(function(data){
              console.log(data);
            })
            .catch(function(error){
              console.log(error);
            })
  })
});
</script>

</head>

<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />


	<div class="container mt-5">

		<!-- 路徑地圖 -->
		<div class="row mb-4">
			<div class="col-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">首頁</a></li>
						<li class="breadcrumb-item"><a href="#">商品</a></li>
						<li class="breadcrumb-item active" aria-current="page">商品名稱</li>
					</ol>
				</nav>
			</div>
		</div>
		<!-- 商品大圖輪播 -->
		<div class="row mb-4">
			<div class="col-12">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<!-- jQuery -->
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
		<!-- 商品基本資訊 -->
		<div class="row mb-4">
			<div class="col-10">
				<h3>${ticket.ticketName}
					<i class="far fa-heart favorite-icon" id="favoriteIcon"></i>
				</h3>
			</div>
			<div class="col-12 mt-3">
				<div class="d-flex justify-content-between align-items-center">
					<div class="rating">
						<p class="mb-0">${averageStars}★★★★★(${totalRatingCount})</p>
					</div>
					<h4>NT$ ${ticket.price}</h4>
				</div>
				<div class="d-flex justify-content-between align-items-center mt-2">
					<select name="quant[1]" class="form-control w-auto">
						<c:forEach var="i" begin="1" end="10">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
					<button class="btn btn-dark">加入購物車</button>
				</div>
			</div>
		</div>

		<!-- 商品描述、位置、評價... -->
		<div class="row mt-5">
			<div class="col-12">
				<h4>票券介紹</h4>
				<p>${ticket.description}</p>
			</div>
		</div>

		<div class="row mt-5">
			<div class="col-12">
				<h4>位置資訊</h4>
				<div id="map" style="width: 100%; height: 400px;"></div>
			</div>
		</div>

<div class="row mt-5">
    <div class="col-12">
        <h4>使用者評價</h4>
        <c:forEach var="reviews" items="${reviews}">
            <div class="review border-top py-3">
                <strong>${review.userName}匿名用戶</strong>
                <div>
                    <!-- 显示实星 -->
                    <c:forEach begin="1" end="${reviews.stars}" var="i">
                        <i class="fas fa-star gold-star"></i>
                    </c:forEach>
                    <!-- 显示半星 -->
                    <c:if test="${reviews.stars % 1 != 0}">
                        <i class="fas fa-star-half-alt gold-star"></i>
                        <!-- 为了计算开始的空星，如果有半星，则加一 -->
                        <c:set var="emptyStarsStart" value="${Math.floor(reviews.stars) + 2}" />
                    </c:if>
                    <!-- 如果没有半星，空星从下一个整数开始 -->
                    <c:if test="${reviews.stars % 1 == 0}">
                        <c:set var="emptyStarsStart" value="${reviews.stars + 1}" />
                    </c:if>
                    <!-- 显示空星 -->
                    <c:forEach begin="${emptyStarsStart}" end="5" var="j">
                        <i class="far fa-star gold-star"></i>
                    </c:forEach>
                </div>
                <p>${reviews.ticketReviews}</p>
            </div>
        </c:forEach>
    </div>
</div>

</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


	<jsp:include page="/indexpage/footer.jsp" />

</body>

</html>
