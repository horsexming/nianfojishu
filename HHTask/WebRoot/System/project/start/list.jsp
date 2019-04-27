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
		<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table" style="width: 35%" >
					<tr>
						<th colspan="3">项目启动书处理流程</th>
					</tr>
					<tr>
						<th>1</th>
						<th>项目启动书</th>
						<td>
							<s:if test="projectStartOutline == null">
								<a href="ProjectStartOutline_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
							</s:if><s:else>
								<a href="ProjectStartOutline_updateInput.action?p.id=${projectStartOutline.id}" target="_blank">修改</a>
								<a href="ProjectStartOutline_list.action?p.id=${projectStartOutline.id}" target="_blank">查看</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th>2</th>
						<th>技术方案</th>
						<td>
							<s:if test="projectStartTechnical == null && projectStartOutline != null">
								<a href="ProjectStartTechnical_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
							</s:if><s:elseif test="projectStartTechnical != null">
								<a href="ProjectStartTechnical_updateInput.action?p.id=${projectStartTechnical.id}" target="_blank">修改</a>
								<a href="ProjectStartTechnical_list.action?p.id=${projectStartTechnical.id}" target="_blank">查看</a>
							</s:elseif>
						</td>
					</tr>
					<tr>
						<th>3</th>
						<th>项目预算</th>
						<td>
							<s:if test="projectStartTechnical != null">
								<a href="ProjectStartBudget_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
								<a href="ProjectStartBudget_list.action?p.root.id=${projectStart.id}" target="_blank">查看</a>
							</s:if>
						</td>
					</tr>
					<tr>
						<th>4</th>
						<th>会签纪要</th>
						<td>
							<s:if test="projectStartCountersigned == null && projectStartBudget > 0">
								<a href="ProjectStartCountersigned_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
							</s:if><s:elseif test="projectStartCountersigned != null">
								<a href="ProjectStartCountersigned_updateInput.action?p.id=${projectStartCountersigned.id}" target="_blank">修改</a>
								<a href="ProjectStartCountersigned_list.action?p.id=${projectStartCountersigned.id}" target="_blank">查看</a>
							</s:elseif>
						</td>
					</tr>
					<tr>
						<th>5</th>
						<th>项目责任书</th>
						<td>
							<s:if test="projectStartResponsible == null && projectStartCountersigned != null">
								<a href="ProjectStartResponsible_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
							</s:if><s:elseif test="projectStartResponsible != null">
								<a href="ProjectStartResponsible_updateInput.action?p.id=${projectStartResponsible.id}" target="_blank">修改</a>
								<a href="ProjectStartResponsible_list.action?p.id=${projectStartResponsible.id}" target="_blank">查看</a>
							</s:elseif>
						</td>
					</tr>
					<tr>
						<th>6</th>
						<th>项目进度</th>
						<td>
							<s:if test="projectStartResponsible != null">
								<a href="ProjectStartSchedule_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
								<a href="ProjectStartSchedule_list.action?p.root.id=${projectStart.id}" target="_blank">查看</a>
							</s:if>
						</td>
					</tr>
					<tr>
						<th>7</th>
						<th>项目成员</th>
						<td>
							<s:if test="projectStartSchedule > 0">
								<a href="ProjectStartUser_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
								<a href="ProjectStartUser_list.action?p.root.id=${projectStart.id}" target="_blank">查看</a>
							</s:if>
						</td>
					</tr>
					<tr>
						<th>8</th>
						<th>项目要求</th>
						<td>
							
							<s:if test="projectStartClaim == null && projectStartUser > 0">
								<a href="ProjectStartClaim_addInput.action?p.root.id=${projectStart.id}" target="_blank">添加</a>
							</s:if><s:elseif test="projectStartClaim != null">
								<a href="ProjectStartClaim_updateInput.action?p.id=${projectStartClaim.id}" target="_blank">修改</a>
								<a href="ProjectStartClaim_list.action?p.id=${projectStartClaim.id}" target="_blank">查看</a>
							</s:elseif>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
