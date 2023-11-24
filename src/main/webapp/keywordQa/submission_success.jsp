<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <jsp:include page="../indexpage/head.jsp" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>提交成功</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <meta http-equiv="refresh" content="3;url=/DepthSpace/" /> <!-- 將 "首頁網址" 替換為您的首頁地址 -->
</head>
<body>
    <jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="alert alert-success">
                    <h1>您的問題已提交成功，我們會盡快回覆您。</h1>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
