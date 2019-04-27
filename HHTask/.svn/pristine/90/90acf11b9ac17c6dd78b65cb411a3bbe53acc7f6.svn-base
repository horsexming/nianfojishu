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
	<body>
		<div align="center" style="color: #ffffff">
			检验完成，系统正在自动打印中......
		</div>
		<script type="text/javascript">
if (typeof (myObj) != "undefined") {
	//打印服务
	myObj.fun1FromAndroid('${message}');
	setInterval(gotoUrl("LogoStickerAction!findSjList.action"), 1000 * 20);
} else {
	setInterval(gotoUrl("LogoStickerAction!findSjList.action"), 1000 * 10);
}

function gotoUrl(url) {
	if (url == "") {
		history.go(-1);
	} else {
		window.location.href = url;
	}
}
</script>
	</body>
</html>
