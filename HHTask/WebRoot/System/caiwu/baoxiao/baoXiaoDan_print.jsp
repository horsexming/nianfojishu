<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

	<body onload="convertCurrency(${baoxiaodan.totalMoney})">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<div id="printDiv" align="center" style="width: 100%;">
					<br />
					<br />
					<h2>
						<table>
							<tr>
								<th>
									<img widtn="64px;" height="55px;"
										src="<%=basePath%>${companyInfo.logoOKjpg}" />
								</th>
								<th>
									${companyInfo.name}费用报销单
								</th>
							</tr>
						</table>
					</h2>
					<table border="0" style="width: 100%">
						<tr>
							<td align="left">
								日期：${baoxiaodan.baoxiaoDate} &nbsp;
							</td>
							<td align="right" width="30%">
								附件 &nbsp;${baoxiaodan.attachmentsCount} &nbsp;张
							</td>
							<td align="right">
								<img
									src="<%=request.getContextPath()%>/barcode.action?msg=<s:property value="baoxiaodan.baoxiaoBarcode" />&type=code128"
									height="40px" width="300px" />
							</td>

						</tr>
					</table>
					<table class="table" style="width: 100%">
						<tr height="35px">
							<th width="12%" align="right">
								收款单位(个人)
							</th>
							<td colspan="2">
								${baoxiaodan.shoukuanRen }
							</td>
							<th>
								预算月份
							</th>
							<td colspan="2">
								${baoxiaodan.planMonth }
							</td>
						</tr>
						<tr height="35px">
							<th width="12%" align="right">
								发票号
							</th>
							<td colspan="2">
								${baoxiaodan.invoiceNum }
							</td>
							<th>
								发票类型
							</th>
							<td colspan="2">
								${baoxiaodan.isTax }
							</td>
						</tr>
						<tr height="35px">
							<th align="center">
								项目明细
							</th>
							<th>
								报销内容摘要
							</th>
							<th>
								承担部门
							</th>
							<th>
								金额
							</th>
							<s:if test="%{baoxiaodan.getBaoxiaoDetail().size()<3}">
								<th rowspan="4" width="5%">
									说明
								</th>
								<td rowspan="4" width="20%" align="left">
									${baoxiaodan.explain }
								</td>
							</s:if>
							<s:else>
								<th
									rowspan="<s:property value='baoxiaodan.getBaoxiaoDetail().size()+1'/>"
									width="5%">
									说明
								</th>
								<th
									rowspan="<s:property value='baoxiaodan.getBaoxiaoDetail().size()+1'/>"
									width="20%" align="left">
									${baoxiaodan.explain }
								</th>
							</s:else>

						</tr>

						<s:iterator value="baoxiaodan.getBaoxiaoDetail()" status="hk"
							id="detail">
							<tr align="center" height="35px">
								<td align="center">
									${detail.baoxiaoStyle}
								</td>
								<td>
									${detail.baoxiaoContent}
								</td>
								<td>
									${detail.budgetDept}
								</td>
								<td>
									${detail.money}
								</td>
							</tr>
						</s:iterator>
						<s:if test="%{baoxiaodan.getBaoxiaoDetail().size()==2}">
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
						<s:elseif test="%{baoxiaodan.getBaoxiaoDetail().size()==1}">
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
								小写合计(${baoxiaodan.currency})
							</th>
							<th align="left" colspan="3">
								<s:if test="%{baoxiaodan.currency=='RMB'}">￥</s:if>
								<s:elseif test="%{'USD'==baoxiaodan.currency}">$</s:elseif>
								<s:elseif test="%{baoxiaodan.currency=='GBP'}">￡</s:elseif>
								<s:elseif test="%{baoxiaodan.currency=='EUR'}">€</s:elseif>
								${baoxiaodan.totalMoney}
							</th>
							<th rowspan="2">
								付款
								<br>
								方式
							</th>
							<th rowspan="2">
								${baoxiaodan.payStyle}
							</th>
						</tr>
						<tr height="35px">
							<th align="right">
								大写合计(${baoxiaodan.currency})
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
							</td>
							<td width="10%">
								报销人
							</td>
							<td width="10%">
								&nbsp;
							</td>
							<td width="10%">
							</td>
							<td width="10%">
							</td>
						</tr>
						<tr>
							<td colspan="10" style="font-size: 12px;">
								备注：
								<br>
								1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
								<br />
								2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
								<br />
								4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
								<br />
								5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等）
								<br />
								6、领款日为每周二、四（对公转账及急事、特事除外）。
							</td>
						</tr>
					</table>

				</div>
			</div>
			<input style="width: 80px; font-size: 18px;"
				onclick="pagePrint('printDiv','sy')" type="button" value="打印">
			<s:if test="'print'==baoxiaodan.status">
			</s:if>
			<s:else>
				<span style="font-size: 20; color: red; font-weight: bold">等待报销承担部门确认后，方可打印报销！</span>
			</s:else>

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
</script>
	</body>
</html>
