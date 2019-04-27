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
				$("#jobNum").bind('blur',function(){
					$.ajax({
						type: 'POST',
						url: 'UsersAction!findByCode.action',
						dataType: "json",
						data: "user.code=" + $("#jobNum").val(),
						success: function(msg){
							if(!msg.success){
								alert('没有该人员!请核对后再输!');
								return;
							}
							$('#name').val(msg.data.name);
							$('#dept').val(msg.data.dept);
							
						}
					});
			});
			});
		</SCRIPT>
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
				<h3>添加年休人员</h3>
				<form action="annualLeave_add.action" method="post">
				<table class="table">
					<tr><th align="right">工号：</th><td><input type="text" name="al.jobNum" id="jobNum"/></td></tr>
					<tr><th align="right">姓名：</th><td><input type="text" name="al.name" id="name"/></td></tr>
					<tr><th align="right">部门	: </th><td><input type="text" name="al.dept" id="dept" /></td></tr>
					<tr><th align="right">工龄：</th><td><input type="text" name="al.lengthOfService"/></td></tr>
					<tr><th align="right">累计可用年休：</th><td><input type="text" name="al.surplus"/></td></tr>
					<tr><th align="right" rowspan="2">备注：</th><td rowspan="2"><input type="text" name="al.remark" style="width: 300px;height: 70px;"/>
							<input type="hidden" name="al.standardAnnualLeave" value="0"/></td></tr>	
							<tr><td></td><td></td></tr>
					<tr><td align="center" colspan="2"><input type="submit" value="添加" class="input" /></td></tr>
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
