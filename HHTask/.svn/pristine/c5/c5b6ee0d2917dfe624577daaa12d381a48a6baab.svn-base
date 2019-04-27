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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div>
					给
					<font color="red"> ${accessEquipment.equipmentName} </font>绑定灯
				</div>
				<div>
					<form action="AccessEquipmentAction_findOneLightCondition.action"
						method="post" style="margin: 0px">
						<br>
						<table class="table">
							<tr>
								<td align="right">
									灯号:
								</td>
								<td>
									<input type="text" name="light.lightNum" />
								</td>
								<td>
									<input type="hidden" name="accessEquipment.id" value="${accessEquipment.id}">
									<input type="submit" value="查询"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
						</table>
					</form>
					<hr>
					<form action="AccessEquipmentAction_bindingOneLight.action"
						method="post" style="margin: 0px">
						<input type="hidden" name="accessEquipment.id" value="${accessEquipment.id}">
						<table class="table" align="center">
							<tr>
								<td align="right" colspan="7">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 工位</font>
									<input type="submit" value="绑定"
										style="width: 60px; height: 40px;" align="top">
									<br>
									<br>
								</td>
							</tr>
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold;">
								<td>
									序号
								</td>
								<td>
									灯号
								</td>
								<td>
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>

							<s:iterator id="lightss" value="banglightList"
								status="ststusfunction">
								<s:if test="#ststusfunction.first">
									<tr bgcolor="green">
										<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
											align="center">
											<font color="#ffffff"> 已绑定灯</font>
										</td>
									</tr>
								</s:if>
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
									${lightss.lightNum}
								</td>
								<td>
									<input type="checkbox"
										id="light<s:property value="#ststusfunction.index"/>"
										name="lightId" value="${lightss.id}" onclick="chageNum(this)"
										checked="checked">
								</td>
								</tr>
								<s:if test="#ststusfunction.last">
									<tr bgcolor="green">
										<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
											align="center">
											<font color="#ffffff"> 未绑定灯</font>
										</td>
									</tr>
								</s:if>
							</s:iterator>

							<s:iterator id="lightss" value="lightList"
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
									${lightss.lightNum}
								</td>
								<td>
									<input type="checkbox"
										id="light<s:property value="#ststusfunction.index"/>"
										name="lightId" value="${lightss.id}" onclick="chageNum(this)">
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="7" align="right"
									style="font-weight: bold; padding-right: 40px">
									<input type="checkbox" id="checkAll2"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="3" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="3" align="center" style="color: red">
										${errorMessage}
									</td>
								</s:else>
							</tr>
							<tr>
								<td align="right" colspan="7">
									<br>
									<font color="red">共选择 <label id="peopleLabel2">
											${count}
										</label>灯</font>
									<input type="submit" value="绑定"
										style="width: 60px; height: 40px;" align="top">
									<br>
									<br>
									<br>
									<br>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function chageBgcolor(obj) {
	obj.style.background = "#c0dcf2";
}
function outBgcolor(obj, oldColor) {
	obj.style.background = oldColor;
}
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox, checkBox.id);
				}
			}
		}
	}
}
var num = "${count}";
if (num == "") {
	num = 0;
}
function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
	document.getElementById("propleText").value = num;
}
</script>
	</body>
</html>
