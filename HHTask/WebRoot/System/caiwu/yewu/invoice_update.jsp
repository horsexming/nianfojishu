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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
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
			<form action="business_updateInvoice.action" method="post">
				<table>
					<tr><th align="center" colspan="2">修改发票</th></tr>
					<tr><td align="center">发票号码</td><td><input type="text" value="${invoice.number }" name="invoice.number"/></td></tr>
					<tr><td align="center">收款单位</td><td><input type="text" value="${invoice.collectionUnit }" name="invoice.collectionUnit"/></td></tr>
					<tr><td align="center">费用金额</td><td><input type="text" value="${invoice.money }" name="invoice.money"/>
					<select name="invoice.currencyType">
					<option value="元" selected="selected">元</option>
					<option value="美金">美金</option>
					</select></td></tr>
					<input type="hidden" value="${buId }" name="id"/>
					<input type="hidden" value="${invoice.id }" name="invoice.id"/>
					<input type="hidden" value="${invoice.file }" name="invoice.file"/>
					<tr><td><input type="submit" value="修改"/></td></tr>
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
