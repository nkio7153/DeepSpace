<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<jsp:include page="../indexpage/head.jsp" />
	<title>新增新行程</title>
	<style>
		label {
			font-weight: bold;
			color: #008CBA;
		}
		
		.form-container {
			display: flex; /* 使用 Flex 布局 */
		}
		
		.left-div {
			flex: 1; /* 左边占据 50% 的宽度 */
			padding: 10px; /* 可以根据需要添加内边距 */
		}
		
		.right-div {
			flex: 1; /* 右边占据 50% 的宽度 */
			padding: 10px; /* 可以根据需要添加内边距 */
		}
		
		#tripDuration {
		    font-weight: bold;
		    color: #008CBA;
		    margin-left: 10px; /* 將元素往右移10px */
		}
		
		#tripDurationSelect {
		    margin-left: 10px; /* 將元素往右移10px */
		}
		
		#attractions {
			margin: 10px 0;
			border: 1px solid #ccc;
			padding: 10px;
			border-radius: 5px;
		}
		
		#newTour {
			background-color: #008CBA;
			color: #fff;
			padding: 10px 20px;
			border: none;
			cursor: pointer;
			margin-top: 10px;
		}
		
		button:hover {
			background-color: #006688;
		}
		
		input[type="text"], input[type="date"] {
			width: 100%;
			padding: 10px;
			margin: 5px 0;
			border: 1px solid #ccc;
			border-radius: 5px;
		}
		
		h1 {
			font-size: 24px;
			color: #008CBA;
			text-align: center;
			margin-top: 20px;
		}
		
		form {
			width: 70%; /* 設置表單的寬度為頁面寬度的70% */
			margin: 0 auto; /* 水平置中 */
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}
		
		.circle {
			width: 21px;
			height: 21px;
			border-radius: 50%;
			background-color: lightskyblue;
			margin-top: -8px;
			margin-left: 5px;
		}
		
		.plus {
			color: white;
			font-size: 15px;
			font-weight: bold;
			margin-top: -1px;
			margin-left: 1px;
		}
		
		.circle2 {
			width: 21px;
			height: 21px;
			border-radius: 50%;
			background-color: lightcoral;
			margin-top: -40px;
			margin-right: 10px;
		}
		
		
		/* 给新增的时间和输入框容器添加样式，调整它们的位置 */
		.new {
		    display: flex;
		    align-items: center;
		}
		
		/* 调整时间输入框的样式 */
/* 		.new-input-container input[type="time"] { */
/* 		    margin-right: 10px; */
/* 		} */
		
		/* 调整景点输入框的样式 */
		.new-input-container input[type="text"] {
/* 		    width: 100%; /* 取消宽度100% */ */
		}
		/*覆蓋之前的input type=text*/
		#newAttraction {
			margin: 5px 25px 5px 0;
		}
		li {
			list-style: none;
		}
		div.row{
			line-height : 1;
		}
		
	</style>
</head>
<body>
<%
	Integer memId;
	try {
	    memId = Integer.valueOf(request.getParameter("memId"));
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
%>
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />
	<h1 style="font-size: 24px; color: #333; text-align: center;">旅遊行程列表新增</h1>
	
	<form action="${pageContext.request.contextPath}/tr/save" method="post" id="form">
	<!-- 	存會員編號 -->
	<input type="hidden" name="memId" value="${param.memId}">

		<label for="tourName">行程名稱:</label>
		<input type="text" name="tourName" value="${tr.tourName}" required><br><br>
		
		<label for="tourDescription">為你規劃的行程寫下簡介吧!</label>
		<br>
		<textarea rows="2" cols="100" name="tourDescription" ></textarea>
		<br>
		
<!-- 		下拉式選單:行程類型 -->
		<label style="margin-left: 10px;">選擇旅遊行程類型:</label>
  		<select name="tourType" id="tourType" style="margin-left: 10px;">
	  		<c:forEach var="tourType" items="${list}">
	            <option value="${tourType.tourTypeId}">${tourType.tourTypName}</option>
	        </c:forEach>
  		</select>
		
		<div class="form-container">
			<div class="left-div">
				<label for="startDate">出發日期:</label>
				<input type="date" name="startDate" required onchange="updateDurationSelect()">
			</div>
			
		<div class="right-div">
				<label for="endDate">結束日期:</label>
				<input type="date" name="endDate" required onchange="updateDurationSelect()">
			</div>
		</div>

		<label for="tripDuration" id="tripDuration">總天數:</label>
		<br><br>	
		
		<div class="tourdays" id="tourdays">
		</div>
		
<!-- 		讓總天數也可以傳到servlet -->
		<input type="hidden" name="tripDuration" id="tripDurationInput" value="">
		<input type="submit" name="newTour" id="newTour" value="進行下一步" >
	</form>

	<script>
	// 更新日期及計算天數
	function updateDurationSelect() {
		var startDateInput = document.querySelector("input[name='startDate']");
		var endDateInput = document.querySelector("input[name='endDate']");
		var tripDuration = document.getElementById("tripDuration");
		//	    var selectedDays = document.getElementById("selectedDays");
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
			//	        selectedDays.innerHTML = "";
			tourdays.innerHTML = ""; // 清除 tourdays 内容
		}
	}
	// 添加日期選擇框的事件監聽器
	document.querySelector("input[name='startDate']").addEventListener("change", updateDurationSelect);
	document.querySelector("input[name='endDate']").addEventListener("change", updateDurationSelect);
	</script>
<%-- 	<script src = "${pageContext.request.contextPath}/tour/js/tour.js"></script> --%>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>