<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/indexpage/css/bootstrap.min.css" rel="stylesheet">
    <!-- bookstrap css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/indexpage/css/style.css">
    <title>深度漫遊</title>
    <!-- 載入icon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/indexpage/images/icon.png" />
    <link rel="bookmark" href="${pageContext.request.contextPath}/indexpage/images/icon.png" />
    <style>

    </style>
</head>

<body>
    <header>
        <div class="container" style="padding-right: 0;">
            <nav class="navbar navbar-expand-lg">
                <div class="container-fluid" style="padding: 20px 0;">
                    <a class="navbar-brand" href="indexDemo.jsp">
                        <img src="${pageContext.request.contextPath}/indexpage/images/icon.png" alt="" width="30" height="24"
                            class="d-inline-block align-text-top">
                        深度漫遊
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav menu ml-auto">
                            <li class="nav-item">
                                <a class="active" href="${pageContext.request.contextPath}/Rest/indexDemo.jsp">首頁</a>
                            </li>
                            <li class="nav-item">
                                <a class="" href="play.html">票券</a>
                            </li>
                            <li class="nav-item">
                                <a class="" href="#">論壇文章</a>
                            </li>
                            <li class="nav-item">
                                <a class="" href="${pageContext.request.contextPath}/Rest/Rest.do?action=getAll">餐廳</a>
                            </li>
                            <li class="nav-item">
                                <a class="booking" href="#">登入/註冊</a>
                            <li class="">
                                <a href="#"><img src="${pageContext.request.contextPath}/indexpage/images/shoppingcar.svg" alt=""
                                        style="width: 2em" /></a>                    
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <section class="banner-inner" id="home">
        <div class="banner-inner_overlay">
            <img src="${pageContext.request.contextPath}/indexpage/images/123.jpg" class="banner-inner_overlay_pic w-100">
        </div>
    </section>


    <!-- account/list.jsp 改成你的包跟jsp檔 -->
<%--     <jsp:include page="../faq/listAllFaq.jsp" /> --%>
    <!-- account/list.jsp 改成你的包跟jsp檔 -->


    <!--footer -->
    <footer>
        <section class="footer footer_w3layouts_section_1its py-5">
            <div class="container py-lg-4 py-3">
                <div class="row footer-top">
                    <div class="col-lg-3 col-sm-6 footer-grid_section_1its_w3">
                        <div class="footer-title">
                            <h3>地點資訊</h3>
                        </div>
                        <div class="row">
                            <ul class="col-12 links">
                                <li>
                                    <a href="https://www.google.com/maps?q=桃園市中壢區復興路46號9樓"
                                        target="_blank">地址：桃園市中壢區復興路46號9樓</a>
                                </li>
                                <li><a href="tel:02-1234-5678">電話：02-1234-5678</a></li>
                                <li>
                                    <a href="mailto:contact@example.com">信箱：triplirht@gmail.com</a>
                                </li>
                                <li><a href="tel:02-1234-5678">傳真：02-1234-5678</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 footer-grid_section mt-sm-0 mt-4">
                        <div class="footer-title">
                            <h3>認識深度漫遊</h3>
                        </div>
                        <div class="row">
                            <ul class="col-10 links">
                                <li><a href="" class="scroll">關於我們</a></li>
                                <li>
                                    <a href="" class="scroll">使用者條款 </a>
                                </li>
                                <li>
                                    <a href="" class="scroll">隱私權保護政策</a>
                                </li>
                                <li><a href="" class="scroll"> 常見問題與幫助</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 mt-lg-0 mt-4 footer-grid_section_1its_w3">
                        <div class="footer-title">
                            <h3>小組成員</h3>
                        </div>
                        <div class="row">
                            <ul class="col-6 links">
                                <li>
                                    <a href="" class="scroll">李彥廷</a>
                                </li>
                                <li>
                                    <a href="" class="scroll">謝閔萱</a>
                                </li>
                                <li>
                                    <a href="" class="scroll">余銘修</a>
                                </li>
                                <li>
                                    <a href="" class="scroll">陳進旺</a>
                                </li>
                                <li>
                                    <a href="" class="scroll">涂昌宏</a>
                                </li>
                                <li>
                                    <a href="" class="scroll">黃欣怡</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 mt-lg-0 mt-4 footer-grid_section_1its_w3">
                        <div class="footer-title">
                            <h3>聯絡我們</h3>
                        </div>
                        <ul class="social_section_1info">
                            <li>
                                <a href="https://www.facebook.com/TibaMe"><img src="${pageContext.request.contextPath}/indexpage/images/facebook.svg"
                                        alt="facebook" /></a>
                            </li>
                            <li>
                                <a href="https://www.instagram.com/tibame_wiedu/"><img src="${pageContext.request.contextPath}/indexpage/images/instagram.svg"
                                        alt="instagram" /></a>
                            </li>
                            <li>
                                <a href="https://twitter.com/"><img src="${pageContext.request.contextPath}/indexpage/images/twitter.svg" alt="twitter" /></a>
                            </li>
                            <li class="youtube">
                                <a href="https://www.youtube.com/channel/UClhecf7eOGHwbKW5e7l_pTA"><img
                                        src="${pageContext.request.contextPath}/indexpage/images/youtube.svg" alt="youtube" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    </footer>
    <!-- //footer -->

    <!-- copyright -->
    <div class="copyright py-3 text-center">
        <p>COPYRIGHT © 2023 深度漫遊 All rights reserved.</p>
    </div>
    <!-- //copyright -->


    <script src="${pageContext.request.contextPath}/indexpage/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/indexpage/js/bootstrap.min.js"></script>
    <!--載入 jQuery-->
    <script src="${pageContext.request.contextPath}/indexpage/js/jquery-3.6.4.min.js"></script>
</body>

</html>