

//回到上一頁
    function index(){
    document.location.href=
        "${pageContext.request.contextPath}/tsc/index"
    }
    //全部刪除確認
    function del1(memId, ticketId){
    console.log("memId:", memId, "ticketId:", ticketId);
    var ok=window.confirm("確定要刪除嗎");
    if(ok){
    document.location.href="${pageContext.request.contextPath}/tsc/delete1?memId="+memId+"&ticketId="+ticketId
}
}
    //確認刪除
    function delAll(memId){
    var ok=window.confirm("確定要刪除嗎");
    if(ok){
    document.location.href="${pageContext.request.contextPath}/tsc/deleteAll?memId="+memId
}
}

    $(document).ready(function() {
        let totalPrice=0;
        $('.sub').each(function(){
            const sub = parseFloat($(this).text());
            if (!isNaN(sub)) {
                totalPrice += sub;
            }
        })
        $('.sub2').text(totalPrice);
        let sub2=parseFloat($('.sub2').text());
        let coupen=parseFloat($('.coupen').text());
        let lastPrice=sub2-coupen;
        $('.total').text(lastPrice);

        //讓後端更新數據
    $('.num').on("blur" ,function() {
        console.log("觸發blur事件")
        // const self=$(this);
        let memId=$(this).closest('tr').find('.mem').text();
        let ticketId=$(this).closest('tr').find('.tid').text();
        let quantity=$(this).val();
        //----------- 打包資料 (start)
        let tscData = {
            memId: memId,
            ticketId: ticketId,
            quantity: quantity
        };

        // 將資料打包進 URLSearchParams()
        let formDataUrlEncoded = new URLSearchParams();
        for (let key in tscData) {
            formDataUrlEncoded.append(key, tscData[key]);
        }
        //----------- 打包資料 (end)

        // --------------------------------- 送出 Ajax 請求
        fetch("${pageContext.request.contextPath}/tsc/flush", {
            method: "post",
            body: formDataUrlEncoded
        })
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                // if (data!=null) {
                //   // 更新小計價格
                //   let price = parseInt($(self).closest('tr').find('.price').text());
                //   let sub = quantity * price;
                //   $(self).closest('tr').find('.sub').text(sub);
                //   // alert("更新成功~");
                // }
            })
        // .catch(function(error) {
        //   console.error('更新失敗:', error);
        // });
    });
    $('.num').on("change" ,function() {
    console.log("觸發change事件")
    let quantity=$(this).val();
    let price = parseInt($(this).closest('tr').find('.price').text());
    let sub=quantity * price;
    $(this).closest('tr').find('.sub').text(sub);

        let totalPrice=0;
        $('.sub').each(function(){
            const sub = parseFloat($(this).text());
            if (!isNaN(sub)) {
                totalPrice += sub;
            }
        })

        $('.sub2').text(totalPrice);

        let sub2=parseFloat($('.sub2').text());
        let coupen=parseFloat($('.coupen').text());
        let lastPrice=sub2-coupen;
        $('.total').text(lastPrice);
})
});
