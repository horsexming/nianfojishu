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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>备件以旧换新 登记</h3>
				<form action="renew_add.action" method="post" onsubmit="return validate();">
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<td>旧申报编号：<input type="text" name="renew.exAppNumber"/></td>
						<td>备件更换时间：<input type="text" class="Wdate" id="time"
									type="text" name="renew.exDate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td>旧备件名称：<input type="text" name="renew.exMetatag" value="${sto.matetag}"/></td>
					</tr>
					<tr>
						<td>旧备件规格：<input type="text" name="renew.exFormat" value="${sto.format}"/></td>
						<td>旧备件库别：<input type="text" name="renew.exStore" style="margin-left: 18px;" value="${sto.storehouse}"/></td>
						<td>旧备件类别：<input type="text" name="renew.exClass" value="${sto.parClass}"/></td>
					</tr>
					<tr>
						<td>换用人工号：<input type="text" name="renew.exUserJobNum"/></td>
						<td>换用人姓名：<input type="text" name="renew.exUser" style="margin-left: 18px;"/></td>
						<td>旧备件单位：
<%--						<input type="text" name="renew.exUnit" value="${sto.unit}"/>--%>
						<select name="renew.exUnit"  id="unit" >
									<option selected="selected" value="${sto.unit}">${sto.unit}</option>
								</select>
						</td>
					</tr>
					<tr>
						<td>旧备件数量：<input type="text" name="renew.exCount" id="count"/></td>
						<td>更换设备名称：<input type="text" name="renew.exObj" style="margin-left: 18px;" value="${sto.matetag}"/></td>
						<td>旧件存放放置：<input type="text" name="renew.exPosition" value="${sto.place}"/></td>
					</tr>
					<tr>
						<td colspan="3" align="left">更换原因:<textArea rows="4" cols="20" name="renew.exResult"></textArea></td>
					</tr>
					<TR>
						<td colspan="3" align="center">
						<input type="hidden" name="vore.id" value="${vore.id}"/>
						<input type="submit" value="添加"/></td>
					</TR>
				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(function(){
			getUnit("unit");
		})
			var exp = /^\d+$/;
			function validate(){
				var num = $('#count').val();
				var time = $('#time').val();
				if(time == ""){
					alert("请选择日期！谢谢");
					return false;
				}
				if(num == ""){
					alert("请输入数量!");
					return false;
				}
				if(!exp.test(num)){
					alert("数量请输入整数!");
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>
