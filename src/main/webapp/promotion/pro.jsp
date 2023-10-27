<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/10/24
  Time: 下午 06:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <style>
    .data-width{
      max-width:110px;
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
  </style>
  <title>促銷管理</title>
  <jsp:include page="../indexpage/head.jsp" />
  <!-- 添加 Bootstrap CSS 链接 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../indexpage/header.jsp" />
<jsp:include page="../indexpage/headpic.jsp"/>
<div class="container mt-5">
  <h1 class="text-center">促銷管理</h1>
  <hr>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th scope="col">序號</th>
      <th scope="col">促銷編號</th>
      <th scope="col">促銷名稱</th>
      <th scope="col">開始日期</th>
      <th scope="col">結束日期</th>
      <th scope="col">描述</th>
      <th scope="col">圖片</th>
      <th scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="pro" varStatus="proStatus">
      <tr>
        <td>${proStatus.count}</td>
        <td name="proId"class="data-width">${pro.promotionId}</td>
        <td name="proName">${pro.promoName}</td>
        <td class="data-width" name="startDate">${pro.startDate}</td>
        <td class="data-width" name="endDate">${pro.endDate}</td>
        <td class="dis-width" name="disc">${pro.description}</td>
        <td>
<%--            ${pro.picture}--%>
          <img src="${pageContext.request.contextPath}/pro/image?promotionId=${pro.promotionId}" class="jpg" name="pic"/>
        </td>
        <td>
          <a href="#" class="badge btn-secondary rounded fs-5" onclick="showProDetail()">促銷明細</a>
          <a href="#" class="badge btn-warning rounded fs-5">下架</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <hr>
  <div class="text-end">
  <a class="btn btn-primary badge badge-info rounded" href="${pageContext.request.contextPath}/pro/getAllTid">新增促銷活動</a>
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
    function showProDetail() {
    //數據
      let tr =$(event.target).closest('tr');
      let proId=tr.find('td[name="proId"]').text();
      getProDetail(proId);


      $('#proName').text(tr.find('td[name="proName"]').text());
      $('#disc').text(tr.find('td[name="disc"]').text());
      $('#startDate').text("開始日期:"+formatDate(new Date(tr.find('td[name="startDate"]').text())));
      $('#endDate').text("結束日期:"+formatDate(new Date(tr.find('td[name="endDate"]').text())));
      // $('#pic').html(tr.find('img').prop('outerHTML'));
      $('#pic').html(tr.find('img')[0].outerHTML);
      $('#pic').find('img').css({
        width:"800px",
        height:"400px"
      });
      let html=`<li class="list-group-item" id="ticketName">An item</li>`;
    // 使用Bootstrap的JavaScript函數來顯示模態對話框
      $('#orderDetailModal').modal('show');

  }
</script>
<!-- 添加 Bootstrap JavaScript 链接（必须在body结束前） -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.min.js"></script>
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
            <a href="#" class="card-link">編輯</a>
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
