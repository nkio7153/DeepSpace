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
</head>
<body class="bg-light">
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>
<h3>歡迎會員${memId}</h3>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">



    //回到上一頁
    function index(memId) {
        document.location.href =
            "${pageContext.request.contextPath}/tsc/index?memId="+memId
    }

    //全部確認刪除
    function delAll(memId) {
        var ok = window.confirm("確定要刪除嗎");
        if (ok) {
            document.location.href = "${pageContext.request.contextPath}/tsc/deleteAll?memId=" + memId
        }

    }
    //進入頁面局部刷新數量及金額
    $(document).ready(function () {
        let totalPrice = 0;
        $('.sub').each(function () {
            const sub = parseFloat($(this).text());
            if (!isNaN(sub)) {
                totalPrice += sub;
            }
        })
        $('.sub2').text(totalPrice);
        let sub2 = parseFloat($('.sub2').text());
        let coupen = parseFloat($('.coupen').val());
        // if(coupen===null){
        //     coupen=0;
        //     return
        // }
        let lastPrice = sub2 - coupen;
        $('.total').text(lastPrice);

//讓後端更新數據
        $('.num').on("blur", function () {
            console.log("觸發blur事件")
            // const self=$(this);
            let memId = $(this).closest('tr').find('.mem').text();
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
                    return response.json();
                })
                .then(function (data) {
        });
        });
        //
        $('.num').on("change", function () {
            console.log("觸發change事件")
            let quantity = $(this).val();
            let price = parseInt($(this).closest('tr').find('.price').text());
            let sub = quantity * price;
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
            let coupen = parseFloat($('.coupen').val());
            // if(coupen===null){
            //     coupen=0;
            //     return
            // }
            let lastPrice = sub2 - coupen;
            $('.total').text(lastPrice);//總金額
        })
    // });
        //刪除確認
        $(".delete1").on("click",function(){
            let memId = $(this).closest('tr').find('.mem').text();
            let ticketId = $(this).closest('tr').find('.tid').text();
            console.log("memId:", memId, "ticketId:", ticketId);
            var ok = window.confirm("確定要刪除嗎");
            if (ok) {
                <%--document.location.href = "${pageContext.request.contextPath}/tsc/delete1?memId=" + memId + "&ticketId=" + ticketId--%>
//----------- 打包資料 (start)
                $(this).closest('tr').remove();
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
    })

</script>


</script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/ticketShoppingCart/css/memAllCart.css">

<h1 class="text-center display-3">票券購物車</h1>

<table align="center" width="80%" class="table table-hover table-striped table-bordered">
    <%--  //票券圖片、票券名稱、票券介紹、價格、數量、小計--%>
    <tr class="bg-info">
        <th class="text-center text-nowrap">序號</th>
        <th class="hidden">會員編號</th>
        <th class="text-center text-nowrap">票券圖片</th>
        <th class="hidden">票券編號</th>
        <th class="text-center text-nowrap">票券名稱</th>
        <th class="text-center text-nowrap">票券介紹</th>
        <th class="text-center text-nowrap">價格</th>
        <th class="text-center text-nowrap">票券數量</th>
        <th class="text-center text-nowrap">小計</th>
        <th class="w150"></th>
    </tr>


    <c:forEach items="${list}" var="cart" varStatus="cartStatus">
        <tr>
            <td align="center" class="vertical">${cartStatus.count}</td>
            <td class="mem hidden">${memId}</td>
            <td>
<%--                <img src="data:image/jpeg;base64,${cart.base64Image}" class="jpg mx-auto d-block"/>--%>
                <img src="${pageContext.request.contextPath}/tsc/image?serialId=${cart.serialId}" class="jpg"/>
            </td>
            <td align="center" class="hidden tid">${cart.ticketId}</td>
            <td align="center" class="w150">${cart.ticketName}</td>
            <td align="center" class="w200">${cart.description}</td>
            <td align="center" class="price vertical">${cart.price}</td>
            <td class="vertical">
                <input type="number" min="1" max="99" step="1" value="${cart.quantity}" class="num"></td>
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
<a href="${pageContext.request.contextPath}/ticketShoppingCart/addCart.jsp?memId=${memId}" class="btn btn-primary p-1 rounded m-lg-1"> 瀏覽其他票券</a>
<hr>

<a href="javascript:void(0)" class="center btn btn-danger p-4 rounded" onclick="delAll(${memId})">清空購物車</a>
<br>


<div class="fs-3 bg-light p-4 rounded">
    <span>小計:</span><span class="sub2"></span>
    <br>
    <span>點數:</span><input placeholder="請輸入欲使用點數" class="coupen w-10" value="0"></input>
<%--    <span placehoder="請輸入欲使用點數" class="coupen">0</span>--%>
</div>

<div class="last fs-2 bg-light p-4 rounded">
    <span>總金額:</span><span class="total text-black-50"></span>
    <input type="button" class="btn btn-primary btn-lg offset-1" onclick="location.href='${pageContext.request.contextPath}/tsc/checkout?memId=${memId}'" value="前往結帳">
</div>

<%--<div class="last fs-2 light p-4 rounded">--%>
<%--    <input type="button" class="btn btn-primary btn-lg" value="前往結帳">--%>
<%--</div>--%>




<jsp:include page="../indexpage/footer.jsp"/>
</body>
</html>
