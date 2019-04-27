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
		<div id="gongneng">
			<div align="center">
				<form action="oaReimBursementAction!saveBaoZhang.action"
					method="post" id="myForm" onsubmit="return check()">
					<table class="table">
						<tr>
							<td align="center" colspan="8">
								<font size="6">付款凭证单</font>
								<h5 align="right">
									付款凭证编号：${oaReimBursement.oaPaymentvouchernumber}
									<input type="hidden"
										value="${oaReimBursement.oaPaymentvouchernumber}"
										name="oaReimBursement.oaPaymentvouchernumber" />
								</h5>
							</td>
						</tr>

						<tr>
							<th>
								商品编号
							</th>
							<th>
								商品名称
							</th>
							<th>
								商品规格
							</th>
							<th>
								商品单位
							</th>
							<th>
								商品数量
							</th>
							<th>
								商品单价(元)
							</th>
							<th>
								商品总金额
							</th>
							<th>
								发票号
							</th>
						</tr>
						<s:iterator id="pp" value="storageSelect" status="ids">
							<input type="hidden" name="storageSelect" value="${pp}" />
						</s:iterator>
						<s:iterator id="p" value="list">
							<tr>

								<tD align="center">

									${p.number}
								</tD>
								<tD align="center">
									${p.matetag}
								</tD>
								<tD align="center">
									${p.format}
								</tD>
								<tD align="center">
									${p.unit}
								</tD>
								<tD align="center">
									${p.num}
								</tD>
								<tD align="center">
									${p.storageTaxPrice}
								</tD>
								<tD align="center">
									${p.storageTaxMoney}
								</tD>
								<tD align="center">
									${p.storageInvoice}
								</tD>
							</tr>
						</s:iterator>
						<tr>
							<th>
								总金额
							</th>
							<td colspan="7">
								<font style="font-weight: bold;" size="5">${oaReimBursement.oathetotalamount}元</font>
								<input type="hidden" name="oaReimBursement.oathetotalamount"
									value="${oaReimBursement.oathetotalamount}">
							</td>
						</tr>
						<tr>
							<th>
								负责人
							</th>
							<td>
								<input type="text" name="oaReimBursement.oaPersonincharge"
									id="fzr">
							</td>
							<th colspan="2">
								收款人单位名称
							</th>
							<td colspan="4">
								<input type="text" name="oaReimBursement.oaBeneficiary"
									id="skrdwmz">
							</td>
						</tr>

						<tr>
							<th>
								评审单号
							</th>
							<td>
								<input type="text" name="oaReimBursement.oaReviewnumber"
									id="psdh">
							</td>
							<th colspan="2">
								关联客户名称
							</th>
							<td colspan="4">
								<input type="text" name="oaReimBursement.oaAssociatedusername"
									id="glkhmc">
							</td>
						</tr>
						<tr>
							<th>
								付款性质
							</th>
							<td colspan="7">
								<input type="radio" value="预付"
									name="oaReimBursement.oaPaymentnature">
								预付
								<input type="radio" value="中间付款"
									name="oaReimBursement.oaPaymentnature">
								中间付款
								<input type="radio" value="余款"
									name="oaReimBursement.oaPaymentnature">
								余款
								<input type="radio" value="质保金"
									name="oaReimBursement.oaPaymentnature">
								质保金
								<input type="radio" value="借款"
									name="oaReimBursement.oaPaymentnature">
								借款
								<input type="radio" value="冲账"
									name="oaReimBursement.oaPaymentnature">
								冲账
								<input type="radio" value="其他"
									name="oaReimBursement.oaPaymentnature">
								其他
							</td>
						</tr>

						<tr>
							<th>
								付款方式
							</th>
							<td colspan="7">
								<input type="radio" value="银行" name="oaReimBursement.oaPayment">
								银行
								<input type="radio" value="汇票" name="oaReimBursement.oaPayment">
								汇票
								<input type="radio" value="汇兑" name="oaReimBursement.oaPayment">
								汇兑
								<input type="radio" value="支票" name="oaReimBursement.oaPayment">
								支票
								<input type="radio" value="贷记" name="oaReimBursement.oaPayment">
								贷记
								<input type="radio" value="现金" name="oaReimBursement.oaPayment">
								现金
								<input type="radio" value="其他" name="oaReimBursement.oaPayment">
								其他
							</td>
						</tr>

						<tr>
							<th>
								付款情况
							</th>
							<td colspan="7">
								<input type="radio" value="总额"
									name="oaReimBursement.oaPaymentsqk">
								总额
								<input type="radio" value="已支付"
									name="oaReimBursement.oaPaymentsqk">
								已支付
								<input type="radio" value="本次应付"
									name="oaReimBursement.oaPaymentsqk">
								本次应付
								<input type="radio" value="累计支付"
									name="oaReimBursement.oaPaymentsqk">
								累计支付
								<input type="radio" value="余额"
									name="oaReimBursement.oaPaymentsqk">
								余额
							</td>
						</tr>


						<tr>
							<th>
								付款条件
							</th>
							<td colspan="7">
								<input type="radio" value="即付"
									name="oaReimBursement.oaTermsPayment">
								即付
								<input type="radio" value="TT30天"
									name="oaReimBursement.oaTermsPayment">
								TT30天
								<input type="radio" value="TT60天"
									name="oaReimBursement.oaTermsPayment">
								TT60天
								<input type="radio" value="TT90天"
									name="oaReimBursement.oaTermsPayment">
								TT90天
								<input type="radio" value="TT120天"
									name="oaReimBursement.oaTermsPayment">
								TT120天
								<input type="radio" value="TT120天以上"
									name="oaReimBursement.oaTermsPayment">
								TT120天以上
								<input type="radio" value="其他"
									name="oaReimBursement.oaTermsPayment">
								其他
							</td>
						</tr>

						<tr>
							<th>
								类别
							</th>
							<td colspan="7">
								<input type="radio" value="总务性采购"
									name="oaReimBursement.oaCategory">
								总务性采购
								<input type="radio" value="原材料采购"
									name="oaReimBursement.oaCategory">
								原材料采购
								<input type="radio" value="工程设备类采购"
									name="oaReimBursement.oaCategory">
								工程设备类采购
								<input type="radio" value="其他" name="oaReimBursement.oaCategory">
								其他
							</td>
						</tr>

						<tr>
							<th>
								付款依据
							</th>
							<td colspan="7">
								<input type="radio" value="合同"
									name="oaReimBursement.oaPaymentaccordancewith">
								合同
								<input type="radio" value="发票"
									name="oaReimBursement.oaPaymentaccordancewith">
								发票
								<input type="radio" value="协议"
									name="oaReimBursement.oaPaymentaccordancewith">
								协议
								<input type="radio" value="通知"
									name="oaReimBursement.oaPaymentaccordancewith">
								通知
								<input type="radio" value="其他"
									name="oaReimBursement.oaPaymentaccordancewith">
								其他
							</td>
						</tr>

						<tr>
							<th>
								档案号
							</th>

							<td>
								<input type="text" name="oaReimBursement.oadangannumber">

							</td>

							<th colspan="2">
								合同号
							</th>
							<td colspan="4">
								<input type="text" name="oaReimBursement.oaContractnumber">

							</td>
						</tr>
						<tr>
							<th>
								发票日期
							</th>
							<td colspan="7">
								<input class="Wdate" type="text" name="oaReimBursement.oadate"
									size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								情况说明
							</th>
							<td colspan="7">
								<textarea name="oaReimBursement.oaFactSheet"
									style="width: 250; height: 60; text-align: left;">
  					   </textarea>
							</td>
						</tr>

						<tr>
							<%--					<td align="center" colspan="8">--%>
							<%--							<input type="submit"  value="提  交">--%>
							<td align="center" colspan="8">
								<input type="button" id="item" value="提  交"
									onclick="checkAndSubmit()" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="取  消">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
function check() {
	var fuzheren = document.getElementById("fzr");//负责人不能为空
	var skrdwnamecheng = document.getElementById("skrdwmz");//收款单位名称
	var guanliankehu = document.getElementById("glkhmc");//关联客户名称
	var pingshengdanhao = document.getElementById("psdh");//评审单号
	if (fuzheren.value == "") {
		alert("负责人不能为空");
		fuzheren.focus();
		return false;
	} else if (skrdwnamecheng.value == "") {
		alert("收款人单位名称不能为空");
		skrdwnamecheng.focus();
		return false;
	} else if (guanliankehu.value == "") {
		alert("关联客户名称不能为空");
		guanliankehu.focus();
		return false;
	} else if (pingshengdanhao.value == "") {
		alert("评审单号不能为空");
		pingshengdanhao.focus();
		return false;
	} else {
		return true;
	}
}
function checkAndSubmit() {
	document.getElementById("item").disabled = true;
	var myForm = document.getElementById("myForm");
	myForm.submit();
}
</script>
	</body>
</html>
