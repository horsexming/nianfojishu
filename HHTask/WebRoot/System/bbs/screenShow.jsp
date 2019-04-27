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
		<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
				<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;" align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table">
					<tr>
						<th>序号</th>
						<th>工位</th>
						<th>总成名称</th>
						<th>件号</th>
						<th>人员姓名</th>
						<th>人员工号</th>
						<th>设备名称</th>
						<th>设备编号</th>
						<th>设备状态</th>
						<td>员工图片</td>
					</tr>
					<s:iterator value="#request.dataList" id="d" status="st">
						<tr>
							<td>${st.index+1 }</td>
							<td>${d.gongwei }</td>
							<td>${d.proname }</td>
							<td>${d.markid }</td>
							<td>${d.usernames }</td>
							<td>${d.usercodes }</td>
							<td>${d.machineName}</td>
							<td>${d.shebeiNo }</td>
							<td>${d.machineStatus }</td>
							<td>
								<s:iterator value='#d.usercodes.split(",")' id="dc">
									<img src="images/users/${dc}.jpg"/>
								</s:iterator>
							</td>
						</tr>
					</s:iterator>
					
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
