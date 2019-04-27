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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h2>
					制作周转流水卡片(Produced water turnover cards)
				</h2>
				<form id="WaterCardForm" action="RunningWaterCardAction!save.action"
					onsubmit="return checkForm()" method="post">
					<table class="table">
						<tr>
							<th>
								制卡类型(Card type):
							</th>
							<td>
								<select name="runningWaterCard.rwStyle" id="rwStyle"
									onblur="selectrwStyle(this)">
									<s:if test="%{null!=runningWaterCard.rwStyle}">
										<option value="${runningWaterCard.rwStyle}">
											${runningWaterCard.rwStyle}
										</option>
									</s:if>
									<s:else>
										<option selected="selected" value="周转单">
											周转单(Turnover single)
										</option>
										<option value="周转卡">
											周转卡(Turnover Card)
										</option>
									</s:else>
								</select>
							</td>
							<th>
								生产类型(Production Type):
							</th>
							<td>
								<select name="runningWaterCard.productStyle">
									<s:if test="%{null!=runningWaterCard.rwStyle}">
										<option>
											${runningWaterCard.productStyle}
										</option>
									</s:if>
									<s:else>
										<option value="批产">
											批产(Batch production)
										</option>
										<option value="试制">
											试制(Trial)
										</option>
									</s:else>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								客户(Customers):
							</th>
							<td>
								<input type="text" id="customer"
									name="runningWaterCard.customer"
									value="${runningWaterCard.customer}" />
							</td>
							<th>
								车型(Models):
							</th>
							<td>
								<input type="text" id="carStyle"
									name="runningWaterCard.carStyle"
									value="${runningWaterCard.carStyle}" />
							</td>
						</tr>
						<tr>
							<th>
								件号(Part No.):
							</th>
							<td>
								<input type="text" id="markId" name="runningWaterCard.markId"
									value="${runningWaterCard.markId}" />
							</td>
							<th>
								卡号(Card number):
							</th>
							<td>
								<div id="rwCard" style="display: none;">
									<input type="text" id="cardNum" name="runningWaterCard.cardNum"
										value="${runningWaterCard.cardNum}" />
								</div>
								<font style="color: red; size: 12;">${message }</font>
							</td>
						</tr>
						<tr>
							<th>
								零件名(Parts name):
							</th>
							<td>
								<input type="text" id="partName"
									name="runningWaterCard.partName"
									value="${runningWaterCard.partName}" />
							</td>
						</tr>
						<th colspan="4">
							<input type="submit" value="提交(Submit)" />
							&nbsp;
							<input type="reset" value="放弃(Give up)" />
						</th>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//选择制卡类型
function selectrwStyle(obj) {
	var rwStyle = obj.value;
	if ("周转卡" == rwStyle) {
		document.getElementById("rwCard").style.display = "block";
	} else {
		document.getElementById("rwCard").style.display = "none";
		$("#cardNum").val();
	}
}
//提交验证
function checkFormm() {
	var customer = document.getElementById("customer");
	var carStyle = document.getElementById("carStyle");
	var markId = document.getElementById("markId");
	var cardNum = document.getElementById("cardNum");
	if (markId.value == "") {
		alert("件号不能为空!");
		markId.focus();
		return false;
	} else if (customer.value == "") {
		alert("客户不能为空!");
		customer.focus();
		return false;
	} else if (carStyle.value == "") {
		alert("车型不能为空!");
		carStyle.focus();
		return false;
	} else if (cardNum.value == "") {
		alert("卡号不能为空!");
		cardNum.focus();
		return false;
	}

}
function checkCard(obj) {
	var card = obj.value;
	alert
	$.ajax( {
		type : "POST",
		url : "RunningWaterCardAction!findCardByCard.action",
		data : {
			tag : card
		},
		dataType : "json",
		success : function(msg) {
			alert(msg);
			if (msg == "false") {
				alert("该卡号已存在,请核实!!!");
				$("#cardNum").val();
				return "no";
			} else {
				return "ok";
			}

		}
	});
}
</script>
	</body>
</html>
