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
		<script type="text/javascript"
			src="<%=basePath%>javascript/FusionCharts.js">
</script>
		<%@include file="/util/sonHead.jsp"%>
		<!-- //根据班组查询出所对应的总额 走势图 -->
		<script type="text/javascript">
function send(obj) {
	var godsSort;
	if (obj == null) {
		godsSort = null;
	} else {
		godsSort = obj.options[obj.options.selectedIndex].value;
	}
	sendRequest("BonusmoneyAction!findTeamMoney.action?type=" + godsSort, Curve);
}
function Curve(obj) {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var chart = new FusionCharts("<%=basePath%>/util/Line.swf", "0",
					"800", "600");
			chart.setDataXML(message);
			chart.render('chartDive');
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
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
				选择班组
				<select name="" onchange="send(this)">
					<option value=""></option>
					<s:iterator id="d" value="deptlist">
						<option value="${d}">
							${d}
						</option>
					</s:iterator>
				</select>
				<div id="chartDive" align="center" style="index: 0">

				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
