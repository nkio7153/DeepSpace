<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
  	<title>預約設定</title>
    <%-- include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  </head>
	
	<style>
	</style>
  <body>
    <%--include header.jsp--%>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid my-0">
      <div class="row">
        <%-- 側邊欄--%>
        <div class="col-lg-2 g-3 mt-1">
          <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>

        <div class="col-lg-10 g-2 transparent rounded mt-1">
	          <%-- 放入自己body裡的代碼--%>
	          <table id="myTable" class="hover">
	            <thead>
	              <tr>
	                <th>餐廳編號</th>
					<th>日期</th>
					<th>營業狀態</th>
					<th>早上可預約組數</th>
					<th>中午可預約組數</th>
					<th>晚上可預約組數</th>
					<th>修改</th>
	              </tr>
	            </thead>
	            <tbody>
	              <c:forEach var="bookDate" items="${bookDate}">
					<tr>
						<td>${bookDate.restId}</td>
						<td>${bookDate.bookingDate}</td>
						<c:choose>
						    <c:when test="${bookDate.restOpen == 0}">
						        <td id="Open"><span class="text-danger">公休</span></td>
						    </c:when>
						    <c:when test="${bookDate.restOpen == 1}">
						        <td id="Open">營業</td>
						    </c:when>
					    </c:choose>
						<td id="morningNum">${bookDate.morningNum}</td>
						<td id="noonNum">${bookDate.noonNum}</td>
						<td id="eveningNum">${bookDate.eveningNum}</td>
						<td>
							<button type="button" class="btn btn-secondary btn-update">修改</button>
					    </td>
					</tr>
				</c:forEach>
	            </tbody>
	          </table>
	          
        </div>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
    <script>
	    $(function() {
	        $('#myTable').DataTable({
	            // 中文化
	            language: {
	                url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/zh-HANT.json',
	            },
	            "pageLength": 50,
	        });
	        
	        $(document).on("click", ".btn-secondary", function() {
	            // 找到按鈕所在的行
	            var row = $(this).closest("tr");

	            // 找到相應的td元素
	            var morningNumTd = row.find("#morningNum");
	            var noonNumTd = row.find("#noonNum");
	            var eveningNumTd = row.find("#eveningNum");
				var openTd = row.find("#Open");
	            // 獲取原始數據
	            var morningNum = morningNumTd.text().trim();
	            var noonNum = noonNumTd.text().trim();
	            var eveningNum = eveningNumTd.text().trim();
				var open = openTd.text().trim();
				
	            // 將td的內容替換為input元素
	            morningNumTd.html('<input type="number" class="form-control" id="morningNumInput" value="' + morningNum + '">');
	            noonNumTd.html('<input type="number" class="form-control" id="noonNumInput" value="' + noonNum + '">');
	            eveningNumTd.html('<input type="number" class="form-control" id="eveningNumInput" value="' + eveningNum + '">');
	            if (open == '營業'){
		            openhtml = '<select class="form-control" id="restOpenSelect">' +
					              '<option selected value="1">營業</option>' +
					              '<option value="0">公休</option>' +
		           			   '</select>';
	            } else if (open == '公休'){
	            	openhtml = '<select class="form-control" id="restOpenSelect">' +
					              '<option value="1">營業</option>' +
					              '<option selected value="0">公休</option>' +
			     			   '</select>';
	            }
	            openTd.html(openhtml);
	            // 創建一個新的保存按鈕
	            var saveButton = $('<button type="button" class="btn btn-success">保存</button>');

	            // 將新的保存按鈕添加到td中
	            row.find("td:last").html(saveButton);

	            // 綁定保存按鈕的點擊事件
	            saveButton.click(function() {
	                // 獲取輸入框的值
	                var newMorningNum = $("#morningNumInput").val();
	                var newNoonNum = $("#noonNumInput").val();
	                var newEveningNum = $("#eveningNumInput").val();
					var newOpen = $("#restOpenSelect").val();
	                // 將td的內容恢復為修改後的數值
	                morningNumTd.text(newMorningNum);
	                noonNumTd.text(newNoonNum);
	                eveningNumTd.text(newEveningNum);
	                if(newOpen == 1) {
		                openTd.text("營業");
	                } else if (newOpen == 0) {
		                openTd.text('公休');
		                openTd.addClass("text-danger");
	                }
	                
	                // update
	                var restId = row.find("td:eq(0)").text().trim();
   					var bookingDate = row.find("td:eq(1)").text().trim();
	                var data = {
	                	restId: restId,
	                	bookingDate: bookingDate,
				        restOpen: newOpen,
				        morningNum: newMorningNum,
				        noonNum: newNoonNum,
				        eveningNum: newEveningNum
				    };
// 	                console.log(data);

					$.ajax({
						type: 'post',
						data: data,
						url: '/DepthSpace/RestApi/doRestBookingDate?action=update',
						success: function(data){
							console.log(data);
						},
						error: function(data){
							console.log(data);
							return false;
						}
					})
	                
	                // 將按鈕恢復為修改按鈕
	                row.find("td:last").html('<button type="button" class="btn btn-secondary">修改</button>');
	            });
	        });
	        
	        
	        
	    });
    </script>
  </body>
</html>
