<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'DmlAppFileUrl_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="System/dmltry/jquery-1.11.3.min.js"></script>
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
				${dmltryAppFiles2.appFilename}		添加明细信息 <br />
				<s:if test="successMessage!=null">
					<font color="red">${successMessage}</font>
				</s:if>
			</h3>

			<form action="DmltryAppFilesAction!addin.action" method="post"
				enctype="multipart/form-data" onsubmit="return validate()">
				<table class="table">
					<tr style="display: none;">
					<td align="right">id：</td>
						<td ><input type="text" value="${dmltryAppFiles2.id}"
							name="dmlAppFileUrl.dmltryAppFiles.id" /></td>
					</tr>
					
					<tr>
						<td align="right">名称：</td>
						<td ><input type="text" value="${dmltryAppFiles2.appFilename}"
							name="dmltryAppFiles.appFilename" readonly="readonly" /></td>
					</tr>

					<tr>
						<td align="right">描述：</td>
						<td><input type="text"
							value="${dmltryAppFiles2.appFilesmshu}"
							name="dmltryAppFiles.appFilesmshu" readonly="readonly" /></td>
					</tr>
					
					
					<tr>
						<td align="right">版本号：</td>
						 <td colspan="2"><input type="text" name="dmlAppFileUrl.coide"  id="bbh"></td>
					</tr>
					
					<tr>
						<td align="right">附件：</td>
						 <td colspan="2"><input type="file" name="fujian" id="fuj"></td>
					</tr>
					
					<tr>
					<td align="right"><input type="submit"  value="提交" /></td>
					<td><input type="reset"  value="重置"/></td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function validate() {
			if (!validateText("fuj", "文件名称")) {
				return false;
			}
				if (!validateText("fuj", "文件名称")) {
				return false;
			}
				if (!validateText("bbh", "版本号")) {
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
