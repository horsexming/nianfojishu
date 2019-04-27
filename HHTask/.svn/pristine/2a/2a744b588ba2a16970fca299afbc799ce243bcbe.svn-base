<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h3></h3>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng"
		style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong"
			style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div
				style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
				align="left"></div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a
					href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
					style="color: #ffffff">刷新</a>
			</div>
		</div>

		<div align="center">
			<h4>修改文件类型/文件等级</h4>
			<form action="systemFileAction_updateFileNameorType.action"
				method="post">
				<table class="table">
					<tr>
						<th align="right">类型</th>
						<td>
							<select name="fileType.typeforit">
								<option value="${fileType.typeforit }">
									${fileType.typeforit}
								</option>
							</select>
						</td>
						<th align="right">（文件等级/文件类型）名称</th>
						<td><input type="text" name="fileType.typeName"
							value="${fileType.typeName}" /></td>
						<s:if test="fileType.typeforit=='文件类别'">
							<th align='right' class='appendTd'>
								类别代码
							</th>
							<td class='appendTd'>
								<input type='text' name='fileType.code' value="${fileType.code }">
							</td>
						</s:if>
					</tr>

					<td colspan="6" align="center">
						<input type="hidden" value="${tag }" name="tag" />
						<input type="hidden" value="${cpage }" name="cpage" />
						<input type="hidden" value="${fileType.id}" name="fileType.id" />
						<input type="submit" value="提交">
					</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		
	</script>
</body>
</html>
