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
				<h3>添加报检记录</h3>
				<form action="maintain_add.action" method="post" onsubmit="return validate()">
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<td>编号：<input type="text" name="voma.number" value="${store.number }" style="margin-left: 30px;"/></td>
						<td>名称：<input type="text" name="voma.matetag" value="${store.matetag }"/></td>
						<td>规格：<input type="text" name="voma.format" value="${store.format }"/></td>
					</tr>
					<tr>
						<td>单位：
<%--						<input type="text" name="voma.unit" value="${store.unit }" style="margin-left: 30px;"/>--%>
						<select name="voma.unit"  id="category">
									<option selected="selected" value="${store.unit}">${store.unit }</option>
								</select>
						</td>
						<td>数量：<input type="text" name="voma.amount" id="count"/></td>
						<td><input type="hidden" name="voma.id" value="${store.id}"/>
							<input type="hidden" name="voma.state" value="修复中"/></td>
					</tr>
					<tr>
						<td>报修时间：<input type="text" style="width: 155px;" class="Wdate"
									type="text" name="voma.fixDate" id="time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td colspan="2">备注：<input type="text" name="voma.more" value="${store.more}" style="width: 240px;"/></td>
					</tr>
					<TR>
						<td colspan="3" align="center">
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
			getUnit("category");
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
