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
		<script type="text/javascript" src="javascript/query-easyui-1.3.1/jquery-1.8.0.min.js">
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
			
			<div  align="center">
				<div id="successMsg" style="color: red"></div>
				<form id="quotationForm">
					<input type="hidden" name="quotationList.id" value="${quotationList.id}" />
					<table class="table"  style="border-collapse:collapse;" border="1">
						<tr>
							<th colspan="7" style="font-size: 24px">
								询 价 包
							</th>
						</tr>
						
						<tr>
							<th colspan="2"  align="center">
								零件代号  
							</th>
							<td align="center" >
								${quotationList.partNum}
							</td>
							<th>
								零件名称
							</th>
							<td align="center" >${quotationList.description}</td>
						</tr>
						<tr>
							<td align="center" ></td>
							<th align="center">
								项目
							</th>
							<th>
								金额（RMB）
							</th>
							<th colspan="3">
								定义及计算方法
							</th>
							<th>
								所占百分率（%）<br />占零件价比率
							</th>
						</tr>
						<tr>
							<th>1.1</th>
							<th>
								材料
							</th>
							<td align="center" >
								<font id="rawMaterialInput">${quotationList.rawMaterial}</font>
							</td>
							<th colspan="3">
								生产过程中构成产品实体的材料
							</th>
							<td align="center" >
								<font id="rawMaterial">
								</font>
							</td>
						</tr>
						<tr>
							<th>1.2</th>
							<th>
								外购外协
							</th>
							<td align="center" >
								<font id="subContractInput" >
									${quotationList.subContract}
								</font>
							</td>
							<th colspan="3">
								外包零件或服务
							</th>
							<td align="center" >
								<font id="subContract">
								</font>
							</td>
						</tr>
						<tr>
							<th>1</th>
							<th>
								采购成本
							</th>
							<td align="center" >
								<font id="purchasingCostInput">${quotationList.purchasingCost}</font>
							</td>
							<th colspan="3">
								材料费用+外购外协成本
							</th>
							<td align="center" >
								<font id="purchasingCost">
								</font>
							</td>
						</tr>
						<tr>
							<th>2</th>
							<th>
								模具成本
							</th>
							<td align="center" >
								<font id="toolingCostInput">
									${quotationList.toolingCost}
								</font>
							</td>
							<th colspan="3">
								专用工装模具/项目总产量
							</th>
							<td align="center" >
								<font id="toolingCost">
								</font>
							</td>
						</tr>
						<tr>
							<th>3.1</th>
							<th>
								直接人工
							</th>
							<td align="center" >
								<font id="directLaborCostInput">${quotationList.directLaborCost}</font>
							</td>
							<th colspan="3">
								直接从事生产的工人的工资
							</th>
							<td align="center" >
								<font id="directLaborCost">
								</font>
							</td>
						</tr>
						<tr>
							<th>3.2</th>
							<th>
								间接人工
							</th>
							<td align="center" >
								<font id="indirectLaborCostInput">${quotationList.indirectLaborCost}</font>
							</td>
							<th colspan="3">
								(20%-25% )*Direct Labor Cost<br />直接人工的20%-25%
							</th>
							<td align="center" >
								<font id="indirectLaborCost">
								
								</font>
							</td>
						</tr>
						<tr>
							<th>3.3</th>
							<th>
								能耗
							</th>
							<td align="center" >
								<font id="energyCostInput">${quotationList.energyCost}</font> 
							</td>
							<th colspan="3">
								功率*工时*千瓦时单位电价*0.4
							</th>
							<td align="center" >
								<font id="energyCost"></font>
							</td>
						</tr>
						<tr>
							<th>3.4</th>
							<th>
								设备折旧
							</th>
							<td align="center" >
								<font id="equipmentDepreciationInput">${quotationList.equipmentDepreciation}</font> 
							</td>
							<th colspan="3">
								与生产本公司产品相关的设备的折旧
							</th>
							<td align="center" >
								<font id="equipmentDepreciation"></font>
							</td>
						</tr>
						<tr>
							<th>3.5</th>
							<th>
								维护费用
							</th>
							<td align="center" >
								<font id="maintenanceCostInput">${quotationList.maintenanceCost}</font>
							</td>
							<th colspan="3">
								工装分摊费用和设备折旧的15%-20%
							</th>
							<td align="center" >
								<font id="maintenanceCost"></font>
							</td>
						</tr>
						<tr>
							<th>3</th>
							<th>
								小计
							</th>
							<td align="center" >
								<font id="subTotalInput">${quotationList.subTotal}</font>
							</td>
							<th colspan="3">
								制造成本
							</th>
							<td align="center" >
								<font id="subTotal"></font>
							</td>
						</tr>
						<tr>
							<th>4.1</th>
							<th>
								运输
							</th>
							<td align="center" >
								<font id="transportationInput">${quotationList.transportation}</font>
							</td>
							<th colspan="3">
							</th>
							<td align="center" >
								<font id="transportation"></font>
							</td>
						</tr>
						<tr>
							<th>4.2</th>
							<th>
								包装
							</th>
							<td align="center" >
								<font id="packInput" >${quotationList.pack}</font>
							</td>
							<th colspan="3">
							</th>
							<td align="center" >
								<font id="pack"></font>
							</td>
						</tr>
						<tr>
							<th>4</th>
							<th>
								物流
							</th>
							<td align="center" >
								<font id="logisticsInput" >${quotationList.logistics}</font>
							</td>
							<th colspan="3">
							</th>
							<td align="center" >
								<font id="logistics"></font>
							</td>
						</tr>
						<tr>
							<th>5</th>
							<th>
								管理费用
							</th>
							<td align="center" >
								<font id="freePriceInput" >${quotationList.freePrice}</font>
							</td>
							<th colspan="3">
								(10-15%)*(制造费用+模具成本)
							</th>
							<td align="center" >
								<font id="freePrice"></font>
							</td>
						</tr>
						<tr>
							<th>6</th>
							<th>
								财务费用
							</th>
							<td align="center" >
								<font id="finicialInterestInput" >${quotationList.finicialInterest}</font>
							</td>
							<th colspan="3">
								投资额(模具投资+第1批材料费用)*年利率/项目产量
							</th>
							<td align="center" >
								<font id="finicialInterest"></font>
							</td>
						</tr>
						<tr>
							<th>7</th>
							<th>
								利润
							</th>
							<td align="center" >
								<font id="oiInput" >${quotationList.oi}</font>
							</td>
							<th colspan="3">
							</th>
							<td align="center" >
								<font id="oi"></font>
							</td>
						</tr>
						<tr>
							<th>8</th>
							<th>
								零件单价
							</th>
							<td align="center" >
								<font id="totalPriceInput" >${quotationList.totalPrice}</font>
							</td>
							<th colspan="3">
							</th>
							<td align="center" >100%</td>
						</tr>
						<tr>
							<th>9</th>
							<th>销售价</th>
							<td align="center">${quotationList.sellingPrice}</td>
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
			
			function autoBaifenbi(){
				$('#rawMaterial').html(($('#rawMaterialInput').html() / $('#totalPriceInput').html() * 100 ).toFixed(2) + '%');
				$('#subContract').html(($('#subContractInput').html() / $('#totalPriceInput').html() * 100 ).toFixed(2) + '%');
				$('#purchasingCost').html(($('#purchasingCostInput').html() / $('#totalPriceInput').html() * 100 ).toFixed(2) + '%');
				$('#toolingCost').html(($('#toolingCostInput').html() / $('#totalPriceInput').html() * 100 ).toFixed(2) + '%');
				$('#directLaborCost').html(($('#directLaborCostInput').html() / $('#totalPriceInput').html() * 100).toFixed(2) + '%');
				$('#indirectLaborCost').html(($('#indirectLaborCostInput').html() / $('#totalPriceInput').html() * 100).toFixed(2) + '%');
				$('#energyCost').html(($('#energyCostInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#equipmentDepreciation').html(($('#equipmentDepreciationInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#maintenanceCost').html(($('#maintenanceCostInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#subTotal').html(($('#subTotalInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#freePrice').html(($('#freePriceInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#finicialInterest').html(($('#finicialInterestInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');

				$('#transportation').html(($('#transportationInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#pack').html(($('#packInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#logistics').html(($('#logisticsInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
				$('#oi').html(($('#oiInput').html() / $('#totalPriceInput').html()*100).toFixed(2) + '%');
			}
			
			$(function(){
				autoBaifenbi();
			});
			
		</SCRIPT>
	</body>
</html>
