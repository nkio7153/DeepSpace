<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>票券訂單</title>
    <jsp:include page="../indexpage/head.jsp" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>
<div class="container mt-4">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/to/listAll">返回訂單全部列表</a>
    <h3>訂單查詢</h3>

    <form class="form_search" id="searchSubmit" method="post" action="${pageContext.request.contextPath}/to/search">
        <label>會員編號</label>
        <select name="selectedMemId" >
            <c:forEach var="selectedMemId" items="${uniqueMemIds}">
                <option value="${selectedMemId}">${selectedMemId}</option>
            </c:forEach>
        </select>
        <%--        <input type="text" class ="wd100" name="memId" >--%>

        <label class="offset-1">訂單狀態</label>
        <select name="selectedStatus" >
            <c:forEach var="selectedStatus" items="${uqStatus}">
                <option value="${selectedStatus}">${selectedStatus}</option>
            </c:forEach>
        </select>
        <%--        <input type="text" class ="wd100" name="status">--%>

        <label class="offset-1">訂單日期區間</label>
        <input type="Date" name="startOrderDate" id="startOrderDate"> ～ <input type="Date" class ="wd-100" name="endOrderDate" id="endOrderDate">


        <input type="hidden" name="choice" value="searchCondition">
        <input type="submit" value="查詢">
        <h1 class="text-center my-4">訂單搜索列表</h1>
    </form>
    <c:if test="${toPageQty > 0}">
        <b><font color=red>第${currentPage}/${toPageQty}頁</font></b>
    </c:if>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="text-center">序號</th>
            <th class="text-center">訂單編號</th>
            <th class="text-center">會員編號</th>
            <th class="text-center">訂單日期</th>
            <th class="text-center">總金額</th>
            <th class="text-center">點數回饋</th>
            <th class="text-center">實付金額</th>
            <th class="text-center">支付方式</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="order" varStatus="orderStatus">
            <tr>
                <td align="center">${orderStatus.count}</td>
                <td align="center">${order.orderId}</td>
                <td align="center">${order.memId}</td>
                <td align="center">${order.orderDate}</td>
                <td align="center">${order.totalAmount}</td>
                <td align="center">${order.pointsFeedback}</td>
                <td align="center">${order.amountPaid}</td>
                <td align="center" name="paymentMethod">${order.paymentMethod}</td>
                <td align="center" name="status">${order.status}</td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/tod/backList?orderId=${order.orderId}&totalAmount=${order.totalAmount}&amountPaid=${order.amountPaid}" class="btn btn-secondary">訂單明細</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/to/search?page=1">至第一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage - 1 != 0}">
        <a href="${pageContext.request.contextPath}/to/search?page=${currentPage - 1}">上一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage + 1 <= toPageQty}">
        <a href="${pageContext.request.contextPath}/to/search?page=${currentPage + 1}">下一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage != toPageQty}">
        <a href="${pageContext.request.contextPath}/to/search?page=${toPageQty}">至最後一頁</a>&nbsp;
    </c:if>
</div>
<script type="text/javascript">
    function orderList(){
        document.location.href= "${pageContext.request.contextPath}/tod/list?orderId=${order.orderId}";
    }
    function index(){
        document.location.href= "${pageContext.request.contextPath}/to/index";
    }
    $(document).ready(function(){
        $('[name="paymentMethod"]').each(function(){
            switch ($(this).text()) {
                case '1':
                    $(this).text("信用卡");
                    break;
                case '2':
                    $(this).text("轉帳");
                    break;
                case '3':
                    $(this).text("街口支付");
                    break;
            }
        })
        $('[name="status"]').each(function(){
            switch ($(this).text()) {
                case '0':
                    $(this).text("已完成");
                    break;
                case '1':
                    $(this).text("已取消");
                    break;
                case '2':
                    $(this).text("已退貨");
                    break;
            }
        })
    })
    $("#search").on("click",function(event){
        event.preventDefault();
        var startOrderDate=new Date($("#startOrderDate").val());
        var endOrderDate=new Date($("#endOrderDate").val());
        if(endOrderDate < startOrderDate){
            window.alert("結束日期有誤");
        }else{
            $("#searchSubmit").submit();
        }
    })
</script>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
