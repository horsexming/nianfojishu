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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					上传加章后附件
				</h3>
			</div>
			<table class="table">
				<tr>
					<th>
						上传协议
					</th>
				</tr>
				<tr>
					<th>
						1、确认上传的加章文件与申请文件一致，违者后果自行承担。
					</th>
				</tr>
				<tr align="center">
					<td>
						<input id="agreen" type="checkbox" onclick="onagreen()" />
						同意
					</td>
				</tr>
			</table>
			<form
				action="NoncoreReceAction!backFujian.action?tag=${tag}"
				method="post" enctype="multipart/form-data"
				onsubmit="return validate()">
				<table id="tongyi" class="table" style="display: none;">
					<tr>
						<td align="right">
							上传盖章后附件
							<input type="hidden" value="${nonCoreReceivablesDetail.id}" name="nonCoreReceivablesDetail.id" />
						</td>
						<td colspan="3">
							<input type="file" id="file1" name="fujian">
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" value="上传" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("file1", "上传文件")) {
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
function onagreen() {
	if (document.getElementById('agreen').checked) {
		$("#tongyi").show();
	} else {
		$("#tongyi").hide();
	}
}
</script>
	</body>
</html>
