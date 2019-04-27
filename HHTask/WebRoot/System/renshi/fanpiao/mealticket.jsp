<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<a href="mealAction!personFind.action" style="color: #ffffff">个人申请查询</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				
				<form action="mealAction!save.action" method="post" name="form1"
					onsubmit="return check()">
					<table class="table">
						<tr>
							<th colspan="4">
								客饭票
							</th>
						</tr>
						<tr>
							<th align="right">
								来客姓名:
							</th>
							<td align="left">
								<div style="float: left;width=90px">
									<input type="text" name="mealticket.name" id="name"
										onblur="nameCheck()">
								</div>
								<div id="rename" style="float: left; width: 150px;">
								</div>
							</td>
							<th align="right">
								职务:
							</th>
							<td align="left">
								<div style="float: left;width=90pxs">
									<input type="text" name="mealticket.job" id="job"
									onblur="jobCheck()">
								</div>
								<div id="rejob" style="float: left; width: 160px;">
								</div>
							</td>
						</tr>
						<tr>
							<th align="right">
								来客单位:
							</th>
							<td align="left">
								<div style="float: left;">
									<input type="text" name="mealticket.company" id="company"
									onblur="companyCheck()">
								</div>
								<div id="recompany" style="float: left; width: 100px;">
								</div>
							</td>
							<th align="right">
								人数:
							</th>
							<td align="left">
								<div style="float: left;width=90pxs">
									<input type="text" name="mealticket.number" id="number"
									onblur="numberCheck()">
								</div>
								<div id="renumber" style="float: left; width: 160px;">
								</div>
							</td>
						</tr>
						<tr>
							<th align="right">
								事由:
							</th>
							<td align="left">
								<input type="text" name="mealticket.reason" id="reason"
									onblur="reasonCheck()">
								<span id="rereason"></span>
							</td>
							<th align="right">
								有效日期:
							</th>
							<td align="left">
								<input type="text" name="mealticket.intime" id="manage" class="Wdate"
									onblur="manageCheck()" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd日',skin:'whyGreen'})">
								<span id="remanage"></span>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="提交" class="input" />
								<input type="reset" value="重置" class="input" />
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
 function check()
 {
 	var testValue1=document.getElementById("name").value;
 	if(testValue1==""){
 		alert("请填写来客姓名!");
 		return false;
 	}
 	var testValue2=document.getElementById("job").value;
 	if(testValue2==""){
 		alert("请填写职务!");
 		return false;
 	}
 	var testValue3=document.getElementById("company").value;
 	if(testValue3==""){
 		alert("请填写单位!");
 		return false;
 	}
 	var testValue4=document.getElementById("number").value;
 	if(testValue4==""){
 		alert("请填写人数!");
 		return false;
 	}
 	if(isNaN(testValue4)){
 		alert("请输入数字!");
 		return false;
 	}
 	var testValue5=document.getElementById("reason").value;
 	if(testValue5==""){
 		alert("请填写事由!");
 		return false;
 	}
 	var testValue6=document.getElementById("manage").value;
 	if(testValue6==""){
 		alert("请填写有效日期!");
 		return false;
 	}
	return true;
 }
 function nameCheck(){
 	var testValue=document.getElementById("name").value;
 	var mdiv = document.getElementById("rename"); 
 	if(testValue==""){ 
 	mdiv.innerHTML ="<font color='red'>来客不能为空</font>"; 
 	}else{
 	mdiv.innerHTML ="<font color='red'></font>"
 	}
 }
  function jobCheck(){
 	var testValue=document.getElementById("job").value;
 	var mdiv = document.getElementById("rejob");
 	if(testValue==""){ 
 	mdiv.innerHTML ="<font color='red'>职务不能为空</font>"; 
 	}else{
 	mdiv.innerHTML ="<font color='red'></font>"
 	}
 }
  function companyCheck(){
 	var testValue=document.getElementById("company").value;
 	var mdiv = document.getElementById("recompany"); 
 	if(testValue==""){
 	mdiv.innerHTML ="<font color='red'>单位不能为空</font>"; 
 	}else{
 	mdiv.innerHTML ="<font color='red'></font>"
 	}
 }
  function numberCheck(){
 	var testValue=document.getElementById("number").value;
 	var mdiv = document.getElementById("renumber"); 
 	if(testValue==""){
 	mdiv.innerHTML ="<font color='red'>人数不能为空</font>"; 
 	}else if(isNaN(testValue)){
 		mdiv.innerHTML ="<font color='red'>请输入数字</font>"
 	}else{
 	mdiv.innerHTML ="<font color='red'></font>"
 	}
 }
  function reasonCheck(){
 	var testValue=document.getElementById("reason").value;
 	var mdiv = document.getElementById("rereason"); 
 	if(testValue==""){
 	mdiv.innerHTML ="<font color='red'>事由不能为空</font>"; 
 	}else{
 	mdiv.innerHTML ="<font color='red'></font>"
 	}
 }
  function manageCheck(){
 	var testValue=document.getElementById("manage").value;
 	var mdiv = document.getElementById("remanage"); 
 	if(testValue==""){
 	mdiv.innerHTML ="<font color='red'>有效日期不能为空</font>"; 
 	}else{
 	mdiv.innerHTML ="<font color='red'></font>"
 	}
 }
 
</script>
	</body>
</html>
