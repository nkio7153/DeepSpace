<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/10/29
  Time: 上午 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
  <jsp:include page="../indexpage/head.jsp" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    img.jpg{
    width:318px ;
    height:160px ;
    }
    .dis-width{
      max-width:318px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    .hidden{
      display:none;
    }
    .gray{
      background-color: lightgrey;
    }
  </style>
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>
<h3 class="text-primary bg-light p-3 border border-primary text-center shadow">促銷資訊</h3>
<div class="container">
  <div class="row">
    <c:forEach items="${list}" var="pro">
      <div class="col-md-4 d-flex justify-content-center mt-5" name="card">
        <div class="card" style="width: 20rem;">
          <div>
            <img src="${pageContext.request.contextPath}/pro/image?promotionId=${pro.promotionId}" class="jpg card-img-top" name="pic"/>
          </div>
          <div class="card-body">
            <input class="hidden" name="proId" value="${pro.promotionId}">
            <h5 class="card-title" name="proName">${pro.promoName}</h5>
            <p class="card-text dis-width" name="disc">${pro.description}</p>
            <div class="d-flex justify-content-end">
              <h6 class="card-text" name="startDate">開始日期:${pro.startDate}</h6><br>
            </div>
            <div class="d-flex justify-content-end">
              <h6 class="card-text" name="endDate">結束日期:${pro.endDate}</h6>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
<hr>
</div>
<script>
  //日期顯示格式保留到 年、月、日、時、分
  function formatDate(date){
    var year= date.getFullYear();
    var month =String(date.getMonth()+1).padStart(2,'0');
    var day=String(date.getDate()).padStart(2,'0');
    var hours=String(date.getHours()).padStart(2,'0');
    var minutes=String(date.getMinutes()).padStart(2,'0');
    return year+"-"+month+"-"+day+"-"+hours+":"+minutes;
  }
  //處理取得回來的資訊
  function showData(){
    console.log("showData被執行了")
    let html2="";
    for(let i=0; i<proDes.length; i++){
      html2 += '<li class="list-group-item"><a href="${pageContext.request.contextPath}/ticketproduct/item?ticketId='+proDes[i].ticketId+'"><span>' + proDes[i].ticketName + '</span><span class="text-danger">(' + proDes[i].discount + '折)</span></a></li>';
    }
    let itemList=$("#itemList");
    itemList.html(html2);
  }
  var proDes;
  //用ajax取得促銷明細資料
  function getProDetail(proId){
    let url="${pageContext.request.contextPath}/pro/showDetail?proId="+proId;
    fetch(url)
            .then(function(response){
              return response.json();
            })
            .then(function(data){
              console.log(data);
              proDes=data;
              showData();
            })
            .catch(function(error){
              console.log(error);
            })
  }
  //動態生成促銷卡片
  $(document).on("click","[name='card']",function(){
    // console.log(target.find("input[name='disc']"));
    let target=$(this);
    let proId=target.find("input[name='proId']").val();
    console.log(proId);
    getProDetail(proId);
    $("#proName").text(target.find("h5[name='proName']").text())
    $("#disc").text(target.find("p[name='disc']").text())
    $("#startDate").text(target.find("h6[name='startDate']").text());
    $("#endDate").text(target.find("h6[name='endDate']").text());
    // $("#pic").html(target.find("img[name='pic']").html());
    $("#pic").html(target.find("img[name='pic']").clone());
    $('#pic').find('img').css({
      width:"800px",
      height:"400px"
    });
    $('#orderDetailModal').modal('show');
  })
  //載入時日期格式轉換
  $(document).ready(function(){
    $("[name='card']").each(function(){
      $(this).find("[name='startDate']").text("開始日期:"+formatDate(new Date($(this).find("[name='startDate']").text())));
      $(this).find("[name='endDate']").text("結束日期:"+formatDate(new Date($(this).find("[name='endDate']").text())));
    })
    //促銷卡片滑過時觸發變灰
    $("[name='card']").hover(
      function(){
        $(this).find(".card-body").addClass("gray");
      },
      function(){
        $(this).find(".card-body").removeClass("gray");
      }
    )
  })

</script>
<jsp:include page="../indexpage/footer.jsp" />
</body>


<!-- 模態對話框 -->
<div class="modal fade" id="orderDetailModal" tabindex="-1" aria-labelledby="orderDetailModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="orderDetailModalLabel" >促銷明細</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 這裡可以放置訂單明細的內容 -->
        <%--        卡片--%>
        <div class="card" style="width: 45rem;" >
          <div id="pic">
            <img src="https://picsum.photos/1000/500?random=11" class="card-img-top" alt="..." >
          </div>
          <div class="card-body">
            <h5 class="card-title" id="proName">促銷名稱:</h5>
            <p class="card-text" id="disc">描述:</p>
            <div class="d-flex justify-content-end">
              <h6 class="card-text" id="startDate">開始日期:</h6><br>
            </div>
            <div class="d-flex justify-content-end">
              <h6 class="card-text" id="endDate">結束日期:</h6>
            </div>
          </div>
          <div class="card-body">
            <h5 class="card-title">適用票券:</h5>
            <ul class="list-group list-group-flush" id="itemList">
              <li class="list-group-item" id="ticketName">An item</li>
            </ul>
          </div>
          <div class="card-body">
          </div>
        </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
      </div>
    </div>
  </div>
</div>

</html>
