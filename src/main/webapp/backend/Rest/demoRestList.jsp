<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
	<jsp:include page="/indexpage/head.jsp" />
	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />
    <style>
    </style>
</head>

<body>

    <!-- account/list.jsp 改成你的包跟jsp檔 -->
    <jsp:include page="../Rest/listAllRests.jsp" />
    <!-- account/list.jsp 改成你的包跟jsp檔 -->


    <!--footer -->
    <jsp:include page="/indexpage/footer.jsp" />
    <!-- //footer -->

    <script src="${pageContext.request.contextPath}/indexpage/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/indexpage/js/bootstrap.min.js"></script>
    <!--載入 jQuery-->
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.6.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/rest.js"></script>
</body>

</html>