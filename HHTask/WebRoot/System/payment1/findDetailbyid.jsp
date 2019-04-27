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
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<h3>
					${companyInfo.name}费用借款单
				</h3>
				<!-- paymentVoucherAction_updateBorrowings.action -->
				<s:if test='test!=null&&test!=""'>
					<form
						action="paymentVoucherAction_updateBorrowings.action?test=<s:property value="test"/>"
						method="post" onsubmit="return checkForm()">
				</s:if>
				<s:else>
					<form action="paymentVoucherAction_updateBorrowings.action"
						method="post" onsubmit="return checkForm()">
				</s:else>
				<table style="width: 100px;" class="table" id="complexselectedlist">
					<tbody>
						<tr>
							<th>
								借款单位(个人)
							</th>
							<td>
								<input type="text" id="unitname" name="paymentVoucher.unitname"
									value="${paymentVoucher.unitname}" />
								<input type="hidden" name="paymentVoucher.epId"
									value="${paymentVoucher.epId}" />
							</td>

							<th>
								收款单位
							</th>
							<td>
								<input type="text" id="relationclient"
									name="paymentVoucher.relationclient"
									value="${paymentVoucher.relationclient}" readonly="readonly" />
							</td>

							<th colspan="4">
								部门
								<input type="text" id="approvaldept"
									name="paymentVoucher.approvaldept"
									value="${sessionScope.Users.dept}" />
								合同号
								<input type="text" id="contractnum"
									name="paymentVoucher.contractnum"
									value="${paymentVoucher.contractnum}" />
							</th>
							<th>
								操作
							</th>
						</tr>
						<tr>
							<th>
								借款依据
							</th>
							<td colspan="8">
								<s:if test='paymentVoucher.voucherbasis=="合同"'>
									<input type="radio" id="budgetDept1"
										name="paymentVoucher.voucherbasis" value="合同"
										checked="checked" />
									<label for="budgetDept1">
										合同
									</label>
									<input type="radio" id="budgetDept2"
										name="paymentVoucher.voucherbasis" value="发票" />
									<label for="budgetDept2">
										发票
									</label>
									<input type="radio" id="budgetDept3" onclick=""
										name="paymentVoucher.voucherbasis" value="协议" />
									<label for="budgetDept3">
										协议
									</label>
									<input type="radio" id="budgetDept4"
										name="paymentVoucher.voucherbasis" value="通知" />
									<label for="budgetDept4">
										通知
									</label>
									<input type="radio" id="budgetDept5"
										name="paymentVoucher.voucherbasis" value="其他" />
									<label for="budgetDept5">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherbasis=="发票"'>
									<input type="radio" id="budgetDept1"
										name="paymentVoucher.voucherbasis" value="合同" />
									<label for="budgetDept1">
										合同
									</label>
									<input type="radio" id="budgetDept2"
										name="paymentVoucher.voucherbasis" value="发票"
										checked="checked" />
									<label for="budgetDept2">
										发票
									</label>
									<input type="radio" id="budgetDept3" onclick=""
										name="paymentVoucher.voucherbasis" value="协议" />
									<label for="budgetDept3">
										协议
									</label>
									<input type="radio" id="budgetDept4"
										name="paymentVoucher.voucherbasis" value="通知" />
									<label for="budgetDept4">
										通知
									</label>
									<input type="radio" id="budgetDept5"
										name="paymentVoucher.voucherbasis" value="其他" />
									<label for="budgetDept5">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherbasis=="协议"'>
									<input type="radio" id="budgetDept1"
										name="paymentVoucher.voucherbasis" value="合同" />
									<label for="budgetDept1">
										合同
									</label>
									<input type="radio" id="budgetDept2"
										name="paymentVoucher.voucherbasis" value="发票" />
									<label for="budgetDept2">
										发票
									</label>
									<input type="radio" id="budgetDept3"
										name="paymentVoucher.voucherbasis" value="协议"
										checked="checked" />
									<label for="budgetDept3">
										协议
									</label>
									<input type="radio" id="budgetDept4"
										name="paymentVoucher.voucherbasis" value="通知" />
									<label for="budgetDept4">
										通知
									</label>
									<input type="radio" id="budgetDept5"
										name="paymentVoucher.voucherbasis" value="其他" />
									<label for="budgetDept5">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherbasis=="通知"'>
									<input type="radio" id="budgetDept1"
										name="paymentVoucher.voucherbasis" value="合同" />
									<label for="budgetDept1">
										合同
									</label>
									<input type="radio" id="budgetDept2"
										name="paymentVoucher.voucherbasis" value="发票" />
									<label for="budgetDept2">
										发票
									</label>
									<input type="radio" id="budgetDept3"
										name="paymentVoucher.voucherbasis" value="协议" />
									<label for="budgetDept3">
										协议
									</label>
									<input type="radio" id="budgetDept4"
										name="paymentVoucher.voucherbasis" value="通知"
										checked="checked" />
									<label for="budgetDept4">
										通知
									</label>
									<input type="radio" id="budgetDept5"
										name="paymentVoucher.voucherbasis" value="其他" />
									<label for="budgetDept5">
										其他
									</label>
								</s:if>

								<s:if test='paymentVoucher.voucherbasis=="其他"'>
									<input type="radio" id="budgetDept1"
										name="paymentVoucher.voucherbasis" value="合同" />
									<label for="budgetDept1">
										合同
									</label>
									<input type="radio" id="budgetDept2"
										name="paymentVoucher.voucherbasis" value="发票" />
									<label for="budgetDept2">
										发票
									</label>
									<input type="radio" id="budgetDept3"
										name="paymentVoucher.voucherbasis" value="协议" />
									<label for="budgetDept3">
										协议
									</label>
									<input type="radio" id="budgetDept4"
										name="paymentVoucher.voucherbasis" value="通知" />
									<label for="budgetDept4">
										通知
									</label>
									<input type="radio" id="budgetDept5"
										name="paymentVoucher.voucherbasis" value="其他"
										checked="checked" />
									<label for="budgetDept5">
										其他
									</label>
								</s:if>
							</td>
						</tr>
						<tr>

						</tr>
						<tr>
							<th>
								借款性质
							</th>
							<td colspan="8">
								<s:if test='paymentVoucher.voucherNature=="预付"'>
									<input type="radio" id="voucherNature1"
										name="paymentVoucher.voucherNature" value="预付"
										checked="checked" />
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
										name="paymentVoucher.voucherNature" value="借款" />
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
								</s:if>

								<s:if test='paymentVoucher.voucherNature=="中间付款"'>
									<input type="radio" id="voucherNature1"
										name="paymentVoucher.voucherNature" value="预付" />
									<label for="voucherNature1">
										预付
									</label>
									<input type="radio" id="voucherNature2"
										name="paymentVoucher.voucherNature" value="中间付款"
										checked="checked" />
									<label for="voucherNature2">
										预付
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
										name="paymentVoucher.voucherNature" value="借款" />
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
								</s:if>

								<s:if test='paymentVoucher.voucherNature=="余额"'>
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
										name="paymentVoucher.voucherNature" value="余款"
										checked="checked" />
									<label for="voucherNature3">
										余款
									</label>
									<input type="radio" id="voucherNature4"
										name="paymentVoucher.voucherNature" value="质保金" />
									<label for="voucherNature4">
										质保金
									</label>
									<input type="radio" id="voucherNature5"
										name="paymentVoucher.voucherNature" value="借款" />
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
								</s:if>

								<s:if test='paymentVoucher.voucherNature=="质保金"'>
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
										name="paymentVoucher.voucherNature" value="质保金"
										checked="checked" />
									<label for="voucherNature4">
										质保金
									</label>
									<input type="radio" id="voucherNature5"
										name="paymentVoucher.voucherNature" value="借款" />
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
								</s:if>

								<s:if test='paymentVoucher.voucherNature=="借款"'>
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
								</s:if>

								<s:if test='paymentVoucher.voucherNature=="冲账"'>
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
										name="paymentVoucher.voucherNature" value="借款" />
									<label for="voucherNature5">
										借款
									</label>
									<input type="radio" id="voucherNature6"
										name="paymentVoucher.voucherNature" value="冲账"
										checked="checked" />
									<label for="voucherNature6">
										冲账
									</label>
									<input type="radio" id="voucherNature7"
										name="paymentVoucher.voucherNature" value="其他" />
									<label for="voucherNature7">
										其他
									</label>
								</s:if>

								<s:if test='paymentVoucher.voucherNature=="其他"'>
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
										name="paymentVoucher.voucherNature" value="借款" />
									<label for="voucherNature5">
										借款
									</label>
									<input type="radio" id="voucherNature6"
										name="paymentVoucher.voucherNature" value="冲账" />
									<label for="voucherNature6">
										冲账
									</label>
									<input type="radio" id="voucherNature7"
										name="paymentVoucher.voucherNature" value="其他"
										checked="checked" />
									<label for="voucherNature7">
										其他
									</label>
								</s:if>
							</td>
						</tr>

						<tr>
							<th>
								借款方式
							</th>
							<td colspan="8">
								<s:if test='paymentVoucher.voucherway=="银行"'>
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
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherway=="汇票"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
									<label for="voucherway1">
										银行
									</label>
									<input type="radio" id="voucherway2"
										name="paymentVoucher.voucherway" value="汇票" checked="checked" />
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
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherway=="汇兑"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
									<label for="voucherway1">
										银行
									</label>
									<input type="radio" id="voucherway2"
										name="paymentVoucher.voucherway" value="汇票" />
									<label for="voucherway2">
										汇票
									</label>
									<input type="radio" id="voucherway3"
										name="paymentVoucher.voucherway" value="汇兑" checked="checked" />
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
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>

								<s:if test='paymentVoucher.voucherway=="支票"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
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
										name="paymentVoucher.voucherway" value="支票" checked="checked" />
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
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>

								<s:if test='paymentVoucher.voucherway=="贷记"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
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
										name="paymentVoucher.voucherway" value="贷记" checked="checked" />
									<label for="voucherway5">
										贷记
									</label>
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="现金" />
									<label for="voucherway6">
										现金
									</label>
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>

								<s:if test='paymentVoucher.voucherway=="现金"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
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
										name="paymentVoucher.voucherway" value="现金" checked="checked" />
									<label for="voucherway6">
										现金
									</label>
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherway=="大众点评"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
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
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评"
										checked="checked" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.voucherway=="其他"'>
									<input type="radio" id="voucherway1"
										name="paymentVoucher.voucherway" value="银行" />
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
									<input type="radio" id="voucherway6"
										name="paymentVoucher.voucherway" value="大众点评"
										checked="checked" />
									<label for="voucherway6">
										大众点评
									</label>
									<input type="radio" id="voucherway7"
										name="paymentVoucher.voucherway" value="其他" checked="checked" />
									<label for="voucherway7">
										其他
									</label>
								</s:if>
							</td>
						</tr>
						<tr>
							<th>
								借款情况
							</th>
							<td colspan="8">
								<s:if test='paymentVoucher.vouchersituation=="总额"'>
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
								</s:if>
								<s:if test='paymentVoucher.vouchersituation=="已支付"'>
									<input type="radio" id="vouchersituation1"
										name="paymentVoucher.vouchersituation" value="总额" />
									<label for="vouchersituation1">
										总额
									</label>
									<input type="radio" id="vouchersituation2"
										name="paymentVoucher.vouchersituation" value="已支付"
										checked="checked" />
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
								</s:if>
								<s:if test='paymentVoucher.vouchersituation=="本次应付"'>
									<input type="radio" id="vouchersituation1"
										name="paymentVoucher.vouchersituation" value="总额" />
									<label for="vouchersituation1">
										总额
									</label>
									<input type="radio" id="vouchersituation2"
										name="paymentVoucher.vouchersituation" value="已支付" />
									<label for="vouchersituation2">
										已支付
									</label>
									<input type="radio" id="vouchersituation3"
										name="paymentVoucher.vouchersituation" value="本次应付"
										checked="checked" />
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
								</s:if>

								<s:if test='paymentVoucher.vouchersituation=="累计支付"'>
									<input type="radio" id="vouchersituation1"
										name="paymentVoucher.vouchersituation" value="总额" />
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
										name="paymentVoucher.vouchersituation" value="累计支付"
										checked="checked" />
									<label for="vouchersituation4">
										累计支付
									</label>
									<input type="radio" id="vouchersituation5"
										name="paymentVoucher.vouchersituation" value="余额" />
									<label for="vouchersituation5">
										余额
									</label>
								</s:if>

								<s:if test='paymentVoucher.vouchersituation=="余额"'>
									<input type="radio" id="vouchersituation1"
										name="paymentVoucher.vouchersituation" value="总额" />
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
										name="paymentVoucher.vouchersituation" value="余额"
										checked="checked" />
									<label for="vouchersituation5">
										余额
									</label>
								</s:if>
							</td>
						</tr>

						<tr>
							<th>
								借款条件
							</th>
							<td colspan="8">
								<s:if test='paymentVoucher.vouchercondition=="即付"'>
									<input type="radio" id="vouchercondition1"
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
										name="paymentVoucher.vvouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.vouchercondition=="TT30天"'>
									<input type="radio" id="vouchercondition1"
										name="paymentVoucher.vouchercondition" value="即付" />
									<label for="vouchercondition1">
										即付
									</label>
									<input type="radio" id="vouchercondition2"
										name="paymentVoucher.vouchercondition" value="TT30天"
										checked="checked" />
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
										name="paymentVoucher.vvouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.vouchercondition=="TT60天"'>
									<input type="radio" id="vouchercondition1"
										name="paymentVoucher.vouchercondition" value="即付" />
									<label for="vouchercondition1">
										即付
									</label>
									<input type="radio" id="vouchercondition2"
										name="paymentVoucher.vouchercondition" value="TT30天" />
									<label for="vouchercondition2">
										TT30天
									</label>
									<input type="radio" id="vouchercondition3"
										name="paymentVoucher.vouchercondition" value="TT60天"
										checked="checked" />
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
										name="paymentVoucher.vvouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.vouchercondition=="TT90天"'>
									<input type="radio" id="vouchercondition1"
										name="paymentVoucher.vouchercondition" value="即付" />
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
										name="paymentVoucher.vouchercondition" value="TT90天"
										checked="checked" />
									<label for="vouchercondition4">
										TT90天
									</label>
									<input type="radio" id="vouchercondition5"
										name="paymentVoucher.vouchercondition" value="TT120天" />
									<label for="vouchercondition5">
										TT120天
									</label>
									<input type="radio" id="vouchercondition6"
										name="paymentVoucher.vvouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.vouchercondition=="TT120天"'>
									<input type="radio" id="vouchercondition1"
										name="paymentVoucher.vouchercondition" value="即付" />
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
										name="paymentVoucher.vouchercondition" value="TT120天"
										checked="checked" />
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
								</s:if>
								<s:if test='paymentVoucher.vouchercondition=="TT120天以上"'>
									<input type="radio" id="vouchercondition1"
										name="paymentVoucher.vouchercondition" value="即付" />
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
										TT60天
									</label>
									<input type="radio" id="vouchercondition5"
										name="paymentVoucher.vouchercondition" value="TT120天" />
									<label for="vouchercondition5">
										TT120天
									</label>
									<input type="radio" id="vouchercondition6"
										name="paymentVoucher.vvouchercondition" value="TT120天以上"
										checked="checked" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他" />
									<label for="vouchercondition7">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.vouchercondition=="其他"'>
									<input type="radio" id="vouchercondition1"
										name="paymentVoucher.vouchercondition" value="即付" />
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
										name="paymentVoucher.vvouchercondition" value="TT120天以上" />
									<label for="vouchercondition6">
										TT120天以上
									</label>
									<input type="radio" id="vouchercondition7"
										name="paymentVoucher.vouchercondition" value="其他"
										checked="checked" />
									<label for="vouchercondition7">
										其他
									</label>
								</s:if>
							</td>
						</tr>

						<tr>
							<th>
								类别
							</th>
							<td colspan="8">
								<s:if test='paymentVoucher.category=="总务性采购"'>
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
								</s:if>
								<s:if test='paymentVoucher.category=="原材料采购"'>
									<input type="radio" id="category1"
										name="paymentVoucher.category" value="总务性采购" />
									<label for="category1">
										总务性采购
									</label>
									<input type="radio" id="category2"
										name="paymentVoucher.category" value="原材料采购" checked="checked" />
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
								</s:if>
								<s:if test='paymentVoucher.category=="工程设备采购"'>
									<input type="radio" id="category1"
										name="paymentVoucher.category" value="总务性采购" />
									<label for="category1">
										总务性采购
									</label>
									<input type="radio" id="category2"
										name="paymentVoucher.category" value="原材料采购" />
									<label for="category2">
										原材料采购
									</label>
									<input type="radio" id="category3"
										name="paymentVoucher.category" value="工程设备采购"
										checked="checked" />
									<label for="category3">
										工程设备采购
									</label>
									<input type="radio" id="category4"
										name="paymentVoucher.category" value="其他" />
									<label for="category4">
										其他
									</label>
								</s:if>
								<s:if test='paymentVoucher.category=="其他"'>
									<input type="radio" id="category1"
										name="paymentVoucher.category" value="总务性采购" />
									<label for="category1">
										总务性采购
									</label>
									<input type="radio" id="category2"
										name="paymentVoucher.category" value="原材料采购" />
									<label for="category2">
										原材料采购
									</label>
									<input type="radio" id="category3"
										name="paymentVoucher.category" value="工程设备采购"
										checked="checked" />
									<label for="category3">
										工程设备采购
									</label>
									<input type="radio" id="category4"
										name="paymentVoucher.category" value="其他" />
									<label for="category4">
										其他
									</label>
								</s:if>

							</td>
						</tr>
						<tr>
							<th colspan="9">
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
							<th colspan="4">
								用途
							</th>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
							<tr align="left" id="1456789">
								<th align="center">
									<textarea cols="20"
										name="listpaymentDetail[${pageStatus.index}].businesscontent">${pageList.businesscontent}</textarea>
								</th>
								<th align="center">
									<s:if test='"是"==#pageList.isOk'>
										<input type="radio" id="isOk1"
											name="listpaymentDetail[${pageStatus.index}].isOk" value="是"
											checked="checked" />
											是
										<input type="radio" id="isOk2"
											name="listpaymentDetail[${pageStatus.index}].isOk" value="否" />
										否
								</s:if>
									<s:else>
										<input type="radio" id="isOk1"
											name="listpaymentDetail[${pageStatus.index}].isOk" value="是" />
											是
										<input type="radio" id="isOk2"
											name="listpaymentDetail[${pageStatus.index}].isOk" value="否"
											checked="checked" />
										否
								</s:else>
								</th>
								<th align="center">
									<select id="dept" style="width: 200px;"
										name="listpaymentDetail[${pageStatus.index}].dept"
										onMouseOver="deptSelect(this)">
										<option selected="selected">
											${pageList.dept}
										</option>
									</select>
								</th>
								<th align="center">
									<input type="text"
										name="listpaymentDetail[${pageStatus.index}].remark"
										value="${pageList.remark}">
								</th>
								<th align="center">
									<input type="text" onKeyUp="hejiJine();comparecount(*)"
										id="h${pageStatus.index}"
										name="listpaymentDetail[${pageStatus.index}].voucherMoney"
										value="${pageList.voucherMoney}">
								</th>
								<th align="center" colspan="3">
									<textarea cols="40" rows="2"
										name="listpaymentDetail[${pageStatus.index}].pay_use">${pageList.pay_use}</textarea>
								</th>
								<th align="center">
									<%--									<button onclick="moveTr(istpaymentDetail[${pageStatus.index}].id)">删除</button>--%>
									<a onclick="moveTr(this)">删除</a>
								</th>
							</tr>
						</s:iterator>
						<tr id="uploadtr">
							<th align="left">
								<input type="button" id="inforButton_1"
									onclick="saveHKInfor(this,1)" value="添加明细" />
							</th>
							<th width="29%" align="left" colspan="2">
								<input id="deleteItem" style="display: none;" type="button"
									onclick="delInfor()" value="删除明细" />
							</th>
							<th>
								合计金额
							</th>
							<td>
								<span id="hejiMoney"><font color="red"><label
											id="allMoney"></label> </font> </span>
							</td>
							<th>
								借款金额
							</th>
							<td colspan="3">
								<span id="hejiMoney"><input type="text"
										readonly="readonly" id="voucherMoney"
										name="paymentVoucher.voucherMoney"
										value="${paymentVoucher.voucherMoney}"> </span>
							</td>
						</tr>
						<tr>
							<td colspan="9" align="center">
								<input type="hidden" name="paymentVoucher.id"
									value="${paymentVoucher.id}">
								<input type="hidden" name="paymentVoucher.printStatus"
									value="${paymentVoucher.printStatus}">
								<input type="hidden" name="paymentVoucher.approvaldept"
									value="${paymentVoucher.approvaldept}">
								<%--									<input type="hidden" name="paymentVoucher.contractnum" value="${paymentVoucher.contractnum}">--%>
								<%--									<input type="hidden" name="paymentVoucher.approvalStatus" value="${paymentVoucher.approvalStatus}">--%>
								<%--									<input type="hidden" name="paymentVoucher.approvalApplier" value="${paymentVoucher.approvalApplier}">--%>
								<%--									<input type="hidden" name="paymentVoucher.contractdate" value="${paymentVoucher.contractdate}">--%>
								<input type="submit" value="提交"
									style="width: 60px; height: 40px;" align="top">
								&nbsp;&nbsp;
								<input type="reset" value="取消"
									style="width: 60px; height: 40px;" align="top">
							</td>
						</tr>
						<tr>
							<td colspan="9" style="font-size: 12px;">
								备注：
								<br>
								1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
								2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
								4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
								5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等） 6、领款日为每周二、四（对公转账及急事、特事除外）。
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
var lineCount = "<s:property value='list.size()'/>";//得到明细的总行数
var begAddLineNum = 10;
var count = 0;//标识删除多少明细个数
//下拉科目
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

function moveTr(obj) {
	var total = 0;
	var selectedTr = obj.parentNode.parentNode;
	var tbody = selectedTr.parentNode;
	tbody.removeChild(selectedTr);
	count += 1;
	//complexselectedlist.deleteRow(10+(obj));
	for ( var t = 0; t < lineCount; t++) {
		var id = "h" + t;
		var obj = document.getElementById(id);
		if (obj != null) {
			var cur = obj.value;
			total = total + parseFloat(cur);
		}
	}
	document.getElementById("allMoney").innerHTML = total;//赋给合计总数
	document.getElementById("voucherMoney").value = total;//把总金额赋给借款总金额
}

//删除明细
function delInfor() {
	complexselectedlist.deleteRow(begAddLineNum + 1);
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
	x5.setAttribute('colspan', '3');
	x5.innerHTML = "<textarea cols='40' rows='2' name='listpaymentDetail["
			+ lineCount + "].pay_use'></textarea>";
	var x6 = _tr.insertCell(6);
	x6.innerHTML = "<input type=\"hidden\" >";
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
	//判断明细总行数和删除明细个数作对比
	if (lineCount - count == 0) {
		alert("借款明细至少有一条!");
		return false;
	}
	if (unitname.value == "") {
		alert("借款单位（人）不能为空!");
		unitname.focus();
		return false;
	} else if (relationclient.value == "") {
		alert("关联客户不能为空!");
		relationclient.focus();
		return false;
	} else if (accreditationnum.value == "") {
		alert("评审编号不能为空!");
		accreditationnum.focus();
		return false;
	} else if (contractnum.value == "") {
		alert("合同号不能为空!");
		contractnum.focus();
		return false;
	} else if (voucherMoney.value == "") {
		alert("借款金额不能为空!");
		voucherMoney.focus();
		return false;
	}
}
</script>

</html>
