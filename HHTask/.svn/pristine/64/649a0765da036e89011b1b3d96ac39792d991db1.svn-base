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
				<h3>添加登记信息</h3>
	<form action="register_add.action" method="post">	
		<table>
			<tr>
				<td><label>人员姓名: </label></td><td><input type="text" name="reg.personName"/></td>
			</tr>
			<tr>
				<td><label>登记号码: </el></td><td><input type="text" name="reg.badgenumber"/></td>
			</tr>
			<tr>
				<td><label>卡号: </label></td><td><input type="text" name="reg.cardNo"/></td>
			</tr>
			<tr>
				<tr>
							<td colspan="4" align="center">
								<input type="submit" value="提交"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="清空"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
			</tr>
		</table>
	</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
