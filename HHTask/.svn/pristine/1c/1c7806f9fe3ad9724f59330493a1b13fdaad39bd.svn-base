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
		<script type="text/javascript">

// 查看工资明细
function wageDetails() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var operatingDiv = document.getElementById("operatingDiv");
			var exportWageformDiv = document.getElementById("exportWageform");
			exportWageformDiv.style.display = "none";
			operatingDiv.style.display = "block";
			operatingDiv.innerHTML = ulMessage;
			chageDiv('block');
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//更改标题
function chageTitle(userName, id) {
	var titleDiv = document.getElementById("title");
	titleDiv.innerHTML = "您正在查看<font color='red'> " + userName
			+ "</font> 的工资明细 ";
	sendRequest("WageAction!showWageDetails.action?id=" + id, wageDetails);
}
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%; background: url('images/bq_bg2.gif');">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div style="display: none; background-color: #ffffff; width: 100%;"
					id="exportWageform">
				</div>
			</div>
		</div>
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
				<div align="center" style="color: red; font-size: 16px;">
					<s:if test="wage!=null">
员工${wageStandard.userName}已经存在离职工资,<a
							href="javascript:chageTitle('${wageStandard.userName}','${wage.id}')">明细</a>
						<a
							href="WageAction!delLeaveWage.action?id=${wage.id}&pageStatus=leave"
							onclick="javascript:return window.confirm('确定要删除该离职工资吗?');">删除</a>
					</s:if>
					<s:else>
	员工${wageStandard.userName}不存在离职工资，请您添加!
					</s:else>
				</div>

				<div>
					<font color="red">${successMessage}</font>
					<s:if test="wage!=null">
						<form id="leaveWageForm" action="WageAction!updateWage.action"
							method="post">
							<input type="hidden" name="pageStatus" value="leave" />
							<input type="hidden" name="id" value="${wage.id}">
							<input type="hidden" name="wage.id" value="${wage.id}">
							<input type="hidden" name="wage.userId" value="${wage.userId}" />
							<input type="hidden" name="wage.wageClass"
								value="${wage.wageClass}">
							<table width="100%" class="table">
								<tr>
									<th align="center" colspan="6">
										<font color="red">${wage.userName}</font> 的离职工资
									</th>
								</tr>
								<tr>
									<th align="right">
										姓名:
									</th>
									<td>
										<input id="userName" name="wage.userName"
											value="${wage.userName}" readonly="readonly" title="只读" />
									</td>
									<th align="right">
										工号:
									</th>
									<td>
										<input id="code" onblur="send(this)" name="wage.code"
											readonly="readonly" value="${wage.code}" title="只读" />
									</td>
									<th align="right">
										卡号:
									</th>
									<td>
										<input id="cardId" name="wage.cardId" readonly="readonly"
											title="只读" value="${wage.cardId}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										部门:
									</th>
									<td>
										<input id="dept" name="wage.dept" value="${wage.dept}"
											readonly="readonly" title="只读" />
									</td>
									<th align="right">
										月份:
									</th>
									<td>
										<input id="tryDays" class="Wdate" type="text"
											name="wage.mouth" value="${wage.mouth}"
											onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
									</td>
									<th align="right">
										&nbsp;
									</th>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<th align="right">
										岗位工资:
									</th>
									<td>
										<input id="gangweigongzi" name="wage.gangweigongzi"
											value="${wage.gangweigongzi}" />
									</td>
									<th align="right">
										绩效工资:
									</th>
									<td>
										<input id="jixiaokaohegongzi" name="wage.jixiaokaohegongzi"
											value="${wage.jixiaokaohegongzi}" />
									</td>
									<th align="right">
										保密津贴:
									</th>
									<td>
										<input id="baomijintie" name="wage.baomijintie"
											value="${wage.baomijintie}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										奖金:
									</th>
									<td>
										<input id="jiangjin" name="wage.jiangjin"
											value="${wage.jiangjin}" />
									</td>
									<th align="right">
										补贴:
									</th>
									<td>
										<input name="wage.dianhuabutie" value="${wage.dianhuabutie}" />
									</td>
									<th align="right">
										特殊补贴:
									</th>
									<td>
										<input name="wage.gonglinggongzi"
											value="${wage.gonglinggongzi}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										午餐费:
									</th>
									<td>
										<input id="wucanfei" name="wage.wucanfei"
											value="${wage.wucanfei}" />
									</td>
									<th align="right">
										加班费:
									</th>
									<td>
										<input id="jiabanfei" name="wage.jiabanfei"
											value="${wage.jiabanfei}" />
									</td>
									<th align="right">
										病事旷等:
									</th>
									<td>
										<input id="bingshikangdeng" name="wage.bingshikangdeng"
											value="${wage.bingshikangdeng}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										补发补扣:
									</th>
									<td>
										<input id="bfgongzi" name="wage.bfgongzi"
											value="${wage.bfgongzi}" />
									</td>
									<th align="right">
										其他:
									</th>
									<td>
										<input id="other" name="wage.other" value="${wage.other}" />
									</td>
									<th align="right">
										离职补偿:
									</th>
									<td>
										<input id="leaveBuchang" name="wage.leaveBuchang"
											value="${wage.leaveBuchang}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										养老保险:
									</th>
									<td>
										<input name="wage.tongchoujin" value="${wage.tongchoujin}" />
									</td>
									<th align="right">
										医疗保险:
									</th>
									<td>
										<input name="wage.yiliaobaoxian" value="${wage.yiliaobaoxian}" />
									</td>
									<th align="right">
										失业保险:
									</th>
									<td>
										<input name="wage.shiyebaoxian" value="${wage.shiyebaoxian}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										公积金:
									</th>
									<td>
										<input name="wage.gongjijin" value="${wage.gongjijin}" />
									</td>
									<th align="right">
										离职申请单编号:
									</th>
									<td>
										<input id="leaveNumber" name="wage.leaveNumber"
											value="${wage.leaveNumber}" />
									</td>
									<th align="right">
										是否补差:
									</th>
									<td>
										<s:if test="wage.isBucha=='yes'">
											<input type="radio" name="wage.isBucha" checked="checked"
												value="yes" />
									是
									<input type="radio" name="wage.isBucha" value="no" />
									否
									</s:if>
										<s:else>
											<input type="radio" name="wage.isBucha" value="yes" />
									是
									<input type="radio" name="wage.isBucha" value="no"
												checked="checked" />
									否
									</s:else>
									</td>
								</tr>
								<tr>
									<th align="right">
										技能工资:
									</th>
									<td>
										<input name="wage.jinenggongzi" value="${wage.jinenggongzi}" />
									</td>
									<th align="right">
										房租:
									</th>
									<td>
										<input name="wage.fangzufei" value="${wage.fangzufei}" />
									</td>
									<th align="right">
										实发工资:
									</th>
									<td>
										${wage.shifagongzi} 元
									</td>
								</tr>
								<tr>
									<th align="right">
										说明:
									</th>
									<td colspan="5">
										<textarea id="directions" name="wage.directions" cols="100"
											rows="5">${wage.directions}</textarea>
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
					</s:if>
					<s:else>
						<form id="leaveWageForm" action="WageAction!addLeaveWage.action"
							method="post">
							<input type="hidden" name="pageStatus" value="leave" />
							<input type="hidden" name="wage.wageClass" value="离职工资" />
							<input type="hidden" name="wage.userId" value="${userId}" />
							<input type="hidden" id="yingfagongzi" name="wage.yingfagongzi"
								value="${wageStandard.yingfagongzi}" />
							<table width="100%" class="table">
								<tr>
									<th align="center" colspan="6">
										添加
										<font color="red">${wageStandard.userName}</font> 的离职工资
									</th>
								</tr>
								<tr>
									<th align="right">
										姓名:
									</th>
									<td>
										<input id="userName" name="wage.userName"
											value="${wageStandard.userName}" readonly="readonly"
											title="只读" />
									</td>
									<th align="right">
										工号:
									</th>
									<td>
										<input id="code" onblur="send(this)" name="wage.code"
											readonly="readonly" value="${wageStandard.code}" title="只读" />
									</td>
									<th align="right">
										卡号:
									</th>
									<td>
										<input id="cardId" name="wage.cardId" readonly="readonly"
											title="只读" value="${wageStandard.cardId}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										部门:
									</th>
									<td>
										<input id="dept" name="wage.dept" value="${wageStandard.dept}"
											readonly="readonly" title="只读" />
									</td>
									<th align="right">
										月份:
									</th>
									<td>
										<input id="tryDays" class="Wdate" type="text"
											name="wage.mouth"
											onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr>
									<th align="right">
										岗位工资:
									</th>
									<td>
										<input id="gangweigongzi" name="wage.gangweigongzi"
											value="${wageStandard.gangweigongzi}" />
									</td>
									<th align="right">
										绩效工资:
									</th>
									<td>
										<input id="jixiaokaohegongzi" name="wage.jixiaokaohegongzi"
											value="${wageStandard.jixiaokaohegongzi}" />
									</td>
									<th align="right">
										保密津贴:
									</th>
									<td>
										<input id="baomijintie" name="wage.baomijintie"
											value="${wageStandard.baomijintie}" />
									</td>

								</tr>
								<tr>
									<th align="right">
										奖金:
									</th>
									<td>
										<input id="jiangjin" name="wage.jiangjin" value="0" />
									</td>
									<th align="right">
										补贴:
									</th>
									<td>
										<input name="wage.dianhuabutie"
											value="${wageStandard.dianhuabutie}" />
									</td>
									<th align="right">
										特殊补贴:
									</th>
									<td>
										<input name="wage.gonglinggongzi"
											value="${wageStandard.gonglinggongzi}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										午餐费:
									</th>
									<td>
										<input id="wucanfei" name="wage.wucanfei" value="0" />
									</td>
									<th align="right">
										加班费:
									</th>
									<td>
										<input id="jiabanfei" name="wage.jiabanfei" value="0" />
									</td>
									<th align="right">
										病事旷等:
									</th>
									<td>
										<input id="bingshikangdeng" name="wage.bingshikangdeng"
											value="0" />
									</td>
								</tr>
								<tr>
									<th align="right">
										补发补扣:
									</th>
									<td>
										<input id="bfgongzi" name="wage.bfgongzi" value="0" />
									</td>
									<th align="right">
										其他:
									</th>
									<td>
										<input id="other" name="wage.other" value="0" />
									</td>
									<th align="right">
										离职补偿:
									</th>
									<td>
										<input id="leaveBuchang" name="wage.leaveBuchang" value="0" />
									</td>
								</tr>
								<tr>
									<th align="right">
										养老保险:
									</th>
									<td>
										<input name="wage.tongchoujin"
											value="-${wageStandard.tongchoujin}" />
									</td>
									<th align="right">
										医疗保险:
									</th>
									<td>
										<input name="wage.yiliaobaoxian"
											value="-${wageStandard.yiliaobaoxian}" />
									</td>
									<th align="right">
										失业保险:
									</th>
									<td>
										<input name="wage.shiyebaoxian"
											value="-${wageStandard.shiyebaoxian}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										公积金:
									</th>
									<td>
										<input name="wage.gongjijin"
											value="-${wageStandard.gongjijin}" />
									</td>
									<th align="right">
										离职申请单编号:
									</th>
									<td>
										<input id="leaveNumber" name="wage.leaveNumber" />
									</td>
									<th align="right">
										是否补差:
									</th>
									<td>
										<s:if test="wage.isBucha=='yes'">
											<input type="radio" name="wage.isBucha" checked="checked"
												value="yes" />
									是
									<input type="radio" name="wage.isBucha" value="no" />
									否
									</s:if>
										<s:else>
											<input type="radio" name="wage.isBucha" value="yes" />
									是
									<input type="radio" name="wage.isBucha" value="no"
												checked="checked" />
									否
									</s:else>
									</td>
								</tr>
								<tr>
									<th align="right">
										技能工资:
									</th>
									<td>
										<input name="wage.jinenggongzi"
											value="${wageStandard.jinenggongzi}" />
									</td>
									<th align="right">
										房租:
									</th>
									<td>
										<input name="wage.fangzufei" value="${wageStandard.fangzufei}" />
									</td>
								</tr>
								<tr>
									<th align="right">
										说明:
									</th>
									<td colspan="5">
										<textarea id="directions" name="wage.directions" cols="100"
											rows="5"></textarea>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="6" style="padding: 10 0 10 0">
										<input type="submit" value="添加"
											style="width: 100px; height: 50px">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置"
											style="width: 100px; height: 50px">
									</td>
								</tr>
							</table>
						</form>
					</s:else>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
$(function() {
	$("#leaveWageForm").bind("submit", function() {
		if ($("#tryDays").val() == "") {
			alert("请填写月份");
			$("#tryDays").focus();
			return false;
		} else if ($("#leaveNumber").val() == "") {
			alert("请填写离职申请编号!")
			$("#leaveNumber").focus();
			return false;
		}
		return true;
	})
});
</script>
	</body>
</html>
