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
// 查看工资明细
function wageDetails() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var ulMessageDIV = document.getElementById("operatingDiv");
			ulMessageDIV.innerHTML = ulMessage;
			chageDiv("block", "");
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

function chageTitle(userName, id) {
	var titleDiv = document.getElementById("title");
	titleDiv.innerHTML = "您正在查看<font color='red'> "
			+ userName
			+ "</font> 的工资明细  <a href='WageAction!updateWageStatus.action?pageStatus=agree&id="
			+ id
			+ "'>同意</a>"
			+ "/<a href='WageAction!updateWageStatus.action?pageStatus=disagree&id="
			+ id + "'>打回</a>";

	sendRequest("WageAction!showWageDetails.action?id=" + id, wageDetails)

}

//全选
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll"&&checkBox.id != "checkAll2") {
					chageNum(checkBox, checkBox.id);
				}
			}
		}
	}
}

//单选
var num = 0;
function chageNum(obj, yingfagongzi) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	if (check.checked == true) {
		var status = true;
		var inputs = document.getElementsByTagName("input");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll"&&checkBox.id != "checkAll2") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
		}
		num += parseFloat(yingfagongzi);
	} else {
		checkAll.checked = false;
		num = num - parseFloat(yingfagongzi);
	}

	document.getElementById("yingfaMoney").innerHTML = Math.round(num * 100) / 100;
}

//改变formAction
function chageFormAction(pageStatus, form) {
	form.action = "WageAction!batchAudit.action?pageStatus=" + pageStatus;
	form.submit();
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<div id="bodyDiv" align="center">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
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
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">工资审核</a>
				</div>
			</div>

			<div align="center">
				<div align="center" id="showAllChageWage">
					<form action="WageAction!findWageByCondition.action" method="post"
						style="margin: 0px;">
						<input type="hidden" name="pageStatus" value="${pageStatus}">
						<table class="table">
							<tr>
								<th colspan="6">
									工资查询
								</th>
							</tr>
							<tr>
								<td align="right">
									姓名:
								</td>
								<td align="left">
									<input name="wage.userName" />
								</td>
								<td align="right">
									工号:
								</td>
								<td align="left">
									<input name="wage.code" />
								</td>
							</tr>
							<tr>
								<td align="right">
									部门:
								</td>
								<td align="left">
									<select name="wage.dept" id="dept" style="width: 155px">
										<option></option>

									</select>
								</td>
								<td align="right">
									月份:
								</td>
								<td align="left">
									<input class="Wdate" type="text" name="wage.mouth"
										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input value="查询" type="submit"
										style="width: 100px; height: 50px">
									<input value="重置" type="reset"
										style="width: 100px; height: 50px">
								</td>
							</tr>
						</table>
					</form>

					<form action="" method="post" style="padding: 0px; margin: 0px">
						<table class="table">
							<tr>
								<th colspan="18">
									待审核员工工资信息
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<th>
									全选
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
								</th>
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
									岗位工资
								</th>
								<th align="center">
									保密津贴
								</th>
								<th align="center">
									特殊补贴
								</th>
								<th align="center">
									考核工资
								</th>
								<th align="center">
									奖金
								</th>
								<th>
									应发工资
								</th>
								<th>
									补发补扣
								</th>
								<th>
									实发工资
								</th>
								<th align="center">
									工资状态
								</th>
								<th align="center">
									工资类别
								</th>
								<th align="center">
									操作
								</th>
							</tr>
							<s:iterator value="wageList" id="pageWageList"
								status="pageListStatus">
								<s:if test='wageClass=="离职工资"'>
									<tr align="center" bgcolor="#FF5151"
										onmouseover="chageBgcolor(this,'red')"
										onmouseout="outBgcolor(this,'#FF5151')">
								</s:if>
								<s:else>
									<s:if test="#pageListStatus.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
								</s:else>
								<td align="">
									<input type="checkbox" id="${pageWageList.shifagongzi}"
										name="wageId" value="${pageWageList.id}"
										onclick="chageNum(this,'${pageWageList.shifagongzi}')">
								</td>
								<td>
									<s:property value="#pageListStatus.index+1" />
								</td>
								<td align="left">
									<a
										href="WageAction!findWageByCondition.action?wage.code=${pageWageList.code}">
										${pageWageList.userName} </a>
								</td>
								<td align="left">
									${pageWageList.dept}
								</td>
								<td align="left">
									${pageWageList.mouth}
								</td>
								<td>
									<fmt:formatNumber type="number"
										value="${pageWageList.gangweigongzi}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									<fmt:formatNumber type="number"
										value="${pageWageList.baomijintie}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									<fmt:formatNumber type="number"
										value="${pageWageList.dianhuabutie}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									<s:if test="#pageWageList.scoreId!=null">
										<a
											href="AssScoreAction!findAssScoreByCondition.action?assScore.id=${pageWageList.scoreId}">
											<fmt:formatNumber type="number"
												value="${pageWageList.jixiaokaohegongzi}" pattern="0.00"
												maxFractionDigits="2" /> </a>
									</s:if>
									<s:else>
										<fmt:formatNumber type="number"
											value="${pageWageList.jixiaokaohegongzi}" pattern="0.00"
											maxFractionDigits="2" />
									</s:else>
								</td>
								<td>
									<s:if test="#pageWageList.jiangjinId!=null">
										<a
											href="ProcardAction!findUMMoneyByCondition.action?umm.id=${pageWageList.jiangjinId}">

											<fmt:formatNumber type="number"
												value="${pageWageList.jiangjin}" pattern="0.00"
												maxFractionDigits="2" /> </a>
									</s:if>
									<s:else>

										<fmt:formatNumber type="number"
											value="${pageWageList.jiangjin}" pattern="0.00"
											maxFractionDigits="2" />
									</s:else>
								</td>
								<td>
									<fmt:formatNumber type="number"
										value="${pageWageList.yingfagongzi}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									<fmt:formatNumber type="number"
										value="${pageWageList.bfgongzi}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									<fmt:formatNumber type="number"
										value="${pageWageList.shifagongzi}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									${pageWageList.wageStatus}
								</td>
								<td>
									${pageWageList.wageClass}
								</td>
								<td>
									<s:if test='wageClass=="离职工资"'>
										<a href=""> 离职协议单</a>
									</s:if>
									<%--<a onclick="return window.confirm('该工资将被审核通过,确定提交?')"
										href="WageAction!batchAudit.action?pageStatus=ok&wageId=${pageWageList.id}">同意</a>/
									<a onclick="return window.confirm('该工资将被打回,确定提交?')"
										href="WageAction!batchAudit.action?pageStatus=back&wageId=${pageWageList.id}">打回</a>/
									--%><a
										href="javascript:chageTitle('${pageWageList.userName}','${pageWageList.id}');">明细</a>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="2">
										全选
										<input type="checkbox" id="checkAll2"
											onclick="chageAllCheck(this)">
									</td>
									<td colspan="3">
										<input type="button" value="批量同意" id="ok"
											onclick="chageFormAction('ok',this.form)">
										<input type="button" value="批量打回" id="back"
											onclick="chageFormAction('back',this.form)">
									</td>
									<td colspan="5" align="right">
										实发工资
										<font color="red"> <span id="yingfaMoney">0</span> </font>元
									</td>
									<td colspan="10" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="18" align="center" style="color: red">
										${errorMessage}
								</s:else>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div>
					${successMessage}
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
