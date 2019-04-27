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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="cusimportanceAction_update.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改客户重要系数<br/>
					（update customers important coefficient）
				</h3>
				<form action="cusimportanceAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
					<tr>
							<th align="right">
								客户名称<br />（Customer name）：
							</th>
							<td>
								<input type="text" name="cusimportance.cuName" id="cuName" 
								value="<s:property value="cusimportance.cuName"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right"><input type="hidden" name="cusimportance.id"
								value="<s:property value="cusimportance.id"/>" />
								当月的销售比<br/>（current month sales ratio）：
							</th>
							<td>
								<input type="text" name="cusimportance.cuMonthSale" id="cuMonthSale" 
								value="<s:property value="cusimportance.cuMonthSale"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								当年的销售比<br/>（current year sales ratio）：
							</th>
							<td>
								<input type="text" name="cusimportance.cuYearSale" id="cuYearSale" 
								value="<s:property value="cusimportance.cuYearSale"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								三年销售预期比<br/>（three years Expected sales ratio）：
							</th>
							<td>
								<input type="text" name="cusimportance.threeYearsExSale" id="threeYearsExSale" 
								value="<s:property value="cusimportance.threeYearsExSale"/>"/>
							</td>
						</tr>
					<%--	<tr>
							<th align="right">
								按金额计算<br/>（Calculated by Amount）：
							</th>
							<td>
								<input type="text" name="cusimportance.byAmount" id="byAmount" 
								value="<s:property value="cusimportance.byAmount"/>"/>
							</td>
						</tr>--%>
						
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var cuMonthSale = document.getElementById("cuMonthSale").value;
	var cuYearSale = document.getElementById("cuYearSale").value;
	var threeYearsExSale = document.getElementById("threeYearsExSale").value;
	
	if (cuMonthSale == "") {
		alert("请输入当月销售比!");
		return false;
	}else if(isNaN(cuMonthSale)){
	  alert("当月销售比栏请输入数字!");
		return false;
	}
	if (cuYearSale == "") {
		alert("请输入当年销售比!");
		return false;
	}else if(isNaN(cuYearSale)){
	  alert("当年销售比栏请输入数字!");
		return false;
	}
	if (threeYearsExSale == "") {
		alert("请输入三年销售预期比!");
		return false;
	}else if(isNaN(threeYearsExSale)){
	  alert("三年销售预期比栏请输入数字!");
		return false;
	}
}
</script>
	</body>
</html>
