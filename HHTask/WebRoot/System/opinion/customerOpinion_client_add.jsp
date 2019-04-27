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
		<div id="gongneng">
			<div align="center">
				<form action="customerOpinionAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th colspan="6">
								产品投诉
							</th>
						</tr>
						<tr>
							<th>
								己方负责人
							</th>
							<td>
								<input name="customerOpinion.otherPerson" id="otherPerson"
									value="${session.Users.name}" style="width: 240px;" />
							</td>
							<th>
								己方联系电话
							</th>
							<td>
								<input name="customerOpinion.otherPhone" id="otherPhone"
									value="${session.Users.password.phoneNumber}" />
							</td>
							<th>
								己方单位
							</th>
							<td>
								<input name="customerOpinion.otherCompany" id="otherCompany"
									value="${session.Users.more}" style="width: 240px;" />
							</td>
						</tr>
						<tr>
							<th>
								投诉标题
							</th>
							<td>
								<input name="customerOpinion.title" id="title" />
							</td>
							<th>
								对方负责人
							</th>
							<td>
								<input name="customerOpinion.ourPerson" id="ourPerson" />
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<th>
								订单编号:
							</th>
							<td colspan="10">
								<select style="width: 155px;" onchange="showOrderProduct(this)"
									name="customerOpinion.orderId">
									<option>
										请选择订单编号
									</option>
									<s:iterator value="list" id="orderManager">
										<option value="${orderManager.id}">
											${orderManager.outOrderNumber}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								产品件号
							</th>
							<td colspan="3">
								<select style="width: 450px;" id="showProcard"
									name="customerOpinion.procardId"
									onchange="showProcessinfor(this)">
								</select>
							</td>
							<th>
								工序:
							</th>
							<td>
								<select style="width: 155px" id="showProcess"
									name="customerOpinion.processInforId">
								</select>
							</td>
						</tr>
						<tr>
							<th>
								投诉内容
							</th>
							<td colspan="5">
								<textarea rows="4" cols="100" name="customerOpinion.context"
									id="context"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="8">
								<input type="submit" value="提交" class="input">
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
		<script type="text/javascript">
function validate() {
	if (!validateMsg("已方负责人", "ourPerson")) {
		return false;
	}
	if (!validateMsg("对方负责人", "otherPerson")) {
		return false;
	}
	if (!validateMsg("对方联系电话", "otherPhone")) {
		return false;
	}
	var filter = /[0-9][-]{0,}[0-9]+$/;
	if (!filter.test($("#otherPhone").val())) {
		alert("号码格式不正确");
		return false;
	}
	if (!validateMsg("对方单位", "otherCompany")) {
		return false;
	}
	if (!validateMsg("投诉标题", "title")) {
		return false;
	}
	if (!validateMsg("投诉内容", "context")) {
		return false;
	}
	return true;
}function validateMsg(msg, id) {
	if ($("#" + id).val() == null || $("#" + id).val() == "") {
		alert(msg + "不能为空!");
		return false;
	}
	return true;
}
function showOrderProduct(order) {
	var orderId = order.value;
	$.ajax( {
		type : "POST",
		url : "orderManager_showProcardDetils.action",
		data : {
			'id' : orderId
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				$("#showProcard").html("");
				var prohemt = "<option>请选择产品批次</option>";
				$.each(data.data, function(i, n) {
					prohemt += "<option value='" + n.id + "'>件号:" + n.markId
							+ "——批次:" + n.selfCard + "——数量:" + n.filnalCount
							+ n.unit + "</option>";
				});
				$("#showProcard").html(prohemt);
			} else {
				alert(data.message);
			}
		}
	});
}
function showProcessinfor(procard) {
	var procardId = procard.value;
	$.ajax( {
		type : "POST",
		url : "ProcardAction!findProcessByProcard.action",
		data : {
			'id' : procardId
		},
		dataType : "json",
		success : function(data) {
			$("#showProcess").html("");
			var prohemt = "";
			$.each(data, function(i, n) {
				prohemt += "<option value='" + n.id + "'>" + n.processName
						+ "</option>";
			});
			$("#showProcess").html(prohemt);
		}
	});
}
</script>
	</body>
</html>
