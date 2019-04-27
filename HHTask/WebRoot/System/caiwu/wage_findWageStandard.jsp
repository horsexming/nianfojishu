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
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
$(function() {
	var pageStatus = "${pageStatus}";
	if (pageStatus == "find" || pageStatus == "all") {
		$("#findWageStrandar").show("slow");
	} else if (pageStatus == "update") {
		$("#updateWageStrandar").show("slow");
	} else if (pageStatus == "details") {
		$("#detailsWageStrandar").show("slow");
	}
});

//表单检查
function checkForm() {
	var cardId = document.getElementById("cardId");
	var userName = document.getElementById("userName");
	var dept = document.getElementById("dept");
	var code = document.getElementById("code");
	var gangweigongzi = document.getElementById("gangweigongzi");
	var baomijintie = document.getElementById("baomijintie");
	var dhbt = document.getElementById("dhbt");
	var jixiaokaohegongzi = document.getElementById("jixiaokaohegongzi");
	var gongjijin = document.getElementById("gongjijin");
	var ssBase = document.getElementById("ssBase");
	var jinenggongzi = document.getElementById("jinenggongzi");
	var gonglinggongzi = document.getElementById("gonglinggongzi");
	var isOnWork = document.getElementById("isOnWork");

	if (code.value == "") {
		alert("工号不能为空!");
		code.focus();
		return false;
	} else if (cardId.value == "") {
		alert("卡号不能为空!");
		cardId.focus();
		return false;
	} else if (userName.value == "") {
		alert("员工名称不能为空!");
		userName.focus();
		return false;
	} else if (dept.value == "") {
		alert("所属部门不能为空!");
		dept.focus();
		return false;
	} else if (gangweigongzi.value == "") {
		alert("岗位工资不能为空!");
		gangweigongzi.focus();
		return false;
	} else if (baomijintie.value == "") {
		alert("保密津贴不能为空!");
		baomijintie.focus();
		return false;
	} else if (dhbt.value == "") {
		alert("补贴不能为空!");
		dhbt.focus();
		return false;
	} else if (jinenggongzi.value == "") {
		alert("技能工资不能为空!");
		jinenggongzi.focus();
		return false;
	} else if (gonglinggongzi.value == "") {
		alert("岗位工资不能为空!");
		gonglinggongzi.focus();
		return false;
	} else if (jixiaokaohegongzi.value == "") {
		alert("绩效考核工资不能为空!");
		jixiaokaohegongzi.focus();
		return false;
	} else if (gongjijin.value == "") {
		alert("公积金基数不能为空!");
		gongjijin.focus();
		return false;
	} else if (ssBase.value == "") {
		alert("社保基金基数不能为空!");
		ssBase.focus();
		return false;
	} else if (isOnWork.value == "") {
		alert("修改说明不能为空!");
		isOnWork.focus();
		return false;
	} else {
		return true;
	}

}
</script>
	</head>
	<body bgcolor="#ffffff" onload="createDept('selectDept')">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng">
				<!-- 查询所有工资模板 -->
				<div align="center" id="findWageStrandar" style="display: none;">
					<form action="WageStandardAction!findWSByCondition.action"
						method="post" style="margin: 0px;">
						<input type="hidden" name="wageStandard.standardStatus" value="默认">
						<input type="hidden" name="pageStatus" value="${pageStatus}">
						<table class="table">
							<tr>
								<th colspan="6">
									工资模板查询
								</th>
							</tr>
							<tr>
								<td align="right">
									姓名:
								</td>
								<td align="left">
									<input name="wageStandard.userName" />
								</td>
								<td align="right">
									工号:
								</td>
								<td align="left">
									<input name="wageStandard.code" />
								</td>
							</tr>
							<tr>
								<td align="right">
									部门:
								</td>
								<td align="left">
									<select name="wageStandard.dept" id="selectDept"
										style="width: 155px">
										<option></option>
									</select>
								</td>
								<td align="right">
									卡号:
								</td>
								<td align="left">
									<input name="wageStandard.cardId">
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input value="查询" type="submit"
										style="width: 100px; height: 50px">
									<input value="重置" type="reset"
										style="width: 100px; height: 50px">
								</td>
							</tr>
						</table>
					</form>

					<table class="table2">
						<tr bgcolor="#c0dcf2" height="50px"
							style="border-collapse: separate;">
							<th align="center">
								序号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								岗位工资
							</th>
							<th align="center">
								保密津贴
							</th>
							<th align="center">
								补贴
							</th>
							<th align="right">
								技能工资
							</th>
							<th align="right">
								特殊补贴
							</th>
							<th align="center">
								绩效工资
							</th>
							<th align="center">
								养老保险
							</th>
							<th align="center">
								医疗保险
							</th>
							<th align="center">
								失业保险
							</th>
							<th align="center">
								公积金
							</th>
							<th align="center">
								审核状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:if test="pageStatus=='all'">
							<s:iterator value="updateWsList" id="pageUpdateWsList"
								status="pageStatus">
								<s:if test="#pageStatus.first">
									<tr bgcolor="red" height="30px;">
										<th colspan="16" style="color: #ffffff;">
											需调整工资模版人员
										</th>
									</tr>
								</s:if>
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 40px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 40px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pageUpdateWsList.userName}
								</td>
								<td>
									${pageUpdateWsList.code}
								</td>
								<td>
									${pageUpdateWsList.dept}
								</td>
								<td>
									${pageUpdateWsList.gangweigongzi}
								</td>
								<td>
									${pageUpdateWsList.baomijintie}
								</td>
								<td>
									${pageUpdateWsList.dianhuabutie}
								</td>
								<td>
									${pageUpdateWsList.jinenggongzi}
								</td>
								<td>
									${pageUpdateWsList.gonglinggongzi}
								</td>
								<td>
									${pageUpdateWsList.jixiaokaohegongzi}
								</td>
								<td>
									${pageUpdateWsList.tongchoujin}
								</td>
								<td>
									${pageUpdateWsList.yiliaobaoxian}
								</td>
								<td>
									${pageUpdateWsList.shiyebaoxian}
								</td>
								<td>
									${pageUpdateWsList.gongjijin}
								</td>
								<td>
									${pageUpdateWsList.processStatus}
								</td>
								<td>
									<a
										href="WageStandardAction!findWsById.action?id=${pageUpdateWsList.id}&pageStatus=update">修改</a>|
									<a target="_blank"
										href="WageStandardAction!findWsById.action?id=${pageUpdateWsList.id}&pageStatus=details">详细</a>|
									<a target="_blank"
										href="ContractAction!findContractByCode.action?code=${pageUpdateWsList.code}&isUse=1">调整协议</a>
								</td>
								</tr>

							</s:iterator>
						</s:if>
						<s:iterator value="auditWsList" id="pageAuditWsList"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red" height="30px;">
									<th colspan="16" style="color: #ffffff;">
										在审核处理工资模版
									</th>
								</tr>
							</s:if>
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 40px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 40px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageAuditWsList.userName}
							</td>
							<td>
								${pageAuditWsList.code}
							</td>
							<td>
								${pageAuditWsList.dept}
							</td>
							<td>
								${pageAuditWsList.gangweigongzi}
							</td>
							<td>
								${pageAuditWsList.baomijintie}
							</td>
							<td>
								${pageAuditWsList.dianhuabutie}
							</td>
							<td>
								${pageAuditWsList.jinenggongzi}
							</td>
							<td>
								${pageAuditWsList.gonglinggongzi}
							</td>
							<td>
								${pageAuditWsList.jixiaokaohegongzi}
							</td>
							<td>
								${pageAuditWsList.tongchoujin}
							</td>
							<td>
								${pageAuditWsList.yiliaobaoxian}
							</td>
							<td>
								${pageAuditWsList.shiyebaoxian}
							</td>
							<td>
								${pageAuditWsList.gongjijin}
							</td>
							<td>
								${pageAuditWsList.processStatus}
							</td>
							<td>
								<a
									href="WageStandardAction!delWs.action?id=${pageAuditWsList.id}"
									onclick="return window.confirm('确定删除吗?')">删除</a>|
								<a
									href="WageStandardAction!findWsById.action?id=${pageAuditWsList.id}&pageStatus=update">修改</a>|
								<a target="_blank"
									href="WageStandardAction!findWsById.action?id=${pageAuditWsList.id}&pageStatus=details">详细</a>|
								<a target="_blank"
									href="ContractAction!findContractByCode.action?code=${pageAuditWsList.code}&isUse=2">调整协议</a>
							</td>
							</tr>
							<s:if test="#pageStatus.last">
								<tr bgcolor="#c0dcf2" height="50px"
									style="border-collapse: separate;">
									<th align="center">
										序号
									</th>
									<th align="center">
										姓名
									</th>
									<th align="center">
										工号
									</th>
									<th align="center">
										部门
									</th>
									<th align="center">
										岗位工资
									</th>
									<th align="center">
										保密津贴
									</th>
									<th align="center">
										补贴
									</th>
									<th align="right">
										技能工资
									</th>
									<th align="right">
										特殊补贴
									</th>
									<th align="center">
										绩效工资
									</th>
									<th align="center">
										养老保险
									</th>
									<th align="center">
										医疗保险
									</th>
									<th align="center">
										失业保险
									</th>
									<th align="center">
										公积金
									</th>
									<th align="center">
										审核状态
									</th>
									<th align="center">
										操作
									</th>
								</tr>
							</s:if>
						</s:iterator>
						<tr bgcolor="green" height="30px">
							<th colspan="16" style="color: #ffffff;">
								默认工资模版
							</th>
						</tr>
						<s:iterator value="WsList" id="pageWsList" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 40px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 40px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageWsList.userName}
							</td>
							<td>
								${pageWsList.code}
							</td>
							<td>
								${pageWsList.dept}
							</td>
							<td>
								${pageWsList.gangweigongzi}
							</td>
							<td>
								${pageWsList.baomijintie}
							</td>
							<td>
								${pageWsList.dianhuabutie}
							</td>
							<td>
								${pageWsList.jinenggongzi}
							</td>
							<td>
								${pageWsList.gonglinggongzi}
							</td>
							<td>
								${pageWsList.jixiaokaohegongzi}
							</td>
							<td>
								${pageWsList.tongchoujin}
							</td>
							<td>
								${pageWsList.yiliaobaoxian}
							</td>
							<td>
								${pageWsList.shiyebaoxian}
							</td>
							<td>
								${pageWsList.gongjijin}
							</td>
							<td>
								${pageWsList.processStatus}
							</td>
							<td style="width: 70px;">
								<a target="_blank"
									href="WageStandardAction!findWsById.action?id=${pageWsList.id}&pageStatus=details">详细</a>|
								<a href="WageStandardAction!delWs.action?id=${pageWsList.id}"
									onclick="return window.confirm('删除操作将清空${pageWsList.userName}的所有工资模版信息,确定删除吗?')">删除</a>|
								<a
									href="WageStandardAction!findWagePrintByCode.action?code=${pageWsList.code}&cardId=${pageWsList.cardId}"
									target="_blank" title="打印工资调整通知单">打印</a>|
								<a target="_blank"
									href="WageStandardAction!findWageHistoryByCode.action?code=${pageWsList.code}&cardId=${pageWsList.cardId}"
									title="查询所 有工资历史信息">历史</a>|
								<s:if test="pageStatus == 'all'">
									<a
										href="WageStandardAction!findWsById.action?id=${pageWsList.id}&pageStatus=update">调整</a>
								</s:if>

							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="16 " align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="16" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
					</table>
				</div>
				<!-- 修改工资模板 -->
				<div id="updateWageStrandar" style="display: none">
					${successMessage}
					<form action="WageStandardAction!updateWs.action"
						onsubmit="return checkForm()" method="post">
						<input type="hidden" name="id" value="${wageStandard.id}">
						<table width="100%" class="table">
							<tr>
								<th align="center" colspan="6">
									调整
									<font color="red">${wageStandard.userName}</font> 的工资模板
								</th>
							</tr>
							<tr>
								<th align="right">
									工号:
								</th>
								<td>
									<input id="code" onblur="send(this)" name="wageStandard.code"
										readonly="readonly" value="${wageStandard.code}" />
								</td>
								<th align="right">
									卡号:
								</th>
								<td>
									<input id="cardId" name="wageStandard.cardId"
										readonly="readonly" title="只读" value="${wageStandard.cardId}" />
								</td>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName" name="wageStandard.userName"
										value="${wageStandard.userName}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									部门:
								</th>
								<td>
									<input id="dept" name="wageStandard.dept"
										value="${wageStandard.dept}" />
								</td>
								<th align="right">
									岗位工资:
								</th>
								<td>
									<input id="gangweigongzi" name="wageStandard.gangweigongzi"
										value="${wageStandard.gangweigongzi}" />
								</td>
								<th align="right">
									保密津贴:
								</th>
								<td>
									<input id="baomijintie" name="wageStandard.baomijintie"
										value="${wageStandard.baomijintie}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									绩效考核工资:
								</th>
								<td>
									<input id="jixiaokaohegongzi"
										name="wageStandard.jixiaokaohegongzi"
										value="${wageStandard.jixiaokaohegongzi}" />
								</td>
							</tr>
							<tr>
								<th colspan="6">
									其他工资模版项查看
								</th>
							</tr>
							<tr>
								<th align="right">
									补贴:
								</th>
								<td>
									<input id="dhbt" name="wageStandard.dianhuabutie"
										value="${wageStandard.dianhuabutie}" />
								</td>
								<th align="right">
									技能工资:
								</th>
								<td>
									<input id="jinenggongzi" name="wageStandard.jinenggongzi"
										value="${wageStandard.jinenggongzi}" />
								</td>
								<th align="right">
									特殊补贴:
								</th>
								<td>
									<input id="gonglinggongzi" name="wageStandard.gonglinggongzi"
										value="${wageStandard.gonglinggongzi}" />
								</td>

							</tr>

							<tr>
								<th align="right">
									住房费:
								</th>
								<td>
									<input id="jixiaokaohegongzi" name="wageStandard.fangzufei"
										value="${wageStandard.fangzufei}" />
								</td>
								<th align="right">
									是否补差:
								</th>
								<td>
									<s:if test="wageStandard.bucha=='yes'">
										<input type="radio" name="wageStandard.bucha"
											checked="checked" value="yes" />
									是
									<input type="radio" name="wageStandard.bucha" value="no" />
									否
									</s:if>
									<s:else>
										<input type="radio" name="wageStandard.bucha" value="yes" />
									是
									<input type="radio" name="wageStandard.bucha" value="no"
											checked="checked" />
									否
									</s:else>
								</td>
							</tr>
							<tr>
								<th align="right">
									公积金基数:
								</th>
								<td>
									<input title="为0时表示不交公积金" id="gongjijin" type="text"
										name="wageStandard.gjjBase" value="${wageStandard.gjjBase}">
								</td>
								<th align="right">
									公积金:
								</th>
								<td>
									<input type="text" name="wageStandard.gongjijin"
										value="${wageStandard.gongjijin}">
								</td>
								<th align="right">
									社保基数:
								</th>
								<td>
									<input id="ssBase" type="text" name="wageStandard.ssBase"
										value="${wageStandard.ssBase}">
								</td>
							</tr>
							<tr>
								<th align="right">
									养老保险:
								</th>
								<td>
									<input type="text" name="wageStandard.tongchoujin"
										value="${wageStandard.tongchoujin}" />
								</td>
								<th align="right">
									医疗保险:
								</th>
								<td>
									<input type="text" name="wageStandard.yiliaobaoxian"
										value="${wageStandard.yiliaobaoxian}" />
								</td>
								<th align="right">
									失业保险:
								</th>
								<td>
									<input type="text" name="wageStandard.shiyebaoxian"
										value="${wageStandard.shiyebaoxian}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									本地或外地:
								</th>
								<td>
									<s:if test="wageStandard.localOrField=='本地'">
										<input type="radio" name="wageStandard.localOrField"
											checked="checked" value="本地">
									本地
									<input type="radio" name="wageStandard.localOrField" value="外地">
									外地
									</s:if>
									<s:else>
										<input type="radio" name="wageStandard.localOrField"
											value="本地">
									本地
									<input type="radio" name="wageStandard.localOrField" value="外地"
											checked="checked">
									外地
									</s:else>
								</td>
								<th align="right">
									户口类型:
								</th>
								<td>
									<s:if test="wageStandard.cityOrCountryside=='城市'">
										<input type="radio" name="wageStandard.cityOrCountryside"
											checked="checked" value="城市">
									城市
									<input type="radio" name="wageStandard.cityOrCountryside"
											value="农村">
									农村
									</s:if>
									<s:else>
										<input type="radio" name="wageStandard.cityOrCountryside"
											value="城市">
									城市
									<input type="radio" name="wageStandard.cityOrCountryside"
											checked="checked" value="农村">
									农村
									</s:else>
								</td>
								<th align="right">
									保险类型:
								</th>
								<td>
									<s:if test="wageStandard.personClass=='城保'">
										<input type="radio" name="wageStandard.personClass" value="城保"
											checked="checked" />
									城保
									<input type="radio" name="wageStandard.personClass" value="社保" />
									社保
									</s:if>
									<s:else>
										<input type="radio" name="wageStandard.personClass" value="城保" />
									城保
									<input type="radio" name="wageStandard.personClass" value="社保"
											checked="checked" />
									社保
									</s:else>
								</td>
							</tr>
							<tr>
								<th align="right">
									修改说明:
								</th>
								<td colspan="5">
									<input id="isOnWork" name="wageStandard.isOnWork"
										style="width: 500px;" value="${wageStandard.isOnWork}" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="6" style="padding: 10 0 10 0">
									<input type="submit" value="修改"
										style="width: 100px; height: 50px">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 100px; height: 50px">
								</td>
							</tr>
						</table>
					</form>
				</div>
				<br>
				<div id="detailsWageStrandar" style="display: none;">
					<table width="100%" class="table">
						<tr>
							<th align="center" colspan="6">
								<font color="red">${wageStandard.userName}</font> 的工资模板明细
							</th>
						</tr>
						<tr>
							<th align="right">
								工号:
							</th>
							<td>
								${wageStandard.code}
							</td>
							<th align="right">
								卡号:
							</th>
							<td>
								${wageStandard.cardId}
							</td>
							<th align="right">
								姓名:
							</th>
							<td>
								${wageStandard.userName}
							</td>
						</tr>
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								${wageStandard.dept}
							</td>
							<th align="right">
								岗位工资:
							</th>
							<td>
								${wageStandard.gangweigongzi}
							</td>
							<th align="right">
								保密津贴:
							</th>
							<td>
								${wageStandard.baomijintie}
							</td>
						</tr>
						<tr>
							<th align="right">
								补贴:
							</th>
							<td>
								${wageStandard.dianhuabutie}
							</td>
							<th align="right">
								技能工资:
							</th>
							<td>
								${wageStandard.jinenggongzi}
							</td>
							<th align="right">
								特殊补贴:
							</th>
							<td>
								${wageStandard.gonglinggongzi}
							</td>

						</tr>
						<tr>
							<th align="right">
								绩效考核工资:
							</th>
							<td>
								${wageStandard.jixiaokaohegongzi}
							</td>
							<th align="right">
								住房费:
							</th>
							<td>
								${wageStandard.fangzufei}
							</td>
							<th align="right">
								是否补差:
							</th>
							<td>
								<s:if test="wageStandard.bucha=='yes'">
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								公积金基数:
							</th>
							<td>
								${wageStandard.gjjBase}
							</td>
							<th align="right">
								公积金:
							</th>
							<td>
								${wageStandard.gongjijin}
							</td>
							<th align="right">
								社保基数:
							</th>
							<td>
								${wageStandard.ssBase}
							</td>
						</tr>
						<tr>
							<th align="right">
								养老保险:
							</th>
							<td>
								${wageStandard.tongchoujin}
							</td>
							<th align="right">
								医疗保险:
							</th>
							<td>
								${wageStandard.yiliaobaoxian}
							</td>
							<th align="right">
								失业保险:
							</th>
							<td>
								${wageStandard.shiyebaoxian}
							</td>
						</tr>
						<tr>
							<th align="right">
								本地或外地:
							</th>
							<td>
								${wageStandard.localOrField}
							</td>
							<th align="right">
								户口类型:
							</th>
							<td>
								${wageStandard.cityOrCountryside}
							</td>
							<th align="right">
								保险类型:
							</th>
							<td>
								${wageStandard.personClass}
							</td>
						</tr>
						<tr>
							<th align="right">
								添加时间:
							</th>
							<td colspan="5">
								${wageStandard.inputDate}
							</td>
						</tr>
						<tr>
							<th align="right">
								修改说明:
							</th>
							<td colspan="5">
								${wageStandard.isOnWork}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
