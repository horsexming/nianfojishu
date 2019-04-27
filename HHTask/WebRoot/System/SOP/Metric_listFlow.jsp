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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div><font color="red">${successMessage}${errorMessage}</font></div>
				<div style="float: left"  >
					<table>
						<tr>
							<td colspan="2" align="center">添加采购料</td>
						</tr>
						<tr>
							<td>物料ID</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>物料模版</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>报价模版</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>物料介绍</td>
							<td><textarea rows="6" cols="17"></textarea> </td>
						</tr>
						
					</table>
				
				</div>
				<div style="float: center;">
					<table>
						<tr>
							<td colspan="2" align="center">报价条件设置</td>
						</tr>
						<tr>
							<td>序号</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>件号</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>规格</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>需求量/吨</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>产地</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>板料级别</td>
							<td><input  /></td>
						</tr>
						<tr>
							<td>报价有效期(天) </td>
							<td><input  /></td>
						</tr>
					</table>
				</div>
				
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
