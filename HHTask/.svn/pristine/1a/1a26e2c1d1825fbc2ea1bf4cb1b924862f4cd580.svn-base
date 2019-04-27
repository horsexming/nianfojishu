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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h3>
					待入库
				</h3>
				<form action="storage_showYiCunGui.action" method="post">
					<table class="table">
						<tr>
							<td align="right">
								名称：
							</td>
							<td align="center">
								<input type="text" name="oa.detailAppName" value="${oa.detailAppName}" />
							</td>
							<td align="right">
								规格：
							</td>
							<td align="center">
								<input type="text" name="oa.detailFormat" value="${oa.detailFormat}" />
							</td>
						</tr>
						<tr>
							<td align="right">
								部门：
							</td>
							<td align="center">
								<input type="text" name="oa.detailAppDept" value="${oa.detailAppDept}" />
							</td>
							<td align="right">
								编号：
							</td>
							<td align="center">
								<input type="text" name="oa.detailSeqNum" value="${oa.detailSeqNum}" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询"
									style="height: 50px; width: 80px;" />
							</td>
						</tr>
					</table>
				</form>
				<h3>
					入库申请详细
				</h3>
				<form action="storage_statisticalProcurement.action" method="post"
					onsubmit="return vali()">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px" align="center">
							<td>
								序号
							</td>
							<td>
								部门
							</td>
							<td>
								类别
							</td>
							<td>
								编号
							</td>
							<td>
								名称
							</td>
							<td>
								规格
							</td>
							<td>
								单位
							</td>
							<td>
								数量
							</td>
							<td>
								计划月份
							</td>
							<td>
								到货期限
							</td>
							<td>
								计划依据
							</td>
							<td>
								预算金额
							</td>
							<td>
								加急
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageList.detailAppDept}
							</td>
							<td>
								${pageList.detailChildClass}
							</td>
							<td>
								${pageList.detailSeqNum}
							</td>
							<td>
								${pageList.detailAppName}
							</td>
							<td>
								${pageList.detailFormat}
							</td>
							<td>
								${pageList.detailUnit}
							</td>
							<td>
								${pageList.detailCount}
							</td>
							<td>
								${pageList.detailPlanMon}
							</td>
							<td>
								${pageList.detailArrDate}
							</td>
							<td>
								${pageList.detailPlanAcco}
							</td>
							<td>
								${pageList.detailBudgetMoney}
							</td>
							<td>
								${pageList.detailIsBusy}
							</td>
							<td>
								<a
									href="storage_getOaAppDetail.action?vsto.oaDetailId=${pageList.id}&vsto.applyForNum=${vsto.applyForNum}&vsto.jump=gz&vsto.number=${pageList.id}">入库</a>
							</td>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="14" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="14" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
