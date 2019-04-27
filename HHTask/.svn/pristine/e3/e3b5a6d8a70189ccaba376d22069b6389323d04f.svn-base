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
			style="width: 100%; border: th in solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="ProcessManagementAction!addprocess.action"
					method="post">
					<table class="table">
						<tr>
							<th colspan="4">
								定制流程管理
							</th>
						</tr>
						<tr>
							<th>
								流程成员分组
							</th>
							<th>
								流程模板成员
							</th>
							<th>
								流程参与部门
							</th>
							<th>
								流程审批成员
							</th>
						</tr>
						<tr>
							<th rowspan="3">
								<select id="groupName" name="templatenode.name"
									style="width: 100px;">
									<option></option>
									<s:iterator value="list" id="pagePeople">
										<option value="${pagePeople.id}">
											${pagePeople.groupName}
										</option>
									</s:iterator>
								</select>
							</th>
							<td rowspan="3" valign="middle" align="left">
								<select id="userSelect" multiple="multiple"
									style="width: 150px; height: 350px;">
								</select>
							</td>
							<td>
								本部门
								<br />
								<input id="basicRightBtn" type="button" value="==>">
								<br />
								<input id="basicLeftBtn" type="button" value="<==">
								<br />
							</td>
							<td>
								<select id="basicSelect" multiple="multiple"
									style="width: 200px; height: 100px;"></select>
							</td>
						</tr>
						<tr>
							<td>
								副总经理名单
								<br />
								<input id="deputuRightBtn" type="button" value="==>">
								<br />
								<input id="deputuLeftBtn" type="button" value="<==">
								<br />
							</td>
							<td>
								<select id="deputuSelect" multiple="multiple"
									style="width: 200px; height: 100px;"></select>
							</td>
						</tr>
						<tr>
							<td>
								总经理
								<br />
								<input id="bossRightBtn" type="button" value="==>">
								<br />
								<input id="bossLeftBtn" type="button" value="<==">
								<br />
							</td>
							<td>
								<select id="bossSelect" multiple="multiple"
									style="width: 200px; height: 100px;"></select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input id="submitBtn" type="button" value="定制">
								<input type="button" value="清空">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

//级联查询审批组所对应的所有人员
$("#groupName").bind(
		"change",
		function() {
			if ($("#groupName").val() != "") {
				$.ajax( {
					url : "ProcessManagementAction!findGroupName.action",
					type : 'post',
					dataType : 'json',
					cache : false,//防止数据缓存
					data : {
						id : $("#groupName").val()
					},
					success : function(date) {
						$("#userSelect").empty();//清空
						$("<option></option>").appendTo("#userSelect");
						$(date).each(
								function() {
									$(
											"<option value='" + this.userName
													+ "'>" + this.userName
													+ "</option>").appendTo(
											"#userSelect")
								});
					}
				});
			}

		});
</script>
	</body>
</html>
