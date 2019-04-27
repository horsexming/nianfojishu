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
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeDiv.gif" width="30"
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
				<h3>
					统计里程管理
				</h3>
				<table class="table">
					<tr>
						<td align="center">
							驾驶员：
							<input id="pilotname1" type="text" name="pilotname"
								value="${singleCarAll.pilotname}" />
						</td>
						<td align="center">
							车牌号：
							<input id="car_number1" type="text" name="car_number"
								value="${singleCarAll.car_number}" />
						</td>
					</tr>
					<tr>
						<td align="center">
							开始时间：
							<input class="Wdate" id="firsttime1" type="text" name="firsttime"
								value="${singleCarAll.firsttime}"
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
						</td>
						<td align="center">
							结束时间：
							<input class="Wdate" id="finishtime1" type="text"
								name="finishtime" value="${singleCarAll.finishtime}"
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<%--							<input type="submit" style="width: 100px; height: 40px;"--%>
							<%--								value="确定" class="input" />--%>
					</tr>
				</table>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">

						<td align="center">
							姓名
						</td>
						<td align="center">
							车号
						</td>
						<td align="center">
							合计公里
						</td>
						<td align="center">
							合计金额
						</td>
						<td align="center">
							长途公里
						</td>
						<td align="center">
							长途金额
						</td>
						<td align="center">
							早出晚归次数
						</td>
						<td align="center">
							早出晚归金额
						</td>
						<td align="center">
							周末及厂休次数
						</td>
						<td align="center">
							周末及厂休金额

						</td>
						<td align="center">
							总金额
						</td>
						<td align="center">
							领款人
						</td>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">

						<td align="center">
							${singleCarAll.pilotname}
						</td>

						<td align="center">
							${singleCarAll.car_number}
						</td>
						<td align="center">
							${singleCarAll.hjkilometers}
						</td>
						<td align="center">
							${singleCarAll.hjmoney}
						</td>
						<td align="center">
							${singleCarAll.ctkilometers}
						</td>
						<td align="center">
							${singleCarAll.ctmoney}
						</td>
						<td align="center">
							${singleCarAll.zcwgcs}
						</td>
						<td align="center">
							${singleCarAll.zcwgmoney}
						</td>
						<td align="center">
							${singleCarAll.zccs}
						</td>
						<td align="center">
							${singleCarAll.zcmoney}
						</td>
						<td align="center">
							${singleCarAll.allmoney}
						</td>
						<td align="center">
							${singleCarAll.permoney}
						</td>

					</tr>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">

								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
						</s:else>
				</table>
				<form action="singleCarAction_updateExamListC.action?" method="post"
					onsubmit="app()">
					<input id="ok" class="input" style="width: 120px;" type="submit"
						value="申请审批" />
					<input id="pilotname2" type="hidden"
						value="${singleCarAll.pilotname}" name="pilotname">
					<input id="car_number2" type="hidden"
						name="car_number">
					<input id="firsttime2" type="hidden"
						value="${singleCarAll.firsttime}" name="firsttime">
					<input id="finishtime2" type="hidden"
						value="${singleCarAll.finishtime}" name="finishtime"><%--
						var car_number = document.getElementById("car_number1").value
	document.getElementById("car_number2").value = car_number;
				--%></form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
function app() {
	var pilotname = document.getElementById("pilotname1").value;
	document.getElementById("pilotname2").value = pilotname;
	var firsttime = document.getElementById("firsttime1").value
	document.getElementById("firsttime2").value = firsttime;
	var finishtime = document.getElementById("finishtime1").value
	document.getElementById("finishtime2").value = finishtime;
}<%--    function validate() {--%>
<%--	if (!validateText("firsttime", "开始时间")) {--%>
<%--		return false;--%>
<%--	}--%>
<%--	if (!validateText("finishtime", "结束时间")) {--%>
<%--		return false;--%>
<%--	}--%>
<%--}--%>
<%----%>
<%--function validateText(id, textname) {--%>
<%--	var textValue = $.trim($("#" + id).val());--%>
<%--	if (textValue == null || textValue == "") {--%>
<%--		alert(textname + "不能为空");--%>
<%--		return false;--%>
<%--	}--%>
<%--	return true;--%>
<%--}--%>
	</script>
</html>
