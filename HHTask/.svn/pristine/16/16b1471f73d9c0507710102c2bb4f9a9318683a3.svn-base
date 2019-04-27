<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script type="text/javascript">
function check() {
	var quxuanName = document.getElementsByName("quxuanName");
	var i = 0
	for (i = 0; i < quxuanName.length; i++) {
		document.getElementById("quxian" + i).checked = true;
	}
}
</script>
		<script type="text/javascript">
function check2() {
	var quxuanName = document.getElementsByName("quxuanName");
	var i = 0
	for (i = 0; i < quxuanName.length; i++) {
		document.getElementById("quxian" + i).checked = false;
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="addCbWage">
					<form action="ContractBonusAction!findAllContractBonus.action"
						target="_blank" method="post" style="padding: 0px; margin: 0px;">
						<input type="hidden" name="contractBonus.userId"
							value="${contractBonus.userId}" />
						<input type="hidden" name="pageStauts" value="buliu" />
						<input type="hidden" name="contractBonus.status" value="bumen" />
						<input type="hidden" name="contractBonus.bonusStatus" value="部门部留" />
						<input type="submit" value="查询部留信息" style="height: 50px;">
					</form>
					<div id="usersGroupDiv" style="display: none">
						<table class="table" style="width: 30%">
							<tr>
								<th colspan="6">
									<font size="3">成员分组选择</font>
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="30px">
								<th align="center">
									分组名称
								</th>
								<th align="center">
									操作
								</th>
							</tr>
							<s:iterator value="list" id="upList" status="pageStatus">
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
									${upList.groupName}
								</td>
								<td>
									<a
										href="ContractBonusAction!findTolAndTeam.action?id=${upList.id}">选择</a>
								</td>
								</tr>
							</s:iterator>
						</table>
						
					</div>
					<form action="WageAction!addBumenBonus.action" method="post">
						<input type="hidden" value="cb" name="pageStatus">
						<table>
							<tr>
								<td align="center" colspan="5">
									<font size="5">部门奖金分配</font>
									<br />
									(
									<font color="red">注:</font>所分配的金额将作为绩效工资存入工资模版内)
								</td>
							</tr>
							<tr>
								<td colspan="5" align="right" style="padding-right: 160px;">
									(本部门${contractBonus.bonusMouth}的承包奖金总额为:
									<font color="red"> ${contractBonus.totalMoney}</font> 元)
								</td>
							</tr>
							<tr>
								<th>
									工号
								</th>
								<th>
									卡号
								</th>
								<th>
									部门
								</th>
								<th>
									姓名
								</th>
								<th align="center">
									金额
								</th>
							</tr>
							<s:iterator id="pageWs" value="wSList" status="stauts">
								<s:if test="#stauts.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									${pageWs.code}
									<input type="hidden" name="code" value="${pageWs.code}">
								</td>
								<td align="center">
									${pageWs.cardId}
									<input type="hidden" name="cardId" value="${pageWs.cardId}">
								</td>
								<td align="center">
									${pageWs.dept}
								</td>
								<td align="center">
									${pageWs.userName}
								</td>
								<td>
									<input name="money"
										onkeyup="if(isNaN(value))execCommand('undo')"
										value="${pageWs.jixiaokaohegongzi}" />
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="5" align="center">
									<br />
									<input type="submit" value="确定"
										style="width: 100px; height: 50px;" />
									<input type="reset" value="清空"
										style="width: 100px; height: 50px;" />
									<br />
								</td>
							</tr>
						</table>
					</form>
				</div>

				<div id="showWage" style="display: none;">
					<font color="red">${successMessage}</font>
					<br />
					<br />
					<table>
						<tr>
							<th>
								序号
							</th>
							<th>
								工号
							</th>
							<th>
								卡号
							</th>
							<th>
								姓名
							</th>
							<th>
								月份
							</th>
							<th align="center">
								金额
							</th>
							<th>
								状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator id="pageWage" value="wageList" status="pageStatus">
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
							<td align="center">
								${pageWage.code}
							</td>
							<td align="center">
								${pageWage.cardId}
							</td>
							<td align="center">
								${pageWage.userName}
							</td>
							<td align="center">
								${pageWage.mouth}
							</td>
							<td>
								<s:if test="pageStatus=='cb'||pageStatus=='chengbao'">
								${pageWage.gangweigongzi+pageWage.jixiaokaohegongzi+pageWage.jiabanfei+pageWage.baomijintie+pageWage.dianhuabutie}
								</s:if>
								<s:else>
								${pageWage.jixiaokaohegongzi}
								</s:else>
							</td>
							<td>
								<s:if test="wageStatus=='承包审核'||wageStatus=='重新分配'">
									${pageWage.wageStatus}
								</s:if>
								<s:else>同意</s:else>
							</td>
							<td>
								<s:if test="wageStatus=='承包审核'||wageStatus=='重新分配'">
									<a href="WageAction!delWage.action?id=${pageWage.id}"> 删除 </a>
								</s:if>
								<s:else>
									<a href="javascript:;"> <font color="gray">删除</font> </a>
								</s:else>
							</td>
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
									${errorMessage}
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


		<script type="text/javascript">

onload = function() {
	var wageList = "${wageList}";//工资
	var errorMessage = "${errorMessage}";//承包
	var showWage = document.getElementById("showWage");//显示金额
	var addCbWage = document.getElementById("addCbWage");//承包金额分配
	var addManageWage = document.getElementById("addManageWage");
	if (wageList != "") {
		showWage.style.display = "block";
	} else if (errorMessage == "chengbao") {
		addCbWage.style.display = "block";
	} else if (errorMessage == "manage" || errorMessage == "bing") {
		addManageWage.style.display = "block";
	}
}
$(function() {
	var groupList = "<s:property value='list.size()' />";
	if (groupList > 1) {
		$("#usersGroupDiv").show("");
	}
});
</script>
	</body>
</html>
