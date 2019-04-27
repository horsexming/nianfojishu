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
		<div id="gongneng">
			<div align="center">
				<form action="BlockAction_fenpeiUser.action" method="post"
					onsubmit="return checkAssessPersonnel()">
					<input name="block.id" value="${block.id}" type="hidden" />
					<input id="userId" name="blockUsers.userId" type="hidden" />
					<table align="center" class="table" style="width: 80%;">
						<tr>
							<th colspan="6">
								<font size="5">分配工作人员</font>
							</th>
						</tr>
						<tr>
							<th align="right">
								工号:
							</th>
							<td>
								<input id="code" onblur="send(this)" name="blockUsers.userCode"
									value="" />
							</td>
							<th align="right">
								卡号:
							</th>
							<td>
								<input id="cardId" name="blockUsers.userCard" title="只读"
									readonly="readonly" value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								<input id="dept" name="blockUsers.userDept" title="只读"
									readonly="readonly" value="" />
							</td>
							<th align="right">
								姓名:
							</th>
							<td>
								<input id="userName" name="blockUsers.userName" title="只读"
									readonly="readonly" value="" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="分 配" class="input" />
								&nbsp;&nbsp;
								<input type="reset" value="取  消" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="5">
							已分配员工
						</th>
					</tr>
					<tr>
						<th>
							序号:
						</th>
						<th>
							工号:
						</th>
						<th>
							卡号:
						</th>
						<th>
							部门:
						</th>
						<th>
							姓名:
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageBlockUser" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageBlockUser.userCode}
						</td>
						<td>
							${pageBlockUser.userCard}
						</td>
						<td>
							${pageBlockUser.userDept}
						</td>
						<td>
							${pageBlockUser.userName}
						</td>
						<td>
							<a href="BlockAction_delBlockUser.action?id=${pageBlockUser.id}&block.id=${block.id}"
								onclick="return window.confirm('确定提交?')">删除</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">

function send(obj) {
	sendRequest("UsersAction!findCardIdBCodeForzgkh.action?user.code="
			+ obj.value, messageResponse);
}
// 人员查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var userId = document.getElementById("userId");//用户id
			var code = document.getElementById("code");//工号
			var cardId = document.getElementById("cardId");//卡号
			var userName = document.getElementById("userName");//用户名
			var dept = document.getElementById("dept");//部门
			var value = message.split("|");
			if (value[1] == null) {
				document.getElementById("showError").innerHTML = message;
				userId.value = "";
				cardId.value = "";
				userName.value = "";
				dept.value = "";
				code.focus();
				code.select();
				code.title = message;
				code.style.border = " solid 1px red";
				return;
			} else {
				code.title = "该工号存在!";
				cardId.value = value[0];
				userName.value = value[1];
				dept.value = value[2];
				userId.value = value[3];
				code.style.border = " solid 1px";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//表单检查
function checkAssessPersonnel() {
	var code = document.getElementById("code");//工号
	var cardId = document.getElementById("cardId");//工号
	if (code.value == "") {
		alert("请填写工号以查询人员信息!");
		code.focus();
		return false;
	} else if (cardId.value == "") {
		alert("请填写卡号以查询人员信息!");
		code.focus();
		return false;
	}
	return true;
}
</script>
		</center>
	</body>
</html>
