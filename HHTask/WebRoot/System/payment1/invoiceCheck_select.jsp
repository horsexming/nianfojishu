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
		<style type="text/css">
.tr { 
	height: 50px;
}

.ant-input-lg {
	padding: 6px 7px;
	height: 32px;
}

.ant-input {
	position: relative;
	display: inline-block;
	padding: 4px 7px;
	width: 100%;
	width: 280px;
	height: 28px;
	font-size: 12px;
	line-height: 1.5;
	color: rgba(0, 0, 0, 0.65);
	background-color: #fff;
	background-image: none;
	border: 1px solid #d9d9d9;
	border-radius: 4px;
	transition: all .3s;
}

.ant-form-explain,.ant-form-extra {
	color: #f04134;
	line-height: 1.5;
	display: none;
}

.has-error .ant-form-explain,.has-error .ant-form-split {
	color: #f04134;
}

.has-success.has-feedback:after {
	content: '\E630';
	color: #00a854;
}

.ant-btn-lg {
	padding: 0 15px;
	font-size: 14px;
	border-radius: 4px;
	height: 32px;
}

.ant-btn,.ant-btn:active,.ant-btn:focus {
	outline: 0;
}

.ant-btn {
	display: inline-block;
	margin-bottom: 0;
	font-weight: 500;
	text-align: center;
	touch-action: manipulation;
	cursor: pointer;
	background-image: none;
	border: 1px solid transparent;
	white-space: nowrap;
	line-height: 1.15;
	padding: 0 15px;
	font-size: 12px;
	border-radius: 4px;
	height: 28px;
	user-select: none;
	transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
	position: relative;
	color: rgba(0, 0, 0, 0.65);
	background-color: #fff;
	border-color: #d9d9d9;
}

.ant-btn-primary {
	border-color: #fff !important;
}

.ant-btn-primary {
	background: #2397CA !important;
}

.hgFSoW {
	float: left;
}

.eCuSsI {
	float: left;
}

.eorNLs {
	float: left;
}
.hgFSoW {
	align: center;
}
.bQwEeP {
	border-left-width: 1px; 
	border-left-style: solid; 
	border-left-color: rgb(160, 160, 160);
<%--	border-right-width: 1px; --%>
<%--	border-right-style: solid; --%>
<%--	border-right-color: rgb(160, 160, 160);--%>
}
.bQwEeR {
	border-right-width: 1px; 
	border-right-style: solid; 
	border-right-color: rgb(160, 160, 160);
}
.jGRTaj {
	border-right-width: 1px; 
	border-right-style: solid; 
	border-right-color: rgb(160, 160, 160);
}
</style>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body onload="convertCurrency(${invoiceCheck.jshj})">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center" >

						<span>
							<!-- react-text: 16 -->查验时间：<!-- /react-text -->
							<span style="color: rgb(106, 106, 106);">${invoiceCheck.addTime}</span>
							<!-- react-text: 16 -->查验人：<!-- /react-text -->
							<span style="color: rgb(106, 106, 106);">${invoiceCheck.addName}</span>
						<button type="button" class="ant-btn no-print ant-btn-primary" onclick="pagePrint('section-to-print','')"
								style="margin-left: 10px;">
								<span>打 印</span>
							</button>
						</span>
				<div id="section-to-print">
					<div class="wJjvU" style="width: 820px; height: 800px;" align="center">
						<p><b>
							<font size="5">${invoiceCheck.title}</font>
						</b></p>
						<div style="margin-bottom: 8px; width: 100%; overflow: hidden;">
							<div style="margin-right: 15px; float: left;">
								<span style="display: inline-block;">发票代码：</span><span
									class="hgFSoW1" style="display: inline-block;">${invoiceCheck.fpdm}</span>
							</div>
							<div style="margin-right: 15px; float: left;">
								<span style="display: inline-block;">发票号码：</span><span
									class="hgFSoW1" style="display: inline-block;">${invoiceCheck.fphm}</span>
							</div>
							<div style="margin-right: 15px; float: left;">
								<span style="display: inline-block;">校 验 码：</span><span
									class="hgFSoW1" style="display: inline-block;">${invoiceCheck.jym}</span>
							</div>
							<div style="margin-right: 15px; float: left;">
								<span style="display: inline-block;">开票日期：</span><span
									class="hgFSoW1" style="display: inline-block;">${invoiceCheck.kprq}</span>
							</div>
							<div style="margin-right: 15px; float: left;">
								<span style="display: inline-block;">机器编号：</span><span
									class="hgFSoW1" style="display: inline-block;">${invoiceCheck.jqbh}</span>
							</div>
						</div>
						<div class="cVaCHB">
							<div style="overflow: hidden; display: flex; border-width: 1px; border-style: solid; border-color: rgb(160, 160, 160);">
								<div class="glOAES" style="overflow: hidden; float: left;width:50.5%;">
									<div class="jGRTaj" style="float: left;border-right-width: 1px; border-right-style: solid; border-right-color: rgb(160, 160, 160);">
										<!-- react-text: 41 -->
										<b>&nbsp;&nbsp;购</b>
										<!-- /react-text -->
										<div style="height: 1px;"></div>
										<br>
										<!-- react-text: 44 -->
										<b>&nbsp;&nbsp;买</b>
										<!-- /react-text -->
										<div style="height: 1px;"></div>
										<br>
										<!-- react-text: 47 -->
										<b>&nbsp;&nbsp;方</b>
										<!-- /react-text -->
									</div>
									<div class="goXnUc" 
										style="border-right-width: 0px; border-right-style: solid; border-right-color: rgb(160, 160, 160);">
										<div style="overflow: hidden;">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;称 :
											</div>
											<span class="hgFSoW" >&nbsp;&nbsp;${invoiceCheck.gfmc}</span>
										</div>
										<div style="overflow: hidden;">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;纳税人识别号:
											</div>
											<span class="hgFSoW">&nbsp;&nbsp;${invoiceCheck.gfsbh}</span>
										</div>
										<div style="overflow: hidden;" align="left">
											<div style="float: left;">
												&nbsp;&nbsp;地&nbsp;址、&nbsp;电&nbsp;话:
											</div>
											<span class="hgFSoW">&nbsp;&nbsp;${invoiceCheck.gfdzdh}</span>
										</div>
										<div style="overflow: hidden;">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;开户行及账户:
											</div>
											<span class="hgFSoW">&nbsp;&nbsp;${invoiceCheck.gfyhzh}</span>
										</div>
									</div>
								</div>
								<div class="qFuDL" style="margin-left: 75px;">
									<div class="jGRTaj" style="border-left-width: 1px; border-left-style: solid; border-left-color: rgb(160, 160, 160);border-right-width: 1px; border-right-style: solid; border-right-color: rgb(160, 160, 160)">
										<!-- react-text: 63 -->
										&nbsp; <b>密</b>
										<!-- /react-text -->
										<div style="height: 1px;"></div>
										<br>
										<!-- react-text: 66 -->
										&nbsp; <b>码</b>
										<!-- /react-text -->
										<div style="height: 1px;"></div>
										<br>
										<!-- react-text: 69 -->
										&nbsp; <b>区</b>
										<!-- /react-text -->
									</div>
									<div class="goXnUc"></div>
								</div>
							</div>
							<div style="overflow: hidden ;border-right-width: 1px; border-right-style: solid; border-right-color: rgb(160, 160, 160);
							border-left-width: 1px; border-left-style: solid; border-left-color: rgb(160, 160, 160)">
								<div style="display: flex;">
									<div class="bQwEeP" style="width: 25%;">
										货物或应税劳务、服务名称
									</div>
									<div class="bQwEeP" style="width: 15%;">
										规&nbsp;格&nbsp;型&nbsp;号
									</div>
									<div class="bQwEeP" style="width: 5%;">
										单&nbsp;位
									</div>
									<div class="bQwEeP" style="width: 7%;">
										数&nbsp;&nbsp;量
									</div>
									<div class="bQwEeP" style="width: 15%;">
										单&nbsp;&nbsp;&nbsp;&nbsp;价
									</div>
									<div class="bQwEeP" style="width: 15%;">
										金&nbsp;&nbsp;&nbsp;&nbsp;额
									</div>
									<div class="bQwEeP" style="width: 5%;">
										税&nbsp;&nbsp;率
									</div>
									<div class="bQwEeP"
										style="border-right-width: 0px; flex-grow: 1;">
										税&nbsp;&nbsp;额
									</div>
								</div>
								<s:iterator value="invoiceCheck.details" id="tool"
									status="pageStatus">
									<div style="display: flex;">
										<div class="bQwEeP" style="width: 25%; text-align: left;">
											<span class="hgFSoW">
												<!-- react-text: 84 -->${tool.hwmc}<!-- /react-text -->
											</span>
										</div>
										<div class="bQwEeP" style="width: 15%; text-align: right;">
											<span class="hgFSoW">${tool.ggxh}</span>
										</div>
										<div class="bQwEeP" style="width: 5%; text-align: center;">
											${tool.dw}
										</div>
										<div class="bQwEeP" style="width: 7%; text-align: center;">
											${tool.sl}
										</div>
										<div class="bQwEeP" style="width: 15%; text-align: center;">
											${tool.dj}
										</div>
										<div class="bQwEeP" style="width: 15%; text-align: center;">
											${tool.je}
										</div>
										<div class="bQwEeP" style="width: 5%; text-align: center;">
											${tool.slv}%
										</div>
										<div class="bQwEeP"
											style="border-right-width: 0px; flex-grow: 1; text-align: center;">
											${tool.se}
										</div>
									</div>
<%--								<s:property value="#pageStatus.index+1" />--%>
								</s:iterator>
								<div style="display: flex;">
									<div class="bQwEeP" style="width: 25%;">
										合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计
									</div>
									<div class="bQwEeP" style="width: 15%;"></div>
									<div class="bQwEeP" style="width: 5%;"></div>
									<div class="bQwEeP" style="width: 7%;"></div>
									<div class="bQwEeP" style="width: 15%;"></div>
									<div class="bQwEeP" style="width: 15%; text-align: right;">
										<span class="hgFSoW">￥${invoiceCheck.je}</span>
									</div>
									<div class="bQwEeP" style="width: 5%;"></div>
									<div class="bQwEeP"
										style="border-right-width: 0px; flex-grow: 1; text-align: right;">
										<span class="hgFSoW">￥${invoiceCheck.se}</span>
									</div>
								</div>
								<div style="display: flex; border-top-width: 1px; border-top-style: solid; border-top-color: rgb(160, 160, 160);">
									<div class="bQwEeP" style="width: 25%;">
										价&nbsp;税&nbsp;合&nbsp;计&nbsp;（&nbsp;大&nbsp;写&nbsp;）&nbsp;
									</div>
									<div class="bQwEeP"
										style="flex-grow: 1; border-right-width: 0px; text-align: left;">
										<b><span class="hgFSoW" style="margin-left: 15px;" id="Result">
										</span></b>
									</div>
									<div class="bQwEeP"
										style="border-right-width: 0px; width: 30%; text-align: left;">
										<!-- react-text: 117 -->
										（&nbsp;小&nbsp;&nbsp;&nbsp;写&nbsp;）
										<!-- /react-text -->
										<span class="hgFSoW" style="margin-left: 15px;">￥${invoiceCheck.jshj}</span>
									</div>
								</div>
							</div>
							<div style="overflow: hidden; display: flex; border-width: 1px; border-style: solid; border-color: rgb(160, 160, 160);">
								<div class="glOAES" style="width: 60%;">
									<div class="jGRTaj" style="float: left">
										<!-- react-text: 122 -->
										<b>&nbsp;销</b>
										<!-- /react-text -->
										<br>
										<div style="height: 5px;"></div>
										<!-- react-text: 125 -->
										<b>&nbsp;售</b>
										<!-- /react-text -->
										<br>
										<div style="height: 5px;"></div>
										<!-- react-text: 128 -->
										<b>方</b>
										<!-- /react-text -->
									</div>
									<div class="goXnUc"
										style="border-right-width: 1px; border-right-style: solid; border-right-color: rgb(160, 160, 160);">
										<div style="overflow: hidden;">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;称 :
											</div>
											<span class="hgFSoW" >&nbsp;&nbsp;${invoiceCheck.xfmc}</span>
										</div>
										<div style="overflow: hidden;">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;纳税人识别号:
											</div>
											<span class="hgFSoW">&nbsp;&nbsp;${invoiceCheck.xfsbh}</span>
										</div>
										<div style="overflow: hidden;" align="left">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;地&nbsp;址、&nbsp;电&nbsp;话:
											</div>
											<span class="hgFSoW">&nbsp;&nbsp;${invoiceCheck.xfdzdh}</span>
										</div>
										<div style="overflow: hidden;">
											<div class="eCuSsI" style="float: left;">
												&nbsp;&nbsp;开户行及账户:
											</div>
											<span class="hgFSoW">&nbsp;&nbsp;${invoiceCheck.xfyhzh}</span>
										</div>
									</div>
								</div>
								<div class="qFuDL" style="width: 30%; margin-left: 5px;">
									<div class="jGRTaj" style="float: left">
										<!-- react-text: 144 -->
										<b>备</b>
										<!-- /react-text -->
										<div style="height: 10px;"></div>
										<br>
										<!-- react-text: 147 -->
										<b>注</b>
										<!-- /react-text -->
									</div>
									<div class="goXnUc" style="float: left">
										<span class="hgFSoW">&nbsp;${invoiceCheck.memo}</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
			hspace="0" vspace="0" frameborder="0" scrolling="yes"
			style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("fpdm", "发票代码")) {
		return false;
	}
	if (!validateText("fphm", "发票号码")) {
		return false;
	}
	if (!validateText("kprq", "开票日期")) {
		return false;
	}
	if (II == 1) {
		if (!validateText("jym", "校验码后6位")) {
			return false;
		}
	} else {
		if (!validateText("je", "税前金额")) {
			return false;
		}
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "" || textValue == "0") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

var II = 0;

function jianyanfpdm(){
	var fpdm = $.trim($("#fpdm").val());
	if(fpdm!=null&&fpdm!=''){
		$("#fa2").hide();
		$("#jym6").hide();
		$("#je6").show();
		II = 0;
		if(isNaN(fpdm)){
			$("#fa1").show();
		}else{
			if(fpdm.length==10){
				var fpdm8 = fpdm.substring(7,8);
				if(fpdm8==1||fpdm8==2||fpdm8==5||fpdm8==7){
					$("#fa1").hide();
				}else if(fpdm8==3||fpdm8==6){
					$("#fa1").hide();
					$("#jym6").show();
					$("#je6").hide();
					II = 1;
				}else{
					$("#fa1").show();
				}
			}else if(fpdm.length==12){
				var fpdm1 = fpdm.substring(0,1);
				var fpdm11 = fpdm.substring(10,12);
				if(fpdm1==0&&(fpdm11=='04'||fpdm11=='05'||fpdm11=='06'||fpdm11=='07'||fpdm11=='11'||fpdm11=='12')){
					$("#fa1").hide();
					$("#jym6").show();
					$("#je6").hide();
					II = 1;
				}else{
					$("#fa1").show();
				}
			}else{
				$("#fa1").show();
			}
		}
	}else{
		$("#fa1").hide();
		$("#fa2").show();
	}
}
function jianyanfphm(){
	var fpdm = $.trim($("#fphm").val());
	if(fpdm!=null&&fpdm!=''){
		$("#fa4").hide();
		if(isNaN(fpdm)){
			$("#fa3").show();
		}else{
			if(fpdm.length==8){
				$("#fa3").hide();
			}else{
				$("#fa3").show();
			}
		}
	}else{
		$("#fa3").hide();
		$("#fa4").show();
	}
}

function bijiaoDate(){
	var kprq = $.trim($("#kprq").val());
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var day = new Date();
	//初始化时间
	Year = day.getFullYear();
	Month = day.getMonth()+1;
	Day = day.getDate();
	var CurrentDate = "";
	CurrentDate += Year;
	if (Month >= 10 ){
		CurrentDate += Month;
	}else{
		CurrentDate += "0" + Month;
	}
	if (Day >= 10){
		CurrentDate += Day ;
	}else{
		CurrentDate += "0" + Day ;
	}
	if(kprq>CurrentDate){
		alert("开票日期不可大于"+CurrentDate);
		$("#kprq").val(CurrentDate);
	}
}

function date(){
	if(controldate==""){
		alert('日期不能为空');
	return false;}
	else{var day = new Date();
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var CurrentDate = "";
	//初始化时间
	Year = day.getFullYear();
	Month = day.getMonth()+1;
	Day = day.getDate();
	CurrentDate += Year + "-";
	if (Month >= 10 ){
		CurrentDate += Month + "-";
	}else{
		CurrentDate += "0" + Month + "-";
	}
	if (Day >= 10 ){
		CurrentDate += Day ;}
	else{
		CurrentDate += "0" + Day ;
	} //alert(CurrentDate);//当前日期//http://www.unitymanual.com/sitemap.xmlvar startDate = new Date(CurrentDate.replace("-",",")).getTime() ;var endDate = new Date(controldate.replace("-",",")).getTime() ; if( startDate > endDate ){alert('选择日期不能小于当前日期！');document.getElementById("sendDate").focus();return false;}else{return true;}}} 
	}
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
	document.getElementById("Result").innerHTML = outputCharacters;
}
function add() {
	var url = "<%=request.getContextPath()%>/AccessEquipmentAction_toAddResAccessJi.action";
	$("#showProcess").attr("src", url);
}
<%--$("#cabOpenOrder").keyup(function() {--%>
<%--	var tmptxt = $(this).val();--%>
<%--	$(this).val(tmptxt.replace(/\D|^0/g, ''));--%>
<%--})--%>
</script>
	</body>
</html>
