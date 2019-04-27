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
<style type="text/css">
#kuwei_div{
	width: 33.3%;
	float: left;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br/>
				<br/>
				<br/>
				<br/>
				<div id="kuwei_div" align="center">
					
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	$(function(){
			$.ajax( {
				type : "POST",
				url : "WaigouwaiweiPlanAction!findWNBywaigouddId.action",
				data : {
					id : '${param.id}',
					pageStatus : "待确认"
				},
				dataType : "json",
				success : function(data) {
					if (data != null) {
						$(data)
								.each(
										function() {
											$("#kuwei_div")
													.append(
															'<div onclick="getsendTow('+this.kuweiId+',${param.id})" style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;float: left; ">  入库码  <br /> 扫描 </div>');
										});
					}
				}
			});
		
	})
	
function getsendTow(){
	$.ajax( {
				type : "POST",
				url : "WaigouwaiweiPlanAction!findWNBywaigouddId.action",
				data : {
					id : '${param.id}'
				},
				dataType : "json",
				success : function(data) {
					if(data!=null){
						if(data.success){
							getcheckList2();
						}else {
						alert(data.message);
					}
					}
				}
			});
		
}
	
	
	function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	window.location.href = "WaigouwaiweiPlanAction!upRuKuBacode.action?bacode=" + tm;
}
	
	</SCRIPT>
		
	</body>
</html>
