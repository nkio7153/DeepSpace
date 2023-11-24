<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.depthspace.faqtypes.model.service.FaqTypesService"%>


<!DOCTYPE html>
<html>
<head>
    <title>FaqTypes: Home</title>
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
                <div class="mb-4">
                    <h3>FaqTypes: Home</h3>
                </div>

                <p>FaqTypes: Home</p>

                <h3>FaqTypes資料查詢:</h3>

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

                <ul>
                    <li><a href='listAllFaq.jsp' class='btn btn-link'>列表</a> all FaqTypes.</li>
                    <li>
                        <form method="post" action="faqTypes.do" class="form-inline my-2">
                            <b>輸入常見問題類型編號 :</b>
                            <input type="text" name="faqNo" class="form-control mx-2">
                            <input type="hidden" name="action" value="getOne_For_Display">
                            <button type="submit" class="btn btn-primary">送出</button>
                        </form>
                    </li>

                    <jsp:useBean id="faqSvc" scope="page" class="com.depthspace.faqtypes.model.service.FaqTypesService" />
                    <li>
                        <form method="post" action="faqTypes.do" class="form-inline my-2">
                            <b>選擇常見問題類型編號:</b>
                            <select size="1" name="faqNo" class="form-control mx-2">
                                <c:forEach var="faqTypesVO" items="${faqSvc.all}">
                                    <option value="${faqTypesVO.faqNo}">${faqTypesVO.faqNo}
                                </c:forEach>
                            </select>
                            <input type="hidden" name="action" value="getOne_For_Display">
                            <button type="submit" class="btn btn-primary">送出</button>
                        </form>
                    </li>

                    <li>
                        <form method="post" action="faqTypes.do" class="form-inline my-2">
                            <b>選擇問題類型:</b>
                            <select size="1" name="faqNo" class="form-control mx-2">
                                <c:forEach var="faqTypesVO" items="${faqSvc.all}">
                                    <option value="${faqTypesVO.faqNo}">${faqTypesVO.QTypes}
                                </c:forEach>
                            </select>
                            <input type="hidden" name="action" value="getOne_For_Display">
                            <button type="submit" class="btn btn-primary">送出</button>
                        </form>
                    </li>
                </ul>

                <h3>FaqTypes管理</h3>
                <ul>
                    <li><a href='addFaq.jsp' class='btn btn-link'>新增</a> a new FaqTypes.</li>
                </ul>
            </div>

    </div>
  </div>
</div>

<!-- Bootstrap 5 JS -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script> -->
</body>
</html>
