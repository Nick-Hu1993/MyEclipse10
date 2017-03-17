$('#dataBoxBtn').click(function(){
    $.ajax({
        url:'addCan.action',
        data:$('#dataBoxForm').serialize(),
        success:function(data){
            // location.reload();
            alert(data[0].description);
            clearForm('dataBoxForm');//make the input tab in form clean
            getDataBoxBox();
            askDataBoxPage();
        }
    });
});

$('#dataBoxBtn1').click(function(){
    $.ajax({
        url:'delCan.action',
        data:$('#dataBoxForm1').serialize(),
        success:function(data){
            alert(data[0].description);
            clearForm('dataBoxForm1');
            getDataBoxBox();
            askDataBoxPage();
        }
    });
});

$('#dataBoxBtn2').click(function(){
    $.ajax({
        url:'updateCan.action',
        data:$('#dataBoxForm2').serialize(),
        success:function(data){
            alert(data[0].description);
            clearForm('dataBoxForm2');
            getDataBoxBox();
            askDataBoxPage();
        }
    });
});