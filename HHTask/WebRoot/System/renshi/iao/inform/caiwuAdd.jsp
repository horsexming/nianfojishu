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
	<body  onload="createDept('dept')">
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
				<div align="center">
					<font size='4' color="green"><B>添加被通知人员</B></font></br>
				</div>
				<div>
					<form action="InformAction!addCome.action" method="post" id="xform">
					
					
						<table class="table">
						<tr>
							<td align="center">
								部门
							</td>
							<td align="center">
								姓名
							</td>							
							<td align="center">
								工号
							</td>
						</tr>
							<tr>
								<td>
									<select id="dept" style="width: 155px;" name="leaveInform.dept"
										onchange="find();">
										<option value="${Users.dept}">
											${Users.dept}
										</option>
									</select>
								</td>
								<td>
									<input name="leaveInform.username" id="userName" type="hidden" />
									<select id="users" style="width: 155px;">
									</select>
								</td>
								<td>
									<input type="text" id="leavePersonCode" name="leaveInform.code"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
								<font size='4' color="red"><B>${show}</B></font></br>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="button" value="离职工资调整"
										style="width: 100px; height: 50px;" onclick="fuck1();"/>
									<input type="button" value="入职工资确认"
										style="width: 100px; height: 50px;" onclick="fuck2();"/>
									<input type="button" value="内退工资确认"
										style="width: 100px; height: 50px;" onclick="fuck3();"/>
									<input type="button" value="个人工资调整"
										style="width: 100px; height: 50px;" onclick="fuck4();"/>
								</td>
							</tr>
						</table> 
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function fuck1(){
	document.forms.xform.action = "InformAction!Cstart.action";
	document.forms.xform.submit();
}
function fuck2(){
	document.forms.xform.action = "InformAction!caiwuruzhiAdd.action";
	document.forms.xform.submit();
}
function fuck3(){
	document.forms.xform.action = "InformAction!caiwuneituiAdd.action";
	document.forms.xform.submit();
}
function fuck4(){
	document.forms.xform.action = "InformAction!caiwugerenAdd.action";
	document.forms.xform.submit();
}
function find() {
	if ($("#dept").val() != "") {
		$.ajax( {
			url : "UsersAction!findUsersByDept.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			data : {
				deptName : $("#dept").val()
			},
			success : function(useradsfa) {
				$("#users").empty();//清空
				$("<option></option>").appendTo("#users");
				$(useradsfa).each(
						function() {
							$(
									"<option value='" + this.code + "|"
											+ this.name + "'>" + this.name
											+ "</option>").appendTo("#users")

						});
			},
			error : function() {
				alert("服务器异常!");
			}
		});
		$("#users").bind("change", function() {
			var users = $("#users").val();
			var usersData = users.split("|");
			var code = usersData[0];
			var userName = usersData[1];
			$("#leavePersonCode").val(code);
			$("#userName").val(userName);
		});
	}
}
		</script>
	</body>
</html>
