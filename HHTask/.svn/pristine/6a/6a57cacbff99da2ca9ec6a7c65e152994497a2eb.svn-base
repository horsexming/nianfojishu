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
		<script type="text/javascript">
</script>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								件号
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
								版次
							</th>
							<th align="center">
								版本号
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pageProcardTem.markId}
							</td>
							<td style="width: 180px;">
								${pageProcardTem.proName}
							</td>
							<td>
								${pageProcardTem.procardStyle}
							</td>
							<td>
								${pageProcardTem.productStyle}
							</td>
							<td>
								${pageProcardTem.banBenNumber}
							</td>
							<td>
								${pageProcardTem.banci}
							</td>
							<td>
								${pageProcardTem.bzStatus}
							</td>
							<td align="center">
								<a target="_showBom"
										href="ProcardTemplateAction!findCardTemForShow.action?id=${pageProcardTem.id}&&pageStatus=view">明细</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="9" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="9" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
