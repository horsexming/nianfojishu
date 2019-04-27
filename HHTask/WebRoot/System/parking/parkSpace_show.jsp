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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.showdiv {
	border: solid 1px #000000;
	width: 90px;
	height: 90px;
	float: left;
	margin-left: 20px;
	margin-top: 50px;
	text-align: center;
	border-radius: 90%;
	cursor: pointer;
	font-size: 14px;
	font-weight: bolder;
	background-color: red;
	color: #ffffff;
}

.showdiv2 {
	border: solid 1px #000000;
	width: 90px;
	height: 90px;
	float: left;
	margin-left: 20px;
	margin-top: 50px;
	text-align: center;
	border-radius: 90%;
	cursor: pointer;
	font-size: 14px;
	font-weight: bolder;
	background-color: green;
	color: #ffffff;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<%--<h3>
					车位开关
				</h3>
				<table class="table">
					<s:iterator id="parkSpace" value="parkSpaceList"
						status="pageStatus">
						<s:if test="#pageStatus.index%3==0">
							<tr>
						</s:if>
						<td align="center">
							${parkSpace.parkNum}
						</td>
						<td align="center">
							<s:if test="#parkSpace.parkStatus=='打开'">
								<a
									href="ParkSpaceAction_openPark.action?parkSpace.id=${parkSpace.id}&infor=${parkSpace.parkOpen}">${parkSpace.parkNum}打开</a>
							</s:if>
							<s:if test="#parkSpace.parkStatus=='关闭'">
								<a
									href="ParkSpaceAction_openPark.action?parkSpace.id=${parkSpace.id}&infor=${parkSpace.parkClose}">${parkSpace.parkNum}关闭</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<td align="center" colspan="2">
							<a href="ParkSpaceAction_openPark.action?infor=0x0A">打开1</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="ParkSpaceAction_openPark.action?infor=0x1A">关闭1</a>
						</td>
						<td align="center" colspan="2">
							<a href="ParkSpaceAction_openPark.action?infor=0x2A">打开2</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="ParkSpaceAction_openPark.action?infor=0x3A">关闭2</a>
						</td>
						<td align="center" colspan="2">
							<a href="ParkSpaceAction_openPark.action?infor=0x4A">打开3</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="ParkSpaceAction_openPark.action?infor=0x5A">关闭3</a>
						</td>
					</tr>
				</table>
				<br />
				<br />
				<br />
				<br />

				--%>
				<s:iterator id="pageParkSpace" value="parkSpaceList"
					status="pageStatus">
					<s:if test="#pageStatus.index%3==0">
						<div style="clear: both;"></div>
					</s:if>
					<s:if test="#pageParkSpace.parkStatus=='打开'">
							<div class="showdiv2"
								onclick="openOrClose('${pageParkSpace.id}','${pageParkSpace.parkClose}')">
								<br />
								${pageParkSpace.parkingLot}
								<br />
								${pageParkSpace.parkNum}
								<br />
								${pageParkSpace.vipName}
							</div>
						</s:if>
						<s:if test="#pageParkSpace.parkStatus=='关闭'">
							<div class="showdiv"
								onclick="openOrClose('${pageParkSpace.id}','${pageParkSpace.parkOpen}')">
								<br />
								${pageParkSpace.parkingLot}
								<br />
								${pageParkSpace.parkNum}
								<br />
								${pageParkSpace.vipName}
							</div>
						</s:if>
				</s:iterator>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function openOrClose(id, status) {
	window.location.href = "ParkSpaceAction_openPark.action?parkSpace.id=" + id
			+ "&id=" + status + "&tag=${tag}";
}
</script>
	</body>
</html>
