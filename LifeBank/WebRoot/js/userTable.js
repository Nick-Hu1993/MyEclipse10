function getUserListSuccess(data){
	$('#userDataTable>tbody').html('');
	var Length = data.length;
	for(var i=0;i<Length;i++){
		var content = 	"<tr>"+
							"<td>"+data[i].id+"</td>"+
							"<td>"+data[i].username+"</td>"+
							"<td>"+data[i].phone+"</td>"+
							"<td>"+data[i].clock+"</td>"+
						"</tr>";
		$('#userDataTable>tbody').append(content);
	};
	if(Length<14){
		for(var i=Length;i<14;i++){
		var content = 	"<tr>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
						"</tr>";
		$('#userDataTable>tbody').append(content);
	};
	}
};

function askUserList(position){
	$.ajax({
		url:'userlist.action',
		type:'post',
		data:{position:position},
		success:function(data){
			getUserListSuccess(data);
		}
	});
};
function askUserTablePage(){
	$.ajax({
		url:'userCount.action',
		type:'get',
		success:function(data){
			userTablePage(data);
		}
	});
};
// userTable分页
function userTablePage(data){
	$("#userTablePage").html('');
	$("#userTablePage").pagination({
		count: data[0], //总数
		size: 15, //每页数量
		index: 1,//当前页
		lrCount: 3,//当前页左右最多显示的数量
		lCount: 1,//最开始预留的数量
		rCount: 1,//最后预留的数量
		callback: function (options) {
			// alert(options.index); 
			$('#userDataTable>tbody').html('');
				askUserList(options.index);    
			//options.count = 300;
			//return options;
		},
		beforeRender: function (jA) {
			//jA.attr("href","default.php?index="+jA.text());
		}
	});
};

function userTableBox(){
	var position = 1;
	askUserList(position);	
};