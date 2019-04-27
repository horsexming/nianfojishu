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
	<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%" id="carzuo">
					<tr>
						<td>
							<span id="title">请添加时间</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none');reload();">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<input type="button" value="添加时间" onclick="toadd('${accessEquipment.id}')"/>
				</div> 
			<div align="center">
				<h3>
				门禁可进时间段
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="accessTimeList" id="access"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${access.startTime}
						</td>
						<td align="center">
							${access.endTime}
						</td>
						<td align="center" colspan="2">
							<a style="text-decoration:none;" onclick="toupdate('${accessEquipment.id}','${access.id}')">修改</a>
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
							 	href="AccessEquipmentAction_deleteAT.action?accessTime.id=${access.id}&accessEquipment.id=${accessEquipment.id}">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
						</s:if>
						<s:else>
							<td colspan="5" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toadd(id) {
	var url = "AccessEquipmentAction_toaddTime.action?accessEquipment.id="
			+ id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function toupdate(id,timeid) {
	var url = "AccessEquipmentAction_toupdateAT.action?accessTime.id="
			+ timeid + "&accessEquipment.id=" + id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
