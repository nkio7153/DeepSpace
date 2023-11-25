<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.tour.service.TourService"%>
<%@ page import="com.depthspace.tour.model.tour.TourVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
TourService ts = new TourService();
List<TourVO> list = ts.getByMemId((Integer) session.getAttribute("memId"));
pageContext.setAttribute("list", list);
%>


<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--     <link rel="stylesheet" href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">  
  

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
        width: 70%;
        border-collapse: collapse;
        border: 1px solid #ccc;
        margin: 10px auto;
    }

    table th {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: left;
    }
    
    table td {
	    border: 1px solid #ccc;
	    padding: 10px;
	    text-align: left;
	    max-width: 200px; /* 設定最大寬度，根據您的需求調整 */
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	}

    th {
        background-color: #008CBA;
        color: #fff;
    }

    #btn, #btn_back, #btn_add, .delete1 {
        background-color: #008CBA;
        color: #fff;
        border: none;
        border-radius: 10px;
        padding: 10px 20px;
        cursor: pointer;
    }

    #btn:hover, #btn_back:hover, #btn_add:hover, .delete1:hover {
        background-color: lightblue;
    }

    h1 {
        font-size: 2.5rem;
         color: #2D3E4E;
        text-align: center;
        margin-top: 20px;
    }

    /* 新增樣式 */
    .add-button {
        position: relative;
/*         padding: 8px 30px; */
        width: 150px;
        height: 40px;
        cursor: pointer;
        display: flex;
        align-items: center;
        border: 1px solid #63bfc9;
        background-color: #63bfc9;
        border-radius: 5px;
        border: none;
    }

    .add-button, .button__icon, .button__text {
        transition: all 0.3s;
    }

    .add-button .button__text {
        transform: translateX(30px);
        color: #fff;
        font-weight: 600;
    }

    .add-button .button__icon {
        position: absolute;
        transform: translateX(109px);
        height: 100%;
        width: 39px;
        background-color: #63bfc9;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 5px;
        border: none;
    }

    .add-button .svg {
        width: 30px;
        stroke: #fff;
    }

    .add-button:hover {
        background: #63bfc9;
    }

    .add-button:hover .button__text {
        color: transparent;
    }

    .add-button:hover .button__icon {
        width: 148px;
        transform: translateX(0);
    }

    .add-button:active .button__icon {
        background-color: #2e8644;
    }

    .add-button:active {
        border: 1px solid #2e8644;
    }

    .btn-primary {
        background: #63bfc9; 
        border: #63bfc9; 
    }
    .btn-primary:hover {
        background: #85cdd5 !important; 
        border: #85cdd5; 
    }
    .btn-success{
        background: #63bfc9 !important;  
        border: #63bfc9 !important;  
    }
    .btn-success:hover {
        background: #85cdd5 !important;  
        border: #85cdd5; 
    }
</style>
</head>
<body>
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />

	<h1>行程列表</h1>
	<hr>
        <div style="display: none">
            <%@ include file="../tour/page1.file"%>
        </div>
	<div style="display: flex; justify-content: center; align-items: center; margin-left:730px;">
        <div style="margin: auto;">
            <form action="${pageContext.request.contextPath}/tr/addTour?memId=${authenticatedMem.memId}" method="post">
                <button type="submit" class="add-button">
                    <span class="button__text">新增</span>
                    <span class="button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
                </button>
            </form>
        </div>
    </div>

    
	<table>
		<tr>
			<th style="text-align: center;">行程名稱</th>
			<th style="text-align: center;">行程資訊</th>
			<th style="text-align: center;">操作</th>
		</tr>

		<c:forEach var="tour" items="${list}" varStatus="status" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
                <td style="text-align: center;">${tour.tourName}</td>
                <td style="text-align: center;">
                    <c:set var="memId" value="${tour.memId}"/>
                    <c:set var="tourId" value="${tour.tourId}"/> 
                        <input type="hidden" name="memId" value="${tour.memId}">
                        <input type="hidden" name="tourId" value="${tour.tourId}">
                        <span class="tourDescription">${tour.tourDescription}</span>
                        <br>
                        <span class="dateRange">
			                <fmt:formatDate value="${tour.startDate}" pattern="yyyy年MM月dd日"/> ~ 
			                <fmt:formatDate value="${tour.endDate}" pattern="yyyy年MM月dd日"/>
			            </span>
                </td>
                <td style="text-align: center; align-items: center;">
                    <a href="${pageContext.request.contextPath}/tr/memTourList?memId=${memId}&tourId=${tourId}"
                        style="text-decoration: none; margin-right: 10px;">
                        <button id="btn">查看行程</button>
                    </a>
                    <button class="btn btn-primary btn-del delete1" data-bs-toggle="modal">刪除</button>
                </td>
            </tr>
		</c:forEach>
	</table>



<div style="text-align: center;">
	<%@ include file="../tour/page2.file"%>
</div>

	<script>
	// 使用JavaScript遍歷所有行程，設定日期格式
    var tourDescriptions = document.getElementsByClassName('tourDescription');
    var dateRanges = document.getElementsByClassName('dateRange');
    
    for (var i = 0; i < tourDescriptions.length; i++) {
//         var startDate = new Date(${list[i].startDate.time});
//         var endDate = new Date(${list[i].endDate.time});
//         var formattedStartDate = startDate.getFullYear() + "年" + (startDate.getMonth() + 1) + "月" + startDate.getDate() + "日";
//         var formattedEndDate = endDate.getFullYear() + "年" + (endDate.getMonth() + 1) + "月" + endDate.getDate() + "日";
//         dateRanges[i].innerHTML = formattedStartDate + "~" + formattedEndDate;
        
        //避免描述太多字
        var maxChars = 20; // 設定最大字數
        var description = tourDescriptions[i].innerHTML;
        if (description.length > maxChars) {
            tourDescriptions[i].innerHTML = description.substring(0, maxChars) + '...';
        }
        
    }
	
	$(".delete1").on("click", function () {
// 		console.log("刪除功能觸發")
        let tourId=$(this).closest("tr").find("[name='tourId']").val();
//         let tourId = $("#tourId").val();
//         console.log("memId=" + memId + ",tourId=" + tourId)
        var ok = window.confirm("確定要刪除嗎");
        if (ok) {
            console.log("進入刪除方法")
            $(this).closest('tr').remove();

//--------------------------------- 送出 Ajax 請求
			let url = "${pageContext.request.contextPath}/tr/deleteByFetch?tourId="+tourId;
            fetch(url)
                .then(function(response){
		    		return response.json();
		    	})
                .then(function (data) {
                    if (data != null) {
//                  	console.log(data);
                     alert("刪除成功")
//                      $(this).closest('tr').remove();
//                      window.location.reload();
                    }
                })
                .catch(function (error) {
                    console.error('刪除失敗:', error);
                });
        }
    })
	</script>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>