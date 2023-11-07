<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>票券訂單明細</title>
    <jsp:include page="../indexpage/head.jsp"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <style>
        .hidden {
            display: none;
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

        span.editable, input[type="text"] {
            width: 100px; /* 或其他固定的值 */
        }
    </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>

<div class="container mt-4">
    <button type="button" class="btn btn-secondary mb-3" onclick="history.back()">返回</button>
    <h1 class="text-center my-4">訂單明細列表</h1>
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
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="od" varStatus="odst">
            <tr>
                <td class="text-center">${odst.count}</td>
                <td class="text-center" name="orderId">${od[0]}</td>
                <td class="text-center hidden" name="ticketId">${od[1]}</td>
                <td class="text-center">${od[2]}</td>
                <td class="text-center">${od[3]}</td>
                <td class="text-center">${od[4]}</td>
                <td class="text-center">${od[5]}</td>
                <td class="text-center">${od[6]}</td>
                <td class="text-center">
                    <span class="editable" name="ticketReviews">${od[7]}</span>
                    <input type="text" class="form-control hidden" name="inputTicketReviews">
                </td>
                <td class="text-center">
                        <%--                    <span class="editable" name="stars">${od[8]}</span>--%>
                        <%--                    <input type="text" class="form-control hidden" name="inputStars">--%>
                    <div class="star_block">
                            ${od[8]}
                            <%--                        <span class="star" data-star="1"><i class="fas fa-star"></i></span>--%>
                            <%--                        <span class="star" data-star="2"><i class="fas fa-star"></i></span>--%>
                            <%--                        <span class="star" data-star="3"><i class="fas fa-star"></i></span>--%>
                            <%--                        <span class="star" data-star="4"><i class="fas fa-star"></i></span>--%>
                            <%--                        <span class="star" data-star="5"><i class="fas fa-star"></i></span>--%>
                    </div>
                </td>
                <td class="text-center">
                    <button name="edit" class="btn btn-primary btn-sm m-1">編輯</button>
                    <button name="save" class="btn btn-success btn-sm m-1 hidden">保存</button>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-end">
        <div>
            <p><strong>小計總額:</strong> $<span name="totalAmount">${totalAmount}</span></p>
            <p><strong>使用點數:</strong> $<span name="coupen">${totalAmount-amountPaid}</span></p>
            <p><strong>實付金額:</strong> $<span name="amountPaid">${amountPaid}</span></p>
        </div>
    </div>
</div>

<script type="text/javascript">
    function index() {
        document.location.href = "${pageContext.request.contextPath}/to/index";
    }

    $(document).ready(function () {
        console.log("乾")
        //判斷星星是否為空
        //如果不為空，按照星星數序加上-on，div.star_block.html()
        //如果為空，預設為黑星星，div.star_block.html()
        $(".star_block").each(function () {
            var starText = $(this).text();
            var starNum = parseInt(starText, 10);
            html="";
            if (starNum) {
                //我想依照startText取得的數字給span標籤，由上往下addClass("-on")
                for (var i = 1; i <= 5; i++) {
                    // 为每个星星创建一个span标签
                    html += '<span class="star' + (i <= starNum ? ' -on' : '') + '" data-star="' + i + '"><i class="fas fa-star"></i></span>';
                }
            }else{
                html+=`
                      <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="5"><i class="fas fa-star"></i></span>`;
            }
            $(this).html(html);
        })


        $("[name='save']").hide();
        //按下編輯鍵
        $("table").on("click", "[name='edit']", function () {//按下編輯鍵
            let tr = $(this).closest("tr");
            tr.find("[name='edit']").hide();//編輯鍵隱藏
            tr.find("[name='save']").show();//保存鍵顯現

            tr.find("[name='inputTicketReviews']").val(tr.find("[name='ticketReviews']").text()).show();//輸入欄顯示
            tr.find("[name='ticketReviews']").hide();//span標籤隱藏

            // 啟動編輯模式時，允許編輯星星評級
            tr.find(".star_block").addClass("editable-stars");
        });
        //按下保存鍵
        $("table").on("click", "[name='save']", function () {
            let tr = $(this).closest("tr");
            let orderId = tr.find("[name='orderId']").text();
            let ticketId = tr.find("[name='ticketId']").text();
            let ticketReviews = tr.find("[name='inputTicketReviews']").val();//取得輸入的評價
            let stars = tr.find(".star.-on").length//取得給予的星星數

            tr.find("[name='save']").hide();//保存鍵隱藏
            tr.find("[name='edit']").show();//編輯鍵顯示

            tr.find("[name='ticketReviews']").text(ticketReviews);

            tr.find("input").hide();
            tr.find(".editable").show();

            // 保存時，禁止編輯星星評級
            tr.find(".star_block").removeClass("editable-stars");


//----------- 打包資料 (start)
            let tscData = {
                orderId: orderId,
                ticketId: ticketId,
                ticketReviews: ticketReviews,
                stars: stars
            };

// 將資料打包進 URLSearchParams()
            let formDataUrlEncoded = new URLSearchParams();
            for (let key in tscData) {
                formDataUrlEncoded.append(key, tscData[key]);
            }
//----------- 打包資料 (end)

// --------------------------------- 送出 Ajax 請求
            fetch("${pageContext.request.contextPath}/tod/update", {
                method: "post",
                body: formDataUrlEncoded
            })
                .then(function (response) {
                    return response.text();
                })
                .then(function (data) {
                    console.log(data);
                })
                .catch(function (error) {
                    console.log(error);
                });

        });
        //按下星星修改數量
        $("table").on("click", ".star", function () {
            if ($(this).closest("div").hasClass("editable-stars")) {
                $(this).closest("div").find(".star").removeClass("-on");
                $(this).addClass("-on").prevAll().addClass("-on");
            }
        })
    })

</script>

<jsp:include page="../indexpage/footer.jsp"/>
</body>
</html>
