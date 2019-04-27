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

			</div>

			<div align="center">
				<h3>
					修改员工车辆信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="InEmployeeCarInforAction_update.action?tag=${tag}"
					method="post" enctype="multipart/form-data"
					onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
								员工工号
								<input type="hidden" name="inEmployeeCarInfor.id"
									value="${inEmployeeCarInfor.id}" />
								<input type="hidden" name="inEmployeeCarInfor.car_User_Id"
									value="${inEmployeeCarInfor.car_User_Id}" />
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.ncode" id="ncode"
									value="${inEmployeeCarInfor.ncode}" //>
							</td>
							<th align="center">
								员工姓名
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.name" id="name"
									value="${inEmployeeCarInfor.name}" / />
							</td>
						</tr>
						<tr>
							<th align="center">
								员工部门
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.ndept" id="ndept"
									value="${inEmployeeCarInfor.ndept}" />
							</td>
							<th align="center">
								员工车牌号
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.nplates"
									id="nplates" value="${inEmployeeCarInfor.nplates}" / />
							</td>
						</tr>
						<tr>
							<th align="center">
								车辆类型
							</th>
							<td align="center">
								<select name="inEmployeeCarInfor.carType" id="carType"
									style="width: 155px;" onchange="dianji()">
									<option value="${inEmployeeCarInfor.carType}">
										${inEmployeeCarInfor.carType}
									</option>
									<option value="公车">
										公车
									</option>
									<option value="个人">
										个人
									</option>
								</select>
							</td>
							<th align="center">
								进出是否发送RTX消息
							</th>
							<td align="center">
								<select name="inEmployeeCarInfor.rtxMessage" id="rtxMessage"
									style="width: 155px;">
									<option value="${inEmployeeCarInfor.rtxMessage}"
										style="width: 156px;">
										${inEmployeeCarInfor.rtxMessage}
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>

						</tr>
						<tr>
							<th align="center">
								车型
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.carModels"
									id="carModels" value="${inEmployeeCarInfor.carModels}" / />
							</td>
							<th align="center">
								车颜色
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.carColor"
									id="carColor" value="${inEmployeeCarInfor.carColor}" / />
							</td>
						</tr>
						<tr id="mytable">
							<th align="center">
								价格（元）
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.price" id="price"
									value="${inEmployeeCarInfor.price}"
									onkeyup="mustBeNumber('price')" />
							</td>
							<th align="center">
								折旧公里数
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.zjDistance"
									id="zjDistance" value="${inEmployeeCarInfor.zjDistance}"
									onkeyup="mustBeNumber('zjDistance')" />
							</td>
						</tr>
						<tr id="mytable1">
							<th align="center">
								行驶公里数
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.driveDistance"
									id="driveDistance" value="${inEmployeeCarInfor.driveDistance}"
									onkeyup="mustBeNumber('driveDistance')" />
							</td>
							<th align="center">
								每公里价钱（不含折旧费）
							</th>
							<td align="center">
								<input type="text" name="inEmployeeCarInfor.drivePrice"
									id="drivePrice" value="${inEmployeeCarInfor.drivePrice}"
									onkeyup="mustBeNumber('drivePrice')" "/>
							</td>
						</tr>

						<tr>
							<th align="center">
								汽车行驶证附件
							</th>
							<td align="center" colspan="1">
								<input type="file" name="fujian" id="carFiles"
									style="width: 105px;" />
								<a href="<%=path%>${inEmployeeCarInfor.carFiles}">查看附件</a>
								<input type="hidden" name="inEmployeeCarInfor.carFiles"
									id="carFiles" value="${inEmployeeCarInfor.carFiles}" />
							</td>
							<th align="center">
								是否白名单
							</th>
							<td align="center">
								<select name="inEmployeeCarInfor.whiteCar" id="whiteCar"
									style="width: 155px;">
									<option value="${inEmployeeCarInfor.whiteCar}">
										${inEmployeeCarInfor.whiteCar}
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="center">
								出门是否检车
							</th>
							<td align="center">
								<select name="inEmployeeCarInfor.nbdaijian" id="nbdaijian"
									style="width: 156px;">
									<option value="${inEmployeeCarInfor.nbdaijian}">
										${inEmployeeCarInfor.nbdaijian}
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
							<th align="center">
								出门是否申请
							</th>
							<td align="center">
								<select name="inEmployeeCarInfor.nbdaiSQ" id="nbdaiSQ"
									style="width: 156px;">
									<option value="${inEmployeeCarInfor.nbdaiSQ}">
										${inEmployeeCarInfor.nbdaiSQ}
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(update)"
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
$(document).ready(function() {
	dianji();
})

//判断车是否为个人用车	
function dianji() {
	var obj_select = document.getElementById("carType");
	var obj_div = document.getElementById("mytable");
	var obj_div1 = document.getElementById("mytable1");
	if ($("#carType").val() == "公车") {
		$("#mytable").show();
		$("#mytable1").show();
	} else {

		$("#mytable").hide();
		$("#mytable1").hide();
	}

}

function validate() {
	if (!validateText("ncode", "员工工号")) {
		return false;
	}
	if (!validateText("name", "员工姓名")) {
		return false;
	}
	if (!validateText("ndept", "员工部门")) {
		return false;
	}
	if (!validateText("nplates", "员工车牌号")) {
		return false;
	}
	if (!validateText("carType", "车辆类型（个人/公司）")) {
		return false;
	}
	if (!validateText("rtxMessage", "是否发送RTX消息")) {
		return false;
	}
	if (!validateText("nbdaijian", "出门是否检车")) {
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
