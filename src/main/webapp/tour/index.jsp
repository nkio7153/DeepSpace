<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
  <title>旅遊行程規劃</title>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
 <h1>旅遊行程規劃</h1>
    <form method="post" action="#"> <!-- 提交表單給保存行程的JSP頁面 -->
        <label for="memberName">會員姓名:</label>
        <input type="text" name="memberName" required><br><br>

        <label for="startDate">出發日期:</label>
        <input type="date" name="startDate" required><br><br>

        <label for="endDate">結束日期:</label>
        <input type="date" name="endDate" required><br><br>

        <label for="tripName">行程名稱:</label>
        <input type="text" name="tripName" required><br><br>

        <h2>景點列表</h2>
        <div id="attractions">
            <!-- 這裡顯示動態新增的景點 -->
        </div>
        <button type="button" onclick="addAttraction()">新增景點</button><br><br>

        <label for="tripDuration">總天數:</label>
        <span id="tripDuration">1</span>

        <input type="submit" value="儲存行程">
    </form>

    <script>
        var dayCounter = 1; // 初始天數

        function addAttraction() {
            dayCounter++;
            var newAttraction = document.createElement("div");
            newAttraction.innerHTML = `
                <h3>第 ${dayCounter} 天</h3>
                <label for="attraction${dayCounter}">景點:</label>
                <input type="text" name="attraction${dayCounter}">
            `;
            document.getElementById("attractions").appendChild(newAttraction);
            document.getElementById("tripDuration").innerText = dayCounter;
        }
    </script>
 <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>