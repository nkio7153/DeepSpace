<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
  	<title>餐廳列表</title>
    <%-- include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"> -->
  </head>
	
	<style>
		.Restimg {
		    width: 100px;
		}
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
<!--         	<div class="container pt-3"> -->
	          <%-- 放入自己body裡的代碼--%>
	          <table id="myTable" class="hover">
	            <thead>
	              <tr>
	                <th>餐廳圖片</th>
	                <th>餐廳編號</th>
	                <th>餐廳名稱</th>
	                <th>餐廳電話</th>
	                <th>餐廳地址</th>
	                <th>餐廳類型</th>
	                <th>營業時間</th>
	                <th>上/下架</th>
	                <th>預設可訂位組數</th>
<!-- 	                <th>管理員ID</th> -->
	                <th>餐廳管理員帳號</th>
	                <th>修改</th>
	                <th>刪除</th>
	              </tr>
	            </thead>
	            <tbody>
	              <c:forEach var="rest" items="${restList}">
	                <tr>
	                  <td><img class="Restimg" src="${pageContext.request.contextPath}/static/images/rest/r_${rest.restId}.jpg" onerror="this.src='${pageContext.request.contextPath}/static/images/rest/404.jpg'"></td>
	                  <td>${rest.restId}</td>
	                  <td>${rest.restName}</td>
	                  <td>${rest.restTel}</td>
	                  <td>${rest.restAddress}</td>
	                  <td>${rest.restType}</td>
	                  <td>${rest.restOpen}</td>
	                  <c:choose>
					    <c:when test="${rest.restStatus == 0}">
					        <td>下架</td>
					    </c:when>
					    <c:when test="${rest.restStatus == 1}">
					        <td>上架</td>
					    </c:when>
					  </c:choose>
	                  <td>${rest.bookingLimit}</td>
<%-- 	                  <td>${rest.adminId}</td> --%>
	                  <td>${rest.adminVO.adminAcc}</td>
	                  
					<td>
						<form method="post" action="${pageContext.request.contextPath}/backend/Rest.do">
							<input type="submit" class="btn btn-secondary" value="修改">
							<input type="hidden" name="restId" value="${rest.restId}">
							<input type="hidden" name="action" value="getId_for_update">
						</form>
					</td>
					<td>
						<form method="post" action="${pageContext.request.contextPath}/backend/Rest.do">
							<input type="submit" class="btn btn-secondary" value="刪除">
							<input type="hidden" name="restId" value="${rest.restId}">
							<input type="hidden" name="action" value="delete">
						</form>
					</td>
	                </tr>
	              </c:forEach>
	              <!-- 其他數據行 -->
	            </tbody>
	          </table>
	          
<!--         	</div> -->
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
	            columnDefs: [
	                {
	                	 targets: 1,
	                     className: "dt-body-center",
	                },
	                {
	                	 targets: 10,
	                     className: "dt-body-center",
	                }
	            ],
	        });
	    });
    </script>
  </body>
</html>
