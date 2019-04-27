<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script type="text/javascript">
//下载工资
function exportWage() {
	var exportWageformDiv = document.getElementById("exportWageform");
	var operatingDiv = document.getElementById("operatingDiv");
	operatingDiv.style.display = "none";
	exportWageformDiv.style.display = "block";
	chageDiv('block', '您将下载工资条或工资单(月份不选为下载所有月份的工资信息)');
}
</script>
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
				<table style="width: 100%; background: url('images/bq_bg2.gif');">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div style="display: none; background-color: #ffffff; width: 100%;"
					id="exportWageform">
					<form action="WageAction!exportWageArticle.action" method="post">
						<input type="hidden" name="pageStatus" value="outsourcing">
						<table align="center">
							<tr>
								<th align="right">
									月份:
								</th>
								<td>
									<input class="Wdate" type="text" name="wage.mouth"
										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
								</td>
								<th align="center">
									<input id="wageArticle" value="导出工资条" type="submit"
										onclick="chageFormAction('wageArticle',this.form);todisabledone(this)" data="downData">
								</th>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">

				</div>
			</div>
			
			<div>
				<a href="WageAction!wageBalance.action?pageStatus=out">
					工资平衡(外发/内发)</a>
				<br />
				<br />
				<a href="WageAction!wageBalance.action?pageStatus=in">已分配奖金转换为工资</a>
				<br />
				<br />
				<a href="WageAction!wageBuCha.action">工资补差(对所有已生成工资信息进行工资补差操作)</a>
				<br />
				<br />
				<a href="javascript:exportWage()">下载外包及补差工资</a>
				<br />
				<br />

			</div>

			<div align="left" style="width: 50%">
				<font color="red">${errorMessage}</font>
				<font color="red">${successMessage}</font>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
