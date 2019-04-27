<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px;"
			align="left">
			<div
				style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
				align="left"></div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
			</div>
		</div>

		<div align="center">
			<h3>
				添加 <br />
				<s:if test="successMessage!=null">
					<font color="red">${successMessage}</font>
				</s:if>
			</h3>
			<form action="DmltryAppFilesAction!add.action"
				method="post">
				<table class="table">

					<tr>
						<td>名称</td>
						<td><input type="text" name="dmltryAppFiles.appFilename" /></td>
					</tr>
					<tr>
						<td>描述</td>
						<td><input type="text" name="dmltryAppFiles.appFilesmshu" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交" /></td>
						<td><input type="reset" value="重置" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function validate() {
			if (!validateText("cabOpenOrder", "工具柜标识")) {
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
