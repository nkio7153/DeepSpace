<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="send_email.php" method="post">
    <label for="message">請輸入文字:</label>
    <textarea id="message" name="message" rows="4" cols="50"></textarea><br>

    <label for="email">請輸入您的信箱:</label>
    <input type="email" id="email" name="email"><br>

    <input type="submit" value="傳送">
</form>
