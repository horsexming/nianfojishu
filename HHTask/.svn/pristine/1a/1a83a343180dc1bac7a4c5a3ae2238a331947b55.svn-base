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
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="ParkSpaceAction_toadd.action"
						style="color: rgb(79, 77, 77)";>刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加车位信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="ParkSpaceAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table" align="center">
						<tr style="width: 100%">
							<th align="right" style="width: 300px;">
								停车场名称
								<input type="hidden" name="tag" value="guan" />
							</th>
							<td align="left" colspan="3">
								<input type="text" name="parkSpace.parkingLot" id="parkingLot" />
							</td>
						</tr>
						<tr>
							<th align="right">
								车位编号
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkNum" id="parkNum" />
							</td>
							<th align="right">
								车位类型
							</th>
							<td align="left">
								<select name="parkSpace.parkType" id="parkType"
									style="width: 156px;" onclick="cevip()" />
									<option>
										请选择车位类型
									</option>
									<option value="员工">
										员工
									</option>
									<option value="来访">
										来访
									</option>
									<option value="VIP">
										VIP
									</option>
								</select>
							</td>
						</tr>
						<tr id="vipwei" style="display: none;">
							<th align="right">
								VIP工号
							</th>
							<td align="left">
								<input type="text" name="parkSpace.vipCode" id="vipCode"
									disabled="disabled" />
							</td>
							<th align="right">
								VIP姓名
							</th>
							<td align="left">
								<input type="text" name="parkSpace.vipName" id="vipName"
									disabled="disabled" />
							</td>
						</tr>
						<tr id="laifang" style="display: none;">
							<th align="right">
								来访人姓名
							</th>
							<td align="left">
								<input type="text" name="parkSpace.visitName" id="visitName"
									disabled="disabled" />
							</td>
							<th align="right">
								来访人电话
							</th>
							<td align="left">
								<input type="text" name="parkSpace.userTelNum" id="userTelNum"
									disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th align="right">
								对应设备IP
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkIP" id="parkIP" />
							</td>
							<th align="right">
								对应设备端口
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkPort" id="parkPort"
									maxlength="5" />
							</td>
						</tr>
						<tr>
							<th align="right">
								打开指令
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkOpen" id="parkOpen" />
							</td>
							<th align="right">
								关闭指令
							</th>
							<td align="left">
								<input type="text" name="parkSpace.parkClose" id="parkClose" />
							</td>
						</tr>
						<tr>
							<th align="right">
								蓝牙名称
							</th>
							<td align="left">
								<input type="text" name="parkSpace.blueName" id="blueName" />
							</td>
							<th align="right">
								蓝牙地址
							</th>
							<td align="left">
								<input type="text" name="parkSpace.blueAddress" id="blueAddress" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
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
function cevip(){
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
$("#parkPort").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
	</body>
</html>
