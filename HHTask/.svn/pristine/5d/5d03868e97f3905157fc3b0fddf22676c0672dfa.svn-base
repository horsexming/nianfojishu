<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript"
			src="<%=basePath%>javascript/FusionCharts.js">
</script>
		<script type="text/javascript">
function zhuzhuang2() {
	var chart = new FusionCharts("<%=basePath%>/util/Line.swf", "0", "800",
			"600");
	chart
			.setDataURL(escape("AttendanceCountAction!findLineChart.action?startDate=<%=startDate %>&endDate=<%=endDate %>"));
	chart.render('showScoreDiv');
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="zhuzhuang2()">
		<center>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				
				<div align="center" id="showScoreDiv" style="index: 0">

				</div>
				<br>
			</div>
		</center>
	</body>
















</html>
