$('#cellBtn').click(function(){
    $.ajax({
        url:'AddCell.action',
        data:$('#cellForm').serialize(),
        success:function(data){
            alert(data[0].description);
            clearForm('cellForm');//make input in form clean 
            getCellBox();
            askCellPage();
        }
    });
});

$('#cellBtn1').click(function(){
    $.ajax({
        url:'DelCell.action',
        data:$('#cellForm1').serialize(),
        success:function(data){
            alert(data[0].description);
            clearForm('cellForm1');
            // location.reload();
            getCellBox();
            askCellPage();
        }
    });
});