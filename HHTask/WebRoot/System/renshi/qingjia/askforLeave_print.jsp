<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>

	<body onload="convertCurrency(${baoxiaodan.totalMoney})">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">

				<div id="printDiv" align="center">
					<br />
					<br />
					<h2>
						<span></span>
						<table width="95%">
							<tr>
								<th align="left" width="10%" style="vertical-align: middle">
									<img widtn="64px;" height="55px;" src="${companyInfo.logoOKjpg}" />
								</th>
								<th align="left" colspan="2">
									${companyInfo.name}
								</th>
							</tr>
							<tr>
								<th colspan="3" align="center">
									员工休假申请单
								</th>
							</tr>
							<tr>
								<th colspan="3" align="right">
									编号：HR-APP- ${askForLeave.leaveId}
								</th>
							</tr>
						</table>
					</h2>
					<table border="0" width="95%">
						<tr>
							<td>
								申请人姓名：${askForLeave.leavePerson}
							</td>
							<td>
								申请人部门：${askForLeave.leavePersonDept}
							</td>
							<td>
								员工卡号：${askForLeave.leaveUserCardId}
							</td>
						</tr>
					</table>
					<table width="95%" class="table">
						<tr>
							<td align="center" style="width: 9%">
								请假事由：
							</td>
							<td>
								${askForLeave.leaveReason}
							</td>
						</tr>
						<tr>
							<td align="center" width=9%">
								期 &nbsp;&nbsp;限
							</td>
							<td align="center">
								自 ${askForLeave.leaveStartDate} 至 ${askForLeave.leaveEndDate} 共计
								${askForLeave.leaveDays} 天 ${askForLeave.leaveHours} 小时
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						审批
					</div>

					<table width="95%" class="table" id="1">
						<tr>
							<td width=9% "rowspan="2">
								①
								<span style="font-size: 18px;"> □</span> 年休假
							</td>
							<td width="12%">
								本人累计工龄
							</td>
							<td align="right">
								年
							</td>
							<td width=15% colspan="2">
								本年应享受年休假
							</td>
							<td width="8%" align="right">
								天
							</td>
							<td colspan="2" width="12%">
								本年度已休年假
							</td>
							<td align="right" width="6%">
								天
							</td>
						</tr>
						<tr>
							<td>
								次使用 ________天
							</td>
							<td width=10%">
								剩余
							</td>
							<td align="right">
								天
							</td>
							<td colspan="2" width=12%">
								人力资源确认
							</td>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>

					<table width="95%" class="table" id="2">
						<tr>
							<td width=9% " rowspan="2">
								②
								<span style="font-size: 18px;"> □</span> 换&nbsp;&nbsp;&nbsp;休
							</td>
							<td colspan="2" width=12%">
								换休产生时间
							</td>
							<td colspan="3">
								&nbsp;
							</td>
							<td colspan="2">
								累计换休天数
							</td>
							<td align="right" colspan="3">
								天
							</td>
						</tr>
						<tr>
							<td width=7%">
								本次换休
							</td>
							<td align="right">
								天
							</td>
							<td>
								剩余
							</td>
							<td colspan="2" align="right">
								天
							</td>
							<td colspan="2" width=12%">
								部门主管确认
							</td>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>
					<table width="95%" class="table" id="3">
						<tr>
							<td width=9%">
								③
								<span style="font-size: 18px;"> □</span> 公假
							</td>
							<td width=7%">
								公假原因
							</td>
							<td colspan="4">
								&nbsp;
							</td>
							<td colspan="2" width=12%">
								相关部门确认
							</td>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>
					<table width="95%" class="table" id="4">
						<tr>
							<td width=9% "rowspan="2">
								④
								<span style="font-size: 18px;"> □</span> 病假
							</td>
							<td width=7%">
								病假原因
							</td>
							<td>
							</td>
							<td>
								病假天数
							</td>
							<td align="right">
								天
							</td>
							<td colspan="2">
								本年度累计病假天数
							</td>
							<td align="right" colspan="2">
								<div style="width: 100px;"></div>
								天
							</td>
						</tr>
						<tr>
							<td width=7%">
								EHS审批
							</td>
							<td>
							</td>
							<td>
								本厂工龄
							</td>
							<td align="right">
								年
							</td>
							<td>
								工资发放比例
							</td>
							<td align="right">
								%
							</td>
							<td colspan="2" width=12%">
								人力资源审批
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>
					<table width="95%" class="table" id="5">
						<tr>
							<td width=9%" >
								⑤
								<span style="font-size: 18px;"> □</span> 婚假
							</td>
							<td colspan="2" width=12%">
								应享受婚假天数
							</td>
							<td colspan="4" align="right">
								天
							</td>
							<td colspan="2" width=12%">
								人力资源确认
							</td>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>
					<table width="95%" class="table" id="6">
						<tr>
							<td width=9%">
								⑥
								<span style="font-size: 18px;"> □</span> 丧假
							</td>
							<td colspan="2" width=12%">
								应享受丧假天数
							</td>
							<td colspan="4" align="right">
								天
							</td>
							<td colspan="2" width=12%">
								人力资源确认
							</td>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>
					<table width="95%" class="table" id="7">
						<tr>
							<td width=9% "rowspan="2">
								⑦
								<span style="font-size: 18px;"> □</span> 事假
							</td>
							<td colspan="8"></td>
						</tr>
					</table>
					<div style="width: 100%">
						&nbsp;
					</div>
					<table width="95%" class="table" id="6">
						<tr>
							<td width=9%">
								其他说明
							</td>
							<td colspan="8"></td>
						</tr>
					</table>
					<div style="width: 100%"></div>
					<table width="95%" border="0">
						<tr height="45px">
							<td width="10%">
								总经理批准
							</td>
							<td width="20%">
								&nbsp;
							</td>
							<td width="10%">
								副总批准
							</td>
							<td width="20%">
								&nbsp;
							</td>
							<td width="10%" align="left">
								主管审批
							</td>
							<td width="20%">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="10" style="font-size: 12px;">
								备注：
								<br>
								请假1天内（含1天）由部门主管审批，请假3天以内（含3天）由主管副总审批，3天以上须总经理审批，
								年休假3天以上须提前一个月审批。根据请假类别在相应的格子内画勾并由相关部门领导签字确认，本件一式三联，
								第一联交人力资源存档考勤，第二联部门考勤留存，第三联个人留存。
							</td>
						</tr>
					</table>
					<br>
					<img
						src="<%=request.getContextPath()%>/barcode.action?msg=<s:property value="askForLeave.leaveUserCardId" />&type=code128"
						height="40px" width="300px" />
				</div>
			</div>
			<input style="width: 80px; font-size: 18px;"
				onclick="pagePrint('printDiv')" type="button" value="打印">
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">

</script>
	</body>
</html>
