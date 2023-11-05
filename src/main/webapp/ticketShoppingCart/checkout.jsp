<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>結帳頁面</title>
    <jsp:include page="../indexpage/head.jsp"/>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Custom Styles -->
    <style>
        .last {
            display: flex;
            justify-content: flex-end;
        }

        .btn-del {
            background-color: #ef7a70;
            color: white;
        }

        .btn-del:hover,
        .btn-del:focus {
            background-color: #f14131;
        }

        .hidden {
            display: none;
        }
    </style>
    <!-- jQuery, Popper.js, Bootstrap JS -->


</head>

<body class="bg-light">
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>
<div class="container mt-4">
    <h1 class="text-center display-3 mb-4">結帳</h1>
    <div class="row">
        <div class="col-lg-8">
            <div class="mb-4 border rounded shadow-sm p-3">
                <h4 class="border-bottom pb-2">訂購人資訊</h4>
                <div class="row">
                    <div class="col-sm">
                        <span>名字</span>
                        <br>
                        <input class="form-control">
                    </div>
                    <div class="col-sm">
                        <span>姓</span>
                        <br>
                        <input class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm">
                        <span>國家/地區</span>
                        <br>
                        <select id="countrySelect" class="form-select">
                            <!-- 國家選單 -->
                        </select>
                    </div>
                    <div class="col-sm">
                        <span>聯絡電話</span>
                        <br>
                        <div class="input-group">
                            <select id="phoneCodeSelect" class="form-select w-25" placeholder="+886">
                                <!-- 國碼選單 -->
                            </select>
                            <input type="text" class="form-control w-75" placeholder="請輸入連絡電話" id="phoneNumber">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm">
                        <span>Email/電子郵件</span>
                        <br>
                        <input class="form-control w-50">
                    </div>
                </div>

                <!-- ... -->
            </div>
            <div class="mb-4 border rounded shadow-sm p-3">
                <h4 class="border-bottom pb-2">請選擇付款方式</h4>
                <div>
                    <input type="radio" name="paymentMethod" data-method="信用卡" checked value="1"><span name="pm" class="fs-5">信用卡</span>/
                    <input type="radio" name="paymentMethod" data-method="轉帳" value="2"><span name="pm" class="fs-5">轉帳</span>/
                    <input type="radio" name="paymentMethod" data-method="行動支付" value="3"><span name="pm" class="fs-5">行動支付</span>
                </div>
                <br>
                <%--          信用卡--%>
                <div class="row text-start" id="masterCard">
                    <div class="col">
                        <span>信用卡號碼</span><br>
                        <input type="text" placeholder="oooo oooo oooo oooo">
                    </div>
                    <div class="col">
                        <span>有效期限(月/年)</span><br>
                        <input type="text" placeholder="MM/YY">
                    </div>
                    <div class="col">
                        <span>背面末三碼</span><br>
                        <input type="text" placeholder="CVC/CVV">
                    </div>
                </div>
                <%--        轉帳--%>
                <div id="transfer">

                </div>
                <%--        街口支付--%>
                <div id="transfer">

                </div>
            </div>
            <!-- <div class="mb-4 border rounded shadow-sm p-3">
                <h4 class="border-bottom pb-2">收貨地址</h4>
                 地址表單
            </div> -->
        </div>
        <div class="col-lg-4">
            <div class="mb-4 border rounded shadow-sm p-3">
                <h4 class="border-bottom pb-2">訂單摘要</h4>
                <table class="table">
                    <!-- 票券名稱 單價 數量 小計 -->
                    <tr>
                        <th>票券</th>
                        <th class="text-nowrap">單價</th>
                        <th class="text-nowrap">數量</th>
                        <th class="text-nowrap">小計</th>
                    </tr>
                    <c:forEach items="${list}" var="cart">
                        <tr>
                            <td name="price" class="hidden">${cart.price}</td>
                            <td name="discount" class="hidden">${cart.discount}</td>
                            <td name="ticketName">${cart.ticketName}</td>
                            <td class="discountPrice" name="discountPrice"></td>
                            <td class="quantity" name="quantity">${cart.quantity}</td>
                            <td class="sub"></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="fs-3 bg-light p-4 rounded mb-4 border rounded shadow-sm">
                <span>小計:</span><span class="sub2 badge bg-primary"></span><br>
                <span>點數:</span><span class="coupen badge bg-warning">${coupen}</span>
            </div>
            <div class="last fs-2 bg-light p-4 rounded border rounded shadow-sm">
                <span class="fs-5">總金額:$</span>
                <div class="total text-black-50 fs-5"></div>
                <br>
                <input type="button" class="btn btn-primary btn-lg ms-2" value="確認付款" id="confirmPaymentButton">
            </div>
        </div>
    </div>
</div>
<jsp:include page="../indexpage/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        // 電話區號列表
        var phoneCodes = [
            "+86",   // 中國
            "+1",    // 美國
            "+81",   // 日本
            "+7",    // 俄羅斯
            "+1",    // 加拿大
            "+49",   // 德國
            "+44",   // 英國
            "+33",   // 法國
            "+61",   // 澳大利亞
            "+886",  // 台灣
            "+65",   // 新加坡
            "+852",  // 香港
            "+91",   // 印度
            "+82",   // 南韓
            "+55",   // 巴西
            "+52",   // 墨西哥
            "+41",   // 瑞士
            "+27",   // 南非
            "+34",   // 西班牙
            "+39",   // 義大利
            "+47",   // 挪威
            "+46",   // 瑞典
            "+358",  // 芬蘭
            "+31",   // 荷蘭
            "+32",   // 比利時
            "+54",   // 阿根廷
            "+56",   // 智利
            "+380",  // 烏克蘭
            "+48",   // 波蘭
            "+43"    // 奧地利
        ];


        // 添加電話區號選項到下拉選單
        var phoneCodeSelect = $("#phoneCodeSelect");
        phoneCodes.forEach(function (code) {
            phoneCodeSelect.append(new Option(code, code));
        })

        var countrySelect = $("#countrySelect");
        var country = [
            "中國",
            "美國",
            "日本",
            "俄羅斯",
            "加拿大",
            "德國",
            "英國",
            "法國",
            "澳大利亞",
            "台灣",
            "新加坡",
            "香港",
            "印度",
            "南韓",
            "巴西",
            "墨西哥",
            "瑞士",
            "南非",
            "西班牙",
            "義大利",
            "挪威",
            "瑞典",
            "芬蘭",
            "荷蘭",
            "比利時",
            "阿根廷",
            "智利",
            "烏克蘭",
            "波蘭",
            "奧地利"
        ];

        var countrySelect = $("#countrySelect");
        country.forEach(function (coun) {
            countrySelect.append(new Option(coun, coun))
        })
        //刷新付款方式
        $('[name="paymentMethod"]').on("change", function () {
            let paymentMethod = $(this).data("method");
            if (paymentMethod === "信用卡") {
                $("#masterCard").show();
            } else {
                $("#masterCard").hide();
            }
        });


        // 計算總金額
        let totalPrice = 0;
        $('.sub').each(function () {
            let price = $(this).closest("tr").find("[name='price']").text();
            //取出折扣
            let discount = $(this).closest("tr").find("[name='discount']").text();
            //取出折扣價，如果折扣不為空的話，
            if (discount === null || discount === "") {
                $(this).closest("tr").find("[name='discountPrice']").text(price);
            } else {
                $(this).closest("tr").find("[name='discountPrice']").text(Math.floor(price * discount / 10));
            }
            let discountPrice = parseInt($(this).closest("tr").find(".discountPrice").text().replace("$", ""));
            console.log(discountPrice);
            //折扣價去除小數點
            $(this).closest("tr").find(".discountPrice").text("$" + discountPrice);
            let quantity = parseInt($(this).closest("tr").find(".quantity").text());
            console.log(quantity);
            //小記計算後賦值
            $(this).text(("$" + discountPrice * quantity));

            const sub = parseFloat($(this).text().substring(1));
            if (!isNaN(sub)) {
                totalPrice += sub;
            }
        })
        //動態修改小計及總金額
        $('.sub2').text(totalPrice);//小計

        let sub2 = parseFloat($('.sub2').text());
        let coupen = parseFloat($('.coupen').text());
        let lastPrice = sub2 - coupen;
        $('.total').text(lastPrice);//總金額


        //確認付款生成訂單轉發
        $("#confirmPaymentButton").on("click", function () {
            var ok = window.confirm("是否確定要進行付款");
            if (ok) {
                var selectedPaymentMethod = $("input[name='paymentMethod']:checked").val();
                let memId =${memId};
                let amountPaid = $(".total").text();
                let url = `${pageContext.request.contextPath}/to/save?memId=` + memId + `&totalAmount=` + sub2 + `&amountPaid=` + amountPaid + `&paymentMethod=` + selectedPaymentMethod
                window.location.href = url;
            }
        })
    })
</script>
</body>
</html>