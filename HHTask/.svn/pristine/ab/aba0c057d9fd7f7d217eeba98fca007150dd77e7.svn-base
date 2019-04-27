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
				<form id="myForm">
					<input type="hidden" name="quotationList.id" value="${quotationList.id}" />
					<table>
						<tr>
							<th colspan="2">修改物流价格</th>
						</tr>
						<tr>
							<th>运输费:</th>
							<td><input name="quotationList.transportation" value="${quotationList.transportation}"/> </td>
						</tr>
						<tr>
							<th>包装费:</th>
							<td><input name="quotationList.pack" value="${quotationList.pack}" /> </td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input id="submitBtn1" type="button" value="确定">
								<input type="reset" />
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
			$("#submitBtn1").bind("click", function(){
				$.ajax({
					type: "POST",
					url: "ProjectQuotationList_updateWl.action",
					data: $("#myForm").serialize(),
					dataType: 'json',
					success: function(msg){
						if(msg.success){
							alert(msg.message);
							window.location.reload();
						} else {
							alert(msg.message);
						}
					}
				});
			});
		</script>
	</body>
</html>
