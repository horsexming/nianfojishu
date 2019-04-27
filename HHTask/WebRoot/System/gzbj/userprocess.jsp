<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sd" uri="/struts-dojo-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<%@include file="/util/sonHead.jsp"%>
	</HEAD>
	<script type="text/javascript">
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
		}
	});
});
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var deptid = $("#deptname").val();
	if (deptid == "0") {
		$("#username").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#username");
		var tinyselect = $(".tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("userstd").removeChild(tinyselect[1]);
		}
		$("#username").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#username");

						}
						$(data).each(
								function() {
									$(
											"<option value='" + this.id + "'>"
													+ this.code + "__"
													+ this.name + "</option>")
											.appendTo("#username");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("userstd").removeChild(
									tinyselect[1]);
						}
						$("#username").tinyselect();

					},
					error:function(data){
						console.log('json:',data.responseText);
						
					}
				});
	}

}
$(document).ready(function() {
	userlist(1);
});
</script>
	<body style="height: 4500px;">
		<div align="center">
			<form action="GzstoreAction_userProcessView.action" method="post"
				style="margin: 0px; padding: 0px;" onsubmit="return validate1()">
				<table class="table" align="center">
					<tr>
						<td align="center">
							<select id="deptname" style="width: 100px;"
								onchange="userlist(0)">
								<s:if test="users==null">
									<option value="0">
										请选择部门
									</option>
								</s:if>
								<s:else>
									<option value="<s:property value="users.deptId"/>">
										<s:property value="users.dept" />
									</option>
								</s:else>
							</select>

						</td>
						<td align="center" id="userstd">
							<s:if test="users==null">
								<select id="username" name="users.id" style="width: 100px;">
									<option value="0">
										请先选择部门
									</option>
								</select>
							</s:if>
							<s:else>
								<select id="username" name="users.id" style="width: 100px;">
									<option value="<s:property value="users.id"/>">
										<s:property value="users.name" />
									</option>
								</select>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询(select)" />
						</td>
					</tr>
				</table>
			</form>
			<form action="GzstoreAction_linkUserProcess.action" method="post"
				style="margin: 0px; padding: 0px;" onsubmit="return validate2()">
				<table style="width: 980px; border-collapse: collapse;" border="0"
					align="center">
					<tr>
						<td align="right" colspan="7">
							<input id="hiddenid" type="hidden" name="users.id"
								value="<s:property value="users.id"/>">
							<font color="red">共选择 <label id="peopleLabel">
									${count}
								</label> 个</font>
							<input type="submit" value="绑定"
								style="width: 60px; height: 40px;" align="top">
							<br>
							<br>
						</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							工序名称
							<br />
							(process name)
						</td>
						<td>
							<input type="checkbox" id="checkAll" onchange="chageAllCheck()">
							全选
						</td>
					</tr>
					<s:iterator id="had" value="hadList" status="ststusfunction">
						<s:if test="#ststusfunction.first">
							<tr bgcolor="green">
								<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
									align="center">
									<font color="#ffffff"> 已绑定技能系数</font>
								</td>
							</tr>
						</s:if>
						<s:if test="#ststusfunction.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusfunction.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#ststusfunction.index+1" />
							</font>
						</td>
						<td>
							${had.processName}
						</td>

						<td>
							<input type="checkbox" name="checkboxs" value="${had.id}"
								onchange="chageNum()" checked="checked">
						</td>
						</tr>
						<s:if test="#ststusfunction.last">
							<tr bgcolor="green">
								<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
									align="center">
									<font color="#ffffff"> 未绑定技能系数</font>
								</td>
							</tr>
						</s:if>
					</s:iterator>

					<s:iterator id="unHad" value="unHadList" status="ststusfunction">
						<s:if test="#ststusfunction.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusfunction.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#ststusfunction.index+1" />
							</font>
						</td>
						<td>
							${unHad.processName}
						</td>

						<td>
							<input type="checkbox" name="checkboxs" value="${unHad.id}"
								onchange="chageNum()" />
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="7" align="center">
							<font color="red">${errorMessage}</font>
						</td>
					</tr>
					<tr>
						<td colspan="11" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
					<tr>
						<td align="right" colspan="7">
							<br>
							<font color="red">共选择 <label id="peopleLabel2">
									${count}
								</label> 个</font>
							<input type="submit" value="绑定"
								style="width: 60px; height: 40px;" align="top">
							<br>
							<br>
							<br>
							<br>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
		peopleLabel.innerHTML = checkboxs.length;
		peopleLabel2.innerHTML = checkboxs.length;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
		peopleLabel.innerHTML = 0;
		peopleLabel2.innerHTML = 0;
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	peopleLabel.innerHTML = count;
	peopleLabel2.innerHTML = count;
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function validate1() {
	var userId = document.getElementById("username").value;
	if (userId == 0) {
		alert("请选择人员");
		return false;
	}
}
function validate2() {
	var hiddenid = document.getElementById("hiddenid").value;
	if (hiddenid == 0) {
		alert("请选择人员");
		return false;
	}
}
</script>
	</body>

</HTML>
