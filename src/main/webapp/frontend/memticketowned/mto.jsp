<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2023/11/2
  Time: 上午 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的票券</title>
    <jsp:include page="/indexpage/head.jsp"/>
    <style>
        .hidden {
            display: none;
        }

        .card-horizontal {
            display: flex;
            flex: 11 auto;
        }

        .btn-outline-primary:hover {
            color: #ffffff; /
            background-color: #007bff;
            border-color: #007bff;
        }

        .ticket {
            background-color: #fff;
            position: relative;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            margin: 20px;
        }
        .voucher-sent{
            font-weight:bold;
            color:green;
            font-size:20px;
        }
        .card-body{
            position:relative;
        }
         .watermark {
             position: absolute;
             top: 50%;
             left: 50%;
             transform: translate(-50%, -50%) rotate(-45deg);
             z-index: 10;
             font-size: 35px; /* 根据需要调整大小 */
             font-weight:bold;
             color: rgba(211, 211, 211, 0.5);
             white-space: nowrap; /* 防止浮水印被换行 */
             top: 50%; /* 定位 */
             left: 50%;
             transform: translate(-50%, -50%) rotate(-35deg);
         }
         .card-text{
             line-height:25px;
         }
         #btn{
             border-radius: 5px;
             padding:3px;
             border: transparent;
         }

        /*@media (min-width: 768px) {*/
        /*    .container, .container-lg, .container-md, .container-sm {*/
        /*        min-width: 1080px !important;*/
        /*    }*/
        /*}*/

    </style>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.3.1/jspdf.umd.min.js"></script>

</head>
<body>
<jsp:include page="/indexpage/header.jsp"/>
<jsp:include page="/indexpage/headpic.jsp"/>
<h3 class="text-primary bg-light p-3 border border-primary text-center shadow">我的票券</h3>
<div class="container mt-4">
<%--    <h1 class="text-center my-4">我的票券</h1>--%>
<%--    <hr>--%>
    <c:if test="${mtoPageQty > 0}">
        <b><font color=red>第${currentPage}/${mtoPageQty}頁</font></b>
    </c:if>
        <div class="row">
            <c:forEach items="${list}" var="mto" varStatus="mtoStatus">
                <div class="col-md-12 mb-4 card-ticket ticket">
                    <div class="card shadow h-100 rounded">
                        <div class="row g-0">
                            <div class="col-md-5 position-relative">
                                <img src="${pageContext.request.contextPath}/tsc/image?serialId=${mto.serialId}" class="card-img-top rounded-start" alt="..."> <!-- 图片覆盖整个卡片顶部宽度 -->
                                <div class="position-absolute bottom-0 start-0 m-3 mx-2 px-2">
                                    <a href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=${mto.ticketId}"type="button" id="btn" class="btn btn-primary btn-sm fs-4">票券詳情</a>
                                </div>
                                <div class="position-absolute bottom-0 end-0 m-3 mx-2 px-2">
                                    <span class="badge bg-success rounded-pill p-2 fs-4">$${mto.discountPrice}</span>
                                </div>
                            </div>
                            <div class="col-md-7 d-flex flex-column justify-content-between">
                                <div class="card-body">
                                    <div class="watermark">深度漫遊-電子票券</div>
                                    <span class="voucher-sent">憑證已發送</span>
                                    <h5 class="card-title fs-3 fw-bold" id="ticketName"> ${mto.ticketName}</h5>
                                    <span class="hidden" id="ticketId">${mto.ticketId}</span>
                                    <p class="card-text fs-5">訂單編號:<span id="orderId">${mto.orderId}</span></p>
                                    <p class="card-text fs-5" name="status">${mto.statusOfUse}</p>
                                    <p class="card-text offset-7 fs-4">使用期限: ${mto.expiryDate}</p>
                                </div>
                                <div class="card-footer bg-transparent border-0">
                                    <button name="down" type="button" class="btn btn-primary w-100">憑證下載</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
        <c:if test="${list.isEmpty()}">
            <div align="center">
                <h1>暫無票券</h1>
            </div>
        </c:if>

    <!-- 分頁 -->
    <div>
        <nav>
            <ul class="pagination justify-content-center">
                <!-- "至第一頁" 只在非第一頁時顯示 -->
                <c:if test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/mto/memList?page=1">第一頁</a>
                    </li>
                </c:if>

                <!-- "上一頁" 如果當前頁是第一頁則隱藏 -->
                <c:if test="${currentPage - 1 != 0}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/mto/memList?page=${currentPage - 1}"
                                             aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                    </a></li>
                </c:if>

                <!-- 動態顯示頁碼，根據總頁數PageQty生成 -->
                <c:forEach var="i" begin="1" end="${mtoPageQty}" step="1">
                    <li class="page-item ${i == currentPage ? 'active' : ''}"><a
                            class="page-link"
                            href="${pageContext.request.contextPath}/mto/memList?page=${i}">${i}</a>
                    </li>
                </c:forEach>

                <!-- "下一頁" 如果當前頁是最後一頁則隱藏 -->
                <c:if test="${currentPage + 1 <= mtoPageQty}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/mto/memList?page=${currentPage + 1}"
                                             aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                    </a></li>
                </c:if>

                <!-- "至最後一頁" 只在非最後一頁時顯示 -->
                <c:if test="${currentPage != mtoPageQty}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/mto/memList?page=${mtoPageQty}">尾頁</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>




</div>
<script>

    $(document).ready(function(){
        $("[name='status']").each(function(){
            switch ($(this).text()){
                case '0':
                    $(this).text("未使用");
                    break;
                case '1':
                    $(this).text("已使用");
                    break;
                case '2':
                    $(this).text("已過期");
                    break;
            }
        })
    })
    //取得票券資料
    //票券名稱
    //使用日
    //訂購人
    //訂單編號
    //數量

    //使用方式
    //注意事項
    $("[name='down']").on("click",function(){
        console.log("進入下載憑證方法");
        let target=$(this).closest(".row");
        let ticketId=target.find("#ticketId").text();
        target.find("[name='status']").text('已下載');
        $("#ticket").text(target.find("#ticketName").text());
        $("#useDate").text(moment().format('YYYY-MM-DD'));
        $("#order").text(target.find("#orderId").text());
        $('#ticketModal').modal('show');
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();


        const base64Font = 'eee';

// 将字体添加到 VFS
        doc.addFileToVFS('mingliu.ttf', toBase64(base64Font));
        doc.addFont('mingliu.ttf');

// 使用自定义字体
        doc.setFont('PMingLiU');

// 写入中文
        doc.text('您的中文内容', 10, 10);
        doc.save('document.pdf');

        let ticketName = target.find("#ticketName").text();
        let orderId = target.find("#orderId").text();
        let useDate = moment().format('YYYY-MM-DD');
        let memName = "${authenticatedMem.memName}";
        // 添加內容到 PDF
        doc.text("票券名稱: " + ticketName, 10, 10);
        doc.text("使用日期: " + useDate, 10, 20);
        doc.text("訂購人: " + memName, 10, 30);
        doc.text("訂單編號: " + orderId, 10, 40);

        // 保存 PDF 文件
        doc.save('ticket_' + ticketId + '.pdf');
    })

    function toUTF8Array(str) {
        var utf8 = [];
        for (var i = 0; i < str.length; i++) {
            var charcode = str.charCodeAt(i);
            if (charcode < 0x80) utf8.push(charcode);
            else if (charcode < 0x800) {
                utf8.push(0xc0 | (charcode >> 6),
                    0x80 | (charcode & 0x3f));
            }
            else if (charcode < 0xd800 || charcode >= 0xe000) {
                utf8.push(0xe0 | (charcode >> 12),
                    0x80 | ((charcode>>6) & 0x3f),
                    0x80 | (charcode & 0x3f));
            }
            // surrogate pair
            else {
                i++;
                // UTF-16 encodes 0x10000-0x10FFFF by subtracting 0x10000 and splitting the
                // 20 bits of 0x0-0xFFFFF into two halves
                charcode = 0x10000 + (((charcode & 0x3ff)<<10)
                    | (str.charCodeAt(i) & 0x3ff));
                utf8.push(0xf0 | (charcode >>18),
                    0x80 | ((charcode>>12) & 0x3f),
                    0x80 | ((charcode>>6) & 0x3f),
                    0x80 | (charcode & 0x3f));
            }
        }
        return utf8;
    }

    function toBase64(str) {
        var utf8Bytes = encodeURIComponent(str).replace(/%([0-9A-F]{2})/g,
            function toSolidBytes(match, p1) {
                return String.fromCharCode('0x' + p1);
            });
        return btoa(utf8Bytes);
    }



</script>
<!-- 引入Bootstrap JavaScript文件（必要時） -->
<jsp:include page="/indexpage/footer.jsp"/>
</body>

<!-- 模態對話框 -->
<div class="modal fade" id="ticketModal" tabindex="-1" aria-labelledby="ticketModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title voucher-sent" id="ticketModalLabel" >深度空間憑證</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- 這裡可以放置訂單明細的內容 -->
                <%--        卡片--%>
                <div class="card" style="width: 45rem;" >
                    <div class="card-body">
                        <h5 class="card-title fw-bold" id="ticket">票券名稱:</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">使用日:<span class="fw-bold" id="useDate"></span></li>
                            <li class="list-group-item">訂購人:<span class="fw-bold">${authenticatedMem.memName}</span></li>
                            <li class="list-group-item">訂單編號:<span class="fw-bold" id="order"></span></li>
                            <li class="list-group-item">數量:<span class="fw-bold">1張</span></li>
                        </ul>
                        <img style="position: absolute; width:160px; top: 0px; right: 0;" src="${pageContext.request.contextPath}/images/qrcode.jpg">
                    </div>
                </div>
                    <div class="card-body">
                        <h5 class="card-title fw-bold">使用方式:</h5>
                        <ul class="list-group list-group-flush" id="itemList">
                            <li class="list-group-item">1.票券有效性: 此票券僅對應於所購買的活動或景點及指定日期有效。</li>
                            <li class="list-group-item">2.不得轉售: 票券一經購買，不可轉讓或轉售給他人。</li>
                            <li class="list-group-item">3.退票政策: 請查看您購票時的退票政策。一般情況下，活動開始前一定時間內可能允許退票。</li>
                            <li class="list-group-item">4.個人物品: 請注意保管好您的個人物品。主辦方或場地不對個人物品的丟失或損壞負責。</li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title fw-bold">注意事項:</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">1.展示票券: 到達活動或景點入口時，請向工作人員展示此電子票券。</li>
                            <li class="list-group-item">2.憑證驗證: 工作人員將會掃描您的票券上的二維碼或條形碼，以驗證票券的有效性。</li>
                            <li class="list-group-item">3.入場: 票券驗證成功後，您將獲得入場許可。請按照工作人員的指引進入。</li>
                            <li class="list-group-item">4.活動時間: 請注意活動的開始和結束時間，並儘量提前到達，避免遲到。</li>
                            <li class="list-group-item">5.氣候因素: 若活動是在戶外舉行，請關注天氣預報並做好相應準備。</li>
                        </ul>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
            </div>
        </div>
    </div>
</div>


</html>
