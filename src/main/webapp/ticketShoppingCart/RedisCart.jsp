<%@ page import="com.depthspace.ticketshoppingcart.service.TscServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>購物車</title>
    <jsp:include page="../indexpage/head.jsp"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ticketShoppingCart/css/memAllCart.css">
<style>
    .bg-check{
        background-color: #ece1be;
    }
    .bg1{
        background-color: cornflowerblue;
    }
    .bg2{
        background-color: #ff6b6b;
    }
    .coupen::placeholder {
        font-size: 15px;
        color: #888;
    }
    /*固定結帳區*/
    .check-sticky{
        position:sticky;
        bottom:0px;
        z-index: 1000;
        background-color:white;
        border-radius:5px;
        width: 100%; /* 確保滿寬 */
        padding-top: 5px;
        left: 0;
        /*height:18vh;*/
    }

    /* 表格滾動 */
    .scrollable-table tbody {
        display: block;
        max-height: 400px;
        overflow-y: auto;
    }

    .scrollable-table thead,
    .scrollable-table tbody tr {
        display: table;
        width: 100%;
        table-layout: fixed;
    }

    .scrollable-table {
        width: 100%;
        margin: auto;
    }

</style>
</head>
<body id="body">
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>
<%--<h3>歡迎會員${memId}</h3>--%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<h3 class="text-primary bg-light p-3 border border-primary text-center shadow mb-4">票券購物車</h3>
<%--    <table align="center" width="80%" class="table table-hover table-striped table-bordered mt-0">--%>
<%--        <tr class="bg-info">--%>
<%--            <th class="text-center text-nowrap" style="width: 4.5vw">序號</th>--%>
<%--            <th class="hidden">會員編號</th>--%>
<%--            <th class="text-center text-nowrap" style="width: 23.5vw">票券圖片</th>--%>
<%--            <th class="hidden">票券編號</th>--%>
<%--            <th class="text-center text-nowrap" style="width: 13vw">票券名稱</th>--%>
<%--            &lt;%&ndash;            <th class="text-center text-nowrap">票券介紹</th>&ndash;%&gt;--%>
<%--            <th class="text-center text-nowrap" style="width: 96px">價格</th>--%>
<%--            <th class="text-center text-nowrap" style="width: 106px">折扣</th>--%>
<%--            <th class="text-center text-nowrap"  style="width:97px">折扣價</th>--%>
<%--            <th class="text-center text-nowrap hidden">庫存</th>--%>
<%--            <th class="text-center text-nowrap">票券數量</th>--%>
<%--            <th class="text-center text-nowrap">小計</th>--%>
<%--            <input class="mem hidden" name="memId" value="${memId}"></input>--%>
<%--            <th class="w150"></th>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--<h1 class="text-center display-3">票券購物車</h1>--%>
<form action="${pageContext.request.contextPath}/tsc/checkout" method="post">
    <div class="scrollable-table">
    <table align="center" width="80%" class="table table-hover table-striped mt-0">
<%--          //票券圖片、票券名稱、票券介紹、價格、數量、小計--%>
        <thead>
        <tr class="bg1">
            <th class="text-center text-nowrap"style="width:7.5vw">序號</th>
            <th class="hidden">會員編號</th>
            <th class="text-center text-nowrap" style="width: 22.5vw">票券圖片</th>
            <th class="hidden">票券編號</th>
            <th class="text-center text-nowrap" style="width: 13vw">票券名稱</th>
<%--            <th class="text-center text-nowrap">票券介紹</th>--%>
            <th class="text-center text-nowrap" style="width: 7.5vw">價格</th>
            <th class="text-center text-nowrap" style="width: 7.5vw">折扣</th>
            <th class="text-center text-nowrap" style="width: 7.5vw">折扣價</th>
            <th class="text-center text-nowrap hidden">庫存</th>
            <th class="text-center text-nowrap" style="width: 7.5vw">票券數量</th>
            <th class="text-center text-nowrap" style="width: 7.5vw">小計</th>
            <input class="mem hidden" name="memId" value="${memId}"></input>
            <th style="width: 14vw"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="cart" varStatus="cartStatus">
            <tr id="tr">
                <td align="center" class="vertical" style="width:7.5vw">${cartStatus.count}</td>
                <td align="center" style="width: 23vw">
                    <img src="${pageContext.request.contextPath}/tsc/image?serialId=${cart.serialId}" class="jpg"/>
                </td>
                <td align="center" class="hidden tid" name="ticketId">${cart.ticketId}</td>
                <td align="center" class="vertical" style="width: 13vw" name="ticketName">${cart.ticketName}</td>
<%--                <td align="center" class="w200">${cart.description}</td>--%>
                <td align="center" class="price vertical"  style="width: 7.5vw">${cart.price}</td>
                <td align="center" class="discount vertical" name="discount" style="width: 8vw">${cart.discount}</td>
                <td align="center" class="discountPrice vertical" name="discountPrice"  style="width: 7.5vw"></td>
                <td align="center" class="hidden">${cart.stock}</td>
                <td align="center" class="vertical" style="width: 7.5vw">
                    <input type="number" min="1" max="99" step="1" value="${cart.quantity}" class="num" name="quantity">
                </td>
                <td align="center" class="sub vertical" style="width: 7.5vw"></td>
                <td align="center" class="no-wrap vertical" style="width: 13vw">
                        <%--                刪除--%>
                    <button class="btn btn-primary btn-del delete1" type="button" data-bs-toggle="modal">
                        <i class="fa fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <c:if test="${list.isEmpty()}">
        <div align="center">
            <h1>未添加票券</h1>
        </div>
    </c:if>
    <div style="display:flex; justify-content: space-between" class="px-3">
        <a href="${pageContext.request.contextPath}/ticketproduct/list"
           class="btn bg1 p-2 rounded-3 m-lg-2"  style="flex-grow: 1;"> 瀏覽其他票券</a>
        <a href="javascript:void(0)" class="btn bg2 p-2 m-lg-2 rounded-3"  style="width: 12vw"  onclick="delAll(${memId})">清空購物車</a>
    </div>
    </div>
    <hr>
    <div style="display:flex;" class="my-0 rounded mt-0 check-sticky">
        <div class="fs-5 px-4 rounded">

            <div style="display: flex; flex-wrap: nowrap; margin-right: 80px" class="mb-3">

                <span class="no-wrap mt-0">總計:</span><span class="sub2"></span>


                <span class="offset-5 no-wrap">點數:</span><input class="coupen no-wrap" name="coupen" placeholder="可使用點數:${authenticatedMem.memPoint}" style="width: 120px; height:30px; border: 0px; border-bottom:1px solid black; background-color:transparent;"></input>

<%--                    <span class="no-wrap"style="font-size: 12px; color:indianred">可使用點數:</span><span style="font-size: 12px; color:indianred" name="exist-coupen">${authenticatedMem.memPoint}</span>--%>

                <span class="offset-4 no-wrap">總金額:</span><span class="total no-wrap"></span>

<%--            </div>--%>
            <%--    <span placehoder="請輸入欲使用點數" class="coupen">0</span>--%>
<%--            <div style="margin-left:auto;">--%>
<%--                <span>總金額:</span><span class="total text-black-50"></span>--%>
                <%--  <input type="button" class="btn btn-primary btn-lg offset-1" onclick="location.href='${pageContext.request.contextPath}/tsc/checkout?memId=${memId}'" value="前往結帳">--%>
            </div>
            <div style="display: flex; padding-bottom: 8px">
                <input type="submit" style="flex-grow: 1; width:95vw" id="submit" class="btn bg1 text-light btn-lg wr" value="前往結帳">
            </div>
        </div>
    </div>



<%--    <div class="last fs-2 bg-light p-4 rounded">--%>
<%--        <span>總金額:</span><span class="total text-black-50"></span>--%>
<%--        &lt;%&ndash;  <input type="button" class="btn btn-primary btn-lg offset-1" onclick="location.href='${pageContext.request.contextPath}/tsc/checkout?memId=${memId}'" value="前往結帳">&ndash;%&gt;--%>
<%--        <input type="submit" class="btn btn-primary btn-lg" value="前往結帳">--%>
<%--    </div>--%>


</form>

<%--<div class="last fs-2 light p-4 rounded">--%>
<%--    <input type="button" class="btn btn-primary btn-lg" value="前往結帳">--%>
<%--</div>--%>
<script type="text/javascript">

    //結帳檢查
    $("#submit").on("click", function(e) {
        if(parseInt($(".total").text())==0){
            e.preventDefault();
            alert("未添加票券，無法結帳");
        }
        var inputValue = $('input[name="coupen"]').val(); // 假設 'inputName' 是您要檢查的輸入欄位的名稱
        if (parseInt(inputValue) > parseInt($("[name='exist-coupen']").text())) {
            e.preventDefault();
            alert("剩餘點數不足");
        }
    });

    //回到上一頁
    function index(memId) {
        document.location.href =
            "${pageContext.request.contextPath}/tsc/index?memId=" + memId
    }

    //全部確認刪除
    function delAll(memId) {
        var ok = window.confirm("確定要刪除嗎");
        if (ok) {
            $("#tr").remove();
            let url="${pageContext.request.contextPath}/tsc/deleteAll?memId=" + memId;
            fetch(url).then(function(response){
                return response.text();
            })
                .then(function(data){
                    console.log("刪除成功")
                })
                .catch(function(error){
                    console.log(error);
                })
        }
    }

    document.addEventListener("DOMContentLoaded", function () {
        console.log("進入js了")
        // Your code here
    });
    //進入頁面局部刷新數量及金額
    $(document).ready(function () {
        $(".discount").each(function(){
            if($(this).text()==""){
                $(this).text("無");
            }
        })
        console.log("進入jquery了")
        let totalPrice = 0;
        //遍歷所有的小計
        $('.sub').each(function () {
            //取出原價
            let price = $(this).closest("tr").find(".price").text();
            //取出折扣
            let discount = $(this).closest("tr").find(".discount").text();
            //取出折扣價，如果折扣不為空的話，
            if (discount === null || discount === ""|| discount=== "無") {
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
        $(".coupen").on("blur",function(e){
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
