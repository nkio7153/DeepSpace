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

        .attraction {
             width: 150px; 
    		 height: 30px; 
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
/*             align-items: center;  */
        }

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
        .oneDay {
		    margin-top: 10px;
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
<!--         <label style="margin-left: 10px;">選擇你想去的縣市:</label> -->
<!--         <select name="city" id="city"  style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 5px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;"> -->
<%--             <c:forEach var="cityList" items="${cityList}"> --%>
<%--                 <option value="${cityList.cityId}">${cityList.cityName}</option> --%>
<%--             </c:forEach> --%>
<!--         </select> -->
<!--         		下拉式選單:選擇地區 -->
<!--         <label style="margin-left: 10px;">選擇你想搜尋的地區:</label> -->
<!--         <select name="area" id="area"  style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 5px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;"> -->
<%--             <c:forEach var="area" items="${data}"> --%>
<%--                 <option value="${area.areaId}">${area.areaName}</option> --%>
<%--             </c:forEach> --%>
<!--         </select> -->
        <div class="tourdays" id="tourdays"></div>
        <!-- 		讓總天數也可以傳到servlet -->
        <input type="hidden" name="tripDuration" id="tripDurationInput" value="">
        <input type="submit" name="newTour" id="newTour" value="儲存行程">
    </form>
    <script>
//     $("#city").on("change",function(){
//     	let cityId=$("#city").find("option:selected").val();
// //         console.log(cityId);
//         let url="${pageContext.request.contextPath}/tr/getArea?cityId="+cityId;
//         fetch(url)
//                 .then(function(response){
//                   return response.json();
//                 })
//                 .then(function(data){
// //                   console.log(data);
//                   let areaSelect = $("#area");
// //               要先清空原本選項
//                   areaSelect.empty();
//                // 遍歷從伺服器獲取的地區資料，並動態生成選項
//                   data.forEach(function(area){
//                       let option = document.createElement("option");
//                       option.value = area.areaId;
//                       option.text = area.areaName;
//                       areaSelect.append(option);
//                   });
//                 })
//                 .catch(function(error){
//                   console.log(error);
//                 })
//       });

		//=====================================================================
			
$(document).ready(function () {
    // 獲取alldays的文字資料
    let alldaysText = $("#tripDuration").text();

    // 使用政則表達式提取數字部分\d來匹配數字
    let alldaysMatch = alldaysText.match(/\d+/);
    if (alldaysMatch) {
        // 如果找到匹配的數字部分，就轉型變成數字
        let alldays = parseInt(alldaysMatch[0]);
//         console.log("alldays=" + alldays)

//         for (var i = 1; i <= alldays; i++) {
            var newTourDay = document.createElement("div");
            newTourDay.classList.add("container");
            
            newTourDay.innerHTML = `
               <c:forEach var="day" items="${dayList}">
                  <div class="row">
                    <br><br>
                    <div>
                        <br><br>
                        <span class="oneDay"> 第 ${day} 天 </span>
                        
                       
                        	 <!-- 		下拉式選單:選擇縣市 -->
				        <label style="margin-left: 10px;" >選擇你想去的縣市:</label>
				        <select name="city" id="city" class="city" data-day="${day}" style="padding: 5px; border: 1px; border-radius: 5px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
				            <c:forEach var="cityList" items="${cityList}">
				                <option value="${cityList.cityId}">${cityList.cityName}</option>
				            </c:forEach>
				        </select>
                       
                        
                        <div class="col-md-12 d-flex justify-content-center">
                            <label for="attractionTime">時間:</label>
                            <input type="time" name="attractionTime" style="margin-right: 10px;" required>
                            <label style="margin-left: 10px;">你要去哪兒?</label>
                            <select name="attractions" id="attractions" style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 50px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
                            <option value="" selected disabled>請選擇</option>
                            	<c:forEach var="attraction" items="${attrvo}">
                                    <option value="${attraction.attractionsId}">${attraction.attractionsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="container offset-5" name="afterSelector">
                        <div class="col-md-5 mt-2"></div>
                        	<div class="col-md-4 d-flex align-items-center">
                            <!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
                            <label for="tourAttr" class="form-label mt-3">新增景點</label>
                            	<div class="circle d-flex align-items-center justify-content-center ml-2 mt-2" name="circle">
                                	<span class="plus" >+</span>
                            	</div>
                        	</div>
                        	<div class="col-md-3 mt-2">
                        	</div>
                    	</div>
                    </div>
                    </div>
                </c:forEach>`;
            tourdays.appendChild(newTourDay);
//         }
    }
})

          
          //輸入景點的焦點文字框
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
          
          //增加行程選項(圓形)
		let circle = $("[name='circle']");
		let circle2 = $("#circle2");
		let select_html = `
		        	<div class="row">
						<div class="col-md-12 justify-content-center d-flex align-items-center">
					 		
					 		<div class="new">
					 		<label for="attractionTime">時間:</label>
					 		<input type="time" name="attractionTime" id="newTime" style="margin-right: 10px;" required>
					 		<label style="margin-left: 10px;">你要去哪兒?</label>
                            <select name="attractions" id="attractions" style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 5px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
                            	<option value="" selected disabled>請選擇</option>
	                            	<c:forEach var="attraction" items="${attrvo}">
	                                    <option value="${attraction.attractionsId}">${attraction.attractionsName}</option>
	                                </c:forEach>
                            </select>
							</div>
						</div>
						<div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2" id="dash">
							<span class="dash ">-</span>
				    	</div>
				    </div>`;
		
		$('body').on('mouseover', '[name="circle"]', function() {
			console.log("mouseover觸發了");
			$(this).css({
				"background-color": "blue",
				"width": "25px",
				"height": "25px"
			});
		});
		
		$('body').on('mouseout', '[name="circle"]', function() {
			$(this).css({
				"background-color": "lightskyblue",
				"width": "21px",
				"height": "21px"
			});
		});
		
		$('body').on('click', '[name="circle"]', function() {
			console.log("click事件觸發了")
			$(this).closest("[name='afterSelector']").before(select_html);
		
			//		    $(this).closest(".row").find("[name='afterSelector']").before(select_html);
		});
		
		
		
		// 圓形-
		// 委派事件到動態生成的 .circle2 元素
		$('body').on('mouseover', '.circle2', function() {
			$(this).css({
				"background-color": "red",
				"width": "25px",
				"height": "25px"
			});
		});
		
		$('body').on('mouseout', '.circle2', function() {
			$(this).css({
				"background-color": "lightcoral",
				"width": "21px",
				"height": "21px"
			});
		});
		
		$('body').on('click', '.circle2', function() {
			$(this).closest(".row").remove();
		});
			
		//=====================================================================
			
			function getSelectedAttraction(event) {
			    let selectedAttraction =  $(event.target).find("option:selected").text();
			    console.log("拿到的地點" + selectedAttraction);
			}
			$(document).ready(function () {
			// 解綁 city 的 change 事件
// 			$(".city").off("change");
			
			$(".city").on("change",function(event){
				 getSelectedAttraction(event);
				
				 let selectedCity = $(this).val();
				 console.log("selectedCity=" + selectedCity)
		    	let cityName=$(this).find("option:selected").text();
		        console.log("cityName="+cityName);
		        
		        let attractionsSelect=$(this).closest(".row").find("[name='attractions']");
		        
		        let url="${pageContext.request.contextPath}/tr/getAttractions?cityName="+cityName;
		        fetch(url)
		                .then(function(response){
		                  return response.json();
		                })
		                .then(function(data){
// 		                  console.log(data);
// 		                  let attractionsSelect = $("[name='attractions']")
		               // 要先清空原本選項
		                  attractionsSelect.empty();
		                  let option = document.createElement("option");
		                  option.text="請選擇";
		                  attractionsSelect.append(option);
		               // 遍歷從伺服器獲取的景點資料，並動態生成選項
		                  data.forEach(function(attractions){
		                      let option = document.createElement("option");
		                      option.value = attractions.attractionsId;
		                      option.text = attractions.attractionsName;
		                      attractionsSelect.append(option);                   
		                  
		                  });


		                })
		                
		                .catch(function(error){
		                  console.log(error);
		                })
		        
// 		        $(this).find("[name='attractions']").each(function(){
// 					 $(this).text(attractionsSelect);
// 				 })
				       
		      });
			});
// 		// 解綁 change 事件
// 		 $("#city").off("change");
    </script>
<%--     <script src="${pageContext.request.contextPath}/tour/js/tour2.js"></script> --%>
    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
