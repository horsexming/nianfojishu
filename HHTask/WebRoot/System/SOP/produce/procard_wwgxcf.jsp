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
				<div align="center">件号：${pwwApplyDetail.markId}&nbsp;&nbsp;批次件号：${pwwApplyDetail.selfCard}&nbsp;&nbsp;数量：${pwwApplyDetail.applyCount}&nbsp;&nbsp;
				</div>
				<div>
					拆分成<input id="cfCount" value="2" onblur="changecfcount();">
				</div>
				<form action="ProcardAction!wwgxcf.action" method="post" onsubmit="return changecfcount2();">
					<input type="hidden" value="pwwApplyDetail.id" name="pwwApplyDetail.id">
					<div id="cfdetailDiv">
						<input class="cf" name="selected" value="${pwwApplyDetail.applyCount}" style="width: 20px;" >&nbsp;&nbsp;
						<input class="cf" name="selected" value="0" style="width: 20px;">
					</div>
					<input type="submit">
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function changecfcount(){
	if(mustBeNumber("cfCount")){
		var cfCount = $("#cfCount").val();
		var html = "";
		for(var i =0;i<cfCount ;cfCount++){
			if(i==0){
				html +="<input class='cf' name='selected' value='${pwwApplyDetail.applyCount}' style='width: 20px;' >&nbsp;&nbsp";
			}else{
				html +="<input class='cf' name='selected' value='0' style='width: 20px;' >&nbsp;&nbsp";
			}
		}
		$("cfdetailDiv").empty();
		$("cfdetailDiv").html(html);
	}
}
function changecfcount2(){
	var tatalCount=0;
	$(".cf").each(function(){
		tatalCount =(tatalCount-0)+this.value;
	})
	var applyCount = ${pwwApplyDetail.applyCount};
	if((tatalCount-applyCount)==0){
		return "false";
	}else{
		alert("分拆总额不对,请重新填写");
	}
}
</SCRIPT>
	</body>
</html>
