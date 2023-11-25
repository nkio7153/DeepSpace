<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.depthspace.faqtypes.model.model.FaqTypesVO"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>

<%
    FaqTypesVO faqTypesVO = (FaqTypesVO) request.getAttribute("faqTypesVO");
%>

<html>
<head>
    <title>FaqTypes資料 - listOneFaq.jsp</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  <!-- Bootstrap 5 CSS -->
<!--     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"> -->
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
      <div class="col-lg-10">
                <h3>FaqTypes資料 - listOneFaq.jsp</h3>
                <a href="select_page.jsp" class="btn btn-primary mb-3">
                    <img src="images/back1.gif" alt="回首頁" class="img-fluid">
                </a>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>常見問題類型編號</th>
                            <th>問題類型</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${faqTypesVO.faqNo}</td>
                            <td>${faqTypesVO.QTypes}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

    </div>
  </div>
</div>

    <!-- Bootstrap 5 JS -->
<!--     <SCRIPT SRC="HTTPS://CDN.JSDELIVR.NET/NPM/@POPPERJS/CORE@2.9.3/DIST/UMD/POPPER.MIN.JS"></SCRIPT> -->
<!--     <SCRIPT SRC="HTTPS://CDN.JSDELIVR.NET/NPM/BOOTSTRAP@5.1.3/DIST/JS/BOOTSTRAP.MIN.JS"></SCRIPT> -->
</body>
</html>
