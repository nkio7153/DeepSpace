<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>常見問題 首頁</title>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .container-fluid {
            padding-top: 20px;
        }
        .card {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .alert-danger {
            background-color: #f8d7da;
            border-color: #f5c6cb;
        }
        .alert-danger ul {
            margin-bottom: 0;
        }
        h3, h4 {
            margin-bottom: 15px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            margin-bottom: 10px;
        }
    </style>
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
                        <h3>IBM Faq: Home (MVC)</h3>
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
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
