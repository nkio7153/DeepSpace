<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.depthspace.admin.model.AdminVO" %>
<%@ page import="com.depthspace.admin.service.HbAdminService" %>
<%@ page import="java.util.List" %>

<html>
  <head>
  	<title>新增餐廳</title>
    <%-- include head.jsp--%>
    <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">--%>
    <script src="https://cdn.ckeditor.com/4.16.1/basic/ckeditor.js"></script>
  </head>
	
	<style>
	    #uploadimg {
		    width: 300px;
            height: 150px;
		}
	</style>
  <body>
    <%--include header.jsp--%>
    <jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
    <div class="container-fluid my-0">
      <div class="row">
        <%-- 側邊欄--%>
        <div class="col-lg-2 g-3 mt-1">
          <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
        </div>

        <div class="col-lg-10 g-2 transparent rounded mt-1">
	          <%-- 放入自己body裡的代碼--%>
	          <form id="toUpdate" class="row g-2 p-3" action="${pageContext.request.contextPath}/backend/Rest.do?action=add" method="post" enctype="multipart/form-data">
	          	  <div class="col-12">
				    <label for="restName" class="form-label">餐廳名稱</label>
				    <label class="text-danger">${errorMsgs.restName}</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="restName" name="restName" value="${rest.restName}">
				  </div>
				  
				  <div class="col-12">
				    <label for="restTel" class="form-label">餐廳電話</label>
				    <label class="text-danger">${errorMsgs.restTel}</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="restTel" name="restTel" value="${rest.restTel}">
				  </div>
				  
				  <div class="col-12">
				    <label for="restAddress" class="form-label">餐廳地址</label>
				    <div class="row">
					    <div class="col-3 mb-2">
					        <select name="city" id="city" class="form-control" required>
					            <option value="">請選縣市</option>
					            <c:forEach var="typeItem" items="${citylist}">
					                <option value="${typeItem.cityId}">${typeItem.cityName}</option>
					            </c:forEach>
					        </select>
					    </div>
					    <div class="col-3 mb-2">
					        <select name="area" id="area" class="form-control" required>
					            <option value="">請選地區</option>
					        </select>
					    </div>
					</div>
				    <label class="text-danger">${errorMsgs.restAddress}</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="restAddress" name="restAddress" value="${rest.restAddress}">
				  </div>
				  
				  <div class="col-12">
				    <label for="restType" class="form-label">餐廳類型</label>
				    <label class="text-danger">${errorMsgs.restType}</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input type="text" class="form-control" id="restType" name="restType" value="${rest.restType}">
				  </div>
				  
				  <div class="col-12">
				    <label for="restOpen" class="form-label">營業時間</label>
				    <label class="text-danger">${errorMsgs.restOpen}</label>
				  </div>
				  <div class="col-6 mb-2">
				    <input id="restOpen" type="text" class="form-control" name="restOpen" value="${rest.restOpen}">
				  </div>
				  
				  <div class="col-12">
				    <label for="restStatus" class="form-label">餐廳上/下架</label>
				  </div>
				  <div class="col-auto mb-2">
				    <select id="restStatus" class="form-select" name="restStatus">
						<option value="1">上架</option>
					    <option value="0" selected>下架</option>
				    </select>
				  </div>
				  
				  <div class="col-12">
				    <label for="bookingLimit" class="form-label bookingLimit">預設可訂位人數</label>
				    <label class="text-danger">${errorMsgs.bookingLimit}</label>
				  </div>
				  <div class="row">
				    <div class="col-2 mb-2">
				        <div class="bookingLimit">早上</div>
				        <input type="number" class="form-control bookingLimit" id="amLimit" name="amLimit" value="${rest.amLimit}">
				    </div>
				    <div class="col-2 mb-2">
				        <div class="bookingLimit">中午</div>
				        <input type="number" class="form-control bookingLimit" id="noonLimit" name="noonLimit" value="${rest.noonLimit}">
				    </div>
					<div class="col-2 mb-2">
				        <div class="bookingLimit">晚上</div>
				        <input type="number" class="form-control bookingLimit" id="pmLimit" name="pmLimit" value="${rest.pmLimit}">
				    </div>
				</div>
				  
				  <div class="col-12">
				    <label for="restText" class="form-label">餐廳簡介</label>
				    <label class="text-danger">${errorMsgs.restText}</label>
				  </div>
				  <div class="col-12 mb-2">
				    <textarea class="form-control" id="restText" name="restText" rows="4" required>${rest.restText}</textarea>
					<script>
						CKEDITOR.replace('restText');
					</script>
				  </div>
				  
				  <div class="col-12">
					<label for="formFile" class="form-label">圖片上傳</label>
				  </div>
				  <div class="col-12 mb-2 preview_jpg">
					<img id="uploadimg" src="${pageContext.request.contextPath}/static/images/rest/r_${rest.restId}.jpg" onerror="this.src='${pageContext.request.contextPath}/static/images/rest/404.jpg'">
				  </div>
				  <div class="col-auto mb-2">
					<input class="form-control" type="file" id="formFile" name="uploadimg">
				  </div>
					
				  <div class="col-12">
				    <label for="adminId" class="form-label">管理員</label>
				  </div>
				  <select class="col-3" id="adminId" name="adminId">
					  <c:forEach var="admin" items="${adminlist}">
					  	<option value="${admin.adminId}">${admin.adminAcc}</option>
					  </c:forEach>
				  </select>
				  
				  <div class="col-12">
				    <button type="submit" class="btn btn-primary">新增</button>
				  </div>
			</form>
        </div>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/member/js/add.js"></script>
    <script>
    	$(function(){
    		
    		$("#formFile").on('change', function() {
    			let file = this.files[0];
    			console.log(file);
    			// 判斷檔案
    			if (file && file.type === 'image/jpeg') {
    	            // 使用 FileReader 讀取檔案內容
    	            let reader = new FileReader();
    	            reader.onload = function (e) {
    	                // 將讀取到的檔案內容顯示在 img 元素上
    	            	$('#uploadimg').attr('src', e.target.result);
    	            };
    	            // 讀取檔案
    	            reader.readAsDataURL(file);
    	        } else {
    	        	alert("請上傳jpg檔");
    	        	$(this).val('');
    	        }
    		});
    		
    	})
    </script>
  </body>
</html>
