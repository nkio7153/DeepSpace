//滑鼠滑過行
 $(document).ready(function(){
    $('table tbody tr').hover(function(){
        $(this).addClass('hover-highlight');
    }, function(){
        $(this).removeClass('hover-highlight'); 
    });
});
