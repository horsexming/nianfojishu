<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%@page import="com.task.ServerImpl.AlertMessagesServerImpl"%>
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
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>

	<body onload="convertCurrency(${fundApply.totalMoney})">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<center>
					<div id="printDiv" align="center" style="width: 100%;">
						<br />
						<br />
						<table>
							<tr>
								<th>
									<h2>
										${companyInfo.name}应付登记单
									</h2>
								</th>
							</tr>
						</table>

						<table border="0" style="width: 100%">
							<tr>
								<td align="left">
									日期：${fundApply.baoxiaoDate} &nbsp;
								</td>
								<td align="right" width="30%">
									附件 &nbsp;<a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/fundApply/<s:property value="fundApply.attachmentsFile"/>"> ${fundApply.attachmentsCount}</a>&nbsp;张
								</td>
								<td align="right">
									<div>
								<div id="showQr" style="float: left;width: 60%"></div>
								<div  style="float: left;width: 40%;margin-top: 30px;height: 50px;text-align: center">
								<img
									src="<%=request.getContextPath()%>/barcode.action?msg=<s:property value="fundApply.number" />&type=code128"
									height="40px" width="300px" />
									</div>
							</div>
								</td>

							</tr>
						</table>
						<table class="table" style="width: 100%">
							<tr height="35px">
								<th width="12%" align="right">
									收款单位
								</th>
								<td colspan="2">
									${fundApply.relationclient}
								</td>
								<th>
									合同号
								</th>
								<td colspan="3">
									${fundApply.contractnum}
								</td>
							</tr>
							<tr height="35px">
								<th width="12%" align="right">
									发票号
								</th>
								<td colspan="2">
									${fundApply.invoiceNum }
								</td>
								<th>
									发票类型
								</th>
								<td colspan="3">
									${fundApply.isTax }
								</td>
							</tr>
							<tr height="35px">
<%--								<s:if test='tag!=null&&tag!=""'>--%>
<%--									<s:if test="tag=='ww'">--%>
<%--										<th align="center">--%>
<%--											件号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											工序号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											批次号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											数量--%>
<%--										</th>--%>
<%--									</s:if>--%>
<%--									<s:elseif test="tag=='wg'">--%>
<%--										<th align="center">--%>
<%--											件号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											零件名称--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											批次号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											数量--%>
<%--										</th>--%>
<%--									</s:elseif>--%>
<%--									<s:else>--%>
<%--										<th align="center">--%>
<%--											件号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											发票号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											内容--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											报销金额--%>
<%--										</th>--%>
<%--									</s:else>--%>
<%--								</s:if>--%>
<%--								<s:else>--%>
<%--									<s:if test="fundApply.producestatus=='其他'">--%>
<%--										<th align="center">--%>
<%--											件号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											发票号--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											内容--%>
<%--										</th>--%>
<%--										<th>--%>
<%--											报销金额--%>
<%--										</th>--%>
<%----%>
<%--									</s:if>--%>
<%--									<s:else>--%>
										<th align="center">
											件号
										</th>
										<th>
											批次
										</th>
										<th>
											数量
										</th>
										<th>
											金额
										</th>
										<th>
											明细
										</th>
<%--									</s:else>--%>
<%--								</s:else>--%>

								<s:if test="%{fundApply.fadSet.size()<3}">
									<th rowspan="4" width="5%">
										说明
									</th>
									<td rowspan="4" width="20%" align="left">
										${fundApply.explain }
									</td>
								</s:if>
								<s:else>
									<th
										rowspan="<s:property value='fundApply.fadSet.size()+1'/>"
										width="5%">
										说明
									</th>
									<th
										rowspan="<s:property value='fundApply.fadSet.size()+1'/>"
										width="20%" align="left">
										${fundApply.explain }
									</th>
								</s:else>

							</tr>

							<s:iterator value="fundApply.fadSet" status="hk"
								id="detail">
<%--								<s:if test='tag!=null&&tag!=""'>--%>
<%--									<s:if test="tag=='wg'">--%>
<%--										<tr align="center" height="35px">--%>
<%--											<td align="center">--%>
<%--												${detail.goodsStoreMarkId}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.partName}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.goodsStoreLot}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.goodsStoreCount}--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--									</s:if>--%>
<%--									<s:elseif test="tag=='ww'">--%>
<%--										<tr align="center" height="35px">--%>
<%--											<td align="center">--%>
<%--												${detail.goodsStoreMarkId}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.processesNo}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.goodsStoreLot}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.goodsStoreCount}--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--									</s:elseif>--%>
<%--									<s:else>--%>
<%--										<tr align="center" height="35px">--%>
<%--											<td align="center">--%>
<%--												${detail.goodsStoreMarkId}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.invoiceNo}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.content}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.money}--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--									</s:else>--%>
<%--								</s:if>--%>
<%--								<s:else>--%>
<%--									<s:if test="fundApply.producestatus=='其他'">--%>
<%--										<tr align="center" height="35px">--%>
<%--											<td align="center">--%>
<%--												${detail.goodsStoreMarkId}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.invoiceNo}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.content}--%>
<%--											</td>--%>
<%--											<td>--%>
<%--												${detail.money}--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--									</s:if>--%>
<%--									<s:else>--%>
										<tr align="center" height="35px">
											<td align="center">
												${detail.markId}
											</td>
											<td>
												${detail.zjStyle}
											</td>
											<td>
												${detail.zjStyleMx}
											</td>
											<td>
												${detail.voucherMoney}
											</td>
											<td>
												${detail.pay_use}
											</td>
										</tr>
<%--									</s:else>--%>
<%--								</s:else>--%>

							</s:iterator>
							<s:if test="%{fundApply.fadSet.size()==2}">
								<tr align="left" height="35px">
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
								</tr>
							</s:if>
							<s:elseif test="%{fundApply.fadSet.size()==1}">
								<tr align="left" height="35px">
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
								</tr>
								<tr align="left" height="35px">
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
								</tr>
							</s:elseif>
							<tr height="35px">
								<th align="right">
									小写合计(${fundApply.currency})
								</th>
								<th align="left" colspan="3">
									<s:if test="%{fundApply.currency=='RMB'}">￥</s:if>
									<s:elseif test="%{'USD'==fundApply.currency}">$</s:elseif>
									<s:elseif test="%{fundApply.currency=='GBP'}">￡</s:elseif>
									<s:elseif test="%{fundApply.currency=='EUR'}">€</s:elseif>
									${fundApply.totalMoney}
								</th>
								<th rowspan="2" colspan="2">
									付款
									<br>
									方式
								</th>
								<td rowspan="2" align="center">
									${fundApply.voucherway}
								</td>
							</tr>
							<tr height="35px">
								<th align="right">
									大写合计(${fundApply.currency})
								</th>
								<th align="left" colspan="3" id="Result"></th>
							</tr>
						</table>
						<table class="table" style="width: 100%">
							<tr height="45px">
								<td width="10%">
									总经理
								</td>
								<td width="10%">
									&nbsp;
									<span id="aaa"></span>
									<span id="bbb"></span>
								</td>
								<td width="10%">
									财务审核
								</td>
								<td width="10%">
									&nbsp;
								</td>
								<td width="10%">
									部门主管
								</td>
								<td width="10%">
									&nbsp;
									<span id="ccc"></span>
									<span id="ddd"></span>
								</td>
								<td width="10%">
									报销人
								</td>
								<td width="10%">
									${fundApply.approvalApplier}
								</td>
							</tr>
						</table>
					</div>
					<table class="table" style="width: 100%">
						<tr>
							<td align="center">
								<s:if test="'同意'==fundApply.epStattus">
									<input style="width: 80px; font-size: 18px;"
										onclick="pagePrint('printDiv','sy')" type="button" value="打印">
								</s:if>
								<s:else>
									<a
										href="CircuitRunAction_findAduitPage.action?id=${fundApply.epId}">待审批</a>
								</s:else>
							</td>
						</tr>
					</table>
				
				</center>
			</div>

			<%@include file="/util/foot.jsp"%>
			<script type="text/javascript">
function convertCurrency(currencyDigits) {
	var MAXIMUM_NUMBER = 99999999999.99;
	var CN_ZERO = "零";
	var CN_ONE = "壹";
	var CN_TWO = "贰";
	var CN_THREE = "叁";
	var CN_FOUR = "肆";
	var CN_FIVE = "伍";
	var CN_SIX = "陆";
	var CN_SEVEN = "柒";
	var CN_EIGHT = "捌";
	var CN_NINE = "玖";
	var CN_TEN = "拾";
	var CN_HUNDRED = "佰";
	var CN_THOUSAND = "仟";
	var CN_TEN_THOUSAND = "万";
	var CN_HUNDRED_MILLION = "亿";
	var CN_SYMBOL = ""; //币种   
	var CN_DOLLAR = "元";
	var CN_TEN_CENT = "角";
	var CN_CENT = "分";
	var CN_INTEGER = "整";

	var integral;
	var decimal;
	var outputCharacters;
	var parts;
	var digits, radices, bigRadices, decimals;
	var zeroCount;
	var i, p, d;
	var quotient, modulus;

	currencyDigits = currencyDigits.toString();
	if (currencyDigits == "") {
		return "";
	}
	if (currencyDigits.match(/[^,.\d]/) != null) {
		return "";
	}
	if ((currencyDigits)
			.match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
		return "";
	}

	currencyDigits = currencyDigits.replace(/,/g, "");
	currencyDigits = currencyDigits.replace(/^0+/, "");

	if (Number(currencyDigits) > MAXIMUM_NUMBER) {
		alert("金额过大，超出转换范围!");
		return "";
	}

	parts = currencyDigits.split(".");
	if (parts.length > 1) {
		integral = parts[0];
		decimal = parts[1];
		decimal = decimal.substr(0, 2);
	} else {
		integral = parts[0];
		decimal = "";
	}
	digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE,
			CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
	radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
	bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
	decimals = new Array(CN_TEN_CENT, CN_CENT);
	outputCharacters = "";
	if (Number(integral) > 0) {
		zeroCount = 0;
		for (i = 0; i < integral.length; i++) {
			p = integral.length - i - 1;
			d = integral.substr(i, 1);
			quotient = p / 4;
			modulus = p % 4;
			if (d == "0") {
				zeroCount++;
			} else {
				if (zeroCount > 0) {
					outputCharacters += digits[0].split(".");
				}
				zeroCount = 0;
				outputCharacters += digits[Number(d)] + radices[modulus];
			}
			if (modulus == 0 && zeroCount < 4) {
				outputCharacters += bigRadices[quotient];
			}
		}
		outputCharacters += CN_DOLLAR;
	}
	if (decimal != "") {
		for (i = 0; i < decimal.length; i++) {
			d = decimal.substr(i, 1);
			if (d != "0") {
				outputCharacters += digits[Number(d)] + decimals[i];
			}
		}
	}
	if (outputCharacters == "") {
		outputCharacters = CN_ZERO + CN_DOLLAR;
	}
	if (decimal == "") {
		outputCharacters += CN_INTEGER;
	}
	outputCharacters = CN_SYMBOL + outputCharacters;
	document.getElementById("Result").innerHTML = outputCharacters;
}
$(function(){
			getQRCode (100,100,"<%=AlertMessagesServerImpl.pebsUrl%>FundApplyAction_findfundDetailedList.action?id=${id}","showQr");
			var epStattus ="${fundApply.epStattus}";
	if(epStattus=="同意"){
			var id = ${fundApply.id};
			$.ajax( {
				type : "POST",
				url : "FundApplyAction_findPay_ExecutionNode.action",
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					$("#aaa").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
					$("#bbb").append("Agr:"+data.data1[0].auditDateTime+"");
					if(data.data2.length>1){
					$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[1].signature_address+"' height='50px' width='120px' align='middle'></img>");
					}
					if(data.data2.length>1){
					$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[data.data2.length-1].signature_address+"' height='50px' width='120px' align='middle'></img>");
					$("#ddd").append(data.data1[data.data1.length-1].auditDateTime+"");
					}

			}
			});
			//document.getElementById("aaa").innerHTML = "毛小龙";
			}
		})
</script>
	</body>
</html>
