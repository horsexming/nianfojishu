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
					输入工号领料
				</h1>
				<div>
					<form action="UsersAction!codelingliao.action?tag=${tag}"
						method="post" onsubmit="return onck()">
						<input type="hidden" name="pageStatus" value="${param.pageStatus}">
						请输入员工号:
						<input type="text" id="runCard" name="code"
							value="" />
						<input type="submit" id="tijiao"
							value="确认"/>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//初始选中
$(function() {
	$("#runCard").focus();
})
function onck(){
	document.getElementById("tijiao").disabled=true;
}
</script>
	</body>
</html>
