<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
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
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
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
								</select>
							</td>
						</tr>
						<tr>

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
							<th align="right">
								状态
							</th>
							<td>
								<select name="deptMonthBudget.status" style="width: 155px;">
									<option></option>
									<option value="同意">
										同意
									</option>
									<option value="审核">
										审核
									</option>
									<option value="打回">
										打回
									</option>
								</select>
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
								剩余金额
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
							<th align="center" style="width: 50px;">
								填报时间
							</th>
							<th align="center">
								说明
							</th>
							<th align="center">
								审批意见
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
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageDmBudget.name}
							</td>
							<td>
								${pageDmBudget.budgetMonth}
							</td>
							<td>
								<fmt:formatNumber type="number" value="${pageDmBudget.accountMoney}" maxFractionDigits="0"/>
							</td>
							<td>
							<fmt:formatNumber type="number" value="${pageDmBudget.accountMoney-pageDmBudget.realMoney} " maxFractionDigits="0"/>
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
									计划外
							</s:else>
							</td>
							<td>
								${pageDmBudget.status}
							</td>
							<td style="width: 100px;">
								${pageDmBudget.subTime}
							</td>
							<td>
								${pageDmBudget.budgetDetail}
							</td>
							<td>
								${pageDmBudget.auditResult}
							</td>
							<td>
								<s:if test="pageStatus=='dept'&&#pageDmBudget.status!='同意'">
									<a
										href="SubMonthMoneyAction!findDmBudget.action?id=${pageDmBudget.id}">修改</a>
									/
									<a onclick="return window.confirm('确定要删除?')"
										href="SubMonthMoneyAction!delDeptMonthBudget.action?id=${pageDmBudget.id}&pageStatus=${pageStatus}">删除</a>

								</s:if>
								<s:else>
									<s:if test="#pageDmBudget.jhStatus=='wai'">
										<a
											href="SubMonthMoneyAction!findDmBudget.action?id=${pageDmBudget.id}&pageStatus=print">打印</a>
									</s:if>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="13" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="13" align="center" style="color: red">
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

//加载查询部门
$(function() {
	var pageStatus = "${pageStatus}";
	if (pageStatus == "all") {
		$("<option ></option>").appendTo("#dept");
		createDept("dept");
	} else if (pageStatus == "dept") {
		$("<option value='${Users.dept}'>${Users.dept}</option>").appendTo(
				"#dept");
	}
})
</script>
	</body>
</html>
