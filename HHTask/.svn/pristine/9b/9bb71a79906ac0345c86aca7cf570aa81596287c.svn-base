<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String cardId = request.getParameter("cardId");
	String userCode = request.getParameter("userCode");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script language="javascript" type="text/javascript"
			src="javascript/DatePicker/WdatePicker.js">
</script>
		<base href="<%=basePath%>">
		<script type="text/javascript"
			src="<%=basePath%>javascript/FusionCharts.js">
</script>
		<script type="text/javascript">
function zhuzhuang2() {
	var chart = new FusionCharts("<%=basePath%>/util/Column3D.swf", "0", "800",
			"600");
	chart
			.setDataURL(escape("AssScoreAction!showScoreView.action?cardId=<%=cardId%>&&userCode=<%=userCode%>"));
	chart.render('showScoreDiv');
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="zhuzhuang2()">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
						<a href="" style="color: #ffffff"></a>
					</div>
				</div>
				
				<div align="center" id="showScoreDiv" style="index: 0">

				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
