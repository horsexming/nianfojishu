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
				<h3>修改年休信息</h3>
				<form action="annualLeave_update.action" method="post">
				<table  class="table">
					<tr><th align="right">工号：</th><td><input type="text" id="jobNum" name="al.jobNum" value="${al.jobNum }"/></td></tr>
					<tr><th align="right">姓名：</th><td><input type="text" name="al.name" value="${al.name }" id="name"/></td></tr>
					<tr><th align="right">部门：</th><td><input type="text" name="al.dept" id="dept" value="${al.dept}"/></td></tr>
					<tr><th align="right">工龄：</th><td><input type="text" name="al.lengthOfService" value="${al.lengthOfService}"/></td></tr>
					<Tr><th align="right">当年应享受年休：</th><td><input type="text" readonly="readonly" name="al.standardAnnualLeave" value="${al.standardAnnualLeave }"/></td></Tr>
					<tr><th align="right">累计可用年休：</th><td><input type="text" name="al.surplus" value="${al.surplus }"/></td></tr>
					<tr style="height: 70px;"><th align="right">备注：</th><td><input type="text" style="width: 300px;height: 70px;" name="al.remark" value="${al.remark }"/></td></tr>	
					<tr><td align="center" colspan="2">
					<input type="hidden" name="al.id" value="${al.id }"/>
					<input type="hidden" name="al.status" value="${al.status}"/>
					<input type="submit" value="修改" style="width: 80px; height: 50px;"/></td></tr>
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
