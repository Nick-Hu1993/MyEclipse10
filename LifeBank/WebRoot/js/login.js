$('.loginLoadingIndex').hide();//隐藏所有界面
$('.loginLoadingIndex').eq(0).show();//显示登录界面
$('#loginBtn').click(function(){
	loginSubmit();
});
function loginSuccess(){
	askForData();
	$('.loginLoadingIndex').hide();
	$('.loginLoadingIndex').eq(2).show();
};
function loginError(){
	$('.loginLoadingIndex').hide();
	$('.loginLoadingIndex').eq(0).show();
};
function loginSubmit(){
	$('.loginLoadingIndex').hide();//隐藏所有界面
	$('.loginLoadingIndex').eq(1).show();//show loading view
	loginAjax();	
};
function loginAjax(){
	$.ajax({
		url:'loginWeb.action',
		type:'post',
		data:$('#loginForm').serialize(),
		success:function(data){
			if(data[0].message=="success"){
				loginSuccess();	
			}else{
				alert(data[0].description);
				loginError();
			}
		}
	});
};
function askForData(){
	userTableBox();//请求用户列表
	askUserTablePage();	//ask for page of userTable
	getDataBoxBox();
	askDataBoxPage();			
	getCellBox();//细胞列表
	askCellPage();
	getCellHistoryBox();//请求列表
	askCellHistoryPage();//请求罐体列表分页	
	askTopic();//请求话题列表	
};
function exitBtn1(){
	location.reload();
};