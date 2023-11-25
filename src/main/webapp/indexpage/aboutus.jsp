<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>關於我們</title>
<jsp:include page="head.jsp" />
<style>
h3.heading, h2.heading {
    font-size: 43px;
}
p.text {
    max-width: 1000px;
    margin: auto;
}
.testimonials {
    background-image: url(${pageContext.request.contextPath}/indexpage/images/indexback.jpg);
    background-size: cover;
}
.test-img img {
    width: 250px;
    height: 250px;
    -webkit-border-radius: 50%;
    -o-border-radius: 50%;
    -ms-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    transition: all 1s ease;
}
</style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<!-- about -->
    <section class="about py-3">
      <div class="container py-lg-5 py-sm-4">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="mt-lg-3 fw-bolder">DepthSpace</h2>
            <p class="mt-4 text">
              "DepthSpace"
              的誕生源自我們對旅行的熱愛。我們深知旅行是一種尋找生命中新鮮事物的方式，也能讓人感受到無限的美好。然而，旅行規劃並不簡單，可能需要花費大量的時間和精力。為了讓更多人輕鬆地享受旅行的樂趣，我們決定創建
              DepthSpace，一個旅遊規劃網站，協助使用者輕鬆規劃旅程，同時提供旅行產品和服務供使用者選擇。我們也歡迎廠商上架旅遊產品，讓更多人可以輕鬆享受旅行帶來的美好體驗。
            </p>
          </div>
        </div>
      </div>
    </section>
    <!-- //about -->
    
    <!-- tramsIntroduction -->
    <section class="testimonials py-5" id="testi">
      <div class="container py-lg-5 py-md-3">
        <h3 class="heading text-capitalize text-center mb-lg-5 mb-4">
          團隊成員介紹
        </h3>
        <div class="row pt-xl-4">
          <div class="col-md-4 test-grid px-lg-4">
            <div class="testi-info text-center">
              <div class="test-img text-center mt-4">
                <img
                  src="${pageContext.request.contextPath}/indexpage/images/about.jpg"
                  class="img-thumbnail img-fluid"
                  alt="user-image"
                  style="max-width: 250px; width: 100%"
                />
              </div>
              <h3 class="mt-md-4 mt-3">余銘修</h3>
            </div>
          </div>
          <div class="col-md-4 test-grid px-lg-4 my-md-0 my-5">
            <div class="testi-info text-center">
              <div class="test-img text-center mt-4">
                <img
                  src="${pageContext.request.contextPath}/indexpage/images/about6.png"
                  class="img-thumbnail img-fluid"
                  alt="user-image"
                  style="max-width: 250px; width: 100%"
                />
              </div>
              <h3 class="mt-md-4 mt-3">李彥廷</h3>
            </div>
          </div>
          <div class="col-md-4 test-grid px-lg-4">
            <div class="testi-info text-center">
              <div class="test-img text-center mt-4">
                <img
                  src="${pageContext.request.contextPath}/indexpage/images/about4.png"
                  class="img-thumbnail img-fluid"
                  alt="user-image"
                  style="max-width: 250px; width: 100%"
                />
              </div>
              <h3 class="mt-md-4 mt-3">謝閔萱</h3>
            </div>
          </div>
        </div>
        <div class="row pt-xl-4">
          <div class="col-md-4 test-grid px-lg-4">
            <div class="testi-info text-center">
              <div class="test-img text-center mt-4">
                <img
                  src="${pageContext.request.contextPath}/indexpage/images/about5.png"
                  class="img-thumbnail img-fluid"
                  alt="user-image"
                  style="max-width: 250px; width: 100%"
                />
              </div>
              <h3 class="mt-md-4 mt-3">陳進旺</h3>
            </div>
          </div>
          <div class="col-md-4 test-grid px-lg-4 my-md-0 my-5">
            <div class="testi-info text-center">
              <div class="test-img text-center mt-4">
                <img
                  src="${pageContext.request.contextPath}/indexpage/images/about.png"                 
                  class="img-thumbnail img-fluid"
                  alt="user-image"
                  style="max-width: 250px; width: 100%"
                />
              </div>
              <h3 class="mt-md-4 mt-3">黃欣怡</h3>
            </div>
          </div>
          <div class="col-md-4 test-grid px-lg-4">
            <div class="testi-info text-center">
              <div class="test-img text-center mt-4">
                <img
                  src="${pageContext.request.contextPath}/indexpage/images/about7.png" 
                  class="img-thumbnail img-fluid"
                  alt="user-image"
                  style="max-width: 250px; width: 100%"
                />
              </div>
              <h3 class="mt-md-4 mt-3">涂昌宏</h3>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- tramsIntroduction -->
    
    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>