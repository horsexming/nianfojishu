<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>下载安装包</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
	<script type="text/javascript"
		src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
	<body> 
		<input type="hidden" value="${param.noapp}" id="noapp">
		<h2>
			<a id="applink1" href="pebs://shhh.task.pebs.com.cn/">打开本地应用</a>
		</h2>
		<h2>
			<a id="applink2"
				href="<%=basePath%>upload/file/download/Manage_signed_12-12.apk">下载pebsAPP</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/dskzxt.apk">动声控制</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/s6jianyan.apk">6S现场检验</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/HHTask_light.apk">下载灯控APP</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/ShowPicture2014_25.apk">下载工艺推送APP</a>
			<br />
			<a id="applink2" href="<%=basePath%>upload/file/download/xunjian.apk">下载巡检APP</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/waigoujy.apk">下载外购件检验APP</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/3DXMLPlayer.msi">下载3DXMLPlayer(32位)</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/3DXMLPlayer_x64.msi">下载3DXMLPlayer(64位)</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/jatoolsPrinter_free.zip">下载自动打印插件</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/shoujian.apk">下载首检APP</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/system.apk">下载系统异常APP</a>
			<br />
			<a id="applink2"
				href="<%=basePath%>upload/file/download/fzyyw.apk">下载非主营业务APP</a>
		</h2>
		<script type="text/javascript">
$(document).ready(function() {
	var noapp = $("#noapp").val();
	if (noapp == "no") {
		alert("您还未安装应用！");
	}
});
var appstore = "<%=basePath%>util/downloadapk.jsp?noapp=no";
function applink(fail) {
	return function() {
		var clickedAt = +new Date;
		// During tests on 3g/3gs this timeout fires immediately if less than 500ms.    
		setTimeout(function() {
			// To avoid failing on return to MobileSafari, ensure freshness!    
				if (+new Date - clickedAt < 2000) {
					window.location = fail;
				}
			}, 500);
	};
}
document.getElementById("applink1").onclick = applink(appstore);
</script>
	</body>
</html>
