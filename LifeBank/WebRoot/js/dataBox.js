//cellhistory分页
function dataBoxPage(data){
	// console.log(data[0]);
	$("#dataBoxPage").html('');
	$("#dataBoxPage").pagination({
		count: data[0], //总数
		size: 15, //每页数量
		index: 1,//当前页
		lrCount: 3,//当前页左右最多显示的数量
		lCount: 1,//最开始预留的数量
		rCount: 1,//最后预留的数量
		callback: function (options) {
			// alert(options.index); 
			$('#dataBoxTable>tbody').html('');
				getDataBox(options.index);    
			//options.count = 300;
			//return options;
		},
		beforeRender: function (jA) {
			//jA.attr("href","default.php?index="+jA.text());
		}
	});
};
function askDataBoxPage(){
	$.ajax({
		url:'getCanCount.action',
		type:'get',
		success:function(data){
			dataBoxPage(data);
		}
	});
};
//刷出表格
function getDataBoxSuccess(data){
	$('#dataBoxTable>tbody').html('');
	var Length = data.length;	
	for(var i=0;i<Length;i++){
		var content = 	"<tr>"+
							"<td>"+data[i].id+"</td>"+
							"<td>"+data[i].address+"</td>"+
							"<td>"+data[i].serial+"</td>"+
						"</tr>";
		$('#dataBoxTable>tbody').append(content);
	};
	if(Length < 14){
		for(var i=Length;i<14;i++){
		var content = 	"<tr>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
						"</tr>";
		$('#dataBoxTable>tbody').append(content);
	};
	};
};

function getDataBox(position){
	$.ajax({
		url:'getCans.action',
		type:'post',
		data:{position:position},
		success:function(data){
			getDataBoxSuccess(data);
			// alert(data[0].id);
		}
	});
};

function getDataBoxBox(){
	var position = 1;
	getDataBox(position);	
};