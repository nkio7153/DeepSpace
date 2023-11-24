<%@ page import="com.depthspace.admin.service.AdminService"%>
<%@ page import="com.depthspace.admin.model.AdminVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>成功登入 listOneAdmin.jsp</title>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid my-0">
        <div class="row">
            <div class="col-lg-2 g-3 mt-1">
                <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
            </div>
            <div class="col-lg-10 g-2 mt-1">
                <h1 class="text-center">已修改成功！</h1>
                <p class="text-center">您已成功登入，歡迎回來，${admin.adminName}！</p>
                <hr>
                <h1 class="text-center">管理員資訊</h1>
          <div class="container">
  <!-- 管理員編號 -->
  <div class="row mb-2">
    <div class="col-sm-3">
      <strong>管理員編號:</strong>
    </div>
    <div class="col-sm-9">
      ${admin.adminId}
    </div>
  </div>
  <!-- 帳號 -->
  <div class="row mb-2">
    <div class="col-sm-3">
      <strong>帳號:</strong>
    </div>
    <div class="col-sm-9">
      ${admin.adminAcc}
    </div>
  </div>
  <!-- 密碼 -->
  <div class="row mb-2">
    <div class="col-sm-3">
      <strong>密碼:</strong>
    </div>
    <div class="col-sm-9">
      ${admin.adminPwd}
    </div>
  </div>
  <!-- 管理員姓名 -->
  <div class="row mb-2">
    <div class="col-sm-3">
      <strong>管理員姓名:</strong>
    </div>
    <div class="col-sm-9">
      ${admin.adminName}
    </div>
  </div>
  <!-- 狀態 -->
  <div class="row mb-2">
    <div class="col-sm-3">
      <strong>狀態:</strong>
    </div>
    <div class="col-sm-9">
      ${admin.adminStatus}
    </div>
  </div>
</div>
                <form class="text-center" action="${pageContext.request.contextPath}/ad/edit?adminId=${admin.adminId}" method="post">
                    <input class="btn btn-primary" type="submit" value="修改管理員資料">
                    <input type="hidden" name="action" value="update">
                </form>
                <input class="btn btn-secondary" type="button" value="登出" onclick="index()">
                <script type="text/javascript">
	<script type="text/javascript">
	    function index() {
	        document.location.href = "${pageContext.request.contextPath}/ad/login.jsp";
	    }
	    function revise() {
	        document.location.href = "${pageContext.request.contextPath}/ad/revise.jsp";
	    }
	</script>

    </div>
  </div>
</div>

</body>
</html>
