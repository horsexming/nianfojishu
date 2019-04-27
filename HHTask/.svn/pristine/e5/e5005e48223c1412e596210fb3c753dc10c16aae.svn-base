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
	<div>
		<form action="OneLightAction_update.action" method="post"
			onsubmit="return validate()">
			<table class="table">
				<tr>
					<th colspan="4">
						<font size="5">修改照明灯</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						灯号:
						<input type="hidden" name="light.id" value="${light.id}" />
					</th>
					<td>
						<input id="lightNum" name="light.lightNum" value="${light.lightNum}" />
					</td>
					<th align="right">
						灯类型:
					</th>
					<td>
						<input id="lightType" name="light.lightType" value="${light.lightType}" />
					</td>
				</tr>
				<tr>
					<th align="right">
						灯IP:
					</th>
					<td>
						<input id="lightIP" name="light.lightIP" value="${light.lightIP}" />
					</td>
					<th align="right">
						灯端口:
					</th>
					<td>
						<input id="lightPort" name="light.lightPort" value="${light.lightPort}" />
					</td>
				</tr>
				<tr>
					<th align="right">
						开关指令(1~26):
					</th>
					<td>
						<input id="lightZhiLing" name="light.lightZhiLing" value="${light.lightZhiLing}" />
					</td>
					<th align="right">
						是否绑定门禁:
					</th>
					<td colspan="3">
						<SELECT  name="light.aceIs" id = "aceIs" style="width: 152px;">
							<option value="${light.aceIs}">${light.aceIs}</option>
							<option value="否">否</option>
							<option value="是">是</option>
						</SELECT>
					</td>
				</tr>
				<tr>
					<th align="right">
						延时打开指令:
					</th>
					<td>
						<input id="lightOpen" name="light.lightOpen" value="${light.lightOpen}" />
					</td>
					<th align="right">
						打开延时时长(分钟):
					</th>
					<td colspan="3">
						<input id="lightOpenMin" name="light.lightOpenMin" value="${light.lightOpenMin}" />
					</td>
				</tr>
				<tr>
					<th align="right">
						延时关闭指令:
					</th>
					<td>
						<input id="lightClose" name="light.lightClose" value="${light.lightClose}" />
					</td>
					<th align="right">
						关闭延时时长(分钟):
					</th>
					<td colspan="3">
						<input id="lightCloseMin" name="light.lightCloseMin" value="${light.lightCloseMin}" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value="修改" class="input" />
						<input type="reset" value="重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
function validate() {
	if (!validateText("lightNum", "灯编号")) {
		return false;
	}
	if (!validateText("lightType", "灯类型")) {
		return false;
	}
	if (!validateText("lightIP", "灯IP")) {
		return false;
	}
	if (!validateText("lightPort", "灯端口")) {
		return false;
	}
	var lightZhi = $("#lightZhiLing").val();
	if(lightZhi>26){
		alert("开关指令不能大于26");
		return false;
	}
	if (!validateText("lightZhiLing", "开关指令")) {
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
$("#lightPort").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
$("#lightZhiLing").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
</html>
