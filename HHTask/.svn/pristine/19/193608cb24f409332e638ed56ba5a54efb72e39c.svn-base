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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<STYLE type="text/css">
.input_a {
	width: 50px;
}

.input_b {
	width: 110px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; font-family: 黑体;"
			align="center">
			<h1>
				系统评估结果
			</h1>
			<h2>
				每年管理成本:
				<font color="red">${isList[0]}万元</font>
				<br />
				使用PEBS系统后
				<br />
				每年管理成本:
				<font color="red">${isList[1]}万元</font>
				<br />
				每年节省:
				<font color="red">${isList[2]}万元</font>
				<br />
				<a
					href="IntelligentDiagnosisAction_findisByid.action?id=${id}&status=mingxi">评估明细</a>
				<br />
				
				联系人:<s:if test="user!=null">
					${user.name} ${user.password.phoneNumber}
					</s:if>
					<s:elseif test="qrcodeku!=null">
					${qrcodeku.userName} ${qrcodeku.phoneNumber}
					</s:elseif>
			</h2>
		</div>
		<%@include file="/util/foot.jsp"%>

	</body>
</html>
