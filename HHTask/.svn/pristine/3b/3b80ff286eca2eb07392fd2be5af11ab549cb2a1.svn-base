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
							<span id="title">您正在进行绑定管理员卡号操作:</span>
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
				<h3>
					卷帘门操作查询
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							门名称
						</td>
						<td align="center">
							申请开始时间
						</td>
						<td align="center">
							申请关门时间
						</td>
						<td align="center">
							时长
						</td>
						<td align="center">
							设备IP
						</td>
						<s:if test="tag!='no'">
							<td align="center" colspan="2">
								操作类型
							</td>
						</s:if>
						<s:elseif test="tag=='no'">
							<td align="center" colspan="2">
								操作类型
							</td>
						</s:elseif>
					</tr>
					<s:iterator value="operationList" id="samples" status="pageStatus">
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
							${samples.doorName}
						</td>
						<td align="center">
							${samples.startTime}
						</td>
						<td align="center">
							${samples.endTime}
						</td>
						<td align="center">
							${samples.timeLength}
						</td>
						<td align="center">
							${samples.doorIP}
						</td>
						<s:if test="tag!='no'">
							<td align="center" colspan="2">
								<a
									onclick="openDoor('${samples.id}','${samples.doorIP}','${samples.doorPort}')">打开</a>
								<a
									onclick="closeDoor('${samples.id}','${samples.doorIP}','${samples.doorPort}')">/关闭</a>
							</td>
						</s:if>
						<s:elseif test="tag=='no'">
							<td align="center" colspan="2">
								<a
									onclick="closeDoor('${samples.id}','${samples.doorIP}','${samples.doorPort}')">关闭</a>
							</td>
						</s:elseif>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function openDoor(id, doorIp, doorPort) {
	$.ajax( {
		url : "JLMApplicationAction_OpenDoorById.action",
		type : "POST",
		data : {
			id : id,
			doorIp : doorIp,
			doorPort : doorPort
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data != null) {
				if (data.success) {
					//getcheckList2();
		alert(data.message)
	} else {
		alert(data.message)
	}
}
},
error : function() {
alert("服务器异常!");
}
	});
}
function closeDoor(id, doorIp, doorPort) {
	$.ajax( {
		url : "JLMApplicationAction_ColseDoorById.action",
		type : "POST",
		data : {
			id : id,
			doorIp : doorIp,
			doorPort : doorPort
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data != null) {
				if (data.success) {
					//getcheckList2();
		alert(data.message)
	} else {
		alert(data.message)
	}
}
},
error : function() {
alert("服务器异常!");
}
	});
}
</script>
	</body>
</html>
