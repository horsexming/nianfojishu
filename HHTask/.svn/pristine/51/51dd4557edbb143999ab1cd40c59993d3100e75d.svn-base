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
	<body onload="gys()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					修改扣款单
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="zhaobiaoAction!update.action" method="post"
					onsubmit="return validate()" enctype="multipart/form-data">
					<table class="table">
						<tr style="width: 100%">
							<th>
								扣款单编号：
							</th>
							<th align="center">
								<input id="number" style="height: 100%;" type="text" name="chargebackNotice.number" value="${chargebackNotice.number}"/>
								<input type="hidden" name="chargebackNotice.id" value="${chargebackNotice.id}"/>
							</th>
							<th>
								供应商：
							</th>
							<td align="center">
								<SELECT style="width: 162px;" id="zhUser">
									<option value="${chargebackNotice.zhUser_name}">${chargebackNotice.zhUser_name}</option>
								</SELECT>
								<input id="zhUser_name" type="hidden" name="chargebackNotice.zhUser_name" value="${chargebackNotice.zhUser_name}"/>
								<input id="zhUser_id" type="hidden" name="chargebackNotice.zhUser.id" value="${chargebackNotice.zhUser.id}"/>
							</td>
						</tr>
						<tr>
							<th>
								扣款事由：
							</th>
							<td align="center">
								<input id="kkCause" type="text" name="chargebackNotice.kkCause" value="${chargebackNotice.kkCause}"/>
							</td>
							<th>
								扣款金额：
							</th>
							<td align="center">
								<input id="kkMoney" type="text" name="chargebackNotice.kkMoney" value="${chargebackNotice.kkMoney}"/>
							</td>
						</tr>
						<tr>
							<th>
								提报单位：
							</th>
							<td align="center">
								<input id="reportUnit" type="text" name="chargebackNotice.reportUnit" value="${chargebackNotice.reportUnit}"/>
							</td>
							<th>
								扣款月份：
							</th>
							<td align="center">
								<input id="kkMouth" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
								type="text" name="chargebackNotice.kkMouth" value="${chargebackNotice.kkMouth}"/>
							</td>
						</tr>
						<tr>
							<th>
								扣款详情(名称、<br/>
								数量、规格等)：
							</th>
							<td align="left" colspan="3">
								<input id="description" style="width: 87%;height: 100%;" type="text" name="chargebackNotice.description" value="${chargebackNotice.description}"/>
							</td>
						</tr>
						<tr>
							<th>
								上传附件
							</th>
							<td>
								<input type="file" name="attachment" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(Update)"
									style="width: 80px; height: 30px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("zhUser", "供应商")) {
		return false;
	}
	if (!validateText("kkCause", "扣款事由")) {
		return false;
	}
	if (!validateText("kkMoney", "扣款金额")) {
		return false;
	}
	if (!validateText("reportUnit", "提报单位")) {
		return false;
	}
	if (!validateText("kkMouth", "扣款月份")) {
		return false;
	}
	if (!validateText("description", "扣款详情")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function gys(){
	$.ajax( {
		type : "post",
		url : "PriceAction!findZhuerList.action",
		dataType : "json",
		success : function(data) {
			//填充供应商信息
			var i=0;
			$(data).each(
				function() {
					$(
					"<option value='" + data[i][0] + "|" + data[i][1] + "'>" + data[i][1]
							+ "</option>").appendTo("#zhUser");
					i++;
				});
			$("#zhUser").tinyselect();
			$("#zhUser").bind("change", function() {
				var name = $("#zhUser").val();
				if ("${chargebackNotice.zhUser_name}" == name) {
					$("#zhUser_id").val('${chargebackNotice.zhUser.id}');
					$("#zhUser_name").val('${chargebackNotice.zhUser_name}');
				} else {
					var usersData = name.split("|");
					var gysid = usersData[0];
					var gysname = usersData[1];
					$("#zhUser_id").val(gysid);
					$("#zhUser_name").val(gysname);
				}
			});
		}
	});
}
function gongyingsh() {
	var name = $("#zhUser").val();
	alert(name);
	if ("${chargebackNotice.zhUser_name}" == name) {
		$("#zhUser_id").val('${chargebackNotice.zhUser.id}');
		$("#zhUser_name").val(chargebackNotice.zhUser_name);
	} else {
		var usersData = name.split("|");
		var gysid = usersData[0];
		var gysname = usersData[1];
		$("#zhUser_id").val(gysid);
		$("#zhUser_name").val(gysname);
	}
}
</script>
	</body>
</html>
