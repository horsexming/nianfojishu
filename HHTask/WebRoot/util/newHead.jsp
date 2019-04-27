<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/jquery/jquery-1.12.4.js">
</script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/javascript/typeahead.js/examples.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
<script language="javascript"
	src="${pageContext.request.contextPath}/javascript/typeahead.js/typeahead.bundle.js">
</script>

<style>
h1,h2,h3,h4,h5,h6 {
	margin: 0;
	padding: 0;
	font-size: 14px;
	font-weight: bold;
}

p {
	margin: 0 0 0 0;
}

#searchinput {
	vertical-align: middle !important;
}
</style>

<script type="text/javascript">
$(function() {
	//公告
	$.ajax( {
		url : "NoticeAction!show.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(useradsfa) {
			$("#show").empty();//清空
		var message = "";
		$(useradsfa).each(function(i, n) {
			message += (i + 1) + "、" + n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
		});
		$("#show").html(message);
	}
	});

	//系统消息提醒
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
				})
				$("#messageCount").bind("mouseout", function() {
				})
			}
		}
	});

	//获取邮箱已读未读
	$.ajax( {
		type : "POST",
		url : "JavaMailAction!getMailInfo.action",
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#allMail").text("(" + data[0] + ")");
				$("#unReadMail").text("(" + data[1] + ")");
				$("#allMail").parent().attr("href", data[2]);
				$("#unReadMail").parent().attr("href", data[2]);
			} else {
				//$("#allMail").parent().parent().html("");
		//$("#unReadMail").parent().parent().html("");
	}
},
error : function() {
	//$("#allMail").parent().parent().remove();
		//$("#unReadMail").parent().parent().remove();
	}
	});

	//========================================zTree显示
	//自动组装树形结构
	//var setting = {
	//	data : {
	//		simpleData : {
	//			enable : true
	//		}
	//	},
	//	callback : {
	//		onClick : onClick
	//	}
	//};
	//读取树形数据
	//$(document).ready(function() {
	//	$.ajax( {
	//		url : 'ModuleFunctionAction!findAllMfForJson.action',
	//		type : 'post',
	//		dataType : 'json',
	//		cache : true,
	//		success : function(doc) {
	//			var zNodes = [];
	//			$(doc).each(function() {
	//				zNodes.push( {
	//					id : $(this).attr('id'),
	//					pId : $(this).attr('fatherId'),
	//					name : $(this).attr('functionName'),
	//					click : false
	//				});
	//
	//			});
	//			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	//		}
	//	});
	//
	//});
	//点击回调函数
	//function onClick(event, treeId, treeNode, clickFlag) {
	//	$('#workMainIframe').attr(
	//			"src",
	//			"ModuleFunctionAction!findMfByIdForJump.action?id="
	//					+ treeNode.id);
	//
	//}
	//=================================== zTree显示结束

});
</script>
<div class="header">
	<!-- 导航条 -->
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<!-- index.css 116line nav -->
			<ul class="nav navbar-nav" style="width: auto">
				<li style="margin-right: 6px;">
					<button type="button" class="btn btn-default navbar-btn"
						onclick="javascript:window.location.href='ModuleFunctionAction!findMfByUser.action?pageStatus='">
						简约首页
					</button>
				</li>
				<li style="margin-right: 6px;">
					<button type="button" class="btn btn-default navbar-btn"
						onclick="javascript:window.location.href='ModuleFunctionAction!findMfByUser.action?pageStatus=qx'">
						清新首页
					</button>
				</li>
				<li style="margin-right: 6px;">
					<button type="button" class="btn btn-default navbar-btn"
						onclick="changeiframe('FavoriteAction_show.action');">
						收藏夹
					</button>
				</li>
			</ul>

			<form class="searchFun navbar-form navbar-left" id="remote"
				action="ModuleFunctionAction!searchModuleFunction.action"
				method="post" target="workMain">
				<input type="text" id="searchinput"
					name="moduleFunction.functionName"
					class="form-control search_text form-control typeahead"
					accesskey="s" tabindex="9" autocomplete="off" x-webkit-speech=""
					placeholder="功能搜索" x-webkit-grammar="builtin:search"
					style="width: 310px">
				<%--				<input type="submit" value="搜索" class="search_bt btn btn-default"--%>
				<%--					onclick="changeiframe();" />--%>
			</form>

			<ul class="nav navbar-nav navbar-right" style="width: auto">
				<li>
					<a
						href="newIndexqx.jsp?url=AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no"
						id="messageCount">消息(0)</a>
				</li>
				<li>
					<a href="#">欢迎您,${sessionScope.Users.name}！</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">个人中心
						<span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:;"
								onclick="changeiframe('CircuitRunAction_findCircuitRun.action?circuitRun.addUserId=0');">待审批</a>
						</li>
						<li>
							<a href="javascript:;"
								onclick="changeiframe('UsersAction!perfectUsers.action');">个人信息维护</a>
						</li>
						<li>
							<a href="newIndex.jsp?url=userCenter/userManage.jsp">工作台</a>
						</li>
						<li>
							<a href="javascript:;"
								onclick="changeiframe('userCenter/updatePassword.jsp');">修改密码</a>
						</li>
						<!-- 邮件显示开始 -->
						<li>
							<a
								href="${pageContext.request.contextPath}/UsersAction!perfectUsers.action"
								target="_blank" style="color: red">已读邮件<span id="allMail"></span>
							</a>
						</li>
						<li>
							<a
								href="${pageContext.request.contextPath}/UsersAction!perfectUsers.action"
								target="_blank" style="color: red">未读邮件<span id="unReadMail"></span>
							</a>
						</li>
						<!-- 邮件显示结束 -->
						<%--						<li>--%>
						<%--							<a href="FavoriteAction_show.action" onclick="changeiframe();"--%>
						<%--								target="workMain">收藏夹</a>--%>
						<%--						</li>--%>
						<li role="separator" class="divider"></li>
						<li>
							<a href="logOff.jsp">退出</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	</nav>
	<!-- 导航条end -->

	<%--	<div class="spana"></div>--%>
	<!-- 公告栏-->
	<div style="width: 100%; background: #f4f4f4; height: 40px">
		<div class="header_bt" style="float: left; width: 59%">
			<div style="float: left; width: 20%">
				<strong>公告：</strong>
			</div>
			<div style="float: left; width: 80%">
				<marquee direction="left" scrollamount="5" onmouseout="this.start()"
					onmouseover="this.stop()">
					<font color="red" id="show"></font>
				</marquee>
			</div>
		</div>
		<div class="fr time" style="float: right; width: 37%;">
			北京时间：
			<span id="hour"></span>时
			<span id="minute"></span>分
			<span id="second"></span>秒
			<a href="DingdanAction!listLoginUsers.action"> 当前 <span
				class="zxrs">${count}</span>人在线</a>
		</div>
	</div>
	<!-- 公告栏end-->
</div>

<script type="text/javascript">
function changeiframe(src) {
	$('#workMainIframe').attr("src", src);
	$("#sysModule").hide();
	$("#showAll").show();
}

var ModuleName= new Bloodhound({
	queryTokenizer: Bloodhound.tokenizers.whitespace,
    datumTokenizer: Bloodhound.tokenizers.whitespace,
  remote: {
<%--    url: 'ModuleFunctionAction!searchModuleFunction2.action?moduleFunction.functionName=%QUERY',--%>
<%--    wildcard: '%QUERY',--%>
	url: 'ModuleFunctionAction!searchModuleFunction2.action',
    prepare: function (query, settings) {
                     settings.url += '?moduleFunction.functionName=' + encodeURI(encodeURI(query));
<%--                     alert(encodeURI(encodeURI(query)));--%>
                      return settings;
                   }
  }
});

$('#remote .typeahead').typeahead(null, {
  name: 'names',
  display: 'modulename',
  source: ModuleName,
  limit : '10',
});
<%--https://github.com/twitter/typeahead.js/pull/1233--%>


$('#remote .typeahead').bind('typeahead:selected', function(obj, datum, name) { 
	changeiframe();
	var eValue=eval('datum.'+"moduleid");
	window.location.href='ModuleFunctionAction!findMfByIdForJump.action?id='+eValue;
});
function addmore(){
	var s="<div class='more tt-suggestion tt-selectable ' style='font-size:14px'>查看更多...</div>";
	$('.tt-menu').append(s);
}
addmore();
$('.more').on("click",function(){
	 changeiframe();
	 $(".searchFun").submit(); 
	
});


document.ready=	function showTime() {
		var datetime = new Date();
		var h = datetime.getHours();
		var m = datetime.getMinutes();
		var s = datetime.getSeconds();
		if (h < 10) {
			h = "0" + h;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (s < 10) {
			s = "0" + s;
		}
		var hour = document.getElementById("hour");
		var minute = document.getElementById("minute");
		var seconds = document.getElementById("second");

		hour.innerHTML = h;
		minute.innerHTML = m;
		seconds.innerHTML = s;
		setTimeout(showTime, 1000);
	}


</script>