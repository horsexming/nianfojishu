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
		<script type="text/javascript">
			$(function(){
				$('#submitBtn').bind('click', function(){
					$.ajax({
						type: "POST",
						url: "ProjectQuotationRawMaterial_add.action",
						data: $('#addForm').serialize(),
						dataType: 'json',
						success: function(msg){
							$('#successMsg').html(msg.message);
						}
					});
				});
			});
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
				<form id="addForm" action="ProjectQuotationRawMaterial_add.action" method="post" >
					<table class="table">
						<tr>
							<th colspan="6" >添加材料</th>
						</tr>
						<tr>
							<th align="right">原材料牌号:</th>
							<td><input name="rawMaterial.materialGrade" title="请填写原材料牌号" /></td>
							<th align="right">类型:</th>
							<td>
								<select name="rawMaterial.rmType">
									<option value="板料">板料</option>
									<option value="管料">管料</option>
								</select>  
							</td>
							<th align="right">密度:</th>
							<td> <input name="rawMaterial.density" title="请填写密度"  /></td>
						</tr>
						<tr>
							<th align="right">原材料单价:</th>
							<td><input name="rawMaterial.price" title="r请填写原材料单价(RMB/Kg)" /></td>
							<th align="right">废料单价:</th>
							<td><input name="rawMaterial.scrapPrice" title="请填写废料单价(RMB/Kg)" /></td>
							<th align="right">厚度及公差:</th>
							<td><input name="rawMaterial.thickness" title="请填写废料单价(RMB/Kg)" /></td>
						</tr>
						<tr>
							<th align="right">材料长:</th>
							<td><input name="rawMaterial.unitLength" title="请填写材料长(mm)" /></td>
							<th align="right">材料宽:</th>
							<td><input name="rawMaterial.unitWidth" title="请填写材料宽(mm)" /></td>
							<th align="right">零件净重:</th>
							<td><input name="rawMaterial.netWeight" title="请填写零件净重(Kg)" /></td>
						</tr>
						<tr>
							<th align="right">内部废品率:</th>
							<td><input name="rawMaterial.nonQuality" title="请填写内部废品率" /></td>
							<th align="right">板材损耗:</th>
							<td><input name="rawMaterial.coilScrap" title="请填写板材损耗" /></td>
							<th align="right">所属报价单:</th>
							<td><input name="rawMaterial.root.id" readonly="readonly" value="${quotation.id}"/>  </td>
						</tr>
						<tr>
							<th>钢厂:</th>
							<td> <input name="rawMaterial.maker" /> </td>
							<th>国产/进口:</th>
							<td><input name="rawMaterial.local" /></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input id="submitBtn" type="button" value="确定">
								<input type="reset">
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
