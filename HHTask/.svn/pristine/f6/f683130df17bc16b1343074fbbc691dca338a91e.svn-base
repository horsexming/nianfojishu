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
					<a href="saleBudgetAction!prepareSave.action"
						style="color: #ffffff">采购申请</a> &nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">

				<form
					action="oaAppDetailAction!findSelectDetail.action"
					method="post">

					<input type="hidden" name="id" value="${id}" id="preId" />
					<input type="hidden" name="tag" value="${tag}" id="tag" />
					<table class="table">
						<tr>
							<td align="right" colspan="13">
								<font color="red">共选择 <label id="peopleLabel">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
								<input type="submit" value="确认"
									style="width: 60px; height: 40px;" align="top">
								<br>
								<br>
							</td>
						</tr>
						<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
							<th align="center">
								序号
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								物品编码
							</th>
							<th align="center">
								计划月份
							</th>
							<th align="center">
								类别
							</th>
							<th align="center">
								编号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								单位
							</th>
							<th align="center">
								申报数量
							</th>
							<th align="center">
								预算单价
							</th>
							<th align="center">
								到货期限
							</th>
							<th align="center" style="width: 40px;">
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</th>
						</tr>

						<s:iterator value="list" status="se" id="detail">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${detail.detailAppDept}
							</td>
							<td>
								${detail.wlcode}
							</td>
							<td>
								${detail.detailPlanMon}
							</td>
							<td>
								${detail.detailChildClass}
							</td>
							<td>
								${detail.detailSeqNum}
							</td>
							<td>
								${detail.detailAppName}
							</td>
							<td>
								${detail.detailFormat}
							</td>
							<td>
								${detail.detailUnit}
							</td>
							<td>
								${detail.detailCount}
							</td>
							<td>
								${detail.detailBudgetMoney}
							</td>
							<td>
								${detail.detailArrDate}
							</td>


							<td>
								<input type="checkbox" id="price<s:property value="#se.index"/>"
									name="detailSelect" value="${detail.id}"
									onclick="chageNum(this)">

							</td>

							</tr>
						</s:iterator>
						<tr>
							<td colspan="13" align="right"
								style="font-weight: bold; padding-right: 40px">
								<input type="checkbox" id="checkAll2"
									onclick="chageAllCheck(this)">
								全选
							</td>
						</tr>
						<tr>
							<td align="right" colspan="13">
								<font color="red">共选择 <label id="peopleLabel2">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
								<input type="submit" value="确认"
									style="width: 60px; height: 40px;" align="top">
								<br>
								<br>
							</td>
						</tr>

					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">

function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox);
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
}
</script>
</html>
