<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理員資料查詢</title>
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

	    <div class="container">
	        <h3 class="text-center mt-2">管理員列表</h3>
	        <hr class="my-0">
	
					<form id="searchForm" class="form-inline" action="" method="post">
					    <div class="row mt-4">
					        <div class="col-md-12">
					            <span for="adminId" class="form-label mt-2">搜尋管理員姓名：</span>
					            <input type="text" id="adminName" name="adminName" placeholder="請輸入姓名">
					            <button type="submit" class="btn btn-primary mx-1 mb-2">查詢</button>
					        </div>
					    </div>
					</form>
	
	        <!-- 管理員列表 -->
<!-- 	        <h2 class="mt-4">管理員列表</h2> -->
	        <table id="adminTable" class="table table-bordered table-hover table-striped" style="white-space: nowrap;">
	            <thead>
	                <tr class="text-center">
	                    <th>管理員編號</th>
	                    <th>帳號</th>
	                    <th>管理員姓名</th>
	                    <th>狀態</th>
	                    <th>管理員狀態修改</th>
	                    <th>權限狀態</th>
	                </tr>
	            </thead>
	            <tbody>
				<!-- 迴圈取出所有管理員資料-->
	                <c:forEach items="${list}" var="admin">
		                <tr align="center">
		                    <td>${admin.adminId}</td>
		                    <td>${admin.adminAcc}</td>
		                    <td>${admin.adminName}</td>
		                    <td>
		                    	<c:choose>
					                <c:when test="${admin.adminStatus == 1}">
					                    啟用
					                </c:when>
					                <c:when test="${admin.adminStatus == 2}">
					                    停權
					                </c:when>
					                 <c:otherwise>
					                    未知狀態
					                </c:otherwise>
					            </c:choose>
                			</td>
		                    <td>
							    <button class="btn btn-primary" onclick="changeStatus('啟用', ${admin.adminId})" data-adminid="${admin.adminId}">啟用</button>
							    <button class="btn btn-danger" onclick="changeStatus('停權', ${admin.adminId})" data-adminid="${admin.adminId}">停權</button>
							
							 	<button class="btn btn-secondary" onclick="location.href='<%=request.getContextPath()%>/admin/revise.jsp?adminId=${admin.adminId}&adminAcc=${admin.adminAcc}'">修改</button>
							</td>
		                   
		                    <td>
		                    	<c:choose>
					                <c:when test="${admin.adminFuncName == 2}">
					                    總管理員
					                </c:when>
					                <c:when test="${admin.adminFuncName == 1}">
					                    餐廳管理員
					                </c:when>
					                 <c:otherwise>
					                    未知狀態
					                </c:otherwise>
					            </c:choose>
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

	
// 	管理員更改權限狀態
    function changeStatus(action, adminId) {
        var status = confirm('確定要' + action + '嗎？');

        if (status) {
        	updateStatus(action, adminId);
        } else {
        	console.log('操作已取消');
        }
    }
    function updateStatus(action, adminId) {
    	let url = "${pageContext.request.contextPath}/backadmin/updateStatus?adminId="+adminId + "&adminStatus=" + action;
    	
        fetch(url)
                .then(function(response){
                  return response.json();
                })
                .then(function(data){
                	console.log(data); //啟用
                	var row = document.querySelector('[data-adminid="' + adminId + '"]').closest('tr');
                    updateTableRow(row, data);
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
                
    }
    //更新權限的欄位
    function updateTableRow(row, newData) {
        // 根據實際的資料結構更新表格行的內容
        row.cells[3].textContent = newData; //adminStatus 是表格中的第 5 個欄位
    }
    
$(document).ready(function () {
   	 // 模糊查詢(搜尋會員姓名)
   	  $('#searchForm').submit(function (event) {
             // 阻止表單預設提交行為
             event.preventDefault();

             // 調用搜尋函數
             searchAdmins();
         });
   	 
	  function searchAdmins() {
	        var adminName = $("#adminName").val();
	//         console.log("memName= " + memName);
			let url = "${pageContext.request.contextPath}/backadmin/searchAdmins?adminName="+adminName;
			    	
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
    	var tableBody = $("#adminTable tbody");
    	tableBody.empty(); // 清空表格內容
    	$.each(data, function (index, admin) {
            // 在表格末尾插入新行
            var newRow = tableBody.append("<tr></tr>").children('tr:last');
         
            console.log("adminBth:", admin);
            
            // 在新行中插入單元格並設置內容
            newRow.append("<td>" + admin.adminId + "</td>");
            newRow.append("<td>" + admin.adminAcc + "</td>");
            newRow.append("<td>" + admin.adminName + "</td>");
            newRow.append("<td>" + ((admin.adminStatus == 1) ? '啟用' : '停權') + "</td>");
            newRow.append("<td>" +
            	    "<button style='margin: 4px;' class=\"btn btn-primary\" onclick=\"changeStatus('啟用', " + admin.adminId + ")\" data-adminid=\"" + admin.adminId + "\">啟用</button>" +
            	    "<button  class=\"btn btn-danger\" onclick=\"changeStatus('停權', " + admin.adminId + ")\" data-memid=\"" + admin.adminId + "\">停權</button>" +
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
