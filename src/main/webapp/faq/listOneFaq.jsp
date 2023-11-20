<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faq.model.FaqVO"%>
<%@ page import="com.depthspace.faq.service.*"%>
<%@ page import="com.depthspace.faq.controller.*"%>
<%@ page import="com.depthspace.faq.model.*"%>

<%
	FaqVO faqVO = (FaqVO) request.getAttribute("faqVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
    <title>常見問題 - listOneFaq</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
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
      <table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneFaq.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>流水號</th>
		<th>FAQ編號</th>
		<th>FAQ名稱</th>
		<th>FAQ答案</th>
	</tr>
	<tr>
		<td>${faqVO.serialId}</td>
		<td>${faqVO.faqNo}</td>
		<td>${faqVO.faqName}</td>
		<td>${faqVO.faqAns}</td>
	</tr>
</table>

    </div>
  </div>
</div>

</body>
</html>
