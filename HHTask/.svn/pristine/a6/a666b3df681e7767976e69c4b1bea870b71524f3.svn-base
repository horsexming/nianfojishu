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
window.onload = chageAddOrFind;

function chageAddOrFind() {
	var list = "${projectRecordList}";
	if (list != "") {
		document.getElementById("findProRecord").style.display = "block";
	} else {
		document.getElementById("addProRecord").style.display = "block";
	}
}

function checkProRecordForm() {
	var websiteName = document.getElementById("websiteName");
	var projectName = document.getElementById("projectName");
	var loginAction = document.getElementById("loginAction");
	var loginField = document.getElementById("loginField");
	var loginFieldName = document.getElementById("loginFieldName");
	if (websiteName.value == "") {
		alert("网站名称不能为空!");
		websiteName.focus();
		return false;
	} else if (projectName.value == "") {
		alert("项目名称不能为空!");
		projectName.focus();
		return false;
	} else if (loginAction.value == "") {
		alert("登录Action不能为空!");
		loginAction.focus();
		return false;
	} else if (loginField.value == "") {
		alert("登录字段不能为空!");
		loginField.focus();
		return false;
	} else if (loginFieldName.value == "") {
		alert("登录字段名称不能为空!");
		loginFieldName.focus();
		return false;
	} else {
		return true;
	}
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>

		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">添加登录网站</a>
					<a
						href="ProjectRecordAction!findAllProRecord.action?projectRecord.projectName="
						style="color: #ffffff">查询所有登录网站</a>
				</div>
			</div>
			
			<div align="center">
				<div id="addProRecord" style="display: none;">
					<form action="ProjectRecordAction!addProRecord.action"
						method="post" onsubmit="return checkProRecordForm();">
						<table border="0">
							<tr>
								<th colspan="3" align="center">
									<s:if test="projectRecord!=null">
									修改登录网站
									<input type="hidden" name="id" value="${projectRecord.id}" />
										<input type="hidden" name="projectRecord.id"
											value="${projectRecord.id}" />
									</s:if>
									<s:else>
								添加登录网站
								</s:else>
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									网站名称:
								</th>
								<td>
									<input id="websiteName" name="projectRecord.websiteName"
										value="${projectRecord.websiteName}">
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th align="right">
									项目名称:
								</th>
								<td>
									<input id="projectName" name="projectRecord.projectName"
										value="${projectRecord.projectName}">
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th align="right">
									登录Action:
								</th>
								<td>
									<input id="loginAction" name="projectRecord.loginAction"
										value="${projectRecord.loginAction}">
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th align="right">
									登录字段:
								</th>
								<td>
									<input id="loginField" name="projectRecord.loginField"
										value="${projectRecord.loginField}" title="多个字段用','分割开;">
								</td>
								<td>
									(如:"name,password")
								</td>
							</tr>
							<tr>
								<th align="right">
									登录字段名称:
								</th>
								<td>
									<input id="loginFieldName" name="projectRecord.loginFieldName"
										value="${projectRecord.loginFieldName}" title="多个名称用','分割开;">
								</td>
								<td>
									(如:"名称,密码" )
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<br />
									<br />
									<input type="submit" value="提交"
										style="width: 100px; height: 50px;" />

									<input type="reset" value="清空"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="findProRecord" style="display: none;">
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								网站名称
							</th>
							<th align="center">
								项目名称
							</th>
							<th align="center">
								登录Action
							</th>
							<th align="center">
								登录字段
							</th>
							<th align="center">
								登录名称
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="projectRecordList" id="pageprojectRecord"
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
								${pageprojectRecord.websiteName}
							</td>
							<td>
								${pageprojectRecord.projectName}
							</td>
							<td>
								${pageprojectRecord.loginAction}
							</td>
							<td>
								${pageprojectRecord.loginField}
							</td>
							<td>
								${pageprojectRecord.loginFieldName}
							</td>
							<td>
								<a
									href="ProjectRecordAction!delProRecord.action?id=${pageprojectRecord.id}">删除</a>
								/
								<a
									href="ProjectRecordAction!findProRecordById.action?id=${pageprojectRecord.id}">修改</a>
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
							</s:else>
							</td>
						</tr>
					</table>





				</div>
				<div align="center" style="color: red">
					${successMessage}
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
