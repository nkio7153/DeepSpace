<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../indexpage/head.jsp" />
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function() {
            $.ajax({
                type: "post",
                url: '<%=request.getContextPath()%>/account/account.do?action=getMemList',
                dataType: "json",
                success: function(data) {
                    var selectBox = $("#memId");
                    selectBox.empty(); 
                    $.each(data, function(index, value) {
                        selectBox.append($("<option></option>").attr("value", value).text(value));
                    });
                }
            });
        });
           
    </script>
</head>
<body>
    <jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />
    <!-- 主標題 -->
    <h1>分帳表查詢</h1>
    <!-- 查詢表單 -->
    <form method="post" action="<%=request.getContextPath()%>/account/account.do?action=doMemList">
        <label for="memId">選擇會員編號：</label>
        <select id="memId" name="memId">
        </select>
        <input type="submit" value="查詢">
    </form>
    <!-- 其他操作 -->
    <ul>
        <li><a href="<%=request.getContextPath()%>/account/list.jsp">查看所有分帳表列表</a></li>
    </ul>

    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
