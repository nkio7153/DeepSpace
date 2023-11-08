<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.admin.model.*"%>
<%@ page import="com.depthspace.admin.model.service.*"%>

<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
%>

<%
    Object adminAccount = session.getAttribute("adminAccount");                    // 從 session內取出 (key) adminVO的值
    if (adminAccount == null) {
    	System.out.println("再次確認清除");                                          // 如為 null, 代表此user未登入過 , 才做以下工作
    	session.setAttribute("location", request.getRequestURI());       		  //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁
        response.sendRedirect(request.getContextPath()+"/admin/adminLogin.jsp");  //*工作2 : 請該user去登入網頁(login.html) , 進行登入
     	return;
    }else{
    	System.out.println("再次確認沒有清除");
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>員工管理系統</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sweetalert2.css">
<style>
body {
            background-image: url('<%=request.getContextPath()%>/img/desktop.jpg');
            background-size: cover;
            background-attachment: fixed; /* 可选，固定背景图片 */
            background-repeat: no-repeat;
        }
        
</style>

</head>
<body>
	<nav class="navbar custom-bg-color">
  <div class="container-fluid">
    <a class="navbar-brand" href="./backendMain.jsp">
      <img src="<%=request.getContextPath()%>/img/backpack2-fill.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
      後臺管理系統
    </a>
    <div class="ms-auto">
      <form method="POST" action="./admin.do">
      	<button class="btn btn-danger">登出</button>
      	<input type="hidden" name="action" value="backendlogout">
      </form>
    </div>
  </div>
</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2 g-3">
			<!--左邊-->
				<div class="accordion" id="accordionExample">
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				        員工管理
				      </button>
				    </h2>
				    <div id="collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
				      	<strong><a href="adminSystem.jsp" class="list-group-item list-group-item-action" onclick="return checkAdminStat();">員工列表</a></strong>
				      </div>
<%--				      <div class="accordion-body">--%>
<%--				      	<strong><a href="#" class="list-group-item list-group-item-action">權限管理</a></strong>--%>
<%--				      </div>--%>
				    </div>
				  </div>
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				        商品管理
				      </button>
				    </h2>
				    <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
						<strong><a href="#" class="list-group-item list-group-item-action">填寫功能名稱</a></strong>
				      </div>
				    </div>
				  </div>
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				        訂單管理
				      </button>
				    </h2>
				    <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
						<strong><a href="#" class="list-group-item list-group-item-action">填寫功能名稱</a></strong>
				      </div>
				    </div>
				  </div>
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
				        客服管理
				      </button>
				    </h2>
				    <div id="collapse4" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
						<strong><a href="<%=request.getContextPath()%>/customer/backendCustomer.jsp" class="list-group-item list-group-item-action">即時客服</a></strong>
				      </div>
				    </div>
				  </div>
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse5" aria-expanded="false" aria-controls="collapse5">
				        寵物領養管理
				      </button>
				    </h2>
				    <div id="collapse5" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
						<strong><a href="#" class="list-group-item list-group-item-action">填寫功能名稱</a></strong>
				      </div>
				    </div>
				  </div>
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse6" aria-expanded="false" aria-controls="collapse6">
				        會員資料管理
				      </button>
				    </h2>
				    <div id="collapse6" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
						<strong><a href="#" class="list-group-item list-group-item-action">填寫功能名稱</a></strong>
				      </div>
				    </div>
				  </div>
				  <div class="accordion-item">
				    <h2 class="accordion-header">
				      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse7" aria-expanded="false" aria-controls="collapse7">
				        公告資訊管理
				      </button>
				    </h2>
				    <div id="collapse7" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
				      <div class="accordion-body">
						<strong><a href="#" class="list-group-item list-group-item-action">填寫功能名稱</a></strong>
				      </div>
				    </div>
				  </div>
				</div>
			</div>
			<!--左邊-->
			
			<div class="col-lg-10 g-3">
			<!--右邊-->
			
				
				
				
			<!--右邊-->
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert2.all.min.js"></script>

	<script>
		function checkAdminStat() {
			let adminStat = <%= session.getAttribute("adminStat") %>;
			if (adminStat === 1) {
				Swal.fire({
					icon: 'error',
					title: '權限不足!!',
					text: '請聯繫系統管理員',
					showConfirmButton: false,
					timer: 2500
				})
				return false;
			}
			return true;
		}
	</script>

</body>
</html>
