<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>票券詳情</title>
<jsp:include page="/indexpage/head.jsp" />

<!-- Leaflet的CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
<!-- Leaflet的JS -->
<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
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

//地圖
$(document).ready(function() {
    // 初始化
    function initMap(latitude, longitude) {
        var map = L.map('map').setView([latitude, longitude], 13);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
        L.marker([latitude, longitude]).addTo(map);
    }

    <%if (request.getAttribute("ticket") != null) {%>
        var ticketLatitude = ${ticket.latitude};
        var ticketLongitude = ${ticket.longitude};

        if(!isNaN(ticketLatitude) && !isNaN(ticketLongitude)) {
            initMap(ticketLatitude, ticketLongitude);
        } else {
            console.error('Invalid latitude or longitude for ticketVO.');
        }
    <%} else {%>
        console.error('TicketVO object not found in request.');
    <%}%>
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
			<div class="col-12 d-flex justify-content-between align-items-center">
				<h3>${ticket.ticketName}</h3>
				<i class="far fa-heart favorite-icon" id="favoriteIcon"
					style="cursor: pointer;"></i>
			</div>
		</div>
		<div class="col-12 mt-3">
			<div class="d-flex justify-content-between align-items-center">
				<div class="rating">
					<p class="mb-0">${formattedAverageStars}
						<!-- 實星 -->
						<c:forEach begin="1" end="${averageStars}" var="i">
							<i class="fas fa-star gold-star"></i>
						</c:forEach>
						<!-- 半星 -->
						<c:if test="${averageStars % 1 != 0}">
							<i class="fas fa-star-half-alt gold-star"></i>
							<!-- 有半星就+ -->
							<c:set var="emptyStarsStart"
								value="${Math.floor(averageStars) + 2}" />
						</c:if>
						<!-- 沒有半星就往下一個數 -->
						<c:if test="${averageStars % 1 == 0}">
							<c:set var="emptyStarsStart" value="${averageStars + 1}" />
						</c:if>
						<!-- 空星 -->
						<c:forEach begin="${emptyStarsStart}" end="5" var="j">
							<i class="far fa-star gold-star"></i>
						</c:forEach>
						(${totalRatingCount})
					</p>
					<div class="row mb-4">
						<div class="col-12">
							<h2 class="display-4">NT$ ${ticket.price}</h2>
						</div>
					</div>
					<!-- Quantity Selector -->
					<div class="row mb-4">
						<div class="col-12">
							<label for="ticketQuantity" class="mr-2">數量</label> <select
								id="ticketQuantity" name="quant[1]" class="custom-select w-auto">
								<c:forEach var="i" begin="1" end="10">
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- Add to Cart Button -->
					<div class="row mb-4">
						<div class="col-12">
							<button class="btn btn-dark btn-lg btn-block">加入購物車</button>
						</div>
					</div>
				</div>
			  </div>
			</div>
			<!-- 商品描述、位置、評價 -->
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
								<!-- 實星 -->
								<c:forEach begin="1" end="${reviews.stars}" var="i">
									<i class="fas fa-star gold-star"></i>
								</c:forEach>
								<!-- 半星 -->
								<c:if test="${reviews.stars % 1 != 0}">
									<i class="fas fa-star-half-alt gold-star"></i>
									<!-- 有半星就+ -->
									<c:set var="emptyStarsStart"
										value="${Math.floor(reviews.stars) + 2}" />
								</c:if>
								<!-- 沒有半星就往下一個數 -->
								<c:if test="${reviews.stars % 1 == 0}">
									<c:set var="emptyStarsStart" value="${reviews.stars + 1}" />
								</c:if>
								<!-- 空星 -->
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
		<script>
    $(document).ready(function() {
 
        // 初始化地图
        function initMap(latitude, longitude) {
            
           
var map = L.map('map').setView([latitude, longitude], 13);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
            var marker = L.marker([latitude, longitude]).addTo(map);
        }

        // 假设ticketVO对象通过服务器端代码传递到JSP，并存在于页面中
        <%if (request.getAttribute("ticket") != null) {%>
            var ticketVO = {
                latitude: ${ticket.latitude},
                longitude: ${ticket.longitude}
            };
            initMap(ticket.latitude, ticket.longitude);
        <%} else {%>
            console.error('ticketVO对象未在请求中找到！');
        <%}%>
    });
</script>

		<jsp:include page="/indexpage/footer.jsp" />
</body>

</html>
