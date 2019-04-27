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
	<body
		onload="convertCurrency('<fmt:formatNumber type="number" value="${cwVouchers.jieMoney}" pattern="0.00" maxFractionDigits="2" />')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br />
				<br />
				<table class="table">
					<tr style="border: 0 0 0 0px;">
						<th colspan="10"
							style="font-size: 28px; font-weight: bolder; font-family: 黑体;">
							记账凭证
						</th>
					</tr>
					<tr>
						<th colspan="10">
							日期：${cwVouchers.voucherdate}
						</th>
					</tr>
					<tr>
						<th colspan="3" align="left">
							单位:${companyInfo.name}
						</th>
						<th colspan="10" align="right">
							编号 No:${cwVouchers.number}
						</th>
					</tr>
					<tr>
						<th align="center" rowspan="2">
							摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要
							<br />
							Explanation
						</th>
						<th align="center" rowspan="2">
							一 级 科 目
							<br />
							Gen. Leg. Acc
						</th>
						<th align="center" rowspan="2">
							明 细 科 目
							<br />
							CorrespondingAccounts
						</th>
						<th align="center" rowspan="2">
							外 币 金 额
							<br />
							For. Cur Amount
						</th>
						<th align="center" rowspan="2">
							汇 率
							<br />
							Exc. Rat
						</th>
						<th align="center" colspan="2">
							金 额 Amount
						</th>
						<th align="center"
							rowspan="<s:property value="cwVouchersDetailList.size+3"/>">
							附
							<br />
							件
							<br />
							<br />
							<br />
							张
						</th>
					</tr>
					<tr>
						<th align="center">
							借 方 Debit
						</th>
						<th align="center">
							贷 方 Credit
						</th>
					</tr>
					<s:iterator value="cwVouchersDetailList" id="pagevdetail"
						status="pageStatus">
						<tr align="center" height="30px"
							ondblclick="showFzclDetail(${pagevdetail.id});clickBgcolor(this)">
							<td align="center">
								${pagevdetail.vClass}:${pagevdetail.remark}
							</td>
							<td align="left">
								${pagevdetail.sub}
							</td>
							<td align="left">
								${pagevdetail.detailSub}
							</td>
							<td align="left">
							</td>
							<td align="left">
							</td>
							<td align="right">
								<s:if test="#pagevdetail.jieMoney<0">
									<font color="red"> <fmt:formatNumber type="number"
											value="${pagevdetail.jieMoney}" pattern="#,##0.00"
											maxFractionDigits="2" /> </font>
								</s:if>
								<s:else>
									<fmt:formatNumber type="number" value="${pagevdetail.jieMoney}"
										pattern="#,##0.00" maxFractionDigits="2" />
								</s:else>
							</td>
							<td align="right">
								<s:if test="#pagevdetail.daiMoney<0">
									<font color="red"> <fmt:formatNumber type="number"
											value="${pagevdetail.daiMoney}" pattern="#,##0.00"
											maxFractionDigits="2" /> </font>
								</s:if>
								<s:else>
									<fmt:formatNumber type="number" value="${pagevdetail.daiMoney}"
										pattern="#,##0.00" maxFractionDigits="2" />
								</s:else>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="5" align="right">
							合计（Total）:
							<span id="total"></span>
						</th>
						<td align="right">
							<fmt:formatNumber type="number" value="${cwVouchers.jieMoney}"
								pattern="#,##0.00" maxFractionDigits="2" />
						</td>
						<td align="right">
							<fmt:formatNumber type="number" value="${cwVouchers.daiMoney}"
								pattern="#,##0.00" maxFractionDigits="2" />
						</td>
					</tr>
					<tr id="showFzclDetailTr">
						<th colspan="8">
							<iframe id="showFzclDetailIf" src="" marginwidth="0"
								marginheight="0" hspace="0" vspace="0" frameborder="0"
								scrolling="yes"
								style="width: 100%; height: 400px; margin: 0px; padding: 0px;"></iframe>
						</th>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$("#showCvDetail", window.parent.document).load(function() {//绑定事件
				var main = $("#showCvDetail", window.parent.document);//找到iframe对象
				//获取窗口高度 
				var thisheight;
				thisheight = document.body.scrollHeight;
				thisheight = parseFloat(thisheight);
				var conHeight = parseFloat($("body").css("height"));//contentDiv div的宽度
				//				alert("thisheight--"+thisheight);
				//				alert("conHeight--"+conHeight);
				if (conHeight > thisheight) {
					thisheight = conHeight;
				}
				if (thisheight < 500) {
					thisheight = 500;
				}
				main.height(thisheight);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
			});
});
function showFzclDetail(cvdId, obj) {
	$("#showFzclDetailIf").attr("src",
			"CwVouchersAction!findCwUseDetailByCvdId.action?id=" + cvdId);

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
	document.getElementById("total").innerHTML = outputCharacters;
}
</script>

	</body>
</html>
