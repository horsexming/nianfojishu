<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>生产力生态平衡系统</title>
		<%@include file="/util/inc.jsp"%>

		<style type="text/css">
ul.ztree {
	border: 1px solid #617775;
	background: #ffffff;
	width: 260px;
	height: 95%;
	overflow-y: scroll;
	overflow-x: auto;
}

#mq {
	color: #000;
	margin: 0;
	left: 0;
	z-index: 2;
	width: 632px;
	height: 34px;
	line-height: 20px;
	padding: 7px 3px 7px 5px;
	border: solid 1px #000;
	border-radius: 2px 0 0 2px;
	font-weight: 900;
}

#J_MallSearchBtn {
	width: 90px;
	border: 0;
	font-size: 14px;
	letter-spacing: 4px;
	cursor: pointer;
	color: #ffffff;
	background-color: #462824;
	background-position: 0 -111px;
	line-height: 34px;
	height: 34px;
	font-weight: bolder;
}
</style>
	</head>
	<body>
		<!-- 导航 -->
		<div id="topDiv" align="center"
			style="background: #ffffff; opacity: 1; position: absolute; top: 0px; left: 0px; width: 100%; margin: auto; border-bottom: solid 3px #e0dedf; filter: Alpha(Opacity =                               75); -moz-opacity: 0.5; -khtml-opacity: 0.8; opacity: 0.8;">
			<div style="width: 980px;" align="right">
				<div style="font-size: 10px; padding-top: 5px;">
					欢迎您
					<a href="userCenter/userManage.jsp" style="color: red;">${sessionScope.Users.name}
					</a>&nbsp;&nbsp;
					<a href="userCenter/userManage.jsp">[个人中心]</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="userCenter/updatePassword.jsp">修改密码</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<img id="pmImg" alt="" src="images/newpm.gif"
						style="display: none;">
					<a id="messageCount"
						href="AlertMessagesAction!findAlertMessages.action">消息 (0)</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<a id="nav-add-favorite" href="javascript:;">收藏本站</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="logOff.jsp" style="color: blue" target="_top">[退出]</a>
				</div>
				<div>

				</div>
			</div>
			<div
				style="width: 980px; padding-top: 10px; font-size: 12px; height: 40px;"
				align="center">
				<div
					style="float: left; width: 20%; font-size: 14px; padding-left: 0px; border: solid #000000 0px;"
					align="left">
					<a id="myFunction" href="javascript:;">我的功能</a>-
					<a href="ModuleFunctionAction!findMfByUser.action">旧版入口</a>-
					<a href="wx/index.jsp">我的五行</a>
				</div>
				<div style="width: 80%; float: left;" align="right">
					<form action="ModuleFunctionAction!searchModuleFunction.action"
						method="post" style="margin: 0px; padding: 0px;" target="workMain">
						<input id="mq" type="text" border="thin solid 1px #0170b8;"
							name="moduleFunction.functionName" class="search_shuru1"
							accesskey="s" tabindex="9" autocomplete="off" autofocus="true"
							x-webkit-speech="" x-webkit-grammar="builtin:search">
						<button id="J_MallSearchBtn" type="submit">
							搜索
							<s></s>
						</button>
					</form>
				</div>
			</div>
		</div>
		<!-- 我的功能侧边栏
		<div id="myFunction"
			style="width: 3%; height: 100%; display: block; top: 0px; left: 0px; float: left;"
			align="center">
			<div style="width: 10px; padding-top: 200px;" align="center">
				我的功能
			</div>
		</div> -->
		<!-- 我的功能树形显示 -->
		<div id="mfunction"
			style="position: absolute; left: 0px; top: 35px; display: none; width: 260px; height: 100%;"
			align="left">
			<div style="height: 100%;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<!--辅助div，为做兼容性  -->
		<div style="padding-top: 45px; _padding-top: 35px;"></div>
		<div id="testMain">

		</div>
		<!--主体 iframe -->
		<iframe name="workMain" target="workMain" id="workMainIframe"
			src="${param.url!=null?param.url:'ModuleFunctionAction!findMfByUser.action'}" marginwidth="0"
			marginheight="0" hspace="0" vspace="0" frameborder="0"
			style="width: 100%; height: 100%; margin: 0px; padding: 0px;"
			scrolling="yes"></iframe>



		<script type="text/javascript">
//========================================zTree显示
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	$.ajax( {
		url : 'ModuleFunctionAction!findAllMfForJson.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('functionName'),
					click : false
				});

			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		error : function() {
			alert("服务器异常!");
		}
	});

});
//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	$('#workMainIframe').attr("src",
			"ModuleFunctionAction!findMfByIdForJump.action?id=" + treeNode.id);

}
//=================================== zTree显示结束

//我的功能隐藏与显示
$(function() {

	$("#myFunction").bind("click", function() {
		$("#myFunction").hide("slow");
		$("#mfunction").slideDown("slow");
	});

	$("#mfunction").bind("mouseleave", function() {
		$("#mfunction").slideUp('slow');
		$("#myFunction").show("slow");
	})
});

//系统消息提醒
$(function() {
	$.ajax( {
		type : "POST",
		url : "AlertMessagesAction!findAMCountByUid.action",
		dateType : "json",
		success : function(msg) {
			if (msg > 0) {
				$("#pmImg").show();
				$("#messageCount").text("消息(" + msg + ")");
				$("#messageCount").css( {
					color : "red"
				});
				$("#messageCount").bind("mouseover", function() {
					$("#messageCount").css("background-color", "#696969");
				})
				$("#messageCount").bind("mouseout", function() {
					$("#messageCount").css("background-color", "#ffffff");
				})
			}
		}
	});
});

//本页所有的标签重定向到 pageIframe
$(function() {
	$("a").bind("click", function() {
		var href = this + "";
		if (href.indexOf("logOff.jsp") < 0) {
			$('#workMainIframe').attr("src", this);
			return false;
		}
	})
})

window.onscroll = function() {
	var topDiv = document.getElementById("topDiv");
	var myFunction = document.getElementById("myFunction");
	var mfunction = document.getElementById("mfunction");
	var scrollTop = getScrollTopHeight();//获取滚动条离顶部距离
	topDiv.style.top = scrollTop;
	mfunction.style.top = scrollTop + 35;
}

//获取滚动条离顶部距离(兼容所有)
function getScrollTopHeight() {
	var scrollPos;
	if (window.pageYOffset) {
		scrollPos = window.pageYOffset;
	} else if (document.compatMode && document.compatMode != 'BackCompat') {
		scrollPos = document.documentElement.scrollTop;
	} else if (document.body) {
		scrollPos = document.body.scrollTop;
	}
	return scrollPos;
}
</script>
		<!-- 天气 
		<script defer
			src="javascript/weather/js/jquery.weather.build.js?parentbox=body&moveArea=all&zIndex=1&move=0&drag=1&autoDrop=0&styleSize=big&style=cartoon-1&areas=client&city=%E5%8C%97%E4%BA%AC">
</script>-->
	</body>
</html>
