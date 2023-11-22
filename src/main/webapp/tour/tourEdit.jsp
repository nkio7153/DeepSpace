<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <jsp:include page="../indexpage/head.jsp"/>
    <!-- 引入Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>新增新行程</title>
    <style>
        label {
            font-weight: bold;
            color: #008CBA;
        }

        /* 		日期 */
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
            margin-bottom: 0px;
            font-size: 18px;
        }

        #tripDurationSelect {
            margin-left: 10px; /* 將元素往右移10px */
        }

        .attraction {
            width: 150px;
            height: 30px;
        }

        #addTour {
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

        .circle2, .circle3 {
            width: 21px;
            height: 21px;
            border-radius: 50%;
            background-color: lightcoral;
            margin-top: 8px;
            margin-left: 0px;
        }


        /* 给新增的时间和输入框容器添加样式，调整它们的位置 */
        .new {
            /*             display: flex; */
            margin-top: 10px;
            /*             align-items: center;  */
        }

        /* 调整景点输入框的样式 */
        .new-input-container input[type="text"] {
            /* width: 100%; /* 取消宽度100% */
        }

        li {
            list-style: none;
        }

        div.row {
            line-height: 1;
        }

        .oneDay {
            margin-top: 10px;
        }

        .city-container {
            display: flex;
            align-items: center;
        }

        label[for="attractionTime"] {
            margin: 10px;
            margin-left: 0px;
        }

        #btn_back {
            margin: 0 auto;
            display: block; /* 讓按鈕變成區塊元素，以佔據滿整行 */
            font-size: 18px;
            color: #fff;
            background-color: #008CBA;
            border: none;
            padding: 8px;
            cursor: pointer;
            border-radius: 6px;
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
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>
<h1 style="font-size: 24px; color: #333; text-align: center;">旅遊行程編輯頁面</h1>

<form action="${pageContext.request.contextPath}/tr/saveSecond" method="post" id="form">
    <!-- 	存會員編號及最新一筆行程編號 -->
    <input type="hidden" name="memId" value="${list[0].memId}">
    <input type="hidden" name="tourId" value="${list[0].tourId}">
    <label>行程名稱:</label>
    <input type="text" name="tourName" value="${list[0].tourName}">
    <br>
    <br>
    <label>為你規劃的行程寫下簡介吧!</label>
    <br>
    <input type="text" rows="2" cols="100" name="tourDescription" value="${list[0].tourDescription}">
    <br>
    <!-- 		下拉式選單:行程類型 -->
    <label style="margin-left: 10px;">行程類型:</label>
    <input type="text" name="tourTypeName" value="${tourTypName}">
    <br>
    <br>
    <div class="form-container">
        <div class="left-div">
            <label>出發日期:</label>
            <input type="date" name="startDate" value="${list[0].startDate}">
        </div>
        <div class="right-div">
            <label>結束日期:</label>
            <input type="text" name="endDate" value="${list[0].endDate}">
        </div>
    </div>

    <label for="tripDuration" id="tripDuration">總天數:${list[0].allDays}</label>
    <br>


    <c:forEach var="day" begin="1" end="${list[0].allDays}">

        <div class="row">
            <br><br>
                <%--            <div>--%>
            <br>
            <div style="display: flex">
                <span class="oneDay"> 第 ${day} 天 </span>
                <div class="circle3 d-flex align-items-center justify-content-center ms-auto ml-2 pb-1" name="dash">
                    <span class="dash">-</span>
                </div>
            </div>
            <input type="hidden" name="oneDay[${day}]" value="${day}" class="inputOneDay">
            <br><br>
            <!-- 下拉式選單:選擇縣市 -->
            <div class="city-container">
                <label style="margin: 10px; display: table-cell; vertical-align: middle;">選擇你想去的縣市:</label>
                <select name="city[${day}]" class="city" data-day="${day}"
                        style="padding: 5px; border: 1px solid #ccc; border-radius: 40px; background-color: #f5f5f5; font-family: Arial; font-size: 18px;">
                    <c:forEach var="dayDetail" items="${list}">
                        <c:if test="${day == dayDetail.tourDays}">
                            <c:forEach var="cityList" items="${cityList}">
                                <%--                        <option value="${cityList.cityId}">${cityList.cityName}</option>--%>
                                <option value="${cityList.cityId}"
                                        <c:if test="${cityList.cityName eq fn:substring(dayDetail.address, 0, 3)}">selected </c:if> >${cityList.cityName}</option>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
                <%--                <div class="addContainer">--%>
            <c:forEach var="dayDetail" items="${list}">
                <c:if test="${day == dayDetail.tourDays}">
                    <div class="ml-2 d-flex mt-2 add" name="add">
                        <div style="display: flex" class="container">
                            <input type="time" name="attractionTime[${day}]" style="margin-right: 10px;" required
                                   value="${dayDetail.start}" class="attractionTime">
                            <label style="margin: 10px; display: table-cell; vertical-align: middle;">你要去哪兒?</label>

                            <select name="attractions[${day}]" class="attraction"
                                    style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 50px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
                                <option value="" selected disabled>請選擇</option>

                                <c:forEach var="attraction" items="${attrAll}">
                                    <c:if test="${fn:substring(dayDetail.address, 0, 3) == fn:substring(attraction.address, 0, 3)}">
                                        <option value="${attraction.attractionsId}" <c:if
                                                test="${attraction.attractionsName eq dayDetail.attractionsName}"> selected </c:if> >
                                                ${attraction.attractionsName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>

                            <div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2 pb-1"
                                 name="dash">
                                <span class="dash">-</span>
                            </div>
                        </div>

                    </div>

                </c:if>
            </c:forEach>
                <%--                </div>--%>
                <%--            </div>--%>
                <%--        </div>--%>
                <%--            </c:forEach>--%>

            <div class="container offset-5" name="afterSelector">
                <div class="col-md-5 mt-2"></div>
                <div class="col-md-4 d-flex align-items-center">
                    <!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
                    <label class="form-label mt-3">新增景點</label>
                    <div class="circle d-flex align-items-center justify-content-center ml-2 mt-2" name="circle">
                        <span class="plus">+</span>
                    </div>
                </div>
                <div class="col-md-3 mt-2">
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="container">
        <div class="col-md-5 mt-2"></div>
        <div class="col-md-4 d-flex align-items-center">
            <!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
            <label class="form-label mt-3">新增天數</label>
            <div class="circle d-flex align-items-center justify-content-center ml-2 mt-2" name="circle">
                <span class="plus">+</span>
            </div>
        </div>
        <div class="col-md-3 mt-2">
        </div>
    </div>


    <!-- 		讓總天數也可以傳到servlet -->
    <input type="hidden" name="allDays">
    <input type="submit" name="addTour" id="addTour" value="儲存行程">
</form>

<script>

    html=`<div class="row">
            <br><br>
                <%--            <div>--%>
            <br>
            <div style="display: flex">
                <span class="oneDay"> 第 ${day} 天 </span>
                <div class="circle3 d-flex align-items-center justify-content-center ms-auto ml-2 pb-1" name="dash">
                    <span class="dash">-</span>
                </div>
            </div>
            <input type="hidden" name="oneDay[${day}]" value="" class="inputOneDay">
            <br><br>
            <!-- 下拉式選單:選擇縣市 -->
            <div class="city-container">
                <label style="margin: 10px; display: table-cell; vertical-align: middle;">選擇你想去的縣市:</label>
                <select name="city[" class="city""
                        style="padding: 5px; border: 1px solid #ccc; border-radius: 40px; background-color: #f5f5f5; font-family: Arial; font-size: 18px;">
                            <c:forEach var="cityList" items="${cityList}">
                                <%--                        <option value="${cityList.cityId}">${cityList.cityName}</option>--%>
                                <option value="${cityList.cityId}">${cityList.cityName}</option>
                            </c:forEach>
                </select>
            </div>
                <%--                <div class="addContainer">--%>
                    <div class="ml-2 d-flex mt-2 add" name="add">
                        <div style="display: flex" class="container">
                            <input type="time" name="attractionTime[${day}]" style="margin-right: 10px;" required
                                   value="">
                            <label style="margin: 10px; display: table-cell; vertical-align: middle;">你要去哪兒?</label>

                            <select name="" class="attraction"
                                    style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 50px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
                                <option value="" selected disabled>請選擇</option>

                                <c:forEach var="attraction" items="${attrAll}">
                                        <option value="${attraction.attractionsId}">${attraction.attractionsName}</option>
                                </c:forEach>
                            </select>

                            <div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2 pb-1"
                                 name="dash">
                                <span class="dash">-</span>
                            </div>
                        </div>

                    </div>


                <%--                </div>--%>
                <%--            </div>--%>
                <%--        </div>--%>
                <%--            </c:forEach>--%>

            <div class="container offset-5" name="afterSelector">
                <div class="col-md-5 mt-2"></div>
                <div class="col-md-4 d-flex align-items-center">
                    <!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
                    <label class="form-label mt-3">新增景點</label>
                    <div class="circle d-flex align-items-center justify-content-center ml-2 mt-2" name="circle">
                        <span class="plus">+</span>
                    </div>
                </div>
                <div class="col-md-3 mt-2">
                </div>
            </div>
        </div>`;
    <%--            tourdays.appendChild(newTourDay);--%>
    <%--        }--%>
    <%--    })--%>


    //           //輸入景點的焦點文字框
    // 			function clearPlaceholder(input) {
    // 				if (input.value === "輸入景點") {
    // 					input.value = "";
    // 				}
    // 			}

    // 			function restorePlaceholder(input) {
    // 				if (input.value === "") {
    // 					input.value = "輸入景點";
    // 				}
    // 			}

    //增加行程選項(圓形)
    // 		let circle = $("[name='circle']");
    // 		let circle2 = $("#circle2");
    // 		let select_html = `
    // 		        	<div class="row">
    // 						<div class="col-md-12 justify-content-center d-flex align-items-center">

    // 					 		<div class="new">
    // 					 		<label for="attractionTime">時間:</label>
    // 					 		<input type="time" name="attractionTime" id="newTime" style="margin-right: 10px;" required>
    // 					 		<label style="margin-left: 10px;">你要去哪兒?</label>
    //                             <select name="attractions" id="attractions" style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 50px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
    //                             	<option value="" selected disabled>請選擇</option>
    // 	                            	<c:forEach var="attraction" items="${attrvo}">
    // 	                                    <option value="${attraction.attractionsId}">${attraction.attractionsName}</option>
    // 	                                </c:forEach>
    //                             </select>
    // 							</div>

    // 						<div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2" id="dash">
    // 							<span class="dash ">-</span>
    // 				    	</div>
    // 				    	</div>
    // 				    </div>`;
    // 1109更新
    let select_html = `
		        	<div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2 pb-1" name="dash">
							<span class="dash">-</span>
				    	</div>
				    </div>`;
    // 1109更新
    $('body').on('mouseover', '[name="circle"]', function () {
// 			console.log("mouseover觸發了");
        $(this).css({
            "background-color": "blue",
            "width": "25px",
            "height": "25px"
        });
    });

    $('body').on('mouseout', '[name="circle"]', function () {
        $(this).css({
            "background-color": "lightskyblue",
            "width": "21px",
            "height": "21px"
        });
    });

    //1109測試
    $('body').on('click', '[name="circle"]', function () {
// 			console.log("click事件觸發了")

        // 複製 .addContainer 內的第一個 .add 元素
        var clonedElement = $(this).closest("[class='row']").find('[name="add"]').eq(0).clone();

        // 重置複製元素內的特定欄位值
        clonedElement.find('input[type="time"]').val('');
        clonedElement.find('select').prop('selectedIndex', 0);
        // 將複製的元素添加到 .addContainer 的末尾
        $(this).closest("[name='afterSelector']").before(clonedElement);


    });
    //1109測試

    // 		$('body').on('click', '[name="circle"]', function() {
    // 			console.log("click事件觸發了")
    // 			$(this).closest("[name='afterSelector']").before(select_html);

    // 			//		    $(this).closest(".row").find("[name='afterSelector']").before(select_html);
    // 		});

    // 圓形-
    // 委派事件到動態生成的 .circle2 元素
    $('body').on('mouseover', '.circle2', function () {
        $(this).css({
            "background-color": "red",
            "width": "25px",
            "height": "25px"
        });
    });

    $('body').on('mouseout', '.circle2', function () {
        $(this).css({
            "background-color": "lightcoral",
            "width": "21px",
            "height": "21px"
        });
    });

    $('body').on('click', '.circle2', function () {
        // $(this).closest('[name="dash"]').prev().remove();
        // $(this).closest('[name="dash"]').remove();
        $(this).closest("[name='add']").remove();
    });

    //刪除天數圓形減
    $('body').on('mouseover', '.circle3', function () {
        $(this).css({
            "background-color": "red",
            "width": "25px",
            "height": "25px"
        });
    });

    $('body').on('mouseout', '.circle3', function () {
        $(this).css({
            "background-color": "lightcoral",
            "width": "21px",
            "height": "21px"
        });
    });
    //修改天數
    $('body').on('click', '.circle3', function () {
        $(this).closest(".row").remove();
        for (let i = 0; i < $(".oneDay").length; i++) {
            $(".oneDay").eq(i).text("第" + (i + 1) + "天");
            $(".inputOneDay").eq(i).attr("name", "oneDay[" + (i + 1) + "]");
            $(".inputOneDay").eq(i).val(i + 1);
            $(".attraction").attr("name", "attractions[" + (i + 1) + "]");
            $(".attractionTime").attr("name", "attractionTime[" + (i + 1) + "]");
        }
    });


    //=====================================================================

    function getSelectedAttraction(event) {
        let selectedAttraction = $(event.target).find("option:selected").text();
// 			    console.log("拿到的地點" + selectedAttraction);
    }

    $(document).ready(function () {

        $(".city").on("change", function (event) {

            let cityName = $(this).find("option:selected").text();
// 		        console.log("cityName="+cityName);
            console.log(this);

            let attractionsSelect = $(this).closest(".row").find(".attraction");
            let url = "${pageContext.request.contextPath}/tr/getAttractions?cityName=" + cityName;
            fetch(url)
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {
// 		                  console.log(data);
// 		                  let attractionsSelect = $("[name='attractions']")
                    // 要先清空原本選項
                    attractionsSelect.empty();
                    let option = document.createElement("option");
                    option.text = "請選擇";
                    attractionsSelect.append(option);
                    // 遍歷從後端獲取的景點資料，並生成選項
                    data.forEach(function (attractions) {
                        let option = document.createElement("option");
                        option.value = attractions.attractionsId;
                        option.text = attractions.attractionsName;
                        attractionsSelect.append(option);

                    });
                })

                .catch(function (error) {
                    console.log(error);
                })


        });
    });
</script>
<%--     <script src="${pageContext.request.contextPath}/tour/js/tour2.js"></script> --%>
<jsp:include page="../indexpage/footer.jsp"/>
</body>
</html>