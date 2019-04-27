<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在付款申请进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
			</div>

			<div align="center">
					<form action="kvpAssessAction_addEightBReport.action?id=<s:property value="#parameters.id"/>&tag=<s:property value="#parameters.tag"/>" method="post" onsubmit="return checkForm()">
					<div align="center">
					<table>
						<tr>
						<th colspan="2" align="left">
							项目名称:
						</th>
						<th align="left" colspan="4">
							<input type="text" name="eightBReport.projectname">
						</th>
					</tr>
					
					</table>
					</div>
					<table class="table">
					<tr>
					<th>
							项目概要:
						</th>
						<th align="left">
						<textarea rows="5" cols="50" name="eightBReport.summary"></textarea>
						</th>
						<th rowspan="3">
							改善结果:
						</th>
						<th align="left" rowspan="3">
						<textarea rows="22 " cols="50" name="eightBReport.improveoutcomes"></textarea>
						</th>
						</tr>
					
					<tr>
					<th>
							问题陈述:
						</th>
						<th align="left">
						<textarea rows="10" cols="50" name="eightBReport.problemstatement"></textarea>
						</th>
					</tr>
					<tr>
						<th>
							目标:
						</th>
						<th align="left">
						<textarea rows="5" cols="50" name="eightBReport.goal"></textarea>
						</th>
					</tr>	
					<tr>
						<th>
							关键因素:
						</th>
						<th align="left"  colspan="4">
						<textarea rows="5" cols="50" name="eightBReport.factor"></textarea>
						</th>
<%--						<th>--%>
<%--							财务节余:--%>
<%--						</th>--%>
<%--						<th align="left" >--%>
<%--						<textarea rows="5" cols="50"></textarea>--%>
<%--						</th>--%>
					</tr>
					<tr>
								<th colspan="4" align="center">
								<input type="hidden" name="eightBReport.name" value="${sessionScope.Users.name}">
								<input type="hidden" name="eightBReport.unit" value="${companyInfo.name}">
									<input type="submit" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
								</th>
							</tr>
					</table>
					</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" src="javascript/DatePicker/WdatePicker.js" >
	 	$(function() {
		var successMessage = "${successMessage}";
		if (successMessage != "") {
			alert(successMessage);
			parent.location.reload(true);//刷新父页面
		}
})
</script>
	</body>
</html>
