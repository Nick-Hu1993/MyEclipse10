//cellhistory分页
function cellPage(data){
	$("#cellPage").html('');
	$("#cellPage").pagination({
		count: data[0], //总数
		size: 15, //每页数量
		index: 1,//当前页
		lrCount: 3,//当前页左右最多显示的数量
		lCount: 1,//最开始预留的数量
		rCount: 1,//最后预留的数量
		callback: function (options) { 
			$('#cellTable>tbody').html('');
				getCell(options.index);    
		},
		beforeRender: function (jA) {
			//jA.attr("href","default.php?index="+jA.text());
		}
	});
};
function askCellPage(){
	$.ajax({
		url:'getCellCount.action',
		type:'get',
		success:function(data){
			cellPage(data);
		}
	});
};
//刷出表格
function getCellSuccess(data){
	$('#cellTable>tbody').html('');
	var Length = data.length;
	for(var i=0;i<Length;i++){
		var content = 	"<tr>"+
							"<td>"+data[i].cellid+"</td>"+
                            "<td>"+data[i].username+"</td>"+
                            "<td>"+data[i].phone+"</td>"+
                            "<td>"+data[i].name+"</td>"+
                            "<td>"+data[i].num+"</td>"+
                            "<td>"+data[i].serial+"</td>"+
                            "<td>"+data[i].time+"</td>"+							
						"</tr>";
		$('#cellTable>tbody').append(content);
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
						"</tr>";
		$('#cellTable>tbody').append(content);
	};
	}
};

function getCell(position){
	$.ajax({
		url:'getCellList.action',
		type:'post',
		data:{position:position},
		success:function(data){
			getCellSuccess(data);
			// alert(data[0].phone);
		}
	});
};

function getCellBox(){
	var position = 1;
	getCell(position);	
};