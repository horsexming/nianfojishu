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
		<form action="paymentDetailAction_addPaymentDetail.action" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加付款明细</font>
					</th>
				</tr>
				<tr>
					<!-- 	<th align="right">
							合同编号:
						</th>
						<td>
						<SELECT id="paymentid" name="detail.paymentid1">
						<option value="">
								请选择合同编号
							</option>
						</SELECT>
 					<input type="hidden" id="paymentid1" name="detail.number1" value="" />  
 					<input type="hidden" id="unitname1" name="detail.unitname1" value="" />  
						</td> -->
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
						<th align="right">
							订购单编号:
						</th>
						<td>
							<input   name="detail.orders_num" 	value="${detail.orders_num}" />
						</td>
				</tr>
				<tr>
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
					 
							 <th align="right">
								付款金额:
							</th>
							 <td>
							 <input type="text" name="detail.voucherMoney" value="${detail.voucherMoney }">
							 </td>
				</tr>
				<tr>
			
				
						<th align="right">
						是否借款:
					</th>
					 <td>
					 <SELECT name="detail.isOk">
					 <option value="">--请选择--</option>
					 	<option value="是">是</option>
					 	<option value="否">否</option>
					 </SELECT>
					 </td>
					 
					 <th align="right">
						付款依据:
					</th>
					 <td colspan="3">
					 <SELECT name="detail.voucherbasis">
					 <option value="">--请选择--</option>
					 	<option value="合同">合同</option>
					 	<option value="发票">发票</option>
					 	<option value="协议">协议</option>
					 	<option value="通知">通知</option>
					 	<option value="其他依据说明">其他依据说明</option>
					 </SELECT>
					 </td>
					
				</tr>
				<tr>
				 <th align="right">
							业务内容:
						</th>
						<td colspan="6">
						<textarea id="businesscontent"  onmouseover="focus()"  rows="8" cols="50" name="detail.businesscontent" >
						</textarea>
						</td>
				
				</tr>
				<tr>
					<td align="center" colspan="6">
					<input type="hidden" id="paymentid1" name="detail.paymentid1" value="${param.paymentid1}">
					<input type="hidden" id="number1" name="detail.number1" value="">
					<input type="hidden" id="unitname1" name="detail.unitname1" value="">
						<input type="submit" value=" 添加" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function focus(){
			$("#businesscontent").focus();
		}
 /*
		$("#paymentid").change(function(){
			var payment1 = $("#paymentid").val();
			//获取select页面显示的值
			var  payment2 = document.getElementById('paymentid').options[document.getElementById('paymentid').selectedIndex].text;
					$("#paymentid1").attr("value", payment2);
						$.ajax( {
						url : "paymentDetailAction_findUnitname.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {paymentid:payment1},
						success : function(data) {
							//$("#paymentid").empty();//清空
							$.each(data.data, function(i) {
								$("#unitname1").attr("value", data.data[i].unitname);
								})
						}
					});
		});
 
	 
				$(function() {
					$.ajax( {
						url : "paymentDetailAction_findNumber.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {},
						success : function(data) {
							//$("#paymentid").empty();//清空
							$.each(data.data, function(i) {
									$("#paymentid").append( "<option value='" + data.data[i].id + "' >"+ data.data[i].number+ "</option>");
								
								})
						}
			});*/
	$(function(){
		var paymentid1 = $("#paymentid1").val();
					$.ajax( {
						url : "paymentDetailAction_findNumber1.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {paymentid:paymentid1},
						success : function(data) {
							//$("#paymentid").empty();//清空
							$.each(data.data, function(i) {
								$("#number1").attr("value", data.data[i].number);
								$("#unitname1").attr("value", data.data[i].unitname);
								
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
</html>
