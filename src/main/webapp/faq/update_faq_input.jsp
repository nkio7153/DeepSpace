<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faq.model.FaqVO"%>
<%@ page import="com.depthspace.faq.service.*"%>
<%@ page import="com.depthspace.faq.controller.*"%>
<%@ page import="com.depthspace.faq.model.*"%>

<%
   FaqVO faqVO = (FaqVO) request.getAttribute("faqVO");
%>

<html>
<head>
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>FAQ資料修改 - update_faq_input.jsp</title>
	<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .container-fluid {
            padding-top: 20px;
        }
        .card {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .form-control {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
	<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
	<div class="container-fluid">
	  <div class="row">
		<div class="col-lg-2">
			<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
		</div>
		<div class="col-lg-10">
			<div class="card">
			   <div class="card-header">
				 <h3>FAQ員工資料修改 - update_faq_input.jsp</h3>
			   </div>
			   <div class="card-body">
				 <h4><a href="select_page.jsp" class="text-light">回首頁</a></h4>
				 <h3>資料修改:</h3>
				 <c:if test="${not empty errorMsgs}">
					<div class="alert alert-danger">
						<strong>請修正以下錯誤:</strong>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</div>
				 </c:if>
				 <FORM METHOD="post" ACTION="faq.do" name="form1">
				   <div class="form-group">
					   <label>SerialId:</label>
					   <input type="text" class="form-control" name="serialId" value="<%=faqVO.getSerialId()%>" readonly>
				   </div>
				   <div class="form-group">
					   <label>Faq編號:</label>
					   <input type="text" class="form-control" name="faqNo" value="<%=faqVO.getFaqNo()%>">
				   </div>
				   <div class="form-group">
					   <label>Faq名稱:</label>
					   <input type="text" class="form-control" name="faqName" value="<%=faqVO.getFaqName()%>">
				   </div>
				   <div class="form-group">
					   <label>Faq答案:</label>
					   <input type="text" class="form-control" name="faqAns" value="<%=faqVO.getFaqAns()%>">
				   </div>
				   <input type="hidden" name="action" value="update">
				   <button type="submit" class="btn btn-primary">送出修改</button>
				 </FORM>
			   </div>
			</div>
		</div>
	  </div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
