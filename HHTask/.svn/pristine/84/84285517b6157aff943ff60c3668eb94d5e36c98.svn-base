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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div>
					<font color="red">${successMessage}${errorMessage}</font>
				</div>
				<div align="center">
					<form action="ShortMessage_sendMessageInput.action" method="post">
						<table>
							<tr>
								<td>
									部门：
									<SELECT id="dept" name="usersInput.dept" style="width: 154px" >
										<option></option>
									</SELECT>
								</td>
								<td>
									工号：
									<input type="text" name="usersInput.code" />
								</td>
								<td rowspan="2" valign="middle" align="center">
									<input type="submit" style="width: 70px; height: 50px"
										value="提交">
								</td>
							</tr>
							<tr>
								<td>
									姓名：
									<input type="text" name="usersInput.name" />
									<br />
								</td>
								<td>
									手机：
									<input type="text" name="usersInput.password.phoneNumber" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<form action="ShortMessage_sendMessage.action" method="post"
					onsubmit="return checkSubmit();">
					<table class="table">
						<tr>
							<td>
								<input type="checkbox" onclick="selectAll(this); ">
								全选
							</td>
							<td>
								序号
							</td>
							<td>
								名称
							</td>
							<td>
								工号
							</td>
							<td>
								卡号
							</td>
							<td>
								部门
							</td>
							<td>
								手机号
							</td>
						</tr>
						<s:iterator value="usersList" id="u" status="st">
							<tr>
								<td>
									<s:if test="#u.password.phoneNumber.length() != 11">
										<input name="usersSend.code" type="checkbox" value="${u.code}"
											disabled="disabled" title="手机号不为11位">
									</s:if>
									<s:else>
										<input name="usersSend.code" type="checkbox" value="${u.code}">
									</s:else>
								</td>
								<td>
									${st.index + 1}
								</td>
								<td>
									${u.name}
								</td>
								<td>
									${u.code}
								</td>
								<td>
									${u.cardId}
								</td>
								<td>
									${u.dept}
								</td>
								<td>
									${u.password.phoneNumber}
								</td>
							</tr>
						</s:iterator>
					</table>
					<table>
						<tr>
							<td>
								发送信息内容：
							</td>
							<td>
								<textarea id="msg" name="msg" rows="4" cols="30" title="请在60字以内"></textarea>
							</td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" value="发送">
					</div>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function selectAll(topCheckBox) {
	var checkboxes = document.getElementsByName("usersSend.code");
	for ( var i = 0; i < checkboxes.length; i++) {
		if (!checkboxes[i].disabled) {
			checkboxes[i].checked = topCheckBox.checked;
		}
	}
}

function checkSubmit() {
	if (!checkCheckBox()) {
		alert("至少选择一项");
		return false;
	}
	var a = document.getElementById("msg");
	if (a.value.length > 50) {
		alert("要发送的消息太长，请控制在50字以内");
		return false;
	}
	a.value = a.value.replace(/^\s+|\s+$/g, "");
	if (a.value.length == 0) {
		alert("请输入短信内容！");
		return false;
	}
	return true;
}

function checkCheckBox() {
	var k = document.getElementsByName("usersSend.code");
	var b = false;
	for ( var a = 0; a < k.length; a++) {
		if (k[a].checked) {
			return true;
		}
	}
	return b;
}
</script>
	</body>
</html>
