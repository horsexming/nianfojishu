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
		$("#submitBtn").bind('click',function(){
			$.ajax({
				type: "POST",
				url: "tclaimform_update.action",
				data: $('#myform').serialize(),
				dataType:"json",
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
			<form id="myform" action="tclaimform_update.action" method="post">
				<table class="table" style="width: 70%">
					<tr>
						<th colspan="4">索赔单</th>
					</tr>
					<tr>
						<th>已方负责人</th>
						<td><input type="hidden" name="claimform.id" value="${claimform.id}"/> <input name="claimform.ourPerson" value="${claimform.ourPerson}"/> </td>
						<th>对方单位</th>
						<td> <input name="claimform.otherCompany" value="${claimform.otherCompany}"/> </td>
					</tr>
					<tr>
						<th>登记时间</th>
						<td> <input name="claimform.regDate" value="${claimform.regDate}" readonly="readonly" class="Wdate" onClick="WdatePicker()"/> </td>
						<th>索赔金额</th>
						<td> <input name="claimform.claimAmount" value="${claimform.claimAmount}"/> </td>
					</tr>
					<tr>
						<th>索赔负责人</th>
						<td> <input name="claimform.otherPerson" value="${claimform.otherPerson}"/> </td>
						<th>联系电话</th>
						<td> <input name="claimform.otherPhone" value="${claimform.otherPhone}"/> </td>
					</tr>
					<tr>
						<th colspan="4">
							<input id="submitBtn" type="button" value="确定" />
						</th>
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
