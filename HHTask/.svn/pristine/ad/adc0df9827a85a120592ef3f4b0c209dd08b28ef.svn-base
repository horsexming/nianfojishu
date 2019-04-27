<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
<script type="text/javascript">
	$(function(){
		$('#submitBtn').bind('click', function(){
			$.ajax({
				type: "POST",
				url: "tclaimform_notification.action",
				data: $('#myform').serialize(),
				dataType: "json",
				success: function(msg){
					alert(msg.message);
				}
			});
		});
	})
</script>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<table id="mytable" width="100%" border="0" style="border-collapse: collapse;" class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<th align="center">生产日期</th>
					<th align="center">件号</th>
					<th align="center">名称</th>
					<th align="center">数量</th>
					<th align="center">分析</th>
					<th align="center">分析人</th>
					<th align="center">整改文件</th>
					<th align="center">状态</th>
				</tr>
				<s:iterator value="tclaimsRecords" id="r">
					<tr onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						<td align="center">${r.productionDate}</td>
						<td align="center">${r.partNumber}</td>
						<td align="center">${r.name}</td>
						<td align="center">${r.quantity}</td>
						<td align="center">${r.reason}</td>
						<td align="center">${r.reasonPerson}</td>
						<td align="center">
							<s:if test="reasonFilename != null">
<%--								<a href="DownAction.action?fileName=${r.reasonFilename}&amp;directory=/upload/file/tclaimsrecord/">下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/tclaimsrecord/${r.reasonFilename}&amp">下载</a>
							</s:if><s:else>
							</s:else>
						</td>
						<td align="center">${r.status}</td>
					</tr>
				</s:iterator>
			</table>
			
			<form id="myform">
				<input type="hidden" name="claimform.id" value="${tclaimsRecord.root.id}" />
				<table class="table" style="width: 70%">
					<tr>
						<td>被通知人</td>
						<td> <input name="claimform.notification"/> </td>
						<td>通知时间</td>
						<td> <input readonly="readonly" class="Wdate" onClick="WdatePicker()" name="claimform.notificationDate"/> </td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" id="submitBtn" value="提交" />
						</td>
					</tr>
				</table>
			
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</body>
</html>
