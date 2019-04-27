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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="AccessEquipmentAction_update.action?accessEquipment.id=${accessEquipment.id}"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					修改门禁设备
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="AccessEquipmentAction_update.action?tag=${tag}" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
								设备名称
								<input type="hidden" name="accessEquipment.id"
									value="${accessEquipment.id}" />
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentName"
									id="equipmentName" value="${accessEquipment.equipmentName}" //>
							</td>
							<th align="center">
								设备编号
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentNum"
									id="equipmentNum" value="${accessEquipment.equipmentNum}" / />
							</td>
						</tr>
						<tr>
							<th align="center">
								设备供应商
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentSupplier"
									id="equipmentSupplier"
									value="${accessEquipment.equipmentSupplier}" / />
							</td>
							<th align="center">
								设备用途
							</th>
							<td align="center">
								<select name="accessEquipment.equipmentOutIn"
									id="equipmentOutIn" style="width:155px">
									<option value="${accessEquipment.equipmentOutIn}">
										${accessEquipment.equipmentOutIn}
									</option>
									<option>
									</option>
									<option value="进门">
										进门
									</option>
									<option value="出门">
										出门
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="center">
								设备IP
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentIP"
									id="equipmentIP" value="${accessEquipment.equipmentIP}" / />
							</td>
							<th align="center">
								设备端口
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentPort"
									id="equipmentPort" value="${accessEquipment.equipmentPort}" / />
							</td>
						</tr>
						<tr>
							<th align="center">
								设备摆放位置
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentLocation"
									id="equipmentLocation"
									value="${accessEquipment.equipmentLocation}" / />
							</td>
							<th align="center">
								请选择行道
							</th>
							<th align="center">
								<select name="accessEquipment.equipmentDaoType" id="equipmentDaoType"
									style="width: 156px;"
									onMouseOver="createDept('equipmentDaoType','JLMApplicationAction!findSelectName.action')">
									<option value="${accessEquipment.equipmentDaoType}">
										${accessEquipment.equipmentDaoType}
									</option>
								</select>
							</th>
						</tr>
						<tr>
							<th>
								是否允许刷卡考勤：
							</th>
							<td colspan="1" align="center">
								<select name="accessEquipment.isTrueKao" id="isTrueKao" style="width: 152px;">
									<option value="${accessEquipment.isTrueKao}">${accessEquipment.isTrueKao}</option>
									<option value="是">是</option>
									<option value="否">否</option>
								</select>
							</td>
							<th>
								是否巡更：
							</th>
							<td colspan="1" align="center">
								<select name="accessEquipment.isXungeng" id="isXungeng" style="width: 152px;">
									<option value="${accessEquipment.isXungeng}">${accessEquipment.isXungeng}</option>
									<option value="否">否</option>
									<option value="是">是</option>
								</select>	
							</td>
						</tr>
						<tr>
							<th colspan="4" align="center">
								<input type="submit" value="修改(update)"
									style="width: 100px; height: 50px;" />
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("equipmentName", "设备名称")) {
		return false;
	}
	if (!validateText("equipmentNum", "设备编号")) {
		return false;
	}
	if (!validateText("equipmentIP", "设备IP")) {
		return false;
	}
	if (!validateText("equipmentPort", "设备端口")) {
		return false;
	}
	if (!validateText("equipmentLocation", "摆放位置")) {
		return false;
	}
	if (!validateText("equipmentDaoType", "行道类型")) {
		return false;
	}
	if (!validateText("isTrueKao", "是否允许刷卡考勤")) {
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

</script>
	</body>
</html>
