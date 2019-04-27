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
	if (errorMessage == "修改成功") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					修改能耗信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="NoncorePayableAction!updateEner.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th>
								能耗类型:<input type="hidden" value="${energyConsumption.id}" name="energyConsumption.id"/>
							</th>
							<td>
								<SELECT name="energyConsumption.type" id="type" style="width: 152px;">
									<option value="${energyConsumption.type}">${energyConsumption.type}</option>
									<option value="水费">水费</option>
									<option value="电费">电费</option>
								</SELECT>
							</td>
							<th>
								能耗单价:
							</th>
							<td>
								<input type="text" name="energyConsumption.unitPrice" id="unitPrice" onblur="mustBeNumber('unitPrice')" value="${energyConsumption.unitPrice}"/>
							</td>
						</tr>
						<tr>
							<th>
								能耗名称:
							</th>
							<td>
								<input type="text" name="energyConsumption.name" id="name" value="${energyConsumption.name}"/>
							</td>
							<th>
								是否限时:
							</th>
							<td>
								<SELECT name="energyConsumption.isTimeLimit" id="isTimeLimit" onclick="xianshi()" style="width: 152px;">
									<option value="${energyConsumption.isTimeLimit}">${energyConsumption.isTimeLimit}</option>
									<option value="否">否</option>
									<option value="是">是</option>
								</SELECT>
							</td>
						</tr>
						<tr id="time" style="display: none;">
							<th>
								限时起始时间:
							</th>
							<td>
								<input type="text" class="Wdate" name="energyConsumption.startTime" id="startTime"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})" value="${energyConsumption.startTime}"
								/>
							</td>
							<th>
								限时结束时间:
							</th>
							<td>
								<input type="text" class="Wdate" name="energyConsumption.endTime" id="endTime"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})" value="${energyConsumption.endTime}"
								/>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改" style="width: 80px; height: 30px;"/>
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
	if (!validateText("type", "类型")) {
		return false;
	}
	if (!validateText("unitPrice", "单价")) {
		return false;
	}
	if($("#unitPrice").val()<=0){
		alert("单价不能小于等于0");
		return false;
	}
	if (!validateText("name", "名称")) {
		return false;
	}
	if($("#isTimeLimit").val()=='是'){
		if (!validateText("startTime", "限时起始时间")) {
		return false;
		}
		if (!validateText("endTime", "限时结束时间")) {
			return false;
		}
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

function xianshi(){
	if($("#isTimeLimit").val()=='是'){
		$("#time").show();
	}else{
		$("#time").hide();
		$("#startTime").val("");
		$("#endTime").val("");
	}
}
$(function(){
	xianshi();
});
</script>
	</body>
</html>
