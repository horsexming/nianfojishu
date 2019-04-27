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
	<body onload="">
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
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">添加金额</a>
					<a
						href="ContractBonusAction!findAllContractBonus.action?pageStauts=${pageStauts}"
						style="color: #ffffff">查看所有总金额</a>
				</div>
			</div>
			
			<div align="center">
				<div id="addContractBonus" style="display: none;">
					<font color="red">${successMessage}</font>
					<form action="ContractBonusAction!addContractBonus.action"
						method="post" onsubmit="return checkContractBonusForm()">
						<input type="hidden" name="pageStauts" value="${pageStauts}">
						<input type="hidden" name="contractBonus.status"
							value="${pageStauts}">
						<table>
							<tr>
								<th colspan="2" align="center">
									添加${moduleFunction.functionName}
								</th>
							</tr>
							<tr>
								<th align="right">
									人员:
								</th>
								<td align="left">
									<select id="userMessage" style="width: 155px;" name="id">
										<s:iterator id="pageUser" value="list">
											<option value="${pageUser.id}">
												${pageUser.name}
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<s:if test="pageStauts!='bumen'">
								<tr>
									<th align="right">
										月份:
									</th>
									<td align="left">
										<input id="bonusMouth" class="Wdate" type="text"
											name="contractBonus.bonusMouth"
											onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
									</td>
								</tr>
							</s:if>
							<s:else>
								<input id="bonusMouth" class="Wdate" type="hidden"
									name="contractBonus.bonusMouth"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</s:else>
							<tr>
								<th align="right">
									总金额:
								</th>
								<td align="left">
									<input id="totalMoney" name="contractBonus.totalMoney"
										onkeyup="if(isNaN(value))execCommand('undo')" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" value="添加"
										style="width: 80px; height: 50px;">
									<input type="submit" value="重置"
										style="width: 80px; height: 50px;">
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="showContractBonus" style="display: none;">
					<font color="red">${successMessage}</font>
					<s:if test="pageStauts!='buliu'">
						<form action="ContractBonusAction!findAllContractBonus.action"
							method="post">
							<input type="hidden" name="pageStauts" value="${pageStauts}">
							<table>
								<tr>
									<th>
										部门:
									</th>
									<td>
										<select id="deptName2" style="width: 155px;"
											name="contractBonus.deptName"
											onclick="createDept('deptName2')">
											<option></option>
											<option value="${contractBonus.deptName}">
												${contractBonus.deptName}
											</option>
										</select>
									</td>
									<th>
										月份:
									</th>
									<td>
										<input id="bonusMouth" class="Wdate" type="text"
											name="contractBonus.bonusMouth"
											onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"
											value="${contractBonus.bonusMouth}" />
									</td>
								</tr>
								<tr>
									<th>
										状态:
									</th>
									<td>
										<select name="contractBonus.bonusStatus" style="width: 155px;">
											<option></option>
											<option value="审核">
												审核
											</option>
											<option value="同意">
												同意
											</option>
										</select>
									</td>
									<td></td>
									<td>
										<input type="submit" value="查询" style="width: 100px;">
									</td>
								</tr>
							</table>
						</form>
					</s:if>
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								卡号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								总金额
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="contractBonusList" id="pageContractBonus"
							status="pageStatus">
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
								${pageContractBonus.code}
							</td>
							<td>
								${pageContractBonus.cardId}
							</td>
							<td>
								${pageContractBonus.userName}
							</td>
							<td>
								${pageContractBonus.deptName}
							</td>
							<td>
								${pageContractBonus.bonusMouth}
							</td>
							<td>
								${pageContractBonus.totalMoney}
							</td>
							<td>
								${pageContractBonus.bonusStatus}
							</td>
							<s:if test="pageStauts=='buliu'">

							</s:if>
							<s:if test="pageStauts=='allBuLiu'">
								<s:if test="#pageContractBonus.ifReceive=='yes'">
									<td>
										<a
											href="ContractBonusAction!findCdr.action?id=${pageContractBonus.id}">领取记录</a>

									</td>
								</s:if>
								<s:else>
									<td>
										<a
											href="ContractBonusAction!findContractBonusById.action?id=${pageContractBonus.id}&pageStauts=receive">领取</a>

									</td>
								</s:else>
							</s:if>
							<s:else>
								<td>
									<s:if test='"打回"==#pageContractBonus.bonusStatus'>
										<a
											href="ContractBonusAction!findContractBonusById.action?id=${pageContractBonus.id}&pageStauts=${pageStauts}">修改</a>/
									<a onclick="return window.confirm('确定删除?')"
											href="ContractBonusAction!delContractBonus.action?id=${pageContractBonus.id}&pageStauts=${pageStauts}">
											删除 </a>
									</s:if>
								</td>
							</s:else>
							</tr>
						</s:iterator>
						<tr>
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
				</div>
				<div id="updateContractBonus" style="display: none;">
					<font color="red">${successMessage}</font>
					<form action="ContractBonusAction!updateContractBonus.action"
						method="post">
						<input type="hidden" name="contractBonus.id"
							value="${contractBonus.id}">
						<input type="hidden" name="pageStauts" value="${pageStauts}">
						<table>
							<tr>
								<th colspan="2" align="center">
									修改 ${contractBonus.userName} 的承包奖金总额
								</th>
							</tr>
							<tr>
								<th align="right">
									人员:
								</th>
								<td align="left">
									<select id="userMessage" style="width: 155px;" name="id">
										<option value="${contractBonus.userId}">
											${contractBonus.userName}
										</option>
									</select>
								</td>
							</tr>
							<s:if test="pageStauts!='bumen'">
								<tr>
									<th align="right">
										月份:
									</th>
									<td align="left">
										<input id="bonusMouth" class="Wdate" type="text"
											name="contractBonus.bonusMouth"
											onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
									</td>
								</tr>
							</s:if>
							<s:else>
								<input id="bonusMouth" class="Wdate" type="hidden"
									name="contractBonus.bonusMouth"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</s:else>
							<tr>
								<th align="right">
									总金额:
								</th>
								<td align="left">
									<input id="totalMoney2" name="contractBonus.totalMoney"
										onkeyup="if(isNaN(value))execCommand('undo')"
										value="${contractBonus.totalMoney}" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" value="修改"
										style="width: 80px; height: 50px;">
									<input type="submit" value="重置"
										style="width: 80px; height: 50px;">
								</td>
							</tr>
						</table>
					</form>
				</div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
//form表单检查
function checkContractBonusForm() {
	var userMessage = document.getElementById("userMessage");//部门名称
	var bonusMouth = document.getElementById("bonusMouth");//奖金月份
	var totalMoney = document.getElementById("totalMoney");//总金额
	if (userMessage.value == "") {
		alert("请选择人员!");
		userMessage.focus();
		return false;
	} else if (bonusMouth.value == "" && "${pageStauts}" == "chengbao") {
		alert("月份不能为空!");
		bonusMouth.focus();
		return false;
	} else if (totalMoney.value == "") {
		alert("总金额不能为空!");
		totalMoney.focus();
		return false;
	} else {
		return true;
	}

}

//流程判断(添加/显示/修改等)
onload = function chackProcess() {
	var contractBonusList = "${contractBonusList}";
	var contractBonus = "${request.contractBonus}";
	var showContractBonus = document.getElementById("showContractBonus");//展示DIv
	var addContractBonus = document.getElementById("addContractBonus");//添加DIv
	var updateContractBonus = document.getElementById("updateContractBonus");//修改Div
	if (contractBonusList != "") {
		showContractBonus.style.display = "block";
	} else if (contractBonus != "") {
		updateContractBonus.style.display = "block";
	} else {
		addContractBonus.style.display = "block";
	}
}
</script>
	</body>
</html>
