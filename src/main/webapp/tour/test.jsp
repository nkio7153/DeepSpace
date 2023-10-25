<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Tset</title>
</head>
<body>
<button onclick="openCreateTripLightbox()">新增行程規劃</button>

<div id="lightbox">
    <form method="post" action="process_form.jsp">
        表單內容，包括會員姓名、出發日期、結束日期、行程名稱等
        <input type="text" name="memberName" required>
        <input type="date" name="startDate" required>
        <input type="date" name="endDate" required>
        <input type="text" name="tripName" required>
        <button type="submit">儲存行程</button>
    </form>
</div>

<script>
<!-- //     function openCreateTripLightbox() { -->
<!-- //         // 顯示燈箱的程式碼，可以使用JavaScript或jQuery等方式實現 -->
<!-- //     	// 獲取按鈕和燈箱的元素 -->
<!-- //     	const openLightboxButton = document.getElementById("openLightboxButton"); -->
<!-- //     	const lightbox = document.getElementById("lightbox"); -->
<!-- //     	const closeLightboxButton = document.getElementById("closeLightboxButton"); -->

<!-- //     	// 點擊按鈕時顯示燈箱 -->
<!-- //     	openLightboxButton.addEventListener("click", function () { -->
<!-- //     	    lightbox.style.display = "block"; -->
<!-- //     	}); -->

<!-- //     	// 點擊取消按鈕時隱藏燈箱 -->
<!-- //     	closeLightboxButton.addEventListener("click", function () { -->
<!-- //     	    lightbox.style.display = "none"; -->
<!-- //     	}); -->

<!-- //     } -->
</script>


</body>
</html>