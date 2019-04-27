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
		<script type="text/javascript">

</script>
	</head>
	<!--	//onload="createDept('repairdepartment');"-->
	<body>
		<form action="CustomerAction_addCustomer.action" method="post" >
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加客户信息</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						姓名:
					</th>
					<td>
						<input   name="customer.customer_name" value="" />
					</td>
					<th align="right">
						客户状态:
					</th>
					<td>
<!--						<input id="customer_state" name="customer.customer_state" />-->
						<select id="customer_state"  name="customer.customer_state" >
							<option value="试用">试用</option>
							<option value="正式">正式</option>
							<option value="永久">永久</option>
						</select>
					</td>
				</tr>
				<tr>
				<th align="right">
						公司名称:
					</th>
					<td>
						<input id="company_name"   name="customer.company_name" />
					</td>
					<th align="right">
						车型:
					</th>
					<td>
						 <input id="cart_ype" name="customer.cart_ype"  />
					</td>
				</tr>
				<tr>
					<th align="right">
						手机号:  
					</th>
					<td>
						<input id="customer_phone" name="customer.customer_phone"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
					</td>
					<th  align="right">
						周期
					</th>
					<td>
					 <input id="period" name="customer.period" />
					</td>
				</tr>
				<tr>
					<th align="right">
						开始时间
					</th>
					<td>
					<input type="text" id="startdate"  class="Wdate" name="customer.startdate" 
					 onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
<!--					 <span style="color:red;font-size:10px">若为空则为系统当前时间</span>-->
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="确  定" class="input" />
						&nbsp;&nbsp;
						<input type="button" value="关 闭" onclick="isdown()" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
$(function() {
	$("#customer_state").click(function(){
	var customer_state = $("#customer_state").val();
	if(customer_state=="永久"){
		$("#period").attr("disabled","true");
		$("#startdate").removeClass("onClick");
		$("#startdate").removeAttr("class");
		$("#startdate").attr("disabled","true");
	}else{
		$("#period").removeAttr("disabled");
		$("#startdate").removeAttr("disabled");
		$("#startdate").attr("class","Wdate");
	}
		
	});
	var successMessage = '${successMessage}';
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})
	//刷新父页面关闭当前窗体
	function isdown(){
		parent.location.reload(true);//刷新父页面
	}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
