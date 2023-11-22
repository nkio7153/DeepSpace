<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <title>行程詳細資訊</title>
</head>

<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<div style="margin: 20px">
    <h1><input type="text" id="tourName" name="tourName" value="${list[0].tourName}"></h1>
    
    <div class="info-container">
        <h2>基本資訊</h2>
        <table style="width: 80%; margin: 0 auto;">
            <input type="hidden" id="memId" name="memId" value="${list[0].memId}">
            <input type="hidden" id="tourId" name="tourId" value="${list[0].tourId}">
            
            <tr>
                <td colspan="2">
                    <input type="date" name="startDate" value="${list[0].startDate}">~
                    <input type="date" name="endDate" value="${list[0].endDate}">
                </td>
            </tr>
<!--             <tr> -->
                <label for="tripDuration" id="tripDuration">總天數:</label>
<!--             </tr> -->
        </table>
    </div>
    
    <table>
        <div class="description-container">
            <h2>行程敘述</h2>
            <textarea id="tourDescription" name="tourDescription">${list[0].tourDescription}</textarea>
        </div>
    </table>
   
    <table>
        <c:forEach var="day" begin="1" end="${list[0].allDays}">
            <tr class="spacing-row"></tr>
       
            <div class="tourdays" id="tourdays">
			</div>
            <tr>
                <th>出發時間</th>
                <th>景點</th>
             
            </tr>
                        
            <c:forEach var="dayDetail" items="${list}">
                <c:if test="${day == dayDetail.tourDays}">
                    <tr>
                        <td>
                            <input type="time" id="start" name="start" value="${dayDetail.start}">
                        </td>
                        <td>
                            <input type="text" id="attractionsName" name="attractionsName" value="${dayDetail.attractionsName}">
                        </td>
                       
                    </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
    <div class="btn-container">
        <input type="button" value="返回" onclick="history.back()" class="btn_back">
        <form action="${pageContext.request.contextPath}/tr/save" method="post">
            <input type="submit" value="儲存行程" class="save">
        </form>
    </div>
</div>
<script type="text/javascript">
	
	
	// 更新日期及計算天數
	function updateDurationSelect() {
		var startDateInput = document.querySelector("input[name='startDate']");
		var endDateInput = document.querySelector("input[name='endDate']");
		var tripDuration = document.getElementById("tripDuration");
		var tourdays = document.getElementById("tourdays");
		var tripDurationInput = document.getElementById("tripDurationInput");

		if (startDateInput.value && endDateInput.value) {
			var startDate = new Date(startDateInput.value);
			var endDate = new Date(endDateInput.value);

			var timeDiff = Math.abs(endDate - startDate);
			var daysDifference = Math.ceil(timeDiff / (1000 * 3600 * 24) + 1);

			tripDuration.innerText = "總天數: 共 " + daysDifference + " 天";
			tripDurationInput.value = daysDifference;

			// 清除先前的 tourdays 内容
			tourdays.innerHTML = "";

		} else {
			tripDuration.innerText = "總天數:";
			tourdays.innerHTML = ""; // 清除 tourdays 内容
		}
	}
	
	//添加日期選擇框的事件監聽器
	document.querySelector("input[name='startDate']").addEventListener("change", updateDurationSelect);
	document.querySelector("input[name='endDate']").addEventListener("change", updateDurationSelect);
</script>

</body>
</html>
