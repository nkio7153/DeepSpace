$(function(){

    $("input.btn_del").on("click", function(){
        let restId = $(this).closest("tr").find("td:first").text();
//        console.log(restId);
    })
    $('img').on('error', function(){
            $(this).attr('src', 'images/404.jpg');
    })
    




})

