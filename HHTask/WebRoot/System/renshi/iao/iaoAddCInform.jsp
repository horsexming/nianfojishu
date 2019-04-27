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
	<body onload="createDept('dept')">
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
				<div>
					<font size='4' color="green"><B>添加人员</B></font></br>
				</div>
				<div>
					<form action="InformAction!addCome.action" method="post" id="xform">
					
					
						<table class="table" width="100%">
						<tr>
							<td align="right" style='width:25%'>
								部门:
							</td>
							<td align="left" style='width:25%'>
									<select id="dept" style="width: 155px;" name="leaveInform.dept"
										onchange="find();">
										<option>
											
										</option>
									</select>
								</td>
							<td align="right" style='width:25%'>
								姓名:
							</td>	
							<td align="left" style='width:25%'>
									<input name="leaveInform.username" id="userName" type="hidden" />
									<select id="users" style="width: 155px;">
									</select>
								</td>						
							
							
						</tr>
							<tr>
								<td align="right">
								工号:
								</td>
								
								<td align="left">
									<input type="text" id="leavePersonCode" name="leaveInform.code"
										readonly="readonly" />
								</td>
								<td align="right">选择添加类型:
								</td>
								<td  align="left">
									<select id="type1" style="width: 155px;" onchange="titi();">
										<option></option>
										<option value="1">添加离职通知</option>
										<option value="2">添加调职通知</option>	
										<option value="3">添加新入职通知</option>
										<option value="4">添加实习入职通知</option>
									</select>
								</td>
							</tr>
							<tr></tr>
							<tr>
								<td colspan="4" align="center">
								<font size='4' color="green"><B>${show}</B></font></br>
								</td>
							</tr>
								<!-- 
							<tr>
								<td colspan="6" align="center">
									<input type="button" value="离职职确认"
										style="width: 100px; height: 50px;" onclick="fuck4();"/>
									<input type="button" value="调职确认"
										style="width: 100px; height: 50px;" onclick="fuck1();"/>
									<input type="button" value="(新进)入职确认"
										style="width: 100px; height: 50px;" onclick="fuck2();"/>
									<input type="button" value="(实习)入职确认"
										style="width: 100px; height: 50px;" onclick="fuck3();"/>
								</td>
							</tr>  -->
						</table> 
						<table class="table" id="tt">
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
	document.forms.xform.action = "InformAction!addCome.action";
	document.forms.xform.submit();
}
function fuck2(){
	document.forms.xform.action = "InformAction!addCame.action";
	document.forms.xform.submit();
}
function fuck3(){
	document.forms.xform.action = "InformAction!addCame1.action";
	document.forms.xform.submit();
}
function fuck4(){
	document.forms.xform.action = "InformAction!start.action";
	document.forms.xform.submit();
}
function titi(){
	$("#tt").empty();
	if($("#type1").val() == "1"){
		$("<tr>" +"<td  align='right' style='width:25%'>离职原因:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.reason'></td>"
			+"<td style='width:25%'></td><td style='width:25%'></td>"
		+"</tr>"
		+"<tr>" 
		+"<td colspan='4' align='center'><input type='button'  value='确认'"
		+"style='width: 50px; height: 50px;' onclick='fuck4();''></td>"
		+"</tr>").appendTo("#tt");
	}
	if($("#type1").val() == "3"){
		$("<tr>" +"<td align='right' style='width:25%'>分配职务:</td>"
			+"<td  align='left' style='width:25%'><input type='text'name='leaveInform.fuck3'></td>"
			+"<td align='right' style='width:25%'>试用期:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.fuck4'></td>"
		+"</tr>"
		+"<tr>" +"<td align='right'>合同编号:</td>"
			+"<td align='left'><input type='text'name='leaveInform.fuck5'></td>"
			+"<td align='right'>月标准工资:</td>"
			+"<td align='left'><input type='text'name='leaveInform.shuoming'></td>"
		+"</tr>"
		+"<tr>" +"<td align='right'>起薪日期:</td>"
			+"<td align='left'><input type='text'name='leaveInform.starttime'></td>"
			+"<td></td>"
			+"<td></td>"
		+"</tr>"
		+"<tr>" 
		+"<td  colspan='4' align='center'><input type='button' " +"value='确认'"
		+"style='width: 50px; height: 50px;' onclick='fuck2();''></td>"
		+"</tr>").appendTo("#tt");
	}
	if($("#type1").val() == "4"){
		$("<tr>" +"<td align='right' style='width:25%'>分配职务:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.fuck3'></td>"
			+"<td align='right' style='width:25%'>试用期:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.fuck4'></td>"
		+"</tr>"
		+"<tr>" +"<td align='right' style='width:25%'>合同编号:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.fuck5'></td>"
			+"<td align='right'>月标准工资:</td>"
			+"<td align='left'><input type='text'name='leaveInform.shuoming'></td>"
		+"</tr>"
		+"<tr>" +"<td align='right'>起薪日期:</td>"
			+"<td align='left'><input type='text'name='leaveInform.starttime'></td>"
			+"<td></td>"
			+"<td></td>"
		+"</tr>"
		+"<tr>" 
		+"<td  colspan='4' align='center'><input type='button'  value='确认'"
		+"style='width: 50px; height: 50px;' onclick='fuck3();''></td>"
		+"</tr>").appendTo("#tt");
	}
	if($("#type1").val() == "2"){
		$("<tr>" +"<td align='right' style='width:25%'>原职:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.fuck3'></td>"
			+"<td align='right' style='width:25%'>新职:</td>"
			+"<td align='left' style='width:25%'><input type='text'name='leaveInform.fuck4'></td>"
		+"</tr>"
		+"<tr>" +"<td align='right'>新单位起薪:</td>"
			+"<td align='left'><input type='text'name='leaveInform.starttime'></td>"
			+"<td align='right'>试 岗 期:</td>"
			+"<td align='left'><input type='text'name='leaveInform.shuoming'></td>"
		+"</tr>"
		+"<td  colspan='4' align='center'><input type='button'  value='确认'"
		+"style='width: 50px; height: 50px;' onclick='fuck1();''></td>"
		+"</tr>").appendTo("#tt");
	}
	
	
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
