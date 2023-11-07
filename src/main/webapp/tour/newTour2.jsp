<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        /* .new-input-container input[type="time"] { */
        /*     margin-right: 10px; */
        /* } */

        /* 调整景点输入框的样式 */
        .new-input-container input[type="text"] {
            /* width: 100%; /* 取消宽度100% */
        }

        /*覆蓋之前的input type=text */
        #newAttraction {
            margin: 5px 25px 5px 0;
        }

        li {
            list-style: none;
        }

        div.row {
            line-height: 1;
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
        <input type="hidden" name="memId" value="${tourVO.memId}">
        <label for="tourName">行程名稱:</label>
        <input type="text" name="tourName" value="${tourVO.tourName}" readonly>
        <br>
        <br>
        <label for="tourDescription">為你規劃的行程寫下簡介吧!</label>
        <br>
        <input type="text" rows="2" cols="100" name="tourDescription" value="${tourVO.tourDescription}" readonly>
        <br>
        <!-- 		下拉式選單:行程類型 -->
        <label style="margin-left: 10px;">行程類型:</label>
        <input type="text" name="tourTypeName" value="${ttvo.tourTypName}" readonly>
        <br>
        <br>
        <div class="form-container">
            <div class="left-div">
                <label for="startDate">出發日期:</label>
                <input type="text" name="startDate" value="${tourVO.startDate}" readonly>
            </div>
            <div class="right-div">
                <label for="endDate">結束日期:</label>
                <input type="text" name="endDate" value="${tourVO.endDate}" readonly>
            </div>
        </div>
        <label for="tripDuration" id="tripDuration">總天數:${tourVO.allDays}</label>
        <br>
        <br>
        <!-- 		下拉式選單:選擇縣市 -->
        <label style="margin-left: 10px;">選擇你想去的縣市:</label>
        <select name="city" id="city" style="margin-left: 10px;">
            <c:forEach var="cityList" items="${cityList}">
                <option value="${cityList.cityId}">${cityList.cityName}</option>
            </c:forEach>
        </select>
        <!-- 		下拉式選單:選擇地區 -->
        <label style="margin-left: 10px;">選擇你想查的地區:</label>
        <select name="area" id="area" style="margin-left: 10px;">
            <c:forEach var="area" items="${data}">
                <option value="${area.areaId}">${area.areaName}</option>
            </c:forEach>
        </select>
        <div class="tourdays" id="tourdays"></div>
        <!-- 		讓總天數也可以傳到servlet -->
        <input type="hidden" name="tripDuration" id="tripDurationInput" value="">
        <input type="submit" name="newTour" id="newTour" value="儲存行程">
    </form>
    <script>
        $("#city").on("change",function(){
            let cityId=$("#city").find("option:selected").val();
            console.log(cityId);
            let url="${pageContext.request.contextPath}/tr/getArea?cityId="+cityId;
            fetch(url)
                    .then(function(response){
                      return response.json();
                    })
                    .then(function(data){
                      console.log(data);
                      let areaSelect = $("#area");
//                   要先清空原本選項
                      areaSelect.empty();
                   // 遍歷從伺服器獲取的地區資料，並動態生成選項
                      data.forEach(function(area){
                          let option = document.createElement("option");
                          option.value = area.areaId;
                          option.text = area.areaName;
                          areaSelect.append(option);
                      });
                    })
                    .catch(function(error){
                      console.log(error);
                    })
          })
          
//           $("#tripDuration")}
          
    </script>
<%--     <script src="${pageContext.request.contextPath}/tour/js/tour2.js"></script> --%>
    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
