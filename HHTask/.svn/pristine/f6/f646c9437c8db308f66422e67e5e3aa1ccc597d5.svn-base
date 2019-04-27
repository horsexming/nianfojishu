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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				现场在制品申请入库数据
			</div>
			<br/>
			<div align="center">
				<s:if test="procardVoList==null||procardVoList.size()==0">
					<font color="red">没有找到对应半成品入库数据</font>
				</s:if>
				<s:else>
				<div align="right"><input type="button" value="导出" onclick="exportbcprkData(${id});todisabledone(this)" data="downData" style="height: 30px;width: 60px;"> </div>
				<table class="table">
					<tr>
						<th>序号</th>
						<th>内部订单号</th>
						<th>业务件号</th>
						<th>总成件号</th>
						<th>总成批次</th>
						<th>件号</th>
						<th>版本</th>
						<th>名称</th>
						<th>工序号</th>
						<th>工序名称</th>
						<th>批次</th>
						<th>批次数量</th>
						<th>申请中数量</th>
						<th>已转库数量</th>
						<th>标记转库</th>
					</tr>
					<s:iterator value="procardVoList" id="pageprocard" status="pageStatus1">
					<s:if test="#pageStatus1.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus1.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus1.index+1" />
							</font>
						</td>
						<td>
							${pageprocard.orderNumber}
						</td>
						<td>
							${pageprocard.ywMarkId}
						</td>
						<td>
							${pageprocard.rootMarkId}
						</td>
						<td>
							${pageprocard.rootSelfCard}
						</td>
						<td>
							<b>${pageprocard.markId}</b>
						</td>
						<td>
							<b>${pageprocard.banBenNumber}</b>
						</td>
						<td>
							${pageprocard.proName}
						</td>
						<td>
							${pageprocard.processNo}
						</td>
						<td>
							${pageprocard.processName}
						</td>
						<td>
							${pageprocard.selfCard}
						</td>
						<td>
							${pageprocard.filnalCount}
						</td>
						<td>
							${pageprocard.zaizhiApplyZk}
						</td>
						<td>
							${pageprocard.zaizhizkCount}
						</td>
						<td>
							${pageprocard.flag}
						</td>
					</tr>
					</s:iterator>
				</table>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function exportbcprkData(id){
	window.location.href="ProcardAction!exportbcprkData.action?id="+id;
}
</SCRIPT>
	</body>
</html>