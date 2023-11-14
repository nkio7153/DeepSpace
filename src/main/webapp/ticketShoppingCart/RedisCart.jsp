<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>所有購物車資料 memAllCart.jsp</title>
    <jsp:include page="../indexpage/head.jsp"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ticketShoppingCart/css/memAllCart.css">
</head>
<body class="bg-light">
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>
<h3>歡迎會員${memId}</h3>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<h1 class="text-center display-3">票券購物車</h1>
<form action="${pageContext.request.contextPath}/tsc/checkout" method="post">
    <table align="center" width="80%" class="table table-hover table-striped table-bordered">
        <%--  //票券圖片、票券名稱、票券介紹、價格、數量、小計--%>
        <tr class="bg-info">
            <th class="text-center text-nowrap">序號</th>
            <th class="hidden">會員編號</th>
            <th class="text-center text-nowrap">票券圖片</th>
            <th class="hidden">票券編號</th>
            <th class="text-center text-nowrap">票券名稱</th>
<%--            <th class="text-center text-nowrap">票券介紹</th>--%>
            <th class="text-center text-nowrap">價格</th>
            <th class="text-center text-nowrap">折扣</th>
            <th class="text-center text-nowrap">折扣價</th>
            <th class="text-center text-nowrap hidden">庫存</th>
            <th class="text-center text-nowrap">票券數量</th>
            <th class="text-center text-nowrap">小計</th>
            <input class="mem hidden" name="memId" value="${memId}"></input>
            <th class="w150"></th>
        </tr>

        <c:forEach items="${list}" var="cart" varStatus="cartStatus">
            <tr>
                <td align="center" class="vertical">${cartStatus.count}</td>
                <td style="width: 300px">
                    <img src="${pageContext.request.contextPath}/tsc/image?serialId=${cart.serialId}" class="jpg"/>
                </td>
                <td align="center" class="hidden tid" name="ticketId">${cart.ticketId}</td>
                <td align="center" class="w150" name="ticketName">${cart.ticketName}</td>
<%--                <td align="center" class="w200">${cart.description}</td>--%>
                <td align="center" class="price vertical">${cart.price}</td>
                <td align="center" class="discount vertical">${cart.discount}</td>
                <td align="center" class="discountPrice vertical" name="discountPrice"></td>
                <td align="center" class="hidden">${cart.stock}</td>
                <td align="center" class="vertical">
                    <input type="number" min="1" max="99" step="1" value="${cart.quantity}" class="num" name="quantity">
                </td>
                <td align="center" class="sub vertical"></td>
                <td align="center" class="no-wrap w150 vertical">
                        <%--                刪除--%>
                    <button class="btn btn-primary btn-del delete1" type="button" data-bs-toggle="modal">
                        <i class="fa fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>

        <hr>
    </table>
    <c:if test="${list.isEmpty()}">
        <div align="center">
            <h1>未添加票券</h1>
        </div>
    </c:if>
    <a href="${pageContext.request.contextPath}/ticketproduct/list"
       class="btn btn-primary p-1 rounded m-lg-1"> 瀏覽其他票券</a>
    <hr>

    <a href="javascript:void(0)" class="center btn btn-danger p-4 rounded" onclick="delAll(${memId})">清空購物車</a>
    <br>


    <div class="fs-3 bg-light p-4 rounded">
        <span>小計:</span><span class="sub2"></span>
        <br>
        <div>
            <span>點數:</span><input placeholder="請輸入" class="coupen" name="coupen" style="width: 100px"></input>
            <br>
            <span style="font-size: 15px">剩餘點數:</span><span style="font-size: 15px" name="exist-coupen">200</span>
        </div>
        <%--    <span placehoder="請輸入欲使用點數" class="coupen">0</span>--%>
    </div>

    <div class="last fs-2 bg-light p-4 rounded">
        <span>總金額:</span><span class="total text-black-50"></span>
        <%--  <input type="button" class="btn btn-primary btn-lg offset-1" onclick="location.href='${pageContext.request.contextPath}/tsc/checkout?memId=${memId}'" value="前往結帳">--%>
        <input type="submit" class="btn btn-primary btn-lg" value="前往結帳">
    </div>

</form>

<%--<div class="last fs-2 light p-4 rounded">--%>
<%--    <input type="button" class="btn btn-primary btn-lg" value="前往結帳">--%>
<%--</div>--%>
<script type="text/javascript">


    //回到上一頁
    function index(memId) {
        document.location.href =
            "${pageContext.request.contextPath}/tsc/index?memId=" + memId
    }

    //全部確認刪除
    function delAll(memId) {
        var ok = window.confirm("確定要刪除嗎");
        if (ok) {
            document.location.href = "${pageContext.request.contextPath}/tsc/deleteAll?memId=" + memId
        }

    }

    document.addEventListener("DOMContentLoaded", function () {
        console.log("進入js了")
        // Your code here
    });
    //進入頁面局部刷新數量及金額
    $(document).ready(function () {
        console.log("進入jquery了")
        let totalPrice = 0;
        //遍歷所有的小計
        $('.sub').each(function () {
            //取出原價
            let price = $(this).closest("tr").find(".price").text();
            //取出折扣
            let discount = $(this).closest("tr").find(".discount").text();
            //取出折扣價，如果折扣不為空的話，
            if (discount === null || discount === "") {
                $(this).closest("tr").find(".discountPrice").text(price);
            } else {
                $(this).closest("tr").find(".discountPrice").text(Math.floor(price * discount / 10));
            }

            let discountPrice = parseInt($(this).closest("tr").find(".discountPrice").text());
            // $(this).closest("tr").find(".discountPrice").text(discountPrice);
            console.log(discountPrice);
            //取出數量
            let quantity = parseInt($(this).closest("tr").find(".num").val());
            console.log(quantity);
            //計算小計並賦值
            $(this).text(parseInt(discountPrice * quantity));
            //
            const sub = parseFloat($(this).text());
            if (!isNaN(sub)) {
                totalPrice += sub;
            }
        })
        $('.sub2').text(totalPrice);
        let sub2 = parseFloat($('.sub2').text());
        let coupenVal = $('.coupen').val();
        let coupen = coupenVal === null || coupenVal === "" ? 0 : parseFloat(coupenVal);
        let lastPrice = sub2 - coupen;
        $('.total').text(lastPrice);

//讓後端更新數據(數量)
        $('.num').on("blur", function () {
            console.log("觸發blur事件")
            // const self=$(this);
            let memId = $('input[name="memId"]').val();
            let ticketId = $(this).closest('tr').find('.tid').text();
            let quantity = $(this).val();
//----------- 打包資料 (start)
            let tscData = {
                memId: memId,
                ticketId: ticketId,
                quantity: quantity
            };

// 將資料打包進 URLSearchParams()
            let formDataUrlEncoded = new URLSearchParams();
            for (let key in tscData) {
                formDataUrlEncoded.append(key, tscData[key]);
            }
//----------- 打包資料 (end)

// --------------------------------- 送出 Ajax 請求
            fetch("${pageContext.request.contextPath}/tsc/flush", {
                method: "post",
                body: formDataUrlEncoded
            })
                .then(function (response) {
                    return response.text();
                })
                .then(function (data) {
                    console.log(data);
                });
        });
        //
        $('.num').on("change", function () {
            console.log("觸發change事件")
            //取得數量
            let quantity = $(this).val();
            //取得折扣價
            let discountPrice = parseInt($(this).closest('tr').find('.discountPrice').text());
            //計算小計
            let sub = quantity * discountPrice;
            //更新小計
            $(this).closest('tr').find('.sub').text(sub);

            let totalPrice = 0;
            $('.sub').each(function () {
                const sub = parseFloat($(this).text());
                if (!isNaN(sub)) {
                    totalPrice += sub;
                }
            })
            //動態修改小計及總金額
            $('.sub2').text(totalPrice);//小計

            let sub2 = parseFloat($('.sub2').text());
            let coupenVal = $('.coupen').val();
            let coupen = coupenVal === null || coupenVal === "" ? 0 : parseFloat(coupenVal);
            let lastPrice = sub2 - coupen;
            $('.total').text(lastPrice);//總金額
        })
        // });
        //刪除確認
        $(".delete1").on("click", function () {

            let memId = $('input[name="memId"]').val();
            let ticketId = $(this).closest('tr').find('.tid').text();
            console.log("memId:", memId, "ticketId:", ticketId);
            var ok = window.confirm("確定要刪除嗎");
            if (ok) {
                <%--document.location.href = "${pageContext.request.contextPath}/tsc/delete1?memId=" + memId + "&ticketId=" + ticketId--%>
//----------- 打包資料 (start)
                $(this).closest('tr').remove();

                let totalPrice=0;
                $('.sub').each(function () {
                    const sub = parseFloat($(this).text());
                    if (!isNaN(sub)) {
                        totalPrice += sub;
                    }
                })
                //動態修改小計及總金額
                $('.sub2').text(totalPrice);//小計

                let sub2 = parseFloat($('.sub2').text());
                let coupenVal = $('.coupen').val();
                let coupen = coupenVal === null || coupenVal === "" ? 0 : parseFloat(coupenVal);
                let lastPrice = sub2 - coupen;
                $('.total').text(lastPrice);//總金額


                let tscData = {
                    memId: memId,
                    ticketId: ticketId,
                };

// 將資料打包進 URLSearchParams()
                let formDataUrlEncoded = new URLSearchParams();
                for (let key in tscData) {
                    formDataUrlEncoded.append(key, tscData[key]);
                }
//----------- 打包資料 (end)

// --------------------------------- 送出 Ajax 請求
                fetch("${pageContext.request.contextPath}/tsc/deleteByFetch", {
                    method: "post",
                    body: formDataUrlEncoded
                })
                    .then(function (response) {
                        return response.text();
                    })
                    .then(function (data) {
                        if (data != null) {
                            console.log(data);
                            $(this).closest('tr').remove();
                        }
                    })
                    .catch(function (error) {
                        console.error('刪除失敗:', error);
                    });
            }
        })
        //輸入點數後更新
        $(".coupen").on("blur",function(){
            if($(this).val() > parseInt($("[name='exist-coupen']").text())){
                alert("剩餘點數不足");
            }else{
                let exixtCoupen=$("[name='exist-coupen']").text();
                let coupenVal=$(this).val();
                let sub2 = parseFloat($('.sub2').text());
                let coupen = coupenVal === null || coupenVal === "" ? 0 : parseFloat(coupenVal);
                let lastPrice = sub2 - coupen;
                // finalCoupen=exixtCoupen-coupen;
                //
                // $("[name='exist-coupen']").text(finalCoupen);
                //更新總金額
                $('.total').text(lastPrice);
            }

        })
    })

</script>


<jsp:include page="../indexpage/footer.jsp"/>
</body>
</html>
