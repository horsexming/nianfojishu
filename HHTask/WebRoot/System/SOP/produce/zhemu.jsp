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
			<div align="left" style="font-size: large;">
				<div id="showMess" style="color: red;">
					正在确认委外订单中,请稍候<font id="showMess1_font"></font>
				</div>
				<div id="showMess2" style="color: red; ">
					正在生成物料计划中,请稍候<font id="showMess2_font"></font>
				</div>
				<div id="showMess3" style="color: red;">
					正在激活生产任务中,请稍候<font id="showMess3_font"></font>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var str = '';
function test(){
	if(str.length>=8){
		str='';
	}else{
		str+='。';	
	}
	$("#showMess1_font").html(str);
	$("#showMess2_font").html(str);
	$("#showMess3_font").html(str);
}

setInterval("test()", 400);
</SCRIPT>
	</body>
</html>
