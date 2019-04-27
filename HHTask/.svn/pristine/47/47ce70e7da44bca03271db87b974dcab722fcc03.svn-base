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
	<body>
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
				<div align="center"  id="image" class="my_show">
				<table class="table">
					<tr>
						<td colspan="2">${leaveInform.username}</td>
						<td align="center">编号</td><td>${leaveInform.time}</td>
					</tr>
					<tr>
						<tdalign="center">班组/部门</td><td align="center">项目</td><td align="center">金额</td><td align="center">签收</td>
					</tr>
					<tr>
						<td rowspan="5" align="center">${ leaveInform.dept}</td>
						<td align="left">  项目一:&nbsp;&nbsp;${leaveInform.fuck2 }</td>
						<td align="center"> ${leaveInform.postsalary }</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">项目二:&nbsp;&nbsp;${leaveInform.fuck3 }</td>
						<td align="center">${leaveInform.secrecysalary }</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">项目三:&nbsp;&nbsp;${leaveInform.fuck4 }</td>
						<td align="center">${leaveInform.performancesalary }</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">项目四:&nbsp;&nbsp;${ leaveInform.fuck5}</td>
						<td align="center">${leaveInform.achievement }</td>
						<td></td>
					</tr>
					<tr>
						<td align="left" >项目五:&nbsp;&nbsp;${leaveInform.starttime }</td>
						<td align="center">${leaveInform.offset }</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" align="right" >合计:</td>
						<td align="center">${ leaveInform.heji}</td>
						<td></td>
					</tr>
					<tr>
						<td align="center" >说明</td>
						<td colspan="3">${leaveInform.shuoming}</td>
					</tr>
				</table>
				</div>
				<table>
				<tr>
					<td align="center"><input type="button" onclick="pagePrint('image')" class="input" 
							id="print" 
							value="打      印"
									style="width: 80px; height: 50px;" /></td>
					<td></td>
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
