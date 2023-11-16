<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

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
    font-size: 12px !important; /* 設定較小的字體大小 */
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
}

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
	           <c:forEach var="ticketType" items="${listAll}">
	               <tr>
	                   <td>${ticketType.ticketTypeId}</td>
	                   <td contenteditable="true">${ticketType.typeName}</td>
	                   <td contenteditable="true">${ticketType.description}</td>
	                   <td><button type="button" style="none" class="btn btn-primary edit-btn"><i class="fas fa-edit" style="background-color: #63bfc9; border-color: #63bfc9;"></i></button></td>
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
    $('.edit-icon').on('click', function() {
        var currentRow = $(this).closest('tr');
        currentRow.find('td').attr('contenteditable', 'true');
        currentRow.find('td:eq(1)').focus(); 
    });
	//存入資料
    $('td[contenteditable="true"]').on('blur', function() {
        var currentRow = $(this).closest('tr');
        var ticketTypeId = currentRow.find('td:eq(0)').text().trim();
        var typeName = currentRow.find('td:eq(1)').text().trim();
        var description = currentRow.find('td:eq(2)').text().trim();
    	if(typeName === ''){
    		Swal.fire({
    			icon: 'error',
    			title: '票券類型名稱不能為空!!!'
    		});
    		return;
    	}
        var data = {
            ticketTypeId: ticketTypeId,        	
            typeName: typeName,
            description: description
        }; 
        $.ajax({
        	url:'<%=request.getContextPath()%>/tickettypesmg/edit',
        	type:'POST',
        	contentType:'application/json',
        	data:JSON.stringify(data),
        	dataType:'json',

        	success:function(response){
        		if(response.status === "success") {
                    Swal.fire({
                        position: 'top',
                        title: '已完成修改',
                        showConfirmButton: false,
                        timer: 500
                    });
        		}
        	},
        	error: function(xhr, status, error) {
        		console.error('輸入失敗', xhr, status, error);
        	}
        });
    });
  });
	// 新增
    $('.add-button').on('click', function() {
        var newRow = $('<tr>').append(
            $('<td>').text('編號自動產生').attr('contenteditable', 'false'),
            $('<td>').text('').attr('contenteditable', 'true'),
            $('<td>').text('').attr('contenteditable', 'true'),
            $('<td>').append($('<button>').addClass('btn btn-primary save-btn').text('保存'))
        );
        $('table tbody').append(newRow);
    // 新增儲存
    $(document).on('click', '.save-btn', function() {
        var currentRow = $(this).closest('tr');
        var typeName = currentRow.find('td:eq(1)').text().trim();
        var description = currentRow.find('td:eq(2)').text().trim();
	    	if(typeName === ''){
	    		Swal.fire({
	    			icon: 'error',
	    			title: '票券類型名稱不能為空!!!'
	    		});
	    		return;
	    	}
        var data = {
            typeName: typeName,
            description: description

        }; 
            $.ajax({
            	url:'<%=request.getContextPath()%>/tickettypesmg/add',
            	type:'POST',
            	contentType:'application/json',
            	data:JSON.stringify(data),
            	dataType:'json',

            	success:function(response){
            		if(response.status === "success") {
                        Swal.fire({
                            position: 'center',
                            title: '已完成新增',
                            showConfirmButton: false,
                            timer: 500
                        });
                        currentRow.find('.save-btn').replaceWith('<i class="fas fa-edit btn btn-primary edit-btn" style="background-color: #63bfc9; border-color: #63bfc9;"></i>');
                        currentRow.find('td:eq(0)').text(response.newTicketTypeId);
            		}
            	},
            	error: function(xhr, status, error) {
            		console.error('輸入失敗', xhr, status, error);
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
