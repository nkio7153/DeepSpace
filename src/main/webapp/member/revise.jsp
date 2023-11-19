<%@ page import="com.depthspace.member.service.MemberService"%>
<%@ page import="com.depthspace.member.model.MemVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Base64" %>

<html>
<head>
<jsp:include page="../indexpage/head.jsp" />
<title>修改會員資料 revise.jsp</title>
<style>
  	img.image {
      border-radius: 60px;
	  width: 120px;
	  height: 120px;
	  object-fit: cover;
  	}
  	#preview span.text{
        position: absolute;
        display: inline-block;
/*         left: 50%; */
        top: 50%;
        transform: translate(-50%, -50%);
        color: lightgray;
  	}
  	.image-container {
        display: flex;
      	align-items: center;
   	}
        
    .preview {
        max-width: 200px;
        max-height: 100px;
        margin-left: 100px; /* 調整預覽圖片與大頭貼之間的間距 */
        }
    .preview_jpg {
     	border-radius: 60px;
        width: 120px;
	    height: 120px;
	    object-fit: cover;
	    position: relative;
        }
   	.btn_save {
	    width: 150px;
	    font-size: 20px;
	    color: #fff;
	    background-color: #008CBA;
	    border: none;
	    padding: 10px;
	    cursor: pointer;
	    border-radius: 10px;
	    margin: 10px;
	}
	.preview_jpg::before {
	    content: "請上傳圖片";
	    position: absolute;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	    color: lightgray;
	    text-align: center;
	    width: 100%;
	}
</style>
</head>
<body>
 <jsp:include page="../indexpage/header.jsp" />
 <jsp:include page="../indexpage/headpic.jsp" />
	<h1 align="center">修改會員資料</h1>
	<form align="center" action="${pageContext.request.contextPath}/mem/modify" method="post" enctype="multipart/form-data">
		<table border="1" align="center" width="50%">
			
				<th style="display: none;">會員編號</th>
				<input type="hidden" name="memId" value="${memId}" readonly>
			
			<tr>
				<th>會員大頭貼</th>
					<td>
						<div class="image-container">
	                        <img src="data:image/jpeg;base64,${base64Image}" class="image" alt="ProfileImage" />
	                        <div class="upload-container" style="display: flex; align-items: center; margin-left: 20px;">
						        <!-- 調整 preview 的樣式 -->
						        <div class="preview" style="margin-right: 10px;">
									<img id="preview_img" src="" name="memImage" alt="" class="preview_jpg">
						        </div>
					        <input type="file" class="form-control" id="picture" name="memImage" style="width: 89px;">
					    </div>
<%-- 					    <input type="hidden" name="memImage" value="${base64Image}" /> --%>
					</td>
			</tr>
			<tr>
	            <th>帳號</th>
	            <td><input type="text" name="memAcc" value="${mem.memAcc}" required readonly></td>
	        </tr>
	        <tr>
	            <th>密碼</th>
	            <td><input type="text" name="memPwd"
					value="${mem.memPwd}" required></td>
	        </tr>
	        <tr>
	            <th>會員姓名</th>
	            <td><input type="text" name="memName"
					value="${mem.memName}" required></td>
	        </tr>
	        <tr>
	            <th>身分證字號</th>
	            <td><br>
	            <input type="text" name="memIdentity"
					value="${mem.memIdentity}" required></td>
	        </tr>
	        <tr>
	            <th>生日</th>
	            <td><input type="date" name="memBth"
					value="${mem.memBth}" required></td>
	        </tr>
	        <tr>
	            <th>性別</th>
	            <td><input type="text" name="memSex"
					value="${sex}" required></td>
	        </tr>
	        <tr>
	            <th>電子郵件</th>
	            <td><input type="email" name="memEmail"
					value="${mem.memEmail}" required></td>
	        </tr>
	        <tr>
	            <th>手機電話</th>
	            <td><input type="text" name="memTel"
					value="${mem.memTel}" required></td>
	        </tr>
	        <tr>
	            <th>地址</th>
	            <td><input type="text" name="memAdd"
					value="${mem.memAdd}" required></td>
	        </tr>
	        <tr>
	            <th>狀態</th>
	            <td><input type="text" name="accStatus"
					value="${status}" readonly></td>
	        </tr>
	        <tr>
	            <th>點數</th>
	            <td>${mem.memPoint}</td>
<!-- 	            <td><input type="text" name="memPoint" -->
<%-- 					value="${mem.memPoint}" disabled></td> --%>
	        </tr>
	        
	    </table>
    <p align="center">
		<input type="submit" class="btn_save" value="儲存會員資料">
	</p>	
     </form>
      <p align="center">
        <input type="button" class="btn_save" value="取消" onclick="history.back()">
       </p>
    
    <script type="text/javascript">
	    var file = $("#picture"); // 獲取input file元素
	    var preview_el = $("#preview_img"); // 獲取預覽圖片元素
	    //上傳檔案觸發change事件時，更換預覽圖
	    file.on("change", function() {
	        if (this.files && this.files[0]) {
	            var reader = new FileReader();
	
	            reader.onload = function(e) {
	                preview_el.attr("src", e.target.result);
	            }
	
	            reader.readAsDataURL(this.files[0]);
	        }
	    });

	</script>
	<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
