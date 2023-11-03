<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>深度漫遊 - 系統</title>
    
    <style>
      *{
        font-family:微軟正黑體;  
      }
      
      body{
        background-color: white;
      }
      
      #username, #password, h3, #fullname, #comfirm_password,#username2, #password2{
        width: 200px;
        height: 20px;
        margin: 10px;
        color: #df5334;
      }
      
      h5{
        margin: 20px;
        color: #a3a2a3;
      }
      
      h5:hover{
        color: black;
      }
      
      #container1, #container2{
        //margin: 50px;
        padding: 10px;
        width: 230px;
        height: 300px;
        background-color: white;
        border-radius: 5px;
        border-top: 10px solid #df5334;
        box-shadow: 0 0px 70px rgba(0, 0, 0, 0.1);
        
        /*定位對齊*/
        position:relative;   
        margin: auto;
        top: 100px;
        //text-align:center;  
      }
      
      .system_name{
        /*定位對齊*/
        position:relative;   
        margin: auto;
        top: 100px;
        text-align:center; 
      }
      
      .submit{
        color: white;  
        background: #df5334;
        width: 200px;
        height: 30px;
        margin: 10px;
        padding: 5px;
        border-radius: 5px;
        border: 0px;
      }
      
      .submit:hover{
        background: #db6937;
      }
      
      #container2{
        visibility: hidden;   /*剛開始消失*/
        height: 350px;
      }
      
      
      #copyright{
        text-align: center;
        color: #a3a2a3;
        margin: -200px 0px 0px 0px;
        font-size: 14px;
      }
      
      input{
        padding: 5px;
        border: none; 
        border:solid 1px #ccc;
        border-radius: 5px;
      }
      
      </style>
</head>
<body>
    <div class="system_name">
        <h2>深度漫遊 - 系統</h2>
    </div>

    <div class="login_page">
        <div id="container1">

            <div class="login">  
                <h3>登入 Login</h3>
                
                <form action="LoginServlet" method="post">
                    <input type="text" id="username" name="username" placeholder="帳號" required>
                    <div class="tab"></div>
                    <input type="password" id="password" name="password" placeholder="密碼" required>
                    <div class="tab"></div>
                    <input type="submit" value="登入" class="submit">
                </form>

                <h5 onclick="show_hide()">註冊帳號</h5>
                
            </div><!-- login end-->
        </div><!-- container1 end-->
    </div><!-- login_page end-->

    <div class="signup_page">
        <div id="container2">

            <div class="signup">  
                <h3>註冊 Sign Up</h3>
                
                <form action="RegisterServlet" method="post">
                    <input type="text" id="fullname" name="fullname" placeholder="使用者全名" required>
                    <div class="tab"></div>
                    <input type="text" id="username2" name="username" placeholder="帳號" required>
                    <div class="tab"></div>
                    <input type="password" id="password2" name="password" placeholder="密碼" required>
                    <div class="tab"></div>
                    <input type="password" id="comfirm_password" name="comfirm_password" placeholder="確認密碼" required>
                    <div class="tab"></div>            
                    <input type="submit" value="註冊" class="submit">
                </form>

                <h5 onclick="show_hide()">登入帳號</h5>
                
            </div><!-- signup end-->
        </div><!-- container2 end-->
    </div><!-- signup_page end-->

    <div id="copyright">
        <h4> </h4><!--因為js，會跑版--> 
    </div>    

    <script>
        function show_hide() {
            var login = document.getElementById("container1");
            var signup = document.getElementById("container2");

            if (login.style.display === "none") {
                login.style.display = "block";
                document.getElementById("username").value="";
                document.getElementById("password").value="";
                signup.style.display = "none";
            } else {
                login.style.display = "none";
                signup.style.display = "block";
                document.getElementById("fullname").value="";
                document.getElementById("username2").value="";
                document.getElementById("password2").value="";
                document.getElementById("comfirm_password").value="";
            }
        }
    </script>
</body>
</html>
