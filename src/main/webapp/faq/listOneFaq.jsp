<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faq.model.FaqVO"%>
<%@ page import="com.depthspace.faq.service.*"%>
<%@ page import="com.depthspace.faq.controller.*"%>
<%@ page import="com.depthspace.faq.model.*"%>

<%
    FaqVO faqVO = (FaqVO) request.getAttribute("faqVO");
%>
<!DOCTYPE html>
<html>
<head>
    <title>常見問題 - listOneFaq</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
  <div class="row">
<%--    側邊欄--%>
    <div class="col-lg-2 g-3 mt-1">
    <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
    </div>

    <div class="col-lg-10 g-2 transparent rounded mt-1">
<%--      放入自己body裡的代碼--%>
      <div class="container mt-4">
        <h3 class="mb-3">常見問題 - listOneFaq</h3>
        <a href="select_page.jsp" class="btn btn-primary mb-3">
            <img src="images/back1.gif" alt="回首頁" class="img-fluid">
        </a>
        
        <div class="table-responsive">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th>流水號</th>
                        <th>FAQ編號</th>
                        <th>FAQ名稱</th>
                        <th>FAQ答案</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${faqVO.serialId}</td>
                        <td>${faqVO.faqNo}</td>
                        <td>${faqVO.faqName}</td>
                        <td>${faqVO.faqAns}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    </div>
  </div>
</div>


  <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
