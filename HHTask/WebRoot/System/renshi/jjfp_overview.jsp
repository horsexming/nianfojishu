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
		<!-- 选择月份 显示个班组的比例   饼图 -->
		<script type="text/javascript">
function bintu() {
	var now = new Date();
	var year = now.getYear();
	var month = now.getMonth() + 1;
	var datatime = year + "0" + month;

	var mou1 = document.getElementById("mou1").value;
	var m1 = mou1.substr(0, 7).replace("-", "");
	var mou2 = document.getElementById("mou2").value;
	var m2 = mou2.substr(0, 7).replace("-", "");
	if (mou1 == "") {
		alert("开始时间不能为空");
		return false;
	} else if (mou2 == "") {
		alert("结束时间不能为空");
		return false;
	}
	if (m1 > datatime) {
		alert("选择的开始时间不能大于当前时间");
		mou1.focus();
		return false;

	} else if (m2 > datatime) {
		alert("选择的结束时间不能大于当前时间");
		mou2.focus();
		return false;
	} else if (m1 > m2) {
		alert("开始时间不能大于结束时间");
		return false;
	}
	if (mou1 != null && mou1 != "" && mou2 != null && mou2 != "") {
		var chart = new FusionCharts("<%=basePath%>/util/Pie3D.swf", "0",
				"800", "600");
		chart.setDataURL(escape("BonusmoneyAction!findDate.action?yuefen="
				+ mou1 + "&yuefen2=" + mou2 + ""));
		chart.render('chartDive');
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="<%=basePath%>System/renshi/jjfp_overview.jsp"
						style="color: #ffffff">各班组比例</a>
					<a href="<%=basePath%>BonusmoneyAction!findTeam.action"
						style="color: #ffffff">班组总额</a>
					<a href="<%=basePath%>BonusmoneyAction!findDepthuanbi.action"
						style="color: #ffffff">班组环比</a>
				</div>
			</div>
			
			<div align="center">
				起
				<input type="text" id="mou1" class="Wdate" name="yuefen1"
					onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
				&nbsp;&nbsp;&nbsp; 终
				<input type="text" class="Wdate"
					onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
					name="yuefen2" id="mou2" />
				<input type="button" onclick="bintu()" value="查   询">
				<div id="chartDive" align="center" style="index: 0">

				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
