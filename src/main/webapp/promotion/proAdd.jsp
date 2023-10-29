<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/10/25
  Time: 上午 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../indexpage/head.jsp"/>
    <style>
        .circle {
            width: 21px;
            height: 21px;
            border-radius: 50%;
            background-color: lightskyblue;
            margin-top:-8px;
            margin-left:5px;
        }

        .plus {
            color: white;
            font-size: 15px;
            font-weight: bold;
            margin-top:-1px;
            margin-left:1px;
        }
        .circle2{
            width: 21px;
            height: 21px;
            border-radius: 50%;
            background-color: lightcoral;
            margin-top:-30px;
            margin-left:330px;
        }
        .dash{
            color: white;
            font-size: 25px;
            font-weight: bold;
            margin-top:-3px;
            margin-left:-1px;
        }
        .preview_jpg{
            max-width:200px ;
            max-height:100px ;
        }
        .dis_bd{
            height:230px;
        }
        .mb-10{
            margin-bottom: -10px;
        }
    </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp"/>
<jsp:include page="../indexpage/headpic.jsp"/>
<div class="container mt-5">
    <h1 class="text-center">促銷資訊</h1>
        <hr>
        <form action="${pageContext.request.contextPath}/pro/save" method="post" class="row" enctype="multipart/form-data">
<%--            第一排--%>
            <div class="col-md-2"></div>
            <div class="col-md-4">
                <label for="promoName" class="form-label">促銷名稱:</label>
                <input type="text" class="form-control" name="promoName" id="promoName"><br>
            </div>

            <div class="col-md-4">
                <label for="startDate" class="form-label">開始日期:</label>
                <input type="datetime-local" class="form-control" name="startDate" id="startDate"><br>
            </div>
            <div class="col-md-2"></div>
<%--        第二排--%>
            <div class="col-md-2"></div>
            <div class="col-md-4">
                <label for="description" class="form-label">描述:</label>
                <textarea class="form-control dis_bd" name="description" id="description" rows="5"></textarea>
            </div>

            <div class="col-md-4">
                <label for="endDate" class="form-label">結束日期:</label>
                <input type="datetime-local" class="form-control" name="endDate" id="endDate"><br>
                <label for="picture" class="form-label">圖片</label>
                <div class="preview">
                    <img id="preview_img" src="" name="pciture1" alt="請上傳促銷圖片" class="preview_jpg">
                </div>
                <input type="file" class="form-control" id="picture" name="picture"><br>
                <%--                <input type="hidden" name="base64Image" value="${base64Image}">--%>
            </div>
            <div class="col-md-2"></div>
<%--            第四排--%>
                <div class="col-md-2 mt-2 mb-10"></div>
                <div class="col-md-2 mt-2 mb-10">
                    <label for="discount" class="form-label">折扣:</label>
                    <select id="discount" name="discount" >
                        <c:forEach var="discount" items="${discountList}">
                            <option value="${discount}">${discount}</option>
                        </c:forEach>
                    </select>
                    <label class="form-label">折</label>
                </div>
                <div class="col-md-4 mt-2 mb-10">
                    <label for="ticketId" class="form-label">選擇促銷票券:</label>
                        <select id="ticketId" name="ticketId" >
                            <c:forEach var="ticket" items="${list}">
                                <option value="${ticket.ticketId}">${ticket.ticketName}</option>
                            </c:forEach>
                        </select>
                </div>
                <div class="col-md-4 mt-2 mb-10"></div>
    <%--            第四排--%>
            <div class="container offset-5" id="afterSelector">
                <div class="col-md-5 mt-2"></div>
                <div class="col-md-4 d-flex align-items-center"> <!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
                    <label for="ticketId" class="form-label mt-3">添加促銷票券</label>
                    <div class="circle d-flex align-items-center justify-content-center ml-2 mt-2" id="circle">
                        <span class="plus">+</span>
                    </div>
                </div>
                <div class="col-md-3 mt-2"></div>
            </div>


            <div class="col-md-12 mt-2 d-flex justify-content-center">
                <button type="submit" class="btn btn-primary">新增</button>
            </div>

        </form>
</div>
<script>
    let circle=$("#circle");
    let circle2=$("#circle2");
    let select_html=`
        <div class="container">
            <div class="row">
            <div class="col-md-4 mt-2" name="se"></div>
            <div class="col-md-4 mt-2" id="selector" name="se">
                <label for="ticketId" class="form-label">選擇促銷票券:</label>
                <select id="ticketId" name="ticketId">
                    <c:forEach var="ticket" items="${list}">
                        <option value="${ticket.ticketId}">${ticket.ticketName}</option>
                    </c:forEach>
                </select>
                <div class="circle2 d-flex align-items-center justify-content-center ml-2" id="dash">
                    <span class="dash">-</span>
                </div>
            </div>
            <div class="col-md-4 mt-2" name="se"></div>
            </div>
        </div>`;
    $(document).ready(function(){
        //圓形+
        circle.on("mouseover", function () {
            $(this).css({
                "background-color": "blue",
                "width":"25px",
                "height":"25px"
            });
        })
        circle.on("mouseout", function () {
            $(this).css({
                "background-color": "lightskyblue",
                "width":"21px",
                "height":"21px"
            });
        })
        circle.on("click", function(){
            $(this).closest(".row").find("#afterSelector").before(select_html);

        })
        // 圓形-
        // 委派事件到動態生成的 .circle2 元素
        $('body').on('mouseover', '.circle2', function() {
            $(this).css({
                "background-color": "red",
                "width": "25px",
                "height": "25px"
            });
        });

        $('body').on('mouseout', '.circle2', function() {
            $(this).css({
                "background-color": "lightcoral",
                "width": "21px",
                "height": "21px"
            });
        });

        $('body').on('click', '.circle2', function() {
            $(this).closest(".container").remove();
        });
        //讀取圖片
        // var preview_img = function(file) {
        //
        //     var reader = new FileReader(); // 用來讀取檔案
        //     reader.readAsDataURL(file); // 讀取檔案
        //     reader.addEventListener("load", function () {
        //         let img_str = '<img src="' + reader.result + '" class="preview_img" >';
        //         preview_el.innerHTML = img_str;
        //     })
        // }
    })
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
<jsp:include page="../indexpage/footer.jsp"/>
</body>
</html>
