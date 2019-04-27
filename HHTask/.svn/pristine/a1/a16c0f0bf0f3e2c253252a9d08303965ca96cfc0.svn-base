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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<meta name="viewport"
			content="width=device-width, initial-scale=1, user-scalable=no">
		<title>二维码扫描</title>
		<script src="${pageContext.request.contextPath}/js/lib/zepto.js">
</script>
		<script
			src="${pageContext.request.contextPath}/js/lib/qrcode.lib.min.js">
</script>
		<script src="${pageContext.request.contextPath}/js/lib/qrcode.js">
</script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/my.css">
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div>
					<div class="qr-btn" node-type="qr-btn">
						扫描二维码1
						<input node-type="jsbridge" type="file" name="myPhoto"
							value="扫描二维码1" />
					</div>
				</div>
				<div>
					<div class="qr-btn" node-type="qr-btn">
						扫描二维码2
						<input node-type="jsbridge" type="file" name="myPhoto"
							value="扫描二维码2" />
					</div>
				</div>
				<div class="result-qrcode">
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
//初始化扫描二维码按钮，传入自定义的 node-type 属性
    $(function() {
        Qrcode.init($('[node-type=qr-btn]'));
    });
</SCRIPT>
	</body>
</html>
