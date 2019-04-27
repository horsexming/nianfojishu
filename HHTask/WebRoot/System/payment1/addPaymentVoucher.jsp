<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
	<body>
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
				<h3>
					${companyInfo.name}费用借款单
				</h3>

				<form
					action="paymentVoucherAction_saveBorrowings.action?paytag=<s:property value="#parameters.PaymentTag"/>"
					method="post" onsubmit="return checkForm()">
					<table style="width: 100px;" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									借款单位(个人)
								</th>
								<td>
									<input type="text" id="unitname" name="paymentVoucher.unitname" />
								</td>

								<th>
									收款单位
								</th>
								<td>
									<input type="text" id="relationclient"
										name="paymentVoucher.relationclient" value="" />
								</td>

								<th colspan="4">
									部门
									<input type="text" id="approvaldept" readonly="readonly"
										name="paymentVoucher.approvaldept"
										value="${sessionScope.Users.dept}" />
									合同号
									<input type="text" id="contractnum"
										name="paymentVoucher.contractnum" />
								</th>

							</tr>
							<tr>
								<th>
									借款依据
								</th>
								<td colspan="7">
									<input type="radio" id="budgetDept1"
										name="paymentVoucher.voucherbasis" value="合同"
										checked="checked" onclick="showHt()" />
									<label for="budgetDept1">
										合同
									</label>
									<input type="radio" id="budgetDept2"
										name="paymentVoucher.voucherbasis" value="发票"
										onclick="showHt()" />
									<label for="budgetDept2">
										发票
									</label>
									<input type="radio" id="budgetDept3"
										name="paymentVoucher.voucherbasis" value="协议"
										onclick="showHt()" />
									<label for="budgetDept3">
										协议
									</label>
									<input type="radio" id="budgetDept4"
										name="paymentVoucher.voucherbasis" value="通知"
										onclick="showHt()" />
									<label for="budgetDept4">
										通知
									</label>
									<input type="radio" id="budgetDept5"
										name="paymentVoucher.voucherbasis" value="其他"
										onclick="showHt()" />
									<label for="budgetDept5">
										其他
									</label>
									<select id="showHeTong" name="paymentVoucher.heTongId"
										style="width: 150px; display: none" onclick="showHeTo()">
									</select>

								</td>
							</tr>
							<tr>

							</tr>
							<tr>
								<th>
									借款性质
								</th>
								<td colspan="7">
									<input type="radio" id="voucherNature1"
										name="paymentVoucher.voucherNature" value="预付" />
									<label for="voucherNature1">
										预付
									</label>
									<input type="radio" id="voucherNature2"
										name="paymentVoucher.voucherNature" value="中间付款" />
									<label for="voucherNature2">
										中间付款
									</label>
									<input type="radio" id="voucherNature3"
										name="paymentVoucher.voucherNature" value="余款" />
									<label for="voucherNature3">
										余款
									</label>
									<input type="radio" id="voucherNature4"
										name="paymentVoucher.voucherNature" value="质保金" />
									<label for="voucherNature4">
										质保金
									</label>
									<input type="radio" id="voucherNature5"
										name="paymentVoucher.voucherNature" value="借款"
										checked="checked" />
									<label for="voucherNature5">
										借款
									</label>
									<input type="radio" id="voucherNature6"
										name="paymentVoucher.voucherNature" value="冲账" />
									<label for="voucherNature6">
										冲账
									</label>
									<input type="radio" id="voucherNature7"
										name="paymentVoucher.voucherNature" value="其他" />
									<label for="voucherNature7">
										其他
									</label>
								</td>
							</tr>

							<tr>
								<th>
									借款方式
								</th>
								<td colspan="7">
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" checked="checked" />
									<label for="voucherway1">
										银行
									</label>
									<input type="radio" id="voucherway2"
										name="paymentVoucher.voucherway" value="汇票" />
									<label for="voucherway2">
										汇票
									</label>
									<input type="radio" id="voucherway3"
										name="paymentVoucher.voucherway" value="汇兑" />
									<label for="voucherway3">
										汇兑
									</label>
									<input type="radio" id="voucherway4"
										name="paymentVoucher.voucherway" value="支票" />
									<label for="voucherway4">
										支票
									</label>
									<input type="radio" id="voucherway5"
										name="paymentVoucher.voucherway" value="贷记" />
									<label for="voucherway5">
										贷记
									</label>
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="现金" />
									<label for="voucherway6">
										现金
									</label>
									<input type="radio" id="voucherway8"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway8">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</td>
							</tr>
							<tr>
								<th>
									借款情况
								</th>
								<td colspan="7">
									<input type="radio" id="vouchersituation1"
										name="paymentVoucher.vouchersituation" value="总额"
										checked="checked" />
									<label for="vouchersituation1">
										总额
									</label>
									<input type="radio" id="vouchersituation2"
										name="paymentVoucher.vouchersituation" value="已支付" />
									<label for="vouchersituation2">
										已支付
									</label>
									<input type="radio" id="vouchersituation3"
										name="paymentVoucher.vouchersituation" value="本次应付" />
									<label for="vouchersituation3">
										本次应付
									</label>
									<input type="radio" id="vouchersituation4"
										name="paymentVoucher.vouchersituation" value="累计支付" />
									<label for="vouchersituation4">
										累计支付
									</label>
									<input type="radio" id="vouchersituation5"
										name="paymentVoucher.vouchersituation" value="余额" />
									<label for="vouchersituation5">
										余额
									</label>
								</td>
							</tr>

							<tr>
								<th>
									借款条件
								</th>
								<td colspan="7">
									<input type="radio" id="vouchercondition1" onclick=""
										name="paymentVoucher.vouchercondition" value="即付"
										checked="checked" />
									<label for="vouchercondition1">
										即付
									</label>
									<input type="radio" id="vouchercondition2"
										name="paymentVoucher.vouchercondition" value="TT30天" />
									<label for="vouchercondition2">
										TT30天
									</label>
									<input type="radio" id="vouchercondition3"
										name="paymentVoucher.vouchercondition" value="TT60天" />
									<label for="vouchercondition3">
										TT60天
									</label>
									<input type="radio" id="vouchercondition4"
										name="paymentVoucher.vouchercondition" value="TT90天" />
									<label for="vouchercondition4">
										TT90天
									</label>
									<input type="radio" id="vouchercondition5"
										name="paymentVoucher.vouchercondition" value="TT120天" />
									<label for="vouchercondition5">
										TT120天
									</label>
									<input type="radio" id="vouchercondition6"
										name="paymentVoucher.vouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</td>
							</tr>

							<tr>
								<th>
									类别
								</th>
								<td colspan="7">
									<input type="radio" id="category1"
										name="paymentVoucher.category" value="总务性采购" checked="checked" />
									<label for="category1">
										总务性采购
									</label>
									<input type="radio" id="category2"
										name="paymentVoucher.category" value="原材料采购" />
									<label for="category2">
										原材料采购
									</label>
									<input type="radio" id="category3"
										name="paymentVoucher.category" value="工程设备采购" />
									<label for="category3">
										工程设备采购
									</label>
									<input type="radio" id="category4"
										name="paymentVoucher.category" value="其他" />
									<label for="category4">
										其他
									</label>
								</td>
							</tr>
							<tr>
								<th colspan="8">
									借款明细
								</th>
							</tr>
							<tr>
								<th>
									业务内容
								</th>
								<th>
									是否借款
								</th>
								<th>
									科目
								</th>
								<th>
									备注
								</th>
								<th>
									付款金额
								</th>
								<th colspan="3">
									用途
								</th>
							</tr>
							<tr align="left">
								<th>
									<textarea cols="20" name="listpaymentDetail[0].businesscontent"></textarea>
								</th>
								<th align="center">
									<input type="radio" id="isOk1" name="listpaymentDetail[0].isOk"
										value="是" checked="checked" />
									<label for="isOk1">
										是
									</label>
									<input type="radio" id="isOk2" name="listpaymentDetail[0].isOk"
										value="否" />
									<label for="isOk2">
										否
									</label>
								</th>
								<th align="center">
									<select id="dept" style="width: 200px;"
										onMouseOver="deptSelect(this)"
										name="listpaymentDetail[0].dept">
										<option>
											选择科目
										</option>
									</select>
								</th>
								<th>
									<input type="text" name="listpaymentDetail[0].remark" value="">
								</th>
								<th>
									<input type="text" onKeyUp="hejiJine();comparecount(0)" id="h0"
										name="listpaymentDetail[0].voucherMoney" value="">
								</th>

								<th colspan="3" align="center">
									<textarea cols="40" rows="2"
										name="listpaymentDetail[0].pay_use"></textarea>
								</th>
							</tr>
							<tr id="uploadtr">
								<th align="left">
									<input type="button" id="inforButton_1"
										onclick="saveHKInfor(this,1)" value="添加明细" />
								</th>
								<th width="29%" align="left" colspan="2">
									<input id="deleteItem" style="display: none;" type="button"
										id="inforButton_2" onclick="delInfor()" value="删除明细" />
								</th>
								<th>
									合计金额(人民币)
								</th>
								<td>
									<span id="hejiMoney"><font color="red"><label
												id="allMoney"></label> </font> </span>
								</td>
								<th>
									借款金额(人民币)
								</th>
								<td colspan="2">
									<span id="hejiMoney"><input type="text"
											readonly="readonly" id="voucherMoney"
											name="paymentVoucher.voucherMoney" value=""> </span>
								</td>
							</tr>
							<tr>
								<td colspan="8" align="center">
									<input type="submit" value="提交" id="loanId" disabled="disabled"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
								</td>
							</tr>
							<tr>
								<td colspan="8" style="font-size: 12px;">
									备注：
									<br>
									1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
									<br />
									2、借款时，请明确借款方式，并在相应的空格内打勾。
									<br />
									3、“借款人”为实际经费使用者。
									<br />
								</td>
							</tr>
							<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(function() {
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
	showHt();
})

function showHt() {
	var val_payPlatform = $('input[name="paymentVoucher.voucherbasis"]:checked')
			.val();
	if (val_payPlatform == "合同") {
		$("#showHeTong").show();
		$.ajax( {
			type : "POST",
			url : "bargainAction_getLoginHeTong.action",
			dataType : "json",
			success : function(data) {
				$("#showHeTong").empty();//清空值
				$.each(data, function(i, n) {
					$("#showHeTong").append(
							"<option value='" + n.id + "' >" + n.contract_num
									+ "</option>");
				})
				//加载数据成功
				showHeTo();
			}
		});
	} else {
		$("#showHeTong").empty();
		$("#showHeTong").hide();
		document.getElementById("loanId").disabled = false
	}
}

function showHeTo() {
	if ($("#showHeTong").val() != null && $("#showHeTong").val() != "") {
		document.getElementById("loanId").disabled = false

	} else {
		document.getElementById("loanId").disabled = true
	}
}

function deptSelect(obj) {
	var selected = obj.id;//获取到当前列的id
	$.ajax( {
		type : "POST",
		url : "paymentVoucherAction_findSubjectName.action",
		data : {},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, n) {
				$("#" + selected + "").append(
						"<option value='" + n + "' >" + n + "</option>");
			})
		}
	});

}
var lineCount = 1;
var begAddLineNum = 10;

//删除明细
function delInfor() {
	//alert(begAddLineNum);
	complexselectedlist.deleteRow(begAddLineNum);
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 11) {
		document.getElementById("deleteItem").style.display = "none";
	}
	hejiJine();
}
function hejiJine() {
	var total = 0;
	for ( var t = 0; t < lineCount; t++) {
		var id = "h" + t;
		var cur = document.getElementById(id).value;
		total = total + parseFloat(cur);
	}
	document.getElementById("allMoney").innerHTML = total;
	document.getElementById("voucherMoney").value = total;//把总金额赋给借款总金额
}
//添加明细
function saveHKInfor(obj, few) {
	
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	var x = _tr.insertCell(0);
	x.innerHTML = "<textarea cols=\"20\" name=\"listpaymentDetail[" + lineCount
			+ "].businesscontent\" ></textarea>";

	var x1 = _tr.insertCell(1);
	x1.innerHTML = "<input type=\"radio\" id=\"isOk1\" name=\"listpaymentDetail["
			+ lineCount
			+ "].isOk\" value='是' "
			+ " checked=\"checked\" /> 是 <input type=\"radio\" id=\"isOk2\"  "
			+ "name=\"listpaymentDetail["
			+ lineCount
			+ "].isOk\" value='否' />否";

	var x2 = _tr.insertCell(2);
	x2.innerHTML = "<select id='dept" + lineCount
			+ "' onMouseOver=\"deptSelect(this)\"  name='listpaymentDetail["
			+ lineCount + "].dept' style='width: 200px;'  >"
			+ "<option>选择科目</option></select>";

	var x3 = _tr.insertCell(3);
	x3.innerHTML = "<input type=\"text\" name=\"listpaymentDetail[" + lineCount
			+ "].remark\">";

	var x4 = _tr.insertCell(4);
	x4.innerHTML = "<input type=\"text\" onKeyUp=\"hejiJine();compareCount("
			+ lineCount + ")\"  id=\"h" + lineCount
			+ "\" name=\"listpaymentDetail[" + lineCount + "].voucherMoney\">";

	var x5 = _tr.insertCell(5);
	x5.setAttribute('colspan', '2');
	x5.innerHTML = "<textarea cols='40' rows='2' name='listpaymentDetail["
			+ lineCount + "].pay_use'></textarea>";
	lineCount++;
	document.getElementById("deleteItem").style.display = "block";
}

//提交验证
function checkForm() {
	var unitname = document.getElementById("unitname");
	var relationclient = document.getElementById("relationclient");
	var accreditationnum = document.getElementById("accreditationnum");
	var contractnum = document.getElementById("contractnum");
	var voucherMoney = document.getElementById("voucherMoney");
	if (unitname.value == "") {
		alert("借款单位（人）不能为空!");
		unitname.focus();
		return false;
	}
	/*else if (relationclient.value == "") {
		alert("关联客户不能为空!");
		relationclient.focus();
		return false;
	} else if (accreditationnum.value == "") {
		alert("评审编号不能为空!");
		accreditationnum.focus();
		return false;
	}else if (contractnum.value == "") {
		alert("合同号不能为空!");
		contractnum.focus();
		return false;
	}*/
	else if (voucherMoney.value == "") {
		alert("借款金额不能为空!");
		voucherMoney.focus();
		return false;
	}
}
</script>

</html>
