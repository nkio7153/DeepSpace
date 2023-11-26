<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"> -->
<script>
$(document).ready(function() {
    $.ajax({
        url: '<%=request.getContextPath()%>/report?action=list',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            var rows = '';
            $.each(data, function(key, value){
            	var statusText = value.reportStatus == 0 ? '未審核' :
                (value.reportStatus == 1 ? '已審核已通過' : '已審核未通過');
            	var date = new Date(value.reportTime);
                var formattedDate = formatDate(date);
                var adminText = value.adminId ? value.adminId : '-';
                rows += '<tr>';
                rows += '<td class="text-center">' + value.articleReportId + '</td>';
                rows += '<td class="text-center">' + value.articleId + '</td>';
                rows += '<td class="text-center">' + value.reportId + '</td>';
                rows += '<td class="text-center">' + adminText + '</td>';
                rows += '<td class="text-center report-content">' + value.reportContent + '</td>';
                rows += '<td class="text-center">' + formattedDate + '</td>';
                rows += '<td class="text-center">' + statusText + '</td>';
                rows += '<td class="text-center"><button class="edit-btn" data-article-report-id="' + value.articleReportId + '" data-article-id="' + value.articleId + '" data-report-id="' + value.reportId + '" data-admin-id="' + value.adminId + '" data-report-status="' + value.reportStatus + '" data-report-content="' + value.reportContent + '" data-report-time="' + formattedDate + '"><i class="fas fa-pencil-alt"></i></button></td>';
                rows += '</tr>';
            });
            $("#reportTable tbody").html(rows);
        }
    });
    
    //更改狀態的ajax
    $('#saveChanges').click(function() {
        // 從表單中獲取數據
        var formData = $('#editReportForm').serialize();
		console.log(formData);
        // 發送 AJAX 請求到後端
        $.ajax({
            url: '<%=request.getContextPath()%>/report?action=update',
            type: 'POST',
            data: formData, // 序列化表單數據
            success: function(response) {
                // 這裡可以根據後端回應更新 UI，例如重新加載列表
                $('#editReportModal').modal('hide');
                alert('更新成功');
                location.reload();
            }
        });
    });
    
  //選擇審核狀態的ajax
    $('#statusFilter').change(function() {
        var selectedStatus = $(this).val();
        var ajaxUrl = '<%=request.getContextPath()%>/report?action=';
        if (selectedStatus !== '') {
            ajaxUrl += 'liststatus&reportStatus=' + selectedStatus;
        } else {
            ajaxUrl += 'list';
        }

        $.ajax({
            url: ajaxUrl,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                updateTable(data);
            },
            error: function(error) {
                console.log(error);
                alert('數據加載失敗');
            }
        });
    });
});

function formatDate(date) {
    var year = date.getFullYear();
    var month = padZero(date.getMonth() + 1); // 月份從0開始
    var day = padZero(date.getDate());
    var hours = padZero(date.getHours());
    var minutes = padZero(date.getMinutes());
    var seconds = padZero(date.getSeconds());
    return year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}

function padZero(value) {
    return value < 10 ? '0' + value : value;
}

function openEditModal(reportAr) {
    $('#articleReportIdInput').val(reportAr.articleReportId);
    $('#articleIdInput').val(reportAr.articleId);
    console.log(reportAr.articleId);
    $('#reportIdInput').val(reportAr.reportId);
    $('#adminIdInput').val(reportAr.adminId);
    $('#reportContentInput').val(reportAr.reportContent);
    $('#reportTimeInput').val(reportAr.reportTime);
    $('#reportStatusSelect').val(reportAr.reportStatus);
    $('#editReportModal').modal('show');
}


$(document).on('click', '.edit-btn', function() {
    var $btn = $(this);
    var reportAr = {
        articleReportId: $btn.data('article-report-id'),
        articleId: $btn.data('article-id'),
        reportId: $btn.data('report-id'),
        adminId: $btn.data('admin-id'),
        reportStatus: $btn.data('report-status'),
        reportContent: $btn.data('report-content'),
        reportTime: $btn.data('report-time')
    };
    openEditModal(reportAr);
});

//選擇完下拉選單後呈現的版面
function updateTable(data) {
    var rows = '';
    $.each(data, function(key, value){
    	var statusText = value.reportStatus == 0 ? '未審核' :
        (value.reportStatus == 1 ? '已審核已通過' : '已審核未通過');
    	var date = new Date(value.reportTime);
        var formattedDate = formatDate(date);
        var adminText = value.adminId ? value.adminId : '-';
        rows += '<tr>';
        rows += '<td class="text-center">' + value.articleReportId + '</td>';
        rows += '<td class="text-center">' + value.articleId + '</td>';
        rows += '<td class="text-center">' + value.reportId + '</td>';
        rows += '<td class="text-center">' + adminText + '</td>';
        rows += '<td class="text-center report-content">' + value.reportContent + '</td>';
        rows += '<td class="text-center">' + formattedDate + '</td>';
        rows += '<td class="text-center">' + statusText + '</td>';
        rows += '<td class="text-center"><button class="edit-btn" data-article-report-id="' + value.articleReportId + '" data-article-id="' + value.articleId + '" data-report-id="' + value.reportId + '" data-admin-id="' + value.adminId + '" data-report-status="' + value.reportStatus + '" data-report-content="' + value.reportContent + '" data-report-time="' + formattedDate + '"><i class="fas fa-pencil-alt"></i></button></td>';
        rows += '</tr>';
    });
    $('#reportTable tbody').html(rows);
}

$(document).on('click', '.report-content', function() {
    var fullContent = $(this).text(); // 從單元格中獲取完整內容
    $('#fullReportContent').text(fullContent); // 將內容設置到模態框中
    $('#reportContentModal').modal('show'); // 顯示模態框
});

//搜尋符合文章編號的文章
function showArticle() {
    var articleId = $('#articleIdInput').val();
    if (articleId) {
        $.ajax({
            url: '<%=request.getContextPath()%>/report?action=list',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // 使用 filter 函數來找出所有匹配的文章
                var matchingArticles = data.filter(article => article.articleId == articleId);
                var rows = '';
                
                if (matchingArticles.length > 0) {
                    // 遍歷所有找到的文章並為每篇文章建立表格行
                    $.each(matchingArticles, function(index, selectedArticle) {
                        var statusText = selectedArticle.reportStatus == 0 ? '未審核' :
                            (selectedArticle.reportStatus == 1 ? '已審核已通過' : '已審核未通過');
                        var date = new Date(selectedArticle.reportTime);
                        var formattedDate = formatDate(date);
                        var adminText = selectedArticle.adminId ? selectedArticle.adminId : '-';
                        rows += '<tr>';
                        rows += '<td class="text-center">' + selectedArticle.articleReportId + '</td>';
                        rows += '<td class="text-center">' + selectedArticle.articleId + '</td>';
                        rows += '<td class="text-center">' + selectedArticle.reportId + '</td>';
                        rows += '<td class="text-center">' + adminText + '</td>';
                        rows += '<td class="text-center report-content">' + selectedArticle.reportContent + '</td>';
                        rows += '<td class="text-center">' + formattedDate + '</td>';
                        rows += '<td class="text-center">' + statusText + '</td>';
                        rows += '<td class="text-center"><button class="edit-btn" data-article-report-id="' + selectedArticle.articleReportId + '" data-article-id="' + selectedArticle.articleId + '" data-report-id="' + selectedArticle.reportId + '" data-admin-id="' + selectedArticle.adminId + '" data-report-status="' + selectedArticle.reportStatus + '" data-report-content="' + selectedArticle.reportContent + '" data-report-time="' + formattedDate + '"><i class="fas fa-pencil-alt"></i></button></td>';
                        rows += '</tr>';
                    });
                    $("#reportTable tbody").html(rows); 
                } else {
                    alert('沒有找到匹配的文章');
                }
            },
            complete: function() {
                $('#articleIdInput').val('');
            }
        });
    }
}

//查詢全部文章
function performNewAction() {
    $.ajax({
        url: '<%=request.getContextPath()%>/report?action=list',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            var rows = '';
            $.each(data, function(key, value){
            	var statusText = value.reportStatus == 0 ? '未審核' :
                (value.reportStatus == 1 ? '已審核已通過' : '已審核未通過');
            	var date = new Date(value.reportTime);
                var formattedDate = formatDate(date);
                var adminText = value.adminId ? value.adminId : '-';
                rows += '<tr>';
                rows += '<td class="text-center">' + value.articleReportId + '</td>';
                rows += '<td class="text-center">' + value.articleId + '</td>';
                rows += '<td class="text-center">' + value.reportId + '</td>';
                rows += '<td class="text-center">' + adminText + '</td>';
                rows += '<td class="text-center report-content">' + value.reportContent + '</td>';
                rows += '<td class="text-center">' + formattedDate + '</td>';
                rows += '<td class="text-center">' + statusText + '</td>';
                rows += '<td class="text-center"><button class="edit-btn" data-article-report-id="' + value.articleReportId + '" data-article-id="' + value.articleId + '" data-report-id="' + value.reportId + '" data-admin-id="' + value.adminId + '" data-report-status="' + value.reportStatus + '" data-report-content="' + value.reportContent + '" data-report-time="' + formattedDate + '"><i class="fas fa-pencil-alt"></i></button></td>';
                rows += '</tr>';
            });
            $("#reportTable tbody").html(rows);
        }
    });
}

</script>
<style>
    table {
        width: 95%;
        border-collapse: collapse;
        background: #fff;
        background: #fff;
    	margin-left: 35px;
    	margin-top: 10px;
    	margin-bottom: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        text-align: center; /* 加入這行代碼來置中表頭 */
    }
    tr:hover {
        background-color: #ddd;
    }
    .text-center {
        text-align: center;
    }
    
    .report-content {
    	max-width: 150px; /* 設置最大寬度 */
    	text-overflow: ellipsis; /* 文本超出時顯示省略號 */
    	overflow: hidden; /* 隱藏超出部分的文本 */
    	white-space: nowrap; /* 保持文本在一行內 */
	}
	
	.marg{
	margin-top: 10px;
	margin-left: 15px;
	}
	
	.select {
	    background-color: #63bfc9;
	    color: white;
	    padding: 8px 40px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	}
	.selects {
	    background-color: #63bfc9;
	    color: white;
	    padding: 8px 40px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	}
	.write{
		height: 40px;
    	width: 270px;
	}
	.container {
	    margin: 10px; /* 設定外部容器的邊距 */
	}

	.write{
	    margin-right: 120px;
	}
	.select{
	    margin-left: -110px;
	    margin-right: 10px;
	}
	.selects{
	    margin-right: 30px;
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
	 <h3 class="text-center mt-2">檢舉列表</h3>
	        <hr class="my-0">
	        <div class="container" style="display: flex; justify-content: space-between;">
	        	<div class="filter-by-status marg">
				    <label for="statusFilter">審查狀態：</label>
				    <select id="statusFilter" name="reportStatus">
				        <option value="">請選擇</option>
				        <option value="0">未審核</option>
				        <option value="1">已審核已通過</option>
				        <option value="2">已審核未通過</option>
				    </select>
				</div>
			    <div>
			        <input type="text" id="articleIdInput" placeholder="輸入文章編號" class="write">
			        <button onclick="showArticle()" class="select">搜尋文章</button>
			        <button onclick="performNewAction()" class="selects">顯示所有文章</button>
			    </div>			    
			</div>
<table id="reportTable" class="reportTable">
    <thead>
        <tr>
            <th>檢舉編號</th>
            <th>檢舉文章編號</th>
            <th>檢舉人編號</th>
            <th>管理員編號</th>
            <th>檢舉內容</th>
            <th>檢舉日期</th>
            <th>處理狀況</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <!-- AJAX 動態數據將被填充在這裡 -->
    </tbody>
</table>

<%--  include --%>	
		</div>
	</div>		
</div>
<%--  include end --%>

<!-- 模態框 -->
<div class="modal fade" id="editReportModal" tabindex="-1" aria-labelledby="editReportModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editReportModalLabel">修改狀況</h5>
      </div>
      <div class="modal-body">
        <form id="editReportForm">
	      <!-- 隱藏的輸入元素 -->
	      <input type="hidden" id="articleReportIdInput" name="articleReportId">
	      <input type="hidden" id="articleIdInput" name="articleId">
	      <input type="hidden" id="reportIdInput" name="reportId">
	      <input type="hidden" id="adminIdInput" name="adminId">
	      <input type="hidden" id="reportContentInput" name="reportContent">
	      <input type="hidden" id="reportTimeInput" name="reportTime">
	      <!-- 可修改的處理狀況選擇框 -->
	      <div class="mb-3">
	        <label for="reportStatusSelect" class="form-label">處理狀況</label>
	        <select class="form-control" id="reportStatusSelect" name="reportStatus">
	          <option value="0">未審核</option>
	          <option value="1">已審核已通過</option>
	          <option value="2">已審核未通過</option>
	        </select>
	      </div>
	    </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
        <button type="button" class="btn btn-primary" id="saveChanges">儲存變更</button>
      </div>
    </div>
  </div>
</div>

<!-- 檢舉內容模態框 -->
<div class="modal fade" id="reportContentModal" tabindex="-1" aria-labelledby="reportContentModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="reportContentModalLabel">檢舉內容</h5>
      </div>
      <div class="modal-body" id="fullReportContent">
        <!-- 完整的檢舉內容將在這裡顯示 -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
