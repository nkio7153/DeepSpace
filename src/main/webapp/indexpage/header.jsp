<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<header>
    <div class="container" style="padding-right: 0;">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid" style="padding: 20px 0;">
                <a class="navbar-brand" href="index.html">
                    <img src="${pageContext.request.contextPath}/indexpage/images/icon.png" alt="" width="30"
                         height="24"
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
                        </li>
                        <li class="nav-item">
                            <a class="active" href="index.html">首頁</a>
                        </li>
                        <li class="nav-item">
                            <a class="" href="play.html">票券</a>
                        </li>
                        <li class="nav-item">
                            <a class="" href="#">論壇文章</a>
                        </li>
                        <li class="nav-item">
                            <a class="" href="#">餐廳</a>
                        </li>
                        <li class="nav-item">
                            <a class="booking" href="#">登入/註冊</a>
                        <li class="">
                            <a href="${pageContext.request.contextPath}/tsc/index"><img
                                    src="${pageContext.request.contextPath}/indexpage/images/shoppingcar.svg"
                                    alt=""
                                    style="width: 2em"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
<section class="banner-inner" id="home">
    <div class="banner-inner_overlay">
        <img src="${pageContext.request.contextPath}/indexpage/images/slowen.jpg"
             class="banner-inner_overlay_pic w-100">
    </div>
</section>
