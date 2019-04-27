<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<script type="text/javascript"
			src="<%=basePath%>/javascript/popwin.js">
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 1024px; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序信息</span>
						</td>
						<td align="right">
							<s:if test="viewStatus=='zjl'">
								<a href="javascript:history.go(-1);">返回</a>
							</s:if>
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
			</div>
			<div id="operatingDiv"
				style="background-color: #ffffff; width: 100%;">
				<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div>
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<div>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<s:if test="errorMessage!=null">
									<font color="red">${errorMessage}</font>
								</s:if>
								<th align="center">
									序号
								</th>
								<th align="center">
									总成件号
								</th>
								<th align="center">
									总成批次
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									批次
								</th>
								<th align="center">
									名称
								</th>
								<th align="center">
									卡片类型
								</th>
								<th align="center">
									产品类型
								</th>
								<th align="center">
									激活时间
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									未领数量
								</th>
								<th align="center">
									状态
								</th>
							</tr>
							<s:iterator value="procardList" id="pageProcard"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pageProcard.rootMarkId}
								</td>
								<td>
									${pageProcard.rootSelfCard}
								</td>
								<th>
									<font color="red"> ${pageProcard.markId}</font>
								</th>
								<td>
									<font color="red">${pageProcard.selfCard}</font>
								</td>
								<td>
									${pageProcard.proName}
								</td>
								<td>
									${pageProcard.procardStyle}
								</td>
								<td>
									${pageProcard.productStyle}
								</td>
								<td>
									${pageProcard.procardTime}
								</td>
								<td>
									${pageProcard.filnalCount}
								</td>
								<td style="color: red">
									<s:if test="pageStatus=='noCardLingliao'">
										<s:if test="#pageProcard.hascount==null">
										${pageProcard.filnalCount}
										</s:if>
										<s:else>
											${pageProcard.hascount}
										</s:else>
									</s:if>
									<s:else>
										${pageProcard.filnalCount-pageProcard.tjNumber}
									</s:else>
								</td>
								<td>
									${pageProcard.status}
								</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<script>
function showLingLiao(id) {
	popWin.showWin("1024", "700", "您正在领料",
			"sellAction!procardLingliaonew.action?id=" + id);
}
function showLingGx(id) {
	$("#showProcess").attr(
			"src",
			"ProcardAction!findProcardByRunCard2.action?id=" + id
					+ "&pageStatus=noCardLingGx&viewStatus=${viewStatus}");
	chageDiv('block');
}
</script>
	</body>
</html>
