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
     <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>常見問題資料修改</title>
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
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h3>常見問題資料修改</h3>
                    </div>
                    <div class="card-body">
                        <h4><a href="select_page.jsp" class="text-light">回首頁</a></h4>
                        <h3>常見問題資料修改:</h3>
                        <c:if test="${not empty errorMsgs}">
                            <div class="alert alert-danger">
                                <strong>請修正以下錯誤:</strong>
                                <ul>
                                    <c:forEach var="message" items="${errorMsgs}">
                                        <li>${message}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                        <form method="post" action="faq.do" name="form1">
                            <div class="form-group">
                                <label>流水號:</label>
                                <input type="text" class="form-control" name="serialId" value="<%=faqVO.getSerialId()%>" readonly>
                            </div>
                            <div class="form-group">
                                <label>常見問題編號:</label>
                                <input type="text" class="form-control" name="faqNo" value="<%=faqVO.getFaqNo()%>">
                            </div>
                            <div class="form-group">
                                <label>常見問題名稱:</label>
                                <input type="text" class="form-control" name="faqName" value="<%=faqVO.getFaqName()%>">
                            </div>
                            <div class="form-group">
                                <label>常見問題答案:</label>
                                <input type="text" class="form-control" name="faqAns" value="<%=faqVO.getFaqAns()%>">
                            </div>
                            <input type="hidden" name="action" value="update">
                            <button type="submit" class="btn btn-primary">送出修改</button>
                        </form>
                    </div>
                </div>
            </div>

    </div>
  </div>
</div>

<!-- Bootstrap 5 JS -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script> -->
</body>
</html>
