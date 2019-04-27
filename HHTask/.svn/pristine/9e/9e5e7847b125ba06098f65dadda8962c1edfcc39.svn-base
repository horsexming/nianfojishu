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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="UsersAction!findUsersAll.action" method="post">
					<table class="table">
						<tr>
							<th>
								部门
							</th>
							<td>
								<select name="user.dept" id="dept1">
									<option value="${user.dept}">${user.dept}</option>
									<option value=""></option>
								</select>
							</td>
							<th>
								姓名
							</th>
							<td>
								<input type="text" value="${user.name }" name="user.name" />
							</td>
						</tr>
						<tr>
							<th>
								工号
							</th>
							<td>
								<input type="text" value="${user.code}" name="user.code" />
							</td>
							<th>
								卡号
							</th>
							<td>
								<input type="text" value="${user.cardId}" name="user.cardId" />
							</td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="hidden" value="${id}" name="id" />
					<input type="submit" value="查询" class="input" />
				</form>
				<form action="UsersAction!UsersCardbangUser.action" method="post" onsubmit="return check()">
				<div id="showError" style="color: red;">
					</div>
					<table class="table">
						<s:if test="id !=null && id>0">
							<input type="hidden" value="${userscard.id}" name="userscard.id"/>
							<input type="hidden" value="${userscard.cardId}" name="userscard.cardId"/>
						</s:if>
						<s:else>
							<tr>
								<th colspan="8">
									<table>
										<tr>
											<th>
												持卡人工号:
											</th>
											<th>
												<input type="text" value="" id="code" name="userscard.ckUserCode" onblur="send(this)"/>
												<input type="hidden" value="" id="userId" name="userscard.ckUserId" />
											</th>
											<th>
												卡号:
											</th>
											<th>
												<input type="text" value="" id="cardId" name="userscard.cardId" />
											</th>
											<th>
												持卡人姓名:
											</th>
											<th>
												<input type="text" value="" id="userName" name="userscard.ckUserName" readonly="readonly"/>
											</th>
											<th>
												所属部门:
											</th>
											<th>
												<input type="text" value="" id="dept" name="userscard.dept" readonly="readonly" />
											</th>
											<th>
												所属班组:
											</th>
											<th>
												<input type="text" value="" id="group" name="userscard.groupcalass"  />
											</th>
										</tr>
									</table>
								</th>

							</tr>
						</s:else>

						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								<input id="checkNotAll" type="checkbox" name="invest"
									value="checkbox" onClick="selectIt(this)">
								<label for="checkNotAll">
									全选
								</label>
							</th>
							<th>
								序号
							</th>
							<th>
								姓名
							</th>
							<th>
								工号
							</th>
							<th>
								卡号
							</th>
							<th>
								部门
							</th>
						</tr>
						<s:iterator value="usersList" id="pageUsers" status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox" name="arrayId" value="${pageUsers.id}">
							</td>
							<td>
								<s:property value="#statussdf.index+1" />
							</td>
							<td>
								${pageUsers.name}
							</td>
							<td>
								${pageUsers.code}
							</td>
							<td>
								${pageUsers.cardId}
							</td>
							<td>
								${pageUsers.dept}
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="6" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus"/>
					<s:if test=" pageStatus == 'ybd'">
						<input type="submit" value="解绑" class="input" />
					</s:if>
					<s:else>
						<input type="submit" value="绑定" class="input" />
					</s:else>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
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
$(function(){
	createDept('dept1');
})
function selectIt(obj) {
	var checkboxs = document.getElementsByName("arrayId");
	if(obj!=null && checkboxs!=null){
		if(obj.checked == true){
			for(var i=0;i<checkboxs.length; i++){
				checkboxs[i].checked = "checked";
			}
		}else if(obj.checked == false){
			for(var i=0;i<checkboxs.length; i++){
				checkboxs[i].checked = false;
			}
		}
	}
	
}

function check(){
	var cardId = $("#cardId").val();
	var code = $("#code").val();
	var userName = $("#userName").val();
	var dept = $("#dept").val();
	if(cardId == ''){
		$("#showError").html("请填写卡号");
		return false;
	}else if(code == ''){
		$("#showError").html("请填写持卡人工号");
		return false;
	}
}

</SCRIPT>
	</body>
</html>
