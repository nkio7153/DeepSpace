<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>常見問題 首頁</title>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
     <!-- 引入 Bootstrap 5 CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2">
                <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
            </div>
            <div class="col-lg-10">
                <div class="card">
                    <div class="card-header">
                        <h3>常見問題 : 首頁</h3>
                    </div>
                    <div class="card-body">
                        <p>Faq: Home</p>
                        <h3>FAQ資料查詢:</h3>
                        <c:if test="${not empty errorMsgs}">
                            <div class="alert alert-danger" role="alert">
                                <strong>請修正以下錯誤:</strong>
                                <ul>
                                    <c:forEach var="message" items="${errorMsgs}">
                                        <li>${message}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>

<ul>
  <li><a href='listAllFaq.jsp'>列表</a> all Faqs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="faq.do" >
        <b>輸入FAQ員工編號 :</b>
        <input type="text" name="serialId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="faqSvc" scope="page" class="com.depthspace.faq.service.FaqService" />
   
  <li>
     <FORM METHOD="post" ACTION="faq.do" >
       <b>選擇FAQ編號:</b>
       <select size="1" name="serialId">
         <c:forEach var="faqVO" items="${faqSvc.all}" > 
          <option value="${faqVO.serialId}">${faqVO.serialId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="faq.do" >
       <b>選擇FAQ問題:</b>
       <select size="1" name="serialId">
         <c:forEach var="faqVO" items="${faqSvc.all}" > 
          <option value="${faqVO.serialId}">${faqVO.faqName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>FAQ管理</h3>
                <ul>
                    <li><a href='addFaq.jsp'>新增</a> a new Faq.</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 引入 Bootstrap 5 JS 和相關依賴 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.7.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"></script>
</body>
</html>
