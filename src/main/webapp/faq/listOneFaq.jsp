<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.depthspace.faq.model.model.FaqVO"%>
<%@ page import="com.depthspace.faq.model.service.*"%>
<%@ page import="com.depthspace.faq.model.controller.*"%>
<%@ page import="com.depthspace.faq.model.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	FaqVO faqVO = (FaqVO) request.getAttribute("faqVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>Faq資料 - listOneFaq.jsp</title>

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
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
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

</body>
</html>