<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>關鍵字 首頁</title>
<%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  <!-- 引入 Bootstrap 5 CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
     <style>
        .card {
            background: none; /* 移除背景色 */
            border: none; /* 如果也想移除邊框 */
            padding: 0; /* 移除內距 */
        }
    </style>
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
      <div class="col-lg-10">
                <div class="card">
                    <div class="card-header">
                        <h3>關鍵字問題 : 首頁</h3>
                    </div>
                    <div class="card-body">
                        <p>關鍵字</p>
                        <h3>關鍵字資料查詢:</h3>
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

                        <ul>
                          <li><a href='listAll.jsp' class='btn btn-link'>列表</a> all 關鍵字.</li>
                          <li>
                            <form method="post" action="<%=request.getContextPath()%>/keywordqa/keywordQa.do" class="form-inline">
                                <b>輸入關鍵字編號 :</b>
                                <input type="text" name="serialId" class="form-control mx-2">
                                <input type="hidden" name="action" value="getOne_For_Display">
                                <button type="submit" class="btn btn-primary">送出</button>
                            </form>
                          </li>
                          <jsp:useBean id="keywordQaSvc" scope="page" class="com.depthspace.keywordqa.service.KeywordQaService" />
                          <li>
                             <form method="post" action="<%=request.getContextPath()%>/keywordqa/keywordQa.do" class="form-inline">
                               <b>選擇關鍵字編號:</b>
                               <select size="1" name="serialId" class="form-control mx-2">
                                 <c:forEach var="keywordQaVO" items="${keywordQaSvc.all}" > 
                                  <option value="${keywordQaVO.serialId}">${keywordQaVO.serialId}
                                 </c:forEach>   
                               </select>
                               <input type="hidden" name="action" value="getOne_For_Display">
                               <button type="submit" class="btn btn-primary">送出</button>
                             </form>
                          </li>
                          <li>
                             <form method="post" action="<%=request.getContextPath()%>/keywordqa/keywordQa.do" class="form-inline">
                               <b>選擇關鍵字問題:</b>
                               <select size="1" name="serialId" class="form-control mx-2">
                                 <c:forEach var="keywordQaVO" items="${keywordQaSvc.all}" > 
                                  <option value="${keywordQaVO.serialId}">${keywordQaVO.kwTypes}
                                 </c:forEach>   
                               </select>
                               <input type="hidden" name="action" value="getOne_For_Display">
                               <button type="submit" class="btn btn-primary">送出</button>
                             </form>
                          </li>
                        </ul>

                        <h3>關鍵字管理</h3>
                        <ul>
                            <li><a href='add.jsp' class='btn btn-link'>新增</a> a new 關鍵字.</li>
                        </ul>
                    </div>
                </div>
            </div>

    </div>
  </div>
</div>

    <!-- 引入 Bootstrap 5 JS 和相關依賴 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.7.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"></script>

</body>
</html>