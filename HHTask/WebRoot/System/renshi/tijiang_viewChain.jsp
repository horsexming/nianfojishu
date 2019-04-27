<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script type="text/javascript"
			src="<%=basePath%>javascript/FusionCharts.js">
</script>
		<!-- 提奖总额环比走势图 -->
		<script type="text/javascript">
function Curve() {
	var chart = new FusionCharts("<%=basePath%>/util/Line.swf", "0", "800",
			"600");
	chart.setDataURL("MentionrecordAction!viewChain.action");
	chart.render('chartDive');
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="Curve()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="<%=basePath%>System/renshi/tijiang_findmentionrecordCurve.jsp"
						style="color: #ffffff">提奖总额</a>
					<a href="<%=basePath%>System/renshi/tijiang_viewChain.jsp"
						style="color: #ffffff">提奖总额环比</a>
					<a href="<%=basePath%>System/renshi/tijiang_viewMonth.jsp"
						style="color: #ffffff">月份的型别比例</a>
					<a href="<%=basePath%>MentionrecordAction!Type.action"
						style="color: #ffffff">型别</a>
				</div>
			</div>
			
			<div align="center">
				<div id="chartDive" align="center" style="index: 0">

				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
