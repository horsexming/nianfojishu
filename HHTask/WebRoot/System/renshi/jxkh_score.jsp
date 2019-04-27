<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ page import="com.task.entity.*,java.text.SimpleDateFormat"%>
<%@ page import="com.task.Dao.*"%>
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
		<style type="text/css">
.style1 {
	background-color: #ff6600;
	color: #ffffff;
}

.style2 {
	background-color: #e4e4e4;
	color: #000000;
}
</style>

		<script type="text/javascript">
// 预览
function viewResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var ulMessageDIV = document.getElementById("operatingDiv");
			ulMessageDIV.innerHTML = ulMessage;
			chageDiv('block', "");
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
//打分
function scoringResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var ulMessageDIV = document.getElementById("scoringForm");
			ulMessageDIV.innerHTML = ulMessage;
			ulMessageDIV.style.display = "block";
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//流程DIV控制
function chageType() {
	var status = "${status}";
	/*评分操作DIV*/
	var choicePeople = document.getElementById("choicePeople");//选人导航
	var choicePeopleDiv = document.getElementById("choicePeopleDiv");//选人DIV
	var choicTemplate = document.getElementById("choicTemplate");//选模板导航
	var choicTemplateDiv = document.getElementById("choicTemplateDiv");//选模板DIV
	var scoring = document.getElementById("scoring");//打分导航
	var scoringDiv = document.getElementById("scoringDiv");//打分DIV
	var finish = document.getElementById("finish");//完成导航
	var finishDiv = document.getElementById("finishDiv");//完成IDV
	if (status == "score") {//评分
		choicePeople.className = "style1";
		choicePeopleDiv.style.display = "block";
		scoreDiv.style.display = "block";
		return;
	} else if (status == "choicePeople") {//选人
		choicePeople.className = "style1";
		choicePeopleDiv.style.display = "block";
		scoreDiv.style.display = "block";
		return;
	} else if (status == "choicTemplate") {//选择模板
		choicTemplate.className = "style1";
		choicTemplateDiv.style.display = "block";
		scoreDiv.style.display = "block";
		return;
	} else if (status == "scoring") {//打分
		scoring.className = "style1";
		scoringDiv.style.display = "block";
		sendRequest("TemplateAction!PreviewTemplate.action?id=${template.id}",
				scoringResponse);
		scoreDiv.style.display = "block";
		return;
	} else if (status == "finish") {//完成
		document.getElementById("message").style.display = "none";
		finish.className = "style1";
		finishDiv.style.display = "block";
		scoreDiv.style.display = "block";
		return;
	}
}
function chackScore(score, obj) {
	var nowScore = parseFloat(obj.value);
	var customScore_sum = parseFloat($("#customScore_sum").val());
	var score = parseFloat(score);
	if (nowScore > score) {
		alert("所填分数不能大于定制分数!");
		obj.focus();
	} else {
		var score = document.getElementsByName("score");
		var sumScore = 0;
		for ( var i = 0, len = score.length; i < len; i++) {
			if (score[i].value == "") {
				sumScore += parseFloat(0);
			} else {
				sumScore += parseFloat(score[i].value);
			}
		}
		document.getElementById("sumScore").value = sumScore ;
		var bfbscore=parseFloat(sumScore / customScore_sum * 100).toFixed(2);
		$("#showbfbScore").html(
				"本次考核百分比分数预览:" + bfbscore+"分");
	}
}

document.onkeydown = banBackSpace;
</script>
	</head>
	<body bgcolor="#ffffff" onload="chageType()">
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
							您正在预览模板:
							<font color="red">${template.name}</font>
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
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<!-- 导航 -->
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a id="chageTemplate"
							href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
							style="color: #ffffff">评分</a>
					</div>
				</div>
				<!-- 评分DIV -->
				<DIV id="scoreDiv" style="display: none">
					<div align="center" style="width: 100%;">
						<div id="choicePeople" class="style2"
							style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding-top: 8px;">
							<font size="4"> 选人 </font>
						</div>
						<div id="choicTemplate" class="style2"
							style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding-top: 8px;">
							<font size="4"> 选考核表 </font>
						</div>
						<div id="scoring" class="style2"
							style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding-top: 8px;">
							<font size="4"> 打分 </font>
						</div>
						<div id="finish" class="style2"
							style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding-top: 8px;">
							<font size="4"> 完成 </font>
						</div>
					</div>
					<div id="choicePeopleDiv" style="clear: both; display: none"
						align="center">
						<table style="width: 980px; border-collapse: collapse;" border="0"
							align="center">
							<tr>
								<td align="center" colspan="7">
									<h2>
										请选择未打分人员
									</h2>
								</td>
							</tr>
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold;">
								<th align="center">
									序号
								</th>
								<td>
									工号
								</td>
								<td>
									卡号
								</td>
								<td>
									姓名
								</td>
								<td>
									部门
								</td>
								<td>
									操作
								</td>
							</tr>
							<s:iterator id="pageAp" value="assessPersonnelList"
								status="ststusfunction">
								<s:if test="#ststusfunction.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#ststusfunction.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#ststusfunction.index+1" />
									</font>
								</td>
								<td>
									${pageAp.code}
								</td>
								<td>
									${pageAp.cardId}
								</td>
								<td>
									${pageAp.userName}
								</td>
								<td>
									${pageAp.dept}
								</td>
								<td>
									<a
										href="TemplateAction!findTemplateAndUser.action?id=${pageAp.id}&status=yuangong">打分</a>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<th colspan="5" style="color: red">
									${errorMessage}
								</th>
							</tr>
						</table>
					</div>
					<div id="choicTemplateDiv" style="clear: both; display: none"
						align="center">
						<table  class="table">
							<tr>
								<th colspan="7">
									<h2>
										选择模板
									</h2>
									(${ap.userName})
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<td align="center">
									模板名称
								</td>
								<td align="center">
									考核部门
								</td>
								<td align="center">
									考核对象
								</td>
								<td align="center">
									考核月份
								</td>
								<td align="center">
									操作
								</td>
							</tr>
							<s:iterator value="templateList" id="pageTemplate"
								status="ststusfunction">
								<s:if test="#ststusfunction.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									${pageTemplate.name}
								</td>
								<td align="center">
									${pageTemplate.dept}
								</td>
								<td align="center">
									${pageTemplate.assObject}
								</td>
								<td align="center">
									${pageTemplate.asstMouth}
								</td>
								<td align="center">
									<a
										href="javascript:sendRequest('TemplateAction!PreviewTemplate.action?id=${pageTemplate.id}',viewResponse)">预览</a>
									/
									<a
										href="TemplateAction!findTemplateAndUser.action?id=${pageTemplate.id}&usersId=${ap.userId}&status=scoring">打分</a>
								</td>
								</tr>
							</s:iterator>
						</table>
						<font color="red">${errorMessage}</font>
					</div>
					<div id="scoringDiv" style="clear: both; display: none"
						align="center">
						<form action="AssScoreAction!addAssScore.action" method="post">
							将考核:
							<font color="red"> (${pageUser.name})</font>
							<!-- 打分显示DIV -->
							<div id="scoringForm" style="overflow: hidden; overflow-y: auto;"></div>
							<br>
							<input type="hidden" name="id" value="${template.id}">
							<input type="hidden" id="sumScore" name="assScore.accScore"
								value="0" />
							<input type="hidden" name="assScore.cardId"
								value="${pageUser.cardId}" />
							<input type="hidden" name="assScore.code"
								value="${pageUser.code}" />
							<input type="hidden" name="assScore.userName"
								value="${pageUser.name}">
							<input type="hidden" name="assScore.accMouth"
								value="${template.asstMouth}" />
							<input type="hidden" name="assScore.assType"
								value="${template.assObject}" />
							<input type="hidden" name="assScore.dept"
								value="${template.dept}" />
							<input type="hidden" name="assScore.remarks"
								value="${template.remarks}" />
							<input type="hidden" name="assScore.assPeople"
								value="${Users.name}" />
							<input type="hidden" name="assScore.userId"
								value="${pageUser.id}" />
							<input type="hidden" name="assScore.addUserId"
								value="${Users.id}" />

							<div id="showbfbScore" style="color: red;"></div>
							<input style="width: 80px; height: 50px" type="submit" value="打分" />
							<input style="width: 80px; height: 50px" type="reset" value="重置" />

						</form>
					</div>
					<div id="finishDiv" style="clear: both; display: none"
						align="center">
						${successMessage}
					</div>

				</DIV>

				<!-- 消息提醒 -->
				<div id="message" align="center">
					<font color="red">${successMessage}</font>
					<%
						session.removeAttribute("successMessage");
						session.removeAttribute("errorMessage");
					%>
				</div>
			</div>
			<BR>
			<!-- 预览模板DIV -->
			<div id="ulMessageDIV"
				style="border: solid #0170b8 1px; width: 100%; display: none">
			</div>


			<%@include file="/util/foot.jsp"%>
			<%
				request.getSession().removeAttribute("successMessage");
			%>
		</center>
	</body>
</html>
