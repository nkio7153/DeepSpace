<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.keywordqa.model.KeywordQaVO"%>
<%@ page import="com.depthspace.keywordqa.service.*"%>
<%@ page import="com.depthspace.keywordqa.controller.*"%>
<%@ page import="com.depthspace.keywordqa.model.*"%>

<%
    KeywordQaService keywordQaSvc = new KeywordQaService();
	List<KeywordQaVO> list = keywordQaSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
    <title>關鍵字管理</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  <!-- Bootstrap CSS -->
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
      <div class="container mt-4 bg-light">
        <h3 class="mb-4">關鍵字管理</h3>
        <a href="<%=request.getContextPath()%>/keywordQa/select_page.jsp" class="btn btn-primary mb-3">回首頁</a>
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>流水號</th>
                        <th>關鍵字編號</th>
                        <th>關鍵字名稱</th>
                        <th>修改</th>
                        <th>刪除</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="keywordQaVO" items="${list}">
                        <tr>
                            <td>${keywordQaVO.serialId}</td>
                            <td>${keywordQaVO.kwTypes}</td>
                            <td>${keywordQaVO.kwAns}</td>
                            <td>
                                <form method="post" action="<%=request.getContextPath()%>/keywordqa/keywordQa.do">
                                    <input type="submit" class="btn btn-secondary btn-sm" value="修改">
                                    <input type="hidden" name="serialId" value="${keywordQaVO.serialId}">
                                    <input type="hidden" name="action" value="getOne_For_Update">
                                </form>
                            </td>
                            <td>
                                <form method="post" action="<%=request.getContextPath()%>/keywordqa/keywordQa.do">
                                    <input type="submit" class="btn btn-danger btn-sm" value="刪除">
                                    <input type="hidden" name="serialId" value="${keywordQaVO.serialId}">
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

</body>
</html>