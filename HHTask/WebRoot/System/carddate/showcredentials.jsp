<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
</script>
		</style>
		<script type="text/javascript">
</script>

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看证件信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<center>
			<div>
				<h3>
					显示证件信息
				</h3>
				<div align="center">
					<font color="red"><s:property value="#request.successMessage" /> </font>
				</div>
				<div id="module1_1" align="center">
					<div id="bangding">
						<form action="CredentialsAction!showallcredentials.action?ps=${ps}"
							method="post" style="margin: 0px">
							<br>
							<s:if test='ps=="all"'>
								<table class="table">
									<tr>
										<td align="center">
											员工工号:
											<input type="text" name="credentials.code" />
											<input type="submit" value="查询"
												style="width: 100px; height: 40px;" />
										</td>
									</tr>
								</table>
							</s:if>
						</form>
						<table class="table">
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold;">
								<td>
									序号
								</td>
								<td>
									姓名
								</td>
								<td>
									工号
								</td>
								<td>
									证件类型
								</td>
								<td>
									车牌/驾驶证类型
								</td>
								<td>
									有效期限
								</td>
								<td>
									周期(年)
								</td>
								<td>
									操作
								</td>
							</tr>
							<s:iterator id="pagecredentials" value="credentialslist"
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
								<td>
									${pagecredentials.name}
								</td>
								<td>
									${pagecredentials.code}
								</td>
								<td>
									${pagecredentials.cardtype}
								</td>
								<td>
									${pagecredentials.platenumber}
									${pagecredentials.cartype}
								</td>
								<td>
									${pagecredentials.validfor}
								</td>
								<td>
									${pagecredentials.cycle}
								</td>
								<td>
									<a onclick="tanchu('${pagecredentials.id}')">查看</a>
									<s:if test='ps=="all"||#pagecredentials.status!="同意"'>
										<a onclick="tanchu1('${pagecredentials.id}')">/修改</a>
										<a onclick="return window.confirm('您将删除数据，是否继续?')"
											href="CredentialsAction!delete.action?id=${pagecredentials.id}&cpage=${cpage}&ps=${ps}">/删除</a>
									</s:if>
									<s:if test='#pagecredentials.isGong!="yes"&&#pagecredentials.status!="未审批"&&#pagecredentials.cardtype=="行驶证"'>
										<a href="CredentialsAction!shenqignGongche.action?credentials.id=${pagecredentials.id}&ps=${ps}">/申请转公车</a>
									</s:if>
									<s:if test='#pagecredentials.epId!=null'>
										<a href="CircuitRunAction_findAduitPage.action?id=${pagecredentials.epId}">/审批动态</a>
									</s:if>
								</td>
							</s:iterator>
							<tr>

								<s:if test="errorMessage==null">
									<td colspan="12" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
									</td>
								</s:if>
								<s:else>
									<td colspan="12" align="center">
										<font color="red"><s:property
												value="#request.errorMessage" /> </font>
									</td>
								</s:else>
							</tr>
						</table>
					</div>
				</div>
				<div align="center">
					<%
						request.getSession().removeAttribute("successMessage");
						request.getSession().removeAttribute("errorMessage");
					%>
					<br />
					<br />
					<br />
				</div>
			</div>
		</center>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function tanchu(id) {
	document.getElementById("xiugaiIframe").src = "CredentialsAction!findcredentialsById.action?id="
			+ id;
	chageDiv('block')
}
function tanchu1(id) {
	document.getElementById("xiugaiIframe").src = "CredentialsAction!toupdate.action?ps=${ps}&tage=2&id="
			+ id + "&cpage=${cpage}";
	chageDiv('block')
}
</script>
	</body>
</html>
