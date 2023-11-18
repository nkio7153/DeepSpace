<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light parent-div pb-0">
<%--    <a class="navbar-brand fs-2 mx-2 my-0 fw-bold p-0" href="${pageContext.request.contextPath}/indexpage/index.jsp">--%>
<%--        <img class="mh my-0" src="${pageContext.request.contextPath}/backend/backIndex/image/logo.jpg">--%>
<%--    </a>--%>
<%--    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"--%>
<%--            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
    <div class="collapse navbar-collapse child-div" id="navbarNav">
        <ul class="navbar-nav ms-auto fs-5 mx-2">
            <c:if test="${adminId != null}">
            <li class="nav-item active">
                <a class="nav-link fs-4 btn logout" href="${pageContext.request.contextPath}/ad/logout">登出<span class="sr-only">()</span></a>
            </li>
            </c:if>
        </ul>
    </div>
</nav>