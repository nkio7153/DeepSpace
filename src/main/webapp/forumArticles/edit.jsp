<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../indexpage/head.jsp" />
    <meta charset="UTF-8">
    <title>編輯論壇文章</title>
    <script src="https://cdn.tiny.cloud/1/3u5wm513cfl9shskemk13n936fx56zfolvirppkzq4b61na9/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4" crossorigin="anonymous"></script>
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
    </style>
</head>
<body>
    <script>

// tinymce.init({
// 	selector: '#artiText',
//     plugins: 'ai tinycomments mentions anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage tableofcontents mergetags powerpaste tinymcespellchecker autocorrect a11ychecker typography inlinecss',
//     toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
//     tinycomments_mode: 'embedded',
//     tinycomments_author: 'Author name',
//     mergetags_list: [
//       { value: 'First.Name', title: 'First Name' },
//       { value: 'Email', title: 'Email' },
//     ],
//     ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
//   });

	  tinymce.init({
	    selector: '#artiText',
	  });
</script>
<script>
function previewImage(event) {
    // 獲取上傳的檔案
    var file = event.target.files[0];

    // 檢查檔案類型是否為 JPEG
    if(file.type === "image/jpeg") {
        var reader = new FileReader();
        reader.onload = function() {
            var output = document.getElementById('preview');
            output.src = reader.result;
            output.style.display = 'block';
        };
        reader.readAsDataURL(file);
    } else {
        // 如果不是 JPEG，顯示提示並重置 file input
        alert("只能選擇 JPEG 格式的圖片檔案。");
        document.getElementById('fileSelect').value = "";
        document.getElementById('preview').style.display = 'none';
    }
}
</script>
    <jsp:include page="../indexpage/header.jsp" />
    <jsp:include page="../indexpage/headpic.jsp" />

    <div id="Edit">
        <form id="editForm" method="Post" action="${pageContext.request.contextPath}/forumArticles.do?action=update" enctype="multipart/form-data">
            <table>
                <tr>
                    <th>文章類型ID</th>
                    <td><input type="text" name="artiTypeId" id="artiTypeId" value="${param.artiTypeId}"></td>
                </tr>
                <tr>
                    <th>文章標題</th>
                    <td><input type="text" name="artiTitle" id="artiTitle" value="${param.artiTitle}"></td>
                </tr>
                <tr>
                    <th>文章內容</th>
                    <td><textarea name="artiText" id="artiText" rows="10" cols="50">${param.artiText}</textarea></td>
                </tr>
                <tr>
                    <th>文章圖片</th>
                    <td>
                        <input type="file" name="artiImgStr" id="fileSelect" accept="image/jpeg" onchange="previewImage(event)">
                        <img id="preview" src="data:image/jpeg;base64,${param.base64Str}" alt="預覽圖片" style="width: 300px; height: auto; ${not empty param.base64Str ? 'display: block;' : 'display: none;'}">
                    </td>
                </tr>
            </table>
            <input type="hidden" name="msgId" id="msgId" value="${param.msgId}">
            <input type="hidden" name="memId" id="memId" value="${param.memId}">
            <input type="hidden" name="artiTime" id="artiTime" value="${param.formattedDate}">
            <input type="hidden" name="articleId" id="articleId" value="${param.articleId}">
            <input type="hidden" name="artiLk" id="artiLk" value="${param.artiLk}">
            <input type="submit" id="btnSave" value="儲存">
            <input type="button" id="btnCancel" value="取消" onclick="window.location.href='<%=request.getContextPath()%>/forumArticles.do?action=getmemlist'">
        </form>
    </div>

    <jsp:include page="../indexpage/footer.jsp" />
</body>
</html>
