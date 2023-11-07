<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
 <jsp:include page="../indexpage/head.jsp" />
<meta charset="UTF-8">
<title>新增論壇文章</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4" crossorigin="anonymous"></script>
<script>
function handleFileSelect(evt) {
    var fileInput = evt.target;
    if (fileInput.files && fileInput.files[0]) {
        var file = fileInput.files[0];

        if (file.type !== "image/jpeg") {
            alert("只能選擇 JPG 檔案");
            return;
        }

        var reader = new FileReader();
        reader.onload = function(e) {
            var preview = document.getElementById("preview");
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(file);
    } else {
        alert("未選擇檔案或不支持的檔案類型");
    }
}
</script>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
        }
        #Add {
            width: 80%;
            margin: 20px auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        input[type="text"],
        textarea {
            width: 95%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
		#fileSelect, #preview {
		  display: inline-block; /* 讓元素水平顯示 */
		  vertical-align: top; /* 確保元素從頂部對齊 */
		}
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 0;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp" />
<div id="Add">
    <form id="addForm" method="Post" action="${pageContext.request.contextPath}/forumArticles.do?action=add" enctype="multipart/form-data">
        <table>
            <tr>
                <th>會員ID</th>
                <td><input type="text" name="memId" id="memId"></td>
            </tr>
            <tr>
                <th>訊息ID</th>
                <td><input type="text" name="msgId" id="msgId"></td>
            </tr>
            <tr>
                <th>文章類型ID</th>
                <td><input type="text" name="artiTypeId" id="artiTypeId"></td>
            </tr>
            <tr>
                <th>文章標題</th>
                <td><input type="text" name="artiTitle" id="artiTitle"></td>
            </tr>
            <tr>
                <th>文章內容</th>
                <td><textarea name="artiText" id="artiText" rows="10" cols="50"></textarea></td>
            </tr>
            <tr>
                <th>讚數</th>
                <td><input type="text" name="artiLk" id="artiLk"></td>
            </tr>
           
            <tr>
                <th>文章圖片</th>
                <td>
                   <input type="file" id="fileSelect" accept="image/jpeg" onchange="handleFileSelect(event)">
                   <img id="preview" src="#" alt="預覽圖片" style="width: 300px; height: auto; display: none;">
                   <input type="hidden" name="artiImg" id="artiImg">
				</td>
           </tr>
            </table>
        <input type="hidden" name="artiTime" id="artiTime" value="<%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>">
        <input type="submit" id="btnAddSave" value="儲存">
        <input type="button" id="btnCancel" value="取消" onclick="window.location.href='<%=request.getContextPath()%>/forumArticles/list.jsp'">
        </form>
</div>
<jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
