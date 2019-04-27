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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="procardTemplateGyAction_bomCompare.action" method="post" enctype="multipart/form-data" onsubmit="return validate();">
			<font size="4">导入BOM:</font>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="file" name="bomTree">
			<input id="scbtn" type="submit" value="上传" style="width: 80px;height: 20px;"><label id="lb" style="display: none;">正在比对中请耐心等待</label>
		</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function validate(){
	$("#scbtn").attr("disabled","disabled");
}
</SCRIPT>
	</body>
</html>
