<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
  <meta charset="UTF-8">
  <title>會員訊息通知</title>
  <jsp:include page="/indexpage/head.jsp" />
  
  <style>
    .notification-list {
      max-width: 800px; /* 限制宽度 */
      margin: 0 auto;
    }
    .notification-item {
      border-left: 4px solid #63bfc9;
      margin-bottom: 10px;
    }
    .notification-read {
      background-color: #f0f0f0; /* 已读消息背景色 */
    }
    .notification-title {
      font-weight: bold;
    }
    .notification-content {
      display: none;
      margin-top: 5px;
    }
    .notification-date {
      font-size: 0.8rem;
      color: #6c757d;
    }
  </style>
</head>
<body>

<jsp:include page="/indexpage/header.jsp" />
<jsp:include page="/indexpage/headpic.jsp" />

<div class="container mt-3 mb-5">
  <h5>會員訊息通知</h5>
  <div class="notification-list">
    <c:forEach items="${notifications}" var="notifications">
      <div class="list-group-item list-group-item-action flex-column align-items-start notification-item ${notifications.noteRead == 0 ? '' : 'notification-read'}">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1 notification-title">${notifications.noteType}</h5>
          <small class="notification-date"><fmt:formatDate value="${notifications.noteCreated}" pattern="yyyy-MM-dd HH:mm" /></small>
        </div>
        <div class="notification-content">
          <p class="mb-1">${notifications.noteContent}</p>
          <small class="notification-date">${notifications.noteRead == 0 ? '未讀' : '已讀'}</small>
        </div>
      </div>
    </c:forEach>
  </div>
  
  <!-- 分页控制 -->
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
  $(document).ready(function() {
    $('.notification-item').click(function(e) {
      e.stopPropagation(); // 防止事件冒泡
      $(this).find('.notification-content').slideToggle(); 
      
      var isRead = $(this).hasClass('notification-read');
      if (!isRead) {
        $(this).addClass('notification-read'); 
      }
    });
  });
</script>


<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>
