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
				url: "tclaimform_debit.action",
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
			
			<form id="myform">
				<input type="hidden" name="claimform.id" value="${claimform.id}" />
				<table class="table" style="width: 50%">
					<tr  bgcolor="#c0dcf2" height="50px">
						<th colspan="4">扣款项</th>
					</tr>
					<tr>
						<td>扣款金额</td>
						<td> <input name="claimform.debitAmount"/> </td>
						<td>捐款时间</td>
						<td> <input readonly="readonly" class="Wdate" onClick="WdatePicker()" name="claimform.debitDate"/> </td>
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
