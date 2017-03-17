$('#dateBtn1').click(function(){
    $.ajax({
        url:'deleteHistoryByPeriod.action',
        data:$('#dateForm1').serialize(),
        success:function(data){
            alert(data[0].description);
            clearForm('dateForm1');
            getCellHistoryBox();
            askCellHistoryPage();
        }
    });
});
$('#dateBtn').click(function(){
    $.ajax({
        url:'getCellHistory.action',
        data:$('#dateForm').serialize(),
        success:function(data){
           getCellHistorySuccess(data)//请求列表
	   clearForm('dateForm');
            // askCellHistoryPage();//请求罐体列表分页 
        }
    });
    $.ajax({
        url:'getCount.action',
        data:$('#dateForm').serialize(),
        success:function(data){
            // getCellHistoryBox();//请求列表
            cellHistoryPage(data)//请求罐体列表分页 
        }
    });
});

function clearForm(formName){
    $('#'+formName).find('input').val('');
}
