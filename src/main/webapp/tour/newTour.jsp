<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
  <title>新稱新行程</title>
  <style>
  
    label {
        font-weight: bold;
        color: #008CBA;
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
	    /* 其他表單的樣式 */
	}
</style>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
 <h1 style="font-size: 24px; color: #333; text-align: center;">旅遊行程列表新增</h1>
    <form method="post" action="#"> <!-- 提交表單給保存行程的JSP頁面 -->
    	<label for="tripName">行程名稱:</label>
        <input type="text" name="tripName" required><br><br>
        
<!--         <label for="memberName">會員姓名:</label> -->
<!--         <input type="text" name="memberName" required><br><br> -->

        <label for="startDate">出發日期:</label>
        <input type="date" name="startDate" required><br><br>

        <label for="endDate">結束日期:</label>
        <input type="date" name="endDate" required><br><br>

<!--         <h2>景點列表</h2> -->
<!--         <div id="attractions"> -->
<!--             這裡顯示動態新增的景點 -->
<!--         </div> -->
        <button type="button" onclick="addAttraction()">新增行程</button><br><br>

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