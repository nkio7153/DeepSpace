<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>DepthSpace</title>
<jsp:include page="head.jsp" />
<style type="text/css">
.test {
  display: flex;
  flex-wrap: wrap;
  align-items: end;
}

.counter {
  display: flex;
  justify-content: center;
  flex-direction: column;
}

.abt-images{
	max-width: 60%;
    height: auto;
}

@media (max-width: 576px) {
 .banner-info h3 {
    font-size: 1rem;
  }

 .banner-info h4 {
    font-size: 0.9rem;
  }
  
  a.btn {
    border: 2px solid #fff;
    border-radius: 0px;
    padding: 3px 6px;
    color: #fff;
    font-size: 10px;
    letter-spacing: 2px;
    text-transform: capitalize;
    transition: all 0.5s ease;
  }
}

.mark{
	background-color: initial;	
}

.marks{
	max-width: 80% ;
}

.markz{
	max-width: 60% ;
}

.destinations-grids:hover div.destinations-info {
    bottom: 0;
}

@media (max-width: 991px){
	.destinations-info {
	    height: 9em;
	}
}

.destinations-info {
    position: absolute;
    bottom: -227px;
    margin: 0;
    background: rgba(0, 0, 0, 0.8);
    padding: 30px 0px 10px;
    transition: 0.5s all;
    -webkit-transition: 0.5s all;
    -moz-transition: 0.5s all;
    -o-transition: 0.5s all;
    -ms-transition: 0.5s all;
    width: 100%;
    height: 11em;
    text-align: center;
}

.caption {
    padding: 0px;
}

@media (max-width: 991px)
.caption a {
    font-size: 14px;
    padding: 10px 20px;
}

.caption a {
    font-size: 15px;
    background: none;
    border: 1px solid #ccc;
    padding: 12px 20px;
    letter-spacing: 1px;
    color: #ccc;
    margin-top: 20px;
    display: inline-block;
}

.destinations-grids .image-position {
  position: relative;
  overflow: hidden; /* 這會確保超出父元素部分的子元素被隱藏 */
}

.card-img {
  width: 100%; /* 圖片寬度設為 100% */
  height: auto; /* 圖片高度自動調整 */
  display: block; /* 避免圖片下方有額外的空間 */
}

.destinations-grids {
  min-height: 350px;
}

.image-position {
  position: relative;
  height: 100%;
  width: 100%;
  overflow: hidden;
}

.card-img {
  height: 100%; /* 設定照片高度與容器相同 */
  width: auto; /* 若需要保持圖片比例，則將此設為 auto，否則可以是 100% */
  object-fit: cover; /* 這樣圖片會覆蓋整個容器，但可能會被裁切 */
}

h3.heading, h2.heading {
    font-size: 43px;
}

p.text {
    max-width: 700px;
    margin: auto;
}

h4.destination {
    text-transform: uppercase;
    font-size: 20px;
    letter-spacing: 3px;
    font-weight: bold;
}

.caption h4{
	color: white;
}

.gg {
    margin-top: 3.5rem !important;
}
</style>
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
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/indexpage/images/picicon.jpg" class="picfak w-100">
                <div class="carousel-caption">
                    <div class="container">
                        <div class="banner-info">
                            <h3 class="text-wh">
                                行程規劃功能 <br />讓您輕鬆打造完美旅程
                            </h3>
                            <h4 class="text-wh">
                                給想要輕鬆安排行程，又不知道從何下手的你
                            </h4>
                            <div class="buttons mt-4">
                                <a href="${pageContext.request.contextPath}/tr/tourList" class="btn" onclick="checkSession(event)">現在開始</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/indexpage/images/picicon1.jpg" class="picfak w-100">
                <div class="carousel-caption">
                    <div class="container">
                        <div class="banner-info">
                            <h3 class="text-wh">
                                餐廳預約功能 <br />讓您輕鬆預定心儀的餐廳
                            </h3>
                            <h4 class="text-wh">
                                給想要預約餐廳，又不知道有哪些餐廳可以選擇的你
                            </h4>
                            <div class="buttons mt-4">
                                <a href="${pageContext.request.contextPath}/Rest/getRests" class="btn">現在開始</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/indexpage/images/picicon3.jpg" class="picfak w-100">
                <div class="carousel-caption">
                    <div class="container">
                        <div class="banner-info">
                            <h3 class="text-wh">
                                票券購買功能 <br />讓您輕鬆購買心儀的票券
                            </h3>
                            <h4 class="text-wh">
                                給想要購買票券，又不知道從哪入手的你
                            </h4>
                            <div class="buttons mt-4">
                                <a href="${pageContext.request.contextPath}/ticketproduct/list" class="btn">現在開始</a>
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
                        在這個追求深度旅遊體驗的時代，我們的平台提供了個性化的台灣自由行計畫工具，您可以不僅跟隨旅遊達人的腳步，也能依照自己的喜好訂製專屬於自己的行程，確保每一次的旅行都是難忘的經歷。
                    </p>
                    <p class="mt-3">
                        除此之外，我們還提供了豐富的票券選擇，從風景名勝到當地活動，從震撼的表演到文化體驗，所有的票券都可以透過我們的網站輕鬆購得。這不僅讓您的旅程更加繽紛多彩，也讓您享受到真正貼心且靈活的旅遊規劃。
                    </p>
                </div>
                <div class="col-lg-6 about-right text-lg-right mt-lg-0 mt-5">
                    <img src="${pageContext.request.contextPath}/indexpage/images/gotrip.jpg" alt="" class="img-fluid abt-image" />
                </div>
            </div>
            <div class="row mt-5 text-center test">
                <div class="col-lg-3 col-6">
                    <div class="counter">
                        <span class="fa">
                            <img src="${pageContext.request.contextPath}/indexpage/images/people.svg" class="img-fluid abt-images">
                        </span>
                        <div class="timer time1 count-title count-number">0</div>
                        <p class="count-text text-uppercase">會員人數</p>
                    </div>
                </div>
                <div class="col-lg-3 col-6">
                    <div class="counter">
                        <span class="fa">
                            <img src="${pageContext.request.contextPath}/indexpage/images/restaurant.svg" class="img-fluid abt-images">
                        </span>
                        <div class="timer time2 count-title count-number">0</div>
                        <p class="count-text text-uppercase">餐廳</p>
                    </div>
                </div>
                <div class="col-lg-3 col-6 mt-lg-0 mt-5">
                    <div class="counter">
                        <span class="fa">
                            <img src="${pageContext.request.contextPath}/indexpage/images/ticket.svg" class="img-fluid abt-images">
                        </span>
                        <div class="timer time3 count-title count-number">0</div>
                        <p class="count-text text-uppercase">票券</p>
                    </div>
                </div>
                <div class="col-lg-3 col-6 mt-lg-0 mt-5">
                    <div class="counter">
                        <span class="fa">
                            <img src="${pageContext.request.contextPath}/indexpage/images/article.svg" class="img-fluid abt-images">
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
                            <img src="${pageContext.request.contextPath}/indexpage/images/iconplace.png" class="mark markz">
                        </div>
                        <h4>選擇目的地</h4>
                        <p class="mt-3">打造專屬旅程的第一步</p>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mt-sm-0 mt-5">
                    <div class="grid-info">
                        <div class="icon">
                            <img src="${pageContext.request.contextPath}/indexpage/images/icon3.png" class="mark marks">
                        </div>
                        <h4>選擇旅遊天數</h4>
                        <p class="mt-3">打造專屬旅程的第二步</p>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mt-lg-0 mt-5">
                    <div class="grid-info">
                        <div class="icon">
                            <img src="${pageContext.request.contextPath}/indexpage/images/icon4.png" class="mark">
                        </div>
                        <h4>享受旅程</h4>
                        <p class="mt-3">好好享受我們為您安排的美好旅程</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- //how to book -->
    
        <!-- destinations -->
    <section class="destinations py-5" id="destinations">
      <div class="container py-xl-5 py-lg-3">
        <h3 class="heading text-capitalize text-center">熱門票券</h3>
        <p class="text mt-2 mb-2 text-center">
          無論您是喜愛自然風光、文化古蹟或是美食之旅，我們都有適合您的票券選擇。
        </p>
        <div class="row inner-sec-w3layouts-w3pvt-lauinfo">
          <div class="col-md-3 col-sm-12 destinations-grids text-center mt-4 gg">
            <h4 class="destination mb-3">Xpark</h4>
            <div class="image-position position-relative">
              <img src="${pageContext.request.contextPath}/indexpage/images/xparkpic.jpg" class="card-img" alt="" />
              <div class="destinations-info">
                <div class="caption mb-lg-3">
                  <h4>Xpark 都會型水生公園門票</h4>
                  <a href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=324003">立即購買</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-12 destinations-grids text-center mt-4 gg">
            <h4 class="destination mb-3">張美阿嬤農場</h4>
            <div class="image-position position-relative">
              <img src="${pageContext.request.contextPath}/indexpage/images/farm.jpg" class="card-img" alt="" />
              <div class="destinations-info">
                <div class="caption mb-lg-3">
                  <h4>張美阿嬤農場門票</h4>
                  <a href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=324002">立即購買</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-12 destinations-grids text-center mt-4 gg">
            <h4 class="destination mb-3">劍湖山</h4>
            <div class="image-position position-relative">
              <img src="${pageContext.request.contextPath}/indexpage/images/park.jpg" class="card-img" alt="" />
              <div class="destinations-info">
                <div class="caption mb-lg-3">
                  <h4>劍湖山世界主題樂園門票</h4>
                  <a href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=324001">立即購買</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-12 destinations-grids text-center mt-4 gg">
            <h4 class="destination mb-3">台北成人展</h4>
            <div class="image-position position-relative">
              <img src="${pageContext.request.contextPath}/indexpage/images/girl.jpg" class="card-img" alt="" />
              <div class="destinations-info">
                <div class="caption mb-lg-3">
                  <h4>台北成人展門票</h4>
                  <a href="javascript:void(0);" onclick="showMessage()">立即購買</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- destinations -->
<jsp:include page="footer.jsp" />
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
        
        function showMessage() {
        	 Swal.fire({ 
        		    title: '即將上架，敬請期待', 
        		    text: '我們的票券很快就會推出！',
        		    icon: 'info', 
        		    confirmButtonText: '確定' 
        		  }).then((result) => { 
        		    if (result.isConfirmed) { 
        		    	location.reload();
        		    }
        		  });
        	}
    </script>
</body>
</html>