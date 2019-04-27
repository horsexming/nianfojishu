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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h3>
					修改衣柜密码
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form
					action="ToolCabineAction_updatemima.action?id=${toolCabine.id}"
					method="post" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
								衣柜原密码
							</th>
							<td align="center">
								<input type="text" id="oldpwd" 
									name="oldpassword" placeholder="旧密码" />
							</td>
						</tr>
						<tr>
							<th align="center">
								新密码（4位数字即可）
							</th>
							<td align="center">
								<input id="newpwd" type="password" placeholder="新密码" maxlength="4" 
								onblur="numyanzheng(this,'zhengshu')" onkeyup="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="center">
								再次确认
							</th>
							<td align="center">
								<input name="newpassword" id="passWord" type="password" maxlength="4"
									placeholder="再次确认" value="" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="提交"
									style="width: 100px; height: 50px;" />
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
	if (!validateText("oldpwd", "旧密码")) {
		return false;
	}
	if (!validateText("newpwd", "新密码")) {
		return false;
	}
	if (!validateText("passWord", "确认密码")) {
		return false;
	}
	if ($("#newpwd").val() != $("#passWord").val()) {
		alert("两次密码不一致");
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
