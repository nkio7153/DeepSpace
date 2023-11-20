<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faq.model.FaqVO"%>
<%@ page import="com.depthspace.faq.service.*"%>
<%@ page import="com.depthspace.faq.controller.*"%>
<%@ page import="com.depthspace.faq.model.*"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
	FaqVO faqVO = (FaqVO) request.getAttribute("faqVO");
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="UTF-8">
    <title>Faq資料新增 - addFaq.jsp</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>
<body bgcolor='white'>
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
      <div class="card">
                <div class="card-header">
		 <h3>常見問題資料新增 - addFaq.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回常見問題的首頁</a></h4>
 				</div>
      <div class="card-body">

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
<div class="alert alert-danger" role="alert">
	<strong>請修正以下錯誤:</strong>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</div>
</c:if>

<!-- 表單部分 -->
<FORM METHOD="post" ACTION="faq.do" name="form1">
<div class="form-group">
                            <label for="faqNo">常見問題編號:</label>
                            <input type="text" class="form-control" name="faqNo" value="<%= (faqVO==null)? "1" : faqVO.getFaqNo()%>" size="45">
                        </div>
                        <div class="form-group">
                            <label for="faqName">常見問題名稱:</label>
                            <input type="text" class="form-control" name="faqName" value="<%= (faqVO==null)? "愛莉" : faqVO.getFaqName()%>" size="45">
                        </div>
                        <div class="form-group">
                            <label for="faqAns">常見問題答案:</label>
                            <input type="text" class="form-control" name="faqAns" value="<%= (faqVO==null)? "殺殺" : faqVO.getFaqName()%>" size="45">
                        </div>
                        <input type="hidden" name="action" value="insert">
                        <button type="submit" class="btn btn-primary">送出新增</button>


		<!-- Bootstrap JS and Popper.js (for Bootstrap 4) -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</body>
</html>
