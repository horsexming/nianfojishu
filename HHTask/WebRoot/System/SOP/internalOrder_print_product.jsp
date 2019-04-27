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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
	</head>
	<body>
		<div id="printDiv">
				<h3 align="center">${title}</h3>
				<div align="left">计划编号: ${orderNum}</div>
				<table width="100%" border="1" style="border-collapse: collapse;">
					<tr>
						<td align="center">件号</td>
						<td align="center">产品名称</td>
						<td align="center">分配数量</td>
						<td align="center">入库数量</td>
						<td align="center">备注</td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
									<tr>
									<td align="center">${pageList.pieceNumber }</td>
									<td align="center">${pageList.name}</td>
									<td align="center">${pageList.num}</td>
									<td align="center">${pageList.quantityCompletion}</td>
									<td align="center">${pageList.remark }</td>
									</tr>
					</s:iterator>
				</table>
		</div>
			<br>
			<input type="button" value="打印" onclick="pagePrint('printDiv','yes')"/>
	</body>
</html>
