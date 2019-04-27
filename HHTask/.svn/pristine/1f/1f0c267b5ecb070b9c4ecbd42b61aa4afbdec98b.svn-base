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
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="business_addInvoice.action" method="post" onsubmit="return validate()" enctype="multipart/form-data">
				<table>
					<tr><th align="center" colspan="5">发票</th></tr>
					<tr>
						<td>发票号:</td><td><input type="text" name="invoice.number" id="number"/></td>
						<td>收款单位:</td><td><input type="text" name="invoice.collectionUnit" id="collectionUnit"/></td>
					</tr>
					<tr>
						<td>费用金额:</td><td><input type="text" name="invoice.money" id="money"/>
						<select name="invoice.currencyType">
							<option value="元" selected="selected">元</option>
							<option value="美金">美金</option>
						</select>
						</td>
						<td>付款依据:</td><td><input type="file" name="myFile" id="myFile"/></td>
					</tr>
					<tr align="center">
						<td align="center" colspan="4">
						<input type="hidden" value="<%=request.getParameter("id") %>" name="id"/>
						<input type="submit" value="添加发票" style="width: 80px;height: 50px;"/>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function validate(){
				var number = document.getElementById("number").value; //发票号
				var collectionUnit = document.getElementById("collectionUnit").value; //收款单位
				var money = document.getElementById("money").value; //费用金额
				var myFile = document.getElementById("myFile").value; //费用金额
				if(number == ""){
					alert("请输入发票号码!谢谢");
					return false;
				}
				if(collectionUnit == ""){
				 	alert("请输入收款单位!谢谢");
					return false;
				}
				if(money == ""){
				 	alert("请输入费用金额!谢谢");
					return false;
				}
				if(myFile == ""){
				 	alert("请上传付款依据!谢谢");
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>
