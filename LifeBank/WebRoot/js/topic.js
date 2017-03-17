function topicBtn(id){
	// console.log(id);
	$.ajax({
		url:'delTopic1.action',
		data:{'topicId':id},
		success:function(o){
			alert("删除成功！");
			askTopic();
			// location.reload();
		}
	});
};//删除话题

function commentBtn(id,index){
	$.ajax({
		url:'delComment1.action',
		type:'post',
		data:{'commentId':id},
		success:function(o){
			alert("删除成功！");
			$('.comment').eq(index).hide();
			// console.log(index);
			// location.reload();
		}
	});
};//删除评论

function topicFor(data){
	for(var i=0;i<data.length;i++){
		var content = 	"<li class='topicItems'>"+
							"<p>"+(i+1)+"</p><p>"+data[i].content+"</p>"+
							"<button class='btn btn-info topicBtn' "+
							"onclick='topicBtn("+data[i].id+");'>删除</button>"+
						"</li>";
		$('#topic').append(content);
	};
	topicItemsClick(data);
};//话题添加

function appendTopic(data){
	$('#topic').html('');
	topicFor(data);
};//刷出话题

function topicItemsClick(data){
	$('.topicItems').click(function(){
		$.ajax({
			url:'getComRep1.action',
			data:{'topicId':data[$(this).index()].id},
			success:function(a){
				appendComment(a);
			}
		});
	});
};//话题点击进入评论

function appendReplyFor(b){
	for(var h=0;h<b.length;h++){									
		var rcontent = "<li class='reply'><p>回复:</p><p>"+b[h].content+"</p></li>";
		$('.commentUl').append(rcontent);
	}
};//添加回复

function appendReply(a,scontent,j){
	$('#topic').append(scontent);
	var b = a[j].replys;
	appendReplyFor(b);
};//刷出回复

function appendCommentFor(a){
	for(var j=0;j<a.length;j++){
		var scontent = 	"<li class='comment'>"+
							"<ul class='commentUl'>"+
								"<li class='commentLi'>"+
									"<p>"+(j+1)+":</p><p>"+a[j].content+"</p>"+
									"<button class='btn btn-info commentBtn' "+
									"onclick='commentBtn("+a[j].id+","+j+")'>"+
									"删除</button>"+
								"</li>"+
							"</ul>"+
						"</li>";
		appendReply(a,scontent,j);
	};
};//添加评论

function appendComment(a){
	var bcontent = '<button class="btn btn-info" id="backBtn" onclick="askTopic();">'+
	        	   '返回</button>';
	$('#topic').html(bcontent);
	appendCommentFor(a);
};//刷出评论

function askTopic(){
	$.ajax({
		url:'getAllInfo.action',
		success:function(data){
			appendTopic(data);				
		}
	});
};