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
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage == "添加成功") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
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
					<a href="AccessEquipmentAction_toadd_Web.action?tag=${tag}"
						style="color: rgb(79, 77, 77)"><br />
					</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加时间段
				</h3>
				<form action="AccessEquipmentAction_addAT.action"
					method="post" onsubmit="return validate()">
						<table class="table">
							<tr style="width: 100%">
								<th align="center">
									开始时间
									<input type="hidden" name="accessEquipment.id"
										value="${accessEquipment.id}" />
								</th>
								<td align="center">
									<input class="Wdate" type="text" name="accessTime.startTime"
										id="startTime" onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})"/>
								</td> 
								<th align="center">
									结束时间
								</th>
								<td align="center">
									<input class="Wdate" type="text" name="accessTime.endTime"
										id="endTime" onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})"/>
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
	if (!validateText("startTime", "开始时间")) {
		return false;
	}
	if (!validateText("endTime", "结束时间")) {
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
