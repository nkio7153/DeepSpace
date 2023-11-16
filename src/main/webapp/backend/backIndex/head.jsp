<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    /*調整導航欄高度*/
    .mh{
        height: 60px;
    }
    /*朦朧區*/
    .transparent{
        background-color: rgba(255, 255, 255, 0.7);
    }
    fs-li{
        font-size: 0.9rem;
    }
    .sidebar-center{
        display: flex;
        justify-content: center;
        align-items: center;
    }
    /*固定側邊欄*/
    .sidebar-sticky{
        position:sticky;
        top:68px;
    }

</style>