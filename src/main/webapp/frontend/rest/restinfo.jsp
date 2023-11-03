<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<jsp:include page="../indexpage/head.jsp" />
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />
</head>
<body>
	
	<jsp:include page="info/r${restId}.jsp" />


	<jsp:include page="../indexpage/footer.jsp" />
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
</body>
</html>
