
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
		<script type="text/javascript"
			src="<%=basePath%>javascript/radialIndicator.js">
</script>



		<title>日志管理,${companyInfo.shortName}作业网</title>
		<%--		<%@include file="/util/inc.jsp"%>--%>
		<script type="text/javascript">

document.onkeydown = banBackSpace;
//表单检查
function checkForm() {
	var title = document.getElementById("logTitle");
	var className = document.getElementById("className");
	if (title.value == "") {
		alert("标题不能为空!");
		title.focus();
		return false;
	} else if (className.value == "") {
		alert("类别不能为空!");
		className.focus();
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
	return true;
}
//流程控制
function chageProcess() {
	var workLogList = "${workLogList}";
	var addWorkLogDiv = document.getElementById("addWorkLog");//添加日志Div
	var findWorkLogDiv = document.getElementById("findWorkLog");//查询日志Div
	if (workLogList == "") {
		addWorkLogDiv.style.display = "block";
	} else if (workLogList != "") {
		findWorkLogDiv.style.display = "block";
	}
}

//显示内容
function showContent(title, content, id, jindu) {
	var logContentDiv = document.getElementById("logContentDiv");
	var operatingDiv = document.getElementById("operatingDiv");
	if (content == "operat") {
		operatingDiv.style.display = "block";
		logContentDiv.style.display = "none";
		document.getElementById("form").removeAttribute("style");
		document.getElementById("workLogTitle").innerHTML = title;
		document.getElementById("workLogId").value = id;
		document.getElementById("xiugaiIframe").src="";
	}else if(content=="queren"){
		operatingDiv.style.display = "block";
		logContentDiv.style.display = "none";
			document.getElementById("form").setAttribute("style","display:none;");
		document.getElementById("xiugaiIframe").src='WorkLogAction!findWorkLogById.action?id='+id+'&pageStatus=queren';
	}
	else {
		operatingDiv.style.display = "none";
		logContentDiv.style.display = "block";
		logContentDiv.innerHTML = content;
	}
	chageDiv("block", "您正在查看  " + title);
}
</script>

	</head>
	<body bgcolor="#ffffff" onload="chageProcess()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center">
		</div>
		<div id="contentDiv"
			style="position: absolute; width: 100%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(images/bq_bg2.gif); width: 980px;">
				<table
					style="width: 100%; background: url('<%=basePath%>images/bq_bg2.gif');">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					处理事件:
					<font color="red"> <span id="workLogTitle"></span> </font>
					<form
						action="WorkLogAction!updateWorkLog.action?pageStatus=${pageStatus}"
						method="post" id="form">
						<input id="workLogId" name="workLog.id" type="hidden">
						<table class="table">
							<tr>
								<th align="right">
									状态:
								</th>
								<td align="left">
									<input type="radio" name="workLog.logStatus" value="办理中"
										checked="checked" />
									办理中
									<input type="radio" name="workLog.logStatus" value="已完成"
										onclick="change('radio1','option','1')" id="radio1" />
									已完成
								</td>
							</tr>
							<tr>
								<th align="right">
									进度:
								</th>
								<td>
									<select id="jindu" name="workLog.jindu"
										onmouseout="change('option1','radio','1')">
										<option value=0>
											0%
										</option>
										<option value=10>
											10%
										</option>
										<option value=20>
											20%
										</option>
										<option value=30>
											30%
										</option>
										<option value=40>
											40%
										</option>
										<option value=50>
											50%
										</option>
										<option value=60>
											60%
										</option>
										<option value=70>
											70%
										</option>
										<option value=80>
											80%
										</option>
										<option value=90>
											90%
										</option>
										<option value=100 id="option1">
											100%
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									备注:
								</th>
								<td align="left">
									<textarea rows="6" cols="80" name="workLog.remarks"></textarea>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" value="提交" class="input">
									<input type="reset" value="重置" class="input">
								</td>
							</tr>
						</table>
					</form>
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
				<div id="logContentDiv" align="left"
					style="background-color: #ffffff; width: 100%; display: none">
				</div>
			</div>
		</div>
		<center>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div align="right">
					<a href="<%=basePath%>userCenter/log_workLog.jsp">添加日志</a>
					<s:if test="pageStatus!=null">
						<a
							href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=">日志查询</a>
					</s:if>
					<s:else>
						<a
							href="WorkLogAction!findWorkLogByCondition.action?pageStatus=single&workLog.logStatus=">日志查询</a>
					</s:else>
				</div>
				<div id="addWorkLog" align="center" style="display: none">
					<form action="WorkLogAction!addWorkLog.action" method="post"
						onsubmit="return checkForm()">
						<table class="table">
							<tr>
								<th colspan="2">
									添加日志
								</th>
							</tr>
							<tr>
								<th align="right">
									标题:
								</th>
								<td>
									<input id="logTitle" type="text" name="workLog.title" size="40" />
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									类别:
								</th>
								<td align="left">
									<select id="className" title="新类别可直接输入"
										onmouseover="createDept('className','WorkLogClassAction!findPersonWorkLogClass.action')"
										name="workLogClass.name" style="width: 275px"
										onkeypress="writeSelect(this)"
										onkeydown="window.history.forward(1);if(event.keyCode == 8){this.options[0].text ='';}">
										<option value=""></option>
									</select>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									状态:
								</th>
								<td>
									<input type="radio" name="workLog.logStatus" value="待办"
										checked="checked" />
									待办
									<input type="radio" name="workLog.logStatus" value="办理中" />
									办理中
									<input type="radio" name="workLog.logStatus" value="已完成"
										onclick="change('radio2','option','2')" id="radio2" />
									已完成
								</td>
							</tr>
							<tr>
								<th align="right">
									进度:
								</th>
								<td>
									<select id="jindu" name="workLog.jindu"
										onmouseout="change('option2','radio','2')">
										<option value=0>
											0%
										</option>
										<option value=10>
											10%
										</option>
										<option value=20>
											20%
										</option>
										<option value=30>
											30%
										</option>
										<option value=40>
											40%
										</option>
										<option value=50>
											50%
										</option>
										<option value=60>
											60%
										</option>
										<option value=70>
											70%
										</option>
										<option value=80>
											80%
										</option>
										<option value=90>
											90%
										</option>
										<option value=100 id="option2">
											100%
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									内容:
								</th>
								<td align="left">
									<textarea rows="6" cols="80" name="workLog.content"></textarea>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input id="sub" type="submit" value="提交"
										style="width: 80px; height: 50px" >
									<input type="reset" value="重置"
										style="width: 80px; height: 50px">
								</td>
							</tr>
						</table>

					</form>
				</div>
				<div id="findWorkLog" align="center" style="display: none;">
					<form
						action="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}"
						id="testFrom" method="post" style="margin: 0px; padding: 0px">
						<table class="table">
							<tr>
								<th colspan="6">
									日志查询
								</th>
							</tr>
							<tr>
								<th align="right">
									标题:
								</th>
								<td align="left">
									<input type="text" name="workLog.title" />
								</td>
								<th align="right">
									状态:
								</th>
								<td align="left">
									<SELECT name="workLog.logStatus" style="width: 155px;">
										<OPTION value=""></OPTION>
										<OPTION value="待办">
											待办
										</OPTION>
										<OPTION value="办理中">
											办理中
										</OPTION>
										<OPTION value="已完成">
											已完成
										</OPTION>
									</SELECT>
								</td>
								<th align="right">
									类别:
								</th>
								<td align="left">
									<select id="logClassName"
										onmouseover="createDept('logClassName','WorkLogClassAction!findPersonWorkLogClass.action')"
										name="workLog.workLogClass.name" style="width: 100px">
										<option value=""></option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									月份:
								</th>
								<td align="left">
									<input class="Wdate" type="text" name="workLog.mouth"
										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
								</td>
								<th align="right">
									添加时间:
								</th>
								<td align="left">
									<input class="Wdate" type="text" name="workLog.addDateTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

								</td>
								<s:if test="pageStatus=='all'">
									<th align="right">
										部门:
										<br />
									</th>
									<td>


										<select id="dept" name="workLog.dept"
											onclick="createDept('dept')">
											<option value="">
												请选择部门
											</option>
										</select>
									</td>
								</s:if>
								<s:else>
									<td>
									</td>
									<td></td>
								</s:else>

							</tr>
							<tr>
								<td align="center" colspan="6">
									<input type="submit" value="查询" onclick="dept"
										style="width: 80px; height: 50px">
									<input type="reset" value="重置"
										style="width: 80px; height: 50px">
								</td>
							</tr>
						</table>
					</form>

					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								类别
							</th>
							<th align="center">
								标题
							</th>
							<th align="center">
								添加人
							</th>
							<th align="center">
								指派人
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								违规次数
							</th>
							<th align="center">
								开始时间
							</th>
							<th align="center">
								结束时间
							</th>
							<th align="center">
								提交时间
							</th>
							<th align="center">
								任务交期
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								完成率
							</th>
							<th align="center">
								操作
							</th>
						</tr>
							<s:iterator value="wqrList" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff"> 未确认工作纪录<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">

									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.zpStatus!='确认'">
									<a href="javascript:;" onclick="showContent('${pageworkLog.title}','queren',${pageworkLog.id})">确认</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">操作</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
							</td>
							</tr>
						</s:iterator>
						
						<s:iterator value="logStatusDeList" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff"> 工作记录待办信息<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">
									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.logStatus!='已完成'">
									<a href="javascript:;"
										onclick="showContent('${pageworkLog.title}','operat','${pageworkLog.id}','${pageworkLog.jindu}')">操作</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">操作</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
							</td>
							</tr>
						</s:iterator>


						<s:iterator value="logStatusBiList" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff"> 工作记录办理中信息<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">

									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.logStatus!='已完成'">
									<a href="javascript:;"
										onclick="showContent('${pageworkLog.title}','operat','${pageworkLog.id}'),'${pageworkLog.jindu}'">操作</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">操作</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
							</td>
							</tr>
						</s:iterator>

						<s:iterator value="logStatusDeList2" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff"> 工作记录待办信息<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">
									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.logStatus!='已完成'">
									<a href="javascript:;"
										onclick="showContent('${pageworkLog.title}','operat','${pageworkLog.id}'),'${pageworkLog.jindu}'">操作</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">操作</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
							</td>
							</tr>
						</s:iterator>


						<s:iterator value="logStatusBiList2" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff"> 工作记录办理中信息<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">

									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.logStatus!='已完成'">
									<a href="javascript:;"
										onclick="showContent('${pageworkLog.title}','operat','${pageworkLog.id}','${pageworkLog.jindu}')">操作</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">操作</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
							</td>
							</tr>
						</s:iterator>

						<s:iterator value="LogStatusCao" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff"> 超时未完成信息<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">

									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.logStatus!='已完成'">
									<a href="javascript:;"
										onclick="showContent('${pageworkLog.title}','operat','${pageworkLog.id}','${pageworkLog.jindu}')">操作</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">操作</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
							</td>
							</tr>
						</s:iterator>

													
						<s:iterator value="workLogList" id="pageworkLog"
							status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="green">
									<th colspan="16" align="center">
										<font color="#ffffff"> 所有工作记录信息<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<s:if test="#pageworkLog.workLogClass!=null">
									<a
										href="WorkLogAction!findWorkLogByCondition.action?workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addDateTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="pageStatus=='single'||pageStatus=='dept'">
									<s:if test="#pageworkLog.logStatus!='已完成'">
										<a href="javascript:;"
											onclick="showContent('${pageworkLog.title}','operat','${pageworkLog.id}','${pageworkLog.jindu}')">操作/</a>
									</s:if>
									<s:else>
										<a href="javascript:;">操作/</a>
									</s:else>
									<a
										href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=${pageStatus}">删除</a>
								</s:if>
							</td>

							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="16" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="9" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
					</table>

					<div align="center">
						<font color="red">${successMessage}</font>
					</div>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">

function todept() {
	var url = "WorkLogAction!findWorkLogByCondition.action";
	window.location.href = url;

}
function change(obj1, obj2, num) {
	var value = "";
	if (obj2 == 'option') {
		value = document.getElementById(obj1).checked;
	} else {

		value = document.getElementById(obj1).selected;
	}
	if (value) {
		if (num == '1') {
			if (obj2 == 'option') {
				document.getElementById(obj2 + num).selected = "selected";
			} else {
				document.getElementById(obj2 + num).checked = "checked";
			}

		} else {
			if (obj2 == 'option') {
				document.getElementById(obj2 + num).selected = "selected";
			} else {
				document.getElementById(obj2 + num).checked = "checked";
			}
		}
	}
}<%--function chaxun(obj1,obj2){--%>
<%--	if(obj2!=null){--%>
<%--		alert(obj2)--%>
<%--		obj1.href="WorkLogAction!findWorkLogByCondition.action?pageStatus="+obj2+"&workLog.logStatus=";--%>
<%--	}else{--%>
<%--		obj1.href="WorkLogAction!findWorkLogByCondition.action?pageStatus=single&workLog.logStatus=";--%>
<%--	}--%>
<%--	jindu();--%>
<%--//href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus="-->--%>
<%--//href="WorkLogAction!findWorkLogByCondition.action?pageStatus=single&workLog.logStatus="--%>
<%--}--%>
$(function(){
	
	$(".jindu").each(function(i) {
		
		var hk_val = $(this).attr('data');
		
			$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
		
		
	});
})

$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="rebeack"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})

</script>
	</body>
</html>
<%
	request.getSession().removeAttribute("mfNames");
	request.getSession().removeAttribute("moduleFunction");
%>
