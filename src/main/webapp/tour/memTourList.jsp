<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20 auto;
        }
        
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center; /* 文字置中 */
        }
        th {
            background-color: #008CBA;
            color: #fff;
        }
        h1, h2 {
		    text-align: center;
		}
		.info-container {
		    width: 50%;
		    float: left;
		}
		
		.description-container {
		    width: 50%;
		    float: left;
		}
	
	    label {
	        margin-left: 50px; /* 调整右边距以控制位置 */
	        margin-bottom: 20px;
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
		#tourDays {
			text-align: center;
			font-size: 20px;
		}
			
		
    </style>
    <title>行程詳細資訊</title>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<div style="margin: 20px">
    <h1>${list[0].tourName}</h1>
    
    <div class="info-container">
    <h2>基本資訊</h2>
    <table style="width: 80%; margin: 0 auto;">
        <tr>
            <td colspan="2">${list[0].startDate}~${list[0].endDate}</td>

        </tr>
        <tr style="text-align: center;">
            <td>總天數：</td>
            <td>${list[0].allDays} 天</td>
        </tr>
        
    </table>
    </div>
    
    <table>
    <div class="description-container">
    <h2>行程敘述</h2>
    <p >${list[0].tourDescription}</p>
    </div>
    </table>
    
    <table>
    	
    	<label for="dropdown">天數查詢：</label>
		<select id="dropdown" name="selectedOption">
		    <c:forEach var="day" items="${list}">
		        <option value="${day.tourDays}">第${day.tourDays}天</option>
		    </c:forEach>
		</select>
    	
    	<div>
    		<p id="tourDays">第${list[0].tourDays}天</p>
    	</div>
    	
        <tr>
            <th>出發時間</th>
            <th>景點</th>
            <th>地址</th>
        </tr>
        
        <c:forEach var="dayDetail" items="${list}">
            <tr>
                <td>${dayDetail.start}</td>
                <td>${dayDetail.attractionsName}</td>
                <td>${dayDetail.address}</td>
            </tr>
        </c:forEach>
    </table>
    
    <input type="button" value="返回" onclick="history.back()" id="btn_back">
</div>
    
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
