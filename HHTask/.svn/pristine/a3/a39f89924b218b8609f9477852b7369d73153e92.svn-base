<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body 
	onload="convertCurrency(${fundApply.totalMoney})"
		oncontextmenu="return false" onselectstart="return false"
		ondragstart="return false">
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
				</div>
			</div>

			<div align="center">
				<center>
					<div id="printDiv">
<%--						<input type="hidden" id="obsId" value="${oaReimBursement.id}" />--%>
						<table align="center" class="table">
							<tr>
								<td colspan="10" align="center">
									<img align="left" ; src="${companyInfo.logoOKjpg}"
										style="width: 60px; height: 50px;">

									<font size="5">${companyInfo.name}费用应付登记单</font>
									<div >
								<div id="showQr" style="float: left;width: 75%" align="right"></div>
								<div  style="float: left;width: 10%;margin-top: 30px;height: 50px;text-align: center">
								<img
									src="<%=request.getContextPath()%>/barcode.action?msg=<s:property value="fundApply.number" />&type=code128"
									height="40px" width="300px" />
									</div>
							</div>
								</td>
							</tr>
							<tr>
								<th>
									业务内容
								</th>
								<th>
									科目
								</th>
								<th>
									借款金额
								</th>
								<th colspan="2">
									用途
								</th>
								<th>
									是否借款
								</th>
								<th colspan="2">
									备注
								</th>
							</tr>
							<s:iterator id="detail" value="fundApply.fadList">
								<tr>
									<td align="center">
										${detail.deptMonthBudgetName}
									</td>
									<td align="center">
										${detail.zjStyle}
									</td>
									<td align="center">
										${detail.voucherMoney}
									</td>
									<td align="center" colspan="2">
										${detail.pay_use}
									</td>
									<td align="center">
										是
									</td>
									<td align="center" colspan="2">
										${detail.more}
									</td>
								</tr>
							</s:iterator>
							<tr>
								<th align="center">
									总金额(元)
								</th>
								<td colspan="4">
									<font style="font-weight: bold;">${fundApply.totalMoney}
									</font>
								</td>	
								<th align="center">
									合同号
								</th>
								<th colspan="2">
									${fundApply.contractnum}
								</th>	
							</tr>

							<tr>
								<th>
									借款金额
								</th>
								<td colspan="9">
									<font style="font-weight: bold;"><span id="Result"></span>
									</font>
								</td>
							</tr>
							<tr>
								<th align="center">
									借款依据
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'合同':'合同','协议':'协议','通知':'通知','其他':'其他'}"
										name="fundApply.voucherbasis"></s:checkboxlist>
									<s:if test='fundApply.voucherbasis=="合同"'>
<%--										<a--%>
<%--											href="bargainAction_salBarContract.action?barContract.id=${fundApply.heTongId}&status=print" target="_block">查看合同</a>--%>
										<a href="systemFileAction_xiangxi.action?id=${fundApply.heTongId}&status=print" target="_block">查看合同</a>
									</s:if>
								</td>
							</tr>
							<tr>
								<th align="center">
									借款方式
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'银行转账':'银行转账','现金':'现金','其他':'其他'}"
										name="fundApply.voucherway"></s:checkboxlist>
								</td>
							</tr>


							<tr>
								<th align="center">
									借款条件
								</th>
								<td colspan="9">
									<s:checkboxlist
										list="#{'即付':'即付','TT30天':'TT30天','TT60天':'TT60天','TT90天':'TT90天','TT120天':'TT120天','TT120天以上':'TT120天以上','其他':'其他'}"
										name="fundApply.vouchercondition"></s:checkboxlist>
								</td>
							</tr>

							<tr>
								<th align="center">
									收款单位
								</th>
								<td>
									${fundApply.relationclient}
								</td>
								<th align="center">
									申请人所在部门
								</th>
								<td>
									${fundApply.approvaldept}
								</td>
								<th align="center">
									借款人
								</th>
								<td>
									${fundApply.approvalApplier}
								</td>
								<th align="center">
									日期
								</th>
								<td colspan="">
									${fundApply.baoxiaoDate}
								</td>
							</tr>
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
					<table class="table" style="width: 100%">
						<tr>
							<s:if test="'同意'==fundApply.epStattus">
									<input style="width: 80px; font-size: 18px;"
										onclick="pagePrint('printDiv','sy')" type="button" value="打印">
								</s:if>
								<s:else>
									<a
										href="CircuitRunAction_findAduitPage.action?id=${fundApply.epId}">待审批</a>
								</s:else>
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
//禁止右键和F12
document.onkeydown = function() {
	if ((event.keyCode == 116) || (window.event.ctrlKey)
			|| (window.event.shiftKey) || (event.keyCode == 122)) {
		event.keyCode = 0;
		event.returnValue = false;
	}

	if (window.event && window.event.keyCode == 123) {
		window.event.keyCode = 505;
		return false;
	}
	if (window.event && window.event.keyCode == 8) {
		alert(str + "\n请使用Del键进行字符的删除操作！");
		window.event.returnValue = false;
	}
if(window.event && window.event.keyCode == 505){ 
			　alert(str) 
			window.event.returnValue=false; 
			} 
	}
		function pagePrint1(){
			pagePrint('printDiv','sy');
			var pay_id1 = $("#pay_id1").val();
			var pay_status =$("#pay_status").val();
				$.ajax( {
				type : "POST",//请求类型
				url : "FundApplyAction_updatePay_ExecutionNode.action",//地址
				data : {//参数
					id : pay_id1,
					pay_status:pay_status
				},
				dataType : "json",//数据格式
				success : function(data) {
					//处理返回的数据
					}
			});
			
			
		}
$(function(){
	getQRCode (70,70,"<%=AlertMessagesServerImpl.pebsUrl%>FundApplyAction_findfundDetailedList.action?id=${id}","showQr");
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
						$("#aaa").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
						$("#bbb").append("("+data.data1[0].auditUserDept+")"+data.data1[i].auditDateTime+"");
						$("#ccc").append("<img src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"' height='50px' width='120px' align='middle'></img>");
						$("#ddd").append("("+data.data1[0].auditUserDept+")"+data.data1[0].auditDateTime+"");
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
