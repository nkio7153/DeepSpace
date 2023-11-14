<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>會員資料查詢</title>
      <%--  include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
    <div class="row">
        <%--    側邊欄--%>
        <div class="col-lg-2 g-3 my-0">
            <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>
        <div class="col-lg-10 g-2 transparent rounded my-0">

  		<%--      放入自己body裡的代碼--%>

	    <div class="container" style="overflow-x: auto; overflow-y: auto;">
	        <h3 class="text-center mt-2">會員列表</h3>
	        <hr class="my-0">
	
<!-- 				<form class="form-inline" action="" method="post" onsubmit="searchMembers()"> -->
<!-- 				    <div class="row mt-4"> -->
<!-- 				        <div class="col-md-12"> -->
<!-- 				            <span for="memId" class="form-label mt-2">搜尋會員姓名：</span> -->
<!-- 				       	    	<input type="text" id="memName" name="memName" placeholder="請輸入姓名"> -->
<!-- 				            <button type="submit" class="btn btn-primary mx-1 mb-2" onclick="searchMembers()">查詢</button> -->
<!-- 				        </div> -->
<!-- 				    </div> -->
<!-- 				</form> -->
					<form id="searchForm" class="form-inline" action="" method="post">
					    <div class="row mt-4">
					        <div class="col-md-12">
					            <span for="memId" class="form-label mt-2">搜尋會員姓名：</span>
					            <input type="text" id="memName" name="memName" placeholder="請輸入姓名">
					            <button type="submit" class="btn btn-primary mx-1 mb-2">查詢</button>
					        </div>
					    </div>
					</form>
	
	        <!-- 會員列表 -->
<!-- 	        <h2 class="mt-4">會員列表</h2> -->
	        <table id="memberTable" class="table table-bordered table-hover table-striped" style="white-space: nowrap;">
	            <thead>
	                <tr class="text-center">
	                    <th>會員編號</th>
	                    <th>帳號</th>
	                    <th>會員姓名</th>
	                    <th>身分證字號</th>
	                    <th>生日</th>
	                    <th>性別</th>
	                    <th>電子郵件</th>
	                    <th>手機電話</th>
	                    <th>地址</th>
	                    <th>狀態</th>
	                    <th>會員點數</th>
	                    <th>會員狀態修改</th>
	                </tr>
	            </thead>
	            <tbody>
				<!-- 迴圈取出所有會員資料-->
	                <c:forEach items="${list}" var="mem">
		                <tr align="center">
		                    <td>${mem.memId}</td>
		                    <td>${mem.memAcc}</td>
		                    <td>${mem.memName}</td>
		                    <td>${mem.memIdentity}</td>
		                    <td><fmt:formatDate value="${mem.memBth}" pattern="yyyy-MM-dd" /></td>
		                    <td>
		                    	<c:choose>
					                <c:when test="${mem.memSex == 1}">
					                    男
					                </c:when>
					                <c:when test="${mem.memSex == 2}">
					                    女
					                </c:when>
					                 <c:otherwise>
					                    未知狀態
					                </c:otherwise>
					            </c:choose>
		                    </td>
		                    <td>${mem.memEmail}</td>
		                    <td>${mem.memTel}</td>
		                    <td>${mem.memAdd}</td>
		                    <td>
		                    	<c:choose>
					                <c:when test="${mem.accStatus == 1}">
					                    啟用
					                </c:when>
					                <c:when test="${mem.accStatus == 2}">
					                    停權
					                </c:when>
					                 <c:otherwise>
					                    未知狀態
					                </c:otherwise>
					            </c:choose>
                			</td>
		                    <td>
		                    	<c:choose>
			                        <c:when test="${not empty mem.memPoint}">
			                            ${mem.memPoint}
			                        </c:when>
			                        <c:otherwise>
			                            0
			                        </c:otherwise>
			                    </c:choose>
		                    </td>
		                   <td>
							    <button class="btn btn-primary" onclick="changeStatus('啟用', ${mem.memId})" data-memid="${mem.memId}">啟用</button>
							    <button class="btn btn-danger" onclick="changeStatus('停權', ${mem.memId})" data-memid="${mem.memId}">停權</button>
							</td>

		                </tr>
	                </c:forEach>
	                
	            </tbody>
	        </table>
	    </div>
     </div>
    </div>
</div>

<script>

	
// 	會員更改權限狀態
    function changeStatus(action, memId) {
        var status = confirm('確定要' + action + '嗎？');

        if (status) {
        	updateStatus(action, memId);
        } else {
        	console.log('操作已取消');
        }
    }
    function updateStatus(action, memId) {
    	let url = "${pageContext.request.contextPath}/backmem/updateStatus?memId="+memId + "&accStatus=" + action;
    	
        fetch(url)
                .then(function(response){
                  return response.json();
                })
                .then(function(data){
//                 	console.log(data); //啟用
                	var row = document.querySelector('[data-memid="' + memId + '"]').closest('tr');
                    updateTableRow(row, data);
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
                
    }
    //更新權限的欄位
    function updateTableRow(row, newData) {
        // 根據實際的資料結構更新表格行的內容
        row.cells[9].textContent = newData; //accStatus 是表格中的第 10 個欄位
    }
    
$(document).ready(function () {
   	 // 模糊查詢(搜尋會員姓名)
   	  $('#searchForm').submit(function (event) {
             // 阻止表單預設提交行為
             event.preventDefault();

             // 調用搜尋函數
             searchMembers();
         });
   	 
	  function searchMembers() {
	        var memName = $("#memName").val();
	//         console.log("memName= " + memName);
			let url = "${pageContext.request.contextPath}/backmem/searchMembers?memName="+memName;
			    	
			        fetch(url)
			                .then(function(response){
			                  return response.json();
			                })
			                .then(function(data){
	// 		                	console.log("搜尋回來的資料：" + data); 
								updateTable(data);
			                })
			                .catch(error => {
			                    console.error('There has been a problem with your fetch operation:', error);
			                });
		}
	    
    function updateTable(data) {
    	var tableBody = $("#memberTable tbody");
    	tableBody.empty(); // 清空表格內容
    	$.each(data, function (index, mem) {
            // 在表格末尾插入新行
            var newRow = tableBody.append("<tr></tr>").children('tr:last');
         
            console.log("memBth:", mem);
            
            // 在新行中插入單元格並設置內容
            newRow.append("<td>" + mem.memId + "</td>");
            newRow.append("<td>" + mem.memAcc + "</td>");
            newRow.append("<td>" + mem.memName + "</td>");
            newRow.append("<td>" + mem.memIdentity + "</td>");
            newRow.append("<td>" + mem.memBth + "</td>");
            newRow.append("<td>" + ((mem.memSex == 1) ? '男' : '女') + "</td>");
            newRow.append("<td>" + mem.memEmail + "</td>");
            newRow.append("<td>" + mem.memTel + "</td>");
            newRow.append("<td>" + mem.memAdd + "</td>");
            newRow.append("<td>" + ((mem.accStatus == 1) ? '啟用' : '停權') + "</td>");
            newRow.append("<td>" + (mem.memPoint !== null ? mem.memPoint : 0) + "</td>");
            newRow.append("<td>" +
            	    "<button style='margin: 4px;' class=\"btn btn-primary\" onclick=\"changeStatus('啟用', " + mem.memId + ")\" data-memid=\"" + mem.memId + "\">啟用</button>" +
            	    "<button  class=\"btn btn-danger\" onclick=\"changeStatus('停權', " + mem.memId + ")\" data-memid=\"" + mem.memId + "\">停權</button>" +
            	    "</td>");

    	});
    }

});
</script>

    <!-- 引入Bootstrap JavaScript，如有需要 -->
<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->

</body>
</html>
