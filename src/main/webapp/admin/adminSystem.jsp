<%@page import="com.depthspace.admin.model.service.AdminService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.admin.model.model.*"%>
<%@ page import="com.depthspace.admin.model.service.*"%>

<%
    Object adminAccount = session.getAttribute("adminAccount");                  // 從 session內取出 (key) adminVO的值
    if (adminAccount == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
    	session.setAttribute("location", request.getRequestURI());       		//*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁
        response.sendRedirect(request.getContextPath()+"/admin/adminLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
     	return;
    }
%>

<%
AdminService adminSvc = new AdminService();
List<AdminVO> list = adminSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>員工管理系統</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<style>
body {
            background-image: url('../img/desktop.jpg');
            background-size: cover;
            background-attachment: fixed; /* 可选，固定背景图片 */
            background-repeat: no-repeat;
        }
        
 .custom-bg-color { 
/* 	background-color: #EDEEF0; /* 自定义颜色代码 */ */
	} 



th {
    text-align: center;
}

.error-message {
	color: red; /* 設置文字顏色為紅色，你可以根據需要進行調整 */
	margin-top: 5px; /* 設置上邊距，控制它與<input>元素之間的距離 */
	margin-left: 12px;
}
</style>

</head>
<body>
	<nav class="navbar custom-bg-color">
  <div class="container-fluid">
    <a class="navbar-brand" href="backendMain.jsp">
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
				      	<strong><a href="adminSystem.jsp" class="list-group-item list-group-item-action">員工列表</a></strong>
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
						<strong><a href="<%=request.getContextPath()%>/customer/backendCustomer.jsp" class="list-group-item list-group-item-action">填寫功能名稱</a></strong>
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
			<div class="card">
					<div class="card-header">
						員工列表
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-box-fill" viewBox="0 0 16 16"
							style="float: right;">
                            <path fill-rule="evenodd"
								d="M15.528 2.973a.75.75 0 0 1 .472.696v8.662a.75.75 0 0 1-.472.696l-7.25 2.9a.75.75 0 0 1-.557 0l-7.25-2.9A.75.75 0 0 1 0 12.331V3.669a.75.75 0 0 1 .471-.696L7.443.184l.004-.001.274-.11a.75.75 0 0 1 .558 0l.274.11.004.001 6.971 2.789Zm-1.374.527L8 5.962 1.846 3.5 1 3.839v.4l6.5 2.6v7.922l.5.2.5-.2V6.84l6.5-2.6v-.4l-.846-.339Z" />
                        </svg>
					</div>
					<div class="card-body">
						<div class="row">
							<form method="post" action="admin.do" class="col-md-3">
								<div>
									<div class="input-group">
										<input type="text" class="form-control" placeholder="請輸入員工編號"
											name="adminNo" value="${param.adminNo}"
											aria-label="Recipient's username"
											aria-describedby="button-addon2"> <input
											type="hidden" name="action" value="getOne_For_Display">
										<button class="btn btn-outline-secondary" type="submit"
											id="button-addon2">搜尋</button>
									</div>
									<div class="error-message">${errorMsgs.adminNo}</div>
								</div>
							</form>

							<jsp:useBean id="adminSel" scope="page"
								class="com.depthspace.admin.model.service.AdminService" />

							<form method="post" action="admin.do" id="adminNoSel"
								class="dropdown col-md-2 ">
								<div class="dropdown col-md-2">
									<div class="dropdown">
										<button class="btn btn-secondary dropdown-toggle"
											type="submit" data-bs-toggle="dropdown" aria-expanded="false">
											選擇員工編號</button>
										<ul class="dropdown-menu" id="adminNoMenu">
											<c:forEach var="adminVO" items="${adminSel.all}">
												<li><a class="dropdown-item" href="#"
													data-admin-no="${adminVO.adminNo}"> ${adminVO.adminNo}
												</a></li>
											</c:forEach>
										</ul>
										<input type="hidden" name="action" value="getOne_For_Display">
									</div>
								</div>
							</form>

							<form method="post" action="admin.do" id="adminName"
								class="dropdown col-md-3 offset--2" style="margin-left: -35px;">
								<div class="dropdown col-md-3">
									<div class="dropdown">
										<button class="btn btn-secondary dropdown-toggle fixed-button"
											type="submit" data-bs-toggle="dropdown" aria-expanded="false">
											選擇員工姓名</button>
										<ul class="dropdown-menu" id="adminNameMenu">
											<c:forEach var="adminVO" items="${adminSel.all}">
												<li><a class="dropdown-item" href="#"
													data-admin-no="${adminVO.adminNo}">
														${adminVO.adminName} </a></li>
											</c:forEach>
										</ul>
									</div>
									<input type="hidden" name="action" value="getOne_For_Display">
								</div>
							</form>
							<div class="col-md-4 d-flex justify-content-end"
								style="margin-left: 25px;">
								<button class="btn btn-primary" id="navigateButton">新增</button>
							</div>
						</div>
						<!--./row-->
						<div>
							<table class="table table-hover table-striped">
								<thead>
									<tr>
										<th>編號</th>
										<th>帳號</th>
										<th>密碼</th>
										<th>姓名</th>
										<th>信箱</th>
										<th>電話</th>
										<th>到職日</th>
										<th>權限</th>
										<th >照片</th>
									</tr>
								</thead>
								<tbody>
									<%@ include file="page1.file" %> 
									<c:forEach var="adminVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
											<th>${adminVO.adminNo}</th>
											<th>${adminVO.adminAccount}</th>
											<th>${adminVO.adminPassword}</th>
											<th>${adminVO.adminName}</th>
											<th>${adminVO.adminEmail}</th>
											<th>${adminVO.adminPhone}</th>
											<th>${adminVO.createDate}</th>
											<th>
									            <c:choose>
									                <c:when test="${adminVO.adminStat == 1}">一般</c:when>
									                <c:when test="${adminVO.adminStat == 2}">管理員</c:when>
									                <c:when test="${adminVO.adminStat == 3}">停權</c:when>
<%--									                <c:otherwise>離職</c:otherwise>--%>
									            </c:choose>
									        </th>
											<th>
												<img src="${pageContext.request.contextPath}/ReadIMG?id=${adminVO.adminNo}" style="max-width: 100px; height: 50px;">
											</th>
							
											<th class="text-center">
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/admin/admin.do">
													<button class="btn btn-success" type="submit">修改</button>
													<input type="hidden" name="adminNo"
														value="${adminVO.adminNo}"> <input type="hidden"
														name="action" value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/admin/admin.do">
													<button class="btn btn-danger" type="submit">刪除</button>
													<input type="hidden" name="adminNo"
														value="${adminVO.adminNo}"> <input type="hidden"
														name="action" value="delete">
												</FORM>
											</td>
											</th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<%@ include file="page2.file" %>

<!-- 							<nav aria-label="Page navigation example" class="text-center"> -->
<!-- 								<ul class="pagination"> -->
<!-- 									<li class="page-item" id="previousPage"><a -->
<!-- 										class="page-link" href="#" aria-label="Previous"><span -->
<!-- 											aria-hidden="true">&laquo;</span></a></li> -->
<!-- 									<li class="page-item" id="page1"><a class="page-link" -->
<!-- 										href="#">1</a></li> -->
<!-- 									<li class="page-item" id="page2"><a class="page-link" -->
<!-- 										href="#">2</a></li> -->
<!-- 									<li class="page-item" id="page3"><a class="page-link" -->
<!-- 										href="#">3</a></li> -->
<!-- 									<li class="page-item" id="nextPage"><a class="page-link" -->
<!-- 										href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li> -->
<!-- 								</ul> -->
<!-- 							</nav> -->

						</div>
					</div>
				</div>
			</div>
			<!--右邊-->
		</div>
	</div>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			// 監聽導航按鈕的點擊事件
			let navigateButton = document.getElementById('navigateButton');
			navigateButton.addEventListener('click', function() {
				window.location.href = 'addadminNew.jsp';
			});

			//監聽員工編號下拉菜单的點擊事件
			let adminNameDropdown = document.getElementById("adminName");
			let adminNameInput = document
					.querySelector('input[name="adminNo"]');
			let formName = document.getElementById("adminNameMenu");

			adminNameDropdown.addEventListener("click", function(event) {
				if (event.target.hasAttribute("data-admin-no")) {
					const selectedAdminNo = event.target
							.getAttribute("data-admin-no");
					adminNameInput.value = selectedAdminNo;
					formName.submit(); // 提交表单
				}
			});

			//監聽員工編號下拉菜单的點擊事件
			let adminNoDropdown = document.getElementById("adminNoSel");
			let adminNoInput = document.querySelector('input[name="adminNo"]');
			let formNo = document.getElementById("adminNoMenu");

			adminNoDropdown.addEventListener("click", function(event) {
				if (event.target.hasAttribute("data-admin-no")) {
					const selectedAdminNo = event.target
							.getAttribute("data-admin-no");
					adminNoInput.value = selectedAdminNo;
					formNo.submit(); // 提交表单
				}
			});
		});
	</script>


	<script src="<%=request.getContextPath()%>/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>
