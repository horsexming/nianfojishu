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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					修改车位信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="ParkSpaceAction_update.action" method="post" onsubmit="return validate()">
					<table class="table" align="center">
						<tr style="width: 100%">
							<th align="right" style="width: 300px;">
								停车场名称
								<input type="hidden" name="tag" value="${tag}"/>
								<input type="hidden" name="parkSpace.id" value="${parkSpace.id}"/>
								<input type="hidden" name="parkSpace.inforId" value="${parkSpace.inforId}"/>
							</th>
							<td align="left" colspan="3">
								<input type="text" name="parkSpace.parkingLot" id="parkingLot" value="${parkSpace.parkingLot}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								车位编号
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkNum" id="parkNum" value="${parkSpace.parkNum}"/>
							</td>
							<th align="right">
								车位类型
							</th>
							<td align="left">
							<select name="parkSpace.parkType" id="parkType" style="width: 156px;" onclick="cevip()"/>
							<option value="${parkSpace.parkType}">${parkSpace.parkType}</option>
							<option value="来访">来访</option>
							<option value="员工">员工</option>
							<option value="VIP">VIP</option>
							</select>
							</td>
						</tr>
						<tr id="vipwei" style="display: none;">
							<th align="right">
								VIP工号
							</th>
							<td align="left">
								<input type="text" name="parkSpace.vipCode" id="vipCode" value="${parkSpace.vipCode}"/>
							</td>
							<th align="right">
								VIP姓名
							</th>
							<td align="left">
								<input type="text" name="parkSpace.vipName" id="vipName" value="${parkSpace.vipName}"/>
							</td>
						</tr>
						<tr id="laifang" style="display: none;">
							<th align="right">
								来访人姓名
							</th>
							<td align="left">
								<input type="text" name="parkSpace.visitName" id="visitName" value="${parkSpace.visitName}"
									disabled="disabled" />
							</td>
							<th align="right">
								来访人电话
							</th>
							<td align="left">
								<input type="text" name="parkSpace.userTelNum" id="userTelNum" value="${parkSpace.userTelNum}"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th align="right">
								对应设备IP
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkIP"
									id="parkIP" value="${parkSpace.parkIP}" />
							</td>
							<th align="right">
								对应设备端口
							</th>
							<td align="left">
							<input type="text" name="parkSpace.parkPort"
									id="parkPort" value="${parkSpace.parkPort}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								打开指令
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkOpen"
									id="parkOpen" value="${parkSpace.parkOpen}" />
							</td>
							<th align="right">
								关闭指令
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkClose"
									id="parkClose" value="${parkSpace.parkClose}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								蓝牙名称
							</th>
							<td align="left">
								<input type="text" name="parkSpace.blueName" id="blueName" value="${parkSpace.blueName}" />
							</td>
							<th align="right">
								蓝牙地址
							</th>
							<td align="left">
								<input type="text" name="parkSpace.blueAddress" id="blueAddress" value="${parkSpace.blueAddress}" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(update))"
									style="width: 100px; height: 50px;" />
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
	if (!validateText("parkingLot", "停车场名称")) {
		return false;
	}
	if (!validateText("parkNum", "车位编号")) {
		return false;
	}
	if (!validateText("parkType", "车位类型")) {
		return false;
	}
	if ($("#parkType").val() == "VIP") {
		if (!validateText("vipCode", "VIP工号")) {
			return false;
		}
		if (!validateText("vipName", "VIP姓名")) {
			return false;
		}
	}else if($("#parkType").val() == "来访"){
		if (!validateText("visitName", "来访人名称")) {
			return false;
		}
		if (!validateText("userTelNum", "来访人手机")) {
			return false;
		}
	}
	if (!validateText("parkIP", "对应设备IP")) {
		return false;
	}
	if (!validateText("parkPort", "对应设备端口")) {
		return false;
	}
	if (!validateText("parkOpen", "开启指令")) {
		return false;
	}
	if (!validateText("parkClose", "关闭指令")) {
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

function cevip(obj){
	if($("#parkType").val()=='VIP'){
		$("#vipwei").show();
		$("#vipName").removeAttr("disabled");
		$("#vipCode").removeAttr("disabled");
		$("#laifang").hide();
		$("#visitName").attr("disabled", "disabled");
		$("#userTelNum").attr("disabled", "disabled");
	}else if($("#parkType").val()=='来访'){
		$("#laifang").show();
		$("#visitName").removeAttr("disabled");
		$("#userTelNum").removeAttr("disabled");
		$("#vipwei").hide();
		$("#vipName").attr("disabled", "disabled");
		$("#vipCode").attr("disabled", "disabled");
	}else{
		$("#vipwei").hide();
		$("#vipName").attr("disabled", "disabled");
		$("#vipCode").attr("disabled", "disabled");
		$("#laifang").hide();
		$("#visitName").attr("disabled", "disabled");
		$("#userTelNum").attr("disabled", "disabled");
	};
}

$(function(){
	cevip();
});
</script>
	</body>
</html>
