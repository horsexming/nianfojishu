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
				$("#addForm [name='p.userCard']").bind('blur',function(){
					$.ajax({
						type: 'POST',
						url: 'UsersAction!findByCode.action',
						dataType: "json",
						data: "user.code=" + $("#addForm [name='p.userCard']").val(),
						success: function(msg){
							if(msg.success){
								$("#addForm [name='p.dept']").val(msg.data.dept);
								$("#addForm [name='p.inCharge']").val(msg.data.name);
								$("#addForm [name='p.telphone']").val(msg.data.password.phoneNumber);
								$("#addForm [name='p.email']").val(msg.data.password.mailBox);
								$("#addForm [name='p.userId']").val(msg.data.id);
							}
						}
					});
				});
				
				$('#submitBtn').bind('click', function(){
					$.ajax({
						type: 'POST',
						url: 'ProjectStartUser_add.action',
						dataType: "json",
						data: $('#addForm').serialize(),
						success: function(jsonData){
							alert(jsonData.message);
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
				<div id="formMsg"></div>
				<form id="addForm">
					<input type="hidden" name="p.root.id" value="${p.root.id}" />
					<input type="hidden" name="p.userId"/>
					<table class="table" style="width: 50%" >
						<tr>
							<th colspan="4">添加项目组成员</th>
						</tr>
						<tr>
							<th  align="right">工号</th>
							<td><input name="p.userCard"/></td>
							<th align="right">姓名</th>
							<td><input name="p.inCharge" readonly="readonly"/></td>
						</tr>
						<tr>
							<th align="right">部门</th>
							<td><input name="p.dept" readonly="readonly" /></td>
							<th align="right">手机</th>
							<td><input name="p.telphone"/></td>
						</tr>
						<tr>
							<th align="right">邮箱</th>
							<td><input name="p.email"/></td>
							<th align="right">用户组</th>
							<td>
								<select name="p.pGroup">
									<option value="项目组">项目组</option>
									<option value="项目相关">项目相关</option>
									<option value="项目负责人">项目负责人</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">职责</th>
							<td colspan="3">
								<textarea rows="6" cols="40" name="p.responsibilities"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" >
								<input id="submitBtn" type="button" value="提交"/>
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
