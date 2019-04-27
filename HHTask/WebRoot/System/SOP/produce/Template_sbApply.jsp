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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				物料设变申请
				<table class="table">
					<tr>
					<th>件号</th>
					<th>类型</th>
					<th>版本号</th>
					<th>版本序号</th>
					<th>设变理由</th>
					</tr>
					<tr>
						<td align="center">${procardTemplate.markId}</td>
						<td align="center">${procardTemplate.procardStyle}</td>
						<td align="center">${procardTemplate.banBenNumber}</td>
						<td align="center">
						<s:if test="procardTemplate.banci==null">0</s:if>
						<s:else>${procardTemplate.banci}
						</s:else>
						</td>
						<td align="center">${procardTemplate}</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
