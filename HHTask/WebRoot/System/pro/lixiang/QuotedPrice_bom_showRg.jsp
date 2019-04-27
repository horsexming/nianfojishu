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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
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
			<div align="center" style="margin-top: 8px;">
				当您的数据全部录入完成后请点击————>
				<a
					href="ProjectManage_updateProTimeForFinal.action?id=${id}&pageStatus=${pageStatus}"
					onclick="return window.confirm('本操作将完成本次报价!确定数据都已经录入完毕?')"><font
					color="red" size="8px">提交</font> </a>
			</div>
			<hr />
			(
			<b>${projectTime.className}录入填报说明</b>: 填报部门:${projectTime.dept}
			截止时间:${projectTime.provisionTime})

			<div align="left">
				<div style="width: 100%">
					<iframe id="updateQpIframe"
						src="<%=basePath%>/System/pro/lixiang/QuotedPrice_dept_rg.jsp?id=${quotedPrice.id}"
						marginwidth="0" marginheight="0" hspace="0" vspace="0"
						frameborder="0" scrolling="auto"
						style="width: 100%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
