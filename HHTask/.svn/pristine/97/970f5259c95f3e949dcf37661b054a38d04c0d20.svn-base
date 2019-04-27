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
		<div align="center">
			<form action="banCiAction_changeBanCi.action" method="post"
				onsubmit="return checkOK()">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">调换班次</font>
						</th>
					</tr>
					<tr>
						<th align="right">
							班次1:
						</th>
						<td>
							<SELECT id="banci1" name="banci1" style="width: 153px;">
								<option></option>
							</SELECT>
						</td>
						<th align="right">
							班次2:
						</th>
						<td>
							<SELECT id="banci2" name="banci2" style="width: 153px;">
								<option></option>
							</SELECT>
						</td>
					</tr>
					<tr>
						<th align="right">
							操作类型:
						</th>
						<td>
							<SELECT name="tager" style="width: 153px;">
								<option value="1">班次对调</option>
								<option value="2">将班次1用户绑定至班次2</option>
							</SELECT>
						</td>
						<td align="center" colspan="2">
							<input type="submit" value="确定" class="input" id="sub" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
function checkOK() {
	var banci1 = document.getElementById("banci1");
	var banci2 = document.getElementById("banci2");
	
	if (banci1.value == "") {
		alert("请选着班次1!");
		banci1.focus();
		return false;
	}
	if(banci2.value == ""){
		alert("请选着班次2");
		banci2.focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		parent.location.reload(true);//刷新父页面
	}
	$.ajax({
		url : "UsersAction!findAllBanci.action",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$(data).each(
				function() {
					$("<option value='" + this.id + "'>"
										+ this.name
										+ "</option>")
							.appendTo("#banci1")
					$("<option value='" + this.id + "'>"
										+ this.name
										+ "</option>")
							.appendTo("#banci2")
			});
			$("#banci1").tinyselect();
			$("#banci2").tinyselect();
		},
		error : function() {
			alert("服务器异常!");
		}
	})
});
</script>
</html>
