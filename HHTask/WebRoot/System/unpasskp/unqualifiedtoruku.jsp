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
$(function() {
	getUnit("danwei");
	$('#submitBtn').bind('click', function() {
		if ($("#goodsStoreMarkId").val() == "") {
			alert("请输入件号!");
		} else if ($("#goodsStoreCount").val() == "") {
			alert("请输入数量!");
		} else if ($("#goodsStoreDate").val() == "") {
			alert("请输入入库日期!");
		} else {
			$.ajax( {
				type : "POST",
				url : "productUnqualifiedAction!addSdrk.action",
				data : $('#myform').serialize(),
				dataType : "json",
				success : function(msg) {
					alert(msg.message);
					if (msg.success) {
						location.href = "GoodsStoreAction!rukuList.action";
					}
				}
			});
		}
	});
});
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<form id="myform" method="post" action="">
					<table class="table">
						<tr>
							<th align="right">
								所属仓库
								<br />
								Owned warehouse
							</th>
							<td><input name="goodsStore.goodsStoreWarehouse" id="warehouse" value="成品库">
							</td>
							<th align="right">
								件号
								<br />
								Part No.
							</th>
							<td>
							<input type="hidden" name="cpage" value="<s:property value="cpage"/>">
							<input type="hidden" name="productUnqualified.id" value="<s:property value="productUnqualified.id"/>">
								<input id="goodsStoreMarkId" name="goodsStore.goodsStoreMarkId" value="<s:property value="productUnqualified.markId"/>" readonly="readonly"/>
							</td>
							<th align="right">
								名称
								<br />
								Name
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsName" id="goodsName" value="<s:property value="productUnqualified.proName"/>" readonly="readonly"/>
							</td>
							<th align="right">
								批次
								<br />
								Batch
							</th>
							<td>
								<input name="goodsStore.goodsStoreLot" />
							</td>


						</tr>
						<tr>
							<th align="right">
								工艺卡号
								<br />
								Process card
							</th>
							<td>
								<input name="goodsStore.goodsStoreArtsCard" />
							</td>
							<th align="right">
								总成件号
								<br />
								Assembly Number
							</th>
							<td>
								<input name="goodsStore.goodsStoreProMarkId" />
							</td>
							<th align="right">
								数量
								<br />
								Quantity
							</th>
							<td>
								<input id="goodsStoreCount" name="goodsStore.goodsStoreCount" value="<s:property value="productUnqualified.surplusCount"/>"/>
							</td>
							<th align="right">
								库位
								<br />
								Location
							</th>
							<td>
								<input name="goodsStore.goodsStorePosition" />
							</td>
						</tr>
						<tr>
							<th align="right">
								价格
								<br />
								Price
							</th>
							<td>
								<input name="goodsStore.goodsStorePrice" />
							</td>
							<th align="right">
								用途
								<br />
								Uses
							</th>
							<td>
								<input name="goodsStore.goodsStoreUseful" />
							</td>
							<th align="right">
								供应商
								<br />
								Supplier
							</th>
							<td>
								<input name="goodsStore.goodsStoreSupplier" />
							</td>
							<th align="right">
								商品备注
								<br />
								Commodity Remarks
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsMore" />
							</td>
						</tr>
						<tr>
							<th align="right">
								经办人
								<br />
								Attn.
							</th>
							<td>
								<input name="goodsStore.goodsStoreCharger" />
							</td>
							<th align="right">
								负责人
								<br />
								Leader
							</th>
							<td>
								<input name="goodsStore.goodsStorePerson" />
							</td>
							<th align="right">
								日期
								<br />
								Date
							</th>
							<td>
								<input id="goodsStoreDate" name="goodsStore.goodsStoreDate"
									onClick="WdatePicker()" class="Wdate" />
							</td>
							<th align="right">
								订单号
								<br />
								Order number
							</th>
							<td>
								<input name="goodsStore.orderId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								送货单号
								<br />
								No delivery note
							</th>
							<td>
								<input name="goodsStore.goodsStoreSendId" />
							</td>
							<th align="right">
								支数
								<br />
								Count
							</th>
							<td>
								<input name="goodsStore.goodsStoreZhishu" />
							</td>
							<th align="right">
								计划员
								<br />
								Planner
							</th>
							<td>
								<input name="goodsStore.goodsStorePlanner" />
							</td>
							<th align="right">
								规格
								<br />
								Specifications
							</th>
							<td>
								<input name="goodsStore.goodsStoreFormat" id="storeFormat" />
							</td>
						</tr>
						<tr>
							<th align="right">
								单位
								<br />
								Unit
							</th>
							<td>
								<select name="goodsStore.goodsStoreUnit" id="danwei">
								</select>
							</td>
							<th align="right">
								入库类型
								<br />
								Storage type
							</th>
							<td><input name="goodsStore.style" value="返修入库" readonly="readonly">
							</td>
							<th align="right">
								申请单编号
								<br />
								Requisition Number
							</th>
							<td>
								<input name="goodsStore.goodsStoreNumber" />
							</td>
							<th align="right">
								炉批号
								<br />
								Furnace batch
							</th>
							<td>
								<input name="goodsStore.goodsStoreLuId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								客户
								<br />
								Customers
							</th>
							<td>
								<input name="goodsStore.goodsStoreCompanyName" value="<s:property value="productUnqualified.cusName"/>" readonly="readonly"/>
							</td>
							<th align="right">
								期初数量
								<br />
								Opening Number
							</th>
							<td>
								<input name="goodsStore.beginning_num" />
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input id="submitBtn" type="button" value="提交(submit)"
									class="input" />
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
		<script type="text/javascript">
</script>
	</body>
</html>
