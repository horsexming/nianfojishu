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
				<h2>更新送货清单</h2>
				<form action="HKSellStaAction!updateHKSellSta.action" method="post">
					<input type="hidden" name="tahkSellSta.id" value="${tahkSellSta.id }">
					<input type="hidden" name="cpage" value="${cpage }">
					<table>
						<tr>
							<th>客户：</th><td><input type="text" name="tahkSellSta.hkSellCumpanyName" value="${tahkSellSta.hkSellCumpanyName }" /></td>
							<th>件号：</th><td><input type="text" readonly="readonly" name="tahkSellSta.hkSellMarkId" value="${tahkSellSta.hkSellMarkId }" /></td>
						</tr>
						<tr>
							<th>送货单号：</th><td><input type="text" name="tahkSellSta.hkSellSendId" value="${tahkSellSta.hkSellSendId }" /></td>
							<th>数量：</th><td><input type="text" name="tahkSellSta.hkSellCount" value="${tahkSellSta.hkSellCount }" /></td>
						</tr>
						<tr>
							<th colspan="2">
							<input type="submit" value="更改" />&nbsp;&nbsp;
							<input type="reset" value="取消" />
							</th>
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
