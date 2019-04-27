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
				<br />
				<div style="font-weight: bolder; font-size: 20;margin-top: 160px;">
					正在为您准备订单${om.orderNum}的成本统计与分布数据,请耐心等待。。。。。。
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		$(document).ready(function() {
	$.ajax( {
		type : "post",
		url : "orderManager_toOrderChengben.action",
		dataType : "json",
		data : {
			id : ${om.id}
		},
		success : function(data) {
			if(data){
				window.location.href="orderManager_orderChengben.action?id=${om.id}";
			}else{
				alert("对不起准备失败!请联系管理员!");
			}
		}
	});
});
					
		</SCRIPT>
	</body>
</html>
