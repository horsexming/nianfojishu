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
				<form action="InformAction!caiwutstz.action" method="post">
				<table class="table">
					<tr>
						<td align="center">特殊通知标题</td>
						<td align="center" colspan="3"><input type="text" name="leaveInform.username"/></td>
					</tr>
					<tr>
						<td align="center"> 班组/部门</td><td align="center">项目名</td><td align="center">金额</td><td align="center">签收</td>
					</tr>
					<tr>
						<td rowspan="5"><input type="text" name="leaveInform.dept"/></td>
						<td><input type="text" name="leaveInform.fuck2"/></td>
						<td><input type="text" name="leaveInform.postsalary" 
										onblur="numberCheck(this.value)"/></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="text" name="leaveInform.fuck3"/></td>
						<td><input type="text" name="leaveInform.secrecysalary"
										onblur="numberCheck(this.value)"/></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="text" name="leaveInform.fuck4"/></td>
						<td><input type="text" name="leaveInform.performancesalary"
										onblur="numberCheck(this.value)"/></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="text" name="leaveInform.fuck5"/></td>
						<td><input type="text" name="leaveInform.achievement"
										onblur="numberCheck(this.value)"/></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="text" name="leaveInform.starttime"/></td>
						<td><input type="text" name="leaveInform.offset"
									onblur="numberCheck(this.value)"/></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" align="center">合计</td>
						<td><input type="text" name="leaveInform.heji"
								onblur="numberCheck(this.value)"/></td>
						<td></td>
					</tr>
					<tr>
						<td align="center">说明</td>
						<td colspan="3"><input type="text" name="leaveInform.shuoming"/></td>
					</tr>
					<tr><td colspan="4" align="center">
						<input type="submit" value= "提交" style="width: 100px; height: 50px;"/>
						<input type="reset" value= "重置" style="width: 100px; height: 50px;"/>
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
function numberCheck(a){
	if(isNaN(a)){
		alert("请填写数字!");
		return false;
	}
}
</script>
	</body>
</html>
