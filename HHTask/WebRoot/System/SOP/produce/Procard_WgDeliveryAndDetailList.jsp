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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center"><h3>共<s:property value="list.size()"/>张送货单</h3></div>
				<s:iterator value="list" id="shdId" status="pstatus">
				<h3 style="border-color: red">-----------------------------------------------------------第${pstatus.index+1}张------------------------------------------------------------</h3>
					<iframe id="showProcess" src="WaigouwaiweiPlanAction!findDeliveryNoteDetail.action?id=${shdId}&pageStatus=sh" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</s:iterator>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
