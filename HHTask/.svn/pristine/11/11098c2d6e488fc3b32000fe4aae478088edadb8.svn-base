<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.*,java.text.SimpleDateFormat"%>
<%@ page import="com.task.Dao.*"%>
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

		<script type="text/javascript">
// 预览
function viewResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var ulMessageDIV = document.getElementById("operatingDiv");
			ulMessageDIV.innerHTML = ulMessage;
			chageDiv("block");
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

// 生成模板修改样式
function generateTemplate() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var updateTemplateDiv = document
					.getElementById("updateTemplateDiv");
			updateTemplateDiv.innerHTML = message;
			updateTemplateDiv.style.display = "block";
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//隐藏或者打开Div
function showOrCloseDiv(obj, newValue, divId) {
	var div = document.getElementById(divId);
	if (div.style.display == "none") {
		div.style.display = "block";
	} else {
		div.style.display = "none"
	}
}

//流程DIV控制
function chageType() {
	var templateStatus = "${template.status}";
	var status = "${status}";
	/*模板操作DIV*/
	var template = document.getElementById("template");//添加模板导航
	var templateDiv = document.getElementById("templateDiv");//添加模板DIV
	var templateDetail = document.getElementById("templateDetail");//添加明细导航
	var templateDetailDiv = document.getElementById("templateDetailDiv");//添加明细DIV
	var examine = document.getElementById("examine");//审核导航
	var examineDiv = document.getElementById("examineDiv");//审核DIV
	var bingBing = document.getElementById("bingBing");//绑定导航
	var bingBingDiv = document.getElementById("bingBingDiv");//绑定DIV

	var newDiv = document.getElementById("newDiv");//添加、明细、审核、绑定DIV
	var oldDiv = document.getElementById("oldDiv");//历史模板DIV
	var scoreDiv = document.getElementById("scoreDiv");//评分DIV

	if (status == "") {
		newDiv.style.display = "block";
	} else if (status == "equalAudit") {//审核中模板
		newDiv.style.display = "block";
		examine.className = "style1";
		examineDiv.style.display = "block";
		return;
	} else if (status == "history" || status == "userHistory"
			|| status == "groupTemplate") {//历史模板
		oldDiv.style.display = "block";
		return;
	}

	if (templateStatus == "") {//初始页面(添加模板)
		template.className = "style1";
		templateDiv.style.display = "block";
	} else if (templateStatus == "添加明细" || templateStatus == "打回") {//添加模板明细
		templateDetail.className = "style1";
		templateDetailDiv.style.display = "block";
	} else if (templateStatus == "审核") {//审核中模板
		examine.className = "style1";
		examineDiv.style.display = "block";
	} else if (templateStatus == "同意" || templateStatus == "打分"
			|| templateStatus == "结束") {//绑定人
		bingBing.className = "style1";
		bingBingDiv.style.display = "block";
	}

}

//表单检查
function checkAddForm() {
	var project = document.getElementById("project");
	var customScore = document.getElementById("customScore");
	if (project.value == "") {
		alert("内容不能为空!");
		project.focus();
		return false;
	} else if (customScore.value == "") {
		alert("分数不能为空!");
		customScore.focus();
		return false;
	} else if (customScore.value == "${scoreMessage}") {
		alert("分数不能为汉字!");
		customScore.focus();
		return false;
	} else {
		return true;
	}
}

//全选
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox);
				}
			}
		}
	}
}

//单选
var num = "${count}";
function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
	} else {
		checkAll.checked = false;
		checkAll2.checked = false;
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
	document.getElementById("propleText").value = num;
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="chageType()">
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%; margin-top: ">
					<tr>
						<td>
							您正在预览模板:
							<font color="red">${template.name}</font>
							<a href="javascript:pagePrint('operatingDiv')">打印</a>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; margin: 0px;; padding: 0px;">

				</div>
			</div>
		</div>
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a
							href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
							style="color: #ffffff">添加模板</a>
						<a id="chageTemplate"
							href="TemplateAction!findAllTemplate.action?status=history"
							style="color: #ffffff">历史模板</a>
					</div>
				</div>
				<!-- 添加 明细 审核 绑定DIV -->
				<div id="newDiv" style="display: none">
					<!-- 模板导航栏 -->
					<div align="center" style="width: 100%;">
						<div id="template" class="style2"
							style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding-top: 7px;">
							<font size="4"> 定制考核模板 </font>
						</div>
						<div id="templateDetail" class="style2"
							style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding-top: 7px;">
							<font size="4"> 考核模板明细 </font>
						</div>
						<div id="examine" class="style2"
							style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding-top: 7px;">
							<font size="4"> 审核中模板 </font>
						</div>
						<div id="bingBing" class="style2"
							style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding-top: 7px;">
							<font size="4"> 选择考核人 </font>
						</div>
					</div>
					<!-- 添加模板DIV -->
					<div id="templateDiv" style="clear: both; display: none"
						align="center">
						<form action="TemplateAction!addTemplate.action" method="post">
							<input type="hidden" name="template.assObject" value="员工级">
							<input type="hidden" name="template.status" value="添加明细">
							<table class="table">
								<tr>
									<td colspan="2" align="center">
										添加考核模板 (
										<a
											href="TemplateAction!findAllTemplate.action?status=userHistory">使用历史模板</a>)
									</td>
								</tr>
								<tr>
									<td align="right">
										模板名称:
									</td>
									<td>
										<input type="text" name="template.name" id="templateName">
									</td>
								</tr>
								<tr style="display: none;" id="tr_asstMouth">
									<td align="right">
										考核月份:
									</td>
									<td id="td_asstMouth">
										<input name="template.asstMouth" class="Wdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"
											value="<%=new SimpleDateFormat("yyyy-MM月").format(new Date())%>">
									</td>
								</tr>
								<%
									Users user = (Users) request.getSession().getAttribute(
											TotalDao.users);
									if (user.getDept().indexOf("and") > 0) {
										String[] dept = user.getDept().split("and");
										out
												.print("<tr><td align='right'>考核部门:</td><td><select name='template.dept' style='width:150px'><option></option>");
										for (int i = 0; i < dept.length; i++) {
											out.print("<option>" + dept[i] + "</option>");
										}
										out.print("</select></td></tr>");
									} else {
										out.print("<input type='hidden' name='template.dept' value='"
												+ user.getDept() + "'");
									}
								%>
								<tr>
									<td align="right">
										备注：
									</td>
									<td>
										<textarea rows="5" cols="40" name="template.remarks"></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" id="td_dingzhi">
										<input type="submit" value="定制"
											style="width: 80px; height: 50px">
										<input type="reset" value="重置"
											style="width: 80px; height: 50px">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<!-- 添加明细模板DIV -->
					<div id="templateDetailDiv" style="display: none;">
						<form action="TemplateDetailsAction!addTemplateDetails.action"
							onsubmit="return checkAddForm()" method="post">
							<input type="hidden" name="id" value="${template.id}">
							<input type="hidden" name="templateDetails.layer"
								value="${pageLayer}">
							<input type="hidden" name="templateDetails.onLayer"
								value="${pageOnLayer}">
							<table class="table" id="templateTable">
								<tr>
									<td colspan="4" align="center">
										<h3>
											<a
												href="TemplateAction!findTemplateById.action?id=${template.id}">${template.name}</a>
										</h3>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										考核对象:(${template.assObject})
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										考核部门:(${template.dept})
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										考核月份:(${template.asstMouth})
										<s:if test="pageOnLayer != 'root'">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											(<a
												href="TemplateAction!findTemplateById.action?id=${template.id}">返回模板</a>)
									</s:if>
										<s:else>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											(<a onclick="return window.confirm('将提交审核该模板,确定提交?')"
												href="TemplateAction!updateTemplate.action?id=${template.id}&status=1">提交审核</a>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (
										<a id='updateTemplate'
												href="javascript:sendRequest('TemplateAction!generateTemplate.action?id=${template.id}',viewResponse)">修改模板</a>)
									</s:else>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (
										<a id="viewLabel"
											href="javascript:sendRequest('TemplateAction!PreviewTemplate.action?id=${template.id}',viewResponse)">预览</a>)
									</td>
								</tr>
								<tr>
									<td align="right">
										内容:
									</td>
									<td>
										<textarea rows="5" cols="60" name="templateDetails.project"
											id="project" style="padding: 0px; margin: 0px"></textarea>
									</td>
									<s:if
										test="templateDetails==null||templateDetails.isSroce != 'yes'">
										<input type="hidden" name="templateDetails.customScore"
											class="search_shuru1" alt="${scoreMessage}"
											title="${scoreMessage}" value="0" id="customScore" />
									</s:if>
									<s:else>
										<td align="right">
											配分:
										</td>
										<td>
											<input type="text" name="templateDetails.customScore"
												style="border: 1px solid #000000; height: 30px;"
												alt="${scoreMessage}" title="${scoreMessage}" value="0"
												id="customScore"
												onblur="javascript:if(!/^-?[1-9]\d*$/.test(value))value=value.replace(/\D/g, '')" />
										</td>
									</s:else>
								</tr>
								<s:if test="pageOnLayer ==  'root'">
									<tr align="right">
										<td>
											权重:
										</td>
										<td align="left">
											<input type="text" onchange="numyanzhen(this)"
												name="templateDetails.weight" id="weight">
										</td>
									</tr>
								</s:if>
								<s:if test="pageOnLayer!='root'">
									<tr>
										<td align="right">
											打分项
										</td>
										<td>
											<s:if test="templateDetails.isSroce == 'yes'">
												<input type="radio" name="templateDetails.isSroce"
													value="no" checked="checked">
										否
										
										</s:if>
											<s:else>
												<input type="radio" name="templateDetails.isSroce"
													value="yes">
										是
										<input type="radio" name="templateDetails.isSroce" value="no"
													checked="checked">
										否
										</s:else>
										</td>
									</tr>
								</s:if>
								<tr>
									<th align="right">
										是否机打:
									</th>
									<td>
										<select name="templateDetails.isJida" id="isJidaSelect"
											onchange="showJidaClass('')">
											<option value="no">
												否
											</option>
											<option value="yes">
												是
											</option>
										</select>
									</td>
									<th align="right" id="jidaClasssTh" style="display: none;">
										机打类别:
									</th>
									<td id="jidaClasssTd" style="display: none;">
										<select name="templateDetails.jidaClasss"
											id="jidaClasssselect">
											<option>
											</option>
											<option value="order">
												订单完成率
											</option>
											<option value="project">
												项目核算完成率
											</option>
											<option value="cgdhjsl">
												采购到货及时率
											</option>
											<option value="scwcjsl">
												生产完成及时率
											</option>
											<option value="6s">
												6S每日检查完成率
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<s:if test="scoreMessage=='最大配分数:0.0'">
											<input type="submit" value="添加" disabled="disabled"
												style="width: 80px; height: 50px;">
											<input type="button" value="重置" disabled="disabled"
												style="width: 80px; height: 50px;">
										</s:if>
										<s:else>
											<input type="submit" value="添加"
												style="width: 80px; height: 50px;">
											<input type="button" value="重置"
												style="width: 80px; height: 50px;">
										</s:else>
									</td>
								</tr>
							</table>
						</form>
						<table class="table">
							<s:if test="pageOnLayer!='root'">
								<tr>
									<td colspan="3" align="center">
										您正在定制
										<font color='red' size="4">${templateDetails.project}</font>
										的下一层
									</td>
								</tr>
							</s:if>
							<tr>
								<td align="center">
									<s:if test="pageLayer==1">项目</s:if>
									<s:else>内容</s:else>
								</td>
								<td align="center">
									分数
								</td>
								<td align="center">
									操作
								</td>
							</tr>
							<s:iterator id="templateDetailsSet"
								value="template.templateDetails" status="stauts">
								<s:if
									test="#templateDetailsSet.layer==pageLayer&#templateDetailsSet.onLayer==pageOnLayer">
									<s:if test="#stauts.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td width="30%" align="center">
										${templateDetailsSet.project}
									</td>
									<td width="30%" align="center">
										${templateDetailsSet.customScore}
									</td>
									<td width="40%" align="center">
										<s:if test="#templateDetailsSet.onLayer!='root'">
											<a
												href="TemplateDetailsAction!findTemDetailsById.action?id=${templateDetailsSet.onLayer}&pageLayer=${pageLayer-1}">上一层</a>
										</s:if>
										<s:if
											test="templateDetails==null||templateDetails.isSroce != 'yes'">
											<a
												href="TemplateDetailsAction!findTemDetailsById.action?id=${templateDetailsSet.id}&pageLayer=${pageLayer+1}">下一层</a>
										</s:if>
										<a
											href="javascript:sendRequest('TemplateDetailsAction!generateTds.action?id=${templateDetailsSet.id}',viewResponse)">修改</a>
										<a
											href="TemplateDetailsAction!delTemplateDetails.action?id=${templateDetailsSet.id}">删除</a>

									</td>
									</tr>
								</s:if>
							</s:iterator>
						</table>


						<div id="updateTemplateDiv" style="display: none">
						</div>

					</div>
					<!-- 审核中模板DIV -->
					<div id="examineDiv" style="display: none; width: 100%">
						<br />
						<table  class="table">
							<tr>
								<th colspan="5">
									模板
									<font color="red">${template.name}</font> 正在审核中(
									<a href="TemplateAction!sendMailAgin.action">发送短信审核提醒</a>)
									<br />
								</th>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<td align="center">
									模板名称
								</td>
								<td align="center">
									考核部门
								</td>
								<td align="center">
									考核对象
								</td>
								<td align="center">
									考核月份
								</td>
								<td align="center">
									操作
								</td>
							</tr>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
								<td align="center">
									${template.name}
								</td>
								<td align="center">
									${template.dept}
								</td>
								<td align="center">
									${template.assObject}
								</td>
								<td align="center">
									${template.asstMouth}
								</td>
								<td align="center">
									<a
										href="javascript:sendRequest('TemplateAction!PreviewTemplate.action?id=${template.id}',viewResponse)">预览</a>
								</td>
							</tr>
						</table>
						<font color="red">${errorMessage}</font>
					</div>
					<!-- 绑定审核人模板DIV -->
					<div id="usersGroupDiv" style="display: none">
						<table class="table" style="width: 30%">
							<tr>
								<th colspan="6">
									<font size="3" color="red">请选择您要使用的分组</font>
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
										href="AssessPersonnelAction!findAssessPersonnelForbb.action?id=${template.id}&userGroupId=${upList.id}">选择</a>
								</td>
								</tr>
							</s:iterator>
						</table>

					</div>
					<div id="bingBingDiv" style="display: none">
						<form action="AssessPersonnelAction!bingdingUsers.action"
							style="margin: 0px">
							<input type="hidden" name="id" value="${id}">

							<table style="width: 980px; border-collapse: collapse;"
								border="0" align="center">
								<tr>
									<td align="right" colspan="6">
										<font color="red">共选择 <label id="peopleLabel">
												${count}
											</label> <input type="hidden" id="propleText" name="peopleNum"
												style="width: 20px;" readonly="readonly"> 人</font>
										<input type="submit" value="绑定"
											style="width: 60px; height: 40px;" align="top">
										<br>
										<br>
									</td>
								</tr>
								<tr>
									<th colspan="6" align="center">
										${template.name}
									</th>
								</tr>
								<tr align="center" bgcolor="#c0dcf2"
									style="height: 40px; font-weight: bold;">
									<td>
										工号
									</td>
									<td>
										卡号
									</td>
									<td>
										姓名
									</td>
									<td>
										部门
									</td>
									<td>
										<input type="checkbox" id="checkAll"
											onclick="chageAllCheck(this)">
										全选
									</td>
								</tr>
								<s:iterator id="pageAp" value="template.assessPersonnel"
									status="ststusfunction">
									<s:if test="#ststusfunction.first">
										<tr bgcolor="green">
											<td colspan="6" style="font-family: 微软雅黑; font-weight: bold;"
												align="center">
												<font color="#ffffff"> 已绑定用户</font>
											</td>
										</tr>
									</s:if>

									<s:if test="#ststusfunction.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										${pageAp.code}
									</td>
									<td>
										${pageAp.cardId}
									</td>
									<td>
										${pageAp.userName}
									</td>
									<td>
										${pageAp.dept}
									</td>
									<td>
										<input type="checkbox"
											id="user<s:property value="#ststusfunction.index"/>"
											name="usersId" value="${pageAp.id}" onclick="chageNum(this)"
											checked="checked">
									</td>
									</tr>
									<s:if test="#ststusfunction.last">
										<tr bgcolor="green">
											<td colspan="6" style="font-family: 微软雅黑; font-weight: bold;"
												align="center">
												<font color="#ffffff"> 可绑定人员</font>
											</td>
										</tr>
									</s:if>
								</s:iterator>
								<s:if
									test="assessPersonnelList.size<=0&&template.assessPersonnel!=null">
									<tr>
										<td colspan="6" align="center"
											style="font-weight: bold; padding-right: 70px">
											您已经绑定过成员!
											<td>
									</tr>
								</s:if>
								<s:elseif test="assessPersonnelList.size<=0">
									<tr>
										<td colspan="6" align="center"
											style="font-weight: bold; padding-right: 70px">
											不存在成员信息,请到成员管理中先添加成员!
											<td>
									</tr>
								</s:elseif>
								<s:iterator id="pageAp" value="assessPersonnelList"
									status="ststusfunction">
									<s:if test="#ststusfunction.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										${pageAp.code}
									</td>
									<td>
										${pageAp.cardId}
									</td>
									<td>
										${pageAp.userName}
									</td>
									<td>
										${pageAp.dept}
									</td>
									<td>
										<input type="checkbox"
											id="user<s:property value="#ststusfunction.index"/>"
											name="usersId" value="${pageAp.id}" onclick="chageNum(this)">
									</td>
									</tr>
								</s:iterator>

								<tr>
									<td colspan="6" align="center">
										<font color="red">${errorMessage}</font>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="right"
										style="font-weight: bold; padding-right: 70px">
										<input type="checkbox" id="checkAll2"
											onclick="chageAllCheck(this)">
										全选
									</td>
								</tr>
								<tr>
									<td align="right" colspan="6">
										<br>
										<font color="red">共选择 <label id="peopleLabel2">
												${count}
											</label>人</font>
										<input type="submit" value="绑定"
											style="width: 60px; height: 40px;" align="top">
										<br>
										<br>
										<br>
										<br>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<!-- 历史模板DIV -->
				<div id="oldDiv" style="display: none">
					<form action="TemplateAction!findTDsByCondition.action"
						style="padding: 0px; margin: 0px;" method="post">
						<input type="hidden" name="status" value="history">
						<input name="template.assObject" value="员工级" type="hidden" />
						<table class="table">
							<tr>
								<th colspan="7" align="center">
									历史模板(
									<a href="TemplateAction!sendMailAgin.action">发送短信审核提醒</a>)
								</th>
							</tr>
							<tr>
								<td align="right">
									模板名称:
								</td>
								<td>
									<input name="template.name" />
								</td>
								<td align="right">
									考核月份:
								</td>
								<td>
									<input class="Wdate" type="text" name="template.asstMouth"
										onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<td align='right'>
									考核部门:
								</td>
								<td>
									<%
										if (user.getDept().indexOf("and") > 0) {
											String[] dept = user.getDept().split("and");
											out
													.print("<select name='template.dept' style='width:150px'><option></option>");
											for (int i = 0; i < dept.length; i++) {
												out.print("<option>" + dept[i] + "</option>");
											}
											out.print("</select>");
										} else {
											out
													.print("<input readonly='readonly' name='template.dept' value='"
															+ user.getDept() + "'");
										}
									%>
								</td>
								<td align="right">
									状态:
								</td>
								<td>
									<select name="template.status">
										<option></option>
										<option>
											审核
										</option>
										<option>
											添加明细
										</option>
										<option>
											通过
										</option>
										<option>
											打回
										</option>
										<option>
											打分
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="查询"
										style="width: 80px; height: 60px">
									<input type="reset" value="重置"
										style="width: 80px; height: 60px">
								</td>
							</tr>
						</table>
					</form>
					<s:if test="%{#pageStatus=='userHistory'}">
						<a
							href="TemplateAction!findAllTemplate.action?status=groupTemplate">查看成员所有历史模版</a>
					</s:if>
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<td align="center">
								模板名称
							</td>
							<td align="center">
								考核部门
							</td>
							<td align="center">
								考核对象
							</td>
							<td align="center">
								考核月份
							</td>
							<td align="center">
								状态
							</td>
							<td align="center">
								操作
							</td>
						</tr>
						<s:iterator value="templateList" id="pageTemplate"
							status="ststusfunction">
							<s:if test="#ststusfunction.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#ststusfunction.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#ststusfunction.index+1" />
								</font>
							</td>
							<td align="center">
								${pageTemplate.name}
							</td>
							<td align="center">
								${pageTemplate.dept}
							</td>
							<td align="center">
								${pageTemplate.assObject}
							</td>
							<td align="center">
								${pageTemplate.asstMouth}
							</td>
							<td align="center">
								${pageTemplate.status}
							</td>
							<td>
								<a
									href="javascript:sendRequest('TemplateAction!PreviewTemplate.action?id=${pageTemplate.id}',viewResponse)">预览</a>

								<s:if
									test="%{#pageStatus=='userHistory'||#pageStatus=='groupTemplate'}">
									/<a onclick="return window.confirm('确定要使用该历史模板吗?')"
										href="TemplateAction!useHistry.action?id=${pageTemplate.id}&status=direct">直接使用</a>/<a
										onclick="return window.confirm('确定要使用该历史模板吗?')"
										href="TemplateAction!useHistry.action?id=${pageTemplate.id}&status=update">修改使用</a>
								</s:if>
								<s:else>
									/<a
										href="TemplateAction!findTemplateById.action?id=${pageTemplate.id}">查看</a>
									<s:if test='#pageTemplate.status!="结束"'>
								/<a id='updateTemplate'
											href="javascript:sendRequest('TemplateAction!generateTemplate.action?id=${pageTemplate.id}',viewResponse)">修改</a>/
								<a onclick="return window.confirm('该模板将被删除,确定要删除吗?')"
											href="TemplateAction!delTemplate.action?id=${pageTemplate.id}&status=history">删除</a>
									</s:if>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="7" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="7" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
					</table>
				</div>


				<!-- 消息提醒 -->
				<div id="message" align="center">
					<font color="red">${successMessage}</font>
					<%
						session.removeAttribute("successMessage");
						session.removeAttribute("errorMessage");
					%>
				</div>
			</div>
			<BR>
			<!-- 预览模板DIV -->
			<div id="ulMessageDIV"
				style="border: solid #0170b8 1px; width: 100%; display: none">
			</div>
			<%@include file="/util/foot.jsp"%>
			<%
				request.getSession().removeAttribute("successMessage");
			%>
		</center>
		<script type="text/javascript">
$(function() {
	var groupList = "<s:property value='list.size()' />";
	if (groupList > 1) {
		$("#bingBingDiv").hide();
		$("#usersGroupDiv").show("");
	}
});

function numyanzhen(obj) {
	var ty1 = /^0(\.\d+)?$/;
	var bChk = ty1.test(obj.value);
	if (!bChk) {
		obj.value = "";
		alert("请输入小于1的小数")
		obj.focus();
		obj.select();
	}
}
var status = '${param.status}';
$(function() {
	if (status != 'zhuangzheng') {
		$("#tr_asstMouth").show();
	} else {
		$("#templateName").val("员工转正考核");
		$("#td_asstMouth").remove();
		$("#td_dingzhi").append(
				'<input type="hidden" value="转正" name="template.type"/>');
	}
})

function showJidaClass(id) {
	if ($("#isJidaSelect" + id).val() == "yes") {
		$("#jidaClasssTh" + id).show();
		$("#jidaClasssTd" + id).show();
	} else if ($("#isJidaSelect" + id).val() == "no") {
		$("#jidaClasssTh" + id).hide();
		$("#jidaClasssTd" + id).hide();
		$("#jidaClasssselect" + id).val("");
	}
}
</script>
	</body>
</html>
