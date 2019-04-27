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
				<form action="productPriceAction!updateProductPrice.action" method="post">
					<input type="hidden" name="productPrice.id" value="${productPrice.id }" />
					<table>
						<tr>
						<td>总成件号：<input type="text" name="productPrice.markId" value="${productPrice.markId }" /></td>
						<td>产品名称：<input type="text" name="productPrice.goodsName" value="${productPrice.goodsName }" /></td>
						</tr>
						<tr>
						<td>直接人工：<input type="text" name="productPrice.laborcost" value="${productPrice.laborcost }" /></td>
						<td>可调系数：<input type="text" name="productPrice.fenpeiRate" value="${productPrice.fenpeiRate }"/></td>
						
						</tr>						
						<tr>
						<td>日产量：<input type="text" name="productPrice.dailyoutput" value="${productPrice.dailyoutput }" /></td>
						<td>产品型别：<input type="text" name="productPrice.style" value="${productPrice.style }" /></td>
						
						</tr>
						<tr>
						<td>车型：<input type="text" name="productPrice.carStyle" value="${productPrice.carStyle }" /></td>
						<td>备注：<input type="text" name="productPrice.more" value="${productPrice.more }" /></td>
						</tr>
						<tr>
						<td colspan="2" align="center">
						<input type="submit" value="更改" />&nbsp;
						<input type="reset" value="取消">
						
						</td></tr>
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
