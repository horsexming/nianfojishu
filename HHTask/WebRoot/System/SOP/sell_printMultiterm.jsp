<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
		<center>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
					<div id="printDiv">
						<table class="table">
							<tr>
								<th width="40%" >
									<img src="${pageContext.request.contextPath}/${companyInfo.logoOKjpg}" width="35px" height="35px" />
									${companyInfo.name}
								</th>
								<th width="59%" 
									style="font-size: 16; color: red; font-weight: bolder; text-align: left">
									多条出库单
								</th>
							</tr>
							<tr>
								<th>所属仓库</th>
								<td width="">${sell.sellWarehouse}</td>
<%--								<th></th>--%>
<%--								<td></td>--%>
							</tr>
							
						</table>
						<table class="table">
							<tr>
								<th>件号</th>
								<th>仓区</th>
								<th>库位</th>
								<th>品名</th>
								<th>数量</th>
								<th>单位</th>
								<th>规格</th>
								<th>供料属性</th>
								<th>物料类别</th>
								<th>版本</th>
								<th>批次</th>
								<th>转换数量</th>
								<th>转换单位</th>
							</tr>
							<s:iterator value="sellList" id="sells" status="pre">
								<tr>
									<td>${sells.sellMarkId}
										<input type="hidden" value="${sells.sellId}" id="sellId_${pre.index}"/>
									</td>
									<td>${sells.goodHouseName}</td>
									<td>${sells.kuwei}</td>
									<td>${sells.sellGoods}</td>
									<td>${sells.sellCount}</td>
									<td>${sells.sellUnit}</td>
									<td>${sells.sellFormat}</td>
									<td>${sells.kgliao}</td>
									<td>${sells.wgType}</td>
									<td>${sells.banBenNumber}</td>
									<td>${sells.sellLot}</td>
									<td>${sells.sellZhishu}</td>
									<td>${sells.goodsStoreZHUnit}</td>
								</tr>							
							</s:iterator>
						</table>
						<input type="hidden" value="${fn:length(list)}" id="sellListSize"/>
						<table class="table">
							<tr>
								<th>出库日期</th>
								<td>${sell.sellDate}</td>
								<th>供应商</th>
								<td>${sell.sellSupplier}</td>
								<th>出库类型</th>
								<td>${sell.style}</td>
							</tr>
							<tr>
								<th>客户</th>
								<td>${sell.sellCompanyName}</td>
								<th>负责人</th>
								<td>${sell.sellCompanyPeople}</td>
								<th>领物品人</th>
								<td>${sell.sellCharger}</td>
							</tr>
							<tr>
								<th>送货单号</th>
								<td>${sell.sellSendnum}</td>
								<th>总成件号</th>
								<td>${sell.sellProMarkId}</td>
								<th>工艺卡片号</th>
								<td>${sell.sellArtsCard}</td>
							</tr>
							<tr>
								<th>订单关联</th>
								<td>${isNeed}</td>
								<th>内部订单号</th>
								<td>${sell.orderNum}</td>
								<th>外部订单号</th>
								<td>${sell.outOrderNumer}</td>
							</tr>
							<tr>
								<th>返回状态</th>
								<td>${sell.sellPeople}</td>
								<th>送货单运费</th>
								<td>${sell.sellSendCost}</td>
								<th>备注</th>
								<td>${sell.sellGoodsMore}</td>
							</tr>
						</table>
					</div>
					<input style="width: 80px; font-size: 18px;" onclick="printStick()"
					type="button" value="打印" />
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function printStick(){
			pagePrintOld('printDiv');
			var sellListSize = $("#sellListSize").val();
			for(var i=0;i<sellListSize;i++){
				var id = $("#sellId_"+i).val();
				$.ajax({
					type : "POST",
					url : "sellAction!print.action",
					data : {
						id : id
					},
					dataType : "json",
					success : function(msg) {
					}
				});
			}
					
		}
		</script>
	</body>
</html>
