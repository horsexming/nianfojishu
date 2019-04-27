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

.zhifu_tr {
	display: none;
}
</style>
		<%
			Users user = (Users) session.getAttribute("Users");
		%>
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
					${companyInfo.name}费用应付登记单
				</h3>

				<form id="fundForm" enctype="multipart/form-data" method="post"
					onsubmit="return checkForm()">
					<table style="width: 100px;" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									单位(个人)
								</th>
								<td>
									<input type="text" id="unitname" name="fundApply.unitname"
										value="${sessionScope.Users.name}" />
								</td>

								<th>
									收款单位
								</th>
								<td>
									<select id="relationclient" name="fundApply.relationclientId">
										<option>
											请选择
										</option>
									</select>
								</td>
								<th>
									申请部门
								</th>
								<td colspan="4">
									<input type="text" id="approvaldept" readonly="readonly"
										name="fundApply.approvaldept"
										value="${sessionScope.Users.dept}" />
								</td>
							</tr>
							<tr>
								<th>
									类型
								</th>
								<td>
									<select id="itemabout" name="fundApply.about"
										onchange="changeAbout()">
										<%--									<option>预算</option>--%>
										<option>
											项目
										</option>
										<option>
											KVP
										</option>
										<option>
											冲账
										</option>
										<option>
											外购
										</option>
										<option>
											外购(历史)
										</option>
										<option>
											外委
										</option>
										<option>
											非主营业务应付
										</option>
									</select>
								</td>
								<th>
									资金使用状态
								</th>
								<td colspan="1">
									<input type="radio" id="zhifuoryufu1"
										name="fundApply.zhifuoryufu" value="支付"
										onclick="showzhifu_tr()" />
									<label for="zhifuoryufu1">
										支付(报销)
									</label>
									<input type="radio" id="zhifuoryufu2"
										name="fundApply.zhifuoryufu" value="预付"
										onclick="hidzhifu_tr()" checked="checked" />
									<label for="zhifuoryufu2">
										预付(借款)
									</label>
								</td>
								<th>
									承担部门
								</th>
								<td colspan="6">
									<input type="radio" id="budgetDept"
										onclick="chagebaoxiaoClass()" name="fundApply.isSelfDept"
										value="self" checked="checked" />
									本部门
									<input type="radio" id="budgetDept2"
										"
										name="fundApply.isSelfDept" value="others" />
									跨部门
								</td>
							</tr>
							<tr class="zhifu_tr">
								<%--								<th>--%>
								<%--									付款方式--%>
								<%--								</th>--%>
								<%--								<td>--%>
								<%--									<select name="fundApply.payStyle" id="payStyle" class="zhifu_" disabled="disabled">--%>
								<%--										<option value="现金">--%>
								<%--											现金--%>
								<%--										</option>--%>
								<%--										<option value="银行对公转账">--%>
								<%--											银行对公转账--%>
								<%--										</option>--%>
								<%--										<option value="归还借款">--%>
								<%--											归还借款--%>
								<%--										</option>--%>
								<%--										<option value="冲账">--%>
								<%--											冲账--%>
								<%--										</option>--%>
								<%--										<option value="其他">--%>
								<%--											其他--%>
								<%--										</option>--%>
								<%--									</select>--%>
								<%--								</td>--%>
								<th>
									报销日期
								</th>
								<td>
									<input class="Wdate" type="text" name="fundApply.baoxiaoDate"
										size="15" id="baoxiaoDate" class="zhifu_"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<th>
									<%--									预算月份--%>
								</th>
								<td colspan="5">
									<%--									<input type="text" id="planMonth" name="fundApply.planMonth" class="zhifu_" disabled="disabled"--%>
									<%--										style="background-color: Gray" value="" readonly="readonly" />--%>
								</td>
							</tr>
							<tr class="zhifu_tr">
								<th>
									发票类型
								</th>
								<td>
									<select name="fundApply.isTax" class="zhifu_" class="zhifu_"
										disabled="disabled">
										<option value="增值税发票">
											增值税发票
										</option>
										<option value="普通发票">
											普通发票
										</option>
										<option value="单据">
											单据
										</option>
									</select>
								</td>
								<th>
									附件张数
								</th>
								<td>
									<select name="fundApply.attachmentsCount" class="zhifu_"
										disabled="disabled">
										<%
											for (int i = 0; i < 301; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>

									</select>
								</td>
								<th>
									附件
								</th>
								<td>
									<input id="fj" name="fj" type="file" value="选择">
								</td>
							</tr>
							<tr class="zhifu_tr">
								<th>
									发票号码
								</th>
								<td colspan="7">
									<input size="100px" type="text" id="invoiceNum" class="zhifu_"
										disabled="disabled" name="fundApply.invoiceNum" />
									(注：多张发票逗号分开)
								</td>
							</tr>
							<tr class="zhifu_tr">
								<th>
									说明
								</th>
								<td colspan="7">
									<textarea cols="88" name="fundApply.explain" id="explain"
										class="zhifu_" disabled="disabled"></textarea>
								</td>
							</tr>
							<tr>
								<th>
									支付依据
								</th>
								<td colspan="7">
									<input type="radio" id="voucherbasis1"
										name="fundApply.voucherbasis" value="合同" checked="checked"
										onclick="showHt()" />
									<label for="voucherbasis1">
										合同
									</label>
									<input type="radio" id="voucherbasis2" style="display: none"
										name="fundApply.voucherbasis" value="发票" onclick="showHt()" />
									<label id="for_voucherbasis2" for="voucherbasis2"
										style="display: none">
										发票
									</label>
									<input type="radio" id="voucherbasis3"
										name="fundApply.voucherbasis" value="协议" onclick="showHt()" />
									<label for="voucherbasis3">
										协议
									</label>
									<input type="radio" id="voucherbasis4"
										name="fundApply.voucherbasis" value="通知" onclick="showHt()" />
									<label for="voucherbasis4">
										通知
									</label>
									<input type="radio" id="voucherbasis5"
										name="fundApply.voucherbasis" value="其他" onclick="showHt()" />
									<label for="voucherbasis5">
										其他
									</label>
									<select id="showHeTong" name="fundApply.heTongId"
										style="width: 150px; display: none" onclick="showHeTo()">
									</select>

								</td>
							</tr>
							<%--							<tr>--%>
							<%--								<th>--%>
							<%--									支付性质--%>
							<%--								</th>--%>
							<%--								<td colspan="7">--%>
							<%--									<input type="radio" id="voucherNature1"--%>
							<%--										name="fundApply.voucherNature" value="预付" onclick="changenature('预付')"/>--%>
							<%--									<label for="voucherNature1">--%>
							<%--										预付--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="voucherNature2"--%>
							<%--										name="fundApply.voucherNature" value="中间付款" onclick="changenature('中间付款')"/>--%>
							<%--									<label for="voucherNature2">--%>
							<%--										中间付款--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="voucherNature3"--%>
							<%--										name="fundApply.voucherNature" value="余款" onclick="changenature('余款')"/>--%>
							<%--									<label for="voucherNature3">--%>
							<%--										余款--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="voucherNature4"--%>
							<%--										name="fundApply.voucherNature" value="质保金" onclick="changenature('质保金')"/>--%>
							<%--									<label for="voucherNature4">--%>
							<%--										质保金--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="voucherNature5"--%>
							<%--										name="fundApply.voucherNature" value="借款" onclick="changenature('借款')"--%>
							<%--										checked="checked" />--%>
							<%--									<label for="voucherNature5">--%>
							<%--										借款--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="voucherNature6"--%>
							<%--										name="fundApply.voucherNature" value="冲账" onclick="changenature('冲账')"/>--%>
							<%--									<label for="voucherNature6">--%>
							<%--										冲账--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="voucherNature7"--%>
							<%--										name="fundApply.voucherNature" value="其他" onclick="changenature('其他')"/>--%>
							<%--									<label for="voucherNature7">--%>
							<%--										其他--%>
							<%--									</label>--%>
							<%--									<input id="voucherNature" type="hidden" value="借款">--%>
							<%--								</td>--%>
							<%--							</tr>--%>

							<tr>
								<th>
									支付方式
								</th>
								<td colspan="7">
									<input type="radio" id="voucherway1"
										name="fundApply.voucherway" value="银行转账" checked="checked"
										onclick="zfType('银行转账')" />
									<label for="voucherway1">
										银行转账
									</label>
									<input type="radio" id="voucherway6"
										name="fundApply.voucherway" value="现金" onclick="zfType('现金')" />
									<label for="voucherway6">
										现金
									</label>
									<input type="radio" id="voucherway7"
										name="fundApply.voucherway" value="其他" onclick="zfType('其他')" />
									<label for="voucherway7">
										其他
									</label>
									账号:
									<input type="text" id="zhanghao"
										name="fundApply.shoukuanZhanghao" />
								</td>
							</tr>
							<%--							<tr>--%>
							<%--								<th>--%>
							<%--									支付情况--%>
							<%--								</th>--%>
							<%--								<td colspan="7">--%>
							<%--									<input type="radio" id="vouchersituation1"--%>
							<%--										name="fundApply.vouchersituation" value="总额"--%>
							<%--										checked="checked" />--%>
							<%--									<label for="vouchersituation1">--%>
							<%--										总额--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="vouchersituation2"--%>
							<%--										name="fundApply.vouchersituation" value="已支付" />--%>
							<%--									<label for="vouchersituation2">--%>
							<%--										已支付--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="vouchersituation3"--%>
							<%--										name="fundApply.vouchersituation" value="本次应付" />--%>
							<%--									<label for="vouchersituation3">--%>
							<%--										本次应付--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="vouchersituation4"--%>
							<%--										name="fundApply.vouchersituation" value="累计支付" />--%>
							<%--									<label for="vouchersituation4">--%>
							<%--										累计支付--%>
							<%--									</label>--%>
							<%--									<input type="radio" id="vouchersituation5"--%>
							<%--										name="fundApply.vouchersituation" value="余额" />--%>
							<%--									<label for="vouchersituation5">--%>
							<%--										余额--%>
							<%--									</label>--%>
							<%--								</td>--%>
							<%--							</tr>--%>

							<tr>
								<th>
									支付条件
								</th>
								<td colspan="7">
									<input type="radio" id="vouchercondition1" onclick=""
										name="fundApply.vouchercondition" value="即付" checked="checked" />
									<label for="vouchercondition1">
										即付
									</label>
									<input type="radio" id="vouchercondition2"
										name="fundApply.vouchercondition" value="TT30天" />
									<label for="vouchercondition2">
										TT30天
									</label>
									<input type="radio" id="vouchercondition3"
										name="fundApply.vouchercondition" value="TT60天" />
									<label for="vouchercondition3">
										TT60天
									</label>
									<input type="radio" id="vouchercondition4"
										name="fundApply.vouchercondition" value="TT90天" />
									<label for="vouchercondition4">
										TT90天
									</label>
									<input type="radio" id="vouchercondition5"
										name="fundApply.vouchercondition" value="TT120天" />
									<label for="vouchercondition5">
										TT120天
									</label>
									<input type="radio" id="vouchercondition6"
										name="fundApply.vouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="fundApply.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</td>
							</tr>

							<tr>
								<th colspan="8">
									资金使用明细
								</th>
							</tr>
							<tr>
								<th id="title1">
									部门
								</th>
								<th id="title2" class="fsc">
									预算科目
								</th>
								<th id="title3">
									资金使用科目
								</th>
								<th id="title4">
									资金使用明细
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
									<select name="fundApply.fadList[0].budgetDept"
										style="width: 80px;" onMouseOver="selectDept(0)"
										onChange="selectSubjects(0)" id="course0">
										<option value="">
											选择部门
										</option>
									</select>
									<input type="hidden" id="kyMoney0" value="0">
								</th>
								<th align="center" class="sc" style="display: none">
									<select name="fundApply.fadList[0].markId"
										style="width: 180px;" id="markId0"
										onchange="selectYuefen(this,0)">
									</select>
								</th>
								<th align="center" class="fsc">
									<select name="fundApply.fadList[0].deptMonthBudgetID"
										style="width: 180px;" id="subject0"
										onchange="selectYuefen(this,0)">
									</select>
								</th>
								<th>
									<select class="zjstyle0" style="min-width: 100px;"
										name="fundApply.fadList[0].zjStyle"
										onchange="changvalue(this,0)" id="zjstyle0">
										<option></option>
										<option value="设备维修费">
											设备维修费
										</option>
										<option value="差旅、招待费">
											差旅、招待费
										</option>
										<option value="规费">
											规费
										</option>
										<option value="零件采购费">
											零件采购费
										</option>
										<option value="辅料采购费">
											辅料采购费
										</option>
										<option value="奖金">
											奖金
										</option>
										<option value="工资">
											工资
										</option>
										<option value="其他">
											其他
										</option>
									</select>
									<input class="zjstyle1" name="fundApply.fadList[0].zjStyle"
										style="display: none;" id="izjstyle0" disabled="disabled" />
								</th>
								<th class="mxtr">
									<div id="input_div0">
										<input type="text" id="zjStyleMx0" value=""
											name="fundApply.fadList[0].zjStyleMx" />
									</div>
								</th>
								<th>
									<input type="text" onKeyUp="comparecount(0);hejiJine()" id="h0"
										onblur="hejiJine(0)" name="fundApply.fadList[0].voucherMoney"
										value="">
								</th>
								<th colspan="3" align="center">
									<textarea cols="40" rows="2" id="pay_use0"
										name="fundApply.fadList[0].pay_use"></textarea>
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
									合计金额
									<select name="fundApply.currency" id="currency"
										onchange="changspan(this)">
										<option value="RMB">
											人民币
										</option>
										<option value="USD">
											美元
										</option>
										<option value="EUR">
											欧元
										</option>
										<option value="HKD">
											港币
										</option>
										<option value="GBR">
											英镑
										</option>
										<option value="JPY">
											日元
										</option>
										<option value="CHF">
											瑞士法郎
										</option>
										<option value="AUD">
											澳元
										</option>
										<option value="CAD">
											加元
										</option>
										<option value="SGD">
											新加坡元
										</option>
										<option value="SEK">
											瑞典克朗
										</option>
										<option value="DKK">
											丹麦克朗
										</option>
										<option value="NOK">
											挪威克朗
										</option>
										<option value="THB">
											泰国铢
										</option>
										<option value="NZD">
											新西兰元
										</option>
										<option value="KRW">
											韩国元
										</option>
									</select>
								</th>
								<td>
									<span id="hejiMoney"><font color="red"><label
												id="allMoney"></label> </font> </span>
								</td>
								<th>
									合计金额(
									<span id="currency_span">人民币</span>)
								</th>
								<td colspan="2">
									<span id="hejiMoney"><input type="text"
											readonly="readonly" id="totalMoney"
											name="fundApply.totalMoney" value=""> </span>
									<input id="allkyMoney" type="hidden" value="0">
								</td>
							</tr>
							<tr>
								<td colspan="8" align="center">
									<input type="button" value="提交" id="loanId" disabled="disabled"
										style="width: 60px; height: 40px;" align="top"
										onclick="sbmitForm()">
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
	getskdw();
})

function getskdw() {
	$.ajax( {
		type : "POST",
		url : "FundApplyAction_getskdw.action",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, n) {
				$("#relationclient")
						.append(
								"<option value='" + n.id + "' >" + n.name
										+ "</option>");
			})
			$("#relationclient").tinyselect();
		}
	});
}
function showHt() {
	var val_payPlatform = $('input[name="fundApply.voucherbasis"]:checked')
			.val();
	if (val_payPlatform == "合同") {
		$("#showHeTong").show();
		$.ajax( {
			type : "POST",
			url : "bargainAction_getLoginHeTong.action",
			dataType : "json",
			success : function(data) {
				$("#showHeTong").empty();//清空值
				$("#showHeTong").append("<option value='' ></option>");
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
	$($('#complexselectedlist tr')[$('#complexselectedlist tr').length - 4])
			.remove();
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 11) {
		document.getElementById("deleteItem").style.display = "none";
	}
	var allkyMoney = 0;
	for ( var t = 0; t < lineCount; t++) {
		var kyMoney = $("#subject" + t + " option:selected").attr("data");
		allkyMoney = allkyMoney + (kyMoney - 0);
	}
	$("#allkyMoney").val(allkyMoney);
	hejiJine(-1);
}
function hejiJine(index) {
	var total = 0;
	for ( var t = 0; t < lineCount; t++) {
		var cur = $("#h" + t).val();
		if (cur > 0) {
			total = total + parseFloat(cur);
		} else {
			$("#h" + t).val(0);
		}
	}
	total = total.toFixed(4);
	if ($("#itemabout").val() == "外购(历史)" || $("#itemabout").val() == "外购"
			|| $("#itemabout").val() == "外委"
			|| $("#itemabout").val() == "非主营业务应付") {
		document.getElementById("allMoney").innerHTML = total;
		document.getElementById("totalMoney").value = total;
	} else {
		var allkyMoney = $("#allkyMoney").val();
		var val_payPlatform = $('input[name="fundApply.voucherbasis"]:checked')
				.val();
		if (val_payPlatform == "其他" && (total - 2000) > 0) {
			if (index > -1) {
				$("#h" + index).val(0);
				hejiJine(-1);
			} else {
				for ( var t = 0; t < lineCount; t++) {
					$("#h" + t).val(0);
				}
				document.getElementById("allMoney").innerHTML = 0;
				document.getElementById("totalMoney").value = 0;
			}
			alert("支付依据为其他时最大额度为2000");
		}
		if ((total - allkyMoney) > 0) {
			if (index > -1) {
				$("#h" + index).val(0);
				hejiJine(-1);
			} else {
				for ( var t = 0; t < lineCount; t++) {
					$("#h" + t).val(0);
				}
				document.getElementById("allMoney").innerHTML = 0;
				document.getElementById("totalMoney").value = 0;
			}
		} else {
			document.getElementById("allMoney").innerHTML = total;
			document.getElementById("totalMoney").value = total;
		}

	}
}
//添加明细
function saveHKInfor(obj, few) {
	begAddLineNum++;
	var html = '<tr>'
			+ '<td><select name="fundApply.fadList['
			+ lineCount
			+ '].budgetDept" style= "width: 80px;" onMouseOver="selectDept('
			+ lineCount
			+ ')" onChange="selectSubjects('
			+ lineCount
			+ ')" id="course'
			+ lineCount
			+ '"> <option value="">选择部门</option></select></td>'
			+ '<td class="fsc"><select name="fundApply.fadList['
			+ lineCount
			+ '].deptMonthBudgetID" style="width: 180px;" id="subject'
			+ lineCount
			+ '" onchange="selectYuefen(this,'
			+ lineCount
			+ ')"></select> <input type="hidden" id="kyMoney'
			+ lineCount
			+ '" value="0"></td>'
			+ '<td><select class="zjstyle0" name="fundApply.fadList['
			+ lineCount
			+ '].zjStyle" onchange="changvalue(this,'
			+ lineCount
			+ ')" id="zjstyle'
			+ lineCount
			+ '">'
			+ '<option></option>'
			+ '<option value="设备维修费">设备维修费</option>'
			+ '<option value="差旅、招待费">差旅、招待费</option>'
			+ '<option value="规费">规费</option>'
			+ ' <option value="零件采购费">零件采购费</option>'
			+ ' <option value="辅料采购费">辅料采购费</option>'
			+ '<option value="奖金">奖金</option>'
			+ '<option value="工资">工资</option>'
			+ '<option value="其他">其他</option></select><input class="zjstyle1" name="fundApply.fadList['
			+ lineCount
			+ '].zjStyle" id="izjstyle'
			+ lineCount
			+ '" style="display: none;" disabled="disabled"/></td>'
			+ '<td class="mxtr"><div id="input_div'
			+ lineCount
			+ '" ><input type="text" value="" id="zjStyleMx'
			+ lineCount
			+ '" name="fundApply.fadList['
			+ lineCount
			+ '].zjStyleMx"/></div></td>'
			+ '<td><input type="text" onblur="hejiJine('
			+ lineCount
			+ ')"  onKeyUp="comparecount('
			+ lineCount
			+ ');hejiJine('
			+ lineCount
			+ ')"  id="h'
			+ lineCount
			+ '" name="fundApply.fadList['
			+ lineCount
			+ '].voucherMoney"></td>'
			+ '<td colspan="3"><textarea cols="40" rows="2" name="fundApply.fadList['
			+ lineCount + '].pay_use" id="pay_use' + lineCount
			+ '"></textarea></td>' + '</tr>';
	lineCount++;
	$("#complexselectedlist>tbody>tr").eq(9 + lineCount).after(html);
	document.getElementById("deleteItem").style.display = "block";
	var itemabout = $("#itemabout").val();
	if (itemabout == "外购" || itemabout == "外委" || itemabout == "外购(历史)") {
		$(".zjstyle1").show();
		$(".zjstyle1").removeAttr("disabled");
		$(".zjstyle0").hide();
		$(".zjstyle0").attr("disabled", "disabled");

	} else {
		$(".zjstyle0").show();
		$(".zjstyle0").removeAttr("disabled");
		$(".zjstyle1").hide();
		$(".zjstyle1").attr("disabled", "disabled");
	}
	if (itemabout == "外购(历史)") {
		$("#title4").hide();
		$(".mxtr").hide();
	} else {
		//$("#title4").hide();
		//$(".mxtr").hide();
	}
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

	document.getElementById("loanId").disabled = false;
}

//选择部门
//清空下拉部门
function chagebaoxiaoClass() {
	var n = 0;
	$(".tinyselect").each(function() {
		if (n !== 0) {
			this.remove();
		}
		n++;
	})
	for ( var i = 0; i < lineCount; i++) {
		$("#course" + i).show();
		$("#course" + i).empty();
		selectDept(i);
	}
}
var planMonth = $("#planMonth").val();
function selectDept(few) {
	var budgetDept = $("#budgetDept");
	var budgetDetpR = document.getElementsByName("fundApply.isSelfDept");
	for ( var i = 0; i < budgetDetpR.length; i++) {
		if (budgetDetpR[i].checked)
			var budgetDept = budgetDetpR[i].value;
	}
	var id = "course" + few;
	if ($("#itemabout").val()=="外购(历史)"){
		if($("#course" + few).val()!=null&&$("#course" + few).val().length>0){
			return ;
		}
		$.ajax( {
			type : "POST",
			url : "BaoXiaoDanAction!findjianhao2.action",
			data : {
			tag : $("#itemabout").val(),
			},
			dataType : "json",
			success : function(data) {
				$("#course" + few).empty();
				$("<option value=''>选择件号</option>")
							.appendTo("#course" + few);
				$(data).each(
					function() {
						$(
							"<option value='" +this+ "'>" +this+ "</option>")
							.appendTo("#course" + few);
					});
		}
	});
		return;
	}else if($("#itemabout").val()=="外委"||$("#itemabout").val()=="外购"){
		if($("#course" + few).val()!=null&&$("#course" + few).val().length>0){
			return ;
		}
		var url = "BaoXiaoDanAction!findWaiweiGys.action";
		if($("#itemabout").val()=="外购"){
			url = "BaoXiaoDanAction!findWaigouGys.action";
		}
		$.ajax( {
			type : "POST",
			url : url,
			data : {
			tag : $("#itemabout").val(),
			},
			dataType : "json",
			success : function(data) {
				$("#course" + few).empty();
				$("<option value=''>选择供应商</option>")
							.appendTo("#course" + few);
				$(data).each(
					function() {
						$(
							"<option value='" +this[0]+ "'>" +this[1]+ "</option>")
							.appendTo("#course" + few);
					});
		}
	});
		return;
	}else if($("#itemabout").val()=="非主营业务应付"){//非主营业务应付
		if($("#course" + few).val()!=null&&$("#course" + few).val().length>0){
			return ;
		}
		var url = "BaoXiaoDanAction!findfzyBusiness.action";
		$.ajax( {
			type : "POST",
			url : url,
			data : {
			tag : $("#itemabout").val(),
			},
			dataType : "json",
			success : function(data) {
				$("#course" + few).empty();
				$("<option value=''>选择收费类型 </option>")
							.appendTo("#course" + few);
				$(data).each(
					function() {
						$(
							"<option value='" +this+ "'>" +this+ "</option>")
							.appendTo("#course" + few);
					});
		}
		});	
	}else if($("#itemabout").val()=="预算"){//预算报销
		createDept(id, "BaoXiaoDanAction!findchildDept.action?tag=" + budgetDept
			+ "&planMonth=" + planMonth);
	}else{
		createDept(id, "BaoXiaoDanAction!findchildDept.action?tag=self");
	}
	selectSubjects(few);
}

//选择科目
function selectSubjects(few) {
	//var planMonth=$("#planMonth").val();
	if($("#itemabout").val()=="冲账"){//冲借款
		var budgetept = $("#course" + few).val();
		$.ajax( {
			type : "POST",
			url : "BaoXiaoDanAction!findchildSubjects3.action",
			data : {
			tag : budgetept,
			planMonth : planMonth
			},
			dataType : 'json',
			success : function(data) {
				var i=0;
			$("#subject" + few).empty();
			$(data).each(
					function() {
						var bxMoney=this.backMoney;
						if(bxMoney==null){
							bxMoney=0;
						}
						var ky=this.totalMoney-bxMoney;
						if(i==0){
							var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+few).val()+this.totalMoney-bxMoney;
							$("#allkyMoney").val(allkyMoney);
							$("#kyMoney"+few).val(ky);
							$("#planMonth").val("");
						}
						$(
								"<option data='"+ky+"' value='" + this.id + "'>" + this.relationclient
										+ "(" + bxMoney + "/"
										+ this.totalMoney + "元" + ")  </option>")
								.appendTo("#subject" + few);
						i++;
					});
		}
	});
	}else if($("#itemabout").val()=="预算"){//预算报销
		var budgetept = $("#course" + few).val();
		$.ajax( {
			type : "POST",
			url : "BaoXiaoDanAction!findchildSubjects.action",
			data : {
			tag : budgetept,
			planMonth : planMonth
			},
			dataType : 'json',
			success : function(data) {
			var i=0;
			$("#subject" + few).empty();
			$(data).each(
					function() {
						var bxMoney=this.realMoney;
						if(bxMoney==null){
							bxMoney=0;
						}
						var ky=this.accountMoney-bxMoney;
						if(i==0){
							var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+few).val()+this.accountMoney-bxMoney;
							$("#allkyMoney").val(allkyMoney);
							$("#kyMoney"+few).val(ky);
							var allText= $("#planMonth").val();
							if (allText.indexOf(this.budgetMonth) < 0) {
								allText = allText + "," + this.budgetMonth;
							}
							$("#planMonth").val(this.budgetMonth);
						}
						$(
								"<option data='"+ky+"' value='" + this.id + "'>" + this.name
										+ "(" + bxMoney + "/"
										+ this.accountMoney + "元" + "/"
										+ this.budgetMonth + ")  </option>")
								.appendTo("#subject" + few);
						i++;
					});
		}
	});
	}else if($("#itemabout").val()=="外购(历史)"){
		var budgetept = $("#course" + few).val();
		$("#subject" + few).empty();
		if($("#subject" + few).val()!=null&&$("#subject" + few).val().length>0){
			return ;
		}
		$.ajax( {
			type : "POST",
			url : "BaoXiaoDanAction!findpici.action",
			data : {
				tag : budgetept
			},
			dataType : "json",
			success : function(data) {
				var i=0;
				$(data).each(
					function() {
						if(i==0){
								$("#kyMoney"+few).val(0);
								$("#planMonth").val("");
								$("<option data='0' value=''>请选择批次</option>")
								.appendTo("#subject" + few);
							}
						$("<option data='"+this.goodsStoreCount+"' value='"+this.goodsStoreId+"'>" +this.goodsStoreLot+ "</option>")
							.appendTo("#subject" + few);
						i++;
					});
		}
	});
	}else if($("#itemabout").val()=="外委"||$("#itemabout").val()=="外购"){
		var budgetept = $("#course" + few).val();
		$("#subject" + few).empty();
		if($("#subject" + few).val()!=null&&$("#subject" + few).val().length>0){
			return ;
		}
		var url = "";
		if($("#itemabout").val()=="外委"){
			url="BaoXiaoDanAction!findpiWaiwei.action";
		}else{
			url="BaoXiaoDanAction!findpiWaigou.action";
		}
		$.ajax( {
			type : "POST",
			url : url,
			data : {
				tag : budgetept
			},
			dataType : "json",
			success : function(data) {
				var i=0;
				$(data).each(
					function() {
						if(i==0){
								$("#kyMoney"+few).val(0);
								$("#planMonth").val("");
								$("<option data='0' value=''>请选择件号</option>")
								.appendTo("#subject" + few);
							}
						$("<option data1='"+this[1]+"' data2='"+this[3]+"' data3='"+this[4]+"' data4='"+this[5]+"' value='"+this[0]+"'>" +this[2]+"("+this[3]+")"+ "</option>")
							.appendTo("#subject" + few);
						i++;
					});
		}
	});
	}else if($("#itemabout").val()=="非主营业务应付"){
		var budgetept = $("#course" + few).val();
		$("#subject" + few).empty();
		if($("#subject" + few).val()!=null&&$("#subject" + few).val().length>0){
			return ;
		}
		$.ajax( {
			type : "POST",
			url : "BaoXiaoDanAction!findfzykm.action",
			data : {
				tag : budgetept
			},
			dataType : "json",
			success : function(data) {
				var i=0;
				$(data).each(
					function() {
						if(i==0){
								$("#kyMoney"+few).val(0);
								$("#planMonth").val("");
								$("<option data='0' value=''>请选择项目</option>")
								.appendTo("#subject" + few);
							}
						var ky = this[3];
						if(this[4]!=null){
							ky=this[3]-this[4];
						}
						$("<option data1='"+this[1]+"' data2='"+this[2]+"' data3='"+ky+"'  data4='"+this[5]+"'  value='"+this[0]+"'>" +this[1]+"-"+this[2]+ "</option>")
							.appendTo("#subject" + few);
						i++;
					});
		}
	});
	}else{
		$.ajax( {
			type : "POST",
			url : "BaoXiaoDanAction!findchildSubjects2.action",
			data : {
			tag : $("#itemabout").val(),
			},
			dataType : "json",
			success : function(data) {
				$("#subject" + few).empty();
				var i=0;
				$(data).each(
					function() {
						var bxMoney=this.bxMoney;
						if(bxMoney==null){
							bxMoney=0;
						}
						if($("#itemabout").val()=="项目"){
							var ky=this.tzMoney-bxMoney;
							if(i==0){
								var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+few).val()+this.tzMoney-bxMoney;
								$("#allkyMoney").val(allkyMoney);
								$("#kyMoney"+few).val(ky);
								$("#planMonth").val("");
							}
							$(
								"<option data='"+ky+"' value='" + this.id + "'>" +this.proName+ ":"+this.markId+"("+bxMoney+"/"+this.tzMoney+"元)"
										+ "</option>")
								.appendTo("#subject" + few);
							
						}else if($("#itemabout").val()=="KVP"){
							var ky=this.costsavings-bxMoney;
							if(i==0){
								var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+few).val()+this.costsavings-bxMoney;
								$("#allkyMoney").val(allkyMoney);
								$("#kyMoney"+few).val(ky);
							}
							$(
								"<option data='"+ky+"' value='" + this.id + "'>" + this.kvpAssess.part_name+"("+bxMoney+"/"+this.costsavings+"元)"
										+ "</option>")
								.appendTo("#subject" + few);
							var budgetept = $("#course" + few).val();
							$.ajax( {
								type : "POST",
								url : "BaoXiaoDanAction!findchildSubjects.action",
								data : {
									tag : budgetept,
									planMonth : planMonth
									},
								dataType : 'json',
								success : function(data) {
									$("#zjstyle" + few).empty();
									$(data).each(
											function() {
												$(
														"<option data='"+ky+"' value='" + this.id + "'>" + this.name
																+ "</option>")
														.appendTo("#zjstyle" + few);
											});
								}
								})
						}
						i++;
					});
		}
	});
	}
}
//点击科目及月份是触发的事件
function selectYuefen(obj,index) {
		if($("#itemabout").val()=="外购(历史)"){
			var data =$("#subject"+index+" option:selected").attr("data");
			$("#izjstyle"+index).val(data);
			return;
		}
		if($("#itemabout").val()=="外委"||$("#itemabout").val()=="外购"){
			var data1 =$("#subject"+index+" option:selected").attr("data1");
			var data2 =$("#subject"+index+" option:selected").attr("data2");
			var data3 =$("#subject"+index+" option:selected").attr("data3");
			var data4 =$("#subject"+index+" option:selected").attr("data4");
			$("#izjstyle"+index).val(data1);
			$("#zjStyleMx"+index).val(data2);
			if(data4!=null&&data4!="null"&&data4.length>0){
				data3 = data3-data4
			}
			$("#h"+index).val(data3);
			var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+index).val()+(data3-0);
			$("#kyMoney"+index).val(data3);
			$("#allkyMoney").val(allkyMoney);
		}
		if($("#itemabout").val()=="非主营业务应付"){
			var data1 =$("#subject"+index+" option:selected").attr("data1");
			var data2 =$("#subject"+index+" option:selected").attr("data2");
			var data3 =$("#subject"+index+" option:selected").attr("data3");
			var data4 =$("#subject"+index+" option:selected").attr("data4");
			$("#izjstyle"+index).val(data1);
			$("#relationclient").val(data1);
			$("#zjStyleMx"+index).val(data2);
			$("#h"+index).val(data3);
			if(data4!=null&&data4!="null"){
				$("#pay_use"+index).val(data4);
			}
			var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+index).val()+(data3-0);
			$("#kyMoney"+index).val(data3);
			$("#allkyMoney").val(allkyMoney);
		}else if($("#itemabout").val()!="外委"&&$("#itemabout").val()!="外购"){
			var data =$("#subject"+index+" option:selected").attr("data");
			var allkyMoney = $("#allkyMoney").val()-$("#kyMoney"+index).val()+(data-0);
			$("#kyMoney"+index).val(data);
			$("#allkyMoney").val(allkyMoney);
		}
		var allText = "";
		//根据页面科目及月份的下拉列表
		for ( var i = 0; i < lineCount; i++) {
			var obj = document.getElementById("subject" + i);
			var a = obj.options[obj.selectedIndex].text;
			if (a != "") {
				var a1 = a.lastIndexOf("/");
				a = a.substring(a1 + 1, a.length - 1);
				if (allText == "") {
				allText = a;
				} else {
					if (allText.indexOf(a) < 0) {
						allText = allText + "," + a;
					}
				}
			}
		}
		if($("#itemabout").val()!="冲账"){
			if($("#itemabout").val()=="非主营业务应付"||$("#itemabout").val()=="外委"||$("#itemabout").val()=="外购"){
		//	$("#planMonth").val($("#subject"+index+" option:selected").attr("data2"));
			}else{
			$("#planMonth").val(allText);
			}
		}else{
			$("#planMonth").val("");
		}
		hejiJine(-1);
	
}
function changvalue(obj,num){
<%--	if(obj!=null && obj.value!=""){--%>
<%--		if(obj.value == '设备维修费' || obj.value == '差旅、招待费' || obj.value == '零件采购费' ){--%>
<%--			$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : "FundApplyAction_getzjStyleMx.action",--%>
<%--		data : {--%>
<%--			style:obj.value--%>
<%--		},--%>
<%--		dataType : "json",--%>
<%--		success : function(data) {--%>
<%--			if (data != null) {--%>
<%--				$("#zjsymx"+num).empty();--%>
<%--				if(obj.value == '设备维修费'){--%>
<%--					$(data).each(function(){--%>
<%--					$("#zjsymx"+num).append("<option value="+this.barcode+">"+this.barcode+"</option>");--%>
<%--				})--%>
<%--				}else if(obj.value == '差旅、招待费'){--%>
<%--					$(data).each(function(){--%>
<%--						$("#zjsymx"+num).append("<option value="+this+">"+this+"</option>");--%>
<%--					})--%>
<%--				}else{--%>
<%--					$(data).each(function(){--%>
<%--						$("#zjsymx"+num).append("<option value="+this.planNumber+">"+this.planNumber+"</option>");--%>
<%--					})--%>
<%--				}--%>
<%--				--%>
<%--			}--%>
<%--		}--%>
<%--	})--%>
<%--			$("#select_div"+num).show();--%>
<%--			$("#input_div"+num).hide();--%>
<%--		}else{--%>
<%--			$("#select_div"+num).hide();--%>
<%--			$("#input_div"+num).show();--%>
<%--		}--%>
<%--	}--%>
}
function showzhifu_tr(){
	$(".zhifu_tr").show();
	$(".zhifu_").removeAttr("disabled");
	$("#voucherbasis2").show();
	$("#for_voucherbasis2").show();
}
function hidzhifu_tr(){
	$(".zhifu_tr").hide();
	$("#voucherbasis2").hide();
	$("#for_voucherbasis2").hide();
}
function changspan(obj){
	if(obj!=null && obj.value!=""){
		var arrays = ['人民币','美元','欧元','港币','英镑','日元','瑞士法郎','澳元','加元','新加坡元','瑞典克朗','丹麦克朗','挪威克朗','泰国铢','新西兰元','韩国元'];
		var rmbs =['RMB','USD','EUR','HKD','GBR','JPY','CHF','AUD','CAD','SGD','SEK','DKK','NOK','THB','NZD','KRW'];
		for(var i=0;i<rmbs.length;i++){
			if(rmbs[i] == obj.value){
				$("#currency_span").html(arrays[i]);
			}
		}
	}
}
function changeAbout(){
	var itemabout= $("#itemabout").val();
	if(itemabout=="外购(历史)"){
		showzhifu_tr();
		$("#title1").empty();
		$("#title1").html("件号");
		$("#title2").empty();
		$("#title2").html("批次");
		$("#title3").empty();
		$("#title3").html("数量");
		$("#title4").empty();
		$("#title4").html("资金使用明细");
		$("#title4").hide();
		$(".mxtr").hide();
		$(".zjstyle1").show();
		$(".zjstyle1").removeAttr("disabled");
		$(".zjstyle0").hide();
		$(".zjstyle0").attr("disabled","disabled");
		$("#zhifuoryufu1").attr("checked","checked");
		$("#zhifuoryufu2").removeAttr("checked");
		showzhifu_tr();
	}else  if(itemabout=="非主营业务应付"){
		$(".mxtr").show();
		$("#title1").empty();
		$("#title1").html("付款类型");
		$("#title2").empty();
		$("#title2").html("收款项目");
		$("#title3").empty();
		$("#title3").html("收款单位");
		$("#title4").empty();
		$("#title4").html("起始时间");
		$(".zjstyle1").show();
		$(".zjstyle1").removeAttr("disabled");
		$(".zjstyle0").hide();
		$(".zjstyle0").attr("disabled","disabled");
		$("#zhifuoryufu1").attr("checked","checked");
		$("#zhifuoryufu2").removeAttr("checked");
		showzhifu_tr();
	}else  if(itemabout=="外委"||itemabout=="外购"){
		showzhifu_tr();
		$(".mxtr").show();
		$("#title1").empty();
		$("#title1").html("供应商");
		$("#title2").empty();
		$("#title2").html("件号");
		$("#title3").empty();
		$("#title3").html("数量");
		$("#title4").empty();
		$("#title4").html("外委单号");
		$(".zjstyle1").show();
		$(".zjstyle1").removeAttr("disabled");
		$(".zjstyle0").hide();
		$(".zjstyle0").attr("disabled","disabled");
		$("#zhifuoryufu1").attr("checked","checked");
		$("#zhifuoryufu2").removeAttr("checked");
	}else{
		$(".mxtr").show();
		$("#title1").empty();
		$("#title1").html("部门");
		$("#title2").empty();
		$("#title2").html("资金使用项目");
		$("#title3").empty();
		$("#title3").html("资金使用科目");
		$("#title4").empty();
		$("#title4").html("资金使用明细");
		$(".zjstyle0").show();
		$(".zjstyle0").removeAttr("disabled");
		$(".zjstyle1").hide();
		$(".zjstyle1").attr("disabled","disabled");
	}
	chagebaoxiaoClass();
}
function changenature(v){
	$("#voucherNature").val(v);
}
function zfType(v){
	if(v=="银行转账"){
		$("#zhanghao").show();
	}else{
		$("#zhanghao").hide();
	}
}
function comparecount(index){
	var itemabout= $("#itemabout").val();
	if(itemabout=="外委"||itemabout=="外购"){
		var bxmoney=$("#h"+index).val();
		var kyMoney = $("#kyMoney"+index).val();
		if((bxmoney-kyMoney)>0){
			alert("此订单可报金额为"+kyMoney+",请勿超额!");
			$("#h"+index).val(kyMoney);
			return false;
		}
	}
	
}
function sbmitForm(){
	var formData = new FormData($( "#fundForm" )[0]);//表单id
	$.ajax( {
		type : "POST",
		url : "FundApplyAction_addfundApply.action",
		dataType : "json",
		data : formData,
		 cache: false,  
          contentType: false,  
          processData: false, 
		success : function(msg) {
				alert(msg.message);
			if (msg.success) {
				window.location.href="FundApplyAction_findfundDetailedList.action?id="+msg.data;
			}
	}
	});
}
</script>

</html>
