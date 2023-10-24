<%@ page import="com.depthspace.account.model.account.service.AccountServiceImpl" %>
<%@ page import="com.depthspace.account.model.account.AccountVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>消費項目</title>
<script>
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous">
  
	$(document).ready(function(){
		processQueryList();
	});
  
  function processQueryList(){
		$.ajax({
			type : 'post',
			url :  '<%=request.getContextPath()%>/accountItem.do?action=list',
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			dataType : 'json',
			async : false,
			success : function(data){
				for(let i = 0 ; i<data.length ; i++) {
					$('#dataTable tr:last').after(
					'<tr>'
					+'<td><input type="radio" name="chooseItem" value="'+data[i].accountItemId+'"></td>'
					+'<td>' + data[i].accountItemId    +'</td>'
					+'<td>' + data[i].accountItem  +'</td>'
					+'<td>' + data[i].date  +'</td>'
					+'<td>' + data[i].consumeAccount  +'</td>'
					+'<td><input type="button" value="唯宸名言" onclick="processTest();"></td>'
					+'</tr>'
					)
				}
				
			}
		})
	}
  function processTest(){
		alert('我是甲甲<3');
	}
 </script>
</head>
<body>
		<div id="list">
			<h1>消費項目清單</h1>
			<form action="">
				<table id='dataTable'>
					<tr>
						<th>選取</th>
						<th>消費項目編號</th>
						<th>消費項目名稱</th>
						<th>消費日期</th>
						<th>消費金額</th>
						<th>功能</th>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>