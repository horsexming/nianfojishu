<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">订单调整-请填写修改原因</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<form action="OrderForB2BAction!updateOrderForB2B.action">
						<input type="hidden" name="orderForB2B.poHeaderId"
							value="${orderForB2B.poHeaderId}" />
						<input type="hidden" name="orderForB2B.poReleaseId"
							value="${orderForB2B.poReleaseId}" />
						<input type="hidden" name="orderForB2B.instanceId"
							value="${orderForB2B.instanceId}" />
						<input type="hidden" name="orderForB2B.poNumber"
							value="${orderForB2B.poNumber}" />
						<input type="hidden" name="orderForB2B.lineLocationId"
							value="${orderForB2B.lineLocationId}" />
						<input type="hidden" id="typeLookupCode"
							name="orderForB2B.typeLookupCode" />
						<input type="hidden" id="promiseDateStr"
							name="orderForB2B.promiseDate" />
						<input type="hidden" id="needQuantity" name="orderForB2B.quantity" />
						<b>PO号:${orderForB2B.poNumber}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							订单行号:${orderForB2B.poLineNum}</b>
						<br />
						<textarea name="orderForB2B.remark" rows="10" cols="80"></textarea>
						<br />
						<input type="button" value="取消修改" class="input"
							onclick="chageDiv('none')">
						<input type="submit" value="提交修改" class="input">
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table">
					<tr>
						<th colspan="6" style="background: #f5f5f5; height: 50px;">
							PO号 :${orderForB2B.poNumber}
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							订单下达日期 :
							${orderForB2B.publishDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;币种
							: ${orderForB2B.currencyCode}
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单总金额
							:
							<fmt:formatNumber type="number"
								value="${orderForB2B.totalAmount}" pattern="#,##0.00"
								maxFractionDigits="2" />
						</th>
					</tr>
					<tr>
						<td colspan="6">
							<br />
							<br />
							<br />
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin: 0xp; padding: 0px;">
							<table class="table"
								style="margin: 0xp; padding: 0px; width: 100%">
								<tr>
									<th align="right" style="width: 25px;">
										供应商编码:
									</th>
									<td>
										${orderForB2B.vendorCode}
									</td>
									<th align="right">
										供应商名称:
									</th>
									<td align="left">
										${orderForB2B.vendorName}
									</td>
								</tr>
								<tr>
									<th align="right">
										采购员:
									</th>
									<td align="left">
										${orderForB2B.agentName}
									</td>
									<th align="left">
										支付条款:
									</th>
									<td>
										${orderForB2B.paymentTerms}
									</td>
								</tr>
								<tr>
									<th align="right">
										开票单位:
									</th>
									<td align="left">
										${orderForB2B.issuOffice}
									</td>
									<th align="right">
										开票地址:
									</th>
									<td align="left">
										${orderForB2B.billToLocation}
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<br />
							<br />
							<br />
						</td>
					</tr>
					<tr>
						<th align="right" style="width: 25px;">
							订单状态:
						</th>
						<td>
							<s:if test="orderForB2B.shipmentStatus=='NEW'">
								新下订单
							</s:if>
							<s:elseif test="orderForB2B.shipmentStatus=='OPEN'">
								在途订单
							</s:elseif>
							<s:elseif test="orderForB2B.shipmentStatus=='CANCELLED'">
								取消订单
							</s:elseif>
							<s:elseif test="orderForB2B.shipmentStatus=='CLOSED'">
								关闭订单
							</s:elseif>
							<s:elseif
								test="orderForB2B.shipmentStatus=='CLOSED FOR RECEVING'">
								已交货未关闭
							</s:elseif>
							<s:else>${orderForB2B.shipmentStatus}</s:else>
						</td>
						<th align="right">
							订单行号:
						</th>
						<td align="right">
							${orderForB2B.poLineNum}
						</td>
						<th align="right">
							发运行号:
						</th>
						<td align="right">
							${orderForB2B.shipmentNum}
						</td>
					</tr>
					<tr>
						<th align="right">
							Item编码:
						</th>
						<td align="right">
							${orderForB2B.itemCode}
						</td>
						<th align="right">
							物料/服务编码版本:
						</th>
						<td align="right">
							${orderForB2B.itemRevision}
						</td>
						<td></td>
					</tr>
					<tr>
						<th align="right">
							Item描述:
						</th>
						<td align="left" colspan="5">
							${orderForB2B.itemDescription}
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">需求数量</font>:
						</th>
						<td align="left">
							<input id="quantity" value="${orderForB2B.quantity}"
								onblur="chageQuantity('${orderForB2B.dueQty}')"
								onfocus="javascript:this.value='';">
							(${orderForB2B.unitOfMeasure})
						</td>
						<th align="right">
							未交付数量:
						</th>
						<td align="right">
							${orderForB2B.dueQty}
						</td>
						<th align="right">
							已交付数量:
						</th>
						<td align="right">
							${orderForB2B.quantityReceived}
						</td>
					</tr>
					<tr>
						<th align="right">
							单价:
						</th>
						<td align="right">
							${orderForB2B.priceOverride}
						</td>
						<th align="right">
							税率:
						</th>
						<td align="right">
							${orderForB2B.taxRate}
						</td>
						<th align="right">
							总额:
						</th>
						<td align="right">
							<fmt:formatNumber type="number"
								value="${orderForB2B.totalAmount}" pattern="#,##0.00"
								maxFractionDigits="2" />
						</td>
					</tr>
					<tr>
						<th align="right">
							订单下达日期:
						</th>
						<td align="right">
							${orderForB2B.publishDate}
						</td>
						<th align="right">
							需求时期:
						</th>
						<td align="right">
							${orderForB2B.needByDate}
						</td>
						<th align="right">
							<font color="red">承诺日期:</font>
						</th>
						<td align="left">
							<input value="${orderForB2B.promiseDate}" class="Wdate"
								id="promiseDate" type="text"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
								onchange="javascript:chagePromiseDate(${orderForB2B.promiseDate});">

						</td>
					</tr>
					<tr>
						<th align="right">
							收货地点:
						</th>
						<td align="left" colspan="5">
							${orderForB2B.shipToLocation}
						</td>
					</tr>
					<tr>
						<th align="right">
							备注:
						</th>
						<td align="left" colspan="5">
							${orderForB2B.remark}
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
							<a
								href="OrderForB2BAction!signBackPOList.action?orderForB2B.poHeaderId=${orderForB2B.poHeaderId}&orderForB2B.poReleaseId=${orderForB2B.poReleaseId}&orderForB2B.poLineId=${orderForB2B.poLineId}&operateType=accept">接受订单</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a
								href="OrderForB2BAction!signBackPOList.action?orderForB2B.poHeaderId=${orderForB2B.poHeaderId}&orderForB2B.poReleaseId=${orderForB2B.poReleaseId}&orderForB2B.poLineId=${orderForB2B.poLineId}&operateType=reject">驳回订单</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function chageQuantity(dueQty) {
	var quantityVal = parseInt($("#quantity").val());
	if (quantityVal > 0) {
		if (quantityVal > dueQty) {
			alert("调整数量不能大于需求数量!");
			$("#quantity").val(dueQty);
		} else if (quantityVal == dueQty) {
			$("#quantity").val(dueQty);
		} else {
			$("#typeLookupCode").val("1");
			$("#needQuantity").val(quantityVal);
			chageDiv("block");
		}
	} else {
		$("#quantity").val(dueQty);
	}
}

function chagePromiseDate(promiseDate) {
	var promiseDateVal = $("#promiseDate").val();
	if (promiseDateVal != "") {
		if (promiseDateVal != promiseDate) {
			$("#typeLookupCode").val("2");
			$("#promiseDateStr").val(promiseDateVal);
			chageDiv("block");
		} else {
			$("#promiseDateVal").val(promiseDate);
		}
	} else {
		$("#promiseDateVal").val(promiseDate);
	}
}
</script>
	</body>
</html>
