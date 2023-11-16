<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
  <meta charset="UTF-8">
  <title>會員訊息通知</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-3">
    <h2>會員訊息通知</h2>
    <div class="list-group">
      <%-- 通知訊息範例 --%>
      <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">訊息標題</h5>
          <small class="text-muted">3 分鐘前</small>
        </div>
        <p class="mb-1">這是一條訊息的內容摘要，點擊可以查看詳情。</p>
        <small class="text-muted">更多詳情</small>
      </a>
      <%-- 可以重複以上區塊來添加更多訊息 --%>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
