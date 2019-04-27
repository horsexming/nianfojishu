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

	<body onload="convertCurrency(${wasteDisponsalTotal.totalMoney})">
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
									${companyInfo.name}报废品处理申请
								</th>
							</tr>
						</table>
					</h2>
					<table border="0" style="width: 100%">
						<tr>
							<td align="left">
								日期：${wasteDisponsalTotal.createTime} &nbsp;
							</td>
							<td align="right">处理单编号：${wasteDisponsalTotal.serialNumber}</td>
						</tr>
					</table>
					<table class="table" style="width: 100%;">
						<tr height="35px">
							<th width="12%" align="right">
								处理单位
							</th>
							<td colspan="5">
								${wasteDisponsalTotal.sellToName}
							</td>
							<th width="12%" align="right">
								申请人
							</th>
							<td colspan="4">
								${wasteDisponsalTotal.createUserName}
							</td>
						</tr>
						<tr height="35px">
							<th align="center">
								序号
							</th>
							<th align="center">
								仓区
							</th>
							<th>
								库位
							</th>
							<th>
								品名
							</th>
							<th>
								规格
							</th>
							<th>
								供应商
							</th>
							<th>
								原价格(单价)
							</th>
							<th>
								处理数量
							</th>
							<th>
								处理价格
							</th>
							<s:if test="wasteDisponsalList.size()<3">
								<th rowspan="6" width="5%">
									说明
								</th>
								<td rowspan="6" width="20%" align="left" colspan="2">
									${wasteDisponsalTotal.explain}
								</td>
							</s:if>
							<s:else>
								<th
									rowspan="<s:property value='wasteDisponsalList.size()+3'/>"
									width="5%">
									说明
								</th>
								<th
									rowspan="<s:property value='wasteDisponsalList.size()+3'/>"
									width="20%" align="left" colspan="2">
									${wasteDisponsalTotal.explain}
								</th>
							</s:else>

						</tr>
						<s:iterator value="wasteDisponsalList" status="hk" id="sd">
							<tr align="center" height="35px">
								<td align="center">
									<s:property value="#hk.index+1" />
								</td>
								<td align="center">
									${sd.goodssHouseName}
								</td>
								<td>
									${sd.goodsPosition}
								</td>
								<td>
									${sd.goodsFullName}
								</td>
								<td>
									${sd.goodsFormat}
								</td>
								<td>
									${sd.goodsSupplier}
								</td>
								<td>
									${sd.goodsPrice}
								</td>
								<td>
									${sd.disposeNum}
								</td>
								<td>
									${sd.disposePrice}
								</td>
							</tr>
						</s:iterator>
						<s:if test="%{wasteDisponsalList.size()==2}">
							<tr align="left" height="35px">
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
							</tr>
						</s:if>
						<s:elseif test="%{wasteDisponsalList.size()==1}">
							<tr align="left" height="35px">
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
							</tr>
							<tr align="left" height="35px">
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
							</tr>
						</s:elseif>
						<tr height="35px">
							<th align="right">
								小写合计(${wasteDisponsalTotal.totalMoney})
							</th>
							<th align="left" colspan="8">
								
								${wasteDisponsalTotal.totalMoney}
							</th>
							<!--<th>类型</th>
							<th rowspan="2">
								付款
								<br>
								方式
							</th>
							<th rowspan="2">
								${fundApply.voucherway}
							</th>
						--></tr>
						<tr height="35px">
							<th align="right">
								大写合计(${wasteDisponsalTotal.totalMoney})
							</th>
							<th align="left" colspan="8" id="Result"></th>
						</tr>
					</table>
					<table class="table" style="width: 100%;">
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
			<s:if test="'同意'==wasteDisponsalTotal.epStatus">
				<input style="width: 80px; font-size: 18px;"
					onclick="pagePrint('printDiv','sy')" type="button" value="打印" />
			</s:if>
			<s:else>
				<a
					href="CircuitRunAction_findAduitPage.action?id=${wasteDisponsalTotal.epId}">待审批</a>
			</s:else>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
$(function(){
	var path = "${pageContext.request.contextPath}";
	var epStatus ="${wasteDisponsalTotal.epStatus}";
	if(epStatus=="同意"){
			var id = ${wasteDisponsalTotal.totalId};
			$.ajax( {
				type : "POST",
				url : "wasteDisposeAction!findPayExecutionNode.action",
				data : {
					totalId : id
				},
				dataType : "json",
				error:function(){
					alert("出错了");
				},
				success : function(data) {
					if(data.data2.length==1){
						if(data.data1[0]!=null&&data.data2[0].signature_address!=null){
							$("#aaa").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
							$("#bbb").append("("+data.data1[0].auditUserDept+")"+data.data1[0].auditDateTime+"");
							$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
							$("#ddd").append("("+data.data1[0].auditUserDept+")"+data.data1[0].auditDateTime+"");
						}
					}else{
						for(var i=data.data2.length-1;i>=0;i--){
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
