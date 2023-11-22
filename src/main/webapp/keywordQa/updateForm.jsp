<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.depthspace.keywordqa.model.KeywordQaVO"%>
<%@ page import="com.depthspace.keywordqa.model.*"%>
<%@ page import="com.depthspace.keywordqa.controller.*"%>
<%@ page import="com.depthspace.keywordqa.service.*"%>
<html>
<head>
    <title>更新關鍵詞問答</title>
</head>
<body>
    <h2>更新關鍵詞問答</h2>
    <form action="KeywordQaServlet?action=update" method="post">
        編號: <input type="text" name="serialId" value="${param.serialId}" readonly><br>
        關鍵詞類型: <input type="text" name="kwTypes" value="${param.kwTypes}"><br>
        回答: <input type="text" name="kwAns" value="${param.kwAns}"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
