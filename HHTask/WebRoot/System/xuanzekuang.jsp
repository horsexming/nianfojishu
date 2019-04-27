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
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/css/button.css" />
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="xuanzekuang">
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	var content =	$('#content', parent.document).val();
	var buutonstr1 = $('#buutonstr1', parent.document).val();
	var buutonstr2 = $('#buutonstr2', parent.document).val();
	var buutonstr3 = $('#buutonstr3', parent.document).val();
	$("#xuanzekuang").append('<span>'+content+'</span>' +
	'<br/><input type="button" value='+buutonstr1+' class="button0 blue" onclick="clik0(this)">' +
	' <input type="button" value='+buutonstr2+' class="button0 blue" onclick="clik0(this)">' +
	' <input type="button" value='+buutonstr3+' class="button0 blue" onclick="clik0(this)">');
})

function clik0(obj){
	var  str = obj.value;
	 if("合格"==str){
		parent.qrzj($("#zhi_id",parent.document).val());
		$("#fullbg1",parent.document).hide();
		$("#dialog1",parent.document).hide();
	}else if("不合格"==str){
		parent.sqbljy($("#zhi_id",parent.document).val(),$("#zhi_markId",parent.document).val(),$("#zhi_lotId",parent.document).val());
		$("#fullbg1",parent.document).hide();
		$("#dialog1",parent.document).hide();
	}else if("取消"==str){
		$("#fullbg1",parent.document).hide();
		$("#dialog1",parent.document).hide();
	}
}
</SCRIPT>
	</body>
</html>
