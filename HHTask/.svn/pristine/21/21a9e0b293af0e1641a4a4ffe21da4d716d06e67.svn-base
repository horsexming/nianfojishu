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
	</head>
	<body bgcolor="#ffffff" onload="chagePrint()">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
					</div>
				</div>
				
				<!-- 入职流程导航栏 -->
				<div align="center" style="border: blue solid 1px; width: 100%;">
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 添加员工 </font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 上传简历 </font>
					</div>
					<div class="style2"
						style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 签订合同 </font>
					</div>
					<div class="style1"
						style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
						<font size="4"> 薪资信息 </font>
					</div>
				</div>
				<div id="add" align="center" style="display: none">
					<font color="red">${successMessage}</font>
					<div style="width: 75%;">
						<form action="WageStandardAction!pladdWageStandard.action" method="post"
						enctype="multipart/form-data" >
						选择导入文件:<input type="file" name="addFile" >
						<a href="<%=basePath %>/upload/file/download/WageStandard.xls">导入模版下载</a>
						<a href="FileViewAction.action?FilePath=/upload/file/download/WageStandard.xls&Refresh=true">/预览</a>
							<input type="submit" value="批量导入" id="sub" >
						</form>
					</div>
					<form action="WageStandardAction!addWageStandard.action"
						onsubmit="return checkForm()" method="post">
						<div id="errorMessageDiv" style="color: red">
						</div>
						<table width="100%" class="table">
							<tr>
								<th align="center" colspan="6">
									添加试用期标准工资
									<input type="hidden" name="wageStandard.isOnWork" value="试用" />
									<input type="hidden" name="wageStandard.standardStatus"
										value="协议" />
								</th>
							</tr>
							<tr>
								<th align="right">
									工号:
								</th>
								<td>
									<input id="code" onblur="send(this)" name="wageStandard.code"
										value="${user.code}" />
								</td>
								<th align="right">
									卡号:
								</th>
								<td>
									<input id="cardId" name="wageStandard.cardId" title="只读"
										readonly="readonly" value="${user.cardId}" />
								</td>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName" name="wageStandard.userName" title="只读"
										readonly="readonly" value="${user.name}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									部门:
								</th>
								<td>
									<input id="dept" name="wageStandard.dept" readonly="readonly"
										title="只读" value="${user.dept}" />
								</td>
								<th align="right">
									岗位工资:
								</th>
								<td>
									<input id="gangweigongzi" name="wageStandard.gangweigongzi" />
								</td>
								<th align="right">
									保密津贴:
								</th>
								<td>
									<input id="baomijintie" name="wageStandard.baomijintie" />
								</td>
							</tr>
							<tr>
								<th align="right">
									补贴:
								</th>
								<td>
									<input id="dhbt" name="wageStandard.dianhuabutie" />
								</td>
								<th align="right">
									技能工资:
								</th>
								<td>
									<input id="jinenggongzi" name="wageStandard.jinenggongzi" />
								</td>
								<th align="right">
									特殊补贴:
								</th>
								<td>
									<input id="gonglinggongzi" name="wageStandard.gonglinggongzi"
										title="特殊补贴项不参与补贴计算" />
								</td>
							</tr>
							<tr>
								<th align="right">
									绩效考核工资:
								</th>
								<td>
									<input id="jixiaokaohegongzi"
										name="wageStandard.jixiaokaohegongzi" />
								</td>
								<th align="right">
									公积金基数:
								</th>
								<td>
									<input title="为0时表示不交公积金" id="gongjijin" type="text"
										name="wageStandard.gjjBase" value="0">
								</td>
								<th align="right">
									社保基数:
								</th>
								<td>
									<input id="ssBase" type="text" name="wageStandard.ssBase">
								</td>
							</tr>
							<tr>
								<th align="right">
									住房费:
								</th>
								<td>
									<input id="jixiaokaohegongzi" name="wageStandard.fangzufei"
										value="0" />
								</td>
								<th align="right">
									是否补差:
								</th>
								<td>
									<input type="radio" name="wageStandard.bucha" checked="checked"
										value="yes" />
									是
									<input type="radio" name="wageStandard.bucha" value="no" />
									否
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
									养老保险:
								</th>
								<td>
									<input type="radio" name="wageStandard.tongchoujin" value="1"
										checked="checked">
									是
									<input type="radio" name="wageStandard.tongchoujin" value="0">
									否
								</td>
								<th align="right">
									医疗保险:
								</th>
								<td>
									<input type="radio" name="wageStandard.yiliaobaoxian"
										checked="checked" value="1">
									是
									<input type="radio" name="wageStandard.yiliaobaoxian" value="0">
									否
								</td>
								<th align="right">
									失业保险:
								</th>
								<td>
									<input type="radio" name="wageStandard.shiyebaoxian" value="1">
									是
									<input type="radio" name="wageStandard.shiyebaoxian"
										checked="checked" value="0">
									否
								</td>

							</tr>
							<tr>
								<th align="right">
									本地或外地:
								</th>
								<td>
									<input type="radio" name="wageStandard.localOrField"
										checked="checked" value="本地">
									本地
									<input type="radio" name="wageStandard.localOrField" value="外地">
									外地
								</td>
								<th align="right">
									户口类型:
								</th>
								<td>
									<input type="radio" name="wageStandard.cityOrCountryside"
										checked="checked" value="城市" />
									城市
									<input type="radio" name="wageStandard.cityOrCountryside"
										value="农村" />
									农村
								</td>
								<th align="right">
									保险类型:
								</th>
								<td>
									<input type="radio" name="wageStandard.personClass" value="城保"
										checked="checked" />
									城保
									<input type="radio" name="wageStandard.personClass" value="社保" />
									社保
								</td>
							</tr>
						</table>

						<table width="100%" class="table">
							<tr>
								<th align="center" colspan="6">
									添加转正后标准工资
									<input type="hidden" name="onWorkWs.isOnWork" value="正式" />
									<input type="hidden" name="onWorkWs.standardStatus" value="协议" />
								</th>
							</tr>
							<input id="code2" onblur="send(this)" name="onWorkWs.code"
								type="hidden" value="${user.code}" />
							<input id="cardId2" name="onWorkWs.cardId" title="只读"
								type="hidden" readonly="readonly" value="${user.cardId}" />
							<input id="dept2" name="onWorkWs.dept" readonly="readonly"
								type="hidden" title="只读" value="${user.dept}" />
							<tr>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName2" name="onWorkWs.userName" title="只读"
										readonly="readonly" value="${user.name}" />
								</td>
								<th align="right">
									岗位工资:
								</th>
								<td>
									<input id="gangweigongzi2" name="onWorkWs.gangweigongzi" />
								</td>
								<th align="right">
									保密津贴:
								</th>
								<td>
									<input id="baomijintie2" name="onWorkWs.baomijintie" />
								</td>
							</tr>
							<tr>
								<th align="right">
									补贴:
								</th>
								<td>
									<input id="dhbt2" name="onWorkWs.dianhuabutie" />
								</td>
								<th align="right">
									技能工资:
								</th>
								<td>
									<input id="jinenggongzi2" name="onWorkWs.jinenggongzi" />
								</td>
								<th align="right">
									特殊补贴:
								</th>
								<td>
									<input id="gonglinggongzi" name="onWorkWs.gonglinggongzi"
										title="特殊补贴项不参与补贴计算" />
								</td>
							</tr>
							<tr>
								<th align="right">
									绩效考核工资:
								</th>
								<td>
									<input id="jixiaokaohegongzi2"
										name="onWorkWs.jixiaokaohegongzi" />
								</td>
								<th align="right">
									公积金基数:
								</th>
								<td>
									<input title="为0时表示不交公积金" id="gongjijin" type="text"
										name="onWorkWs.gjjBase" value="0">
								</td>
								<th align="right">
									社保基数:
								</th>
								<td>
									<input id="ssBase2" type="text" name="onWorkWs.ssBase">
								</td>
							</tr>
							<tr>
								<th align="right">
									住房费:
								</th>
								<td>
									<input id="jixiaokaohegongzi" name="onWorkWs.fangzufei"
										value="0" />
								</td>
								<th align="right">
									是否补差:
								</th>
								<td>
									<input type="radio" name="onWorkWs.bucha" checked="checked"
										value="yes" />
									是
									<input type="radio" name="onWorkWs.bucha" value="no" />
									否
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
									养老保险:
								</th>
								<td>
									<input type="radio" name="onWorkWs.tongchoujin" value="1"
										checked="checked">
									是
									<input type="radio" name="onWorkWs.tongchoujin" value="0">
									否
								</td>
								<th align="right">
									医疗保险:
								</th>
								<td>
									<input type="radio" name="onWorkWs.yiliaobaoxian"
										checked="checked" value="1">
									是
									<input type="radio" name="onWorkWs.yiliaobaoxian" value="0">
									否
								</td>
								<th align="right">
									失业保险:
								</th>
								<td>
									<input type="radio" name="onWorkWs.shiyebaoxian" value="1">
									是
									<input type="radio" name="onWorkWs.shiyebaoxian"
										checked="checked" value="0">
									否
								</td>

							</tr>
							<tr>
								<th align="right">
									本地或外地:
								</th>
								<td>
									<input type="radio" name="onWorkWs.localOrField"
										checked="checked" value="本地">
									本地
									<input type="radio" name="onWorkWs.localOrField" value="外地">
									外地
								</td>
								<th align="right">
									户口类型:
								</th>
								<td>
									<input type="radio" name="onWorkWs.cityOrCountryside"
										checked="checked" value="城市" />
									城市
									<input type="radio" name="onWorkWs.cityOrCountryside"
										value="农村" />
									农村
								</td>
								<th align="right">
									保险类型:
								</th>
								<td>
									<input type="radio" name="onWorkWs.personClass" value="城保"
										checked="checked" />
									城保
									<input type="radio" name="onWorkWs.personClass" value="社保" />
									社保
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
				</div>
				<div id="allPrintDiv" style="display: none">
					<div id="printDiv">
						<div style="padding: 10 60 0 40px; width: 100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td colspan="2" align="right">
										&nbsp;
										<br />
										<br />
									</td>
									<td colspan="2" align="center">
										合同编号:${contract.contractNumber}
									</td>
								</tr>
								<tr>
									<th align="center" colspan="4"
										style="font-family: 华文行楷; font-size: 25px;">
										岗 位 薪 资 协 议 书
										<br />
										<br />
									</th>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 20px;">
										甲方:${contract.recruitmentSquare}
									</td>
									<td colspan="2" align="center">
										乙方:${contract.employedSquare}
									</td>
								</tr>
								<tr>
									<td colspan="3" style="padding-left: 20px;">
										注册地址:${contract.qiandingAddress}
									</td>
									<td>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="padding-left: 20px;">
										1、根据岗位薪资标准，甲乙双方本着实事求是和平等自愿的原则。依照《中华人民共和国劳动合同法》共同商定工资补充协议。
									</td>
								</tr>
								<tr>
									<td colspan="4" style="padding-left: 20px;" align="left">
										<br />
										2、乙方的岗位工资标准为：
									</td>
								</tr>
								<tr>
									<th colspan="2">
										试用期薪资
									</th>
									<th colspan="2">
										转正后薪资
									</th>
								</tr>
								<tr>
									<th align="right">
										岗位基本工资:
									</th>
									<td>
										<input class="horizontalLine" id="gangweigongzi"
											name="wageStandard.gangweigongzi"
											value="${wageStandard.gangweigongzi}0" style="width: 100px" />
										元
									</td>
									<th align="right">
										岗位基本工资:
									</th>
									<td>
										<input class="horizontalLine" id="gangweigongzi"
											name="onWorkWs.gangweigongzi"
											value="${onWorkWs.gangweigongzi}0" style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<th align="right">
										职务保密津贴:
									</th>
									<td>
										<input class="horizontalLine" id="baomijintie"
											name="wageStandard.baomijintie"
											value="${wageStandard.baomijintie}0" style="width: 100px" />
										元
									</td>
									<th align="right">
										职务保密津贴:
									</th>
									<td>
										<input class="horizontalLine" id="baomijintie"
											name="onWorkWs.baomijintie" value="${onWorkWs.baomijintie}0"
											style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<th align="right">
										岗位技能工资:
									</th>
									<td>
										<input class="horizontalLine" id="jinenggongzi"
											name="wageStandard.jinenggongzi"
											value="${wageStandard.jinenggongzi}0" style="width: 100px" />
										元
									</td>
									<th align="right">
										岗位技能工资:
									</th>
									<td>
										<input class="horizontalLine" id="jinenggongzi"
											name="onWorkWs.jinenggongzi"
											value="${onWorkWs.jinenggongzi}0" style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<th align="right">
										绩效考核工资:
									</th>
									<td>
										0~
										<input class="horizontalLine" id="jixiaokaohegongzi"
											name="wageStandard.jixiaokaohegongzi"
											value="${wageStandard.jixiaokaohegongzi}0"
											style="width: 100px" />
										元
									</td>
									<th align="right">
										绩效考核工资:
									</th>
									<td>
										0~
										<input class="horizontalLine" id="jixiaokaohegongzi"
											name="onWorkWs.jixiaokaohegongzi"
											value="${onWorkWs.jixiaokaohegongzi}0" style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<td colspan="4" style="padding-left: 20px;">
										<br />
										3、双方约定，假期工资及法定节假日加班工资基数均以岗位工资为基数计算。
									</td>
								</tr>
								<tr>
									<td colspan="4" style="padding-left: 20px;">
										<br />
										4、双方同时约定：绩效工资将根据乙方每月工作表现及完成工作情况并根据甲方效益情况按绩效考核成绩计算发放。
										本协议及将作为甲乙双方于${contract.startDate}签定的合同编号为${contract.contractNumber}的《劳动合同书》之附件与《劳动合同书》具同等法律效力。
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 20px;">
										甲方:${contract.recruitmentSquare}
									</td>
									<td colspan="2">
										乙方:(签字)__________________________
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 20px;">
										<br />
										法定代表人:
									</td>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="2" style="padding-left: 20px;">
										<br />
										日期:__________________________
									</td>
									<td colspan="2">
										日期:_________________________________
									</td>
								</tr>
							</table>
						</div>
					</div>
					<input type="button" value="打印" onclick="pagePrint('printDiv')"
						style="width: 80px; height: 50px;">
					<input type="button" value="预览"
						onclick="pagePrint('printDiv','view')"
						style="width: 80px; height: 50px;">
				</div>

				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>

		<script type="text/javascript">
function send(obj) {
	sendRequest("UsersAction!findCardIdBCode.action?user.code=" + obj.value,
			messageResponse);
}

// 工号查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var code = document.getElementById("code");
			var cardId = document.getElementById("cardId");
			var userName = document.getElementById("userName");
			var dept = document.getElementById("dept");
			var code2 = document.getElementById("code2");
			var cardId2 = document.getElementById("cardId2");
			var userName2 = document.getElementById("userName2");
			var dept2 = document.getElementById("dept2");
			var gangweigongzi = document.getElementById("gangweigongzi");
			var gangweigongzi2 = document.getElementById("gangweigongzi2");

			var errorMessageDiv = document.getElementById("errorMessageDiv");
			var value = message.split("|");
			if (value[1] == null) {
				errorMessageDiv.innerHTML = message;
				cardId.value = "";
				userName.value = "";
				dept.value = "";
				code.focus();
				code.select();
				code.title = message;
				userName2.value = "";
				code.style.border = " solid 1px red";
				return;
			} else {
				code.title = "该工号存在!";
				cardId.value = value[0];
				userName.value = value[1];
				dept.value = value[2];
				gangweigongzi.focus();

				code2.title = "该工号存在!";
				code2.value = code.value;
				cardId2.value = value[0];
				userName2.value = value[1];
				dept2.value = value[2];
				errorMessageDiv.innerHTML = "该工号可以添加薪资信息!";
				code.style.border = " solid 1px #0f0f1f";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

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

	var cardId2 = document.getElementById("cardId2");
	var userName2 = document.getElementById("userName2");
	var dept2 = document.getElementById("dept2");
	var code2 = document.getElementById("code2");
	var gangweigongzi2 = document.getElementById("gangweigongzi2");
	var baomijintie2 = document.getElementById("baomijintie2");
	var dhbt2 = document.getElementById("dhbt2");
	var jixiaokaohegongzi2 = document.getElementById("jixiaokaohegongzi2");
	var gongjijin2 = document.getElementById("gongjijin2");
	var ssBase2 = document.getElementById("ssBase2");
	var jinenggongzi2 = document.getElementById("jinenggongzi2");
	var gonglinggongzi2 = document.getElementById("gonglinggongzi2");

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
		alert("工龄工资不能为空!");
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
	} else if (code2.value == "") {
		alert("工号不能为空!");
		code2.focus();
		return false;
	} else if (cardId.value == "") {
		alert("卡号不能为空!");
		cardId.focus();
		return false;
	} else if (userName2.value == "") {
		alert("员工名称不能为空!");
		userName2.focus();
		return false;
	} else if (dept2.value == "") {
		alert("所属部门不能为空!");
		dept2.focus();
		return false;
	} else if (gangweigongzi2.value == "") {
		alert("岗位工资不能为空!");
		gangweigongzi2.focus();
		return false;
	} else if (baomijintie2.value == "") {
		alert("保密津贴不能为空!");
		baomijintie2.focus();
		return false;
	} else if (dhbt2.value == "") {
		alert("补贴不能为空!");
		dhbt2.focus();
		return false;
	} else if (jinenggongzi2.value == "") {
		alert("技能工资不能为空!");
		jinenggongzi2.focus();
		return false;
	} else if (gonglinggongzi2.value == "") {
		alert("工龄工资不能为空!");
		gonglinggongzi2.focus();
		return false;
	} else if (jixiaokaohegongzi2.value == "") {
		alert("绩效考核工资不能为空!");
		jixiaokaohegongzi2.focus();
		return false;
	} else if (gongjijin2.value == "") {
		alert("公积金基数不能为空!");
		gongjijin2.focus();
		return false;
	} else if (ssBase2.value == "") {
		alert("社保基金基数不能为空!");
		ssBase2.focus();
		return false;
	} else {
		return true;
	}
}

function chagePrint() {
	var wageStandard = "${wageStandard}";
	var onWorkWs = "${onWorkWs}";
	if (wageStandard != "" && onWorkWs != "") {
		document.getElementById("allPrintDiv").style.display = "block";
	} else {
		document.getElementById("add").style.display = "block";
	}
}
</script>

	</body>
</html>
