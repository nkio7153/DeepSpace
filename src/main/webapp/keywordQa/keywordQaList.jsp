<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.depthspace.keywordqa.model.KeywordQaVO"%>
<%@ page import="com.depthspace.keywordqa.model.*"%>
<%@ page import="com.depthspace.keywordqa.controller.*"%>
<%@ page import="com.depthspace.keywordqa.service.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>關鍵詞問答管理</title>
</head>
<body>
    <h2>關鍵詞問答列表</h2>
    <table border="1">
        <tr>
            <th>編號</th>
            <th>關鍵詞類型</th>
            <th>回答</th>
            <th>操作</th>
        </tr>
        <c:forEach var="keywordQa" items="${keywordQaList}">
            <tr>
                <td>${keywordQa.serialId}</td>
                <td>${keywordQa.kwTypes}</td>
                <td>${keywordQa.kwAns}</td>
                <td>
                    <a href="updateForm.jsp?serialId=${keywordQa.serialId}">編輯</a>
                    |
                    <a href="KeywordQaServlet?action=del&serialId=${keywordQa.serialId}">刪除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="addForm.jsp">新增關鍵詞問答</a>
</body>
</html>
