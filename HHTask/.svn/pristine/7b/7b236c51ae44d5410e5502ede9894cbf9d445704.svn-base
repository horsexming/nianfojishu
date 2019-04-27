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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;" align="center">
			<input value="扫描工序委外领料单条码" style="height: 60px;" type="button"
				onclick="getcheckList2()">
			<form action="sellAction!showWwPlanoutDetail.action" method="post">
				或输入工序委外领料单条码:
				<input type="text" name="barCode" id="barCode">
				<input type="submit" value="确定">
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
$(document).ready(function(){
	$("#barCode").focus();
})
function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	window.location.href = "sellAction!showWwPlanoutDetail.action?barCode="
			+ tm;
}
</SCRIPT>
</html>
