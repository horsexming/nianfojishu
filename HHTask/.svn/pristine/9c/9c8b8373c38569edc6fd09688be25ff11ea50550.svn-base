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
		<script type="text/javascript">
$(function() {
	//表单提交验证
	$("#checkQuoForm").bind("submit", function() {
		if ($("#quotedNumber").val() == "") {
			alert("请填写询价单号!");
			$("#quotedNumber").focus();
			return false;
		} else if ($("#procardLifeCycle").val() == "") {
			alert("请填写产品生命周期!");
			$("#procardLifeCycle").focus();
			return false;
		} else if ($("#markId").val() == "") {
			alert("请填写总成号!");
			$("#markId").focus();
			return false;
		} else if ($("#proName").val() == "") {
			alert("请填写产品名称!");
			$("#proName").focus();
			return false;
		} else if ($("#filnalCount").val() == "") {
			alert("请填写数量!");
			$("#filnalCount").focus();
			return false;
		} else {
			return true;
		}
	});
})
</script>
	</head>
	<body>
		<div align="center">
			<form id="checkQuoForm" action="QuotedPrice_addQuotedPrice.action"
				enctype="multipart/form-data" method="post">
				<input name="quotedPrice.proId" value="${projectManage.id}"
					type="hidden" />
				<table class="table">
					<tr>
						<th colspan="2">
							核算填报
						</th>
					</tr>
					<tr>
						<th align="right">
							询价单号:
						</th>
						<td>
							<input type="text" value="" name="quotedPrice.quotedNumber"/><br/>
							（不填的话系统自动生成）
						</td>
					</tr>
					<tr>
						<th align="right">
							产品生命周期:
						</th>
						<td>
							<input id="procardLifeCycle" name="quotedPrice.procardLifeCycle" />
							(年)
						</td>
					</tr>
					<tr>
						<th align="right">
							总成号:
						</th>
						<td>
							<input id="markId" name="quotedPrice.markId" />
						</td>
					</tr>
					<tr>
						<th align="right">
							产品名称:
						</th>
						<td>
							<input id="proName" name="quotedPrice.proName" />
						</td>
					</tr>
					<tr>
						<th align="right">
							型别:
						</th>
						<td>
							<input name="quotedPrice.xingbie" />
						</td>
					</tr>
					<tr>
						<th align="right">
							数量:
						</th>
						<td>
							<input id="filnalCount" name="quotedPrice.filnalCount" />
						</td>
					</tr>
					<tr>
					<tr>
						<th align="right">
							是否保密:
						</th>
						<td>
							<s:if test="projectManage.isbaomi == '是'">
								<input type="radio" value="是" name="quotedPrice.isbaomi" checked="checked"/>是
								<input type="radio" value="否" name="quotedPrice.isbaomi" />否
							</s:if>
							<s:else>
								<input type="radio" value="是" name="quotedPrice.isbaomi" />是
								<input type="radio" value="否" name="quotedPrice.isbaomi" checked="checked"/>否
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="right">
							备注:
						</th>
						<td>
							<input name="quotedPrice.remark" />
						</td>
					</tr>
					<tr>
						<th align="right">
							附件上传:
						</th>
						<th align="left" colspan="3">
							<input type="file" name="attachment">
						</th>
					</tr>
					<tr>
						<th colspan="2">
							<input type="submit" value="添加" class="input" />
							<input type="reset" value="清空" class="input" />
						</th>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
