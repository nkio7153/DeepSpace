<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>會員權限管理</title>
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
	        <h1 class="mt-5">會員權限管理</h1>
	
	        <!-- 依帳號查詢會員 -->
	        <h2 class="mt-4">查詢會員</h2>
	        <form class="form-inline" action="your_search_action.jsp" method="post">
	            <div class="form-group mb-2">
	                <label for="memId">會員編號：</label>
	            </div>
	            <div class="form-group mx-sm-3 mb-2">
	                <input type="text" class="form-control" id="memId" name="memId" placeholder="請輸入帳號">
	            </div>
	            <button type="submit" class="btn btn-primary mb-2">查詢</button>
	        </form>
	
	        <!-- 會員列表 -->
	        <h2 class="mt-4">會員列表</h2>
	        <table class="table table-bordered">
	            <thead>
	                <tr>
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
	                    <th>操作</th>
	                </tr>
	            </thead>
	            <tbody>
	                <!-- 在這裡使用JSP語法生成會員列表 -->
	                <c:forEach items="${list}" var="list">
		                <tr>
		                    <td>${list.memId}</td>
		                    <td>user1</td>
		                    <td>王小明</td>
		                    <td>A123456789</td>
		                    <td>1990-01-01</td>
		                    <td>男</td>
		                    <td>user1@example.com</td>
		                    <td>0912345678</td>
		                    <td>台北市</td>
		                    <td>正常</td>
		                    <td>100</td>
		                    <td>
		                        <!-- 編輯按鈕 -->
		                        <button class="btn btn-primary">編輯</button>
		                    </td>
		                </tr>
	                </c:forEach>
	                
	            </tbody>
	        </table>
	    </div>
     </div>
    </div>
</div>

    <!-- 引入Bootstrap JavaScript，如有需要 -->
<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->

</body>
</html>
