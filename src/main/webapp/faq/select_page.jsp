<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Faq: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Faq: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Faq: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFaq.jsp'>List</a> all Faqs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="Faq.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="serialId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="faqSvc" scope="page" class="com.depthspace.faq.model.service.FaqService" />
   
  <li>
     <FORM METHOD="post" ACTION="faq.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="serialId">
         <c:forEach var="faqVO" items="${faqSvc.all}" > 
          <option value="${faqVO.serialId}">${faqVO.serialId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="faq.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="faqNo">
         <c:forEach var="faqVO" items="${faqSvc.all}" > 
          <option value="${faqVO.faqNo}">${faqVO.faqNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addFaq.jsp'>Add</a> a new Faq.</li>
</ul>

</body>
</html>