<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <style>
    .data-width{
      width:110px;
    }
    .dis-width{
      max-width:150px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    img.jpg {
      width:200px;
      height:100px;
    }
    .hidden{
      display: none;
    }
    .img-bd{
      width:210px;
    }
    .num-width{
      width:50px;
    }

  </style>
  <title>促銷管理</title>
  <%--  include head.jsp--%>
  <jsp:include page="/backend/backIndex/head.jsp"></jsp:include>
</head>
<body>
<%--include header.jsp--%>
<jsp:include page="/backend/backIndex/header.jsp"></jsp:include>
<div class="container-fluid my-0">
  <div class="row">
    <%--    側邊欄--%>
    <div class="col-lg-2 g-3 mt-0">
      <jsp:include page="/backend/backIndex/sidebar.jsp"></jsp:include>
    </div>

    <div class="col-lg-10 g-2 transparent rounded mt-2">
      <%--      放入自己body裡的代碼--%>
        <div class="container mt-4 g-3 pb-2">
          <h3 class="text-center">促銷管理</h3>
          <hr>
          <div class="row">
            <div class="col-md-12">
              <table class="table table-bordered table-hover" width="70%">
                <thead>
                <tr>
                  <th scope="col"class="num-width">序號</th>
                  <th scope="col" class="hidden">促銷編號</th>
                  <th scope="col" class="data-width">促銷名稱</th>
                  <th scope="col" class="data-width">開始日期</th>
                  <th scope="col" class="data-width">結束日期</th>
                  <th scope="col">描述</th>
                  <th scope="col" class="img-bd">圖片</th>
                  <th scope="col" class="img-bd">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="pro" varStatus="proStatus">
                  <tr>
                    <td>${proStatus.count}</td>
                    <td name="proId"class="data-width hidden">${pro.promotionId}</td>
                    <td name="proName">${pro.promoName}</td>
                    <td name="startDate">${pro.startDate}</td>
                    <td name="endDate">${pro.endDate}</td>
                    <td class="dis-width" name="disc">${pro.description}</td>
                    <td>
                        <%--            ${pro.picture}--%>
                      <img src="${pageContext.request.contextPath}/pro/image?promotionId=${pro.promotionId}" class="jpg" name="pic"/>
                    </td>
                    <td>
                      <a href="#" class="badge btn-outline-info rounded fs-5 text-dark" onclick="showProDetail()">促銷明細</a>
                      <a href="#" class="badge btn-outline-light rounded fs-5 text-dark" onclick="deleteUpdate()">下架</a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <hr>
          <div class="text-end">
            <a class="btn btn-primary badge badge-info rounded fs-5" href="${pageContext.request.contextPath}/pro/getAllTid">新增促銷活動</a>
          </div>
        </div>
    </div>
  </div>
</div>
<script>
  let proDes = [];
  //日期顯示格式保留到 年、月、日、時、分
  function formatDate(date) {
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0');
    var day = String(date.getDate()).padStart(2, '0');
    var hours = String(date.getHours()).padStart(2, '0');
    var minutes = String(date.getMinutes()).padStart(2, '0');
    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
  }
  //處理取得回來的促銷明細
  function showData(){
    console.log("showData方法被執行了")
    for (let i = 0; i < proDes.length; i++) {
      console.log(proDes[i].ticketName);
      console.log(proDes[i].discount);
    }
    console.log("遍歷完畢")
    let html="";
    for(let i=0; i<proDes.length; i++){
      html += '<li class="list-group-item"><span>' + proDes[i].ticketName + '</span><span class="text-danger">(' + proDes[i].discount + '折)</span></li>';
    }
    let itemList=$("#itemList");
    itemList.html(html);
  }
  function deleteByProId(proId){
    let url="${pageContext.request.contextPath}/pro/delete?proId="+ proId;
    fetch(url)
            .then(function(response) {
              return response.text()
            })
            .then(function(data){
              console.log(data)
            })
            .catch(function(error){
              console.log(error);
            })
  }
  function deleteUpdate() {
    var ok = window.confirm("確定要下架嗎");
    if (ok) {
      let tr = $(event.target).closest("tr");
      let proId = tr.find('td[name="proId"]').text();
      tr.remove();
      deleteByProId(proId);
    }
  }



  //發送fetch請求取得促銷明細資料
  function getProDetail(proId) {
    let url = "${pageContext.request.contextPath}/pro/showDetail?proId=" + proId;
    fetch(url)
            .then(function (response) {
              return response.json()
            })
            .then(function (data) {
              proDes = data;
              showData();//將其顯示到頁面中
            })
            .catch(function(error){
              console.log(error);
            })
  }
  var proId;
  function showProDetail() {
    //數據
    let tr =$(event.target).closest('tr');
    proId=tr.find('td[name="proId"]').text();
    getProDetail(proId);


    $('#proName').text(tr.find('td[name="proName"]').text());
    $('#disc').text(tr.find('td[name="disc"]').text());
    $('#startDate').text("開始日期:"+formatDate(new Date(tr.find('td[name="startDate"]').text())));
    $('#endDate').text("結束日期:"+formatDate(new Date(tr.find('td[name="endDate"]').text())));
    // $('#pic').html(tr.find('img').prop('outerHTML'));
    $('#pic').html(tr.find('img')[0].outerHTML);
    $('#pic').find('img').css({
      width:"718px",
      height:"400px"
    });
    let html=`<li class="list-group-item" id="ticketName">An item</li>`;
    // 使用Bootstrap的JavaScript函數來顯示模態對話框
    $('#orderDetailModal').modal('show');

  }
  function promotionEdit(){
    window.location.href="${pageContext.request.contextPath}/pro/doEdit?proId="+proId;
  }
</script>


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
            <img class="card-img-top" alt="..." >
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
            <button class="btn btn-link card-link" onclick="promotionEdit()" >編輯</button>
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