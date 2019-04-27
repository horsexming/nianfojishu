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
		<%--		<link rel="stylesheet" type="text/css" href="css/screencollect.css">--%>
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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jgcharts.js">
</script>

		<script type="text/javascript">
var sign = "T";
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
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
}

$(function() {
	var pagePage = 1;

	//页面定时刷新(3分钟/次)
	setTimeout(nextPage, 1000 * 10);

	//下一个页面
	function nextPage() {
		window.parent.clearsetInterval();
		window.parent.next();
	}

});
</script>

	</head>
	<style>
body { <%--
	background: black; --%> <%--
	background: white; --%>
	background: #ccc;
	font-family: "黑体";
}

.b {
	width: 100%;
	background: #1b1430;
}

.content {
	text-align: center;
	background: #1b1430;
	color: white;
	font-size: 60px;
	font-weight: bold;
}

#left {
	text-align: center;
	width: 50%;
	background-color: #32228c;
	color: #ffffff;
	float: left;
	font-size: 60px;
	border-bottom: 2px solid black;
}

#right {
	text-align: center;
	background-color: #32228c;
	color: #ffffff;
	width: 49.5%;
	float: right;
	font-size: 60px;
	border-bottom: 2px solid black;
}

.second {
	text-align: center;
	font-size: 60px;
	color: #000000;
	background-color: #407eb8;
	width: 100%;
	border-bottom: 2px solid black;
	height: 100px;
	padding-top: 20px;
}

.data {
	font-size: 60px;
	color: #ffff00;
}

.third {
	text-align: left;
	height: 140px;
	padding-left: 60px;
	padding-top: 40px;
	border-bottom: 2px solid black;
}
</style>


	<body>
		<div class=b>
			<div class=content style="padding-top: 0px">
				${screen.name}运转汇总
				<img src="<%=basePath%>images/pebs.png"
					style="position: absolute; right: 150px; top: 20px;">
			</div>
			<div id="left">
				<div class=second>
					生产状态
				</div>
				<div class=third>
					工位状态：
					<span class=data>${scList[1]}/${scList[0]}</span>个
				</div>
				<div class=third>
					设备运转：
					<span class=data>${scList[4]}/${scList[3]}</span>台
				</div>
				<div class=third>
					生产员工：
					<span class=data>${scList[2]}</span>人
				</div>
			</div>
			<div id="right">
				<div class=second>
					设备能耗
				</div>
				<div class=third>
					总能耗：78.43万 kwh
					<span class=data>
						<!--  ${scList[5]}-->
					</span>
				</div>
				<div class=third>
					月能耗：23.56万 kwh
					<span class=data>
						<!--${scList[6]}-->
					</span>
				</div>
				<div class=third>
					日能耗：4056 kwh
					<span class=data>
						<!--${scList[7]}-->
					</span>
				</div>
			</div>
			<%--			<div style="font-size: 24px; clear: both; margin-top: 10px;"--%>
			<%--				align="right">--%>
			<%--				<input value="下一页"--%>
			<%--					onclick="javascript:window.location.href = 'screen_printScreen3.action?screen.id=${screen.id}';"--%>
			<%--					style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;"--%>
			<%--					type="button">--%>
			<%--			</div>--%>
		</div>

	</body>


</html>

