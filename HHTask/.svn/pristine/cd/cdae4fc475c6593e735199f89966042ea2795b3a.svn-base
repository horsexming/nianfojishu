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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				添加试制奖金
				<form action="TijingAction!addShizhiJIangjin.action" method="post">
				<table>
				<tr><td>试制产品件号:<input type="text" name="tijing.tjmarkId" /></td><td>试制产品型别:<input type="text" name="tijing.tjstyle" /></td></tr>
				<tr><td>试制数量:<input type="text" name="tijing.tjcount" /></td><td>试制奖金额:<input type="text" name="tijing.tjmoney" /></td></tr>
				<tr><td>奖金分配月份<input class="Wdate" type="text" name="tijing.tjmonth" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" /></td><td>备注：</td></tr>
				<tr><td colspan="2"><input type="submit" value="添加" />&nbsp;<input type="reset" value="取消" /></td></tr>
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
