<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在设置报价天数</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					选择需要报价的供应商
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							件号名称
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							委外申请数量
						</td>
						<td align="center">
							批次数量
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center">
							周期
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="noPriceprocessList" id="list"
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
							${list.rootMarkId}
						</td>
						<td align="center">
							${list.markId}
						</td>
						<td align="center">
							${list.name}
						</td>
						<td align="center">
							${list.processNO}
						</td>
						<td align="center">
							${list.processName}
						</td>
						<td align="center">
							${list.waiweiShenqiCount}
						</td>
						<td align="center">
							${list.piciCount}
						</td>
						<td align="center">
							${list.bjStartDate}
						</td>
						<td align="center">
							${list.bjEndDate}
						</td>
						<td align="center">
						<s:if test="#list.cycle1=null">
							${list.cycle}天
						</s:if>
						</td>
						<td align="center">
							${list.stutas}
						</td>
						<td align="center">
						<s:if test="#list.cycle==null||#list.cycle==''">
							<a onclick="showProcess(${list.id});">选择报价周期</a>/
						</s:if>
						<s:if test="#list.stutas==null||#list.stutas=='报价中'||#list.stutas==''">
							<a onclick="checkZhuser(${list.id});">选择供应商</a>
						</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
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
		function checkZhuser(id) {
		window.location.href = "NoPriceprocessAction_findAllZhusers.action?noPriceprocess.id="
			+ id + "&cpage=${cpage}";
		}
		function addTime(id) {
		window.location.href = "NoPriceprocessAction_.action?noPriceprocess.id="
			+ id + "&cpage=${cpage}";
		}
		function showProcess(id) {
			$("#showProcess").attr("src", "NoPriceprocessAction_findOne.action?noPriceprocess.id=" + id+ "&cpage=${cpage}");
			chageDiv('block');
		}

</script>
	</body>
</html>
