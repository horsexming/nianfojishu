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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>
					添加初始积分
				</h2>
				<form action="IntegralAction_test.action" method="post"
					onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								<select name="integral.integrdept" id="dept"
									style="width: 155px;" onmouseout="showzpname()">
									<option value=""></option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								姓名:
							</th>
							<td>
								<select name="" id="users" style="width: 155px;" onclick="sel()">
									<option value=""></option>
								</select>
								<input type="hidden" name="integral.integralName" id="userName"
									value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								开始时间:
							</th>
							<td>
								<input class="Wdate" type="text" name="start"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							</tr>
							<tr>
							<th align="right">
								结束时间:
							</th>
							<td>
								<input class="Wdate" type="text" name="end"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								<input type="hidden" id="userId" value="" name="userId">
								<input type="hidden" id="code" name="integral.integrcode" />
								<input type="hidden" id="rebeack" value='${successMessage}' />
								<input type="submit" value="消费"
									style="width: 70px; height: 30px;" id="sub">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var select;
function showzpname() {
	deptname = document.getElementById("dept").value;
	select = document.getElementById("users");
	if (deptname != "") {
		$.ajax( {
			type : "POST",
			url : "UsersAction!findUsersByDept.action",
			data : {
				deptName : deptname
			},
			dataType : "json",
			success : function(useradsfa) {
				$("#users").empty();//清空
			$("<option></option>").appendTo("#users");
			$(useradsfa).each(
					function() {
						$(
								"<option value='" + this.code + "|" + this.name
										+ "|" + this.id + "|" + this.cardId
										+ "'>" + this.name + "</option>")
								.appendTo("#users")
					});
			$("#users").bind("change", function() {
				var user = $("#users").val();
				var userCodeName = user.split("|");
				if (userCodeName != "") {
					$("#code").val(userCodeName[0]);
					$("#userName").val(userCodeName[1]);
					$("#userId").val(userCodeName[2]);
					$("#cardId").val(userCodeName[3]);
				} else {
					$("#code").val("");
					$("#userName").val("");
				}
			})

		},
		error : function() {
			alert("服务器异常!");
		}
		});
	} else {
		$("#users").empty();//清空
	}
}
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		var xfjf=$("#xfjf").text();
		if(rebeack =="成功!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
function sel(){
	var dept=document.getElementById("dept").value;
	if(dept==""){
		alert("请先选择部门")
	}
}
function check(){
	var dept =document.getElementById("dept");
	var users =document.getElementById("users");
	var code = document.getElementById("code");
	var addintegral = document.getElementById("addintegral");
	var laiyuan = document.getElementById("laiyuan");
	var reg='^[1-9]\d*|0$';
	var value=addintegral.value;
	var r=value.match(reg);
	if(dept!=null && dept.value == ""){
		alert("请先选择部门")
		dept.focus();
		return false;
	}else if(users!=null && users.value == ""){
		alert("请选择员工姓名");
		users.focus();
		return false;
	}else if(code!=null && code.value == ""){
		alert("请选择员工编号");
		code.focus();
		return false;
	}else if(addintegral !=null && r==null){
		alert("请输入非负整数");
		addintegral.focus();
		return false;
	}else if(laiyuan!=null && laiyuan.value == ""){
		alert("请输入积分来源");
		laiyuan.focus();
		return false ;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}
</SCRIPT>
	</body>
</html>
