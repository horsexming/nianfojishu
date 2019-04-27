<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
						<a href="FunctionAction!findFunctionById.action?id=${function.id}"
							style="color: #ffffff">添加功能</a>
					</div>
				</div>
				
				<div align="center">
					<table align="center" style="width: 30%">
						<tr>
							<td align="center" colspan="2">
								<font color="red" size="5">请选择订单类型</font>
							</td>
						</tr>
						<tr>
							<td>
								<form action="OrderManagementAction!saveqian.action"
									method="post">
									<input type="submit" value="实  制"
										style="width: 100px; height: 80px;" />
								</form>
							</td>

							<td align="right">
								<form action="" method="post">
									<input type="submit" value="批  产"
										style="width: 100px; height: 80px;" />
								</form>
							</td>
						</tr>
					</table>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
