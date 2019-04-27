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
		<title>消息中心,生产力生态平衡系统</title>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 980px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%; margin-top: ">
					<tr>
						<td style="font-size: 13px;">
							${alertMessages.title}
							<font color="red">${alertMessages.addTime}</font>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; height: 100px">
					<div align="left" style="padding: 5 10px; height: 50px;">
						${alertMessages.sendUserName}提醒您:
						<br />
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;${alertMessages.content}(
						<s:if test="alertMessages.functionId!=0">
							<a id="goToHandle" target="_blank"
								href="AlertMessagesAction!findAlertMessagesForUrl.action?id=${alertMessages.id}">前往处理</a>)
										</s:if>
						<s:else>
							<a href="${alertMessages.functionUrl}">前往处理</a>)
									</s:else>
						)
					</div>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">

			<div align="center">
				<div style="color: red">
					${successMessage}
				</div>
				<div align="right"
					style="padding-right: 30px; font-size: 13px; height: 30px;">
					<div></div>
					<div></div>
					<s:if test="alertMessagesList"></s:if>
					<a href="AlertMessagesAction!updateMessagesStatus.action"
						style="margin-right: 5px;">全部设为已读</a>

					<a
						href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus="
						style="margin-right: 5px;">所有消息</a>
					<a
						href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=yes"
						style="margin-right: 5px;">只看已读</a>
					<a
						href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no"
						style="margin-right: 5px;">只看未读</a>
					<a href="AlertMessagesAction!delAllAlertMessages.action"
						onclick="return window.confirm('确定要清空消息吗?')">清空消息</a>
				</div>
				<div>
					<form name="lzyy"
						action="AlertMessagesAction!delAlertMessagess.action"
						method="post" id="xform">

						<table width="99%" border="0" cellspacing="0" cellpadding="3"
							style="border: solid 1px #dadada;">
							<s:iterator value="alertMessagesList" id="pageMessages"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onclick="toChuli('${pageMessages.functionUrl}')"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onclick="toChuli('${pageMessages.functionUrl}')"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td class="tdhorizontalLine" width="50px;">
									<img alt="${pageMessages.sendUserName}提醒您"
										src="${pageMessages.sendUserImg}" height="50px;" width="50px;"
										align="middle" onerror="onerror=this.src='images/man.jpg'">
								</td>
								<td align="left" valign="top"
									style="padding-top: 10px; font-size: 11px;"
									class="tdhorizontalLine">
									${pageMessages.sendUserName}
									<br />
									${pageMessages.addTime}
								</td>
								<td align="left" class="tdhorizontalLine">
									<s:if test="#pageMessages.readStatus=='no'">
										<a href="${pageMessages.functionUrl}" style="color: red;"
											title="未处理">${pageMessages.title}(未处理)</a>
									</s:if>
									<s:else>
										<a href="${pageMessages.functionUrl}" style="color: #000000;"
											title="已处理">${pageMessages.title} </a>(已处理)
								</s:else>
								</td>
								<td class="tdhorizontalLine">
									<a onclick="return window.confirm('确定要删除吗?')"
										href="AlertMessagesAction!delAlertMessages.action?id=${pageMessages.id}">删除</a>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="11" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="11" align="center" style="color: red">
								</s:else>
								</td>
							</tr>
							<s:if test="alertMessagesList.size<=0">
								<tr>
									<td colspan="6" class="tdhorizontalLine" align="center">
										当前没有任何短消息~
									</td>
								</tr>
							</s:if>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
$(function() {
	var alertMessages = "${alertMessages.id}";
	if (alertMessages) {
		chageDiv("block");
	}
	$("#goToHandle").bind("click", function() {
		location.reload();
	});

});
//删除
function del() {
	if (window.confirm('确定要删除吗?')) {
		document.forms.xform.action = "AlertMessagesAction!delAlertMessagess.action";
		document.forms.xform.submit();
	}
}
//设置已读
function yidu() {
	document.forms.xform.action = "AlertMessagesAction!updateMessagesStatus.action";
	document.forms.xform.submit();
}
function selectIt() {
	form = document.lzyy
	action = event.srcElement.name
	for ( var i = 0; i < form.elements.length; i++) {
		if (form.elements[i].name == "ids") {
			e = form.elements[i]
			e.checked = (action == "selectAll") ? (form.selectAll.checked)
					: (!e.checked)
		}
	}
}

function toChuli(url) {
	window.location.href = url;
}
</script>
	</body>
</html>
