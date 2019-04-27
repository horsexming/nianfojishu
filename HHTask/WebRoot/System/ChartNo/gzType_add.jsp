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
				<form action="ChartNOSQAction_addgzType.action" method="post">
					<b>
						规则类型:
					</b>
					<input type="text" value="" name="gzType.type" onchange="keyiadd(this)"/><br/>
					<b>
						组别:
					</b>
					<input type="text" value="" name="gzType.groups" /><br/>
					<input type="submit" value="添加" id="sub" class="input" onclick="todisabled(this)" disabled="disabled"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">

$(document).ready(function() {
	var rebeack = '${errorMessage}';
	if (rebeack == "true") {
		alert('添加成功!');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
	
	function keyiadd(obj){
		if(obj!=null && obj.value!=''){
			$("#sub").removeAttr("disabled");
		}else{
			$("#sub").attr("disabled","disabled");
		}
	}
	
	</SCRIPT>
	</body>
</html>
