<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3pV0n4V7KGx6Bl2OJHsE7L7ZC3ZT3HsGpH4MdXU8lx7Er3pM6XpGg5yJq9" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<style>
   .card-custom {
    width: 100%; /* 設置為100%以實現滿版寬度 */
    height: 250px; /* 讓高度自適應內容 */
    border-radius: 15px;
    border: 1px solid #ddd;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    transition: transform 0.3s ease-in-out;
}

    .card-custom:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 12px rgba(0,0,0,0.2);
    }
    .card-header-custom {
        background-color: #007bff;
        color: white;
        border-bottom: none;
        border-top-left-radius: 15px;
        border-top-right-radius: 15px;
        font-weight: bold;
    }
    .card-body-custom {
        padding: 20px;
    }
    .btn-custom {
        border-radius: 20px;
        font-size: 0.9rem;
        padding: 5px 15px;
    }
    .btn-update {
        background-color: #ffc107;
        color: white;
    }
    .btn-delete {
        background-color: #dc3545;
        color: white;
    }
    .btn-add {
    background-color: #53FF53;
    color: white;
	}
    @media (max-width: 768px) {
        .card-custom {
            margin-bottom: 20px;
        }
    }
</style>

<script type="text/javascript">
//表單點擊找出對應的function
  function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/admin/adminUpdate.jsp?adminId=" + jsonData.adminId ;
  }
  function processDelete(jsonData){
 	 $.ajax({
     //指定http參數傳輸格式為POST
     type : "POST",
     //ajax請求配置
     data : jsonData,
     //請求目標的url
     url : "<%=request.getContextPath()%>/admin.do?action=del",
     //Ajax成功後執行的function，response為回傳的值
     success : function(data) {
    	 alert('刪除成功');
    	 window.location.reload();
     },
     //Ajax失敗後要執行的function，此例為印出錯誤訊息
     error : function(xhr, ajaxOptions, thrownError) {
      alert("哇 錯了");
     }
    });
  }
  //跳轉到add.jsp頁面
  function processAdd() {
	  window.location.href= "<%=request.getContextPath()%>/admin/adminAdd.jsp"
  }
</script>
</head>
<body>

<jsp:useBean id="adminService" scope="page" class="com.depthspace.admin.model.service.AdminServiceImpl"/>
<!-- 使用Bootstrap的按鈕樣式 -->
<input type="button" class='btn btn-custom btn-add mb-3' id="btnAdd" value="新增" onclick="processAdd();" >
<div class="container">
    <div class="row row-cols-1 row-cols-md-3">
        <c:forEach var="admin" items="${adminService.allAdmins}">
            <div class="col mb-4">
                <div class="card card-custom">
                    <div class="card-header card-header-custom">
                        管理員
                    </div>
                    <div class="card-body card-body-custom">
                        <h5 class="card-title">${admin.adminId}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${admin.adminName}</h6>
                        <p class="card-text">${admin.adminStatus}</p>
                    </div>
                    <div class="card-footer bg-white">
                        <input type="button" class="btn btn-custom btn-update" value="修改" onclick="processUpdate({adminId:'${admin.adminId}'});">
                        <input type="button" class="btn btn-custom btn-delete" value="刪除" onclick="processDelete({adminId:'${admin.adminId}'});">
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>