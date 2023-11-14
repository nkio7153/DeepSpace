<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faqtypes.model.model.FaqTypesVO"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的faqVO物件 (此為從資料庫取出的faqVO, 也可以是輸入格式有錯誤時的faqVO物件)
FaqTypesVO faqTypesVO = (FaqTypesVO) request.getAttribute("faqTypesVO");
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>FaqTypes資料修改 - update_faq_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
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
		 <h3>FaqTypes資料修改 - update_faq_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="faqTypes.do" name="form1">

<table>
	<tr>
		<td>FaqNo<font color=red><b>*</b></font></td>
		<td><%=faqTypesVO.getFaqNo()%></td>
	</tr>
	<tr>
		<td>問題類型:</td>
		<td><input type="TEXT" name="qTypes" value="<%=faqTypesVO.getqTypes()%>" size="45"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="faqNo" value="<%=faqTypesVO.getFaqNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

    </div>
  </div>
</div>

</body>
</html>
