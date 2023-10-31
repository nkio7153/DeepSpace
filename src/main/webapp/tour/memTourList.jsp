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
			
		
    </style>
    <title>行程詳細資訊</title>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
    <h1>行程名稱~</h1>
    
    <div class="info-container">
    <h2>基本資訊</h2>
    <table style="width: 80%; margin: 0 auto;">
        <tr>
            <td colspan="2">*日期*</td>

        </tr>
        <tr style="text-align: center;">
            <td>總天數：</td>
            <td>${tour.allDays} 天</td>
        </tr>
        
    </table>
    </div>
    
    <table>
    <div class="description-container">
    <h2>行程敘述</h2>
    <p>${tour.tourDescription}</p>
    </div>
    </table>
    
    <table>    
    <h2></h2>
    	
    	<label for="dropdown">天數查詢：</label>
		<select id="dropdown" name="selectedOption">
		    <c:forEach var="option" items="${yourOptionList}">
		        <option value="${option.value}">第${option.label}天</option>
		    </c:forEach>
		</select>
    	
        <tr>
            <th>出發時間</th>
            <th>結束時間</th>
            <th>地點</th>
            <th>地址</th>
        </tr>
        <c:forEach var="dayDetail" items="${tour.daysDetails}">
            <tr>
                <td>${dayDetail.tourDays}</td>
                <td>${dayDetail.attractions.attractionsName}</td>
                <td>${dayDetail.start}</td>
                <td>${dayDetail.end}</td>
            </tr>
        </c:forEach>
    </table>
    
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
