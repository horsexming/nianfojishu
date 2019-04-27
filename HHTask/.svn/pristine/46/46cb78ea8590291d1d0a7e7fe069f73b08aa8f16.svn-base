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
	<body onload="enter()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="goodsAction!outGoodsByBts.action" method="post">
					<p>
						<span><font style="font-size: 20px; font-weight: bold">请扫描条码：</font>
						</span>
						<input id="cardNum" name="tag" style="width: 200px; height: 45px;"
							value="${tag}" />
						<input type="submit" value="确定" style="width: 80px; height: 45px;" />
						<input type="button" value="扫描"  style="width: 80px; height: 45px; display: none;" 
							onclick="getcheckList2()"		id="saomiao" />
					</p>
				</form>
				<s:if test="errorMessage=='true'">
					<font color="green">扫码出库完成!</font>
				</s:if>
				<s:else>
					<font color="red">${errorMessage}</font>
				</s:else>
			</div>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function enter() {
	document.getElementById("cardNum").focus();
	document.getElementById("cardNum").select();
}
$(function() {
	if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		$("#saomiao").show();
	}
})
	
	
function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	$("#cardNum").val(tm);
}

</script>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
