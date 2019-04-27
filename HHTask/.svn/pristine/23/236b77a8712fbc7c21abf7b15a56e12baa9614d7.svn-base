<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.ServerImpl.AlertMessagesServerImpl"%>
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
									${companyInfo.name}应付登记单
								</th>
							</tr>
						</table>
					</h2>
					<table border="0" style="width: 100%">
						<tr>
							<td align="left">
								日期：${fundApply.baoxiaoDate} &nbsp;
							</td>
							<td align="right" width="30%">
								附件 &nbsp;<a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/fundApply/<s:property value="fundApply.attachmentsFile"/>"> ${fundApply.attachmentsCount}</a> &nbsp;张
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
								收款单位(个人)
							</th>
							<td colspan="4">
								${fundApply.relationclient}
							</td>
							<th>
								预算月份
							</th>
							<td colspan="2">
								${fundApply.planMonth}
							</td>
						</tr>
						<tr height="35px">
							<th width="12%" align="right">
								发票号
							</th>
							<td colspan="4">
<%--								${fundApply.invoiceNum}<br/>--%>
								<s:iterator value="fundApply.invoiceNum.split(',')" id="fapiao">
									<a href="FundApplyAction_findInvoiceCheckByfpdm.action?wwNumber=${fapiao}">${fapiao}</a>,
								</s:iterator>
							</td>
							<th>
								发票类型
							</th>
							<td colspan="2">
								${fundApply.isTax}
							</td>
						</tr>
						<tr height="35px">
							<th align="center">
								项目
							</th>
							<th align="center">
								科目
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
							<s:if test="fundApply.fadList.size()<3">
								<th rowspan="4" width="5%">
									说明
								</th>
								<td rowspan="4" width="20%" align="left" colspan="2">
									${fundApply.explain}
								</td>
							</s:if>
							<s:else>
								<th
									rowspan="<s:property value='fundApply.fadList.size()+1'/>"
									width="5%">
									说明
								</th>
								<th
									rowspan="<s:property value='fundApply.fadList.size()+1'/>"
									width="20%" align="left" colspan="2">
									${fundApply.explain}
								</th>
							</s:else>

						</tr>
						<s:iterator value="fundApply.fadList" status="hk"
							id="detail">
							<tr align="center" height="35px">
							<td align="center">
									${detail.deptMonthBudgetName}
								</td>
								<td align="center">
									${detail.zjStyle}
								</td>
								<td>
									${detail.pay_use}
								</td>
								<td>
									${detail.budgetDept}
								</td>
								<td>
									${detail.voucherMoney}
								</td>
							</tr>
						</s:iterator>
						<s:if test="%{fundApply.fadList.size()==2}">
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
							</tr>
						</s:if>
						<s:elseif test="%{fundApply.fadList.size()==1}">
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
								<th>
									&nbsp;
								</th>
							</tr>
						</s:elseif>
						<tr height="35px">
							<th align="right">
								小写合计(${fundApply.currency})
							</th>
							<th align="left" colspan="4">
								<s:if test="%{fundApply.currency=='RMB'}">￥</s:if>
								<s:elseif test="%{'USD'==fundApply.currency}">$</s:elseif>
								<s:elseif test="%{fundApply.currency=='GBP'}">￡</s:elseif>
								<s:elseif test="%{fundApply.currency=='EUR'}">€</s:elseif>
								${fundApply.totalMoney}
							</th>
							<th>类型</th>
							<th rowspan="2">
								付款
								<br>
								方式
							</th>
							<th rowspan="2">
								${fundApply.voucherway}
							</th>
						</tr>
						<tr height="35px">
							<th align="right">
								大写合计(${fundApply.currency})
							</th>
							<th align="left" colspan="4" id="Result"></th>
							<th>${fundApply.about}</th>
						</tr>
					</table>
					<table class="table" style="width: 100%">
						<tr height="45px">
							<th width="10%" align="center">
									已审批人员
								</th>
								<td width="45%" align="center" colspan="2">
									&nbsp;
									<span id="aaa"></span>
									<br/>
									<span id="bbb"></span>
								</td>
								<th width="10%" align="center">
									批准人:
								</th>
								<td width="10%" align="center" colspan="5">
									<span id="ccc"></span>	
									<br/>
									<span id="ddd"></span>
								</td>
						</tr>
					</table>

				</div>
			</div>
			<s:if test="'同意'==fundApply.epStattus">
									<input style="width: 80px; font-size: 18px;"
										onclick="pagePrint('printDiv','sy')" type="button" value="打印">
								</s:if>
								<s:else>
									<a
										href="CircuitRunAction_findAduitPage.action?id=${fundApply.epId}">待审批</a>
								</s:else>

		
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
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
					if(data.data2.length==1){
						if(data.data2[0]!=null&&data.data2[0].signature_address!=null){
						$("#aaa").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
						$("#bbb").append("("+data.data1[0].auditUserDept+")"+data.data1[i].auditDateTime+"");
						$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
						$("#ddd").append("("+data.data1[0].auditUserDept+")"+data.data1[0].auditDateTime+"");
						}
					}else{
					for(var i=data.data2.length-1;i>=0;i--){
						if(data.data2[i]!=null&&data.data2[i].signature_address!=null){
						if(i==0){
							$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[i].signature_address+"' height='50px' width='120px' align='middle'></img>");
						$("#ddd").append("("+data.data1[i].auditUserDept+")"+data.data1[i].auditDateTime+"");
						}else{
						if(i<(data.data2.length-1)){
							$("#aaa").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
							$("#bbb").append("&nbsp;&nbsp;");
						}
						$("#aaa").append("<img src='<%=request.getContextPath()%>"+data.data2[i].signature_address+"' height='50px' width='120px' align='middle'></img>");
						$("#bbb").append("("+data.data1[i].auditUserDept+")"+data.data1[i].auditDateTime+"");
						}
						}
						}
					}

			}
			});
			//document.getElementById("aaa").innerHTML = "毛小龙";
			}
		})
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
