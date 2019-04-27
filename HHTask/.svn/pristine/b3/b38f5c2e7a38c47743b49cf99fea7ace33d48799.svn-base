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
		<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right"> 
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="successMsg" style="color: red" ></div>
				<form id="form1"  method="post" >
					<table class="table" style="width: 60%">
						<tr>
							<th colspan="4">
								添加直接人工
							</th>
						</tr>
						<tr>
							<th align="right">工序名称:</th>
							<td><input name="directLaborCost.process" title="请填写工序名称" /></td>
							<th align="right">设备名称:</th>
							<td><input name="directLaborCost.equipment" title="请填写设备名称" /></td>
						</tr>
						<tr>
							<th align="right">功率(K/W):</th>
							<td><input name="directLaborCost.power" title="请填写功率(K/W)" /></td>
							<th align="right">班产定额(PC):</th>
							<td><input name="directLaborCost.capacity" title="请填写班产定额(PC)" /></td>
						</tr>
						<tr>
							<th align="right">操作员:</th>
							<td><input name="directLaborCost.operator" title="请填写操作员" /></td>
							<th align="right">人工单价(RMB/H):</th>
							<td><input name="directLaborCost.laborPrice" title="请填写人工单价(RMB/H)" /></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" id="submitBtn1" value="确定" >
								<input type="reset" value="清空" >
							</td>
						</tr>
					</table>
					<input type="hidden" name="quotation.id" value="${quotation.id}" />
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
		<SCRIPT type="text/javascript">
			$("#submitBtn1").bind("click", function(){
				$.ajax({
					type: "POST",
					url: "ProjectQuotationDirectLaborCost_add.action",
					data: $("#form1").serialize(),
					dataType:'json',
					success: function(msg){
						if(msg.success){
							$('#form1').get(0).reset();
						}
						$('#successMsg').html(msg.message);
					}
				});
			});
		</SCRIPT>
	</body>
</html>
