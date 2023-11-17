<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Depthspace後台</title>
    <%--  include head.jsp--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" rel="stylesheet">

    <!-- 引入Bootstrap 5.3.0 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- 引入Bootstrap 5.3.0 Bundle JS (包括Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- 引入jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <!-- 載入icon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/indexpage/images/iconhtml.png" />
    <style>
        #sidebar-wrapper .nav-item {
            list-style-type: none;
            /* 移除項目符號 */
        }

        #wrapper {
            min-height: 60vh;
            /* 設定最小高度為視口的100% */
        }

        #sidebar-wrapper {
            width:180px;
            min-width: 180px;
            min-height: 50vh;
        }

        .parent-div {
            display: flex;
            /* 使用Flexbox布局 */
            justify-content: flex-start;
            /* 確保項目從開始位置對齊 */
        }

        .child-div {
            margin-left: auto;
            /* 推動這個元素到最右側 */
        }

        /*.bg-gray {*/
        /*    background-color: rgb(211, 211, 211);*/
        /*    !* 淺灰色 *!*/
        /*}*/

        .text-choco {
            color: #4E342E;
            text-decoration: none;
        }

        .text-choco.collapse-item:hover {
            color: #f2f0ee;
            text-decoration: underline;
        }
        .text-dark{
            color: #4E342E;
            text-decoration: none;
        }
        .text-dark.fw-bold:hover {
            text-decoration: underline;
        }


        body {

            margin: 0;
            /* 移除邊距 */
            padding: 0;
            /* 移除填充 */
            height: 100%;
            /* 設定高度為100% */

        <%--background-image: url("${pageContext.request.contextPath}/backend/backIndex/image/table2.jpg");--%>
            background-image: url("${pageContext.request.contextPath}/backend/backIndex/image/mountain.jpg");

            background-size: cover;

            background-attachment: fixed;

            background-position: top center;

            background-repeat: no-repeat;
        }

        .color-2-4 {
            background-color: #627D98;
        }
        .color-4-1 {
            background-color: #B0C4DE;
        }


        nav.navbar {
            background-color: rgba(211, 211, 211, 0);
        }
        .mh{
            height: 60px;
        }
        .transparent{
            background-color: rgba(255, 255, 255, 0.7);
        }
        .fs-li{
            font-size: 0.9rem;
        }
        .sidebar-center{
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<%--include header.jsp--%>
<nav class="navbar navbar-expand-lg navbar-light parent-div pb-0 ">
    <a class="navbar-brand fs-2 mx-2 my-0 fw-bold p-0" href="${pageContext.request.contextPath}/indexpage/index.jsp">
        <img class="mh my-0" src="${pageContext.request.contextPath}/backend/backIndex/image/logo.jpg">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse child-div" id="navbarNav">
        <ul class="navbar-nav ms-auto fs-5 mx-2 ">
            <li class="nav-item active">
                <a class="nav-link fs-4 btn btn-outline-primary" href="#">登出<span class="sr-only">()</span></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <%--    側邊欄--%>
        <div class="col-lg-2 g-3 mt-0">
            <div class="border-right rounded color-2-4" id="sidebar-wrapper">
<%--                <div class="fs-4 fw-bold offset-1 my-2">後台管理系統</div>--%>
                <a href="${pageContext.request.contextPath}/backend/backIndex/index.jsp" class="fs-4 text-dark fw-bold my-2 sidebar-center">後台管理系統</a>
                <div class="list-group list-group-flush sidebar-center">
                    <!-- 員工管理 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                                <span class="text-light fs-5">員工管理 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/backmem/list">查詢資料</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">新增資料</a>
                            </div>
                        </div>
                    </li>

                    <!-- 會員管理 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapseUtilities" aria-expanded="true"
                           aria-controls="collapseUtilities">
                                <span class="text-light fs-5">會員管理 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/backmem/list">查詢資料</a>
<%--                                <br>--%>
<%--                                <a class="text-choco collapse-item fs-5 offset-3" href="#">修改資料</a>--%>
                            </div>
                        </div>
                    </li>

                    <!-- 廠商管理 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                                <span class="text-light fs-5">廠商管理 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">新增資料</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">修改資料</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">檢舉廠商</a>
                            </div>
                        </div>
                    </li>

                    <!-- 票卷管理 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapseTickets" aria-expanded="true" aria-controls="collapseTickets">
                                <span class="text-light fs-5">票卷管理 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapseTickets" class="collapse" aria-labelledby="headingTickets"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/ticketmg/list">票卷列表</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/tickettypesmg/list">票卷類型</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/to/listAll">訂單管理</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/pro/getAll">促銷管理</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">銷售分析</a>
                            </div>
                        </div>
                    </li>

                    <!-- 網頁管理 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapseWebManagement" aria-expanded="true"
                           aria-controls="collapseWebManagement">
                                <span class="text-light fs-5">網頁管理 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapseWebManagement" class="collapse" aria-labelledby="headingWebManagement"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">文章管理</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">檢舉管理</a>
                            </div>
                        </div>
                    </li>

                    <!-- 權限設定 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapsePermissions" aria-expanded="true"
                           aria-controls="collapsePermissions">
                                <span class="text-light fs-5">權限設定 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapsePermissions" class="collapse" aria-labelledby="headingPermissions"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item offset-1" href="#">員工權限</a>
                                <br>
                                <a class="text-choco collapse-item offset-1" href="#">廠商權限</a>
                            </div>
                        </div>
                    </li>

                    <!-- 專欄 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapseColumnManagement" aria-expanded="false"
                           aria-controls="collapseColumnManagement">
                                <span class="text-light fs-5">專欄管理 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapseColumnManagement" class="collapse" aria-labelledby="headingColumnManagement"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/columnmg/list">專欄列表</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="${pageContext.request.contextPath}/columntypesmg/list">專欄類型</a>
                            </div>
                        </div>
                    </li>

                    <!-- 客服中心 -->
                    <li class="nav-item mb-2">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#collapseCustomerService" aria-expanded="false"
                           aria-controls="collapseCustomerService">
                                <span class="text-light fs-5">客服中心 <i
                                        class="fa-solid fa-angle-down"></i></span>
                        </a>
                        <div id="collapseCustomerService" class="collapse" aria-labelledby="headingCustomerService"
                             data-bs-parent="#sidebar-wrapper">
                            <div class="color-4-1 py-2 collapse-inner rounded">
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">客服問題</a>
                                <br>
                                <a class="text-choco collapse-item fs-6 offset-1" href="#">回報問題</a>
                            </div>
                        </div>
                    </li>

                </div>
            </div>
        </div>
            <div class="col-lg-10 g-2 mt-2">
            <%--      放入自己body裡的代碼--%>


        </div>
    </div>
</div>

</body>
</html>
