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
		$("#submitBtn").attr("disabled", "disabled");
		$("#showrukuMes").html("正在入库中,请等待......");
		$.ajax( {
			type : "POST",
			url : "GoodsStoreAction!addWgrk.action",
			data : $('#myform').serialize(),
			dataType : "json",
			success : function(msg) {
				alert(msg.message);
				if (msg.success) {
					location.href = "OsRecord_rukuList.action";
				}
			}
		});
	});

	getcangqu();
});

//得到仓区;
function getcangqu() {
	var warehouse = $("#warehouse").val();
	if (warehouse != "") {
		$
				.ajax( {
					type : "POST",
					url : "WarehouseAreaAction_findwaListByNO.action",
					data : {
						wareHouseName : warehouse
					},
					dataType : "json",
					success : function(data) {
						if (data != null) {
							$("#goodHouseName").empty();
							$("#goodHouseName").append(
									'<option value="">--请选择--</option>')
							$(data).each(
									function() {
										$("#goodHouseName").append(
												'<option value='
														+ this.goodHouseName
														+ '>'
														+ this.goodHouseName
														+ '</option>');
									});
						}
						var tinyselect = $("#goodHouseName_td").children(
								".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodHouseName_td")
									.removeChild(tinyselect[0]);
						}
						$("#goodHouseName").tinyselect();
					}
				});
	}
}

//得到库位
function getkuwei(obj) {
	var warehouse = $("#warehouse").val();
	if (warehouse != "" && obj != null && obj.value != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwnListByNO.action",
			data : {
				wareHouseName : warehouse,
				cangqu : obj.value
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					$("#goodsStorePosition").empty();
					$("#goodsStorePosition").append(
							'<option value="">--请选择--</option>')
					$(data).each(
							function() {
								$("#goodsStorePosition").append(
										'<option value=' + this.number + '>'
												+ this.number + '</option>');
							});
				}
				var tinyselect = $("#goodsStorePosition_td").children(
						".tinyselect");
				if (tinyselect[0] != null) {
					document.getElementById("goodsStorePosition_td")
							.removeChild(tinyselect[0]);
				}
				$("#goodsStorePosition").tinyselect();
			}
		});
	}
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<form id="myform" method="post" action="">
					<input type="hidden" name="goodsStore.osrecordId"
						value="${record.id}" />
					<input type="hidden" name="goodsStore.wwddId"
						value="${waigoudd.id}" />
					<input type="hidden" name="goodsStore.goodsStorePrice"
						value="${waigoudd.price}" />
					<input type="hidden" name="goodsStore.hsPrice"
						value="${waigoudd.hsPrice}" />
					<input type="hidden" name="goodsStore.taxprice"
						value="${waigoudd.taxprice}" />
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input name="goodsStore.goodsStoreMarkId"
									value="${record.markId}" readonly="readonly" />
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsName"
									value="${record.proName}" readonly="readonly" />
							</td>
							<th align="right">
								数量
							</th>
							<td>
								<input name="goodsStore.goodsStoreCount"
									value="${record.hgNumber}" readonly="readonly" />
							</td>
							<th align="right">
								单位
							</th>
							<td>
								<select name="goodsStore.goodsStoreUnit">
									<option>
										${record.unit}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								批次
							</th>
							<td>
								<input name="goodsStore.goodsStoreLot" value="${record.jcpc}"
									readonly="readonly" />
							</td>
							<th align="right">
								供料属性
							</th>
							<td>
								<input name="goodsStore.kgliao" value="${record.kgliao}"
									readonly="readonly">
							</td>
							<th align="right">
								版本
							</th>
							<td>
								<input name="goodsStore.banBenNumber"
									value="${record.banbenNumber}" readonly="readonly">
							</td>
							<th align="right">
								供应商
							</th>
							<td>
								<input name="goodsStore.goodsStoreSupplier"
									value="${record.gysName}" readonly="readonly" />
								<input name="goodsStore.gysId" type="hidden"
									value="${record.gysId}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								所属仓库
							</th>
							<td>
								<s:if test='waigoudd.wwType=="外购件"'>
									<input id="warehouse" name="goodsStore.goodsStoreWarehouse"
										value="外购件库" readonly="readonly" />
								</s:if>
								<s:elseif test='waigoudd.wwType=="原材料"'>
									<input id="warehouse" name="goodsStore.goodsStoreWarehouse"
										value="原材料库" readonly="readonly" />
								</s:elseif>
								<s:else>
									<input id="warehouse" name="goodsStore.goodsStoreWarehouse"
										value="委外库" readonly="readonly" />
								</s:else>
							</td>
							<th align="right">
								所属仓区
							</th>
							<td>
								<select name="goodsStore.goodHouseName" style="width: 155px"
									id="goodHouseName" onchange="getkuwei(this)">
								</select>
							</td>
							<th align="right">
								库位
							</th>
							<td>
								<select name="goodsStore.goodsStorePosition"
									style="width: 155px" id="goodsStorePosition">
									<s:iterator value="list" id="pageGoodsStore">
										<option>
											${pageGoodsStore.goodsStorePosition}
										</option>
									</s:iterator>
								</select>
							</td>
							<th align="right">
								送货单号
							</th>
							<td>
								<input name="goodsStore.goodsStoreSendId"
									value="${record.wwdNumber}" readonly="readonly" />
							</td>
							<%--<th align="right">
								日期
							</th>
							<td>
								<input name="goodsStore.goodsStoreDate" onClick="WdatePicker()"
									class="Wdate" />
							</td>
						--%>
						</tr>
						<tr>

							<th align="right">
								转换数量
							</th>
							<td>
								<input name="goodsStore.goodsStoreZhishu" />
							</td>
							<th align="right">
								工艺卡号
							</th>
							<td>
								<input name="goodsStore.goodsStoreArtsCard" />
							</td>
							<th align="right">
								总成件号
							</th>
							<td>
								<input name="goodsStore.goodsStoreProMarkId" />
							</td>
							<th align="right">
								订单号
							</th>
							<td>
								<input name="goodsStore.orderId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								商品备注
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsMore" />
							</td>
							<th align="right">
								经办人
							</th>
							<td>
								<input name="goodsStore.goodsStoreCharger" />
							</td>
							<th align="right">
								负责人
							</th>
							<td>
								<input name="goodsStore.goodsStorePerson" />
							</td>
						</tr>
						<tr>
							<th align="right">
								计划员
							</th>
							<td>
								<input name="goodsStore.goodsStorePlanner" />
							</td>
							<th align="right">
								申请单编号
							</th>
							<td>
								<input name="goodsStore.goodsStoreNumber" />
							</td>
							<th align="right">
								用途
							</th>
							<td>
								<input name="goodsStore.goodsStoreUseful" />
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input id="submitBtn" type="button" value="入库" class="input" />
								<br />
								<span id="showrukuMes"></span>
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
