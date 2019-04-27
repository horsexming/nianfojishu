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
			<form action="CustomerAction_updateCustomer.action" method="post"  onsubmit="return check()">
				<table class="table">
					<tr>
						<th colspan="4" align="center"><h3>修改客户信息</h3></th>
					</tr>
					<tr>
						<th align="right">姓名:</th>
						<td>
						<input type="text" name="customer.customer_name" value="${customer.customer_name}" />
						</td>
						<th align="right">公司名称:</th>
						<td><input type="text"  name="customer.company_name"  value="${customer.company_name}" />
						</td>
					</tr>
					<tr>
						<th align="right">手机号:</th>
						<td><input type="text"   name="customer.customer_phone" value="${customer.customer_phone}"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
						</td>
						<th align="right">车型:</th>
						<td><input type="text"  name="customer.cart_ype" value="${customer.cart_ype}" />
						</td>
					</tr>
					<tr>
					<th align="right">状态:</th>
						<td>
						<select id="customer_state" name="customer.customer_state"  >
							<option value="customer.customer_state"  selected="selected">${customer.customer_state}</option>
<!--							<option value="${customer.customer_state}" >${customer.customer_state}</option>-->
							<option value="试用" >试用</option>
							<option value="正式">正式</option>
							<option value="永久">永久</option>
						</select>
						</td>
						<th align="right">周期:</th>
						<td><input type="text" id="period" name="customer.period"  value="${customer.period}" /></td>
					</tr> 
					<tr>
					<th align="right">开始时间</th>
						<td><input type="text" id="startdate"   class="Wdate"  name="customer.startdate" value="${customer.startdate}" 
						 onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
						<input name="customer.customer_id" value="${customer.customer_id}"  type="hidden">
						<input type="submit" value="修改 " class="input" />
						</td>
					</tr>
				</table>
			</form>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
 		$(function(){
// 		var index = "${customer.customer_state}"
// 		var customer_state = document.getElementById("customer_state");
// 		 document.getElementById("customer_state").find("option value='${customer.customer_state}'").attr("selected","true");
// 		customer_state.remove(index);
 			var successMessage = '${successMessage}';
 			if(successMessage!=""){
 				alert(successMessage);
 				parent.location.reload(true);//刷新父页面
 			}
 		})
	</script>
	
	</body>
</html>
