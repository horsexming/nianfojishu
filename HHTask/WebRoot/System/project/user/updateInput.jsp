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
		<SCRIPT type="text/javascript">
			$(function(){
				$("#userCard").bind('blur',function(){
					$.ajax({
						type: 'POST',
						url: 'UsersAction!findByCode.action',
						dataType: "json",
						data: "user.code=" + $("#userCard").val(),
						success: function(msg){
							if(msg.success){
								$("#dept").val(msg.data.dept);
								$("#inCharge").val(msg.data.name);
								$("#addForm [name='user.telphone']").val(msg.data.password.phoneNumber);
								$("#addForm [name='user.email']").val(msg.data.password.mailBox);
								$("#addForm [name='user.user.id']").val(msg.data.id);
							} else {
								$("#addForm [name='user.user.id']").val('');
							}
						}
					});
				});
				
				$("#addButton").bind('click',function(){
					if($("[name='user.user.id']").val() == ''){
						return ;
					}
					$.ajax({
						type : 'POST',
						url : 'ProjectUser_update.action',
						dataType: "json",
						data: $('#addForm').serialize(),
						success: function(msg){
							if(!msg.success){
								$('#msgDiv').html(msg.message);
								return ;
							}
							$('#msgDiv').html($("#inCharge").val() + '修改成功!');
						}
					});
				});
			})
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="msgDiv" style="color: red">
				</div>
				<form id="addForm" action="ProjectStartUser_add.action" method="post" >
					<input type="hidden" name="user.id" value="${user.id}" />
					<input type="hidden" name="user.root.id" value="${user.root.id}" />
					<input type="hidden" name="user.user.id" value="${user.user.id}" />
					<table class="table" style="width: 50%" >
						<tr>
							<th colspan="4">添加项目组成员</th>
						</tr>
						<tr>
							<th  align="right">工号</th>
							<td><input id="userCard" name="p.userCard" value="${user.user.code}"/></td>
							<th align="right">姓名</th>
							<td><input readonly="readonly" id="inCharge" value="${user.user.name}"/></td>
						</tr>
						<tr>
							<th align="right">部门</th>
							<td><input readonly="readonly" id="dept" value="${user.user.dept}" /></td>
							<th align="right">手机</th>
							<td><input name="user.telphone" value="${user.telphone}"/></td>
						</tr>
						<tr>
							<th align="right">邮箱</th>
							<td><input name="user.email" value="${user.email}"/></td>
							<th align="right">用户组</th>
							<td>
								<select name="user.pGroup">
									<s:if test="user.pGroup.equals('项目成员')">
										<option value="项目成员" selected="selected">项目成员</option>
										<option value="项目相关">项目相关</option>
										<option value="项目负责人">项目负责人</option>
									</s:if><s:elseif test="user.pGroup.equals('项目相关')">
										<option value="项目成员" >项目成员</option>
										<option value="项目相关" selected="selected">项目相关</option>
										<option value="项目负责人">项目负责人</option>
									</s:elseif><s:elseif test="user.pGroup.equals('项目负责人')">
										<option value="项目成员" >项目成员</option>
										<option value="项目相关">项目相关</option>
										<option value="项目负责人" selected="selected">项目负责人</option>
									</s:elseif>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">职责</th>
							<td colspan="3">
								<textarea rows="6" cols="40" name="user.responsibilities">${user.responsibilities}</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" >
								<input id="addButton" type="button" value="提交"/>
								<input type="reset" value="清空" />
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
	</body>
</html>
