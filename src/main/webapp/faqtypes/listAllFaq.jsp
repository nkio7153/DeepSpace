<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.FaqTypesVO"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>

<%
    FaqTypesService faqSvc = new FaqTypesService();
    List<FaqTypesVO> list = faqSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>FAQTypes</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  <!-- Bootstrap 5 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
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
        <h3>FAQTypes - 首頁</h3>
        <a href="select_page.jsp" class="btn btn-primary mb-3">回首頁</a>
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>常見問題類型編號</th>
                        <th>問題類型</th>
                        <th>修改</th>
                        <th>刪除</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="faqTypesVO" items="${list}">
                        <tr>
                            <td>${faqTypesVO.faqNo}</td>
                            <td>${faqTypesVO.QTypes}</td>
                            <td>
                                <form method="post" action="<%=request.getContextPath()%>/faqtypes/faqTypes.do">
                                    <button type="submit" class="btn btn-secondary btn-sm">修改</button>
                                    <input type="hidden" name="faqNo" value="${faqTypesVO.faqNo}">
                                    <input type="hidden" name="action" value="getOne_For_Update">
                                </form>
                            </td>
                            <td>
                                <form method="post" action="<%=request.getContextPath()%>/faqtypes/faqTypes.do">
                                    <button type="submit" class="btn btn-danger btn-sm">刪除</button>
                                    <input type="hidden" name="faqNo" value="${faqTypesVO.faqNo}">
                                    <input type="hidden" name="action" value="delete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
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
