<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>

	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="AssessPersonnelAction!findAllAssessPersonnel.action"
						style="color: #ffffff">考核人员管理</a>
				</div>
			</div>
			
			<div align="center">
				<div id="addDiv">
					<font color="red">${successMessage}</font>
					<form action="AssessPersonnelAction!addAssessPersonnel.action"
						method="post" onsubmit="return checkAssessPersonnel()">
						<input type="hidden" id="userId" name="assessPersonnel.userId">
						<table align="center">
							<tr>
								<th colspan="6">
									<font size="5">添加考核人员</font>
								</th>
							</tr>
							<tr>
								<th align="right">
									工号:
								</th>
								<td>
									<input id="code" onblur="send(this)"
										name="assessPersonnel.code" value="${user.code}" />
								</td>
								<th align="right">
									卡号:
								</th>
								<td>
									<input id="cardId" name="assessPersonnel.cardId" title="只读"
										readonly="readonly" />
								</td>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName" name="assessPersonnel.userName" title="只读"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="right">
									部门:
								</th>
								<td>
									<input id="dept" name="assessPersonnel.dept"
										readonly="readonly" title="只读" />
								</td>
								<th align="right">
									分组:
								</th>
								<td>
									<select>
										<option>
											默认
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="5">
									<textarea rows="" style="width: 350px; height: 80px;" cols=""
										name="assessPersonnel.remark"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="确  定" />
									&nbsp;&nbsp;
									<input type="reset" value="取  消" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="showDiv">
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								卡号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="assessPersonnelList" id="pageAp"
							status="pageStatus">
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
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageAp.code}
							</td>
							<td>
								${pageAp.cardId}
							</td>
							<td>
								${pageAp.userName}
							</td>
							<td>
								${pageAp.dept}
							</td>
							<td>
								<a onclick="return window.confirm('确定删除?')"
									href="AssessPersonnelAction!findAPById.action?id=${pageAp.id}">删除</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
				</div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
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
	if (code.value == "") {
		alert("请填写工号以查询人员信息!");
		code.focus();
		return false;
	}
	return true;
}
</script>
	</body>
</html>
