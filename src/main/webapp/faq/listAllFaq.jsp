<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.faq.model.FaqVO"%>
<%@ page import="com.depthspace.faq.service.*"%>
<%@ page import="com.depthspace.faq.controller.*"%>
<%@ page import="com.depthspace.faq.model.*"%>

<%
FaqService faqSvc = new FaqService();
List<FaqVO> list = faqSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>常見問題</title>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container-fluid {
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            padding: 15px;
        }

        h3 {
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid">
        <h3>常見問題管理</h3>
        <a href="select_page.jsp" class="btn btn-primary mb-3">回首頁</a>
        <table>
            <tr>
                <th>流水號</th>
                <th>FAQ編號</th>
                <th>FAQ名稱</th>
                <th>FAQ答案</th>
                <th>修改</th>
                <th>刪除</th>
            </tr>
            <c:forEach var="faqVO" items="${list}">
                <tr>
                    <td>${faqVO.serialId}</td>
                    <td>${faqVO.faqNo}</td>
                    <td>${faqVO.faqName}</td>
                    <td>${faqVO.faqAns}</td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/faq/faq.do">
                            <input type="submit" value="修改">
                            <input type="hidden" name="serialId" value="${faqVO.serialId}">
                            <input type="hidden" name="action" value="getOne_For_Update">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/faq/faq.do">
                            <input type="submit" value="刪除">
                            <input type="hidden" name="serialId" value="${faqVO.serialId}">
                            <input type="hidden" name="action" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
