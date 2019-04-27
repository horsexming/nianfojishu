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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${procard.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"||procardStyle=="自制"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h1>
					刷卡领料
				</h1>
				<div>
					<form action="ProcardAction!findProcardByCardNum.action"
						target="_lingliao" method="post" id="myform">
						<input type="hidden" name="pageStatus" value="${param.pageStatus}">
						请刷流水卡片(扫描报废单条码)/员工卡:
						<input type="text" id="runCard" name="cardNumber"
							value="${cardNumber}" />
						<input type="button" value="扫描"  style="width: 80px; height: 45px; display: none;" 
							onclick="getcheckList2()"		id="saomiao" />
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//初始选中
$(function() {
	$("#runCard").focus();
})
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
	$("#runCard").val(tm);
	$("#myform").submit();
}

</script>
	</body>
</html>
