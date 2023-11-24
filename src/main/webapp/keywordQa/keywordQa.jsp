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
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .kwAns { display: none; }
    </style>
</head>
<body>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
    <div class="row">
        <div class="col-lg-2 g-3 mt-1">
            <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>
        <div class="col-lg-10 g-2 transparent rounded mt-1">
            <div class="container mt-4 bg-light">
                <h3 class="mb-4">關鍵字管理</h3>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>關鍵字編號</th>
                                <th>關鍵字名稱</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="keywordQaVO" items="${list}">
                                <tr>
                                    <td class="kwType" data-type="${keywordQaVO.kwTypes}">${keywordQaVO.kwTypes}</td>
                                    <td class="kwAns" data-type="${keywordQaVO.kwTypes}">${keywordQaVO.kwAns}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $('.kwType').click(function() {
            var type = $(this).data('type');
            $('.kwAns[data-type="' + type + '"]').toggle(); // 切換顯示/隱藏對應的答案
        });
    });
</script>

</body>
</html>
