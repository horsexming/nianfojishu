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
	<body onload="convertCurrency(${paymentVoucher.voucherMoney})"  oncontextmenu="return false" 
	onselectstart="return false" ondragstart="return false">
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
				</div>
			</div>

			<div align="center">
				<center>
					<div id="printDiv">
						<table align="center" class="table" style="width: 98%;">
							<tr>
								<th rowspan="2" align="center">
								<!-- ${companyInfo.logoOKjpg} -->
									<img align="middle" ; src="<%=path %>/images/biaozhi.png"
										style="width: 60px; height: 50px;">
									</th>
									<th>
									<font size="5"> <b>项目名称:</b></font>
										<font size="4">${eightBReport.projectname}</font>
								</th>
							</tr>
							<tr>
								<th>
									<img align="left" ; src="<%=path %>/images/image2.png"
										style="width: 100%; height: 15px;">
								</th>
							</tr>
						</table>
						<table class="table" style="width: 98%;height: 90%;">
							<tr>
						<th align="center" style="width: 50%;height: 30px;">
							单位:${eightBReport.unit}
						</th>
						<th align="left" style="width: 50%;height: 30px;">
							姓名:	${eightBReport.name}
						</th>
					</tr>
					<tr>
					<th align="left">
							项目概要:&nbsp;&nbsp;&nbsp;&nbsp;
							${eightBReport.summary}
						</th>
						<th rowspan="3" align="left">
							改善结果<br/><br/><br/><br/>${eightBReport.improveoutcomes}
						</th>
					</tr>
					<tr>
					<th align="left">
							问题陈述:<br/>${eightBReport.problemstatement}
						</th>
					</tr>
					<tr>
						<th align="left">
							目标:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							${eightBReport.goal}
						</th>
					</tr>
					<tr>
						<th align="left">
							关键因素:<br/>${eightBReport.factor}
						</th>
						<th align="left">
							财务节余:<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							${executeKVP.costsavings}
						</th>
					</tr>
					</table>
					</div>
					<table class="table" style="width: 98%">
						<tr>
							<td align="center">
								<s:if test="'同意'==eightBReport.status">
									<input style="width: 80px; font-size: 18px;"
										onclick="pagePrint1()" type="button" value="打印">
								</s:if>
								<s:else>
									<span style="font-size: 20; color: red; font-weight: bold">等待审核人通过后，方可打印借款！</span>
								</s:else>
							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script language="JavaScript">
			function pagePrint1(){
			pagePrint('printDiv','sy');
		}
</script>
	</body>
</html>
