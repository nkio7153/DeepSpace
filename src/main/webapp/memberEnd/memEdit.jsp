<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編輯會員</title>
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
			<div class="container mt-5">
                    <h2 class="text-center">會員資訊修改：${memvo.memName}</h2>
                    <hr>
                    <form action="${pageContext.request.contextPath}/backmem/modify" id="form" method="post" class="row">
                    	<div class="col-md-2"></div>
                    	<div class="col-md-4">
                    		<input type="hidden" name="memId" id="memId" value="${memvo.memId}">
                    		<label for="memName"> 狀態 </label>
                    		 <select id="accStatus" name="accStatus">
                                <option value="1" ${memvo.accStatus == 1 ? 'selected' : ''}>正常使用中</option>
    							<option value="2" ${memvo.accStatus == 2 ? 'selected' : ''}>停權</option>
                            </select>
                    		<input type="hidden" name="accStatus" id="accStatus" value="${memvo.accStatus}">
                    		
                    	</div>
                    	<div class="col-md-4">測試2</div>
                    	<div class="col-md-2"></div>
                    </form>
            </div>
		</div>
	</div>
</div>
</body>
</html>