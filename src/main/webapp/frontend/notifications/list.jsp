<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
  <meta charset="UTF-8">
  <title>會員訊息通知</title>
  <jsp:include page="/indexpage/head.jsp" />
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="/indexpage/header.jsp" />
<jsp:include page="/indexpage/headpic.jsp" />

  <div class="container mt-3 mb-5">
    <h5>會員訊息通知</h5>
    <div class="list-group">
      <c:forEach items="${notifications}" var="notifi">
        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">${notifi.noteType}</h5>
            <small class="text-muted">${notifi.noteCreated}</small>
          </div>
          <p class="mb-1">${notifi.noteContent}</p>
          <small class="text-muted">${notifi.noteRead == 0 ? '未讀' : '已讀'}</small>
        </a>
      </c:forEach>
    </div>
  </div>
  
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>
