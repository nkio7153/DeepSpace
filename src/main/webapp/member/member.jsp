<%@ page import="com.depthspace.member.service.MemberService" %>
<%@ page import="java.util.List" %>
<%@ page import=" com.depthspace.member.model.MemVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
   <body>
    <div class="main-box login">
      <h3>登入</h3>
      <form action="">
        <div class="input-box">
          <i class="fas fa-envelope icon2"></i>
          <label for="">信箱</label>
          <input type="email" name="email" id="email" required />
        </div>
        <div class="input-box">
            <i class="fas fa-lock icon2"></i>
            <label for="">密碼</label>
          <input type="password" name="password" id="password" required />
        </div>
        <div class="check">
          <label><input type="checkbox" />記住我</label>
          <a href="#" id="forgetPwd">忘記密碼</a>
        </div>
        <button type="submit" class="main-btn">Login</button>
        <div class="register">
          <p>如果沒有帳號?<a href="#" class="register-link">點擊註冊</a></p>
        </div>
      </form>
    </div>
    </body>
</html>