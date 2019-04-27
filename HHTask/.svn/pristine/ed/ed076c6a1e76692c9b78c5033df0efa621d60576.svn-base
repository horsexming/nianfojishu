<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
#fullbg {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =               50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	height: 400px;
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 45%;
	width: 400px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe {
	background-color: #fff;
	height: 365px;
	line-height: 24px;
	width: 400px;
}
</style>
<script type="text/javascript">
var time;
var i = 1;
$(function() {
	time = setInterval("showzhemu()", 1000 * 60 * 30);
	$("html").click(function() {
		clearInterval(time);
		i = 0;
		time = setInterval("showzhemu()", 1000 * 60 * 30);
	});
	$("html").mouseover(function() {
		clearInterval(time);
		i = 0;
		time = setInterval("showzhemu()", 1000 * 60 * 30);
	})
	$("html").keydown(function() {
		clearInterval(time);
		i = 0;
		time = setInterval("showzhemu()", 1000 * 60 * 30);
	})
});
function showzhemu() {
	if (i == 1) {
		$("body").append("<div id='fullbg'></div>");
		$("body")
				.append(
						"<div id='dialog' class='loginbox'>"
								+ "<iframe id='xiugaiIframe' src='UsersAction!tiaozhuan.action' "
								+ "marginwidth='0' marginheight='0' hspace='0' vspace='0' "
								+ "frameborder='0' scrolling='yes'"
								+ " style='width: 100%;margin: 0px; padding: 0px;'>"
								+ "</iframe></div>")

		var sWidth, sHeight;
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
		$("#fullbg").css( {
			height : sHeight,
			width : sWidth,
			display : "block"
		});
		$("#dialog").show();
	} else {
		//$.ajax( {
		//	type : "POST",
		//	url : "UsersAction!userOnlineTest.action",
		//	dataType : "json",
		//	async : false,
		//	success : function(data) {
		//		if (!data) {
		//			i++;
		//		} else {
		//			time = setInterval("showzhemu()", 1000 * 60 * 3);
		//		}
		//	},
		//	error : function() {
		//		time = setInterval("showzhemu()", 1000 * 60 * 3);
		//	}
		//});
	}

}

//每一分钟更新一下时长;
<%--function updateOnelineLong() {--%>
<%--	$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : "UsersAction!updateOnlineLong.action",--%>
<%--		data : {--%>
<%--			id : '${sessionScope.Users.id}'--%>
<%--		},--%>
<%--		dataType : "json",--%>
<%--		success : function(data) {--%>
<%----%>
<%--		}--%>
<%--	})--%>
<%--}--%>
<%--setInterval("updateOnelineLong()", 1000 * 60 * 120);--%>

$(function() {
	//为select统一添加查询搜索
})
</script>

