<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML >
<html>
	<head>

		<title>${companyInfo.shortName}电子看板</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>

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
			message += n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
		});
		$("#show").html(message);
	}
	});
});
function login() {
	$("#tcIframe").attr("src", "<%=basePath%>login.jsp");
	chageDiv("block");
}
function tozuye() {
	window.location.href = "ModuleFunctionAction!findMfByUser.action?pageStatus=qx";
}
var winWidth = 0;
var winHeight = 0;
function findDimensions() //函数：获取尺寸 
{
	//获取窗口宽度 
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;

	//获取窗口高度 
	if (window.innerHeight) {
		winHeight = window.innerHeight;
	} else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;

	//通过深入Document内部对body进行检测，获取窗口大小 
	//	if (document.documentElement && document.documentElement.clientHeight
	//			&& document.documentElement.clientWidth) {
	//		winHeight = document.documentElement.clientHeight;
	//		winWidth = document.documentElement.clientWidth;
	//	}
}

$(function() {
	var pagePage = 1;
	var intervalKey = null;
	//加载页面
	function load() {
		getOnline();//在线检测
		findDimensions();
		//计算每页显示数量
		var pageRows = parseInt((winHeight - 210) / 107);
		var linRows = (winHeight - 210) / 107;
		if (linRows - pageRows >= 0.9) {
			pageRows++;
		}

		$
				.ajax( {
					type : "POST",
					url : "${pageContext.request.contextPath}/screen_showScreen.action",
					data : "screen.id=${screen.id}&page=" + pagePage
							+ "&rows =" + pageRows,
					dataType : "json",
					async : false,
					success : function(object) {
						var count = object[0];//总数量
					var data = object[1];//数据列表
					var usercodes = new Array();
					var usernames = new Array();
					var usergw = new Array();
					for ( var i = 0; i < data.length; i++) {
						if (!data[i].usercodes) {
							continue;
						}
						var b = data[i].usercodes.split(",");
						var a = data[i].usernames.split(",");

						for ( var j = 0; j < b.length; j++) {
							if ($.inArray(b[j], usercodes) < 0) {
								usercodes.push(b[j]);
								usernames.push(a[j]);
								usergw.push(data[i].gongwei);
							}
						}
					}

					$('#machineTable').empty();//清空表格

					//添加表头

					var name = "${Users.name}";
					var tr1 = "<div style='width:100%'>"
							+ "<div class='title' style='width:15%'>件号</div>"
							+ "<div class='title' style='width:10%'>批次号</div>"
							+ "<div class='title' style='width:7%;padding-top: 40px;height: 65px;font-size: 33px;'>工序号</div>"
							+ "<div class='title' style='font-size:33px;padding-top: 40px;height:65px'>工序名称</div>"
							+ "<div class='title' style='font-size:33px;padding-top: 40px;height:65px'>领取数量</div>"
							+ "<div class='title'>工位</div>"
							+ "<div class='title' style='font-size:33px;padding-top: 40px;height:65px'>设备编号</div>"
							+ "<div class='title' style='font-size:33px;padding-top: 40px;height:65px'>设备状态</div>"
							+ "<div class='title' style='width:16%' >加工者</div>"
							+ "<div style='clear: both'></div>" + "</div>"
					//+ "</tr><tr><td align='center' >序号</td><td align='center'>件号</td><td align='center'>批次号</td><td align='center'>工序号</td><td align='center'>工序名称</td><td align='center'>总数量</td><td align='center'>领取数量</td><td align='center'>提交数量</td><td align='center'>工位</td>"
					//+ "<td align='center'>设备编号</td><td align='center'>设备状态</td><td align='center'>加工者</td><td align='center' >工号</td></tr>"
					$('#machineTable').append(tr1);

					//填充内容
					//var js = 0;
					for ( var i = 0; i < data.length; i++) {
						//设备状态处理
						var icon;
						if (data[i].machineStatus == ""
								|| data[i].machineStatus == null) {
							icon = "<image src='${pageContext.request.contextPath}/images/mk/green.png' width='20' height='20'>";
						} else {
							if (data[i].machineStatus == "正常") {
								icon = "<image src='${pageContext.request.contextPath}/images/mk/green.png' width='20' height='20'>";
							} else if (data[i].machineStatus == "故障") {
								icon = "<image src='${pageContext.request.contextPath}/images/mk/red.png' width='20' height='20'>";
							} else {
								icon = "<image src='${pageContext.request.contextPath}/images/mk/yellow.png' width='20' height='20'>";
							}
						}
						//人员照片处理(是否存在多人干一道工序)
						if ((data[i].usercodes + "").split(',').length > 1) {
							var pics = (data[i].usercodes + "").split(',');
							var urls = "";
							for ( var j = 0; j < pics.length; j++) {
								var userCode = pics[j];
								$
										.ajax( {
											type : "POST",
											url : "UsersAction!findImageByCode.action?code="
													+ userCode,
											dataType : "json",
											async : false,
											success : function(object) {
												urls += '<img src="${pageContext.request.contextPath}/upload/user/' + object + '" width="50%" height="72px" style="background-color:#32228c"/>';
												
											}
										});
							}
							for ( var j = 0; j < pics.length; j++) {
								var userCode = pics[j];
								$
										.ajax( {
											type : "POST",
											url : "UsersAction!findImageByCode.action?code="
													+ userCode,
											dataType : "json",
											async : false,
											success : function(object) {
												urls += "<div style='width:100%'>"
													+ data[i].usercodes
													+ '</div>';
											}
										});
							}
							
							
						} else {
							var userCode = data[i].usercodes;
							$
									.ajax( {
										type : "POST",
										url : "UsersAction!findImageByCode.action?code="
												+ userCode,
										dataType : "json",
										async : false,
										success : function(object) {
											urls = '<img src="${pageContext.request.contextPath}/upload/user/'
													+ object
													+ '" width="150px" height="72px" style="background-color:#32228c" alt="'
													+ data[i].usercodes
													+ '"/>'
													+ '<div>'
													+ data[i].usercodes
													+ '</div>'
										}
									});
						}
						//换行颜色处理
						var bgColor = "";
						if (true) {
							bgColor = "background-color:#32228c	";
						}
						var tr = "<div style='width:100%'>"
								+ "<div class='detail' style=' width:15%; font-size: 30px;text-align: left;word-wrap:break-word'>"
								+ data[i].markId
								+ "</div><div class='detail' style='width:10%'>"
								+ data[i].selfCard
								+ "</div><div class='detail' style='width:7%'>"
								+ data[i].processNO
								+ "</div><div class='detail' style='padding-top: 30px;height: 75px;font-size: 25px;text-align: center;'>"
								+ data[i].processName
								+ "</div><div class='detail'>"
								+ (data[i].applyCount)
								+ "</div><div class='detail' style='color: #ffff00;'>"
								+ data[i].gongwei
								+ "</div><div class='detail'>"
								+ data[i].shebeiNo
								+ "</div><div class='detail'>"
								+ icon
								+ "</div><div class='detail' style='padding:0px;width:16%;height: 105px;'>"
								+ urls
								+ "</div><div style='clear: both;'></div>"
								+ "</div>"
						//<td align='center' >"
						//+ data[i].usercodes + "</td></tr>";

						$('#machineTable').append(tr);
					}
					var allPage = (Math.ceil(count / pageRows)) == 0 ? 1
							: (Math.ceil(count / pageRows));
					var tr3 = "<tr><td colspan='8' align='center'>页码:"
							+ pagePage + "/" + allPage + "</td></tr>";
					$('#machineTable').append(tr3);
					//计算下一页 
					var nexPage = (pagePage + 1) * pageRows - count;
					var allPage = count / pageRows;
					if (nexPage >= pageRows) {
						//swiper next
						setInterval(nextPage, 1000 * 50);
					} else {
						pagePage++;
<%--						window.parent.clearsetInterval();--%>
					}
				}

				});
	}

	function getUserImage(userCode) {
		$.ajax( {
			type : "POST",
			url : "UsersAction!findImageByCode.action?code=" + userCode,
			dataType : "json",
			async : false,
			success : function(object) {
				return object;
			}
		});
	}

	//重新加载页面
	function reLoad() {
		//window.location.reload();
	}

	load();//第一次加载页面

	//	//页面尺寸变化时重新加载
	//	window.onresize = load;

	//自动切换下一页 (20秒/次)
	intervalKey = setInterval(load, 1000 * 55);
	//
	//页面定时刷新(3分钟/次)
	//setTimeout(reLoad, 1000 * 60 * 3);

	//下一个页面
	function nextPage() {
		window.parent.clearsetInterval();	
		window.parent.next();  
<%--		window.parent.clearsetInterval();--%>
<%--			alert("页面2");--%>
<%--		getOnline();//在线检测--%>
<%--		window.location.href = "ProcardAction!showOrderAndProcard.action?id=${screen.id}";--%>
<%--		//window.location.href = "screen_printScreen3.action?screen.id=${screen.id}";--%>
	
	}

});
</script>
		<style type="text/css">
table {
	border-collapse: collapse;
}

.table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #999 0px;
	border-width: 0px;
}

.table th,.table td {
	border: solid #999;
	border-width: 1px;
	padding: 0px;
}
body {
	color: white;
	<%--	background-color: #1b1430--%>
	background: #ccc;
}

.content {
	text-align: center;
	background: #1b1430;
	color: white;
	font-size: 60px;
	font-weight: bold;
	width: 100%;
	height: 90px;
}

.title {
	border: 1px solid black;
	width: 10%;
	float: left;
	background-color: #407eb8;
	font-weight: bolder;
	font-size: 30px;
	color: #000000;
	text-align: center;
	padding-top: 40px;
	height: 65px;
}

.detail {
	border: 1px solid black;
	width: 10%;
	float: left;
	background-color: #32228c;
	height: 65px;
	font-weight: bolder;
	font-size: 30px;
	padding-top: 40px;
	color: #ffffff;
	text-align: center;
}
</style>
	</head>

	<body id="aaa" style="overflow: hidden; font-family: 黑体;">
		<div class=content style="height: 70px">
			${screen.name}运转汇总
			<img src="<%=basePath%>images/pebs.png"
				style="position: absolute; right: 150px; top: 20px;">
		</div>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">信息展示</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="tcIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 700px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="machineTable" style="width: 100%"></div>
		<div style="clear: both;"></div>
		<%--		<div style="font-size: 24px; clear: both; margin-top: 10px;"--%>
		<%--			align="right">--%>
		<%--			<input value="下一页"--%>
		<%--				onclick="javascript:window.location.href = 'ProcardAction!showOrderAndProcard.action?id=${screen.id}';"--%>
		<%--				style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;"--%>
		<%--				type="button">--%>
		<%--		</div>--%>
	</body>
</html>
