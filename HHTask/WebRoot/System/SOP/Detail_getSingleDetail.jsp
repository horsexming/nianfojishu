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
					<a href="Detail_listInput.action" style="color: #ffffff">返回</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table"  >
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>件号</th>
						<th>规格</th>
						<th>数量</th>
						<th>单位</th>
						<th>类别</th>
					</tr>
					<s:iterator value="datas" id="data" status="st">
						<tr>
							<td>${st.index+1}</td>
							<td>${data.value.name}</td>
							<td>${data.value.partsNumber}</td>
							<td>${data.value.specification}</td>
							<td>${data.value.advPosition}</td>
							<td>${data.value.unit}</td>
							<td>${data.value.category}</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
