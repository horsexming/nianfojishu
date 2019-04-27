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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
				onclick="chageDiv('none')">
			</div>
			<div id="contentDiv"
				style="position: absolute; z-index: 255; width: 900px; display: none;"
				align="center">
				<div id="closeDiv"
					style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
					<table style="width: 100%">
						<tr>
							<td>
								<span id="title">您正在进行申请销假操作</span>
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									id="closeTcDiv" height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
							hspace="0" vspace="0" frameborder="0" scrolling="yes"
							style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

					</div>
				</div>
			</div>
		<div id="gongneng">
			<div align="center">
				<form id="testFrom" action="AskForLeaveAction!selectAllByLeavePage.action"
					method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<input id="submitPersonDept" name="askForLeave.submitPersonDept"
						value="${askForLeave.submitPersonDept}" type="hidden" />
					<table class="table">
						<tr>
							<td>
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								<input type="text" name="askForLeave.leavePerson" />
							</td>
							<td>
								工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<input type="text" name="askForLeave.leavePersonCode" />
							</td>
							<td>
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
<%--								<input type="text" name="askForLeave.leavePersonDept" />--%>
								<s:if test="askForLeave.submitPersonDept=='dept'">
								${Users.dept}
								</s:if>
								<s:else>
									<select name="askForLeave.leavePersonDept" id="dept" style="width: 155px;">
											<option value=""></option>
									</select>							
								</s:else>
								</td>
							<td>
								天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数:
								<input type="text" name="askForLeave.leaveDays" />
							</td>
							<td rowspan="2" align="center">
								<input type="submit" class="input" value="查询" />
								<input type="reset" class="input" value="重置" />
							</td>
						</tr>
						<tr>
							<td>
								请假类型:
								<input type="text" name="askForLeave.leaveType" />
							</td>
							<td>
								时间从:
								<input type="text" name="startDate" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								到:
								<input type="text" name="endDate" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								假事类型:
								<input type="text" name="askForLeave.leaveTypeOf" />
							</td>
						</tr>
						<tr>
							<td>
								导出月份:
								<input type="text" name="yuefen" class="Wdate" id="yuefen"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<td colspan="4" align="center">
								<input type="button" value="导出" class="input"
									onclick="daochuExec(1);todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
				</br>
				</br>
				<h1>
					请假管理
				</h1>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							请假类型
						</th>
						<th>
							请假人
						</th>
						<th>
							请假人工号
						</th>
						<th>
							请假人所在部门
						</th>
						<th>
							请假开始时间
						</th>
						<th>
							请假结束时间
						</th>
						<th>
							请假天数
						</th>
						<th>
							请假小时数
						</th>
						<th>
							假事类型
						</th>
						<th>
							请假原因
						</th>
						<th>
							提交人
						</th>
						<th>
							出门时间
						</th>
						<th>
							返回时间
						</th>
						<th>
							审批状态
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作请假
						</th>
					</tr>
					<!-- 个人可打印记录 -->
					<%--<s:if test="%{list.size()>0}">
						<tr>
							<td colspan="15" align="center"
								style="font-size: 14px; color: red;">
								个人可打印记录
							</td>
						</tr>
						<s:iterator value="list" id="print" status="printStatus">
							<tr align="center" bgcolor="#FFE4E1"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#F5FFFA')">
								<td>
									<s:property value="#printStatus.index+1" />
								</td>
								<td>
									${print.leaveType}
								</td>
								<td>
									${print.leavePerson}
								</td>
								<td>
									${print.leavePersonCode}
								</td>
								<td>
									${print.leavePersonDept}
								</td>
								<td>
									${print.leaveStartDate}
								</td>
								<td>
									${print.leaveEndDate}
								</td>
								<td>
									${print.leaveDays}
								</td>
								<td>
									${print.leaveHours}
								</td>
								<td>
									${print.leaveTypeOf}
								</td>
								<td>
									${print.leaveReason}
								</td>
								<td>
									${print.submitPerson}
								</td>
								<td>
									${print.exitTime}
								</td>
								<td>
									${print.returnTime}
								</td>
								<td>
									${print.approvalStatus}
								</td>
								<td>
									<a href="CircuitRunAction_findAduitPage.action?id=${print.epId}">审批流程</a>
								</td>
								<td>
									<a
										href="AskForLeaveAction!getPrintById.action?id=${print.leaveId}"><font
										color="red">打印</font> </a>

								</td>
							</tr>
						</s:iterator>
					</s:if>
					--%>
					<!-- 请假历史记录 -->

					<tr>
						<td colspan="17" align="center">
							请假历史记录
						</td>
					</tr>
					<s:iterator value="#attr.askList" id="pageAskForLeave"
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageAskForLeave.leaveType}
						</td>
						<td>
							${pageAskForLeave.leavePerson}
						</td>
						<td>
							${pageAskForLeave.leavePersonCode}
						</td>
						<td>
							${pageAskForLeave.leavePersonDept}
						</td>
						<td>
							${pageAskForLeave.leaveStartDate}
						</td>
						<td>
							${pageAskForLeave.leaveEndDate}
						</td>
						<td>
							${pageAskForLeave.leaveDays}
						</td>
						<td>
							${pageAskForLeave.leaveHours}
						</td>
						<td>
							${pageAskForLeave.leaveTypeOf}
						</td>
						<td>
							${pageAskForLeave.leaveReason}
						</td>
						<td>
							${pageAskForLeave.submitPerson}
						</td>
						<td>
							${pageAskForLeave.exitTime}
						</td>
						<td>
							${pageAskForLeave.returnTime}
						</td>
						<td>
							${pageAskForLeave.approvalStatus}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageAskForLeave.epId}">审批流程</a>
						</td>
						<td>
							<s:if
								test='"未审批".equals(#pageAskForLeave.approvalStatus)||"打回".equals(#pageAskForLeave.approvalStatus)'>
								<a
									href="javascript:if(confirm('确定要删除该条请假信息吗？')) location.href='AskForLeaveAction!deleteLeaves.action?id=${pageAskForLeave.leaveId}'"><font
									color="red">删除</font> </a>
								<hr>
								<a
									href="AskForLeaveAction!updateLeaves.action?id=${pageAskForLeave.leaveId}"><font
									color="red">修改</font> </a>
							</s:if>
							<s:elseif test="'同意' == #pageAskForLeave.approvalStatus && pageStatus == 'self'
							 && ('事假'==#pageAskForLeave.leaveTypeOf  ) "><!-- || '换休'==#pageAskForLeave.leaveTypeOf || '年休'==#pageAskForLeave.leaveTypeOf -->
								<s:if test="#pageAskForLeave.sqStatus!='已申请'">
									<a href="javascript:;"  onclick="tanchu('${pageAskForLeave.leaveId}')">
										<font color="red">申请销假</font>
									</a>
								</s:if>
								<s:else>
									<a href="javascript:;" onclick="tanchu1('${pageAskForLeave.leaveId}')">
										<font color="red">销假记录</font>
									</a>
								</s:else>
							</s:elseif>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="18" align="center" style="color: red">
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<div align="left">
			<%@include file="/util/foot.jsp"%>
			<center></center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
		<script type="text/javascript">
<%--function daochuExec() {--%>
<%--	var yuefen = document.getElementById("yuefen").value;--%>
<%--	var dept = document.getElementById("submitPersonDept").value;--%>
<%--	//objForm.action="nianXiuAction!exportExcel.action?kaoQin.yuefen="+yuefen;--%>
<%--	//objForm.submit();--%>
<%--	window.location.href = "AskForLeaveAction!exportExcel.action?yuefen="--%>
<%--			+ yuefen + "&depts=" + dept;--%>
<%--}--%>
function daochuExec(a){
		if(a==1){
			$("#testFrom").attr("action","AskForLeaveAction!exportExcel.action");
			$("#testFrom").submit();
			$("#testFrom").attr("action","AskForLeaveAction!selectAllByLeavePage.action");
			
		}
	}

var XMLHttpReq;
//创建XMLHttpRequest对象 
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {//Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
	}
}
//发送请求函数 (url=请求地址 ,obj=指定响应函数)
function sendRequest(url, obj) {
	createXMLHttpRequest();
	var url = url;
	url = encodeURI(encodeURI(url));
	XMLHttpReq.open("post", url, true);
	XMLHttpReq.onreadystatechange = obj;//指定响应函数
	XMLHttpReq.send(null);// 发送请求
}

var select;
//为select赋值(返回信息为包含|的字符串)
function findAllDept() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var dept = message.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				var isPresence = jsSelectIsExitItem(select, dept[i]);
				if (isPresence == false) {
					var optionItem = new Option(dept[i], dept[i]);
					select.options.add(optionItem);
				}
			}
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

// 判断select选项中 是否存在Value和查出的相同的Item   
function jsSelectIsExitItem(objSelect, objItemValue) {
	var isPresence = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			isPresence = true;
			break;
		}
	}
	return isPresence;
}

//生成下拉列表(默认是查询部门)
function createDept(selectName, actionUrl) {
	if(selectName=='dept'){
		if (actionUrl == null) {
			actionUrl = "DeptNumberAction!finAllDeptNumberForSetlect.action";
		}
	}
	select = document.getElementById(selectName);
	sendRequest(actionUrl, findAllDept)
}
function tanchu(num) {
		document.getElementById("xiugaiIframe").src = "AskForLeaveAction!updateLeaves.action?id="+num+"&pageStatus=xj";
	chageDiv('block')
}
function tanchu1(num) {
		document.getElementById("xiugaiIframe").src = "AskForLeaveAction!qxAskForLeaveshow.action?id="+num;
	chageDiv('block')
}
</script>
	</body>
</html>
