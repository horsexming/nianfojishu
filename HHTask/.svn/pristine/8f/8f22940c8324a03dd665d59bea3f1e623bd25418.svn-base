<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<form action="signatureAction_updateSignature.action"
			enctype="multipart/form-data" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">修改电子签名</font>
					</th>
				</tr>
				<tr>
					<th>
						电子签名:
					</th>
					<td colspan="3">
						重新上传:<input id="signature_address" type="file" name="signature_address"
							value="${signature.signature_address}" />
						历史签名:<img src="<%=basePath%>${signature.signature_address}" width="100px;" height="100px;">
						<input type="hidden" name="signature.code"
							value="${signature.code}" />
						<input type="hidden" name="signature.name"
							value="${signature.name}" />
						<input type="hidden" name="signature.id" value="${signature.id}" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value=" 修改" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</html>
