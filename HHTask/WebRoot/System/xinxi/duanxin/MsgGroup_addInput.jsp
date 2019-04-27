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
				<div>
					<form action="MsgGroup_add.action" method="post">
						<table class="table" style="width: 30%" >
							<tr>
								<td align="right">姓名</td>
								<td><input type="text" name="msgAdd.username" maxlength="10" title="姓名为十个汉字" /> </td>
							</tr>
							<tr>
								<td align="right">手机号</td>
								<td><input type="text" name="msgAdd.phone" maxlength="11" title="手机号为11位" /> </td>
							</tr>
							<tr>
								<td align="right">所在组</td>
								<td><input type="text" name="msgAdd.userGroup" maxlength="10" title="最长输入十个汉字" /> </td>
							</tr>
							<tr>
								<td align="center" colspan="2" ><input type="submit" value="提交"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
