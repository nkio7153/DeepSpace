<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
    <meta charset="UTF-8">
    <title>會員權限管理</title>
    <!-- 引入Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />
    <div class="container">
        <h1 class="mt-5">會員權限管理</h1>

        <!-- 依帳號查詢會員 -->
        <h2 class="mt-4">查詢會員</h2>
        <form class="form-inline" action="your_search_action.jsp" method="post">
            <div class="form-group mb-2">
                <label for="memId">會員編號：</label>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <input type="text" class="form-control" id="memId" name="memId" placeholder="請輸入帳號">
            </div>
            <button type="submit" class="btn btn-primary mb-2">查詢</button>
        </form>

        <!-- 會員列表 -->
        <h2 class="mt-4">會員列表</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>會員編號</th>
                    <th>帳號</th>
                    <th>會員姓名</th>
                    <th>身分證字號</th>
                    <th>生日</th>
                    <th>性別</th>
                    <th>電子郵件</th>
                    <th>手機電話</th>
                    <th>地址</th>
                    <th>狀態</th>
                    <th>會員點數</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <!-- 在這裡使用JSP語法生成會員列表 -->
                <!-- 每一行都是一個會員的資料 -->
                <tr>
                    <td>1</td>
                    <td>user1</td>
                    <td>王小明</td>
                    <td>A123456789</td>
                    <td>1990-01-01</td>
                    <td>男</td>
                    <td>user1@example.com</td>
                    <td>0912345678</td>
                    <td>台北市</td>
                    <td>正常</td>
                    <td>100</td>
                    <td>
                        <!-- 編輯按鈕 -->
                        <button class="btn btn-primary">編輯</button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>user2</td>
                    <td>張小美</td>
                    <td>B987654321</td>
                    <td>1995-05-05</td>
                    <td>女</td>
                    <td>user2@example.com</td>
                    <td>0923456789</td>
                    <td>新竹市</td>
                    <td>正常</td>
                    <td>200</td>
                    <td>
                        <!-- 編輯按鈕 -->
                        <button class="btn btn-primary">編輯</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- 引入Bootstrap JavaScript，如有需要 -->
<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
