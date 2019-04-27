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
		<div id="gongneng" style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
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
				<div><font color="red">${successMessage}${errorMessage} </font></div>
				<form action="Product_list.action" method="post">
						输入月份：<input id="month" style="width: 155px" class="Wdate" type="text" name="month" 	onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" /> <input type="submit" value="查询">
				</form>
				<table class="table">
					<tr>
						<td>序号</td>
						<td>名称</td>
						<td>件号</td>
						<td>规格</td>
						<td>数量</td>
						<td>单位</td>
						<td>月份</td>
						<td>类别</td>
						<td>说明</td>
						<td>采购</td>
					</tr>
					<s:iterator value="products" id="p" status="st">
						<tr>
							<td>${st.index+1}</td>
							<td>${p.name}</td>
							<td>${p.partsNumber}</td>
							<td>${p.specification}</td>
							<td>${p.quantity}</td>
							<td>${p.unit}</td>
							<td>${p.month}</td>
							<td>${p.type}</td>
							<td>${p.directions}</td>
							<td> <a href="Flow_listInput.action?product.id=${p.id}">采购</a> </td>
						</tr>
					</s:iterator>
				</table>
			<br>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
