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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="SubMonthMoneyAction!findAuditDmB.action" method="post">
					<input type="hidden" name="deptMonthBudget.status" value="审核" />
					<table class="table">
						<tr>
							<th colspan="6">
								部门预算填报管理
							</th>
						</tr>
						<tr>
							<th align="right">
								科目名称:
							</th>
							<td>
								<input name="deptMonthBudget.name" />
							</td>
							<th align="right">
								预算月份:
							</th>
							<td>
								<input class="Wdate" type="text"
									name="deptMonthBudget.budgetMonth"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</td>
							<th align="right">
								计划类型:
							</th>
							<td>
								<select name="deptMonthBudget.jhStatus" style="width: 155px;">
									<option></option>
									<option value="nei">
										计划内
									</option>
									<option value="wai">
										计划外
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								填报人姓名:
							</th>
							<td>
								<input name="deptMonthBudget.username" />
							</td>
							<th align="right">
								填报人部门:
							</th>
							<td>
								<select id="dept" name="deptMonthBudget.userDept"
									style="width: 155px;">
									<option></option>
								</select>
							</td>

							<th align="right">
								&nbsp;
							</th>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="重置" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<form action="" method="post">
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</th>
							<th align="center">
								序号
							</th>
							<th align="center">
								科目名称
							</th>
							<th align="center">
								预算月份
							</th>
							<th align="center">
								预算金额
							</th>
							<th align="center">
								填报人姓名
							</th>
							<th align="center">
								填报人部门
							</th>
							<th align="center">
								计划类型
							</th>
							<th align="center">
								审核状态
							</th>
							<th align="center">
								说明
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageDmBudget" status="pageStatus">
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
								<input type="checkbox" id="${pageDmBudget.accountMoney}"
									name="submmIds" value="${pageDmBudget.id}"
									onclick="chageNum(this,'${pageDmBudget.accountMoney}')">
							</td>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageDmBudget.name}
							</td>
							<td>
								${pageDmBudget.budgetMonth}
							</td>
							<td>
								${pageDmBudget.accountMoney}
							</td>
							<td>
								${pageDmBudget.username}
							</td>
							<td>
								${pageDmBudget.userDept}
							</td>
							<td>
								<s:if test="#pageDmBudget.jhStatus=='nei'">
								计划内
							</s:if>
								<s:else>
									<font color="red">计划外</font>
								</s:else>
							</td>
							<td>
								${pageDmBudget.status}
							</td>
							<td>
								${pageDmBudget.budgetDetail}
							</td>
							<td>
								<a
									href="SubMonthMoneyAction!updateAudit.action?pageStatus=ok&submmIds=${pageDmBudget.id}">同意</a>/
								<a
									href="SubMonthMoneyAction!updateAudit.action?pageStatus=back&submmIds=${pageDmBudget.id}">打回</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5">
								<input type="button" value="批量同意" id="ok" class="input"
									onclick="chageFormAction('ok',this.form)">
								<input type="button" value="批量打回" id="back" class="input"
									onclick="chageFormAction('back',this.form)">
							</td>
							<td colspan="5" align="right">
								预算总额
								<font color="red"> <span id="yingfaMoney">0</span> </font>元
							</td>
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
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

//全选
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll") {
					chageNum(checkBox, checkBox.id);
				}
			}
		}
	}
}

//单选
var num = 0;
function chageNum(obj, yingfagongzi) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	if (check.checked == true) {
		var status = true;
		var inputs = document.getElementsByTagName("input");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
		}
		num += parseFloat(yingfagongzi);
	} else {
		checkAll.checked = false;
		num = num - parseFloat(yingfagongzi);
	}

	document.getElementById("yingfaMoney").innerHTML = Math.round(num * 100) / 100;
}

//改变formAction
function chageFormAction(pageStatus, form) {
	form.action = "SubMonthMoneyAction!updateAudit.action?pageStatus="
			+ pageStatus;
	form.submit();
}

$(function() {
	createDept("dept");
})
</script>
	</body>
</html>
