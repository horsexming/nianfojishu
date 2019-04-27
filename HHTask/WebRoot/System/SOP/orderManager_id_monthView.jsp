<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<iframe target="workMain" id="orderIframe" src="" marginwidth="0"
			marginheight="0" hspace="0" vspace="0" frameborder="0"
			scrolling="auto"
			style="width: 100%; height: 1000px; margin: 0px; padding: 0px;"></iframe>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var ms = 1000 * 2;//五分钟刷新
var list = ${list};
$('#orderIframe').attr("src",
				"orderManager_showOrderDetils.action?id=" + list[0]);
setTimeout(function() {
			al()
		}, ms);
var i = list.length;
function al() {
	i--;
	var id=list[i];
	$('#orderIframe').attr("src",
				"orderManager_showOrderDetils.action?id=" + id);
	if (i >0) {
		setTimeout(function() {
			al()
		}, ms);
	}else{
		setTimeout(function() {
			$('#orderIframe').attr("src",
				"/System/bbs/screen_printScreen2.action?screen.id=3" );
		}, ms);
		
		setTimeout(function() {
			window.location.href="orderManager_findOrderFormMonth.action";
		}, ms);
		
	}
}
</script>
	</body>
</html>
