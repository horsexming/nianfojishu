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
				<h3>生产件设变新添采购</h3>
				<div align="center">
				<form action="procardTemplateGyAction_addsbcgTowlxq.action" method="post">
					<input type="hidden" value="${cpage}" name="cpage">
					<input type="hidden" value="${procardsbwg.id}" name="procardsbwg.id">
					<table class="table">
					<tr>
						<th>件号</th><td>${procardsbwg.markId}</td>
					</tr>
					<tr>
						<th>名称</th><td>${procardsbwg.proName}</td>
					</tr>
					<tr>
						<th>版本号</th><td>${procardsbwg.banbenNumber}</td>
					</tr>
					<tr>
						<th>供料属性</th><td>${procardsbwg.kgliao}</td>
					</tr>
					<tr>
						<th>数量</th>
						<td>
						<input id="clCount" onkeyup="mustBeNumber('clCount')" name="procardsbwg.clCount" value="${procardsbwg.addCount}" >
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
					<td colspan="2" align="center">
					<input value="加入物料需求" type="submit">
					</td>
					</tr>
					</table>
				</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
