<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.FaqTypesVO"%>
<%@ page import="com.depthspace.faqtypes.model.service.*"%>
<%@ page import="com.depthspace.faqtypes.model.controller.*"%>
<%@ page import="com.depthspace.faqtypes.model.model.*"%>

<%
FaqTypesService faqSvc = new FaqTypesService();
List<FaqTypesVO> list = faqSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>FAQTypes</title>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
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
        <h3>FAQTypes - 首頁</h3>
        <a href="select_page.jsp" class="btn btn-primary mb-3">回首頁</a>
        <table class="table">
            <tr>
                <th>常見問題類型編號</th>
                <th>問題類型</th>
                <th>修改</th>
                <th>刪除</th>
            </tr>
            <c:forEach var="faqTypesVO" items="${list}">
                <tr>
                    <td>${faqTypesVO.faqNo}</td>
                    <td>${faqTypesVO.QTypes}</td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/faqtypes/faqTypes.do">
                            <input type="submit" value="修改">
                            <input type="hidden" name="faqNo" value="${faqTypesVO.faqNo}">
                            <input type="hidden" name="action" value="getOne_For_Update">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/faqtypes/faqTypes.do">
                            <input type="submit" value="刪除">
                            <input type="hidden" name="faqNo" value="${faqTypesVO.faqNo}">
                            <input type="hidden" name="action" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
