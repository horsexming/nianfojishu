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
			<form action="business_updateExamineFlow.action" method="post" onsubmit="validate()">
				<table>
						<tr>
							<th align="center" colspan="2">修改审核流程</th>
						</tr>
						<tr>
							<td>审核流程：</td><td><input type="text" id="flow" name="ef.flow" style="width: 200px;" value="${ef.flow }"/></td>
						</tr>
						<tr>
							<td>部门：</td><td><input type="text" id="dept" name="ef.dept" value="${ef.dept }"/></td>
						</tr>
						<tr>
							<input type="hidden" name="ef.id" value="${ef.id }"/>
							<td align="center" colspan="2"><input type="submit" value="修改流程"/></td>
						</tr>
					</table>
					</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script>
			function validate() {
				var content = document.getElementById("flow").value; //流程
				var collectionUnit = document.getElementById("dept").value; //部门
				if(flow == ""){
					alert("请输入审核流程!谢谢");
					return false;
				}
				if(dept == ""){
					alert("请输入部门！谢谢");
					return false;
				}
				return true;
			}
			</script>
	</body>
</html>
