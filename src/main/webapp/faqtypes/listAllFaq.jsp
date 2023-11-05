<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.FaqTypesVO"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
FaqTypesService faqSvc = new FaqTypesService();
List<FaqTypesVO> list = faqSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>FAQTypes</title>
<jsp:include page="../indexpage/head.jsp" />

<style>
 #table-1 {
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center; /* 水平置中 */
    }
table#table-1 {
	background-color: #ffcccc;
	border: 2px solid black;
	text-align: center;
	margin: 0 auto;
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
body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
}

#container {
    width: 80%;
    margin: 0 auto;
    background-color: white;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

table {
    width: 100%;
    background-color: white;
    margin-top: 20px;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 15px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

h3 {
    color: #333;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
</style>


</head>
<body bgcolor='white'>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />


	<table id="table-1">
		<tr>
			<td>
				<h3>FAQTypes - 首頁</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/bear.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>常見問題類型編號</th>
			<th>問題類型</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="faqTypesVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${faqTypesVO.faqNo}</td>
				<td>${faqTypesVO.qTypes}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/faqtypes/faq.do1"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="faqNo" value="${faqTypesVO.faqNo}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/faqtypes/faq.do1"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="faqNo" value="${faqTypesVO.faqNo}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>