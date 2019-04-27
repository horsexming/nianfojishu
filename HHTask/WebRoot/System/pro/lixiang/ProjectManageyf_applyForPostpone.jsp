<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<BODY>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					申请延期
				</h3>
			</div>
			<form action="${pageContext.request.contextPath}/projectPoolAction_submitForPostpone.action" id="form" method="post">
				<input type="hidden" name="projectManageyf.id" value="${projectManageyf.id}"/>
				<input type="hidden" name="pageStatus" value="${pageStatus}" />
				<table class="table" >
					<tr>
						<th align="right" style="width: 50%">延期时间</th>
						<td align="left" style="width: 50%"><input type="text" name="projectManageyf.outTime" id="outTime"
						 value="${projectManageyf.outTime}" onkeyup="mustBeNumber('outTime')"/>(天)</td>
					</tr>
					<tr>
						
						<th align="right">延期说明</th>
						<td align="left"><textarea rows="3" cols="30" name="projectManageyf.outExplain">${projectManageyf.outExplain}</textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="提交" style="width:80px;height:40px;" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重置" style="width:80px;height:40px;" />
						</td>
					</tr>
				</table>
			</form>
			
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	
<script type="text/javascript">
	$(function(){
		$("#form").submit(function(){
			var outTime = $("#outTime").val();
			if(null==outTime || 0==outTime){
				alert("请输入延期时间");
				$("#outTime").focus();
				return false;
			}
		});
	});



</script>
	</BODY>
</HTML>