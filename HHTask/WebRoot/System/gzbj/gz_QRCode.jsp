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
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/css/button.css" />
		<STYLE type="text/css">
.button:disabled {
	border: 1px solid #DDD;
	background-color: #F5F5F5;
	color: #ACA899;
	pointer-events: none;
}

@media print {
	#noprint {
		display: none;
	}
	.noprint {
		display: none;
	}
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div>
					<div class="noprint">
						<h2>
							设置尺寸
						</h2>
						<select name="" id="size" onchange="setSize()">
							<option value="100x100">100x100</option>
							<option value="75x75">75x75</option>
							<option value="50x50">50x50</option>
							<option value="25x25">25x25</option>
						</select>
					</div>
					<div id="ercode">
					</div>
					<p>
						${param.no}
					</p>
				</div>


				<input type="button" id="noprint" value="打印" class="button blue"
					onclick="window.print();">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	getQRCode(100,100,'<%=basePath%>GzstoreAction_findAll.action?id=${param.id}','ercode');
})
function setSize(){
	$("#ercode").empty();
	var size =	$("#size").val();
	var strs = size.split("x");
	getQRCode(strs[0],strs[1],'<%=basePath%>GzstoreAction_findAll.action?id=${param.id}','ercode');
}
</SCRIPT>
	</body>
</html>
