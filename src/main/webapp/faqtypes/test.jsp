<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>FaqTypes: Home</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
  <div class="row">
<%--    側邊欄--%>
    <div class="col-lg-2 g-3 mt-1">
    <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
    </div>

    <div class="col-lg-10 g-2 transparent rounded mt-1">
<%--      放入自己body裡的代碼--%>
      <table id="table-1">
   <tr><td><h3>FaqTypes: Home</h3></td></tr>
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
    <FORM METHOD="post" ACTION="faqTypes.do" >
        <b>輸入常見問題類型編號 :</b>
        <input type="text" name="faqNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="faqSvc" scope="page" class="com.depthspace.faqtypes.model.service.FaqTypesService" />
   
  <li>
     <FORM METHOD="post" ACTION="faqTypes.do" >
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
     <FORM METHOD="post" ACTION="faqTypes.do" >
       <b>選擇問題類型:</b>
       <select size="1" name="faqNo">
         <c:forEach var="faqTypesVO" items="${faqSvc.all}" > 
          <option value="${faqTypesVO.faqNo}">${faqTypesVO.QTypes}
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


    </div>
  </div>
</div>

</body>
</html>
