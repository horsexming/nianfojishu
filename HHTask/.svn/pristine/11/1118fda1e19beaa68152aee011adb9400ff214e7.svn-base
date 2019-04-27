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
							您正在查询打分明细:
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
			</div>
		</div>
		<div id="operatingDiv"
			style="display: none; width: 600px; position: absolute; top: 200px; left: 360px; z-index: 255; background-color: #FFF">
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="showSS">
					<form action="ScoreStatisticsAction!findAllSS.action" method="post">
						<table class="table">
							<tr>
								<th colspan="5">
									查询
								</th>
							</tr>
							<tr>
								<td align="right">
									工号:
								</td>
								<td align="left">
									<input name="scoreStatistics.code" />
								</td>
								<td align="right">
									卡号:
								</td>
								<td align="left">
									<input name="scoreStatistics.cardId" />
								</td>
								<td rowspan="2">
									<input type="submit" value="查询"
										style="width: 80px; height: 80px;">
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名:
								</td>
								<td align="left">
									<input name="scoreStatistics.userName" />
								</td>
								<td align="right">
									考核月份:
								</td>
								<td align="left">
									<input name="scoreStatistics.mouth" class="Wdate" type="text"
										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
								</td>
							</tr>
						</table>
					</form>
					<form action="WageAction!addCbWage.action" method="post">
						<input name="pageStatus" value="manage" type="hidden" />
						<table  class="table">
							<tr>
								<th align="center" colspan="8">
									总分明细
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
								</th>
								<th align="center">
									工号
								</th>
								<th align="center">
									姓名
								</th>
								<th align="center">
									考核月份
								</th>
								<th align="center">
									考核分数
								</th>
								<th align="center">
									操作
								</th>
								<th align="center">
									绩效工资
								</th>
							</tr>
							<s:iterator value="scoreStatisticsList" id="pagess"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td>
									${pagess.code}
									<input type="hidden" name="code" value="${pagess.code}">
									<input type="hidden" name="cardId" value="${pagess.cardId}">
								</td>
								<td>
									${pagess.userName}
								</td>
								<td>
									${pagess.mouth}
								</td>
								<td>
									<s:if test="#pagess.scoreStatus=='打分'">
									0
								</s:if>
									<s:else>
									${pagess.totalScore}
								</s:else>
								</td>
								<td>
									<a
										href="ScoreStatisticsAction!findAllSS.action?scoreStatistics.code=&cpage=${cpage}&total=${total}&id=${pagess.id}">明细</a>
								</td>
								<td align="right" style="padding-right: 50px;">
									<input name="money"
										onkeyup="if(isNaN(value))execCommand('undo')"
										value="${pagess.sdWage}" />
									(${pagess.wsWage}元)
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="6"></td>
								<td align="center">
									<input type="submit" value="确定">
								</td>
								</td>
							</tr>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="11" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="11" align="center" style="color: red">
								</s:else>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="showScore" style="display: none">
					<hr>
					<table  class="table">
						<tr>
							<th align="center" colspan="8">
								考核明细
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								卡号
							</th>
							<th align="center">
								考核月份
							</th>
							<th align="center">
								考核成绩
							</th>
							<th align="center">
								打分人
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator id="pageAssScore" value="list" status="iteratorStatus">
							<s:if test="#iteratorStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#iteratorStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#iteratorStatus.index+1" />
								</font>
							</td>
							<td align="center">
								${pageAssScore.userName}
							</td>
							<td align="center">
								${pageAssScore.cardId}
							</td>
							<td align="center">
								${pageAssScore.asstMouth}
							</td>
							<td align="center">
								${pageAssScore.percentageScore}分
							</td>
							<td align="center">
								${pageAssScore.assPeople}
							</td>
							<td align="center">
								<a
									href="javascript:sendRequest('AssScoreAction!scoreDetail.action?id=${pageAssScore.id}',sdsView)">打分明细</a>
								<s:if test="%{#pageStatus=='dept'}">
									/
								<a onclick="return window.confirm('确定要删除该成绩?')"
										href="AssScoreAction!delScore.action?id=${pageAssScore.id}&status=${pageStatus}">删除</a>
								</s:if>
							</td>
						</s:iterator>
					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">
onload = function() {
	var scoreStatisticsList = "${scoreStatisticsList}";//总分
	var list = "${list}";//分数

	var showSS = document.getElementById("showSS");//总分层
	var showScore = document.getElementById("showScore");//分数层

	if (list != "") {
		showScore.style.display = "block";
	}
}

function sdsView() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var scoreDetailsDIV = document.getElementById("operatingDiv");
			scoreDetailsDIV.innerHTML = message;
			chageDiv('block', "");
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
</script>
	</body>
</html>
