<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
//改变ActionUrl
function chageFormAction(obj, form) {
	form.action = "WageAction!exportWageArticle.action?articleOrSingle=" + obj;
	form.submit();

}

// 查看工资明细
function wageDetails() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var operatingDiv = document.getElementById("operatingDiv");
			var exportWageformDiv = document.getElementById("exportWageform");
			exportWageformDiv.style.display = "none";
			operatingDiv.style.display = "block";
			operatingDiv.innerHTML = ulMessage;
			chageDiv('block');
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//更改标题
function chageTitle(userName, id) {
	var titleDiv = document.getElementById("title");
	titleDiv.innerHTML = "您正在查看<font color='red'> "
			+ userName
			+ "</font> 的工资明细 <input type='button' value='打印' onclick=pagePrint('operatingDiv')>";
	sendRequest("WageAction!showWageDetails.action?id=" + id, wageDetails);
}

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
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; width: 980px;">
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
						<input type="hidden" name="pageStatus" value="ass">
						<table align="center">
							<tr>
								<th align="right">
									月份:
								</th>
								<td>
									<input class="Wdate" type="text" name="wage.mouth"
										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
								</td>
								<th align="right">
									姓名:
								</th>
								<td>
									<input type="text" name="wage.userName" />
								</td>
								<th align="right">
									排除部门:
								</th>
								<td>
									<input type="text" name="wage.dept" value="" />
									<span><font style="font-size: 12px;">多个部门用逗号隔开(如:工会,物业)</font>
									</span>
								</td>
							</tr>
							<tr>
								<th align="left" colspan="4" style="padding: 20px 0 0 150px;">
									<input id="wageArticle" value="导出工资条明细" type="submit"
										style="height: 50px;"
										onclick="chageFormAction('wageArticle',this.form);todisabledone(this)"
										data="downData" />
									<input id="wageArticle" value="导出工资发放表" type="submit"
										style="height: 50px;"
										onclick="chageFormAction('wageff',this.form);todisabledone(this)"
										data="downData" />
									<input id="wageSingle" value="导出工资单" type="submit"
										style="height: 50px;"
										onclick="chageFormAction('wageSingle',this.form);todisabledone(this)"
										data="downData" />
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

			<div align="center" id="findAllWage" style="display: block">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							姓名
						</th>
						<th align="center">
							部门
						</th>
						<th align="center">
							发放月份
						</th>
						<th align="center">
							工资状态
						</th>
						<th align="center">
							工资类别
						</th>
						<th>
							实发工资
						</th>
						<th>
						</th>
						<th>
							社保(个人)
						</th>
						<th>
							社保(单位)
						</th>
						<th>
						</th>
						<th>
							公积金(个人)
						</th>
						<th>
							公积金(单位)
						</th>
						<th>
						</th>
						<th>
							个税
						</th>
						<th>
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="wageList" id="pageWageList"
						status="pageListStatus">
						<s:if test="#pageListStatus.index%2==1">
							<tr align="right" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="right" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageListStatus.index+1" />
						</td>
						<td align="left">
							${pageWageList.userName}
						</td>
						<td align="left">
							${pageWageList.dept}
						</td>
						<td align="left">
							${pageWageList.mouth}
						</td>
						<td>
							${pageWageList.wageStatus}
						</td>
						<td>
							${pageWageList.wageClass}
						</td>
						<td>
							<fmt:formatNumber type="number"
								value="${pageWageList.shifagongzi}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<th align="center"
							onclick="window.open('<%=basePath%>/System/caiwu/baobiao/wageDataAnalysis.jsp?id=${pageWageList.userId}&pageStatus=sf')"
							style="cursor: hand">
							<s:if test='#pageWageList.sfsj=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='#pageWageList.sfsj=="↓"'>
								<font color="green">
							</s:elseif>
							${pageWageList.sfsj}
							</font>
						</th>
						<td>
							<fmt:formatNumber type="number"
								value="${pageWageList.tongchoujin+pageWageList.yiliaobaoxian+pageWageList.shiyebaoxian+pageWageList.gongshangbaoxian+pageWageList.shengyubaoxian}"
								pattern="0.00" maxFractionDigits="2" />
						</td>
						<td>
							<fmt:formatNumber type="number"
								value="${pageWageList.dwtongchoujin+pageWageList.dwyiliaobaoxian+pageWageList.dwshiyebaoxian+pageWageList.dwgongshangbaoxian+pageWageList.dwshengyubaoxian}"
								pattern="0.00" maxFractionDigits="2" />
						</td>
						<th align="center"
							onclick="window.open('<%=basePath%>/System/caiwu/baobiao/wageDataAnalysis.jsp?id=${pageWageList.userId}&pageStatus=sb')"
							style="cursor: hand">
							<s:if test='#pageWageList.sbsj=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='#pageWageList.sbsj=="↓"'>
								<font color="green">
							</s:elseif>
							${pageWageList.sbsj}
							</font>
						</th>
						<td>
							<fmt:formatNumber type="number" value="${pageWageList.gongjijin}"
								pattern="0.00" maxFractionDigits="2" />
						</td>
						<td>
							<fmt:formatNumber type="number"
								value="${pageWageList.dwgongjijin}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<th align="center"
							onclick="window.open('<%=basePath%>/System/caiwu/baobiao/wageDataAnalysis.jsp?id=${pageWageList.userId}&pageStatus=gjj')"
							style="cursor: hand">
							<s:if test='#pageWageList.gjjsj=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='#pageWageList.gjjsj=="↓"'>
								<font color="green">
							</s:elseif>
							${pageWageList.gjjsj}
							</font>
						</th>
						<td>
							<fmt:formatNumber type="number"
								value="${pageWageList.yingjiaoshuijin}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<th align="center"
							onclick="window.open('<%=basePath%>/System/caiwu/baobiao/wageDataAnalysis.jsp?id=${pageWageList.userId}&pageStatus=gs')"
							style="cursor: hand">
							<s:if test='#pageWageList.gssj=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='#pageWageList.gssj=="↓"'>
								<font color="green">
							</s:elseif>
							${pageWageList.gssj}
							</font>
						</th>
						<td>
							<a
								href="javascript:chageTitle('${pageWageList.userName}','${pageWageList.id}')">明细</a>
						</td>
						</tr>
					</s:iterator>
					<tr align="right" style="font-weight: bolder;">
						<td colspan="6">
							金额汇总(以及与上月金额比较):
						</td>
						<td>
							<fmt:formatNumber type="number" value="${code[0]}" pattern="0.00"
								maxFractionDigits="2" />

						</td>
						<td align="center">
							<s:if test='code[1]=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='code[1]=="↓"'>
								<font color="green">
							</s:elseif>
							${code[1]}
							</font>
						</td>
						<td colspan="2">
							<fmt:formatNumber type="number" value="${code[2]}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<td align="center">
							<s:if test='code[3]=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='code[3]=="↓"'>
								<font color="green">
							</s:elseif>
							${code[3]}
							</font>
						</td>
						<td colspan="2">
							<fmt:formatNumber type="number" value="${code[4]}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<td align="center">
							<s:if test='code[5]=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='code[5]=="↓"'>
								<font color="green">
							</s:elseif>
							${code[5]}
							</font>
						</td>
						<td colspan="1">
							<fmt:formatNumber type="number" value="${code[6]}" pattern="0.00"
								maxFractionDigits="2" />
						</td>
						<td align="center">
							<s:if test='code[7]=="↑"'>
								<font color="red">
							</s:if>
							<s:elseif test='code[7]=="↓"'>
								<font color="green">
							</s:elseif>
							${code[7]}
							</font>
						</td>
						<td></td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
