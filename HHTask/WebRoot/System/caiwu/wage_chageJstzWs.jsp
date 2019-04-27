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
	} else if (pageStatus == "jsgzupdate") {
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
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<!-- 查询所有工资模板 -->
				<div align="center" id="findWageStrandar" style="display: none;">
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
								公积金基数
							</th>
							<th align="center">
								社保基数
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
								状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="auditWsList" id="pageUpdateWsList"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red" height="30px;">
									<th colspan="16" style="color: #ffffff;">
										需添加工资模版人员
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
								${pageUpdateWsList.gjjBase}
							</td>
							<td>
								${pageUpdateWsList.ssBase}
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
									href="WageStandardAction!findWsById.action?id=${pageUpdateWsList.id}&pageStatus=jsgzupdate">修改</a>|
								<a target="_blank"
									href="ContractAction!findContractByCode.action?code=${pageUpdateWsList.code}&isUse=1">调整协议</a>
							</td>
							</tr>
						</s:iterator>
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
								${pageUpdateWsList.gjjBase}
							</td>
							<td>
								${pageUpdateWsList.ssBase}
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
									href="WageStandardAction!findWsById.action?id=${pageUpdateWsList.id}&pageStatus=jsgzupdate">修改</a>|
								<a target="_blank"
									href="ContractAction!findContractByCode.action?code=${pageUpdateWsList.code}&isUse=1">调整协议</a>
							</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<!-- 修改工资模板 -->
				<div id="updateWageStrandar" style="display: none">
					${successMessage}
					<form action="WageStandardAction!updateJsWs.action" method="post">
						<input type="hidden" name="wageStandard.id"
							value="${wageStandard.id}">
						<table width="100%" class="table">
							<tr>
								<th align="center" colspan="6">
									修改
									<font color="red">${wageStandard.userName}</font> 的工资模板
								</th>
							</tr>
							<tr>
								<th align="right">
									工号:
								</th>
								<td>
									<input id="code" onblur="send(this)" name="wageStandard.code"
										disabled="disabled" value="${wageStandard.code}" />
								</td>
								<th align="right">
									卡号:
								</th>
								<td>
									<input id="cardId" name="wageStandard.cardId"
										disabled="disabled" title="只读" value="${wageStandard.cardId}" />
								</td>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName" name="wageStandard.userName"
										value="${wageStandard.userName}" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th align="right">
									部门:
								</th>
								<td>
									<input id="dept" name="wageStandard.dept"
										value="${wageStandard.dept}" disabled="disabled" />
								</td>
								<th align="right">
									缴纳比例:
								</th>
								<td>
									<select name="wageStandard.insuranceGoldId">
										<s:if test="insuranceGold!=null">
											<option value="${insuranceGold.id}">
												${insuranceGold.localOrField}、${insuranceGold.cityOrCountryside}
											</option>
										</s:if>
										<s:iterator value="list" id="pageIns">
											<option value="${pageIns.id}">
												${pageIns.localOrField}、${pageIns.cityOrCountryside}、${pageIns.personClass}
											</option>
										</s:iterator>
									</select>
								</td>
								<td>
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<th align="right">
									宿舍住房费:
								</th>
								<td>
									<input id="jixiaokaohegongzi" name="wageStandard.fangzufei"
										value="${wageStandard.fangzufei}" />
								</td>
								<th align="right">
									公积金基数:
								</th>
								<td>
									<input title="为0时表示不交公积金" id="gongjijin" type="text"
										name="wageStandard.gjjBase" value="${wageStandard.gjjBase}">
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
								<th align="right">
									调整岗位或绩效:
								</th>
								<td>
									<input type="radio" name="pageStatus" checked="checked"
										value="yes" />
									是
									<input type="radio" name="pageStatus" value="no"
										disabled="disabled" />
									否
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<th align="right">
									修改说明:
								</th>
								<td colspan="5">
									<s:if test="contract.sqyy!=''">
										<input id="isOnWork" name="wageStandard.isOnWork"
											style="width: 500px;" value="${contract.sqyy}" />
									</s:if>
									<s:else>
										<input id="isOnWork" name="wageStandard.isOnWork"
											style="width: 500px;" value="${wageStandard.isOnWork}" />
									</s:else>

								</td>
							</tr>
							<tr>
								<td align="center" colspan="6" style="padding: 10 0 10 0">
									<input type="submit" value="调整"
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
