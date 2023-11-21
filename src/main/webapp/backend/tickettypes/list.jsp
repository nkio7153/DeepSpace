<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">  
<style>
.add-button {
    background-color: #63bfc9; 
    color: white; 
    padding: 8px 40px; 
    border: none; 
    border-radius: 5px;
    cursor: pointer; 
}
.add-button:hover {
    background-color: #5b969c; 
}
.table-hover tbody tr:hover {
    background-color: #f5f5f5;
}
.table td, .table th {
	text-align: center;
	vertical-align: middle;
	padding: .3rem;

}
.table{
/* 	max-width: 90%; */
}
.table .edit-btn {
     background: #63bfc9;
     border: #63bfc9;
/*      padding: 0; */
 }
.swal2-title {
    font-size: 14px !important; 
}
/* 新增 */
.add-button {
	position: relative;
	padding: 8px 30px;
	width: 150px;
	height: 40px;
	cursor: pointer;
	display: flex;
	align-items: center;
	border: 1px solid #63bfc9;
	background-color: #63bfc9;
	border-radius:5px;
	border: none;
}

.add-button, .button__icon, .button__text {
	transition: all 0.3s;
}

.add-button .button__text {
	transform: translateX(30px);
	color: #fff;
	font-weight: 600;
}

.add-button .button__icon {
	position: absolute;
	transform: translateX(109px);
	height: 100%;
	width: 39px;
	background-color: #63bfc9;
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius:5px;
	border: none;
}

.add-button .svg {
	width: 30px;
	stroke: #fff;
}

.add-button:hover {
	background: #63bfc9;
}ㄋ

.add-button:hover .button__text {
	color: transparent;
}

.add-button:hover .button__icon {
	width: 148px;
	transform: translateX(0);
}

.add-button:active .button__icon {
	background-color: #2e8644;
}

.add-button:active {
	border: 1px solid #2e8644;
}

.btn-primary {
	background: #63bfc9; 
	border: #63bfc9; 
}
.btn-primary:hover {
	background: #85cdd5 !important; 
	border: #85cdd5; 
}
.btn-success{
	background: #63bfc9 !important;  
	border: #63bfc9 !important;  
}
.btn-success:hover {
	background: #85cdd5 !important;  
	border: #85cdd5; 
}
</style>

<title>票券類型列表</title>

<%--  include --%>
	<jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
  
</head>

	<body>

	<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
	<div class="container-fluid my-0">
	<div class="row">
	  
	<div class="col-lg-2 g-3 my-0">
	<jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
	</div>
	
	<div class="col-lg-10 g-2 transparent rounded my-0">
	
	<%-- include end--%>
	
	
	<div class="container mt-5">
		<h5>票券類型</h5>
		       <table class="table table-bordered table-hover">
	           <thead>
	               <tr>
	                   <th>編　　號</th>
	                   <th>類型名稱</th>
	                   <th>類型描述</th>
	                   <th>操　　作</th>
	               </tr>
	           </thead>
	           <tbody>
	           <c:forEach var="ticketType" items="${listAll}" varStatus="status">
		   			<td style="display: none;">${ticketType.ticketTypeId}</td>
		            <td>${status.index + 1}</td>
		            <td>
	           			<span class="typeName">${ticketType.typeName}</span>
		                <input type="text" class="form-control typeName-input" style="display: none;">
		            </td>
		            <td>
	           			<span class="description">${ticketType.description}</span>
		                <input type="text" class="form-control description-input" style="display: none;">
		            </td>		            
		            <td>
		                <span class="edit-icon btn btn-primary edit-btn">
		                    <i class="fas fa-edit"></i>
		                </span>
		                <span class="save-icon btn btn-success save-btn" style="display: none;">
		                    <i class="fas fa-save"></i>
		                </span>
		            </td>
		            </tr>
	               </c:forEach>
	           </tbody>
	       </table>
	       <button type="button" class="add-button">
			  <span class="button__text">新增</span>
			  <span class="button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
			</button>	       
	   </div>
	<br/>   
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
  
  <script>
  //點選編輯進入編輯，離開後保存
  $(document).ready(function() {
	$(document).on('click', '.edit-btn', function() {
        var currentRow = $(this).closest('tr');
        var typeName = currentRow.find('.typeName').text();
        var description = currentRow.find('.description').text();
        currentRow.find('.typeName').hide();
        currentRow.find('.typeName-input').val(typeName).show().focus();
        currentRow.find('.edit-icon').hide();
        currentRow.find('.save-icon').show();
        currentRow.find('.description').hide();
        currentRow.find('.description-input').val(description).show().focus();
        currentRow.find('.edit-icon').hide();
        currentRow.find('.save-icon').show();
    });
	//存入資料
    $(document).on('click', '.save-btn', function() {
        var currentRow = $(this).closest('tr');
        var ticketTypeId = currentRow.find('td:eq(0)').text().trim();
        var typeName = currentRow.find('.typeName-input').val().trim();
		var description = currentRow.find('.description-input').val().trim();
        if(typeName === ''){
            Swal.fire({
                icon: 'error',
                title: '類型名稱不能為空！'
            });
            return;
        }	
        $.ajax({
            url: '<%=request.getContextPath()%>/tickettypesmg/edit',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ticketTypeId: ticketTypeId, typeName: typeName, description: description}),
            dataType: 'json',
            success: function(response) {
                if(response.status === "success") {
                    Swal.fire({
                        position: 'center',
                        title: '完成修改',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    currentRow.find('.typeName').text(typeName).show();
                    currentRow.find('.typeName-input').hide();
                    currentRow.find('.edit-icon').show();
                    currentRow.find('.save-icon').hide();
                    currentRow.find('.description').text(description).show();
                    currentRow.find('.description-input').hide();
                    currentRow.find('.edit-icon').show();
                    currentRow.find('.save-icon').hide();    
                }
            },
            error: function(xhr, status, error) {
                console.error('更新失敗', xhr, status, error);
            }
        });
    });
});
	// 新增
    $('.add-button').on('click', function() {
    	var rowCount = $('table tbody tr').length + 1; // 流水號
        var newRow = $('<tr>').append(
           $('<td>').text(rowCount), 
           $('<td>').append($('<input>').addClass('form-control typeName-input')),
           $('<td>').append($('<input>').addClass('form-control description-input')),
           $('<td>').append(
               $('<span>').addClass('save-icon btn btn-success Nsave-btn').append(
                   $('<i>').addClass('fas fa-save')
               )
           )
       );
        $('table tbody').append(newRow);
    // 新增儲存
        $(document).on('click', '.Nsave-btn', function() {
            var currentRow = $(this).closest('tr');
            var typeName = currentRow.find('.typeName-input').val().trim();
    		var description = currentRow.find('.description-input').val().trim();
            
            if(typeName === ''){
                Swal.fire({
                    icon: 'error',
                    title: '類型名稱不能為空！'
                });
                return;
            }

            $.ajax({
                url: '<%=request.getContextPath()%>/tickettypesmg/add',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({typeName: typeName, description: description}),
                dataType: 'json',
                success: function(response) {
                    if(response.status === "success") {
                        Swal.fire({
                            position: 'center',
                            title: '已完成新增',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        currentRow.find('.typeName-input').replaceWith($('<span>').addClass('typeName').text(typeName));
                        currentRow.find('.description-input').replaceWith($('<span>').addClass('description').text(description));
                        currentRow.find('.save-icon').replaceWith(
                            $('<span>').addClass('edit-icon btn btn-primary edit-btn').append(
                                $('<i>').addClass('fas fa-edit')
                            )
                        );
                    }
                },
                error: function(xhr, status, error) {
                    console.error('新增失敗', xhr, status, error);
                }
            });
        });
        });
  </script>
	    
	<%--  include --%>	
			</div>
		</div>		
	</div>
	<%--  include end --%>
	</body>
</html>
