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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.min.js">
</script>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="UserRoleAction_addusers.action" id="myform"
					method="post">

					<table class="table" style="width: 60%">
						<tr>
							<th>
							</th>
							<td>
								原有人员
							</td>
							<th>
							</th>
							<td>
								获得功能人员
							</td>

						</tr>
						<tr>
							<th align="right">
								所在部门:
							</th>
							<td align="left">
								<select id="dept" style="width: 155px;">
									<option 
									>
									</option>
								</select>
							</td>
							<th align="right">
								所在部门:
							</th>
							<td align="left">
								<select id="dept2" style="width: 155px;">
									<option 
									>
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								人&nbsp;员:&nbsp;
							</th>
							<td align="left">
								<input id="userName" type="text" readonly="readonly"
									>
								<input id="personid" type="hidden"  name="Copyuserid" />
								<select id="users" style="display: none; width: 155px;">
								</select>
							</td>
							<th align="right">
								人&nbsp;员:&nbsp;
							</th>
							<td align="left">
								<input id="userName2" type="text" readonly="readonly"
									>
								<input id="personid2" type="hidden"  name="Pasteuserid"/>
								<select id="users2" style="display: none; width: 155px;">
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								人员工号:
							</th>
							<td align="left">
								<input type="text" id="personCode"
									readonly="readonly" />
							</td>
							<th align="right">
								人员工号:
							</th>
							<td align="left">
								<input type="text" id="personCode2" 
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="button" id="submitBtn" value="拷贝功能"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table>


				</table>

			</div>
			<br>

		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
changeType("2");
function submit() {
	$.ajax( {
		type : "POST",
		url : "UserRoleAction_addusers.action",
		url : "ModuleFunctionAction!copymf.action",
		data : $('#myform').serialize(),
		dataType : "json",
		success : function(json) {
			alert(json.message);
		}
	});
}
//<<=====================================>>
function changeType(type) {
	if (type == "1") {
		$("#userName").val("${Users.name}");
		$("#userName").css("display", "block");
		$("#users").css("display", "none");
		$("#userName").attr("readonly", "readonly");
		$("#dept").empty();
		$("<option value='${Users.dept}'>${Users.dept}</option>").appendTo(
				"#dept");
	} else if (type == "2") {
		$("#userName").css("display", "none");
		$("#users").css("display", "block");
		$("#personCode").val("");
		$("#userName2").css("display", "none");
		$("#users2").css("display", "block");
		$("#personCode2").val("");
		//显示所有部门信息
		$.ajax( {
			url : 'DeptNumberAction!findAllDept.action',
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(allDdept) {

				$("#dept").empty();
				$("<option value=''>--请选择部门--</option>").appendTo("#dept");
				$(allDdept).each(
						function() {
							$(
									"<option value='" + this.dept + "'>"
											+ this.dept + "</option>")
									.appendTo("#dept");
						});

				$("#dept2").empty();
				$("<option value=''>--请选择部门--</option>").appendTo("#dept2");
				$(allDdept).each(
						function() {
							$(
									"<option value='" + this.dept + "'>"
											+ this.dept + "</option>")
									.appendTo("#dept2");
						});
			}
		});
		//显示部门对应的员工信息
		$("#dept").bind(
				"change",
				function() {
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
													"<option value='"
															+ this.code + "|"
															+ this.id + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
													.appendTo("#users")
										});
							},
							error : function() {
								alert("服务器异常!");
							}
						});
					}
				});
		//显示部门对应的员工信息2
		$("#dept2").bind(
				"change",
				function() {
					if ($("#dept2").val() != "") {
						$.ajax( {
							url : "UsersAction!findUsersByDept.action",
							type : 'post',
							dataType : 'json',
							cache : false,//防止数据缓存
							data : {
								deptName : $("#dept2").val()
							},
							success : function(useradsfa) {
								$("#users2").empty();//清空
								$("<option></option>").appendTo("#users2");
								$(useradsfa).each(
										function() {
											$(
													"<option value='"
															+ this.code + "|"
															+ this.id + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
													.appendTo("#users2")
										});
							},
							error : function() {
								alert("服务器异常!");
							}
						});
					}
				});
	}
	
	$("#users").bind("change", function() {
		var users = $("#users").val();
		var usersData = users.split("|");
		var id = usersData[1];
		$("#userName").val(usersData[2]);
		$("#personCode").val(usersData[0]);
		$("#personid").val(id);
	});
	
	$("#users2").bind("change", function() {
		var users = $("#users2").val();
		var usersData = users.split("|");
		var id = usersData[1];
		$("#userName2").val(usersData[2]);
		$("#personCode2").val(usersData[0]);
		$("#personid2").val(id);
	});

}

//<<===================over ==================>>

$("#submitBtn").click(function() {
				document.getElementById("submitBtn").disabled=true;
				submit();

})

</script>
	</body>
</html>