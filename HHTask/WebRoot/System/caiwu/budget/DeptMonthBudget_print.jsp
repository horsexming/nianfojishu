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
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="SubMonthMoneyAction!findAuditDmB.action?id=${deptMonthBudget.rootId}&pageStatus=dept"
						style="color: #ffffff">填报明细</a>
				</div>
			</div>
			
			<div align="center">
				<div id="printDiv" class="my_show" align="center"
					style="width: 100%;">
					<table class="table" style="width: 100%; line-height: 40px;">
						<tr>
							<th align="center" colspan="4">
								${deptMonthBudget.name}的计划外预算
							</th>
						</tr>
						<tr>
							<th align="right" colspan="4">
								<img
									src="barcode.action?msg=${deptMonthBudget.borcode}&type=code39"
									style="height: 30px; width: 180px;">
							</th>
						</tr>
						<tr>
							<th align="right">
								计划类型:
							</th>
							<td>
								<s:if test="deptMonthBudget.jhStatus=='nei'">
									 &nbsp;计划内
								</s:if>
								<s:else>
								 	&nbsp; 计划外
								</s:else>
							</td>
							<th align="right">
								审核状态:
							</th>
							<td>
								&nbsp;${deptMonthBudget.status}
							</td>
						</tr>
						<tr>
							<th align="right">
								科目名:
							</th>
							<td>
								&nbsp;${deptMonthBudget.name}
							</td>
							<th align="right">
								预算金额:
							</th>
							<td>
								&nbsp;${deptMonthBudget.accountMoney}元
							</td>
						</tr>
						<tr>
							<th align="right">
								填报人:
							</th>
							<td>
								&nbsp;${deptMonthBudget.username}
							</td>
							<th align="right">
								填报部门:
							</th>
							<td>
								&nbsp;${deptMonthBudget.userDept}
							</td>
						</tr>
						<tr>
							<th align="right">
								提交时间:
							</th>
							<td>
								&nbsp;${deptMonthBudget.subTime}
							</td>
							<th align="right">
								审核时间:
							</th>
							<td>
								&nbsp;${deptMonthBudget.auditTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								说明:
							</th>
							<td colspan="3">
								&nbsp;${deptMonthBudget.budgetDetail}
							</td>
						</tr>
						<tr>
							<td colspan="4" align="right" style="padding-right: 20px;">
								总经理:_____________
							</td>
						</tr>
					</table>
				</div>
				<input type="button" id="print" value="打印" class="input"
					onclick="pagePrint('printDiv','sy')" />
				<br />
				<br />
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
