function navHeaderToggle(me){
	if($('#navHeader').hasClass('glyphicon-chevron-left')){//if navHeader has the class do that thing
		$('#navHeader')
		.removeClass('glyphicon-chevron-left')
		.addClass('glyphicon-chevron-right');//change icon of navHeader

		$('.sliderItems>p').hide(); 

		$('#navBox')
		.removeClass('col-lg-2 col-md-3 col-sm-4')
		.addClass('col-lg-1 col-md-2 col-sm-2');

		$('#contentBox')
		.removeClass('col-lg-10 col-md-9 col-sm-8')
		.addClass('col-lg-11 col-md-10 col-sm-10');
	}else{//if navHeader dont't has the class do that thing
		$('#navHeader')
		.removeClass('glyphicon-chevron-right')
		.addClass('glyphicon-chevron-left');

		$('.sliderItems>p').show();

		$('#navBox').removeClass('col-lg-1 col-md-2 col-sm-2')
		.addClass('col-lg-2 col-md-3 col-sm-4');
		
		$('#contentBox')
		.removeClass('col-lg-11 col-md-10 col-sm-10')
		.addClass('col-lg-10 col-md-9 col-sm-8');
	};
	return false;
};
$('#sliderBar').click(function(){navHeaderToggle(this)});
$('.content').hide();//隐藏主界面所有东西
$('.content').eq(0).show();//显示用户列表
$('.sliderItems:not(:last-child)').click(function(){
	$(this)
	.addClass('Aactive')
	.siblings()
	.removeClass('Aactive');
	$('.content').hide();
	$('.content')
	.eq(($(this).index()-1))
	.show();			
});//切换主界面的显示东西