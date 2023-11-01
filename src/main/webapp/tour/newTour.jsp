<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
}

#attractions {
	margin: 10px 0;
	border: 1px solid #ccc;
	padding: 10px;
	border-radius: 5px;
}

button {
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
	margin-top: -30px;
	margin-left: 330px;
}
</style>
</head>
<body>
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />
	<h1 style="font-size: 24px; color: #333; text-align: center;">旅遊行程列表新增</h1>
	<form method="post" action="#">
		<!-- 提交表單給保存行程的JSP頁面 -->
		<label for="tripName">行程名稱:</label> <input type="text" name="tripName"
			required><br> <br>

		<!--         <label for="memberName">會員姓名:</label> -->
		<!--         <input type="text" name="memberName" required><br><br> -->

		<div class="form-container">
			<div class="left-div">
				<label for="startDate">出發日期:</label> <input type="date"
					name="startDate" required onchange="updateDurationSelect()">
			</div>
			<div class="right-div">
				<label for="endDate">結束日期:</label> <input type="date" name="endDate"
					required onchange="updateDurationSelect()">
			</div>
		</div>
		<!--         <h2>景點列表</h2> -->
		<!--         <div id="attractions"></div> -->
		<!--             這裡顯示動態新增的景點 -->

		<label for="tripDuration">總天數:</label> <select id="tripDurationSelect"
			name="tripDurationSelect">
			<!-- 下拉式选单将在JavaScript中动态生成 -->
		</select>
		<!--         <span id="tripDuration"></span> -->

		<div class="col-md-12 mt-2 d-flex justify-content-center">
			<label for="attractionTime">時間:</label> <input type="time"
				name="attractionTime" style="margin-right: 10px;" required>
			<input type="text" name="attraction" required placeholder="輸入景點"
				onfocus="clearPlaceholder(this)" onblur="restorePlaceholder(this)">
		</div>

		<!-- 		<div class="col-md-12 mt-2 d-flex justify-content-center"> -->
		<!-- 		    <input type="time" name="attractionDate" required> -->
		<!-- 		    <input type="text" name="attraction" required placeholder="輸入景點" onfocus="clearPlaceholder(this)" onblur="restorePlaceholder(this)"> -->
		<!-- 		</div> -->
		<!-- 		<div class="col-md-12 mt-2 d-flex justify-content-center"> -->
		<!-- 			<input type="text" name="attraction" required placeholder="輸入景點" onfocus="clearPlaceholder(this)" onblur="restorePlaceholder(this)"> -->
		<!-- 		</div> -->

		<div class="container offset-5" id="afterSelector">
			<div class="col-md-5 mt-2"></div>
			<div class="col-md-4 d-flex align-items-center">
				<!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
				<label for="ticketId" class="form-label mt-3">新增行程</label>
				<div
					class="circle d-flex align-items-center justify-content-center ml-2 mt-2"
					id="circle">
					<span class="plus">+</span>
				</div>
			</div>
			<div class="col-md-3 mt-2"></div>
		</div>

		<button type="button" onclick="addAttraction()">新增行程</button>

	</form>

	<script>
	// 函数用于更新時間选择框
	function updateAttractionTime(input) {
	    var attractionTimeInput = document.querySelector("input[name='attractionTime']");
	    var selectedTime = input.value;
	    attractionTimeInput.value = selectedTime;
	}

	// 在輸入景點的文字框之前的時間選擇框上添加事件監聽器
	document.querySelector("input[name='attractionTime']").addEventListener("change", function () {
	    updateAttractionTime(this);
	});
	
    // 函数用于更新下拉式选单中的天数选项
    function updateDurationSelect() {
        var startDateInput = document.querySelector("input[name='startDate']");
        var endDateInput = document.querySelector("input[name='endDate']");
        var tripDurationSelect = document.getElementById("tripDurationSelect");

        if (startDateInput.value && endDateInput.value) {
            var startDate = new Date(startDateInput.value);
            var endDate = new Date(endDateInput.value);

            var timeDiff = Math.abs(endDate - startDate);
            var daysDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));

            // 清空下拉式选单
            tripDurationSelect.innerHTML = '';

            // 动态生成天数选项并添加到下拉式选单中
            for (var i = 1; i <= daysDifference; i++) {
                var option = document.createElement("option");
                option.value = i;
                option.text ="第 " + i + " 天";
                tripDurationSelect.appendChild(option);
            }
        } else {
            // 若未选择日期，则清空下拉式选单
            tripDurationSelect.innerHTML = '';
        }
    }
    
    //輸入景點
    function clearPlaceholder(input) {
	    if (input.value === "輸入景點") {
	        input.value = "";
	    }
	}
	
	function restorePlaceholder(input) {
	    if (input.value === "") {
	        input.value = "輸入景點";
	    }
	}
    
    
    let circle=$("#circle");
    let circle2=$("#circle2");
    let select_html=`
        <div class="container">
            <div class="row">
            <div class="col-md-4 mt-2" name="se"></div>
            <div class="col-md-4 mt-2" id="selector" name="se">
                <label for="ticketId" class="form-label">新增行程:</label>
                <input type="text" id="newAttraction" name="newAttraction">
                </select>
                <div class="circle2 d-flex align-items-center justify-content-center ml-2" id="dash">
                    <span class="dash">-</span>
                </div>
            </div>
            <div class="col-md-4 mt-2" name="se"></div>
            </div>
        </div>`;
    $(document).ready(function(){
	    circle.on("mouseover", function () {
	        $(this).css({
	            "background-color": "blue",
	            "width":"25px",
	            "height":"25px"
	        });
	    })
	    circle.on("mouseout", function () {
	        $(this).css({
	            "background-color": "lightskyblue",
	            "width":"21px",
	            "height":"21px"
	        });
	    })
	    circle.on("click", function(){
	        $(this).closest(".row").find("#afterSelector").before(select_html);
	
	    })
    });
    
    
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