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
		<form action="paymentDetailAction_updatePaymentDetail.action" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">付款付款明细</font>
					</th>
				</tr>
				<tr>
						<th align="right">
							合同编号:
						</th>
						<td>
						<SELECT id="paymentid" name="detail.paymentid1">
						<option value=""  >
								请选择合同编号
							</option>
						</SELECT>
<%--							<input id="paymentid" name="detail.paymentid" value="${detail.unitname}" />--%>
						</td>
					<!--					<set var="n" value="#parameters.test" />-->
						<th align="right" >
							借款单号码:
						</th>
						<td>
							<input name="detail.borrowerlist_num" value="${detail.borrowerlist_num}" />
						</td>
						<th align="right">
							请购申请单编号:
						</th>
						<td>
							<input name="detail.askrequisition_num" value="${detail.askrequisition_num}" />
						</td>
				</tr>
				<tr>
						
						<th align="right">
							订购单编号:
						</th>
						<td>
							<input   name="detail.orders_num" 	value="${detail.orders_num}" />
						</td>
						<th align="right">
						入库单编号:
					</th>
					 
					
					 <td>
					 <input   name="detail.receipt_num" 	value="${detail.receipt_num}" />
					 </td>
					 <th align="right">
						发票号码:
					</th>
					 <td>
					 <input type="text" name="detail.invoice_num" value="${detail.invoice_num}">
					 </td>
				</tr>
				<tr>
				<th align="right">
						付款金额:
					</th>
					 <td>
					 <input type="text" name="detail.voucherMoney" value="${detail.voucherMoney }">
					 </td>
				
						<th align="right">
						是否借款:
					</th>
					 <td colspan="3">
					 <SELECT name="detail.isOk" >
					 <option value="${detail.isOk}">${detail.isOk}</option>
					 	<option value="是">是</option>
					 	<option value="否">否</option>
					 </SELECT>
					 </td>
					
				</tr>
				<tr>
				 <th align="right">
							业务内容:
						</th>
						<td colspan="6">
						<textarea id="businesscontent"  onmouseover="focus()"  rows="8" cols="50" name="detail.businesscontent" >
						${detail.businesscontent}
						</textarea>
						</td>
				
				</tr>
				<tr>
					<td align="center" colspan="6">
							<input type="hidden" name="detail.id"  value="${detail.id}">
							<input type="hidden" name="detail.unitname1"  value="${detail.unitname1}">
							<input type="hidden" name="detail.number1"  value="${detail.number1}">
						<input type="submit" value="修改" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
$(function() {
	var paymentid = "${detail.paymentid1}";
	$.ajax( {
		url : "paymentDetailAction_findNumber.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {},
		success : function(data) {
			//$("#paymentid").empty();//清空
			$.each(data.data, function(i) {
				if(paymentid==data.data[i].id){
					$("#paymentid").append( "<option value='" + data.data[i].id + "' selected='selected'>"+ data.data[i].number+ "</option>");
				}
					$("#paymentid").append( "<option value='" + data.data[i].id + "'>"+ data.data[i].number+ "</option>");
				})
		}
	});
	var successMessage = '${successMessage}';
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
