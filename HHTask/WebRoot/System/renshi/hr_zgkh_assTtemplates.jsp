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
	obj.innerHTML = "asfasd";
}

//流程DIV控制
function chageType() {
	var template = "${template}";
	var templateList = "${templateList}";//所有模版(模版管理)
	var list = "${list}${assessPersonnelList}";//考核人员(绑定)
	/*模板操作DIV*/
	var templateDiv = document.getElementById("templateDiv");//添加模板DIV
	var templateDetailDiv = document.getElementById("templateDetailDiv");//添加明细DIV
	var bingBingDiv = document.getElementById("bingBingDiv");//绑定人员DIV
	var newDiv = document.getElementById("newDiv");//添加模板DIV和明细
	var oldDiv = document.getElementById("oldDiv");//历史模板DIV

	if (templateList != "") {
		oldDiv.style.display = "block";
	} else {
		newDiv.style.display = "block";
		if (list != "") {//绑定人员
			bingBingDiv.style.display = "block";
		} else if (template != "") {//添加模板明细
			templateDetailDiv.style.display = "block";
		} else {//初始页面(添加模板)
			templateDiv.style.display = "block";
		}
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
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
			</div>
		</div>
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; height: 500px; border: solid 1px #0170b8; margin-top: 10px;">
				<!-- 导航 -->
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a id="chageTemplate"
							href="TemplateAction!findAllTemplate.action?status=zhuguan"
							style="color: #ffffff">模板管理</a>
					</div>
				</div>
				<!-- 添加 明细 DIV -->
				<div id="newDiv" style="display: none">
					<!-- 添加模板DIV -->
					<div id="templateDiv" style="clear: both; display: none"
						align="center">
						<form action="TemplateAction!addTemplate.action" method="post">
							<input type="hidden" name="template.assObject" value="主管级">
							<table width="100%" border="1" class="table">
								<tr>
									<td colspan="2" align="center">
										添加考核模板(
										<a
											href="TemplateAction!findAllTemplate.action?status=zhuguan&pageStatus=addTemplate">使用历史模版</a>)
									</td>
								</tr>
								<tr>
									<td align="right">
										模板名称:
									</td>
									<td>
										<input type="text" name="template.name">
									</td>
								</tr>
								<%
									Users user = (Users) request.getSession().getAttribute(
											TotalDao.users);
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
									<td colspan="2" align="center">
										<input type="submit" value="添加"
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
							<table border="1" width="100%" id="templateTable" class="table">
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
										<s:if test="pageOnLayer != 'root'">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											(<a
												href="TemplateAction!findTemplateById.action?id=${template.id}">返回模板</a>)
									</s:if>
										<s:else>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (
										<a id='updateTemplate'
												href="javascript:sendRequest('TemplateAction!generateTemplate.action?id=${template.id}',viewResponse)">修改模板	</a>)
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
									<td align="right">
										配分:
									</td>
									<td>
										<input type="text" name="templateDetails.customScore"
											class="search_shuru1" alt="${scoreMessage}"
											title="${scoreMessage}" value="${scoreMessage}"
											onkeyup="if(isNaN(value))execCommand('undo')"
											id="customScore" />


									</td>
								</tr>
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
						<table border="1" width="100%" class="table">
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
									是否机打
								</td>
								<td align="center">
									机打类别
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
									<td align="center">
										${templateDetailsSet.isJida}
									</td>
									<td align="center">
										${templateDetailsSet.jidaClasss}
									</td>
									<td width="40%" align="center">
										<s:if test="#templateDetailsSet.onLayer!='root'">
											<a
												href="TemplateDetailsAction!findTemDetailsById.action?id=${templateDetailsSet.onLayer}&pageLayer=${pageLayer-1}">上一层</a>
										</s:if>
										<a
											href="TemplateDetailsAction!findTemDetailsById.action?id=${templateDetailsSet.id}&pageLayer=${pageLayer+1}">下一层</a>
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
					<!-- 绑定模板DIV -->
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
										href="AssessPersonnelAction!findAssessPersonnelForbb.action?id=${template.id}&userGroupId=${upList.id}">选择</a>
								</td>
								</tr>
							</s:iterator>
						</table>

					</div>
					<div id="bingBingDiv" style="display: none">
						<form action="AssessPersonnelAction!bingdingUsers.action"
							style="margin: 0px; padding: 0px;">
							<input type="hidden" name="id" value="${id}">

							<table style="width: 980px; border-collapse: collapse;"
								border="0" align="center">
								<tr>
									<td align="right" colspan="5">
										<font color="red">共选择 <label id="peopleLabel">
												${count}
											</label>人</font>
										<input type="submit" value="绑定"
											style="width: 60px; height: 40px;" align="top">
										<br>
									</td>
								</tr>
								<tr>
									<th colspan="6" align="center">
										考核模版
										<a
											href="TemplateAction!findTemplateById.action?id=${template.id}">
											<font color="red"> ${template.name}</font> </a> 的绑定人员管理
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
								</s:iterator>

								<s:iterator id="pageAp" value="assessPersonnelList"
									status="ststusfunction">
									<s:if test="#ststusfunction.first">
										<tr bgcolor="green">
											<td colspan="6" style="font-family: 微软雅黑; font-weight: bold;"
												align="center">
												<font color="#ffffff"> 可绑定人员</font>
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
											name="usersId" value="${pageAp.id}" onclick="chageNum(this)">
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="6" align="center">
										<font color="red">${errorMessage}</font>
									</td>
								</tr>
								<tr align="center" bgcolor="#c0dcf2"
									style="height: 40px; font-weight: bold;">
									<td colspan="4"></td>
									<td>
										<input type="checkbox" id="checkAll2"
											onclick="chageAllCheck(this)">
										全选
									</td>
								</tr>
								<tr>
									<td align="right" colspan="5">
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
					<table  class="table">
						<tr>
							<th colspan="5" align="center">
								主管级考核模版管理
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<td align="center">
								模板名称
							</td>
							<td align="center">
								考核对象
							</td>
							<td align="center">
								备注
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
									<font color="red">
								</s:else>
								<s:property value="#ststusfunction.index+1" />
								</font>
							</td>
							<td align="left" style="padding-left: 30px;">
								${pageTemplate.name}
							</td>
							<td align="center">
								${pageTemplate.assObject}
							</td>
							<td align="left" style="padding-left: 30px;">
								${pageTemplate.remarks}
							</td>
							<s:if test="%{pageStatus=='addTemplate'}">
								<td>
									<a
										href="TemplateAction!useHistry.action?id=${pageTemplate.id}&status=direct">使用历史模版</a>
								</td>
							</s:if>
							<s:else>
								<td>
									<a
										href="javascript:sendRequest('TemplateAction!PreviewTemplate.action?id=${pageTemplate.id}',viewResponse)">预览</a>
									/
									<a
										href="TemplateAction!findTemplateById.action?id=${pageTemplate.id}">查看</a>
									/
									<a
										href="AssessPersonnelAction!findAssessPersonnelForbb.action?id=${pageTemplate.id}">绑定人员</a>/
									<a onclick="return window.confirm('该模板将被删除,确定要删除吗?')"
										href="TemplateAction!delTemplate.action?id=${pageTemplate.id}&status=zhuguan">删除</a>
								</td>
							</s:else>
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
		$("#usersGroupDiv").show("");
	}
});
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
