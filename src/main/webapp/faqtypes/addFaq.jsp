<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
FaqTypesVO faqTypesVO = (FaqTypesVO) request.getAttribute("faqTypesVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>FaqTypes資料新增 - addFaq.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>FaqTypes資料新增 - addFaq.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/wu.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>FaqTypes資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="faq.do1" name="form1">
<table>
	
	<tr>
		<td>問題類型:</td>
		<td><input type="TEXT" name="qTypes"   value="<%= (faqTypesVO==null)? "1" : faqTypesVO.getqTypes()%>" size="45"/></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<link rel="stylesheet" type="text/css"
 href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
 src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
 width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
 height: 151px; /* height:  151px; */
}
</style>

        

</html>