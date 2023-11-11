<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
 <jsp:include page="head.jsp" />
</head>
<body>
<jsp:include page="header.jsp" />
  <div id="carouselExampleCaptions" class="carousel slide" data-bs-touch="false">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        </div>
        <style>
        </style>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="./images/picicon.jpg" class="picfak w-100">
                <div class="carousel-caption">
                    <div class="container">
                        <div class="banner-info">
                            <h3 class="text-wh">
                                AI行程規劃功能 <br />讓您輕鬆打造完美旅程
                            </h3>
                            <h4 class="text-wh">
                                給想要輕鬆安排行程，又不知道從何下手的你
                            </h4>
                            <div class="buttons mt-4">
                                <a href="" class="btn">現在開始</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="./images/picicon1.jpg" class="picfak w-100">
                <div class="carousel-caption d-none d-md-block">
                    <div class="container">
                        <div class="banner-info">
                            <h3 class="text-wh">
                                AI行程規劃功能 <br />讓您輕鬆打造完美旅程
                            </h3>
                            <h4 class="text-wh">
                                給想要輕鬆安排行程，又不知道從何下手的你
                            </h4>
                            <div class="buttons mt-4">
                                <a href="" class="btn">現在開始</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="./images/picicon3.jpg" class="picfak w-100">
                <div class="carousel-caption d-none d-md-block">
                    <div class="container">
                        <div class="banner-info">
                            <h3 class="text-wh">
                                AI行程規劃功能 <br />讓您輕鬆打造完美旅程
                            </h3>
                            <h4 class="text-wh">
                                給想要輕鬆安排行程，又不知道從何下手的你
                            </h4>
                            <div class="buttons mt-4">
                                <a href="" class="btn">現在開始</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>


    <section class="about py-5">
        <div class="container py-lg-5 py-sm-4">
            <div class="row">
                <div class="col-lg-6 about-left">
                    <h3 class="mt-lg-3">
                        <strong>讓旅行更輕鬆，讓生活更美好</strong>
                    </h3>
                    <p class="mt-4">
                        在現今生活水平提升的時代，人們渴望深度的旅行體驗。在台灣，個人自由行已逐漸超越傳統團體旅遊。因此，我們推出一個彈性旅遊規劃平台，不僅可以追隨旅遊達人，還能自訂行程，讓每次旅行都是一段獨特的回憶。
                    </p>
                    <p class="mt-3">
                        如果您需要更加輕鬆的選擇，可以考慮購買我們的套裝行程。我們的套裝行程已經為您精心設計，可以讓您輕鬆享受美好的旅行體驗，而無需擔心任何瑣碎的細節。另外，我們也提供各種各樣的票券，包括景點、活動、表演等等。只需透過我們的網站，便可以輕鬆購買您感興趣的票券，讓您的旅行更加豐富多彩。
                    </p>
                </div>
                <div class="col-lg-6 about-right text-lg-right mt-lg-0 mt-5">
                    <img src="./images/124.png" alt="" class="img-fluid abt-image" />
                </div>
            </div>
            <div class="row mt-5 text-center">
                <div class="col-lg-3 col-6">
                    <div class="counter">
                        <span class="fa">
                            <img src="./images/people.svg" class="img-fluid abt-image">
                        </span>
                        <div class="timer time1 count-title count-number">0</div>
                        <p class="count-text text-uppercase">會員人數</p>
                    </div>
                </div>
                <div class="col-lg-3 col-6">
                    <div class="counter">
                        <span class="fa">
                            <img src="./images/restaurant.svg" class="img-fluid abt-image">
                        </span>
                        <div class="timer time2 count-title count-number">0</div>
                        <p class="count-text text-uppercase">餐廳</p>
                    </div>
                </div>
                <div class="col-lg-3 col-6 mt-lg-0 mt-5">
                    <div class="counter">
                        <span class="fa">
                            <img src="./images/ticket.svg" class="img-fluid abt-image">
                        </span>
                        <div class="timer time3 count-title count-number">0</div>
                        <p class="count-text text-uppercase">票券</p>
                    </div>
                </div>
                <div class="col-lg-3 col-6 mt-lg-0 mt-5">
                    <div class="counter">
                        <span class="fa">
                            <img src="./images/article.svg" class="img-fluid abt-image">
                        </span>
                        <div class="timer time4 count-title count-number">0</div>
                        <p class="count-text text-uppercase">旅遊文章</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- //about -->

    <!-- how to book -->
    <section class="book py-5">
        <div class="container py-lg-5 py-sm-3">
            <h2 class="heading text-capitalize text-center">如何計畫您的旅程</h2>
            <div class="row mt-5 text-center">
                <div class="col-lg-4 col-sm-6">
                    <div class="grid-info">
                        <div class="icon">
                            <img src="./images/icon2.png" class="mark">
                        </div>
                        <h4>選擇目的地</h4>
                        <p class="mt-3">打造專屬旅程的第一步</p>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mt-sm-0 mt-5">
                    <div class="grid-info">
                        <div class="icon">
                            <img src="./images/icon3.png" class="mark">
                        </div>
                        <h4>選擇旅遊天數</h4>
                        <p class="mt-3">打造專屬旅程的第二步</p>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mt-lg-0 mt-5">
                    <div class="grid-info">
                        <div class="icon">
                            <img src="./images/icon4.png" class="mark">
                        </div>
                        <h4>享受旅程</h4>
                        <p class="mt-3">好好享受我們為您安排的美好旅程</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- //how to book -->
<jsp:include page="footer.jsp" />
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--載入 jQuery-->
    <script src="js/jquery-3.6.4.min.js"></script>
    <script>
        window.addEventListener("load", function () {
            // 增加動畫
            $(".time1").animate(
                // 動畫終點
                { num: 9453 },
                {
                    // 持續時間
                    duration: 8000,
                    step: function (now) {
                        $(this).text(Math.floor(now)); // 更新數字
                    },
                }
            );
            // 增加動畫
            $(".time2").animate(
                // 動畫終點
                { num: 3466 },
                {
                    // 持續時間
                    duration: 6000,
                    step: function (now) {
                        $(this).text(Math.floor(now)); // 更新數字
                    },
                }
            );
            // 增加動畫
            $(".time3").animate(
                // 動畫終點
                { num: 1688 },
                {
                    // 持續時間
                    duration: 4000,
                    step: function (now) {
                        $(this).text(Math.floor(now)); // 更新數字
                    },
                }
            );
            // 增加動畫
            $(".time4").animate(
                // 動畫終點
                { num: 745 },
                {
                    // 持續時間
                    duration: 2000,
                    step: function (now) {
                        $(this).text(Math.floor(now)); // 更新數字
                    },
                }
            );
        });
    </script>
</body>
</html>