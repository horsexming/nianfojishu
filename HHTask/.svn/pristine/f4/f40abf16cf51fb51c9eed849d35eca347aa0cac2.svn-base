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
	<body onload="convertCurrency('${oaReimBursement.oathetotalamount}')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<center>
					<div id="printDiv">
						<input type="hidden" id="obsId" value="${oaReimBursement.id}" />
						<table align="center" class="table">
							<tr>
								<td colspan="10" align="center">
									<img align="left" ; src="<%=basePath%>${companyInfo.logoOKjpg}"
										style="width: 60px; height: 50px;">

									<font size="5">付款凭证(单)</font>
									<h5 align="right">
										付款凭证编号：${oaReimBursement.oaPaymentvouchernumber}
									</h5>
								</td>
							</tr>
							<tr>
								<th>
									商品编号
								</th>
								<th>
									名称
								</th>
								<th>
									规格
								</th>
								<th>
									单位
								</th>
								<th>
									数量
								</th>
								<th>
									单价（元）
								</th>
								<th>
									总金额
								</th>
								<th>
									发票号
								</th>
								<th>
									档案号
								</th>
								<th>
									合同号
								</th>
							</tr>
							<s:iterator id="yw" value="list">
								<tr>
									<td align="center">
										${yw.oaproductnumber}
									</td>
									<td align="center">
										${yw.oaproductName}
									</td>
									<td align="center">
										${yw.oaspecification}
									</td>
									<td align="center">
										${yw.oaunit}
									</td>
									<td align="center">
										${yw.oaquantity}
									</td>
									<td align="center">
										${yw.oaunitprice}
									</td>
									<td align="center">
										${yw.oatotalMon}
									</td>
									<td align="center">
										${yw.oainvoicenumber}
									</td>
									<td align="center">
										${yw.oacontractNumber}
									</td>
									<td align="center">
										${yw.oahetongnumber}
									</td>
								</tr>
							</s:iterator>
							<tr>
								<th align="center">
									总金额(元)
								</th>
								<td colspan="9">
									<font style="font-weight: bold;">${oaReimBursement.oathetotalamount}</font>
								</td>
							</tr>

							<tr>
								<th>
									付款金额
								</th>
								<td colspan="9">
									<font style="font-weight: bold;"><span id="Result"></span>
									</font>
								</td>
							</tr>

							<tr>
								<th align="center">
									收款人单位名称
								</th>
								<td colspan="9">
									${oaReimBursement.oaBeneficiary}
								</td>
							</tr>

							<tr>
								<th align="center">
									付款性质
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'预付':'预付','中间付款':'中间付款','余款':'余款','质保金':'质保金','借款':'借款','冲账':'冲账','其他':'其他'}"
										name="oaReimBursement.oaPaymentnature"></s:checkboxlist>
								</td>
							</tr>



							<tr>
								<th align="center">
									付款方式
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'银行':'银行','汇票':'汇票','汇兑':'汇兑','支票':'支票','贷记':'贷记','现金':'现金','其他':'其他'}"
										name="oaReimBursement.oaPayment"></s:checkboxlist>
								</td>
							</tr>


							<tr>
								<th align="center">
									付款情况
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'总额':'总额','已支付':'已支付','本次应付':'本次应付','累计支付':'累计支付','余额':'余额'}"
										name="oaReimBursement.oaPaymentsqk"></s:checkboxlist>

								</td>
							</tr>


							<tr>
								<th align="center">
									付款条件
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'即付':'即付','TT30天':'TT30天','TT60天':'TT60天','TT90天':'TT90天','TT120天':'TT120天','TT120天以上':'TT120天以上','其他':'其他'}"
										name="oaReimBursement.oaTermsPayment"></s:checkboxlist>
								</td>
							</tr>

							<tr>
								<th align="center">
									类别
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'总务性采购':'总务性采购','原材料采购':'原材料采购','工程设备类采购':'工程设备类采购','其他':'其他'}"
										name="oaReimBursement.oaCategory"></s:checkboxlist>
								</td>
							</tr>

							<tr>
								<th align="center">
									付款依据
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'合同':'合同','发票':'发票','协议':'协议','通知':'通知','其他':'其他'}"
										name="oaReimBursement.oaPaymentaccordancewith"></s:checkboxlist>
								</td>
							</tr>

							<tr>
								<th align="center">
									关联客户名称
								</th>
								<td>
									${oaReimBursement.oaAssociatedusername }
								</td>
								<th align="center">
									评审单号
								</th>
								<td>
									${oaReimBursement.oaReviewnumber}
								</td>
								<th align="center">
									负责人
								</th>
								<td>
									${oaReimBursement.oaPersonincharge}
								</td>
								<th align="center">
									日期
								</th>
								<td colspan="3">
									${oaReimBursement.oadate}
								</td>
							</tr>

							<tr>
								<th>
									情况说明
								</th>
								<td colspan="9" style="width: 250px; height: 50px;">
									${oaReimBursement.oaFactSheet}
								</td>
							</tr>
							<tr>
								<td style="height: 80px;" align="left" colspan="10">
									<div style="float: left; width: 25%">
										批准
									</div>
									<div style="float: left; width: 25%">
										审核
									</div>
									<div style="float: left; width: 25%">
										经办人
									</div>
									<div style="float: left; width: 25%">
										部门经理
									</div>

								</tD>
							</tr>
							<tr>
								<td align="center" colspan="10">
									<img
										src="<%=request.getContextPath()%>/barcode.action?msg=${oaReimBursement.oaPaymentvouchernumber}&type=code128"
										height="55px" width=200px />
								</td>
							</tr>
						</table>
					</div>
					<table>
						<tr>
							<td align="center">
								<s:if test="%{'print'==tag}">
								 <s:if test="oaReimBursement.oastatus=='可打印'||oaReimBursement.oastatus=='审核中'">
									<input type="button" class="input" value="打    印"
										onclick="printosw()">
										</s:if>
								</s:if>
							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

		<script language="JavaScript">
function printosw() {
	pagePrint('printDiv');
	//处理状态
	var id = $("#obsId").val();
	//alert($('#osP').serialize());
	$.ajax( {
		type : "POST",
		url : "oaReimBursementAction!updateOBSById.action",
		data : {
			id : id
		},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(msg) {
		}
	});
}
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
	var CN_SYMBOL = "人民币(大写)：";
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
</script>
	</body>
</html>
