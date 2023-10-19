<%@ page import="com.depthspace.account.model.account.service.AccountServiceImpl" %>
<%@ page import="com.depthspace.account.model.account.AccountVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous">
 </script>
<script>
	$(document).ready(function(){
		processQueryList();
		$('#Add').hide();
		//按鈕控制
		$('#btnAddSave,#btnCancel').prop('disabled',true);
		$('#btnAdd,#btnAddDel').prop('disabled',false);
	});
	
	function processQueryList(){
		$.ajax({
			type : 'post',
			url :  '<%=request.getContextPath()%>/account/account.do?action=list',
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			dataType : 'json',
			async : false,
			success : function(data){
				for(let i = 0 ; i<data.length ; i++) {
					$('#dataTable tr:last').after(
					'<tr>'
					+'<td><input type="radio" name="chooseItem" value="'+data[i].accountId+'"></td>'
					+'<td>' + data[i].accountId    +'</td>'
					+'<td>' + data[i].accountName  +'</td>'
					+'<td>' + data[i].memId  +'</td>'
					+'<td><input type="button" value="閔萱名言" onclick="processTest();"></td>'
					+'</tr>'
					)
				}
				
			}
		})
	}
	
	function processTest(){
		alert('你0分!!');
	}
	
	function processAdd() {
		//區塊控制;
		$('#list').hide();
		$('#Add').show();
		//按鈕控制
		$('#btnAddSave,#btnCancel').prop('disabled',false);
		$('#btnAdd,#btnAddDel').prop('disabled',true);
	}
	
	function processAddSave() {
		$.ajax({
			type : 'post',
			data : $('#addForm').serialize(),
			url :  '<%=request.getContextPath()%>/account/account.do?action=add',
			async : false,
			success : function(data){
				alert('新增成功');				
			}
		})
	}
	
	function processDelete() {
		var jsonObj = {};
		jsonObj.accountId = $('input[name=chooseItem]:checked').val();
		$.ajax({
			type : 'post',
			data : jsonObj,
			url :  '<%=request.getContextPath()%>/account/account.do?action=del',
			async : false,
			success : function(data){
				alert('刪除成功');	
				//清除list
				$('#dataTable tr:not(:first)').html("");
				//重新build
				processQueryList();
			}
		})
	}
	
	function processCancel() {
		//區塊控制;
		$('#list').show();
		$('#Add').hide();
		//按鈕控制
		$('#btnAddSave,#btnCancel').prop('disabled',true);
		$('#btnAdd,#btnAddDel').prop('disabled',false);
	}

</script>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>分帳表</title>
</head>
<body>
	<content class="btnContent">
		<input type="button" id="btnAdd" value="新增" onclick="processAdd();">
		<input type="button" id="btnAddSave" value="儲存" onclick="processAddSave();">
		<input type="button" id="btnCancel" value="取消" onclick="processCancel();">
		<input type="button" id="btnAddDel" value="刪除" onclick="processDelete();">
	</content>
	<content id="list">
    <h1>分帳表清單</h1>
    <table id='dataTable'>
        <tr>
        	<th>選取</th>
            <th>分帳表編號</th>
            <th>分帳表名稱</th>
            <th>會員ID</th>
            <th>功能</th>
        </tr>
    </table>
    </content>
    <content id="Add">
    	<form id="addForm" method="Post">
    		<table>
    			<tr>
    				<th>分帳表名稱</th>
    				<td>
    					<input type="text" name="accountName" id="accountName"> 
    				</td>
    			</tr>
    			<tr>
    				<th>會員ID</th>
    				<td>
    					<input type="text" name="memId" id="memId"> 
    				</td>
    			</tr>
    		</table>
    	</form>
    </content>
    
    
</body>
</html>
