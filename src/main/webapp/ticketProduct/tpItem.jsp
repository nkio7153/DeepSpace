<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>票券詳情</title>
    <jsp:include page="../indexpage/head.jsp" /> 
        
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>

<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />

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
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="<%=request.getContextPath()%>/ticketimage?ticketId=${ticket.ticketId}" class="d-block w-100 rounded" alt="..." style="height: 350px; object-fit: cover;">
                    </div>
                    <!-- 更多圖片... -->
                </div>
            </div>
        </div>
    </div>

    <!-- 商品基本資訊 -->
    <div class="row mb-4">
        <div class="col-10">
            <h3>${ticket.ticketName} <i class="far fa-heart favorite-icon" id="favoriteIcon"></i></h3>
        </div>
        <div class="col-12 mt-3">
            <div class="d-flex justify-content-between align-items-center">
                <div class="rating">
                    <p class="mb-0">4.4 ★★★★☆ (520) < 1000次購買</p>
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
            <!-- 評價列表 -->
            <div class="review border-top py-3">
                <strong>會員名稱</strong>
                <div>★★★★☆</div>
                <p>這裡是評價內容...</p>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $("#favoriteIcon").click(function() {
            if ($(this).hasClass("far")) {
                $(this).removeClass("far").addClass("fas");
            } else {
                $(this).removeClass("fas").addClass("far");
            }
        });
    });
</script>

<jsp:include page="../indexpage/footer.jsp" /> 

</body>

</html>
