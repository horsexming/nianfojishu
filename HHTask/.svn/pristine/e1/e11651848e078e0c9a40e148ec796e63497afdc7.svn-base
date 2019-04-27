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
					<form id="form1" method="post">
						<input type="hidden" name="toolingCost.id" value="${toolingCost.id}">
						<input type="hidden" name="quotation.id" value="${quotation.id}" >
						<table class="table" style="width: 60%" >
							<tr>
								<th colspan="4" >添加模具成本</th>
							</tr>
							<tr>
								<th align="right">名称:</th>
								<td><input name="toolingCost.name" value="${toolingCost.name}" title="请填写模具名称" /></td>
								<th>备注</th>
							</tr>
							<tr>
								<th align="right">数量:</th>
								<td><input name="toolingCost.quantity" value="${toolingCost.quantity}" title="请填写数量" /></td>
								<td rowspan="4">
									<textarea name="toolingCost.note" rows="8" cols="22">${toolingCost.note}</textarea>
								</td>
							</tr>
							<tr>
								<th align="right">单价(RMB):</th>
								<td><input name="toolingCost.price" value="${toolingCost.price}" title="请填写单价" /></td>
							</tr>
							<tr>
								<th align="right">项目产量:</th>
								<td><input name="toolingCost.lifeTimeVolume" value="${toolingCost.lifeTimeVolume}" title="请填写项目产量" /></td>
							</tr>
							<tr>
								<th align="right">模具类型:</th>
								<td>
									<select name="toolingCost.mouldType">
										<s:if test="#toolingCost.mouldType('自制件')">
											<option value="自制件" selected="selected" >自制件</option>
											<option value="外购件">外购件</option>
										</s:if><s:else>
											<option value="自制件" >自制件</option>
											<option value="外购件" selected="selected">外购件</option>
										</s:else>
										
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input id="submitBtn1" type="button" value="确定" />
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
					url: "ProjectQuotationToolingCost_update.action",
					data: $("#form1").serialize(),
					success: function(msg){
						alert( "修改成功!");
					}
				});
			});
		</SCRIPT>
	</body>
</html>
