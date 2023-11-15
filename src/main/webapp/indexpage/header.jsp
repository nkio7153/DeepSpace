<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<script>

        function checkSession(e){
            let check=$("[name='check']").text();
            if(check=="登入/註冊"){
                e.preventDefault();
                window.alert("請先登入")
            }
        }
    </script>

    <div class="container" style="padding-right: 0;">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid" style="padding: 20px 0;">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/indexpage/index.jsp">
                    <img src="${pageContext.request.contextPath}/indexpage/images/icon.png" alt="" width="200px"                       
                         class="d-inline-block align-text-top">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav menu ml-auto">
                        <li class="nav-item">
                            <a class="active" href="${pageContext.request.contextPath}/indexpage/index.jsp">首頁</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownTicket" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                票券
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownTicket">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ticketproduct/list">票券瀏覽</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/to/memOrderList" onclick="checkSession(event)">訂單管理</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/pro/getCard">促銷活動</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/mto/memList"  onclick="checkSession(event)">我的票券</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="" href="${pageContext.request.contextPath}/forumArticles/list.jsp">論壇文章</a>
                        </li>
                        <li class="nav-item">
                            <a class="" href="${pageContext.request.contextPath}/Rest/getRests">餐廳</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownTicket" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                景點
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="trip">
                                <li><a class="dropdown-item" href="">景點瀏覽</a></li>
                                <li><a class="dropdown-item" href="">我的行程</a></li>                               
                            </ul>
                        </li>
                       	<c:if test="${sessionScope.memId == null}">
                        <li class="nav-item">
                            <a class="booking" href="${pageContext.request.contextPath}/member/login.jsp" name="check">登入/註冊</a>
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.memId != null}">
                        <li class="nav-item dropdown">
                            		<a class="booking">會員</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownTicket">
                                <li><a class="dropdown-item">${authenticatedMem.memName}</a></li>
                                <li><a class="dropdown-item" href="">我的會員資料</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/mto/memList"  onclick="checkSession(event)">我的票券</a></li>
                                <li><a class="dropdown-item" href="">通知</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/forumArticles.do?action=getmemlist">我的文章</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/mem/logout" name="check">登出</a></li>
                            </ul>
                        </li>
                        </c:if>
                        <li class="">
                            <a href="${pageContext.request.contextPath}/tsc/memCartList"><img
                               src="${pageContext.request.contextPath}/indexpage/images/shoppingcar.svg"
                               alt=""
                               style="width: 2em"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>


