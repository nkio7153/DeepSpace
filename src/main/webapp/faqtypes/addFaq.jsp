<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>

<%
    FaqTypesVO faqTypesVO = (FaqTypesVO) request.getAttribute("faqTypesVO");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>FaqTypes資料新增 - addFaq.jsp</title>
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
      <div class="col-lg-10 g-2 my-0">
                <h3>FaqTypes資料新增 - addFaq.jsp</h3>
                <h4><a href="select_page.jsp" class="btn btn-primary">回首頁</a></h4>

                <h3>FaqTypes資料新增:</h3>

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

                <form method="post" action="faqTypes.do" name="form1" class="form-group">
                    <label for="qTypes">問題類型:</label>
                    <input type="text" class="form-control" name="qTypes" value="<%= (faqTypesVO==null)? "1" : faqTypesVO.getQTypes()%>" size="45">
                    <input type="hidden" name="action" value="insert">
                    <button type="submit" class="btn btn-primary mt-2">送出新增</button>
                </form>
            </div>

    </div>
  </div>
</div>

<!-- Bootstrap 5 JS -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script> -->
</body>
</html>
