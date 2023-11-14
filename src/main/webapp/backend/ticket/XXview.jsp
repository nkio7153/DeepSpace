<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>查看票券</title>
    <%--  include --%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>

<body>

<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
    <div class="row">
        <div class="col-lg-2 g-3 my-0">
            <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>
        
        <div class="col-lg-10 g-2 transparent rounded my-0">
            <div class="container mt-5">
                <h1>查看票券</h1>
                <div class="row">
                    <!-- 票券詳情 -->
                    <div class="col-md-6">
                        <p><strong>票券類型:</strong> ${ticket.ticketType.typeName}</p>
                        <p><strong>票券名稱:</strong> ${ticket.ticketName}</p>
                        <p><strong>價格:</strong> ${ticket.price}</p>
                        <p><strong>數量:</strong> ${ticket.stock}</p>
                        <p><strong>使用天數:</strong> ${ticket.validDays}</p>
                        <p><strong>描述:</strong> ${ticket.description}</p>
                        <p><strong>區域:</strong> ${ticket.city.cityName}</p>
                        <p><strong>地址:</strong> ${ticket.address}</p>
                        <p><strong>經度:</strong> ${ticket.longitude}</p>
                        <p><strong>緯度:</strong> ${ticket.latitude}</p>
                        <p><strong>上下架狀況:</strong> ${ticket.status == 1 ? '上架' : '未上架'}</p>
                    </div>
                    <!-- 圖片列表 -->
                    <div class="col-md-6">
                        <label><strong>圖片列表:</strong></label>
                        <div id="image-list" class="d-flex flex-wrap">
                            <!-- 已上傳的圖片會透過JavaScript動態加載到這裡 -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    $(document).ready(function() {
        const ticketId = ${ticket.ticketId};
        $.getJSON("<%=request.getContextPath()%>/ticketallimage?action=getIds&ticketId=" + ticketId, function(serialIds) {
            var imageListDiv = $("#image-list").empty();
            serialIds.forEach(function(id) {
                var image = $('<img class="img-thumbnail m-2" style="max-height: 200px; object-fit: cover;">')
                    .attr("src", "<%=request.getContextPath()%>/ticketallimage?action=getImage&imageId="  + id +
