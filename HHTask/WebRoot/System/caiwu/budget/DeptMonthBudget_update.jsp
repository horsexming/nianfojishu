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
				<div>
					<font color="red">${successMessage}</font>
				</div>
				<form action="SubMonthMoneyAction!updateDmBudget.action"
					method="post" style="margin: 0px; padding: 0px;">
					<input type="hidden" id="id" name="id"
						value="${deptMonthBudget.id}" />
					<table class="table" style="width: 100%;">
						<tr>
							<th align="center" colspan="2">
								修改科目 ${deptMonthBudget.name} 的预算信息 (
								<a
									href="SubMonthMoneyAction!findAuditDmB.action?id=${deptMonthBudget.rootId}&pageStatus=dept">填报明细列表</a>)
							</th>
						</tr>
						<tr>
							<th align="right">
								计划类型:
							</th>
							<td>
								<s:if test="deptMonthBudget.jhStatus=='nei'">
									计划内
								</s:if>
								<s:else>
									计划外
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								科目名:
							</th>
							<td>
								<input id="name" name="deptMonthBudget.name" readonly="readonly"
									value="${deptMonthBudget.name}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								预算金额:
							</th>
							<td>
								<input id="accountMoney" name="deptMonthBudget.accountMoney"
									onkeyup="value=value.replace(/[^\d\.]/g,'')"
									value="${deptMonthBudget.accountMoney}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								说明:
							</th>
							<td>
								<textarea id="budgetDetail" name="deptMonthBudget.budgetDetail"
									rows="5" cols="60">${deptMonthBudget.budgetDetail}</textarea>
							</td>
						</tr>
						<tr>
							<th align="center" colspan="2">
								<input type="submit" value="修改" class="input" />
								<input type="reset" value="重置" class="input" />
							</th>
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
