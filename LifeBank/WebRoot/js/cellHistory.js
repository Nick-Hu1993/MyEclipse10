//cellhistory分页
function cellHistoryPage(data){
	// console.log(data[0]);
	$("#jqueryPage").html('');
	$("#jqueryPage").pagination({
		count: data[0], //总数
		size: 15, //每页数量
		index: 1,//当前页
		lrCount: 3,//当前页左右最多显示的数量
		lCount: 1,//最开始预留的数量
		rCount: 1,//最后预留的数量
		callback: function (options) {
			// alert(options.index); 
			$('#proDataTable>tbody').html('');
				getCellHistory(options.index);    
			//options.count = 300;
			//return options;
		},
		beforeRender: function (jA) {
			//jA.attr("href","default.php?index="+jA.text());
		}
	});
};
function askCellHistoryPage(){
	$.ajax({
		url:'getCount.action',
		type:'get',
		success:function(data){
			cellHistoryPage(data);
		}
	});
};
//刷出表格
function getCellHistorySuccess(data){
	// console.log(data[0].itemid);
	$('#proDataTable>tbody').html('');
	var Length = data.length;
	for(var i=0;i<Length;i++){
		var content = 	"<tr>"+
							"<td>"+data[i].itemid+"</td>"+
							"<td>"+data[i].记录时间+"</td>"+
							"<td>"+data[i].罐内温度高报警+"</td>"+
							"<td>"+data[i].罐内温度超高报警+"</td>"+
							"<td>"+data[i].底部液氮T1+"</td>"+
							"<td>"+data[i].底部液氮T2+"</td>"+
							"<td>"+data[i].底部气氮T3+"</td>"+
							"<td>"+data[i].底部气氮T4+"</td>"+
							"<td>"+data[i].样品温度+"</td>"+
							"<td>"+data[i].液位低报警+"</td>"+
							"<td>"+data[i].罐体顶部温度+"</td>"+
							"<td>"+data[i].液氮罐液位+"</td>"+
							"<td>"+data[i].液位高报警+"</td>"+
							"<td>"+data[i].存放地点+"</td>"+
						"</tr>";
		$('#proDataTable>tbody').append(content);
	};
	if(Length<14){
		for(var i=Length;i<14;i++){
		var content = 	"<tr>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
							"<td></td>"+
						"</tr>";
		$('#proDataTable>tbody').append(content);
	};
	}
};

function getCellHistory(position){
	$.ajax({
		url:'getCellHistory.action',
		type:'post',
		data:{position:position},
		success:function(data){
			getCellHistorySuccess(data);
			// alert(data[0].phone);
		}
	});
};

function getCellHistoryBox(){
	var position = 1;
	getCellHistory(position);	
};