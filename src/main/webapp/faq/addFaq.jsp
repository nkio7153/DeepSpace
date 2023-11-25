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
<html lang="zh-TW">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="UTF-8">
    <title>Faq資料新增 - addFaq.jsp</title>
    <!-- Bootstrap 5 CSS -->
<!--     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"> -->
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
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
                    <div class="card-header text-white bg-primary">
                        <h3>常見問題資料新增</h3>
                        <h4><a href="select_page.jsp" class="text-light">回常見問題的首頁</a></h4>
                    </div>
                    <div class="card-body">
                        <h3>資料新增:</h3>
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
                        <form method="post" action="faq.do" name="form1">
                            <div class="form-group">
                                <label for="faqNo">常見問題編號:</label>
                                <input type="text" class="form-control" name="faqNo" value="<%= (faqVO==null)? "101" : faqVO.getFaqNo()%>" size="45">
                            </div>
                            <div class="form-group">
                                <label for="faqName">常見問題名稱:</label>
                                <input type="text" class="form-control" name="faqName" value="<%= (faqVO==null)? "測試" : faqVO.getFaqName()%>" size="45">
                            </div>
                            <div class="form-group">
                                <label for="faqAns">常見問題答案:</label>
                                <input type="text" class="form-control" name="faqAns" value="<%= (faqVO==null)? "你好" : faqVO.getFaqName()%>" size="45">
                            </div>
                            <input type="hidden" name="action" value="insert">
                            <button type="submit" class="btn btn-primary">送出新增</button>
                        </form>
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
