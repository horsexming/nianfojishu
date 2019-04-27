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
		<script type="text/javascript">
$(function() {
	//检查显示消息层
	var message = "${pageStauts}";
	if (message == "success") {
		$("#receiveForm").hide("slow");
	}

	//显示领取信息，并隐藏领取div
	var cbr = "${cbr}";
	if (cbr) {
		$("#showCbr").show("slow");
		$("#receiveForm").hide();
	}
});

//为form表单绑定提交审核事件
$(function() {
	$("#receiveForm").bind("submit", function() {
		var receivePeople = $("#receivePeople").attr("value");
		if (receivePeople == "") {
			alert("请填写领取人!");
			$("#receivePeople").focus();
			return false;
		}
		return true;
	});
});
</script>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
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
				<form id="receiveForm"
					action="ContractBonusAction!contractBonusReceive.action"
					method="post">
					<input type="hidden" name="id" value="${contractBonus.id}">
					<input type="hidden" name="cbr.cbId" value="${contractBonus.id}">
					<table class="table" style="width: 60%;">
						<tr>
							<th colspan="2">
								领取${contractBonus.deptName}部门的${contractBonus.bonusMouth}部留金额
							</th>
						</tr>
						<tr>
							<th align="right">
								领取人:
							</th>
							<td>
								<input id="receivePeople" name="cbr.receivePeople" />
							</td>
						</tr>
						<tr>
							<th align="right">
								领取金额:
							</th>
							<td>
								<input name="cbr.receiveMoney"
									value="${contractBonus.totalMoney}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								领取月份:
							</th>
							<td>
								<input name="cbr.receiveMonth"
									value="${contractBonus.bonusMouth}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								领取部门:
							</th>
							<td>
								<input name="cbr.receiveDept" value="${contractBonus.deptName}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="领取"
									style="height: 50px; width: 80px;" />
								<input type="reset" value="重置"
									style="height: 50px; width: 80px;" />
							</td>
						</tr>
					</table>
				</form>
				<div id="showMessage">
					${successMessage}
				</div>
				<table id="showCbr" class="table" style="width: 60%; display: none;">
					<tr>
						<th colspan="2">
							${cbr.receiveDept}部门${cbr.receiveMonth}的部留金额领取信息
						</th>
					</tr>
					<tr>
						<th align="right">
							领取人:
						</th>
						<td>
							<input id="receivePeople" value="${cbr.receivePeople}"
								readonly="readonly" class="horizontalLine" />
						</td>
					</tr>
					<tr>
						<th align="right">
							领取金额:
						</th>
						<td>
							<input value="${cbr.receiveMoney}" readonly="readonly"
								class="horizontalLine" />
						</td>
					</tr>
					<tr>
						<th align="right">
							领取月份:
						</th>
						<td>
							<input value="${cbr.receiveMonth}" readonly="readonly"
								class="horizontalLine" />
						</td>
					</tr>
					<tr>
						<th align="right">
							领取部门:
						</th>
						<td>
							<input value="${cbr.receiveDept}" readonly="readonly"
								class="horizontalLine" />
						</td>
					</tr>
					<tr>
						<th align="right">
							领取时间:
						</th>
						<td>
							<input value="${cbr.receiveTime}" readonly="readonly"
								class="horizontalLine" />
						</td>
					</tr>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>

	</body>
</html>
