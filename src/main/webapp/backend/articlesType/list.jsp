<%@ page import="com.depthspace.forum.model.articlestype.service.ArticlesTypeServiceImpl" %>
<%@ page import="com.depthspace.forum.model.articlestype.ArticlesTypeVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
    <title>文章類型列表</title>
    <!-- 引入 FontAwesome 圖標 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

    $(document).ready(function(){
        // 取得文章類型列表的函式
        function getTypeList() {
            $.ajax({
                url: '<%=request.getContextPath()%>/type?action=list',
                type: 'post',
                dataType: 'json',
                success: function(data) {
                    var table = "<table class='table table-bordered'><thead class='custom-thead'><tr><th class='width-id'>類型ID</th><th>類型名稱</th><th class='width-action'></th></tr></thead><tbody>";
                    $.each(data, function(index, item){
                        // 添加 text-center 使垃圾桶圖標置中
                        table += "<tr><td class='text-center'>" + item.artiTypeId + "</td><td class='text-center'>" + item.artiTypeText + "</td><td class='text-center'><button class='btn btn-danger btn-sm' onclick='deleteType(" + item.artiTypeId + ")'><i class='fas fa-trash'></i></button></td></tr>";
                    });
                    table += "</tbody></table>";
                    $("#typeList").html(table);
                },
                error: function() {
                    alert("無法獲取數據");
                }
            });
        }

        // 初始載入文章類型列表
        getTypeList();

     	// 綁定新增類型按鈕的點擊事件
        $("#btnAdd").click(function(){
            $("#Add").show(); // 顯示新增表單
            $("#btnAdd").hide(); // 隱藏新增按鈕
        });

        // 綁定取消按鈕的點擊事件
        $("#btnCancel").click(function(){
            $("#Add").hide(); // 隱藏新增表單
            $("#btnAdd").show(); // 顯示新增按鈕
        });
        
        // 綁定取消按鈕的點擊事件
        $("#btnAddSave").click(function(){
            $("#Add").hide(); // 隱藏新增表單
            $("#btnAdd").show(); // 顯示新增按鈕
        });

        // 綁定儲存按鈕的提交事件
        $("#addForm").submit(function(event){
            event.preventDefault(); // 阻止表單的原生提交
            if (validateForm()) {
            $.ajax({
                url: $(this).attr('action'),
                type: 'post',
                data: $(this).serialize(), // 序列化表單數據
                success: function(data) {
                	 // 表單提交成功後的操作
                    $("#Add").hide(); // 隱藏新增表單
                    $("#addForm")[0].reset(); // 重置表單欄位為初始值
                    getTypeList(); // 刷新文章類型列表
                	}
            	});
            }
        });        
        // 這裡可以放置其他的事件處理函式
    });
    
    function deleteType(artiTypeId) {
        // 首先顯示確認彈窗
        Swal.fire({
            title: "您確定要刪除嗎?",
            text: "您將無法恢復此操作！",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "是的",
            cancelButtonText: "取消"
        }).then((result) => {
            // 如果用戶確認了刪除操作
            if (result.isConfirmed) {
                var jsonObj = { artiTypeId: artiTypeId };
                $.ajax({
                    type: 'post',
                    data: jsonObj,
                    url: '<%=request.getContextPath()%>/type?action=del',
                    async: true,
                    success: function(data) {
                        // 刪除成功，顯示提示彈窗
                        Swal.fire("已刪除！", "類型已被刪除。", "success").then(() => {
                            // 從頁面中移除該類型的元素
                            $('#typeList table tr').each(function() {
                                if ($(this).find('td:first').text() == artiTypeId) {
                                    $(this).remove();
                                }
                            });
                        });
                    }
                });
            }
        });
    }
    
    function validateForm() {
        var artiTypeText = $("#artiTypeText").val();
        
        // 檢查是否為空
        if (!artiTypeText) {
            alert("類型名稱不能為空！");
            $("#artiTypeText").val('');
            return false;
        }

        // 檢查是否為中文
        if (!/^[\u4e00-\u9fa5]+$/.test(artiTypeText)) {
            alert("類型名稱只能輸入中文！");
            $("#artiTypeText").val('');
            return false;
        }

        return true;
    }
    </script>
    <style>
    	
   		.width-action {
        	max-width: 90px; /* 限制操作欄位（垃圾桶圖標）的最大寬度 */
    	}
    	
    	.type{
    		margin-top: 10px;
    	}
    	
    	.marg{
		margin-top: 10px;
		margin-bottom: 10px;
		}
		
		.margs{
		margin-top: -10px;
		margin-bottom: 20px;
		}
	</style>
</head>
<body>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
	<div class="row">
	  
	<div class="col-lg-2 g-3 mt-1">
	<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
	</div>
	
	<div class="col-lg-10 g-2 transparent rounded mt-1">
	<h3 class="text-center mt-2">文章類型列表</h3>
	 <hr class="my-0">
		<div class="row">
            <!-- 新增按鈕，默認顯示 -->
            <div class="col-lg-12 marg">
                <button id="btnAdd" class="btn btn-primary mb-2 float-right">新增類型</button>
            </div>
        </div>
        <!-- 新增類型的表單，默認隱藏 -->
        <form id="addForm" method="Post" action="${pageContext.request.contextPath}/type?action=add">
	        <div id="Add" class="row" style="display: none;">
	            <!-- 類型名稱輸入框 -->
	            <div class="col-lg-4 margs">
	                <input type="text" name="artiTypeText" id="artiTypeText" class="form-control">
	            </div>
	            <!-- 儲存和取消按鈕 -->
	            <div class="col-lg-4 margs">
	                <input type="submit" id="btnAddSave" value="儲存" class="btn btn-primary">
	                <input type="button" id="btnCancel" value="取消" class="btn btn-secondary">
	            </div>
	        </div>
        </form>
		<div id="typeList">
			<!-- 這裡將顯示從 Servlet 獲取的數據 -->
		</div>


<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>
	<!-- 引入 Bootstrap JS 和 Popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
