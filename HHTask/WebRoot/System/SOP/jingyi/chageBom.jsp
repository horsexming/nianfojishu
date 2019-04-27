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
				<div align="center">
					请选择要计算的BOM
					<br />
					<br />
				</div>
				<div>
					1、
					<a href="" style="margin-right: 40px;">报价BOM</a> 2、
					<a
						href="ProcardTemplateAction!findAllProcardTem.action?pageStatus=jy">LP(批产)BOM</a>
				</div>
				<%--
				<div >
					<div
						style="width: 100px; height: 100px; border: solid 1px #000000; float: left; margin-right: 40px;">
						报价BOM
					</div>
					<div
						style="width: 100px; height: 100px; border: solid 1px #000000; float: left;">
						LP(批产)BOM
					</div>
				</div>
			--%>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
