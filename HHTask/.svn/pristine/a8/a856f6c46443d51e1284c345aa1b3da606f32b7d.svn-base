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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form id="processPeopleFrom"
					action="ProcessPeopleAction!addProcessPeople.action" method="post">
					<input name="processPeople.processId" type="hidden"
						value="${processId}" />
					<input id="userName" name="processPeople.name" type="hidden" />
					<table class="table" style="width: 600px;">
						<tr>
							<th colspan="4">
								添加人员
							</th>
						</tr>
						<tr>
							<th align="right">
								部门
							</th>
							<td align="left">
								<select name="processPeople.dept" id="dept"
									style="width: 100px; float: left;">
									<option></option>
								</select>
							</td>
							<th align="right">
								名称
							</th>
							<td align="left">
								<select id="users" style="width: 100px; float: left;"
									name="calendar.userId">
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								工号
							</th>
							<td align="left">
								<input id="code" name="processPeople.code" readonly="readonly">
							</td>
							<th align="right">
								提交量
							</th>
							<td align="left">
								<input id="sum" name="processPeople.sum">
							</td>
						</tr>
						<tr>
							<th align="right">
								类型
							</th>
							<td align="left">
								<select name="processPeople.status">
									<option value="准备">
										准备
									</option>
									<option value="操作">
										操作
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th colspan="4">
								<input type="submit" value="添加"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="清空"
									style="width: 80px; height: 50px;" />
							</th>
						</tr>
					</table>
				</form>

				<table class="table">
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 20px; font-weight: bold; height: 50px;">
						<td>
							工号
						</td>
						<td>
							名称
						</td>
						<td>
							部门
						</td>
						<td>
							提交量
						</td>
						<td>
							类型
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="processPeopleList" status="se" id="pagePp">
						<s:if test="#se.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							${pagePp.code}
						</td>
						<td>
							${pagePp.name}
						</td>
						<td>
							${pagePp.dept}
						</td>
						<td>
							${pagePp.sum}
						</td>
						<td>
							${pagePp.status}
						</td>
						<td>
							<a href="">修改</a>/
							<a
								href="ProcessPeopleAction!delProcessPeople.action?id=${pagePp.id}"
								onclick="return window.confirm('确定要删除该人员吗?')">删除</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<script type="text/javascript">

//表单验证
$("#processPeopleFrom").bind("submit", function() {
	if ($("#dept").val() == "") {
		alert("请选择部门");
		return false;
	} else if ($("#users").val() == "") {
		alert("请选择名称");
		return false;
	} else if ($("#sum").val() == "") {
		alert("请输入提交量");
		$("#sum").select();
		return false;
	}
	return true;
});

//查询所有的部门
$.ajax( {
	url : 'DeptNumberAction!findAllDept.action',
	dataType : 'json',
	cache : false,//防止数据缓存
	success : function(allDdept) {
		$("#dept").empty();
		$("<option></option>").appendTo("#dept");
		$(allDdept).each(
				function() {
					$(
							"<option value='" + this.dept + "'>" + this.dept
									+ "</option>").appendTo("#dept");
				});
	}

});
//级联查询出部门所对应的所有人员
$("#dept")
		.bind(
				"change",
				function() {
					if ($("#dept").val() != "") {
						$
								.ajax( {
									url : "UsersAction!findUsersByDept.action",
									type : 'post',
									dataType : 'json',
									cache : false,//防止数据缓存
									contentType : "application/x-www-form-urlencoded; charset=utf-8",
									data : {
										deptName : $("#dept").val()
									},
									success : function(useradsfa) {
										$("#users").empty();//清空
										$("<option></option>").appendTo(
												"#users");
										$(useradsfa)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.code
																			+ "|"
																			+ this.name
																			+ "'>"
																			+ this.name
																			+ "</option>")
																	.appendTo(
																			"#users")
														});
										$("#users")
												.bind(
														"change",
														function() {
															var user = $(
																	"#users")
																	.val();
															var userCodeName = user
																	.split("|");
															if (userCodeName != "") {
																$("#code")
																		.val(
																				userCodeName[0]);
																$("#userName")
																		.val(
																				userCodeName[1]);
															} else {
																$("#code").val(
																		"");
																$("#userName")
																		.val("");
															}
														})
									},
									error : function() {
										alert("服务器异常!");
									}
								});
					}

				});
</script>
	</body>
</html>
