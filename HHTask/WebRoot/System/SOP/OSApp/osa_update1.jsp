<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
//表单检查
function chackForm() {
	var customer = document.getElementById("customer");//客户
	var markID = document.getElementById("markID");//件号
	var processNO = document.getElementById("processNO");//籍贯
	var deliveryCount = document.getElementById("deliveryCount");//交付数量
	var orderId = document.getElementById("orderId");//订单号
	var yuceCount = document.getElementById("yuceCount");//预测产量
	var procardCycle = document.getElementById("procardCycle");//加工周期
	var productCysle = document.getElementById("productCysle");//产品生命周期
	var osOneRengong = document.getElementById("osOneRengong");//外委单件人工费用
	var osOneMater = document.getElementById("osOneMater");//单件材料
	var osOneOthsers = document.getElementById("osOneOthsers");//单价其他
	var selfOneRengong = document.getElementById("selfOneRengong");//自制所需工资总额
	//var presentAddress = document.getElementById("presentAddress");//现住址
	if (customer.value == "") {
		alert("客户信息不能为空!");
		customer.focus();
		return false;
	} else if (markID.value == "") {
		alert("零件号不能为空!");
		markID.focus();
		return false;
	} else if (processNO.value == "") {
		alert("工序号不能为空!");
		processNO.focus();
		return false;
	} else if (deliveryCount.value == "") {
		alert("交付数不能为空!");
		deliveryCount.focus();
		return false;
	} else if (orderId.value == "") {
		alert("订单号不能为空!");
		orderId.focus();
		return false;
	} else if (yuceCount.value == "") {
		alert("预测产量不能为空!");
		yuceCount.focus();
		return false;
	} else if (procardCycle.value == "") {
		alert("加工周期不能为空!");
		procardCycle.focus();
		return false;
	} else if (productCysle.value == "") {
		alert("产品生命周期不能为空!");
		productCysle.focus();
		return false;
	} else if (osOneRengong.value == "") {
		alert("外委单件人工不能为空!");
		osOneRengong.focus();
		return false;
	} else if (osOneMater.value == "") {
		alert("单价材料不能为空!")
		osOneMater.focus();
		return false;
	} else if (osOneOthsers.value == "") {
		alert("单价其他不能为空!")
		osOneOthsers.focus();
		return false;
	//} else if (selfOneRengong.value == "") {
		//alert("自制所需工资总额不能为空!");
	//	selfOneRengong.focus();
	//	return false;
	} else {
		return true;
	}
}

 

 
 
 
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px; "
					align="left">
					 
				</div>

				<div align="center">
					<form action="osaAction!updateOSAById0.action?cpage=<s:property value="#parameters.cpage"/>" method="post"
						onsubmit="return chackForm()" enctype="multipart/form-data">
						<table width="100%" border="0" class="table">
							<tr>
								<th colspan="6" align="center">
									外委产品信息登记
								</th>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									基本信息
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									申报部门:
								</th>
								<td>
									<input type="text"  name="osa.dept" class="horizontalLine"
										value="${sessionScope.Users.dept}" readonly="readonly"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" />
									<font color="red"> *</font>
								</td>
								<th align="right">
									客户名称:
								</th>
								<td>
									<input id="customer" name="osa.customer" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" value="${osa.customer}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									加急:
								</th>
								<td>
								<s:if test='osa.isJiaji=="是"'>
									<input type="radio" name="osa.isJiaji" value="是"
											checked="checked">
										是
										<input type="radio" name="osa.isJiaji" value="否">
										否
									<font color="red"> *</font>
								</s:if>
								<s:else>
								<input type="radio" name="osa.isJiaji" value="是" >
									是
									<input type="radio" name="osa.isJiaji" value="否" checked="checked" >
									否
									<font color="red"> *</font>
								</s:else>
									
								</td>

							</tr>
							<tr>

								<th align="right">
									零件号:
								</th>
								<td>
									<input id="markID" name="osa.markID" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" value="${osa.markID}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									外委交付数量:
								</th>
								<td colspan="3">
									<input onkeyup="this.value=this.value.replace(/\D/g,'')"
										id="deliveryCount" name="osa.deliveryCount"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" value="${osa.deliveryCount}">
									<font color="red"> 件*</font>
								</td>
							</tr>
							<tr>
							<th align="right">
									工序号:
								</th>
								<td>
									<input id="processNO" name="osa.processNO"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" value="${osa.processNO}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									工序名:
								</th>
								<td colspan="3">
									<input   name="osa.processName"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" value="${osa.processName}">
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<br />
									<br />
									<input type="hidden"  name="osa.id"  value="${osa.id}">
									<input type="submit" value="修改"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置"
										style="width: 80px; height: 50px;" />
									<br />
									<br />
								</td>
							</tr>
						</table>
					</form>
					<div>
						<font color="red">${successMessage}</font>
						<font color="red">${errorMessage}</font>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
