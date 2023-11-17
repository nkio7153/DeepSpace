<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <%--  include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>

    <style>
        .hidden{
            display:none;
        }
        /* ===== 重要性的星號 ===== */
        div.star_block {
            display: inline-block;
        }

        div.star_block > span.star {
            cursor: pointer;
            display: inline-block;
            margin-right: 3px;
        }

        div.star_block > span.star.-on {
            color: yellow;
        }
    </style>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
    <div class="row">
        <%--    側邊欄--%>
        <div class="col-lg-2 g-3 my-0">
            <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>
            <div class="col-lg-10 g-2 transparent rounded my-0">
            <%--      放入自己body裡的代碼--%>
                <div class="container mt-4 transparent rounded">
<%--                    <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">返回</button>--%>
                    <h3 class="text-center my-4">訂單明細列表</h3>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th class="text-center">序號</th>
                            <th class="text-center">訂單編號</th>
                            <th class="text-center hidden">票券編號</th>
                            <th class="text-center">票券名稱</th>
                            <th class="text-center">單價</th>
                            <th class="text-center">商品折扣價</th>
                            <th class="text-center">數量</th>
                            <th class="text-center">商品小計</th>
                            <th class="text-center">商品評價</th>
                            <th class="text-center">星星數</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="od" varStatus="odst">
                            <tr>
                                <td class="text-center">${odst.count}</td>
                                <td class="text-center" name="orderId">${od[0]}</td>
                                <td class="text-center hidden">${od[1]}</td>
                                <td class="text-center" name="ticketId">${od[2]}</td>
                                <td class="text-center">${od[3]}</td>
                                <td class="text-center">${od[4]}</td>
                                <td class="text-center">${od[5]}</td>
                                <td class="text-center">${od[6]}</td>
                                <td class="text-center" name="ticketReviews">${od[7]}</td>
                                <td class="text-center">
                                    <div class="star_block">
                                            ${od[8]}
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-end">
                        <div>
                            <p><strong>小計總額:</strong> $<span name="totalAmount">${totalAmount}</span></p>
                            <p><strong>使用點數:</strong> $<span name="coupen">${totalAmount-amountPaid}</span></p>
<%--                        </div>--%>
                            <hr>
<%--                        <div>--%>
                            <p><strong>實付金額:</strong> $<span name="amountPaid">${amountPaid}</span></p>
                        </div>
                    </div>
                </div>

                <script type="text/javascript">
                    function index() {
                        document.location.href = "${pageContext.request.contextPath}/to/index";
                    }
                    $(document).ready(function () {
                        //判斷星星是否為空
                        //如果不為空，按照星星數序加上-on，div.star_block.html()
                        //如果為空，預設為黑星星，div.star_block.html()
                        $(".star_block").each(function () {
                            var starText = $(this).text();
                            var starNum = parseInt(starText, 10);
                            html = "";
                            if (starNum) {
                                for (var i = 1; i <= 5; i++) {
                                    // 为每个星星创建一个span标签
                                    html += '<span class="star' + (i <= starNum ? ' -on' : '') + '" data-star="' + i + '"><i class="fas fa-star"></i></span>';
                                }
                            } else {
                                html += `
                      <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="5"><i class="fas fa-star"></i></span>`;
                            }
                            $(this).html(html);
                        })
                    });
                </script>
        </div>
    </div>
</div>

</body>
</html>
