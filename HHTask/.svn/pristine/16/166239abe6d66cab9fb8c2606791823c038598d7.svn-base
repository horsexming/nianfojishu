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
					<a href="AccessEquipmentAction_toadd.action"
						style="color: rgb(79, 77, 77)">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加门禁设备
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="AccessEquipmentAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
								设备名称
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentName"
									id="equipmentName" />
							</td>
							<th align="center">
								设备编号
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentNum"
									id="equipmentNum" />
							</td>
						</tr>
						<tr>
							<th align="center">
								设备供应商
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentSupplier"
									id="equipmentSupplier" />
							</td>
							<th align="center">
								设备用途
							</th>
							<td align="center">
								<select name="accessEquipment.equipmentOutIn"
									id="equipmentOutIn" style="width: 152px;">
									<option value="">
										设备用途(不选默认为双向)
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
									id="equipmentIP" />
							</td>
							<th align="center">
								设备端口
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentPort"
									id="equipmentPort" />
							</td>
						</tr>
						<tr>
							<th align="center">
								设备摆放位置
							</th>
							<td align="center">
								<input type="text" name="accessEquipment.equipmentLocation"
									id="equipmentLocation" />
							</td>
							<th align="center">
								请选择门禁类型
							</th>
							<th align="center">
								<select name="accessEquipment.equipmentDaoType" id="equipmentDaoType"
										style="width: 156px;"
										onMouseOver="createDept('equipmentDaoType','JLMApplicationAction!findSelectName.action')">
										<option value="">
											请选择类型
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
									<option value="">--请选择--</option>
									<option value="是">是</option>
									<option value="否">否</option>
								</select>	
							</td>
							<th>
								是否巡更：
							</th>
							<td colspan="1" align="center">
								<select name="accessEquipment.isXungeng" id="isXungeng" style="width: 152px;">
									<option value="否">否</option>
									<option value="是">是</option>
								</select>	
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
	if (!validateText("equipmentLocation", "设备摆放位置")) {
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
