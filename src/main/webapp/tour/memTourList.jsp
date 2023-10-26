<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 0 auto;
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
		
    </style>
    <title>行程詳細資訊</title>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
    <h1>行程名稱~</h1>
    
    <div class="info-container">
    <h2>基本資訊</h2>
    <table>
        <tr>
            <td>*日期*</td>
        </tr>
        <tr>
            <td>總天數：</td>
            <td>${tour.allDays} 天</td>
        </tr>
        <tr>
            <td></td>
            <td>${tour.tourType.typeName}</td>
        </tr>
    </table>
    </div>
    <table>
    <div class="description-container">
    <h2>行程敘述</h2>
    <p>${tour.tourDescription}</p>
    </div>
    <table>
    
    <table>
    <h2>行程圖片</h2>
    <img src="${tour.tourImg}" alt="行程圖片">
    
    <h2>行程天數明細</h2>
    
        <tr>
            <th>天數</th>
            <th>景點名稱</th>
            <th>開始時間</th>
            <th>結束時間</th>
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
