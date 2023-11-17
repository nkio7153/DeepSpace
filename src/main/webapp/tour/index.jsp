<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	width: 80%;
	border-collapse: collapse;
	border: 1px solid #ccc;
	margin: 10px auto; /* 使用auto來置中 */
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

#btn {
	background-color: lightblue;
	color: #fff;
	border: none;
	border-radius: 10px;
	padding: 10px 20px;
	cursor: pointer;
}

#btn:hover {
	background-color: #008CBA;
}
#btn_back {
	margin-left: 50px;
	font-size: 20px;
	color: #fff;
	background-color: #008CBA;
	border: none;
	padding: 10px;
	cursor: pointer;
	border-radius: 10px;
}
#btn_back:hover {
	background-color: lightblue;
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
</style>
</head>
<body>
	<jsp:include page="../indexpage/header.jsp" />
	<jsp:include page="../indexpage/headpic.jsp" />

	<h1 style="font-size: 24px; color: #333; text-align: center;">查看我的行程列表</h1>
	
	
	<input type="button" value="返回" onclick="history.back()" id="btn_back">
<!-- 	<input type="button" value="返回" onclick="goBack()" id="btn_back"> -->
	
	<!-- <form method="" action=""> -->
	<table>
		<tr>
			<th style="text-align: center;">行程名稱</th>
			<th style="text-align: center;">查看行程</th>
		</tr>
		
				
		<c:forEach var="tour" items="${list}">
			<tr>
<%-- 			<input type="text" name="tourId" value="${tour.tourId}" readonly"> --%>
				<td name="tourName" style="text-align: center;">${tour.tourName}</td>
				<td align="center">
					<c:set var="memId" value="${tour.memId}" />
					<c:set var="tourId" value="${tour.tourId}" />
					<a
					href="${pageContext.request.contextPath}/tr/memTourList?memId=${memId}&tourId=${tourId}"
					style="text-decoration: none; display: block; text-align: center;">
						<button id="btn">查看行程</button>
				</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<script>
	
	function goBack() {
	    // 檢查前一頁的URL是否包含 success.jsp
	    if (document.referrer && document.referrer.indexOf("success.jsp") !== -1) {
	        // 如果前一頁是 success.jsp，就使用 history.back()
	        history.back();
	    } else {
	        // 否則導向到 success.jsp
	        window.location.href = "success.jsp";
	    }
	}
	
    let tourDes = [];
//     處理行程明細
    function showProDetail() {
        let tr =$(event.target).closest('tr');
        let tourId=tr.find('td[name="tourId"]').text();
        gettourDetail(tourId);

    }
    
    function gettourDetail(proId) {
    	let url ="${pageContext.request.contextPath}/tr/showDetail?tourId=" + tourId;
    	fetch(url)
    		.then(function (response){
    			return response.json()
    		})
    		.then(function (data){
    			console.log(data)
    			tourDes = data;
    			shoeData();
    		})
    		 .catch(function(error){
              console.log(error);
            })
    
    function shoeData(){
    	console.log("showData方法被執行了")
    	for(let i = 0; i < tourDes.length; i++) {
    		console.log(tourDes[i].tourName);
    	}
    	 console.log("遍歷完畢")
    	 let html="";
    	for(let i=0; i<tourDes.length; i++){
    	   html += '<li class="list-group-item"><span>' + proDes[i].ticketName + '</span><span class="text-danger">(' + proDes[i].discount + '折)</span></li>';
    	    }
    }
    	
    	
    }
    
    
    
    
    </script>
	<!-- </form> -->
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>