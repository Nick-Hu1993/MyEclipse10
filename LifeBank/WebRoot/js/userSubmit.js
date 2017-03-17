// 添加调用
$('#userBtn').click(function(){
	userAddSubmit()
});
// 删除调用
$('#userBtn1').click(function(){
	userDelectSubmit()
});
//用户添加
function userAddSubmit(){
	$.ajax({
		url:'addUser.action',
		type:'post',
		data:$('#userForm').serialize(),
		success:function(data){
			alert(data[0].description);
			clearForm('userForm');
			userTableBox();
			askUserTablePage();
		}
	});
	return false;
};
// 用户删除
function userDelectSubmit(){
	$.ajax({
		url:'delUser.action',
		type:'post',
		data:$('#userForm1').serialize(),
		success:function(data){
			alert(data[0].description);
			clearForm('userForm1');
			userTableBox();
			askUserTablePage();
		}
	});
	return false;
}
