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
	if (errorMessage == "修改成功！") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					修改积分
					<br />
				</h3>
				<form action="ToolCabineAction_updateDr.action" method="post"
					onsubmit="return validate()">
					<table style="width: 100%">
						<tr>
							<th align="right">
								物品名称:${drinksType.drinkName}
								<input type="hidden" name="drinksType.id"
									value="${drinksType.id}" />
							</th>
							<th align="center">
								物品积分：
								<input type="text" id="drinkJiFen" name="drinksType.drinkJiFen"
									value="${drinksType.drinkJiFen}" />
							</th>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="修改"
									style="width: 80px; height: 50px;" />
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
	if (!validateText("drinkJiFen", "积分")) {
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
$("#drinkJiFen").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
	</body>
</html>
