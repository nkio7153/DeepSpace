<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>FaqTypes: Home</title>

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
   <tr><td><h3>FaqTypes: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>FaqTypes: Home</p>

<h3>FaqTypes資料查詢:</h3>
	
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
  <li><a href='listAllFaq.jsp'>列表</a> all FaqTypes.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="faq.do1" >
        <b>輸入常見問題類型編號 :</b>
        <input type="text" name="faqNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="faqSvc" scope="page" class="com.depthspace.faqtypes.model.service.FaqTypesService" />
   
  <li>
     <FORM METHOD="post" ACTION="faq.do1" >
       <b>選擇常見問題類型編號:</b>
       <select size="1" name="faqNo">
         <c:forEach var="faqTypesVO" items="${faqSvc.all}" > 
          <option value="${faqTypesVO.faqNo}">${faqTypesVO.faqNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="faq.do1" >
       <b>選擇問題類型:</b>
       <select size="1" name="faqNo">
         <c:forEach var="faqTypesVO" items="${faqSvc.all}" > 
          <option value="${faqTypesVO.faqNo}">${faqTypesVO.qTypes}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>FaqTypes管理</h3>

<ul>
  <li><a href='addFaq.jsp'>新增</a> a new FaqTypes.</li>
</ul>

</body>
</html>