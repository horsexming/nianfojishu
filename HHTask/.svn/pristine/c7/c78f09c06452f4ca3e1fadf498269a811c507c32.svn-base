<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<body >
		<form action="paymentVoucherAction_addPaymentVoucher.action" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加付款申请</font>
					</th>
				</tr>
				<tr>
						<th align="right">
							收款单位:
						</th>
						<td>
							<input name="paymentVoucher.unitname" />
						</td>
					<!--					<set var="n" value="#parameters.test" />-->
						<th align="right">
							关联客户:
						</th>
						<td>
							<input  name="paymentVoucher.relationclient" />
						</td>
						<th align="right">
							评审编号:
						</th>
						<td>
							<input name="paymentVoucher.accreditationnum"/>
						</td>
				</tr>
				<tr>
						
						<th align="right">
							合同号:
						</th>
						<td>
							<input id="contractnum"  name="paymentVoucher.contractnum" />
						</td>
						<th align="right">
						付款性质:
					</th>
					 
<%--					<textarea id="paymentVoucher.voucherMoney" rows="10" cols="50" name="${paymentVoucher.voucherMoney}" ></textarea>--%>
					 <td>
					 <SELECT name="paymentVoucher.voucherNature">
					 	<option value="">--请选择--</option>
					 	<option value="预付">预付</option>
					 	<option value="中间付款">中间付款</option>
					 	<option value="余款">余款</option>
					 	<option value="质保金">质保金</option>
					 	<option value="借款">借款</option>
					 	<option value="冲账">冲账</option>
					 	<option value="其他">其他</option>
					 </SELECT>
					 </td>
					 
					 <th align="right">
						付款方式:
					</th>
					 <td>
					 <SELECT name="paymentVoucher.voucherway">
					 <option value="">--请选择--</option>
					 	<option value="银行">银行</option>
					 	<option value="汇兑">汇兑</option>
					 	<option value="支票">支票</option>
					 	<option value="贷记">贷记</option>
					 	<option value="现金">现金</option>
					 	<option value="其他">其他</option>
					 </SELECT>
					 </td>
				</tr>
				<tr>
				<th align="right">
						付款情况:
					</th>
					 <td>
					 <SELECT name="paymentVoucher.vouchersituation">
					 <option value="">--请选择--</option>
					 	<option value="总额">总额</option>
					 	<option value="已支付">已支付</option>
					 	<option value="本次应付">本次应付</option>
					 	<option value="累计支付">累计支付</option>
					 	<option value="余额">余额</option>
					 </SELECT>
					 </td>
				
						<th align="right">
						付款条件:
					</th>
					 <td>
					 <SELECT name="paymentVoucher.vouchercondition">
					 <option value="">--请选择--</option>
					 	<option value="即付">即付</option>
					 	<option value="TT30天">TT30天</option>
					 	<option value="TT60天">TT60天</option>
					 	<option value="TT90天">TT90天</option>
					 	<option value="TT120天">TT120天</option>
					 	<option value="TT120天以上">TT120天以上</option>
					 	<option value="其他">其他</option>
					 </SELECT>
					 </td>
					 	<th align="right" >
							类别:
						</th>
						<td>
<%--							<input name="paymentVoucher.category" value="${paymentVoucher.category}" />--%>
							<select name="paymentVoucher.category">
							<option value="">--请选择--</option>
							<option value="总务性采购">总务性采购</option>
							<option value="原材料采购">原材料采购</option>
							<option value="工程设备类采购">工程设备类采购</option>
							<option value="其他">其他</option>
							</select>
						</td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						<input type="submit" value=" 添加" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
$(function() {
	$("#contractnum").focus();
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
