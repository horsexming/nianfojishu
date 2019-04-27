var  aa={
	'id':'',
	'markId':'',
	'lotId':'',
	'content':'',
	'buutonstr1' :'',
	'buutonstr2':'',
	'xuanzeOne':function(){},
	'xuanzetwo':function(){},
	'tocheck':tocheck
};
	
function tocheck(){
	$("#zhi").html()==undefined?$("body").append('<div id="zhi"><input type="hidden" value ='+aa.id+' id="zhi_id">' +
		'<input type="hidden" value ='+aa.markId+' id="zhi_markId">' +
		'<input type="hidden" value ='+aa.lotId+' id="zhi_lotId"> </div>'):$("#zhi").html('<input type="hidden" value ='+aa.id+' id="zhi_id">' +
		'<input type="hidden" value ='+aa.markId+' id="zhi_markId">' +
		'<input type="hidden" value ='+aa.lotId+' id="zhi_lotId">')
	xuanzekuang(aa.content,aa.buutonstr1,aa.buutonstr2,'取消');
	
}

function xuanzekuang(content,buutonstr1,buutonstr2,buutonstr3){
	$("#xuan").html() == undefined?$("body").append('<div><input type="hidden" value='+content+' id="content">' +
		' <input type="hidden"  value='+buutonstr1+' id="buutonstr1">' +
		' <input type="hidden"  value='+buutonstr2+' id="buutonstr2">' +
		' <input type="hidden"  value='+buutonstr3+' id="buutonstr3"></div>'):$("#xuan").html('<input type="hidden" value='+content+' id="content">' +
		' <input type="hidden"  value='+buutonstr1+' id="buutonstr1">' +
		' <input type="hidden"  value='+buutonstr2+' id="buutonstr2">' +
		' <input type="hidden"  value='+buutonstr3+' id="buutonstr3">')
//	if($("#xuan").html() == undefined ){
//		$("body").append('<div><input type="hidden" value='+content+' id="content">' +
//		' <input type="hidden"  value='+buutonstr1+' id="buutonstr1">' +
//		' <input type="hidden"  value='+buutonstr2+' id="buutonstr2">' +
//		' <input type="hidden"  value='+buutonstr3+' id="buutonstr3"></div>')
//	}else{
//		$("#xuan").html('<input type="hidden" value='+content+' id="content">' +
//		' <input type="hidden"  value='+buutonstr1+' id="buutonstr1">' +
//		' <input type="hidden"  value='+buutonstr2+' id="buutonstr2">' +
//		' <input type="hidden"  value='+buutonstr3+' id="buutonstr3">')
//	}
	zhengmu();
}
function zhengmu(){
	//弹出遮罩层开始
		$("body").append("<div id='fullbg1'></div>");
		$("body")
				.append(
						"<div id='dialog1' class='loginbox'>"
								+ "<iframe id='xiugaiIframe1' src='./System/xuanzekuang.jsp' "
								+ "marginwidth='0' marginheight='0' hspace='0' vspace='0' "
								+ "frameborder='0' scrolling='yes'"
								+ " style='width: 100%;margin: 0px; padding: 0px;'>"
								+ "</iframe></div>")

		var sWidth, sHeight,top;
		//sWidth=document.body.offsetWidth;//得出当前屏幕的宽
		sWidth = document.body.clientWidth;//BODY对象宽度

		//sHeight=screen.height; //得到当前屏幕的高
		//sHeight=document.body.clientHeight;//BODY对象高度
		if (window.innerHeight && window.scrollMaxY) {
			sHeight = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight) {
			sHeight = document.body.scrollHeight;
		} else {
			sHeight = document.body.offsetHeight;
		} //以上得到整个屏幕的高
		//		var bw = $("body").width();
		//		var bh = window.screen.availHeight;
//		alert(window.event.screenY)
		top = window.event.screenY;
		$("#fullbg1").css( {
			height : sHeight-200,
			width : sWidth,
			display : "block"
		});
		
		$("#dialog1").css({
			top:top,
			display : "block"
		});
		//弹出遮罩层结束
		
}
