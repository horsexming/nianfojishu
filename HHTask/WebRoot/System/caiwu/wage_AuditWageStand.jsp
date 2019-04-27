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
//全选
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll") {
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
				if (checkBox.id != "checkAll") {
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
	form.action = "WageStandardAction!auditWageStand.action?pageStatus="
			+ pageStatus;
	form.submit();
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity =                                                                                                                                                                 75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">工资审核</a>
				</div>
			</div>
			
			<div align="center">
				<div align="center" id="showAllChageWage">
					
					<form action="" method="post" style="padding: 0px; margin: 0px">
						<table class="table2">
							<tr>
								<th colspan="15">
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
									岗位工资
								</th>
								<th align="center">
									保密津贴
								</th>
								<th align="center">
									补贴
								</th>
								<th align="right">
									技能工资
								</th>
								<th align="right">
									工龄工资
								</th>
								<th align="center">
									绩效考核工资
								</th>
								<th align="center">
									养老保险
								</th>
								<th align="center">
									医疗保险
								</th>
								<th align="center">
									失业保险
								</th>
								<th align="center">
									公积金
								</th>
								<th align="center">
									操作
								</th>
							</tr>
							<s:iterator value="WsList" id="pageWsList" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="">
									<input type="checkbox" id="${pageWsList.yingfagongzi}"
										name="wsId" value="${pageWsList.id}"
										onclick="chageNum(this,'${pageWsList.yingfagongzi}')">
								</td>
								<td>
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td>
									${pageWsList.userName}
								</td>
								<td>
									${pageWsList.dept}
								</td>
								<td>
									${pageWsList.gangweigongzi}
								</td>
								<td>
									${pageWsList.baomijintie}
								</td>
								<td>
									${pageWsList.dianhuabutie}
								</td>
								<td>
									${pageWsList.jinenggongzi}
								</td>
								<td>
									${pageWsList.gonglinggongzi}
								</td>
								<td>
									${pageWsList.jixiaokaohegongzi}
								</td>
								<td>
									${pageWsList.tongchoujin}
								</td>
								<td>
									${pageWsList.yiliaobaoxian}
								</td>
								<td>
									${pageWsList.shiyebaoxian}
								</td>
								<td>
									${pageWsList.gongjijin}
								</td>
								<td>
									<a
										href="WageStandardAction!auditWageStand.action?pageStatus=ok&&wsId=${pageWsList.id}"
										onclick="javascript:">同意</a>/
									<a
										href="WageStandardAction!auditWageStand.action?pageStatus=back&wsId=${pageWsList.id}">打回</a>/
									<a target="_blank"
										href="WageStandardAction!findWsById.action?id=${pageWsList.id}&pageStatus=details">详细</a>/
									<a target="_blank"
										href="ContractAction!findContractByCode.action?code=${pageWsList.code}&isUse=2">调整协议</a>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="5">

										<input type="button" value="批量同意" id="ok"
											onclick="chageFormAction('ok',this.form)">
										<input type="button" value="批量打回" id="back"
											onclick="chageFormAction('back',this.form)">
									</td>
									<td colspan="5" align="right">
										应发工资
										<font color="red"> <span id="yingfaMoney">0</span> </font>元
									</td>
									<td colspan="6" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="11" align="center" style="color: red">
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
