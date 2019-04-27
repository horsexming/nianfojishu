<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script type="text/javascript"
		src="javascript/DatePicker/WdatePicker.js" />

</script>
	<head>
		<!-- include file="/util/inc.jsp" -->
		<base href="<%=basePath%>">
		<title>超级管理员管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
.tag_1 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_1.jpg');
}

.tag_2 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_2_2.jpg');
}
</style>
		<script type="text/javascript">
function addsubmit() {
	var adminName = document.getElementById("adminName");
	var form=document.getElementById("addform");
	if (adminName.value == "") {
		alert("管理员名称不能为空!");
		return false;
	}else {
		form.action = "AdminAction!addSuper.action";
		form.submit();
	}
}
var oldObj;
var oldObj2;
function chageModule(obj, obj2) {
	if (obj.id != "module1") {
		document.getElementById("module1").className = "tag_1";
		document.getElementById("module1_1").style.display = "none";
	}
	if (oldObj != null) {
		oldObj.className = "tag_1";
		document.getElementById("module1_" + oldObj2).style.display = "none";
	}

	obj.className = "tag_2";
	document.getElementById("module1_" + obj2).style.display = "block";

	oldObj = obj;
	oldObj2 = obj2;
}
function chageBgcolor(obj) {
	obj.style.background = "#c0dcf2";
}
function outBgcolor(obj, oldColor) {
	obj.style.background = oldColor;
}
</script>

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">超级管理员管理</font>
				</div>
				<div
					style="border: solid 1px #0170b8; width: 1000px; font-family: 微软雅黑; margin-top: 10px;"
					align="left">
					<div
						style="padding-top: 10px; padding-left: 20px; border-bottom: solid #0170b8 1px;">
						<table align="center">
							<tr align="center">
								<td align="center">
									<div id="module1" class="tag_2" onclick="chageModule(this,'1')">
										超级管理员列表
									</div>
								</td>
								<td align="center">
									<div id="module2" class="tag_1" onclick="chageModule(this,'2')">
										添加超级管理员
									</div>
								</td>
						</table>
					</div>
				</div>
			</div>
			<div>
				<div align="center">
					<font color="red"><s:property
							value="#request.successMessage" /> </font>
				</div>
				<div id="module1_1" align="center">
					<div id="bangding">
						<form action="AdminAction!showsuperAdmin.action" method="post"
							style="margin: 0px">
							<br>
							<table class="table">
								<tr>

									<td align="right">
										管理员名称:
									</td>
									<td>
										<input type="text" name="superAdmin.adminName"
											value="<s:property value="superAdmin.adminName"/>" />
									</td>
									<td>
										<input type="submit" value="查询"
											style="width: 100px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
						<hr>
						<form action="AdminAction!showsuperAdmin.action"
							method="post" target="main" style="margin: 0px">
							<table class="table">
								<tr align="center" bgcolor="#c0dcf2"
									style="height: 40px; font-weight: bold;">
									<td>
										序号
									</td>
									<td>
										管理员名称
									</td>

									<td>
										最后一次登录时间
									</td>
									<td>
										管理员类型
									</td>
									<td>
										登录次数
									</td>
									<td>
										操作
									</td>
								</tr>
									<s:iterator id="pagesuperAdmin" value="superAdminList"
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
										${ststusfunction.index+1}
									</td>
									<td>
										<s:property value="#pagesuperAdmin.adminName" />
									</td>
									<td>
										<s:property value="#pagesuperAdmin.lastLogin" />
									</td>
									<td>
										<s:property value="#pagesuperAdmin.type" />
									</td>
									<td>
										<s:property value="#pagesuperAdmin.count" />

									</td>
									

									<td>
										<a
											onclick="if(window.confirm('您将删除数据是否继续？')){window.location.href = 'AdminAction!delete.action?superAdmin.id=<s:property value="#pagesuperAdmin.id"/>'};"
											target="main">删除</a>
									</td>
									</tr>
								</s:iterator>
								<tr>

									<s:if test="errorMessage==null">
										<td colspan="6" align="right">
											第
											<font color="red"><s:property value="cpage" /> </font> /
											<s:property value="total" />
											页
											<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
												styleClass="page" theme="number" />
										</td>
									</s:if>
									<s:else>
										<td colspan="6" align="center">
											<font color="red"><s:property
													value="#request.errorMessage" /> </font>
										</td>
									</s:else>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<!--添加版块 -->
				<div id="module1_2" align="center"
					style="display: none; padding-top: 20px;">
					<div align="center" id="addModule">
						<form id="addform" action="" method="post">
							<table class="table">
								<tr>
									<td align="right" width="45%">
										管理员名称:
									</td>
									<td>
									<input type="hidden" name="cpage" value="${cpage}">
										<input id="adminName" type="text"
											name="superAdmin.adminName" />
										<font color="red">*</font>
									</td>
								</tr>

								<tr>
									<td colspan="2" align="center">
										<br>
										<input type="button" class="input" onclick="addsubmit()"
											value="提交">
										<input type="reset" class="input" value="重置">
									</td>
								</tr>
							</table>
						</form>
					</div>

				</div>

				<div align="center">
					<%
						request.getSession().removeAttribute("successMessage");
						request.getSession().removeAttribute("errorMessage");
					%>
					<br />
					<br />
					<br />
				</div>
			</div>
		</center>
		<script type="text/javascript">
</script>
	</body>
</html>
