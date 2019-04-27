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
								<th colspan="4">添加外购件</th>
							</tr>
							<tr>
								<th align="right">名称:</th>
								<td><input name="subContract.name" title="请填写外购件名称"/></td>
								<th align="right">件号:</th>
								<td><input name="subContract.partNumber" title="请填写零件编号"/></td>
							</tr>
							<tr>
								<th align="right">次级供应商:</th>
								<td><input name="subContract.subSupplier" title="请填写次级供应商"/></td>
								<th align="right">客户指定:</th>
								<td><input name="subContract.directed" title="请填写客户指定"/></td>
							</tr>
							<tr>
								<th align="right">单价:</th>
								<td><input name="subContract.unitPrice" title="请填写单价(RMB)"/></td>
								<th align="right">零部件成本:</th>
								<td><input name="subContract.cost" title="请填写零部件成本(RMB)"/></td>
							</tr>
							<tr>
								<th align="right">定额:</th>
								<td><input name="subContract.quantity" title="请填写定额"/></td>
								<th align="right">所属报价单:</th>
								<td>
									<input type="hidden" name="quotation.id" value="${quotation.id}" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="button" id="submitBtn1" value="确定" >
									<input type="reset" value="清空" >
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
		
		<SCRIPT type="text/javascript">
			$("#submitBtn1").bind("click", function(){
				$.ajax({
					type: "POST",
					url: "ProjectQuotationSubContract_add.action",
					data: $("#form1").serialize(),
					dataType: 'json',
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
