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
             "DepthSpace"的構想源於我們對旅遊的深厚熱愛。我們理解旅遊不僅是尋找生命中新奇體驗的途徑，同時也是一個發現世界美好的機會。
             通過旅行，我們能夠窺探不同文化，體驗多元的生活方式，從而豐富自己的生命經歷。
             然而，我們也意識到旅遊規劃的過程可能既繁瑣又耗時，從選擇目的地到規劃行程，每一步都需要細心的考量與規劃。
			 正因為這樣，DepthSpace 應運而生。我們創建這個旅遊規劃網站的宗旨，在於減輕旅行者規劃旅程的負擔，讓每個人都能夠輕鬆享受旅行帶來的樂趣。
			 DepthSpace 不僅提供直觀易用的旅遊規劃工具，更涵蓋了一系列的旅行產品和服務。
			 我們致力於為使用者打造一站式的旅遊規劃平台，從行前規劃到行程中的各種需求，DepthSpace 都能提供周到的服務與支持。
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