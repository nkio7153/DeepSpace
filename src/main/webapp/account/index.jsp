<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

<script>
    function processSelect() {
        $.ajax({
            type: 'GET',
            url: 'list.jsp', // URL of the page you want to load
            dataType: 'html',
            success: function(data) {
                // Replace the content of a specific element with the loaded content
                $('#content-container').html(data);
            },
            error: function() {
                alert('Failed to load the page.');
            }
        });
    }
</script>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首頁</title>
</head>
<body>
    <div class="btnContent">
        <input type="button" id="btnSelectAll" value="查詢所有分帳表" onclick="processSelect();">
    </div>
    
    <!-- This is where the content will be loaded -->
    <div id="content-container"></div>
</body>
</html>
