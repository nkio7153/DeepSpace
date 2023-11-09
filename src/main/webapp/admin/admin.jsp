<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%-- <%@ page language="java" contentType="text/html;charset=UTF-8" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<html>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600&family=Roboto:wght@300;400;500&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

section {
  position: relative;
  width: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background: #2196f3; */
  background-image: url("https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/2018_McLaren_720S_V8_S-A_4.0.jpg/800px-2018_McLaren_720S_V8_S-A_4.0.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  overflow: hidden;
}

.swiper {
  width: 100%;
  padding-top: 50px;
  padding-bottom: 50px;
}

.swiper-slide {
  width: 300px;
  height: 400px;
  box-shadow: 0 15px 50px rgba(0, 0, 0, 0.2);
  filter: blur(1px);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: end;
  align-items: self-start;
}

.swiper-slide-active {
  filter: blur(0px);
}

.swiper-pagination-bullet,
.swiper-pagination-bullet-active {
  background: #fff;
}

.swiper-slide span {
  text-transform: uppercase;
  color: #fff;
  background: #1b7402;
  padding: 7px 18px 7px 25px;
  display: inline-block;
  border-radius: 0 20px 20px 0px;
  letter-spacing: 2px;
  font-size: 0.8rem;
  font-family: "Open Sans", sans-serif;
}

.swiper-slide--one span {
  background: #62667f;
}

.swiper-slide--two span {
  background: #087ac4;
}

.swiper-slide--three span {
  background: #b45205;
}

.swiper-slide--four span {
  background: #087ac4;
}

.swiper-slide h2 {
  color: #fff;
  font-family: "Roboto", sans-serif;
  font-weight: 400;
  font-size: 1.3rem;
  line-height: 1.4;
  margin-bottom: 15px;
  padding: 25px 45px 0 25px;
}

.swiper-slide p {
  color: #fff;
  font-family: "Roboto", sans-serif;
  font-weight: 300;
  display: flex;
  align-items: center;
  padding: 0 25px 35px 25px;
}

.swiper-slide svg {
  color: #fff;
  width: 22px;
  height: 22px;
  margin-right: 7px;
}

.swiper-3d .swiper-slide-shadow-left,
.swiper-3d .swiper-slide-shadow-right {
  background-image: none;
}
</style>

<head>
<title>admin</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/group/css/registration.css"/>
<script type="text/javascript">
  $(document).ready(function(){
      var swiper = new Swiper(".swiper", {
          effect: "coverflow",
          grabCursor: true,
          centeredSlides: true,
          slidesPerView: "auto",
          coverflowEffect: {
              rotate: 0,
              stretch: 0,
              depth: 100,
              modifier: 2,
              slideShadows: true
          },
          keyboard: {
              enabled: true
          },
          mousewheel: {
              thresholdDelta: 70
          },
          spaceBetween: 60,
          loop: true,
          pagination: {
              el: ".swiper-pagination",
              clickable: true
          }
       });
  })
  
  //表單點擊找出對應的function
  function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/admin/adminUpdate.jsp?adminId=" + jsonData.adminID ;
  }
  function processDelete(jsonData){
 	 $.ajax({
     //指定http參數傳輸格式為POST
     type : "POST",
     //ajax請求配置
     data : jsonData,
     //請求目標的url
     url : "<%=request.getContextPath()%>/administrator.do?action=del",
     //Ajax成功後執行的function，response為回傳的值
     success : function(data) {
    	 alert('刪除成功');
    	 window.location.reload();
     },
     //Ajax失敗後要執行的function，此例為印出錯誤訊息
     error : function(xhr, ajaxOptions, thrownError) {
      alert("哇 錯了");
     }
    });
  }
  //跳轉到add.jsp頁面
  function processAdd() {
	  window.location.href= "<%=request.getContextPath()%>/admin/adminAdd.jsp"
  }
</script>
</head>
<body>
<jsp:useBean id="adminService" scope="page" class="com.depthspace.admin.model.service.AdminServiceImpl"/>
    <section>
    	<div style="display:inline-block;">
    		<input type="button" class='button' id="btnAdd" value="新增" onclick="processAdd();" > 
			<td><input type="button" value="修改" onclick="processUpdate({adminId:'${admin.adminId}'});"></td> 
   			<td><input type="button" value="刪除" onclick="processDelete({ADMIN_ID:'${admin.adminId}'});"></td>	
    </section> 
    
</body>
</html>



