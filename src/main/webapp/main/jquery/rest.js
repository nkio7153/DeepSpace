$(function(){

    $("input.btn_del").on("click", function(){
        let restId = $(this).closest("tr").find("td:first").text();
        console.log(restId);
    })



})

