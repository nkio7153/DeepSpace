<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>票券詳情</title>
<jsp:include page="/indexpage/head.jsp" />


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
		

<style>
/* 收藏愛心樣式 */
#favoriteIcon {
    cursor: pointer;
    font-size: 32px; 
}
/* 評價的星星樣式(實) */
.gold-star {
	color: gold;
}
/* 評價的星星樣式(虛) */
.fa-star, .fa-star-half-alt {
	border: none;
}
/* 加入購物車的閃爍 */
.flash-effect {
    animation: flash-animation 1s;
}

@keyframes flash-animation {
    0%, 100% { opacity: 1; }
    50% { opacity: 0.5; }
}

.table-list {
     width: 80%;
     margin: auto; 
 } 
.table-list .breadcrumb{
	 background-color: transparent;
}
.display-4{
	font-size: 2.5rem;
}
.swal2-title {
    font-size: 1em !important; 
}
.swal2-icon {
    font-size: 0.8em !important;
}
</style>



</head>

<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />
	
<div class="table-list">
		<!-- 路徑地圖 -->
		<div class="row mb-4">
			<div class="col-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb view" style="padding: 10px;">
						<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/" style="color: #9ba1a7;">首頁</a></li>
						<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ticketproduct/list" style="color: #9ba1a7;">票券列表</a></li>
						<li class="breadcrumb-item active" aria-current="page">${ticket.ticketName}</li>
					</ol>
				</nav>
			</div>
		</div>
<div class="row mb-4">
    <!-- 商品大圖輪播 -->
    <div class="col-md-7">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <!-- jQuery -->
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a> 
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

    <!-- 商品基本資訊 -->
    <div class="col-md-5">
        <div class="row">
            <div class="col-10 d-flex justify-content-between align-items-center">
                <h3>${ticket.ticketName}</h3>
                <i class="fa-heart favorite-icon ${isFavorite ? 'fas' : 'far'}" 
                   id="favoriteIcon" style="cursor: pointer;color: #ff160094;" data-ticketId="${ticket.ticketId}"></i>
            </div>
            <div class="col-12 d-flex justify-content-between align-items-center">
                <h6>${ticket.ticketType.typeName}&emsp;|&emsp;${ticket.city.cityName}</h6>
            </div>
        </div>
		<div class="col-12 mt-3">
			<div class="d-flex justify-content-between align-items-center">
				<div class="rating">
				 <c:choose>
                    <c:when test="${averageStars == 0 || totalRatingCount == 0 || averageStars == null}">
                        <p>尚無評價</p>
                    </c:when>
                    <c:otherwise>
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
				 </c:otherwise>
                </c:choose>
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
					<!-- 加入購物車 -->
					<div class="row mb-4">
						<div class="col-12">
							<button class="btn btn-dark btn-lg btn-block">加入購物車</button>
						</div>
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
				<p>${ticket.address}</p>
				<div id="map" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
 <!-- 使用者評價 -->
<div class="row mt-5">
    <div class="col-12">
	 <c:choose>
       <c:when test="${averageStars == 0 || totalRatingCount == 0 || averageStars == null}">
           <p></p>
        </c:when>
        <c:otherwise>
        <h4>使用者評價</h4>
        </c:otherwise>
        </c:choose>
        <c:forEach var="review" items="${reviews}">
            <c:if test="${review.stars > 0}">
                <div class="review border-top py-3">
                    <strong>匿名用戶</strong>
                    <div>
                        <!-- 實星 -->
                        <c:forEach begin="1" end="${review.stars}" var="i">
                            <i class="fas fa-star gold-star"></i>
                        </c:forEach>
                        <!-- 半星 -->
                        <c:if test="${review.stars % 1 != 0}">
                            <i class="fas fa-star-half-alt gold-star"></i>
                            <!-- 有半星就+ -->
                            <c:set var="emptyStarsStart" value="${Math.floor(review.stars) + 2}" />
                        </c:if>
                        <!-- 沒有半星就往下一個數 -->
                        <c:if test="${review.stars % 1 == 0}">
                            <c:set var="emptyStarsStart" value="${review.stars + 1}" />
                        </c:if>
                        <!-- 空星 -->
                        <c:forEach begin="${emptyStarsStart}" end="5" var="j">
                            <i class="far fa-star gold-star"></i>
                        </c:forEach>
                    </div>
                    <p>${review.ticketReviews}</p>
                	</div>
            	</c:if>
        	</c:forEach>
    	</div>
		</div>
	</div>
</div>


<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script async defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAxM8Qw1Zvra1gPDfG5mwO-7FlPtXUoFns&callback=initMap"></script>

<script>

$(document).ready(function() {
//求圖片ID列表
	const ticketId = ${ticket.ticketId}; //取得票券ID
    $.getJSON("<%=request.getContextPath()%>/ticketallimage?action=getIds&ticketId=" + ticketId, function(serialIds) {
        var carouselInner = $("#carouselExampleIndicators .carousel-inner").empty(); // 清空輪播內容

        serialIds.forEach(function(id, index) {
            // 抓取票券ID每個圖
            var carouselItem = $("<div>").addClass("carousel-item").addClass(index === 0 ? "active" : "");
            var img = $("<img>")
                .attr("src", "<%=request.getContextPath()%>/ticketallimage?action=getImage&imageId="  + id + "&ticketId=" + ticketId)
                .addClass("d-block w-100 rounded")
                .css("height", "300px")
                .css("object-fit", "cover");

            carouselItem.append(img);
            carouselInner.append(carouselItem);
        });
    });
});
//愛心收藏
$(document).ready(function() {
    $(".favorite-icon").click(function() {
        var ticketId;
		var ticketId = $(this).data('ticketid');
        var requestData = { "ticketId": ticketId};
        var iconElement = $(this);
        // 檢查 memId 是否為 null
        var memId = ${memId != null ? memId : 'null'};
        if(memId == null) {
        	Swal.fire({
        		  position: "center",
        		  icon: "error",
        		  title: "請先登入！",
        		  showConfirmButton: false,
        		  timer: 1500
        		});
            return; 
        }
        console.log("Request Data:", requestData);
        
        $.get("${pageContext.request.contextPath}/ticketcollection/toggleFavorite", requestData, function(response) {
            if (response.isFavorite) {
                iconElement.removeClass("far").addClass("fas");
            	Swal.fire({
            		  position: "center",
//             		  icon: "success",
            		  title: "成功加入",
            		  showConfirmButton: false,
            		  timer: 1500
            		});
            } else {
                iconElement.removeClass("fas").addClass("far");
            	Swal.fire({
          		  position: "center",
//           		  icon: "success",
          		  title: "成功移除",
          		  showConfirmButton: false,
          		  timer: 1500
          		});
            }
        }).fail(function(xhr, status, error) {
            if(memId == null) { 
                alert("請先登入！");
            } else {
                alert("發生錯誤： " + error);
            }
        });
    });
});
  
//購物車加入
$(".btn").on("click", function(e) {
	let check = $("[name='check']").text();
	if(check === "登入/註冊"){
		e.preventDefault(); // 防止預設事件，例如表單提交或連結跳轉

		// 使用 SweetAlert2 顯示彈窗
		Swal.fire({
			icon: "error",
			text: "尚未登入！",
			footer: '<a href="${pageContext.request.contextPath}/member/login.jsp">點此登入</a>'
		});
	}else{
	let cartNum=$("#cartNum");
    let button = $(this);
	let quantity=$("#ticketQuantity").val();
	$("#ticketQuantity").val(1);
	console.log(quantity);
    let url = "${pageContext.request.contextPath}/tsc/save?ticketId=" + ${ticket.ticketId} + "&quantity="+quantity;
    fetch(url)
        .then(function(response) {
            return response.text();
        })
        .then(function(data) {
            console.log(data);
            cartNum.text(data);
            button.addClass('flash-effect');
            
            if (cartNum.text() === "0" || cartNum.text() === "") {
                cartNum.hide();
               } else {
                cartNum.show();
               }
            
            // 1 秒後移除閃爍效果
            setTimeout(() => {
                button.removeClass('flash-effect');
            }, 1000);
            
        	Swal.fire({
      		  position: "center",
      		  icon: "success",
      		  title: "已加入購物車",
      		  showConfirmButton: false,
      		  timer: 1500
      		});
        })
        .catch(function(error) {
            console.log(error);
        });
	}
});

//地圖
function initMap() {
    var latitude = ${ticket.latitude}; // 票券的緯度
    var longitude = ${ticket.longitude}; // 票券的經度

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        center: {lat: latitude, lng: longitude}
    });

    var marker = new google.maps.Marker({
        position: {lat: latitude, lng: longitude},
        map: map
    });
}

</script>


	<jsp:include page="/indexpage/footer.jsp" />
</body>

</html>
