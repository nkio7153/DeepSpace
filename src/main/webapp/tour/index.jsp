<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
  <title>我的行程</title>
  <style>
  
    label {
        font-weight: bold;
        color: #008CBA;
    }
    #tripDuration {
        font-weight: bold;
        color: #008CBA;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        border: 1px solid #ccc;
        margin: 10px 0;
    }

    table th, table td {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: left;
    }

    th {
        background-color: #008CBA;
        color: #fff;
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

<h1 style="font-size: 24px; color: #333; text-align: center;">查看我的行程列表</h1>
<form method="post" action="#"> <!-- 提交表單給保存行程的JSP頁面 -->
    <table>
        <tr>
            <th>行程名稱</th>
            <th>查看行程</th>
        </tr>
        <c:forEach var="trip" items="${yourTripList}">
            <tr>
                <td>${trip.tripName}</td>
                <td>
                    <button type="button" onclick="viewTrip('${trip.tripId}')">查看行程</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>