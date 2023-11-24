<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    // 設定請求對象的字符編碼為UTF-8
    request.setCharacterEncoding("UTF-8");
    // 假定 "submitted" 是一個請求參數，它在表單提交後被設置
    boolean isSubmitted = "true".equals(request.getParameter("submitted"));
    // 首頁URL地址
    String homeURL = "首頁網址"; // 請將此處的 "首頁網址" 替換為您的實際首頁 URL
%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <jsp:include page="../indexpage/head.jsp" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>問題提交</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <% if (isSubmitted) { %>
    <meta http-equiv="refresh" content="3;url=<%= homeURL %>">
    <% } %>
</head>
<body>
    <jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <% if (!isSubmitted) { %>
                <form action="QuestionSubmissionServlet" method="post">
                    <div class="mb-3">
                        <label for="question" class="form-label">問題:</label>
                        <textarea id="question" name="question" class="form-control" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">電子郵件:</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">送出</button>
                </form>
                <% } else { %>
                <div class="alert alert-success mt-3">
                    <h1>您的問題已提交成功，我們會盡快回覆您。</h1>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
